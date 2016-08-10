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

import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.PseudostateKind
import org.eclipse.uml2.uml.Vertex
import org.eclipse.uml2.uml.Transition
import java.util.ArrayList
import org.eclipse.uml2.uml.State
import org.eclipse.uml2.uml.FinalState
import org.eclipse.uml2.uml.Region
import static extension org.eclipse.papyrus.designer.transformation.library.statemachine.SMCodeGeneratorConstants.*
import java.util.List

class PseudostateGenerator {
	protected extension CDefinitions cdefs;
	private SM2ClassesTransformationCore core
	org.eclipse.uml2.uml.Class superContext
	org.eclipse.uml2.uml.Package targetPack
	new (SM2ClassesTransformationCore core) {
		this.core = core
		this.superContext = core.superContext
		this.targetPack = core.targetPacket
		this.cdefs = core.cdefs
	}
	
	def String generatePseudo(Pseudostate p) {
		var ret = ''''''
		switch(p.kind) {
			case PseudostateKind.JUNCTION_LITERAL: {
				ret = p.generateJunction
			}
			
			case PseudostateKind.JOIN_LITERAL: {
				ret = p.generateJoin
			}
			case PseudostateKind.FORK_LITERAL: {
				ret = p.generateFork
			}
			
			case PseudostateKind.CHOICE_LITERAL: {
				ret = p.generateChoice
			}
			
			case PseudostateKind.SHALLOW_HISTORY_LITERAL: {
				ret = p.generateShallowHistory
			}
			
			case PseudostateKind.DEEP_HISTORY_LITERAL: {
				ret = p.generateDeepHistory
			}
			
			case PseudostateKind.ENTRY_POINT_LITERAL: {
				ret = p.generateEntryPoint
			}
			
			case PseudostateKind.EXIT_POINT_LITERAL: {
				ret = p.generateExitPoint
			}
			case INITIAL_LITERAL: {
			}
			case TERMINATE_LITERAL: {
			}
		}
		
		return ret
	}
	
	def String generateJunction(Pseudostate p) {
		var ret = '''
		«FOR out:p.outgoings SEPARATOR ' else '»
		if («p.name» == «p.outgoings.indexOf(out)») {
			«generateBetweenVertex(p, out.target, out)»
		}
		«ENDFOR»'''
		return ret
	}
	
	
	def String generateBetweenVertex(Vertex v1, Vertex v2, Transition t) {
		var exits = new ArrayList<Vertex>
		var enters = new ArrayList<Vertex>
		var ret = ''''''
		core.eventTransform.calculateExitsEnters(t, exits, enters)
		var cpEx = exits.last
		ret = '''
		«ret»
		«IF cpEx instanceof State»
		«core.generateExitingSubStates(cpEx as State, true)»
		«ENDIF»
		«TransformationUtil.getTransitionEffect(t)»'''
		
		var cpEn = enters.last
		var innermost = enters.head
		if (innermost instanceof State) {
			ret = '''
			«ret»
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
			ret = '''
			«ret»
			«IF cpEn instanceof State»
				«core.generateEnteringOnSubVertex(cpEn, innermost)»
			«ELSE»
				«generatePseudo(innermost as Pseudostate)»
			«ENDIF»'''
		}
		return ret
	}
	
	def String generateDeepHistory(Pseudostate p) {
		var regionIndex = p.container.state.regions.indexOf(p.container)
		var subCompositeStates = p.container.subvertices.filter(State).filter[!(it instanceof FinalState) && it.composite]
		var parentId = p.container.state.name.toUpperCase + "_ID"
		var subAtomics = p.container.subvertices.filter(State).filter[!(it instanceof FinalState) && !it.composite]
		var atomicsWithTimeEvent = subAtomics.filter[core.states2TimeEvents.get(it) != null && !core.states2TimeEvents.get(it).empty]
		var ret = '''
		unsigned int «p.container.getDeepHistoryVariableName(p)»;
		if («STATE_ARRAY_ATTRIBUTE»[«parentId»].«PREVIOUS_STATES»[«regionIndex»] != «STATE_MAX») {
			//restore active sub-state of «p.container.state.name»
			«p.container.getDeepHistoryVariableName(p)» = «STATE_ARRAY_ATTRIBUTE»[«parentId»].«PREVIOUS_STATES»[«regionIndex»];
			(this->*«STATE_ARRAY_ATTRIBUTE»[«p.container.getDeepHistoryVariableName(p)»].«ENTRY_NAME»)();
			«FOR a:atomicsWithTimeEvent SEPARATOR ' else '»
				if («a.name.toUpperCase»_ID == «p.container.getDeepHistoryVariableName(p)») {
					«core.generateActivateTimeEvent(a)»
				}
			«ENDFOR»
			«SET_FLAG»(«p.container.getDeepHistoryVariableName(p)», «THREAD_FUNC_DOACTIVITY_TYPE», true);
			«FOR comp:subCompositeStates SEPARATOR ' else '»
				if («comp.name.toUpperCase»_ID == «p.container.getDeepHistoryVariableName(p)») {
					«generateRestoreString(comp, p)»
				}
			«ENDFOR»
		} else {
			//for the first time, the history should be initialized
			«IF p.outgoings.size > 0»
				«generateBetweenVertex(p, p.outgoings.head.target, p.outgoings.head)»
			«ELSE»
				«core.getRegionMethodName(p.container)»(«core.getInitialMacroName(p.container)»);
			«ENDIF»
		}'''
		return ret
	}
	
	private def String generateRestoreString(State s, Pseudostate p) {
		var subAtomics = p.container.subvertices.filter(State).filter[!(it instanceof FinalState) && !it.composite]
		var atomicsWithTimeEvent = subAtomics.filter[core.states2TimeEvents.get(it) != null && !core.states2TimeEvents.get(it).empty]
		var ret = '''
		//restore active-stub state of «s.name»
		«FOR r:s.regions»
			unsigned int «r.getDeepHistoryVariableName(p)» = «STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«PREVIOUS_STATES»[«s.regions.indexOf(r)»];
			(this->*«STATE_ARRAY_ATTRIBUTE»[«r.getDeepHistoryVariableName(p)»].«ENTRY_NAME»)();
			«FOR a:atomicsWithTimeEvent SEPARATOR ' else '»
				if («a.name.toUpperCase»_ID == «r.getDeepHistoryVariableName(p)») {
					«core.generateActivateTimeEvent(a)»
				}
			«ENDFOR»
			«SET_FLAG»(«r.getDeepHistoryVariableName(p)», «THREAD_FUNC_DOACTIVITY_TYPE», true);
			«var subCompositeStates = r.subvertices.filter(State).filter[!(it instanceof FinalState) && it.composite]»
			«FOR comp:subCompositeStates SEPARATOR ' else '»
				if («comp.name.toUpperCase»_ID == «r.getDeepHistoryVariableName(p)») {
					«generateRestoreString(comp, p)»
				}
			«ENDFOR»
		«ENDFOR»
		'''
		return ret
	}
	
	def getDeepHistoryVariableName(Region r, Pseudostate p) {
		return '''«r.state.name»_«r.name»_«p.name»'''
	}
	
	def String generateShallowHistory(Pseudostate p) {
		var regionIndex = p.container.state.regions.indexOf(p.container)
		var subCompositeStates = p.container.subvertices.filter(State).filter[!(it instanceof FinalState) && it.composite]
		var pseudoInitial = TransformationUtil.firstPseudoState(p.container, PseudostateKind.INITIAL_LITERAL)
		var parentId = p.container.state.name.toUpperCase + "_ID"
		var subAtomics = p.container.subvertices.filter(State).filter[!(it instanceof FinalState) && !it.composite]
		var atomicsWithTimeEvent = subAtomics.filter[core.states2TimeEvents.get(it) != null && !core.states2TimeEvents.get(it).empty]
		var ret = '''
		unsigned int loc_ActiveId;
		if («STATE_ARRAY_ATTRIBUTE»[«parentId»].«PREVIOUS_STATES»[«regionIndex»] != «STATE_MAX») {
			loc_ActiveId = «STATE_ARRAY_ATTRIBUTE»[«parentId»].«PREVIOUS_STATES»[«regionIndex»];
		} else {
			//for the first time, the history should be initialized
			«IF p.outgoings.size > 0»
				«TransformationUtil.getTransitionEffect(p.outgoings.head)»
				loc_ActiveId = «p.outgoings.head.target.name.toUpperCase»_ID;
			«ELSE»
				«TransformationUtil.getTransitionEffect(pseudoInitial.outgoings.head)»
				loc_ActiveId = «pseudoInitial.outgoings.head.target.name.toUpperCase»_ID;
			«ENDIF»
		}
		«STATE_ARRAY_ATTRIBUTE»[«parentId»].«ACTIVE_SUB_STATES»[«regionIndex»] = loc_ActiveId;
		«FOR comp:subCompositeStates SEPARATOR ' else '»
			if («comp.name.toUpperCase»_ID == loc_ActiveId) {
				«core.generateEnteringOnSubVertex(comp, null)»
			}
		«ENDFOR» «IF subCompositeStates.size > 0» else { «ENDIF»
			(this->*«STATE_ARRAY_ATTRIBUTE»[loc_ActiveId].«ENTRY_NAME»)();
			«FOR a:atomicsWithTimeEvent SEPARATOR ' else '»
			if («a.name.toUpperCase»_ID == loc_ActiveId) {
				«core.generateActivateTimeEvent(a)»
			}
			«ENDFOR»
			«SET_FLAG»(loc_ActiveId, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«IF subCompositeStates.size > 0»}«ENDIF»'''
		return ret
	}
	
	def String generateChoice(Pseudostate p) {
		var ret = '''
		«FOR out:p.outgoings.filter[it.guard != null] SEPARATOR ' else '»
		if («core.getGuard(out)») {
			«generateBetweenVertex(p, out.target, out)»
		}«ENDFOR» else {
			«var t = p.outgoings.filter[it.guard == null].head»
			«generateBetweenVertex(p, t.target, t)»
		}'''
		return ret
	}
	
	def intersect(List<Vertex> l1, List<Vertex> l2) {
		return l1.filter[l2.contains(it)].toList
	}
	
	def String generateEntryPoint(Pseudostate p) {
		var ret = '''
		'''	
		val targets = p.outgoings.map[it.target].toList
		val enteredRegions = (p.eContainer as State).regions.filter[
			core.allSubVertexes(it).toList.intersect(targets).size > 0
		].toList
		var defaultRegions = (p.eContainer as State).regions.filter[!enteredRegions.contains(it)]
		ret = '''
			«FOR out:p.outgoings»
				«TransformationUtil.getTransitionEffect(out)»
			«ENDFOR»
			«FOR r:defaultRegions.filter[TransformationUtil.findInitialState(it) != null]»
				«core.getRegionMethodName(r)»(«core.getInitialMacroName(r)»);
			«ENDFOR»
			«FOR r:enteredRegions»
				«core.getRegionMethodName(r)»(«core.getVertexMacroName(targets.filter[r.allNamespaces.contains(it)].head)»);
			«ENDFOR»'''		
		return ret
	}
	
	def String generateExitPoint(Pseudostate p) {
		var ret = '''
		'''	
		ret = '''
		«generateBetweenVertex(p, p.outgoings.head.target, p.outgoings.head)»
		'''		
		return ret
	}
	
	def String generateFork(Pseudostate p) {
		var targets = p.outgoings.map[it.target]
		var ret = ''''''
		//look for the orthogonal parent state
		
		var exits = new ArrayList<Vertex>
		var enters = new ArrayList<Vertex>
		core.eventTransform.calculateExitsEnters(p.outgoings.head, exits, enters)
		var cpEx = exits.last
		var parent = enters.last as State
		if (!parent.orthogonal) {
			throw new Exception("state machine is ill-formed")
		}
		
		ret = '''
		«ret»
		«IF cpEx instanceof State»
		«core.generateExitingSubStates(cpEx as State, true)»
		«ENDIF»
		//need to be concurrently executed
		«FOR out:p.outgoings»
		«TransformationUtil.getTransitionEffect(out)»
		«ENDFOR»
		'''
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		ret = '''
		«ret»
		«core.generateChangeState(parent)»
		«core.getFptrCall(pAttr, false, ENTRY_NAME)»;
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«FOR r:parent.regions»
			«FOR target:targets»
				«IF core.allSubVertexes(r).contains(target)»
					«core.concurrency.generateForkCall(r, true, core.getVertexMacroName(target))»
				«ENDIF»
			«ENDFOR»
		«ENDFOR»
		'''		
		return ret
	}
	
	def String generateJoin(Pseudostate p) {
		return '''
		«generateBetweenVertex(p, p.outgoings.head.target, p.outgoings.head)»'''
	}
}
