/**
 * Copyright (c) 2013 CEA LIST
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
package org.eclipse.papyrus.designer.components.FCM.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.papyrus.designer.components.FCM.Assembly;
import org.eclipse.papyrus.designer.components.FCM.CodeGenOptions;
import org.eclipse.papyrus.designer.components.FCM.CompImplTemplate;
import org.eclipse.papyrus.designer.components.FCM.CompToOOmapping;
import org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance;
import org.eclipse.papyrus.designer.components.FCM.Connector;
import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind;
import org.eclipse.papyrus.designer.components.FCM.FCMFactory;
import org.eclipse.papyrus.designer.components.FCM.FCMPackage;
import org.eclipse.papyrus.designer.components.FCM.Fragment;
import org.eclipse.papyrus.designer.components.FCM.ImplementationGroup;
import org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.components.FCM.InterceptionKind;
import org.eclipse.papyrus.designer.components.FCM.InterceptionRule;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.FCM.RuleApplication;
import org.eclipse.papyrus.designer.components.FCM.Singleton;
import org.eclipse.papyrus.designer.components.FCM.TemplatePort;
import org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator;
import org.eclipse.uml2.types.TypesPackage;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FCMPackageImpl extends EPackageImpl implements FCMPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass implementationGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compToOOmappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass codeGenOptionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singletonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurableElementInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass useInstanceConfiguratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceConfiguratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleApplicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templatePortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portKindEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compImplTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interceptionRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fragmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum containerRuleKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum interceptionKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.papyrus.designer.components.FCM.FCMPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FCMPackageImpl() {
		super(eNS_URI, FCMFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link FCMPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FCMPackage init() {
		if (isInited) return (FCMPackage)EPackage.Registry.INSTANCE.getEPackage(FCMPackage.eNS_URI);

		// Obtain or create and register package
		FCMPackageImpl theFCMPackage = (FCMPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FCMPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FCMPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFCMPackage.createPackageContents();

		// Initialize created meta-data
		theFCMPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFCMPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FCMPackage.eNS_URI, theFCMPackage);
		return theFCMPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getImplementationGroup() {
		return implementationGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getImplementationGroup_Base_Class() {
		return (EReference)implementationGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompToOOmapping() {
		return compToOOmappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompToOOmapping_Base_Class() {
		return (EReference)compToOOmappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCodeGenOptions() {
		return codeGenOptionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCodeGenOptions_Base_Package() {
		return (EReference)codeGenOptionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCodeGenOptions_CompToOOmapping() {
		return (EReference)codeGenOptionsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInteractionComponent() {
		return interactionComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInteractionComponent_ForDistribution() {
		return (EAttribute)interactionComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInteractionComponent_ConnectionPattern() {
		return (EReference)interactionComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInteractionComponent_Base_Class() {
		return (EReference)interactionComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSingleton() {
		return singletonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSingleton_Base_Class() {
		return (EReference)singletonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConnector() {
		return connectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnector_Base_Connector() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnector_Ic() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnector_Base_Property() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConfigurableElementInstance() {
		return configurableElementInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConfigurableElementInstance_Configuration() {
		return (EReference)configurableElementInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUseInstanceConfigurator() {
		return useInstanceConfiguratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getUseInstanceConfigurator_Base_Class() {
		return (EReference)useInstanceConfiguratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getUseInstanceConfigurator_Configurator() {
		return (EReference)useInstanceConfiguratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInstanceConfigurator() {
		return instanceConfiguratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInstanceConfigurator_Base_Class() {
		return (EReference)instanceConfiguratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInstanceConfigurator_OnNodeModel() {
		return (EAttribute)instanceConfiguratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRuleApplication() {
		return ruleApplicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRuleApplication_Base_Class() {
		return (EReference)ruleApplicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRuleApplication_ContainerRule() {
		return (EReference)ruleApplicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRuleApplication_Base_Package() {
		return (EReference)ruleApplicationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerRule() {
		return containerRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerRule_Base_Class() {
		return (EReference)containerRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerRule_Kind() {
		return (EAttribute)containerRuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplatePort() {
		return templatePortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplatePort_BoundType() {
		return (EReference)templatePortEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPort() {
		return portEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPort_Base_Port() {
		return (EReference)portEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPort_Kind() {
		return (EReference)portEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPort_Type() {
		return (EReference)portEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPortKind() {
		return portKindEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPortKind_Base_Class() {
		return (EReference)portKindEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPortKind_ProvidedInterface() {
		return (EReference)portKindEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPortKind_RequiredInterface() {
		return (EReference)portKindEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPortKind_ExtendedPort() {
		return (EAttribute)portKindEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompImplTemplate() {
		return compImplTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCompImplTemplate_TemplateDefs() {
		return (EAttribute)compImplTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCompImplTemplate_Base_Class() {
		return (EReference)compImplTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInterceptionRule() {
		return interceptionRuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInterceptionRule_InterceptionKind() {
		return (EAttribute)interceptionRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInterceptionRule_InterceptionSet() {
		return (EReference)interceptionRuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInterceptionRule_Base_Property() {
		return (EReference)interceptionRuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInterceptionRule_Base_Operation() {
		return (EReference)interceptionRuleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFragment() {
		return fragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragment_Base_Class() {
		return (EReference)fragmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssembly() {
		return assemblyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssembly_Base_Class() {
		return (EReference)assemblyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getContainerRuleKind() {
		return containerRuleKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getInterceptionKind() {
		return interceptionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FCMFactory getFCMFactory() {
		return (FCMFactory)getEFactoryInstance();
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
		implementationGroupEClass = createEClass(IMPLEMENTATION_GROUP);
		createEReference(implementationGroupEClass, IMPLEMENTATION_GROUP__BASE_CLASS);

		compToOOmappingEClass = createEClass(COMP_TO_OOMAPPING);
		createEReference(compToOOmappingEClass, COMP_TO_OOMAPPING__BASE_CLASS);

		codeGenOptionsEClass = createEClass(CODE_GEN_OPTIONS);
		createEReference(codeGenOptionsEClass, CODE_GEN_OPTIONS__BASE_PACKAGE);
		createEReference(codeGenOptionsEClass, CODE_GEN_OPTIONS__COMP_TO_OOMAPPING);

		interactionComponentEClass = createEClass(INTERACTION_COMPONENT);
		createEAttribute(interactionComponentEClass, INTERACTION_COMPONENT__FOR_DISTRIBUTION);
		createEReference(interactionComponentEClass, INTERACTION_COMPONENT__CONNECTION_PATTERN);
		createEReference(interactionComponentEClass, INTERACTION_COMPONENT__BASE_CLASS);

		singletonEClass = createEClass(SINGLETON);
		createEReference(singletonEClass, SINGLETON__BASE_CLASS);

		connectorEClass = createEClass(CONNECTOR);
		createEReference(connectorEClass, CONNECTOR__BASE_CONNECTOR);
		createEReference(connectorEClass, CONNECTOR__IC);
		createEReference(connectorEClass, CONNECTOR__BASE_PROPERTY);

		configurableElementInstanceEClass = createEClass(CONFIGURABLE_ELEMENT_INSTANCE);
		createEReference(configurableElementInstanceEClass, CONFIGURABLE_ELEMENT_INSTANCE__CONFIGURATION);

		useInstanceConfiguratorEClass = createEClass(USE_INSTANCE_CONFIGURATOR);
		createEReference(useInstanceConfiguratorEClass, USE_INSTANCE_CONFIGURATOR__BASE_CLASS);
		createEReference(useInstanceConfiguratorEClass, USE_INSTANCE_CONFIGURATOR__CONFIGURATOR);

		instanceConfiguratorEClass = createEClass(INSTANCE_CONFIGURATOR);
		createEReference(instanceConfiguratorEClass, INSTANCE_CONFIGURATOR__BASE_CLASS);
		createEAttribute(instanceConfiguratorEClass, INSTANCE_CONFIGURATOR__ON_NODE_MODEL);

		ruleApplicationEClass = createEClass(RULE_APPLICATION);
		createEReference(ruleApplicationEClass, RULE_APPLICATION__BASE_CLASS);
		createEReference(ruleApplicationEClass, RULE_APPLICATION__CONTAINER_RULE);
		createEReference(ruleApplicationEClass, RULE_APPLICATION__BASE_PACKAGE);

		containerRuleEClass = createEClass(CONTAINER_RULE);
		createEReference(containerRuleEClass, CONTAINER_RULE__BASE_CLASS);
		createEAttribute(containerRuleEClass, CONTAINER_RULE__KIND);

		templatePortEClass = createEClass(TEMPLATE_PORT);
		createEReference(templatePortEClass, TEMPLATE_PORT__BOUND_TYPE);

		portEClass = createEClass(PORT);
		createEReference(portEClass, PORT__BASE_PORT);
		createEReference(portEClass, PORT__KIND);
		createEReference(portEClass, PORT__TYPE);

		portKindEClass = createEClass(PORT_KIND);
		createEReference(portKindEClass, PORT_KIND__BASE_CLASS);
		createEReference(portKindEClass, PORT_KIND__PROVIDED_INTERFACE);
		createEReference(portKindEClass, PORT_KIND__REQUIRED_INTERFACE);
		createEAttribute(portKindEClass, PORT_KIND__EXTENDED_PORT);

		compImplTemplateEClass = createEClass(COMP_IMPL_TEMPLATE);
		createEAttribute(compImplTemplateEClass, COMP_IMPL_TEMPLATE__TEMPLATE_DEFS);
		createEReference(compImplTemplateEClass, COMP_IMPL_TEMPLATE__BASE_CLASS);

		interceptionRuleEClass = createEClass(INTERCEPTION_RULE);
		createEAttribute(interceptionRuleEClass, INTERCEPTION_RULE__INTERCEPTION_KIND);
		createEReference(interceptionRuleEClass, INTERCEPTION_RULE__INTERCEPTION_SET);
		createEReference(interceptionRuleEClass, INTERCEPTION_RULE__BASE_PROPERTY);
		createEReference(interceptionRuleEClass, INTERCEPTION_RULE__BASE_OPERATION);

		fragmentEClass = createEClass(FRAGMENT);
		createEReference(fragmentEClass, FRAGMENT__BASE_CLASS);

		assemblyEClass = createEClass(ASSEMBLY);
		createEReference(assemblyEClass, ASSEMBLY__BASE_CLASS);

		// Create enums
		containerRuleKindEEnum = createEEnum(CONTAINER_RULE_KIND);
		interceptionKindEEnum = createEEnum(INTERCEPTION_KIND);
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
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		connectorEClass.getESuperTypes().add(this.getConfigurableElementInstance());
		templatePortEClass.getESuperTypes().add(this.getPort());
		portEClass.getESuperTypes().add(this.getConfigurableElementInstance());

		// Initialize classes and features; add operations and parameters
		initEClass(implementationGroupEClass, ImplementationGroup.class, "ImplementationGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getImplementationGroup_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, ImplementationGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(compToOOmappingEClass, CompToOOmapping.class, "CompToOOmapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCompToOOmapping_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, CompToOOmapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(codeGenOptionsEClass, CodeGenOptions.class, "CodeGenOptions", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCodeGenOptions_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, CodeGenOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getCodeGenOptions_CompToOOmapping(), this.getCompToOOmapping(), null, "compToOOmapping", null, 1, 1, CodeGenOptions.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(interactionComponentEClass, InteractionComponent.class, "InteractionComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getInteractionComponent_ForDistribution(), theTypesPackage.getBoolean(), "forDistribution", "false", 1, 1, InteractionComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getInteractionComponent_ConnectionPattern(), theUMLPackage.getCollaboration(), null, "connectionPattern", null, 0, 1, InteractionComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getInteractionComponent_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, InteractionComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(singletonEClass, Singleton.class, "Singleton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSingleton_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, Singleton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(connectorEClass, Connector.class, "Connector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getConnector_Base_Connector(), theUMLPackage.getConnector(), null, "base_Connector", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnector_Ic(), this.getInteractionComponent(), null, "ic", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getConnector_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(configurableElementInstanceEClass, ConfigurableElementInstance.class, "ConfigurableElementInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getConfigurableElementInstance_Configuration(), theUMLPackage.getInstanceSpecification(), null, "configuration", null, 0, 1, ConfigurableElementInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(useInstanceConfiguratorEClass, UseInstanceConfigurator.class, "UseInstanceConfigurator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getUseInstanceConfigurator_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, UseInstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getUseInstanceConfigurator_Configurator(), this.getInstanceConfigurator(), null, "configurator", null, 1, 1, UseInstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(instanceConfiguratorEClass, InstanceConfigurator.class, "InstanceConfigurator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getInstanceConfigurator_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, InstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getInstanceConfigurator_OnNodeModel(), theTypesPackage.getBoolean(), "onNodeModel", null, 0, 1, InstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(ruleApplicationEClass, RuleApplication.class, "RuleApplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRuleApplication_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 0, 1, RuleApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getRuleApplication_ContainerRule(), this.getContainerRule(), null, "containerRule", null, 0, -1, RuleApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getRuleApplication_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 0, 1, RuleApplication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(containerRuleEClass, ContainerRule.class, "ContainerRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getContainerRule_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, ContainerRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getContainerRule_Kind(), this.getContainerRuleKind(), "kind", null, 1, 1, ContainerRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(templatePortEClass, TemplatePort.class, "TemplatePort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplatePort_BoundType(), this.getPortKind(), null, "boundType", null, 0, 1, TemplatePort.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(portEClass, Port.class, "Port", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPort_Base_Port(), theUMLPackage.getPort(), null, "base_Port", null, 1, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPort_Kind(), this.getPortKind(), null, "kind", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPort_Type(), theUMLPackage.getType(), null, "type", null, 1, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(portKindEClass, PortKind.class, "PortKind", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPortKind_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, PortKind.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPortKind_ProvidedInterface(), theUMLPackage.getInterface(), null, "providedInterface", null, 0, 1, PortKind.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getPortKind_RequiredInterface(), theUMLPackage.getInterface(), null, "requiredInterface", null, 0, 1, PortKind.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPortKind_ExtendedPort(), theTypesPackage.getBoolean(), "extendedPort", null, 1, 1, PortKind.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(compImplTemplateEClass, CompImplTemplate.class, "CompImplTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCompImplTemplate_TemplateDefs(), theTypesPackage.getString(), "templateDefs", null, 0, 1, CompImplTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getCompImplTemplate_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, CompImplTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(interceptionRuleEClass, InterceptionRule.class, "InterceptionRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getInterceptionRule_InterceptionKind(), this.getInterceptionKind(), "interceptionKind", null, 1, 1, InterceptionRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptionRule_InterceptionSet(), theUMLPackage.getFeature(), null, "interceptionSet", null, 0, -1, InterceptionRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptionRule_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, InterceptionRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$
		initEReference(getInterceptionRule_Base_Operation(), theUMLPackage.getOperation(), null, "base_Operation", null, 1, 1, InterceptionRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(fragmentEClass, Fragment.class, "Fragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getFragment_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, Fragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		initEClass(assemblyEClass, Assembly.class, "Assembly", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAssembly_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, Assembly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(containerRuleKindEEnum, ContainerRuleKind.class, "ContainerRuleKind"); //$NON-NLS-1$
		addEEnumLiteral(containerRuleKindEEnum, ContainerRuleKind.COMPONENT_RULE);
		addEEnumLiteral(containerRuleKindEEnum, ContainerRuleKind.LIGHT_WEIGHT_OO_RULE);
		addEEnumLiteral(containerRuleKindEEnum, ContainerRuleKind.CUSTOM_TRANSFORMATION);

		initEEnum(interceptionKindEEnum, InterceptionKind.class, "InterceptionKind"); //$NON-NLS-1$
		addEEnumLiteral(interceptionKindEEnum, InterceptionKind.INTERCEPT_SOME);
		addEEnumLiteral(interceptionKindEEnum, InterceptionKind.INTERCEPT_ALL);
		addEEnumLiteral(interceptionKindEEnum, InterceptionKind.INTERCEPT_ALL_OUT);
		addEEnumLiteral(interceptionKindEEnum, InterceptionKind.INTERCEPT_ALL_IN);
		addEEnumLiteral(interceptionKindEEnum, InterceptionKind.INTERCEPT_MATCHING);

		// Create resource
		createResource(eNS_URI);
	}

} // FCMPackageImpl
