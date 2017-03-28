/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl;

import org.eclipse.emf.ecore.EClass;
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

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.CompositeComponentImplementation;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Connection;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ConnectionLink;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IBinding;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponentImplementation;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IPortSpec;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.InteractionItemBinding;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortRoleSpec;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsFactory;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.util.Ucm_componentsValidator;

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
public class Ucm_componentsPackageImpl extends EPackageImpl implements Ucm_componentsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionItemBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portRoleSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iPortSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portTypeSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass technicalPolicyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomicComponentImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iComponentImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeComponentImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTypeBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentModuleEClass = null;

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
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Ucm_componentsPackageImpl() {
		super(eNS_URI, Ucm_componentsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link Ucm_componentsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Ucm_componentsPackage init() {
		if (isInited) return (Ucm_componentsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_componentsPackage.eNS_URI);

		// Obtain or create and register package
		Ucm_componentsPackageImpl theUcm_componentsPackage = (Ucm_componentsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Ucm_componentsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Ucm_componentsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCMProfilePackageImpl theUCMProfilePackage = (UCMProfilePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) instanceof UCMProfilePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCMProfilePackage.eNS_URI) : UCMProfilePackage.eINSTANCE);
		Ucm_interactionsPackageImpl theUcm_interactionsPackage = (Ucm_interactionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) instanceof Ucm_interactionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI) : Ucm_interactionsPackage.eINSTANCE);
		Ucm_contractsPackageImpl theUcm_contractsPackage = (Ucm_contractsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) instanceof Ucm_contractsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI) : Ucm_contractsPackage.eINSTANCE);
		Ucm_technicalpoliciesPackageImpl theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) instanceof Ucm_technicalpoliciesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI) : Ucm_technicalpoliciesPackage.eINSTANCE);
		Ucm_commonsPackageImpl theUcm_commonsPackage = (Ucm_commonsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) instanceof Ucm_commonsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Ucm_commonsPackage.eNS_URI) : Ucm_commonsPackage.eINSTANCE);

		// Create package meta-data objects
		theUcm_componentsPackage.createPackageContents();
		theUCMProfilePackage.createPackageContents();
		theUcm_interactionsPackage.createPackageContents();
		theUcm_contractsPackage.createPackageContents();
		theUcm_technicalpoliciesPackage.createPackageContents();
		theUcm_commonsPackage.createPackageContents();

		// Initialize created meta-data
		theUcm_componentsPackage.initializePackageContents();
		theUCMProfilePackage.initializePackageContents();
		theUcm_interactionsPackage.initializePackageContents();
		theUcm_contractsPackage.initializePackageContents();
		theUcm_technicalpoliciesPackage.initializePackageContents();
		theUcm_commonsPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theUcm_componentsPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return Ucm_componentsValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theUcm_componentsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Ucm_componentsPackage.eNS_URI, theUcm_componentsPackage);
		return theUcm_componentsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteractionItemBinding() {
		return interactionItemBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteractionItemBinding_Item() {
		return (EReference)interactionItemBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIBinding() {
		return iBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIBinding_Base_Property() {
		return (EReference)iBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortRoleSpec() {
		return portRoleSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortRoleSpec_Role() {
		return (EReference)portRoleSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIPortSpec() {
		return iPortSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIPortSpec_Base_Class() {
		return (EReference)iPortSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortTypeSpec() {
		return portTypeSpecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortTypeSpec_Type() {
		return (EReference)portTypeSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_Base_Property() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectionLink() {
		return connectionLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectionLink_Base_Connector() {
		return (EReference)connectionLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTechnicalPolicy() {
		return technicalPolicyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTechnicalPolicy_Definition() {
		return (EReference)technicalPolicyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTechnicalPolicy_Base_Class() {
		return (EReference)technicalPolicyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTechnicalPolicy_ManagedPort() {
		return (EReference)technicalPolicyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTechnicalPolicy_ManagePolicy() {
		return (EReference)technicalPolicyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtomicComponentImplementation() {
		return atomicComponentImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAtomicComponentImplementation_Language() {
		return (EReference)atomicComponentImplementationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIComponentImplementation() {
		return iComponentImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIComponent() {
		return iComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIComponent_Base_Class() {
		return (EReference)iComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentType() {
		return componentTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeComponentImplementation() {
		return compositeComponentImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssemblyPart() {
		return assemblyPartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyPart_Base_Property() {
		return (EReference)assemblyPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getAssemblyPart__Constraint1__DiagnosticChain_Map() {
		return assemblyPartEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTypeBinding() {
		return abstractTypeBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeBinding_AbstractType() {
		return (EReference)abstractTypeBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentModule() {
		return componentModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentModule_Base_Package() {
		return (EReference)componentModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_componentsFactory getUcm_componentsFactory() {
		return (Ucm_componentsFactory)getEFactoryInstance();
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
		interactionItemBindingEClass = createEClass(INTERACTION_ITEM_BINDING);
		createEReference(interactionItemBindingEClass, INTERACTION_ITEM_BINDING__ITEM);

		iBindingEClass = createEClass(IBINDING);
		createEReference(iBindingEClass, IBINDING__BASE_PROPERTY);

		portRoleSpecEClass = createEClass(PORT_ROLE_SPEC);
		createEReference(portRoleSpecEClass, PORT_ROLE_SPEC__ROLE);

		iPortSpecEClass = createEClass(IPORT_SPEC);
		createEReference(iPortSpecEClass, IPORT_SPEC__BASE_CLASS);

		portTypeSpecEClass = createEClass(PORT_TYPE_SPEC);
		createEReference(portTypeSpecEClass, PORT_TYPE_SPEC__TYPE);

		connectionEClass = createEClass(CONNECTION);
		createEReference(connectionEClass, CONNECTION__BASE_PROPERTY);

		connectionLinkEClass = createEClass(CONNECTION_LINK);
		createEReference(connectionLinkEClass, CONNECTION_LINK__BASE_CONNECTOR);

		technicalPolicyEClass = createEClass(TECHNICAL_POLICY);
		createEReference(technicalPolicyEClass, TECHNICAL_POLICY__DEFINITION);
		createEReference(technicalPolicyEClass, TECHNICAL_POLICY__BASE_CLASS);
		createEReference(technicalPolicyEClass, TECHNICAL_POLICY__MANAGED_PORT);
		createEReference(technicalPolicyEClass, TECHNICAL_POLICY__MANAGE_POLICY);

		atomicComponentImplementationEClass = createEClass(ATOMIC_COMPONENT_IMPLEMENTATION);
		createEReference(atomicComponentImplementationEClass, ATOMIC_COMPONENT_IMPLEMENTATION__LANGUAGE);

		iComponentImplementationEClass = createEClass(ICOMPONENT_IMPLEMENTATION);

		iComponentEClass = createEClass(ICOMPONENT);
		createEReference(iComponentEClass, ICOMPONENT__BASE_CLASS);

		componentTypeEClass = createEClass(COMPONENT_TYPE);

		compositeComponentImplementationEClass = createEClass(COMPOSITE_COMPONENT_IMPLEMENTATION);

		assemblyPartEClass = createEClass(ASSEMBLY_PART);
		createEReference(assemblyPartEClass, ASSEMBLY_PART__BASE_PROPERTY);
		createEOperation(assemblyPartEClass, ASSEMBLY_PART___CONSTRAINT1__DIAGNOSTICCHAIN_MAP);

		abstractTypeBindingEClass = createEClass(ABSTRACT_TYPE_BINDING);
		createEReference(abstractTypeBindingEClass, ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE);

		componentModuleEClass = createEClass(COMPONENT_MODULE);
		createEReference(componentModuleEClass, COMPONENT_MODULE__BASE_PACKAGE);
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
		Ucm_interactionsPackage theUcm_interactionsPackage = (Ucm_interactionsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_interactionsPackage.eNS_URI);
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);
		Ucm_contractsPackage theUcm_contractsPackage = (Ucm_contractsPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_contractsPackage.eNS_URI);
		Ucm_technicalpoliciesPackage theUcm_technicalpoliciesPackage = (Ucm_technicalpoliciesPackage)EPackage.Registry.INSTANCE.getEPackage(Ucm_technicalpoliciesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		interactionItemBindingEClass.getESuperTypes().add(this.getIBinding());
		portRoleSpecEClass.getESuperTypes().add(this.getIPortSpec());
		portTypeSpecEClass.getESuperTypes().add(this.getIPortSpec());
		connectionEClass.getESuperTypes().add(theUcm_contractsPackage.getIConfigured());
		technicalPolicyEClass.getESuperTypes().add(theUcm_contractsPackage.getIConfigured());
		atomicComponentImplementationEClass.getESuperTypes().add(this.getIComponentImplementation());
		iComponentImplementationEClass.getESuperTypes().add(this.getIComponent());
		componentTypeEClass.getESuperTypes().add(this.getIComponent());
		compositeComponentImplementationEClass.getESuperTypes().add(this.getIComponentImplementation());
		abstractTypeBindingEClass.getESuperTypes().add(this.getIBinding());

		// Initialize classes, features, and operations; add parameters
		initEClass(interactionItemBindingEClass, InteractionItemBinding.class, "InteractionItemBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInteractionItemBinding_Item(), theUcm_interactionsPackage.getInteractionItem(), null, "item", null, 1, 1, InteractionItemBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iBindingEClass, IBinding.class, "IBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIBinding_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, IBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(portRoleSpecEClass, PortRoleSpec.class, "PortRoleSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortRoleSpec_Role(), theUcm_interactionsPackage.getInteractionRole(), null, "role", null, 1, 1, PortRoleSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iPortSpecEClass, IPortSpec.class, "IPortSpec", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIPortSpec_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, IPortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(portTypeSpecEClass, PortTypeSpec.class, "PortTypeSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortTypeSpec_Type(), theUcm_interactionsPackage.getIPortType(), null, "type", null, 1, 1, PortTypeSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnection_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectionLinkEClass, ConnectionLink.class, "ConnectionLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnectionLink_Base_Connector(), theUMLPackage.getConnector(), null, "base_Connector", null, 1, 1, ConnectionLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(technicalPolicyEClass, TechnicalPolicy.class, "TechnicalPolicy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTechnicalPolicy_Definition(), theUcm_technicalpoliciesPackage.getTechnicalPolicyDefinition(), null, "definition", null, 1, 1, TechnicalPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTechnicalPolicy_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, TechnicalPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTechnicalPolicy_ManagedPort(), theUMLPackage.getPort(), null, "managedPort", null, 0, -1, TechnicalPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTechnicalPolicy_ManagePolicy(), this.getTechnicalPolicy(), null, "managePolicy", null, 0, -1, TechnicalPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(atomicComponentImplementationEClass, AtomicComponentImplementation.class, "AtomicComponentImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAtomicComponentImplementation_Language(), theUcm_technicalpoliciesPackage.getLanguage(), null, "language", null, 1, 1, AtomicComponentImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iComponentImplementationEClass, IComponentImplementation.class, "IComponentImplementation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iComponentEClass, IComponent.class, "IComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIComponent_Base_Class(), theUMLPackage.getClass_(), null, "base_Class", null, 1, 1, IComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentTypeEClass, ComponentType.class, "ComponentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeComponentImplementationEClass, CompositeComponentImplementation.class, "CompositeComponentImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(assemblyPartEClass, AssemblyPart.class, "AssemblyPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssemblyPart_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, AssemblyPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		EOperation op = initEOperation(getAssemblyPart__Constraint1__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "Constraint1", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(abstractTypeBindingEClass, AbstractTypeBinding.class, "AbstractTypeBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractTypeBinding_AbstractType(), theUcm_contractsPackage.getAbstractDeclaration(), null, "abstractType", null, 1, 1, AbstractTypeBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentModuleEClass, ComponentModule.class, "ComponentModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentModule_Base_Package(), theUMLPackage.getPackage(), null, "base_Package", null, 1, 1, ComponentModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
	}

} //Ucm_componentsPackageImpl
