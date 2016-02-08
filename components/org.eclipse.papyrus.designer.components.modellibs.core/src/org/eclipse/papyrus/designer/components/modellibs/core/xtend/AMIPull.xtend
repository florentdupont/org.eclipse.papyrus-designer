package org.eclipse.papyrus.designer.components.modellibs.core.xtend

import org.eclipse.uml2.uml.Operation
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.Marshalling.*

class AMIPull {
	def operation_(Operation operation) '''
		«IF operation.name.endsWith("Poll")»
			// comment polling function
			if (!resultArrived) {
				// wait for result, condition will be fired upon arrival
				TimeVal timeVal = TimeVal::current () + timeout;
				m_cond.waitUpto (timeout);
			}
			«operation.unmarshall»
			}
			«IF operation.type != null»return retValue;«ENDIF»
		«ELSE»
			// TODO: need suitable constant dimensioning
			pBuffer = &buffer[500];  // grows backwards
			int operationID = ID_[operation.name/];

			// now marshall in and inout parameters via ASN.1
			«operation.marshall»
			BEncAsnContent (&amp;pBuffer, &amp;operationID);

			pthread_t pt;
			pthread_create (&amp;pt, NULL, staticDispatch, (void *) this);
			// TODO: add semaphore which assures that subsequent calls to [operation.name/] are not executed before dispatch
			// has removed the parameters from the pBuffer stack (pBuffer can be corrupted).
			// even worse: buffer will be deallocated even without a 2nd call!
		«ENDIF»
	'''
}