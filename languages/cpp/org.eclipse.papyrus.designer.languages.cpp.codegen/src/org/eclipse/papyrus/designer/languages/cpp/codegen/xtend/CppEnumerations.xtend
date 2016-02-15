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

import org.eclipse.uml2.uml.Enumeration
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.CppInit
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.uml2.uml.EnumerationLiteral

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppEnumerations {
	static def CppEnumerationDefinition(Enumeration enumeration) '''
		«CppDocumentation.CppElementDoc(enumeration)»
		enum «enumeration.name» {
			«FOR i : 0 ..< enumeration.ownedLiterals.size»
				«CppDocumentation.CppElementDoc(enumeration.ownedLiterals.get(i))»
				«enumeration.ownedLiterals.get(i).name»«enumeration.ownedLiterals.get(i).defaultValue»«IF i < enumeration.ownedLiterals.size - 1»,«ENDIF»
			«ENDFOR»
		};
	'''
	
	static def defaultValue(EnumerationLiteral literal) {
		if (literal.specification != null) {
			" = " + literal.specification.stringValue()
		} else if (GenUtils.hasStereotype(literal, CppInit)) {
			" = " +  UMLUtil.getStereotypeApplication(literal, CppInit).value
		}
	}
}
