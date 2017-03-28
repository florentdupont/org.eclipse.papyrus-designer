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

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage;

import org.eclipse.uml2.uml.DataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Alias</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.AliasImpl#getAliasedType <em>Aliased Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AliasImpl extends IStandardDataTypeImpl implements Alias {
	/**
	 * The cached value of the '{@link #getAliasedType() <em>Aliased Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAliasedType()
	 * @generated
	 * @ordered
	 */
	protected DataType aliasedType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AliasImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.ALIAS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getAliasedType() {
		if (aliasedType != null && aliasedType.eIsProxy()) {
			InternalEObject oldAliasedType = (InternalEObject)aliasedType;
			aliasedType = (DataType)eResolveProxy(oldAliasedType);
			if (aliasedType != oldAliasedType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.ALIAS__ALIASED_TYPE, oldAliasedType, aliasedType));
			}
		}
		return aliasedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType basicGetAliasedType() {
		return aliasedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAliasedType(DataType newAliasedType) {
		DataType oldAliasedType = aliasedType;
		aliasedType = newAliasedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.ALIAS__ALIASED_TYPE, oldAliasedType, aliasedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.ALIAS__ALIASED_TYPE:
				if (resolve) return getAliasedType();
				return basicGetAliasedType();
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
			case TypesPackage.ALIAS__ALIASED_TYPE:
				setAliasedType((DataType)newValue);
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
			case TypesPackage.ALIAS__ALIASED_TYPE:
				setAliasedType((DataType)null);
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
			case TypesPackage.ALIAS__ALIASED_TYPE:
				return aliasedType != null;
		}
		return super.eIsSet(featureID);
	}

} //AliasImpl
