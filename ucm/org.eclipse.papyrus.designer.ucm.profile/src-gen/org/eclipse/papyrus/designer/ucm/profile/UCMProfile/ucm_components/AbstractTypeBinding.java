/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Type Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding#getAbstractType <em>Abstract Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getAbstractTypeBinding()
 * @model
 * @generated
 */
public interface AbstractTypeBinding extends IBinding {
	/**
	 * Returns the value of the '<em><b>Abstract Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract Type</em>' reference.
	 * @see #setAbstractType(AbstractDeclaration)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage#getAbstractTypeBinding_AbstractType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	AbstractDeclaration getAbstractType();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding#getAbstractType <em>Abstract Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract Type</em>' reference.
	 * @see #getAbstractType()
	 * @generated
	 */
	void setAbstractType(AbstractDeclaration value);

} // AbstractTypeBinding
