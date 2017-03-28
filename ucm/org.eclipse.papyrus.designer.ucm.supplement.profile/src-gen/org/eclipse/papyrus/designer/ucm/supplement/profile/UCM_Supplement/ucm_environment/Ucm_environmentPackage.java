/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment;

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
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.Ucm_environmentFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_environmentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_environment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/environment/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCM_Supplement.ucm_environment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_environmentPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.Ucm_environmentPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.EnvironmentModuleImpl <em>Environment Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.EnvironmentModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.Ucm_environmentPackageImpl#getEnvironmentModule()
	 * @generated
	 */
	int ENVIRONMENT_MODULE = 0;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Environment Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Environment Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_MODULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.ResourceDefinitionModuleImpl <em>Resource Definition Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.ResourceDefinitionModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.Ucm_environmentPackageImpl#getResourceDefinitionModule()
	 * @generated
	 */
	int RESOURCE_DEFINITION_MODULE = 1;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEFINITION_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Resource Definition Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEFINITION_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Resource Definition Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEFINITION_MODULE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.EnvironmentModule <em>Environment Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.EnvironmentModule
	 * @generated
	 */
	EClass getEnvironmentModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.EnvironmentModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.EnvironmentModule#getBase_Package()
	 * @see #getEnvironmentModule()
	 * @generated
	 */
	EReference getEnvironmentModule_Base_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.ResourceDefinitionModule <em>Resource Definition Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Definition Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.ResourceDefinitionModule
	 * @generated
	 */
	EClass getResourceDefinitionModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.ResourceDefinitionModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.ResourceDefinitionModule#getBase_Package()
	 * @see #getResourceDefinitionModule()
	 * @generated
	 */
	EReference getResourceDefinitionModule_Base_Package();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_environmentFactory getUcm_environmentFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.EnvironmentModuleImpl <em>Environment Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.EnvironmentModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.Ucm_environmentPackageImpl#getEnvironmentModule()
		 * @generated
		 */
		EClass ENVIRONMENT_MODULE = eINSTANCE.getEnvironmentModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT_MODULE__BASE_PACKAGE = eINSTANCE.getEnvironmentModule_Base_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.ResourceDefinitionModuleImpl <em>Resource Definition Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.ResourceDefinitionModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.Ucm_environmentPackageImpl#getResourceDefinitionModule()
		 * @generated
		 */
		EClass RESOURCE_DEFINITION_MODULE = eINSTANCE.getResourceDefinitionModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_DEFINITION_MODULE__BASE_PACKAGE = eINSTANCE.getResourceDefinitionModule_Base_Package();

	}

} //Ucm_environmentPackage
