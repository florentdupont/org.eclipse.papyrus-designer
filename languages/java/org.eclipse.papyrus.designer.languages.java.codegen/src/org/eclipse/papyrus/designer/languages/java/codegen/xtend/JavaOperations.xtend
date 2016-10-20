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
 
package org.eclipse.papyrus.designer.languages.java.codegen.xtend

import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.papyrus.designer.languages.java.codegen.Constants
import org.eclipse.papyrus.designer.languages.java.codegen.utils.JavaGenUtils
import org.eclipse.papyrus.designer.languages.java.codegen.utils.Modifier
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Default
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Native
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.profile.standard.Create
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.uml2.uml.ParameterDirectionKind

class JavaOperations {
	static def javaOperationImplementation(Operation operation) '''	
		«GenUtils.getBody(operation, Constants.supportedLanguages)»
	'''	
	
	static def javaReturnSpec(Operation operation) {
		if ((operation.type == null) || isConstructor(operation)) {
			JavaOperations.constructorOrVoid(operation)
		} else {
			JavaGenUtils.javaQualifiedName(operation.type, operation.owner) + Modifier.modArray(JavaOperations.returnParameter(operation)) + ' '
		}
	}
	
	static def returnParameter(Operation operation) {
		for (parameter : operation.returnResult()) {
			if (parameter.direction == ParameterDirectionKind.RETURN_LITERAL && parameter.type == operation.type) {
				return parameter
			}
		}
		
		return null;
	}
	
	static def throwss(Operation operation) '''
		«IF operation.raisedExceptions.length > 0»
		throws «FOR re : operation.raisedExceptions SEPARATOR ','»«re.name»«ENDFOR»
		«ENDIF»
	'''
	
	static def constructorOrVoid(Operation operation) {
		if (isConstructor(operation)) {
			''
		} else {
			'void ' 
		}
	}
	
	static def isConstructor(Operation operation) {
		GenUtils.hasStereotype(operation, Create)
	}

	// return a list of owned operations, return emptyset, if null
	static def getOwnedOperations(Classifier cl) {
		val operations = getOwnedOperationsWNull(cl)
		if (operations == null) {
			emptySet
		}
		else {
			operations
		} 
	}
	
	// return a list of owned operations, since this is not supported directly on a classifier
	static def getOwnedOperationsWNull(Classifier cl) {
		if (cl instanceof Class) {
			(cl as Class).ownedOperations
		} else {
			if (cl instanceof DataType) {
				(cl as DataType).ownedOperations
			} else {
				if (cl instanceof Interface) {
					(cl as Interface).ownedOperations
				} else {
					// Sequence{}
				}
			}
		}
	}
	
	static def javaOperationDeclaration(Operation operation) '''
		«JavaDocumentation.javaOperationDoc(operation)»
		«Modifier.methodModifiers(operation)»«JavaOperations.javaReturnSpec(operation)»«operation.name»(«JavaParameter.javaOperationParameters(operation)») «throwss(operation)»«IF mustGenerateBody(operation)»{«ELSE»;«ENDIF»
			«IF mustGenerateBody(operation)»«JavaOperations.javaOperationImplementation(operation)»«ENDIF»
		«IF mustGenerateBody(operation)»}«ENDIF»
	'''
	
	static def isAbstract(Operation operation) {
		return (operation.isAbstract && operation.owner instanceof Classifier && ((operation.owner as Classifier).isAbstract || operation.owner instanceof Enumeration)) 
			|| operation.interface != null
	}
	
	static def isDefault(Operation operation) {
		return (operation.interface != null && UMLUtil.getStereotypeApplication(operation, Default) != null) 
	}
	
	static def mustGenerateBody(Operation operation) {
		if (!isAbstract(operation)) { // Operation is not abstract (not part of interface and not abstract in abstract class)
			return UMLUtil.getStereotypeApplication(operation, Native) == null
		} else if (operation.getInterface() == null) { // Operation is abstract and in an abstract class (unless it is static which means it shouldn't pass validation)
			if (operation.isStatic) {
				return UMLUtil.getStereotypeApplication(operation, Native) == null
			} else {
				return false;
			}
		} else if (operation.getInterface() != null) { // Operation is in an interface
			return operation.isStatic || UMLUtil.getStereotypeApplication(operation, Default) != null
		}
		
		return true;
	}
}