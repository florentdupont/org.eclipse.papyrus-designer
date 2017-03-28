/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.impl.UCMProfilePackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.ApplicationModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.PlatformModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsFactory;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_commonsPackageImpl extends EPackageImpl implements Ucm_commonsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass platformModuleEClass = null;

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
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ucm_commonsPackageImpl() {
		super(eNS_URI, Ucm_commonsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Ucm_commonsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ucm_commonsPackage init() {
		if (isInited) return (Ucm_commonsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI);

		// Obtain or create and register package
		Ucm_commonsPackageImpl theUcm_commonsPackage = (Ucm_commonsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ucm_commonsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ucm_commonsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCMProfilePackageImpl theUCMProfilePackage = (UCMProfilePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) instanceof UCMProfilePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) : UCMProfilePackage.eINSTANCE);
		Ucm_componentsPackageImpl theUcm_componentsPackage = (Ucm_componentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) instanceof Ucm_componentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) : Ucm_componentsPackage.eINSTANCE);
		Ucm_interactionsPackageImpl theUcm_interactionsPackage = (Ucm_interactionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) instanceof Ucm_interactionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) : Ucm_interactionsPackage.eINSTANCE);
		Ucm_contractsPackageImpl theUcm_contractsPackage = (Ucm_contractsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) instanceof Ucm_contractsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) : Ucm_contractsPackage.eINSTANCE);
		Ucm_technicalpoliciesPackageImpl theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) instanceof Ucm_technicalpoliciesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) : Ucm_technicalpoliciesPackage.eINSTANCE);

		// Create package meta-data objects
		theUcm_commonsPackage.createPackageContents();
		theUCMProfilePackage.createPackageContents();
		theUcm_componentsPackage.createPackageContents();
		theUcm_interactionsPackage.createPackageContents();
		theUcm_contractsPackage.createPackageContents();
		theUcm_technicalpoliciesPackage.createPackageContents();

		// Initialize created meta-data
		theUcm_commonsPackage.initializePackageContents();
		theUCMProfilePackage.initializePackageContents();
		theUcm_componentsPackage.initializePackageContents();
		theUcm_interactionsPackage.initializePackageContents();
		theUcm_contractsPackage.initializePackageContents();
		theUcm_technicalpoliciesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUcm_commonsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ucm_commonsPackage.eNS_URI, theUcm_commonsPackage);
		return theUcm_commonsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplicationModule() {
		return applicationModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getApplicationModule_Base_Package() {
		return (EReference)applicationModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlatformModule() {
		return platformModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlatformModule_Base_Package() {
		return (EReference)platformModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_commonsFactory getUcm_commonsFactory() {
		return (Ucm_commonsFactory)getEFactoryInstance();
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
		applicationModuleEClass = createEClass(APPLICATION_MODULE);
		createEReference(applicationModuleEClass, APPLICATION_MODULE__BASE_PACKAGE);

		platformModuleEClass = createEClass(PLATFORM_MODULE);
		createEReference(platformModuleEClass, PLATFORM_MODULE__BASE_PACKAGE);
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
		initEClass(applicationModuleEClass, ApplicationModule.class, "ApplicationModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplicationModule_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, ApplicationModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(platformModuleEClass, PlatformModule.class, "PlatformModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPlatformModule_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, PlatformModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
	}

} //Ucm_commonsPackageImpl
