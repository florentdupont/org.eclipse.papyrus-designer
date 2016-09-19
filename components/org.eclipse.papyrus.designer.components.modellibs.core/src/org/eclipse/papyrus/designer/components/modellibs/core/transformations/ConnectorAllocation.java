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

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.Fragment;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.deployment.tools.AllocUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;

public class ConnectorAllocation implements IM2MTrafoElem {
	/**
	 * Propagate node allocation into a reified connector (identified via its part)
	 *
	 * The allocation algorithm works as follows.
	 * - get a connector (simple) originating from the passed part
	 * - get the opposite end of this simple connector and identify the connected part
	 * (other part, e.g. a part typed with an application component)
	 * - obtain the nodes to which this other part is allocated:
	 * - in case of a composite, connections are followed up to a monolithic component
	 * - propagate the node allocation into the connector (propagation will finally
	 * allocate leafs within a (composite) connector.
	 *
	 * @param composite
	 *            the composite class (e.g. system) in which a connector is reified
	 * @param compositeIS
	 *            the associated instance specification
	 * @param partSlot
	 *            the slot associated with the part that is typed with the reified connector.
	 */
	public static void propagateNodeAllocation(Class composite, InstanceSpecification compositeIS, Slot partSlot) {
		Property part = (Property) partSlot.getDefiningFeature();

		// loop over connectors of composite that originate from passed part.
		for (Connector connector : composite.getOwnedConnectors()) {
			ConnectorEnd myEnd = ConnectorUtil.connEndForPart(connector, part);
			if (myEnd == null) {
				// the connector does not connect this part
				continue;
			}
			ConnectorEnd otherEnd = ConnectorUtil.connEndNotPart(connector, part);

			Property otherPart = otherEnd.getPartWithPort();
			Port otherPort = (Port) otherEnd.getRole();
			// Property myPart = myEnd.getPartWithPort();
			Port myPort = (Port) myEnd.getRole();

			// find instance that is associated with other part.
			for (Slot slot : compositeIS.getSlots()) {
				if (slot.getDefiningFeature() == otherPart) {
					InstanceSpecification containedInstance = DepUtils.getInstance(slot);
					EList<InstanceSpecification> nodes = AllocUtils.getAllNodesForPort(containedInstance, otherPort);
					AllocUtils.propagateNodesViaPort(DepUtils.getInstance(partSlot), myPort, nodes);
					break;
				}
			}
		}
	}

	@Override
	public void transformElement(M2MTrafo trafo, Element element) throws TransformationException {
		if (element instanceof Slot) {
			Slot partSlot = (Slot) element;
			Property part = (Property) partSlot.getDefiningFeature();
			// get part and type referenced by the slot
			Type type = part.getType();
			if (StereotypeUtil.isApplied(type,  InteractionComponent.class) ||
				StereotypeUtil.isApplied(type,  Fragment.class)) {
				// the part is for an interaction component. Retrieve instance of enclosing composite 
				InstanceSpecification is = partSlot.getOwningInstance();
				Classifier composite = DepUtils.getClassifier(is);
				if (composite instanceof Class) {
					propagateNodeAllocation((Class) composite, is, partSlot);
				}
			}
		}
	}
}
