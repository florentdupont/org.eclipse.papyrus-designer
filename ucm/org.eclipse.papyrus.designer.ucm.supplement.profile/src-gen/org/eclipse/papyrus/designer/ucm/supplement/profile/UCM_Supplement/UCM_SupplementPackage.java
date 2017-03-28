/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.UCM_SupplementFactory
 * @model kind="package"
 * @generated
 */
public interface UCM_SupplementPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "UCM_Supplement";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/supplement/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCM_Supplement";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UCM_SupplementPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.UCM_SupplementPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.dummyImpl <em>dummy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.dummyImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.UCM_SupplementPackageImpl#getdummy()
	 * @generated
	 */
	int DUMMY = 0;

	/**
	 * The number of structural features of the '<em>dummy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>dummy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DUMMY_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.dummy <em>dummy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>dummy</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.dummy
	 * @generated
	 */
	EClass getdummy();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UCM_SupplementFactory getUCM_SupplementFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.dummyImpl <em>dummy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.dummyImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.UCM_SupplementPackageImpl#getdummy()
		 * @generated
		 */
		EClass DUMMY = eINSTANCE.getdummy();

	}

} //UCM_SupplementPackage
