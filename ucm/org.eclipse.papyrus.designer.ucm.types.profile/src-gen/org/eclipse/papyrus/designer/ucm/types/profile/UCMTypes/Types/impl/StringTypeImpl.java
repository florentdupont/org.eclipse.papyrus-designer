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

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IDataType;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IStandardDataType;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StringType;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage;

import org.eclipse.uml2.uml.DataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StringTypeImpl#getBase_DataType <em>Base Data Type</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StringTypeImpl#getCharBase <em>Char Base</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StringTypeImpl extends IResizableImpl implements StringType {
	/**
	 * The cached value of the '{@link #getBase_DataType() <em>Base Data Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_DataType()
	 * @generated
	 * @ordered
	 */
	protected DataType base_DataType;

	/**
	 * The default value of the '{@link #getCharBase() <em>Char Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharBase()
	 * @generated
	 * @ordered
	 */
	protected static final PrimitiveCharKind CHAR_BASE_EDEFAULT = PrimitiveCharKind.CHAR;

	/**
	 * The cached value of the '{@link #getCharBase() <em>Char Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharBase()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveCharKind charBase = CHAR_BASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.STRING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType getBase_DataType() {
		if (base_DataType != null && base_DataType.eIsProxy()) {
			InternalEObject oldBase_DataType = (InternalEObject)base_DataType;
			base_DataType = (DataType)eResolveProxy(oldBase_DataType);
			if (base_DataType != oldBase_DataType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.STRING_TYPE__BASE_DATA_TYPE, oldBase_DataType, base_DataType));
			}
		}
		return base_DataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType basicGetBase_DataType() {
		return base_DataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_DataType(DataType newBase_DataType) {
		DataType oldBase_DataType = base_DataType;
		base_DataType = newBase_DataType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.STRING_TYPE__BASE_DATA_TYPE, oldBase_DataType, base_DataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveCharKind getCharBase() {
		return charBase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharBase(PrimitiveCharKind newCharBase) {
		PrimitiveCharKind oldCharBase = charBase;
		charBase = newCharBase == null ? CHAR_BASE_EDEFAULT : newCharBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.STRING_TYPE__CHAR_BASE, oldCharBase, charBase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.STRING_TYPE__BASE_DATA_TYPE:
				if (resolve) return getBase_DataType();
				return basicGetBase_DataType();
			case TypesPackage.STRING_TYPE__CHAR_BASE:
				return getCharBase();
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
			case TypesPackage.STRING_TYPE__BASE_DATA_TYPE:
				setBase_DataType((DataType)newValue);
				return;
			case TypesPackage.STRING_TYPE__CHAR_BASE:
				setCharBase((PrimitiveCharKind)newValue);
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
			case TypesPackage.STRING_TYPE__BASE_DATA_TYPE:
				setBase_DataType((DataType)null);
				return;
			case TypesPackage.STRING_TYPE__CHAR_BASE:
				setCharBase(CHAR_BASE_EDEFAULT);
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
			case TypesPackage.STRING_TYPE__BASE_DATA_TYPE:
				return base_DataType != null;
			case TypesPackage.STRING_TYPE__CHAR_BASE:
				return charBase != CHAR_BASE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IDataType.class) {
			switch (derivedFeatureID) {
				case TypesPackage.STRING_TYPE__BASE_DATA_TYPE: return TypesPackage.IDATA_TYPE__BASE_DATA_TYPE;
				default: return -1;
			}
		}
		if (baseClass == IStandardDataType.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IDataType.class) {
			switch (baseFeatureID) {
				case TypesPackage.IDATA_TYPE__BASE_DATA_TYPE: return TypesPackage.STRING_TYPE__BASE_DATA_TYPE;
				default: return -1;
			}
		}
		if (baseClass == IStandardDataType.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (charBase: ");
		result.append(charBase);
		result.append(')');
		return result.toString();
	}

} //StringTypeImpl
