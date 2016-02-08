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

import org.eclipse.uml2.uml.Classifier
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Friend
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen
import org.eclipse.papyrus.designer.languages.common.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassFriendDeclaration {
	static def CppClassFriendDeclaration(Classifier friend) '''
		friend class «CppGenUtils.cppQualifiedName(friend)»
	'''

	static def CppClassIncludeFriendDeclaration(Classifier clazz) {
		for (uc : GenUtils.getUsedClassifiers(clazz)) {
			if (GenUtils.hasStereotype(uc, Friend) && (!GenUtils.hasStereotype(uc, NoCodeGen))) {
				CppClassFriendDeclaration(uc)
			}
		}
	}
}