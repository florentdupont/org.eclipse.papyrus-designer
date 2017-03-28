/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponentImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage
 * @generated
 */
public class Ucm_detailed_componentsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ucm_detailed_componentsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_detailed_componentsSwitch() {
		if (modelPackage == null) {
			modelPackage = Ucm_detailed_componentsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION: {
				DetailedComponentImplementation detailedComponentImplementation = (DetailedComponentImplementation)theEObject;
				T result = caseDetailedComponentImplementation(detailedComponentImplementation);
				if (result == null) result = caseAtomicComponentImplementation(detailedComponentImplementation);
				if (result == null) result = caseIComponentImplementation(detailedComponentImplementation);
				if (result == null) result = caseIComponent(detailedComponentImplementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION: {
				IPortElementImplementation iPortElementImplementation = (IPortElementImplementation)theEObject;
				T result = caseIPortElementImplementation(iPortElementImplementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_detailed_componentsPackage.COMPONENT_PORT_IMPLEMENTATION: {
				ComponentPortImplementation componentPortImplementation = (ComponentPortImplementation)theEObject;
				T result = caseComponentPortImplementation(componentPortImplementation);
				if (result == null) result = caseIPortElementImplementation(componentPortImplementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION: {
				TechnicalPortImplementation technicalPortImplementation = (TechnicalPortImplementation)theEObject;
				T result = caseTechnicalPortImplementation(technicalPortImplementation);
				if (result == null) result = caseIPortElementImplementation(technicalPortImplementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Detailed Component Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Detailed Component Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDetailedComponentImplementation(DetailedComponentImplementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IPort Element Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IPort Element Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIPortElementImplementation(IPortElementImplementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Port Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Port Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentPortImplementation(ComponentPortImplementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Technical Port Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Technical Port Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTechnicalPortImplementation(TechnicalPortImplementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IComponent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IComponent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIComponent(IComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IComponent Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IComponent Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIComponentImplementation(IComponentImplementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Atomic Component Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Atomic Component Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAtomicComponentImplementation(AtomicComponentImplementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //Ucm_detailed_componentsSwitch
