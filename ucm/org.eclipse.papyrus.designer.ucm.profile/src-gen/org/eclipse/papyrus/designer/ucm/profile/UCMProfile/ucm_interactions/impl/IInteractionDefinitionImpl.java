/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage;
import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IInteraction Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IInteractionDefinitionImpl#getBase_StructuredClassifier <em>Base Structured Classifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class IInteractionDefinitionImpl extends MinimalEObjectImpl.Container implements IInteractionDefinition {
	/**
	 * The cached value of the '{@link #getBase_StructuredClassifier() <em>Base Structured Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_StructuredClassifier()
	 * @generated
	 * @ordered
	 */
	protected StructuredClassifier base_StructuredClassifier;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IInteractionDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_interactionsPackage.Literals.IINTERACTION_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredClassifier getBase_StructuredClassifier() {
		if (base_StructuredClassifier != null && base_StructuredClassifier.eIsProxy()) {
			InternalEObject oldBase_StructuredClassifier = (InternalEObject)base_StructuredClassifier;
			base_StructuredClassifier = (StructuredClassifier)eResolveProxy(oldBase_StructuredClassifier);
			if (base_StructuredClassifier != oldBase_StructuredClassifier) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_interactionsPackage.IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER, oldBase_StructuredClassifier, base_StructuredClassifier));
			}
		}
		return base_StructuredClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredClassifier basicGetBase_StructuredClassifier() {
		return base_StructuredClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_StructuredClassifier(StructuredClassifier newBase_StructuredClassifier) {
		StructuredClassifier oldBase_StructuredClassifier = base_StructuredClassifier;
		base_StructuredClassifier = newBase_StructuredClassifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_interactionsPackage.IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER, oldBase_StructuredClassifier, base_StructuredClassifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_interactionsPackage.IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER:
				if (resolve) return getBase_StructuredClassifier();
				return basicGetBase_StructuredClassifier();
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
			case Ucm_interactionsPackage.IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER:
				setBase_StructuredClassifier((StructuredClassifier)newValue);
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
			case Ucm_interactionsPackage.IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER:
				setBase_StructuredClassifier((StructuredClassifier)null);
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
			case Ucm_interactionsPackage.IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER:
				return base_StructuredClassifier != null;
		}
		return super.eIsSet(featureID);
	}

} //IInteractionDefinitionImpl
