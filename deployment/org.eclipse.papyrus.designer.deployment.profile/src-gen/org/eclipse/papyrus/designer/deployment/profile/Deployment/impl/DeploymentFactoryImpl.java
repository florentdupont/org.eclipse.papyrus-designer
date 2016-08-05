/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.deployment.profile.Deployment.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeploymentFactoryImpl extends EFactoryImpl implements DeploymentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeploymentFactory init() {
		try {
			DeploymentFactory theDeploymentFactory = (DeploymentFactory)EPackage.Registry.INSTANCE.getEFactory(DeploymentPackage.eNS_URI);
			if (theDeploymentFactory != null) {
				return theDeploymentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DeploymentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentFactoryImpl() {
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
			case DeploymentPackage.CONFIGURATION_PROPERTY: return createConfigurationProperty();
			case DeploymentPackage.AUTO_INDEX: return createAutoIndex();
			case DeploymentPackage.AUTO_INDEX_PER_NODE: return createAutoIndexPerNode();
			case DeploymentPackage.COPY_ATTRIBUTE_VALUE: return createCopyAttributeValue();
			case DeploymentPackage.INIT_PRECEDENCE: return createInitPrecedence();
			case DeploymentPackage.TARGET: return createTarget();
			case DeploymentPackage.OPERATING_SYSTEM: return createOperatingSystem();
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES: return createImplementationProperties();
			case DeploymentPackage.TARGET_ARCHITECTURE: return createTargetArchitecture();
			case DeploymentPackage.DEPLOYMENT_PLAN: return createDeploymentPlan();
			case DeploymentPackage.SINGLETON: return createSingleton();
			case DeploymentPackage.INSTANCE_CONFIGURATOR: return createInstanceConfigurator();
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR: return createUseInstanceConfigurator();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationProperty createConfigurationProperty() {
		ConfigurationPropertyImpl configurationProperty = new ConfigurationPropertyImpl();
		return configurationProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AutoIndex createAutoIndex() {
		AutoIndexImpl autoIndex = new AutoIndexImpl();
		return autoIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AutoIndexPerNode createAutoIndexPerNode() {
		AutoIndexPerNodeImpl autoIndexPerNode = new AutoIndexPerNodeImpl();
		return autoIndexPerNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CopyAttributeValue createCopyAttributeValue() {
		CopyAttributeValueImpl copyAttributeValue = new CopyAttributeValueImpl();
		return copyAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitPrecedence createInitPrecedence() {
		InitPrecedenceImpl initPrecedence = new InitPrecedenceImpl();
		return initPrecedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Target createTarget() {
		TargetImpl target = new TargetImpl();
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatingSystem createOperatingSystem() {
		OperatingSystemImpl operatingSystem = new OperatingSystemImpl();
		return operatingSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementationProperties createImplementationProperties() {
		ImplementationPropertiesImpl implementationProperties = new ImplementationPropertiesImpl();
		return implementationProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetArchitecture createTargetArchitecture() {
		TargetArchitectureImpl targetArchitecture = new TargetArchitectureImpl();
		return targetArchitecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentPlan createDeploymentPlan() {
		DeploymentPlanImpl deploymentPlan = new DeploymentPlanImpl();
		return deploymentPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Singleton createSingleton() {
		SingletonImpl singleton = new SingletonImpl();
		return singleton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceConfigurator createInstanceConfigurator() {
		InstanceConfiguratorImpl instanceConfigurator = new InstanceConfiguratorImpl();
		return instanceConfigurator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseInstanceConfigurator createUseInstanceConfigurator() {
		UseInstanceConfiguratorImpl useInstanceConfigurator = new UseInstanceConfiguratorImpl();
		return useInstanceConfigurator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentPackage getDeploymentPackage() {
		return (DeploymentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DeploymentPackage getPackage() {
		return DeploymentPackage.eINSTANCE;
	}

} //DeploymentFactoryImpl
