/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
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

package org.eclipse.papyrus.designer.components.modellibs.core.transformations;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.components.modellibs.core.Activator;
import org.eclipse.papyrus.designer.components.modellibs.core.utils.CompDepUtils;
import org.eclipse.papyrus.designer.components.transformation.Messages;
import org.eclipse.papyrus.designer.components.transformation.PortUtils;
import org.eclipse.papyrus.designer.components.transformation.sync.CompImplSync;
import org.eclipse.papyrus.designer.components.transformation.templates.ConnectorBinding;
import org.eclipse.papyrus.designer.components.transformation.templates.TemplateUtils;
import org.eclipse.papyrus.designer.deployment.tools.AllocUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.CopyUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateInstantiation;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * This class enables the reification of connectors, i.e. the replacement of
 * a UML connector with an interaction component. Reification is primarily
 * done on the level of a composite class.
 */
public class ConnectorReification implements IM2MTrafoCDP {

	/**
	 * Find a port that would match a connection
	 *
	 * @param connectorType
	 *            a connector type, i.e. a component with ports
	 * @param the
	 *            port on the other side of the connection
	 * @return the first port (of all ports owned or inherited by the type) that
	 *         is compatible with the passed otherPort.
	 */
	public static Port getConnectorPort(EncapsulatedClassifier connectorType, Port otherPort, boolean isAssembly) {
		EList<Port> ports = PortUtils.getAllPorts(connectorType);
		// try to find matching port
		for (Port port : ports) {
			if (PortUtils.isCompatible(port, otherPort, isAssembly)) {
				return port;
			}
		}
		throw new RuntimeException(String.format(Messages.ConnectorReification_CannotFindMatchingPort, connectorType.getName(), otherPort.getQualifiedName()));
	}

	/**
	 * Simple helper function that finds the opposite connector end when one is given.
	 * If returns the first connector end that is not equal to the passed one, but results
	 * will not be useful in general for n-ary connectors (n!=2)
	 * 
	 * @param connector
	 *            a connector
	 * @param myEnd
	 *            one end of a connector.
	 * @return the connector end that is associated with the "other" end of a
	 *         connection, i.e. the end that differs from the passed connector end
	 */
	public static ConnectorEnd oppositeConnEnd(Connector connector, ConnectorEnd myEnd) {
		// look for the other end (connectedEnd != myEnd)
		for (ConnectorEnd end : connector.getEnds()) {
			if (end != myEnd) {
				return end;
			}
		}
		return null;
	}

	/**
	 * Reify a connector already represented in form of a part (useful for n-ary connectors)
	 * 
	 * @param composite
	 *            containing composite in target target model
	 * @param connectorPart
	 *            Part representing the connector
	 * @param compositeIS
	 *            target instance specification of the composite passed as 2nd parameter
	 *            (required for obtaining node allocation and choosing the right implementation.
	 *            Main use: decide whether a distributed implementation of an interaction component needs to be used)
	 * @return The created part within tmComponent that represents the reified
	 *         connector
	 * @throws TransformationException
	 */
	public Property reifyConnector(Class composite, Property connectorPart, InstanceSpecification compositeIS) throws TransformationException {

		LazyCopier copier = TransformationContext.current.copier;

		if (!(connectorPart.getType() instanceof Class)) {
			// type is not a class, now check whether it is an interaction component
			return null;
		}
		Class connImplementation = (Class) connectorPart.getType();
		if (!StereotypeUtil.isApplied(connImplementation, InteractionComponent.class)) {
			return null;
		}

		// choose an implementation
		Class connectorImplemTemplate = CompDepUtils.chooseImplementation(connImplementation, AllocUtils.getAllNodes(compositeIS), null);

		TemplateBinding binding = ConnectorBinding.obtainBinding(composite, connectorPart, connectorImplemTemplate, true);
		Class connectorImplem;

		if (binding != null) {
			TemplateUtils.adaptActualsToTargetModel(copier, binding);
			TemplateInstantiation ti = new TemplateInstantiation(binding);
			connectorImplem = ti.bindElement(connectorImplemTemplate);
		} else {
			// no binding, class is not a template => copy as it is
			connectorImplem = copier.getCopy(connectorImplemTemplate);
		}
		Property tmConnectorPart = copier.getCopy(connectorPart);
		tmConnectorPart.setType(connectorImplem);
		// now re-target connectors towards this part
		TemplateUtils.retargetConnectors(composite, tmConnectorPart);
		return tmConnectorPart;
	}

	/**
	 * Reify a connector
	 *
	 * @param composite
	 *            containing composite
	 * @param connector
	 *            connector within the passed composite. If connector reification is successful, the passed connector will be destroyed
	 *            (since replaced by a new connector-part-connector combination)
	 * @param compositeIS
	 *            instance specification of the composite passed as 2nd parameter
	 *            (required for obtaining node allocation and choosing the right implementation.
	 *            Main use: decide whether a distributed implementation of an interaction component needs to be used)
	 * @return the created part within the composite or null, if no reification could be executed.
	 * @throws TransformationException
	 */
	public Property reifyConnector(Class composite, Connector connector, InstanceSpecification compositeIS) throws TransformationException {

		InteractionComponent connType = null;
		org.eclipse.papyrus.designer.components.FCM.Connector fcmConn = UMLUtil.getStereotypeApplication(connector, org.eclipse.papyrus.designer.components.FCM.Connector.class);
		if (fcmConn != null) {
			connType = fcmConn.getIc();
		}
		if (connType == null) {
			return null;
		}

		LazyCopier copier = TransformationContext.current.copier;
		String name = ElementUtils.varName(connector);

		// choose an implementation
		Class connectorImplemTemplate = DepUtils.chooseImplementation(connType.getBase_Class(), AllocUtils.getAllNodes(compositeIS), null);

		// ---- obtain binding & instantiate template type ...
		TemplateBinding binding = ConnectorBinding.obtainBinding(composite, connector, connectorImplemTemplate, true);
		Class connectorImplem;

		if (binding != null) {
			TemplateUtils.adaptActualsToTargetModel(copier, binding);
			// make copy of bound package and restore it later. Required for nested template instantiations, in particular
			// the bound package is set within container transformations and is (by default) restored to "null" afterwards.
			// TODO: TemplateInstantiation should do this automatically
			copier.pushPackageTemplate();
			TemplateInstantiation ti = new TemplateInstantiation(binding);
			connectorImplem = ti.bindElement(connectorImplemTemplate);
			copier.popPackageTemplate();
		} else {
			// no binding, class is not a template => copy as it is
			connectorImplem = copier.getCopy(connectorImplemTemplate);
		}

		if (connectorImplem == null) {
			throw new TransformationException(String.format(Messages.ConnectorReification_CouldNotBind, connectorImplemTemplate.getName()));
		}

		Property tmConnectorPart = composite.createOwnedAttribute(name, connectorImplemTemplate);
		// copy id, but prefix it with "p" (for part)
		CopyUtils.copyID(connector, tmConnectorPart, "p"); //$NON-NLS-1$
		tmConnectorPart.setIsComposite(true);

		Activator.log.info(String.format(Messages.ConnectorReification_InfoAddConnectorPart, connectorImplemTemplate.getName(), connectorImplem.getName()));

		// now create (simple) connections towards the new part
		int i = 0;
		for (ConnectorEnd smEnd : connector.getEnds()) {
			Connector tmConnector = composite.createOwnedConnector("c " //$NON-NLS-1$
					+ name + " " + String.valueOf(i)); //$NON-NLS-1$
			CopyUtils.copyID(connector, tmConnector);
			i++;
			// the new connector connects the existing end with an end of the
			// reified connector (the newly created property.)

			// --- first end, connected with the existing end (of another non-connector part)
			ConnectorEnd tmEnd1 = tmConnector.createEnd();
			Property smPartWithPort = smEnd.getPartWithPort();
			Property tmPartWithPort = copier.getCopy(smPartWithPort);
			ConnectableElement smRole = smEnd.getRole();
			ConnectableElement tmRole = copier.getCopy(smRole);
			tmEnd1.setPartWithPort(tmPartWithPort);
			tmEnd1.setRole(tmRole);

			// --- 2nd end, connected with the reified connector (new part)
			ConnectorEnd tmEnd2 = tmConnector.createEnd();
			tmEnd2.setPartWithPort(tmConnectorPart);
			// inheritance between connector type and implementation (ports should be identical)
			// TODO: check whether filter condition is unique? (first returned by getConnectorPort is "good" one)
			if (tmRole instanceof Port) {
				tmEnd2.setRole(getConnectorPort(connectorImplem, (Port) tmRole, (tmPartWithPort != null)));
			} else {
				throw new TransformationException(Messages.ConnectorReification_RequiresUseOfPorts);
			}
		}

		tmConnectorPart.setType(connectorImplem);
		// updatePorts(tmComponent, connectorPart, connectorImplem);
		// connectContainerPorts(tmComponent, connectorPart);
		connector.destroy();

		return tmConnectorPart;
	}

	/**
	 * Components can contain additional ports that are inherited via the
	 * container extension. These ports should typically be connected with
	 * additional ports of the (reified) connector. This connection is based on
	 * equal port types and is done automatically by this function, i.e. it
	 * cannot be done by the developer.
	 *
	 * @param composite
	 *            the composite in which a connector has been reified.
	 * @param reifiedConnector
	 *            the part associated with the reifiedConnector
	 */
	public void connectContainerPorts(Class composite, Property reifiedConnector) {
		// This function is based on the assumption that the additional ports of the reified
		// connector need to be connected with a port of a component that is already
		// connected via the "normal" connectors (explicitly modeled by the user).
		// For instance, in case of ACCORD-calls, the server component provides the additional
		// RTU port via its container.

		// Create a subset of connectors that are connected with the reified connector
		EList<Connector> connSubset = new BasicEList<Connector>();
		for (Connector connector : composite.getOwnedConnectors()) {
			if (ConnectorUtil.connectsPart(connector, reifiedConnector)) {
				connSubset.add(connector);
			}
		}

		for (Port port : PortUtils.getAllPorts((Class) reifiedConnector.getType())) {
			// check, if port is unconnected.
			boolean connected = false;
			// check whether a port of the reified connector is not yet
			// connected.
			for (Connector connector : connSubset) {
				if (ConnectorUtil.connectsPort(connector, port)) {
					connected = true;
				}
			}
			if (!connected) {
				// port is not connected yet. Check to find a connectable port
				// among all ports of connected parts.
				// In the moment, we assume the processes is stopped, as soon as
				// the port is connected, i.e. we do not want to connect the port to
				// potentially set of ports (todo: restriction always useful?)
				for (Connector connector : connSubset) {
					ConnectorEnd connEnd = ConnectorUtil.connEndNotPart(connector, reifiedConnector);
					Property otherPart = connEnd.getPartWithPort();
					// this is a part which is connected with the reified
					// connector check whether one of its ports is compatible with the
					// non-connected port.
					if (!(otherPart.getType() instanceof EncapsulatedClassifier)) {
						continue;
					}

					for (Port otherPort : PortUtils.getAllPorts((EncapsulatedClassifier) otherPart.getType())) {
						Activator.log.info(String.format(Messages.ConnectorReification_InfoPortTypes, otherPort.getType().getQualifiedName(), port.getType().getQualifiedName()));
						if (otherPort.getType() == port.getType()) {
							Connector newConnector = composite.createOwnedConnector("connector - container of " //$NON-NLS-1$
									+ otherPart.getName());
							ConnectorEnd end1 = newConnector.createEnd();
							ConnectorEnd end2 = newConnector.createEnd();
							end1.setPartWithPort(reifiedConnector);
							end1.setRole(port);
							end2.setPartWithPort(otherPart);
							end2.setRole(otherPort);
							connected = true;
							break;
						}
					}
					if (connected) {
						break;
					}
				}
				if (!connected) {
					if (port.getType() == null) {
						Activator.log.debug(String.format(Messages.ConnectorReification_CouldNotConnectPort, port.getName()));
					} else {
						Activator.log.debug(String.format(Messages.ConnectorReification_CouldNotConnectPortOfType, port.getName(), port.getType().getName()));
					}
				}
			}
		}
	}

	@Override
	public void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
		for (InstanceSpecification is : DepUtils.getInstances(deploymentPlan)) {
			Classifier cl = DepUtils.getClassifier(is);
			if (cl instanceof Class) {
				Class clazz = (Class) cl;
				EList<Connector> connectorListCopy = new BasicEList<Connector>();
				connectorListCopy.addAll(clazz.getOwnedConnectors());
				for (Connector connector : connectorListCopy) {
					Class tmComponent = (Class) connector.getOwner();
					Property newPart = reifyConnector(tmComponent, connector, is);
					// update value specification (to the one just created)
					// InstanceSpecification tmPartIS = EcoreUtil.copy(DepUtils.getInstance(slot));
					// if (tmPartIS.getClassifiers().size() > 0) {
					// replace classifier referenced from instance with type of reified connector
					// tmPartIS.getClassifiers().set(0, (Classifier) tmPart.getType());
					// TransformationUtil.updateDerivedInterfaces(tmPartIS);
					// }
					if (newPart != null) {
						Type implCandidate = newPart.getType();
						if (implCandidate instanceof Class) {
							Class implementation = (Class) implCandidate;
							CompImplSync.updatePorts(implementation);
							CompImplSync.syncRealizations(implementation);
						}
					}
				}

				EList<Property> attributeListCopy = new BasicEList<Property>();
				attributeListCopy.addAll(clazz.getAttributes());
				for (Property attribute : attributeListCopy) {

					Class tmComponent = (Class) attribute.getOwner();
					reifyConnector(tmComponent, attribute, is);
				}
			}
		}
	}
}
