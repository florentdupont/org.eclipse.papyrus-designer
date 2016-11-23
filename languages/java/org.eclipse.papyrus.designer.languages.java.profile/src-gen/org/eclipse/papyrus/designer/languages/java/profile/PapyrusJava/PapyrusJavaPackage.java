/*******************************************************************************
 * Copyright (c) 2006, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *     
 *     
 *******************************************************************************/
/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaFactory
 * @model kind="package"
 * @generated
 */
public interface PapyrusJavaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "PapyrusJava";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/PapyrusJava/1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "PapyrusJava";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PapyrusJavaPackage eINSTANCE = org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ArrayImpl <em>Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ArrayImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getArray()
	 * @generated
	 */
	int ARRAY = 0;

	/**
	 * The feature id for the '<em><b>Base parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__BASE_PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__DEFINITION = 1;

	/**
	 * The feature id for the '<em><b>Base property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__BASE_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Base association</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__BASE_ASSOCIATION = 3;

	/**
	 * The number of structural features of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternalImpl <em>External</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternalImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getExternal()
	 * @generated
	 */
	int EXTERNAL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL__NAME = 0;

	/**
	 * The feature id for the '<em><b>Base Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL__BASE_CLASSIFIER = 1;

	/**
	 * The number of structural features of the '<em>External</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>External</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl <em>Extern Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getExternLibrary()
	 * @generated
	 */
	int EXTERN_LIBRARY = 2;

	/**
	 * The feature id for the '<em><b>Base package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__BASE_PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Import Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__IMPORT_PATHS = 1;

	/**
	 * The feature id for the '<em><b>Lib Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__LIB_PATHS = 2;

	/**
	 * The feature id for the '<em><b>Libs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__LIBS = 3;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__IMPORTS = 4;

	/**
	 * The number of structural features of the '<em>Extern Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Extern Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ImportImpl <em>Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ImportImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getImport()
	 * @generated
	 */
	int IMPORT = 3;

	/**
	 * The feature id for the '<em><b>Manual Imports</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__MANUAL_IMPORTS = 0;

	/**
	 * The feature id for the '<em><b>Base class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__BASE_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Base Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__BASE_CLASSIFIER = 2;

	/**
	 * The number of structural features of the '<em>Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NoCodeGenImpl <em>No Code Gen</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NoCodeGenImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getNoCodeGen()
	 * @generated
	 */
	int NO_CODE_GEN = 4;

	/**
	 * The feature id for the '<em><b>Base element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CODE_GEN__BASE_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>No Code Gen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CODE_GEN_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>No Code Gen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CODE_GEN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateImpl <em>Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTemplate()
	 * @generated
	 */
	int TEMPLATE = 5;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__DECLARATION = 0;

	/**
	 * The feature id for the '<em><b>Base class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__BASE_CLASS = 1;

	/**
	 * The number of structural features of the '<em>Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateBindingImpl <em>Template Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateBindingImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTemplateBinding()
	 * @generated
	 */
	int TEMPLATE_BINDING = 6;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_BINDING__BINDING = 0;

	/**
	 * The feature id for the '<em><b>Base templatebinding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_BINDING__BASE_TEMPLATEBINDING = 1;

	/**
	 * The number of structural features of the '<em>Template Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_BINDING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Template Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_BINDING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateParameterImpl <em>Template Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateParameterImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTemplateParameter()
	 * @generated
	 */
	int TEMPLATE_PARAMETER = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Base templateparameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER = 1;

	/**
	 * The number of structural features of the '<em>Template Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Template Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ManualGenerationImpl <em>Manual Generation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ManualGenerationImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getManualGeneration()
	 * @generated
	 */
	int MANUAL_GENERATION = 8;

	/**
	 * The feature id for the '<em><b>Extension Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_GENERATION__EXTENSION_BODY = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_GENERATION__BASE_CLASS = 1;

	/**
	 * The number of structural features of the '<em>Manual Generation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_GENERATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Manual Generation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_GENERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VariadicImpl <em>Variadic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VariadicImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getVariadic()
	 * @generated
	 */
	int VARIADIC = 9;

	/**
	 * The feature id for the '<em><b>Base Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIADIC__BASE_PARAMETER = 0;

	/**
	 * The number of structural features of the '<em>Variadic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIADIC_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Variadic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIADIC_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VolatileImpl <em>Volatile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VolatileImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getVolatile()
	 * @generated
	 */
	int VOLATILE = 10;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Volatile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Volatile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TransientImpl <em>Transient</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TransientImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTransient()
	 * @generated
	 */
	int TRANSIENT = 11;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSIENT__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Transient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSIENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Transient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSIENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.SynchronizedImpl <em>Synchronized</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.SynchronizedImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getSynchronized()
	 * @generated
	 */
	int SYNCHRONIZED = 12;

	/**
	 * The feature id for the '<em><b>Base Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONIZED__BASE_OPERATION = 0;

	/**
	 * The number of structural features of the '<em>Synchronized</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONIZED_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Synchronized</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONIZED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StrictfpImpl <em>Strictfp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StrictfpImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getStrictfp()
	 * @generated
	 */
	int STRICTFP = 13;

	/**
	 * The feature id for the '<em><b>Base Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRICTFP__BASE_OPERATION = 0;

	/**
	 * The feature id for the '<em><b>Base Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRICTFP__BASE_CLASSIFIER = 1;

	/**
	 * The number of structural features of the '<em>Strictfp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRICTFP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Strictfp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRICTFP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NativeImpl <em>Native</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NativeImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getNative()
	 * @generated
	 */
	int NATIVE = 14;

	/**
	 * The feature id for the '<em><b>Base Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE__BASE_OPERATION = 0;

	/**
	 * The number of structural features of the '<em>Native</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Native</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.DefaultImpl <em>Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.DefaultImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getDefault()
	 * @generated
	 */
	int DEFAULT = 15;

	/**
	 * The feature id for the '<em><b>Base Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__BASE_OPERATION = 0;

	/**
	 * The number of structural features of the '<em>Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StaticClassifierImpl <em>Static Classifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StaticClassifierImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getStaticClassifier()
	 * @generated
	 */
	int STATIC_CLASSIFIER = 16;

	/**
	 * The feature id for the '<em><b>Base Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_CLASSIFIER__BASE_CLASSIFIER = 0;

	/**
	 * The number of structural features of the '<em>Static Classifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_CLASSIFIER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Static Classifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_CLASSIFIER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.FinalImpl <em>Final</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.FinalImpl
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getFinal()
	 * @generated
	 */
	int FINAL = 17;

	/**
	 * The feature id for the '<em><b>Base Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL__BASE_PARAMETER = 0;

	/**
	 * The number of structural features of the '<em>Final</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Final</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array
	 * @generated
	 */
	EClass getArray();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getBase_parameter <em>Base parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getBase_parameter()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Base_parameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getDefinition()
	 * @see #getArray()
	 * @generated
	 */
	EAttribute getArray_Definition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getBase_property <em>Base property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getBase_property()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Base_property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getBase_association <em>Base association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base association</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array#getBase_association()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Base_association();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External <em>External</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External
	 * @generated
	 */
	EClass getExternal();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External#getName()
	 * @see #getExternal()
	 * @generated
	 */
	EAttribute getExternal_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External#getBase_Classifier <em>Base Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External#getBase_Classifier()
	 * @see #getExternal()
	 * @generated
	 */
	EReference getExternal_Base_Classifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary <em>Extern Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extern Library</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary
	 * @generated
	 */
	EClass getExternLibrary();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getBase_package <em>Base package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base package</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getBase_package()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EReference getExternLibrary_Base_package();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getImportPaths <em>Import Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Import Paths</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getImportPaths()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_ImportPaths();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getLibPaths <em>Lib Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Lib Paths</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getLibPaths()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_LibPaths();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getLibs <em>Libs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Libs</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getLibs()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_Libs();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Imports</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getImports()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_Imports();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import
	 * @generated
	 */
	EClass getImport();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import#getManualImports <em>Manual Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Manual Imports</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import#getManualImports()
	 * @see #getImport()
	 * @generated
	 */
	EAttribute getImport_ManualImports();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import#getBase_class <em>Base class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import#getBase_class()
	 * @see #getImport()
	 * @generated
	 */
	EReference getImport_Base_class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import#getBase_Classifier <em>Base Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import#getBase_Classifier()
	 * @see #getImport()
	 * @generated
	 */
	EReference getImport_Base_Classifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen <em>No Code Gen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Code Gen</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen
	 * @generated
	 */
	EClass getNoCodeGen();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen#getBase_element <em>Base element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base element</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen#getBase_element()
	 * @see #getNoCodeGen()
	 * @generated
	 */
	EReference getNoCodeGen_Base_element();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Template
	 * @generated
	 */
	EClass getTemplate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Template#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Declaration</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Template#getDeclaration()
	 * @see #getTemplate()
	 * @generated
	 */
	EAttribute getTemplate_Declaration();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Template#getBase_class <em>Base class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Template#getBase_class()
	 * @see #getTemplate()
	 * @generated
	 */
	EReference getTemplate_Base_class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateBinding <em>Template Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Binding</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateBinding
	 * @generated
	 */
	EClass getTemplateBinding();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateBinding#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateBinding#getBinding()
	 * @see #getTemplateBinding()
	 * @generated
	 */
	EAttribute getTemplateBinding_Binding();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateBinding#getBase_templatebinding <em>Base templatebinding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base templatebinding</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateBinding#getBase_templatebinding()
	 * @see #getTemplateBinding()
	 * @generated
	 */
	EReference getTemplateBinding_Base_templatebinding();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateParameter <em>Template Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateParameter
	 * @generated
	 */
	EClass getTemplateParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateParameter#getName()
	 * @see #getTemplateParameter()
	 * @generated
	 */
	EAttribute getTemplateParameter_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateParameter#getBase_templateparameter <em>Base templateparameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base templateparameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateParameter#getBase_templateparameter()
	 * @see #getTemplateParameter()
	 * @generated
	 */
	EReference getTemplateParameter_Base_templateparameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration <em>Manual Generation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manual Generation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration
	 * @generated
	 */
	EClass getManualGeneration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getExtensionBody <em>Extension Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extension Body</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getExtensionBody()
	 * @see #getManualGeneration()
	 * @generated
	 */
	EAttribute getManualGeneration_ExtensionBody();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration#getBase_Class()
	 * @see #getManualGeneration()
	 * @generated
	 */
	EReference getManualGeneration_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Variadic <em>Variadic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variadic</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Variadic
	 * @generated
	 */
	EClass getVariadic();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Variadic#getBase_Parameter <em>Base Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Variadic#getBase_Parameter()
	 * @see #getVariadic()
	 * @generated
	 */
	EReference getVariadic_Base_Parameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Volatile <em>Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Volatile</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Volatile
	 * @generated
	 */
	EClass getVolatile();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Volatile#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Volatile#getBase_Property()
	 * @see #getVolatile()
	 * @generated
	 */
	EReference getVolatile_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Transient <em>Transient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transient</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Transient
	 * @generated
	 */
	EClass getTransient();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Transient#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Transient#getBase_Property()
	 * @see #getTransient()
	 * @generated
	 */
	EReference getTransient_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Synchronized <em>Synchronized</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synchronized</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Synchronized
	 * @generated
	 */
	EClass getSynchronized();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Synchronized#getBase_Operation <em>Base Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Synchronized#getBase_Operation()
	 * @see #getSynchronized()
	 * @generated
	 */
	EReference getSynchronized_Base_Operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp <em>Strictfp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Strictfp</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp
	 * @generated
	 */
	EClass getStrictfp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp#getBase_Operation <em>Base Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp#getBase_Operation()
	 * @see #getStrictfp()
	 * @generated
	 */
	EReference getStrictfp_Base_Operation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp#getBase_Classifier <em>Base Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp#getBase_Classifier()
	 * @see #getStrictfp()
	 * @generated
	 */
	EReference getStrictfp_Base_Classifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Native <em>Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Native</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Native
	 * @generated
	 */
	EClass getNative();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Native#getBase_Operation <em>Base Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Native#getBase_Operation()
	 * @see #getNative()
	 * @generated
	 */
	EReference getNative_Base_Operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Default <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Default
	 * @generated
	 */
	EClass getDefault();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Default#getBase_Operation <em>Base Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Default#getBase_Operation()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_Base_Operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier <em>Static Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier
	 * @generated
	 */
	EClass getStaticClassifier();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier#getBase_Classifier <em>Base Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier#getBase_Classifier()
	 * @see #getStaticClassifier()
	 * @generated
	 */
	EReference getStaticClassifier_Base_Classifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final
	 * @generated
	 */
	EClass getFinal();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final#getBase_Parameter <em>Base Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final#getBase_Parameter()
	 * @see #getFinal()
	 * @generated
	 */
	EReference getFinal_Base_Parameter();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PapyrusJavaFactory getPapyrusJavaFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ArrayImpl <em>Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ArrayImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getArray()
		 * @generated
		 */
		EClass ARRAY = eINSTANCE.getArray();

		/**
		 * The meta object literal for the '<em><b>Base parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY__BASE_PARAMETER = eINSTANCE.getArray_Base_parameter();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY__DEFINITION = eINSTANCE.getArray_Definition();

		/**
		 * The meta object literal for the '<em><b>Base property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY__BASE_PROPERTY = eINSTANCE.getArray_Base_property();

		/**
		 * The meta object literal for the '<em><b>Base association</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY__BASE_ASSOCIATION = eINSTANCE.getArray_Base_association();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternalImpl <em>External</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternalImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getExternal()
		 * @generated
		 */
		EClass EXTERNAL = eINSTANCE.getExternal();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL__NAME = eINSTANCE.getExternal_Name();

		/**
		 * The meta object literal for the '<em><b>Base Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL__BASE_CLASSIFIER = eINSTANCE.getExternal_Base_Classifier();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl <em>Extern Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getExternLibrary()
		 * @generated
		 */
		EClass EXTERN_LIBRARY = eINSTANCE.getExternLibrary();

		/**
		 * The meta object literal for the '<em><b>Base package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERN_LIBRARY__BASE_PACKAGE = eINSTANCE.getExternLibrary_Base_package();

		/**
		 * The meta object literal for the '<em><b>Import Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__IMPORT_PATHS = eINSTANCE.getExternLibrary_ImportPaths();

		/**
		 * The meta object literal for the '<em><b>Lib Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__LIB_PATHS = eINSTANCE.getExternLibrary_LibPaths();

		/**
		 * The meta object literal for the '<em><b>Libs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__LIBS = eINSTANCE.getExternLibrary_Libs();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__IMPORTS = eINSTANCE.getExternLibrary_Imports();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ImportImpl <em>Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ImportImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getImport()
		 * @generated
		 */
		EClass IMPORT = eINSTANCE.getImport();

		/**
		 * The meta object literal for the '<em><b>Manual Imports</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT__MANUAL_IMPORTS = eINSTANCE.getImport_ManualImports();

		/**
		 * The meta object literal for the '<em><b>Base class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT__BASE_CLASS = eINSTANCE.getImport_Base_class();

		/**
		 * The meta object literal for the '<em><b>Base Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT__BASE_CLASSIFIER = eINSTANCE.getImport_Base_Classifier();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NoCodeGenImpl <em>No Code Gen</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NoCodeGenImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getNoCodeGen()
		 * @generated
		 */
		EClass NO_CODE_GEN = eINSTANCE.getNoCodeGen();

		/**
		 * The meta object literal for the '<em><b>Base element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NO_CODE_GEN__BASE_ELEMENT = eINSTANCE.getNoCodeGen_Base_element();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateImpl <em>Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTemplate()
		 * @generated
		 */
		EClass TEMPLATE = eINSTANCE.getTemplate();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE__DECLARATION = eINSTANCE.getTemplate_Declaration();

		/**
		 * The meta object literal for the '<em><b>Base class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE__BASE_CLASS = eINSTANCE.getTemplate_Base_class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateBindingImpl <em>Template Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateBindingImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTemplateBinding()
		 * @generated
		 */
		EClass TEMPLATE_BINDING = eINSTANCE.getTemplateBinding();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE_BINDING__BINDING = eINSTANCE.getTemplateBinding_Binding();

		/**
		 * The meta object literal for the '<em><b>Base templatebinding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_BINDING__BASE_TEMPLATEBINDING = eINSTANCE.getTemplateBinding_Base_templatebinding();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateParameterImpl <em>Template Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateParameterImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTemplateParameter()
		 * @generated
		 */
		EClass TEMPLATE_PARAMETER = eINSTANCE.getTemplateParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE_PARAMETER__NAME = eINSTANCE.getTemplateParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Base templateparameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER = eINSTANCE.getTemplateParameter_Base_templateparameter();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ManualGenerationImpl <em>Manual Generation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ManualGenerationImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getManualGeneration()
		 * @generated
		 */
		EClass MANUAL_GENERATION = eINSTANCE.getManualGeneration();

		/**
		 * The meta object literal for the '<em><b>Extension Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANUAL_GENERATION__EXTENSION_BODY = eINSTANCE.getManualGeneration_ExtensionBody();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANUAL_GENERATION__BASE_CLASS = eINSTANCE.getManualGeneration_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VariadicImpl <em>Variadic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VariadicImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getVariadic()
		 * @generated
		 */
		EClass VARIADIC = eINSTANCE.getVariadic();

		/**
		 * The meta object literal for the '<em><b>Base Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIADIC__BASE_PARAMETER = eINSTANCE.getVariadic_Base_Parameter();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VolatileImpl <em>Volatile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.VolatileImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getVolatile()
		 * @generated
		 */
		EClass VOLATILE = eINSTANCE.getVolatile();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VOLATILE__BASE_PROPERTY = eINSTANCE.getVolatile_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TransientImpl <em>Transient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TransientImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getTransient()
		 * @generated
		 */
		EClass TRANSIENT = eINSTANCE.getTransient();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSIENT__BASE_PROPERTY = eINSTANCE.getTransient_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.SynchronizedImpl <em>Synchronized</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.SynchronizedImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getSynchronized()
		 * @generated
		 */
		EClass SYNCHRONIZED = eINSTANCE.getSynchronized();

		/**
		 * The meta object literal for the '<em><b>Base Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNCHRONIZED__BASE_OPERATION = eINSTANCE.getSynchronized_Base_Operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StrictfpImpl <em>Strictfp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StrictfpImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getStrictfp()
		 * @generated
		 */
		EClass STRICTFP = eINSTANCE.getStrictfp();

		/**
		 * The meta object literal for the '<em><b>Base Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRICTFP__BASE_OPERATION = eINSTANCE.getStrictfp_Base_Operation();

		/**
		 * The meta object literal for the '<em><b>Base Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRICTFP__BASE_CLASSIFIER = eINSTANCE.getStrictfp_Base_Classifier();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NativeImpl <em>Native</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.NativeImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getNative()
		 * @generated
		 */
		EClass NATIVE = eINSTANCE.getNative();

		/**
		 * The meta object literal for the '<em><b>Base Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NATIVE__BASE_OPERATION = eINSTANCE.getNative_Base_Operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.DefaultImpl <em>Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.DefaultImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getDefault()
		 * @generated
		 */
		EClass DEFAULT = eINSTANCE.getDefault();

		/**
		 * The meta object literal for the '<em><b>Base Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__BASE_OPERATION = eINSTANCE.getDefault_Base_Operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StaticClassifierImpl <em>Static Classifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.StaticClassifierImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getStaticClassifier()
		 * @generated
		 */
		EClass STATIC_CLASSIFIER = eINSTANCE.getStaticClassifier();

		/**
		 * The meta object literal for the '<em><b>Base Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATIC_CLASSIFIER__BASE_CLASSIFIER = eINSTANCE.getStaticClassifier_Base_Classifier();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.FinalImpl <em>Final</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.FinalImpl
		 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaPackageImpl#getFinal()
		 * @generated
		 */
		EClass FINAL = eINSTANCE.getFinal();

		/**
		 * The meta object literal for the '<em><b>Base Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FINAL__BASE_PARAMETER = eINSTANCE.getFinal_Base_Parameter();

	}

} //PapyrusJavaPackage
