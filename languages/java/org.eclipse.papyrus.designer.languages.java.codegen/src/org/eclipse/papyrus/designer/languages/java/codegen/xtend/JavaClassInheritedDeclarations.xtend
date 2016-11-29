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

import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Generalization
import org.eclipse.uml2.uml.InterfaceRealization
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.papyrus.designer.languages.java.codegen.utils.JavaGenUtils

class JavaClassInheritedDeclarations {
	
	static def javaClassInheritedDeclarations(Classifier clazz) '''
		«IF (!(clazz instanceof Enumeration) && generalizations(clazz).length > 0)» extends «JavaGenUtils.javaQualifiedName((generalizations(clazz).get(0).targets.get(0) as Classifier), (generalizations(clazz).get(0).targets.get(0) as Classifier).owner)»«ENDIF»«IF (realizations(clazz).length > 0)» implements «ENDIF»«FOR fr: realizations(clazz) SEPARATOR ", "»«JavaGenUtils.javaQualifiedName(fr.targets.get(0) as Classifier, (fr.targets.get(0) as Classifier).owner)»«ENDFOR»
	'''
	
	/**
	 * Return a list of relationships that are Generalizations
	 * and the target does not point to a classifier that has the no-code-gen Stereotype
	 */
	static def generalizations(Classifier clazz) {
		clazz.sourceDirectedRelationships.filter[(it instanceof Generalization) &&
				(it.targets.size > 0) && (!GenUtils.hasStereotype(it.targets.get(0), NoCodeGen))
		]
	}
	
	/**
	 * Return a list of relationships that are InterfaceRealizations
	 * and the target does not point to a classifier that has the no-code-gen Stereotype
	 */
	static def realizations(Classifier clazz) {
		clazz.sourceDirectedRelationships.filter[(it instanceof InterfaceRealization) &&
				(it.targets.size > 0) && (!GenUtils.hasStereotype(it.targets.get(0), NoCodeGen))
		]
	}
}