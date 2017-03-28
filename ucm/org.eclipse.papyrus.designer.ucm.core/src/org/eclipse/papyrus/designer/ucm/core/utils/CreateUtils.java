/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;

public class CreateUtils {
	/**
	 * Navigate to the nearest package having a given stereotype. Will return null, if none exists
	 * @param element
	 * @param stereoApplication
	 * @return
	 */
	public static Package nearestPackageWithStereo(Element element, Class<? extends EObject> stereoApplication) {
		if (element != null) {
			Package nearest = element.getNearestPackage();
			if (StereotypeUtil.isApplied(nearest, stereoApplication)) {
				return nearest;
			}
			return nearestPackageWithStereo(nearest.getOwner(), stereoApplication);
		}
		return null;
	}
	
	/**
	 * Return an existing sub-package or create a new one, if none exists yet
	 * (will do nothing and return null, if a sub-element with the name exist, but is not a package)
	 *
	 * @param pkg
	 * @param subPkgName
	 * @return
	 */
	public static Package subPackage(Package pkg, String subPkgName) {
		PackageableElement subPkg = pkg.getPackagedElement(subPkgName);
		if (subPkg == null) {
			subPkg = pkg.createPackagedElement(subPkgName, UMLPackage.eINSTANCE.getPackage());
		}
		if (subPkg instanceof Package) {
			return (Package) subPkg;
		}
		return null;
	}
}
