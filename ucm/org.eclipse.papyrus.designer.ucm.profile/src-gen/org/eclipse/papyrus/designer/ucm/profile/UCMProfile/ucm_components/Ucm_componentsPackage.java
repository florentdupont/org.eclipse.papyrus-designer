/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage;

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
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_componentsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_components";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/components/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCMProfile.ucm_components";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_componentsPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IBindingImpl <em>IBinding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IBindingImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIBinding()
	 * @generated
	 */
	int IBINDING = 1;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBINDING__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>IBinding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBINDING_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IBinding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IBINDING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.InteractionItemBindingImpl <em>Interaction Item Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.InteractionItemBindingImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getInteractionItemBinding()
	 * @generated
	 */
	int INTERACTION_ITEM_BINDING = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM_BINDING__BASE_PROPERTY = IBINDING__BASE_PROPERTY;

	/**
	 * The feature id for the '<em><b>Item</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM_BINDING__ITEM = IBINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Interaction Item Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM_BINDING_FEATURE_COUNT = IBINDING_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Interaction Item Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_ITEM_BINDING_OPERATION_COUNT = IBINDING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IPortSpecImpl <em>IPort Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IPortSpecImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIPortSpec()
	 * @generated
	 */
	int IPORT_SPEC = 3;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_SPEC__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>IPort Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_SPEC_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IPort Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_SPEC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortRoleSpecImpl <em>Port Role Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortRoleSpecImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getPortRoleSpec()
	 * @generated
	 */
	int PORT_ROLE_SPEC = 2;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ROLE_SPEC__BASE_CLASS = IPORT_SPEC__BASE_CLASS;

	/**
	 * The feature id for the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ROLE_SPEC__ROLE = IPORT_SPEC_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Role Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ROLE_SPEC_FEATURE_COUNT = IPORT_SPEC_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Port Role Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ROLE_SPEC_OPERATION_COUNT = IPORT_SPEC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortTypeSpecImpl <em>Port Type Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortTypeSpecImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getPortTypeSpec()
	 * @generated
	 */
	int PORT_TYPE_SPEC = 4;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE_SPEC__BASE_CLASS = IPORT_SPEC__BASE_CLASS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE_SPEC__TYPE = IPORT_SPEC_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Type Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE_SPEC_FEATURE_COUNT = IPORT_SPEC_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Port Type Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_TYPE_SPEC_OPERATION_COUNT = IPORT_SPEC_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 5;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__VALUES = Ucm_contractsPackage.ICONFIGURED__VALUES;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__BASE_PROPERTY = Ucm_contractsPackage.ICONFIGURED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = Ucm_contractsPackage.ICONFIGURED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_OPERATION_COUNT = Ucm_contractsPackage.ICONFIGURED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionLinkImpl <em>Connection Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionLinkImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getConnectionLink()
	 * @generated
	 */
	int CONNECTION_LINK = 6;

	/**
	 * The feature id for the '<em><b>Base Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_LINK__BASE_CONNECTOR = 0;

	/**
	 * The number of structural features of the '<em>Connection Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_LINK_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Connection Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl <em>Technical Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getTechnicalPolicy()
	 * @generated
	 */
	int TECHNICAL_POLICY = 7;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY__VALUES = Ucm_contractsPackage.ICONFIGURED__VALUES;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY__DEFINITION = Ucm_contractsPackage.ICONFIGURED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY__BASE_CLASS = Ucm_contractsPackage.ICONFIGURED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Managed Port</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY__MANAGED_PORT = Ucm_contractsPackage.ICONFIGURED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Manage Policy</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY__MANAGE_POLICY = Ucm_contractsPackage.ICONFIGURED_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Technical Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY_FEATURE_COUNT = Ucm_contractsPackage.ICONFIGURED_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Technical Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY_OPERATION_COUNT = Ucm_contractsPackage.ICONFIGURED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImpl <em>IComponent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIComponent()
	 * @generated
	 */
	int ICOMPONENT = 10;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPONENT__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>IComponent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPONENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IComponent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPONENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImplementationImpl <em>IComponent Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImplementationImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIComponentImplementation()
	 * @generated
	 */
	int ICOMPONENT_IMPLEMENTATION = 9;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPONENT_IMPLEMENTATION__BASE_CLASS = ICOMPONENT__BASE_CLASS;

	/**
	 * The number of structural features of the '<em>IComponent Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPONENT_IMPLEMENTATION_FEATURE_COUNT = ICOMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>IComponent Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPONENT_IMPLEMENTATION_OPERATION_COUNT = ICOMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AtomicComponentImplementationImpl <em>Atomic Component Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AtomicComponentImplementationImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getAtomicComponentImplementation()
	 * @generated
	 */
	int ATOMIC_COMPONENT_IMPLEMENTATION = 8;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_COMPONENT_IMPLEMENTATION__BASE_CLASS = ICOMPONENT_IMPLEMENTATION__BASE_CLASS;

	/**
	 * The feature id for the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_COMPONENT_IMPLEMENTATION__LANGUAGE = ICOMPONENT_IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Atomic Component Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_COMPONENT_IMPLEMENTATION_FEATURE_COUNT = ICOMPONENT_IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Atomic Component Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_COMPONENT_IMPLEMENTATION_OPERATION_COUNT = ICOMPONENT_IMPLEMENTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentTypeImpl <em>Component Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getComponentType()
	 * @generated
	 */
	int COMPONENT_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__BASE_CLASS = ICOMPONENT__BASE_CLASS;

	/**
	 * The number of structural features of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_FEATURE_COUNT = ICOMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_OPERATION_COUNT = ICOMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.CompositeComponentImplementationImpl <em>Composite Component Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.CompositeComponentImplementationImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getCompositeComponentImplementation()
	 * @generated
	 */
	int COMPOSITE_COMPONENT_IMPLEMENTATION = 12;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_COMPONENT_IMPLEMENTATION__BASE_CLASS = ICOMPONENT_IMPLEMENTATION__BASE_CLASS;

	/**
	 * The number of structural features of the '<em>Composite Component Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_COMPONENT_IMPLEMENTATION_FEATURE_COUNT = ICOMPONENT_IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Composite Component Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_COMPONENT_IMPLEMENTATION_OPERATION_COUNT = ICOMPONENT_IMPLEMENTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AssemblyPartImpl <em>Assembly Part</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AssemblyPartImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getAssemblyPart()
	 * @generated
	 */
	int ASSEMBLY_PART = 13;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_PART__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Assembly Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_PART_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Constraint1</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_PART___CONSTRAINT1__DIAGNOSTICCHAIN_MAP = 0;

	/**
	 * The number of operations of the '<em>Assembly Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_PART_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AbstractTypeBindingImpl <em>Abstract Type Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AbstractTypeBindingImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getAbstractTypeBinding()
	 * @generated
	 */
	int ABSTRACT_TYPE_BINDING = 14;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_BINDING__BASE_PROPERTY = IBINDING__BASE_PROPERTY;

	/**
	 * The feature id for the '<em><b>Abstract Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE = IBINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Type Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_BINDING_FEATURE_COUNT = IBINDING_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Abstract Type Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_BINDING_OPERATION_COUNT = IBINDING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentModuleImpl <em>Component Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getComponentModule()
	 * @generated
	 */
	int COMPONENT_MODULE = 15;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Component Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Component Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_MODULE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.InteractionItemBinding <em>Interaction Item Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction Item Binding</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.InteractionItemBinding
	 * @generated
	 */
	EClass getInteractionItemBinding();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.InteractionItemBinding#getItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Item</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.InteractionItemBinding#getItem()
	 * @see #getInteractionItemBinding()
	 * @generated
	 */
	EReference getInteractionItemBinding_Item();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IBinding <em>IBinding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBinding</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IBinding
	 * @generated
	 */
	EClass getIBinding();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IBinding#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IBinding#getBase_Property()
	 * @see #getIBinding()
	 * @generated
	 */
	EReference getIBinding_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortRoleSpec <em>Port Role Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Role Spec</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortRoleSpec
	 * @generated
	 */
	EClass getPortRoleSpec();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortRoleSpec#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortRoleSpec#getRole()
	 * @see #getPortRoleSpec()
	 * @generated
	 */
	EReference getPortRoleSpec_Role();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IPortSpec <em>IPort Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IPort Spec</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IPortSpec
	 * @generated
	 */
	EClass getIPortSpec();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IPortSpec#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IPortSpec#getBase_Class()
	 * @see #getIPortSpec()
	 * @generated
	 */
	EReference getIPortSpec_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec <em>Port Type Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Type Spec</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec
	 * @generated
	 */
	EClass getPortTypeSpec();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec#getType()
	 * @see #getPortTypeSpec()
	 * @generated
	 */
	EReference getPortTypeSpec_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Connection#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Connection#getBase_Property()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ConnectionLink <em>Connection Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Link</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ConnectionLink
	 * @generated
	 */
	EClass getConnectionLink();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ConnectionLink#getBase_Connector <em>Base Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Connector</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ConnectionLink#getBase_Connector()
	 * @see #getConnectionLink()
	 * @generated
	 */
	EReference getConnectionLink_Base_Connector();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy <em>Technical Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Technical Policy</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy
	 * @generated
	 */
	EClass getTechnicalPolicy();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Definition</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getDefinition()
	 * @see #getTechnicalPolicy()
	 * @generated
	 */
	EReference getTechnicalPolicy_Definition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getBase_Class()
	 * @see #getTechnicalPolicy()
	 * @generated
	 */
	EReference getTechnicalPolicy_Base_Class();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getManagedPort <em>Managed Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Managed Port</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getManagedPort()
	 * @see #getTechnicalPolicy()
	 * @generated
	 */
	EReference getTechnicalPolicy_ManagedPort();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getManagePolicy <em>Manage Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Manage Policy</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy#getManagePolicy()
	 * @see #getTechnicalPolicy()
	 * @generated
	 */
	EReference getTechnicalPolicy_ManagePolicy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation <em>Atomic Component Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Atomic Component Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation
	 * @generated
	 */
	EClass getAtomicComponentImplementation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Language</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation#getLanguage()
	 * @see #getAtomicComponentImplementation()
	 * @generated
	 */
	EReference getAtomicComponentImplementation_Language();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponentImplementation <em>IComponent Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IComponent Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponentImplementation
	 * @generated
	 */
	EClass getIComponentImplementation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent <em>IComponent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IComponent</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent
	 * @generated
	 */
	EClass getIComponent();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent#getBase_Class()
	 * @see #getIComponent()
	 * @generated
	 */
	EReference getIComponent_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentType
	 * @generated
	 */
	EClass getComponentType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.CompositeComponentImplementation <em>Composite Component Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Component Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.CompositeComponentImplementation
	 * @generated
	 */
	EClass getCompositeComponentImplementation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart <em>Assembly Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assembly Part</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart
	 * @generated
	 */
	EClass getAssemblyPart();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart#getBase_Property()
	 * @see #getAssemblyPart()
	 * @generated
	 */
	EReference getAssemblyPart_Base_Property();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart#Constraint1(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Constraint1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Constraint1</em>' operation.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart#Constraint1(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getAssemblyPart__Constraint1__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding <em>Abstract Type Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Type Binding</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding
	 * @generated
	 */
	EClass getAbstractTypeBinding();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding#getAbstractType <em>Abstract Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Abstract Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding#getAbstractType()
	 * @see #getAbstractTypeBinding()
	 * @generated
	 */
	EReference getAbstractTypeBinding_AbstractType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule <em>Component Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule
	 * @generated
	 */
	EClass getComponentModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule#getBase_Package()
	 * @see #getComponentModule()
	 * @generated
	 */
	EReference getComponentModule_Base_Package();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_componentsFactory getUcm_componentsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.InteractionItemBindingImpl <em>Interaction Item Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.InteractionItemBindingImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getInteractionItemBinding()
		 * @generated
		 */
		EClass INTERACTION_ITEM_BINDING = eINSTANCE.getInteractionItemBinding();

		/**
		 * The meta object literal for the '<em><b>Item</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION_ITEM_BINDING__ITEM = eINSTANCE.getInteractionItemBinding_Item();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IBindingImpl <em>IBinding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IBindingImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIBinding()
		 * @generated
		 */
		EClass IBINDING = eINSTANCE.getIBinding();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IBINDING__BASE_PROPERTY = eINSTANCE.getIBinding_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortRoleSpecImpl <em>Port Role Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortRoleSpecImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getPortRoleSpec()
		 * @generated
		 */
		EClass PORT_ROLE_SPEC = eINSTANCE.getPortRoleSpec();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_ROLE_SPEC__ROLE = eINSTANCE.getPortRoleSpec_Role();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IPortSpecImpl <em>IPort Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IPortSpecImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIPortSpec()
		 * @generated
		 */
		EClass IPORT_SPEC = eINSTANCE.getIPortSpec();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPORT_SPEC__BASE_CLASS = eINSTANCE.getIPortSpec_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortTypeSpecImpl <em>Port Type Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.PortTypeSpecImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getPortTypeSpec()
		 * @generated
		 */
		EClass PORT_TYPE_SPEC = eINSTANCE.getPortTypeSpec();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_TYPE_SPEC__TYPE = eINSTANCE.getPortTypeSpec_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__BASE_PROPERTY = eINSTANCE.getConnection_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionLinkImpl <em>Connection Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ConnectionLinkImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getConnectionLink()
		 * @generated
		 */
		EClass CONNECTION_LINK = eINSTANCE.getConnectionLink();

		/**
		 * The meta object literal for the '<em><b>Base Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_LINK__BASE_CONNECTOR = eINSTANCE.getConnectionLink_Base_Connector();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl <em>Technical Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.TechnicalPolicyImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getTechnicalPolicy()
		 * @generated
		 */
		EClass TECHNICAL_POLICY = eINSTANCE.getTechnicalPolicy();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TECHNICAL_POLICY__DEFINITION = eINSTANCE.getTechnicalPolicy_Definition();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TECHNICAL_POLICY__BASE_CLASS = eINSTANCE.getTechnicalPolicy_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Managed Port</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TECHNICAL_POLICY__MANAGED_PORT = eINSTANCE.getTechnicalPolicy_ManagedPort();

		/**
		 * The meta object literal for the '<em><b>Manage Policy</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TECHNICAL_POLICY__MANAGE_POLICY = eINSTANCE.getTechnicalPolicy_ManagePolicy();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AtomicComponentImplementationImpl <em>Atomic Component Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AtomicComponentImplementationImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getAtomicComponentImplementation()
		 * @generated
		 */
		EClass ATOMIC_COMPONENT_IMPLEMENTATION = eINSTANCE.getAtomicComponentImplementation();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATOMIC_COMPONENT_IMPLEMENTATION__LANGUAGE = eINSTANCE.getAtomicComponentImplementation_Language();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImplementationImpl <em>IComponent Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImplementationImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIComponentImplementation()
		 * @generated
		 */
		EClass ICOMPONENT_IMPLEMENTATION = eINSTANCE.getIComponentImplementation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImpl <em>IComponent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.IComponentImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getIComponent()
		 * @generated
		 */
		EClass ICOMPONENT = eINSTANCE.getIComponent();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICOMPONENT__BASE_CLASS = eINSTANCE.getIComponent_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentTypeImpl <em>Component Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getComponentType()
		 * @generated
		 */
		EClass COMPONENT_TYPE = eINSTANCE.getComponentType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.CompositeComponentImplementationImpl <em>Composite Component Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.CompositeComponentImplementationImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getCompositeComponentImplementation()
		 * @generated
		 */
		EClass COMPOSITE_COMPONENT_IMPLEMENTATION = eINSTANCE.getCompositeComponentImplementation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AssemblyPartImpl <em>Assembly Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AssemblyPartImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getAssemblyPart()
		 * @generated
		 */
		EClass ASSEMBLY_PART = eINSTANCE.getAssemblyPart();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_PART__BASE_PROPERTY = eINSTANCE.getAssemblyPart_Base_Property();

		/**
		 * The meta object literal for the '<em><b>Constraint1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ASSEMBLY_PART___CONSTRAINT1__DIAGNOSTICCHAIN_MAP = eINSTANCE.getAssemblyPart__Constraint1__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AbstractTypeBindingImpl <em>Abstract Type Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.AbstractTypeBindingImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getAbstractTypeBinding()
		 * @generated
		 */
		EClass ABSTRACT_TYPE_BINDING = eINSTANCE.getAbstractTypeBinding();

		/**
		 * The meta object literal for the '<em><b>Abstract Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TYPE_BINDING__ABSTRACT_TYPE = eINSTANCE.getAbstractTypeBinding_AbstractType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentModuleImpl <em>Component Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.ComponentModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsPackageImpl#getComponentModule()
		 * @generated
		 */
		EClass COMPONENT_MODULE = eINSTANCE.getComponentModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_MODULE__BASE_PACKAGE = eINSTANCE.getComponentModule_Base_Package();

	}

} //Ucm_componentsPackage
