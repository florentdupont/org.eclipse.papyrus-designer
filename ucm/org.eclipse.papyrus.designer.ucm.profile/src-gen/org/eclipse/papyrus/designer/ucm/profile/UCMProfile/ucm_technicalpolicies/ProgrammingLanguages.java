/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Programming Languages</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Must only contain Languages. container = NFAspectModule (OCL rule)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages#getBase_Package <em>Base Package</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages#getLanguages <em>Languages</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#getProgrammingLanguages()
 * @model
 * @generated
 */
public interface ProgrammingLanguages extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Package</em>' reference.
	 * @see #setBase_Package(org.eclipse.uml2.uml.Package)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#getProgrammingLanguages_Base_Package()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Package getBase_Package();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages#getBase_Package <em>Base Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Package</em>' reference.
	 * @see #getBase_Package()
	 * @generated
	 */
	void setBase_Package(org.eclipse.uml2.uml.Package value);

	/**
	 * Returns the value of the '<em><b>Languages</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Languages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Languages</em>' reference list.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#getProgrammingLanguages_Languages()
	 * @model transient="true" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Language> getLanguages();

} // ProgrammingLanguages
