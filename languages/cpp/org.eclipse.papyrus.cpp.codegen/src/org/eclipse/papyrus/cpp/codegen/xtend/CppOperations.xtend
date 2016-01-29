package org.eclipse.papyrus.cpp.codegen.xtend

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
 
 import org.eclipse.papyrus.C_Cpp.Inline
import org.eclipse.papyrus.C_Cpp.Virtual
import org.eclipse.papyrus.cpp.codegen.utils.CppGenUtils
import org.eclipse.papyrus.cpp.codegen.utils.Modifier
import org.eclipse.uml2.uml.Behavior
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.profile.standard.Create
import org.eclipse.uml2.uml.profile.standard.Destroy
import org.eclipse.papyrus.C_Cpp.ConstInit
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.cpp.codegen.Constants
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.Region
import org.eclipse.papyrus.cpp.codegen.utils.ClassUtils
import org.eclipse.papyrus.codegen.base.GenUtils
import org.eclipse.papyrus.C_Cpp.Variadic
import org.eclipse.uml2.uml.ParameterDirectionKind

class CppOperations {
	static def CppOperationImplementation(Operation operation) '''
		«CppDocumentation.CppOperationDoc(operation)»
		«IF (operation.name == 'main')»
			«CppReturnSpec(operation)»«operation.name»(«CppParameter.CppOperationParameters(operation,false)») {
				«GenUtils.getBody(operation, Constants.supportedLanguages)»
			} 
		«ELSE»
			«CppTemplates.templateSignature(operation)»«InlineTxt(operation)»«CppReturnSpec(operation)»«GenUtils.getNestedOperationFarthestClassifierOwnerNamespace(operation)»«CppTemplates.templateShortSignature(operation)»::«destructor(operation)»«operation.name»(«CppParameter.CppOperationParameters(operation, false)»«variadicParameter(operation)»)«throwss(operation)»«Modifier.modCVQualifier(operation)»«CppConstInit(operation)» {
				«GenUtils.getBody(operation, Constants.supportedLanguages)»
			}
		«ENDIF»
	'''	
	
	static def CppReturnSpec(Operation operation) {
		if ((operation.type == null) || isConsOrDestructor(operation)) {
			ConsDestructorOrVoid(operation)
		} else {
			Modifier.modCVQualifier(operation.getReturnResult())+' '+CppGenUtils.cppQualifiedName(operation.type)+Modifier.modPtr(operation.getReturnResult())+Modifier.modRef(operation.getReturnResult())+' ' 
		}
	}
	
	static def CppReturnSpec(Behavior behavior) '''
		«IF (GenUtils.returnResult(behavior) == null)»void «ELSE»«Modifier.modCVQualifier(GenUtils.returnResult(behavior))» «CppGenUtils.cppQualifiedName(GenUtils.returnResult(behavior).type)»«Modifier.modPtr(GenUtils.returnResult(behavior))»«Modifier.modRef(GenUtils.returnResult(behavior))» «ENDIF»
	'''
	
	static def throwss(Operation operation) '''
		«IF operation.raisedExceptions.length > 0»
		throws «FOR re : operation.raisedExceptions SEPARATOR ','»«re.name»«ENDFOR»
		«ENDIF»
	'''
	
	static def ConsDestructorOrVoid(Operation operation) {
		if (isConsOrDestructor(operation)) {
		
		} else {
			'void ' 
		}
	}
	
	static def isConsOrDestructor(Operation operation) {
		GenUtils.hasStereotype(operation, Create) || GenUtils.hasStereotype(operation, Destroy)
	}
	
	static def CppConstInit(Operation operation) {
		if (GenUtils.hasStereotype(operation, ConstInit) && GenUtils.hasStereotype(operation, Create)) {
			": " + UMLUtil.getStereotypeApplication(operation, ConstInit).initialisation
		}
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
	
	static def getNestedOperations(Classifier c1) {
		val operations = getNestedOperationsWNull(c1)
		if (operations == null) {
			emptySet
		}
		else {
			operations
		}
	}
	
	static def getNestedOperationsWNull(Classifier cl) {
		if (cl instanceof Class || cl instanceof Interface) {
			ClassUtils.nestedOperations(cl)
		}
	}
	
	static def CppBehaviorImplementation(OpaqueBehavior behavior) '''
		«CppDocumentation.CppBehaviorDoc(behavior)»
		«CppReturnSpec(behavior)»«GenUtils.getNestedBehaviorFarthestClassifierOwnerNamespace(behavior)»::«behavior.qualifiedBehaviorName»(«CppParameter.CppBehaviorParameters(behavior, false)»)«Modifier.modCVQualifier(behavior)» {
			«GenUtils.getBodyFromOB(behavior, Constants.supportedLanguages)»
		}
	'''
	
	static def CppOperationDeclaration(Operation operation) '''
		«CppDocumentation.CppOperationDoc(operation)»
		«InlineTxt(operation)»«virtualTxt(operation)»«staticTxt(operation)»«CppReturnSpec(operation)»«destructor(operation)»«operation.name»(«CppParameter.CppOperationParameters(operation,true)»«variadicParameter(operation)»)«Modifier.modCVQualifier(operation)»«virtualSuffix(operation)»;
	'''
	
	static def InlineTxt(Element element) {
		if (GenUtils.hasStereotype(element, Inline)) 'inline '
	}
	
	static def virtualTxt(Operation operation) {
		if ((operation.interface != null) || (operation.isAbstract) || (GenUtils.hasStereotype(operation, Virtual))) 'virtual '
	}
	
	static def staticTxt(Operation operation) {
		if (operation.isStatic) 'static '
	}
	
	static def destructor(Operation operation) {
		if (GenUtils.hasStereotype(operation, Destroy) && (!operation.name.startsWith('~'))) {
			'~'
		} else { 
			''
		}
	}
		
	static def public virtualSuffix(Operation operation) {
		if ((operation.interface != null) || (operation.isAbstract)) ' = 0'
	}
	
	static def CppBehaviorDeclaration(Behavior behavior) '''
		«CppDocumentation.CppBehaviorDoc(behavior)»
		«InlineTxt(behavior)»«CppReturnSpec(behavior)»«behavior.qualifiedBehaviorName»(«CppParameter.CppBehaviorParameters(behavior, true)»)«Modifier.modCVQualifier(behavior)»;
	'''
	
	static def qualifiedBehaviorName(Behavior behavior) {
		var ne = behavior as NamedElement
		var name = behavior.name
		while ((ne != null) && !(ne instanceof Classifier) || (ne instanceof Behavior)) {
			if (ne.owner instanceof NamedElement) {
				ne = ne.owner as NamedElement
			}
			if (ne.owner instanceof Region) {
				name = ne.name + "_" + name
			}
		}
		return name
	}
	
	static def variadicParameter(Operation operation) {
		var hasParameters = false;
		var i = 0;
		if (GenUtils.hasStereotype(operation, Variadic)) {
			while (i < operation.ownedParameters.size && !hasParameters) {
				if (operation.ownedParameters.get(i).direction != ParameterDirectionKind.RETURN_LITERAL) {
					hasParameters = true;
				}
			}
			
			if (hasParameters) {
				', ...'
			} else {
				'...'
			}
		}
	}
}