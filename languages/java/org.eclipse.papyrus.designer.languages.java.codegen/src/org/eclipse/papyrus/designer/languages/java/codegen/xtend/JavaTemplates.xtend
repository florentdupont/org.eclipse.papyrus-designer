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

import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.papyrus.designer.languages.java.codegen.utils.JavaGenUtils
import org.eclipse.uml2.uml.Classifier

class JavaTemplates {
	static def templateSignature(Classifier clazz)'''
		«IF (isTemplate(clazz))»
			<«FOR templateParam : GenUtils.getTemplateParameters(clazz) SEPARATOR ', '»«JavaGenUtils.getTemplateTypeName(templateParam)»«ENDFOR»>
		«ENDIF»
	'''
	
	static def isTemplate(Classifier clazz) {
		GenUtils.getTemplateParameters(clazz).size() > 0
	}
}