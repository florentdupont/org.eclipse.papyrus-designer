/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentFactory
 * @model kind="package"
 * @generated
 */
public interface DeploymentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Deployment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/Deployment/1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Deployment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DeploymentPackage eINSTANCE = org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ConfigurationPropertyImpl <em>Configuration Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ConfigurationPropertyImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getConfigurationProperty()
	 * @generated
	 */
	int CONFIGURATION_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Configuration Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Configuration Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexImpl <em>Auto Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getAutoIndex()
	 * @generated
	 */
	int AUTO_INDEX = 1;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Auto Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Auto Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexPerNodeImpl <em>Auto Index Per Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexPerNodeImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getAutoIndexPerNode()
	 * @generated
	 */
	int AUTO_INDEX_PER_NODE = 2;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_PER_NODE__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Auto Index Per Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_PER_NODE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Auto Index Per Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_PER_NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.CopyAttributeValueImpl <em>Copy Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.CopyAttributeValueImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getCopyAttributeValue()
	 * @generated
	 */
	int COPY_ATTRIBUTE_VALUE = 3;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE__BASE_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE__SOURCE = 1;

	/**
	 * The number of structural features of the '<em>Copy Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Copy Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InitPrecedenceImpl <em>Init Precedence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InitPrecedenceImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getInitPrecedence()
	 * @generated
	 */
	int INIT_PRECEDENCE = 4;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_PRECEDENCE__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Invoke Before</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_PRECEDENCE__INVOKE_BEFORE = 1;

	/**
	 * The feature id for the '<em><b>Invoke After</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_PRECEDENCE__INVOKE_AFTER = 2;

	/**
	 * The number of structural features of the '<em>Init Precedence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_PRECEDENCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Init Precedence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INIT_PRECEDENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl <em>Target</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getTarget()
	 * @generated
	 */
	int TARGET = 5;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Used OS</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET__USED_OS = 1;

	/**
	 * The feature id for the '<em><b>Avail RAM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET__AVAIL_RAM = 2;

	/**
	 * The feature id for the '<em><b>Avail ROM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET__AVAIL_ROM = 3;

	/**
	 * The number of structural features of the '<em>Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.OperatingSystemImpl <em>Operating System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.OperatingSystemImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getOperatingSystem()
	 * @generated
	 */
	int OPERATING_SYSTEM = 6;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATING_SYSTEM__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Operating System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATING_SYSTEM_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Operating System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATING_SYSTEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl <em>Implementation Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getImplementationProperties()
	 * @generated
	 */
	int IMPLEMENTATION_PROPERTIES = 7;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_PROPERTIES__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Size Ram</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_PROPERTIES__SIZE_RAM = 1;

	/**
	 * The feature id for the '<em><b>Size ROM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_PROPERTIES__SIZE_ROM = 2;

	/**
	 * The feature id for the '<em><b>Supported OS</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_PROPERTIES__SUPPORTED_OS = 3;

	/**
	 * The feature id for the '<em><b>Arch</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_PROPERTIES__ARCH = 4;

	/**
	 * The number of structural features of the '<em>Implementation Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_PROPERTIES_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Implementation Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_PROPERTIES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetArchitectureImpl <em>Target Architecture</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetArchitectureImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getTargetArchitecture()
	 * @generated
	 */
	int TARGET_ARCHITECTURE = 8;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_ARCHITECTURE__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Target Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_ARCHITECTURE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Target Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TARGET_ARCHITECTURE_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl <em>Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getDeploymentPlan()
	 * @generated
	 */
	int DEPLOYMENT_PLAN = 9;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PLAN__BASE_PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Project Mappings</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PLAN__PROJECT_MAPPINGS = 1;

	/**
	 * The number of structural features of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PLAN_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PLAN_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.SingletonImpl <em>Singleton</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.SingletonImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getSingleton()
	 * @generated
	 */
	int SINGLETON = 10;

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
	 * The number of operations of the '<em>Singleton</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLETON_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InstanceConfiguratorImpl <em>Instance Configurator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InstanceConfiguratorImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getInstanceConfigurator()
	 * @generated
	 */
	int INSTANCE_CONFIGURATOR = 11;

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
	 * The number of operations of the '<em>Instance Configurator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_CONFIGURATOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.UseInstanceConfiguratorImpl <em>Use Instance Configurator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.UseInstanceConfiguratorImpl
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getUseInstanceConfigurator()
	 * @generated
	 */
	int USE_INSTANCE_CONFIGURATOR = 12;

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
	 * The number of operations of the '<em>Use Instance Configurator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_INSTANCE_CONFIGURATOR_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty <em>Configuration Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Property</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty
	 * @generated
	 */
	EClass getConfigurationProperty();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty#getBase_Property()
	 * @see #getConfigurationProperty()
	 * @generated
	 */
	EReference getConfigurationProperty_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndex <em>Auto Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Auto Index</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndex
	 * @generated
	 */
	EClass getAutoIndex();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndex#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndex#getBase_Property()
	 * @see #getAutoIndex()
	 * @generated
	 */
	EReference getAutoIndex_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndexPerNode <em>Auto Index Per Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Auto Index Per Node</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndexPerNode
	 * @generated
	 */
	EClass getAutoIndexPerNode();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndexPerNode#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndexPerNode#getBase_Property()
	 * @see #getAutoIndexPerNode()
	 * @generated
	 */
	EReference getAutoIndexPerNode_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue <em>Copy Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Copy Attribute Value</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue
	 * @generated
	 */
	EClass getCopyAttributeValue();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue#getBase_Property()
	 * @see #getCopyAttributeValue()
	 * @generated
	 */
	EReference getCopyAttributeValue_Base_Property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue#getSource()
	 * @see #getCopyAttributeValue()
	 * @generated
	 */
	EReference getCopyAttributeValue_Source();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence <em>Init Precedence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Init Precedence</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence
	 * @generated
	 */
	EClass getInitPrecedence();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getBase_Class()
	 * @see #getInitPrecedence()
	 * @generated
	 */
	EReference getInitPrecedence_Base_Class();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getInvokeBefore <em>Invoke Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Invoke Before</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getInvokeBefore()
	 * @see #getInitPrecedence()
	 * @generated
	 */
	EReference getInitPrecedence_InvokeBefore();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getInvokeAfter <em>Invoke After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Invoke After</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getInvokeAfter()
	 * @see #getInitPrecedence()
	 * @generated
	 */
	EReference getInitPrecedence_InvokeAfter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Target</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.Target
	 * @generated
	 */
	EClass getTarget();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getBase_Class()
	 * @see #getTarget()
	 * @generated
	 */
	EReference getTarget_Base_Class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getUsedOS <em>Used OS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Used OS</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getUsedOS()
	 * @see #getTarget()
	 * @generated
	 */
	EReference getTarget_UsedOS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailRAM <em>Avail RAM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Avail RAM</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailRAM()
	 * @see #getTarget()
	 * @generated
	 */
	EAttribute getTarget_AvailRAM();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailROM <em>Avail ROM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Avail ROM</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailROM()
	 * @see #getTarget()
	 * @generated
	 */
	EAttribute getTarget_AvailROM();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem <em>Operating System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operating System</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem
	 * @generated
	 */
	EClass getOperatingSystem();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem#getBase_Class()
	 * @see #getOperatingSystem()
	 * @generated
	 */
	EReference getOperatingSystem_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties <em>Implementation Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implementation Properties</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties
	 * @generated
	 */
	EClass getImplementationProperties();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getBase_Class()
	 * @see #getImplementationProperties()
	 * @generated
	 */
	EReference getImplementationProperties_Base_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeRam <em>Size Ram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size Ram</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeRam()
	 * @see #getImplementationProperties()
	 * @generated
	 */
	EAttribute getImplementationProperties_SizeRam();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeROM <em>Size ROM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size ROM</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeROM()
	 * @see #getImplementationProperties()
	 * @generated
	 */
	EAttribute getImplementationProperties_SizeROM();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSupportedOS <em>Supported OS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supported OS</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSupportedOS()
	 * @see #getImplementationProperties()
	 * @generated
	 */
	EReference getImplementationProperties_SupportedOS();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getArch <em>Arch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Arch</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getArch()
	 * @see #getImplementationProperties()
	 * @generated
	 */
	EReference getImplementationProperties_Arch();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.TargetArchitecture <em>Target Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Target Architecture</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.TargetArchitecture
	 * @generated
	 */
	EClass getTargetArchitecture();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.TargetArchitecture#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.TargetArchitecture#getBase_Class()
	 * @see #getTargetArchitecture()
	 * @generated
	 */
	EReference getTargetArchitecture_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plan</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan
	 * @generated
	 */
	EClass getDeploymentPlan();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getBase_Package()
	 * @see #getDeploymentPlan()
	 * @generated
	 */
	EReference getDeploymentPlan_Base_Package();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getProjectMappings <em>Project Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Project Mappings</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getProjectMappings()
	 * @see #getDeploymentPlan()
	 * @generated
	 */
	EAttribute getDeploymentPlan_ProjectMappings();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Singleton <em>Singleton</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Singleton</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.Singleton
	 * @generated
	 */
	EClass getSingleton();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Singleton#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.Singleton#getBase_Class()
	 * @see #getSingleton()
	 * @generated
	 */
	EReference getSingleton_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator <em>Instance Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Configurator</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator
	 * @generated
	 */
	EClass getInstanceConfigurator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator#getBase_Class()
	 * @see #getInstanceConfigurator()
	 * @generated
	 */
	EReference getInstanceConfigurator_Base_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator#isOnNodeModel <em>On Node Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Node Model</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator#isOnNodeModel()
	 * @see #getInstanceConfigurator()
	 * @generated
	 */
	EAttribute getInstanceConfigurator_OnNodeModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator <em>Use Instance Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Instance Configurator</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator
	 * @generated
	 */
	EClass getUseInstanceConfigurator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getBase_Class()
	 * @see #getUseInstanceConfigurator()
	 * @generated
	 */
	EReference getUseInstanceConfigurator_Base_Class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getConfigurator <em>Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Configurator</em>'.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getConfigurator()
	 * @see #getUseInstanceConfigurator()
	 * @generated
	 */
	EReference getUseInstanceConfigurator_Configurator();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DeploymentFactory getDeploymentFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ConfigurationPropertyImpl <em>Configuration Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ConfigurationPropertyImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getConfigurationProperty()
		 * @generated
		 */
		EClass CONFIGURATION_PROPERTY = eINSTANCE.getConfigurationProperty();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION_PROPERTY__BASE_PROPERTY = eINSTANCE.getConfigurationProperty_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexImpl <em>Auto Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getAutoIndex()
		 * @generated
		 */
		EClass AUTO_INDEX = eINSTANCE.getAutoIndex();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTO_INDEX__BASE_PROPERTY = eINSTANCE.getAutoIndex_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexPerNodeImpl <em>Auto Index Per Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.AutoIndexPerNodeImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getAutoIndexPerNode()
		 * @generated
		 */
		EClass AUTO_INDEX_PER_NODE = eINSTANCE.getAutoIndexPerNode();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTO_INDEX_PER_NODE__BASE_PROPERTY = eINSTANCE.getAutoIndexPerNode_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.CopyAttributeValueImpl <em>Copy Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.CopyAttributeValueImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getCopyAttributeValue()
		 * @generated
		 */
		EClass COPY_ATTRIBUTE_VALUE = eINSTANCE.getCopyAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COPY_ATTRIBUTE_VALUE__BASE_PROPERTY = eINSTANCE.getCopyAttributeValue_Base_Property();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COPY_ATTRIBUTE_VALUE__SOURCE = eINSTANCE.getCopyAttributeValue_Source();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InitPrecedenceImpl <em>Init Precedence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InitPrecedenceImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getInitPrecedence()
		 * @generated
		 */
		EClass INIT_PRECEDENCE = eINSTANCE.getInitPrecedence();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INIT_PRECEDENCE__BASE_CLASS = eINSTANCE.getInitPrecedence_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Invoke Before</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INIT_PRECEDENCE__INVOKE_BEFORE = eINSTANCE.getInitPrecedence_InvokeBefore();

		/**
		 * The meta object literal for the '<em><b>Invoke After</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INIT_PRECEDENCE__INVOKE_AFTER = eINSTANCE.getInitPrecedence_InvokeAfter();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl <em>Target</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getTarget()
		 * @generated
		 */
		EClass TARGET = eINSTANCE.getTarget();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TARGET__BASE_CLASS = eINSTANCE.getTarget_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Used OS</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TARGET__USED_OS = eINSTANCE.getTarget_UsedOS();

		/**
		 * The meta object literal for the '<em><b>Avail RAM</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TARGET__AVAIL_RAM = eINSTANCE.getTarget_AvailRAM();

		/**
		 * The meta object literal for the '<em><b>Avail ROM</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TARGET__AVAIL_ROM = eINSTANCE.getTarget_AvailROM();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.OperatingSystemImpl <em>Operating System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.OperatingSystemImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getOperatingSystem()
		 * @generated
		 */
		EClass OPERATING_SYSTEM = eINSTANCE.getOperatingSystem();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATING_SYSTEM__BASE_CLASS = eINSTANCE.getOperatingSystem_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl <em>Implementation Properties</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getImplementationProperties()
		 * @generated
		 */
		EClass IMPLEMENTATION_PROPERTIES = eINSTANCE.getImplementationProperties();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_PROPERTIES__BASE_CLASS = eINSTANCE.getImplementationProperties_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Size Ram</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION_PROPERTIES__SIZE_RAM = eINSTANCE.getImplementationProperties_SizeRam();

		/**
		 * The meta object literal for the '<em><b>Size ROM</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION_PROPERTIES__SIZE_ROM = eINSTANCE.getImplementationProperties_SizeROM();

		/**
		 * The meta object literal for the '<em><b>Supported OS</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_PROPERTIES__SUPPORTED_OS = eINSTANCE.getImplementationProperties_SupportedOS();

		/**
		 * The meta object literal for the '<em><b>Arch</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION_PROPERTIES__ARCH = eINSTANCE.getImplementationProperties_Arch();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetArchitectureImpl <em>Target Architecture</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetArchitectureImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getTargetArchitecture()
		 * @generated
		 */
		EClass TARGET_ARCHITECTURE = eINSTANCE.getTargetArchitecture();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TARGET_ARCHITECTURE__BASE_CLASS = eINSTANCE.getTargetArchitecture_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl <em>Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getDeploymentPlan()
		 * @generated
		 */
		EClass DEPLOYMENT_PLAN = eINSTANCE.getDeploymentPlan();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_PLAN__BASE_PACKAGE = eINSTANCE.getDeploymentPlan_Base_Package();

		/**
		 * The meta object literal for the '<em><b>Project Mappings</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_PLAN__PROJECT_MAPPINGS = eINSTANCE.getDeploymentPlan_ProjectMappings();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.SingletonImpl <em>Singleton</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.SingletonImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getSingleton()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InstanceConfiguratorImpl <em>Instance Configurator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InstanceConfiguratorImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getInstanceConfigurator()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.UseInstanceConfiguratorImpl <em>Use Instance Configurator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.UseInstanceConfiguratorImpl
		 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPackageImpl#getUseInstanceConfigurator()
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

	}

} //DeploymentPackage
