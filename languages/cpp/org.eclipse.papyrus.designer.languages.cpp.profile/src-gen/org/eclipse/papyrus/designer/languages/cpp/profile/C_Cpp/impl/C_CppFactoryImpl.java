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
package org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.C_CppFactory;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.C_CppPackage;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Const;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ConstInit;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.CppInit;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.CppRoot;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.EAccessKind;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.EStorageClass;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ExternLibrary;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Friend;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Inline;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ManualGeneration;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Mutable;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ref;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.StorageClass;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Template;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.TemplateBinding;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.TemplateParameter;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Typedef;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Union;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Variadic;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Virtual;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Visibility;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.VisibilityKind;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Volatile;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class C_CppFactoryImpl extends EFactoryImpl implements C_CppFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static C_CppFactory init() {
		try {
			C_CppFactory theC_CppFactory = (C_CppFactory)EPackage.Registry.INSTANCE.getEFactory(C_CppPackage.eNS_URI);
			if (theC_CppFactory != null) {
				return theC_CppFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new C_CppFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public C_CppFactoryImpl() {
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
			case C_CppPackage.PTR: return createPtr();
			case C_CppPackage.INCLUDE: return createInclude();
			case C_CppPackage.MANUAL_GENERATION: return createManualGeneration();
			case C_CppPackage.EXTERN_LIBRARY: return createExternLibrary();
			case C_CppPackage.NO_CODE_GEN: return createNoCodeGen();
			case C_CppPackage.CPP_ROOT: return createCppRoot();
			case C_CppPackage.ARRAY: return createArray();
			case C_CppPackage.CONST: return createConst();
			case C_CppPackage.REF: return createRef();
			case C_CppPackage.EXTERNAL: return createExternal();
			case C_CppPackage.CONST_INIT: return createConstInit();
			case C_CppPackage.FRIEND: return createFriend();
			case C_CppPackage.INLINE: return createInline();
			case C_CppPackage.VIRTUAL: return createVirtual();
			case C_CppPackage.TYPEDEF: return createTypedef();
			case C_CppPackage.VISIBILITY: return createVisibility();
			case C_CppPackage.CPP_INIT: return createCppInit();
			case C_CppPackage.TEMPLATE: return createTemplate();
			case C_CppPackage.TEMPLATE_BINDING: return createTemplateBinding();
			case C_CppPackage.TEMPLATE_PARAMETER: return createTemplateParameter();
			case C_CppPackage.UNION: return createUnion();
			case C_CppPackage.STORAGE_CLASS: return createStorageClass();
			case C_CppPackage.VOLATILE: return createVolatile();
			case C_CppPackage.VARIADIC: return createVariadic();
			case C_CppPackage.MUTABLE: return createMutable();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case C_CppPackage.VISIBILITY_KIND:
				return createVisibilityKindFromString(eDataType, initialValue);
			case C_CppPackage.EACCESS_KIND:
				return createEAccessKindFromString(eDataType, initialValue);
			case C_CppPackage.ESTORAGE_CLASS:
				return createEStorageClassFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case C_CppPackage.VISIBILITY_KIND:
				return convertVisibilityKindToString(eDataType, instanceValue);
			case C_CppPackage.EACCESS_KIND:
				return convertEAccessKindToString(eDataType, instanceValue);
			case C_CppPackage.ESTORAGE_CLASS:
				return convertEStorageClassToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Ptr createPtr() {
		PtrImpl ptr = new PtrImpl();
		return ptr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Include createInclude() {
		IncludeImpl include = new IncludeImpl();
		return include;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ManualGeneration createManualGeneration() {
		ManualGenerationImpl manualGeneration = new ManualGenerationImpl();
		return manualGeneration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExternLibrary createExternLibrary() {
		ExternLibraryImpl externLibrary = new ExternLibraryImpl();
		return externLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NoCodeGen createNoCodeGen() {
		NoCodeGenImpl noCodeGen = new NoCodeGenImpl();
		return noCodeGen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CppRoot createCppRoot() {
		CppRootImpl cppRoot = new CppRootImpl();
		return cppRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Array createArray() {
		ArrayImpl array = new ArrayImpl();
		return array;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Const createConst() {
		ConstImpl const_ = new ConstImpl();
		return const_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Ref createRef() {
		RefImpl ref = new RefImpl();
		return ref;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public External createExternal() {
		ExternalImpl external = new ExternalImpl();
		return external;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConstInit createConstInit() {
		ConstInitImpl constInit = new ConstInitImpl();
		return constInit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Friend createFriend() {
		FriendImpl friend = new FriendImpl();
		return friend;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Inline createInline() {
		InlineImpl inline = new InlineImpl();
		return inline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Virtual createVirtual() {
		VirtualImpl virtual = new VirtualImpl();
		return virtual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Typedef createTypedef() {
		TypedefImpl typedef = new TypedefImpl();
		return typedef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Visibility createVisibility() {
		VisibilityImpl visibility = new VisibilityImpl();
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CppInit createCppInit() {
		CppInitImpl cppInit = new CppInitImpl();
		return cppInit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Template createTemplate() {
		TemplateImpl template = new TemplateImpl();
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateBinding createTemplateBinding() {
		TemplateBindingImpl templateBinding = new TemplateBindingImpl();
		return templateBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateParameter createTemplateParameter() {
		TemplateParameterImpl templateParameter = new TemplateParameterImpl();
		return templateParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Union createUnion() {
		UnionImpl union = new UnionImpl();
		return union;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StorageClass createStorageClass() {
		StorageClassImpl storageClass = new StorageClassImpl();
		return storageClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Volatile createVolatile() {
		VolatileImpl volatile_ = new VolatileImpl();
		return volatile_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variadic createVariadic() {
		VariadicImpl variadic = new VariadicImpl();
		return variadic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mutable createMutable() {
		MutableImpl mutable = new MutableImpl();
		return mutable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind createVisibilityKindFromString(EDataType eDataType, String initialValue) {
		VisibilityKind result = VisibilityKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVisibilityKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAccessKind createEAccessKindFromString(EDataType eDataType, String initialValue) {
		EAccessKind result = EAccessKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEAccessKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStorageClass createEStorageClassFromString(EDataType eDataType, String initialValue) {
		EStorageClass result = EStorageClass.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEStorageClassToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public C_CppPackage getC_CppPackage() {
		return (C_CppPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static C_CppPackage getPackage() {
		return C_CppPackage.eINSTANCE;
	}

} // C_CppFactoryImpl
