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

package org.eclipse.papyrus.designer.components.transformation.connector;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.components.transformation.deployment.DepPlanUtils;
import org.eclipse.papyrus.designer.components.transformation.deployment.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Flatten interaction components for distributed communication, i.e. remove the enclosing interaction component
 * and only deploy the fragments.
 * This is required, since the interaction component itself cannot be deployed on several nodes.
 */
public class FlattenInteractionComponents implements IM2MTrafoElem {

	private static final String FLATTEN_SEP = "_"; //$NON-NLS-1$

	/**
	 *
	 * Flatten a composite interaction component, i.e. replace it by the containing fragments. This includes the following actions
	 * 1. Parts typed with the interaction component must be replaced with a set of parts typed with the fragment.
	 * 2. Connectors must be redirected.
	 *
	 * Please note that we do not delete the original interaction component, but it will not appear in a model generated for a specific node.
	 * TODO: We need to re-target connectors in all containing composites, but the same containingComposite might have several instances within an application. In this case,
	 * we only need to move slots.
	 *
	 * Well, there are different classes: first: the flattened interaction component and other components that reference the flattened interaction component.
	 *
	 * @param composite
	 *            a composite class
	 * @param instance
	 *            the instance associated with the composite class (1st parameter)
	 * @param containingInstance
	 *            an instance of the containing composite
	 * @param containingSlot
	 *            the slot associated with the instance (2nd parameter)
	 */
	public void flattenAssembly(Class composite, InstanceSpecification instance, InstanceSpecification containingInstance, Slot containingSlot)
	{
		Classifier containingCompositeCl = DepUtils.getClassifier(containingInstance);
		StructuralFeature sfForIA = containingSlot.getDefiningFeature();
		Map<Property, Property> replaceParts = new HashMap<Property, Property>();
		if ((containingCompositeCl instanceof Class) && (sfForIA instanceof Property)) {
			Class containingComposite = (Class) containingCompositeCl;
			Property partForIA = (Property) sfForIA;

			for (Property fragmentPart : composite.getAllAttributes()) {
				if (fragmentPart instanceof Port) {
					continue;
				}
				String partName = partForIA.getName() + FLATTEN_SEP + fragmentPart.getName();
				// create a new part in the containing composite
				Property newPartForFragment = containingComposite.createOwnedAttribute(partName, fragmentPart.getType());
				replaceParts.put(fragmentPart, newPartForFragment);

				boolean foundConnector = false;
				// now redirect connectors: find whether a port delegates to the fragment
				for (Connector connector : composite.getOwnedConnectors()) {
					if (ConnectorUtil.connectsPart(connector, fragmentPart)) {
						foundConnector = true;
						// internal connector for the part, check whether delegation
						ConnectorEnd ce = ConnectorUtil.connEndNotPart(connector, fragmentPart);
						if ((ce != null) && (ce.getPartWithPort() == null)) {
							// delegation connector, need to re-targed connections to external port
							Port port = (Port) ce.getRole();
							// now look for connections to this port in the containingComposite and shortcut these ...
							retargetConnections(containingComposite, port, partForIA, newPartForFragment);
						} else {
							// assembly: assembly connection between fragments are not authorized
						}
					}
				}
				if (!foundConnector) {
					throw new RuntimeException("the part <" + fragmentPart.getName() + "> within composite <" + composite.getName() + "> is not connected with any of its ports"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
			// on instance level: move slots from instance to containingInstance
			EList<Slot> slots = instance.getSlots();
			containingInstance.getSlots().addAll(slots);
			// replace defining feature
			for (Slot slot : containingInstance.getSlots()) {
				StructuralFeature sf = slot.getDefiningFeature();
				if (replaceParts.containsKey(sf)) {
					slot.setDefiningFeature(replaceParts.get(sf));
					// assure naming convention for instances: here, we update the name of the instance
					// and its sub-instances with the name of the containingInstance
					InstanceSpecification subInstance = DepUtils.getInstance(slot);
					if ((subInstance != null) && !DepUtils.isShared(slot)) {
						DepPlanUtils.updateInstanceNames(subInstance, instance.getName() + FLATTEN_SEP + sf.getName());
					}
				}
			}

			containingSlot.destroy();
			// destroy relationships of flattened instance, in particular allocation
			for (DirectedRelationship dr : instance.getSourceDirectedRelationships()) {
				dr.destroy();
			}
			instance.destroy();
			partForIA.destroy();

			// move is
			/*
			 * InstanceSpecification partIS;
			 * DepUtils.createSlot (cdp, newIS, partIS, newPart);
			 */
		}
	}

	/**
	 * Find a connection within a connecting
	 *
	 * @param containingComposite
	 * @param port
	 *            A port which delegates to a fragment
	 * @param part
	 *            the part within the containing composite (1st parameter) point to the interactionComponent
	 * @param subPart
	 *            a newly created part corresponding to the fragment
	 */
	public void retargetConnections(Class containingComposite, Port port, Property part, Property subPart) {
		for (Connector connector : containingComposite.getOwnedConnectors()) {
			if (ConnectorUtil.connectsPart(connector, part) && ConnectorUtil.connectsPort(connector, port)) {
				ConnectorEnd ce = ConnectorUtil.connEndForPart(connector, part);
				if (ce != null) {
					// TODO: only with with connections targeting a port of a part, not with
					// those targeting directly a part
					ce.setPartWithPort(subPart);
				}
			}
		}
	}

	@Override
	public void transformElement(M2MTrafo trafo, Element element) throws TransformationException {
		// TODO Auto-generated method stub
		if (element instanceof InstanceSpecification) {
			InstanceSpecification instance = (InstanceSpecification) element;
			Classifier cl = DepUtils.getClassifier(instance);
			if (cl instanceof Class) {
				InteractionComponent ic = UMLUtil.getStereotypeApplication(cl, InteractionComponent.class);
				if ((ic != null) && ic.isForDistribution()) {
					Slot containingSlot = DepUtils.getParentSlot(instance);
					InstanceSpecification containingInstance = containingSlot.getOwningInstance();
					flattenAssembly((Class) cl, instance, containingInstance, containingSlot);
				}
			}
		}
	}
}
