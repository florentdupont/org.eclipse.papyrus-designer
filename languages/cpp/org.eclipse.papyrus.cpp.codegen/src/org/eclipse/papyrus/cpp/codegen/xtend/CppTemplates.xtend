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
 
 package org.eclipse.papyrus.cpp.codegen.xtend

import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Classifier
import org.eclipse.papyrus.cpp.codegen.utils.CppGenUtils
import org.eclipse.uml2.uml.TemplateParameterSubstitution
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.LiteralInteger
import org.eclipse.papyrus.codegen.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppTemplates {
	
	static def templateSignature(Operation operation) {
		templateSignature(operation.owner as Classifier)
	}
	
	static def templateSignature(Classifier clazz)'''
		«IF (isTemplate(clazz))»
			template<«FOR templateParam : GenUtils.getTemplateParameters(clazz) SEPARATOR ', '»«CppGenUtils.getTemplateTypeName(templateParam)»«ENDFOR»>
		«ENDIF»
	'''
	
	static def templateShortSignature(Operation operation) {
		templateShortSignature(operation.owner as Classifier)
	}
	
	static def templateShortSignature(Classifier clazz)'''
		«IF (isTemplate(clazz))»
			<«FOR templateParam : GenUtils.getTemplateParameters(clazz) SEPARATOR ', '»«GenUtils.getTemplateName(templateParam)»«ENDFOR»>
		«ENDIF»
	'''
	
	static def isTemplate(Classifier clazz) {
		GenUtils.getTemplateParameters(clazz).size() > 0
	}
	
	static def CppTemplateBindingParameter(TemplateParameterSubstitution tps) {
		if (tps.actual instanceof LiteralInteger) {
			(tps.actual as LiteralInteger).value
		} else {
			(tps.actual as NamedElement).name
		}
	}
}