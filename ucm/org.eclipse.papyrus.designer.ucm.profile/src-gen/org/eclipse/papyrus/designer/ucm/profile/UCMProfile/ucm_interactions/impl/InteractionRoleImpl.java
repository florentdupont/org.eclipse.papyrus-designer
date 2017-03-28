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

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage;

import org.eclipse.uml2.uml.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interaction Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionRoleImpl#getInvolvedItem <em>Involved Item</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionRoleImpl#getBase_Property <em>Base Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InteractionRoleImpl extends MinimalEObjectImpl.Container implements InteractionRole {
	/**
	 * The cached value of the '{@link #getInvolvedItem() <em>Involved Item</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvolvedItem()
	 * @generated
	 * @ordered
	 */
	protected InteractionItem involvedItem;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InteractionRoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_interactionsPackage.Literals.INTERACTION_ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionItem getInvolvedItem() {
		if (involvedItem != null && involvedItem.eIsProxy()) {
			InternalEObject oldInvolvedItem = (InternalEObject)involvedItem;
			involvedItem = (InteractionItem)eResolveProxy(oldInvolvedItem);
			if (involvedItem != oldInvolvedItem) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_interactionsPackage.INTERACTION_ROLE__INVOLVED_ITEM, oldInvolvedItem, involvedItem));
			}
		}
		return involvedItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionItem basicGetInvolvedItem() {
		return involvedItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInvolvedItem(InteractionItem newInvolvedItem) {
		InteractionItem oldInvolvedItem = involvedItem;
		involvedItem = newInvolvedItem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_interactionsPackage.INTERACTION_ROLE__INVOLVED_ITEM, oldInvolvedItem, involvedItem));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_interactionsPackage.INTERACTION_ROLE__BASE_PROPERTY, oldBase_Property, base_Property));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_interactionsPackage.INTERACTION_ROLE__BASE_PROPERTY, oldBase_Property, base_Property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_interactionsPackage.INTERACTION_ROLE__INVOLVED_ITEM:
				if (resolve) return getInvolvedItem();
				return basicGetInvolvedItem();
			case Ucm_interactionsPackage.INTERACTION_ROLE__BASE_PROPERTY:
				if (resolve) return getBase_Property();
				return basicGetBase_Property();
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
			case Ucm_interactionsPackage.INTERACTION_ROLE__INVOLVED_ITEM:
				setInvolvedItem((InteractionItem)newValue);
				return;
			case Ucm_interactionsPackage.INTERACTION_ROLE__BASE_PROPERTY:
				setBase_Property((Property)newValue);
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
			case Ucm_interactionsPackage.INTERACTION_ROLE__INVOLVED_ITEM:
				setInvolvedItem((InteractionItem)null);
				return;
			case Ucm_interactionsPackage.INTERACTION_ROLE__BASE_PROPERTY:
				setBase_Property((Property)null);
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
			case Ucm_interactionsPackage.INTERACTION_ROLE__INVOLVED_ITEM:
				return involvedItem != null;
			case Ucm_interactionsPackage.INTERACTION_ROLE__BASE_PROPERTY:
				return base_Property != null;
		}
		return super.eIsSet(featureID);
	}

} //InteractionRoleImpl
