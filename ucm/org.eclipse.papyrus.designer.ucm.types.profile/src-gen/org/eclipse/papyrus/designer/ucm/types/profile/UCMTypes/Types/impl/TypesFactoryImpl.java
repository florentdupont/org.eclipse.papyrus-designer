/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesFactoryImpl extends EFactoryImpl implements TypesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypesFactory init() {
		try {
			TypesFactory theTypesFactory = (TypesFactory)EPackage.Registry.INSTANCE.getEFactory(TypesPackage.eNS_URI);
			if (theTypesFactory != null) {
				return theTypesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TypesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesFactoryImpl() {
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
			case TypesPackage.STRUCTURE_FIELD: return createStructureField();
			case TypesPackage.UNION_CASE: return createUnionCase();
			case TypesPackage.ARRAY_DIMENSION: return createArrayDimension();
			case TypesPackage.ALIAS: return createAlias();
			case TypesPackage.STRUCTURE: return createStructure();
			case TypesPackage.UNION: return createUnion();
			case TypesPackage.ARRAY: return createArray();
			case TypesPackage.STRING_TYPE: return createStringType();
			case TypesPackage.NATIVE_TYPE: return createNativeType();
			case TypesPackage.SEQUENCE: return createSequence();
			case TypesPackage.PRIMITIVE_INTEGER: return createPrimitiveInteger();
			case TypesPackage.PRIMITIVE_FLOAT: return createPrimitiveFloat();
			case TypesPackage.PRIMITIVE_CHAR: return createPrimitiveChar();
			case TypesPackage.PRIMITIVE_BOOLEAN: return createPrimitiveBoolean();
			case TypesPackage.PRIMITIVE_OCTET: return createPrimitiveOctet();
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
			case TypesPackage.PRIMITIVE_INTEGER_KIND:
				return createPrimitiveIntegerKindFromString(eDataType, initialValue);
			case TypesPackage.PRIMITIVE_CHAR_KIND:
				return createPrimitiveCharKindFromString(eDataType, initialValue);
			case TypesPackage.PRIMITIVE_FLOAT_KIND:
				return createPrimitiveFloatKindFromString(eDataType, initialValue);
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
			case TypesPackage.PRIMITIVE_INTEGER_KIND:
				return convertPrimitiveIntegerKindToString(eDataType, instanceValue);
			case TypesPackage.PRIMITIVE_CHAR_KIND:
				return convertPrimitiveCharKindToString(eDataType, instanceValue);
			case TypesPackage.PRIMITIVE_FLOAT_KIND:
				return convertPrimitiveFloatKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureField createStructureField() {
		StructureFieldImpl structureField = new StructureFieldImpl();
		return structureField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionCase createUnionCase() {
		UnionCaseImpl unionCase = new UnionCaseImpl();
		return unionCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayDimension createArrayDimension() {
		ArrayDimensionImpl arrayDimension = new ArrayDimensionImpl();
		return arrayDimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Alias createAlias() {
		AliasImpl alias = new AliasImpl();
		return alias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Structure createStructure() {
		StructureImpl structure = new StructureImpl();
		return structure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Union createUnion() {
		UnionImpl union = new UnionImpl();
		return union;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Array createArray() {
		ArrayImpl array = new ArrayImpl();
		return array;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringType createStringType() {
		StringTypeImpl stringType = new StringTypeImpl();
		return stringType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NativeType createNativeType() {
		NativeTypeImpl nativeType = new NativeTypeImpl();
		return nativeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence createSequence() {
		SequenceImpl sequence = new SequenceImpl();
		return sequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveInteger createPrimitiveInteger() {
		PrimitiveIntegerImpl primitiveInteger = new PrimitiveIntegerImpl();
		return primitiveInteger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveFloat createPrimitiveFloat() {
		PrimitiveFloatImpl primitiveFloat = new PrimitiveFloatImpl();
		return primitiveFloat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveChar createPrimitiveChar() {
		PrimitiveCharImpl primitiveChar = new PrimitiveCharImpl();
		return primitiveChar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveBoolean createPrimitiveBoolean() {
		PrimitiveBooleanImpl primitiveBoolean = new PrimitiveBooleanImpl();
		return primitiveBoolean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveOctet createPrimitiveOctet() {
		PrimitiveOctetImpl primitiveOctet = new PrimitiveOctetImpl();
		return primitiveOctet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveIntegerKind createPrimitiveIntegerKindFromString(EDataType eDataType, String initialValue) {
		PrimitiveIntegerKind result = PrimitiveIntegerKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrimitiveIntegerKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveCharKind createPrimitiveCharKindFromString(EDataType eDataType, String initialValue) {
		PrimitiveCharKind result = PrimitiveCharKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrimitiveCharKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveFloatKind createPrimitiveFloatKindFromString(EDataType eDataType, String initialValue) {
		PrimitiveFloatKind result = PrimitiveFloatKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrimitiveFloatKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesPackage getTypesPackage() {
		return (TypesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TypesPackage getPackage() {
		return TypesPackage.eINSTANCE;
	}

} //TypesFactoryImpl
