/**
 */
package org.eclipse.papyrus.designer.transformation.profile.Transformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>M2M Trafo</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a M2M transformation. Such an M2M is directly associated with a Java implementation (via an extension point). Can define a relative execution order by referencing other transformations
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBefore <em>Before</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getAfter <em>After</em>}</li>
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
	 * Returns the value of the '<em><b>Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Before</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Before</em>' reference.
	 * @see #setBefore(M2MTrafo)
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getM2MTrafo_Before()
	 * @model ordered="false"
	 * @generated
	 */
	M2MTrafo getBefore();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBefore <em>Before</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Before</em>' reference.
	 * @see #getBefore()
	 * @generated
	 */
	void setBefore(M2MTrafo value);

	/**
	 * Returns the value of the '<em><b>After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>After</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>After</em>' reference.
	 * @see #setAfter(M2MTrafo)
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage#getM2MTrafo_After()
	 * @model ordered="false"
	 * @generated
	 */
	M2MTrafo getAfter();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getAfter <em>After</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After</em>' reference.
	 * @see #getAfter()
	 * @generated
	 */
	void setAfter(M2MTrafo value);

} // M2MTrafo
