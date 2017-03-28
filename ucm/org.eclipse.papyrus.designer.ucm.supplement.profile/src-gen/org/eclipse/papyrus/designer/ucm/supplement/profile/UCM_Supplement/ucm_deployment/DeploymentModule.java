/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A DeploymentModule contains a unique AppAssembly
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getProgrammingLanguages <em>Programming Languages</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getBase_Package <em>Base Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentPackage#getDeploymentModule()
 * @model
 * @generated
 */
public interface DeploymentModule extends EObject {
	/**
	 * Returns the value of the '<em><b>Programming Languages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Programming Languages</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Programming Languages</em>' containment reference.
	 * @see #setProgrammingLanguages(ProgrammingLanguages)
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentPackage#getDeploymentModule_ProgrammingLanguages()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	ProgrammingLanguages getProgrammingLanguages();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getProgrammingLanguages <em>Programming Languages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Programming Languages</em>' containment reference.
	 * @see #getProgrammingLanguages()
	 * @generated
	 */
	void setProgrammingLanguages(ProgrammingLanguages value);

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
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentPackage#getDeploymentModule_Base_Package()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Package getBase_Package();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule#getBase_Package <em>Base Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Package</em>' reference.
	 * @see #getBase_Package()
	 * @generated
	 */
	void setBase_Package(org.eclipse.uml2.uml.Package value);

} // DeploymentModule
