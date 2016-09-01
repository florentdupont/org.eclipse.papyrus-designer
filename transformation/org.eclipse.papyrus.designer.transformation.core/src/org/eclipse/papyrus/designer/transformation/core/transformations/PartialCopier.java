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

package org.eclipse.papyrus.designer.transformation.core.transformations;

import org.eclipse.papyrus.designer.deployment.tools.AllocUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;

/**
 * Copy a composite class while taking into account node allocation, i.e. only
 * copy parts that are deployed on a certain node (properties that are not typed
 * by a component are always copied). This function may be called several times
 * and will successively add parts that are required. If the same composite
 * (class level) is used in two different contexts, e.g. a socket connector
 * serving as client and server on the same node (for the same interface), the
 * resulting composite will be complete. On the instance level however, such as
 * composite is sub-optimal, since their are two different instances that use a
 * different subset. These cases are not very critical, since unused parts are
 * not instantiated (overhead = pointer)
 *
 * TODO: comment not up-to-date. Can we simply use standard LazyCopier?
 *
 */
public class PartialCopier implements InstanceDeployer {

	@Override
	public void init(LazyCopier copier,
			InstanceSpecification node) {
		this.copier = copier;
		this.node = node; // only needed for debug output
		// add copier listeners ---
		// 1. only add required parts
		// does nothing for the moment
	}

	@Override
	public InstanceSpecification deployInstance(InstanceSpecification is) throws TransformationException {
		Classifier classifier = DepUtils.getClassifier(is);

		// only make a partial copy of the top-level instances
		if (!(classifier instanceof Class) || !DepUtils.isTopLevelInstance(is)) {
			return copier.getCopy(is);
		}
		if (AllocUtils.getNodes(is).contains(node)) {
			return copier.getCopy(is);
		}

		Class smCl = (Class) classifier;

		// create parts in target model, if allocated.
		for (Slot slot : is.getSlots()) {
			copyPart(smCl, slot);
		}
		// since we copied some of its attributes, the copier class created a shallow copy of the class itself
		InstanceSpecification tmIS = (InstanceSpecification) copier.get(is);
		return tmIS;
	}

	/**
	 * Copy a part of a classifier, without being recursive [shouldn't that be in the generic deploy part?]
	 * This function is called, whenever a sub-instance is deployed
	 * Brainstorming: add containing composite to deployInstance? (in this case, deployInstance could create the
	 * part in the containing composite, if it does not exist yet)
	 *
	 * @param cl
	 * @param newCl
	 * @param slot
	 * @param allocAll
	 * @throws TransformationException
	 */
	protected void copyPart(Class smCl, Slot slot) throws TransformationException {
		Property smPart = (Property) slot.getDefiningFeature();

		// String partName = smPart.getName();
		InstanceSpecification instanceOrThread = DepUtils.getInstance(slot);
		// instance may be null, if slot refers to a basic type, e.g. a string
		if ((instanceOrThread == null) || AllocUtils.getNodes(instanceOrThread).contains(node)) {
			copier.copy(slot);

			// add connectors when possible, i.e. connectors that target the newly added part
			for (Connector smConnector : smCl.getOwnedConnectors()) {
				// check whether the newly added property enables the addition of connectors
				// that connect this port.
				if (ConnectorUtil.connectsPart(smConnector, smPart)) {
					ConnectorEnd otherEnd = ConnectorUtil.connEndNotPart(smConnector, smPart);
					// check whether the part references by the other end (i.e. that not connected with the part)
					// TODO: take connections without port into account
					Property otherPart = otherEnd.getPartWithPort();
					// compare part names, since connector points to parts within the source model
					if ((otherPart == null) || (copier.get(otherPart) != null)) {
						copier.copy(smConnector);
					}
				}
			}
		}
	}

	private InstanceSpecification node;

	private LazyCopier copier;
}