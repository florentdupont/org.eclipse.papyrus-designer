/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.InstanceSpecification;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IConfigured</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage#getIConfigured()
 * @model abstract="true"
 * @generated
 */
public interface IConfigured extends EObject {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' reference.
	 * @see #setValues(InstanceSpecification)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage#getIConfigured_Values()
	 * @model
	 * @generated
	 */
	InstanceSpecification getValues();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured#getValues <em>Values</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Values</em>' reference.
	 * @see #getValues()
	 * @generated
	 */
	void setValues(InstanceSpecification value);

} // IConfigured
