/**
 */
package org.eclipse.papyrus.designer.languages.cpp.profile;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variadic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.cpp.profile.Variadic#getBase_operation <em>Base operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.cpp.profile.C_CppPackage#getVariadic()
 * @model
 * @generated
 */
public interface Variadic extends EObject {
	/**
	 * Returns the value of the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base operation</em>' reference.
	 * @see #setBase_operation(Operation)
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.C_CppPackage#getVariadic_Base_operation()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Operation getBase_operation();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Variadic#getBase_operation <em>Base operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base operation</em>' reference.
	 * @see #getBase_operation()
	 * @generated
	 */
	void setBase_operation(Operation value);

} // Variadic
