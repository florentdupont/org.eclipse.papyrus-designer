/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_technicalpoliciesFactoryImpl extends EFactoryImpl implements Ucm_technicalpoliciesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_technicalpoliciesFactory init() {
		try {
			Ucm_technicalpoliciesFactory theUcm_technicalpoliciesFactory = (Ucm_technicalpoliciesFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_technicalpoliciesPackage.eNS_URI);
			if (theUcm_technicalpoliciesFactory != null) {
				return theUcm_technicalpoliciesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_technicalpoliciesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_technicalpoliciesFactoryImpl() {
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
			case Ucm_technicalpoliciesPackage.TECHNICAL_ASPECT: return createTechnicalAspect();
			case Ucm_technicalpoliciesPackage.PROGRAMMING_LANGUAGES: return createProgrammingLanguages();
			case Ucm_technicalpoliciesPackage.LANGUAGE: return createLanguage();
			case Ucm_technicalpoliciesPackage.NONFUNCTIONAL_ASPECT_MODULE: return createNonfunctionalAspectModule();
			case Ucm_technicalpoliciesPackage.TECHNICAL_POLICY_DEFINITION: return createTechnicalPolicyDefinition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case Ucm_technicalpoliciesPackage.TECHNICAL_ASPECT_CONSTRAINT:
				return createTechnicalAspectConstraintFromString(eDataType, initialValue);
			case Ucm_technicalpoliciesPackage.TECHNICAL_POLICY_APPLICABILITY:
				return createTechnicalPolicyApplicabilityFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case Ucm_technicalpoliciesPackage.TECHNICAL_ASPECT_CONSTRAINT:
				return convertTechnicalAspectConstraintToString(eDataType, instanceValue);
			case Ucm_technicalpoliciesPackage.TECHNICAL_POLICY_APPLICABILITY:
				return convertTechnicalPolicyApplicabilityToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalAspect createTechnicalAspect() {
		TechnicalAspectImpl technicalAspect = new TechnicalAspectImpl();
		return technicalAspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProgrammingLanguages createProgrammingLanguages() {
		ProgrammingLanguagesImpl programmingLanguages = new ProgrammingLanguagesImpl();
		return programmingLanguages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Language createLanguage() {
		LanguageImpl language = new LanguageImpl();
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NonfunctionalAspectModule createNonfunctionalAspectModule() {
		NonfunctionalAspectModuleImpl nonfunctionalAspectModule = new NonfunctionalAspectModuleImpl();
		return nonfunctionalAspectModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalPolicyDefinition createTechnicalPolicyDefinition() {
		TechnicalPolicyDefinitionImpl technicalPolicyDefinition = new TechnicalPolicyDefinitionImpl();
		return technicalPolicyDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalAspectConstraint createTechnicalAspectConstraintFromString(EDataType eDataType, String initialValue) {
		TechnicalAspectConstraint result = TechnicalAspectConstraint.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTechnicalAspectConstraintToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalPolicyApplicability createTechnicalPolicyApplicabilityFromString(EDataType eDataType, String initialValue) {
		TechnicalPolicyApplicability result = TechnicalPolicyApplicability.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTechnicalPolicyApplicabilityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_technicalpoliciesPackage getUcm_technicalpoliciesPackage() {
		return (Ucm_technicalpoliciesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_technicalpoliciesPackage getPackage() {
		return Ucm_technicalpoliciesPackage.eINSTANCE;
	}

} //Ucm_technicalpoliciesFactoryImpl
