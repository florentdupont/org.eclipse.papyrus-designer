/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AtomicComponentImplementationImpl;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Detailed Component Implementation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.DetailedComponentImplementationImpl#getPortImplementation <em>Port Implementation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DetailedComponentImplementationImpl extends AtomicComponentImplementationImpl implements DetailedComponentImplementation {
	/**
	 * The cached value of the '{@link #getPortImplementation() <em>Port Implementation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortImplementation()
	 * @generated
	 * @ordered
	 */
	protected EList<IPortElementImplementation> portImplementation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DetailedComponentImplementationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_detailed_componentsPackage.Literals.DETAILED_COMPONENT_IMPLEMENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IPortElementImplementation> getPortImplementation() {
		if (portImplementation == null) {
			portImplementation = new EObjectContainmentEList<IPortElementImplementation>(IPortElementImplementation.class, this, Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION);
		}
		return portImplementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION:
				return ((InternalEList<?>)getPortImplementation()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION:
				return getPortImplementation();
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
			case Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION:
				getPortImplementation().clear();
				getPortImplementation().addAll((Collection<? extends IPortElementImplementation>)newValue);
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
			case Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION:
				getPortImplementation().clear();
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
			case Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION:
				return portImplementation != null && !portImplementation.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DetailedComponentImplementationImpl
