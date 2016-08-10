/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.transformation.library.statemachine

import org.eclipse.uml2.uml.ChangeEvent
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.OpaqueExpression
import org.eclipse.uml2.uml.Package

import static org.eclipse.papyrus.designer.transformation.library.statemachine.SMCodeGeneratorConstants.*

import static extension org.eclipse.papyrus.designer.transformation.library.statemachine.TransformationUtil.*
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array
import org.eclipse.uml2.uml.util.UMLUtil

class ChangeEventTransformation {
	protected extension CDefinitions cdefs;
	private SM2ClassesTransformationCore core
	PThreadTypes ptTypes
	Class superContext
	Package targetPack
	new (SM2ClassesTransformationCore core) {
		this.core = core
		this.superContext = core.superContext
		this.targetPack = core.targetPacket
		this.ptTypes = core.ptTypes
		this.cdefs = core.cdefs
	}
	
	public def createChangeEvents() {
		if (core.changeEvents.empty) {
			return
		}
		
		var threads = superContext.createOwnedAttribute(THREADS_CHANGE_EVENT, ptTypes.pthread)
		StereotypeUtil.apply(threads, Array)
		UMLUtil.getStereotypeApplication(threads, Array).definition = '''[«core.changeEvents.size»]'''
		
		var threadStructs = superContext.createOwnedAttribute(THREAD_STRUCTS_FOR_CHANGEEVENT, core.concurrency.threadStructType)
		StereotypeUtil.apply(threadStructs, Array)
		UMLUtil.getStereotypeApplication(threadStructs, Array).definition = '''[«core.changeEvents.size»]''' 
		
		var WAIT_TIME = "10" //ms
		core.appendIncludeHeader('''
		#ifdef WIN32
		#include <windows.h>
		#elif _POSIX_C_SOURCE >= 199309L
		#include <time.h>   // for nanosleep
		#else
		#include <unistd.h> // for usleep
		#endif''')
		//create changeEvent function sleep during an amount of time and check values of boolean expressions
		var changeEventOp = superContext.createOwnedOperation(CHANGE_EVENT_LISTEN_FUNCTION, null, null)
		changeEventOp.createOwnedParameter("id", core.intType)
		core.createOpaqueBehavior(superContext, changeEventOp, '''
		struct timeval tv;
		struct timespec ts;
		bool oldValue;
		bool newValue;
		bool isFirstTime = true;
		bool periodic = «WAIT_TIME»; //ms
		while(true) {
			switch(id) {
				«FOR ch:core.changeEvents»
					case «ch.eventID»:
						newValue = «ch.changeEventExpression»;
						break;
				«ENDFOR»
			}
			if (isFirstTime) {
				oldValue = newValue;
				isFirstTime = false;
			} else {
				#ifdef WIN32
					Sleep(periodic);
				#elif _POSIX_C_SOURCE >= 199309L
				    struct timespec ts;
				    ts.tv_sec = periodic / 1000;
				    ts.tv_nsec = (periodic % 1000) * 1000000;
				    nanosleep(&ts, NULL);
				#else
				    usleep(periodic * 1000);
				#endif
			}
			if (oldValue != newValue) {
				//the expression is changed, push an event to the queue
				«EVENT_QUEUE».push(statemachine::PRIORITY_2, NULL, id, statemachine::CHANGE_EVENT, 0);
			}
			oldValue = newValue;
		}''')
	}
	
	def String getChangeEventExpression(ChangeEvent e) {
		if (e.changeExpression == null || !(e.changeExpression instanceof OpaqueExpression)) {
			return "false"
		} else {
			return (e.changeExpression as OpaqueExpression).bodies.head
		}
	}
}
