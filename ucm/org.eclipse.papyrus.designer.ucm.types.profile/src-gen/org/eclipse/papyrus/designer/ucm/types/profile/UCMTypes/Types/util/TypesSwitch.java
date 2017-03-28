/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage
 * @generated
 */
public class TypesSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TypesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesSwitch() {
		if (modelPackage == null) {
			modelPackage = TypesPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TypesPackage.STRUCTURE_FIELD: {
				StructureField structureField = (StructureField)theEObject;
				T result = caseStructureField(structureField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.UNION_CASE: {
				UnionCase unionCase = (UnionCase)theEObject;
				T result = caseUnionCase(unionCase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ARRAY_DIMENSION: {
				ArrayDimension arrayDimension = (ArrayDimension)theEObject;
				T result = caseArrayDimension(arrayDimension);
				if (result == null) result = caseIIndexable(arrayDimension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.IINDEXABLE: {
				IIndexable iIndexable = (IIndexable)theEObject;
				T result = caseIIndexable(iIndexable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ALIAS: {
				Alias alias = (Alias)theEObject;
				T result = caseAlias(alias);
				if (result == null) result = caseIStandardDataType(alias);
				if (result == null) result = caseIDataType(alias);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ISTANDARD_DATA_TYPE: {
				IStandardDataType iStandardDataType = (IStandardDataType)theEObject;
				T result = caseIStandardDataType(iStandardDataType);
				if (result == null) result = caseIDataType(iStandardDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.IDATA_TYPE: {
				IDataType iDataType = (IDataType)theEObject;
				T result = caseIDataType(iDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.STRUCTURE: {
				Structure structure = (Structure)theEObject;
				T result = caseStructure(structure);
				if (result == null) result = caseIStandardDataType(structure);
				if (result == null) result = caseIDataType(structure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.UNION: {
				Union union = (Union)theEObject;
				T result = caseUnion(union);
				if (result == null) result = caseIStandardDataType(union);
				if (result == null) result = caseIDataType(union);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.IRESIZABLE: {
				IResizable iResizable = (IResizable)theEObject;
				T result = caseIResizable(iResizable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ARRAY: {
				Array array = (Array)theEObject;
				T result = caseArray(array);
				if (result == null) result = caseIStandardDataType(array);
				if (result == null) result = caseIDataType(array);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.STRING_TYPE: {
				StringType stringType = (StringType)theEObject;
				T result = caseStringType(stringType);
				if (result == null) result = caseIResizable(stringType);
				if (result == null) result = caseIStandardDataType(stringType);
				if (result == null) result = caseIDataType(stringType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.NATIVE_TYPE: {
				NativeType nativeType = (NativeType)theEObject;
				T result = caseNativeType(nativeType);
				if (result == null) result = caseIResizable(nativeType);
				if (result == null) result = caseIStandardDataType(nativeType);
				if (result == null) result = caseIDataType(nativeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.SEQUENCE: {
				Sequence sequence = (Sequence)theEObject;
				T result = caseSequence(sequence);
				if (result == null) result = caseIIndexable(sequence);
				if (result == null) result = caseIResizable(sequence);
				if (result == null) result = caseIStandardDataType(sequence);
				if (result == null) result = caseIDataType(sequence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.PRIMITIVE_INTEGER: {
				PrimitiveInteger primitiveInteger = (PrimitiveInteger)theEObject;
				T result = casePrimitiveInteger(primitiveInteger);
				if (result == null) result = caseIStandardDataType(primitiveInteger);
				if (result == null) result = caseIDataType(primitiveInteger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.PRIMITIVE_FLOAT: {
				PrimitiveFloat primitiveFloat = (PrimitiveFloat)theEObject;
				T result = casePrimitiveFloat(primitiveFloat);
				if (result == null) result = caseIStandardDataType(primitiveFloat);
				if (result == null) result = caseIDataType(primitiveFloat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.PRIMITIVE_CHAR: {
				PrimitiveChar primitiveChar = (PrimitiveChar)theEObject;
				T result = casePrimitiveChar(primitiveChar);
				if (result == null) result = caseIStandardDataType(primitiveChar);
				if (result == null) result = caseIDataType(primitiveChar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.PRIMITIVE_BOOLEAN: {
				PrimitiveBoolean primitiveBoolean = (PrimitiveBoolean)theEObject;
				T result = casePrimitiveBoolean(primitiveBoolean);
				if (result == null) result = caseIStandardDataType(primitiveBoolean);
				if (result == null) result = caseIDataType(primitiveBoolean);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.PRIMITIVE_OCTET: {
				PrimitiveOctet primitiveOctet = (PrimitiveOctet)theEObject;
				T result = casePrimitiveOctet(primitiveOctet);
				if (result == null) result = caseIStandardDataType(primitiveOctet);
				if (result == null) result = caseIDataType(primitiveOctet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structure Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structure Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructureField(StructureField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionCase(UnionCase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Dimension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrayDimension(ArrayDimension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IIndexable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IIndexable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIIndexable(IIndexable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alias</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alias</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlias(Alias object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IStandard Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IStandard Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIStandardDataType(IStandardDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IData Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IData Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIDataType(IDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructure(Structure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnion(Union object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IResizable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IResizable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIResizable(IResizable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArray(Array object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringType(StringType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Native Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Native Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNativeType(NativeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequence(Sequence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Integer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Integer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveInteger(PrimitiveInteger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Float</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Float</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveFloat(PrimitiveFloat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Char</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Char</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveChar(PrimitiveChar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveBoolean(PrimitiveBoolean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Octet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Octet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveOctet(PrimitiveOctet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TypesSwitch
