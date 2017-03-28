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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_componentsFactoryImpl extends EFactoryImpl implements Ucm_componentsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_componentsFactory init() {
		try {
			Ucm_componentsFactory theUcm_componentsFactory = (Ucm_componentsFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_componentsPackage.eNS_URI);
			if (theUcm_componentsFactory != null) {
				return theUcm_componentsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_componentsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_componentsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Ucm_componentsPackage.INTERACTION_ITEM_BINDING: return createInteractionItemBinding();
			case Ucm_componentsPackage.PORT_ROLE_SPEC: return createPortRoleSpec();
			case Ucm_componentsPackage.PORT_TYPE_SPEC: return createPortTypeSpec();
			case Ucm_componentsPackage.CONNECTION: return createConnection();
			case Ucm_componentsPackage.CONNECTION_LINK: return createConnectionLink();
			case Ucm_componentsPackage.TECHNICAL_POLICY: return createTechnicalPolicy();
			case Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION: return createAtomicComponentImplementation();
			case Ucm_componentsPackage.COMPONENT_TYPE: return createComponentType();
			case Ucm_componentsPackage.COMPOSITE_COMPONENT_IMPLEMENTATION: return createCompositeComponentImplementation();
			case Ucm_componentsPackage.ASSEMBLY_PART: return createAssemblyPart();
			case Ucm_componentsPackage.ABSTRACT_TYPE_BINDING: return createAbstractTypeBinding();
			case Ucm_componentsPackage.COMPONENT_MODULE: return createComponentModule();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InteractionItemBinding createInteractionItemBinding() {
		InteractionItemBindingImpl interactionItemBinding = new InteractionItemBindingImpl();
		return interactionItemBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortRoleSpec createPortRoleSpec() {
		PortRoleSpecImpl portRoleSpec = new PortRoleSpecImpl();
		return portRoleSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortTypeSpec createPortTypeSpec() {
		PortTypeSpecImpl portTypeSpec = new PortTypeSpecImpl();
		return portTypeSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionLink createConnectionLink() {
		ConnectionLinkImpl connectionLink = new ConnectionLinkImpl();
		return connectionLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalPolicy createTechnicalPolicy() {
		TechnicalPolicyImpl technicalPolicy = new TechnicalPolicyImpl();
		return technicalPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomicComponentImplementation createAtomicComponentImplementation() {
		AtomicComponentImplementationImpl atomicComponentImplementation = new AtomicComponentImplementationImpl();
		return atomicComponentImplementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType createComponentType() {
		ComponentTypeImpl componentType = new ComponentTypeImpl();
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeComponentImplementation createCompositeComponentImplementation() {
		CompositeComponentImplementationImpl compositeComponentImplementation = new CompositeComponentImplementationImpl();
		return compositeComponentImplementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyPart createAssemblyPart() {
		AssemblyPartImpl assemblyPart = new AssemblyPartImpl();
		return assemblyPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractTypeBinding createAbstractTypeBinding() {
		AbstractTypeBindingImpl abstractTypeBinding = new AbstractTypeBindingImpl();
		return abstractTypeBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentModule createComponentModule() {
		ComponentModuleImpl componentModule = new ComponentModuleImpl();
		return componentModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_componentsPackage getUcm_componentsPackage() {
		return (Ucm_componentsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_componentsPackage getPackage() {
		return Ucm_componentsPackage.eINSTANCE;
	}

} //Ucm_componentsFactoryImpl
