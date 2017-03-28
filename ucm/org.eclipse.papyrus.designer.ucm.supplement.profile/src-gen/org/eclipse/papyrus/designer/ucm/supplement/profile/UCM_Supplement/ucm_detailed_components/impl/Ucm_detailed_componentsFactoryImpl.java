/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ucm_detailed_componentsFactoryImpl extends EFactoryImpl implements Ucm_detailed_componentsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ucm_detailed_componentsFactory init() {
		try {
			Ucm_detailed_componentsFactory theUcm_detailed_componentsFactory = (Ucm_detailed_componentsFactory)EPackage.Registry.INSTANCE.getEFactory(Ucm_detailed_componentsPackage.eNS_URI);
			if (theUcm_detailed_componentsFactory != null) {
				return theUcm_detailed_componentsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ucm_detailed_componentsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_detailed_componentsFactoryImpl() {
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
			case Ucm_detailed_componentsPackage.DETAILED_COMPONENT_IMPLEMENTATION: return createDetailedComponentImplementation();
			case Ucm_detailed_componentsPackage.COMPONENT_PORT_IMPLEMENTATION: return createComponentPortImplementation();
			case Ucm_detailed_componentsPackage.TECHNICAL_PORT_IMPLEMENTATION: return createTechnicalPortImplementation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailedComponentImplementation createDetailedComponentImplementation() {
		DetailedComponentImplementationImpl detailedComponentImplementation = new DetailedComponentImplementationImpl();
		return detailedComponentImplementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPortImplementation createComponentPortImplementation() {
		ComponentPortImplementationImpl componentPortImplementation = new ComponentPortImplementationImpl();
		return componentPortImplementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TechnicalPortImplementation createTechnicalPortImplementation() {
		TechnicalPortImplementationImpl technicalPortImplementation = new TechnicalPortImplementationImpl();
		return technicalPortImplementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_detailed_componentsPackage getUcm_detailed_componentsPackage() {
		return (Ucm_detailed_componentsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ucm_detailed_componentsPackage getPackage() {
		return Ucm_detailed_componentsPackage.eINSTANCE;
	}

} //Ucm_detailed_componentsFactoryImpl
