/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Parameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Final</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final#getBase_Parameter <em>Base Parameter</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getFinal()
 * @model
 * @generated
 */
public interface Final extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Parameter</em>' reference.
	 * @see #setBase_Parameter(Parameter)
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getFinal_Base_Parameter()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Parameter getBase_Parameter();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final#getBase_Parameter <em>Base Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Parameter</em>' reference.
	 * @see #getBase_Parameter()
	 * @generated
	 */
	void setBase_Parameter(Parameter value);

} // Final
