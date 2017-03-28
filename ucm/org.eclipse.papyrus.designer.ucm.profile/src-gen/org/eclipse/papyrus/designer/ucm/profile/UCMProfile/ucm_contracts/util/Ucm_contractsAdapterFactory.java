/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage
 * @generated
 */
public class Ucm_contractsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ucm_contractsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_contractsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Ucm_contractsPackage.eINSTANCE;
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
	protected Ucm_contractsSwitch<Adapter> modelSwitch =
		new Ucm_contractsSwitch<Adapter>() {
			@Override
			public Adapter caseAbstractDeclaration(AbstractDeclaration object) {
				return createAbstractDeclarationAdapter();
			}
			@Override
			public Adapter caseAnnotation(Annotation object) {
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseIConfigured(IConfigured object) {
				return createIConfiguredAdapter();
			}
			@Override
			public Adapter caseAnnotationDefinition(AnnotationDefinition object) {
				return createAnnotationDefinitionAdapter();
			}
			@Override
			public Adapter caseConfigurationParameter(ConfigurationParameter object) {
				return createConfigurationParameterAdapter();
			}
			@Override
			public Adapter caseIConfigurationParameter(IConfigurationParameter object) {
				return createIConfigurationParameterAdapter();
			}
			@Override
			public Adapter caseContractModule(ContractModule object) {
				return createContractModuleAdapter();
			}
			@Override
			public Adapter caseIConfigurationParameterValue(IConfigurationParameterValue object) {
				return createIConfigurationParameterValueAdapter();
			}
			@Override
			public Adapter caseConfigurationParameterValue(ConfigurationParameterValue object) {
				return createConfigurationParameterValueAdapter();
			}
			@Override
			public Adapter caseAbstractInterface(AbstractInterface object) {
				return createAbstractInterfaceAdapter();
			}
			@Override
			public Adapter caseAbstractDataType(AbstractDataType object) {
				return createAbstractDataTypeAdapter();
			}
			@Override
			public Adapter caseConstant(Constant object) {
				return createConstantAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration <em>Abstract Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration
	 * @generated
	 */
	public Adapter createAbstractDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation
	 * @generated
	 */
	public Adapter createAnnotationAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AnnotationDefinition <em>Annotation Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AnnotationDefinition
	 * @generated
	 */
	public Adapter createAnnotationDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameter <em>Configuration Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameter
	 * @generated
	 */
	public Adapter createConfigurationParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameter <em>IConfiguration Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameter
	 * @generated
	 */
	public Adapter createIConfigurationParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule <em>Contract Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule
	 * @generated
	 */
	public Adapter createContractModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue <em>IConfiguration Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue
	 * @generated
	 */
	public Adapter createIConfigurationParameterValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameterValue <em>Configuration Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameterValue
	 * @generated
	 */
	public Adapter createConfigurationParameterValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface <em>Abstract Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface
	 * @generated
	 */
	public Adapter createAbstractInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType <em>Abstract Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType
	 * @generated
	 */
	public Adapter createAbstractDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Constant
	 * @generated
	 */
	public Adapter createConstantAdapter() {
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

} //Ucm_contractsAdapterFactory
