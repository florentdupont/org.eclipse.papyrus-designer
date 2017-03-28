/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.*;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage
 * @generated
 */
public class Ucm_componentsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ucm_componentsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_componentsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Ucm_componentsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Ucm_componentsSwitch<Adapter> modelSwitch =
		new Ucm_componentsSwitch<Adapter>() {
			@Override
			public Adapter caseInteractionItemBinding(InteractionItemBinding object) {
				return createInteractionItemBindingAdapter();
			}
			@Override
			public Adapter caseIBinding(IBinding object) {
				return createIBindingAdapter();
			}
			@Override
			public Adapter casePortRoleSpec(PortRoleSpec object) {
				return createPortRoleSpecAdapter();
			}
			@Override
			public Adapter caseIPortSpec(IPortSpec object) {
				return createIPortSpecAdapter();
			}
			@Override
			public Adapter casePortTypeSpec(PortTypeSpec object) {
				return createPortTypeSpecAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter caseConnectionLink(ConnectionLink object) {
				return createConnectionLinkAdapter();
			}
			@Override
			public Adapter caseTechnicalPolicy(TechnicalPolicy object) {
				return createTechnicalPolicyAdapter();
			}
			@Override
			public Adapter caseAtomicComponentImplementation(AtomicComponentImplementation object) {
				return createAtomicComponentImplementationAdapter();
			}
			@Override
			public Adapter caseIComponentImplementation(IComponentImplementation object) {
				return createIComponentImplementationAdapter();
			}
			@Override
			public Adapter caseIComponent(IComponent object) {
				return createIComponentAdapter();
			}
			@Override
			public Adapter caseComponentType(ComponentType object) {
				return createComponentTypeAdapter();
			}
			@Override
			public Adapter caseCompositeComponentImplementation(CompositeComponentImplementation object) {
				return createCompositeComponentImplementationAdapter();
			}
			@Override
			public Adapter caseAssemblyPart(AssemblyPart object) {
				return createAssemblyPartAdapter();
			}
			@Override
			public Adapter caseAbstractTypeBinding(AbstractTypeBinding object) {
				return createAbstractTypeBindingAdapter();
			}
			@Override
			public Adapter caseComponentModule(ComponentModule object) {
				return createComponentModuleAdapter();
			}
			@Override
			public Adapter caseIConfigured(IConfigured object) {
				return createIConfiguredAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.InteractionItemBinding <em>Interaction Item Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.InteractionItemBinding
	 * @generated
	 */
	public Adapter createInteractionItemBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IBinding <em>IBinding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IBinding
	 * @generated
	 */
	public Adapter createIBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortRoleSpec <em>Port Role Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortRoleSpec
	 * @generated
	 */
	public Adapter createPortRoleSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IPortSpec <em>IPort Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IPortSpec
	 * @generated
	 */
	public Adapter createIPortSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec <em>Port Type Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec
	 * @generated
	 */
	public Adapter createPortTypeSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ConnectionLink <em>Connection Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ConnectionLink
	 * @generated
	 */
	public Adapter createConnectionLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy <em>Technical Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy
	 * @generated
	 */
	public Adapter createTechnicalPolicyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation <em>Atomic Component Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation
	 * @generated
	 */
	public Adapter createAtomicComponentImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponentImplementation <em>IComponent Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponentImplementation
	 * @generated
	 */
	public Adapter createIComponentImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent <em>IComponent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.IComponent
	 * @generated
	 */
	public Adapter createIComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentType
	 * @generated
	 */
	public Adapter createComponentTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.CompositeComponentImplementation <em>Composite Component Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.CompositeComponentImplementation
	 * @generated
	 */
	public Adapter createCompositeComponentImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart <em>Assembly Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart
	 * @generated
	 */
	public Adapter createAssemblyPartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding <em>Abstract Type Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding
	 * @generated
	 */
	public Adapter createAbstractTypeBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule <em>Component Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule
	 * @generated
	 */
	public Adapter createComponentModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured <em>IConfigured</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured
	 * @generated
	 */
	public Adapter createIConfiguredAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Ucm_componentsAdapterFactory
