package org.eclipse.papyrus.qompass.modellibs.core.xtend

import org.eclipse.uml2.uml.Operation
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.CppUtils.cppCall

class MultipleReceptacle {
	def operation_(Operation operation) '''
		for (int i=0; i&lt;9; i++) {
			if (rconn[i] != 0) {
				rconn[i]->«operation.cppCall»;
			}
		}
	'''
}