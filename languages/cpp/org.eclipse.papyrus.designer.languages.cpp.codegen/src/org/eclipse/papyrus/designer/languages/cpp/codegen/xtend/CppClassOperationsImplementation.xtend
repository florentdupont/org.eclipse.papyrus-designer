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
import org.eclipse.papyrus.designer.languages.cpp.profile.NoCodeGen
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.papyrus.designer.languages.cpp.profile.Inline
import org.eclipse.papyrus.designer.languages.common.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppClassOperationsImplementation {
	
	// implementations of a classifier, parameter controls whether only inline (or only not inline) operations
	//are produced
	static def CppClassOperationsImplementation(Classifier clazz, boolean inline) '''
		«FOR ownedOperation : CppOperations.getOwnedOperations(clazz).filter[(!(GenUtils.hasStereotype(it, NoCodeGen) || it.isAbstract)) && (GenUtils.hasStereotype(it, org.eclipse.papyrus.designer.languages.cpp.profile.Inline) == inline)] SEPARATOR "\n"»
			«CppOperations.CppOperationImplementation(ownedOperation)»
		«ENDFOR»
		
		«FOR nestedOperation : CppOperations.getNestedOperations(clazz).filter[(!(GenUtils.hasStereotype(it, NoCodeGen) || it.isAbstract)) && (GenUtils.hasStereotype(it, org.eclipse.papyrus.designer.languages.cpp.profile.Inline) == inline)] SEPARATOR "\n"»
			«CppOperations.CppOperationImplementation(nestedOperation)»
		«ENDFOR»
		
		«FOR b : clazz.eAllContents.toIterable.filter[(it instanceof OpaqueBehavior) && GenUtils.hasStereotype((it as OpaqueBehavior), Inline)==inline]»
			«IF (b as OpaqueBehavior).specification == null»
				// opaque behavior without specification (typically from state machine)
				«CppOperations.CppBehaviorImplementation(b as OpaqueBehavior)»
			«ENDIF»
		«ENDFOR»
	'''
	
}