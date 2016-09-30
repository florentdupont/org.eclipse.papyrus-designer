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
 
package org.eclipse.papyrus.designer.transformation.languages.cpp.library.statemachine

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.papyrus.designer.transformation.library.xtend.BehaviorUtil
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ref
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.CallEvent
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Event
import org.eclipse.uml2.uml.FinalState
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.PseudostateKind
import org.eclipse.uml2.uml.SignalEvent
import org.eclipse.uml2.uml.State
import org.eclipse.uml2.uml.Transition
import org.eclipse.uml2.uml.TransitionKind
import org.eclipse.uml2.uml.Vertex
import static extension org.eclipse.papyrus.designer.transformation.languages.cpp.library.statemachine.TransformationUtil.eventName
import static extension org.eclipse.papyrus.designer.transformation.languages.cpp.library.statemachine.SMCodeGeneratorConstants.*
import org.eclipse.papyrus.designer.transformation.languages.cpp.library.statemachine.SM2ClassesTransformationCore

class EventTransformation {
	protected extension CDefinitions cdefs;
	SM2ClassesTransformationCore core
	Class superContext
	Package targetPack
	new (SM2ClassesTransformationCore core) {
		this.core = core
		this.superContext = core.superContext
		this.targetPack = core.targetPacket
	}
	
	def List<Event> getDeferredEvents(State s) {
		s.deferrableTriggers.map[it.event].toList
	}
	
	def completionEventCheck(String eventName, String stateId) {
		if (eventName == "CompletionEvent") {
			return ''' && (currentEvent->associatedState == «stateId»)'''
		}
		return ''''''
	}
	
	def createEventMethod(String eventName, List<Transition> transitions) {
		var method = superContext.createOwnedOperation("process" + eventName, null, null)
		val tempSources = transitions.map[it.source].filter(State).toList;
		val sources = new ArrayList<State>
		tempSources.forEach[
			if (!sources.contains(it)) {
				sources.add(it)
			}
		]
		
		val Map<State, List<State>> map = new HashMap
		for(source:sources) {
			if (source.container.state != null) {
				if (!map.containsKey(source.container.state)) {
					map.put(source.container.state, new ArrayList) 
				}
				//if (!map.get(source.container.state).contains(source)) {
					map.get(source.container.state).add(source)
				//}
				
			}
		}
		var arraySet = map.keySet.toList	
		var size = arraySet.size
		for(var i = 0; i < size; i++) {
			for(var j = i + 1; j < size; j++) {
				if (core.calculateDepth(arraySet.get(i)) <= core.calculateDepth(arraySet.get(j))) {
					var tmp = arraySet.remove(j)
					arraySet.add(i, tmp)
				}
			}
		}
		
		var rootSourceStates = core.getRootStates(sources)
		val passeds = new ArrayList<Transition>
		var statesDeferredEvent = core.states.filter[it.deferredEvents.filter[it.name == eventName].size > 0]
		var notInSources = statesDeferredEvent.filter[!sources.contains(it)]
		val notInConcurrentState = notInSources.filter[it.container.state == null || it.container.state.orthogonal].toList
		var inConcurrentState = notInSources.filter[!notInConcurrentState.contains(it)]
		var isCallEvent = core.callEvents.filter[it.eventName == eventName].size > 0
		var body = '''
		«IF isCallEvent»
			«superContext.name.toUpperCase»_GET_CONTROL
		«ENDIF»
		«SYSTEM_STATE_ATTR» = statemachine::EVENT_PROCESSING;
		«FOR s:arraySet»
			«IF s.orthogonal»
				«FOR sub:map.get(s).getActualStateList(transitions)»
					«IF transitions.filter[it.source == sub].filter[!passeds.contains(it)].size > 0»
					«IF eventName == "CompletionEvent" && transitions.filter[it.source == sub && (it.target instanceof Pseudostate) && (it.target as Pseudostate).kind == PseudostateKind.JOIN_LITERAL].size > 0»
						«var trans = transitions.filter[it.source == sub && (it.target instanceof Pseudostate) && (it.target as Pseudostate).kind == PseudostateKind.JOIN_LITERAL]»
						«var join = trans.filter[!joins.contains(it.target)].map[it.target].head»
						«var sourcesOfJoin = join.incomings.map[it.source]»
						if («FOR src:sourcesOfJoin SEPARATOR ' || '»(currentEvent->associatedState == «src.name.toUpperCase»_ID)«ENDFOR») {
							«FOR t:transitions.filter[it.source == sub].filter[!passeds.contains(it)]»
								«var b = passeds.add(t)»
								«generateTransitionCode(sub, t)»
							«ENDFOR»
						}
					«ELSE»					
						if («core.hasSubstatesAcceptingEvent(sub, sources)»«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«core.getRegionNumber(sub)»] == «sub.name.toUpperCase»_ID«completionEventCheck(eventName, sub.name.toUpperCase + "_ID")») {
							«IF sub.deferredEvents.filter[it.name == eventName].size > 0»
								«SYSTEM_STATE_ATTR» = statemachine::EVENT_DEFERRED;
							«ELSE»
							«FOR t:transitions.filter[it.source == sub].filter[!passeds.contains(it)]»
								«var b = passeds.add(t)»
								«generateTransitionCode(sub, t)»
							«ENDFOR»
							«ENDIF»
						}
					«ENDIF»
					«ENDIF»
				«ENDFOR»
			«ELSE»
				«FOR sub:map.get(s) SEPARATOR ' else '»
				if («core.hasSubstatesAcceptingEvent(sub, sources)»«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] == «sub.name.toUpperCase»_ID«completionEventCheck(eventName, sub.name.toUpperCase + "_ID")») {
					«IF sub.deferredEvents.filter[it.name == eventName].size > 0»
						«SYSTEM_STATE_ATTR» = statemachine::EVENT_DEFERRED;
					«ELSE»
						«var hasGuards = transitions.filter[it.source == sub && it.guard != null]»
						«FOR t:transitions.filter[it.source == sub && it.guard != null] SEPARATOR ' else '»
						«generateTransitionCode(sub, t)»
						«ENDFOR»
						«IF hasGuards.empty» 
							«generateTransitionCode(sub, transitions.filter[it.source == sub && it.guard == null].head)»
						«ELSEIF transitions.filter[it.source == sub && it.guard == null].head != null»
							else {
								«generateTransitionCode(sub, transitions.filter[it.source == sub && it.guard == null].head)»
							}
						«ENDIF»
					«ENDIF»
				}
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
		«IF rootSourceStates.size > 0»
			if («SYSTEM_STATE_ATTR» == statemachine::EVENT_PROCESSING«completionEventCheck(eventName, ACTIVE_ROOT_STATE_ID)») {
				switch(«ACTIVE_ROOT_STATE_ID») {
					«FOR root:rootSourceStates»
						case «root.name.toUpperCase»_ID: 
							«IF root.deferredEvents.filter[it.name == eventName].size > 0»
								«SYSTEM_STATE_ATTR» = statemachine::EVENT_DEFERRED;
							«ELSE»
							«FOR t:transitions.filter[it.source == root] SEPARATOR ' else '»
								«generateTransitionCode(root, t)»
							«ENDFOR»
							«ENDIF»
							break;
					«ENDFOR»
					default:
						//do nothing
						break;
				}
			}
		«ENDIF»
		«IF isCallEvent»
			«superContext.name.toUpperCase»_RELEASE_CONTROL
		«ENDIF»'''
		core.createOpaqueBehavior(superContext, method, body)
		return method
	}
	
	private def List<State> getActualStateList(List<State> l, List<Transition> trans) {
		var transComingToJoin = trans.filter[(it.target instanceof Pseudostate) && (it.target as Pseudostate).kind == PseudostateKind.JOIN_LITERAL]
		val ret = new ArrayList<State>
		ret.addAll(l)
		if (transComingToJoin.size > 0) {
			val joins = new ArrayList<Pseudostate>
			transComingToJoin.map[it.target].filter(Pseudostate).forEach[
				if (!joins.contains(it)) {
					joins.add(it)
				}
			]
			joins.forEach[
				var toRemoveds = it.incomings.map[source].filter(State).toList
				toRemoveds.remove(toRemoveds.head)
				ret.removeAll(toRemoveds)
			]
		}		
		return ret
	}
	
	def String generateTransitionCode(State s, Transition t) {
		var ret = ''''''
		var hasGuard = (t.guard != null)
		if (t.target instanceof State) {
			var callCompletionEvent = '''process«COMPLETION_EVENT»();'''
			if (t.target instanceof FinalState) {
				var composite = t.target.container.state
				if (composite == null) {
					callCompletionEvent = ''''''
				} else {
					callCompletionEvent = '''
					if («FOR r:composite.regions SEPARATOR ' && '»(«STATE_ARRAY_ATTRIBUTE»[«composite.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«composite.regions.indexOf(r)»] == «STATE_MAX»)«ENDFOR») {
						«EVENT_QUEUE».push(statemachine::PRIORITY_1, NULL, COMPLETIONEVENT_ID, statemachine::COMPLETION_EVENT, «composite.name.toUpperCase»_ID);
					}'''
				}
			}
			if (t.kind == TransitionKind.INTERNAL_LITERAL) {
				ret = '''
				«IF t.effect != null»
				«(t.effect as OpaqueBehavior).bodies.head»
				«ENDIF»'''
			} else if (t.kind == TransitionKind.LOCAL_LITERAL) {
				var State parent
				var State child
				if (TransformationUtil.transitiveSubStates(s).contains(t.target)) {
					parent = s
					child = t.target as State
					val finalChild = child
					ret = '''
					«core.generateExitingSubStates(s, false)»
					«TransformationUtil.getTransitionEffect(t)»
					«IF t.target instanceof FinalState»
						«IF t.target.container == core.topRegion»
						«ACTIVE_ROOT_STATE_ID» = «STATE_MAX»;
						«ELSE»
						«STATE_ARRAY_ATTRIBUTE»[«t.target.container.state.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«t.target.container.state.regions.indexOf(t.target.container)»] = «STATE_MAX»;
						«ENDIF»
						«callCompletionEvent»;
					«ELSE»
						«var region = parent.regions.filter[core.allSubVertexes(it).contains(finalChild)].head»
						«core.getRegionMethodName(region)»(«core.getVertexMacroName(child)»);
					«ENDIF»
					'''					
				} else if (TransformationUtil.transitiveSubStates((t.target as State)).contains(s)) {
					parent = t.target as State
					child = s
					val finalChild = child
					ret = '''
					«core.generateExitingSubStates(s, false)»
					«TransformationUtil.getTransitionEffect(t)»
					«IF t.target instanceof FinalState»
						«IF t.target.container == core.topRegion»
							«ACTIVE_ROOT_STATE_ID» = «STATE_MAX»;
						«ELSE»
							«STATE_ARRAY_ATTRIBUTE»[«t.target.container.state.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«t.target.container.state.regions.indexOf(t.target.container)»] = «STATE_MAX»;
						«ENDIF»
						«callCompletionEvent»;
					«ELSE»
						«var region = parent.regions.filter[core.allSubVertexes(it).contains(finalChild)].head»
						«core.getRegionMethodName(region)»(«core.getInitialMacroName(region)»);
					«ENDIF»
					'''				
				}				
			} else {

				ret = '''
				«core.pseudostateGenerator.generateBetweenVertex(s, t.target, t)»'''
			}
			
			ret = '''
				//from «t.source.name» to «t.target.name»
				if («IF hasGuard»«core.getGuard(t)»«ELSE»true«ENDIF») {
				«ret»
				«SYSTEM_STATE_ATTR» = statemachine::EVENT_CONSUMED;
				}'''
		} else {
			ret = '''
				//from «t.source.name» to «t.target.name»
				if («IF hasGuard»«core.getGuard(t)»«ELSE»true«ENDIF») {
				«generateTransitionGraphCode(s, t)»
				«SYSTEM_STATE_ATTR» = statemachine::EVENT_CONSUMED;
				}'''
		}
		
		return ret
	}
	
	def int getIndexOfUnguardTransition(Pseudostate junction) {
		var ungaurds = junction.outgoings.filter[it.guard == null]
		if (ungaurds.empty || ungaurds.size > 1) {
			
		}
		var unguard = ungaurds.head
		return junction.outgoings.indexOf(unguard)
	}
	
	private List<Pseudostate> joins = new ArrayList
	
	def createEventMethod(Event e, List<Transition> transitions) {
		var evMethod= createEventMethod(e.eventName, transitions)
		if (e instanceof CallEvent) {
			var op = core.copier.getCopy((e as CallEvent).operation)
			core.copyParameters(op, evMethod, false)
			var params = evMethod.ownedParameters
			var body = '''
					this->process«e.eventName»(«FOR p:params SEPARATOR ', '»«p.name»«ENDFOR»);'''
			
			var String existingBody = null
			if (op.methods.size > 0) {
				val existingMethod = op.methods.get(0)
				existingBody = BehaviorUtil.body(existingMethod, "C++")
				if (existingBody == null) {
					// try C/C++ tag
					existingBody = BehaviorUtil.body(existingMethod, "C/C++")
				}
			}
			if (existingBody != null) {
				body += "\n// original method code\n" + existingBody;
			}
			core.createOpaqueBehavior(superContext, op, body)
		} else if (e instanceof SignalEvent) {
			var send = superContext.createOwnedOperation("send" + e.name, null, null)
			if (e.signal != null) {
				StereotypeUtil.apply(send.createOwnedParameter("sig", core.copier.getCopy(e.signal)), Ref)
				StereotypeUtil.apply(evMethod.createOwnedParameter("sig", core.copier.getCopy(e.signal)), Ref)
				
			}
			core.createOpaqueBehavior(superContext, send, '''
			«EVENT_QUEUE».push(statemachine::PRIORITY_2, «IF e.signal != null»&sig«ELSE»NULL«ENDIF», «e.name.toUpperCase»_ID, statemachine::SIGNAL_EVENT, 0«IF e.signal != null», sizeof(«CppGenUtils.cppQualifiedName(core.copier.getCopy(e.signal))»)«ENDIF»);''')
		}		
	}
	
	def String generateTransitionGraphCode(State s, Transition t) {
		var graph = core.calculateTransitionGraphs(s, t)
		var target = t.target as Pseudostate
		var junctions = graph.P.filter[it.kind == PseudostateKind.JUNCTION_LITERAL].toList
		var Pseudostate join = null
		if (target.kind == PseudostateKind.JOIN_LITERAL) {
			join = target
		}
		if (join != null && joins.contains(join)) {
			return ''''''
		}
		joins.add(join)
		var ret = ''''''
		var exits = new ArrayList<Vertex>
		var enters = new ArrayList<Vertex>
		
		calculateExitsEnters(t, exits, enters)
		var cpEx = exits.last		
		var cpEn = enters.last
		var bodyInside = '''
		«FOR junction:junctions»
			«junction.name» = «getIndexOfUnguardTransition(junction)»;
			«FOR tr:junction.outgoings.filter[it.guard != null] SEPARATOR ' else '»
				if («core.getGuard(tr)») {
					«junction.name» = «junction.outgoings.indexOf(tr)»;
				}
			«ENDFOR»
		«ENDFOR»
		«ret»
		«IF cpEx instanceof State»
			«core.generateExitingSubStates(cpEx as State, true)»
		«ENDIF»
		«IF join != null»
			//TODO: concurrency
			«FOR in:join.incomings»
				«TransformationUtil.getTransitionEffect(in)»
			«ENDFOR»
		«ELSE»
			«TransformationUtil.getTransitionEffect(t)»
		«ENDIF»'''
		
		var innermost = enters.head
		if (innermost instanceof State) {
			bodyInside = '''
			«bodyInside»
			«IF innermost instanceof FinalState»
				«IF innermost.container == core.topRegion»
					«ACTIVE_ROOT_STATE_ID» = «STATE_MAX»;
					«ELSE»
					«STATE_ARRAY_ATTRIBUTE»[«ACTIVE_ROOT_STATE_ID»].«ACTIVE_SUB_STATES»[«innermost.container.state.regions.indexOf(innermost.container)»] = «STATE_MAX»;
					«core.generateCompletionCall(innermost)»
				«ENDIF»
			«ELSE»
				«core.generateEnteringOnSubVertex(cpEn as State, innermost)»
			«ENDIF»'''
		} else {
			bodyInside = '''
			«bodyInside»
			«IF cpEn instanceof State»
				«core.generateEnteringOnSubVertex(cpEn, innermost)»
			«ELSE»
				«core.pseudostateGenerator.generatePseudo(innermost as Pseudostate)»
			«ENDIF»'''
		}
		
		ret = '''
		«IF join != null»
		if («FOR src:join.incomings.map[it.source] SEPARATOR ' && '»(«STATE_ARRAY_ATTRIBUTE»[«src.container.state.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«src.container.state.regions.indexOf(src.container)»] == «src.name.toUpperCase»_ID)«ENDFOR») {
		«ENDIF»
			«bodyInside»
		«IF join != null»
		}
		«ENDIF»'''
		
		return ret
	}
	
	def calculateExitsEnters(Transition t, List<Vertex> exits, List<Vertex> enters) {
		//step 1
		var tp1 = new ArrayList<Vertex>
		tp1.addAll(core.getTransitiveParentStates(t.source))
		tp1.add(0, t.source)
		
		var tp2 = new ArrayList<Vertex>
		tp2.addAll(core.getTransitiveParentStates(t.target))
		tp2.add(0, t.target)
		
		//step2
		var Vertex bound1 = null
		var Vertex bound2 = null
		for(v:tp1) {
			var tp = tp2.filter[it.container == v.container].head
			if (bound1 == null && bound2 == null && tp != null) {
				bound1 = v
				bound2 = tp
			}
		}
		
		var index1 = tp1.indexOf(bound1)
		var index2 = tp2.indexOf(bound2)
		exits.addAll(tp1.subList(0, index1 + 1))
		enters.addAll(tp2.subList(0, index2 + 1))
	}
}
