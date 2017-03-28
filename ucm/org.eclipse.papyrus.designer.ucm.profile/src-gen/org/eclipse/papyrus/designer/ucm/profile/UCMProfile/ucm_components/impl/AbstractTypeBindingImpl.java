/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Type Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AbstractTypeBindingImpl#getAbstractType <em>Abstract Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbstractTypeBindingImpl extends IBindingImpl implements AbstractTypeBinding {
	/**
	 * The cached value of the '{@link #getAbstractType() <em>Abstract Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractType()
	 * @generated
	 * @ordered
	 */
	protected AbstractDeclaration abstractType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractTypeBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_componentsPackage.Literals.ABSTRACT_TYPE_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractDeclaration getAbstractType() {
		if (abstractType != null && abstractType.eIsProxy()) {
			InternalEObject oldAbstractType = (InternalEObject)abstractType;
			abstractType = (AbstractDeclaration)eResolveProxy(oldAbstractType);
			if (abstractType != oldAbstractType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_componentsPackage.ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE, oldAbstractType, abstractType));
			}
		}
		return abstractType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractDeclaration basicGetAbstractType() {
		return abstractType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstractType(AbstractDeclaration newAbstractType) {
		AbstractDeclaration oldAbstractType = abstractType;
		abstractType = newAbstractType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_componentsPackage.ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE, oldAbstractType, abstractType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_componentsPackage.ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE:
				if (resolve) return getAbstractType();
				return basicGetAbstractType();
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
			case Ucm_componentsPackage.ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE:
				setAbstractType((AbstractDeclaration)newValue);
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
			case Ucm_componentsPackage.ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE:
				setAbstractType((AbstractDeclaration)null);
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
			case Ucm_componentsPackage.ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE:
				return abstractType != null;
		}
		return super.eIsSet(featureID);
	}

} //AbstractTypeBindingImpl
