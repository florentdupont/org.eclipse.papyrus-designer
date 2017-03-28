/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_environment.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_environmentFactoryImpl extends EFactoryImpl implements Ucm_environmentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_environmentFactory init() {
		try {
			Ucm_environmentFactory theUcm_environmentFactory = (Ucm_environmentFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_environmentPackage.eNS_URI);
			if (theUcm_environmentFactory != null) {
				return theUcm_environmentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_environmentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_environmentFactoryImpl() {
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
			case Ucm_environmentPackage.ENVIRONMENT_MODULE: return createEnvironmentModule();
			case Ucm_environmentPackage.RESOURCE_DEFINITION_MODULE: return createResourceDefinitionModule();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentModule createEnvironmentModule() {
		EnvironmentModuleImpl environmentModule = new EnvironmentModuleImpl();
		return environmentModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceDefinitionModule createResourceDefinitionModule() {
		ResourceDefinitionModuleImpl resourceDefinitionModule = new ResourceDefinitionModuleImpl();
		return resourceDefinitionModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_environmentPackage getUcm_environmentPackage() {
		return (Ucm_environmentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_environmentPackage getPackage() {
		return Ucm_environmentPackage.eINSTANCE;
	}

} //Ucm_environmentFactoryImpl
