/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IIndexable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IIndexable#getIndexType <em>Index Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getIIndexable()
 * @model abstract="true"
 * @generated
 */
public interface IIndexable extends EObject {
	/**
	 * Returns the value of the '<em><b>Index Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Type</em>' attribute.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind
	 * @see #setIndexType(PrimitiveIntegerKind)
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getIIndexable_IndexType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	PrimitiveIntegerKind getIndexType();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IIndexable#getIndexType <em>Index Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Type</em>' attribute.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind
	 * @see #getIndexType()
	 * @generated
	 */
	void setIndexType(PrimitiveIntegerKind value);

} // IIndexable
