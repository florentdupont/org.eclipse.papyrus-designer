/**
 * Copyright (c) 2013 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CEA LIST - Initial API and implementation
 *
 */
package org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ptr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * "declaration" attribute should only be used, if more than one indirections of pointers (often "**")
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr#getBase_parameter <em>Base parameter</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr#getBase_property <em>Base property</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.C_CppPackage#getPtr()
 * @model
 * @generated
 */
public interface Ptr extends EObject {
	/**
	 * Returns the value of the '<em><b>Base parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base parameter</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base parameter</em>' reference.
	 * @see #setBase_parameter(Parameter)
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.C_CppPackage#getPtr_Base_parameter()
	 * @model ordered="false"
	 * @generated
	 */
	Parameter getBase_parameter();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr#getBase_parameter <em>Base parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base parameter</em>' reference.
	 * @see #getBase_parameter()
	 * @generated
	 */
	void setBase_parameter(Parameter value);

	/**
	 * Returns the value of the '<em><b>Base property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base property</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base property</em>' reference.
	 * @see #setBase_property(Property)
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.C_CppPackage#getPtr_Base_property()
	 * @model ordered="false"
	 * @generated
	 */
	Property getBase_property();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr#getBase_property <em>Base property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base property</em>' reference.
	 * @see #getBase_property()
	 * @generated
	 */
	void setBase_property(Property value);

	/**
	 * Returns the value of the '<em><b>Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declaration</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declaration</em>' attribute.
	 * @see #setDeclaration(String)
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.C_CppPackage#getPtr_Declaration()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	String getDeclaration();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr#getDeclaration <em>Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaration</em>' attribute.
	 * @see #getDeclaration()
	 * @generated
	 */
	void setDeclaration(String value);

} // Ptr
