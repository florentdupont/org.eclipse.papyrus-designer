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
import org.eclipse.uml2.uml.VisibilityKind

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassAttributesDeclaration {
	static def CppClassAttributesDeclaration(Classifier clazz, VisibilityKind visibilityFilter) '''
		«FOR oa : CppAttribute.getOwnedAttributes(clazz).filter[it.visibility == visibilityFilter]»
			«CppAttribute.CppAttributeDeclaration(oa)»
		«ENDFOR»
	'''
}