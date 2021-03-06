/*******************************************************************************
 * Copyright (c) 2014 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/
 
 package org.eclipse.papyrus.designer.languages.cpp.codegen.xtend

import java.util.ArrayList
import java.util.List
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ExternLibrary
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Template
import org.eclipse.papyrus.designer.languages.cpp.codegen.preferences.CppCodeGenUtils
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.designer.languages.common.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassIncludeDeclaration {
	
	static def cppClassIncludes(NamedElement ne) {
		var List<String> result = new ArrayList<String>()
		if (GenUtils.hasStereotypeTree(ne, ExternLibrary)) {
			// If a class is in an external library, use #include
			// directives defined there
			result.addAll(GenUtils.getApplicationTree(ne, ExternLibrary).includes)
			// No individual includes are produced for members
			// unless the stereotype "External" defines one explicitly
			if (GenUtils.hasStereotype(ne, External)) {
				result.add(includeName(ne))			
			}
		} else {
			result.add(includeName(ne))
		}
		return result
	}
	
	static def includeName(NamedElement ne) {
		if (GenUtils.hasStereotypeTree(ne, Template)) {
			return UMLUtil.getStereotypeApplication(ne, Template).declaration
		} else {
			if (GenUtils.hasStereotypeTree(ne, External)) {
				return UMLUtil.getStereotypeApplication(ne, External).name
			} else {
				// standard case (no stereotypes are applied)
				return GenUtils.getFullPath(ne.nearestPackage) + '/' + ne.name + '.' + CppCodeGenUtils.getHeaderSuffix()
			}
		}
	}
}