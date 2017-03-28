/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.impl.UCMProfilePackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.impl.Ucm_interactionsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.NonfunctionalAspectModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspectConstraint;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesFactory;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.util.Ucm_technicalpoliciesValidator;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_technicalpoliciesPackageImpl extends EPackageImpl implements Ucm_technicalpoliciesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass technicalAspectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass programmingLanguagesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass languageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nonfunctionalAspectModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass technicalPolicyDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum technicalAspectConstraintEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum technicalPolicyApplicabilityEEnum = null;

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
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ucm_technicalpoliciesPackageImpl() {
		super(eNS_URI, Ucm_technicalpoliciesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Ucm_technicalpoliciesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ucm_technicalpoliciesPackage init() {
		if (isInited) return (Ucm_technicalpoliciesPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI);

		// Obtain or create and register package
		Ucm_technicalpoliciesPackageImpl theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ucm_technicalpoliciesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ucm_technicalpoliciesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCMProfilePackageImpl theUCMProfilePackage = (UCMProfilePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) instanceof UCMProfilePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) : UCMProfilePackage.eINSTANCE);
		Ucm_componentsPackageImpl theUcm_componentsPackage = (Ucm_componentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) instanceof Ucm_componentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) : Ucm_componentsPackage.eINSTANCE);
		Ucm_interactionsPackageImpl theUcm_interactionsPackage = (Ucm_interactionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) instanceof Ucm_interactionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) : Ucm_interactionsPackage.eINSTANCE);
		Ucm_contractsPackageImpl theUcm_contractsPackage = (Ucm_contractsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) instanceof Ucm_contractsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) : Ucm_contractsPackage.eINSTANCE);
		Ucm_commonsPackageImpl theUcm_commonsPackage = (Ucm_commonsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) instanceof Ucm_commonsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) : Ucm_commonsPackage.eINSTANCE);

		// Create package meta-data objects
		theUcm_technicalpoliciesPackage.createPackageContents();
		theUCMProfilePackage.createPackageContents();
		theUcm_componentsPackage.createPackageContents();
		theUcm_interactionsPackage.createPackageContents();
		theUcm_contractsPackage.createPackageContents();
		theUcm_commonsPackage.createPackageContents();

		// Initialize created meta-data
		theUcm_technicalpoliciesPackage.initializePackageContents();
		theUCMProfilePackage.initializePackageContents();
		theUcm_componentsPackage.initializePackageContents();
		theUcm_interactionsPackage.initializePackageContents();
		theUcm_contractsPackage.initializePackageContents();
		theUcm_commonsPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theUcm_technicalpoliciesPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return Ucm_technicalpoliciesValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theUcm_technicalpoliciesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ucm_technicalpoliciesPackage.eNS_URI, theUcm_technicalpoliciesPackage);
		return theUcm_technicalpoliciesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTechnicalAspect() {
		return technicalAspectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTechnicalAspect_Multiplicity() {
		return (EAttribute)technicalAspectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTechnicalAspect_Base_Class() {
		return (EReference)technicalAspectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProgrammingLanguages() {
		return programmingLanguagesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProgrammingLanguages_Base_Package() {
		return (EReference)programmingLanguagesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProgrammingLanguages_Languages() {
		return (EReference)programmingLanguagesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLanguage() {
		return languageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLanguage_Base_Class() {
		return (EReference)languageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getLanguage__Constraint1__DiagnosticChain_Map() {
		return languageEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNonfunctionalAspectModule() {
		return nonfunctionalAspectModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNonfunctionalAspectModule_Base_Package() {
		return (EReference)nonfunctionalAspectModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTechnicalPolicyDefinition() {
		return technicalPolicyDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTechnicalPolicyDefinition_Base_Class() {
		return (EReference)technicalPolicyDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTechnicalPolicyDefinition_Applicability() {
		return (EAttribute)technicalPolicyDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTechnicalAspectConstraint() {
		return technicalAspectConstraintEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTechnicalPolicyApplicability() {
		return technicalPolicyApplicabilityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_technicalpoliciesFactory getUcm_technicalpoliciesFactory() {
		return (Ucm_technicalpoliciesFactory)getEFactoryInstance();
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
		technicalAspectEClass = createEClass(TECHNICAL_ASPECT);
		createEAttribute(technicalAspectEClass, TECHNICAL_ASPECT__MULTIPLICITY);
		createEReference(technicalAspectEClass, TECHNICAL_ASPECT__BASE_CLASS);

		programmingLanguagesEClass = createEClass(PROGRAMMING_LANGUAGES);
		createEReference(programmingLanguagesEClass, PROGRAMMING_LANGUAGES__BASE_PACKAGE);
		createEReference(programmingLanguagesEClass, PROGRAMMING_LANGUAGES__LANGUAGES);

		languageEClass = createEClass(LANGUAGE);
		createEReference(languageEClass, LANGUAGE__BASE_CLASS);
		createEOperation(languageEClass, LANGUAGE___CONSTRAINT1__DIAGNOSTICCHAIN_MAP);

		nonfunctionalAspectModuleEClass = createEClass(NONFUNCTIONAL_ASPECT_MODULE);
		createEReference(nonfunctionalAspectModuleEClass, NONFUNCTIONAL_ASPECT_MODULE__BASE_PACKAGE);

		technicalPolicyDefinitionEClass = createEClass(TECHNICAL_POLICY_DEFINITION);
		createEReference(technicalPolicyDefinitionEClass, TECHNICAL_POLICY_DEFINITION__BASE_CLASS);
		createEAttribute(technicalPolicyDefinitionEClass, TECHNICAL_POLICY_DEFINITION__APPLICABILITY);

		// Create enums
		technicalAspectConstraintEEnum = createEEnum(TECHNICAL_ASPECT_CONSTRAINT);
		technicalPolicyApplicabilityEEnum = createEEnum(TECHNICAL_POLICY_APPLICABILITY);
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
		Ucm_contractsPackage theUcm_contractsPackage = (Ucm_contractsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		technicalPolicyDefinitionEClass.getESuperTypes().add(theUcm_contractsPackage.getIConfigurable());

		// Initialize classes, features, and operations; add parameters
		initEClass(technicalAspectEClass, TechnicalAspect.class, "TechnicalAspect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTechnicalAspect_Multiplicity(), this.getTechnicalAspectConstraint(), "multiplicity", null, 1, 1, TechnicalAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTechnicalAspect_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, TechnicalAspect.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(programmingLanguagesEClass, ProgrammingLanguages.class, "ProgrammingLanguages", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProgrammingLanguages_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, ProgrammingLanguages.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getProgrammingLanguages_Languages(), this.getLanguage(), null, "languages", null, 0, -1, ProgrammingLanguages.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

		initEClass(languageEClass, Language.class, "Language", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLanguage_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, Language.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		EOperation op = initEOperation(getLanguage__Constraint1__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "Constraint1", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(nonfunctionalAspectModuleEClass, NonfunctionalAspectModule.class, "NonfunctionalAspectModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNonfunctionalAspectModule_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, NonfunctionalAspectModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(technicalPolicyDefinitionEClass, TechnicalPolicyDefinition.class, "TechnicalPolicyDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTechnicalPolicyDefinition_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, TechnicalPolicyDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getTechnicalPolicyDefinition_Applicability(), this.getTechnicalPolicyApplicability(), "applicability", null, 1, 1, TechnicalPolicyDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(technicalAspectConstraintEEnum, TechnicalAspectConstraint.class, "TechnicalAspectConstraint");
		addEEnumLiteral(technicalAspectConstraintEEnum, TechnicalAspectConstraint.ANY_NUMBER);
		addEEnumLiteral(technicalAspectConstraintEEnum, TechnicalAspectConstraint.AT_MOST_ONE);
		addEEnumLiteral(technicalAspectConstraintEEnum, TechnicalAspectConstraint.EXACTLY_ONE);
		addEEnumLiteral(technicalAspectConstraintEEnum, TechnicalAspectConstraint.AT_LEAST_ONE);

		initEEnum(technicalPolicyApplicabilityEEnum, TechnicalPolicyApplicability.class, "TechnicalPolicyApplicability");
		addEEnumLiteral(technicalPolicyApplicabilityEEnum, TechnicalPolicyApplicability.ON_COMPONENT_ONLY);
		addEEnumLiteral(technicalPolicyApplicabilityEEnum, TechnicalPolicyApplicability.ON_SOME_PORTS);
		addEEnumLiteral(technicalPolicyApplicabilityEEnum, TechnicalPolicyApplicability.ON_ALL_PORTS);
	}

} //Ucm_technicalpoliciesPackageImpl
