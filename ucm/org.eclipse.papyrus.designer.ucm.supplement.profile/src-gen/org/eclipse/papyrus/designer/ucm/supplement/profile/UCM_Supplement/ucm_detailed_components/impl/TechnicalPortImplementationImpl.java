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
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Technical Port Implementation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.TechnicalPortImplementationImpl#getPolicy <em>Policy</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TechnicalPortImplementationImpl extends IPortElementImplementationImpl implements TechnicalPortImplementation {
	/**
	 * The cached value of the '{@link #getPolicy() <em>Policy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected TechnicalPolicy policy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TechnicalPortImplementationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_detailed_componentsPackage.Literals.TECHNICAL_PORT_IMPLEMENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- enbd-user-doc -->
	 * @generated
	 */
	public TechnicalPolicy getPolicy() {
		if (policy != null && policy.eIsProxy()) {
			InternalEObject oldPolicy = (InternalEObject)policy;
			policy = (TechnicalPolicy)eResolveProxy(oldPolicy);
			if (policy != oldPolicy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION__POLICY, oldPolicy, policy));
			}
		}
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalPolicy basicGetPolicy() {
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolicy(TechnicalPolicy newPolicy) {
		TechnicalPolicy oldPolicy = policy;
		policy = newPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION__POLICY, oldPolicy, policy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION__POLICY:
				if (resolve) return getPolicy();
				return basicGetPolicy();
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
			case Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION__POLICY:
				setPolicy((TechnicalPolicy)newValue);
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
			case Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION__POLICY:
				setPolicy((TechnicalPolicy)null);
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
			case Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION__POLICY:
				return policy != null;
		}
		return super.eIsSet(featureID);
	}

} //TechnicalPortImplementationImpl
