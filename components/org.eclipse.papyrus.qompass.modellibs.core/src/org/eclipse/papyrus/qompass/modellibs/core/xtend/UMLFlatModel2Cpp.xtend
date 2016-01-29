/*******************************************************************************
* Copyright (c) 2014 Zeligsoft, CEA and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package org.eclipse.papyrus.qompass.modellibs.core.xtend

import org.eclipse.uml2.uml.Port
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.State
import org.eclipse.uml2.uml.StateMachine
import org.eclipse.uml2.uml.Transition
import java.util.Map
import org.eclipse.uml2.uml.NamedElement
import java.util.Collection
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.StateMachineUtil.*
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.BehaviorUtil.*
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.EnumerationLiteral
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.PseudostateKind
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.CallEvent
import org.eclipse.uml2.uml.Stereotype
import org.eclipse.uml2.uml.Type
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.UMLChain.chain
import java.util.List
import org.eclipse.papyrus.qompass.designer.core.EnumService

/**
 * This class contains the transformation from flat UML-RT state machines to the
 * C/C++ language model.
 *
 * It implements the algorithms described in the technical report
 *
 * E. Posse. "Transforming flat UML-RT State Machines to a C/C++ language model".
 * Technical Report ZTR-2014-EP-002, Version 2, Zeligsoft, Sep 2014.
 *
 * Ansgar Radermacher (CEA): translation to UML transformation 
 * @author eposse
 */
class UMLFlatModel2Cpp {

	/**
     * The CppCodePattern that is being used for this transformation operation.
     */

	/** The source state machine to transform */
	StateMachine machine
	Class clazz

	/** Elements that go into the generated model. */
	Enumeration statesDeclaration
	Property currentStateField
	Map<State, EnumerationLiteral> stateEnumerators
	Property historyTableDeclaration
	Operation saveHistoryFunction
	Map<Transition, OpaqueBehavior> actionChainFunctions
	Map<Pseudostate, OpaqueBehavior> junctionPointFunctions
	Map<Pseudostate, OpaqueBehavior> choicePointFunctions
	Map<State, OpaqueBehavior> stateFunctions
	Operation injectFunc
	Operation initializeFunc
	Stereotype refStereo
	Stereotype constStereo

	new() {
		stateEnumerators = newHashMap
		// userActionFunctions = newHashMap
		actionChainFunctions = newHashMap
		junctionPointFunctions = newHashMap
		choicePointFunctions = newHashMap
		stateFunctions = newHashMap
	}

	/**
     * This is the main method of the transformation. It performs the
     * transformation by invoking methods that generate each part of the
     * target language model.
     */
	def transform(StateMachine m) {
		machine = m
		clazz = m.owner as Class

		generateStatesDeclaration
		generateCurrentStateField
		generateHistoryTableDeclaration
		generateSaveHistoryFunction
		// generateAllUserActionFunctions
		generateAllActionChainFunctions
		generateAllJunctionFunctions
		generateAllChoicePointFunctions
		generateAllStateFunctions
		generateInjectFunc
		generateInitializeFunc
	}

	/**
     * Builds an enum type for the states of the state machine.
     *
     * The generated code would be something like:
     *
     * <p><pre>
     * <code>enum State { s0, s1, s1_s0, s1_s1, s2, ... };</code>
     * </pre>
     */
	def generateStatesDeclaration() {

		val clazz = machine.owner as Class
		statesDeclaration = clazz.createNestedClassifier(GlobalConstants.STATE_TYPE_NAME,
			UMLPackage.eINSTANCE.getEnumeration) as Enumeration
		for (s : machine.states) {
			if (s.name == null) {
				s.name = "undefined"
			}
			val stateLiteral = statesDeclaration.createOwnedLiteral(s.name)
			stateEnumerators.put(s, stateLiteral)
		}
		statesDeclaration.createOwnedLiteral(GlobalConstants.UNVISITED)

	// stateEnumerators.put(State.UNVISITED, unvisitedStateLiteral)
	}

	/**
     * Generates a field to hold the current state.
     *
     * The generated code would be something like:
--     *
     * <p><pre>
     * <code>
     * State currentState;
     * </code>
     * </pre>
     */
	def generateCurrentStateField() {
		currentStateField = clazz.createOwnedAttribute(GlobalConstants.CURRENT_STATE_FIELD_NAME, statesDeclaration)
	}

	/**
     * Generates a declaration for the history table for the state machine.
     *
     * The generated code would be something like:
     *
     * <p>
     * <code>State[] history = { UNDEFINED, ..., UNDEFINED };
     */
	def generateHistoryTableDeclaration() {

		// TODO: currentStateField already used (probably wrong!)
		historyTableDeclaration = clazz.createOwnedAttribute(GlobalConstants.HISTORY_TABLE_NAME, statesDeclaration)
		val numStates = machine.states.size

		// use CppArray instead?
		// TODO
		// currentStateField.upper = numStates

		// get constructor TODO
		var ctor = clazz.getOwnedOperation(clazz.getName(), null, null)
		var OpaqueBehavior ctorOB
		if (ctor != null) {
			ctorOB = ctor.methods.get(0) as OpaqueBehavior
		}
		if (ctor == null) {
			ctor = clazz.createOperation(clazz.getName(), null)
			ctorOB = clazz.createOpaqueBehavior(ctor) 
		}
		
		// TODO: might be more than one constructor
		ctorOB.set('''
			int i = 0;
			while (i<«numStates») {
				«GlobalConstants.HISTORY_TABLE_NAME»[i++] = «GlobalConstants.UNVISITED»;
			}''')
	}

	/**
     * Generates a function that saves history.
     *
     * The code generated is as follows:
     *
     * <p><pre>
     * <code>
     * void saveHistory(State compositeState, State subState) {
     *     history[compositeState] = subState;
     * }
     * <code>
     * </pre>
     *
     * where <code>State</code> is the capsule's state type (an enum) and
     * <code>history</code> is the capsule's history table.
     *
     * <p><b>Note:</b> The current implementation generates this as a normal
     * function but it should be either a macro or an inline function.
     * However the C/C++ language model does not currently support these.
     *
     * @see
     *  #generateStatesDeclaration
     *  #generateHistoryTableDeclaration
     */
	def generateSaveHistoryFunction() {

		// Create the operation programmatically. Yet, it would be better
		// to provide a model template for this
		// [LW container-transformation
		// saveHist(State compositeState, State subState) {
		//   historyTable[param1] = param2;
		// }
		saveHistoryFunction = clazz.createOwnedOperation(GlobalConstants.SAVE_HISTORY_FUNC_NAME, null, null)
		saveHistoryFunction.createOwnedParameter("compositeState", stateType)
		saveHistoryFunction.createOwnedParameter("subState", stateType)
		// behavior might exist already (created by flattener)
		var funcOB = clazz.getOwnedBehavior(GlobalConstants.SAVE_HISTORY_FUNC_NAME)
		if (funcOB == null) {
			funcOB = clazz.createOpaqueBehavior(saveHistoryFunction)
		}
		else {
			funcOB.specification = saveHistoryFunction
		}
		funcOB.set("history[compositeState] = subState;")
	}

	/**
     * Generate function declarations for transition action chains.
     *
     * <p>Each function generated will have a sequence of calls, invoking either
     * the action functions generated by {@link generateActionFunc} for
     * transition, state entry and state exit actions, as well as invoking
     * "save history" actions produced by the flattening transformation.
     */
	def generateAllActionChainFunctions() {
		for (t : machine.region.transitions) {
			val f = generateActionFunc(t)
			actionChainFunctions.put(t, f)
		}
	}

	/**
     * Generates the function declaration for a single action chain.
     */
	def generateActionFunc(Transition t) {
		val transitionFuncName = GlobalConstants.TRANS_ACTION_FUNC_PREFIX + "__" + t.name
		val transitionFunc = clazz.createOwnedOperation(transitionFuncName, null, null)
		transitionFunc.createRTMessageParam(GlobalConstants.CHAIN_FUNC_PARAM)
		var transitionFuncOB = t.effect
		if (transitionFuncOB == null) {
			transitionFuncOB = clazz.createOpaqueBehavior(transitionFunc);
		}
		else {
			transitionFuncOB.specification = transitionFunc
			clazz.ownedBehaviors.add(transitionFuncOB)
		}
		
		val chainFuncName = GlobalConstants.ACTION_FUNC_PREFIX + "__" + t.name
		val chainFunc = clazz.createOwnedOperation(chainFuncName, null, null)
		chainFunc.createRTMessageParam(GlobalConstants.CHAIN_FUNC_PARAM)
		val	chainFuncOB = clazz.createOpaqueBehavior(chainFunc);
		
		// now examine chain (separate function generateActionInvocation in original code)
		// Generates a call to an action, either user action or action generated by
 		// the transformation.
		var code = ""
		for (action : t.chain.actions) {
			code += call(action.specification, action.callParams)
		}
		chainFuncOB.set(code)
		return chainFuncOB as OpaqueBehavior
	}
    
	//    def Enumerator getEnum(CppEnum enum1, String string) {
	//        throw new UnsupportedOperationException("TODO: auto-generated method stub")
	//    }
	/**
     * Generates all functions corresponding to choice points.
     */
	def generateAllChoicePointFunctions() {
		for (c : machine.region.choicePoints) {
			val f = generateChoiceFunc(c)
			choicePointFunctions.put(c, f)
		}
	}

	/**
     * Generates the function corresponding to a given choice point.
     */
	def generateChoiceFunc(Pseudostate p) {
		val funcName = GlobalConstants.CHOICE_FUNC_PREFIX + p.name
		val funcDecl = clazz.createOperation(funcName, null)
		val param = funcDecl.createRTMessageParam(GlobalConstants.CHOICE_FUNC_PARAM)
		val funcDeclOB = clazz.createOpaqueBehavior(funcDecl)

		// if (generateGuard) { [actFunc/](param); [DestStatement]
		var code = ""
		for (t : p.outgoings) {
			code += '''
				if («t.guard.body») {
					«call(actionChainFunctions.get(t), param.name)»
					// TODO: return instead
					«currentStateField.name» = «getDestination(t, false)»;
				}
				'''
		}
		funcDeclOB.set(code)
		return funcDeclOB
	}

	/**
     * Generates all functions corresponding to junction points.
     */
	def generateAllJunctionFunctions() {
		for (j : machine.region.junctionPoints) {
			val f = generateJunctionFunc(j)
			junctionPointFunctions.put(j, f)
		}
	}

	/**
     * Generates the function corresponding to a specific junction point.
     */
	def generateJunctionFunc(
		Pseudostate p
	) {
		val funcName = GlobalConstants.JUNCTION_FUNC_PREFIX + p.name
		val funcDecl = clazz.createOperation(funcName, null)
		val funcDeclOB = clazz.createOpaqueBehavior(funcDecl)
		funcDecl.createRTMessageParam(GlobalConstants.JUNC_FUNC_PARAM)
		val t = p.outgoings.get(0) // There should be only one.
		var code = ""
		if (t != null) {
			val actFunc = actionChainFunctions.get(t)
			code += call(actFunc, GlobalConstants.JUNC_FUNC_PARAM)
		}
		code += getDestination(t, false)
		funcDeclOB.set(code)
		funcDeclOB
	}

	/**
     * Generates all functions corresponding to states.
     */
	def generateAllStateFunctions() {
		for (s : machine.states) {
			val f = generateStateFunc(s)
			stateFunctions.put(s, f)
		}
	}

	/**
     * Generates the function corresponding to a given state.
     */
	def generateStateFunc(State state) {
		val name = GlobalConstants.STATE_FUNC_PREFIX + state.name
		val retType = statesDeclaration
		val func = clazz.createOperation(name, retType)
		val param = func.createRTMessageParam(GlobalConstants.STATE_FUNC_PARAM)
		val funcOB = clazz.createOpaqueBehavior(func)
		val table = getPortTransitionsTable(state)

		val code = '''
			switch («getPortCond(param)») {
				«FOR port : table.keySet»
				case «enumeratorFor(port)»:
					switch(«getSigCond(param)») {
					«FOR t : table.get(port)»
					«FOR trigger : t.triggers.filter[it.ports.contains(port)]»
						case «enumeratorFor(trigger.event as CallEvent)»:
							«IF (t.guard != null)»if (t.guard) { «ENDIF»
							«actionChainFunctions.get(t).name»(«param.name»);
							return «getDestination(t, false)»;
							«IF (t.guard != null)»}«ENDIF»
					«ENDFOR»
					«ENDFOR»
					}
				«ENDFOR»
			}
		'''
		funcOB.set(code)
		funcOB
	}

	/**
     * Builds a table that contains for each port, all the outgoing transitions
     * of a given state whose trigger has that port.
     *
     * @param s     a {@link State}
     * @return a table T indexed by {@link Port}s such that for each port p, the
     *         entry T[p] contains all outgoing {@link Transition}s from s
     *         whose trigger has port p.
     */
	private def getPortTransitionsTable(State s) {
		val table = newHashMap
		for (t : s.outgoings) {
			for (trigger : t.triggers) {
				for (p : trigger.ports) {
					if (!table.containsKey(p)) {
						table.put(p, newHashSet)
					}
					table.get(p).add(t)
				}
			}
		}
		return table
	}

	/**
     * Obtains the function call corresponding to a transition's destination.
     *
     * @param t - The {@link Transition}
     * @param init - Whether we are looking for the destination to be obtained
     *               in the ized method or in the inject method. 
     */
	private def getDestination(Transition t, boolean init) {
		var String retVal
		if (t.target instanceof Pseudostate) {
			val ps = t.target as Pseudostate
			if (ps.kind == PseudostateKind.CHOICE_LITERAL) {
				val func = choicePointFunctions.get(ps)
				retVal = func.getName() + "();"
			} else if (ps.kind == PseudostateKind.JUNCTION_LITERAL) {
				val func = junctionPointFunctions.get(ps)
				retVal = func.getName() + "();"
			}
		} else {
			retVal = stateEnumerators.get(t.target).name
		}
		if (init) {
			// currentStateField = << code >>
			retVal = '''«currentStateField.name» = «retVal»;'''
		}
		retVal
	}

	/**
     * Generates the main 'inject' function that receives and handles events.
     */
	def generateInjectFunc() {
		injectFunc = clazz.createOperation(GlobalConstants.INJECT_FUNC_NAME, null)

		// injectFunc.setVirtual()
		injectFunc.createRTMessageParam(GlobalConstants.INJECT_FUNC_PARAM)
		val injectFuncOB = clazz.createOpaqueBehavior(injectFunc)
		val code = '''
			switch(stateCond) {
			«FOR s : machine.states»
				case «enumFor(s)»:
					«currentStateField.name» = «call(stateFunctions.get(s), GlobalConstants.INJECT_FUNC_PARAM)»
					break;
			«ENDFOR»
			}
		'''
		injectFuncOB.set(code)
		injectFunc
	}


	/**
     * Build the initialize function which performs the initial transition.
     *
     * <p>This assumes that the top level of the state machine must have an
     * initial pseudo-state, and that there is exactly one outgoing transition
     * from such initial point.
     *
     * <p> If there is no initial point, the body of the initialize method is
     * empty.
     */
	def generateInitializeFunc() {
		initializeFunc = clazz.createOperation(GlobalConstants.INITIALIZE_FUNC_NAME, null)

		// initializeFunc.setVirtual()
		initializeFunc.createRTMessageParam(GlobalConstants.INITIALIZE_FUNC_PARAM)
		val initializeFuncOB = clazz.createOpaqueBehavior(initializeFunc)

		var code = ""
		if (machine.region.initialState != null) {
			val initialTransition = machine.region.initialState.outgoings.get(0);
			if (initialTransition != null) {
				val actFunc = actionChainFunctions.get(initialTransition)
				if (actFunc != null) {
					code += call(actFunc, GlobalConstants.INITIALIZE_FUNC_PARAM)
				}
			}
			code += getDestination(initialTransition, true)
			initializeFuncOB.set(code)
		}
	}

	def call(NamedElement fct, String param) '''
		«fct.name»(«param»);
	'''

	def call(NamedElement fct, List<String> params) '''
		«IF params != null»
			«fct.name»(«FOR param : params SEPARATOR ', '»«param»«ENDFOR»);
		«ELSE»
			«fct.name»();
		«ENDIF»
	'''
	
	/**
     * Generates the compilation unit for the state machine (*)
     *
     * <p><b>Notes:</b> This implementation generates only a list of elements
     * to be consumed by the capsule generator which is assumed to be
     * responsible for putting together the full compilation unit.
     */
	/*
	def generateCompilationUnit() {
		// already added?

		cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, statesDeclaration);
		cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, currentStateField)
		cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, historyTableDeclaration)
		cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, saveHistoryFunction)
		for (action : userActionFunctions.keySet) {
			cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, userActionFunctions.get(action))
		}
		for (chain : actionChainFunctions.keySet) {
			cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, actionChainFunctions.get(chain))
		}
		for (junction : junctionPointFunctions.keySet) {
			cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, junctionPointFunctions.get(junction))
		}
		for (choice : choicePointFunctions.keySet) {
			cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, choicePointFunctions.get(choice))
		}
		for (state : stateFunctions.keySet) {
			cppCapsuleClass.addMember(CppClass.Visibility.PRIVATE, stateFunctions.get(state))
		}
		
		return generatedModel
	}
	*/
	/**
     * Auxiliary methods
     */
	private def getStateType() {
		statesDeclaration
	}

	private def getRTMessageType() {
		// TODO: import umlrts instead of declaring it in model
		val umlrts = clazz.getModel().getPackagedElement("umlrts") as org.eclipse.uml2.uml.Package
		umlrts.getPackagedElement("RTMessage") as org.eclipse.uml2.uml.Type;
	}

	private def createRTMessageParam(Operation operation, String paramName) {
		val param = operation.createOwnedParameter(paramName, RTMessageType)
		if (refStereo == null) {
			refStereo = param.getApplicableStereotype("C_Cpp::Ref")
		}
		if (refStereo != null) {
			param.applyStereotype(refStereo)
		}
		if (constStereo == null) {
			constStereo = param.getApplicableStereotype("C_Cpp::Const")
		}
		if (constStereo != null) {
			param.applyStereotype(constStereo)
		}
		param
	}
		
	// TODO - why not used any more?
	private def getRTDataType(Transition t) {
		var Type rtdataType = null
		val Collection<Type> types = newHashSet
		for (trigger : t.triggers) {
			val evt = trigger.event as CallEvent
			val params = evt.operation.ownedParameters
			if (! params.empty) {
				val first = params.get(0)
				types.add(first.type)
			}
		}
		if (types.size == 1) {
			val type = types.get(0)
			// TODO - was - createType
			rtdataType = type
		}
		rtdataType
	}

	private def enumFor(State s) {
		stateEnumerators.get(s).label
	}

	private def dispatch enumeratorFor(Port port) {
		val enumerator = EnumService.literal('PortID', port.name)
		enumerator
	}

	private def dispatch enumeratorFor(CallEvent ce) {
		val operation = ce.operation
		val protocolCls = EnumService.literal('ProtocolClass', (operation.getInterface().owner as NamedElement).name);
		'''«protocolCls»::signal_«ce.name»'''
	}

	/**
     * Builds an expression to obtain the port enum id for the switch.
     *
     * <p>It assumes that the message parameter to the inject function is a
     * pointer to the RTMessage type, as returned by {@link #getRTMessageType},
     * this is, the signature of the inject function must be:
     *
     * <p><pre>
     * <code>void inject(UMLRTMessage * msg)</code>
     * </pre>
     *
     * <p>It also assumes that the port id and signal id are accessible from
     * this type. Basically the assumption is that the relevant definitions are
     * as follows:
     *
     * <p>
     * <pre>
     * <code>
     * class UMLRTMessage : ... {
     * public:
     *     UMLRTPort * destPort;
     *     UMLRTSignal * signal;
     * }
     *
     * struct UMLRTPort {
     *     int id;
     *     // ...
     * }
     *
     * class UMLRTSignal {
     * public:
     *     int id;
     *     // ...
     * }
     * </code>
     * </pre>
     *
     * <p>... where the typed <code>UMLRTPortId</code> and
     * <code>UMLRTSignalId</code> can be cast to the <code>Port</code> and
     * <code>Signal</code> enum types generated in the state machine's class.
     *
     * <p>Given this assumptions, the port condition generated has the form:
     *
     * <p><pre><code>(Port)(msg->destPort)->id</code></pre>
     *
     * <p>and the signal condition is:
     *
     * <p><pre><code>(ProtocolX::Signal)(msg->signal)->getId()</code></pre>
     *
     * <p>where <code>ProtocolX</code> is the name of the port's protocol
     */
	private def getPortCond(Parameter param) {
		//val messagePortField = UMLRTMessage.destPort()
		//val portIdField = UMLRTCommsPort.id()
		//'''«param.name».«messagePortField.name»->«portIdField.name»'''
		'''TODO'''
	}

	private def getSigCond(Parameter param) {
		//val messageSignalField = UMLRTMessage.signal()
		//val signalIdAccessor = UMLRTSignal.getId()
		//'''«param.name»->«messageSignalField.name»->«signalIdAccessor.name»()'''
		'''TODO'''
	}
}
