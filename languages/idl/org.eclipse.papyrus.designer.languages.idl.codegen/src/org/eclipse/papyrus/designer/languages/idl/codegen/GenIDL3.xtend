/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ansgar Radermacher - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.idl.codegen;

import org.eclipse.uml2.uml.Class;
import static extension org.eclipse.papyrus.designer.languages.idl.codegen.GenIDL2.*
import org.eclipse.papyrus.designer.languages.idl.codegen.preferences.IDLCodeGenUtils
import static extension org.eclipse.papyrus.designer.languages.idl.codegen.Utils.*

class GenIDL3 {
	public static def genIDL3(Class clazz) '''
		«IDLCodeGenUtils.getCommentHeader»
		// IDL3 for component «clazz.name»
		
		#ifndef D_«clazz.name»
		#define D_«clazz.name»
		
		«clazz.includes»
		
		«clazz.openNamespaceIDL»
		
		component «clazz.name»
		{
			«FOR port : clazz.ownedPorts»
				«FOR intf : port.provideds»
					provide «intf.name» «port.name»;
				«ENDFOR»
				«FOR intf : port.requireds»
					use «intf.name» «port.name»;
				«ENDFOR»
			«ENDFOR»	
		}
		
		home «clazz.name»_home manages «clazz.name»
		{
		}
		
		«clazz.closeNamespaceIDL»
		
		#endif
	'''
}
