package org.eclipse.papyrus.designer.transformation.library.xtend;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;

public class BehaviorUtil {
	private static final String DEFAULT_LANGUAGE = "C++"; //$NON-NLS-1$

	public static void set(Behavior behavior, String selectLanguage, String textblock) {
		if (behavior instanceof OpaqueBehavior) {
			OpaqueBehavior ob = (OpaqueBehavior) behavior;
			if (ob.getLanguages().size() == 0) {
				ob.getLanguages().add(DEFAULT_LANGUAGE);
				ob.getBodies().add(textblock);
			}
			else {
				int i = 0;
				for (String language : ob.getLanguages()) {
					if (selectLanguage.equals(language)) {
						if (i < ob.getBodies().size()) {
							ob.getBodies().set(i, textblock);
						}
						break;
					}
				}
			}
		}
	}

	public static void set(Behavior behavior, String textblock) {
		set(behavior, DEFAULT_LANGUAGE, textblock);
	}
	
	public static OpaqueBehavior createOpaqueBehavior(BehavioredClassifier clazz, Operation operation) {
		OpaqueBehavior ob = (OpaqueBehavior)
				clazz.createOwnedBehavior(operation.getName(), UMLPackage.eINSTANCE.getOpaqueBehavior());
		ob.setSpecification(operation);
		return ob;
	}

	public static OpaqueBehavior createOpaqueEffect(Transition transition) {
		OpaqueBehavior ob = (OpaqueBehavior)
				transition.createEffect("", UMLPackage.eINSTANCE.getOpaqueBehavior()); //$NON-NLS-1$
		return ob;
	}

	public static OpaqueExpression createOpaqueExpression(Constraint constraint, String guardCode) {
		OpaqueExpression oe = (OpaqueExpression)
				constraint.createSpecification("", null, UMLPackage.eINSTANCE.getOpaqueExpression()); //$NON-NLS-1$
		oe.getLanguages().add(DEFAULT_LANGUAGE);
		oe.getBodies().add(guardCode);
		return oe;
	}
	
	public static String body(Constraint constraint) {
		if (constraint.getSpecification() instanceof OpaqueExpression) {
			OpaqueExpression oe = (OpaqueExpression) constraint.getSpecification();
			if (oe.getBodies().size() > 0)  {
				return oe.getBodies().get(0);
			}
		}
		return constraint.getSpecification().stringValue();
	}

	/**
	 * Create an operation with an operation return type
	 * 
	 * @param clazz
	 * @param name
	 * @param retType
	 * @return
	 */
	public static Operation createOperation(Class clazz, String name, Type retType) {
		Operation operation = clazz.createOwnedOperation(name, null, null);
		if (retType != null) {
			Parameter parameter = operation.createOwnedParameter("ret", retType); //$NON-NLS-1$
			parameter.setDirection(ParameterDirectionKind.RETURN_LITERAL);
		}
		return operation;
	}

	public static String body(Behavior behavior) {
		return body(behavior, DEFAULT_LANGUAGE);
	}

	public static String body(Behavior behavior, String selectLanguage) {
		if (behavior instanceof OpaqueBehavior) {
			OpaqueBehavior ob = (OpaqueBehavior) behavior;
			int i = 0;
			for (String language : ob.getLanguages()) {
				if (selectLanguage.equals(language)) {
					if (i < ob.getBodies().size()) {
						return ob.getBodies().get(i);
					}
					break;
				}
			}
		}
		return null;
	}
}
