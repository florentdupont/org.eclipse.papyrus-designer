package org.eclipse.papyrus.designer.components.modellibs.core.statemachine

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Map.Entry
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.common.util.UniqueEList
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.papyrus.designer.components.FCM.DerivedElement
import org.eclipse.papyrus.designer.transformation.core.OperationUtils
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier
import org.eclipse.papyrus.designer.components.vsl.ParseVSL
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Typedef
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Virtual
import org.eclipse.papyrus.uml.tools.utils.PackageUtil
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.AnyReceiveEvent
import org.eclipse.uml2.uml.Behavior
import org.eclipse.uml2.uml.CallEvent
import org.eclipse.uml2.uml.ChangeEvent
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.Event
import org.eclipse.uml2.uml.FinalState
import org.eclipse.uml2.uml.Model
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.OpaqueExpression
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.PseudostateKind
import org.eclipse.uml2.uml.Region
import org.eclipse.uml2.uml.SignalEvent
import org.eclipse.uml2.uml.State
import org.eclipse.uml2.uml.StateMachine
import org.eclipse.uml2.uml.TimeEvent
import org.eclipse.uml2.uml.Transition
import org.eclipse.uml2.uml.Trigger
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.Vertex
import org.eclipse.uml2.uml.util.UMLUtil

import static org.eclipse.papyrus.designer.components.modellibs.core.statemachine.SMCodeGeneratorConstants.*

import static extension org.eclipse.papyrus.designer.components.modellibs.core.statemachine.TransformationUtil.eventID
import org.eclipse.uml2.uml.Profile
import org.eclipse.uml2.uml.Element
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen

class SM2ClassesTransformationCore {
	protected extension CDefinitions cdefs;
	public int MAX_NUMBER_ORTHOGONAL_REGIONS = 1
	private Class mContainerClass
	private StateMachine sm
	public Type boolType
	public Class superContext
	private List<Transition> transitions = new ArrayList
	private List<OpaqueBehavior> actions = new ArrayList
	public Region topRegion
	public List<State> states = new ArrayList
	private List<Vertex> vertexes = new ArrayList
	public LazyCopier copier
	private String langID = "C++"
	private Type stateStruct

	public boolean generateActionLog = false
	private boolean createDoActivity = true
	private boolean useThreadCpp11 = true
	Type threadCpp11
	public Type voidType
	private Enumeration stateIdEnum
	public Type intType
	public Type charType
	Enumeration eventIdEnum
	public Type fptr
	public PThreadTypes ptTypes
	public Type threadStructType
	
	public List<TimeEvent> timeEvents = new ArrayList
	List<ChangeEvent> changeEvents = new ArrayList
	List<CallEvent> callEvents = new ArrayList
	List<SignalEvent> signalEvents = new ArrayList
	List<AnyReceiveEvent> anyEvents = new ArrayList
	List<Pseudostate> junctions = new ArrayList
	public ConcurrencyGenerator concurrency
	public EventTransformation eventTransform
	public PseudostateGenerator pseudostateGenerator
	public static String ansiUri = "pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml"

	public static String smLibraryUri = "platform:/resource/org.eclipse.papyrus.designer.codegen.statemachine/models/SmLibrary.uml"
	public List<Behavior> doActivityList = new ArrayList
	public Package smPack
	private List<Region> regions = new ArrayList
	public List<Transition> parallelTransitions = new ArrayList
	public Map<State, List<TimeEvent>> states2TimeEvents = new HashMap

	new(LazyCopier copier, Class smClass, StateMachine sm, Class tmClass) {
		this.copier = copier
		this.mContainerClass = smClass
		this.superContext = tmClass
		
		// perClassPackage = copier.target.createNestedPackage("PerClass_" + mContainerClass.name)
		// perClassPackage = copier.target
		this.sm = sm
		
		val ResourceSet resourceSet = targetPacket.eResource.resourceSet
		boolType = getPrimitiveType("bool", resourceSet)
		voidType = getPrimitiveType("void", resourceSet)
		intType = getPrimitiveType("int", resourceSet)
		charType = getPrimitiveType("char", resourceSet)
		ptTypes = new PThreadTypes(targetPacket as Model)
		this.cdefs = new CDefinitions(superContext)
	}
	
	def getPrimitiveType(String name, ResourceSet resourceSet) {
		val Package ansiCLibrary = PackageUtil.loadPackage(URI.createURI("pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml"), resourceSet)
		val Element element = ansiCLibrary.getPackagedElement(name)
		if (element instanceof Type) {
			return element
		}
		return null
	}
	
	def getTargetPacket() {
		copier.target;
	}
	
	def getExternalPackage(Package parentPack) {
		if (parentPack.getNestedPackage("external") == null) {
			var createdPack = parentPack.createNestedPackage("external")
			StereotypeUtil.apply(createdPack, NoCodeGen)
		}
		return parentPack.getNestedPackage("external")
	}
	
	// each state class has a super context and ancestor context
	def transform() {
		val targetPack = getTargetPacket;
		// copier = new SM2ClassCopier(mContainerClass.model, targetPack, false, true, mContainerClass, superContext, sm)
		val ResourceSet resourceSet = targetPack.eResource.resourceSet
		
		PackageUtil.loadPackage(URI.createURI("pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml"), resourceSet)
		val Package profile = PackageUtil.loadPackage(URI.createURI("pathmap://UML_PROFILES/Standard.profile.uml"), resourceSet)
		if (profile instanceof Profile) {
			PackageUtil.applyProfile(targetPack, profile as Profile, true)
		}
		
		if (useThreadCpp11) {
			var externalPackage = getExternalPackage(targetPack)
			threadCpp11 = externalPackage.createOwnedType("std::thread", UMLPackage.Literals.DATA_TYPE)
			StereotypeUtil.apply(threadCpp11, External)
		}
		topRegion = sm.regions.head
		concurrency = new ConcurrencyGenerator(this)
		eventTransform = new EventTransformation(this)
		pseudostateGenerator = new PseudostateGenerator(this)
		
		var eventMap = topRegion.allEvents
		eventMap.forEach[e, k|
			if (e instanceof TimeEvent) {
				timeEvents.add(e)
			} else if (e instanceof CallEvent) {
				callEvents.add(e)
			} else if (e instanceof SignalEvent) {
				signalEvents.add(e)
			} else if (e instanceof ChangeEvent) {
				changeEvents.add(e)
			} else if (e instanceof AnyReceiveEvent) {
				anyEvents.add(e)
			}
		]
		eventIdEnum = superContext.createNestedClassifier(EVENT_ID, UMLPackage.Literals.ENUMERATION) as Enumeration

		appendIncludeHeader('''#define «TIME_EVENT_LOWER_BOUND» (0)''')
		timeEvents.forEach[
			eventIdEnum.createOwnedLiteral(it.eventID)
		]		
		changeEvents.forEach[
			eventIdEnum.createOwnedLiteral(it.eventID)
		]			
		appendIncludeHeader('''#define «CHANGE_EVENT_LOWER_BOUND» («timeEvents.size»)''')
		
		appendIncludeHeader('''#define «TE_INDEX»(id) (id - «TIME_EVENT_LOWER_BOUND»)''')
		appendIncludeHeader('''#define «CHE_INDEX»(id) (id - «CHANGE_EVENT_LOWER_BOUND»)''')
		
		signalEvents.forEach[
			eventIdEnum.createOwnedLiteral(it.eventID)
		]	
		callEvents.forEach[
			eventIdEnum.createOwnedLiteral(it.eventID)
		]		
		anyEvents.forEach[
			eventIdEnum.createOwnedLiteral(it.eventID)
		]	
		
		eventIdEnum.createOwnedLiteral(COMPLETION_EVENT.toUpperCase + "_ID")
		
		topRegion.allActionsTransitionsStates
		states.filter[it.orthogonal].forEach[
			if (it.regions.size > MAX_NUMBER_ORTHOGONAL_REGIONS) {
				MAX_NUMBER_ORTHOGONAL_REGIONS = it.regions.size 
			}
		]
		
		states.forEach[
			val triggers = new ArrayList<Trigger>
			it.outgoings.forEach[
				triggers.addAll(it.triggers)
			]
			val events = triggers.map[it.event]
			val timeEvents = events.filter(TimeEvent).toList
			states2TimeEvents.put(it, timeEvents)
		]
		
		vertexes.filter(Pseudostate).forEach[
			if (it.kind == PseudostateKind.JUNCTION_LITERAL) {
				junctions.add(it)
			}
		]
		
		//for each junction, create a class variable
		junctions.forEach[
			superContext.createOwnedAttribute(it.name, intType)
		]
		
		//create state ID enumeration
		//val stateIdEnum = targetPack.createOwnedEnumeration("StateIDEnum") as Enumeration
		stateIdEnum = superContext.createNestedClassifier("StateIDEnum", UMLPackage.Literals.ENUMERATION) as Enumeration
		
		//create state struct
		createStateStruct		

		var stateArrayAttr = superContext.createOwnedAttribute(STATE_ARRAY_ATTRIBUTE, stateStruct)
		StereotypeUtil.apply(stateArrayAttr, Array)
		//StereotypeUtil.apply(stateArrayAttr, Ptr)
		UMLUtil.getStereotypeApplication(stateArrayAttr, Array).definition = '''[«states.size»]'''
		//create root active state ID
		superContext.createOwnedAttribute(ACTIVE_ROOT_STATE_ID, stateIdEnum)
		
		createRegionMethods
				
		//create constructor
		var ctor = superContext.createOwnedOperation(superContext.name, null, null)
		superContext.createOpaqueBehavior(ctor, '''
		«SYSTEM_STATE_ATTR» = statemachine::IDLE;
		«FOR s:states»
			«IF s.entry != null»
				«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«ENTRY_NAME» = &«superContext.name»::«s.name + "_" + ENTRY_NAME»;
			«ENDIF»
			«IF s.exit != null»
				«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«EXIT_NAME» = &«superContext.name»::«s.name + "_" + EXIT_NAME»;
			«ENDIF»
			«IF s.doActivity != null»
				«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«DO_ACTIVITY_NAME» = &«superContext.name»::«s.name + "_" + DO_ACTIVITY_NAME»;
			«ENDIF»		
			«DO_ACTIVITY_TABLE»[«s.name.toUpperCase»_ID] =  «STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID].«DO_ACTIVITY_NAME»;
		«ENDFOR»
		
		// initialize all threads, the threads wait until the associated flag is set
		for(int i = 0; i < (int) «STATE_MAX»; i++) {
			if («STATE_ARRAY_ATTRIBUTE»[i].«DO_ACTIVITY_NAME» != &«superContext.name»::doActivity_dft) {
				«THREAD_STRUCTS»[i].id = i;
				«THREAD_STRUCTS»[i].ptr = this;
				«THREAD_STRUCTS»[i].func_type = «THREAD_FUNC_DOACTIVITY_TYPE»;
				«MUTEXES»[i] = PTHREAD_MUTEX_INITIALIZER;
				«CONDITIONS»[i] = PTHREAD_COND_INITIALIZER;
				«FORK_NAME»(&«THREADS»[i], NULL, &«superContext.name»::«THREAD_FUNC_WRAPPER», &«THREAD_STRUCTS»[i]);
			}
		}
		
		«IF timeEvents.size > 0»
			«FOR e:timeEvents»
				«var duration = ParseVSL.getMsDurationFromVSL((e.when.expr as OpaqueExpression).bodies.get(0))»
				«THREAD_STRUCTS_FOR_TIMEEVENT»[«TE_INDEX»(«e.eventID»)].duration = «duration»;
			«ENDFOR»
			for(int i = «TIME_EVENT_LOWER_BOUND»; i < «timeEvents.size»; i++) {
				«THREAD_STRUCTS_FOR_TIMEEVENT»[«TE_INDEX»(i)].id = i;
				«THREAD_STRUCTS_FOR_TIMEEVENT»[«TE_INDEX»(i)].ptr = this;
				«THREAD_STRUCTS_FOR_TIMEEVENT»[«TE_INDEX»(i)].func_type = «THREAD_FUNC_TIMEEVENT_TYPE»;
				«MUTEXES_TIME_EVENT»[«TE_INDEX»(i)] = PTHREAD_MUTEX_INITIALIZER;
				«CONDITIONS_TIME_EVENT»[«TE_INDEX»(i)] = PTHREAD_COND_INITIALIZER;
				«FORK_NAME»(&«THREADS_TIME_EVENT»[«TE_INDEX»(i)], NULL, &«superContext.name»::«THREAD_FUNC_WRAPPER», &«THREAD_STRUCTS_FOR_TIMEEVENT»[«TE_INDEX»(i)]);
				while(«FLAGS_TIME_EVENT»[«TE_INDEX»(i)]) {}
			}
		«ENDIF»
		
		«IF !orthogonalRegions.empty»
			«FOR r:orthogonalRegions»
				«REGION_TABLE»[«r.regionMacroId»] = &«superContext.name»::«r.regionMethodName»;
				«REGION_TABLE_EXIT»[«r.regionMacroId»] = &«superContext.name»::«r.regionMethodExitName»;
			«ENDFOR»
		«ENDIF»
		
		«IF !parallelTransitions.empty»
			«FOR t:parallelTransitions»
				«PARALLEL_TRANSITION_TABLE»[«concurrency.parallelTransitionId(t)»] = &«superContext.name»::«concurrency.parallelTransitionMethodName(t)»;
			«ENDFOR»
		«ENDIF»
		//initialze root active state
		//execute initial effect
		«getRegionMethodName(topRegion)»(«topRegion.initialMacroName»);
		''')
		
		StereotypeUtil.apply(ctor, "StandardProfile::Create")
		
		eventMap.forEach[e, trans|
			eventTransform.createEventMethod(e, trans)
		]
		
		val autoTrans = new ArrayList<Transition>
		transitions.forEach[
			if (it.source instanceof State && it.triggers.map[it.event].size == 0) {
				autoTrans.add(it)
			}
		]
		
		eventTransform.createEventMethod(COMPLETION_EVENT, autoTrans)
		
		//create entry/exit/doactivity of each state
		states.forEach[
			stateIdEnum.createOwnedLiteral(it.name.toUpperCase + "_ID")
			if (it.entry != null && it.entry instanceof OpaqueBehavior) {
				var entry = superContext.createOwnedOperation(it.name + "_" + ENTRY_NAME, null, null)
				var opaque = superContext.createOpaqueBehavior(entry, (it.entry as OpaqueBehavior).bodies.head)
				opaque.languages.add(langID)
			}
			if (it.exit != null && it.exit instanceof OpaqueBehavior) {
				var exit = superContext.createOwnedOperation(it.name + "_" + EXIT_NAME, null, null)
				var opaque = superContext.createOpaqueBehavior(exit, (it.exit as OpaqueBehavior).bodies.head)
				opaque.languages.add(langID)
			}
			if (it.doActivity != null && it.doActivity instanceof OpaqueBehavior) {
				doActivityList.add(it.doActivity)
				var doActivity = superContext.createOwnedOperation(it.name + "_" + DO_ACTIVITY_NAME, null, null)
				var callCompletionEvent = ''''''
				if (!it.composite) {
					//callCompletionEvent = '''process«COMPLETION_EVENT»();'''
				}
				var opaque = superContext.createOpaqueBehavior(doActivity, (it.doActivity as OpaqueBehavior).bodies.head + "\n" + callCompletionEvent)
				opaque.languages.add(langID)
			} else {
//				var doActivity = superContext.createOwnedOperation(it.name + "_" + DO_ACTIVITY_NAME, null, null)
//				var callCompletionEvent = ''''''
//				if (!it.composite) {
//					//callCompletionEvent = '''process«COMPLETION_EVENT»();'''
//				}
//				var opaque = superContext.createOpaqueBehavior(doActivity, callCompletionEvent)
//				opaque.languages.add(langID)
			}
		]
		stateIdEnum.createOwnedLiteral(STATE_MAX)
		copier.copy(mContainerClass)
		concurrency.createThreadBasedParallelism
		
		appendIncludeHeader('''
		#define «THREAD_FUNC_STATE_MACHINE_TYPE» 6
		#define CHECKPOINT if («SYSTEM_STATE_ATTR» == statemachine::EVENT_PROCESSING) {return;}
		#define THREAD_CREATE(thThread, str) «FORK_NAME»(&thThread, NULL, &«superContext.name»::«THREAD_FUNC_WRAPPER», &str);''')
		
		concurrency.createConcurrencyForTransitions
	}
	
	
	
	def appendIncludeHeader(String appended) {
		if (UMLUtil.getStereotypeApplication(superContext, Include) == null) {
			StereotypeUtil.apply(superContext, Include)
		}
		var header = UMLUtil.getStereotypeApplication(superContext, Include).header
		header = '''
		«header»
		«appended»'''
		UMLUtil.getStereotypeApplication(superContext, Include).header = header
	}	
	
	
	
	private def createChangeEvents() {
		var copier = new EcoreUtil.Copier
		//copier.copy()
	}
	
	def String generateChangeState(State s) {
		if (s.container == topRegion) {
			return '''«ACTIVE_ROOT_STATE_ID» = «s.name.toUpperCase»_ID;'''
		}
		var rIndex = s.container.state.regions.indexOf(s.container)
		return '''«STATE_ARRAY_ATTRIBUTE»[«s.container.state.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«rIndex»] = «s.name.toUpperCase»_ID;'''
	}
	
	
	def String generateExitingSubStates(State parent, boolean exitParent) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«IF parent.orthogonal»
			//exiting concurrent state «parent.name»
			«FOR r:parent.regions»
				«concurrency.generateForkCall(r, false, "0")»
			«ENDFOR»
			«FOR r:parent.regions»
				«concurrency.generateJoinCall(r, false)»
			«ENDFOR»
		«ELSEIF parent.composite»
			«parent.regions.head.regionMethodExitName»();
		«ELSE»
		«ENDIF»
		«IF exitParent»
			//signal to exit the doActivity of «parent.name»
			«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», false);
			«parent.generateDeactivateTimeEvent»
			//exit action of «parent.name»
			«getFptrCall(pAttr, false, EXIT_NAME)»;
		«ENDIF»'''
	}
	
	public def generateActivateTimeEvent(State s) {
		return '''
		«FOR te:states2TimeEvents.get(s)»
			«SET_FLAG»(«te.eventID», «THREAD_FUNC_TIMEEVENT_TYPE», true);
		«ENDFOR»'''
	}
	
	def generateDeactivateTimeEvent(State s) {
		return '''
		«FOR te:states2TimeEvents.get(s)»
			«SET_FLAG»(«te.eventID», «THREAD_FUNC_TIMEEVENT_TYPE», false);
		«ENDFOR»'''
	}
	
	@Deprecated
	def String generateExitingSubStatesWithTransition(State parent, boolean exitParent, Transition t) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«IF parent.orthogonal»
		«FOR r:parent.regions»
			«FOR s:r.subvertices.filter(State).filter[!(it instanceof FinalState) && it.composite] SEPARATOR ' else '»
				if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] == &«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID]) {
					«generateExitingSubStates(s, true)»
				}
			«ENDFOR»
			if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] != NULL) {
				«FOR sub:r.subvertices.filter(State).filter[!(it instanceof FinalState)] SEPARATOR ' else '»
				if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] == &«STATE_ARRAY_ATTRIBUTE»[«sub.name.toUpperCase»_ID]) {
					«SET_FLAG»(«sub.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», false);	
				}	
				«ENDFOR»
				«getFptrCall(STATE_ARRAY_ATTRIBUTE+"["+parent.name.toUpperCase+"_ID]."+ACTIVE_SUB_STATES+"["+parent.regions.indexOf(r)+"]", true, EXIT_NAME)»;
			}
			«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] = NULL;
		«ENDFOR»
		«ELSEIF parent.composite»
			«FOR s:parent.regions.head.subvertices.filter(State).filter[!(it instanceof FinalState) && it.composite] SEPARATOR ' else '»
				if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] == &«STATE_ARRAY_ATTRIBUTE»[«s.name.toUpperCase»_ID]) {
					«generateExitingSubStates(s, true)»
				}
			«ENDFOR»
			if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] != NULL) {
				«FOR sub:parent.regions.head.subvertices.filter(State).filter[!(it instanceof FinalState)] SEPARATOR ' else '»
				if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] == &«STATE_ARRAY_ATTRIBUTE»[«sub.name.toUpperCase»_ID]) {
					«SET_FLAG»(«sub.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», false);	
				}	
				«ENDFOR»
				«getFptrCall(STATE_ARRAY_ATTRIBUTE+"["+parent.name.toUpperCase+"_ID]."+ACTIVE_SUB_STATES+"[0]", true, EXIT_NAME)»;
			}
			«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] = NULL;
		«ELSE»
		«ENDIF»
		«IF exitParent»
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», false);
		«getFptrCall(pAttr, false, EXIT_NAME)»;
		«ENDIF»
		«TransformationUtil.getTransitionEffect(t)»'''
	}
	
	@Deprecated
	def String generateEnteringSubStates(State parent, boolean enterParent) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«IF enterParent»
		«generateChangeState(parent)»
		«getFptrCall(pAttr, false, ENTRY_NAME)»;
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«ENDIF»
		«IF parent.orthogonal»
		«FOR r:parent.regions»
			«IF TransformationUtil.findInitialState(r) != null»
				«TransformationUtil.getInitialEffect(r)»
				«generateEnteringSubStates(TransformationUtil.findInitialState(r), true)»
			«ENDIF»
		«ENDFOR»
		«ELSEIF parent.composite»
			«IF TransformationUtil.findInitialState(parent.regions.head) != null»
				«TransformationUtil.getInitialEffect(parent.regions.head)»
				«generateEnteringSubStates(TransformationUtil.findInitialState(parent.regions.head), true)»
			«ENDIF»
		«ELSE»
		«ENDIF»'''
	}
	
	@Deprecated
	def String generateEnteringSubStates(State parent, State child, boolean enterParent, boolean gotoSubstate) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«IF enterParent»
		«generateChangeState(parent)»
		«getFptrCall(pAttr, false, ENTRY_NAME)»;
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«ENDIF»
		«IF parent.orthogonal»
		«FOR r:parent.regions»
			«IF child.container == r»
				«IF gotoSubstate»
				«generateEnteringSubStates(child, true)»
				«ENDIF»
			«ELSEIF TransformationUtil.findInitialState(r) != null»
				«TransformationUtil.getInitialEffect(r)»
				«generateEnteringSubStates(TransformationUtil.findInitialState(r), true)»
			«ENDIF»
		«ENDFOR»
		«ELSEIF parent.composite»
			//«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] = &«STATE_ARRAY_ATTRIBUTE»[«child.name.toUpperCase»_ID];
			«generateEnteringSubStates(child, true)»
		«ELSE»
		«ENDIF»'''
	}
	
	@Deprecated
	def String generateEnteringTransitiveSubStates(State parent, State child) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«generateChangeState(parent)»
		«getFptrCall(pAttr, false, ENTRY_NAME)»;
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«IF parent.orthogonal»
		«FOR r:parent.regions»
			«IF child.container == r»
				«generateEnteringSubStates(child, true)»
			«ELSEIF TransformationUtil.transitiveSubStates(r).contains(child)»
				«var nextParent = r.subvertices.filter(State).filter[TransformationUtil.transitiveSubStates(it).contains(child)].head»
				«generateEnteringTransitiveSubStates(nextParent, child)»
			«ELSEIF TransformationUtil.findInitialState(r) != null»
				«TransformationUtil.getInitialEffect(r)»
				«generateEnteringSubStates(TransformationUtil.findInitialState(r), true)»
			«ENDIF»
		«ENDFOR»
		«ELSEIF parent.composite»
			«IF child.container.state == parent»
				«generateEnteringSubStates(parent, child, false, false)»
			«ELSE»
				«var nextParent = parent.regions.head.subvertices.filter(State).filter[TransformationUtil.transitiveSubStates(it).contains(child)].head»
				«generateEnteringTransitiveSubStates(nextParent, child)»
			«ENDIF»
		«ELSE»
		«ENDIF»'''
	}
	
	@Deprecated
	def String generateEnteringTransitiveSubStates(State parent, State child, boolean enterParent, boolean gotoChild) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«IF enterParent»
		«generateChangeState(parent)»
		«getFptrCall(pAttr, false, ENTRY_NAME)»;
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«ENDIF»
		«IF parent.orthogonal»
		«FOR r:parent.regions»
			«IF child.container == r»
				«IF gotoChild»
				«generateEnteringSubStates(child, true)»
				«ENDIF»
			«ELSEIF TransformationUtil.transitiveSubStates(r).contains(child)»
				«var nextParent = r.subvertices.filter(State).filter[TransformationUtil.transitiveSubStates(it).contains(child)].head»
				«generateEnteringTransitiveSubStates(nextParent, child)»
			«ELSEIF TransformationUtil.findInitialState(r) != null»
				«TransformationUtil.getInitialEffect(r)»
				«generateEnteringSubStates(TransformationUtil.findInitialState(r), true)»
			«ENDIF»
		«ENDFOR»
		«ELSEIF parent.composite»
			«IF child.container.state == parent»
				«IF gotoChild»
				«generateEnteringSubStates(parent, child, enterParent, false)»
				«ENDIF»
			«ELSE»
				«var nextParent = parent.regions.head.subvertices.filter(State).filter[TransformationUtil.transitiveSubStates(it).contains(child)].head»
				«generateEnteringTransitiveSubStates(nextParent, child)»
			«ENDIF»
		«ELSE»
		«ENDIF»'''
	}
		
	
	
	def String hasSubstatesAcceptingEvent(State parent, List<State> states) {
		var transitives = TransformationUtil.transitiveSubStates(parent)
		var intersects = transitives.filter[states.contains(it)]
		if (intersects.size > 0) {
			return '!isProcessed && '
		} else {
			return ''''''
		}
	}
	
	
	@Deprecated
	def String generateEntryWithPseudo(State cp, Pseudostate p, List<Vertex> enters) {
		var ret = ''''''
		if (cp == p) {
			ret = pseudostateGenerator.generatePseudo(p)
		} else {
			ret = generateEnteringSpecial(cp, p)
		}
	}
	
	private def transitiveSubStates(Region r) {
		return TransformationUtil.transitiveSubStates(r)
	}
	
	private def transitiveSubStates(State s) {
		return TransformationUtil.transitiveSubStates(s)
	}
	
	@Deprecated
	def String generateEnteringSpecial(State parent, Pseudostate child) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«generateChangeState(parent)»
		«getFptrCall(pAttr, false, ENTRY_NAME)»;
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«IF parent.orthogonal»
		«FOR r:parent.regions»
			«IF child.container == r»
				«pseudostateGenerator.generatePseudo(child)»
			«ELSEIF r.transitiveSubStates.contains(child)»
				«var nextParent = r.subvertices.filter(State).filter[it.transitiveSubStates.contains(child)].head»
				«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] = «nextParent.name.toUpperCase»_ID;
				«generateEnteringSpecial(nextParent, child)»
			«ELSEIF TransformationUtil.findInitialState(r) != null»
				«TransformationUtil.getInitialEffect(r)»
				«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] = «TransformationUtil.findInitialState(r).name.toUpperCase»_ID;
				«generateEnteringSubStates(TransformationUtil.findInitialState(r), true)»
			«ENDIF»
		«ENDFOR»
		«ELSEIF parent.composite»
			«IF child.container.state == parent»
				«pseudostateGenerator.generatePseudo(child)»
			«ELSE»
				«var nextParent = parent.regions.head.subvertices.filter(State).filter[it.transitiveSubStates.contains(child)].head»
				«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] = «nextParent.name.toUpperCase»_ID;
				«generateEnteringSpecial(nextParent, child)»
			«ENDIF»
		«ELSE»
		«ENDIF»'''
	}
	
	@Deprecated
	def String generateEnteringSpecial(State parent, Pseudostate child, Transition t) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«generateChangeState(parent)»
		«TransformationUtil.getTransitionEffect(t)»
		«getFptrCall(pAttr, false, ENTRY_NAME)»;
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		
		«IF parent.orthogonal»
		«FOR r:parent.regions»
			«IF child.container == r»
				«pseudostateGenerator.generatePseudo(child)»
			«ELSEIF r.transitiveSubStates.contains(child)»
				«var nextParent = r.subvertices.filter(State).filter[it.transitiveSubStates.contains(child)].head»
				«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] = «nextParent.name.toUpperCase»_ID;
				«generateEnteringSpecial(nextParent, child)»
			«ELSEIF TransformationUtil.findInitialState(r) != null»
				«TransformationUtil.getInitialEffect(r)»
				«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«parent.regions.indexOf(r)»] = «TransformationUtil.findInitialState(r).name.toUpperCase»_ID;
				«generateEnteringSubStates(TransformationUtil.findInitialState(r), true)»
			«ENDIF»
		«ENDFOR»
		«ELSEIF parent.composite»
			«IF child.container.state == parent»
				«pseudostateGenerator.generatePseudo(child)»
			«ELSE»
				«var nextParent = parent.regions.head.subvertices.filter(State).filter[it.transitiveSubStates.contains(child)].head»
				«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0] = «nextParent.name.toUpperCase»_ID;
				«generateEnteringSpecial(nextParent, child)»
			«ENDIF»
		«ELSE»
		«ENDIF»'''
	}
	
	
	
	
	def String generateCompletionCall(State state) {
		var callCompletionEvent = ''''''
		var composite = state.container.state
		if (composite == null) {
			callCompletionEvent = ''''''
		} else {
			callCompletionEvent = '''
			if («FOR r:composite.regions SEPARATOR ' && '»(«STATE_ARRAY_ATTRIBUTE»[«composite.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«composite.regions.indexOf(r)»] == «STATE_MAX»)«ENDFOR») {
				process«COMPLETION_EVENT»();
			}'''
		}
		return callCompletionEvent
	}
	
	def TransitionGraph calculateTransitionGraphs(State s, Transition t) {
		var ret = new TransitionGraph
		ret.P.add(t.target as Pseudostate)
		ret.T.add(t)
		ret.S.add(s)
		var target = t.target as Pseudostate
		if (target.kind == PseudostateKind.JOIN_LITERAL) {
			var ins = target.incomings.filter[it.source.container.state == s.container.state].toList
			ret.S.addAll(ins.map[it.source].filter(State))
			ret.T.addAll(ins)
		}
		var nexts = t.target.findTrans
		ret.T.addAll(nexts)
		var H = nexts.map[t.target].filter(Pseudostate).filter[
			it.kind == PseudostateKind.DEEP_HISTORY_LITERAL || it.kind == PseudostateKind.SHALLOW_HISTORY_LITERAL
		]
		ret.P.addAll(H)
		ret.P.addAll(nexts.map[it.source].filter(Pseudostate))
		ret.L.addAll(nexts.map[it.target].filter(State))
		return ret
	}
	
	def List<Transition> findTrans(Vertex v) {
		var ret= new UniqueEList<Transition>
		ret.addAll(v.outgoings)
		for(out:v.outgoings) {
			if (!(out.target instanceof State)) {
				ret.addAll(out.target.findTrans)
			}
		}
		return ret
	}
	
	def List<State> getRootStates(List<State> states) {
		val ret = new ArrayList<State>
		ret.addAll(states.filter[it.container == topRegion])
		return ret
	}
	
	def State getRootState(State state) {
		var ret = state
		while (ret.container.state != null) {
			ret = ret.container.state
		}
		return ret
	}
	
	
	def getRegionNumber(State child) {
		if (child.container == topRegion) {
			return 0
		}
		return child.container.state.regions.indexOf(child.container)
	}
	
	@Deprecated
	def String generateHierarchy(List<State> hie, List<State> ins, int mode, List<Transition> ts) {
		var ret = ""
		var tmpHie = new ArrayList<State>
		tmpHie.addAll(hie)
		if (tmpHie.size == 1) {
			if (mode == 0) {
				ret = '''
				«FOR s:ins SEPARATOR ' else '»
				if (!isProcessed && «STATE_ARRAY_ATTRIBUTE»[«tmpHie.head.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«s.regionNumber»] == «s.name.toUpperCase»_ID) {
					«eventTransform.generateTransitionCode(s, ts.filter[it.source == s].head)»
				}
				«ENDFOR»
				'''
			} else {
				ret = '''
				if (isProcessed) {
					«FOR s:ins SEPARATOR ' else '»
					if («STATE_ARRAY_ATTRIBUTE»[«tmpHie.head.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«s.regionNumber»] == «s.name.toUpperCase»_ID) {
						«eventTransform.generateTransitionCode(s, ts.filter[it.source == s].head)»
					}
					«ENDFOR»
				}
				'''
			}
			
		} else if (tmpHie.size > 1) {
			var head = tmpHie.head
			tmpHie.remove(head)
			ret = '''
			if (!isProcessed && «STATE_ARRAY_ATTRIBUTE»[«head.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«tmpHie.head.regionNumber»] == «tmpHie.head.name.toUpperCase»_ID) {
				«generateHierarchy(tmpHie, ins, mode, ts)»
			}'''
		} else {
			ret = '''«FOR s:ins SEPARATOR ' else '»
					«eventTransform.generateTransitionCode(s, ts.filter[it.source == s].head)»
				«ENDFOR»'''
		}
		return ret
	}
	
	//get transitive parent states
	def List<State> getTransitiveParentStates(Vertex s) {
		var ret = new ArrayList<State>
		var parent = s.container.state
		while(parent != null) {
			ret.add(parent)
			parent = parent.container.state
		}
		return ret
	}
	
	def List<State> getStateHierarchy(State s) {
		var ret = new ArrayList<State>
		var parent = s.container.state
		while(parent != null && parent.container != topRegion) {
			ret.add(0, parent)
			parent = parent.container.state
		}
		return ret
	}
	
	def List<Entry<List<State>, Integer>> calculateAcceptingStates(State parent, List<State> acceptingStates) {
		var transitiveSubstates = parent.transitiveSubStates
		val retTmp = new ArrayList<State>
		transitiveSubstates.forEach[
			if (acceptingStates.contains(it)) {
				retTmp.add(it)
			}
		]
		
		//0->sequential, 1->parallel
		val List<Entry<List<State>, Integer>> ret = new ArrayList
		//sorting the result, the first item is the deepest state
		val Map<Integer, List<State>> map = new HashMap
		retTmp.forEach[
			var depth = it.calculateDepth
			if (!map.containsKey(depth)) {
				map.put(depth, new ArrayList)
			}
			map.get(depth).add(it)
		]		
		
		var maxDepth = findMax(map.keySet.toList)
		for(var i = 0; i <= maxDepth; i++) {
			if (map.containsKey(i)) {
				if (map.get(i).size == 1) {
					var newList = new ArrayList<State>
					newList.addAll(map.get(i))
					var mapEntry = new HashMap<List<State>, Integer>
					mapEntry.put(newList, 0)
					ret.addAll(mapEntry.entrySet)
				} else {
					val Map<State, List<State>> m = new HashMap
					for(s:map.get(i)) {
						if (!m.containsKey(s.container.state)) {
							m.put(s.container.state, new ArrayList)
						}
						m.get(s.container.state).add(s)
					}
					for(en:m.entrySet) {
						var mapEntry = new HashMap<List<State>, Integer>
						var newList = new ArrayList<State>
						newList.addAll(en.value)
						if (en.key.orthogonal) {
							mapEntry.put(newList, 1)
							ret.addAll(mapEntry.entrySet)
						} else {
							mapEntry.put(newList, 0)
							ret.addAll(mapEntry.entrySet)
						}
					}
				}
			}
		}
		
		return ret
	}
	
	private def int findMax(List<Integer> l) {
		var ret = l.head
		for (i : l) {
			if (i > ret) {
				ret = i
			}
		}
		return ret
	}

	def int calculateDepth(State s) {
		if (s.container == topRegion) {
			return 1
		} else {
			return 1 + s.container.state.calculateDepth
		}
	}
	
	def generateDelegation(State s, List<Transition> transitions) {
		return '''
		«IF checkTransitiveSubStatesAcceptEvent(s, transitions)»
		«IF s.orthogonal»
		«ELSE»
		
		«ENDIF»
		«ENDIF»'''
	}
	
	//check whether the substate of a parent state accepts the event
	def checkSubStatesAcceptEvent(State parent, List<Transition> transitions) {
		val substates = new ArrayList<State>
		parent.regions.forEach[
			substates.addAll(it.subvertices.filter(State))
		]
		var ret = false
		for(t:transitions) {
			if (!ret && substates.contains(t.source)) {
				ret = true
			}
		}
		return ret
	}
	
	//check whether the transitive substates of a parent state accepts the event
	def checkTransitiveSubStatesAcceptEvent(State parent, List<Transition> transitions) {
		val substates = TransformationUtil.transitiveSubStates(parent)
		var ret = false
		for(t:transitions) {
			if (!ret && substates.contains(t.source)) {
				ret = true
			}
		}
		return ret
	}
	
	//create State_t struct
	def createStateStruct() {
		superContext.createOwnedOperation("entry_dft", null, null)
		superContext.createOwnedOperation("exit_dft", null, null)
		superContext.createOwnedOperation("doActivity_dft", null, null)
		
		//stateStruct = targetPack.createOwnedPrimitiveType(STATE_STRUCT_NAME)
		stateStruct = superContext.createNestedClassifier(STATE_STRUCT_NAME, UMLPackage.Literals.PRIMITIVE_TYPE)
		StereotypeUtil.apply(stateStruct, Typedef)
		UMLUtil.getStereotypeApplication(stateStruct, Typedef).definition = '''
		struct «STATE_STRUCT_NAME» {
			//«MAX_NUMBER_ORTHOGONAL_REGIONS» is configured as the maximum number of orthogonal regions a composite states can have
			unsigned int /*«STATE_ID_ENUM»*/ «PREVIOUS_STATES»[«MAX_NUMBER_ORTHOGONAL_REGIONS»]; //for history states
			unsigned int /*«STATE_ID_ENUM»*/ «ACTIVE_SUB_STATES»[«MAX_NUMBER_ORTHOGONAL_REGIONS»];
			void («superContext.name»::*entry)();
			void («superContext.name»::*exit)();
			void («superContext.name»::*doActivity)();
			«STATE_STRUCT_NAME»() {
				entry = &«superContext.name»::entry_dft;
				exit = &«superContext.name»::exit_dft;
				doActivity = &«superContext.name»::doActivity_dft;
				for(int i = 0; i < «MAX_NUMBER_ORTHOGONAL_REGIONS»; i++) {
					«PREVIOUS_STATES»[i] = «STATE_MAX»;
					«ACTIVE_SUB_STATES»[i] = «STATE_MAX»;
				}
			}
		} '''
		stateStruct.createDependency(stateIdEnum)
	}
	
	def getFptrCall(String attr, boolean isPointer, String fptrName) {
		if (isPointer) {
			return '''(this->*«attr»->«fptrName»)()'''
		} 
		return '''(this->*«attr».«fptrName»)()'''
	}

	private def Map<Event, List<Transition>> getAllEvents(Region region) {
		val Map<Event, List<Transition>> ret = new HashMap
		var subVertices = region.subvertices
		var transitions = region.transitions

		transitions.forEach [
			for (trigger : it.triggers) {
				if (trigger.event != null) {
					if (!ret.containsKey(trigger.event)) {
						ret.put(trigger.event, new ArrayList)
					}
					if (!ret.get(trigger.event).contains(it)) {
						ret.get(trigger.event).add(it)
					}
				}
			}
		]

		subVertices.filter(typeof(State)).forEach [
			for (subRe : it.regions) {
				var m = subRe.allEvents
				for (e : m.entrySet) {
					if (!ret.containsKey(e.key)) {
						ret.put(e.key, e.value)
					} else {
						ret.get(e.key).addAll(e.value)
					}
				}

			}
		]

		return ret
	}

	private def void getAllActionsTransitionsStates(Region region) {
		if (!regions.contains(region)) {
			regions.add(region)
		}
		transitions.addAll(region.transitions.filter[it.source != null && it.target != null])
		
		transitions.filter[!parallelTransitions.contains(it)].forEach[
			if (it.source instanceof Pseudostate) {
				if ((it.source as Pseudostate).kind == PseudostateKind.FORK_LITERAL && it.effect != null) {
					parallelTransitions.add(it)
				}  
			}
			
			if (it.target instanceof Pseudostate) {
				if ((it.target as Pseudostate).kind == PseudostateKind.JOIN_LITERAL && it.effect != null) {
					parallelTransitions.add(it)
				}
			}
		]
		
		for (s : region.subvertices) {
			vertexes.add(s)
			if (s instanceof State) {
				if (!(s instanceof FinalState)) {
					states.add(s)
				}
				
				if (s.entry != null && s.entry instanceof OpaqueBehavior) {
					actions.add(s.entry as OpaqueBehavior)
				}

				if (s.exit != null && s.exit instanceof OpaqueBehavior) {
					actions.add(s.exit as OpaqueBehavior)
				}

				if (createDoActivity && s.doActivity != null && s.doActivity instanceof OpaqueBehavior) {
					actions.add(s.doActivity as OpaqueBehavior)
				}

				s.regions.forEach [
					it.getAllActionsTransitionsStates
				]
			}

		}
	}
	
	public List<Region> orthogonalRegions = new ArrayList
	
	private def createRegionMethods() {
		//create macro for regions
		//regions.remove(topRegion)
		var macros = ''''''
		for(var i = 0; i < regions.size; i++) {
			macros = '''
			«macros»
			#define «regions.get(i).regionMacroName» («i»)'''
			regions.get(i).createRegionMethod
			createRegionMethodExit(regions.get(i))
			
			if (regions.get(i).state != null && regions.get(i).state.orthogonal) {
				orthogonalRegions.add(regions.get(i))
			}
		}
		
		for(var i = 0; i < orthogonalRegions.size; i++) {
			appendIncludeHeader('''
			#define «orthogonalRegions.get(i).regionMacroId» («i»)''')
		}
		
		if (!StereotypeUtil.isApplied(superContext, Include)) {
			StereotypeUtil.apply(superContext, Include)
		}
		var header = UMLUtil.getStereotypeApplication(superContext, Include).header
		UMLUtil.getStereotypeApplication(superContext, Include).header = '''
		«header»
		«macros»'''
		
	}
	
	def getRegionMacroId(Region r) {
		return '''REGION_ID_«r.state.name.toUpperCase»_«r.name.toUpperCase»'''
	}
	
	def getRegionMethodName(Region r) {
		if (r == topRegion) {
			return '''«r.stateMachine.name»_«r.name»_Enter'''
		}
		return '''«r.state.name»_«r.name»_Enter'''
	}
	
	def getRegionMacroName(Region r) {
		if (r == topRegion) {
			return '''«r.stateMachine.name.toUpperCase»_«r.name.toUpperCase»'''
		}
		return '''«r.state.name.toUpperCase»_«r.name.toUpperCase»'''
	}
	
	def getVertexMacroName(Vertex v) {
		if (v.container == topRegion) {
			return '''«topRegion.stateMachine.name.toUpperCase»_«v.container.name.toUpperCase»_«v.name.toUpperCase»'''
		}
		return '''«v.container.state.name.toUpperCase»_«v.container.name.toUpperCase»_«v.name.toUpperCase»'''
	}
	
	def getInitialMacroName(Region r) {
		if (r == topRegion) {
			return '''«r.stateMachine.name.toUpperCase»_«r.name.toUpperCase»_DEFAULT'''
		}
		return '''«r.state.name.toUpperCase»_«r.name.toUpperCase»_DEFAULT'''
	}
	
	private String paramName = "enter_mode"
	
	private def getSubVertexes(State state) {
		val ret = new ArrayList<Vertex>
		state.regions.forEach[
			ret.addAll(it.subvertices)
		]
		return ret
	}
	
	private def void createRegionMethod(Region r) {
		var endVertexs = new ArrayList<Vertex>
		
		//state2TransSubVertices maps from a sub-state sub of r to its sub-vertices (of sub) which
		// have incoming transitions outgoing from a vertex which is not a transitive sub-vertex of r 
		var Map<State, List<Vertex>> state2TransSubVertices = new HashMap
		//looking for vertexes having transitions incoming from vertexes which are not direct or indirect sub-vertex of r
		for(v:r.subvertices) {
			val incomings = v.incomings.filter[it.container != v.container || (it.source instanceof State && (it.source as State).subVertexes.contains(it.target))]
			val sources = incomings.map[it.source]
			if (sources.size > 0) {
				if (!endVertexs.contains(v)) {
					endVertexs.add(v)
				}
			}
			
			//looking for vertex
			
			if (v instanceof State) {
				val subvertices = new ArrayList<Vertex>
				v.regions.forEach[subvertices.addAll(it.allSubVertexes)]
				for(subV:subvertices) {
					var sourcesOfIncomings = subV.incomings.map[it.source]
					
					//sourcesOutSideOfR is a list of vertices having transitions targeting subV
					var sourcesOutSideOfR = sourcesOfIncomings.filter[!subvertices.contains(it)]
					if (sourcesOutSideOfR.size > 0) {
						if (!state2TransSubVertices.containsKey(v)) {
							state2TransSubVertices.put(v, new ArrayList)
						}
						state2TransSubVertices.get(v).add(subV)
					}
				}
			}
		}
		var switchBody = ''''''
		var body = ''''''
		var macros = ''''''
		//create macros for vertexes which are used to differentiate ways entering the region/state
		if (TransformationUtil.firstPseudoState(r, PseudostateKind.INITIAL_LITERAL) != null) {
			var initialP = TransformationUtil.firstPseudoState(r, PseudostateKind.INITIAL_LITERAL)
			var initialState = initialP.outgoings.head.target as State
			var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«initialState.name.toUpperCase»_ID]'''
			macros = '''
			«macros»
			#define «r.initialMacroName» (0)'''
			body = '''
			«body»
			case «r.initialMacroName»:
				«TransformationUtil.getTransitionEffect(initialP.outgoings.head)»
				«generateChangeState(initialState)»
				«getFptrCall(pAttr, false, ENTRY_NAME)»;
				//starting the counters for time events
				«generateActivateTimeEvent(initialState)»
				
				//start activity of «initialState.name» by calling setFlag
				«SET_FLAG»(«initialState.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
				«IF initialState.composite»
					«IF initialState.orthogonal»
					//TODO: fork region funtions
					«FOR subRegion: initialState.regions»
						//«subRegion.regionMethodName»(«subRegion.initialMacroName»);
						«concurrency.generateForkCall(subRegion, true, subRegion.initialMacroName)»
					«ENDFOR»
					//TODO: join region functions
					«FOR subRegion: initialState.regions»
						//«subRegion.regionMethodName»(«subRegion.initialMacroName»);
						«concurrency.generateJoinCall(subRegion, true)»
					«ENDFOR»
					«ELSE»
						«initialState.regions.head.regionMethodName»(«initialState.regions.head.initialMacroName»);
					«ENDIF»
				«ENDIF»
				//TODO: set systemState to EVENT_CONSUMED
				break;'''
		}
		for(var i = 0; i < endVertexs.size; i++) {
			macros = '''
			«macros»
			#define «endVertexs.get(i).vertexMacroName» («i + 1»)'''
		}
		if (UMLUtil.getStereotypeApplication(superContext, Include) == null) {
			StereotypeUtil.apply(superContext, Include)
		}
		var header = UMLUtil.getStereotypeApplication(superContext, Include).header
		UMLUtil.getStereotypeApplication(superContext, Include).header = '''
		«header»
		«macros»'''
		
		
		var regionMethod = superContext.createOwnedOperation(r.regionMethodName, null, null)
		regionMethod.createOwnedParameter(paramName, charType)
		for(v:endVertexs) {
			if (v instanceof State) {
				var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«v.name.toUpperCase»_ID]'''
				body = '''
				«body»
				case «v.vertexMacroName»:
					«generateChangeState(v)»
					«getFptrCall(pAttr, false, ENTRY_NAME)»;
					//starting the counters for time events
					«generateActivateTimeEvent(v)»
					//start activity of «v.name» by calling setFlag
					«SET_FLAG»(«v.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
					
					«IF v.composite»
						«IF v.orthogonal»
						//TODO: fork region funtions
						«FOR subRegion: v.regions»
							//«subRegion.regionMethodName»(«subRegion.initialMacroName»);
							«concurrency.generateForkCall(subRegion, true, subRegion.initialMacroName)»
						«ENDFOR»
						//TODO: join region functions
						«FOR subRegion: v.regions»
							//«subRegion.regionMethodName»(«subRegion.initialMacroName»);
							«concurrency.generateJoinCall(subRegion, true)»
						«ENDFOR»
						«ELSE»
							«v.regions.head.regionMethodName»(«v.regions.head.initialMacroName»);
						«ENDIF»
					«ENDIF»
					//TODO: set systemState to EVENT_CONSUMED
					break;'''
			} else {
				body = '''
				«body»
				case «v.vertexMacroName»: 
					«pseudostateGenerator.generatePseudo(v as Pseudostate)»
					//TODO: set systemState to EVENT_CONSUMED
					break;'''
			}
		}
		if (r != topRegion) {
			for(e:state2TransSubVertices.entrySet) {
				var state = e.key
				for(transitiveSubVertex:e.value) {
					body = '''
					«body»
					case «transitiveSubVertex.vertexMacroName»:
						«generateEnteringOnSubVertex(state, transitiveSubVertex)»
						break;'''
				}
			}
		}
		switchBody = '''
		switch(«paramName») {
			«body»
		}'''
		superContext.createOpaqueBehavior(regionMethod, switchBody)
	}
	
	def generateEnteringOnSubVertex(State parent, Vertex subVertex) {
		var pAttr = '''«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID]'''
		return '''
		«generateChangeState(parent)»
		«getFptrCall(pAttr, false, ENTRY_NAME)»;
		//starting the counters for time events
		«generateActivateTimeEvent(parent)»
		//start activity of «parent.name» by calling setFlag
		«SET_FLAG»(«parent.name.toUpperCase»_ID, «THREAD_FUNC_DOACTIVITY_TYPE», true);
		«IF parent.composite && parent != subVertex»
			«IF parent.orthogonal»
				«var toJoinList = new ArrayList<Region>»
				«FOR r:parent.regions»
					«IF subVertex != null»
						«IF subVertex.container == r»
							«var b = toJoinList.add(r)»
							//«getRegionMethodName(r)»(«subVertex.vertexMacroName»);
							«concurrency.generateForkCall(r, true, subVertex.vertexMacroName)»
						«ELSEIF r.allSubVertexes.contains(subVertex)»
							«var b = toJoinList.add(r)»
							//«getRegionMethodName(r)»(«subVertex.vertexMacroName»);
							«concurrency.generateForkCall(r, true, subVertex.vertexMacroName)»
						«ELSEIF TransformationUtil.findInitialState(r) != null»
							«var b = toJoinList.add(r)»
							//«getRegionMethodName(r)»(«r.initialMacroName»);
							«concurrency.generateForkCall(r, true, r.initialMacroName)»
						«ENDIF»
					«ELSE»
						«IF TransformationUtil.findInitialState(r) != null»
							«var b = toJoinList.add(r)»
							//«getRegionMethodName(r)»(«r.initialMacroName»);
							«concurrency.generateForkCall(r, true, r.initialMacroName)»
						«ENDIF»
					«ENDIF»
				«ENDFOR»
				«FOR r:toJoinList»
					«concurrency.generateJoinCall(r, true)»
				«ENDFOR»
			«ELSEIF parent.composite»
				«IF subVertex != null»
					«IF subVertex.container.state == parent»
						«getRegionMethodName(parent.regions.head)»(«subVertex.vertexMacroName»);
					«ELSE»
						«var containingRegion = parent.regions.filter[it.allSubVertexes.contains(subVertex)].head»
						«getRegionMethodName(containingRegion)»(«subVertex.vertexMacroName»);
					«ENDIF»
				«ELSE»
					«getRegionMethodName(parent.regions.head)»(«parent.regions.head.initialMacroName»);
				«ENDIF»
			«ELSE»
			«ENDIF»
		«ENDIF»'''
		
	}
	
	private def createRegionMethodExit(Region r) {
		if (r.state == null || !r.state.composite) {
			return
		}
		
		//todo: save states for history
		
		var parent = r.state
		var body = '''
		//exiting region «r.name»
		«var regionIndex = r.state.regions.indexOf(r)»
		«FOR s:r.subvertices.filter(State).filter[!(it instanceof FinalState) && it.composite] SEPARATOR ' else '»
			if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«regionIndex»] == «s.name.toUpperCase»_ID) {
				«IF s.orthogonal»
				«FOR subRegion:s.regions»
					«concurrency.generateForkCall(subRegion, false, "0")»
				«ENDFOR»
				«FOR subRegion:s.regions»
					«concurrency.generateJoinCall(subRegion, false)»
				«ENDFOR»
				«ELSE»
					«getRegionMethodExitName(s.regions.head)»();
				«ENDIF»
			}
		«ENDFOR»
		if («STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«regionIndex»] != «STATE_MAX») {
			//signal to exit the doActivity of sub-state of «parent.name»
			«SET_FLAG»(«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0], «THREAD_FUNC_DOACTIVITY_TYPE», false);
			«FOR sub:r.subvertices.filter(State) SEPARATOR ' else '»
				if («sub.name.toUpperCase»_ID == «STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«regionIndex»]) {
					«sub.generateDeactivateTimeEvent»
				}	
			«ENDFOR»
			//exit action of sub-state of «parent.name»
			(this->*«STATE_ARRAY_ATTRIBUTE»[«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[0]].«EXIT_NAME»)();
			«IF TransformationUtil.isSavehistory(topRegion, r)»
				//save history region «r.name» of state «parent.name»
				«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«PREVIOUS_STATES»[«regionIndex»] = «STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«regionIndex»];
			«ENDIF»
			//set active sub-state of «parent.name» to «STATE_MAX» meaning NULL
			«STATE_ARRAY_ATTRIBUTE»[«parent.name.toUpperCase»_ID].«ACTIVE_SUB_STATES»[«regionIndex»] = «STATE_MAX»;
		}'''
		var exitOp = superContext.createOwnedOperation(r.regionMethodExitName, null, null)
		superContext.createOpaqueBehavior(exitOp, body)
	}
	
	
	
	private def getRegionMethodExitName(Region r) {
		if (r == topRegion) {
			return '''«r.stateMachine.name»_«r.name»_Exit'''
		}
		return '''«r.state.name»_«r.name»_Exit'''
	}
	
	def List<Vertex> allSubVertexes(Region r) {
		val ret = new ArrayList<Vertex>
		ret.addAll(r.subvertices)
		r.subvertices.filter(State).forEach[
			it.regions.forEach[
				ret.addAll(it.allSubVertexes)
			]
		] 
		return ret
	}
	
	private def List<Vertex> allSubVertexes(State s) {
		val ret = new ArrayList<Vertex>
		s.regions.forEach[
			ret.addAll(it.allSubVertexes)
		]
		return ret
	}

	private def createOpaqueBehavior(Class container, Operation op) {
		var opaque = op.methods.filter(typeof(OpaqueBehavior)).head
		if (opaque == null) {
			opaque = container.createClassifierBehavior(op.name, UMLPackage.Literals.OPAQUE_BEHAVIOR) as OpaqueBehavior
			op.methods.add(opaque)
			opaque.languages.add(langID)
		}
		return opaque
	}

	def createOpaqueBehavior(Class container, Operation op, String body) {
		var opaque = container.createOpaqueBehavior(op)
		if (opaque.bodies.size > 0) {
			opaque.bodies.set(0, body)
		} else {
			opaque.languages.add(langID)
			opaque.bodies.add(body)
		}
		return opaque
	}

	public def createDerivedOperation(Class clz, Operation source) {
		var name = source.name
		var derivedOp = clz.createOwnedOperation(name, null, null)
		OperationUtils.syncOperation(source, derivedOp)
		derivedOp.name = name
		StereotypeUtil.apply(derivedOp, DerivedElement)
		UMLUtil.getStereotypeApplication(derivedOp, DerivedElement).source = source
		derivedOp.setVirtual
		derivedOp

	}

	private def setVirtual(Operation op) {
		StereotypeUtil.apply(op, Virtual)
	}
	
	def getGuard(Transition t) {
		return (t.guard.specification as OpaqueExpression).bodies.head
	}


}