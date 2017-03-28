/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage
 * @generated
 */
public class Ucm_interactionsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ucm_interactionsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_interactionsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Ucm_interactionsPackage.eINSTANCE;
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
	protected Ucm_interactionsSwitch<Adapter> modelSwitch =
		new Ucm_interactionsSwitch<Adapter>() {
			@Override
			public Adapter caseInteractionDefinitionModule(InteractionDefinitionModule object) {
				return createInteractionDefinitionModuleAdapter();
			}
			@Override
			public Adapter caseInteractionPattern(InteractionPattern object) {
				return createInteractionPatternAdapter();
			}
			@Override
			public Adapter caseIInteractionDefinition(IInteractionDefinition object) {
				return createIInteractionDefinitionAdapter();
			}
			@Override
			public Adapter caseConnectorPort(ConnectorPort object) {
				return createConnectorPortAdapter();
			}
			@Override
			public Adapter caseInteractionRole(InteractionRole object) {
				return createInteractionRoleAdapter();
			}
			@Override
			public Adapter casePortElement(PortElement object) {
				return createPortElementAdapter();
			}
			@Override
			public Adapter caseConnectorDefinition(ConnectorDefinition object) {
				return createConnectorDefinitionAdapter();
			}
			@Override
			public Adapter caseItemBinding(ItemBinding object) {
				return createItemBindingAdapter();
			}
			@Override
			public Adapter casePortType(PortType object) {
				return createPortTypeAdapter();
			}
			@Override
			public Adapter caseIPortType(IPortType object) {
				return createIPortTypeAdapter();
			}
			@Override
			public Adapter caseConnectorPortConfiguration(ConnectorPortConfiguration object) {
				return createConnectorPortConfigurationAdapter();
			}
			@Override
			public Adapter caseInteractionItem(InteractionItem object) {
				return createInteractionItemAdapter();
			}
			@Override
			public Adapter caseIConfigurable(IConfigurable object) {
				return createIConfigurableAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule <em>Interaction Definition Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule
	 * @generated
	 */
	public Adapter createInteractionDefinitionModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionPattern <em>Interaction Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionPattern
	 * @generated
	 */
	public Adapter createInteractionPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition <em>IInteraction Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition
	 * @generated
	 */
	public Adapter createIInteractionDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort <em>Connector Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPort
	 * @generated
	 */
	public Adapter createConnectorPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole <em>Interaction Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionRole
	 * @generated
	 */
	public Adapter createInteractionRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement <em>Port Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement
	 * @generated
	 */
	public Adapter createPortElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorDefinition <em>Connector Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorDefinition
	 * @generated
	 */
	public Adapter createConnectorDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding <em>Item Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ItemBinding
	 * @generated
	 */
	public Adapter createItemBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortType <em>Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortType
	 * @generated
	 */
	public Adapter createPortTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType <em>IPort Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType
	 * @generated
	 */
	public Adapter createIPortTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration <em>Connector Port Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorPortConfiguration
	 * @generated
	 */
	public Adapter createConnectorPortConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem <em>Interaction Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionItem
	 * @generated
	 */
	public Adapter createInteractionItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable <em>IConfigurable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable
	 * @generated
	 */
	public Adapter createIConfigurableAdapter() {
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

} //Ucm_interactionsAdapterFactory
