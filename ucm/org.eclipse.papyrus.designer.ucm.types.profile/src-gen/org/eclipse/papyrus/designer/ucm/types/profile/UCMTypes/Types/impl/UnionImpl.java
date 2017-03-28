/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union;

import org.eclipse.uml2.uml.Enumeration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionImpl#getSelectorName <em>Selector Name</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionImpl#getSelectorType <em>Selector Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnionImpl extends IStandardDataTypeImpl implements Union {
	/**
	 * The default value of the '{@link #getSelectorName() <em>Selector Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectorName()
	 * @generated
	 * @ordered
	 */
	protected static final String SELECTOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSelectorName() <em>Selector Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectorName()
	 * @generated
	 * @ordered
	 */
	protected String selectorName = SELECTOR_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSelectorType() <em>Selector Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectorType()
	 * @generated
	 * @ordered
	 */
	protected Enumeration selectorType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.UNION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSelectorName() {
		return selectorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelectorName(String newSelectorName) {
		String oldSelectorName = selectorName;
		selectorName = newSelectorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.UNION__SELECTOR_NAME, oldSelectorName, selectorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration getSelectorType() {
		if (selectorType != null && selectorType.eIsProxy()) {
			InternalEObject oldSelectorType = (InternalEObject)selectorType;
			selectorType = (Enumeration)eResolveProxy(oldSelectorType);
			if (selectorType != oldSelectorType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.UNION__SELECTOR_TYPE, oldSelectorType, selectorType));
			}
		}
		return selectorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration basicGetSelectorType() {
		return selectorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelectorType(Enumeration newSelectorType) {
		Enumeration oldSelectorType = selectorType;
		selectorType = newSelectorType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.UNION__SELECTOR_TYPE, oldSelectorType, selectorType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.UNION__SELECTOR_NAME:
				return getSelectorName();
			case TypesPackage.UNION__SELECTOR_TYPE:
				if (resolve) return getSelectorType();
				return basicGetSelectorType();
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
			case TypesPackage.UNION__SELECTOR_NAME:
				setSelectorName((String)newValue);
				return;
			case TypesPackage.UNION__SELECTOR_TYPE:
				setSelectorType((Enumeration)newValue);
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
			case TypesPackage.UNION__SELECTOR_NAME:
				setSelectorName(SELECTOR_NAME_EDEFAULT);
				return;
			case TypesPackage.UNION__SELECTOR_TYPE:
				setSelectorType((Enumeration)null);
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
			case TypesPackage.UNION__SELECTOR_NAME:
				return SELECTOR_NAME_EDEFAULT == null ? selectorName != null : !SELECTOR_NAME_EDEFAULT.equals(selectorName);
			case TypesPackage.UNION__SELECTOR_TYPE:
				return selectorType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (selectorName: ");
		result.append(selectorName);
		result.append(')');
		return result.toString();
	}

} //UnionImpl
