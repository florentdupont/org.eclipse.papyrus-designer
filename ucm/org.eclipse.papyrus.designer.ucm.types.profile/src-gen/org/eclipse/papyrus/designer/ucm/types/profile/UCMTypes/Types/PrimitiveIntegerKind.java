/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Primitive Integer Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.Types.TypesPackage#getPrimitiveIntegerKind()
 * @model
 * @generated
 */
public enum PrimitiveIntegerKind implements Enumerator {
	/**
	 * The '<em><b>SHORT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHORT_VALUE
	 * @generated
	 * @ordered
	 */
	SHORT(0, "SHORT", "SHORT"),

	/**
	 * The '<em><b>LONG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LONG_VALUE
	 * @generated
	 * @ordered
	 */
	LONG(1, "LONG", "LONG"),

	/**
	 * The '<em><b>LONGLONG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LONGLONG_VALUE
	 * @generated
	 * @ordered
	 */
	LONGLONG(2, "LONGLONG", "LONGLONG"),

	/**
	 * The '<em><b>USHORT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #USHORT_VALUE
	 * @generated
	 * @ordered
	 */
	USHORT(3, "USHORT", "USHORT"),

	/**
	 * The '<em><b>ULONG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ULONG_VALUE
	 * @generated
	 * @ordered
	 */
	ULONG(4, "ULONG", "ULONG"),

	/**
	 * The '<em><b>ULONGLONG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ULONGLONG_VALUE
	 * @generated
	 * @ordered
	 */
	ULONGLONG(5, "ULONGLONG", "ULONGLONG");

	/**
	 * The '<em><b>SHORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SHORT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHORT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SHORT_VALUE = 0;

	/**
	 * The '<em><b>LONG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LONG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LONG
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LONG_VALUE = 1;

	/**
	 * The '<em><b>LONGLONG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LONGLONG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LONGLONG
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LONGLONG_VALUE = 2;

	/**
	 * The '<em><b>USHORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>USHORT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #USHORT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int USHORT_VALUE = 3;

	/**
	 * The '<em><b>ULONG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ULONG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ULONG
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ULONG_VALUE = 4;

	/**
	 * The '<em><b>ULONGLONG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ULONGLONG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ULONGLONG
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ULONGLONG_VALUE = 5;

	/**
	 * An array of all the '<em><b>Primitive Integer Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PrimitiveIntegerKind[] VALUES_ARRAY =
		new PrimitiveIntegerKind[] {
			SHORT,
			LONG,
			LONGLONG,
			USHORT,
			ULONG,
			ULONGLONG,
		};

	/**
	 * A public read-only list of all the '<em><b>Primitive Integer Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PrimitiveIntegerKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Primitive Integer Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PrimitiveIntegerKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PrimitiveIntegerKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Primitive Integer Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PrimitiveIntegerKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PrimitiveIntegerKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Primitive Integer Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PrimitiveIntegerKind get(int value) {
		switch (value) {
			case SHORT_VALUE: return SHORT;
			case LONG_VALUE: return LONG;
			case LONGLONG_VALUE: return LONGLONG;
			case USHORT_VALUE: return USHORT;
			case ULONG_VALUE: return ULONG;
			case ULONGLONG_VALUE: return ULONGLONG;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PrimitiveIntegerKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PrimitiveIntegerKind
