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

import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.papyrus.designer.languages.cpp.profile.Typedef
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.designer.languages.common.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppPrimitiveTypes {
	
	 /** Support two different kinds of primitive types
	 * (1) those that are native types of the programming language such as long
	 *     For these, no additional definition has to be done and they should be referenced
	 *     with their name only
	 * (2) those that correspond to a typedef (e.g. typedef long ErrorType). These require
	 *     a typedef definition within the package and need to be referenced with their
	 *     fully qualified name (e.g. MyPackage::ErrorType)
	 * (3) Sometimes the name of the type defined by a typedef does not appear at the end, e.g.
	 *     when defining functionPointers such as "typedef int (*HandlerFct) (void*);"
	 *	   (a function with a void* argument returning int)
	 *     Support this case via a specific 'typeName' constant that gets replaced by the name of the
	 *     type
	 */
	static def CppPrimitiveTypeDefinition(PrimitiveType primitiveType) '''
		«CppDocumentation.CppElementDoc(primitiveType)»
		«IF GenUtils.hasStereotype(primitiveType, Typedef)»
		typedef «UMLUtil.getStereotypeApplication(primitiveType, Typedef).definition.replaceAll('typeName', primitiveType.name)»
			«IF !UMLUtil.getStereotypeApplication(primitiveType, Typedef).definition.contains('typeName')»«primitiveType.name»«ENDIF»;
			
		«ELSE»
		«CppGenUtils.getStdtypes(primitiveType)»
		 
		«ENDIF»
	'''
}