/**
 */
package org.eclipse.papyrus.designer.transformation.profile.Transformation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Apply Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Force the application of a specific M2M transformtion on an element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getTrafo <em>Trafo</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getBase_Element <em>Base Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getApplyTransformation()
 * @model
 * @generated
 */
public interface ApplyTransformation extends EObject {
	/**
	 * Returns the value of the '<em><b>Trafo</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trafo</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trafo</em>' reference list.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getApplyTransformation_Trafo()
	 * @model ordered="false"
	 * @generated
	 */
	EList<M2MTrafo> getTrafo();

	/**
	 * Returns the value of the '<em><b>Base Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Element</em>' reference.
	 * @see #setBase_Element(Element)
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getApplyTransformation_Base_Element()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Element getBase_Element();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getBase_Element <em>Base Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Element</em>' reference.
	 * @see #getBase_Element()
	 * @generated
	 */
	void setBase_Element(Element value);

} // ApplyTransformation
