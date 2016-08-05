/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Use Instance Configurator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getConfigurator <em>Configurator</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getUseInstanceConfigurator()
 * @model
 * @generated
 */
public interface UseInstanceConfigurator extends EObject {
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
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getUseInstanceConfigurator_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

	/**
	 * Returns the value of the '<em><b>Configurator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configurator</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configurator</em>' reference.
	 * @see #setConfigurator(InstanceConfigurator)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getUseInstanceConfigurator_Configurator()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	InstanceConfigurator getConfigurator();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator#getConfigurator <em>Configurator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configurator</em>' reference.
	 * @see #getConfigurator()
	 * @generated
	 */
	void setConfigurator(InstanceConfigurator value);

} // UseInstanceConfigurator
