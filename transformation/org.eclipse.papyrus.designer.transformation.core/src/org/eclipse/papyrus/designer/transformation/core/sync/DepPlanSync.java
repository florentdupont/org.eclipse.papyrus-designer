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

package org.eclipse.papyrus.designer.transformation.core.sync;

import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.deployment.tools.Activator;
import org.eclipse.papyrus.designer.deployment.tools.DepCreation;
import org.eclipse.papyrus.designer.deployment.tools.DepPlanUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.ElementFilter;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationRTException;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Currently unused synchronization for deployment plans.
 *
 * @see comp.cea.ec3m.gentools.core.listeners.DepPlanListener
 *
 * @author ansgar
 */
public class DepPlanSync {

	/**
	 * Synchronize a deployment plan: remove slots that refer to properties that exist no longer
	 * and add those that do not exist yet
	 *
	 * @param element
	 *            an arbitrary element of the source model (i.e. the model that will
	 *            store the deployment plan
	 * @throws TransformationException
	 */
	public static void syncAllDepPlans(Element element) {
		for (Package depPlan : DepPlanUtils.getAllDepPlans(element)) {
			syncDepPlan(depPlan);
		}
	}

	/**
	 * Synchronize a deployment plan: remove slots that refer to properties that exist no longer
	 * and add those that do not exist yet
	 *
	 * @param depPlan
	 * @throws TransformationException
	 */
	public static void syncDepPlan(Package depPlan) {
		removeUnusedSlots(depPlan);
		for (InstanceSpecification is : DepUtils.getTopLevelInstances(depPlan)) {
			addCDP(depPlan, is, is.getName());
		}
	}

	private static void addCDP(Package depPlan, InstanceSpecification instance, String canonicalName)
	{
		Class implementation = DepUtils.getImplementation(instance);
		if (!instance.getName().equals(canonicalName)) {
			instance.setName(canonicalName);
		}
		// check sub-instances
		for (Slot slot : instance.getSlots()) {
			InstanceSpecification subInstance = DepUtils.getInstance(slot);
			if (subInstance != null) {
				addCDP(depPlan, subInstance, canonicalName + "." + slot.getDefiningFeature().getName()); //$NON-NLS-1$
			}
		}
		for (Property attribute : ElementUtils.getParts(implementation)) {
			Type type = attribute.getType();
			if (type instanceof Class) {
				if (!hasSlot(instance, attribute)) {
					try {
						InstanceSpecification partIS =
								DepCreation.createDepPlan(depPlan, (Class) type, instance.getName() + "." + attribute.getName(), true); //$NON-NLS-1$
						DepPlanUtils.createSlot(depPlan, instance, partIS, attribute);
					} catch (TransformationException e) {
						Activator.log.error(e);
						throw new TransformationRTException(e.getMessage());
					}
				}
			}
		}
		// TODO: handle interaction components (extension point?)
	}

	/**
	 * return a candidate for an interaction component. Since these instances have
	 * no associated slot, the derivedElement hints to the original connector
	 * (could still be problematic!)
	 * 
	 * @param cdp
	 * @param cl
	 * @return
	 */
	protected static InstanceSpecification findISforConn(Package cdp, final Connector conn) {
		ElementFilter filter = new ElementFilter() {

			@Override
			public boolean acceptElement(Element element) {
				if (element instanceof InstanceSpecification) {
					InstanceSpecification is = (InstanceSpecification) element;
					DerivedElement de = UMLUtil.getStereotypeApplication(is, DerivedElement.class);
					if (de != null && de.getSource() == conn) {
						return true;
					}
				}
				return false;
			}
		};
		EList<InstanceSpecification> iList = new BasicEList<InstanceSpecification>();
		DepUtils.getAllInstances(cdp, iList, filter);
		if (iList.size() > 0) {
			return iList.get(0);
		}
		return null;
	}

	private static boolean hasSlot(InstanceSpecification instance, Property attribute) {
		for (Slot slot : instance.getSlots()) {
			if (slot.getDefiningFeature() == attribute) {
				return true;
			}
		}
		return false;
	}

	public static void removeUnusedSlots(Package depPlan) {
		// remove elements that are no longer in the plan
		for (NamedElement member : depPlan.getMembers()) {
			if (member instanceof InstanceSpecification) {
				InstanceSpecification instance = (InstanceSpecification) member;
				Iterator<Slot> slotIt = instance.getSlots().iterator();
				while (slotIt.hasNext()) {
					Slot slot = slotIt.next();
					if (slot.getDefiningFeature() == null) {
						// property has been removed => remove associated slot
						for (ValueSpecification value : slot.getValues()) {
							if (value instanceof InstanceValue) {
								InstanceSpecification is = ((InstanceValue) value).getInstance();
								DepPlanUtils.delDepPlan(is);
							}
						}
						slot.destroy();
						slotIt.remove();
					}
				}
			}
		}
	}
}
