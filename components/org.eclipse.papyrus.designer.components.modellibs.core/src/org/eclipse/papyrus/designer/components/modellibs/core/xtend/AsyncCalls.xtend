package org.eclipse.papyrus.designer.components.modellibs.core.xtend

import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Class
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.CppUtils.*
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.Marshalling.*

class AsyncCalls {
	def asyncCall(Operation operation) '''
		// TODO: need suitable constant dimensioning
		pBuffer = &buffer[500];  // grows backwards
		«operation.marshall»
		int operationID = ID_[operation.name/];
		BEncAsnContent (&amp;pBuffer, &amp;operationID);

		pthread_t pt;
		pthread_create (&amp;pt, NULL, staticDispatch, (void *) this);
		// TODO: add semaphore which assures that subsequent calls to «operation.name» are not executed before dispatch
		// has removed the parameters from the pBuffer stack (pBuffer can be corrupted).
	'''

	def dispatch_(Class clazz) '''
		int operationID;
		BDecAsnContent (&pBuffer, operationID);
		switch (operationID) {
			«FOR operation : clazz.ownedOperations»
			case ID_«operation.name»
			{
				
				// delegate call to executor
				rconn->«operation.cppCall»;
				break;
			}
			«ENDFOR»
		}
 	'''

	def dispatchWithThreadPool(Class clazz) '''
		int operationID;
		BDecAsnContent (&pBuffer, operationID);
		switch (operationID) {
			«FOR operation : clazz.ownedOperations»
			case ID_«operation.name»
			{
				«operation.unmarshall»
				// delegate call to executor
				rconn->«operation.cppCall»;
				«IF operation.type != null»«operation.type.cppType» ret = «ENDIF»rconn->«operation.cppCall»;
				«operation.marshallOutInout»
				resultsReady = 1;
				break;
			}
			«ENDFOR»
		}
	}
	'''
}