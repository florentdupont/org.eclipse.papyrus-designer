/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.impl.UCMProfilePackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.Ucm_commonsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl.Ucm_commonsPackageImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AnnotationDefinition;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameter;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameterValue;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Constant;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameter;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsFactory;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage;

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
public class Ucm_contractsPackageImpl extends EPackageImpl implements Ucm_contractsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iConfiguredEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iConfigurationParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contractModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iConfigurationParameterValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationParameterValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iConfigurableEClass = null;

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
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ucm_contractsPackageImpl() {
		super(eNS_URI, Ucm_contractsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Ucm_contractsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ucm_contractsPackage init() {
		if (isInited) return (Ucm_contractsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI);

		// Obtain or create and register package
		Ucm_contractsPackageImpl theUcm_contractsPackage = (Ucm_contractsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ucm_contractsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ucm_contractsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCMProfilePackageImpl theUCMProfilePackage = (UCMProfilePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) instanceof UCMProfilePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) : UCMProfilePackage.eINSTANCE);
		Ucm_componentsPackageImpl theUcm_componentsPackage = (Ucm_componentsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) instanceof Ucm_componentsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI) : Ucm_componentsPackage.eINSTANCE);
		Ucm_interactionsPackageImpl theUcm_interactionsPackage = (Ucm_interactionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) instanceof Ucm_interactionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) : Ucm_interactionsPackage.eINSTANCE);
		Ucm_technicalpoliciesPackageImpl theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) instanceof Ucm_technicalpoliciesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) : Ucm_technicalpoliciesPackage.eINSTANCE);
		Ucm_commonsPackageImpl theUcm_commonsPackage = (Ucm_commonsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) instanceof Ucm_commonsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) : Ucm_commonsPackage.eINSTANCE);

		// Create package meta-data objects
		theUcm_contractsPackage.createPackageContents();
		theUCMProfilePackage.createPackageContents();
		theUcm_componentsPackage.createPackageContents();
		theUcm_interactionsPackage.createPackageContents();
		theUcm_technicalpoliciesPackage.createPackageContents();
		theUcm_commonsPackage.createPackageContents();

		// Initialize created meta-data
		theUcm_contractsPackage.initializePackageContents();
		theUCMProfilePackage.initializePackageContents();
		theUcm_componentsPackage.initializePackageContents();
		theUcm_interactionsPackage.initializePackageContents();
		theUcm_technicalpoliciesPackage.initializePackageContents();
		theUcm_commonsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUcm_contractsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ucm_contractsPackage.eNS_URI, theUcm_contractsPackage);
		return theUcm_contractsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractDeclaration() {
		return abstractDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotation() {
		return annotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_Definition() {
		return (EReference)annotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_Base_Comment() {
		return (EReference)annotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIConfigured() {
		return iConfiguredEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIConfigured_Values() {
		return (EReference)iConfiguredEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationDefinition() {
		return annotationDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationDefinition_Base_Class() {
		return (EReference)annotationDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfigurationParameter() {
		return configurationParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIConfigurationParameter() {
		return iConfigurationParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIConfigurationParameter_Base_Property() {
		return (EReference)iConfigurationParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContractModule() {
		return contractModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContractModule_Base_Package() {
		return (EReference)contractModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIConfigurationParameterValue() {
		return iConfigurationParameterValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIConfigurationParameterValue_Base_Slot() {
		return (EReference)iConfigurationParameterValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfigurationParameterValue() {
		return configurationParameterValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractInterface() {
		return abstractInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractInterface_Base_Interface() {
		return (EReference)abstractInterfaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractDataType() {
		return abstractDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractDataType_Base_DataType() {
		return (EReference)abstractDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstant() {
		return constantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstant_Base_InstanceSpecification() {
		return (EReference)constantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIConfigurable() {
		return iConfigurableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_contractsFactory getUcm_contractsFactory() {
		return (Ucm_contractsFactory)getEFactoryInstance();
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
		abstractDeclarationEClass = createEClass(ABSTRACT_DECLARATION);

		annotationEClass = createEClass(ANNOTATION);
		createEReference(annotationEClass, ANNOTATION__DEFINITION);
		createEReference(annotationEClass, ANNOTATION__BASE_COMMENT);

		iConfiguredEClass = createEClass(ICONFIGURED);
		createEReference(iConfiguredEClass, ICONFIGURED__VALUES);

		annotationDefinitionEClass = createEClass(ANNOTATION_DEFINITION);
		createEReference(annotationDefinitionEClass, ANNOTATION_DEFINITION__BASE_CLASS);

		configurationParameterEClass = createEClass(CONFIGURATION_PARAMETER);

		iConfigurationParameterEClass = createEClass(ICONFIGURATION_PARAMETER);
		createEReference(iConfigurationParameterEClass, ICONFIGURATION_PARAMETER__BASE_PROPERTY);

		contractModuleEClass = createEClass(CONTRACT_MODULE);
		createEReference(contractModuleEClass, CONTRACT_MODULE__BASE_PACKAGE);

		iConfigurationParameterValueEClass = createEClass(ICONFIGURATION_PARAMETER_VALUE);
		createEReference(iConfigurationParameterValueEClass, ICONFIGURATION_PARAMETER_VALUE__BASE_SLOT);

		configurationParameterValueEClass = createEClass(CONFIGURATION_PARAMETER_VALUE);

		abstractInterfaceEClass = createEClass(ABSTRACT_INTERFACE);
		createEReference(abstractInterfaceEClass, ABSTRACT_INTERFACE__BASE_INTERFACE);

		abstractDataTypeEClass = createEClass(ABSTRACT_DATA_TYPE);
		createEReference(abstractDataTypeEClass, ABSTRACT_DATA_TYPE__BASE_DATA_TYPE);

		constantEClass = createEClass(CONSTANT);
		createEReference(constantEClass, CONSTANT__BASE_INSTANCE_SPECIFICATION);

		iConfigurableEClass = createEClass(ICONFIGURABLE);
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
		annotationEClass.getESuperTypes().add(this.getIConfigured());
		annotationDefinitionEClass.getESuperTypes().add(this.getIConfigurable());
		configurationParameterEClass.getESuperTypes().add(this.getIConfigurationParameter());
		configurationParameterValueEClass.getESuperTypes().add(this.getIConfigurationParameterValue());
		abstractInterfaceEClass.getESuperTypes().add(this.getAbstractDeclaration());
		abstractDataTypeEClass.getESuperTypes().add(this.getAbstractDeclaration());

		// Initialize classes, features, and operations; add parameters
		initEClass(abstractDeclarationEClass, AbstractDeclaration.class, "AbstractDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnnotation_Definition(), this.getAnnotationDefinition(), null, "definition", null, 1, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnnotation_Base_Comment(), theUMLPackage.getComment(), null, "base_Comment", null, 1, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iConfiguredEClass, IConfigured.class, "IConfigured", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIConfigured_Values(), theUMLPackage.getInstanceSpecification(), null, "values", null, 0, 1, IConfigured.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(annotationDefinitionEClass, AnnotationDefinition.class, "AnnotationDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnnotationDefinition_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, AnnotationDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(configurationParameterEClass, ConfigurationParameter.class, "ConfigurationParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iConfigurationParameterEClass, IConfigurationParameter.class, "IConfigurationParameter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIConfigurationParameter_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, IConfigurationParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(contractModuleEClass, ContractModule.class, "ContractModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContractModule_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, ContractModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iConfigurationParameterValueEClass, IConfigurationParameterValue.class, "IConfigurationParameterValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIConfigurationParameterValue_Base_Slot(), theUMLPackage.getSlot(), null, "base_Slot", null, 1, 1, IConfigurationParameterValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(configurationParameterValueEClass, ConfigurationParameterValue.class, "ConfigurationParameterValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractInterfaceEClass, AbstractInterface.class, "AbstractInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractInterface_Base_Interface(), theUMLPackage.getInterface(), null, "base_Interface", null, 1, 1, AbstractInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(abstractDataTypeEClass, AbstractDataType.class, "AbstractDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractDataType_Base_DataType(), theUMLPackage.getDataType(), null, "base_DataType", null, 1, 1, AbstractDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(constantEClass, Constant.class, "Constant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstant_Base_InstanceSpecification(), theUMLPackage.getInstanceSpecification(), null, "base_InstanceSpecification", null, 1, 1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iConfigurableEClass, IConfigurable.class, "IConfigurable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //Ucm_contractsPackageImpl
