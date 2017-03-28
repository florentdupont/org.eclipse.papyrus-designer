/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Primitive Char</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveChar#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getPrimitiveChar()
 * @model
 * @generated
 */
public interface PrimitiveChar extends IStandardDataType {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind
	 * @see #setKind(PrimitiveCharKind)
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getPrimitiveChar_Kind()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	PrimitiveCharKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveChar#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(PrimitiveCharKind value);

} // PrimitiveChar
