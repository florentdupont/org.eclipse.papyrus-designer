/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init Precedence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getInvokeBefore <em>Invoke Before</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getInvokeAfter <em>Invoke After</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getInitPrecedence()
 * @model
 * @generated
 */
public interface InitPrecedence extends EObject {
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
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getInitPrecedence_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

	/**
	 * Returns the value of the '<em><b>Invoke Before</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invoke Before</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invoke Before</em>' reference list.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getInitPrecedence_InvokeBefore()
	 * @model ordered="false"
	 * @generated
	 */
	EList<org.eclipse.uml2.uml.Class> getInvokeBefore();

	/**
	 * Retrieves the first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>' from the '<em><b>Invoke Before</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name The '<em><b>Name</b></em>' of the {@link org.eclipse.uml2.uml.Class} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getInvokeBefore()
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getInvokeBefore(String name);

	/**
	 * Retrieves the first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>' from the '<em><b>Invoke Before</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name The '<em><b>Name</b></em>' of the {@link org.eclipse.uml2.uml.Class} to retrieve, or <code>null</code>.
	 * @param ignoreCase Whether to ignore case in {@link java.lang.String} comparisons.
	 * @param eClass The Ecore class of the {@link org.eclipse.uml2.uml.Class} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getInvokeBefore()
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getInvokeBefore(String name, boolean ignoreCase, EClass eClass);

	/**
	 * Returns the value of the '<em><b>Invoke After</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.uml2.uml.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invoke After</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invoke After</em>' reference list.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getInitPrecedence_InvokeAfter()
	 * @model ordered="false"
	 * @generated
	 */
	EList<org.eclipse.uml2.uml.Class> getInvokeAfter();

	/**
	 * Retrieves the first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>' from the '<em><b>Invoke After</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name The '<em><b>Name</b></em>' of the {@link org.eclipse.uml2.uml.Class} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getInvokeAfter()
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getInvokeAfter(String name);

	/**
	 * Retrieves the first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>' from the '<em><b>Invoke After</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name The '<em><b>Name</b></em>' of the {@link org.eclipse.uml2.uml.Class} to retrieve, or <code>null</code>.
	 * @param ignoreCase Whether to ignore case in {@link java.lang.String} comparisons.
	 * @param eClass The Ecore class of the {@link org.eclipse.uml2.uml.Class} to retrieve, or <code>null</code>.
	 * @return The first {@link org.eclipse.uml2.uml.Class} with the specified '<em><b>Name</b></em>', or <code>null</code>.
	 * @see #getInvokeAfter()
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getInvokeAfter(String name, boolean ignoreCase, EClass eClass);

} // InitPrecedence
