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
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.VisibilityKind

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassOperationsDeclaration {
	
	static def CppClassOperationsDeclaration(Classifier clazz, VisibilityKind visibilityFilter) '''
		«FOR op : CppOperations.getOwnedOperations(clazz).filter[it.visibility == visibilityFilter]»
		
		«CppOperations.CppOperationDeclaration(op)»
		«ENDFOR»
		«FOR b : clazz.eAllContents.toList.filter(OpaqueBehavior).filter[it.visibility == visibilityFilter]»
			«IF b.specification == null»
			
				// opaque behavior without specification (typically from state machine)
				«CppOperations.CppBehaviorDeclaration(b)»
			«ENDIF»
		«ENDFOR»
	'''
}