/**
 * Copyright (c) 2013 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - Initial API and implementation
 *
 */
package org.eclipse.papyrus.designer.components.FCM.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.components.FCM.*;
import org.eclipse.papyrus.designer.components.FCM.CodeGenOptions;
import org.eclipse.papyrus.designer.components.FCM.CompImplTemplate;
import org.eclipse.papyrus.designer.components.FCM.CompToOOmapping;
import org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance;
import org.eclipse.papyrus.designer.components.FCM.Connector;
import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.FCM.FCMPackage;
import org.eclipse.papyrus.designer.components.FCM.ImplementationGroup;
import org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.components.FCM.InterceptionRule;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.FCM.RuleApplication;
import org.eclipse.papyrus.designer.components.FCM.Singleton;
import org.eclipse.papyrus.designer.components.FCM.TemplatePort;
import org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.components.FCM.FCMPackage
 * @generated
 */
public class FCMAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FCMPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FCMAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FCMPackage.eINSTANCE;
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
	protected FCMSwitch<Adapter> modelSwitch =
			new FCMSwitch<Adapter>() {
			@Override
			public Adapter caseImplementationGroup(ImplementationGroup object) {
				return createImplementationGroupAdapter();
			}
			@Override
			public Adapter caseCompToOOmapping(CompToOOmapping object) {
				return createCompToOOmappingAdapter();
			}
			@Override
			public Adapter caseCodeGenOptions(CodeGenOptions object) {
				return createCodeGenOptionsAdapter();
			}
			@Override
			public Adapter caseInteractionComponent(InteractionComponent object) {
				return createInteractionComponentAdapter();
			}
			@Override
			public Adapter caseSingleton(Singleton object) {
				return createSingletonAdapter();
			}
			@Override
			public Adapter caseConnector(Connector object) {
				return createConnectorAdapter();
			}
			@Override
			public Adapter caseConfigurableElementInstance(ConfigurableElementInstance object) {
				return createConfigurableElementInstanceAdapter();
			}
			@Override
			public Adapter caseUseInstanceConfigurator(UseInstanceConfigurator object) {
				return createUseInstanceConfiguratorAdapter();
			}
			@Override
			public Adapter caseInstanceConfigurator(InstanceConfigurator object) {
				return createInstanceConfiguratorAdapter();
			}
			@Override
			public Adapter caseRuleApplication(RuleApplication object) {
				return createRuleApplicationAdapter();
			}
			@Override
			public Adapter caseContainerRule(ContainerRule object) {
				return createContainerRuleAdapter();
			}
			@Override
			public Adapter caseTemplatePort(TemplatePort object) {
				return createTemplatePortAdapter();
			}
			@Override
			public Adapter casePort(Port object) {
				return createPortAdapter();
			}
			@Override
			public Adapter casePortKind(PortKind object) {
				return createPortKindAdapter();
			}
			@Override
			public Adapter caseCompImplTemplate(CompImplTemplate object) {
				return createCompImplTemplateAdapter();
			}
			@Override
			public Adapter caseInterceptionRule(InterceptionRule object) {
				return createInterceptionRuleAdapter();
			}
			@Override
			public Adapter caseFragment(Fragment object) {
				return createFragmentAdapter();
			}
			@Override
			public Adapter caseAssembly(Assembly object) {
				return createAssemblyAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.ImplementationGroup <em>Implementation Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.ImplementationGroup
	 * @generated
	 */
	public Adapter createImplementationGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.CompToOOmapping <em>Comp To OOmapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.CompToOOmapping
	 * @generated
	 */
	public Adapter createCompToOOmappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.CodeGenOptions <em>Code Gen Options</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.CodeGenOptions
	 * @generated
	 */
	public Adapter createCodeGenOptionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.InteractionComponent <em>Interaction Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.InteractionComponent
	 * @generated
	 */
	public Adapter createInteractionComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.Singleton <em>Singleton</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.Singleton
	 * @generated
	 */
	public Adapter createSingletonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.Connector
	 * @generated
	 */
	public Adapter createConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance <em>Configurable Element Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance
	 * @generated
	 */
	public Adapter createConfigurableElementInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator <em>Use Instance Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator
	 * @generated
	 */
	public Adapter createUseInstanceConfiguratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator <em>Instance Configurator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator
	 * @generated
	 */
	public Adapter createInstanceConfiguratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.RuleApplication <em>Rule Application</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.RuleApplication
	 * @generated
	 */
	public Adapter createRuleApplicationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.ContainerRule <em>Container Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.ContainerRule
	 * @generated
	 */
	public Adapter createContainerRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.TemplatePort <em>Template Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.TemplatePort
	 * @generated
	 */
	public Adapter createTemplatePortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.Port
	 * @generated
	 */
	public Adapter createPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.PortKind <em>Port Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.PortKind
	 * @generated
	 */
	public Adapter createPortKindAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.CompImplTemplate <em>Comp Impl Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.CompImplTemplate
	 * @generated
	 */
	public Adapter createCompImplTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.InterceptionRule <em>Interception Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.InterceptionRule
	 * @generated
	 */
	public Adapter createInterceptionRuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.Fragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.Fragment
	 * @generated
	 */
	public Adapter createFragmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.components.FCM.Assembly <em>Assembly</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.components.FCM.Assembly
	 * @generated
	 */
	public Adapter createAssemblyAdapter() {
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

} // FCMAdapterFactory
