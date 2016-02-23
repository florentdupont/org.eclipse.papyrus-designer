/*******************************************************************************
 * Copyright (c) 2006 - 2012 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.codegen.utils;

import org.eclipse.papyrus.designer.languages.common.base.GenUtils;
import org.eclipse.papyrus.designer.languages.cpp.codegen.preferences.CppCodeGenUtils;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Const;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Mutable;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ref;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.StorageClass;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Volatile;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Utility functions managing the "modifier" of an element, i.e. additional
 * information whether a passed parameter or an attribute is a pointer, a
 * reference, an array or constant.
 *
 * @author ansgar
 *
 */
public class Modifier {

	public static String modPtr(Element propertyOrParameter) {
		// Pointer
		String ptr; //$NON-NLS-1$
		Ptr cppPtr = UMLUtil.getStereotypeApplication(propertyOrParameter, Ptr.class);
		if (cppPtr != null) {
			ptr = (cppPtr.getDeclaration() != null && !cppPtr.getDeclaration().equals("")) ? cppPtr.getDeclaration() : "*"; //$NON-NLS-1$
		} else {
			if (propertyOrParameter instanceof Property
					&& ((Property) propertyOrParameter).getAggregation() == AggregationKind.SHARED_LITERAL) {
				ptr = "*"; //$NON-NLS-1$
			} else {
				ptr = ""; //$NON-NLS-1$
			}
		}
		
		boolean ptrOrRef = GenUtils.hasStereotype(propertyOrParameter, Ref.class)
				|| GenUtils.hasStereotype(propertyOrParameter, Ptr.class);

		// out and inout parameter are realized by means of a pointer
		if (propertyOrParameter instanceof Parameter) {
			ParameterDirectionKind directionKind = ((Parameter) propertyOrParameter).getDirection();
			if (directionKind == ParameterDirectionKind.OUT_LITERAL || directionKind == ParameterDirectionKind.INOUT_LITERAL) {
				// parameter is an out or inout parameter. If the user already either a pointer or reference, use this.
				if (!ptrOrRef) {
					// .. otherwise add the oeprator from the preferences
					ptr += CppCodeGenUtils.getOutInoutOp();
				}
			}
		}
		return ptr;
	}

	public static String modRef(Element propertyOrParameter) {
		// Ref
		return GenUtils.hasStereotype(propertyOrParameter, Ref.class) ? "&" : ""; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static String modArray(Element propertyOrParameter) {
		// Array
		Array cppArray = UMLUtil.getStereotypeApplication(propertyOrParameter, Array.class);
		String array = ""; //$NON-NLS-1$
		if (cppArray != null) {
			// explicit array definition
			array = (cppArray.getDefinition() != null && !cppArray.getDefinition().isEmpty()) ? cppArray.getDefinition() : "[]"; //$NON-NLS-1$
		} else {
			// calculate array from multiplicity definition
			int multiplicity = 1;
			if (propertyOrParameter instanceof MultiplicityElement) {
				multiplicity = ((MultiplicityElement) propertyOrParameter).getUpper();
			}
			array = ""; //$NON-NLS-1$
			if (multiplicity == -1) {
				array = "[]"; //$NON-NLS-1$
			} else if (multiplicity > 1) {
				array = "[" + multiplicity + "]"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return array;
	}

	/**
	 * return modifier for const and volatile
	 * @param propertyOrParameter
	 * @return
	 */
	public static String modCVQualifier(Element propertyOrParameter) {
		String cvQualifier = ""; //$NON-NLS-1$
		// CVQualifiers cannot be used with static functions
		if (propertyOrParameter instanceof Operation && ((Operation) propertyOrParameter).isStatic()) {
			// do nothing
		}
		// Const
		else if (GenUtils.hasStereotype(propertyOrParameter, Const.class)) {
			// Volatile with const
			if (GenUtils.hasStereotype(propertyOrParameter, Volatile.class)) {
				cvQualifier = (propertyOrParameter instanceof Operation) ? " const volatile" //$NON-NLS-1$
						: // added at the end of operation, prefix with " "
						"const volatile "; // before operation or //$NON-NLS-1$
											// parameter, postfix with " "
			}
			// Const without Volatile
			else {
				cvQualifier = (propertyOrParameter instanceof Operation) ? " const" //$NON-NLS-1$
						: // added at the end of operation, prefix with " "
						"const "; // before operation or //$NON-NLS-1$
									// parameter, postfix with " "
			}
		}
		// Volatile without const
		else if (GenUtils.hasStereotype(propertyOrParameter, Volatile.class)) {
			cvQualifier = (propertyOrParameter instanceof Operation) ? " volatile" //$NON-NLS-1$
					: // added at the end of operation, prefix with " "
					"volatile "; // before operation or parameter, //$NON-NLS-1$
									// postfix with " "
		}
		
		// Mutable (non-static attribute only)
		if (GenUtils.hasStereotype(propertyOrParameter, Mutable.class)) {
			if (propertyOrParameter instanceof Property && !((Property) propertyOrParameter).isStatic()) {
				cvQualifier = "mutable " + cvQualifier;
			}
		}
		
		return cvQualifier;
	}

	/**
	 * return modifier for storage class
	 * @param propertyOrParameter
	 * @return
	 */
	public static String modSCQualifier(Element propertyOrParameter) {
		StorageClass sc = UMLUtil.getStereotypeApplication(propertyOrParameter, StorageClass.class);
		if (sc != null) {
			return sc.getStorageClass().getLiteral() + " "; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * Return inform about the direction of a parameter in form of a comment
	 * 
	 * @param propertyOperationOrParameter
	 * @return
	 */
	public static String dirInfo(Element propertyOperationOrParameter) {
		if (propertyOperationOrParameter instanceof Parameter) {
			ParameterDirectionKind directionKind = ((Parameter) propertyOperationOrParameter).getDirection();
			if (directionKind == ParameterDirectionKind.IN_LITERAL) {
				return " /*in*/"; //$NON-NLS-1$
			} else if (directionKind == ParameterDirectionKind.OUT_LITERAL) {
				return " /*out*/"; //$NON-NLS-1$
			} else if (directionKind == ParameterDirectionKind.INOUT_LITERAL) {
				return " /*inout*/"; //$NON-NLS-1$
			}
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * initialize the ptr/ref/array/isConst attributes.
	 *
	 * @param propertyOperationOrParameter
	 */
	public static void update(Element propertyOperationOrParameter) {

		
	}
}
