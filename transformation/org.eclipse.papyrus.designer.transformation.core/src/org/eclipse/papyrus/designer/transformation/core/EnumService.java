/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.transformation.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.CppInit;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;


/**
 * Manage enumerations within Acceleo code
 */
public class EnumService {

	private static final String GLOBALENUMS = "globalenums"; //$NON-NLS-1$

	public static void init() {
		enumHash.clear();
	}

	/**
	 * Return qualified name of enum package which is used to prefix enumerations (namespace)
	 *
	 * @param dummy
	 * @return
	 */
	public static String enumSvcPrefix() {
		return enumPkg.getQualifiedName();
	}

	public static String literalKey(Element dummy, String enumName, String literalKey) {
		String prefix = "ID_"; //$NON-NLS-1$
		String literal = ""; //$NON-NLS-1$
		if (literalKey.equals("Port")) { //$NON-NLS-1$
			literal = prefix + TransformationContext.port.getName();
		}
		else if (literalKey.equals("Formal")) { //$NON-NLS-1$
			literal = prefix + TransformationContext.formalParameter.getName();
		}
		else if (literalKey.equals("Instance")) { //$NON-NLS-1$
			literal = prefix + TransformationContext.instance.getName();
		}
		else if (literalKey.equals("Interface")) { //$NON-NLS-1$
			literal = prefix + TransformationContext.classifier.getName();
		}
		return literal(enumName, literal);
	}

	public static String quoteLiteral(Element dummy, String enumName, String literal) {
		return Utils.quoteString(literal(enumName, literal));
	}

	/**
	 * Create a literal within an enumeration. Both, the literal and the enumeration may be an
	 * Acceleo template. If the name of the enumeration starts with "L", it is considered as a
	 * local enumeration, i.e. a nested classifier within the classifier (it has to be a class)
	 * from the transformation context.
	 *
	 * @param enumName
	 *            the name of an enumeration
	 * @param literal
	 *            the name of a literal within that enumeration.
	 * @return
	 */
	public static String literal(String enumName, String literal) {
		return literal(enumName, literal, -1);
	}
	
	/**
	 * Create a literal within an enumeration. Both, the literal and the enumeration may be an
	 * xtend template. If the name of the enumeration starts with "L", it is considered as a
	 * local enumeration, i.e. a nested classifier within the classifier (it has to be a class)
	 * from the transformation context.
	 *
	 * @param enumName
	 *            the name of an enumeration
	 * @param literal
	 *            the name of a literal within that enumeration.
	 * @return
	 */
	public static String literal(String enumName, String literal, int initialValue) {
		Enumeration enumeration;
		boolean first = false;
		if (enumName.startsWith("L")) { //$NON-NLS-1$
			// magic prefix for class local (only allowed for local classes)
			if (TransformationContext.classifier instanceof Class) {
				Class clazz = (Class) TransformationContext.classifier;
				enumeration = (Enumeration) clazz.getNestedClassifier(enumName);
				if (enumeration == null) {
					enumeration = (Enumeration)
							((Class) TransformationContext.classifier).createNestedClassifier(enumName, UMLPackage.eINSTANCE.getEnumeration());
				}
			}
			else {
				throw new RuntimeException("Local enumeration " + enumName + " is not used in the transformation context of a class");
			}
		}
		else {
			enumeration = enumHash.get(enumName);
			if (enumPkg == null) {
				// enumeration can not be created
				throw new RuntimeException("global enumeration " + enumName + " can not be created, since the enumPkg (from Transformation context) is not initialized");
			}
			if (enumeration == null) {
				enumeration = enumPkg.createOwnedEnumeration(enumName);
				enumHash.put(enumName, enumeration);
				first = true;
			}
		}
		if (enumeration.getOwnedLiteral(literal) == null) {
			EnumerationLiteral umlLiteral = enumeration.createOwnedLiteral(literal);
			if (first && initialValue != -1) {
				CppInit cppInit = StereotypeUtil.applyApp(umlLiteral, CppInit.class);
				cppInit.setValue(initialValue);
			}
		}
		// declare a dependency to the enumeration from the current classifier
		checkAndCreateDependency(TransformationContext.classifier, enumeration);

		if (enumName.startsWith("L")) { //$NON-NLS-1$
			return literal;
		}
		else {
			return GLOBALENUMS + "::" + literal; //$NON-NLS-1$
		}
	}

	/**
	 * Create a dependency between the passed classifier, target pair. The objective
	 * of this function is that code generators do the necessary to assure that the
	 * target is known within the classifier (e.g. include directives)
	 *
	 * @param classifier
	 *            a classifier
	 * @param target
	 *            a target, on which the classifier or its code depends.
	 */
	public static void checkAndCreateDependency(Classifier classifier, NamedElement target) {
		boolean found = false;
		for (Dependency dep : classifier.getClientDependencies()) {
			if (dep.getSuppliers().contains(target)) {
				found = true;
			}
		}
		if (!found) {
			Dependency dep = classifier.createDependency(target);
			dep.setName(String.format("from %s to %s", classifier.getName(), target.getName())); //$NON-NLS-1$
		}
	}

	public static void createEnumPackage(Package root) {
		init();
		enumPkg = root.createNestedPackage(GLOBALENUMS);
	}

	public static Package enumPkg;

	public static void createEnums() {

	}

	private static Map<String, Enumeration> enumHash = new HashMap<String, Enumeration>();
}
