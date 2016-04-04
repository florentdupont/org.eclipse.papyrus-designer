/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen.utils;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.papyrus.designer.languages.common.base.GenUtils;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;

/**
 * A set of utility functions related to classes.
 */
public class ClassUtils {
	/**
	 * Calculate the list of classifiers that are required by another classifier
	 *
	 * @param currentClass
	 * @return
	 */
	public static EList<Classifier> requiredClassifiers(Classifier currentClass) {
		// Retrieve package used by current package (dependencies)
		// use a unique list to avoid duplicates
		EList<Classifier> usedClasses = new UniqueEList<Classifier>();

		// class attributes dependencies
		usedClasses.addAll(GenUtils.getDirectTypesViaAttributes(currentClass));
		// operation parameters dependencies
		usedClasses.addAll(GenUtils.getDirectTypesViaOperations(currentClass));
		// inner classifier dependencies
		usedClasses.addAll(GenUtils.getDirectInnerClassifierTypes(currentClass));
		// realized interface dependencies
		if (currentClass instanceof org.eclipse.uml2.uml.Class) {
			org.eclipse.uml2.uml.Class clazz = (org.eclipse.uml2.uml.Class) currentClass;
			EList<Interface> implementedInterfaces = clazz.getImplementedInterfaces();
			usedClasses.addAll(implementedInterfaces);
		}
		// dependencies and associations
		usedClasses.addAll(GenUtils.getDirectTypesViaRelationshipsNoDeps(currentClass));
		usedClasses.addAll(GenUtils.getDirectTypesViaDependencies(currentClass));

		// template parameters are declared locally (if owned) and do not correspond to a file
		// that can be included
		usedClasses.removeAll(GenUtils.getTemplateParameteredElements(currentClass));
		return usedClasses;
	}
}
