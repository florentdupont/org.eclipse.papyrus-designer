/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen.utils;

import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Array;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Default;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Native;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Synchronized;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Transient;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Variadic;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Volatile;
import org.eclipse.papyrus.designer.languages.java.codegen.xtend.JavaOperations;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Utility functions managing the "modifier" of an element
 */
public class Modifier {
	public static String modArray(Element propertyOrParameter) {
		// Array
		Array javaArray = UMLUtil.getStereotypeApplication(propertyOrParameter, Array.class);
		String array = ""; //$NON-NLS-1$
		if (javaArray != null) {
			// explicit array definition
			array = (javaArray.getDefinition() != null && !javaArray.getDefinition().isEmpty()) ? javaArray.getDefinition() : "[]"; //$NON-NLS-1$
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
	
	public static String modVariadic(Parameter parameter) {
		if (parameter != null) {
			if (UMLUtil.getStereotypeApplication(parameter, Variadic.class) != null) {
				return "...";
			}
		}
		
		return "";
	}
	
	public static String attributeModifiers(Property attribute) {
		String result = "";
		
		if (attribute.getInterface() == null) {
			result += visibilityModifier(attribute);
			result += staticModifier(attribute);
			result += finalModifier(attribute);
			result += transientModifier(attribute);
			result += volatileModifier(attribute);
		} else {
			result += "public ";
			result += staticModifier(attribute);
			result += finalModifier(attribute);
		}
		
		return result;
	}
	
	public static String methodModifiers(Operation operation) {
		String result = "";
		
		if (JavaOperations.isConstructor(operation)) {
			if (operation.getInterface() == null) {
				if (operation.getOwner() instanceof Enumeration) {
					result += "private ";
				} else {
					result += visibilityModifier(operation);
				}
			}
		} else {
			// Visibility
			if (!JavaOperations.isAbstract(operation)) { // Operation is not abstract (not part of interface and not abstract in abstract class)
				// Normal case
				// ==> public OR protected OR private OR static OR final OR synchronized OR (native XOR strictfp)
				result += visibilityModifier(operation);
				result += staticModifier(operation);
				result += finalModifier(operation);
				result += synchronizedModifier(operation);
				result += nativeModifier(operation);
				if (nativeModifier(operation).isEmpty()) {
					result += strictfpModifier(operation);
				}
			} else if (operation.getInterface() == null) { // Operation is abstract and in an abstract class (unless it is static which means it shouldn't pass validation)
				if (operation.isStatic()) {
					// Invalid abstract operation case: static and also abstract operation
					// ==> we consider only the static modifier and handle it as a normal case instead of abstract operation case
					result += visibilityModifier(operation);
					result += staticModifier(operation);
					result += finalModifier(operation);
					result += synchronizedModifier(operation);
					result += nativeModifier(operation);
					if (nativeModifier(operation).isEmpty()) {
						result += strictfpModifier(operation);
					}
				} else {
					// Abstract operation case
					// ==> only (public XOR protected) OR abstract modifiers are allowed
					if (operation.getVisibility() == VisibilityKind.PROTECTED_LITERAL) {
						result += visibilityModifier(operation);
					} else {
						result += "public ";
					}
					result += abstractModifier(operation);
				}
			} else if (operation.getInterface() != null) { // Operation is in an interface
				// Interface case
				// ==> public OR ((static XOR default) OR (strictfp if static XOR default)) modifiers are allowed (note that abstract is useless)
				result += "public ";
				if (operation.isStatic()) {
					result += staticModifier(operation);
					result += strictfpModifier(operation);
				} else {
					result += defaultModifier(operation);
					if (!defaultModifier(operation).isEmpty()) {
						result += strictfpModifier(operation);
					}
				}
			}
		}
		
		return result;
	}
	
	public static String parameterModifiers(Parameter parameter) {
		if (UMLUtil.getStereotypeApplication(parameter, Final.class) != null) {
			return "final ";
		}
		
		return "";
	}
	
	private static String visibilityModifier(NamedElement element) {
		if (element.getVisibility() == VisibilityKind.PACKAGE_LITERAL) {
			return ""; // UML package visibility is default visibility in Java
		}
		return element.getVisibility().toString().toLowerCase() + " ";
	}
	
	private static String transientModifier(Element element) {
		if (UMLUtil.getStereotypeApplication(element, Transient.class) != null) {
			return "transient ";
		}
		
		return "";
	}
	
	private static String staticModifier(Feature element) {
		if (element.isStatic()) {
			if (element.getOwner() != null) {
				if (element.getOwner().getOwner() instanceof Classifier) { // element is a feature of an inner classifier
					if (UMLUtil.getStereotypeApplication(element.getOwner(), StaticClassifier.class) != null) {
						return "static ";
					}
				} else { // element is a feature of a normal classifier
					return "static ";
				}
			}
		}
		
		return "";
	}
	
	private static String finalModifier(Feature element) {
		if (element.isLeaf()) {
			return "final ";
		}
		return "";
	}
	
	private static String volatileModifier(Element element) {
		if (element instanceof Feature && ((Feature) element).isLeaf()) {
			return "";
		}
		
		if (UMLUtil.getStereotypeApplication(element, Volatile.class) != null) {
			return "volatile ";
		}
		
		return "";
	}
	
	private static String abstractModifier(BehavioralFeature element) {
		if (element.isAbstract()) {
			return "abstract ";
		}
		return "";
	}
	
	private static String synchronizedModifier(Element element) {
		if (UMLUtil.getStereotypeApplication(element, Synchronized.class) != null) {
			return "synchronized ";
		}
		return "";
	}
	
	private static String nativeModifier(Element element) {
		if (UMLUtil.getStereotypeApplication(element, Native.class) != null) {
			return "native ";
		}
		return "";
	}
	
	private static String strictfpModifier(Element element) {
		if (UMLUtil.getStereotypeApplication(element, Strictfp.class) != null) {
			return "strictfp ";
		}
		return "";
	}
	
	private static String defaultModifier(Element element) {
		if (UMLUtil.getStereotypeApplication(element, Default.class) != null) {
			return "default ";
		}
		return "";
	}
}
