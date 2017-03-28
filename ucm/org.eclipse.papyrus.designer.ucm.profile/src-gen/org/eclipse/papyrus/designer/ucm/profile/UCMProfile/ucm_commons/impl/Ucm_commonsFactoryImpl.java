/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_commonsFactoryImpl extends EFactoryImpl implements Ucm_commonsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_commonsFactory init() {
		try {
			Ucm_commonsFactory theUcm_commonsFactory = (Ucm_commonsFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_commonsPackage.eNS_URI);
			if (theUcm_commonsFactory != null) {
				return theUcm_commonsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_commonsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_commonsFactoryImpl() {
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
			case Ucm_commonsPackage.APPLICATION_MODULE: return createApplicationModule();
			case Ucm_commonsPackage.PLATFORM_MODULE: return createPlatformModule();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationModule createApplicationModule() {
		ApplicationModuleImpl applicationModule = new ApplicationModuleImpl();
		return applicationModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformModule createPlatformModule() {
		PlatformModuleImpl platformModule = new PlatformModuleImpl();
		return platformModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_commonsPackage getUcm_commonsPackage() {
		return (Ucm_commonsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_commonsPackage getPackage() {
		return Ucm_commonsPackage.eINSTANCE;
	}

} //Ucm_commonsFactoryImpl
