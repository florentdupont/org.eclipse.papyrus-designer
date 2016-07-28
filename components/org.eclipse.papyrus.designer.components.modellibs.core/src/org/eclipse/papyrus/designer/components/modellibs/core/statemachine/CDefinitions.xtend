/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.modellibs.core.statemachine

import org.eclipse.uml2.uml.Class

class CDefinitions {
	new (Class contextClass) {
		this.contextClass = contextClass;
	}
	Class contextClass
	
	public def String TIME_EVENT_LOWER_BOUND() {
		macro("TIME_EVENT_LOWER_BOUND")
	}
	
	public def String CHANGE_EVENT_LOWER_BOUND() {
		macro("CHANGE_EVENT_LOWER_BOUND")
	}
	
	public def String TE_INDEX() {
		macro("TE_INDEX")
	}
	
	public def String CHE_INDEX() {
		macro("CHE_INDEX")
	}
	
	/**
	 * return "qualified" macro name, i.e. macro name prefixed with class name
	 */
	def macro(String shortMacroName) {
		return contextClass.name.toUpperCase + "_" + shortMacroName
	}
}
