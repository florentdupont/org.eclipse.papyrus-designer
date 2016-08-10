/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.components.transformation.cpp.xtend

import org.eclipse.papyrus.designer.components.transformation.extensions.IOOTrafo
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.Port
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.ConnectorEnd
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr
import org.eclipse.uml2.uml.Type
import java.util.HashMap
import java.util.Map
import org.eclipse.uml2.uml.Connector
import org.eclipse.emf.common.util.EList
import org.eclipse.papyrus.designer.components.transformation.cpp.Messages
import org.eclipse.papyrus.designer.components.transformation.cpp.Constants
import static extension org.eclipse.papyrus.designer.components.transformation.cpp.xtend.CppUtils.nameRef;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil
import org.eclipse.papyrus.designer.components.transformation.PortUtils
import org.eclipse.papyrus.designer.components.transformation.transformations.PrefixConstants
import org.eclipse.papyrus.designer.components.transformation.PortInfo
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtil
import org.eclipse.papyrus.designer.transformation.base.utils.CopyUtil
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException

/**
 * This class realizes the dynamic variant of the OO-transformation 
 *
 */
class DynamicCppToOO implements IOOTrafo {

	protected LazyCopier copier

	val static final String PART_MANAGER = "services::PartManager"

	val static final String INIT_PARTS = "initParts"

	// 	name of generic "parts" attribute for dynamic deployment (TODO: should be inherited)
	val static final String PARTS = "parts"
	
	val static final String progLang = "C/C++"
	
	protected Class bootloader // why required?

	def override init(LazyCopier copier, Class bootloader) {
		this.copier = copier
		this.bootloader = bootloader
	}

	override addPortOperations(Class implementation) {
		addGetPortOperation(implementation)
		addConnectPortOperation(implementation)
	}

	/**
	 * Add the get_p operation for each port with a provided interface. It also
	 * adds a suitable implementation that evaluates delegation connectors from
	 * the port to a property within the composite. The delegation target could
	 * either be a normal class (no port) or an inner component.
	 *
	 * @param implementation
	 */
	def addGetPortOperation(Class implementation) {
		for (PortInfo portInfo : PortUtils.flattenExtendedPorts(PortUtils.getAllPorts2(implementation))) {
			val providedIntf = portInfo.getProvided()
			if (providedIntf != null) {

				// port provides an interface, add "get_p" operation &
				// implementation
				val opName = PrefixConstants.getP_Prefix + portInfo.name
				var op = implementation.getOwnedOperation(opName, null, null)
				if (op != null) {

					// operation already exists. Assume that user wants to
					// override standard delegation
					if (op.type != providedIntf) {
						op.createOwnedParameter(Constants.retParamName, providedIntf)
					}
				} else {
					op = implementation.createOwnedOperation(opName, null, null, providedIntf)
					val retParam = op.getOwnedParameters().get(0)
					retParam.setName(Constants.retParamName)
					StereotypeUtil.apply(retParam, Ptr)

					val behavior = implementation.createOwnedBehavior(opName, UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior
					op.getMethods().add(behavior)

					val ce = ConnectorUtil.getDelegation(implementation, portInfo.getModelPort())

					// if there is an delegation to an inner property, delegate to
					// it
					// Make distinction between delegation to component (with a
					// port) or
					// "normal" class (without).
					var String body
					if (ce != null) {
						val part = ce.partWithPort
						val role = ce.role

						body = "return " 
						if (role instanceof Port) {

							// check whether the part exists within the implementation (might not be the case
							// due to partially copied composites).
							// Check is based on names, since the connector points to elements within another
							// model (copyClassifier does not make a proper connector copy)
							// TODO: this will NOT work for extended ports!
							body += '''«part.nameRef»«PrefixConstants.getP_Prefix»«role.name»();'''
						} else {

							// role is not a port: connector connects directly to a
							// structural feature
							// without passing via a port
							// TODO: check whether structural feature exists
							body += role.name
						}
					} else {

						// no delegation, check whether port implements provided interface
						var implementsIntf = implementation.getInterfaceRealization(null, providedIntf) != null
						if (!implementsIntf) {

							// The extended port itself is not copied to the target
							// model (since referenced via a stereotype). Therefore,
							// a port of an extended port still points to the
							// original model. We try whether the providedIntf
							// within
							// the target model is within the interface
							// realizations.
							val providedIntfInCopy = copier.getCopy(providedIntf)
							implementsIntf = implementation.getInterfaceRealization(null, providedIntfInCopy) != null
						}
						if (implementsIntf) {
							body = "return this;" 
						} else {
							throw new RuntimeException(
								String.format(Messages.CompImplTrafos_IntfNotImplemented, providedIntf.name,
									portInfo.port.name, implementation.name))
						}
					}
					behavior.getLanguages().add(Constants.progLang)
					behavior.getBodies().add(body)
				}
			}
		}
	}

	/**
	 * Add a connect_<portName> operation for ports with a required interface.
	 * Whereas operation and a behavior is added for each owned port, a behavior
	 * (method) is needed for ports inherited from a component type (the
	 * behavior is implementation specific, as it needs to take delegation to
	 * parts into account)
	 *
	 * @param implementation
	 */
	static def addConnectPortOperation(Class implementation) {
		for (PortInfo portInfo : PortUtils.flattenExtendedPorts(PortUtils.getAllPorts2(implementation))) {
			val requiredIntf = portInfo.getRequired()
			if (requiredIntf != null) {

				// port requires an interface, add "connect_p" operation &
				// implementation
				val opName = PrefixConstants.connectQ_Prefix + portInfo.name

				if (implementation.getOwnedOperation(opName, null, null) != null) {
					// do not add the operation, if it already exists. This means that the
					// user wants to override it with custom behavior. In case of extended
					// ports, we may have to do that.
				} else {
					var op = implementation.createOwnedOperation(opName, null, null)
					val boolean multiPort = (portInfo.getUpper() > 1) || (portInfo.getUpper() == -1) // -1 indicates "*"
					if (multiPort) {

						// add index parameter
						val eLong = ElementUtil.getQualifiedElement(PackageUtil.getRootPackage(implementation),
							PrefixConstants.INDEX_TYPE_FOR_MULTI_RECEPTACLE)
						if (eLong instanceof Type) {
							op.createOwnedParameter("index", eLong as Type) 
						} else {
							throw new RuntimeException(
								String.format(Messages.CompImplTrafos_CannotFindType,
									PrefixConstants.INDEX_TYPE_FOR_MULTI_RECEPTACLE))
						}
					}
					val refParam = op.createOwnedParameter("ref", requiredIntf) 
					StereotypeUtil.apply(refParam, Ptr)

					val behavior = implementation.createOwnedBehavior(opName, UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior

					op.getMethods().add(behavior)

					val ConnectorEnd ce = ConnectorUtil.getDelegation(implementation, portInfo.getModelPort())

					// if there is an delegation to an inner property, delegate to it
					// Make distinction between delegation to component (with a port) or
					// "normal" class (without).
					var String body
					if (ce != null) {
						val part = ce.partWithPort
						body = part.name
						val role = ce.role
						if (role instanceof Port) {
							// in case of a delegation, use name of target port which might be different
							val targetOpName = PrefixConstants.connectQ_Prefix + role.name
							body = '''«part.nameRef»«targetOpName»'''

							// TODO: no check that multiplicity of both port matches
							if ((portInfo.getUpper() > 1) || (portInfo.getUpper() == -1)) {
								body += "(index, ref);";
							}
							else {
								body += "(ref);";
							}

						} else {
							// TODO: does this case make sense?
							body += '''«part.name»;'''
						}
					} else {
						// no delegation - create attribute for port
						val attributeName = PrefixConstants.attributePrefix + portInfo.name
						var attr = implementation.getOwnedAttribute(attributeName, null)
						if (attr == null || attr instanceof Port) {
							attr = implementation.createOwnedAttribute(attributeName, requiredIntf)
							CopyUtil.copyMultElemModifiers(portInfo.port, attr)

							// is shared (should store a reference)
							attr.setAggregation(AggregationKind.SHARED_LITERAL)
						}
						body = attributeName
						if(multiPort) body += "[index]"
						body += " = ref;"
					}

					// TODO: defined by template
					behavior.getLanguages().add(Constants.progLang)
					behavior.getBodies().add(body)

					// -------------------------
					// add body to get-connection operation (which exists already if the port is also
					// owned, since it is synchronized automatically during model edit)
					// getConnQ prefix may be empty to indicate that the port is accessed directly
					// TODO: reconsider optimization that delegated required ports do not have a
					// local attribute & associated operation (an inner class may delegate, but the
					// composite may be using it as well).
					if ((PrefixConstants.getConnQ_Prefix.length() > 0) && (ce != null)) {
						val getConnOpName = PrefixConstants.getConnQ_Prefix + portInfo.name
						var getConnOp = implementation.getOwnedOperation(getConnOpName, null, null)
						if (getConnOp == null) {
							getConnOp = implementation.createOwnedOperation(getConnOpName, null, null, requiredIntf)
							val retParam = op.getOwnedParameters().get(0)
							retParam.setName(Constants.retParamName)
							StereotypeUtil.apply(retParam, Ptr)
						}
						val getConnBehavior = implementation.createOwnedBehavior(getConnOpName,
							UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior
						getConnOp.getMethods().add(getConnBehavior)

						// no delegation
						val String name = PrefixConstants.attributePrefix + portInfo.name
						body = '''return «name»;'''
						behavior.getLanguages().add(Constants.progLang)
						behavior.getBodies().add(body)
					}
				}
			}
		}
	}

	/**
	 * Add an operation "createConnections" that implements the connections
	 * between composite parts. It only takes the assembly connections into
	 * account, since delegation connectors are handled by the get_ and connect_
	 * port operations above.
	 *
	 * @param implementation
	 */
	override addConnectionOperation(Class compositeImplementation) throws TransformationException {
		var createConnBody = "" 
		val Map<ConnectorEnd, Integer> indexMap = new HashMap<ConnectorEnd, Integer>()

		for (Connector connector : compositeImplementation.getOwnedConnectors()) {
			if (ConnectorUtil.isAssembly(connector)) {

				// Boolean associationBased = false
				if (connector.ends.size() != 2) {
					throw new TransformationException(
						'''Connector <«connector.name»> does not have two ends. This is currently not supported''')
				}
				val end1 = connector.ends.get(0)
				val end2 = connector.ends.get(1)
				var cmd = '''// realization of connector <«connector.name»>\n'''
				if ((end1.role instanceof Port) && PortUtils.isExtendedPort(end1.role as Port)) {
					val port = end1.role as Port
					val EList<PortInfo> subPorts = PortUtils.flattenExtendedPort(port)
					for (PortInfo subPort : subPorts) {
						cmd += '''  // realization of connection for sub-port «subPort.port.name»\n'''
						cmd += connectPorts(indexMap, connector, end1, end2, subPort.port)
						cmd += connectPorts(indexMap, connector, end2, end1, subPort.port)
					}
				} else {
					cmd += connectPorts(indexMap, connector, end1, end2, null)
					cmd += connectPorts(indexMap, connector, end2, end1, null)
				}
				createConnBody += cmd + "\n" 
			}
		}

		// TODO: use template, as in bootloader
		if (createConnBody.length() > 0) {
			val operation = compositeImplementation.createOwnedOperation(Constants.CREATE_CONNECTIONS, null, null)

			val behavior = compositeImplementation.createOwnedBehavior("b:" + operation.name, 
				UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior
			behavior.getLanguages().add(Constants.progLang)
			behavior.getBodies().add(createConnBody)
			behavior.setSpecification(operation)
		}
	}

	/**
	 * Create the body C++ code code that creates a connection between the two ends
	 * of a connector. This function checks whether the first end really is a receptacle
	 * and the second really is a facet.
	 * TODO: cleaner rewrite in xtend
	 * 
	 * @param indexMap
	 *            a map of indices that are used in case of multiplex
	 *            receptacles
	 * @param connector
	 *            a connector
	 * @param receptacleEnd
	 *            an end of the connector that may point to a receptacle port
	 * @param facetEnd
	 *            an end of the connector that may point to a facet port
	 * @param subPort
	 *            a sub-port in case of extended ports
	 * @return
	 * @throws TransformationException
	 */
	static def connectPorts(Map<ConnectorEnd, Integer> indexMap, Connector connector, ConnectorEnd receptacleEnd,
		ConnectorEnd facetEnd, Port subPort) throws TransformationException {
		val association = connector.type
		if ((receptacleEnd.role instanceof Port) && (facetEnd.role instanceof Port)) {
			val facetPort = facetEnd.role as Port
			val receptaclePort = receptacleEnd.role as Port
			val facetPI = PortInfo.fromSubPort(facetPort, subPort)
			val receptaclePI = PortInfo.fromSubPort(receptaclePort, subPort)

			if ((facetPI.getProvided() != null) && (receptaclePI.getRequired() != null)) {
				val facetPart = facetEnd.partWithPort
				val receptaclePart = receptacleEnd.partWithPort

				var subPortName = ""
				if(subPort != null) subPortName += "_" + subPort.name
				val indexName = getIndexName(indexMap, receptaclePort, receptacleEnd)
				val setter = '''«receptaclePart.nameRef»connect_«receptaclePort.name» «subPortName»;'''
				val getter = '''«facetPart.nameRef»get_«facetPort.name» «subPortName»()'''
				return '''«setter»(«indexName»«getter»);\n'''
				}

		} else if (receptacleEnd.role instanceof Port) {

			// only the receptacle end is of type port.
			val Port receptaclePort = receptacleEnd.role as Port
			if (PortUtils.getRequired(receptaclePort) != null) {
				val facetPart = facetEnd.role as Property
				val receptaclePart = facetEnd.partWithPort

				val indexName = getIndexName(indexMap, receptaclePort, receptacleEnd)
				val setter = '''«receptaclePart.nameRef»connect_«receptaclePort.name»'''
				val getter = '''&«facetPart.name»'''
				return '''«setter»(«indexName»«getter»);\n'''
			}
		} else if (facetEnd.role instanceof Port) {

			// only the facet end is of type port. Unsupported combination
			val facetPort = facetEnd.role as Port
			if (PortUtils.getProvided(facetPort) != null) {
				val facetPart = facetEnd.partWithPort
				val receptaclePart = facetEnd.role as Property

				val setter = receptaclePart.name
				val getter = '''«facetPart.nameRef»get_«facetPort.name»();'''
				return '''«setter» = «getter»;\n'''
			}
		} else if (association != null) {

			// both connector ends do not target ports. In this case, we require that the connector is typed
			// with an association. We use this association to find out which end is navigable and assume that
			// the part pointed to by the other end is a pointer that gets initialized with the part of the
			// navigable end.
			val facetPart = facetEnd.role as Property
			val receptaclePart = receptacleEnd.role as Property

			val assocProp1 = association.getMemberEnd(null, facetPart.type)

			// Property assocProp2 = facetPart.getOtherEnd()
			if ((assocProp1 != null) && assocProp1.isNavigable) {
				val setter = '''«receptaclePart.nameRef»«assocProp1.name»'''
				val getter = '''&«facetPart.name»'''
				return '''«setter» = «getter»;\n'''
			}
		} else {

			// not handled (a connector not targeting a port must be typed)
			throw new TransformationException(
				"Connector <" + connector.name + 
					"> does not use ports, but it is not typed (only connectors between ports should not be typed)") 
		}
		return "" 
	}

	/**
	 * Handle ports with multiplicity > 1. The idea is that we could have
	 * multiple connections targeting a receptacle. The first connection would
	 * start with index 0. Implementations can make no assumption which
	 * connection is associated with a certain index. [want to avoid associative
	 * array in runtime].
	 *
	 * @param port
	 * @param end
	 * @return
	 */
	static def getIndexName(Map<ConnectorEnd, Integer> indexMap, Port port, ConnectorEnd end) {
		if ((port.getUpper() > 1) || (port.getUpper() == -1)) {

			// index depends of combination of property and port, use connector
			// end as key
			var indexValue = indexMap.get(end)
			if (indexValue == null) {
				indexValue = 0
				indexMap.put(end, indexValue)
			}
			var index = indexValue + ", " 
			indexValue++
			indexMap.put(end, indexValue)
			return index
		}
		return ""
	}

	/**
	 * Transform parts if necessary.
	 * 
	 * @param compositeImplementation
	 *            a (composite) component
	 */
	override transformParts(Class compositeImplementation) {

/*
		for (Property attribute : Utils.getParts(compositeImplementation)) {
			val type = attribute.type
			if (type instanceof Class) {
				val cl = type as Class

				// always transform into pointer (enable dynamic creation)
				StereotypeUtil.apply(attribute, Ptr)
			}
		}
*/	
		var String initPartsBody = ""
		for (Property attribute : ElementUtil.getParts(compositeImplementation)) {
			val type = attribute.type;
			if (type instanceof Class) {
				initPartsBody += initPartBody(attribute);
				attribute.destroy();
			}
		}
		
		val partManager = ElementUtil.getQualifiedElement(PackageUtil.getRootPackage(compositeImplementation), PART_MANAGER);
		if (partManager instanceof Type) {
			compositeImplementation.createOwnedAttribute(PARTS, partManager as Type);
		}
		
		val operation = compositeImplementation.createOwnedOperation(INIT_PARTS, null, null);

		val behavior = compositeImplementation.createOwnedBehavior("b:" + operation.name, UMLPackage.eINSTANCE.opaqueBehavior)
			as OpaqueBehavior 
		behavior.getLanguages().add(progLang);
		behavior.getBodies().add(initPartsBody);
	}
	
	def initPartBody(Property part) {
		"parts.add(" + part.getName() + ", " + part.getType() + ")"
	}
}
