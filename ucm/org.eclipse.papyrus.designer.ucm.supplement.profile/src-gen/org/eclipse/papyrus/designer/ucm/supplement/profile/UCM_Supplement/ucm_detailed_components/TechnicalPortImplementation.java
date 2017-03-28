/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Technical Port Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation#getPolicy <em>Policy</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage#getTechnicalPortImplementation()
 * @model
 * @generated
 */
public interface TechnicalPortImplementation extends IPortElementImplementation {
	/**
	 * Returns the value of the '<em><b>Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Policy</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Policy</em>' reference.
	 * @see #setPolicy(TechnicalPolicy)
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage#getTechnicalPortImplementation_Policy()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	TechnicalPolicy getPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation#getPolicy <em>Policy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Policy</em>' reference.
	 * @see #getPolicy()
	 * @generated
	 */
	void setPolicy(TechnicalPolicy value);

} // TechnicalPortImplementation
