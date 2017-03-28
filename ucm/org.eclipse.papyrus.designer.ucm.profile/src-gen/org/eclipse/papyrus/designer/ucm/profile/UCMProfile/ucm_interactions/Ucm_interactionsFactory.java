/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage
 * @generated
 */
public interface Ucm_interactionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_interactionsFactory eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Interaction Definition Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction Definition Module</em>'.
	 * @generated
	 */
	InteractionDefinitionModule createInteractionDefinitionModule();

	/**
	 * Returns a new object of class '<em>Interaction Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction Pattern</em>'.
	 * @generated
	 */
	InteractionPattern createInteractionPattern();

	/**
	 * Returns a new object of class '<em>Connector Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Port</em>'.
	 * @generated
	 */
	ConnectorPort createConnectorPort();

	/**
	 * Returns a new object of class '<em>Interaction Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction Role</em>'.
	 * @generated
	 */
	InteractionRole createInteractionRole();

	/**
	 * Returns a new object of class '<em>Port Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Element</em>'.
	 * @generated
	 */
	PortElement createPortElement();

	/**
	 * Returns a new object of class '<em>Connector Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Definition</em>'.
	 * @generated
	 */
	ConnectorDefinition createConnectorDefinition();

	/**
	 * Returns a new object of class '<em>Item Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Item Binding</em>'.
	 * @generated
	 */
	ItemBinding createItemBinding();

	/**
	 * Returns a new object of class '<em>Port Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Type</em>'.
	 * @generated
	 */
	PortType createPortType();

	/**
	 * Returns a new object of class '<em>Connector Port Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Port Configuration</em>'.
	 * @generated
	 */
	ConnectorPortConfiguration createConnectorPortConfiguration();

	/**
	 * Returns a new object of class '<em>Interaction Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction Item</em>'.
	 * @generated
	 */
	InteractionItem createInteractionItem();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Ucm_interactionsPackage getUcm_interactionsPackage();

} //Ucm_interactionsFactory
