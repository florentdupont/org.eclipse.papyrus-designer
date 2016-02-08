/*******************************************************************************
* Copyright (c) 2014 Zeligsoft (2009) Limited  and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/

package org.eclipse.papyrus.designer.components.modellibs.core.xtend

import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.uml2.uml.Behavior
import org.eclipse.uml2.uml.Transition
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * A chain of state entry, exit and transition actions for a "flat"
 * {@link Transition}.
 *
 * @author eposse
 *
 */
class UMLChain {

    @Accessors List<UMLAction> actions
    @Accessors Transition owner

	static Map<Transition, UMLChain> chains;

    new () {
        actions = newArrayList
    }

    new (List<UMLAction> actions) {
        this.actions = newArrayList(actions) // We need this to be mutable.
    }

	def static void initChains() {
		chains = new HashMap<Transition, UMLChain>();
	}
	
	/**
	 * Return the chain of behaviors associated with a transition
	 * IF the chain does not exist yet, it will be created (and stored in a hash map) 
	 * @param t
	 * @return
	 */
	def static UMLChain chain(Transition t) {
		var UMLChain chain = chains.get(t)
		if (chain == null) {
			chain = new UMLChain()
			chain.setOwner(t)
			chains.put(t, chain)
		}
		return chain
	}

    def append(UMLAction action) {
        actions.add(action)
        return this
    }

	def append(Behavior behavior) {
		if (behavior != null) {
	    	append(new UMLAction(behavior))
	    }
        return this
    }

    def prepend(UMLAction action) {
        actions.add(0, action)
        return this
    }

	def prepend(Behavior behavior) {
	    prepend(new UMLAction(behavior))
        return this
    }

    def getOwner() {
        owner
    }

    def setOwner(Transition t) {
       	owner = t
       	// add transition effect to action list
       	append(t.effect)
    }
}