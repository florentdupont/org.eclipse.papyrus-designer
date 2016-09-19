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
package org.eclipse.papyrus.designer.components.transformation.component;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.util.UMLUtil;

public class ContainerUtil {
	 // TODO: need a generic utility function for getting all elements obeying a certain
    // criteria from a model
    public static EList<ContainerRule> getAllRules(Package pkg) {
        EList<Package> visitedPackages = new BasicEList<Package>();
        EList<ContainerRule> contRuleList = new BasicEList<ContainerRule>();
        getAllRules(pkg, visitedPackages, contRuleList);
        return contRuleList;
    }

    public static void getAllRules(Package pkg, EList<Package> visitedPackages, EList<ContainerRule> contRuleList) {
        for (Element el : pkg.getMembers()) {
            if (el instanceof Package) {
                if (!visitedPackages.contains(el)) {
                    visitedPackages.add((Package) el);
                    getAllRules((Package) el, visitedPackages, contRuleList);
                }
            }
            else if (el instanceof Class) {
                if (StereotypeUtil.isApplied(el, ContainerRule.class)) {
                    ContainerRule rule = UMLUtil.getStereotypeApplication(el, ContainerRule.class);
                    contRuleList.add(rule);
                }
            }
        }
    }

}
