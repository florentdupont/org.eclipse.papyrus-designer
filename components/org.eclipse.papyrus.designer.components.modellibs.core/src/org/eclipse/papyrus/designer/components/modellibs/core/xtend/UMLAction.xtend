/*******************************************************************************
* Copyright (c) 2014 Zeligsoft, CEA and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/

package org.eclipse.papyrus.designer.components.modellibs.core.xtend

import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.uml2.uml.Behavior
import java.util.List
import java.util.ArrayList

/**
 * An action in the sense of the invocation of a behavior.
 * This means that the actual behavior is not described here. The action
 * is referencing the behavior and providing information how to invoke the
 * behavior.
 * The action has no prefix: the name of the referenced behavior needs to be prefixed, if necessary
 * 
 * @author Ansgar (based on code from eposse)
 *
 */
class UMLAction {

    /** Source UMLRT model element. */
    @Accessors Behavior origin

	// list of parameters for a call, simply a list of strings. if null => no parameters
	public List<String> callParams;
	
   	new(Behavior origin) {
		this.origin = origin
	}
	
	/**
	 * Add a parameter
	 */
	def addParam(String parameter) {
		if (callParams == null) {
			callParams = new ArrayList<String>()
		}
		callParams.add = parameter
		}
	
	def getSpecification() {
		origin.specification	
	}
	
	def getFuncName() {
		origin.name
 	}

   	override toString() '''Action(«origin.name»)'''

}