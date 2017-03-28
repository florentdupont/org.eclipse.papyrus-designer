/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage
 * @generated
 */
public interface TypesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypesFactory eINSTANCE = org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.impl.TypesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Structure Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Structure Field</em>'.
	 * @generated
	 */
	StructureField createStructureField();

	/**
	 * Returns a new object of class '<em>Union Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Union Case</em>'.
	 * @generated
	 */
	UnionCase createUnionCase();

	/**
	 * Returns a new object of class '<em>Array Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Dimension</em>'.
	 * @generated
	 */
	ArrayDimension createArrayDimension();

	/**
	 * Returns a new object of class '<em>Alias</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Alias</em>'.
	 * @generated
	 */
	Alias createAlias();

	/**
	 * Returns a new object of class '<em>Structure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Structure</em>'.
	 * @generated
	 */
	Structure createStructure();

	/**
	 * Returns a new object of class '<em>Union</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Union</em>'.
	 * @generated
	 */
	Union createUnion();

	/**
	 * Returns a new object of class '<em>Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array</em>'.
	 * @generated
	 */
	Array createArray();

	/**
	 * Returns a new object of class '<em>String Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Type</em>'.
	 * @generated
	 */
	StringType createStringType();

	/**
	 * Returns a new object of class '<em>Native Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Native Type</em>'.
	 * @generated
	 */
	NativeType createNativeType();

	/**
	 * Returns a new object of class '<em>Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence</em>'.
	 * @generated
	 */
	Sequence createSequence();

	/**
	 * Returns a new object of class '<em>Primitive Integer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Integer</em>'.
	 * @generated
	 */
	PrimitiveInteger createPrimitiveInteger();

	/**
	 * Returns a new object of class '<em>Primitive Float</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Float</em>'.
	 * @generated
	 */
	PrimitiveFloat createPrimitiveFloat();

	/**
	 * Returns a new object of class '<em>Primitive Char</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Char</em>'.
	 * @generated
	 */
	PrimitiveChar createPrimitiveChar();

	/**
	 * Returns a new object of class '<em>Primitive Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Boolean</em>'.
	 * @generated
	 */
	PrimitiveBoolean createPrimitiveBoolean();

	/**
	 * Returns a new object of class '<em>Primitive Octet</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Primitive Octet</em>'.
	 * @generated
	 */
	PrimitiveOctet createPrimitiveOctet();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TypesPackage getTypesPackage();

} //TypesFactory
