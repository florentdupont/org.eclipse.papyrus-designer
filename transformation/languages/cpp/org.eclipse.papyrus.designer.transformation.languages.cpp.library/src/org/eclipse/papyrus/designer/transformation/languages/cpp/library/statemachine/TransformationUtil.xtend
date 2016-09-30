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

import java.util.Map
import org.eclipse.uml2.uml.StateMachine
import java.util.HashMap
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.Vertex
import org.eclipse.uml2.uml.Region
import org.eclipse.uml2.uml.PseudostateKind
import java.util.List
import org.eclipse.uml2.uml.State
import java.util.ArrayList
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.Transition
import org.eclipse.uml2.uml.FinalState
import org.eclipse.uml2.uml.Event
import org.eclipse.uml2.uml.Behavior

class TransformationUtil {

	static def getStateMachineClass(org.eclipse.uml2.uml.Package pack) {
		val Map<Class, StateMachine> map = new HashMap
		
		pack.ownedElements.filter(typeof(Class)).forEach[
			var behavior = it.ownedBehaviors.filter(typeof(StateMachine)).head
			if (behavior != null) {
				map.put(it, behavior)
			}
		]
		
		return map
	}
	
	public static def Pseudostate firstPseudoState(Region region, PseudostateKind kind) {
		for (Vertex vertex : region.getSubvertices()) {
			if (vertex instanceof Pseudostate) {
				var pseudoState = vertex as Pseudostate
				if (pseudoState.getKind() == kind) {
					return pseudoState;
				}
			}
		}
		return null;
	}
	
	public static def List<State> findCompositeStatesInRegion(Vertex des1, Vertex des2) {
		var ret = new ArrayList<State>
		val path1 = new ArrayList<State>
		if (des1 instanceof State) {
			path1.add(des1)
		}
		var owner1 = des1.container.state
		while(owner1 != null) {
			path1.add(owner1)
			owner1 = owner1.container.state
		}

		var owner2 = des2
		while(owner2 != null && ret.empty) {
			for(s:path1) {
				if (s.container == owner2.container && owner2 instanceof State) {
					ret.add(s)
					ret.add(owner2 as State)
				}
			}
			owner2 = owner2.container.state
		}

		return ret
	}
	
	def static State findInitialState(Region r) {
		var pseudoDefault = TransformationUtil.firstPseudoState(r, PseudostateKind.INITIAL_LITERAL)
		if (pseudoDefault != null) {
			return pseudoDefault.outgoings.head.target as State
		}
		return null
	}
	
	def static String getInitialEffect(Region r) {
		var pseudoDefault = TransformationUtil.firstPseudoState(r, PseudostateKind.INITIAL_LITERAL)
		if (pseudoDefault != null) {
			var t = pseudoDefault.outgoings.head
			if (t.effect != null && t.effect instanceof OpaqueBehavior) {
				return (t.effect as OpaqueBehavior).bodies.head
			}
		}
		return "//no initial effect is defined"
	}
	
	def static String getTransitionEffect(Transition t) {
		if (t.effect != null && t.effect instanceof OpaqueBehavior) {
			return (t.effect as OpaqueBehavior).bodies.head
		}
		return ''''''
	}
	
	def static List<State> transitiveSubStates(State parent) {
		val ret = new ArrayList<State>
		if (parent.composite) {
			for(r:parent.regions) {
				ret.addAll(r.subvertices.filter(State))
				r.subvertices.filter(State).forEach[
					ret.addAll(it.transitiveSubStates)
				]
			}
		}
		return ret
	}
	
	def static List<State> transitiveSubStates(Region parent) {
		val ret = new ArrayList<State>
		parent.subvertices.filter(State).filter[!(it instanceof FinalState)].forEach[
			ret.add(it)
			ret.addAll(it.transitiveSubStates)
		]
		return ret
	}

	/**
	 * Return the name of an event (remove non-ASCII characters)
	 */
	public def static eventName(Event event) {
		var name = ""
		var repeat = false
		for (c : event.name.toCharArray) {
			if (Character.isAlphabetic(c) || Character.isDigit(c)) {
				name += c
				repeat = false;
			}
			else if (!repeat) {
				name += "_";
				repeat = true;
			}
		}
		name
	}

	/**
	 * Return the ID of an event
	 */
	public def static eventID(Event event) {
		event.eventName.toUpperCase + "_ID"
	}
	
	def static isSavehistory(Region topRegion, Region r) {
		if (r.subvertices.filter(Pseudostate).filter[it.kind == PseudostateKind.SHALLOW_HISTORY_LITERAL].size > 0) {
			return true
		}
		
		return topRegion.isSaveDeepHistory(r)
	}
	
	def static isBehaviorExist(Behavior b) {
		var ret = false
		if (b != null && b instanceof OpaqueBehavior) {
			if (!(b as OpaqueBehavior).bodies.head.trim.empty) {
				ret = true
			}
		}
		return ret
	}
	
	def static hasTriggerlessTransition(State s) {
		return s.outgoings.filter[it.triggers.map[it.event].empty].size > 0
	}
	
	def static boolean isSaveDeepHistory(Region topRegion, Region r) {
		if (r.subvertices.filter(Pseudostate).filter[it.kind == PseudostateKind.DEEP_HISTORY_LITERAL].size > 0) {
			return true
		}
		if (r != topRegion) {
			var nextRegion = r.state.container
			return topRegion.isSaveDeepHistory(nextRegion)
		}		
		return false
	}
}
