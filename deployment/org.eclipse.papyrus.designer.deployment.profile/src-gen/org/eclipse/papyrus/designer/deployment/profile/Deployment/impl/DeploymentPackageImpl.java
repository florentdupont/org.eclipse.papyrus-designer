/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndex;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndexPerNode;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentFactory;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Singleton;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Target;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.TargetArchitecture;

import org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage;
import org.eclipse.uml2.types.TypesPackage;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeploymentPackageImpl extends EPackageImpl implements DeploymentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass autoIndexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass autoIndexPerNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass copyAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initPrecedenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass targetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operatingSystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass implementationPropertiesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass targetArchitectureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deploymentPlanEClass = null;

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
	private EClass instanceConfiguratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass useInstanceConfiguratorEClass = null;

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
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DeploymentPackageImpl() {
		super(eNS_URI, DeploymentFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DeploymentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DeploymentPackage init() {
		if (isInited) return (DeploymentPackage)EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI);

		// Obtain or create and register package
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DeploymentPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TransformationPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theDeploymentPackage.createPackageContents();

		// Initialize created meta-data
		theDeploymentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDeploymentPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DeploymentPackage.eNS_URI, theDeploymentPackage);
		return theDeploymentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfigurationProperty() {
		return configurationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfigurationProperty_Base_Property() {
		return (EReference)configurationPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAutoIndex() {
		return autoIndexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAutoIndex_Base_Property() {
		return (EReference)autoIndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAutoIndexPerNode() {
		return autoIndexPerNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAutoIndexPerNode_Base_Property() {
		return (EReference)autoIndexPerNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCopyAttributeValue() {
		return copyAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCopyAttributeValue_Base_Property() {
		return (EReference)copyAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCopyAttributeValue_Source() {
		return (EReference)copyAttributeValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitPrecedence() {
		return initPrecedenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInitPrecedence_Base_Class() {
		return (EReference)initPrecedenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInitPrecedence_InvokeBefore() {
		return (EReference)initPrecedenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInitPrecedence_InvokeAfter() {
		return (EReference)initPrecedenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTarget() {
		return targetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTarget_Base_Class() {
		return (EReference)targetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTarget_UsedOS() {
		return (EReference)targetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTarget_AvailRAM() {
		return (EAttribute)targetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTarget_AvailROM() {
		return (EAttribute)targetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTarget_Target() {
		return (EReference)targetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperatingSystem() {
		return operatingSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperatingSystem_Base_Class() {
		return (EReference)operatingSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImplementationProperties() {
		return implementationPropertiesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationProperties_Base_Class() {
		return (EReference)implementationPropertiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImplementationProperties_SizeRam() {
		return (EAttribute)implementationPropertiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImplementationProperties_SizeROM() {
		return (EAttribute)implementationPropertiesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationProperties_SupportedOS() {
		return (EReference)implementationPropertiesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImplementationProperties_Arch() {
		return (EReference)implementationPropertiesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTargetArchitecture() {
		return targetArchitectureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTargetArchitecture_Base_Class() {
		return (EReference)targetArchitectureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeploymentPlan() {
		return deploymentPlanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeploymentPlan_Base_Package() {
		return (EReference)deploymentPlanEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDeploymentPlan_ProjectMappings() {
		return (EAttribute)deploymentPlanEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeploymentPlan_Chain() {
		return (EReference)deploymentPlanEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeploymentPlan_AdditionalTrafos() {
		return (EReference)deploymentPlanEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleton() {
		return singletonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleton_Base_Class() {
		return (EReference)singletonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanceConfigurator() {
		return instanceConfiguratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceConfigurator_Base_Class() {
		return (EReference)instanceConfiguratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstanceConfigurator_OnNodeModel() {
		return (EAttribute)instanceConfiguratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUseInstanceConfigurator() {
		return useInstanceConfiguratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseInstanceConfigurator_Base_Class() {
		return (EReference)useInstanceConfiguratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseInstanceConfigurator_Configurator() {
		return (EReference)useInstanceConfiguratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentFactory getDeploymentFactory() {
		return (DeploymentFactory)getEFactoryInstance();
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
		deploymentPlanEClass = createEClass(DEPLOYMENT_PLAN);
		createEReference(deploymentPlanEClass, DEPLOYMENT_PLAN__BASE_PACKAGE);
		createEAttribute(deploymentPlanEClass, DEPLOYMENT_PLAN__PROJECT_MAPPINGS);
		createEReference(deploymentPlanEClass, DEPLOYMENT_PLAN__CHAIN);
		createEReference(deploymentPlanEClass, DEPLOYMENT_PLAN__ADDITIONAL_TRAFOS);

		configurationPropertyEClass = createEClass(CONFIGURATION_PROPERTY);
		createEReference(configurationPropertyEClass, CONFIGURATION_PROPERTY__BASE_PROPERTY);

		autoIndexEClass = createEClass(AUTO_INDEX);
		createEReference(autoIndexEClass, AUTO_INDEX__BASE_PROPERTY);

		autoIndexPerNodeEClass = createEClass(AUTO_INDEX_PER_NODE);
		createEReference(autoIndexPerNodeEClass, AUTO_INDEX_PER_NODE__BASE_PROPERTY);

		copyAttributeValueEClass = createEClass(COPY_ATTRIBUTE_VALUE);
		createEReference(copyAttributeValueEClass, COPY_ATTRIBUTE_VALUE__BASE_PROPERTY);
		createEReference(copyAttributeValueEClass, COPY_ATTRIBUTE_VALUE__SOURCE);

		initPrecedenceEClass = createEClass(INIT_PRECEDENCE);
		createEReference(initPrecedenceEClass, INIT_PRECEDENCE__BASE_CLASS);
		createEReference(initPrecedenceEClass, INIT_PRECEDENCE__INVOKE_BEFORE);
		createEReference(initPrecedenceEClass, INIT_PRECEDENCE__INVOKE_AFTER);

		targetEClass = createEClass(TARGET);
		createEReference(targetEClass, TARGET__BASE_CLASS);
		createEReference(targetEClass, TARGET__USED_OS);
		createEAttribute(targetEClass, TARGET__AVAIL_RAM);
		createEAttribute(targetEClass, TARGET__AVAIL_ROM);
		createEReference(targetEClass, TARGET__TARGET);

		operatingSystemEClass = createEClass(OPERATING_SYSTEM);
		createEReference(operatingSystemEClass, OPERATING_SYSTEM__BASE_CLASS);

		targetArchitectureEClass = createEClass(TARGET_ARCHITECTURE);
		createEReference(targetArchitectureEClass, TARGET_ARCHITECTURE__BASE_CLASS);

		implementationPropertiesEClass = createEClass(IMPLEMENTATION_PROPERTIES);
		createEReference(implementationPropertiesEClass, IMPLEMENTATION_PROPERTIES__BASE_CLASS);
		createEAttribute(implementationPropertiesEClass, IMPLEMENTATION_PROPERTIES__SIZE_RAM);
		createEAttribute(implementationPropertiesEClass, IMPLEMENTATION_PROPERTIES__SIZE_ROM);
		createEReference(implementationPropertiesEClass, IMPLEMENTATION_PROPERTIES__SUPPORTED_OS);
		createEReference(implementationPropertiesEClass, IMPLEMENTATION_PROPERTIES__ARCH);

		singletonEClass = createEClass(SINGLETON);
		createEReference(singletonEClass, SINGLETON__BASE_CLASS);

		instanceConfiguratorEClass = createEClass(INSTANCE_CONFIGURATOR);
		createEReference(instanceConfiguratorEClass, INSTANCE_CONFIGURATOR__BASE_CLASS);
		createEAttribute(instanceConfiguratorEClass, INSTANCE_CONFIGURATOR__ON_NODE_MODEL);

		useInstanceConfiguratorEClass = createEClass(USE_INSTANCE_CONFIGURATOR);
		createEReference(useInstanceConfiguratorEClass, USE_INSTANCE_CONFIGURATOR__BASE_CLASS);
		createEReference(useInstanceConfiguratorEClass, USE_INSTANCE_CONFIGURATOR__CONFIGURATOR);
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
		TransformationPackage theTransformationPackage = (TransformationPackage)EPackage.Registry.INSTANCE.getEPackage(TransformationPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(deploymentPlanEClass, DeploymentPlan.class, "DeploymentPlan", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeploymentPlan_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, DeploymentPlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDeploymentPlan_ProjectMappings(), theTypesPackage.getString(), "projectMappings", null, 0, -1, DeploymentPlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDeploymentPlan_Chain(), theTransformationPackage.getM2MTrafoChain(), null, "chain", null, 0, 1, DeploymentPlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDeploymentPlan_AdditionalTrafos(), theTransformationPackage.getM2MTrafo(), null, "additionalTrafos", null, 0, -1, DeploymentPlan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(configurationPropertyEClass, ConfigurationProperty.class, "ConfigurationProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConfigurationProperty_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, ConfigurationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(autoIndexEClass, AutoIndex.class, "AutoIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAutoIndex_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, AutoIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(autoIndexPerNodeEClass, AutoIndexPerNode.class, "AutoIndexPerNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAutoIndexPerNode_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, AutoIndexPerNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(copyAttributeValueEClass, CopyAttributeValue.class, "CopyAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCopyAttributeValue_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, CopyAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCopyAttributeValue_Source(), theUMLPackage.getProperty(), null, "source", null, 1, 1, CopyAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(initPrecedenceEClass, InitPrecedence.class, "InitPrecedence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInitPrecedence_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, InitPrecedence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInitPrecedence_InvokeBefore(), theUMLPackage.getClass_(), null, "invokeBefore", null, 0, -1, InitPrecedence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInitPrecedence_InvokeAfter(), theUMLPackage.getClass_(), null, "invokeAfter", null, 0, -1, InitPrecedence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(targetEClass, Target.class, "Target", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTarget_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTarget_UsedOS(), this.getOperatingSystem(), null, "usedOS", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getTarget_AvailRAM(), theTypesPackage.getInteger(), "availRAM", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getTarget_AvailROM(), theTypesPackage.getInteger(), "availROM", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTarget_Target(), this.getTargetArchitecture(), null, "target", null, 1, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(operatingSystemEClass, OperatingSystem.class, "OperatingSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperatingSystem_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, OperatingSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(targetArchitectureEClass, TargetArchitecture.class, "TargetArchitecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTargetArchitecture_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, TargetArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(implementationPropertiesEClass, ImplementationProperties.class, "ImplementationProperties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImplementationProperties_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, ImplementationProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImplementationProperties_SizeRam(), theTypesPackage.getInteger(), "sizeRam", null, 1, 1, ImplementationProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getImplementationProperties_SizeROM(), theTypesPackage.getInteger(), "sizeROM", null, 1, 1, ImplementationProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getImplementationProperties_SupportedOS(), this.getOperatingSystem(), null, "supportedOS", null, 0, -1, ImplementationProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getImplementationProperties_Arch(), this.getTargetArchitecture(), null, "arch", null, 1, 1, ImplementationProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(singletonEClass, Singleton.class, "Singleton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleton_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, Singleton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(instanceConfiguratorEClass, InstanceConfigurator.class, "InstanceConfigurator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstanceConfigurator_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, InstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstanceConfigurator_OnNodeModel(), theTypesPackage.getBoolean(), "onNodeModel", null, 1, 1, InstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(useInstanceConfiguratorEClass, UseInstanceConfigurator.class, "UseInstanceConfigurator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUseInstanceConfigurator_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, UseInstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUseInstanceConfigurator_Configurator(), this.getInstanceConfigurator(), null, "configurator", null, 1, 1, UseInstanceConfigurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //DeploymentPackageImpl
