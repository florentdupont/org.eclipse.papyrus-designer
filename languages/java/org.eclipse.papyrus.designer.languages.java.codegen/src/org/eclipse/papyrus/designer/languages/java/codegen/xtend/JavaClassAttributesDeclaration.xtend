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

import org.eclipse.uml2.uml.Classifier

class JavaClassAttributesDeclaration {
	static def javaClassAttributesDeclaration(Classifier clazz) '''
		«FOR oa : JavaAttribute.getOwnedAttributes(clazz)»
			«JavaAttribute.javaAttributeDeclaration(oa)»
		«ENDFOR»
	'''
}