/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UCM_SupplementFactoryImpl extends EFactoryImpl implements UCM_SupplementFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UCM_SupplementFactory init() {
		try {
			UCM_SupplementFactory theUCM_SupplementFactory = (UCM_SupplementFactory)EPackage.Registry.INSTANCE.getEFactory(UCM_SupplementPackage.eNS_URI);
			if (theUCM_SupplementFactory != null) {
				return theUCM_SupplementFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UCM_SupplementFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UCM_SupplementFactoryImpl() {
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
			case UCM_SupplementPackage.DUMMY: return createdummy();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public dummy createdummy() {
		dummyImpl dummy = new dummyImpl();
		return dummy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UCM_SupplementPackage getUCM_SupplementPackage() {
		return (UCM_SupplementPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UCM_SupplementPackage getPackage() {
		return UCM_SupplementPackage.eINSTANCE;
	}

} //UCM_SupplementFactoryImpl
