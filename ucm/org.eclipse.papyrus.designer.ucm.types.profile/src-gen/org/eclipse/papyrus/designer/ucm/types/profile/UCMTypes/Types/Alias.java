/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types;

import org.eclipse.uml2.uml.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alias</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias#getAliasedType <em>Aliased Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getAlias()
 * @model
 * @generated
 */
public interface Alias extends IStandardDataType {
	/**
	 * Returns the value of the '<em><b>Aliased Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aliased Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aliased Type</em>' reference.
	 * @see #setAliasedType(DataType)
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getAlias_AliasedType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DataType getAliasedType();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias#getAliasedType <em>Aliased Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aliased Type</em>' reference.
	 * @see #getAliasedType()
	 * @generated
	 */
	void setAliasedType(DataType value);

} // Alias
