/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfiguredImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition;

import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Technical Policy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl#getManagedPort <em>Managed Port</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl#getManagePolicy <em>Manage Policy</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TechnicalPolicyImpl extends IConfiguredImpl implements TechnicalPolicy {
	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected TechnicalPolicyDefinition definition;

	/**
	 * The cached value of the '{@link #getBase_Class() <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_Class()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.Class base_Class;

	/**
	 * The cached value of the '{@link #getManagedPort() <em>Managed Port</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagedPort()
	 * @generated
	 * @ordered
	 */
	protected EList<Port> managedPort;

	/**
	 * The cached value of the '{@link #getManagePolicy() <em>Manage Policy</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagePolicy()
	 * @generated
	 * @ordered
	 */
	protected EList<TechnicalPolicy> managePolicy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TechnicalPolicyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_componentsPackage.Literals.TECHNICAL_POLICY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalPolicyDefinition getDefinition() {
		if (definition != null && definition.eIsProxy()) {
			InternalEObject oldDefinition = (InternalEObject)definition;
			definition = (TechnicalPolicyDefinition)eResolveProxy(oldDefinition);
			if (definition != oldDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_componentsPackage.TECHNICAL_POLICY__DEFINITION, oldDefinition, definition));
			}
		}
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalPolicyDefinition basicGetDefinition() {
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinition(TechnicalPolicyDefinition newDefinition) {
		TechnicalPolicyDefinition oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_componentsPackage.TECHNICAL_POLICY__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class getBase_Class() {
		if (base_Class != null && base_Class.eIsProxy()) {
			InternalEObject oldBase_Class = (InternalEObject)base_Class;
			base_Class = (org.eclipse.uml2.uml.Class)eResolveProxy(oldBase_Class);
			if (base_Class != oldBase_Class) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_componentsPackage.TECHNICAL_POLICY__BASE_CLASS, oldBase_Class, base_Class));
			}
		}
		return base_Class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class basicGetBase_Class() {
		return base_Class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_Class(org.eclipse.uml2.uml.Class newBase_Class) {
		org.eclipse.uml2.uml.Class oldBase_Class = base_Class;
		base_Class = newBase_Class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_componentsPackage.TECHNICAL_POLICY__BASE_CLASS, oldBase_Class, base_Class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Port> getManagedPort() {
		if (managedPort == null) {
			managedPort = new EObjectResolvingEList<Port>(Port.class, this, Ucm_componentsPackage.TECHNICAL_POLICY__MANAGED_PORT);
		}
		return managedPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port getManagedPort(String name, Type type) {
		return getManagedPort(name, type, false);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port getManagedPort(String name, Type type, boolean ignoreCase) {
		managedPortLoop: for (Port managedPort : getManagedPort()) {
			if (name != null && !(ignoreCase ? name.equalsIgnoreCase(managedPort.getName()) : name.equals(managedPort.getName())))
				continue managedPortLoop;
			if (type != null && !type.equals(managedPort.getType()))
				continue managedPortLoop;
			return managedPort;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TechnicalPolicy> getManagePolicy() {
		if (managePolicy == null) {
			managePolicy = new EObjectResolvingEList<TechnicalPolicy>(TechnicalPolicy.class, this, Ucm_componentsPackage.TECHNICAL_POLICY__MANAGE_POLICY);
		}
		return managePolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_componentsPackage.TECHNICAL_POLICY__DEFINITION:
				if (resolve) return getDefinition();
				return basicGetDefinition();
			case Ucm_componentsPackage.TECHNICAL_POLICY__BASE_CLASS:
				if (resolve) return getBase_Class();
				return basicGetBase_Class();
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGED_PORT:
				return getManagedPort();
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGE_POLICY:
				return getManagePolicy();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Ucm_componentsPackage.TECHNICAL_POLICY__DEFINITION:
				setDefinition((TechnicalPolicyDefinition)newValue);
				return;
			case Ucm_componentsPackage.TECHNICAL_POLICY__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)newValue);
				return;
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGED_PORT:
				getManagedPort().clear();
				getManagedPort().addAll((Collection<? extends Port>)newValue);
				return;
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGE_POLICY:
				getManagePolicy().clear();
				getManagePolicy().addAll((Collection<? extends TechnicalPolicy>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Ucm_componentsPackage.TECHNICAL_POLICY__DEFINITION:
				setDefinition((TechnicalPolicyDefinition)null);
				return;
			case Ucm_componentsPackage.TECHNICAL_POLICY__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)null);
				return;
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGED_PORT:
				getManagedPort().clear();
				return;
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGE_POLICY:
				getManagePolicy().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Ucm_componentsPackage.TECHNICAL_POLICY__DEFINITION:
				return definition != null;
			case Ucm_componentsPackage.TECHNICAL_POLICY__BASE_CLASS:
				return base_Class != null;
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGED_PORT:
				return managedPort != null && !managedPort.isEmpty();
			case Ucm_componentsPackage.TECHNICAL_POLICY__MANAGE_POLICY:
				return managePolicy != null && !managePolicy.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TechnicalPolicyImpl
