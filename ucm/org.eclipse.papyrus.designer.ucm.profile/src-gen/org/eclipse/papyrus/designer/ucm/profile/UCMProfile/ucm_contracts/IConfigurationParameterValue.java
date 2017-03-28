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

import org.eclipse.uml2.uml.Slot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IConfiguration Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Slot sterotyped by IConfigurationParameterValue must belong to an IConfigured. The type of the Slot itself must be an IConfigurationParameter
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue#getBase_Slot <em>Base Slot</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage#getIConfigurationParameterValue()
 * @model abstract="true"
 * @generated
 */
public interface IConfigurationParameterValue extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Slot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Slot</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Slot</em>' reference.
	 * @see #setBase_Slot(Slot)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsPackage#getIConfigurationParameterValue_Base_Slot()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Slot getBase_Slot();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue#getBase_Slot <em>Base Slot</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Slot</em>' reference.
	 * @see #getBase_Slot()
	 * @generated
	 */
	void setBase_Slot(Slot value);

} // IConfigurationParameterValue
