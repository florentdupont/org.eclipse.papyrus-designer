/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types;

import org.eclipse.uml2.uml.Enumeration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorName <em>Selector Name</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorType <em>Selector Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getUnion()
 * @model
 * @generated
 */
public interface Union extends IStandardDataType {
	/**
	 * Returns the value of the '<em><b>Selector Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selector Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selector Name</em>' attribute.
	 * @see #setSelectorName(String)
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getUnion_SelectorName()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getSelectorName();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorName <em>Selector Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selector Name</em>' attribute.
	 * @see #getSelectorName()
	 * @generated
	 */
	void setSelectorName(String value);

	/**
	 * Returns the value of the '<em><b>Selector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selector Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selector Type</em>' reference.
	 * @see #setSelectorType(Enumeration)
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getUnion_SelectorType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Enumeration getSelectorType();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorType <em>Selector Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selector Type</em>' reference.
	 * @see #getSelectorType()
	 * @generated
	 */
	void setSelectorType(Enumeration value);

} // Union
