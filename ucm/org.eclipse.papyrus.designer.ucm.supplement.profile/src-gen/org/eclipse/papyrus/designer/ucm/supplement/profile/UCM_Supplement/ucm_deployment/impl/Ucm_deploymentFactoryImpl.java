/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_deploymentFactoryImpl extends EFactoryImpl implements Ucm_deploymentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_deploymentFactory init() {
		try {
			Ucm_deploymentFactory theUcm_deploymentFactory = (Ucm_deploymentFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_deploymentPackage.eNS_URI);
			if (theUcm_deploymentFactory != null) {
				return theUcm_deploymentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_deploymentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_deploymentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Ucm_deploymentPackage.APP_ASSEMBLY: return createAppAssembly();
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE: return createDeploymentModule();
			case Ucm_deploymentPackage.ALLOCATION_PLAN: return createAllocationPlan();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AppAssembly createAppAssembly() {
		AppAssemblyImpl appAssembly = new AppAssemblyImpl();
		return appAssembly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentModule createDeploymentModule() {
		DeploymentModuleImpl deploymentModule = new DeploymentModuleImpl();
		return deploymentModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllocationPlan createAllocationPlan() {
		AllocationPlanImpl allocationPlan = new AllocationPlanImpl();
		return allocationPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_deploymentPackage getUcm_deploymentPackage() {
		return (Ucm_deploymentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_deploymentPackage getPackage() {
		return Ucm_deploymentPackage.eINSTANCE;
	}

} //Ucm_deploymentFactoryImpl
