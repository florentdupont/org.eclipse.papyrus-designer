/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Technical Policy Applicability</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.Ucm_technicalpoliciesPackage#getTechnicalPolicyApplicability()
 * @model
 * @generated
 */
public enum TechnicalPolicyApplicability implements Enumerator {
	/**
	 * The '<em><b>ON COMPONENT ONLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ON_COMPONENT_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	ON_COMPONENT_ONLY(0, "ON_COMPONENT_ONLY", "ON_COMPONENT_ONLY"),

	/**
	 * The '<em><b>ON SOME PORTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ON_SOME_PORTS_VALUE
	 * @generated
	 * @ordered
	 */
	ON_SOME_PORTS(1, "ON_SOME_PORTS", "ON_SOME_PORTS"),

	/**
	 * The '<em><b>ON ALL PORTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ON_ALL_PORTS_VALUE
	 * @generated
	 * @ordered
	 */
	ON_ALL_PORTS(2, "ON_ALL_PORTS", "ON_ALL_PORTS");

	/**
	 * The '<em><b>ON COMPONENT ONLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ON COMPONENT ONLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ON_COMPONENT_ONLY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ON_COMPONENT_ONLY_VALUE = 0;

	/**
	 * The '<em><b>ON SOME PORTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ON SOME PORTS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ON_SOME_PORTS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ON_SOME_PORTS_VALUE = 1;

	/**
	 * The '<em><b>ON ALL PORTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ON ALL PORTS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ON_ALL_PORTS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ON_ALL_PORTS_VALUE = 2;

	/**
	 * An array of all the '<em><b>Technical Policy Applicability</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TechnicalPolicyApplicability[] VALUES_ARRAY =
		new TechnicalPolicyApplicability[] {
			ON_COMPONENT_ONLY,
			ON_SOME_PORTS,
			ON_ALL_PORTS,
		};

	/**
	 * A public read-only list of all the '<em><b>Technical Policy Applicability</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TechnicalPolicyApplicability> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Technical Policy Applicability</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TechnicalPolicyApplicability get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TechnicalPolicyApplicability result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Technical Policy Applicability</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TechnicalPolicyApplicability getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TechnicalPolicyApplicability result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Technical Policy Applicability</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TechnicalPolicyApplicability get(int value) {
		switch (value) {
			case ON_COMPONENT_ONLY_VALUE: return ON_COMPONENT_ONLY;
			case ON_SOME_PORTS_VALUE: return ON_SOME_PORTS;
			case ON_ALL_PORTS_VALUE: return ON_ALL_PORTS;
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
	private TechnicalPolicyApplicability(int value, String name, String literal) {
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
	
} //TechnicalPolicyApplicability
