/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage
 * @generated
 */
public interface Ucm_technicalpoliciesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_technicalpoliciesFactory eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.impl.Ucm_technicalpoliciesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Technical Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Technical Aspect</em>'.
	 * @generated
	 */
	TechnicalAspect createTechnicalAspect();

	/**
	 * Returns a new object of class '<em>Programming Languages</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Programming Languages</em>'.
	 * @generated
	 */
	ProgrammingLanguages createProgrammingLanguages();

	/**
	 * Returns a new object of class '<em>Language</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Language</em>'.
	 * @generated
	 */
	Language createLanguage();

	/**
	 * Returns a new object of class '<em>Nonfunctional Aspect Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nonfunctional Aspect Module</em>'.
	 * @generated
	 */
	NonfunctionalAspectModule createNonfunctionalAspectModule();

	/**
	 * Returns a new object of class '<em>Technical Policy Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Technical Policy Definition</em>'.
	 * @generated
	 */
	TechnicalPolicyDefinition createTechnicalPolicyDefinition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Ucm_technicalpoliciesPackage getUcm_technicalpoliciesPackage();

} //Ucm_technicalpoliciesFactory
