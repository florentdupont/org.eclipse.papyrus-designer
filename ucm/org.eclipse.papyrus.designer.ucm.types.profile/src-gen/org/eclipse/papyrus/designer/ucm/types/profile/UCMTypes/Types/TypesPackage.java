/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types;

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
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesFactory
 * @model kind="package"
 * @generated
 */
public interface TypesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Types";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/types/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCMTypes.Types";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypesPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureFieldImpl <em>Structure Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureFieldImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getStructureField()
	 * @generated
	 */
	int STRUCTURE_FIELD = 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_FIELD__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Structure Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_FIELD_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Structure Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_FIELD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionCaseImpl <em>Union Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionCaseImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getUnionCase()
	 * @generated
	 */
	int UNION_CASE = 1;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CASE__BASE_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Selector Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CASE__SELECTOR_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Is Default Case</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CASE__IS_DEFAULT_CASE = 2;

	/**
	 * The number of structural features of the '<em>Union Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CASE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Union Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CASE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IIndexableImpl <em>IIndexable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IIndexableImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIIndexable()
	 * @generated
	 */
	int IINDEXABLE = 3;

	/**
	 * The feature id for the '<em><b>Index Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINDEXABLE__INDEX_TYPE = 0;

	/**
	 * The number of structural features of the '<em>IIndexable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINDEXABLE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IIndexable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IINDEXABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayDimensionImpl <em>Array Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayDimensionImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getArrayDimension()
	 * @generated
	 */
	int ARRAY_DIMENSION = 2;

	/**
	 * The feature id for the '<em><b>Index Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DIMENSION__INDEX_TYPE = IINDEXABLE__INDEX_TYPE;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DIMENSION__SIZE = IINDEXABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DIMENSION__BASE_PROPERTY = IINDEXABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Array Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DIMENSION_FEATURE_COUNT = IINDEXABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Array Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DIMENSION_OPERATION_COUNT = IINDEXABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IDataTypeImpl <em>IData Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IDataTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIDataType()
	 * @generated
	 */
	int IDATA_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDATA_TYPE__BASE_DATA_TYPE = 0;

	/**
	 * The number of structural features of the '<em>IData Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDATA_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IData Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDATA_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IStandardDataTypeImpl <em>IStandard Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IStandardDataTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIStandardDataType()
	 * @generated
	 */
	int ISTANDARD_DATA_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISTANDARD_DATA_TYPE__BASE_DATA_TYPE = IDATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The number of structural features of the '<em>IStandard Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISTANDARD_DATA_TYPE_FEATURE_COUNT = IDATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>IStandard Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISTANDARD_DATA_TYPE_OPERATION_COUNT = IDATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.AliasImpl <em>Alias</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.AliasImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getAlias()
	 * @generated
	 */
	int ALIAS = 4;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALIAS__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Aliased Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALIAS__ALIASED_TYPE = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Alias</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALIAS_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Alias</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALIAS_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureImpl <em>Structure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getStructure()
	 * @generated
	 */
	int STRUCTURE = 7;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The number of structural features of the '<em>Structure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Structure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionImpl <em>Union</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getUnion()
	 * @generated
	 */
	int UNION = 8;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Selector Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__SELECTOR_NAME = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Selector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__SELECTOR_TYPE = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Union</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Union</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IResizableImpl <em>IResizable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IResizableImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIResizable()
	 * @generated
	 */
	int IRESIZABLE = 9;

	/**
	 * The feature id for the '<em><b>Max Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESIZABLE__MAX_SIZE = 0;

	/**
	 * The number of structural features of the '<em>IResizable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESIZABLE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IResizable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESIZABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayImpl <em>Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getArray()
	 * @generated
	 */
	int ARRAY = 10;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__TYPE = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__DIMENSION = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StringTypeImpl <em>String Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StringTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getStringType()
	 * @generated
	 */
	int STRING_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Max Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__MAX_SIZE = IRESIZABLE__MAX_SIZE;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__BASE_DATA_TYPE = IRESIZABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Char Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE__CHAR_BASE = IRESIZABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE_FEATURE_COUNT = IRESIZABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>String Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TYPE_OPERATION_COUNT = IRESIZABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.NativeTypeImpl <em>Native Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.NativeTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getNativeType()
	 * @generated
	 */
	int NATIVE_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Max Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE__MAX_SIZE = IRESIZABLE__MAX_SIZE;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE__BASE_DATA_TYPE = IRESIZABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Native Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_FEATURE_COUNT = IRESIZABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Native Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_OPERATION_COUNT = IRESIZABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.SequenceImpl <em>Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.SequenceImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getSequence()
	 * @generated
	 */
	int SEQUENCE = 13;

	/**
	 * The feature id for the '<em><b>Index Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__INDEX_TYPE = IINDEXABLE__INDEX_TYPE;

	/**
	 * The feature id for the '<em><b>Max Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__MAX_SIZE = IINDEXABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__BASE_DATA_TYPE = IINDEXABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__TYPE = IINDEXABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_FEATURE_COUNT = IINDEXABLE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_OPERATION_COUNT = IINDEXABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveIntegerImpl <em>Primitive Integer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveIntegerImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveInteger()
	 * @generated
	 */
	int PRIMITIVE_INTEGER = 14;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_INTEGER__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_INTEGER__KIND = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_INTEGER_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Primitive Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_INTEGER_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveFloatImpl <em>Primitive Float</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveFloatImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveFloat()
	 * @generated
	 */
	int PRIMITIVE_FLOAT = 15;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_FLOAT__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_FLOAT__KIND = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Float</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_FLOAT_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Primitive Float</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_FLOAT_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveCharImpl <em>Primitive Char</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveCharImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveChar()
	 * @generated
	 */
	int PRIMITIVE_CHAR = 16;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_CHAR__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_CHAR__KIND = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Char</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_CHAR_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Primitive Char</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_CHAR_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveBooleanImpl <em>Primitive Boolean</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveBooleanImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveBoolean()
	 * @generated
	 */
	int PRIMITIVE_BOOLEAN = 17;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BOOLEAN__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The number of structural features of the '<em>Primitive Boolean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BOOLEAN_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive Boolean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BOOLEAN_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveOctetImpl <em>Primitive Octet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveOctetImpl
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveOctet()
	 * @generated
	 */
	int PRIMITIVE_OCTET = 18;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_OCTET__BASE_DATA_TYPE = ISTANDARD_DATA_TYPE__BASE_DATA_TYPE;

	/**
	 * The number of structural features of the '<em>Primitive Octet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_OCTET_FEATURE_COUNT = ISTANDARD_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Primitive Octet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_OCTET_OPERATION_COUNT = ISTANDARD_DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind <em>Primitive Integer Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveIntegerKind()
	 * @generated
	 */
	int PRIMITIVE_INTEGER_KIND = 19;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind <em>Primitive Char Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveCharKind()
	 * @generated
	 */
	int PRIMITIVE_CHAR_KIND = 20;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloatKind <em>Primitive Float Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloatKind
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveFloatKind()
	 * @generated
	 */
	int PRIMITIVE_FLOAT_KIND = 21;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StructureField <em>Structure Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structure Field</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StructureField
	 * @generated
	 */
	EClass getStructureField();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StructureField#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StructureField#getBase_Property()
	 * @see #getStructureField()
	 * @generated
	 */
	EReference getStructureField_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase <em>Union Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Case</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase
	 * @generated
	 */
	EClass getUnionCase();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase#getBase_Property()
	 * @see #getUnionCase()
	 * @generated
	 */
	EReference getUnionCase_Base_Property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase#getSelectorValue <em>Selector Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Selector Value</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase#getSelectorValue()
	 * @see #getUnionCase()
	 * @generated
	 */
	EReference getUnionCase_SelectorValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase#isDefaultCase <em>Is Default Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Default Case</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.UnionCase#isDefaultCase()
	 * @see #getUnionCase()
	 * @generated
	 */
	EAttribute getUnionCase_IsDefaultCase();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.ArrayDimension <em>Array Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Dimension</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.ArrayDimension
	 * @generated
	 */
	EClass getArrayDimension();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.ArrayDimension#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.ArrayDimension#getSize()
	 * @see #getArrayDimension()
	 * @generated
	 */
	EAttribute getArrayDimension_Size();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.ArrayDimension#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.ArrayDimension#getBase_Property()
	 * @see #getArrayDimension()
	 * @generated
	 */
	EReference getArrayDimension_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IIndexable <em>IIndexable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IIndexable</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IIndexable
	 * @generated
	 */
	EClass getIIndexable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IIndexable#getIndexType <em>Index Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IIndexable#getIndexType()
	 * @see #getIIndexable()
	 * @generated
	 */
	EAttribute getIIndexable_IndexType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alias</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias
	 * @generated
	 */
	EClass getAlias();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias#getAliasedType <em>Aliased Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Aliased Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Alias#getAliasedType()
	 * @see #getAlias()
	 * @generated
	 */
	EReference getAlias_AliasedType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IStandardDataType <em>IStandard Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IStandard Data Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IStandardDataType
	 * @generated
	 */
	EClass getIStandardDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IDataType <em>IData Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IData Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IDataType
	 * @generated
	 */
	EClass getIDataType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IDataType#getBase_DataType <em>Base Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Data Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IDataType#getBase_DataType()
	 * @see #getIDataType()
	 * @generated
	 */
	EReference getIDataType_Base_DataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Structure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structure</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Structure
	 * @generated
	 */
	EClass getStructure();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union <em>Union</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union
	 * @generated
	 */
	EClass getUnion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorName <em>Selector Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Selector Name</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorName()
	 * @see #getUnion()
	 * @generated
	 */
	EAttribute getUnion_SelectorName();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorType <em>Selector Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Selector Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Union#getSelectorType()
	 * @see #getUnion()
	 * @generated
	 */
	EReference getUnion_SelectorType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IResizable <em>IResizable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IResizable</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IResizable
	 * @generated
	 */
	EClass getIResizable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IResizable#getMaxSize <em>Max Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Size</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.IResizable#getMaxSize()
	 * @see #getIResizable()
	 * @generated
	 */
	EAttribute getIResizable_MaxSize();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Array <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Array
	 * @generated
	 */
	EClass getArray();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Array#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Array#getType()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Array#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimension</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Array#getDimension()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Dimension();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StringType <em>String Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StringType
	 * @generated
	 */
	EClass getStringType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StringType#getCharBase <em>Char Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Char Base</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.StringType#getCharBase()
	 * @see #getStringType()
	 * @generated
	 */
	EAttribute getStringType_CharBase();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.NativeType <em>Native Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Native Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.NativeType
	 * @generated
	 */
	EClass getNativeType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Sequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Sequence
	 * @generated
	 */
	EClass getSequence();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Sequence#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.Sequence#getType()
	 * @see #getSequence()
	 * @generated
	 */
	EReference getSequence_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveInteger <em>Primitive Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Integer</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveInteger
	 * @generated
	 */
	EClass getPrimitiveInteger();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveInteger#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveInteger#getKind()
	 * @see #getPrimitiveInteger()
	 * @generated
	 */
	EAttribute getPrimitiveInteger_Kind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloat <em>Primitive Float</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Float</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloat
	 * @generated
	 */
	EClass getPrimitiveFloat();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloat#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloat#getKind()
	 * @see #getPrimitiveFloat()
	 * @generated
	 */
	EAttribute getPrimitiveFloat_Kind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveChar <em>Primitive Char</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Char</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveChar
	 * @generated
	 */
	EClass getPrimitiveChar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveChar#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveChar#getKind()
	 * @see #getPrimitiveChar()
	 * @generated
	 */
	EAttribute getPrimitiveChar_Kind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveBoolean <em>Primitive Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Boolean</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveBoolean
	 * @generated
	 */
	EClass getPrimitiveBoolean();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveOctet <em>Primitive Octet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Octet</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveOctet
	 * @generated
	 */
	EClass getPrimitiveOctet();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind <em>Primitive Integer Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Primitive Integer Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind
	 * @generated
	 */
	EEnum getPrimitiveIntegerKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind <em>Primitive Char Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Primitive Char Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind
	 * @generated
	 */
	EEnum getPrimitiveCharKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloatKind <em>Primitive Float Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Primitive Float Kind</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloatKind
	 * @generated
	 */
	EEnum getPrimitiveFloatKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypesFactory getTypesFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureFieldImpl <em>Structure Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureFieldImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getStructureField()
		 * @generated
		 */
		EClass STRUCTURE_FIELD = eINSTANCE.getStructureField();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURE_FIELD__BASE_PROPERTY = eINSTANCE.getStructureField_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionCaseImpl <em>Union Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionCaseImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getUnionCase()
		 * @generated
		 */
		EClass UNION_CASE = eINSTANCE.getUnionCase();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_CASE__BASE_PROPERTY = eINSTANCE.getUnionCase_Base_Property();

		/**
		 * The meta object literal for the '<em><b>Selector Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_CASE__SELECTOR_VALUE = eINSTANCE.getUnionCase_SelectorValue();

		/**
		 * The meta object literal for the '<em><b>Is Default Case</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNION_CASE__IS_DEFAULT_CASE = eINSTANCE.getUnionCase_IsDefaultCase();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayDimensionImpl <em>Array Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayDimensionImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getArrayDimension()
		 * @generated
		 */
		EClass ARRAY_DIMENSION = eINSTANCE.getArrayDimension();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_DIMENSION__SIZE = eINSTANCE.getArrayDimension_Size();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_DIMENSION__BASE_PROPERTY = eINSTANCE.getArrayDimension_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IIndexableImpl <em>IIndexable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IIndexableImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIIndexable()
		 * @generated
		 */
		EClass IINDEXABLE = eINSTANCE.getIIndexable();

		/**
		 * The meta object literal for the '<em><b>Index Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IINDEXABLE__INDEX_TYPE = eINSTANCE.getIIndexable_IndexType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.AliasImpl <em>Alias</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.AliasImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getAlias()
		 * @generated
		 */
		EClass ALIAS = eINSTANCE.getAlias();

		/**
		 * The meta object literal for the '<em><b>Aliased Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALIAS__ALIASED_TYPE = eINSTANCE.getAlias_AliasedType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IStandardDataTypeImpl <em>IStandard Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IStandardDataTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIStandardDataType()
		 * @generated
		 */
		EClass ISTANDARD_DATA_TYPE = eINSTANCE.getIStandardDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IDataTypeImpl <em>IData Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IDataTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIDataType()
		 * @generated
		 */
		EClass IDATA_TYPE = eINSTANCE.getIDataType();

		/**
		 * The meta object literal for the '<em><b>Base Data Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDATA_TYPE__BASE_DATA_TYPE = eINSTANCE.getIDataType_Base_DataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureImpl <em>Structure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StructureImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getStructure()
		 * @generated
		 */
		EClass STRUCTURE = eINSTANCE.getStructure();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionImpl <em>Union</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.UnionImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getUnion()
		 * @generated
		 */
		EClass UNION = eINSTANCE.getUnion();

		/**
		 * The meta object literal for the '<em><b>Selector Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNION__SELECTOR_NAME = eINSTANCE.getUnion_SelectorName();

		/**
		 * The meta object literal for the '<em><b>Selector Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION__SELECTOR_TYPE = eINSTANCE.getUnion_SelectorType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IResizableImpl <em>IResizable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.IResizableImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getIResizable()
		 * @generated
		 */
		EClass IRESIZABLE = eINSTANCE.getIResizable();

		/**
		 * The meta object literal for the '<em><b>Max Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IRESIZABLE__MAX_SIZE = eINSTANCE.getIResizable_MaxSize();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayImpl <em>Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.ArrayImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getArray()
		 * @generated
		 */
		EClass ARRAY = eINSTANCE.getArray();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY__TYPE = eINSTANCE.getArray_Type();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY__DIMENSION = eINSTANCE.getArray_Dimension();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StringTypeImpl <em>String Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.StringTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getStringType()
		 * @generated
		 */
		EClass STRING_TYPE = eINSTANCE.getStringType();

		/**
		 * The meta object literal for the '<em><b>Char Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TYPE__CHAR_BASE = eINSTANCE.getStringType_CharBase();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.NativeTypeImpl <em>Native Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.NativeTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getNativeType()
		 * @generated
		 */
		EClass NATIVE_TYPE = eINSTANCE.getNativeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.SequenceImpl <em>Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.SequenceImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getSequence()
		 * @generated
		 */
		EClass SEQUENCE = eINSTANCE.getSequence();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEQUENCE__TYPE = eINSTANCE.getSequence_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveIntegerImpl <em>Primitive Integer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveIntegerImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveInteger()
		 * @generated
		 */
		EClass PRIMITIVE_INTEGER = eINSTANCE.getPrimitiveInteger();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_INTEGER__KIND = eINSTANCE.getPrimitiveInteger_Kind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveFloatImpl <em>Primitive Float</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveFloatImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveFloat()
		 * @generated
		 */
		EClass PRIMITIVE_FLOAT = eINSTANCE.getPrimitiveFloat();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_FLOAT__KIND = eINSTANCE.getPrimitiveFloat_Kind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveCharImpl <em>Primitive Char</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveCharImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveChar()
		 * @generated
		 */
		EClass PRIMITIVE_CHAR = eINSTANCE.getPrimitiveChar();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_CHAR__KIND = eINSTANCE.getPrimitiveChar_Kind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveBooleanImpl <em>Primitive Boolean</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveBooleanImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveBoolean()
		 * @generated
		 */
		EClass PRIMITIVE_BOOLEAN = eINSTANCE.getPrimitiveBoolean();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveOctetImpl <em>Primitive Octet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.PrimitiveOctetImpl
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveOctet()
		 * @generated
		 */
		EClass PRIMITIVE_OCTET = eINSTANCE.getPrimitiveOctet();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind <em>Primitive Integer Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveIntegerKind
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveIntegerKind()
		 * @generated
		 */
		EEnum PRIMITIVE_INTEGER_KIND = eINSTANCE.getPrimitiveIntegerKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind <em>Primitive Char Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveCharKind
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveCharKind()
		 * @generated
		 */
		EEnum PRIMITIVE_CHAR_KIND = eINSTANCE.getPrimitiveCharKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloatKind <em>Primitive Float Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.PrimitiveFloatKind
		 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesPackageImpl#getPrimitiveFloatKind()
		 * @generated
		 */
		EEnum PRIMITIVE_FLOAT_KIND = eINSTANCE.getPrimitiveFloatKind();

	}

} //TypesPackage
