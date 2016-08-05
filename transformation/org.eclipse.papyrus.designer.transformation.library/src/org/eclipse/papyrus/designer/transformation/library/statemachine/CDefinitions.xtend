package org.eclipse.papyrus.designer.transformation.library.statemachine

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