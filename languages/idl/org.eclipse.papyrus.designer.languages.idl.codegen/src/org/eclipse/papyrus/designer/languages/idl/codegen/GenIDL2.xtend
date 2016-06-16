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

import static extension org.eclipse.papyrus.uml.tools.utils.StereotypeUtil.*;

import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.papyrus.designer.languages.idl.codegen.preferences.IDLCodeGenUtils
import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import static extension org.eclipse.papyrus.designer.languages.idl.codegen.Utils.*

class GenIDL2 {
	public static def genIDL2 (Classifier cl) '''
		«IDLCodeGenUtils.getCommentHeader»
		«IF (cl instanceof Interface)»
			«(cl as Interface).genIDL2intf» 
		«ELSEIF (cl instanceof DataType)»
			«(cl as DataType).genIDL2dataType»
		«ENDIF»
	'''

	public static def getIncludes(Classifier cl) '''
		«FOR relShip : cl.getSourceDirectedRelationships»
			«IF relShip.targets.get(0) instanceof Classifier»
				#include "«(relShip.targets.get(0) as NamedElement).includeFilename»"
			«ENDIF»
		«ENDFOR»
	'''

	public static def includeFilename(NamedElement ne) {
		GenUtils.getFullPath(ne.nearestPackage) + '/' + ne.name + '.' + IDLCodeGenUtils.IDLSuffix
	}
		
	public static def genIDL2intf(Interface intf) '''
		// need to include referenced types (assuming a naming convention) 
		#ifndef D_«intf.name»
		#define D_«intf.name»
		
		«intf.includes»

		«intf.openNamespaceIDL»
		
		«FOR general : intf.generals SEPARATOR(', ')»
			«general.qualifiedName»
		«ENDFOR»
		{
			«FOR operation : intf.ownedOperations»
				«FOR raisedException : operation.raisedExceptions SEPARATOR (', ')»
					«raisedException.qualifiedName»
				«ENDFOR»
			«ENDFOR»
			«FOR attribute : intf.attributes»
				attribute «attribute.type.qualifiedName» «attribute.name»;
			«ENDFOR»
		};
		
		«intf.closeNamespaceIDL()»
		
		#endif
	'''

	public static def genIDL2dataType (DataType dataType) '''
		«dataType.openNamespaceIDL»
		
		struct «dataType.name» {
			«FOR attribute : dataType.attributes»
				«attribute.type.name» «attribute.name»;
			«ENDFOR»
		};
		«IF dataType.attributes.filter[it.isApplied('Key')].size() > 0»
			#pragma keylist «dataType.name» {
				«FOR attribute : dataType.attributes.filter[it.isApplied('Key')]»
					«attribute.name» 
				«ENDFOR»
			}
		«ENDIF»
		
		«dataType.closeNamespaceIDL»
	'''


	public static def IDLparameters(Operation operation) '''
		«FOR parameter : operation.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL] SEPARATOR(', ')»
			«parameter.direction» «parameter.type.name» «parameter.name»
		«ENDFOR»
	'''

	public static def IDLretType(Operation operation) '''
		«IF (operation.type == null)»
			void
		«ELSE»
			«operation.type.name»
		«ENDIF»
	'''
}
