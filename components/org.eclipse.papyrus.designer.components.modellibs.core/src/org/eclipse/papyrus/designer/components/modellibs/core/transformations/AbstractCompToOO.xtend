/*****************************************************************************
 * Copyright (c) 2017 CEA LIST.
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

package org.eclipse.papyrus.designer.components.modellibs.core.transformations

import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.Port
import org.eclipse.papyrus.designer.components.transformation.PortInfo
import org.eclipse.papyrus.designer.components.transformation.PortUtils
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.ConnectorEnd
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil
import org.eclipse.uml2.uml.Type
import java.util.HashMap
import java.util.Map
import org.eclipse.uml2.uml.Connector
import org.eclipse.emf.common.util.EList
import org.eclipse.uml2.uml.StructuralFeature
import org.eclipse.papyrus.designer.components.transformation.extensions.IOOTrafo
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.papyrus.designer.components.FCM.Assembly
import org.eclipse.papyrus.designer.transformation.base.utils.CopyUtils
import org.eclipse.papyrus.designer.components.transformation.component.PrefixConstants
import org.eclipse.papyrus.designer.components.transformation.component.PrefixConstants.CIFvariant
import org.eclipse.uml2.uml.MultiplicityElement
import org.eclipse.uml2.uml.Element
import org.eclipse.papyrus.designer.languages.common.base.ElementUtils

/**
 * This class realizes the transformation from component-based to object-oriented
 * models. It includes the replacement of ports and connectors. Ports are
 * replaced with attributes and access operations, connectors within a composite
 * by an operation that creates the initial setup. It is an abstract class that is
 * refined for C++ and Java
 * 
 * 1. add an operation that allows to retrieve the reference to an interface provided
 *    by a port. This operation has a mapping to a specific name, e.g. get_<port_name>
 * 
 * 2. add an operation that allows to connect a specific port.
 *    the connect_q operation (*including a storage attribute*) for a port with a required interface
 * 
 * 3. add an implementation for the getcnx_q operation for a port
 *    with a required interface (the operation itself has been added before)
 * 
 */
abstract class AbstractCompToOO implements IOOTrafo {

	protected LazyCopier copier

	protected String progLang

	public static final String NL = "\n"

	def override init(LazyCopier copier, Class bootloader) {
		PrefixConstants.init(CIFvariant.UML);
		this.copier = copier
	}

	/**
	 * de-reference an attribute. Use language specific mechanism, e.g. attributeName-> in case of a C++ pointer
	 */
	abstract def String nameRef(Property attribute)

	/**
	 * apply a stereotypes that transforms an elements into a reference, a pointer in case of C++
	 */
	abstract def void applyRef(Element element)

	/**
	 * Return the reference of an attribute
	 */
	abstract def String getRef(Property attribute)
	
	override addPortOperations(Class implementation) {
		// only implementations (non abstract classes) have get operations for ports
		if (!implementation.isAbstract) {
			addGetPortOperation(implementation)
		}
		// but all classes need a connection operation, since it does not rely on an implementation 
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

				// port provides an interface, add "get_p" operation and implementation
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
					applyRef(retParam)

					val behavior = implementation.createOwnedBehavior(opName,
						UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior
					op.getMethods().add(behavior)

					val ce = ConnectorUtil.getDelegation(implementation, portInfo.getModelPort())

					// if there is an delegation to an inner property, delegate to it
					// Make distinction between delegation to component (with a port) or
					// "normal" class (without).
					var String body
					if (ce != null) {
						val part = ce.partWithPort
						val role = ce.role

						body = "return "
						if (role instanceof Port) {
							body += '''«part.nameRef»«PrefixConstants.getP_Prefix»«role.name»();'''
						} else {
							// role is not a port: connector connects directly to a
							// structural feature without passing via a port
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
							// within the target model is within the interface
							// realizations.
							val providedIntfInCopy = copier.getCopy(providedIntf)
							implementsIntf = implementation.getInterfaceRealization(null, providedIntfInCopy) != null
						}
						if (implementsIntf) {
							body = "return this;"
						} else {
							throw new RuntimeException(
								String.format(
									"Interface <%s> provided by port <%s> of class <%s> is not implemented by the component itself nor does the port delegate to a part",
									providedIntf.name, portInfo.port.name, implementation.name))
						}
					}
					behavior.getLanguages().add(progLang)
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
	def addConnectPortOperation(Class implementation) {
		for (PortInfo portInfo : PortUtils.flattenExtendedPorts(PortUtils.getAllPorts2(implementation))) {
			val requiredIntf = portInfo.getRequired()
			if (requiredIntf != null) {

				// port requires an interface, add "connect_p" operation and implementation
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
						var eLong = ElementUtils.getQualifiedElementFromRS(copier.source,
							PrefixConstants.INDEX_TYPE_FOR_MULTI_RECEPTACLE)
						if (eLong != null) {
							eLong = copier.getCopy(eLong);
						}
						if (eLong instanceof Type) {
							op.createOwnedParameter("index", eLong)
						} else {
							throw new RuntimeException(
								String.format(
									"Can not find type %s. Thus, unable to create suitable connect operation in component to OO transformation",
									PrefixConstants.INDEX_TYPE_FOR_MULTI_RECEPTACLE))
						}
					}
					val refParam = op.createOwnedParameter("ref", requiredIntf)
					applyRef(refParam)

					val behavior = implementation.createOwnedBehavior(opName,
						UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior

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
								body += "(index, ref);"
							} else {
								body += "(ref);"
							}

						} else {
							body += '''«part.name»;'''
						}
					} else {
						// no delegation - create attribute for port
						val attributeName = PrefixConstants.attributePrefix + portInfo.name
						var attr = implementation.getOwnedAttribute(attributeName, null)
						if (attr == null || attr instanceof Port) {
							attr = implementation.createOwnedAttribute(attributeName, requiredIntf)
							CopyUtils.copyMultElemModifiers(portInfo.port, attr)

							// is shared (should store a reference)
							attr.setAggregation(AggregationKind.SHARED_LITERAL)
						}
						body = attributeName
						if(multiPort) body += "[index]"
						body += " = ref;"
					}

					behavior.getLanguages().add(progLang)
					behavior.getBodies().add(body)

					// -------------------------
					// add body to get-connection operation (which exists already if the port is also
					// owned, since it is synchronized automatically during model edit)
					// getConnQ prefix may be empty to indicate that the port is accessed directly
					// TODO: reconsider optimization that delegated required ports do not have a
					// local attribute and associated operation (an inner class may delegate, but the
					// composite may be using it as well).
					if ((PrefixConstants.getConnQ_Prefix.length() > 0) && (ce != null)) {
						val getConnOpName = PrefixConstants.getConnQ_Prefix + portInfo.name
						var getConnOp = implementation.getOwnedOperation(getConnOpName, null, null)
						if (getConnOp == null) {
							getConnOp = implementation.createOwnedOperation(getConnOpName, null, null, requiredIntf)
							val retParam = op.getOwnedParameters().get(0)
							retParam.setName(Constants.retParamName)
							applyRef(retParam)
						}
						val getConnBehavior = implementation.createOwnedBehavior(getConnOpName,
							UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior
						getConnOp.getMethods().add(getConnBehavior)

						// no delegation
						val String name = PrefixConstants.attributePrefix + portInfo.name
						body = '''return «name»;'''
						behavior.getLanguages().add(progLang)
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
		val Map<MultiplicityElement, Integer> indexMap = new HashMap<MultiplicityElement, Integer>()

		for (Connector connector : compositeImplementation.getOwnedConnectors()) {
			if (ConnectorUtil.isAssembly(connector)) {

				// Boolean associationBased = false
				if (connector.ends.size() != 2) {
					throw new TransformationException(
						'''Connector <«connector.name»> does not have two ends. This is currently not supported''')
				}
				val end1 = connector.ends.get(0)
				val end2 = connector.ends.get(1)
				var cmd = '''// realization of connector <«connector.name»>''' + NL
				if ((end1.role instanceof Port) && PortUtils.isExtendedPort(end1.role as Port)) {
					val port = end1.role as Port
					val EList<PortInfo> subPorts = PortUtils.flattenExtendedPort(port)
					for (PortInfo subPort : subPorts) {
						cmd += '''  // realization of connection for sub-port «subPort.port.name»''' + NL
						cmd += connectPorts(indexMap, connector, end1, end2, subPort.port)
						cmd += connectPorts(indexMap, connector, end2, end1, subPort.port)
					}
				} else {
					cmd += connectPorts(indexMap, connector, end1, end2, null)
					cmd += connectPorts(indexMap, connector, end2, end1, null)
				}
				createConnBody += cmd + NL
			}
		}

		if (createConnBody.length() > 0) {
			val operation = compositeImplementation.createOwnedOperation(Constants.CREATE_CONNECTIONS, null, null)

			val behavior = compositeImplementation.createOwnedBehavior(operation.name,
				UMLPackage.eINSTANCE.getOpaqueBehavior()) as OpaqueBehavior
			behavior.getLanguages().add(progLang)
			behavior.getBodies().add(createConnBody)
			behavior.setSpecification(operation)
		}
	}

	/**
	 * Create the body code that creates a connection between the two ends
	 * of a connector. This function checks whether the first end really is a receptacle
	 * and the second really is a facet.
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
	def connectPorts(Map<MultiplicityElement, Integer> indexMap, Connector connector, ConnectorEnd receptacleEnd,
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
				val setter = '''«receptaclePart.nameRef»connect_«receptaclePort.name»«subPortName»'''
				val getter = '''«facetPart.nameRef»get_«facetPort.name»«subPortName»()'''
				return '''«setter»(«indexName»«getter»);''' + NL
			}

		} else if (receptacleEnd.role instanceof Port) {

			// only the receptacle end is of type port.
			val Port receptaclePort = receptacleEnd.role as Port
			if (PortUtils.getRequired(receptaclePort) != null) {
				val facetPart = facetEnd.role as Property
				val receptaclePart = facetEnd.partWithPort

				val indexName = getIndexName(indexMap, receptaclePort, receptacleEnd)
				val setter = '''«receptaclePart.nameRef»connect_«receptaclePort.name»'''
				val getter = '''«facetPart.getRef»'''
				return '''«setter»(«indexName»«getter»);''' + NL
			}
		} else if (facetEnd.role instanceof Port) {

			// only the facet end is of type port. Unsupported combination
			val facetPort = facetEnd.role as Port
			if (PortUtils.getProvided(facetPort) != null) {
				val facetPart = facetEnd.partWithPort
				val receptaclePart = facetEnd.role as Property

				val setter = receptaclePart.name
				val getter = '''«facetPart.nameRef»get_«facetPort.name»();'''
				return '''«setter» = «getter»;''' + NL
			}
		} else if (association != null) {

			// both connector ends do not target ports. In this case, we require that the connector is typed
			// with an association. We use this association to find out which end is navigable and assume that
			// the part pointed to by the other end is a pointer that gets initialized with the part of the
			// navigable end.
			val facetPart = facetEnd.role as Property
			val receptaclePart = receptacleEnd.role as Property

			val assocProp1 = association.getMemberEnd(null, facetPart.type)

			if ((assocProp1 != null) && assocProp1.isNavigable) {
				var setter = '''«receptaclePart.name».«assocProp1.name»'''
				val getter = '''«facetPart.name»'''
				if (assocProp1.upper != 1) {
					// handle association ends with multiplicty != 1, use same map as for ports
					var indexValue = indexMap.get(assocProp1)
					if (indexValue == null) {
						indexValue = new Integer(0)
						indexMap.put(assocProp1, indexValue)
					}
					setter += '''[«indexValue++»]'''
					indexMap.put(assocProp1, indexValue)
				}
				return '''«setter» = «getter»;''' + NL
			}

		} else {

			// not handled (a connector not targeting a port must be typed)
			throw new TransformationException("Connector <" + connector.name +
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
	static def getIndexName(Map<MultiplicityElement, Integer> indexMap, Port port, ConnectorEnd end) {
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
	 * Return true, if the bootloader is responsible for the instantiation of a
	 * part.
	 * 
	 * If a part is a component type or an abstract implementation, it cannot be
	 * instantiated. Thus, a heir has to be selected in the deployment plan.
	 * Since the selection might be different for different instances of the
	 * composite, the instantiation is not done by the component itself, but by
	 * the bootloader. The bootloader also has to instantiate, if different
	 * allocation variants are required. (this is for instance the case for
	 * distribution connectors and for the system itself)
	 * 
	 * If possible, we want to let composites instantiate sub-components, since
	 * this eases the transition to systems which support reconfiguration.
	 * 
	 * [TODO: optimization: analyze whether the deployment plan selects a single
	 * implementation. If yes, let the composite instantiate]
	 * 
	 * @param implementation a composite component
	 * @return
	 */
	static def instantiateViaBootloader(Class implementation) {
		return implementation.isAbstract() || StereotypeUtil.isApplied(implementation, Assembly)
	}

	/**
	 * Return whether a part needs to be instantiated by the bootloader instead
	 * by the composite in which it is contained. The criteria is based on the
	 * question whether the containing composite is flattened, as it is the case
	 * for the system component and the interaction components for distribution.
	 * 
	 * @param part
	 * @return
	 */
	static def instantiateViaBootloader(StructuralFeature part) {
		if (part != null) {
			if (part.type instanceof Class) {
				val implementation = part.type as Class
				// TODO: wrong criteria? (must be shared or not?)
				return instantiateViaBootloader(implementation)
			} else {

				// not a class, assume primitive type instantiated by composite
				return false
			}
		}
		return false
	}
}
