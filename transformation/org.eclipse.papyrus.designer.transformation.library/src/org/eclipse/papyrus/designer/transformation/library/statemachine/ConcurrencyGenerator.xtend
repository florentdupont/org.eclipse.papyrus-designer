/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Van Cam Pham        <VanCam.PHAM@cea.fr>
 *
 *****************************************************************************/
 
 package org.eclipse.papyrus.designer.transformation.library.statemachine

import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Typedef
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Region
import org.eclipse.uml2.uml.Transition
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.util.UMLUtil

import static org.eclipse.papyrus.designer.transformation.library.statemachine.SMCodeGeneratorConstants.*
import static extension org.eclipse.papyrus.designer.transformation.library.statemachine.TransformationUtil.*

class ConcurrencyGenerator {
	protected extension CDefinitions cdefs;
	private SM2ClassesTransformationCore core
	PThreadTypes ptTypes
	public Type threadStructType
	Class superContext
	Package targetPack
	
	new (SM2ClassesTransformationCore core) {
		this.core = core
		this.superContext = core.superContext
		this.targetPack = core.targetPacket
		this.ptTypes = core.ptTypes
		this.cdefs = core.cdefs
	}
	def createThreadBasedParallelism() {
		//we should create wrapper functions (static) to create pthreads
		//We should have: 1 wrapper for doActivity: DO_ACTIVITY_FOR_STATE
		//1 wrapper for regions: WRAPPER_REGION: similar to DO_ACTIVITY_FOR_STATE
		//1 wrapper for time event detection: WRAPPER_TIME_EVENT => run and wait as doActivity similar to DO_ACTIVITY_FOR_STATE
		//1 wrapper for change event detection: WRAPPER_TIME_EVENT => run, check value difference, write event similar to DO_ACTIVITY_FOR_STATE
		
		core.appendIncludeHeader("#include \"time.h\"")
		core.appendIncludeHeader("#include \"pthread.h\"")
		
		var threads = superContext.createOwnedAttribute(THREADS, ptTypes.pthread)
		StereotypeUtil.apply(threads, Array)
		UMLUtil.getStereotypeApplication(threads, Array).definition = '''[«STATE_MAX»]'''
		
		var flags = superContext.createOwnedAttribute(FLAGS_ACTIVITY, core.boolType)
		StereotypeUtil.apply(flags, Array)
		UMLUtil.getStereotypeApplication(flags, Array).definition = '''[«STATE_MAX»]'''
		
		var conds = superContext.createOwnedAttribute(CONDITIONS, ptTypes.pthreadCond)
		StereotypeUtil.apply(conds, Array)
		UMLUtil.getStereotypeApplication(conds, Array).definition = '''[«STATE_MAX»]'''
		
		var mutexes = superContext.createOwnedAttribute(MUTEXES, ptTypes.pthreadMutex)
		StereotypeUtil.apply(mutexes, Array)
		UMLUtil.getStereotypeApplication(mutexes, Array).definition = '''[«STATE_MAX»]'''
		
		threadStructType = core.threadStructType
		//threadStructType = superContext.createNestedClassifier(STRUCT_FOR_THREAD, UMLPackage.Literals.PRIMITIVE_TYPE)
//		StereotypeUtil.apply(threadStructType, Typedef)
//		UMLUtil.getStereotypeApplication(threadStructType, Typedef).definition = '''
//		struct «STRUCT_FOR_THREAD» {
//			void* ptr;
//			int id; //id or index used to specify the corresponding function in a table
//			char enter_mode; //used for specifying how a region is entered
//			char func_type; // doActivity/enter region/exit region/time event/change event/ transition
//			int duration; //in millisecond which is used for time events
//			«STRUCT_FOR_THREAD»(«superContext.name»* ptr, int id, char enter_mode, char func_type, int duration): ptr(ptr), id(id), enter_mode(enter_mode), func_type(func_type), duration(duration){}
//			«STRUCT_FOR_THREAD»(){}
//		} '''
		
		var threadStructs = superContext.createOwnedAttribute(THREAD_STRUCTS, threadStructType)
		StereotypeUtil.apply(threadStructs, Array)
		UMLUtil.getStereotypeApplication(threadStructs, Array).definition = '''[«STATE_MAX»]''' 
		
		var threadFuncWrapper = superContext.createOwnedOperation(THREAD_FUNC_WRAPPER, null, null)
		threadFuncWrapper.isStatic = true
		var inParam = threadFuncWrapper.createOwnedParameter("data", core.voidType)
		StereotypeUtil.apply(inParam, Ptr)
		var outParam = threadFuncWrapper.createOwnedParameter("ret", core.voidType)
		StereotypeUtil.apply(outParam, Ptr)
		outParam.direction = ParameterDirectionKind.RETURN_LITERAL
		core.createOpaqueBehavior(superContext, threadFuncWrapper, '''
		«STRUCT_FOR_THREAD»* cptr = («STRUCT_FOR_THREAD»*)data;
		«superContext.name»* ptr = («superContext.name»*) cptr->ptr;
		switch(cptr->func_type) {
			«IF core.doActivityList.size > 0»
				case «THREAD_FUNC_DOACTIVITY_TYPE»:
					ptr->«DO_CALL_ACTIVITY»(cptr->id);
					break;	
			«ENDIF»
			«IF core.timeEvents.size > 0»
				case «THREAD_FUNC_TIMEEVENT_TYPE»:
					ptr->«TIME_EVENT_LISTEN_FUNCTION»(«TE_INDEX»(cptr->id), cptr->duration);
					break;
				«ENDIF»
			«IF core.orthogonalRegions.size > 0»
				case «THREAD_FUNC_ENTER_REGION_TYPE»:
					ptr->regionCall(cptr->id, cptr->enter_mode);
					break;
				case «THREAD_FUNC_EXIT_REGION_TYPE»:
					ptr->exitRegionCall(cptr->id);
					break;
			«ENDIF»
			«IF core.parallelTransitions.size > 0»
				case «THREAD_FUNC_TRANSITION_TYPE»:
					ptr->transitionCall(cptr->id);
					break;
			«ENDIF»
			«IF core.changeEvents.size > 0»
				case «THREAD_FUNC_CHANGEEVENT_TYPE»:
					ptr->«CHANGE_EVENT_LISTEN_FUNCTION»(cptr->id);
					break;
			«ENDIF»
				case «THREAD_FUNC_STATE_MACHINE_TYPE»:
					ptr->«EVENT_DISPATCH»();
					break;
		}
		return NULL;''')
		
		//function pointer
		core.fptr = superContext.createNestedClassifier(FPT_POINTER_FOR_TABLE, UMLPackage.Literals.PRIMITIVE_TYPE)
		StereotypeUtil.apply(core.fptr, Typedef)
		UMLUtil.getStereotypeApplication(core.fptr, Typedef).definition = '''
		void («superContext.name»::*typeName)()'''
		
		var doActivityTable = superContext.createOwnedAttribute(DO_ACTIVITY_TABLE, core.fptr)
		StereotypeUtil.apply(doActivityTable, Array)
		UMLUtil.getStereotypeApplication(doActivityTable, Array).definition = '''[«STATE_MAX»]'''
		
		var doCallActivity = superContext.createOwnedOperation(DO_CALL_ACTIVITY, null, null)
		doCallActivity.createOwnedParameter("id", core.intType)
		core.createOpaqueBehavior(superContext, doCallActivity, '''
		«FLAGS_ACTIVITY»[id] = false;
		while(true) {
			pthread_mutex_lock(&«MUTEXES»[id]);
			while(!flags[id]) {
				pthread_cond_wait(&«CONDITIONS»[id], &«MUTEXES»[id]);
			}
			(this->*«DO_ACTIVITY_TABLE»[id])();
			bool commitEvent = false;
			if (flags[id]) {
				commitEvent = true;
				flags[id] = false;
			}
			pthread_cond_signal(&«CONDITIONS»[id]);
			pthread_mutex_unlock(&«MUTEXES»[id]);
			«IF core.states.filter[!it.composite && it.hasTriggerlessTransition].size > 0»
			if (commitEvent) {
				if(«FOR s:core.states.filter[!it.composite && it.hasTriggerlessTransition] SEPARATOR ' || '»id == «s.name.toUpperCase»_ID«ENDFOR») {
					//processCompletionEvent();
					«EVENT_QUEUE».push(statemachine::PRIORITY_1, NULL, COMPLETIONEVENT_ID, statemachine::COMPLETION_EVENT, id);
				}
			}
			«ENDIF»
		}''')
		
		var setFlag = superContext.createOwnedOperation(SET_FLAG, null, null)
		setFlag.createOwnedParameter("id", core.intType)
		setFlag.createOwnedParameter("func_type", core.charType)
		setFlag.createOwnedParameter("value", core.boolType)
		core.createOpaqueBehavior(superContext, setFlag, '''
		//value = true => start activity
		//value = false => stop activity
		«IF core.timeEvents.size > 0»
		if (func_type == «THREAD_FUNC_TIMEEVENT_TYPE») {
			pthread_mutex_lock(&«MUTEXES_TIME_EVENT»[«TE_INDEX»(id)]);
			«FLAGS_TIME_EVENT»[«TE_INDEX»(id)] = value;
			pthread_cond_signal(&«CONDITIONS_TIME_EVENT»[«TE_INDEX»(id)]);
			pthread_mutex_unlock(&«MUTEXES_TIME_EVENT»[«TE_INDEX»(id)]);
			return;
		}
		«ENDIF»
		if (func_type == «THREAD_FUNC_DOACTIVITY_TYPE») {
			«IF core.doActivityList.size > 0»
				if («STATE_ARRAY_ATTRIBUTE»[id].doActivity != &«superContext.name»::doActivity_dft) {
					//state has doActivity => if value = true => should
					pthread_mutex_lock(&«MUTEXES»[id]);
					if (value) {
						«FLAGS_ACTIVITY»[id] = true;
					} else {
						//wait until doActivity exit
						while(«FLAGS_ACTIVITY»[id]) {
							pthread_cond_wait(&«CONDITIONS»[id], &«MUTEXES»[id]);
						}
					}
					pthread_cond_signal(&«CONDITIONS»[id]);
					pthread_mutex_unlock(&«MUTEXES»[id]);
				} else {
					«IF core.states.filter[!it.composite && it.hasTriggerlessTransition].size > 0»
					//push completion event
					if (value) {
						if(«FOR s:core.states.filter[!it.composite && it.hasTriggerlessTransition] SEPARATOR ' || '»id == «s.name.toUpperCase»_ID«ENDFOR») {
							«EVENT_QUEUE».push(statemachine::PRIORITY_1, NULL, COMPLETIONEVENT_ID, statemachine::COMPLETION_EVENT, id);
						}
					}
					«ENDIF»
				}
				return;
			«ELSE»
				«IF core.states.filter[!it.composite && it.hasTriggerlessTransition].size > 0»
				//push completion event
					if (value) {
						if(«FOR s:core.states.filter[!it.composite && it.hasTriggerlessTransition] SEPARATOR ' || '»id == «s.name.toUpperCase»_ID«ENDFOR») {
							«EVENT_QUEUE».push(statemachine::PRIORITY_1, NULL, COMPLETIONEVENT_ID, statemachine::COMPLETION_EVENT, id);
						}
						return;
					} 
				«ENDIF» 
			«ENDIF»
		}
		''')
		
		
		//create utility fork and join for regions
		//fork has two parameters: threadId and data (thread struct)
//		var forkOp = superContext.createOwnedOperation(FORK_NAME, null, null)
//		var threadIdParam = forkOp.createOwnedParameter("tId", pThreadType)
//		StereotypeUtil.apply(threadIdParam, Ptr)
//		var data = forkOp.createOwnedParameter("data", core.voidType)
//		StereotypeUtil.apply(data, Ptr)
//		core.createOpaqueBehavior(superContext, forkOp, '''
//		pthread_create(tId, NULL, &«superContext.name»::«THREAD_FUNC_WRAPPER», data);''')
//		
//		var joinOp = superContext.createOwnedOperation(JOIN_NAME, null, null)
//		joinOp.createOwnedParameter("tId", pThreadType)
//		core.createOpaqueBehavior(superContext, joinOp, '''
//		pthread_join(tId, NULL);''')
		new TimeEventTransformation(core).createTimeEvents
		createRegionParallel
	}
	
	def createRegionParallel() {
		if (core.orthogonalRegions.empty)
			return
		var regionFptr = superContext.createNestedClassifier(REGION_FUNCTION_PTR, UMLPackage.Literals.PRIMITIVE_TYPE)
		StereotypeUtil.apply(regionFptr, Typedef)
		UMLUtil.getStereotypeApplication(regionFptr, Typedef).definition = '''
		void («superContext.name»::*typeName)(char enter_mode)'''
		
		var regionTable = superContext.createOwnedAttribute(REGION_TABLE, regionFptr)
		StereotypeUtil.apply(regionTable, Array)
		UMLUtil.getStereotypeApplication(regionTable, Array).definition = '''[«core.orthogonalRegions.size»]'''
		var regionCallOp = superContext.createOwnedOperation("regionCall", null, null)
		regionCallOp.createOwnedParameter("id", core.intType)
		regionCallOp.createOwnedParameter("enter_mode", core.charType)
		core.createOpaqueBehavior(superContext, regionCallOp, '''
		(this->*«REGION_TABLE»[id])(enter_mode);''')
		
		var regionExitTable = superContext.createOwnedAttribute(REGION_TABLE_EXIT, core.fptr)
		StereotypeUtil.apply(regionExitTable, Array)
		UMLUtil.getStereotypeApplication(regionExitTable, Array).definition = '''[«core.orthogonalRegions.size»]'''
		var regionCallExitOp = superContext.createOwnedOperation("exitRegionCall", null, null)
		regionCallExitOp.createOwnedParameter("id", core.charType)
		core.createOpaqueBehavior(superContext, regionCallExitOp, '''
		(this->*«REGION_TABLE_EXIT»[id])();''')
	}
	
	def createConcurrencyForTransitions() {
		if (core.parallelTransitions.empty) {
			return
		}
		for(var i = 0; i < core.parallelTransitions.size; i++) {
			core.appendIncludeHeader('''
			#define «core.parallelTransitions.get(i).parallelTransitionId» («i»)''')
			var op = superContext.createOwnedOperation(core.parallelTransitions.get(i).parallelTransitionMethodName, null, null)
			core.createOpaqueBehavior(superContext, op, '''
			«TransformationUtil.getTransitionEffect(core.parallelTransitions.get(i))»''')
		}
		
		var transitionTable = superContext.createOwnedAttribute(PARALLEL_TRANSITION_TABLE, core.fptr)
		StereotypeUtil.apply(transitionTable, Array)
		UMLUtil.getStereotypeApplication(transitionTable, Array).definition = '''[«core.parallelTransitions.size»]'''
		var transitionCallOp = superContext.createOwnedOperation("transitionCall", null, null)
		transitionCallOp.createOwnedParameter("id", core.intType)
		core.createOpaqueBehavior(superContext, transitionCallOp, '''
		(this->*«PARALLEL_TRANSITION_TABLE»[id])();''')		
	}
	
	def parallelTransitionMethodName(Transition t) {
		return '''paralleTransition«core.parallelTransitions.indexOf(t)»'''
	}
	
	def parallelTransitionId(Transition t) {
		return '''PARALLEL_TRANSITION_ID_«core.parallelTransitions.indexOf(t)»'''
	}
	
	def generateForkCall(Region r, boolean isEnter, String enteringMode) {
		var paramThreadName = '''«r.state.name»_«r.name»_enter_thread'''
		var threadStructParam = '''«r.state.name»_«r.name»_enter_thread_struct'''
		if (!isEnter) {
			paramThreadName = '''«r.state.name»_«r.name»_exit_thread'''
			threadStructParam = '''«r.state.name»_«r.name»_exit_thread_struct'''
		}
//		«superContext.name»* ptr;
//			int id; //id or index used to specify the corresponding function in a table
//			char enter_mode; //used for specifying how a region is entered
//			char func_type; // doActivity/enter region/exit region/time event/change event/ transition
//			int duration; //in millisecond which is used for time events
		var ret = '''
			pthread_t «paramThreadName»;
			«STRUCT_FOR_THREAD» «threadStructParam»(this, «core.getRegionMacroId(r)», «enteringMode», «IF isEnter»«THREAD_FUNC_ENTER_REGION_TYPE»«ELSE»«THREAD_FUNC_EXIT_REGION_TYPE»«ENDIF», 0);
			«superContext.name»_THREAD_CREATE(«paramThreadName», «threadStructParam»)'''
		return ret
	}
	
	def generateJoinCall(Region r, boolean isEnter) {
		var paramThreadName = '''«r.state.name»_«r.name»_enter_thread'''
		if (!isEnter) {
			paramThreadName = '''«r.state.name»_«r.name»_exit_thread'''
		}
		return '''«JOIN_NAME»(«paramThreadName», NULL);'''
	}
	
}
