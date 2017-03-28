/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Technical Policy Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getApplicability <em>Applicability</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#getTechnicalPolicyDefinition()
 * @model
 * @generated
 */
public interface TechnicalPolicyDefinition extends IConfigurable {
	/**
	 * Returns the value of the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Class</em>' reference.
	 * @see #setBase_Class(org.eclipse.uml2.uml.Class)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#getTechnicalPolicyDefinition_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

	/**
	 * Returns the value of the '<em><b>Applicability</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applicability</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applicability</em>' attribute.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability
	 * @see #setApplicability(TechnicalPolicyApplicability)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#getTechnicalPolicyDefinition_Applicability()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	TechnicalPolicyApplicability getApplicability();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition#getApplicability <em>Applicability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applicability</em>' attribute.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability
	 * @see #getApplicability()
	 * @generated
	 */
	void setApplicability(TechnicalPolicyApplicability value);

} // TechnicalPolicyDefinition
