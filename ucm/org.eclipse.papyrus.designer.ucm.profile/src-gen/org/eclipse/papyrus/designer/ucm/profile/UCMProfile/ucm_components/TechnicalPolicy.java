/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components;

import org.eclipse.emf.common.util.EList;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition;

import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Technical Policy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * managedComponent is computed from
 * the association between componentTechnicalPolicy
 *  and AtomicComponentImplementation
 * policy is computed from
 * the association between componentTechnicalPolicy
 * and AtomicComponentImplementation
 * managedPort and managedPolicy are optional. The former is only used in case of applicability = "ON_SOME_PORTS"
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getManagedPort <em>Managed Port</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getManagePolicy <em>Manage Policy</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getTechnicalPolicy()
 * @model
 * @generated
 */
public interface TechnicalPolicy extends IConfigured {
	/**
	 * Returns the value of the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' reference.
	 * @see #setDefinition(TechnicalPolicyDefinition)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getTechnicalPolicy_Definition()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	TechnicalPolicyDefinition getDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getDefinition <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(TechnicalPolicyDefinition value);

	/**
	 * Returns the value of the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Class</em>' reference.
	 * @see #setBase_Class(org.eclipse.uml2.uml.Class)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getTechnicalPolicy_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

	/**
	 * Returns the value of the '<em><b>Managed Port</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Port}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Port</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Managed Port</em>' reference list.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getTechnicalPolicy_ManagedPort()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Port> getManagedPort();

	/**
	 * Retrieves the first {@link org.eclipse.uml2.uml.Port} with the specified '<em><b>Name</b></em>', and '<em><b>Type</b></em>' from the '<em><b>Managed Port</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name The '<em><b>Name</b></em>' of the {@link org.eclipse.uml2.uml.Port} to retrieve, or <code>null</code>.
	 * @param type The '<em><b>Type</b></em>' of the {@link org.eclipse.uml2.uml.Port} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.uml2.uml.Port} with the specified '<em><b>Name</b></em>', and '<em><b>Type</b></em>', or <code>null</code>.
	 * @see #getManagedPort()
	 * @generated
	 */
	Port getManagedPort(String name, Type type);

	/**
	 * Retrieves the first {@link org.eclipse.uml2.uml.Port} with the specified '<em><b>Name</b></em>', and '<em><b>Type</b></em>' from the '<em><b>Managed Port</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name The '<em><b>Name</b></em>' of the {@link org.eclipse.uml2.uml.Port} to retrieve, or <code>null</code>.
	 * @param type The '<em><b>Type</b></em>' of the {@link org.eclipse.uml2.uml.Port} to retrieve, or <code>null</code>.
	 * @param ignoreCase Whether to ignore case in {@link java.lang.String} comparisons.
	 * @return The first {@link org.eclipse.uml2.uml.Port} with the specified '<em><b>Name</b></em>', and '<em><b>Type</b></em>', or <code>null</code>.
	 * @see #getManagedPort()
	 * @generated
	 */
	Port getManagedPort(String name, Type type, boolean ignoreCase);

	/**
	 * Returns the value of the '<em><b>Manage Policy</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manage Policy</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manage Policy</em>' reference list.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getTechnicalPolicy_ManagePolicy()
	 * @model ordered="false"
	 * @generated
	 */
	EList<TechnicalPolicy> getManagePolicy();

} // TechnicalPolicy
