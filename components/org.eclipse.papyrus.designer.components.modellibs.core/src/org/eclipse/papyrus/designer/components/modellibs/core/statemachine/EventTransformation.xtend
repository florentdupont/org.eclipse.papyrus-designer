package org.eclipse.papyrus.designer.components.modellibs.core.statemachine

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.uml2.uml.CallEvent
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Event
import org.eclipse.uml2.uml.FinalState
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.PseudostateKind
import org.eclipse.uml2.uml.State
import org.eclipse.uml2.uml.Transition
import org.eclipse.uml2.uml.TransitionKind
import org.eclipse.uml2.uml.Vertex
import static extension org.eclipse.papyrus.designer.components.modellibs.core.statemachine.TransformationUtil.eventName
import org.eclipse.papyrus.designer.components.modellibs.core.xtend.BehaviorUtil
import org.eclipse.uml2.uml.Behavior
import static extension org.eclipse.papyrus.designer.components.modellibs.core.statemachine.SMCodeGeneratorConstants.*

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
	def createEventMethod(String eventName, List<Transition> transitions) {
		var method = superContext.createOwnedOperation("process" + eventName, null, null)
		var sources = transitions.map[it.source].filter(State).toList
		
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
		var body = '''
		«SYSTEM_STATE_ATTR» = statemachine::EVENT_PROCESSING;
		«FOR s:arraySet»
			«IF s.orthogonal»
				«FOR sub:map.get(s).getActualStateList(transitions)»
					«IF transitions.filter[it.source == sub].filter[!passeds.contains(it)].size > 0»
					if («core.hasSubstatesAcceptingEvent(sub, sources)»«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«core.getRegionNumber(sub)»] == «sub.name.toUpperCase»_ID) {
						«FOR t:transitions.filter[it.source == sub].filter[!passeds.contains(it)]»
							«generateTransitionCode(sub, t)»
						«ENDFOR»
					}
					«ENDIF»
				«ENDFOR»
			«ELSE»
				«FOR sub:map.get(s) SEPARATOR ' else '»
					if («core.hasSubstatesAcceptingEvent(sub, sources)»«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] == «sub.name.toUpperCase»_ID) {
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
					}
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
		«FOR root:rootSourceStates SEPARATOR ' else '»
			if («core.hasSubstatesAcceptingEvent(root, sources)»«ACTIVE_ROOT_STATE_ID» == «root.name.toUpperCase»_ID) {
				«FOR t:transitions.filter[it.source == root] SEPARATOR ' else '»
					«generateTransitionCode(root, t)»
				«ENDFOR»
			}
		«ENDFOR»'''
		core.createOpaqueBehavior(superContext, method, body)
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
						process«COMPLETION_EVENT»();
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
				«IF hasGuard» if («core.getGuard(t)») {
				«ENDIF»
				«ret»
				«SYSTEM_STATE_ATTR» = statemachine::EVENT_CONSUMED;
				«IF hasGuard»}
				«ENDIF»'''
		} else {
			ret = '''
				//from «t.source.name» to «t.target.name»
				«IF hasGuard» if («core.getGuard(t)») {
				«ENDIF»
				«generateTransitionGraphCode(s, t)»
				«SYSTEM_STATE_ATTR» = statemachine::EVENT_CONSUMED;
				«IF hasGuard»}
				«ENDIF»'''
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
		createEventMethod(e.eventName, transitions)
		if (e instanceof CallEvent) {
			var op = core.copier.getCopy((e as CallEvent).operation)
			var body = '''
					this->process«e.eventName»();'''
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
			«FOR out:join.outgoings»
				«TransformationUtil.getTransitionEffect(out)»
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