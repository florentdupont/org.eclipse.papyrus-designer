/*****************************************************************************
 * Copyright (c) 2014 Jonathan Geoffroy
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Jonathan Geoffroy  geoffroy.jonathan@gmail.com  - Meta-model conception
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmFactory;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmPackage;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.Options;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class GeneratoroptionsmmFactoryImpl extends EFactoryImpl implements GeneratoroptionsmmFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static GeneratoroptionsmmFactory init() {
		try {
			GeneratoroptionsmmFactory theGeneratoroptionsmmFactory = (GeneratoroptionsmmFactory) EPackage.Registry.INSTANCE.getEFactory(GeneratoroptionsmmPackage.eNS_URI);
			if (theGeneratoroptionsmmFactory != null) {
				return theGeneratoroptionsmmFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GeneratoroptionsmmFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public GeneratoroptionsmmFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case GeneratoroptionsmmPackage.OPTIONS:
			return createOptions();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Options createOptions() {
		OptionsImpl options = new OptionsImpl();
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public GeneratoroptionsmmPackage getGeneratoroptionsmmPackage() {
		return (GeneratoroptionsmmPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GeneratoroptionsmmPackage getPackage() {
		return GeneratoroptionsmmPackage.eINSTANCE;
	}

} // GeneratoroptionsmmFactoryImpl
