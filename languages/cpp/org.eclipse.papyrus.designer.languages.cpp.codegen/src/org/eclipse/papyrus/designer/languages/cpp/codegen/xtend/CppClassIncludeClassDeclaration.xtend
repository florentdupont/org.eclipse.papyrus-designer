package org.eclipse.papyrus.designer.languages.cpp.codegen.xtend

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

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.ClassUtils
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.CppRoot
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.PrimitiveType

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassIncludeClassDeclaration {
 	
 	static def cppOwnerPackageIncludePath(Package pkg) {
		if ((pkg != null) && (!GenUtils.hasStereotype(pkg, CppRoot))) {
			return GenUtils.getFullPath(pkg) + '/Pkg_' + pkg.name + '.h'
		} else {
			return null
		}
	}
	
	static def CppClassAllIncludesDeclarationBody(Classifier classifier) {
		cppClassAllIncludes(classifier, ClassUtils.includedImplementationClassifiers(classifier))
	}
	
	static def cppClassAllIncludes(Classifier classifier, EList<Classifier> list) {
		var List<String> newList = new ArrayList<String>()
		for (cl : list) {
			//var String str = null
			if ((cl != classifier) && (!GenUtils.hasStereotype(cl, NoCodeGen)) || (GenUtils.hasStereotype(cl, External))) {
				if ((cl instanceof Enumeration) || (cl instanceof PrimitiveType)) {
					if (cl.owner instanceof Package && cl.owner != classifier.owner) {
						/* Enum and Primitive must not be in same package as classifier since
						we always enums and primitives are defined in the package header which
						is always included in the classifier header */
						var includePath = (cl.owner as Package).cppOwnerPackageIncludePath
						if (!newList.contains(includePath)) newList.add(includePath)
					} else {
						//str = null
					}
				} else {
					newList.addAll(CppClassIncludeDeclaration.cppClassIncludes(cl))
				}
			} else {
				//str = null
			}
		}
		return newList.filter[str | str != null]
	}
	
	static def cppClassAllDeclares(Classifier classifier, EList<Classifier> declaredClassifiers, EList<Classifier> includedClassifiers) {
		var List<String> newList = new ArrayList<String>()
		
		if (declaredClassifiers != null) {
			if (includedClassifiers != null) {
				declaredClassifiers.removeAll(includedClassifiers)
			}
			
			for (cl : declaredClassifiers) {
				if ((cl != classifier) && (!GenUtils.hasStereotype(cl, NoCodeGen)) || (GenUtils.hasStereotype(cl, External))) {
					var declaration = "";
					
					if (!(cl instanceof Enumeration) && !(cl instanceof PrimitiveType)) {
						if (cl instanceof DataType) {
							declaration = CppGenUtils.openNSMinimal(cl) + "struct " + cl.name + ";" + CppGenUtils.closeNSMinimal(cl);
						} else {
							declaration = CppGenUtils.openNSMinimal(cl).replaceAll("\r", "").replaceAll("\n", "") + "class " + cl.name + ";" + CppGenUtils.closeNSMinimal(cl).replaceAll("\r", "").replaceAll("\n", "");
						}
					}
					
					if (declaration != "") {
						if (!newList.contains(declaration)) {
							newList.add(declaration);
						}
					}
				}
			}
		}
		
		return newList.filter[str | str != null]
	}
	
	static def CppClassAllIncludes(Classifier clazz) {
		cppClassAllIncludes(clazz, ClassUtils.includedClassifiers(clazz))
	}
	
	static def CppClassAllDeclares(Classifier clazz) {
		cppClassAllDeclares(clazz, ClassUtils.declaredClassifiers(clazz), ClassUtils.includedClassifiers(clazz))
	}
}