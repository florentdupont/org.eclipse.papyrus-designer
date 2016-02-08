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

import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Union
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.papyrus.designer.languages.cpp.codegen.preferences.CppCodeGenUtils
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.VisibilityKind
import org.eclipse.uml2.uml.profile.standard.Create

/**
 * @author Ansgar Radermacher (ansgar.radermacher@cea.fr)
 */
class CppClassifierGenerator {

	static def generateBindHeaderCode(Classifier classifier) ''' 
		#ifndef «GenUtils.getFullNameUC(classifier)»_H
		#define «GenUtils.getFullNameUC(classifier)»_H
		
		/************************************************************
		              «classifier.name» template binding header
		 ************************************************************/
		
		«CppIncludeUtil.includeDirective(CppClassIncludeClassDeclaration.cppOwnerPackageIncludePath(classifier.package))»
		
		«CppIncludeUtil.CppIncludeHeader(classifier)»
		
		«var tb = GenUtils.getTemplateBinding(classifier)»
		«var templateElement = tb.targets.get(0)»
		«CppIncludeUtil.includeDirective(
			GenUtils.getFullPath(classifier.package) + '/' + (templateElement.owner as NamedElement).name + '.' +
				CppCodeGenUtils.getHeaderSuffix())»
		
		«FOR path : getSortedDeclarePathList(classifier)» 
			«CppIncludeUtil.declareDirective(path)»
		«ENDFOR»
		
		«CppGenUtils.openNS(classifier)»
		/************************************************************/
		typedef «(templateElement.owner as NamedElement).name»<«FOR ps : tb.parameterSubstitutions SEPARATOR ', '»«
			CppTemplates.CppTemplateBindingParameter(ps)»«ENDFOR»> «classifier.name»;
		
		«CppGenUtils.closeNS(classifier)»
		
		/************************************************************
		              End of «classifier.name» template binding header
		 ************************************************************/
		
		#endif
	'''
	
	static def getSortedIncludePathList(Classifier classifier) {
		var includePathList = CppClassIncludeClassDeclaration.CppClassAllIncludes(classifier).sort;
		return includePathList
	}
	
	static def getSortedDeclarePathList(Classifier classifier) {
		var declarePathList = CppClassIncludeClassDeclaration.CppClassAllDeclares(classifier).sort;
		return declarePathList
	}

	static def generateBindBodyCode(Classifier classifier) '''
		#define «GenUtils.getFullNameUC(classifier)»_BODY
		
		/************************************************************
		              «classifier.name» template binding body
		 ************************************************************/
		
		«CppIncludeUtil.CppIncludePreBody(classifier)»
		
		// include associated header file
		«CppIncludeUtil.includeDirective(
			GenUtils.getFullPath(classifier.package) + '/' + classifier.name + '.' + CppCodeGenUtils.getHeaderSuffix())»
		
		
		// Derived includes directives
		«FOR path : CppClassIncludeClassDeclaration.CppClassAllIncludesDeclarationBody(classifier).sort» 
			«CppIncludeUtil.includeDirective(path)»
		«ENDFOR»
		
		«CppIncludeUtil.CppIncludeBody(classifier)»
		
		«CppGenUtils.openNS(classifier)»
		
		/************************************************************/
		«var tb = GenUtils.getTemplateBinding(classifier)»
		«var templateElement = tb.targets.get(0)»
		template class «(templateElement.owner as NamedElement).name»<«FOR ps : tb.parameterSubstitutions SEPARATOR ', '»«
			CppTemplates.CppTemplateBindingParameter(ps)»«ENDFOR»>;
		
		«CppGenUtils.closeNS(classifier)»
		
		/************************************************************
		              End of «classifier.name» template binding body
		 ************************************************************/
	'''
	
	static def generateClassHeaderCode(Classifier classifier) '''
		#ifndef «GenUtils.getFullNameUC(classifier)»_H
		#define «GenUtils.getFullNameUC(classifier)»_H
		
		/************************************************************
		              «classifier.name» class header
		 ************************************************************/
		
		«CppIncludeUtil.includeDirective(CppClassIncludeClassDeclaration.cppOwnerPackageIncludePath(classifier.package))»
		
		«FOR path : getSortedIncludePathList(classifier)»
			«CppIncludeUtil.includeDirective(path)»
		«ENDFOR»
		
		«CppIncludeUtil.CppIncludeHeader(classifier)»
		
		«FOR path : getSortedDeclarePathList(classifier)» 
			«CppIncludeUtil.declareDirective(path)»
		«ENDFOR»
		
		«CppGenUtils.openNS(classifier)»
		
		/************************************************************/
		«CppDocumentation.CppElementDoc(classifier)»
		«CppTemplates.templateSignature(classifier)»«classUnionOrStruct(classifier)» «classifier.name»«CppClassInheritedDeclarations.
			CppClassInheritedDeclarations(classifier)» {
		    «CppClassFriendDeclaration.CppClassIncludeFriendDeclaration(classifier)»«CppClassTypeAndEnum.CppClassTypeAndEnum(classifier)»
			«var publicVisibility = VisibilityKind.PUBLIC_LITERAL»
			«CppGenUtils.getSection(publicVisibility, defaultInitializer(classifier))»
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
		/************************************************************/
		/* External declarations (package visibility)               */
		«var packageVisibility = VisibilityKind.PACKAGE_LITERAL»
		«CppClassAttributesDeclaration.CppClassAttributesDeclaration(classifier, packageVisibility)»
		«CppClassOperationsDeclaration.CppClassOperationsDeclaration(classifier, packageVisibility)»
		/************************************************************/
		
		«IF CppTemplates.isTemplate(classifier)»
			/************************************************************/
			/* Template functions                                       */
			«CppClassOperationsImplementation.CppClassOperationsImplementation(classifier, false)»
		«ENDIF»
		
		/* Inline functions                                         */
		«CppClassOperationsImplementation.CppClassOperationsImplementation(classifier, true)»
		«CppGenUtils.closeNS(classifier)»
		
		/************************************************************
		              End of «classifier.name» class header
		 ************************************************************/
		
		#endif
	'''
	
	static def classUnionOrStruct(Classifier classifier) {
		if (GenUtils.hasStereotype(classifier, Union)) {
			return 'union'
		} else {
			if (classifier instanceof DataType) {
				CppGenUtils.resetVisibility(VisibilityKind.PUBLIC_LITERAL)
				return 'struct'
			} else {
				CppGenUtils.resetVisibility(VisibilityKind.PRIVATE_LITERAL)
				return 'class'
			}
		}
	}

	/**
	 * default initializer for non-static attributes with a default value TODO: should be disabled by default, since non-static members can be initialized directly in C++ 011
	 */
	static def defaultInitializer(Classifier classifier) {

		/*	Bug 422373: The default initializer should not be generated if there are any user-defined
		constructors.  In plain C++ code, the existence of a constructor with parameters
		means that the compiler will not synthesize a default one.
		
		E.g., this would be invalid in plain C++ code:
		class T1 { };
		class T2 { public: T(int); };
		T1 * t1 = new T1;     // OK, sythesized default constructor used
		T2 * t2a = new T2(5); // OK, user-defined constructor used
		T2 * t2b = new T2; // ERROR, default constructor was not synthesized
		
		Also, if the user has provided a default constructor in the code, we don't need
		to generate a second copy here.  A default constructor should only be generated
		here if the compiler would have synthesized one anyhow.  Otherwise the default
		property values should be set in the constructors that are generated by
		CppClassOperationsDeclaration.
		*/
		var code = '''
		«IF CppOperations.getOwnedOperations(classifier).filter[GenUtils.hasStereotype(it, Create)].empty»
			«var attributeList = classifier.attributes.filter[
			(it.isStatic == false) && (it.defaultValue != null) && (it.defaultValue.stringValue != null)]»
			«IF !attributeList.empty»
				«classifier.name»() : «FOR a : attributeList SEPARATOR ', '»«a.name»(«a.defaultValue.stringValue»)«ENDFOR» {}
			«ENDIF»
		«ENDIF»'''
		return code.trim
	}

	static def generateClassBodyCode(Classifier classifier) '''
		#define «GenUtils.getFullName(classifier)»_BODY
		
		/************************************************************
		              «classifier.name» class body
		 ************************************************************/
		
		«CppIncludeUtil.CppIncludePreBody(classifier)»
		
		// include associated header file
		«CppIncludeUtil.includeDirective(
			GenUtils.getFullPath(classifier.nearestPackage) + '/' + classifier.name + '.' +
				CppCodeGenUtils.getHeaderSuffix())»
		
		// Derived includes directives
		«FOR path : CppClassIncludeClassDeclaration.CppClassAllIncludesDeclarationBody(classifier).sort» 
			«CppIncludeUtil.includeDirective(path)»
		«ENDFOR»
		
		«CppIncludeUtil.CppIncludeBody(classifier)»
		
		«CppGenUtils.openNS(classifier)»
		
		«CppAttribute.CppStaticAttributes(classifier)»
		
		«IF !classifier.isTemplate»
			«CppClassOperationsImplementation.CppClassOperationsImplementation(classifier, false)»
		«ENDIF»
		
		«CppGenUtils.closeNS(classifier)»
		
		/************************************************************
		              End of «classifier.name» class body
		 ************************************************************/
	'''
}
