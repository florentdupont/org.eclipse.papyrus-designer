/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.*;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured;

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
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage
 * @generated
 */
public class Ucm_componentsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ucm_componentsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_componentsSwitch() {
		if (modelPackage == null) {
			modelPackage = Ucm_componentsPackage.eINSTANCE;
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
			case Ucm_componentsPackage.INTERACTION_ITEM_BINDING: {
				InteractionItemBinding interactionItemBinding = (InteractionItemBinding)theEObject;
				T result = caseInteractionItemBinding(interactionItemBinding);
				if (result == null) result = caseIBinding(interactionItemBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.IBINDING: {
				IBinding iBinding = (IBinding)theEObject;
				T result = caseIBinding(iBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.PORT_ROLE_SPEC: {
				PortRoleSpec portRoleSpec = (PortRoleSpec)theEObject;
				T result = casePortRoleSpec(portRoleSpec);
				if (result == null) result = caseIPortSpec(portRoleSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.IPORT_SPEC: {
				IPortSpec iPortSpec = (IPortSpec)theEObject;
				T result = caseIPortSpec(iPortSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.PORT_TYPE_SPEC: {
				PortTypeSpec portTypeSpec = (PortTypeSpec)theEObject;
				T result = casePortTypeSpec(portTypeSpec);
				if (result == null) result = caseIPortSpec(portTypeSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.CONNECTION: {
				Connection connection = (Connection)theEObject;
				T result = caseConnection(connection);
				if (result == null) result = caseIConfigured(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.CONNECTION_LINK: {
				ConnectionLink connectionLink = (ConnectionLink)theEObject;
				T result = caseConnectionLink(connectionLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.TECHNICAL_POLICY: {
				TechnicalPolicy technicalPolicy = (TechnicalPolicy)theEObject;
				T result = caseTechnicalPolicy(technicalPolicy);
				if (result == null) result = caseIConfigured(technicalPolicy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION: {
				AtomicComponentImplementation atomicComponentImplementation = (AtomicComponentImplementation)theEObject;
				T result = caseAtomicComponentImplementation(atomicComponentImplementation);
				if (result == null) result = caseIComponentImplementation(atomicComponentImplementation);
				if (result == null) result = caseIComponent(atomicComponentImplementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.ICOMPONENT_IMPLEMENTATION: {
				IComponentImplementation iComponentImplementation = (IComponentImplementation)theEObject;
				T result = caseIComponentImplementation(iComponentImplementation);
				if (result == null) result = caseIComponent(iComponentImplementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.ICOMPONENT: {
				IComponent iComponent = (IComponent)theEObject;
				T result = caseIComponent(iComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.COMPONENT_TYPE: {
				ComponentType componentType = (ComponentType)theEObject;
				T result = caseComponentType(componentType);
				if (result == null) result = caseIComponent(componentType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.COMPOSITE_COMPONENT_IMPLEMENTATION: {
				CompositeComponentImplementation compositeComponentImplementation = (CompositeComponentImplementation)theEObject;
				T result = caseCompositeComponentImplementation(compositeComponentImplementation);
				if (result == null) result = caseIComponentImplementation(compositeComponentImplementation);
				if (result == null) result = caseIComponent(compositeComponentImplementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.ASSEMBLY_PART: {
				AssemblyPart assemblyPart = (AssemblyPart)theEObject;
				T result = caseAssemblyPart(assemblyPart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.ABSTRACT_TYPE_BINDING: {
				AbstractTypeBinding abstractTypeBinding = (AbstractTypeBinding)theEObject;
				T result = caseAbstractTypeBinding(abstractTypeBinding);
				if (result == null) result = caseIBinding(abstractTypeBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_componentsPackage.COMPONENT_MODULE: {
				ComponentModule componentModule = (ComponentModule)theEObject;
				T result = caseComponentModule(componentModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction Item Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction Item Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteractionItemBinding(InteractionItemBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IBinding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IBinding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIBinding(IBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Role Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Role Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortRoleSpec(PortRoleSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IPort Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IPort Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIPortSpec(IPortSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Type Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Type Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortTypeSpec(PortTypeSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectionLink(ConnectionLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Technical Policy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Technical Policy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTechnicalPolicy(TechnicalPolicy object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Component Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentType(ComponentType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Component Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Component Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeComponentImplementation(CompositeComponentImplementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssemblyPart(AssemblyPart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Type Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Type Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractTypeBinding(AbstractTypeBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentModule(ComponentModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConfigured</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConfigured</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConfigured(IConfigured object) {
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

} //Ucm_componentsSwitch
