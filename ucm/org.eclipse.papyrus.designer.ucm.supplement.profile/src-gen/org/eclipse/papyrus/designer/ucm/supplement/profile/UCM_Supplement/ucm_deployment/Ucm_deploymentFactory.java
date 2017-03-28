/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentPackage
 * @generated
 */
public interface Ucm_deploymentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_deploymentFactory eINSTANCE = org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.Ucm_deploymentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>App Assembly</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>App Assembly</em>'.
	 * @generated
	 */
	AppAssembly createAppAssembly();

	/**
	 * Returns a new object of class '<em>Deployment Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Module</em>'.
	 * @generated
	 */
	DeploymentModule createDeploymentModule();

	/**
	 * Returns a new object of class '<em>Allocation Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Allocation Plan</em>'.
	 * @generated
	 */
	AllocationPlan createAllocationPlan();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Ucm_deploymentPackage getUcm_deploymentPackage();

} //Ucm_deploymentFactory
