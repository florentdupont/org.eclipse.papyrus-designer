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
 * A representation of the model object '<em><b>IResizable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IResizable#getMaxSize <em>Max Size</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getIResizable()
 * @model abstract="true"
 * @generated
 */
public interface IResizable extends EObject {
	/**
	 * Returns the value of the '<em><b>Max Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Size</em>' attribute.
	 * @see #setMaxSize(int)
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getIResizable_MaxSize()
	 * @model dataType="org.eclipse.uml2.types.Integer" required="true" ordered="false"
	 * @generated
	 */
	int getMaxSize();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IResizable#getMaxSize <em>Max Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Size</em>' attribute.
	 * @see #getMaxSize()
	 * @generated
	 */
	void setMaxSize(int value);

} // IResizable
