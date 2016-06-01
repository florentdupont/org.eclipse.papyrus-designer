package org.eclipse.papyrus.designer.components.modellibs.core.statemachine

import org.eclipse.uml2.uml.State
import java.util.List
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.Transition
import org.eclipse.emf.common.util.UniqueEList

class TransitionGraph {
	public List<State> S = new UniqueEList
	public List<State> L = new UniqueEList
	public List<Pseudostate> P = new UniqueEList<Pseudostate>
	public List<Transition> T = new UniqueEList
}