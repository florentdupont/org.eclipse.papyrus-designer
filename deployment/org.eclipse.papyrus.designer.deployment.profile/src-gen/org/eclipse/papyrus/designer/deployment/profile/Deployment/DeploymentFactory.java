/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage
 * @generated
 */
public interface DeploymentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DeploymentFactory eINSTANCE = org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Configuration Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configuration Property</em>'.
	 * @generated
	 */
	ConfigurationProperty createConfigurationProperty();

	/**
	 * Returns a new object of class '<em>Auto Index</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Auto Index</em>'.
	 * @generated
	 */
	AutoIndex createAutoIndex();

	/**
	 * Returns a new object of class '<em>Auto Index Per Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Auto Index Per Node</em>'.
	 * @generated
	 */
	AutoIndexPerNode createAutoIndexPerNode();

	/**
	 * Returns a new object of class '<em>Copy Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Copy Attribute Value</em>'.
	 * @generated
	 */
	CopyAttributeValue createCopyAttributeValue();

	/**
	 * Returns a new object of class '<em>Init Precedence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Init Precedence</em>'.
	 * @generated
	 */
	InitPrecedence createInitPrecedence();

	/**
	 * Returns a new object of class '<em>Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Target</em>'.
	 * @generated
	 */
	Target createTarget();

	/**
	 * Returns a new object of class '<em>Operating System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operating System</em>'.
	 * @generated
	 */
	OperatingSystem createOperatingSystem();

	/**
	 * Returns a new object of class '<em>Implementation Properties</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Implementation Properties</em>'.
	 * @generated
	 */
	ImplementationProperties createImplementationProperties();

	/**
	 * Returns a new object of class '<em>Target Architecture</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Target Architecture</em>'.
	 * @generated
	 */
	TargetArchitecture createTargetArchitecture();

	/**
	 * Returns a new object of class '<em>Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Plan</em>'.
	 * @generated
	 */
	DeploymentPlan createDeploymentPlan();

	/**
	 * Returns a new object of class '<em>Singleton</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Singleton</em>'.
	 * @generated
	 */
	Singleton createSingleton();

	/**
	 * Returns a new object of class '<em>Instance Configurator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instance Configurator</em>'.
	 * @generated
	 */
	InstanceConfigurator createInstanceConfigurator();

	/**
	 * Returns a new object of class '<em>Use Instance Configurator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Use Instance Configurator</em>'.
	 * @generated
	 */
	UseInstanceConfigurator createUseInstanceConfigurator();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DeploymentPackage getDeploymentPackage();

} //DeploymentFactory
