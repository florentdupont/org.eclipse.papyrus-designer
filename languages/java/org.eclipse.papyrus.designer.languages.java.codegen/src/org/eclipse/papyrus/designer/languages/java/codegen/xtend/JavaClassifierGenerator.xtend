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

import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.VisibilityKind
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier
import org.eclipse.papyrus.designer.languages.java.codegen.utils.JavaGenUtils

class JavaClassifierGenerator {
	
	static def getSortedIncludePathList(Classifier classifier, String prefix) {
		var importPathList = JavaClassImportClassDeclaration.javaClassAllImports(classifier, prefix).sort;
		return importPathList
	}
	
	static def generateClassCode(Classifier classifier, String prefix) '''
		«packageDeclaration(classifier, prefix)»
		
		«FOR path : getSortedIncludePathList(classifier, prefix)»
			«JavaImportUtil.importDirective(path)»
		«ENDFOR»
		
		«JavaImportUtil.javaImport(classifier)»
		
		«JavaGenUtils.openNS(classifier)»
		/************************************************************/
		«JavaDocumentation.javaElementDoc(classifier)»
		«classVisibility(classifier)» «classModifiers(classifier)»«classifierType(classifier)» «classifier.name»«JavaTemplates.templateSignature(classifier)»«JavaClassInheritedDeclarations.
			javaClassInheritedDeclarations(classifier)» {
			«IF classifier instanceof Enumeration»«JavaEnumerations.javaEnumerationLiterals(classifier as Enumeration)»«ENDIF»
			«JavaClassTypeAndEnum.javaClassTypeAndEnum(classifier)»
			««« TODO: Not possible to model inner classifier in an Enumeration in UML»
			«JavaClassAttributesDeclaration.javaClassAttributesDeclaration(classifier).toString»
			«JavaClassOperationsDeclaration.javaClassOperationsDeclaration(classifier).toString»
		};
	'''
	
	static def classifierType(Classifier classifier) {
		if (classifier instanceof Interface) {
			return 'interface'
		} else if (classifier instanceof Enumeration) {
			return 'enum'
		} else {
			return 'class'
		}
	}
	
	static def classVisibility(Classifier classifier) {
		if (classifier.owner instanceof Classifier) {
			return classifier.visibility.toString.toLowerCase
		} else {
			if (classifier.visibility == VisibilityKind.PUBLIC_LITERAL) {
				return "public"
			}
			// protected and private are not legal modifiers of root classes
			return "" //Java package-private visibility (default) for class
		}
	}
	
	static def classModifiers(Classifier classifier) { // TODO refactor this in Modifier.java
		var String result = ""
			
		if (!(classifier instanceof Enumeration) && !(classifier instanceof Interface)) {
			if (classifier.abstract) {
				result += "abstract "
			} else if (classifier.leaf) {
				result += "final "
			}
		}
		
		if (classifier.owner instanceof Classifier) {
			if (UMLUtil.getStereotypeApplication(classifier, StaticClassifier) != null) {
				result += "static "
			}
		}
		
		if (UMLUtil.getStereotypeApplication(classifier, Strictfp) != null) {
			result += "strictfp "
		}
		
		return result
	}
	
	static def packageDeclaration(Classifier classifier, String prefix) {
		var qName = prefix + GenUtils.getFullPath(classifier.package, ".", false)
		
		if (qName.endsWith(".")) {
			qName = qName.substring(0, qName.length - 1)
		}
		
		if (qName.empty) {
			return ""
		}
		
		return "package " + qName + ";"
	}
}
