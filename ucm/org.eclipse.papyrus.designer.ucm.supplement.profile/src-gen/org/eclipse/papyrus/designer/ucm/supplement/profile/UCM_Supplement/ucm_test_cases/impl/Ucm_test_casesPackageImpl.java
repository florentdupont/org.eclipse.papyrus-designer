/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_test_cases.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.UCM_SupplementPackage;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.UCM_SupplementPackageImpl;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentPackage;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.Ucm_environmentPackage;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.Ucm_environmentPackageImpl;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.Ucm_extra_typesPackage;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.Ucm_extra_typesPackageImpl;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_test_cases.TestCaseGroup;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_test_cases.Ucm_test_casesFactory;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_test_cases.Ucm_test_casesPackage;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.UCMTypesPackage;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_test_casesPackageImpl extends EPackageImpl implements Ucm_test_casesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseGroupEClass = null;

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
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_test_cases.Ucm_test_casesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ucm_test_casesPackageImpl() {
		super(eNS_URI, Ucm_test_casesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Ucm_test_casesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ucm_test_casesPackage init() {
		if (isInited) return (Ucm_test_casesPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_test_casesPackage.eNS_URI);

		// Obtain or create and register package
		Ucm_test_casesPackageImpl theUcm_test_casesPackage = (Ucm_test_casesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ucm_test_casesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ucm_test_casesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UCMProfilePackage.eINSTANCE.eClass();
		UCMTypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCM_SupplementPackageImpl theUCM_SupplementPackage = (UCM_SupplementPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCM_SupplementPackage.eNS_URI) instanceof UCM_SupplementPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCM_SupplementPackage.eNS_URI) : UCM_SupplementPackage.eINSTANCE);
		Ucm_deploymentPackageImpl theUcm_deploymentPackage = (Ucm_deploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_deploymentPackage.eNS_URI) instanceof Ucm_deploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_deploymentPackage.eNS_URI) : Ucm_deploymentPackage.eINSTANCE);
		Ucm_detailed_componentsPackageImpl theUcm_detailed_componentsPackage = (Ucm_detailed_componentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_detailed_componentsPackage.eNS_URI) instanceof Ucm_detailed_componentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_detailed_componentsPackage.eNS_URI) : Ucm_detailed_componentsPackage.eINSTANCE);
		Ucm_environmentPackageImpl theUcm_environmentPackage = (Ucm_environmentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_environmentPackage.eNS_URI) instanceof Ucm_environmentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_environmentPackage.eNS_URI) : Ucm_environmentPackage.eINSTANCE);
		Ucm_extra_typesPackageImpl theUcm_extra_typesPackage = (Ucm_extra_typesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_extra_typesPackage.eNS_URI) instanceof Ucm_extra_typesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_extra_typesPackage.eNS_URI) : Ucm_extra_typesPackage.eINSTANCE);

		// Create package meta-data objects
		theUcm_test_casesPackage.createPackageContents();
		theUCM_SupplementPackage.createPackageContents();
		theUcm_deploymentPackage.createPackageContents();
		theUcm_detailed_componentsPackage.createPackageContents();
		theUcm_environmentPackage.createPackageContents();
		theUcm_extra_typesPackage.createPackageContents();

		// Initialize created meta-data
		theUcm_test_casesPackage.initializePackageContents();
		theUCM_SupplementPackage.initializePackageContents();
		theUcm_deploymentPackage.initializePackageContents();
		theUcm_detailed_componentsPackage.initializePackageContents();
		theUcm_environmentPackage.initializePackageContents();
		theUcm_extra_typesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUcm_test_casesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ucm_test_casesPackage.eNS_URI, theUcm_test_casesPackage);
		return theUcm_test_casesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCaseGroup() {
		return testCaseGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCaseGroup_Base_Package() {
		return (EReference)testCaseGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_test_casesFactory getUcm_test_casesFactory() {
		return (Ucm_test_casesFactory)getEFactoryInstance();
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
		testCaseGroupEClass = createEClass(TEST_CASE_GROUP);
		createEReference(testCaseGroupEClass, TEST_CASE_GROUP__BASE_PACKAGE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(testCaseGroupEClass, TestCaseGroup.class, "TestCaseGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCaseGroup_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, TestCaseGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
	}

} //Ucm_test_casesPackageImpl
