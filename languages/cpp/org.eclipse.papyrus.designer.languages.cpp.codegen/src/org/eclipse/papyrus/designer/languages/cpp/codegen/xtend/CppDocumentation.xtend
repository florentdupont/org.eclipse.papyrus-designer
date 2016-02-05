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

import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.Behavior
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.papyrus.designer.languages.common.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppDocumentation {
	static def CppElementDoc(Element argument) '''
		/**
		 * «GenUtils.getComments(argument).replaceAll('\n', '\n * ')»
		 */
	'''
	
	static def CppOperationDoc(Operation operation) '''
		/**
		 * «GenUtils.getComments(operation).replaceAll('\n', '\n * ')»
		 «FOR op: operation.ownedParameters»«CppParamDoc(op)»«ENDFOR»
		 */
	'''
	
	static def CppParamDoc(Parameter parameter) '''
		* «IF (parameter.direction == ParameterDirectionKind.RETURN_LITERAL)
		  »@return«ELSE»@param«ENDIF» «parameter.name» «GenUtils.getComments(parameter).replaceAll('\n', '\n *    ')»
	'''
	
	static def CppBehaviorDoc(Behavior behavior) '''
		/**
		 * «GenUtils.getComments(behavior).replaceAll('\n', '\n * ')»
		 * «FOR op : behavior.ownedParameters»«CppParamDoc(op)»«ENDFOR»
		 */
	'''
}
