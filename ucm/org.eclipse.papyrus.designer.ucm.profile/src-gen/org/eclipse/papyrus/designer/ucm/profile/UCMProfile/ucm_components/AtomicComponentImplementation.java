/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Language;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Atomic Component Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * policy is computed from
 * the association between componentTechnicalPolicy
 * and AtomicComponentImplementation
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation#getLanguage <em>Language</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getAtomicComponentImplementation()
 * @model
 * @generated
 */
public interface AtomicComponentImplementation extends IComponentImplementation {
	/**
	 * Returns the value of the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' reference.
	 * @see #setLanguage(Language)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getAtomicComponentImplementation_Language()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Language getLanguage();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation#getLanguage <em>Language</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' reference.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(Language value);

} // AtomicComponentImplementation
