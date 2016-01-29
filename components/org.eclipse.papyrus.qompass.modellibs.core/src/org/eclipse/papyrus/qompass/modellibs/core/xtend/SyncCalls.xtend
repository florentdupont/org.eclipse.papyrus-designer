package org.eclipse.papyrus.qompass.modellibs.core.xtend

import org.eclipse.uml2.uml.Operation
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.CppUtils.*

class SyncCalls {
	def syncCall(Operation operation) '''
		// put pre-interceptors here
		[comment type is a derived property containing the operations return type/]
		«IF operation.type != null»return «ENDIF»rconn->«operation.cppCall»;
		// put post-interceptors here
	'''
}