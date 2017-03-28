/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.UCMProfilePackage;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.UCM_SupplementPackage;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl.UCM_SupplementPackageImpl;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentPackage;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentPackageImpl;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsFactory;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.Ucm_environmentPackage;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl.Ucm_environmentPackageImpl;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.Ucm_extra_typesPackage;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.impl.Ucm_extra_typesPackageImpl;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_test_cases.Ucm_test_casesPackage;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_test_cases.impl.Ucm_test_casesPackageImpl;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.UCMTypesPackage;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_detailed_componentsPackageImpl extends EPackageImpl implements Ucm_detailed_componentsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass detailedComponentImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPortElementImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentPortImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass technicalPortImplementationEClass = null;

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
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ucm_detailed_componentsPackageImpl() {
		super(eNS_URI, Ucm_detailed_componentsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Ucm_detailed_componentsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ucm_detailed_componentsPackage init() {
		if (isInited) return (Ucm_detailed_componentsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_detailed_componentsPackage.eNS_URI);

		// Obtain or create and register package
		Ucm_detailed_componentsPackageImpl theUcm_detailed_componentsPackage = (Ucm_detailed_componentsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ucm_detailed_componentsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ucm_detailed_componentsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UCMProfilePackage.eINSTANCE.eClass();
		UCMTypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCM_SupplementPackageImpl theUCM_SupplementPackage = (UCM_SupplementPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCM_SupplementPackage.eNS_URI) instanceof UCM_SupplementPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCM_SupplementPackage.eNS_URI) : UCM_SupplementPackage.eINSTANCE);
		Ucm_deploymentPackageImpl theUcm_deploymentPackage = (Ucm_deploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_deploymentPackage.eNS_URI) instanceof Ucm_deploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_deploymentPackage.eNS_URI) : Ucm_deploymentPackage.eINSTANCE);
		Ucm_environmentPackageImpl theUcm_environmentPackage = (Ucm_environmentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_environmentPackage.eNS_URI) instanceof Ucm_environmentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_environmentPackage.eNS_URI) : Ucm_environmentPackage.eINSTANCE);
		Ucm_test_casesPackageImpl theUcm_test_casesPackage = (Ucm_test_casesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_test_casesPackage.eNS_URI) instanceof Ucm_test_casesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_test_casesPackage.eNS_URI) : Ucm_test_casesPackage.eINSTANCE);
		Ucm_extra_typesPackageImpl theUcm_extra_typesPackage = (Ucm_extra_typesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_extra_typesPackage.eNS_URI) instanceof Ucm_extra_typesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_extra_typesPackage.eNS_URI) : Ucm_extra_typesPackage.eINSTANCE);

		// Create package meta-data objects
		theUcm_detailed_componentsPackage.createPackageContents();
		theUCM_SupplementPackage.createPackageContents();
		theUcm_deploymentPackage.createPackageContents();
		theUcm_environmentPackage.createPackageContents();
		theUcm_test_casesPackage.createPackageContents();
		theUcm_extra_typesPackage.createPackageContents();

		// Initialize created meta-data
		theUcm_detailed_componentsPackage.initializePackageContents();
		theUCM_SupplementPackage.initializePackageContents();
		theUcm_deploymentPackage.initializePackageContents();
		theUcm_environmentPackage.initializePackageContents();
		theUcm_test_casesPackage.initializePackageContents();
		theUcm_extra_typesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUcm_detailed_componentsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ucm_detailed_componentsPackage.eNS_URI, theUcm_detailed_componentsPackage);
		return theUcm_detailed_componentsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDetailedComponentImplementation() {
		return detailedComponentImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDetailedComponentImplementation_PortImplementation() {
		return (EReference)detailedComponentImplementationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPortElementImplementation() {
		return iPortElementImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPortElementImplementation_PortElement() {
		return (EReference)iPortElementImplementationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPortElementImplementation_Base_Class() {
		return (EReference)iPortElementImplementationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentPortImplementation() {
		return componentPortImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentPortImplementation_Port() {
		return (EReference)componentPortImplementationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTechnicalPortImplementation() {
		return technicalPortImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTechnicalPortImplementation_Policy() {
		return (EReference)technicalPortImplementationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_detailed_componentsFactory getUcm_detailed_componentsFactory() {
		return (Ucm_detailed_componentsFactory)getEFactoryInstance();
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
		detailedComponentImplementationEClass = createEClass(DETAILED_COMPONENT_IMPLEMENTATION);
		createEReference(detailedComponentImplementationEClass, DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION);

		iPortElementImplementationEClass = createEClass(IPORT_ELEMENT_IMPLEMENTATION);
		createEReference(iPortElementImplementationEClass, IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT);
		createEReference(iPortElementImplementationEClass, IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS);

		componentPortImplementationEClass = createEClass(COMPONENT_PORT_IMPLEMENTATION);
		createEReference(componentPortImplementationEClass, COMPONENT_PORT_IMPLEMENTATION__PORT);

		technicalPortImplementationEClass = createEClass(TECHNICAL_PORT_IMPLEMENTATION);
		createEReference(technicalPortImplementationEClass, TECHNICAL_PORT_IMPLEMENTATION__POLICY);
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
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		detailedComponentImplementationEClass.getESuperTypes().add(theUcm_componentsPackage.getAtomicComponentImplementation());
		componentPortImplementationEClass.getESuperTypes().add(this.getIPortElementImplementation());
		technicalPortImplementationEClass.getESuperTypes().add(this.getIPortElementImplementation());

		// Initialize classes, features, and operations; add parameters
		initEClass(detailedComponentImplementationEClass, DetailedComponentImplementation.class, "DetailedComponentImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDetailedComponentImplementation_PortImplementation(), this.getIPortElementImplementation(), null, "portImplementation", null, 0, -1, DetailedComponentImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iPortElementImplementationEClass, IPortElementImplementation.class, "IPortElementImplementation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIPortElementImplementation_PortElement(), theUcm_interactionsPackage.getPortElement(), null, "portElement", null, 1, 1, IPortElementImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIPortElementImplementation_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, IPortElementImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentPortImplementationEClass, ComponentPortImplementation.class, "ComponentPortImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentPortImplementation_Port(), theUMLPackage.getPort(), null, "port", null, 1, 1, ComponentPortImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(technicalPortImplementationEClass, TechnicalPortImplementation.class, "TechnicalPortImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTechnicalPortImplementation_Policy(), theUcm_componentsPackage.getTechnicalPolicy(), null, "policy", null, 1, 1, TechnicalPortImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
	}

} //Ucm_detailed_componentsPackageImpl
