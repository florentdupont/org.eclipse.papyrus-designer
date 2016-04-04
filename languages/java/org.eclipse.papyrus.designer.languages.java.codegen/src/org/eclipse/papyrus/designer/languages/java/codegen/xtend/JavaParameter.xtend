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

import org.eclipse.papyrus.designer.languages.java.codegen.utils.JavaGenUtils
import org.eclipse.papyrus.designer.languages.java.codegen.utils.Modifier
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.util.UMLUtil

class JavaParameter {
	
	static def javaOperationParameters(Operation operation) '''
		«FOR ownedParameter : operation.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL] SEPARATOR ', '»«JavaParameter.javaParameter(ownedParameter)»«ENDFOR»'''
	
	/**
	 * Java parameter. Default values are added, if parameter showDefault is true (implementation signature
	 */ 
	static def javaParameter(Parameter parameter) {
		Modifier.parameterModifiers(parameter) + JavaGenUtils.javaQualifiedName(parameter.type, parameter.operation.owner)+ Modifier.modArray(parameter) + Modifier.modVariadic(parameter) + " " + parameter.name
	}
	
	/**
	 * JavaParameterCalculation for JDT
	 */
	static def javaParameterForJDT(Parameter parameter) {
		var paramStr = JavaGenUtils.javaQualifiedName(parameter.type, parameter.operation.owner)
		if (UMLUtil.getStereotypeApplication(parameter, Array) != null) {
			paramStr += "[]"
		}
		return paramStr
	}	
	 
	static def defaultValue(Parameter parameter) {
		if (parameter.defaultValue != null)  " = " + parameter.defaultValue.stringValue() else ""
	}
}