package org.eclipse.papyrus.designer.transformation.library.xtend

import org.eclipse.uml2.uml.Operation
import static extension org.eclipse.papyrus.designer.transformation.library.xtend.CppUtils.cppCall

class SyncCalls {
	def syncCall(Operation operation) '''
		// put pre-interceptors here
		[comment type is a derived property containing the operations return type/]
		«IF operation.type != null»return «ENDIF»rconn->«operation.cppCall»;
		// put post-interceptors here
	'''
}