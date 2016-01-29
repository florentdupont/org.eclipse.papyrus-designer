package org.eclipse.papyrus.qompass.modellibs.tracing.acceleo

import org.eclipse.uml2.uml.Operation
import static extension org.eclipse.papyrus.qompass.designer.core.UMLTool.*
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.CppUtils.*
import org.eclipse.papyrus.qompass.designer.core.EnumService
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.papyrus.qompass.designer.core.extensions.IXtend

class TraceOTF implements IXtend {
	public def traceOp(Operation operation) '''
		// <instance>_<port>_<operation>
		// easier: <component>_<port>_<operation>
		updateTimestamp ();
		if (!hasDeclaredFunctions) {
			declareFunctions();
			hasDeclaredFunctions = true;
		}
		Tracing::TraceInit::wait();
		int processID = Tracing::TraceInit::getProcessID();
		OTF_Writer * writer = Tracing::TraceInit::getWriter();
		OTF_Writer_writeEnter (writer, timestamp.ticks(), «EnumService.enumSvcPrefix»::id_«(operation.owner as NamedElement).varName»_«operation.varName», processID, 0);
		OTF_Writer_writeEventComment (writer, timestamp.ticks(), processID, portName);
		OTF_Writer_writeEventComment (writer, timestamp.ticks(), processID, instanceName);
		Tracing::TraceInit::post();
		«IF (operation.type != null)» type.cppType ret = «ENDIF»rconn->«operation.cppCall»;
		updateTimestamp ();
		Tracing::TraceInit::wait();
		OTF_Writer_writeLeave (writer, timestamp.ticks(), «EnumService.enumSvcPrefix»::id_«(operation.owner as NamedElement).varName»_«operation.varName», processID, 0);
		Tracing::TraceInit::post();
		«IF (operation.type != null)» return ret;«ENDIF»
	'''
}