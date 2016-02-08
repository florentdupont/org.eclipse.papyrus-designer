package org.eclipse.papyrus.designer.components.modellibs.tracing.xtend

import org.eclipse.uml2.uml.Operation
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.CppUtils.*
import org.eclipse.papyrus.designer.components.transformation.core.extensions.IXtend

class TraceCout implements IXtend {
	
	public def traceOp(Operation operation) '''
		// declare operation & use it directly. Problem: declaration can not be within the scope of an operation
		updateTimestamp();
		cout << "enter operation: " << «operation.name» << " at " << timestamp.ticks() << "  parameters: "
			«FOR parameter : operation.ownedParameters» << "«parameter.name» (of type «parameter.type.name») "«ENDFOR» << endl;

		«IF (operation.type != null)» type.cppType ret = «ENDIF»rconn->«operation.cppCall»;
		«IF (operation.type != null)» return ret;«ENDIF»
	'''
}