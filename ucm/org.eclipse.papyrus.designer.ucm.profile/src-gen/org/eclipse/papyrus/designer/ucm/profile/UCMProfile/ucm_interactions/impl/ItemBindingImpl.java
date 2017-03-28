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
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage;

import org.eclipse.uml2.uml.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ItemBindingImpl#getBase_Property <em>Base Property</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ItemBindingImpl#getInteractionItem <em>Interaction Item</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ItemBindingImpl extends MinimalEObjectImpl.Container implements ItemBinding {
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
	 * The cached value of the '{@link #getInteractionItem() <em>Interaction Item</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteractionItem()
	 * @generated
	 * @ordered
	 */
	protected InteractionItem interactionItem;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ItemBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_interactionsPackage.Literals.ITEM_BINDING;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_interactionsPackage.ITEM_BINDING__BASE_PROPERTY, oldBase_Property, base_Property));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_interactionsPackage.ITEM_BINDING__BASE_PROPERTY, oldBase_Property, base_Property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionItem getInteractionItem() {
		if (interactionItem != null && interactionItem.eIsProxy()) {
			InternalEObject oldInteractionItem = (InternalEObject)interactionItem;
			interactionItem = (InteractionItem)eResolveProxy(oldInteractionItem);
			if (interactionItem != oldInteractionItem) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_interactionsPackage.ITEM_BINDING__INTERACTION_ITEM, oldInteractionItem, interactionItem));
			}
		}
		return interactionItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionItem basicGetInteractionItem() {
		return interactionItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInteractionItem(InteractionItem newInteractionItem) {
		InteractionItem oldInteractionItem = interactionItem;
		interactionItem = newInteractionItem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_interactionsPackage.ITEM_BINDING__INTERACTION_ITEM, oldInteractionItem, interactionItem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_interactionsPackage.ITEM_BINDING__BASE_PROPERTY:
				if (resolve) return getBase_Property();
				return basicGetBase_Property();
			case Ucm_interactionsPackage.ITEM_BINDING__INTERACTION_ITEM:
				if (resolve) return getInteractionItem();
				return basicGetInteractionItem();
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
			case Ucm_interactionsPackage.ITEM_BINDING__BASE_PROPERTY:
				setBase_Property((Property)newValue);
				return;
			case Ucm_interactionsPackage.ITEM_BINDING__INTERACTION_ITEM:
				setInteractionItem((InteractionItem)newValue);
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
			case Ucm_interactionsPackage.ITEM_BINDING__BASE_PROPERTY:
				setBase_Property((Property)null);
				return;
			case Ucm_interactionsPackage.ITEM_BINDING__INTERACTION_ITEM:
				setInteractionItem((InteractionItem)null);
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
			case Ucm_interactionsPackage.ITEM_BINDING__BASE_PROPERTY:
				return base_Property != null;
			case Ucm_interactionsPackage.ITEM_BINDING__INTERACTION_ITEM:
				return interactionItem != null;
		}
		return super.eIsSet(featureID);
	}

} //ItemBindingImpl
