package org.eclipse.papyrus.designer.components.modellibs.core.xtend

import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr
import org.eclipse.papyrus.designer.components.FCM.DerivedElement
import org.eclipse.papyrus.designer.components.transformation.core.StUtils
import org.eclipse.papyrus.designer.components.transformation.core.Utils
import org.eclipse.papyrus.designer.components.transformation.core.UMLTool
import org.eclipse.papyrus.designer.components.transformation.core.extensions.IXtend
import org.eclipse.papyrus.designer.components.transformation.core.sync.InterfaceSync
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationContext
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.Behavior
import org.eclipse.uml2.uml.BehavioredClassifier
import org.eclipse.uml2.uml.CallEvent
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.OpaqueExpression
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.PseudostateKind
import org.eclipse.uml2.uml.Reception
import org.eclipse.uml2.uml.Signal
import org.eclipse.uml2.uml.SignalEvent
import org.eclipse.uml2.uml.State
import org.eclipse.uml2.uml.StateMachine
import org.eclipse.uml2.uml.TimeEvent
import org.eclipse.uml2.uml.Transition
import org.eclipse.uml2.uml.Trigger
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.ValueSpecification
import org.eclipse.uml2.uml.Vertex
import org.eclipse.uml2.uml.util.UMLUtil

import static org.eclipse.papyrus.designer.components.transformation.core.EnumService.*
import static org.eclipse.papyrus.designer.components.vsl.ParseVSL.*

import static extension org.eclipse.papyrus.designer.components.transformation.core.UMLTool.*
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.BehaviorUtil.*
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.CppUtils.cppCall
import static extension org.eclipse.papyrus.designer.components.modellibs.core.xtend.StateMachineUtil.*
import org.eclipse.papyrus.uml.tools.utils.PackageUtil
import org.eclipse.papyrus.designer.components.modellibs.core.Activator
import org.eclipse.papyrus.designer.components.transformation.core.transformations.filters.FilterStateMachines

class StateMachineGen implements IXtend {

	Class clazz
	
	boolean ooPattern;
	
	String executorRef
	
	String sigParamName

	static final int SIG_INITIAL = 1000

	def getStateMachine(Class clazz) {
		// organized in a loop. But effectively supports single state machine
		for (smBehavior : (clazz as BehavioredClassifier).ownedBehaviors.filter[it instanceof StateMachine]) {
			return smBehavior as StateMachine
		}
		return null
	}
	
	def void init(boolean ooPattern) {
		this.ooPattern = ooPattern
		if (ooPattern) {
			executorRef = ""
			sigParamName = "params"
		}
		else {
			executorRef = "executor->"
			sigParamName = "signal"
		}
	}
	
	def activate(Class clazz) {
		val sm = clazz.stateMachine
		val flattener = new UMLFlattener
		init(false)
		flattener.transform(sm)
		activate(clazz, sm)
	}

	def activateOO(Class clazz) {
		// caveat: order is important. Make sure that this is the first operation in the template definition
		// within the model  (must be flattened once)
		val sm = clazz.stateMachine
		val flattener = new UMLFlattener
		init(true)
		flattener.transform(sm)
		activate(clazz, sm)
	}
	
	def activate(Class clazz, StateMachine sm) '''
		m_currentState = STATE_«sm.region.initialState.name»;
#ifdef SM_VERBOSE
		cout << "SM «clazz.name»: in state «sm.region.initialState.name»" << endl;
#endif
		for (;;) {
			processEvents();
		}
	'''
	
	def processEventsOO(Class clazz) {
		init(true)
		processEventsCommon(clazz)
	}
	
	def processEvents(Class clazz) {
		init(false)
		processEventsCommon(clazz)
	}
	
	def processEventsCommon(Class clazz) {
		val sm = clazz.stateMachine
		// - Option to put processElements into original class (but, would need to copy dependencies & attributes)
		// create new operation in class owning the state machine.
		// val operation = clazz.createOperation("processEvents", null)
		// val ob = clazz.createOpaqueBehavior(operation)
		// ob.set(clazz.processEventsSM(sm).toString)
		// return "«executor»processEvents();"
		this.clazz = clazz
		return clazz.processEventsSM(sm)
	}

	def eventInterceptor(Operation operation) '''
		«/*TODO: need better way to detect signal*/»
		«operation.eventInterceptorCommon» 
 		event.portID = portID;
		out->writeEvent(event);

		«IF (operation.type != null)»return «ENDIF»rconn->«operation.cppCall»;
	'''

	/**
	 * OO variant of event interceptor. Adds call event to pool and
	 * then calls the original method (handled by LW container)
	 */
	def eventInterceptorOO(Operation operation) '''
		«init(true)»
		«operation.eventInterceptorCommon» 
	'''

	def eventInterceptorCommon(Operation operation) '''
		«val derivedElement = UMLUtil.getStereotypeApplication(operation, DerivedElement)»
		«IF (derivedElement != null) && (derivedElement.source instanceof Reception)»
			«val signal = (derivedElement.source as Reception).signal»
			// create event with global signal ID
			core::ContainerServices::CallEvent_ event;
			event.operationID = «literal(SIGNAL_ENUM, InterfaceSync.SIG_PREFIX + signal.name, SIG_INITIAL)»;
			// map signal into value-buffer and copy attributes
			::«signal.qualifiedName» * signal = (::«signal.qualifiedName» *) &event.params;
			«FOR attribute : signal.ownedAttributes»
				signal->«attribute.name» = «attribute.name»;
			«ENDFOR»
			«PackageUtil.getRootPackage(operation).declareDependencyToSignalIDs»
			«UMLTool.declareDependency(TransformationContext.classifier, signal)»
		«ELSE»
			// create event with operationID/portID and pass call
			core::ContainerServices::CallEvent_ event;
			«IF ooPattern»
				event.operationID = «literal("LOperationIDs", '''OP_ID_«operation.name»''')»;
			«ELSE»
				event.operationID = ID_«operation.name»;
			«ENDIF»
		«ENDIF»
		eventPool.writeEvent(event);
	'''
	
	def processEventsSM(BehavioredClassifier clazz, StateMachine sm) '''
		// processEvents body - generated by Qompass 
		//
		// supports ports «FOR port : (clazz as Class).ownedPorts» «literal('PortEnum_'+clazz.name, 'port_'+clazz.name+'_'+port.name)»«ENDFOR»

		core::ContainerServices::CallEvent_ event;
		int timeout;
		int newState;
		bool needsTrigger;

		switch(m_currentState) {
			«FOR state : sm.region.subvertices»
				case «literal('LStateIDs_'+clazz.name, 'STATE_'+state.name)»:
					// -------- treatment of accepted events
					«state.acceptableEvents»
					break;
			«ENDFOR»
			default:
				OSAL_ERROR ("Inconsistent state");
			break;
		}
		«IF (!ooPattern)»
			if (animOut != 0) {
				animOut->enterState(newState, «clazz.fragment»);
			}
		«ENDIF»
	'''

	/*
	 * Pass the actual to which the port is bound. In case of a CallEvent, pass the implemented interface
	 * (Not the class to which the state machine is bound). This is useful in the context of components
	 * whose ports implement an interface
	 */
	def cetrigger(Operation operation) {
		val intf = operation.implementsInterface
		if (intf != null) {
			val packageRef = intf.boundPackageRef
			// declare dependency to OperationIDs enumeration
			packageRef.declareDependencyToOperationIDs
			'''«packageRef.qualifiedName»::ID_«operation.name»'''
		}
		else {
			// create operationIDs literal
			literal("LOperationIDs", '''OP_ID_«operation.name»''')
		}
	}

	// Use service for global enumerations
	def setrigger(Trigger trigger) {
		val se = trigger.event as SignalEvent
		literal(SIGNAL_ENUM, InterfaceSync.SIG_PREFIX + se.signal.name, SIG_INITIAL)
	}

	/**
	 * create code for acceptable events
	 * 
	 * big restriction: will only analyse first of possibly multiple triggers
	 */
	def acceptableEvents(Vertex state) '''
		// loop on state
		// execute action ...
		timeout = -1;	// no timeout by default
		«FOR transition : state.outgoings»
			«IF transition.triggers.size > 0»
				«val trigger = transition.triggers.get(0)»
				«IF (trigger.event instanceof TimeEvent)»
					«val timeEvent = trigger.event as TimeEvent»
					// transition «transition.name» - trigger: TimeEvent, expression «(timeEvent.when.expr as OpaqueExpression).bodies.get(0)».
					timeout = «getDurationFromVSL((timeEvent.when.expr as OpaqueExpression).bodies.get(0))»/1000;
				«ENDIF»
			«ENDIF»
		«ENDFOR»

		«IF ((state instanceof State) && (state as State).entry != null)»
			// execute entry action
			«executorRef»«(state as State).entry.name»();
		«ENDIF»

		needsTrigger = true;

		«IF hasTransitionWithoutTrigger(state)»
			«FOR transition : state.outgoings»
				«IF transition.triggers.size == 0»
					«IF transition.guard != null»
						«transition.guard.specification.createGuardFct(null)»
						if («executorRef»«transition.guard.specification.name»()) {
					«ENDIF»
					newState = STATE_«transition.target.name»;
#ifdef SM_VERBOSE
						cout << "SM «clazz.name»: transition to state «transition.target.name»" << endl;
#endif
					«IF (transition.effect != null)»
						«executorRef»«effectName(transition)»();
					«ENDIF»
						needsTrigger = false;
					«IF transition.guard != null»
						}
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		«ENDIF»

		if (needsTrigger) {
			// get an event from the pool.
			«IF ooPattern»
				event = eventPool.readEvent(timeout);
			«ELSE»
				event = ep->readEvent(timeout);
			«ENDIF»
		}
		else {
			event.operationID = -1;
		}
		
		«val allOutgoings = state.outgoings»
		«FOR transition : allOutgoings»
			«IF transition.triggers.size > 0»
				// has «transition.triggers.size» outgoing transitions
				«val trigger = transition.triggers.get(0)»
				«IF (trigger.event instanceof TimeEvent)»
					// transition «transition.name» - trigger: TimeEvent (there should be at most one outgoing timed transition per state).
					if (event.operationID == core::ContainerServices::EventPool::ID_TIMEOUT) {
						«IF transition.guard != null»
							«transition.guard.specification.createGuardFct(null)»
							if («executorRef»«transition.guard.specification.name»()) {
						«ENDIF»
						newState = STATE_«transition.target.name»;
#ifdef SM_VERBOSE
						cout << "SM «clazz.name»: transition to state «transition.target.name»" << endl;
#endif
						«IF (transition.effect != null)»
							«executorRef»«effectName(transition)»();
						«ENDIF»
						«IF transition.guard != null»
							}
						«ENDIF»
					}
				«ELSEIF (trigger.event instanceof CallEvent)» 
					// transition «trigger.name» - trigger: CallEvent («trigger.event.name»), operation «(trigger.event as CallEvent).operation.name»
					if (event.operationID == «cetrigger((trigger.event as CallEvent).operation)») {
						newState = STATE_«transition.target.name»;
#ifdef SM_VERBOSE
						cout << "SM «clazz.name»: transition to state «transition.target.name»" << endl;
#endif
						«IF (transition.effect != null)»
							«executorRef»«effectName(transition)»();
						«ENDIF»
					} 
				«ENDIF»
				«IF (trigger.event instanceof SignalEvent)» 
					«val signalEvent = trigger.event as SignalEvent»
					// transition «trigger.name» - trigger: SignalEvent («signalEvent.name»), signal «signalEvent.signal.name»
					if (event.operationID == «setrigger(trigger)») {
						«IF (transition.effect != null) || (transition.guard != null)»
							// map signal to parameter section
							::«signalEvent.signal.qualifiedName» * signal = (::«signalEvent.signal.qualifiedName» *) &event.params;
						«ENDIF»
						«IF transition.guard != null»
							«transition.guard.specification.createGuardFct(signalEvent)»
							if («executorRef»«transition.guard.specification.name»(signal)) {
						«ENDIF»
						newState = STATE_«transition.target.name»;
#ifdef SM_VERBOSE
						cout << "SM «clazz.name»: transition to state «transition.target.name» (due to signal «signalEvent.signal.name»)" << endl;
#endif
						«IF (transition.effect != null)»
							«transition.effect.addSignalParameter(signalEvent.signal)»
							«executorRef»«effectName(transition)»(«IF signalEvent.signal.attributes.size > 0»signal«ENDIF»);
						«ENDIF»
						// ok = EvQUEUE ;
						«IF transition.guard != null»
							}
						«ENDIF»
					}
				«ENDIF»
			«ENDIF»
		«ENDFOR»
	
		if (newState != m_currentState) {
			m_currentState = newState;
			«IF ((state instanceof State) && (state as State).exit != null)»
			// execute exit action
			«executorRef»«(state as State).exit.name»();
		«ENDIF»

			
		}
	'''
	
	def boolean hasTransitionWithoutTrigger(Vertex state) {
		for (transition : state.outgoings) {
			if (transition.triggers.size == 0) {
				return true;
			}
		}
		return false;		
	}
	
	def EList<Transition> calculateTransitions(Vertex state) {
		val allOutgoings = new BasicEList<Transition>
		allOutgoings.addAll(state.outgoings)
		for (transition : state.outgoings) {
			if (transition.target instanceof Pseudostate) {
				val ps = transition.target as Pseudostate
				if (ps.kind == PseudostateKind.JUNCTION_LITERAL) {
					allOutgoings.addAll(ps.outgoings.clone)
				}
			}
		}
		return allOutgoings
	}
	
	/**
	 * Effects are moved from behaviors embedded into transitions towards behaviors of the class. Calculate the name of these
	 * effects. The class FilterStateMachines moves the effects (TODO: single name calculation)
	 */
	def effectName(Transition transition) {
		if (transition.effect.name == null) {
			throw new TransformationException(
				String.format("effect of transition has no name (in SM %s)", transition.containingStateMachine.name))
		}
		FilterStateMachines.newBehaviorName(transition)
	}
	
	def void addSignalParameter(Behavior behavior, Signal signal) {
		if (behavior.ownedParameters.size == 0) {
			val parameter = behavior.createOwnedParameter(sigParamName, signal)
			StereotypeUtil.apply(parameter, Ptr)
			if (ooPattern) {
				// create dependency between class hosting stereotype and signal (used by C++ code generators to add proper code)
				clazz.createDependency(signal)
			}
		}
	}

	def void moveBehavior(String newName, Class tmClass, Behavior effect) {
		val copiedEffect = EcoreUtil.copy(effect)
		if (tmClass.getOwnedOperation(newName, null, null) != null) {
			// has already been added
			return;
		}
		val operation = tmClass.createOwnedOperation(newName, null, null);
		for (parameter : effect.getOwnedParameters()) {
			val newParameter = EcoreUtil.copy(parameter);
			operation.getOwnedParameters().add(newParameter);
			StUtils.copyStereotypes(parameter, newParameter);
		}
		copiedEffect.setSpecification(operation);
		copiedEffect.setName(newName);
		tmClass.getOwnedBehaviors().add(copiedEffect);
	}

	static final String CLIB_BOOL = "AnsiCLibrary::bool"

	def void createGuardFct(ValueSpecification specification, SignalEvent event) {
		val name = specification.getName()
		
		if (clazz.getOwnedBehavior(name) != null) {
			return
		}
		var booleanNamedElement = Utils.getQualifiedElement(TransformationContext.sourceRoot, CLIB_BOOL);
		
		if (booleanNamedElement instanceof Type) {
			val booleanType = TransformationContext.copier.getCopy(booleanNamedElement) as Type
			val operation = clazz.createOperation(name, booleanType as Type)
			if (event != null) {
				val sigParam = operation.createOwnedParameter("signal", event.signal)
				StereotypeUtil.apply(sigParam, Ptr)
			}
			val ob = clazz.createOpaqueBehavior(operation)
			if (specification instanceof OpaqueExpression) {
				ob.getLanguages().addAll(specification.getLanguages());
				for (String body : specification.getBodies()) {
					ob.getBodies().add("return " + body + ";");
				}
			}
		}
		else {
			Activator.log.debug("error, expecting a guard typed with a boolean value");
		}
	}
}