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
import org.eclipse.papyrus.designer.languages.cpp.profile.CppInit
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.designer.languages.common.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppEnumerations {
	static def CppEnumerationDefinition(Enumeration enumeration) '''
		«CppDocumentation.CppElementDoc(enumeration)»
		enum «enumeration.name» {
			«FOR ownedLiteral : enumeration.ownedLiterals»
				«CppDocumentation.CppElementDoc(ownedLiteral)»
				«ownedLiteral.name»«IF GenUtils.hasStereotype(ownedLiteral, CppInit)» = «UMLUtil.
				getStereotypeApplication(ownedLiteral, CppInit).value»«ENDIF»,
			«ENDFOR»
		};
	'''
}
