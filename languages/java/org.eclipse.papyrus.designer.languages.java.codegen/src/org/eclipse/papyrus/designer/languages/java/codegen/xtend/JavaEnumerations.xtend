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

import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.EnumerationLiteral

class JavaEnumerations {
	static def javaEnumerationLiterals(Enumeration enumeration) '''
		«FOR i : 0 ..< enumeration.ownedLiterals.size»
			«JavaDocumentation.javaElementDoc(enumeration.ownedLiterals.get(i))»
			«enumeration.ownedLiterals.get(i).name»«enumeration.ownedLiterals.get(i).defaultValue»«IF i < enumeration.ownedLiterals.size - 1»,«ELSE»;«ENDIF»
		«ENDFOR»
	'''
	
	static def defaultValue(EnumerationLiteral literal) {
		if (literal.specification != null) {
			literal.specification.stringValue()
		}
	}
}
