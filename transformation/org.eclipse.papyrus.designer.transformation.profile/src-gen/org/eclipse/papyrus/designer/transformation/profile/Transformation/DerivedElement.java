/**
 */
package org.eclipse.papyrus.designer.transformation.profile.Transformation;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Derived Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getBase_Element <em>Base Element</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getDerivedElement()
 * @model
 * @generated
 */
public interface DerivedElement extends EObject {
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
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getDerivedElement_Base_Element()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Element getBase_Element();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getBase_Element <em>Base Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Element</em>' reference.
	 * @see #getBase_Element()
	 * @generated
	 */
	void setBase_Element(Element value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Element)
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getDerivedElement_Source()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Element getSource();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Element value);

} // DerivedElement
