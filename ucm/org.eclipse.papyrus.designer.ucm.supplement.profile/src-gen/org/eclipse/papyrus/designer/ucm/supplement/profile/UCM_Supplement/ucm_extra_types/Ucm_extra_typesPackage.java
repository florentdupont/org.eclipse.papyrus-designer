/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage;

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
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.Ucm_extra_typesFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_extra_typesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_extra_types";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/extratypes/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCM_Supplement.ucm_extra_types";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_extra_typesPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.Ucm_extra_typesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.DataPointerImpl <em>Data Pointer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.DataPointerImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.Ucm_extra_typesPackageImpl#getDataPointer()
	 * @generated
	 */
	int DATA_POINTER = 0;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_POINTER__BASE_DATA_TYPE = TypesPackage.IDATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_POINTER__TYPE = TypesPackage.IDATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Pointer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_POINTER_FEATURE_COUNT = TypesPackage.IDATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Data Pointer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_POINTER_OPERATION_COUNT = TypesPackage.IDATA_TYPE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.DataPointer <em>Data Pointer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Pointer</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.DataPointer
	 * @generated
	 */
	EClass getDataPointer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.DataPointer#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.DataPointer#getType()
	 * @see #getDataPointer()
	 * @generated
	 */
	EReference getDataPointer_Type();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_extra_typesFactory getUcm_extra_typesFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.DataPointerImpl <em>Data Pointer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.DataPointerImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.Ucm_extra_typesPackageImpl#getDataPointer()
		 * @generated
		 */
		EClass DATA_POINTER = eINSTANCE.getDataPointer();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_POINTER__TYPE = eINSTANCE.getDataPointer_Type();

	}

} //Ucm_extra_typesPackage
