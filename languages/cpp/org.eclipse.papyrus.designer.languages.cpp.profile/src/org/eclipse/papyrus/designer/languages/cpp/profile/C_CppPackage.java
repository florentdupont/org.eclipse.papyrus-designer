/**
 * Copyright (c) 2013 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CEA LIST - Initial API and implementation
 *
 */
package org.eclipse.papyrus.designer.languages.cpp.profile;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.languages.cpp.profile.C_CppFactory
 * @model kind="package"
 * @generated
 */
public interface C_CppPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "C_Cpp"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/C_Cpp/1"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "C_Cpp"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	C_CppPackage eINSTANCE = org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.PtrImpl <em>Ptr</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.PtrImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getPtr()
	 * @generated
	 */
	int PTR = 0;

	/**
	 * The feature id for the '<em><b>Base parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTR__BASE_PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>Base property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTR__BASE_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTR__DECLARATION = 2;

	/**
	 * The number of structural features of the '<em>Ptr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTR_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Ptr</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PTR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.IncludeImpl <em>Include</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.IncludeImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getInclude()
	 * @generated
	 */
	int INCLUDE = 1;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE__BODY = 0;

	/**
	 * The feature id for the '<em><b>Pre Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE__PRE_BODY = 1;

	/**
	 * The feature id for the '<em><b>Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE__HEADER = 2;

	/**
	 * The feature id for the '<em><b>Base package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE__BASE_PACKAGE = 3;

	/**
	 * The feature id for the '<em><b>Base class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE__BASE_CLASS = 4;

	/**
	 * The feature id for the '<em><b>Base Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE__BASE_CLASSIFIER = 5;

	/**
	 * The number of structural features of the '<em>Include</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Include</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ManualGenerationImpl <em>Manual Generation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ManualGenerationImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getManualGeneration()
	 * @generated
	 */
	int MANUAL_GENERATION = 2;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternLibraryImpl <em>Extern Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternLibraryImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getExternLibrary()
	 * @generated
	 */
	int EXTERN_LIBRARY = 3;

	/**
	 * The feature id for the '<em><b>Include Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__INCLUDE_PATHS = 0;

	/**
	 * The feature id for the '<em><b>Base package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__BASE_PACKAGE = 1;

	/**
	 * The feature id for the '<em><b>Lib Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__LIB_PATHS = 2;

	/**
	 * The feature id for the '<em><b>Macros</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__MACROS = 3;

	/**
	 * The feature id for the '<em><b>Libs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__LIBS = 4;

	/**
	 * The feature id for the '<em><b>Includes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY__INCLUDES = 5;

	/**
	 * The number of structural features of the '<em>Extern Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Extern Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERN_LIBRARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.NoCodeGenImpl <em>No Code Gen</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.NoCodeGenImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getNoCodeGen()
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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppRootImpl <em>Cpp Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppRootImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getCppRoot()
	 * @generated
	 */
	int CPP_ROOT = 5;

	/**
	 * The feature id for the '<em><b>Base package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPP_ROOT__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Cpp Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPP_ROOT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Cpp Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPP_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ArrayImpl <em>Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ArrayImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getArray()
	 * @generated
	 */
	int ARRAY = 6;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstImpl <em>Const</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getConst()
	 * @generated
	 */
	int CONST = 7;

	/**
	 * The feature id for the '<em><b>Base parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST__BASE_PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>Base property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST__BASE_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST__BASE_OPERATION = 2;

	/**
	 * The number of structural features of the '<em>Const</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Const</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.RefImpl <em>Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.RefImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getRef()
	 * @generated
	 */
	int REF = 8;

	/**
	 * The feature id for the '<em><b>Base parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF__BASE_PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>Base property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF__BASE_PROPERTY = 1;

	/**
	 * The number of structural features of the '<em>Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternalImpl <em>External</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternalImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getExternal()
	 * @generated
	 */
	int EXTERNAL = 9;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstInitImpl <em>Const Init</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstInitImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getConstInit()
	 * @generated
	 */
	int CONST_INIT = 10;

	/**
	 * The feature id for the '<em><b>Initialisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_INIT__INITIALISATION = 0;

	/**
	 * The feature id for the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_INIT__BASE_OPERATION = 1;

	/**
	 * The number of structural features of the '<em>Const Init</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_INIT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Const Init</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_INIT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.FriendImpl <em>Friend</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.FriendImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getFriend()
	 * @generated
	 */
	int FRIEND = 11;

	/**
	 * The feature id for the '<em><b>Base dependency</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRIEND__BASE_DEPENDENCY = 0;

	/**
	 * The feature id for the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRIEND__BASE_OPERATION = 1;

	/**
	 * The number of structural features of the '<em>Friend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRIEND_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Friend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRIEND_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.InlineImpl <em>Inline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.InlineImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getInline()
	 * @generated
	 */
	int INLINE = 12;

	/**
	 * The feature id for the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INLINE__BASE_OPERATION = 0;

	/**
	 * The number of structural features of the '<em>Inline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INLINE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Inline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INLINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VirtualImpl <em>Virtual</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VirtualImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVirtual()
	 * @generated
	 */
	int VIRTUAL = 13;

	/**
	 * The feature id for the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL__BASE_OPERATION = 0;

	/**
	 * The number of structural features of the '<em>Virtual</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Virtual</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIRTUAL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TypedefImpl <em>Typedef</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TypedefImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTypedef()
	 * @generated
	 */
	int TYPEDEF = 14;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPEDEF__DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Base primitivetype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPEDEF__BASE_PRIMITIVETYPE = 1;

	/**
	 * The number of structural features of the '<em>Typedef</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPEDEF_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Typedef</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPEDEF_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VisibilityImpl <em>Visibility</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VisibilityImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVisibility()
	 * @generated
	 */
	int VISIBILITY = 15;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Base generalization</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY__BASE_GENERALIZATION = 1;

	/**
	 * The number of structural features of the '<em>Visibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Visibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBILITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppInitImpl <em>Cpp Init</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppInitImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getCppInit()
	 * @generated
	 */
	int CPP_INIT = 16;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPP_INIT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Base enumerationliteral</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPP_INIT__BASE_ENUMERATIONLITERAL = 1;

	/**
	 * The number of structural features of the '<em>Cpp Init</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPP_INIT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Cpp Init</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPP_INIT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateImpl <em>Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTemplate()
	 * @generated
	 */
	int TEMPLATE = 17;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateBindingImpl <em>Template Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateBindingImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTemplateBinding()
	 * @generated
	 */
	int TEMPLATE_BINDING = 18;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateParameterImpl <em>Template Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateParameterImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTemplateParameter()
	 * @generated
	 */
	int TEMPLATE_PARAMETER = 19;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.UnionImpl <em>Union</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.UnionImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getUnion()
	 * @generated
	 */
	int UNION = 20;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__BASE_DATA_TYPE = 0;

	/**
	 * The number of structural features of the '<em>Union</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Union</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.StorageClassImpl <em>Storage Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.StorageClassImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getStorageClass()
	 * @generated
	 */
	int STORAGE_CLASS = 21;

	/**
	 * The feature id for the '<em><b>Storage Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORAGE_CLASS__STORAGE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORAGE_CLASS__BASE_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Base Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORAGE_CLASS__BASE_PARAMETER = 2;

	/**
	 * The number of structural features of the '<em>Storage Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORAGE_CLASS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Storage Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORAGE_CLASS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VolatileImpl <em>Volatile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VolatileImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVolatile()
	 * @generated
	 */
	int VOLATILE = 22;

	/**
	 * The feature id for the '<em><b>Base parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE__BASE_PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>Base property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE__BASE_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE__BASE_OPERATION = 2;

	/**
	 * The number of structural features of the '<em>Volatile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Volatile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOLATILE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VariadicImpl <em>Variadic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VariadicImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVariadic()
	 * @generated
	 */
	int VARIADIC = 23;

	/**
	 * The feature id for the '<em><b>Base operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIADIC__BASE_OPERATION = 0;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.MutableImpl <em>Mutable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.MutableImpl
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getMutable()
	 * @generated
	 */
	int MUTABLE = 24;

	/**
	 * The feature id for the '<em><b>Base property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTABLE__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Mutable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTABLE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Mutable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MUTABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.VisibilityKind <em>Visibility Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.VisibilityKind
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVisibilityKind()
	 * @generated
	 */
	int VISIBILITY_KIND = 25;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.EAccessKind <em>EAccess Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.EAccessKind
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getEAccessKind()
	 * @generated
	 */
	int EACCESS_KIND = 26;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.EStorageClass <em>EStorage Class</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.EStorageClass
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getEStorageClass()
	 * @generated
	 */
	int ESTORAGE_CLASS = 27;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Ptr <em>Ptr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ptr</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Ptr
	 * @generated
	 */
	EClass getPtr();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Ptr#getBase_parameter <em>Base parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Ptr#getBase_parameter()
	 * @see #getPtr()
	 * @generated
	 */
	EReference getPtr_Base_parameter();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Ptr#getBase_property <em>Base property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Ptr#getBase_property()
	 * @see #getPtr()
	 * @generated
	 */
	EReference getPtr_Base_property();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Ptr#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Declaration</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Ptr#getDeclaration()
	 * @see #getPtr()
	 * @generated
	 */
	EAttribute getPtr_Declaration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Include <em>Include</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Include</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Include
	 * @generated
	 */
	EClass getInclude();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBody()
	 * @see #getInclude()
	 * @generated
	 */
	EAttribute getInclude_Body();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Include#getPreBody <em>Pre Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Body</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Include#getPreBody()
	 * @see #getInclude()
	 * @generated
	 */
	EAttribute getInclude_PreBody();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Include#getHeader <em>Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Header</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Include#getHeader()
	 * @see #getInclude()
	 * @generated
	 */
	EAttribute getInclude_Header();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBase_package <em>Base package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base package</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBase_package()
	 * @see #getInclude()
	 * @generated
	 */
	EReference getInclude_Base_package();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBase_class <em>Base class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBase_class()
	 * @see #getInclude()
	 * @generated
	 */
	EReference getInclude_Base_class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBase_Classifier <em>Base Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Include#getBase_Classifier()
	 * @see #getInclude()
	 * @generated
	 */
	EReference getInclude_Base_Classifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ManualGeneration <em>Manual Generation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manual Generation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ManualGeneration
	 * @generated
	 */
	EClass getManualGeneration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ManualGeneration#getExtensionBody <em>Extension Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extension Body</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ManualGeneration#getExtensionBody()
	 * @see #getManualGeneration()
	 * @generated
	 */
	EAttribute getManualGeneration_ExtensionBody();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ManualGeneration#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ManualGeneration#getBase_Class()
	 * @see #getManualGeneration()
	 * @generated
	 */
	EReference getManualGeneration_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary <em>Extern Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extern Library</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary
	 * @generated
	 */
	EClass getExternLibrary();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getIncludePaths <em>Include Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Include Paths</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getIncludePaths()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_IncludePaths();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getBase_package <em>Base package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base package</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getBase_package()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EReference getExternLibrary_Base_package();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getLibPaths <em>Lib Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Lib Paths</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getLibPaths()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_LibPaths();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getMacros <em>Macros</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Macros</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getMacros()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_Macros();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getLibs <em>Libs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Libs</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getLibs()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_Libs();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getIncludes <em>Includes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Includes</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ExternLibrary#getIncludes()
	 * @see #getExternLibrary()
	 * @generated
	 */
	EAttribute getExternLibrary_Includes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.NoCodeGen <em>No Code Gen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Code Gen</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.NoCodeGen
	 * @generated
	 */
	EClass getNoCodeGen();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.NoCodeGen#getBase_element <em>Base element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base element</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.NoCodeGen#getBase_element()
	 * @see #getNoCodeGen()
	 * @generated
	 */
	EReference getNoCodeGen_Base_element();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.CppRoot <em>Cpp Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cpp Root</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.CppRoot
	 * @generated
	 */
	EClass getCppRoot();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.CppRoot#getBase_package <em>Base package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base package</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.CppRoot#getBase_package()
	 * @see #getCppRoot()
	 * @generated
	 */
	EReference getCppRoot_Base_package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Array <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Array
	 * @generated
	 */
	EClass getArray();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Array#getBase_parameter <em>Base parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Array#getBase_parameter()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Base_parameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Array#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Array#getDefinition()
	 * @see #getArray()
	 * @generated
	 */
	EAttribute getArray_Definition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Array#getBase_property <em>Base property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Array#getBase_property()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Base_property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Array#getBase_association <em>Base association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base association</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Array#getBase_association()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Base_association();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Const <em>Const</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Const</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Const
	 * @generated
	 */
	EClass getConst();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Const#getBase_parameter <em>Base parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Const#getBase_parameter()
	 * @see #getConst()
	 * @generated
	 */
	EReference getConst_Base_parameter();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Const#getBase_property <em>Base property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Const#getBase_property()
	 * @see #getConst()
	 * @generated
	 */
	EReference getConst_Base_property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Const#getBase_operation <em>Base operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Const#getBase_operation()
	 * @see #getConst()
	 * @generated
	 */
	EReference getConst_Base_operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Ref <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ref</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Ref
	 * @generated
	 */
	EClass getRef();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Ref#getBase_parameter <em>Base parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Ref#getBase_parameter()
	 * @see #getRef()
	 * @generated
	 */
	EReference getRef_Base_parameter();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Ref#getBase_property <em>Base property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Ref#getBase_property()
	 * @see #getRef()
	 * @generated
	 */
	EReference getRef_Base_property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.External <em>External</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.External
	 * @generated
	 */
	EClass getExternal();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.External#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.External#getName()
	 * @see #getExternal()
	 * @generated
	 */
	EAttribute getExternal_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.External#getBase_Classifier <em>Base Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Classifier</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.External#getBase_Classifier()
	 * @see #getExternal()
	 * @generated
	 */
	EReference getExternal_Base_Classifier();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ConstInit <em>Const Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Const Init</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ConstInit
	 * @generated
	 */
	EClass getConstInit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ConstInit#getInitialisation <em>Initialisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initialisation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ConstInit#getInitialisation()
	 * @see #getConstInit()
	 * @generated
	 */
	EAttribute getConstInit_Initialisation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.ConstInit#getBase_operation <em>Base operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.ConstInit#getBase_operation()
	 * @see #getConstInit()
	 * @generated
	 */
	EReference getConstInit_Base_operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Friend <em>Friend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Friend</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Friend
	 * @generated
	 */
	EClass getFriend();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Friend#getBase_dependency <em>Base dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base dependency</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Friend#getBase_dependency()
	 * @see #getFriend()
	 * @generated
	 */
	EReference getFriend_Base_dependency();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Friend#getBase_operation <em>Base operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Friend#getBase_operation()
	 * @see #getFriend()
	 * @generated
	 */
	EReference getFriend_Base_operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Inline <em>Inline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inline</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Inline
	 * @generated
	 */
	EClass getInline();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Inline#getBase_operation <em>Base operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Inline#getBase_operation()
	 * @see #getInline()
	 * @generated
	 */
	EReference getInline_Base_operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Virtual <em>Virtual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Virtual</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Virtual
	 * @generated
	 */
	EClass getVirtual();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Virtual#getBase_operation <em>Base operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Virtual#getBase_operation()
	 * @see #getVirtual()
	 * @generated
	 */
	EReference getVirtual_Base_operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Typedef <em>Typedef</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typedef</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Typedef
	 * @generated
	 */
	EClass getTypedef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Typedef#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Definition</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Typedef#getDefinition()
	 * @see #getTypedef()
	 * @generated
	 */
	EAttribute getTypedef_Definition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Typedef#getBase_primitivetype <em>Base primitivetype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base primitivetype</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Typedef#getBase_primitivetype()
	 * @see #getTypedef()
	 * @generated
	 */
	EReference getTypedef_Base_primitivetype();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Visibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visibility</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Visibility
	 * @generated
	 */
	EClass getVisibility();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Visibility#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Visibility#getValue()
	 * @see #getVisibility()
	 * @generated
	 */
	EAttribute getVisibility_Value();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Visibility#getBase_generalization <em>Base generalization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base generalization</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Visibility#getBase_generalization()
	 * @see #getVisibility()
	 * @generated
	 */
	EReference getVisibility_Base_generalization();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.CppInit <em>Cpp Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cpp Init</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.CppInit
	 * @generated
	 */
	EClass getCppInit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.CppInit#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.CppInit#getValue()
	 * @see #getCppInit()
	 * @generated
	 */
	EAttribute getCppInit_Value();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.CppInit#getBase_enumerationliteral <em>Base enumerationliteral</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base enumerationliteral</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.CppInit#getBase_enumerationliteral()
	 * @see #getCppInit()
	 * @generated
	 */
	EReference getCppInit_Base_enumerationliteral();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Template
	 * @generated
	 */
	EClass getTemplate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Template#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Declaration</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Template#getDeclaration()
	 * @see #getTemplate()
	 * @generated
	 */
	EAttribute getTemplate_Declaration();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Template#getBase_class <em>Base class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Template#getBase_class()
	 * @see #getTemplate()
	 * @generated
	 */
	EReference getTemplate_Base_class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.TemplateBinding <em>Template Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Binding</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.TemplateBinding
	 * @generated
	 */
	EClass getTemplateBinding();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.TemplateBinding#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.TemplateBinding#getBinding()
	 * @see #getTemplateBinding()
	 * @generated
	 */
	EAttribute getTemplateBinding_Binding();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.TemplateBinding#getBase_templatebinding <em>Base templatebinding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base templatebinding</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.TemplateBinding#getBase_templatebinding()
	 * @see #getTemplateBinding()
	 * @generated
	 */
	EReference getTemplateBinding_Base_templatebinding();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.TemplateParameter <em>Template Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.TemplateParameter
	 * @generated
	 */
	EClass getTemplateParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.TemplateParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.TemplateParameter#getName()
	 * @see #getTemplateParameter()
	 * @generated
	 */
	EAttribute getTemplateParameter_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.TemplateParameter#getBase_templateparameter <em>Base templateparameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base templateparameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.TemplateParameter#getBase_templateparameter()
	 * @see #getTemplateParameter()
	 * @generated
	 */
	EReference getTemplateParameter_Base_templateparameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Union <em>Union</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Union
	 * @generated
	 */
	EClass getUnion();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Union#getBase_DataType <em>Base Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Data Type</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Union#getBase_DataType()
	 * @see #getUnion()
	 * @generated
	 */
	EReference getUnion_Base_DataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass <em>Storage Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Storage Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass
	 * @generated
	 */
	EClass getStorageClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass#getStorageClass <em>Storage Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Storage Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass#getStorageClass()
	 * @see #getStorageClass()
	 * @generated
	 */
	EAttribute getStorageClass_StorageClass();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass#getBase_Property()
	 * @see #getStorageClass()
	 * @generated
	 */
	EReference getStorageClass_Base_Property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass#getBase_Parameter <em>Base Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.StorageClass#getBase_Parameter()
	 * @see #getStorageClass()
	 * @generated
	 */
	EReference getStorageClass_Base_Parameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Volatile <em>Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Volatile</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Volatile
	 * @generated
	 */
	EClass getVolatile();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Volatile#getBase_parameter <em>Base parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base parameter</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Volatile#getBase_parameter()
	 * @see #getVolatile()
	 * @generated
	 */
	EReference getVolatile_Base_parameter();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Volatile#getBase_property <em>Base property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Volatile#getBase_property()
	 * @see #getVolatile()
	 * @generated
	 */
	EReference getVolatile_Base_property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Volatile#getBase_operation <em>Base operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Volatile#getBase_operation()
	 * @see #getVolatile()
	 * @generated
	 */
	EReference getVolatile_Base_operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Variadic <em>Variadic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variadic</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Variadic
	 * @generated
	 */
	EClass getVariadic();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Variadic#getBase_operation <em>Base operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base operation</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Variadic#getBase_operation()
	 * @see #getVariadic()
	 * @generated
	 */
	EReference getVariadic_Base_operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Mutable <em>Mutable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mutable</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Mutable
	 * @generated
	 */
	EClass getMutable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.cpp.profile.Mutable#getBase_property <em>Base property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.Mutable#getBase_property()
	 * @see #getMutable()
	 * @generated
	 */
	EReference getMutable_Base_property();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.languages.cpp.profile.VisibilityKind <em>Visibility Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Visibility Kind</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.VisibilityKind
	 * @generated
	 */
	EEnum getVisibilityKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.languages.cpp.profile.EAccessKind <em>EAccess Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>EAccess Kind</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.EAccessKind
	 * @generated
	 */
	EEnum getEAccessKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.languages.cpp.profile.EStorageClass <em>EStorage Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>EStorage Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.cpp.profile.EStorageClass
	 * @generated
	 */
	EEnum getEStorageClass();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	C_CppFactory getC_CppFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.PtrImpl <em>Ptr</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.PtrImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getPtr()
		 * @generated
		 */
		EClass PTR = eINSTANCE.getPtr();

		/**
		 * The meta object literal for the '<em><b>Base parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PTR__BASE_PARAMETER = eINSTANCE.getPtr_Base_parameter();

		/**
		 * The meta object literal for the '<em><b>Base property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PTR__BASE_PROPERTY = eINSTANCE.getPtr_Base_property();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PTR__DECLARATION = eINSTANCE.getPtr_Declaration();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.IncludeImpl <em>Include</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.IncludeImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getInclude()
		 * @generated
		 */
		EClass INCLUDE = eINSTANCE.getInclude();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCLUDE__BODY = eINSTANCE.getInclude_Body();

		/**
		 * The meta object literal for the '<em><b>Pre Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCLUDE__PRE_BODY = eINSTANCE.getInclude_PreBody();

		/**
		 * The meta object literal for the '<em><b>Header</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCLUDE__HEADER = eINSTANCE.getInclude_Header();

		/**
		 * The meta object literal for the '<em><b>Base package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INCLUDE__BASE_PACKAGE = eINSTANCE.getInclude_Base_package();

		/**
		 * The meta object literal for the '<em><b>Base class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INCLUDE__BASE_CLASS = eINSTANCE.getInclude_Base_class();

		/**
		 * The meta object literal for the '<em><b>Base Classifier</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INCLUDE__BASE_CLASSIFIER = eINSTANCE.getInclude_Base_Classifier();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ManualGenerationImpl <em>Manual Generation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ManualGenerationImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getManualGeneration()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternLibraryImpl <em>Extern Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternLibraryImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getExternLibrary()
		 * @generated
		 */
		EClass EXTERN_LIBRARY = eINSTANCE.getExternLibrary();

		/**
		 * The meta object literal for the '<em><b>Include Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__INCLUDE_PATHS = eINSTANCE.getExternLibrary_IncludePaths();

		/**
		 * The meta object literal for the '<em><b>Base package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERN_LIBRARY__BASE_PACKAGE = eINSTANCE.getExternLibrary_Base_package();

		/**
		 * The meta object literal for the '<em><b>Lib Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__LIB_PATHS = eINSTANCE.getExternLibrary_LibPaths();

		/**
		 * The meta object literal for the '<em><b>Macros</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__MACROS = eINSTANCE.getExternLibrary_Macros();

		/**
		 * The meta object literal for the '<em><b>Libs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__LIBS = eINSTANCE.getExternLibrary_Libs();

		/**
		 * The meta object literal for the '<em><b>Includes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERN_LIBRARY__INCLUDES = eINSTANCE.getExternLibrary_Includes();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.NoCodeGenImpl <em>No Code Gen</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.NoCodeGenImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getNoCodeGen()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppRootImpl <em>Cpp Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppRootImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getCppRoot()
		 * @generated
		 */
		EClass CPP_ROOT = eINSTANCE.getCppRoot();

		/**
		 * The meta object literal for the '<em><b>Base package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CPP_ROOT__BASE_PACKAGE = eINSTANCE.getCppRoot_Base_package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ArrayImpl <em>Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ArrayImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getArray()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstImpl <em>Const</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getConst()
		 * @generated
		 */
		EClass CONST = eINSTANCE.getConst();

		/**
		 * The meta object literal for the '<em><b>Base parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONST__BASE_PARAMETER = eINSTANCE.getConst_Base_parameter();

		/**
		 * The meta object literal for the '<em><b>Base property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONST__BASE_PROPERTY = eINSTANCE.getConst_Base_property();

		/**
		 * The meta object literal for the '<em><b>Base operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONST__BASE_OPERATION = eINSTANCE.getConst_Base_operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.RefImpl <em>Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.RefImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getRef()
		 * @generated
		 */
		EClass REF = eINSTANCE.getRef();

		/**
		 * The meta object literal for the '<em><b>Base parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REF__BASE_PARAMETER = eINSTANCE.getRef_Base_parameter();

		/**
		 * The meta object literal for the '<em><b>Base property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REF__BASE_PROPERTY = eINSTANCE.getRef_Base_property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternalImpl <em>External</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ExternalImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getExternal()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstInitImpl <em>Const Init</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.ConstInitImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getConstInit()
		 * @generated
		 */
		EClass CONST_INIT = eINSTANCE.getConstInit();

		/**
		 * The meta object literal for the '<em><b>Initialisation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONST_INIT__INITIALISATION = eINSTANCE.getConstInit_Initialisation();

		/**
		 * The meta object literal for the '<em><b>Base operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONST_INIT__BASE_OPERATION = eINSTANCE.getConstInit_Base_operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.FriendImpl <em>Friend</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.FriendImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getFriend()
		 * @generated
		 */
		EClass FRIEND = eINSTANCE.getFriend();

		/**
		 * The meta object literal for the '<em><b>Base dependency</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRIEND__BASE_DEPENDENCY = eINSTANCE.getFriend_Base_dependency();

		/**
		 * The meta object literal for the '<em><b>Base operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRIEND__BASE_OPERATION = eINSTANCE.getFriend_Base_operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.InlineImpl <em>Inline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.InlineImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getInline()
		 * @generated
		 */
		EClass INLINE = eINSTANCE.getInline();

		/**
		 * The meta object literal for the '<em><b>Base operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INLINE__BASE_OPERATION = eINSTANCE.getInline_Base_operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VirtualImpl <em>Virtual</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VirtualImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVirtual()
		 * @generated
		 */
		EClass VIRTUAL = eINSTANCE.getVirtual();

		/**
		 * The meta object literal for the '<em><b>Base operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIRTUAL__BASE_OPERATION = eINSTANCE.getVirtual_Base_operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TypedefImpl <em>Typedef</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TypedefImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTypedef()
		 * @generated
		 */
		EClass TYPEDEF = eINSTANCE.getTypedef();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPEDEF__DEFINITION = eINSTANCE.getTypedef_Definition();

		/**
		 * The meta object literal for the '<em><b>Base primitivetype</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPEDEF__BASE_PRIMITIVETYPE = eINSTANCE.getTypedef_Base_primitivetype();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VisibilityImpl <em>Visibility</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VisibilityImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVisibility()
		 * @generated
		 */
		EClass VISIBILITY = eINSTANCE.getVisibility();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIBILITY__VALUE = eINSTANCE.getVisibility_Value();

		/**
		 * The meta object literal for the '<em><b>Base generalization</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VISIBILITY__BASE_GENERALIZATION = eINSTANCE.getVisibility_Base_generalization();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppInitImpl <em>Cpp Init</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.CppInitImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getCppInit()
		 * @generated
		 */
		EClass CPP_INIT = eINSTANCE.getCppInit();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPP_INIT__VALUE = eINSTANCE.getCppInit_Value();

		/**
		 * The meta object literal for the '<em><b>Base enumerationliteral</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CPP_INIT__BASE_ENUMERATIONLITERAL = eINSTANCE.getCppInit_Base_enumerationliteral();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateImpl <em>Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTemplate()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateBindingImpl <em>Template Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateBindingImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTemplateBinding()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateParameterImpl <em>Template Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.TemplateParameterImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getTemplateParameter()
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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.UnionImpl <em>Union</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.UnionImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getUnion()
		 * @generated
		 */
		EClass UNION = eINSTANCE.getUnion();

		/**
		 * The meta object literal for the '<em><b>Base Data Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION__BASE_DATA_TYPE = eINSTANCE.getUnion_Base_DataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.StorageClassImpl <em>Storage Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.StorageClassImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getStorageClass()
		 * @generated
		 */
		EClass STORAGE_CLASS = eINSTANCE.getStorageClass();

		/**
		 * The meta object literal for the '<em><b>Storage Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORAGE_CLASS__STORAGE_CLASS = eINSTANCE.getStorageClass_StorageClass();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORAGE_CLASS__BASE_PROPERTY = eINSTANCE.getStorageClass_Base_Property();

		/**
		 * The meta object literal for the '<em><b>Base Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORAGE_CLASS__BASE_PARAMETER = eINSTANCE.getStorageClass_Base_Parameter();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VolatileImpl <em>Volatile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VolatileImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVolatile()
		 * @generated
		 */
		EClass VOLATILE = eINSTANCE.getVolatile();

		/**
		 * The meta object literal for the '<em><b>Base parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VOLATILE__BASE_PARAMETER = eINSTANCE.getVolatile_Base_parameter();

		/**
		 * The meta object literal for the '<em><b>Base property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VOLATILE__BASE_PROPERTY = eINSTANCE.getVolatile_Base_property();

		/**
		 * The meta object literal for the '<em><b>Base operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VOLATILE__BASE_OPERATION = eINSTANCE.getVolatile_Base_operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.VariadicImpl <em>Variadic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.VariadicImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVariadic()
		 * @generated
		 */
		EClass VARIADIC = eINSTANCE.getVariadic();

		/**
		 * The meta object literal for the '<em><b>Base operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIADIC__BASE_OPERATION = eINSTANCE.getVariadic_Base_operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.impl.MutableImpl <em>Mutable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.MutableImpl
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getMutable()
		 * @generated
		 */
		EClass MUTABLE = eINSTANCE.getMutable();

		/**
		 * The meta object literal for the '<em><b>Base property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MUTABLE__BASE_PROPERTY = eINSTANCE.getMutable_Base_property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.VisibilityKind <em>Visibility Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.VisibilityKind
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getVisibilityKind()
		 * @generated
		 */
		EEnum VISIBILITY_KIND = eINSTANCE.getVisibilityKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.EAccessKind <em>EAccess Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.EAccessKind
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getEAccessKind()
		 * @generated
		 */
		EEnum EACCESS_KIND = eINSTANCE.getEAccessKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.cpp.profile.EStorageClass <em>EStorage Class</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.EStorageClass
		 * @see org.eclipse.papyrus.designer.languages.cpp.profile.impl.C_CppPackageImpl#getEStorageClass()
		 * @generated
		 */
		EEnum ESTORAGE_CLASS = eINSTANCE.getEStorageClass();

	}

} // C_CppPackage
