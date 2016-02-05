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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmFactory;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmPackage;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.Options;
import org.eclipse.uml2.types.TypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class GeneratoroptionsmmPackageImpl extends EPackageImpl implements GeneratoroptionsmmPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	private EClass optionsEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GeneratoroptionsmmPackageImpl() {
		super(eNS_URI, GeneratoroptionsmmFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>
	 * This method is used to initialize {@link GeneratoroptionsmmPackage#eINSTANCE} when that field is accessed. Clients should not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GeneratoroptionsmmPackage init() {
		if (isInited) {
			return (GeneratoroptionsmmPackage) EPackage.Registry.INSTANCE.getEPackage(GeneratoroptionsmmPackage.eNS_URI);
		}

		// Obtain or create and register package
		GeneratoroptionsmmPackageImpl theGeneratoroptionsmmPackage = (GeneratoroptionsmmPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GeneratoroptionsmmPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new GeneratoroptionsmmPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theGeneratoroptionsmmPackage.createPackageContents();

		// Initialize created meta-data
		theGeneratoroptionsmmPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGeneratoroptionsmmPackage.freeze();


		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GeneratoroptionsmmPackage.eNS_URI, theGeneratoroptionsmmPackage);
		return theGeneratoroptionsmmPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EClass getOptions() {
		return optionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EAttribute getOptions_DefaultSourceFolder() {
		return (EAttribute) optionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public GeneratoroptionsmmFactory getGeneratoroptionsmmFactory() {
		return (GeneratoroptionsmmFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) {
			return;
		}
		isCreated = true;

		// Create classes and their features
		optionsEClass = createEClass(OPTIONS);
		createEAttribute(optionsEClass, OPTIONS__DEFAULT_SOURCE_FOLDER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) {
			return;
		}
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		TypesPackage theTypesPackage = (TypesPackage) EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(optionsEClass, Options.class, "Options", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOptions_DefaultSourceFolder(), theTypesPackage.getString(), "defaultSourceFolder", null, 1, 1, Options.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/uml2/2.0.0/UML
		createUMLAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/uml2/2.0.0/UML</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected void createUMLAnnotations() {
		String source = "http://www.eclipse.org/uml2/2.0.0/UML";
		addAnnotation(this,
				source,
				new String[] {
						"originalName", "GeneratorOptionsMM"
				});
	}

} // GeneratoroptionsmmPackageImpl
