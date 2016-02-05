/*******************************************************************************
 * Copyright (c) 2015 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.codegen.xtend

import org.eclipse.uml2.uml.Classifier
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.uml2.uml.VisibilityKind
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.uml2.uml.NamedElement

/**
 * @author Shuai Li (CEA) <shuai.li@cea.fr>
 */

class CppInnerClassifiers {
	static def CppInnerClassDefinition(Classifier classifier) '''
		«CppDocumentation.CppElementDoc(classifier)»
		«CppTemplates.templateSignature(classifier)»«CppClassifierGenerator.classUnionOrStruct(classifier)» «classifier.name»«CppClassInheritedDeclarations.
			CppClassInheritedDeclarations(classifier)» {
		«CppClassFriendDeclaration.CppClassIncludeFriendDeclaration(classifier)»«CppClassTypeAndEnum.CppClassTypeAndEnum(classifier)»
			«var publicVisibility = VisibilityKind.PUBLIC_LITERAL»
			«CppGenUtils.getSection(publicVisibility, CppClassifierGenerator.defaultInitializer(classifier))»
			«CppGenUtils.getSection(publicVisibility,
			CppClassAttributesDeclaration.CppClassAttributesDeclaration(classifier, publicVisibility).toString)»
			«CppGenUtils.getSection(publicVisibility,
			CppClassOperationsDeclaration.CppClassOperationsDeclaration(classifier, publicVisibility).toString)»

			«var protectedVisibility = VisibilityKind.PROTECTED_LITERAL»
			«CppGenUtils.getSection(protectedVisibility,
			CppClassAttributesDeclaration.CppClassAttributesDeclaration(classifier, protectedVisibility).toString)»
			«CppGenUtils.getSection(protectedVisibility,
			CppClassOperationsDeclaration.CppClassOperationsDeclaration(classifier, protectedVisibility).toString)»

			«var privateVisibility = VisibilityKind.PRIVATE_LITERAL»
			«CppGenUtils.getSection(privateVisibility,
			CppClassAttributesDeclaration.CppClassAttributesDeclaration(classifier, privateVisibility).toString)»
			«CppGenUtils.getSection(privateVisibility,
			CppClassOperationsDeclaration.CppClassOperationsDeclaration(classifier, privateVisibility).toString)»
		};
	'''

	static def CppInnerBindDefinition(Classifier classifier) '''
		«var tb = GenUtils.getTemplateBinding(classifier)»
		«var templateElement = tb.targets.get(0)»
		/************************************************************/
		typedef «(templateElement.owner as NamedElement).name»<«FOR ps : tb.parameterSubstitutions SEPARATOR ', '»«
			CppTemplates.CppTemplateBindingParameter(ps)»«ENDFOR»> «classifier.name»;
	'''	
}