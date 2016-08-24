/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getBase_Package <em>Base Package</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getProjectMappings <em>Project Mappings</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getChain <em>Chain</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getDeploymentPlan()
 * @model
 * @generated
 */
public interface DeploymentPlan extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Package</em>' reference.
	 * @see #setBase_Package(org.eclipse.uml2.uml.Package)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getDeploymentPlan_Base_Package()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Package getBase_Package();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getBase_Package <em>Base Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Package</em>' reference.
	 * @see #getBase_Package()
	 * @generated
	 */
	void setBase_Package(org.eclipse.uml2.uml.Package value);

	/**
	 * Returns the value of the '<em><b>Project Mappings</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Mappings</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Mappings</em>' attribute list.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getDeploymentPlan_ProjectMappings()
	 * @model dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	EList<String> getProjectMappings();

	/**
	 * Returns the value of the '<em><b>Chain</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Chain</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chain</em>' reference.
	 * @see #setChain(M2MTrafoChain)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getDeploymentPlan_Chain()
	 * @model ordered="false"
	 * @generated
	 */
	M2MTrafoChain getChain();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan#getChain <em>Chain</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Chain</em>' reference.
	 * @see #getChain()
	 * @generated
	 */
	void setChain(M2MTrafoChain value);

} // DeploymentPlan
