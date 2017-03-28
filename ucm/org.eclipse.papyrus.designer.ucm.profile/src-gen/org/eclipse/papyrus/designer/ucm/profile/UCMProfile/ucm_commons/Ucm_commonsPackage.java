/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons;

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
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_commonsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_commons";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/commons/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCMProfile.ucm_commons";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_commonsPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.ApplicationModuleImpl <em>Application Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.ApplicationModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl#getApplicationModule()
	 * @generated
	 */
	int APPLICATION_MODULE = 0;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Application Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Application Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.PlatformModuleImpl <em>Platform Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.PlatformModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl#getPlatformModule()
	 * @generated
	 */
	int PLATFORM_MODULE = 1;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Platform Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Platform Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_MODULE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.ApplicationModule <em>Application Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.ApplicationModule
	 * @generated
	 */
	EClass getApplicationModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.ApplicationModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.ApplicationModule#getBase_Package()
	 * @see #getApplicationModule()
	 * @generated
	 */
	EReference getApplicationModule_Base_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.PlatformModule <em>Platform Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.PlatformModule
	 * @generated
	 */
	EClass getPlatformModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.PlatformModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.PlatformModule#getBase_Package()
	 * @see #getPlatformModule()
	 * @generated
	 */
	EReference getPlatformModule_Base_Package();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_commonsFactory getUcm_commonsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.ApplicationModuleImpl <em>Application Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.ApplicationModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl#getApplicationModule()
		 * @generated
		 */
		EClass APPLICATION_MODULE = eINSTANCE.getApplicationModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_MODULE__BASE_PACKAGE = eINSTANCE.getApplicationModule_Base_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.PlatformModuleImpl <em>Platform Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.PlatformModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl#getPlatformModule()
		 * @generated
		 */
		EClass PLATFORM_MODULE = eINSTANCE.getPlatformModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM_MODULE__BASE_PACKAGE = eINSTANCE.getPlatformModule_Base_Package();

	}

} //Ucm_commonsPackage
