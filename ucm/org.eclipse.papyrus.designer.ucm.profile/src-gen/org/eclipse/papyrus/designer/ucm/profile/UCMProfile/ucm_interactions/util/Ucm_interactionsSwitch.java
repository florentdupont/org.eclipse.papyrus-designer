/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.*;

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
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage
 * @generated
 */
public class Ucm_interactionsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ucm_interactionsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_interactionsSwitch() {
		if (modelPackage == null) {
			modelPackage = Ucm_interactionsPackage.eINSTANCE;
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
			case Ucm_interactionsPackage.INTERACTION_DEFINITION_MODULE: {
				InteractionDefinitionModule interactionDefinitionModule = (InteractionDefinitionModule)theEObject;
				T result = caseInteractionDefinitionModule(interactionDefinitionModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.INTERACTION_PATTERN: {
				InteractionPattern interactionPattern = (InteractionPattern)theEObject;
				T result = caseInteractionPattern(interactionPattern);
				if (result == null) result = caseIInteractionDefinition(interactionPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.IINTERACTION_DEFINITION: {
				IInteractionDefinition iInteractionDefinition = (IInteractionDefinition)theEObject;
				T result = caseIInteractionDefinition(iInteractionDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.CONNECTOR_PORT: {
				ConnectorPort connectorPort = (ConnectorPort)theEObject;
				T result = caseConnectorPort(connectorPort);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.INTERACTION_ROLE: {
				InteractionRole interactionRole = (InteractionRole)theEObject;
				T result = caseInteractionRole(interactionRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.PORT_ELEMENT: {
				PortElement portElement = (PortElement)theEObject;
				T result = casePortElement(portElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.CONNECTOR_DEFINITION: {
				ConnectorDefinition connectorDefinition = (ConnectorDefinition)theEObject;
				T result = caseConnectorDefinition(connectorDefinition);
				if (result == null) result = caseIInteractionDefinition(connectorDefinition);
				if (result == null) result = caseIConfigurable(connectorDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.ITEM_BINDING: {
				ItemBinding itemBinding = (ItemBinding)theEObject;
				T result = caseItemBinding(itemBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.PORT_TYPE: {
				PortType portType = (PortType)theEObject;
				T result = casePortType(portType);
				if (result == null) result = caseIPortType(portType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.IPORT_TYPE: {
				IPortType iPortType = (IPortType)theEObject;
				T result = caseIPortType(iPortType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.CONNECTOR_PORT_CONFIGURATION: {
				ConnectorPortConfiguration connectorPortConfiguration = (ConnectorPortConfiguration)theEObject;
				T result = caseConnectorPortConfiguration(connectorPortConfiguration);
				if (result == null) result = caseIConfigurable(connectorPortConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_interactionsPackage.INTERACTION_ITEM: {
				InteractionItem interactionItem = (InteractionItem)theEObject;
				T result = caseInteractionItem(interactionItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction Definition Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction Definition Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteractionDefinitionModule(InteractionDefinitionModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteractionPattern(InteractionPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IInteraction Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IInteraction Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIInteractionDefinition(IInteractionDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectorPort(ConnectorPort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteractionRole(InteractionRole object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortElement(PortElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectorDefinition(ConnectorDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Item Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Item Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseItemBinding(ItemBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortType(PortType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IPort Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IPort Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIPortType(IPortType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector Port Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector Port Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectorPortConfiguration(ConnectorPortConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteractionItem(InteractionItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConfigurable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConfigurable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConfigurable(IConfigurable object) {
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

} //Ucm_interactionsSwitch
