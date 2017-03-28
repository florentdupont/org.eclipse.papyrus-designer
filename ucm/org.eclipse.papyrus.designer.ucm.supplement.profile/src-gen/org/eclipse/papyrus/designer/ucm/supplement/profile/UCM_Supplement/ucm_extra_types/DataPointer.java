/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IDataType;

import org.eclipse.uml2.uml.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Pointer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.DataPointer#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.Ucm_extra_typesPackage#getDataPointer()
 * @model
 * @generated
 */
public interface DataPointer extends IDataType {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(DataType)
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.Ucm_extra_typesPackage#getDataPointer_Type()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DataType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_extra_types.DataPointer#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(DataType value);

} // DataPointer
