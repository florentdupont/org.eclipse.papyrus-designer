/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfileFactory;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.dummy;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl;

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
public class UCMProfilePackageImpl extends EPackageImpl implements UCMProfilePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dummyEClass = null;

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
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UCMProfilePackageImpl() {
		super(eNS_URI, UCMProfileFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UCMProfilePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UCMProfilePackage init() {
		if (isInited) return (UCMProfilePackage)EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI);

		// Obtain or create and register package
		UCMProfilePackageImpl theUCMProfilePackage = (UCMProfilePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UCMProfilePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UCMProfilePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Ucm_componentsPackageImpl theUcm_componentsPackage = (Ucm_componentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) instanceof Ucm_componentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) : Ucm_componentsPackage.eINSTANCE);
		Ucm_interactionsPackageImpl theUcm_interactionsPackage = (Ucm_interactionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) instanceof Ucm_interactionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) : Ucm_interactionsPackage.eINSTANCE);
		Ucm_contractsPackageImpl theUcm_contractsPackage = (Ucm_contractsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) instanceof Ucm_contractsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) : Ucm_contractsPackage.eINSTANCE);
		Ucm_technicalpoliciesPackageImpl theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) instanceof Ucm_technicalpoliciesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) : Ucm_technicalpoliciesPackage.eINSTANCE);
		Ucm_commonsPackageImpl theUcm_commonsPackage = (Ucm_commonsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) instanceof Ucm_commonsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) : Ucm_commonsPackage.eINSTANCE);

		// Create package meta-data objects
		theUCMProfilePackage.createPackageContents();
		theUcm_componentsPackage.createPackageContents();
		theUcm_interactionsPackage.createPackageContents();
		theUcm_contractsPackage.createPackageContents();
		theUcm_technicalpoliciesPackage.createPackageContents();
		theUcm_commonsPackage.createPackageContents();

		// Initialize created meta-data
		theUCMProfilePackage.initializePackageContents();
		theUcm_componentsPackage.initializePackageContents();
		theUcm_interactionsPackage.initializePackageContents();
		theUcm_contractsPackage.initializePackageContents();
		theUcm_technicalpoliciesPackage.initializePackageContents();
		theUcm_commonsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUCMProfilePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UCMProfilePackage.eNS_URI, theUCMProfilePackage);
		return theUCMProfilePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getdummy() {
		return dummyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UCMProfileFactory getUCMProfileFactory() {
		return (UCMProfileFactory)getEFactoryInstance();
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
		dummyEClass = createEClass(DUMMY);
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
		Ucm_componentsPackage theUcm_componentsPackage = (Ucm_componentsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI);
		Ucm_interactionsPackage theUcm_interactionsPackage = (Ucm_interactionsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI);
		Ucm_contractsPackage theUcm_contractsPackage = (Ucm_contractsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI);
		Ucm_technicalpoliciesPackage theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI);
		Ucm_commonsPackage theUcm_commonsPackage = (Ucm_commonsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theUcm_componentsPackage);
		getESubpackages().add(theUcm_interactionsPackage);
		getESubpackages().add(theUcm_contractsPackage);
		getESubpackages().add(theUcm_technicalpoliciesPackage);
		getESubpackages().add(theUcm_commonsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(dummyEClass, dummy.class, "dummy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //UCMProfilePackageImpl
