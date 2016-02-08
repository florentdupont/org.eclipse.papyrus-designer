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

import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Parameter
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.Modifier
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Behavior
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array
import org.eclipse.uml2.uml.util.UMLUtil

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppParameter {
	
	static def CppOperationParameters(Operation operation, boolean showDefault) '''
		«FOR ownedParameter : operation.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL] SEPARATOR ', '»«CppParameter(ownedParameter, showDefault)»«ENDFOR»'''
	
	/**  
	 * comment signature for a given behavior (e.g. effect within state machine)
	 */ 
	static def CppBehaviorParameters(Behavior behavior, boolean showDefault) '''
		«FOR ownedParameter : behavior.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL] SEPARATOR ', '»«CppParameter(ownedParameter, showDefault)»«ENDFOR»
	'''
	
	/**
	 * C++ parameter. Default values are added, if parameter showDefault is true (implementation signature
	 */ 
	static def CppParameter(Parameter parameter, boolean showDefault) {
		Modifier.modCVQualifier(parameter) + Modifier.modSCQualifier(parameter) + CppGenUtils.cppQualifiedName(parameter.type) +
			Modifier.modPtr(parameter) + Modifier.modRef(parameter) + Modifier.dirInfo(parameter) + " " + parameter.name +
			Modifier.modArray(parameter) + {if (showDefault) defaultValue(parameter) else ""}
	}
	
	/**
	 * CppParameterCalculation for CDT
	 */
	static def CppParameterForCDT(Parameter parameter) {
		var paramStr = Modifier.modCVQualifier(parameter) + Modifier.modSCQualifier(parameter) + CppGenUtils.cppQualifiedName(parameter.type) +
			Modifier.modPtr(parameter) + Modifier.modRef(parameter)
		if (UMLUtil.getStereotypeApplication(parameter, Array) != null) {
			paramStr += "[]"
		}
		return paramStr
	}	
	 
	static def defaultValue(Parameter parameter) {
		if (parameter.defaultValue != null)  " = " + parameter.defaultValue.stringValue() else ""
	}
}