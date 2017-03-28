/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment;

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
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_deploymentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_deployment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/deployment/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCM_Supplement.ucm_deployment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_deploymentPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AppAssemblyImpl <em>App Assembly</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AppAssemblyImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl#getAppAssembly()
	 * @generated
	 */
	int APP_ASSEMBLY = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APP_ASSEMBLY__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>App Assembly</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APP_ASSEMBLY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>App Assembly</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APP_ASSEMBLY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.DeploymentModuleImpl <em>Deployment Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.DeploymentModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl#getDeploymentModule()
	 * @generated
	 */
	int DEPLOYMENT_MODULE = 1;

	/**
	 * The feature id for the '<em><b>Programming Languages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES = 0;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_MODULE__BASE_PACKAGE = 1;

	/**
	 * The number of structural features of the '<em>Deployment Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_MODULE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Deployment Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_MODULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AllocationPlanImpl <em>Allocation Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AllocationPlanImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl#getAllocationPlan()
	 * @generated
	 */
	int ALLOCATION_PLAN = 2;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_PLAN__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Allocation Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_PLAN_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Allocation Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_PLAN_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AppAssembly <em>App Assembly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>App Assembly</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AppAssembly
	 * @generated
	 */
	EClass getAppAssembly();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AppAssembly#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AppAssembly#getBase_Class()
	 * @see #getAppAssembly()
	 * @generated
	 */
	EReference getAppAssembly_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule <em>Deployment Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule
	 * @generated
	 */
	EClass getDeploymentModule();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getProgrammingLanguages <em>Programming Languages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Programming Languages</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getProgrammingLanguages()
	 * @see #getDeploymentModule()
	 * @generated
	 */
	EReference getDeploymentModule_ProgrammingLanguages();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getBase_Package()
	 * @see #getDeploymentModule()
	 * @generated
	 */
	EReference getDeploymentModule_Base_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AllocationPlan <em>Allocation Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Allocation Plan</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AllocationPlan
	 * @generated
	 */
	EClass getAllocationPlan();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AllocationPlan#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AllocationPlan#getBase_Package()
	 * @see #getAllocationPlan()
	 * @generated
	 */
	EReference getAllocationPlan_Base_Package();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_deploymentFactory getUcm_deploymentFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AppAssemblyImpl <em>App Assembly</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AppAssemblyImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl#getAppAssembly()
		 * @generated
		 */
		EClass APP_ASSEMBLY = eINSTANCE.getAppAssembly();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APP_ASSEMBLY__BASE_CLASS = eINSTANCE.getAppAssembly_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.DeploymentModuleImpl <em>Deployment Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.DeploymentModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl#getDeploymentModule()
		 * @generated
		 */
		EClass DEPLOYMENT_MODULE = eINSTANCE.getDeploymentModule();

		/**
		 * The meta object literal for the '<em><b>Programming Languages</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES = eINSTANCE.getDeploymentModule_ProgrammingLanguages();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_MODULE__BASE_PACKAGE = eINSTANCE.getDeploymentModule_Base_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AllocationPlanImpl <em>Allocation Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.AllocationPlanImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl#getAllocationPlan()
		 * @generated
		 */
		EClass ALLOCATION_PLAN = eINSTANCE.getAllocationPlan();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALLOCATION_PLAN__BASE_PACKAGE = eINSTANCE.getAllocationPlan_Base_Package();

	}

} //Ucm_deploymentPackage
