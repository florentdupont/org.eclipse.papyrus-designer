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
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.UMLFactory

class JavaClassTypeAndEnum {
	
	static def javaClassTypeAndEnum(Classifier clazz) '''
		«FOR ownedElement : clazz.ownedElements»
			«org.eclipse.papyrus.designer.languages.java.codegen.xtend.JavaClassTypeAndEnum.typeAndEnum(ownedElement)»
		«ENDFOR»
	'''

	static def typeAndEnum(Element element) {
		if ((!GenUtils.hasStereotype(element, NoCodeGen)) && (element instanceof Classifier)) {
			if ((element instanceof Enumeration || element instanceof Interface || element.eClass.equals(UMLFactory.eINSTANCE.getUMLPackage().getClass_())) && !(element.owner instanceof Package)) {
				return JavaInnerClassifiers.javaInnerClassDefinition(element as Classifier)
			}
		}
	}
}
