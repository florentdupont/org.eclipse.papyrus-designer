/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage
 * @generated
 */
public class Ucm_contractsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Ucm_contractsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_contractsSwitch() {
		if (modelPackage == null) {
			modelPackage = Ucm_contractsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case Ucm_contractsPackage.ABSTRACT_DECLARATION: {
				AbstractDeclaration abstractDeclaration = (AbstractDeclaration)theEObject;
				T result = caseAbstractDeclaration(abstractDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ANNOTATION: {
				Annotation annotation = (Annotation)theEObject;
				T result = caseAnnotation(annotation);
				if (result == null) result = caseIConfigured(annotation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ICONFIGURED: {
				IConfigured iConfigured = (IConfigured)theEObject;
				T result = caseIConfigured(iConfigured);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ANNOTATION_DEFINITION: {
				AnnotationDefinition annotationDefinition = (AnnotationDefinition)theEObject;
				T result = caseAnnotationDefinition(annotationDefinition);
				if (result == null) result = caseIConfigurable(annotationDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.CONFIGURATION_PARAMETER: {
				ConfigurationParameter configurationParameter = (ConfigurationParameter)theEObject;
				T result = caseConfigurationParameter(configurationParameter);
				if (result == null) result = caseIConfigurationParameter(configurationParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ICONFIGURATION_PARAMETER: {
				IConfigurationParameter iConfigurationParameter = (IConfigurationParameter)theEObject;
				T result = caseIConfigurationParameter(iConfigurationParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.CONTRACT_MODULE: {
				ContractModule contractModule = (ContractModule)theEObject;
				T result = caseContractModule(contractModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ICONFIGURATION_PARAMETER_VALUE: {
				IConfigurationParameterValue iConfigurationParameterValue = (IConfigurationParameterValue)theEObject;
				T result = caseIConfigurationParameterValue(iConfigurationParameterValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.CONFIGURATION_PARAMETER_VALUE: {
				ConfigurationParameterValue configurationParameterValue = (ConfigurationParameterValue)theEObject;
				T result = caseConfigurationParameterValue(configurationParameterValue);
				if (result == null) result = caseIConfigurationParameterValue(configurationParameterValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ABSTRACT_INTERFACE: {
				AbstractInterface abstractInterface = (AbstractInterface)theEObject;
				T result = caseAbstractInterface(abstractInterface);
				if (result == null) result = caseAbstractDeclaration(abstractInterface);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ABSTRACT_DATA_TYPE: {
				AbstractDataType abstractDataType = (AbstractDataType)theEObject;
				T result = caseAbstractDataType(abstractDataType);
				if (result == null) result = caseAbstractDeclaration(abstractDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.CONSTANT: {
				Constant constant = (Constant)theEObject;
				T result = caseConstant(constant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Ucm_contractsPackage.ICONFIGURABLE: {
				IConfigurable iConfigurable = (IConfigurable)theEObject;
				T result = caseIConfigurable(iConfigurable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractDeclaration(AbstractDeclaration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotation(Annotation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConfigured</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConfigured</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConfigured(IConfigured object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotationDefinition(AnnotationDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationParameter(ConfigurationParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConfiguration Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConfiguration Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConfigurationParameter(IConfigurationParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contract Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contract Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContractModule(ContractModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConfiguration Parameter Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConfiguration Parameter Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConfigurationParameterValue(IConfigurationParameterValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration Parameter Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration Parameter Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurationParameterValue(ConfigurationParameterValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractInterface(AbstractInterface object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractDataType(AbstractDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstant(Constant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConfigurable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConfigurable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConfigurable(IConfigurable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //Ucm_contractsSwitch
