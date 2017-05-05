/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_interactionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_interactions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/interactions/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCMProfile.ucm_interactions";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_interactionsPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionDefinitionModuleImpl <em>Interaction Definition Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionDefinitionModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionDefinitionModule()
	 * @generated
	 */
	int INTERACTION_DEFINITION_MODULE = 0;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_DEFINITION_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Interaction Definition Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_DEFINITION_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Interaction Definition Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_DEFINITION_MODULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IInteractionDefinitionImpl <em>IInteraction Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IInteractionDefinitionImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getIInteractionDefinition()
	 * @generated
	 */
	int IINTERACTION_DEFINITION = 2;

	/**
	 * The feature id for the '<em><b>Base Structured Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER = 0;

	/**
	 * The number of structural features of the '<em>IInteraction Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINTERACTION_DEFINITION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IInteraction Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINTERACTION_DEFINITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionPatternImpl <em>Interaction Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionPatternImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionPattern()
	 * @generated
	 */
	int INTERACTION_PATTERN = 1;

	/**
	 * The feature id for the '<em><b>Base Structured Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_PATTERN__BASE_STRUCTURED_CLASSIFIER = IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER;

	/**
	 * The number of structural features of the '<em>Interaction Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_PATTERN_FEATURE_COUNT = IINTERACTION_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Interaction Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_PATTERN_OPERATION_COUNT = IINTERACTION_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortImpl <em>Connector Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getConnectorPort()
	 * @generated
	 */
	int CONNECTOR_PORT = 3;

	/**
	 * The feature id for the '<em><b>Implements</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT__IMPLEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT__BASE_PROPERTY = 1;

	/**
	 * The number of structural features of the '<em>Connector Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Connector Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionRoleImpl <em>Interaction Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionRoleImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionRole()
	 * @generated
	 */
	int INTERACTION_ROLE = 4;

	/**
	 * The feature id for the '<em><b>Involved Item</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ROLE__INVOLVED_ITEM = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ROLE__BASE_PROPERTY = 1;

	/**
	 * The number of structural features of the '<em>Interaction Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ROLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Interaction Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ROLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortElementImpl <em>Port Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortElementImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getPortElement()
	 * @generated
	 */
	int PORT_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ELEMENT__KIND = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ELEMENT__BASE_PROPERTY = 1;

	/**
	 * The number of structural features of the '<em>Port Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Port Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorDefinitionImpl <em>Connector Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorDefinitionImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getConnectorDefinition()
	 * @generated
	 */
	int CONNECTOR_DEFINITION = 6;

	/**
	 * The feature id for the '<em><b>Base Structured Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEFINITION__BASE_STRUCTURED_CLASSIFIER = IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER;

	/**
	 * The number of structural features of the '<em>Connector Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEFINITION_FEATURE_COUNT = IINTERACTION_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Connector Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_DEFINITION_OPERATION_COUNT = IINTERACTION_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ItemBindingImpl <em>Item Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ItemBindingImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getItemBinding()
	 * @generated
	 */
	int ITEM_BINDING = 7;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM_BINDING__BASE_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Interaction Item</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM_BINDING__INTERACTION_ITEM = 1;

	/**
	 * The number of structural features of the '<em>Item Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM_BINDING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Item Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITEM_BINDING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IPortTypeImpl <em>IPort Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IPortTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getIPortType()
	 * @generated
	 */
	int IPORT_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_TYPE__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>IPort Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IPort Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortTypeImpl <em>Port Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getPortType()
	 * @generated
	 */
	int PORT_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE__BASE_CLASS = IPORT_TYPE__BASE_CLASS;

	/**
	 * The number of structural features of the '<em>Port Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE_FEATURE_COUNT = IPORT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Port Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE_OPERATION_COUNT = IPORT_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortConfigurationImpl <em>Connector Port Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortConfigurationImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getConnectorPortConfiguration()
	 * @generated
	 */
	int CONNECTOR_PORT_CONFIGURATION = 10;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT_CONFIGURATION__PORT = Ucm_contractsPackage.ICONFIGURABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT_CONFIGURATION__BASE_CLASS = Ucm_contractsPackage.ICONFIGURABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Connector Port Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT_CONFIGURATION_FEATURE_COUNT = Ucm_contractsPackage.ICONFIGURABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Connector Port Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_PORT_CONFIGURATION_OPERATION_COUNT = Ucm_contractsPackage.ICONFIGURABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionItemImpl <em>Interaction Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionItemImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionItem()
	 * @generated
	 */
	int INTERACTION_ITEM = 11;

	/**
	 * The feature id for the '<em><b>Nature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM__NATURE = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM__BASE_PROPERTY = 1;

	/**
	 * The number of structural features of the '<em>Interaction Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Interaction Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind <em>Port Element Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getPortElementKind()
	 * @generated
	 */
	int PORT_ELEMENT_KIND = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItemKind <em>Interaction Item Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItemKind
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionItemKind()
	 * @generated
	 */
	int INTERACTION_ITEM_KIND = 13;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule <em>Interaction Definition Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction Definition Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule
	 * @generated
	 */
	EClass getInteractionDefinitionModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule#getBase_Package()
	 * @see #getInteractionDefinitionModule()
	 * @generated
	 */
	EReference getInteractionDefinitionModule_Base_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionPattern <em>Interaction Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction Pattern</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionPattern
	 * @generated
	 */
	EClass getInteractionPattern();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition <em>IInteraction Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IInteraction Definition</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition
	 * @generated
	 */
	EClass getIInteractionDefinition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition#getBase_StructuredClassifier <em>Base Structured Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Structured Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition#getBase_StructuredClassifier()
	 * @see #getIInteractionDefinition()
	 * @generated
	 */
	EReference getIInteractionDefinition_Base_StructuredClassifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort <em>Connector Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Port</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort
	 * @generated
	 */
	EClass getConnectorPort();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort#getImplements <em>Implements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Implements</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort#getImplements()
	 * @see #getConnectorPort()
	 * @generated
	 */
	EReference getConnectorPort_Implements();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort#getBase_Property()
	 * @see #getConnectorPort()
	 * @generated
	 */
	EReference getConnectorPort_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole <em>Interaction Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction Role</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole
	 * @generated
	 */
	EClass getInteractionRole();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole#getInvolvedItem <em>Involved Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Involved Item</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole#getInvolvedItem()
	 * @see #getInteractionRole()
	 * @generated
	 */
	EReference getInteractionRole_InvolvedItem();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole#getBase_Property()
	 * @see #getInteractionRole()
	 * @generated
	 */
	EReference getInteractionRole_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement <em>Port Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Element</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement
	 * @generated
	 */
	EClass getPortElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement#getKind()
	 * @see #getPortElement()
	 * @generated
	 */
	EAttribute getPortElement_Kind();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement#getBase_Property()
	 * @see #getPortElement()
	 * @generated
	 */
	EReference getPortElement_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorDefinition <em>Connector Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Definition</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorDefinition
	 * @generated
	 */
	EClass getConnectorDefinition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding <em>Item Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Item Binding</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding
	 * @generated
	 */
	EClass getItemBinding();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding#getBase_Property()
	 * @see #getItemBinding()
	 * @generated
	 */
	EReference getItemBinding_Base_Property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding#getInteractionItem <em>Interaction Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Interaction Item</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding#getInteractionItem()
	 * @see #getItemBinding()
	 * @generated
	 */
	EReference getItemBinding_InteractionItem();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortType <em>Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortType
	 * @generated
	 */
	EClass getPortType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType <em>IPort Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IPort Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType
	 * @generated
	 */
	EClass getIPortType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType#getBase_Class()
	 * @see #getIPortType()
	 * @generated
	 */
	EReference getIPortType_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration <em>Connector Port Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Port Configuration</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration
	 * @generated
	 */
	EClass getConnectorPortConfiguration();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration#getPort()
	 * @see #getConnectorPortConfiguration()
	 * @generated
	 */
	EReference getConnectorPortConfiguration_Port();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration#getBase_Class()
	 * @see #getConnectorPortConfiguration()
	 * @generated
	 */
	EReference getConnectorPortConfiguration_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem <em>Interaction Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction Item</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem
	 * @generated
	 */
	EClass getInteractionItem();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem#getNature <em>Nature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nature</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem#getNature()
	 * @see #getInteractionItem()
	 * @generated
	 */
	EAttribute getInteractionItem_Nature();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem#getBase_Property()
	 * @see #getInteractionItem()
	 * @generated
	 */
	EReference getInteractionItem_Base_Property();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind <em>Port Element Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Port Element Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind
	 * @generated
	 */
	EEnum getPortElementKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItemKind <em>Interaction Item Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Interaction Item Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItemKind
	 * @generated
	 */
	EEnum getInteractionItemKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_interactionsFactory getUcm_interactionsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionDefinitionModuleImpl <em>Interaction Definition Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionDefinitionModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionDefinitionModule()
		 * @generated
		 */
		EClass INTERACTION_DEFINITION_MODULE = eINSTANCE.getInteractionDefinitionModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION_DEFINITION_MODULE__BASE_PACKAGE = eINSTANCE.getInteractionDefinitionModule_Base_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionPatternImpl <em>Interaction Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionPatternImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionPattern()
		 * @generated
		 */
		EClass INTERACTION_PATTERN = eINSTANCE.getInteractionPattern();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IInteractionDefinitionImpl <em>IInteraction Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IInteractionDefinitionImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getIInteractionDefinition()
		 * @generated
		 */
		EClass IINTERACTION_DEFINITION = eINSTANCE.getIInteractionDefinition();

		/**
		 * The meta object literal for the '<em><b>Base Structured Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IINTERACTION_DEFINITION__BASE_STRUCTURED_CLASSIFIER = eINSTANCE.getIInteractionDefinition_Base_StructuredClassifier();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortImpl <em>Connector Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getConnectorPort()
		 * @generated
		 */
		EClass CONNECTOR_PORT = eINSTANCE.getConnectorPort();

		/**
		 * The meta object literal for the '<em><b>Implements</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_PORT__IMPLEMENTS = eINSTANCE.getConnectorPort_Implements();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_PORT__BASE_PROPERTY = eINSTANCE.getConnectorPort_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionRoleImpl <em>Interaction Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionRoleImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionRole()
		 * @generated
		 */
		EClass INTERACTION_ROLE = eINSTANCE.getInteractionRole();

		/**
		 * The meta object literal for the '<em><b>Involved Item</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION_ROLE__INVOLVED_ITEM = eINSTANCE.getInteractionRole_InvolvedItem();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION_ROLE__BASE_PROPERTY = eINSTANCE.getInteractionRole_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortElementImpl <em>Port Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortElementImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getPortElement()
		 * @generated
		 */
		EClass PORT_ELEMENT = eINSTANCE.getPortElement();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_ELEMENT__KIND = eINSTANCE.getPortElement_Kind();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_ELEMENT__BASE_PROPERTY = eINSTANCE.getPortElement_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorDefinitionImpl <em>Connector Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorDefinitionImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getConnectorDefinition()
		 * @generated
		 */
		EClass CONNECTOR_DEFINITION = eINSTANCE.getConnectorDefinition();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ItemBindingImpl <em>Item Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ItemBindingImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getItemBinding()
		 * @generated
		 */
		EClass ITEM_BINDING = eINSTANCE.getItemBinding();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITEM_BINDING__BASE_PROPERTY = eINSTANCE.getItemBinding_Base_Property();

		/**
		 * The meta object literal for the '<em><b>Interaction Item</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITEM_BINDING__INTERACTION_ITEM = eINSTANCE.getItemBinding_InteractionItem();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortTypeImpl <em>Port Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.PortTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getPortType()
		 * @generated
		 */
		EClass PORT_TYPE = eINSTANCE.getPortType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IPortTypeImpl <em>IPort Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.IPortTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getIPortType()
		 * @generated
		 */
		EClass IPORT_TYPE = eINSTANCE.getIPortType();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPORT_TYPE__BASE_CLASS = eINSTANCE.getIPortType_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortConfigurationImpl <em>Connector Port Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.ConnectorPortConfigurationImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getConnectorPortConfiguration()
		 * @generated
		 */
		EClass CONNECTOR_PORT_CONFIGURATION = eINSTANCE.getConnectorPortConfiguration();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_PORT_CONFIGURATION__PORT = eINSTANCE.getConnectorPortConfiguration_Port();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_PORT_CONFIGURATION__BASE_CLASS = eINSTANCE.getConnectorPortConfiguration_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionItemImpl <em>Interaction Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.InteractionItemImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionItem()
		 * @generated
		 */
		EClass INTERACTION_ITEM = eINSTANCE.getInteractionItem();

		/**
		 * The meta object literal for the '<em><b>Nature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION_ITEM__NATURE = eINSTANCE.getInteractionItem_Nature();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION_ITEM__BASE_PROPERTY = eINSTANCE.getInteractionItem_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind <em>Port Element Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getPortElementKind()
		 * @generated
		 */
		EEnum PORT_ELEMENT_KIND = eINSTANCE.getPortElementKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItemKind <em>Interaction Item Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItemKind
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl#getInteractionItemKind()
		 * @generated
		 */
		EEnum INTERACTION_ITEM_KIND = eINSTANCE.getInteractionItemKind();

	}

} //Ucm_interactionsPackage
