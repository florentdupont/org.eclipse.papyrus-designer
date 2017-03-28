/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage
 * @generated
 */
public class Ucm_technicalpoliciesValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final Ucm_technicalpoliciesValidator INSTANCE = new Ucm_technicalpoliciesValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Constraint1' of 'Language'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int LANGUAGE__CONSTRAINT1 = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_technicalpoliciesValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return Ucm_technicalpoliciesPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case Ucm_technicalpoliciesPackage.TECHNICAL_ASPECT:
				return validateTechnicalAspect((TechnicalAspect)value, diagnostics, context);
			case Ucm_technicalpoliciesPackage.PROGRAMMING_LANGUAGES:
				return validateProgrammingLanguages((ProgrammingLanguages)value, diagnostics, context);
			case Ucm_technicalpoliciesPackage.LANGUAGE:
				return validateLanguage((Language)value, diagnostics, context);
			case Ucm_technicalpoliciesPackage.NONFUNCTIONAL_ASPECT_MODULE:
				return validateNonfunctionalAspectModule((NonfunctionalAspectModule)value, diagnostics, context);
			case Ucm_technicalpoliciesPackage.TECHNICAL_POLICY_DEFINITION:
				return validateTechnicalPolicyDefinition((TechnicalPolicyDefinition)value, diagnostics, context);
			case Ucm_technicalpoliciesPackage.TECHNICAL_ASPECT_CONSTRAINT:
				return validateTechnicalAspectConstraint((TechnicalAspectConstraint)value, diagnostics, context);
			case Ucm_technicalpoliciesPackage.TECHNICAL_POLICY_APPLICABILITY:
				return validateTechnicalPolicyApplicability((TechnicalPolicyApplicability)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTechnicalAspect(TechnicalAspect technicalAspect, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(technicalAspect, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProgrammingLanguages(ProgrammingLanguages programmingLanguages, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(programmingLanguages, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLanguage(Language language, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(language, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(language, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(language, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(language, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(language, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(language, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(language, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(language, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(language, diagnostics, context);
		if (result || diagnostics != null) result &= validateLanguage_Constraint1(language, diagnostics, context);
		return result;
	}

	/**
	 * Validates the Constraint1 constraint of '<em>Language</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLanguage_Constraint1(Language language, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return language.Constraint1(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNonfunctionalAspectModule(NonfunctionalAspectModule nonfunctionalAspectModule, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(nonfunctionalAspectModule, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTechnicalPolicyDefinition(TechnicalPolicyDefinition technicalPolicyDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(technicalPolicyDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTechnicalAspectConstraint(TechnicalAspectConstraint technicalAspectConstraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTechnicalPolicyApplicability(TechnicalPolicyApplicability technicalPolicyApplicability, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //Ucm_technicalpoliciesValidator
