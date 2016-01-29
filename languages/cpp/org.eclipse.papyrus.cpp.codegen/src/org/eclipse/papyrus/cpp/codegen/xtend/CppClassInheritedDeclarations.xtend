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
import org.eclipse.papyrus.C_Cpp.Visibility
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Generalization
import org.eclipse.uml2.uml.InterfaceRealization
import org.eclipse.uml2.uml.Relationship
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.cpp.codegen.utils.CppGenUtils
import org.eclipse.papyrus.codegen.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassInheritedDeclarations {
	
	static def CppClassInheritedDeclarations(Classifier clazz) '''
		«IF (filteredRelationships(clazz).length > 0)» : «ENDIF»
		«FOR fr: filteredRelationships(clazz) SEPARATOR ', \n'»
			«getCppVisibility(fr)» «CppGenUtils.cppQualifiedName(fr.targets.get(0) as Classifier)»«ENDFOR»	
	'''
	
	//Return a list of relationships that are either Generalizations or InterfaceRealiazations
	//and the target does not point to a classifier that has the no-code-gen Stereotype
	//TODO: not sure if it makes sense NOT to declare an inheritance, since we do not generate code.
	static def filteredRelationships(Classifier clazz) {
		clazz.sourceDirectedRelationships.filter[((it instanceof Generalization) || (it instanceof InterfaceRealization)) &&
				(it.targets.size > 0) && (!GenUtils.hasStereotype(it.targets.get(0), NoCodeGen))
		]
	}
	
	static def getCppVisibility(Relationship relationship) {
		if (GenUtils.hasStereotype(relationship, Visibility)) {
			(UMLUtil.getStereotypeApplication(relationship, Visibility) as Visibility).value 
		} else { 
			'public'
		}
	}
}