package org.eclipse.papyrus.designer.components.modellibs.core.statemachine

import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array
import static extension org.eclipse.papyrus.designer.components.modellibs.core.statemachine.SMCodeGeneratorConstants.*

class TimeEventTransformation {
	protected extension CDefinitions cdefs;
	private SM2ClassesTransformationCore core
	PThreadTypes ptTypes
	org.eclipse.uml2.uml.Class superContext
	org.eclipse.uml2.uml.Package targetPack
	new (SM2ClassesTransformationCore core) {
		this.core = core
		this.superContext = core.superContext
		this.targetPack = core.targetPacket
		this.ptTypes = core.ptTypes
		this.cdefs = core.cdefs
	}
	
	public def createTimeEvents() {
		if (core.timeEvents.empty) {
			return
		}
		core.appendIncludeHeader('''#include "time.h"
		#include "sys/time.h"''')
		var timeEventTable = superContext.createOwnedAttribute(TIME_EVENT_TABLE, core.fptr)
		StereotypeUtil.apply(timeEventTable, Array)
		UMLUtil.getStereotypeApplication(timeEventTable, Array).definition = '''[«core.timeEvents.size»]'''
		
		var threads = superContext.createOwnedAttribute(THREADS_TIME_EVENT, ptTypes.pthread)
		StereotypeUtil.apply(threads, Array)
		UMLUtil.getStereotypeApplication(threads, Array).definition = '''[«core.timeEvents.size»]'''
		
		var timeEventFlags = superContext.createOwnedAttribute(FLAGS_TIME_EVENT, core.boolType)
		StereotypeUtil.apply(timeEventFlags, Array)
		UMLUtil.getStereotypeApplication(timeEventFlags, Array).definition = '''[«core.timeEvents.size»]'''
		
		var timeEvnetConds = superContext.createOwnedAttribute(CONDITIONS_TIME_EVENT, ptTypes.pthreadCond)
		StereotypeUtil.apply(timeEvnetConds, Array)
		UMLUtil.getStereotypeApplication(timeEvnetConds, Array).definition = '''[«core.timeEvents.size»]'''
		
		var timeEventMutexes = superContext.createOwnedAttribute(MUTEXES_TIME_EVENT, ptTypes.pthreadMutex)
		StereotypeUtil.apply(timeEventMutexes, Array)
		UMLUtil.getStereotypeApplication(timeEventMutexes, Array).definition = '''[«core.timeEvents.size»]'''
		
		var threadStructs = superContext.createOwnedAttribute(THREAD_STRUCTS_FOR_TIMEEVENT, core.concurrency.threadStructType)
		StereotypeUtil.apply(threadStructs, Array)
		UMLUtil.getStereotypeApplication(threadStructs, Array).definition = '''[«core.timeEvents.size»]''' 
		
		//create timeEvent function sleep during an amount of time
		var timeEventOp = superContext.createOwnedOperation(TIME_EVENT_LISTEN_FUNCTION, null, null)
		timeEventOp.createOwnedParameter("id", core.intType)
		timeEventOp.createOwnedParameter("duration", core.intType)
		core.createOpaqueBehavior(superContext, timeEventOp, '''
		struct timeval tv;
		struct timespec ts;
		int timedWaitResult;
		while(true) {
			pthread_mutex_lock(&«MUTEXES_TIME_EVENT»[id]);
			while(!«FLAGS_TIME_EVENT»[id]) {
				pthread_cond_wait(&«CONDITIONS_TIME_EVENT»[id], &«MUTEXES_TIME_EVENT»[id]);
			}
			
			gettimeofday(&tv, NULL);
			ts.tv_sec = time(NULL) + duration / 1000;
			ts.tv_nsec = tv.tv_usec * 1000 + 1000 * 1000 * (duration % 1000);
			ts.tv_sec += ts.tv_nsec / (1000 * 1000 * 1000);
			ts.tv_nsec %= (1000 * 1000 * 1000);
			
			timedWaitResult = pthread_cond_timedwait(&«CONDITIONS_TIME_EVENT»[id], &«MUTEXES_TIME_EVENT»[id], &ts);
			
		  	bool commitEvent = false;
		  	if (timedWaitResult != 0) {
		  		//timeout
		  		commitEvent = true;
		  	}
			«FLAGS_TIME_EVENT»[id] = false;
			pthread_cond_signal(&«CONDITIONS_TIME_EVENT»[id]);
			pthread_mutex_unlock(&«MUTEXES_TIME_EVENT»[id]);
			if (commitEvent) {
				//the state does not change, push time event to the queue
				«EVENT_QUEUE».push(statemachine::PRIORITY_2, NULL, id, statemachine::TIME_EVENT, id);
			}
		}''')
	}
}