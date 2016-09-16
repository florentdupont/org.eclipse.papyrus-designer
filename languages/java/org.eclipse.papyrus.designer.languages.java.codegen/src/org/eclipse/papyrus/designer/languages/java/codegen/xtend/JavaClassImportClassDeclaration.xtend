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

package org.eclipse.papyrus.designer.languages.java.codegen.xtend

import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.papyrus.designer.languages.java.codegen.utils.ClassUtils
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary
import org.eclipse.emf.common.util.UniqueEList

class JavaClassImportClassDeclaration {
	static def javaClassAllImports(Classifier classifier, EList<Classifier> list, String prefix) {
		var List<String> newList = new UniqueEList<String>()
		for (cl : list) {
			if ((cl != classifier && !GenUtils.hasStereotype(cl, NoCodeGen)) || GenUtils.hasStereotype(cl, External) || GenUtils.hasStereotypeTree(cl, ExternLibrary)) {
				if (!(cl instanceof PrimitiveType)) {
					val imports = JavaClassImportDeclaration.javaClassImports(cl, classifier)
					
					if (GenUtils.hasStereotype(cl, External) || GenUtils.hasStereotypeTree(cl, ExternLibrary)) {
						// We don't add a prefix to External.name or ExternLibrary.imports
						newList.addAll(JavaClassImportDeclaration.javaClassImports(cl, classifier))
					} else {
						for (theImport : imports) {
							newList.add(prefix + theImport)
						}
					}
				}
			}
		}
		return newList.filter[str | str != null]
	}
	
	static def javaClassAllImports(Classifier clazz, String prefix) {
		javaClassAllImports(clazz, ClassUtils.requiredClassifiers(clazz), prefix)
	}
}