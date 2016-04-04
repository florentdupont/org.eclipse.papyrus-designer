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

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.papyrus.designer.languages.java.codegen.utils.ClassUtils
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.PrimitiveType

class JavaClassImportClassDeclaration {
	static def javaClassAllImports(Classifier classifier, EList<Classifier> list) {
		var List<String> newList = new ArrayList<String>()
		for (cl : list) {
			//var String str = null
			if (cl != classifier && !GenUtils.hasStereotype(cl, NoCodeGen) || GenUtils.hasStereotype(cl, External)) {
				if (!(cl instanceof PrimitiveType)) {
					newList.addAll(JavaClassImportDeclaration.javaClassImports(cl, classifier))
				}
			} else {
				//str = null
			}
		}
		return newList.filter[str | str != null]
	}
	
	static def javaClassAllImports(Classifier clazz) {
		javaClassAllImports(clazz, ClassUtils.requiredClassifiers(clazz))
	}
}