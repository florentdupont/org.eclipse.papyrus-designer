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
package org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/uml2/2.0.0/UML originalName='GeneratorOptionsMM'"
 * @generated
 */
public interface GeneratoroptionsmmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNAME = "generatoroptionsmm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNS_URI = "http:///GeneratorOptionsMM.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	String eNS_PREFIX = "GeneratorOptionsMM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	GeneratoroptionsmmPackage eINSTANCE = org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.GeneratoroptionsmmPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.OptionsImpl <em>Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.OptionsImpl
	 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.GeneratoroptionsmmPackageImpl#getOptions()
	 * @generated
	 */
	int OPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Default Source Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPTIONS__DEFAULT_SOURCE_FOLDER = 0;

	/**
	 * The number of structural features of the '<em>Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPTIONS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 * @ordered
	 */
	int OPTIONS_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.Options <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for class '<em>Options</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.Options
	 * @generated
	 */
	EClass getOptions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.Options#getDefaultSourceFolder <em>Default Source Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the meta object for the attribute '<em>Default Source Folder</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.Options#getDefaultSourceFolder()
	 * @see #getOptions()
	 * @generated
	 */
	EAttribute getOptions_DefaultSourceFolder();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GeneratoroptionsmmFactory getGeneratoroptionsmmFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.OptionsImpl <em>Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.OptionsImpl
		 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.GeneratoroptionsmmPackageImpl#getOptions()
		 * @generated
		 */
		EClass OPTIONS = eINSTANCE.getOptions();

		/**
		 * The meta object literal for the '<em><b>Default Source Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 *
		 * @generated
		 */
		EAttribute OPTIONS__DEFAULT_SOURCE_FOLDER = eINSTANCE.getOptions_DefaultSourceFolder();

	}

} // GeneratoroptionsmmPackage
