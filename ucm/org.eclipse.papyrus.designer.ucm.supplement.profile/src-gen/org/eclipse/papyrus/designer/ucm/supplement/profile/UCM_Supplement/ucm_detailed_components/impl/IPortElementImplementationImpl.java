/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IPort Element Implementation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.IPortElementImplementationImpl#getPortElement <em>Port Element</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.IPortElementImplementationImpl#getBase_Class <em>Base Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class IPortElementImplementationImpl extends MinimalEObjectImpl.Container implements IPortElementImplementation {
	/**
	 * The cached value of the '{@link #getPortElement() <em>Port Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortElement()
	 * @generated
	 * @ordered
	 */
	protected PortElement portElement;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IPortElementImplementationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_detailed_componentsPackage.Literals.IPORT_ELEMENT_IMPLEMENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortElement getPortElement() {
		if (portElement != null && portElement.eIsProxy()) {
			InternalEObject oldPortElement = (InternalEObject)portElement;
			portElement = (PortElement)eResolveProxy(oldPortElement);
			if (portElement != oldPortElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT, oldPortElement, portElement));
			}
		}
		return portElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortElement basicGetPortElement() {
		return portElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortElement(PortElement newPortElement) {
		PortElement oldPortElement = portElement;
		portElement = newPortElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT, oldPortElement, portElement));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS, oldBase_Class, base_Class));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS, oldBase_Class, base_Class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT:
				if (resolve) return getPortElement();
				return basicGetPortElement();
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS:
				if (resolve) return getBase_Class();
				return basicGetBase_Class();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT:
				setPortElement((PortElement)newValue);
				return;
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)newValue);
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
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT:
				setPortElement((PortElement)null);
				return;
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)null);
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
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT:
				return portElement != null;
			case Ucm_detailed_componentsPackage.IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS:
				return base_Class != null;
		}
		return super.eIsSet(featureID);
	}

} //IPortElementImplementationImpl
