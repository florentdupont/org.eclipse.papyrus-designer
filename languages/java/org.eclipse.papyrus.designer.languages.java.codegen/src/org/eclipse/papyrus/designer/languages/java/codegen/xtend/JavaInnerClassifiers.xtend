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

import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Enumeration

class JavaInnerClassifiers {
	static def javaInnerClassDefinition(Classifier classifier) '''
		«JavaDocumentation.javaElementDoc(classifier)»
		«JavaClassifierGenerator.classVisibility(classifier)» «JavaClassifierGenerator.classModifiers(classifier)»«JavaClassifierGenerator.classifierType(classifier)» «classifier.name»«JavaTemplates.templateSignature(classifier)»«JavaClassInheritedDeclarations.
			javaClassInheritedDeclarations(classifier)» {
			«IF classifier instanceof Enumeration»«JavaEnumerations.javaEnumerationLiterals(classifier as Enumeration)»«ENDIF»
			«JavaClassTypeAndEnum.javaClassTypeAndEnum(classifier)»
			««« TODO: Not possible to model inner classifier in an Enumeration in UML»
			«JavaClassAttributesDeclaration.javaClassAttributesDeclaration(classifier).toString»
			«JavaClassOperationsDeclaration.javaClassOperationsDeclaration(classifier).toString»
		};
	'''
}