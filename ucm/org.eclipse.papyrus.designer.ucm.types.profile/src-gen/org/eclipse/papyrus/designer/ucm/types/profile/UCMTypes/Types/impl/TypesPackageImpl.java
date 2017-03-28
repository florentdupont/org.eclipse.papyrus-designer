/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Array;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.ArrayDimension;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IDataType;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IIndexable;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IResizable;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IStandardDataType;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.NativeType;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveBoolean;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveChar;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloat;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloatKind;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveInteger;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveOctet;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Sequence;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StringType;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Structure;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StructureField;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesFactory;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union;
import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.UCMTypesPackage;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.impl.UCMTypesPackageImpl;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesPackageImpl extends EPackageImpl implements TypesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structureFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayDimensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iIndexableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass aliasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iStandardDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iResizableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nativeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveIntegerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveFloatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveCharEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveBooleanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveOctetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum primitiveIntegerKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum primitiveCharKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum primitiveFloatKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypesPackageImpl() {
		super(eNS_URI, TypesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TypesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TypesPackage init() {
		if (isInited) return (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Obtain or create and register package
		TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TypesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UCMTypesPackageImpl theUCMTypesPackage = (UCMTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UCMTypesPackage.eNS_URI) instanceof UCMTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UCMTypesPackage.eNS_URI) : UCMTypesPackage.eINSTANCE);

		// Create package meta-data objects
		theTypesPackage.createPackageContents();
		theUCMTypesPackage.createPackageContents();

		// Initialize created meta-data
		theTypesPackage.initializePackageContents();
		theUCMTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TypesPackage.eNS_URI, theTypesPackage);
		return theTypesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructureField() {
		return structureFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructureField_Base_Property() {
		return (EReference)structureFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnionCase() {
		return unionCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionCase_Base_Property() {
		return (EReference)unionCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionCase_SelectorValue() {
		return (EReference)unionCaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnionCase_IsDefaultCase() {
		return (EAttribute)unionCaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayDimension() {
		return arrayDimensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayDimension_Size() {
		return (EAttribute)arrayDimensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayDimension_Base_Property() {
		return (EReference)arrayDimensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIIndexable() {
		return iIndexableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIIndexable_IndexType() {
		return (EAttribute)iIndexableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlias() {
		return aliasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlias_AliasedType() {
		return (EReference)aliasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIStandardDataType() {
		return iStandardDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIDataType() {
		return iDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIDataType_Base_DataType() {
		return (EReference)iDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructure() {
		return structureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnion() {
		return unionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnion_SelectorName() {
		return (EAttribute)unionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnion_SelectorType() {
		return (EReference)unionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIResizable() {
		return iResizableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIResizable_MaxSize() {
		return (EAttribute)iResizableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArray() {
		return arrayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArray_Type() {
		return (EReference)arrayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArray_Dimension() {
		return (EReference)arrayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringType() {
		return stringTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringType_CharBase() {
		return (EAttribute)stringTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNativeType() {
		return nativeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequence() {
		return sequenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSequence_Type() {
		return (EReference)sequenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveInteger() {
		return primitiveIntegerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveInteger_Kind() {
		return (EAttribute)primitiveIntegerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveFloat() {
		return primitiveFloatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveFloat_Kind() {
		return (EAttribute)primitiveFloatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveChar() {
		return primitiveCharEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveChar_Kind() {
		return (EAttribute)primitiveCharEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveBoolean() {
		return primitiveBooleanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveOctet() {
		return primitiveOctetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPrimitiveIntegerKind() {
		return primitiveIntegerKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPrimitiveCharKind() {
		return primitiveCharKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPrimitiveFloatKind() {
		return primitiveFloatKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesFactory getTypesFactory() {
		return (TypesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		structureFieldEClass = createEClass(STRUCTURE_FIELD);
		createEReference(structureFieldEClass, STRUCTURE_FIELD__BASE_PROPERTY);

		unionCaseEClass = createEClass(UNION_CASE);
		createEReference(unionCaseEClass, UNION_CASE__BASE_PROPERTY);
		createEReference(unionCaseEClass, UNION_CASE__SELECTOR_VALUE);
		createEAttribute(unionCaseEClass, UNION_CASE__IS_DEFAULT_CASE);

		arrayDimensionEClass = createEClass(ARRAY_DIMENSION);
		createEAttribute(arrayDimensionEClass, ARRAY_DIMENSION__SIZE);
		createEReference(arrayDimensionEClass, ARRAY_DIMENSION__BASE_PROPERTY);

		iIndexableEClass = createEClass(IINDEXABLE);
		createEAttribute(iIndexableEClass, IINDEXABLE__INDEX_TYPE);

		aliasEClass = createEClass(ALIAS);
		createEReference(aliasEClass, ALIAS__ALIASED_TYPE);

		iStandardDataTypeEClass = createEClass(ISTANDARD_DATA_TYPE);

		iDataTypeEClass = createEClass(IDATA_TYPE);
		createEReference(iDataTypeEClass, IDATA_TYPE__BASE_DATA_TYPE);

		structureEClass = createEClass(STRUCTURE);

		unionEClass = createEClass(UNION);
		createEAttribute(unionEClass, UNION__SELECTOR_NAME);
		createEReference(unionEClass, UNION__SELECTOR_TYPE);

		iResizableEClass = createEClass(IRESIZABLE);
		createEAttribute(iResizableEClass, IRESIZABLE__MAX_SIZE);

		arrayEClass = createEClass(ARRAY);
		createEReference(arrayEClass, ARRAY__TYPE);
		createEReference(arrayEClass, ARRAY__DIMENSION);

		stringTypeEClass = createEClass(STRING_TYPE);
		createEAttribute(stringTypeEClass, STRING_TYPE__CHAR_BASE);

		nativeTypeEClass = createEClass(NATIVE_TYPE);

		sequenceEClass = createEClass(SEQUENCE);
		createEReference(sequenceEClass, SEQUENCE__TYPE);

		primitiveIntegerEClass = createEClass(PRIMITIVE_INTEGER);
		createEAttribute(primitiveIntegerEClass, PRIMITIVE_INTEGER__KIND);

		primitiveFloatEClass = createEClass(PRIMITIVE_FLOAT);
		createEAttribute(primitiveFloatEClass, PRIMITIVE_FLOAT__KIND);

		primitiveCharEClass = createEClass(PRIMITIVE_CHAR);
		createEAttribute(primitiveCharEClass, PRIMITIVE_CHAR__KIND);

		primitiveBooleanEClass = createEClass(PRIMITIVE_BOOLEAN);

		primitiveOctetEClass = createEClass(PRIMITIVE_OCTET);

		// Create enums
		primitiveIntegerKindEEnum = createEEnum(PRIMITIVE_INTEGER_KIND);
		primitiveCharKindEEnum = createEEnum(PRIMITIVE_CHAR_KIND);
		primitiveFloatKindEEnum = createEEnum(PRIMITIVE_FLOAT_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);
		org.eclipse.uml2.types.TypesPackage theTypesPackage_1 = (org.eclipse.uml2.types.TypesPackage)EPackage.Registry.INSTANCE.getEPackage(org.eclipse.uml2.types.TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		arrayDimensionEClass.getESuperTypes().add(this.getIIndexable());
		aliasEClass.getESuperTypes().add(this.getIStandardDataType());
		iStandardDataTypeEClass.getESuperTypes().add(this.getIDataType());
		structureEClass.getESuperTypes().add(this.getIStandardDataType());
		unionEClass.getESuperTypes().add(this.getIStandardDataType());
		arrayEClass.getESuperTypes().add(this.getIStandardDataType());
		stringTypeEClass.getESuperTypes().add(this.getIResizable());
		stringTypeEClass.getESuperTypes().add(this.getIStandardDataType());
		nativeTypeEClass.getESuperTypes().add(this.getIResizable());
		nativeTypeEClass.getESuperTypes().add(this.getIStandardDataType());
		sequenceEClass.getESuperTypes().add(this.getIIndexable());
		sequenceEClass.getESuperTypes().add(this.getIResizable());
		sequenceEClass.getESuperTypes().add(this.getIStandardDataType());
		primitiveIntegerEClass.getESuperTypes().add(this.getIStandardDataType());
		primitiveFloatEClass.getESuperTypes().add(this.getIStandardDataType());
		primitiveCharEClass.getESuperTypes().add(this.getIStandardDataType());
		primitiveBooleanEClass.getESuperTypes().add(this.getIStandardDataType());
		primitiveOctetEClass.getESuperTypes().add(this.getIStandardDataType());

		// Initialize classes, features, and operations; add parameters
		initEClass(structureFieldEClass, StructureField.class, "StructureField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStructureField_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, StructureField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(unionCaseEClass, UnionCase.class, "UnionCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnionCase_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, UnionCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUnionCase_SelectorValue(), theUMLPackage.getEnumerationLiteral(), null, "selectorValue", null, 1, 1, UnionCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getUnionCase_IsDefaultCase(), theTypesPackage_1.getBoolean(), "isDefaultCase", "false", 1, 1, UnionCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(arrayDimensionEClass, ArrayDimension.class, "ArrayDimension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrayDimension_Size(), theTypesPackage_1.getInteger(), "size", null, 1, 1, ArrayDimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArrayDimension_Base_Property(), theUMLPackage.getProperty(), null, "base_Property", null, 1, 1, ArrayDimension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iIndexableEClass, IIndexable.class, "IIndexable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIIndexable_IndexType(), this.getPrimitiveIntegerKind(), "indexType", null, 1, 1, IIndexable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(aliasEClass, Alias.class, "Alias", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlias_AliasedType(), theUMLPackage.getDataType(), null, "aliasedType", null, 1, 1, Alias.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iStandardDataTypeEClass, IStandardDataType.class, "IStandardDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iDataTypeEClass, IDataType.class, "IDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIDataType_Base_DataType(), theUMLPackage.getDataType(), null, "base_DataType", null, 1, 1, IDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(structureEClass, Structure.class, "Structure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unionEClass, Union.class, "Union", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnion_SelectorName(), theTypesPackage_1.getString(), "selectorName", null, 1, 1, Union.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUnion_SelectorType(), theUMLPackage.getEnumeration(), null, "selectorType", null, 1, 1, Union.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(iResizableEClass, IResizable.class, "IResizable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIResizable_MaxSize(), theTypesPackage_1.getInteger(), "maxSize", null, 1, 1, IResizable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(arrayEClass, Array.class, "Array", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArray_Type(), theUMLPackage.getDataType(), null, "type", null, 1, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArray_Dimension(), this.getArrayDimension(), null, "dimension", null, 1, -1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(stringTypeEClass, StringType.class, "StringType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringType_CharBase(), this.getPrimitiveCharKind(), "charBase", null, 1, 1, StringType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(nativeTypeEClass, NativeType.class, "NativeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sequenceEClass, Sequence.class, "Sequence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSequence_Type(), theUMLPackage.getDataType(), null, "type", null, 1, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(primitiveIntegerEClass, PrimitiveInteger.class, "PrimitiveInteger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrimitiveInteger_Kind(), this.getPrimitiveIntegerKind(), "kind", null, 1, 1, PrimitiveInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(primitiveFloatEClass, PrimitiveFloat.class, "PrimitiveFloat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrimitiveFloat_Kind(), this.getPrimitiveFloatKind(), "kind", null, 1, 1, PrimitiveFloat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(primitiveCharEClass, PrimitiveChar.class, "PrimitiveChar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrimitiveChar_Kind(), this.getPrimitiveCharKind(), "kind", null, 1, 1, PrimitiveChar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(primitiveBooleanEClass, PrimitiveBoolean.class, "PrimitiveBoolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveOctetEClass, PrimitiveOctet.class, "PrimitiveOctet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(primitiveIntegerKindEEnum, PrimitiveIntegerKind.class, "PrimitiveIntegerKind");
		addEEnumLiteral(primitiveIntegerKindEEnum, PrimitiveIntegerKind.SHORT);
		addEEnumLiteral(primitiveIntegerKindEEnum, PrimitiveIntegerKind.LONG);
		addEEnumLiteral(primitiveIntegerKindEEnum, PrimitiveIntegerKind.LONGLONG);
		addEEnumLiteral(primitiveIntegerKindEEnum, PrimitiveIntegerKind.USHORT);
		addEEnumLiteral(primitiveIntegerKindEEnum, PrimitiveIntegerKind.ULONG);
		addEEnumLiteral(primitiveIntegerKindEEnum, PrimitiveIntegerKind.ULONGLONG);

		initEEnum(primitiveCharKindEEnum, PrimitiveCharKind.class, "PrimitiveCharKind");
		addEEnumLiteral(primitiveCharKindEEnum, PrimitiveCharKind.CHAR);
		addEEnumLiteral(primitiveCharKindEEnum, PrimitiveCharKind.WCHAR);

		initEEnum(primitiveFloatKindEEnum, PrimitiveFloatKind.class, "PrimitiveFloatKind");
		addEEnumLiteral(primitiveFloatKindEEnum, PrimitiveFloatKind.FLOAT);
		addEEnumLiteral(primitiveFloatKindEEnum, PrimitiveFloatKind.DOUBLE);
		addEEnumLiteral(primitiveFloatKindEEnum, PrimitiveFloatKind.LONGDOUBLE);
	}

} //TypesPackageImpl
