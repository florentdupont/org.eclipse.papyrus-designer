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

import org.eclipse.papyrus.C_Cpp.NoCodeGen
import org.eclipse.papyrus.cpp.codegen.utils.CppGenUtils
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.Namespace
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.uml2.uml.VisibilityKind
import org.eclipse.papyrus.codegen.base.GenUtils
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.UMLFactory

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassTypeAndEnum {
	
	static def CppClassTypeAndEnum(Classifier clazz) '''
		«var publicStr = TypesAndEnumsForVisibility(clazz, VisibilityKind.PUBLIC_LITERAL).toString»
		«CppGenUtils.getSection(VisibilityKind.PUBLIC_LITERAL, publicStr)»
		«var protectedStr = TypesAndEnumsForVisibility(clazz, VisibilityKind.PROTECTED_LITERAL).toString»
		«CppGenUtils.getSection(VisibilityKind.PROTECTED_LITERAL, protectedStr)»
		«var privateStr = TypesAndEnumsForVisibility(clazz, VisibilityKind.PRIVATE_LITERAL).toString»
		«CppGenUtils.getSection(VisibilityKind.PRIVATE_LITERAL, privateStr)»
	'''
	
	static def CppClassTypeAndEnumPackage(Package pkg) {
		TypesAndEnumsForVisibility(pkg, VisibilityKind.get(VisibilityKind.PACKAGE))
		TypesAndEnumsForVisibility(pkg, VisibilityKind.get(VisibilityKind.PUBLIC))
	}

	static def TypesAndEnumsForVisibility(Namespace ne, VisibilityKind visibilityFilter) '''
		«FOR ownedElement : ne.ownedElements»
			«TypeAndEnumForVisibility(ownedElement, visibilityFilter)»
		«ENDFOR»
	'''

	static def TypeAndEnumForVisibility(Element element, VisibilityKind visibilityFilter) {
		if ((!GenUtils.hasStereotype(element, NoCodeGen)) && (element instanceof Classifier)) {
			var elementVisibility = (element as Classifier).visibility
			var value = elementVisibility.value
			if ((value == visibilityFilter.value)) {
				if (element instanceof Enumeration) {
					return CppEnumerations.CppEnumerationDefinition(element as Enumeration)
				} else if (element instanceof PrimitiveType) {
					return CppPrimitiveTypes.CppPrimitiveTypeDefinition(element as PrimitiveType)
				} else if ((element instanceof DataType || element.eClass.equals(UMLFactory.eINSTANCE.getUMLPackage().getClass_())) && !(element.owner instanceof Package)) {
					if (GenUtils.isTemplateBoundElement(element as Classifier)) {
						return CppInnerClassifiers.CppInnerBindDefinition(element as Classifier)
					} else {
						return CppInnerClassifiers.CppInnerClassDefinition(element as Classifier)
					}
					
				}
			}
		}
	}
}
