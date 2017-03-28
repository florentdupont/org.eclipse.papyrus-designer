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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_contractsFactoryImpl extends EFactoryImpl implements Ucm_contractsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_contractsFactory init() {
		try {
			Ucm_contractsFactory theUcm_contractsFactory = (Ucm_contractsFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_contractsPackage.eNS_URI);
			if (theUcm_contractsFactory != null) {
				return theUcm_contractsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_contractsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_contractsFactoryImpl() {
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
			case Ucm_contractsPackage.ANNOTATION: return createAnnotation();
			case Ucm_contractsPackage.ANNOTATION_DEFINITION: return createAnnotationDefinition();
			case Ucm_contractsPackage.CONFIGURATION_PARAMETER: return createConfigurationParameter();
			case Ucm_contractsPackage.CONTRACT_MODULE: return createContractModule();
			case Ucm_contractsPackage.CONFIGURATION_PARAMETER_VALUE: return createConfigurationParameterValue();
			case Ucm_contractsPackage.ABSTRACT_INTERFACE: return createAbstractInterface();
			case Ucm_contractsPackage.ABSTRACT_DATA_TYPE: return createAbstractDataType();
			case Ucm_contractsPackage.CONSTANT: return createConstant();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Annotation createAnnotation() {
		AnnotationImpl annotation = new AnnotationImpl();
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationDefinition createAnnotationDefinition() {
		AnnotationDefinitionImpl annotationDefinition = new AnnotationDefinitionImpl();
		return annotationDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationParameter createConfigurationParameter() {
		ConfigurationParameterImpl configurationParameter = new ConfigurationParameterImpl();
		return configurationParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContractModule createContractModule() {
		ContractModuleImpl contractModule = new ContractModuleImpl();
		return contractModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationParameterValue createConfigurationParameterValue() {
		ConfigurationParameterValueImpl configurationParameterValue = new ConfigurationParameterValueImpl();
		return configurationParameterValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractInterface createAbstractInterface() {
		AbstractInterfaceImpl abstractInterface = new AbstractInterfaceImpl();
		return abstractInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractDataType createAbstractDataType() {
		AbstractDataTypeImpl abstractDataType = new AbstractDataTypeImpl();
		return abstractDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constant createConstant() {
		ConstantImpl constant = new ConstantImpl();
		return constant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_contractsPackage getUcm_contractsPackage() {
		return (Ucm_contractsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_contractsPackage getPackage() {
		return Ucm_contractsPackage.eINSTANCE;
	}

} //Ucm_contractsFactoryImpl
