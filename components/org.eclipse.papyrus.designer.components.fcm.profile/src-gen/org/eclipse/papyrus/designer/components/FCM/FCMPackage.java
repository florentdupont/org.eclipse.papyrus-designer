/**
 * Copyright (c) 2015 CEA LIST
 * 
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Ansgar Radermacher - Initial API and implementation
 * 
 */
package org.eclipse.papyrus.designer.components.FCM;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.components.FCM.FCMFactory
 * @model kind="package"
 * @generated
 */
public interface FCMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "FCM"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/FCM/1"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "FCM"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FCMPackage eINSTANCE = org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ImplementationGroupImpl <em>Implementation Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.ImplementationGroupImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getImplementationGroup()
	 * @generated
	 */
	int IMPLEMENTATION_GROUP = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_GROUP__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Implementation Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_GROUP_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.CompToOOmappingImpl <em>Comp To OOmapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.CompToOOmappingImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getCompToOOmapping()
	 * @generated
	 */
	int COMP_TO_OOMAPPING = 1;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMP_TO_OOMAPPING__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Comp To OOmapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMP_TO_OOMAPPING_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.CodeGenOptionsImpl <em>Code Gen Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.CodeGenOptionsImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getCodeGenOptions()
	 * @generated
	 */
	int CODE_GEN_OPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE_GEN_OPTIONS__BASE_PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Comp To OOmapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE_GEN_OPTIONS__COMP_TO_OOMAPPING = 1;

	/**
	 * The number of structural features of the '<em>Code Gen Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODE_GEN_OPTIONS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.InteractionComponentImpl <em>Interaction Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.InteractionComponentImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInteractionComponent()
	 * @generated
	 */
	int INTERACTION_COMPONENT = 3;

	/**
	 * The feature id for the '<em><b>For Distribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_COMPONENT__FOR_DISTRIBUTION = 0;

	/**
	 * The feature id for the '<em><b>Connection Pattern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_COMPONENT__CONNECTION_PATTERN = 1;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_COMPONENT__BASE_CLASS = 2;

	/**
	 * The number of structural features of the '<em>Interaction Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_COMPONENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.SingletonImpl <em>Singleton</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.SingletonImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getSingleton()
	 * @generated
	 */
	int SINGLETON = 4;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Singleton</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ConfigurableElementInstanceImpl <em>Configurable Element Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.ConfigurableElementInstanceImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getConfigurableElementInstance()
	 * @generated
	 */
	int CONFIGURABLE_ELEMENT_INSTANCE = 6;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURABLE_ELEMENT_INSTANCE__CONFIGURATION = 0;

	/**
	 * The number of structural features of the '<em>Configurable Element Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ConnectorImpl <em>Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.ConnectorImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getConnector()
	 * @generated
	 */
	int CONNECTOR = 5;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__CONFIGURATION = CONFIGURABLE_ELEMENT_INSTANCE__CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Base Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__BASE_CONNECTOR = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ic</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__IC = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__BASE_PROPERTY = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.UseInstanceConfiguratorImpl <em>Use Instance Configurator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.UseInstanceConfiguratorImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getUseInstanceConfigurator()
	 * @generated
	 */
	int USE_INSTANCE_CONFIGURATOR = 7;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_INSTANCE_CONFIGURATOR__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Configurator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_INSTANCE_CONFIGURATOR__CONFIGURATOR = 1;

	/**
	 * The number of structural features of the '<em>Use Instance Configurator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_INSTANCE_CONFIGURATOR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.InstanceConfiguratorImpl <em>Instance Configurator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.InstanceConfiguratorImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInstanceConfigurator()
	 * @generated
	 */
	int INSTANCE_CONFIGURATOR = 8;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_CONFIGURATOR__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>On Node Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_CONFIGURATOR__ON_NODE_MODEL = 1;

	/**
	 * The number of structural features of the '<em>Instance Configurator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_CONFIGURATOR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.RuleApplicationImpl <em>Rule Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.RuleApplicationImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getRuleApplication()
	 * @generated
	 */
	int RULE_APPLICATION = 9;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_APPLICATION__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Container Rule</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_APPLICATION__CONTAINER_RULE = 1;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_APPLICATION__BASE_PACKAGE = 2;

	/**
	 * The number of structural features of the '<em>Rule Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_APPLICATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ContainerRuleImpl <em>Container Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.ContainerRuleImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getContainerRule()
	 * @generated
	 */
	int CONTAINER_RULE = 10;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RULE__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RULE__KIND = 1;

	/**
	 * The number of structural features of the '<em>Container Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RULE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.PortImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getPort()
	 * @generated
	 */
	int PORT = 12;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__CONFIGURATION = CONFIGURABLE_ELEMENT_INSTANCE__CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Base Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__BASE_PORT = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__KIND = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__TYPE = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = CONFIGURABLE_ELEMENT_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.TemplatePortImpl <em>Template Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.TemplatePortImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getTemplatePort()
	 * @generated
	 */
	int TEMPLATE_PORT = 11;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PORT__CONFIGURATION = PORT__CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Base Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PORT__BASE_PORT = PORT__BASE_PORT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PORT__KIND = PORT__KIND;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PORT__TYPE = PORT__TYPE;

	/**
	 * The feature id for the '<em><b>Bound Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PORT__BOUND_TYPE = PORT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Template Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.PortKindImpl <em>Port Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.PortKindImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getPortKind()
	 * @generated
	 */
	int PORT_KIND = 13;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_KIND__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Provided Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_KIND__PROVIDED_INTERFACE = 1;

	/**
	 * The feature id for the '<em><b>Required Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_KIND__REQUIRED_INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Extended Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_KIND__EXTENDED_PORT = 3;

	/**
	 * The number of structural features of the '<em>Port Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_KIND_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.CompImplTemplateImpl <em>Comp Impl Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.CompImplTemplateImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getCompImplTemplate()
	 * @generated
	 */
	int COMP_IMPL_TEMPLATE = 14;

	/**
	 * The feature id for the '<em><b>Template Defs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMP_IMPL_TEMPLATE__TEMPLATE_DEFS = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMP_IMPL_TEMPLATE__BASE_CLASS = 1;

	/**
	 * The number of structural features of the '<em>Comp Impl Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMP_IMPL_TEMPLATE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.InterceptionRuleImpl <em>Interception Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.InterceptionRuleImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInterceptionRule()
	 * @generated
	 */
	int INTERCEPTION_RULE = 15;

	/**
	 * The feature id for the '<em><b>Interception Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERCEPTION_RULE__INTERCEPTION_KIND = 0;

	/**
	 * The feature id for the '<em><b>Interception Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERCEPTION_RULE__INTERCEPTION_SET = 1;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERCEPTION_RULE__BASE_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Base Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERCEPTION_RULE__BASE_OPERATION = 3;

	/**
	 * The number of structural features of the '<em>Interception Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERCEPTION_RULE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.FragmentImpl <em>Fragment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FragmentImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getFragment()
	 * @generated
	 */
	int FRAGMENT = 16;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.AssemblyImpl <em>Assembly</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.AssemblyImpl
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getAssembly()
	 * @generated
	 */
	int ASSEMBLY = 17;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Assembly</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind <em>Container Rule Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getContainerRuleKind()
	 * @generated
	 */
	int CONTAINER_RULE_KIND = 18;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionKind <em>Interception Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionKind
	 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInterceptionKind()
	 * @generated
	 */
	int INTERCEPTION_KIND = 19;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.ImplementationGroup <em>Implementation Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implementation Group</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ImplementationGroup
	 * @generated
	 */
	EClass getImplementationGroup();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.ImplementationGroup#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ImplementationGroup#getBase_Class()
	 * @see #getImplementationGroup()
	 * @generated
	 */
	EReference getImplementationGroup_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.CompToOOmapping <em>Comp To OOmapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comp To OOmapping</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CompToOOmapping
	 * @generated
	 */
	EClass getCompToOOmapping();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.CompToOOmapping#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CompToOOmapping#getBase_Class()
	 * @see #getCompToOOmapping()
	 * @generated
	 */
	EReference getCompToOOmapping_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.CodeGenOptions <em>Code Gen Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Code Gen Options</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CodeGenOptions
	 * @generated
	 */
	EClass getCodeGenOptions();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.CodeGenOptions#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CodeGenOptions#getBase_Package()
	 * @see #getCodeGenOptions()
	 * @generated
	 */
	EReference getCodeGenOptions_Base_Package();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.CodeGenOptions#getCompToOOmapping <em>Comp To OOmapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Comp To OOmapping</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CodeGenOptions#getCompToOOmapping()
	 * @see #getCodeGenOptions()
	 * @generated
	 */
	EReference getCodeGenOptions_CompToOOmapping();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.InteractionComponent <em>Interaction Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction Component</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InteractionComponent
	 * @generated
	 */
	EClass getInteractionComponent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.components.FCM.InteractionComponent#isForDistribution <em>For Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>For Distribution</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InteractionComponent#isForDistribution()
	 * @see #getInteractionComponent()
	 * @generated
	 */
	EAttribute getInteractionComponent_ForDistribution();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.InteractionComponent#getConnectionPattern <em>Connection Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connection Pattern</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InteractionComponent#getConnectionPattern()
	 * @see #getInteractionComponent()
	 * @generated
	 */
	EReference getInteractionComponent_ConnectionPattern();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.InteractionComponent#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InteractionComponent#getBase_Class()
	 * @see #getInteractionComponent()
	 * @generated
	 */
	EReference getInteractionComponent_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.Singleton <em>Singleton</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Singleton</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Singleton
	 * @generated
	 */
	EClass getSingleton();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Singleton#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Singleton#getBase_Class()
	 * @see #getSingleton()
	 * @generated
	 */
	EReference getSingleton_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Connector
	 * @generated
	 */
	EClass getConnector();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Connector#getBase_Connector <em>Base Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Connector</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Connector#getBase_Connector()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Base_Connector();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Connector#getIc <em>Ic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ic</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Connector#getIc()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Ic();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Connector#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Connector#getBase_Property()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance <em>Configurable Element Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configurable Element Instance</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance
	 * @generated
	 */
	EClass getConfigurableElementInstance();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Configuration</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance#getConfiguration()
	 * @see #getConfigurableElementInstance()
	 * @generated
	 */
	EReference getConfigurableElementInstance_Configuration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator <em>Use Instance Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Instance Configurator</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator
	 * @generated
	 */
	EClass getUseInstanceConfigurator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator#getBase_Class()
	 * @see #getUseInstanceConfigurator()
	 * @generated
	 */
	EReference getUseInstanceConfigurator_Base_Class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator#getConfigurator <em>Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Configurator</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator#getConfigurator()
	 * @see #getUseInstanceConfigurator()
	 * @generated
	 */
	EReference getUseInstanceConfigurator_Configurator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator <em>Instance Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Configurator</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator
	 * @generated
	 */
	EClass getInstanceConfigurator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator#getBase_Class()
	 * @see #getInstanceConfigurator()
	 * @generated
	 */
	EReference getInstanceConfigurator_Base_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator#isOnNodeModel <em>On Node Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Node Model</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator#isOnNodeModel()
	 * @see #getInstanceConfigurator()
	 * @generated
	 */
	EAttribute getInstanceConfigurator_OnNodeModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.RuleApplication <em>Rule Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Application</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.RuleApplication
	 * @generated
	 */
	EClass getRuleApplication();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.RuleApplication#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.RuleApplication#getBase_Class()
	 * @see #getRuleApplication()
	 * @generated
	 */
	EReference getRuleApplication_Base_Class();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.components.FCM.RuleApplication#getContainerRule <em>Container Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Container Rule</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.RuleApplication#getContainerRule()
	 * @see #getRuleApplication()
	 * @generated
	 */
	EReference getRuleApplication_ContainerRule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.RuleApplication#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.RuleApplication#getBase_Package()
	 * @see #getRuleApplication()
	 * @generated
	 */
	EReference getRuleApplication_Base_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.ContainerRule <em>Container Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Rule</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ContainerRule
	 * @generated
	 */
	EClass getContainerRule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.ContainerRule#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ContainerRule#getBase_Class()
	 * @see #getContainerRule()
	 * @generated
	 */
	EReference getContainerRule_Base_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.components.FCM.ContainerRule#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ContainerRule#getKind()
	 * @see #getContainerRule()
	 * @generated
	 */
	EAttribute getContainerRule_Kind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.TemplatePort <em>Template Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Port</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.TemplatePort
	 * @generated
	 */
	EClass getTemplatePort();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.TemplatePort#getBoundType <em>Bound Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bound Type</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.TemplatePort#getBoundType()
	 * @see #getTemplatePort()
	 * @generated
	 */
	EReference getTemplatePort_BoundType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Port#getBase_Port <em>Base Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Port</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Port#getBase_Port()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_Base_Port();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Port#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Kind</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Port#getKind()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_Kind();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Port#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Port#getType()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.PortKind <em>Port Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Kind</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.PortKind
	 * @generated
	 */
	EClass getPortKind();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.PortKind#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.PortKind#getBase_Class()
	 * @see #getPortKind()
	 * @generated
	 */
	EReference getPortKind_Base_Class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.PortKind#getProvidedInterface <em>Provided Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Provided Interface</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.PortKind#getProvidedInterface()
	 * @see #getPortKind()
	 * @generated
	 */
	EReference getPortKind_ProvidedInterface();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.PortKind#getRequiredInterface <em>Required Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Required Interface</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.PortKind#getRequiredInterface()
	 * @see #getPortKind()
	 * @generated
	 */
	EReference getPortKind_RequiredInterface();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.components.FCM.PortKind#isExtendedPort <em>Extended Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extended Port</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.PortKind#isExtendedPort()
	 * @see #getPortKind()
	 * @generated
	 */
	EAttribute getPortKind_ExtendedPort();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.CompImplTemplate <em>Comp Impl Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comp Impl Template</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CompImplTemplate
	 * @generated
	 */
	EClass getCompImplTemplate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.components.FCM.CompImplTemplate#getTemplateDefs <em>Template Defs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template Defs</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CompImplTemplate#getTemplateDefs()
	 * @see #getCompImplTemplate()
	 * @generated
	 */
	EAttribute getCompImplTemplate_TemplateDefs();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.CompImplTemplate#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.CompImplTemplate#getBase_Class()
	 * @see #getCompImplTemplate()
	 * @generated
	 */
	EReference getCompImplTemplate_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionRule <em>Interception Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interception Rule</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionRule
	 * @generated
	 */
	EClass getInterceptionRule();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getInterceptionKind <em>Interception Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interception Kind</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getInterceptionKind()
	 * @see #getInterceptionRule()
	 * @generated
	 */
	EAttribute getInterceptionRule_InterceptionKind();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getInterceptionSet <em>Interception Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Interception Set</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getInterceptionSet()
	 * @see #getInterceptionRule()
	 * @generated
	 */
	EReference getInterceptionRule_InterceptionSet();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getBase_Property()
	 * @see #getInterceptionRule()
	 * @generated
	 */
	EReference getInterceptionRule_Base_Property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getBase_Operation <em>Base Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Operation</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionRule#getBase_Operation()
	 * @see #getInterceptionRule()
	 * @generated
	 */
	EReference getInterceptionRule_Base_Operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.Fragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Fragment
	 * @generated
	 */
	EClass getFragment();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Fragment#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Fragment#getBase_Class()
	 * @see #getFragment()
	 * @generated
	 */
	EReference getFragment_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.components.FCM.Assembly <em>Assembly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assembly</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Assembly
	 * @generated
	 */
	EClass getAssembly();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.components.FCM.Assembly#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.Assembly#getBase_Class()
	 * @see #getAssembly()
	 * @generated
	 */
	EReference getAssembly_Base_Class();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind <em>Container Rule Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Container Rule Kind</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind
	 * @generated
	 */
	EEnum getContainerRuleKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionKind <em>Interception Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Interception Kind</em>'.
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionKind
	 * @generated
	 */
	EEnum getInterceptionKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FCMFactory getFCMFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ImplementationGroupImpl <em>Implementation Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.ImplementationGroupImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getImplementationGroup()
		 * @generated
		 */
		EClass IMPLEMENTATION_GROUP = eINSTANCE.getImplementationGroup();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_GROUP__BASE_CLASS = eINSTANCE.getImplementationGroup_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.CompToOOmappingImpl <em>Comp To OOmapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.CompToOOmappingImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getCompToOOmapping()
		 * @generated
		 */
		EClass COMP_TO_OOMAPPING = eINSTANCE.getCompToOOmapping();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMP_TO_OOMAPPING__BASE_CLASS = eINSTANCE.getCompToOOmapping_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.CodeGenOptionsImpl <em>Code Gen Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.CodeGenOptionsImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getCodeGenOptions()
		 * @generated
		 */
		EClass CODE_GEN_OPTIONS = eINSTANCE.getCodeGenOptions();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CODE_GEN_OPTIONS__BASE_PACKAGE = eINSTANCE.getCodeGenOptions_Base_Package();

		/**
		 * The meta object literal for the '<em><b>Comp To OOmapping</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CODE_GEN_OPTIONS__COMP_TO_OOMAPPING = eINSTANCE.getCodeGenOptions_CompToOOmapping();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.InteractionComponentImpl <em>Interaction Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.InteractionComponentImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInteractionComponent()
		 * @generated
		 */
		EClass INTERACTION_COMPONENT = eINSTANCE.getInteractionComponent();

		/**
		 * The meta object literal for the '<em><b>For Distribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION_COMPONENT__FOR_DISTRIBUTION = eINSTANCE.getInteractionComponent_ForDistribution();

		/**
		 * The meta object literal for the '<em><b>Connection Pattern</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION_COMPONENT__CONNECTION_PATTERN = eINSTANCE.getInteractionComponent_ConnectionPattern();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION_COMPONENT__BASE_CLASS = eINSTANCE.getInteractionComponent_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.SingletonImpl <em>Singleton</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.SingletonImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getSingleton()
		 * @generated
		 */
		EClass SINGLETON = eINSTANCE.getSingleton();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLETON__BASE_CLASS = eINSTANCE.getSingleton_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ConnectorImpl <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.ConnectorImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '<em><b>Base Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__BASE_CONNECTOR = eINSTANCE.getConnector_Base_Connector();

		/**
		 * The meta object literal for the '<em><b>Ic</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__IC = eINSTANCE.getConnector_Ic();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR__BASE_PROPERTY = eINSTANCE.getConnector_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ConfigurableElementInstanceImpl <em>Configurable Element Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.ConfigurableElementInstanceImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getConfigurableElementInstance()
		 * @generated
		 */
		EClass CONFIGURABLE_ELEMENT_INSTANCE = eINSTANCE.getConfigurableElementInstance();

		/**
		 * The meta object literal for the '<em><b>Configuration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURABLE_ELEMENT_INSTANCE__CONFIGURATION = eINSTANCE.getConfigurableElementInstance_Configuration();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.UseInstanceConfiguratorImpl <em>Use Instance Configurator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.UseInstanceConfiguratorImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getUseInstanceConfigurator()
		 * @generated
		 */
		EClass USE_INSTANCE_CONFIGURATOR = eINSTANCE.getUseInstanceConfigurator();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_INSTANCE_CONFIGURATOR__BASE_CLASS = eINSTANCE.getUseInstanceConfigurator_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Configurator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_INSTANCE_CONFIGURATOR__CONFIGURATOR = eINSTANCE.getUseInstanceConfigurator_Configurator();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.InstanceConfiguratorImpl <em>Instance Configurator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.InstanceConfiguratorImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInstanceConfigurator()
		 * @generated
		 */
		EClass INSTANCE_CONFIGURATOR = eINSTANCE.getInstanceConfigurator();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_CONFIGURATOR__BASE_CLASS = eINSTANCE.getInstanceConfigurator_Base_Class();

		/**
		 * The meta object literal for the '<em><b>On Node Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE_CONFIGURATOR__ON_NODE_MODEL = eINSTANCE.getInstanceConfigurator_OnNodeModel();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.RuleApplicationImpl <em>Rule Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.RuleApplicationImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getRuleApplication()
		 * @generated
		 */
		EClass RULE_APPLICATION = eINSTANCE.getRuleApplication();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_APPLICATION__BASE_CLASS = eINSTANCE.getRuleApplication_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Container Rule</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_APPLICATION__CONTAINER_RULE = eINSTANCE.getRuleApplication_ContainerRule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_APPLICATION__BASE_PACKAGE = eINSTANCE.getRuleApplication_Base_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.ContainerRuleImpl <em>Container Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.ContainerRuleImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getContainerRule()
		 * @generated
		 */
		EClass CONTAINER_RULE = eINSTANCE.getContainerRule();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_RULE__BASE_CLASS = eINSTANCE.getContainerRule_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RULE__KIND = eINSTANCE.getContainerRule_Kind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.TemplatePortImpl <em>Template Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.TemplatePortImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getTemplatePort()
		 * @generated
		 */
		EClass TEMPLATE_PORT = eINSTANCE.getTemplatePort();

		/**
		 * The meta object literal for the '<em><b>Bound Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PORT__BOUND_TYPE = eINSTANCE.getTemplatePort_BoundType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.PortImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '<em><b>Base Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__BASE_PORT = eINSTANCE.getPort_Base_Port();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__KIND = eINSTANCE.getPort_Kind();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__TYPE = eINSTANCE.getPort_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.PortKindImpl <em>Port Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.PortKindImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getPortKind()
		 * @generated
		 */
		EClass PORT_KIND = eINSTANCE.getPortKind();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_KIND__BASE_CLASS = eINSTANCE.getPortKind_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Provided Interface</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_KIND__PROVIDED_INTERFACE = eINSTANCE.getPortKind_ProvidedInterface();

		/**
		 * The meta object literal for the '<em><b>Required Interface</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_KIND__REQUIRED_INTERFACE = eINSTANCE.getPortKind_RequiredInterface();

		/**
		 * The meta object literal for the '<em><b>Extended Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_KIND__EXTENDED_PORT = eINSTANCE.getPortKind_ExtendedPort();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.CompImplTemplateImpl <em>Comp Impl Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.CompImplTemplateImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getCompImplTemplate()
		 * @generated
		 */
		EClass COMP_IMPL_TEMPLATE = eINSTANCE.getCompImplTemplate();

		/**
		 * The meta object literal for the '<em><b>Template Defs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMP_IMPL_TEMPLATE__TEMPLATE_DEFS = eINSTANCE.getCompImplTemplate_TemplateDefs();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMP_IMPL_TEMPLATE__BASE_CLASS = eINSTANCE.getCompImplTemplate_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.InterceptionRuleImpl <em>Interception Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.InterceptionRuleImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInterceptionRule()
		 * @generated
		 */
		EClass INTERCEPTION_RULE = eINSTANCE.getInterceptionRule();

		/**
		 * The meta object literal for the '<em><b>Interception Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERCEPTION_RULE__INTERCEPTION_KIND = eINSTANCE.getInterceptionRule_InterceptionKind();

		/**
		 * The meta object literal for the '<em><b>Interception Set</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERCEPTION_RULE__INTERCEPTION_SET = eINSTANCE.getInterceptionRule_InterceptionSet();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERCEPTION_RULE__BASE_PROPERTY = eINSTANCE.getInterceptionRule_Base_Property();

		/**
		 * The meta object literal for the '<em><b>Base Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERCEPTION_RULE__BASE_OPERATION = eINSTANCE.getInterceptionRule_Base_Operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.FragmentImpl <em>Fragment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FragmentImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getFragment()
		 * @generated
		 */
		EClass FRAGMENT = eINSTANCE.getFragment();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT__BASE_CLASS = eINSTANCE.getFragment_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.impl.AssemblyImpl <em>Assembly</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.AssemblyImpl
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getAssembly()
		 * @generated
		 */
		EClass ASSEMBLY = eINSTANCE.getAssembly();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY__BASE_CLASS = eINSTANCE.getAssembly_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind <em>Container Rule Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getContainerRuleKind()
		 * @generated
		 */
		EEnum CONTAINER_RULE_KIND = eINSTANCE.getContainerRuleKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionKind <em>Interception Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionKind
		 * @see org.eclipse.papyrus.designer.components.FCM.impl.FCMPackageImpl#getInterceptionKind()
		 * @generated
		 */
		EEnum INTERCEPTION_KIND = eINSTANCE.getInterceptionKind();

	}

} //FCMPackage
