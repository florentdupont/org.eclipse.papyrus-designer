/*******************************************************************************
* Copyright (c) 2014 Zeligsoft, CEA and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package org.eclipse.papyrus.qompass.modellibs.core.xtend

import org.eclipse.uml2.uml.Behavior
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.PseudostateKind
import org.eclipse.uml2.uml.Region
import org.eclipse.uml2.uml.State
import org.eclipse.uml2.uml.StateMachine
import org.eclipse.uml2.uml.Transition
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.Vertex

import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.BehaviorUtil.*
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.StateMachineUtil.*
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.UMLChain.chain
import org.eclipse.uml2.uml.OpaqueBehavior

/**
 * This class contains the transformation for flattening UML-RT state machines
 *
 * It implements the algorithms described in the technical report
 *
 * E. Posse. "Flattening UML-RT State Machines".
 * Technical Report ZTR-2014-EP-001, Version 2, Zeligsoft, Sep 2014.
 *
 * @author eposse
 */
class UMLFlattener {

	val FRESH_NAME_PREFIX = "p"
	val FRESH_ENTRYPOINT_NAME_PREFIX = "entrypoint_"
	val FRESH_EXITPOINT_NAME_PREFIX = "exitpoint_"
	val FRESH_CHOICEPOINT_NAME_PREFIX = "c"
	val FRESH_JUNCTIONPOINT_NAME_PREFIX = "j"
	var FRESH_TRANSITION_NAME_PREFIX = "transition_"

	var freshNameCounter = 0
	var freshEntryPointNameCounter = 0
	var freshExitPointNameCounter = 0
	var freshChoicePointNameCounter = 0
	var freshJunctionPointNameCounter = 0
	var freshTransitionNameCounter = 0
	
	var Behavior saveHistoryBehavior = null
	var Class clazz
	
	/**
     * Flattens a UML-RT state machine, removing composite states, and making
     * explicit transitions implied by group transitions, history points and
     * initial points.
     *
     * This is the 'main' method of the transformer. It performs the
     * transformation in-place.
     *
     * @param m a {@link StateMachine}
     * @return the flat {@link StateMachine} m with all composite states, group
     *          transitions, history and initial pseudo-states removed.
     */
	def StateMachine transform(StateMachine m) {
		var StateMachine intermediateModel
		clazz = m.owner as Class
		UMLChain.initChains
		phase0QualifyNames(m.region, m.name)
		phase1(m)
		intermediateModel = phase2(m)
		return intermediateModel
	}

	/**
	 * Change the names of states and transitions: prefix them with the names
	 * of the stateMachine and composite states.
	 */
	protected def void phase0QualifyNames(Region region, String parentName) {
		for (Transition transition : region.transitions) {
			transition.name = parentName + '_' + transition.name
		}
		for (Vertex vertex : region.subvertices) {
			vertex.name = parentName + '_' + vertex.name
			if (vertex instanceof State) {
				val State state = vertex as State
				for (subRegion : state.regions) {
					phase0QualifyNames(subRegion, vertex.name)
				}
			}
		}
	}
	

	/**
     * Makes explicit transitions implied by group transitions, history points
     * and initial points.
     */
	protected def StateMachine phase1(StateMachine m) {
		for (State s : m.states) {
			phase1ProcessState(s)
		}
		return m
	}

	def void phase1ProcessState(State state) {
		phase1ProcessSimpleState(state)
		if (!state.regions.empty) {
			phase1ProcessCompositeState(state)
		}
	}
	
	/**
     * Moves a simple state's entry actions to its incoming transitions and
     * the state's exit actions to its outgoing transitions.
     * 
     */
	def void phase1ProcessSimpleState(State state) {
		// TODO: should that be done in UML2FlatBody (need chain?)
		if (state.entry != null) {
			val entryOp = clazz.createOperation(entryFctName(state), null);
			state.entry.name = entryFctName(state)
			// add specification
			state.entry.specification = entryOp;
			// clazz.ownedBehaviors.add(state.entry)
			for (Transition t : state.incomings) {
				t.chain.append(state.entry)
			}
		}
		if (state.exit != null) {
			val exitOp = clazz.createOperation(exitFctName(state), null);
			state.exit.name = exitFctName(state)
			// add specification
			state.exit.specification = exitOp;
			// clazz.ownedBehaviors.add(state.exit)
			for (Transition t : state.allOutgoings) {
				t.chain.prepend(state.exit)
			}
		}
	}

	def entryFctName(State state) {
		GlobalConstants.ENTRY_ACTION_FUNC_PREFIX + GlobalConstants.FUNC_NAME_QUAL_NAME_SEP + state.name + GlobalConstants.FUNC_NAME_QUAL_NAME_SEP + state.entry.name
	}
	
	def exitFctName(State state) {
		GlobalConstants.EXIT_ACTION_FUNC_PREFIX + GlobalConstants.FUNC_NAME_QUAL_NAME_SEP + state.name + GlobalConstants.FUNC_NAME_QUAL_NAME_SEP + state.exit.name
	}
	
	/**
     * Creates entry transitions for incoming arrows ending at the composite
     * state's boundary or history pseudo-state. Creates exit transitions
     * implied by group transitions.
     */
	def void phase1ProcessCompositeState(State state) {

		//        if (state.substates.empty) {
		//            replaceBySimpleState(state)
		//            return
		//        }
		// Deal with incoming transitions
		// TODO (ar): such a state can have entry and exit as well, no?
		annotateExplicitEntryTransitions(state)
		createEntryPoints(state)
		var historyChoicePoint = replaceHistoryPoint(state)
		removeInitialTransition(state, historyChoicePoint)

		// Deal with outgoing transitions
		annotateExplicitExitTransitions(state)
		createExitPoints(state)
		createExitTransitions(state)

		// Process sub-states
		for (State subState : state.subStates) {
			phase1ProcessState(subState)
		}
	}

	/**
     * Prepends the state's entry action to each explicit entry transition
     * (transition from an entry point to a sub-state).
     */
	def annotateExplicitEntryTransitions(State state) {
		for (Pseudostate p : state.entryPoints) {
			if (!p.outgoings.empty) {
				for (Transition t : p.outgoings) {
					t.chain.append(state.entry)
				}
			}
		}
	}

	/**
     * Creates entry points for every direct incoming transition (transition
     * ending directly on the state's boundary).
     */
	def createEntryPoints(State state) {
		for (Transition t : state.incomings) {
			val p = state.createConnectionPoint(state.newFreshEntryPointName)
			p.kind = PseudostateKind.ENTRY_POINT_LITERAL
			t.target = p
		} //TODO: maybe remove all directIncomming?
	}

	/**
     * Replaces the (deep)history pseudo-state with a choice point with transitions
     * to each sub-state with conditions checking the value of the history table
     * entry for this composite-state.
     */
	def Pseudostate replaceHistoryPoint(State state) {

		// transform history state into a choice point
		val c = state.region.deepHistory;

		if (c != null) {
			// No need to redirect incoming transitions, since we transform the history
			// state with a choice point
			c.kind = PseudostateKind.CHOICE_LITERAL;
		}
		else {
			return null;
		}

		// Create transitions from the choice point to each sub-state with the
		// appropriate guard.
		for (State subState : state.subStates) {
			var Vertex targetVertex
			if (subState.regions.size() == 0) {
				targetVertex = subState
			}
			else {
				targetVertex = subState.region.deepHistory
				if (targetVertex == null) {
					targetVertex = subState
				}
			}

			val newTransition = state.region.createTransition(state.newFreshTransitionName)
			newTransition.source = c;
			newTransition.target = targetVertex;
			val guard = newTransition.createGuard("CheckHistory");
			guard.createOpaqueExpression('''history[«state.name»] == «subState.name»''')
			if (state.entry != null) {
				newTransition.chain.append(state.entry)
			}
		}
		// If there was an initial pseudo-state, create a transition from the
		// choice point to the initial state where the guard will be true if
		// the composite state has not been visited before.
		val initialState = state.region.initialState
		if (initialState != null) {
			val initialTransition = initialState.outgoings.get(0)

			val newTransition = state.region.createTransition(state.newFreshTransitionName)
			newTransition.source = c;
			newTransition.target = initialTransition.target;
			val guard = newTransition.createGuard("CheckHistory");
			guard.createOpaqueExpression('''history[«state.name»] == «GlobalConstants.UNVISITED»''')
			// TODO: not sure, if semantics is the same.
			if (initialTransition.effect != null) {
				// caveat: assigining the effect to the new transition will remove it from the original
				// (initial will be removed later on => not a problem?)
				newTransition.effect = initialTransition.effect;
				newTransition.chain.append(newTransition.effect)
			}
			newTransition.chain.append(state.entry)
		}
        // If there was no initial pseudo-state, create a 'dummy' simple state
		// representing "staying at the boundary", and create a transition from
		// the choice point to this dummy state, where the guard is true if
		// the state has not been visited before.
		else {
			// The name should be different name because one of the sub-states
			// may have the same name as the composite.
			val border = state.region.createState(state.newFreshName);

			val newTransition = state.region.createTransition(state.newFreshTransitionName);
			newTransition.source = c;
			newTransition.target = border;
			val guard = newTransition.createGuard("Check History")
			guard.createOpaqueExpression('''history[«state.name»] == «GlobalConstants.UNVISITED»''')
			if (state.entry != null) {
				newTransition.chain.append(state.entry)
			}
		}
		return c
	}

	/**
     * Remove the initial pseudo-state and transition and create a transition
     * from each entry point which does not have an explicit entry transition
     * to a sub-state, to the initial state.
     */
	def removeInitialTransition(State state, Pseudostate historyChoicePoint) {
		val initialState = state.region.initialState
		if (initialState != null) {
			val initialTransition = initialState.outgoings.get(0) // There should be exactly one initial transition
			for (Pseudostate p : state.entryPoints) {
				if (p.outgoings.empty) {
					val newTransition =
						state.region.createTransition(state.newFreshTransitionName)
					newTransition.source = p
					newTransition.target = initialTransition.target
					newTransition.chain.append(state.entry)
					newTransition.chain.append(initialTransition.effect);
				}
			}
			state.region.transitions.remove(initialTransition)
			// initialTransition.targetVertex.incomingTransitions.remove(initialTransition) // TODO: this might not be enough if the initial transition ends in a pseudo-state
			// state.removeInitial
		}
		else {
			for (Pseudostate p : state.entryPoints) {
				if (p.outgoings.empty) {
					val newTransition =
						state.region.createTransition(state.newFreshTransitionName)
					newTransition.setSource(p)
					newTransition.setTarget(historyChoicePoint)
					newTransition.effect = state.entry;	
				}
			}
		}
	}

	/**
     * Annotate explicit exit transitions (transitions from a sub-state to an
     * exit point) with a "history saving action", an action that sets the
     * history table entry for the composite state according to the sub-state
     * that is exited.
     */
	def annotateExplicitExitTransitions(State state) {
		for (Pseudostate p : state.exitPoints) {
			if (!p.incomings.empty) {
				for (Transition t : p.incomings) {
					if (saveHistoryBehavior == null) {
						createSaveHistoryBehavior
					}
					// Actions can have specific subclasses in the Zeligsoft model. Here we use a single generic class.
					// Therefore we add call information explicitly.
					val UMLAction saveHistoryAction = new UMLAction(saveHistoryBehavior)
					saveHistoryAction.addParam = state.name
					saveHistoryAction.addParam = t.source.name
					t.chain.append = saveHistoryAction
					if (state.exit != null) {
						t.chain.append(state.exit)
					}
				}
			}
		}
	}

	/**
     * Create exit points for each direct outgoing transition (transition leaving
     * directly from the composite state's boundary).
     */
	def createExitPoints(State state) {
		for (Transition t : state.outgoings) {
			val p = state.createConnectionPoint(state.newFreshExitPointName);
			p.kind = PseudostateKind.EXIT_POINT_LITERAL
			t.source = p;
		}
	}

	def createSaveHistoryBehavior() {
		// create an opaque behavior without body. This will be added later in the UMLFlat2Cpp operation
		saveHistoryBehavior = clazz.createOwnedBehavior(GlobalConstants.SAVE_HISTORY_FUNC_NAME,
			UMLPackage.eINSTANCE.getOpaqueBehavior()
		);	
	}
	
	/**
     * Create explicit exit transitions implied by group transitions. For each
     * group transition creates a transition from every sub-state to the
     * group-transitions's source exit point. It annotates these transitions
     * with a "history saving action", an action which stores the sub-state
     * in the history table entry for this composite state.
     */
	def createExitTransitions(State state) {

		// Iterate over exit points, looking for group transitions.
		for (Pseudostate p : state.exitPoints) {

			// If the exit point doesn't have incoming transitions then there
			// is at least one group transition from it.
			if (p.incomings.empty) {

				// Create explicit transitions from every sub-state to this
				// exit point...
				for (State subState : state.subStates) {

					// ...for each group transition leaving the exit point.
					for (Transition t : p.outgoings) {
							
						if (saveHistoryBehavior == null) {
							createSaveHistoryBehavior
						}
						// Append the history saving action and the state's
						// exit action.
						val newTransition = state.region.createTransition(state.newFreshTransitionName)
						newTransition.source = subState
						newTransition.target = p
						// make a copy (adding it to triggers list would remove it from original transition).
						for (trigger : t.triggers) {
							val newTrigger = newTransition.createTrigger(trigger.name)
							newTrigger.event = trigger.event
						}
						// newTransition.triggers.addAll(t.triggers)
						newTransition.guard = t.guard
						val UMLAction saveHistoryAction = new UMLAction(saveHistoryBehavior)
						saveHistoryAction.addParam = state.name
						saveHistoryAction.addParam = subState.name
						newTransition.chain.append(saveHistoryAction)
						newTransition.chain.append(state.exit)
						// make a copy of the effect (but: quite bad, since we might create several copies of same effect)
						if (t.effect instanceof OpaqueBehavior) {
							val newBehavior = newTransition.createOpaqueEffect
							newBehavior.name = t.effect.name
							newBehavior.set = t.effect.body
						}
					}
				}

				// Remove the trigger and guard from the old group transition,
				// but leave the transition.
				// TODO: we must replace the direct group transition with an indirect group transition leaving from the exit point
				for (Transition t : p.outgoings) {
					t.triggers.clear
					t.guard = null
					t.effect = null
				}
			}
		}
	}

	/**
     * Flatten composite states.
     */
	protected def StateMachine phase2(StateMachine m) {
		for (State s : m.states.clone) {
			phase2ProcessState(s)
			if (!s.subStates.empty) {
				m.region.moveContents(s)
				// remove regions from composite state
				s.regions.clear	
			}
		}
		return m
	}

	/**
     * Removes entry and exit points of a simple state, making all transitions
     * to and from it, direct.
     */
	def dispatch void phase2ProcessState(State state) {
		if (state.regions.empty) {
			phase2ProcessSimpleState(state)
		}
		else {
			phase2ProcessComplexState(state)		
		}
		// change name afterwards (avoid that new names are taken into account recursively)
	}

	/**
     * Removes entry and exit points of a simple state, making all transitions
     * to and from it, direct.
     */
	def dispatch void phase2ProcessSimpleState(State state) {
		for (Transition t : state.indirectIncomings) {
			t.target = state // TODO: Potential problem: depending on the set implementation we may be modifying the iterator
		}
		for (Transition t : state.outgoings) {
			t.source = state
		}
		for (Pseudostate p : state.entryPoints) {
			state.connectionPoints.remove(p)
		}
		for (Pseudostate p : state.exitPoints) {
			state.connectionPoints.remove(p)
		}
	}

	/**
     * Replaces all entry and exit points of the composite state with junction
     * points, and recursively apply this to sub-states.
     */
	def dispatch void phase2ProcessComplexState(State state) {
		// Replace connection points with junction points
		for (Pseudostate p : state.connectionPoints.clone) {
			var j = state.region.createPseudostate(GlobalConstants.JUNCTION_FUNC_PREFIX + p.name)
			j.kind = PseudostateKind.JUNCTION_LITERAL 
					
			for (Transition t : p.incomings) {
				t.target = j
			}
			for (Transition t : p.outgoings) {
				t.source = j
			}
			if (p.kind == PseudostateKind.ENTRY_POINT_LITERAL) {
				state.connectionPoints.remove(p)
			}
			else if (p.kind == PseudostateKind.EXIT_POINT_LITERAL) {
				state.connectionPoints.remove(p)
			}
		}

		// Process substates
		for (State subState : state.subStates.clone) {
			phase2ProcessState(subState)
			if (!subState.regions.empty) {
				state.region.moveContents(subState)
				subState.regions.clear
			}
		}
	}

	/**
     * Auxiliary methods
     */
	def String getNewFreshName(NamedElement parent) {
		freshNameCounter ++
		return parent.namePrefix + FRESH_NAME_PREFIX + freshNameCounter
	}

	def String getNewFreshEntryPointName(NamedElement parent) {
		freshEntryPointNameCounter ++
		return parent.namePrefix + FRESH_ENTRYPOINT_NAME_PREFIX + freshEntryPointNameCounter
	}

	def String getNewFreshExitPointName(NamedElement parent) {
		freshExitPointNameCounter ++
		return parent.namePrefix + FRESH_EXITPOINT_NAME_PREFIX + freshExitPointNameCounter
	}

	def String getNewFreshChoicePointName(NamedElement parent) {
		freshChoicePointNameCounter ++
		return parent.namePrefix + FRESH_CHOICEPOINT_NAME_PREFIX + freshChoicePointNameCounter
	}

	def String getNewFreshJunctionPointName(NamedElement parent) {
		freshJunctionPointNameCounter ++
		return parent.namePrefix + FRESH_JUNCTIONPOINT_NAME_PREFIX + freshJunctionPointNameCounter
	}

	def String getNewFreshTransitionName(NamedElement parent) {
		freshTransitionNameCounter ++
		return parent.namePrefix + FRESH_TRANSITION_NAME_PREFIX + freshTransitionNameCounter
	}

	def namePrefix(NamedElement ne) {
		return ne.name + "_"
	}
}
