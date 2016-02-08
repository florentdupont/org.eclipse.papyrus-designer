package org.eclipse.papyrus.designer.components.modellibs.core.xtend;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.transformation.core.UMLTool;
import org.eclipse.papyrus.designer.components.transformation.core.Utils;
import org.eclipse.papyrus.designer.components.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;


public class StateMachineUtil {
	public static Region region(State state) {
		EList<Region> regions = state.getRegions();
		if (regions.size() > 0) {
			return regions.get(0);
		}
		return null;
	}

	public static Region region(StateMachine stateMachine) {
		EList<Region> regions = stateMachine.getRegions();
		if (regions.size() > 0) {
			return regions.get(0);
		}
		return null;
	}

	public static EList<State> subStates(State state) {
		Region region = region(state);
		return (region != null) ? states(region) : new BasicEList<State>();
	}

	public static EList<State> states(StateMachine stateMachine) {
		Region region = region(stateMachine);
		return (region != null) ? states(region) : new BasicEList<State>();
	}

	public static EList<State> states(Region region) {
		EList<State> states = new BasicEList<State>();
		for (org.eclipse.uml2.uml.Vertex vertex : region.getSubvertices()) {
			if (vertex instanceof State) {
				states.add((State) vertex);
			}
		}
		return states;
	}

	public static EList<Pseudostate> entryPoints(State state) {
		EList<Pseudostate> pseudoStates = new BasicEList<Pseudostate>();
		for (Pseudostate pseudoState : state.getConnectionPoints()) {
			if (pseudoState.getKind() == PseudostateKind.ENTRY_POINT_LITERAL) {
				pseudoStates.add(pseudoState);
			}
		}
		return pseudoStates;
	}

	public static EList<Pseudostate> exitPoints(State state) {
		EList<Pseudostate> pseudoStates = new BasicEList<Pseudostate>();
		for (Pseudostate pseudoState : state.getConnectionPoints()) {
			if (pseudoState.getKind() == PseudostateKind.EXIT_POINT_LITERAL) {
				pseudoStates.add(pseudoState);
			}
		}
		return pseudoStates;
	}

	public static EList<Pseudostate> junctionPoints(Region region) {
		return filteredPseudoStates(region, PseudostateKind.JUNCTION_LITERAL);
	}

	public static EList<Pseudostate> choicePoints(Region region) {
		return filteredPseudoStates(region, PseudostateKind.CHOICE_LITERAL);
	}

	public static EList<Pseudostate> filteredPseudoStates(Region region, PseudostateKind kind) {
		EList<Pseudostate> pseudoStates = new BasicEList<Pseudostate>();
		for (Vertex vertex : region.getSubvertices()) {
			if (vertex instanceof Pseudostate) {
				Pseudostate pseudoState = (Pseudostate) vertex;
				if (pseudoState.getKind() == kind) {
					pseudoStates.add(pseudoState);
				}
			}
		}
		return pseudoStates;
	}

	public static Pseudostate firstPseudoState(Region region, PseudostateKind kind) {
		for (Vertex vertex : region.getSubvertices()) {
			if (vertex instanceof Pseudostate) {
				Pseudostate pseudoState = (Pseudostate) vertex;
				if (pseudoState.getKind() == kind) {
					return pseudoState;
				}
			}
		}
		return null;
	}

	/**
	 * @return The set of all transitions ending indirectly on the state.
	 *         This is, those which end on one of the state's entry points.
	 */
	public static Collection<Transition> indirectIncomings(State state) {
		EList<Transition> indirectIncoming = new BasicEList<Transition>();
		for (Pseudostate p : entryPoints(state)) {
			for (Transition t : p.getIncomings()) {
				indirectIncoming.add(t);
			}
		}
		return indirectIncoming;
	}

	/**
	 * @return The set of all transitions ending indirectly on the state.
	 *         This is, those which end on one of the state's entry points.
	 */
	public static Collection<Transition> indirectOutgoings(State state) {
		EList<Transition> indirectIncoming = new BasicEList<Transition>();
		for (Pseudostate p : entryPoints(state)) {
			for (Transition t : p.getIncomings()) {
				indirectIncoming.add(t);
			}
		}
		return indirectIncoming;
	}


	/**
	 * @return The set of all direct and indirect incoming transitions to the state.
	 */
	public static Collection<Transition> allIncomings(State state) {
		EList<Transition> allIncoming = new BasicEList<Transition>();
		for (Transition t : state.getIncomings()) {
			allIncoming.add(t);
		}
		allIncoming.addAll(indirectIncomings(state));
		return allIncoming;
	}

	/**
	 * @return The set of all direct and indirect incoming transitions to the state.
	 */
	public static Collection<Transition> allOutgoings(State state) {
		EList<Transition> allIncoming = new BasicEList<Transition>();
		for (Transition t : state.getIncomings()) {
			allIncoming.add(t);
		}
		allIncoming.addAll(indirectOutgoings(state));
		return allIncoming;
	}

	/**
	 * Adds all the inner elements of a composite state to self, except for the
	 * initial, entry, exit and history pseudo-states.
	 *
	 * This operation moves those elements, as it removes them from their
	 * original container and sets their owner to the recipient.
	 */
	public static void moveContents(Region targetRegion, State source) {
		Region sourceRegion = region(source);
		EList<Vertex> vertices = new BasicEList<Vertex>(sourceRegion.getSubvertices());
		for (Vertex v : vertices) {
			// move states and pseudostates
			targetRegion.getSubvertices().add(v);
		}
		EList<Transition> transitions = new BasicEList<Transition>(sourceRegion.getTransitions());
		for (Transition t : transitions) {
			targetRegion.getTransitions().add(t);
		}
	}

	public static Pseudostate initialState(Region region) {
		return firstPseudoState(region, PseudostateKind.INITIAL_LITERAL);
	}

	public static Pseudostate deepHistory(Region region) {
		return firstPseudoState(region, PseudostateKind.DEEP_HISTORY_LITERAL);
	}

	public static State createState(Region region, String name) {
		return (State) region.createSubvertex(name, UMLPackage.eINSTANCE.getState());
	}

	public static Pseudostate createPseudostate(Region region, String name) {
		return (Pseudostate) region.createSubvertex(name, UMLPackage.eINSTANCE.getPseudostate());
	}
	
	/**
	 * Return the bound package in the context of template instantiation for a given actual.
	 *
	 * This is required by the state machine template which needs to access operation IDs
	 * provided by the call interceptor.
	 *
	 * @param actual
	 *            The actual for template binding
	 * @return
	 */
	public static Package boundPackageRef(Type actual) {
		for (Package nestedPkg : PackageUtil.getRootPackage(actual).getNestedPackages()) {
			// search for bound package templates who are bound to the same actual
			// TODO: search is ambiguous, if same actual is used more than once
			if (nestedPkg.getTemplateBindings().size() > 0) {
				TemplateBinding binding = nestedPkg.getTemplateBindings().get(0);
				if (actual == TemplateUtils.getFirstActualFromBinding(binding)) {
					return nestedPkg;
				}
			}
		}
		return null;
	}

	/**
	 * Declares a dependency from the current classifier which is produced by template instantiation
	 * to the element (enum) "OperationIDs", if found within the passed package.
	 *
	 * @param pkg
	 * @return
	 */
	public static void declareDependencyToOperationIDs(Package pkg) {
		PackageableElement type = pkg.getPackagedElement("OperationIDs"); //$NON-NLS-1$
		if (type instanceof Type) {
			UMLTool.declareDependency(TransformationContext.classifier, (Type) type);
		}
	}

	/**
	 * Declares a dependency from the current classifier which is produced by template instantiation
	 * to the element (enum) "SignalIDs", if found within the passed package.
	 *
	 * @param pkg
	 *            the package in which an enumeration is looked up
	 * @return
	 */
	public static void declareDependencyToSignalIDs(Package pkg) {
		NamedElement type = Utils.getQualifiedElement(pkg, "globalenums::" + SIGNAL_ENUM); //$NON-NLS-1$
		if (type instanceof Type) {
			UMLTool.declareDependency(TransformationContext.classifier, (Type) type);
		}
	}
	
	static final String SIGNAL_ENUM = "SignalIDs"; 
}
