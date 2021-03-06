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
package org.eclipse.papyrus.designer.components.modellibs.core.utils;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.ImplementationGroup;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.deployment.tools.ImplementationChooser;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLUtil;

public class CompDepUtils {
	
	public static Class chooseImplementation(Class componentType, EList<InstanceSpecification> nodes, ImplementationChooser chooser) {

		EList<Class> implList = new BasicEList<Class>();
		if (nodes.size() > 1) {
			// indicates distribution
			InteractionComponent connImpl = UMLUtil.getStereotypeApplication(componentType, InteractionComponent.class);
			// if a connector implementation, it must support distribution (in case of multiple nodes)
			// TODO: criteria is not optimal, since a composite may be deployed on multiple nodes,
			// but a contained connector might still only connect local parts.
			if (connImpl != null) {
				if (!connImpl.isForDistribution()) {
					return null;
				}
			}
		}
		
		if (StereotypeUtil.isApplied(componentType, ImplementationGroup.class)) {
			for (Property groupAttribute : componentType.getAttributes()) {
				Type implClass = groupAttribute.getType();
				if ((implClass instanceof Class) && DepUtils.isImplEligible((Class) implClass, nodes)) {
					InteractionComponent connImpl = UMLUtil.getStereotypeApplication(implClass, InteractionComponent.class);
					if ((connImpl != null) && connImpl.isForDistribution()) {
						// only add distributed connector, if distributed
						// don't put check into
						if (nodes.size() > 1) {
							implList.add((Class) implClass);
						}
					}
					else {
						implList.add((Class) implClass);
					}
				}
			}
		}
		return DepUtils.chooseImplementation(implList, componentType, nodes, chooser);
	}
	
}
