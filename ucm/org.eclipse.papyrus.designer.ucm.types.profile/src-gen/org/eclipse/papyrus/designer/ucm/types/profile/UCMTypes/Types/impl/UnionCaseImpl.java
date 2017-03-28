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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase;

import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union Case</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionCaseImpl#getBase_Property <em>Base Property</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionCaseImpl#getSelectorValue <em>Selector Value</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionCaseImpl#isDefaultCase <em>Is Default Case</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnionCaseImpl extends MinimalEObjectImpl.Container implements UnionCase {
	/**
	 * The cached value of the '{@link #getBase_Property() <em>Base Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_Property()
	 * @generated
	 * @ordered
	 */
	protected Property base_Property;

	/**
	 * The cached value of the '{@link #getSelectorValue() <em>Selector Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectorValue()
	 * @generated
	 * @ordered
	 */
	protected EnumerationLiteral selectorValue;

	/**
	 * The default value of the '{@link #isDefaultCase() <em>Is Default Case</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultCase()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_DEFAULT_CASE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDefaultCase() <em>Is Default Case</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultCase()
	 * @generated
	 * @ordered
	 */
	protected boolean isDefaultCase = IS_DEFAULT_CASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionCaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.UNION_CASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getBase_Property() {
		if (base_Property != null && base_Property.eIsProxy()) {
			InternalEObject oldBase_Property = (InternalEObject)base_Property;
			base_Property = (Property)eResolveProxy(oldBase_Property);
			if (base_Property != oldBase_Property) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.UNION_CASE__BASE_PROPERTY, oldBase_Property, base_Property));
			}
		}
		return base_Property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetBase_Property() {
		return base_Property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_Property(Property newBase_Property) {
		Property oldBase_Property = base_Property;
		base_Property = newBase_Property;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.UNION_CASE__BASE_PROPERTY, oldBase_Property, base_Property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationLiteral getSelectorValue() {
		if (selectorValue != null && selectorValue.eIsProxy()) {
			InternalEObject oldSelectorValue = (InternalEObject)selectorValue;
			selectorValue = (EnumerationLiteral)eResolveProxy(oldSelectorValue);
			if (selectorValue != oldSelectorValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.UNION_CASE__SELECTOR_VALUE, oldSelectorValue, selectorValue));
			}
		}
		return selectorValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationLiteral basicGetSelectorValue() {
		return selectorValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelectorValue(EnumerationLiteral newSelectorValue) {
		EnumerationLiteral oldSelectorValue = selectorValue;
		selectorValue = newSelectorValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.UNION_CASE__SELECTOR_VALUE, oldSelectorValue, selectorValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefaultCase() {
		return isDefaultCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsDefaultCase(boolean newIsDefaultCase) {
		boolean oldIsDefaultCase = isDefaultCase;
		isDefaultCase = newIsDefaultCase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.UNION_CASE__IS_DEFAULT_CASE, oldIsDefaultCase, isDefaultCase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.UNION_CASE__BASE_PROPERTY:
				if (resolve) return getBase_Property();
				return basicGetBase_Property();
			case TypesPackage.UNION_CASE__SELECTOR_VALUE:
				if (resolve) return getSelectorValue();
				return basicGetSelectorValue();
			case TypesPackage.UNION_CASE__IS_DEFAULT_CASE:
				return isDefaultCase();
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
			case TypesPackage.UNION_CASE__BASE_PROPERTY:
				setBase_Property((Property)newValue);
				return;
			case TypesPackage.UNION_CASE__SELECTOR_VALUE:
				setSelectorValue((EnumerationLiteral)newValue);
				return;
			case TypesPackage.UNION_CASE__IS_DEFAULT_CASE:
				setIsDefaultCase((Boolean)newValue);
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
			case TypesPackage.UNION_CASE__BASE_PROPERTY:
				setBase_Property((Property)null);
				return;
			case TypesPackage.UNION_CASE__SELECTOR_VALUE:
				setSelectorValue((EnumerationLiteral)null);
				return;
			case TypesPackage.UNION_CASE__IS_DEFAULT_CASE:
				setIsDefaultCase(IS_DEFAULT_CASE_EDEFAULT);
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
			case TypesPackage.UNION_CASE__BASE_PROPERTY:
				return base_Property != null;
			case TypesPackage.UNION_CASE__SELECTOR_VALUE:
				return selectorValue != null;
			case TypesPackage.UNION_CASE__IS_DEFAULT_CASE:
				return isDefaultCase != IS_DEFAULT_CASE_EDEFAULT;
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
		result.append(" (isDefaultCase: ");
		result.append(isDefaultCase);
		result.append(')');
		return result.toString();
	}

} //UnionCaseImpl
