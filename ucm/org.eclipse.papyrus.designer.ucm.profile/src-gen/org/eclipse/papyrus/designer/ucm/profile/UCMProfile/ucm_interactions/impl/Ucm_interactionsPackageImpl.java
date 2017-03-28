/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.impl.UCMProfilePackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorDefinition;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItemKind;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionPattern;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsFactory;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_interactionsPackageImpl extends EPackageImpl implements Ucm_interactionsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionDefinitionModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iInteractionDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorPortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itemBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPortTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorPortConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum portElementKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum interactionItemKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ucm_interactionsPackageImpl() {
		super(eNS_URI, Ucm_interactionsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link Ucm_interactionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ucm_interactionsPackage init() {
		if (isInited) return (Ucm_interactionsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI);

		// Obtain or create and register package
		Ucm_interactionsPackageImpl theUcm_interactionsPackage = (Ucm_interactionsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ucm_interactionsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ucm_interactionsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCMProfilePackageImpl theUCMProfilePackage = (UCMProfilePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) instanceof UCMProfilePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) : UCMProfilePackage.eINSTANCE);
		Ucm_componentsPackageImpl theUcm_componentsPackage = (Ucm_componentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) instanceof Ucm_componentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) : Ucm_componentsPackage.eINSTANCE);
		Ucm_contractsPackageImpl theUcm_contractsPackage = (Ucm_contractsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) instanceof Ucm_contractsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) : Ucm_contractsPackage.eINSTANCE);
		Ucm_technicalpoliciesPackageImpl theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) instanceof Ucm_technicalpoliciesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) : Ucm_technicalpoliciesPackage.eINSTANCE);
		Ucm_commonsPackageImpl theUcm_commonsPackage = (Ucm_commonsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) instanceof Ucm_commonsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) : Ucm_commonsPackage.eINSTANCE);

		// Create package meta-data objects
		theUcm_interactionsPackage.createPackageContents();
		theUCMProfilePackage.createPackageContents();
		theUcm_componentsPackage.createPackageContents();
		theUcm_contractsPackage.createPackageContents();
		theUcm_technicalpoliciesPackage.createPackageContents();
		theUcm_commonsPackage.createPackageContents();

		// Initialize created meta-data
		theUcm_interactionsPackage.initializePackageContents();
		theUCMProfilePackage.initializePackageContents();
		theUcm_componentsPackage.initializePackageContents();
		theUcm_contractsPackage.initializePackageContents();
		theUcm_technicalpoliciesPackage.initializePackageContents();
		theUcm_commonsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUcm_interactionsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ucm_interactionsPackage.eNS_URI, theUcm_interactionsPackage);
		return theUcm_interactionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteractionDefinitionModule() {
		return interactionDefinitionModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteractionDefinitionModule_Base_Package() {
		return (EReference)interactionDefinitionModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteractionPattern() {
		return interactionPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIInteractionDefinition() {
		return iInteractionDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIInteractionDefinition_Base_Class() {
		return (EReference)iInteractionDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectorPort() {
		return connectorPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorPort_Implements() {
		return (EReference)connectorPortEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorPort_Base_Property() {
		return (EReference)connectorPortEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteractionRole() {
		return interactionRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteractionRole_InvolvedItem() {
		return (EReference)interactionRoleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteractionRole_Base_Property() {
		return (EReference)interactionRoleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortElement() {
		return portElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortElement_Kind() {
		return (EAttribute)portElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortElement_Base_Property() {
		return (EReference)portElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectorDefinition() {
		return connectorDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getItemBinding() {
		return itemBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItemBinding_Base_Property() {
		return (EReference)itemBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getItemBinding_InteractionItem() {
		return (EReference)itemBindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortType() {
		return portTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPortType() {
		return iPortTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPortType_Base_Class() {
		return (EReference)iPortTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectorPortConfiguration() {
		return connectorPortConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorPortConfiguration_Port() {
		return (EReference)connectorPortConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorPortConfiguration_Base_Class() {
		return (EReference)connectorPortConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteractionItem() {
		return interactionItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInteractionItem_Nature() {
		return (EAttribute)interactionItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteractionItem_Base_Property() {
		return (EReference)interactionItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPortElementKind() {
		return portElementKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInteractionItemKind() {
		return interactionItemKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_interactionsFactory getUcm_interactionsFactory() {
		return (Ucm_interactionsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		interactionDefinitionModuleEClass = createEClass(INTERACTION_DEFINITION_MODULE);
		createEReference(interactionDefinitionModuleEClass, INTERACTION_DEFINITION_MODULE__BASE_PACKAGE);

		interactionPatternEClass = createEClass(INTERACTION_PATTERN);

		iInteractionDefinitionEClass = createEClass(IINTERACTION_DEFINITION);
		createEReference(iInteractionDefinitionEClass, IINTERACTION_DEFINITION__BASE_CLASS);

		connectorPortEClass = createEClass(CONNECTOR_PORT);
		createEReference(connectorPortEClass, CONNECTOR_PORT__IMPLEMENTS);
		createEReference(connectorPortEClass, CONNECTOR_PORT__BASE_PROPERTY);

		interactionRoleEClass = createEClass(INTERACTION_ROLE);
		createEReference(interactionRoleEClass, INTERACTION_ROLE__INVOLVED_ITEM);
		createEReference(interactionRoleEClass, INTERACTION_ROLE__BASE_PROPERTY);

		portElementEClass = createEClass(PORT_ELEMENT);
		createEAttribute(portElementEClass, PORT_ELEMENT__KIND);
		createEReference(portElementEClass, PORT_ELEMENT__BASE_PROPERTY);

		connectorDefinitionEClass = createEClass(CONNECTOR_DEFINITION);

		itemBindingEClass = createEClass(ITEM_BINDING);
		createEReference(itemBindingEClass, ITEM_BINDING__BASE_PROPERTY);
		createEReference(itemBindingEClass, ITEM_BINDING__INTERACTION_ITEM);

		portTypeEClass = createEClass(PORT_TYPE);

		iPortTypeEClass = createEClass(IPORT_TYPE);
		createEReference(iPortTypeEClass, IPORT_TYPE__BASE_CLASS);

		connectorPortConfigurationEClass = createEClass(CONNECTOR_PORT_CONFIGURATION);
		createEReference(connectorPortConfigurationEClass, CONNECTOR_PORT_CONFIGURATION__PORT);
		createEReference(connectorPortConfigurationEClass, CONNECTOR_PORT_CONFIGURATION__BASE_CLASS);

		interactionItemEClass = createEClass(INTERACTION_ITEM);
		createEAttribute(interactionItemEClass, INTERACTION_ITEM__NATURE);
		createEReference(interactionItemEClass, INTERACTION_ITEM__BASE_PROPERTY);

		// Create enums
		portElementKindEEnum = createEEnum(PORT_ELEMENT_KIND);
		interactionItemKindEEnum = createEEnum(INTERACTION_ITEM_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);
		Ucm_contractsPackage theUcm_contractsPackage = (Ucm_contractsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		interactionPatternEClass.getESuperTypes().add(this.getIInteractionDefinition());
		connectorDefinitionEClass.getESuperTypes().add(this.getIInteractionDefinition());
		connectorDefinitionEClass.getESuperTypes().add(theUcm_contractsPackage.getIConfigurable());
		portTypeEClass.getESuperTypes().add(this.getIPortType());
		connectorPortConfigurationEClass.getESuperTypes().add(theUcm_contractsPackage.getIConfigurable());

		// Initialize classes, features, and operations; add parameters
		initEClass(interactionDefinitionModuleEClass, InteractionDefinitionModule.class, "InteractionDefinitionModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInteractionDefinitionModule_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, InteractionDefinitionModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(interactionPatternEClass, InteractionPattern.class, "InteractionPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iInteractionDefinitionEClass, IInteractionDefinition.class, "IInteractionDefinition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIInteractionDefinition_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, IInteractionDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectorPortEClass, ConnectorPort.class, "ConnectorPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnectorPort_Implements(), this.getInteractionRole(), null, "implements", null, 1, 1, ConnectorPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConnectorPort_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, ConnectorPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(interactionRoleEClass, InteractionRole.class, "InteractionRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInteractionRole_InvolvedItem(), this.getInteractionItem(), null, "involvedItem", null, 1, 1, InteractionRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInteractionRole_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, InteractionRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(portElementEClass, PortElement.class, "PortElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortElement_Kind(), this.getPortElementKind(), "kind", null, 1, 1, PortElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPortElement_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, PortElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectorDefinitionEClass, ConnectorDefinition.class, "ConnectorDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(itemBindingEClass, ItemBinding.class, "ItemBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getItemBinding_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, ItemBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getItemBinding_InteractionItem(), this.getInteractionItem(), null, "interactionItem", null, 1, 1, ItemBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(portTypeEClass, PortType.class, "PortType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iPortTypeEClass, IPortType.class, "IPortType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIPortType_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, IPortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectorPortConfigurationEClass, ConnectorPortConfiguration.class, "ConnectorPortConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnectorPortConfiguration_Port(), this.getConnectorPort(), null, "port", null, 1, 1, ConnectorPortConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConnectorPortConfiguration_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, ConnectorPortConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(interactionItemEClass, InteractionItem.class, "InteractionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInteractionItem_Nature(), this.getInteractionItemKind(), "nature", null, 1, 1, InteractionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInteractionItem_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, InteractionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(portElementKindEEnum, PortElementKind.class, "PortElementKind");
		addEEnumLiteral(portElementKindEEnum, PortElementKind.PROVIDED);
		addEEnumLiteral(portElementKindEEnum, PortElementKind.REQUIRED);

		initEEnum(interactionItemKindEEnum, InteractionItemKind.class, "InteractionItemKind");
		addEEnumLiteral(interactionItemKindEEnum, InteractionItemKind.DATA);
		addEEnumLiteral(interactionItemKindEEnum, InteractionItemKind.INTERFACE);
	}

} //Ucm_interactionsPackageImpl
