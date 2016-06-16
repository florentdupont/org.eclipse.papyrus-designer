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

import org.eclipse.uml2.uml.NamedElement

/**
 * Namespace related tools
 * As for C++, skip top-level namespace (test whether owner != null)
 */
class Utils {
	public static def openNamespaceIDL(NamedElement type) '''
		«FOR ns : type.allNamespaces.clone.reverse»
			«IF ns.owner != null»
				module «ns.name» {
			«ENDIF»
		«ENDFOR»
	'''

	public static def closeNamespaceIDL(NamedElement type) '''
		«FOR ns : type.allNamespaces.clone.reverse»
			«IF ns.owner != null»
				}; // of module «ns.name»
			«ENDIF»
		«ENDFOR»
	'''


	public static def dirName(NamedElement type) '''
		«FOR ns : type.allNamespaces SEPARATOR('/')»
			«ns.name»
		«ENDFOR»
	'''
}
