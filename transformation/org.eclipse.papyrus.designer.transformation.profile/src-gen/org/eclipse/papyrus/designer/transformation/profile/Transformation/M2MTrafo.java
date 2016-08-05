/**
 */
package org.eclipse.papyrus.designer.transformation.profile.Transformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>M2M Trafo</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getKind <em>Kind</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getM2MTrafo()
 * @model
 * @generated
 */
public interface M2MTrafo extends EObject {
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
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getM2MTrafo_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' reference.
	 * @see #setKind(M2MTrafoKind)
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getM2MTrafo_Kind()
	 * @model ordered="false"
	 * @generated
	 */
	M2MTrafoKind getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getKind <em>Kind</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' reference.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(M2MTrafoKind value);

} // M2MTrafo
