/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_interactionsFactoryImpl extends EFactoryImpl implements Ucm_interactionsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_interactionsFactory init() {
		try {
			Ucm_interactionsFactory theUcm_interactionsFactory = (Ucm_interactionsFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_interactionsPackage.eNS_URI);
			if (theUcm_interactionsFactory != null) {
				return theUcm_interactionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_interactionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_interactionsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Ucm_interactionsPackage.INTERACTION_DEFINITION_MODULE: return createInteractionDefinitionModule();
			case Ucm_interactionsPackage.INTERACTION_PATTERN: return createInteractionPattern();
			case Ucm_interactionsPackage.CONNECTOR_PORT: return createConnectorPort();
			case Ucm_interactionsPackage.INTERACTION_ROLE: return createInteractionRole();
			case Ucm_interactionsPackage.PORT_ELEMENT: return createPortElement();
			case Ucm_interactionsPackage.CONNECTOR_DEFINITION: return createConnectorDefinition();
			case Ucm_interactionsPackage.ITEM_BINDING: return createItemBinding();
			case Ucm_interactionsPackage.PORT_TYPE: return createPortType();
			case Ucm_interactionsPackage.CONNECTOR_PORT_CONFIGURATION: return createConnectorPortConfiguration();
			case Ucm_interactionsPackage.INTERACTION_ITEM: return createInteractionItem();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case Ucm_interactionsPackage.PORT_ELEMENT_KIND:
				return createPortElementKindFromString(eDataType, initialValue);
			case Ucm_interactionsPackage.INTERACTION_ITEM_KIND:
				return createInteractionItemKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case Ucm_interactionsPackage.PORT_ELEMENT_KIND:
				return convertPortElementKindToString(eDataType, instanceValue);
			case Ucm_interactionsPackage.INTERACTION_ITEM_KIND:
				return convertInteractionItemKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionDefinitionModule createInteractionDefinitionModule() {
		InteractionDefinitionModuleImpl interactionDefinitionModule = new InteractionDefinitionModuleImpl();
		return interactionDefinitionModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionPattern createInteractionPattern() {
		InteractionPatternImpl interactionPattern = new InteractionPatternImpl();
		return interactionPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorPort createConnectorPort() {
		ConnectorPortImpl connectorPort = new ConnectorPortImpl();
		return connectorPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionRole createInteractionRole() {
		InteractionRoleImpl interactionRole = new InteractionRoleImpl();
		return interactionRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortElement createPortElement() {
		PortElementImpl portElement = new PortElementImpl();
		return portElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorDefinition createConnectorDefinition() {
		ConnectorDefinitionImpl connectorDefinition = new ConnectorDefinitionImpl();
		return connectorDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemBinding createItemBinding() {
		ItemBindingImpl itemBinding = new ItemBindingImpl();
		return itemBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortType createPortType() {
		PortTypeImpl portType = new PortTypeImpl();
		return portType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorPortConfiguration createConnectorPortConfiguration() {
		ConnectorPortConfigurationImpl connectorPortConfiguration = new ConnectorPortConfigurationImpl();
		return connectorPortConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionItem createInteractionItem() {
		InteractionItemImpl interactionItem = new InteractionItemImpl();
		return interactionItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortElementKind createPortElementKindFromString(EDataType eDataType, String initialValue) {
		PortElementKind result = PortElementKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPortElementKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionItemKind createInteractionItemKindFromString(EDataType eDataType, String initialValue) {
		InteractionItemKind result = InteractionItemKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInteractionItemKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_interactionsPackage getUcm_interactionsPackage() {
		return (Ucm_interactionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_interactionsPackage getPackage() {
		return Ucm_interactionsPackage.eINSTANCE;
	}

} //Ucm_interactionsFactoryImpl
