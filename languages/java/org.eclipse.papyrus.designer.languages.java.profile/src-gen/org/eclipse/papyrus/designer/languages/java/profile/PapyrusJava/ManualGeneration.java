/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Manual Generation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getExtensionBody <em>Extension Body</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getBase_Class <em>Base Class</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getManualGeneration()
 * @model
 * @generated
 */
public interface ManualGeneration extends EObject {
	/**
	 * Returns the value of the '<em><b>Extension Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Body</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension Body</em>' attribute.
	 * @see #setExtensionBody(String)
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getManualGeneration_ExtensionBody()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getExtensionBody();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getExtensionBody <em>Extension Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension Body</em>' attribute.
	 * @see #getExtensionBody()
	 * @generated
	 */
	void setExtensionBody(String value);

	/**
	 * Returns the value of the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Class</em>' reference.
	 * @see #setBase_Class(org.eclipse.uml2.uml.Class)
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getManualGeneration_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

} // ManualGeneration
