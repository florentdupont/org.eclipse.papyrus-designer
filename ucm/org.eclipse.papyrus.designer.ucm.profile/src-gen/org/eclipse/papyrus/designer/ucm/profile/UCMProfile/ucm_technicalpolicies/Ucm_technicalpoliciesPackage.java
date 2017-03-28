/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_technicalpoliciesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_technicalpolicies";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/policies/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCMProfile.ucm_technicalpolicies";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_technicalpoliciesPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalAspectImpl <em>Technical Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalAspectImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalAspect()
	 * @generated
	 */
	int TECHNICAL_ASPECT = 0;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_ASPECT__MULTIPLICITY = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_ASPECT__BASE_CLASS = 1;

	/**
	 * The number of structural features of the '<em>Technical Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_ASPECT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Technical Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_ASPECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.ProgrammingLanguagesImpl <em>Programming Languages</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.ProgrammingLanguagesImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getProgrammingLanguages()
	 * @generated
	 */
	int PROGRAMMING_LANGUAGES = 1;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRAMMING_LANGUAGES__BASE_PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Languages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRAMMING_LANGUAGES__LANGUAGES = 1;

	/**
	 * The number of structural features of the '<em>Programming Languages</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRAMMING_LANGUAGES_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Programming Languages</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRAMMING_LANGUAGES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.LanguageImpl <em>Language</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.LanguageImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getLanguage()
	 * @generated
	 */
	int LANGUAGE = 2;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Constraint1</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE___CONSTRAINT1__DIAGNOSTICCHAIN_MAP = 0;

	/**
	 * The number of operations of the '<em>Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.NonfunctionalAspectModuleImpl <em>Nonfunctional Aspect Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.NonfunctionalAspectModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getNonfunctionalAspectModule()
	 * @generated
	 */
	int NONFUNCTIONAL_ASPECT_MODULE = 3;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NONFUNCTIONAL_ASPECT_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Nonfunctional Aspect Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NONFUNCTIONAL_ASPECT_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Nonfunctional Aspect Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NONFUNCTIONAL_ASPECT_MODULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalPolicyDefinitionImpl <em>Technical Policy Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalPolicyDefinitionImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalPolicyDefinition()
	 * @generated
	 */
	int TECHNICAL_POLICY_DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY_DEFINITION__BASE_CLASS = Ucm_contractsPackage.ICONFIGURABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Applicability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY_DEFINITION__APPLICABILITY = Ucm_contractsPackage.ICONFIGURABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Technical Policy Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY_DEFINITION_FEATURE_COUNT = Ucm_contractsPackage.ICONFIGURABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Technical Policy Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_POLICY_DEFINITION_OPERATION_COUNT = Ucm_contractsPackage.ICONFIGURABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspectConstraint <em>Technical Aspect Constraint</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspectConstraint
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalAspectConstraint()
	 * @generated
	 */
	int TECHNICAL_ASPECT_CONSTRAINT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability <em>Technical Policy Applicability</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalPolicyApplicability()
	 * @generated
	 */
	int TECHNICAL_POLICY_APPLICABILITY = 6;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect <em>Technical Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Technical Aspect</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect
	 * @generated
	 */
	EClass getTechnicalAspect();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect#getMultiplicity()
	 * @see #getTechnicalAspect()
	 * @generated
	 */
	EAttribute getTechnicalAspect_Multiplicity();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect#getBase_Class()
	 * @see #getTechnicalAspect()
	 * @generated
	 */
	EReference getTechnicalAspect_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages <em>Programming Languages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Programming Languages</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages
	 * @generated
	 */
	EClass getProgrammingLanguages();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages#getBase_Package()
	 * @see #getProgrammingLanguages()
	 * @generated
	 */
	EReference getProgrammingLanguages_Base_Package();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages#getLanguages <em>Languages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Languages</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages#getLanguages()
	 * @see #getProgrammingLanguages()
	 * @generated
	 */
	EReference getProgrammingLanguages_Languages();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Language</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language
	 * @generated
	 */
	EClass getLanguage();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language#getBase_Class()
	 * @see #getLanguage()
	 * @generated
	 */
	EReference getLanguage_Base_Class();

	/**
	 * Returns the meta object for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language#Constraint1(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Constraint1</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Constraint1</em>' operation.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language#Constraint1(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getLanguage__Constraint1__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.NonfunctionalAspectModule <em>Nonfunctional Aspect Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nonfunctional Aspect Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.NonfunctionalAspectModule
	 * @generated
	 */
	EClass getNonfunctionalAspectModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.NonfunctionalAspectModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.NonfunctionalAspectModule#getBase_Package()
	 * @see #getNonfunctionalAspectModule()
	 * @generated
	 */
	EReference getNonfunctionalAspectModule_Base_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition <em>Technical Policy Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Technical Policy Definition</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition
	 * @generated
	 */
	EClass getTechnicalPolicyDefinition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getBase_Class()
	 * @see #getTechnicalPolicyDefinition()
	 * @generated
	 */
	EReference getTechnicalPolicyDefinition_Base_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getApplicability <em>Applicability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Applicability</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getApplicability()
	 * @see #getTechnicalPolicyDefinition()
	 * @generated
	 */
	EAttribute getTechnicalPolicyDefinition_Applicability();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspectConstraint <em>Technical Aspect Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Technical Aspect Constraint</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspectConstraint
	 * @generated
	 */
	EEnum getTechnicalAspectConstraint();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability <em>Technical Policy Applicability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Technical Policy Applicability</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability
	 * @generated
	 */
	EEnum getTechnicalPolicyApplicability();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_technicalpoliciesFactory getUcm_technicalpoliciesFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalAspectImpl <em>Technical Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalAspectImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalAspect()
		 * @generated
		 */
		EClass TECHNICAL_ASPECT = eINSTANCE.getTechnicalAspect();

		/**
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TECHNICAL_ASPECT__MULTIPLICITY = eINSTANCE.getTechnicalAspect_Multiplicity();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TECHNICAL_ASPECT__BASE_CLASS = eINSTANCE.getTechnicalAspect_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.ProgrammingLanguagesImpl <em>Programming Languages</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.ProgrammingLanguagesImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getProgrammingLanguages()
		 * @generated
		 */
		EClass PROGRAMMING_LANGUAGES = eINSTANCE.getProgrammingLanguages();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROGRAMMING_LANGUAGES__BASE_PACKAGE = eINSTANCE.getProgrammingLanguages_Base_Package();

		/**
		 * The meta object literal for the '<em><b>Languages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROGRAMMING_LANGUAGES__LANGUAGES = eINSTANCE.getProgrammingLanguages_Languages();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.LanguageImpl <em>Language</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.LanguageImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getLanguage()
		 * @generated
		 */
		EClass LANGUAGE = eINSTANCE.getLanguage();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LANGUAGE__BASE_CLASS = eINSTANCE.getLanguage_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Constraint1</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation LANGUAGE___CONSTRAINT1__DIAGNOSTICCHAIN_MAP = eINSTANCE.getLanguage__Constraint1__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.NonfunctionalAspectModuleImpl <em>Nonfunctional Aspect Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.NonfunctionalAspectModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getNonfunctionalAspectModule()
		 * @generated
		 */
		EClass NONFUNCTIONAL_ASPECT_MODULE = eINSTANCE.getNonfunctionalAspectModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NONFUNCTIONAL_ASPECT_MODULE__BASE_PACKAGE = eINSTANCE.getNonfunctionalAspectModule_Base_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalPolicyDefinitionImpl <em>Technical Policy Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.TechnicalPolicyDefinitionImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalPolicyDefinition()
		 * @generated
		 */
		EClass TECHNICAL_POLICY_DEFINITION = eINSTANCE.getTechnicalPolicyDefinition();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TECHNICAL_POLICY_DEFINITION__BASE_CLASS = eINSTANCE.getTechnicalPolicyDefinition_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Applicability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TECHNICAL_POLICY_DEFINITION__APPLICABILITY = eINSTANCE.getTechnicalPolicyDefinition_Applicability();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspectConstraint <em>Technical Aspect Constraint</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspectConstraint
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalAspectConstraint()
		 * @generated
		 */
		EEnum TECHNICAL_ASPECT_CONSTRAINT = eINSTANCE.getTechnicalAspectConstraint();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability <em>Technical Policy Applicability</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesPackageImpl#getTechnicalPolicyApplicability()
		 * @generated
		 */
		EEnum TECHNICAL_POLICY_APPLICABILITY = eINSTANCE.getTechnicalPolicyApplicability();

	}

} //Ucm_technicalpoliciesPackage
