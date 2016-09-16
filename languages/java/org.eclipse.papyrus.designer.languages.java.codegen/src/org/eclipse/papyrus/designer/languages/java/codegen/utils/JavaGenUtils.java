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

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.languages.common.base.GenUtils;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Some utilities specific to Java code generation
 */
public class JavaGenUtils {

	public static final String JAVA_LIB = "JavaLibrary"; //$NON-NLS-1$
	
	private static NamedElement currentNE;
	private static Namespace currentNS;
	public static Map<NamedElement, EList<String>> imports = new HashMap<NamedElement, EList<String>>();

	/**
	 * The standard UML, MARTE, and JavaPrimitive libraries provide some primitives to be translated as Java primitives
	 *
	 * @param type
	 * @return
	 */
	public static String getStdtypes(PrimitiveType type) {
		Object owner = type.getOwner();
		String owningPkgName = ""; //$NON-NLS-1$
		if (owner instanceof Package) {
			owningPkgName = ((Package) owner).getName();
		}
		if (owningPkgName.equals("PrimitiveTypes") || // used in UML >= 2.4 //$NON-NLS-1$
				owningPkgName.equals("UMLPrimitiveTypes") || // used in UML < 2.4 //$NON-NLS-1$
				owningPkgName.equals("MARTE_PrimitivesTypes") ||
				owningPkgName.equals("JavaPrimitiveTypes")) { //$NON-NLS-1$
			String td = "";
			String name = type.getName();

			if (name.equalsIgnoreCase("Integer")) { //$NON-NLS-1$
				td = "int"; //$NON-NLS-1$
			} else if (name.equalsIgnoreCase("Unlimited Natural")) { //$NON-NLS-1$
				td = "long"; //$NON-NLS-1$
			} else if (name.equalsIgnoreCase("Real")) { //$NON-NLS-1$
				td = "float"; //$NON-NLS-1$
			} else if (name.equalsIgnoreCase("String")) { //$NON-NLS-1$
				td = "String"; //$NON-NLS-1$
			} else {
				td = name.toLowerCase();
			}
			
			return td;
		}

		return ""; //$NON-NLS-1$
	}

	/**
	 * Return a kind of qualifiedName, except if
	 * - The named element has the stereotype External or NoCodeGen
	 * - The named element is part of the ANSI C library
	 * - The named element is a primitive type that has no further definition via a stereotype (TODO: why is this required/useful?)
	 *
	 * @param ne
	 * @param ns scope in which ne is used
	 * @return
	 */
	public static String javaQualifiedName(NamedElement ne, Element ns) {
		if (ne == null) {
			return "undefined"; //$NON-NLS-1$
		}
		Object owner = ne.getOwner();
		String owningPkgName = ""; //$NON-NLS-1$
		if (owner instanceof Package) {
			owningPkgName = ((Package) owner).getName();
		}
		if (GenUtils.hasStereotype(ne, External.class) ||
				GenUtils.hasStereotypeTree(ne, NoCodeGen.class) ||
				GenUtils.hasStereotypeTree(ne, ExternLibrary.class)) {
			return ne.getName();
		}
		else if (owningPkgName.equals(JAVA_LIB)) {
			// always use the short name for types within the ANSI C library
			return ne.getName();
		} else if (ne instanceof PrimitiveType) {
			String stdType = getStdtypes((PrimitiveType) ne);
			if (!stdType.isEmpty()) {
				return stdType;
			}
		}
		else if (owner instanceof ClassifierTemplateParameter) {
			// return short name for template in Type
			return ne.getName();
		}
		
		// Get qualified name and remove root name if root is a <<Project>>
		String qName = GenUtils.getFullName(ne, ".", false);
		
		// First check that the ne is not a direct inner class of ns
		// Also check that ne doesnt have the same short name as a direct inner class of ns
		if (ns instanceof Classifier) {
			Classifier classifier = (Classifier) ns;
			for (Element directlyOwnedElement : classifier.getOwnedElements()) {
				if (directlyOwnedElement instanceof Enumeration
						|| directlyOwnedElement instanceof Interface
						|| directlyOwnedElement.eClass().equals(UMLFactory.eINSTANCE.getUMLPackage().getClass_())) {
					if (((Classifier) directlyOwnedElement).getQualifiedName().equals(ne.getQualifiedName())) {
						return ne.getName();
					} else if (((Classifier) directlyOwnedElement).getName().equals(ne.getName())) {
						return qName;
					}
				}
			}
		}
		
		// Then check that ne hasn't been imported
		EList<String> importsOfCurrentNs = imports.get(currentNE);
		if (importsOfCurrentNs != null) {
			for (String importOfCurrentNs : importsOfCurrentNs) {
				if (importOfCurrentNs.equals(qName)) {
					// ne is imported in the current ns, so we use its short name
					return ne.getName();
				}
			}
		}
		
		// We return the qualified name of ne otherwise
		return qName;		
	}


	/**
	 * Returns the string that is used within a Java template declaration, e.g. the "Class XY" in template<class XY>.
	 *
	 * @return the template type formated as string
	 */
	public static String getTemplateTypeName(TemplateParameter templateParam) {
		String name = ""; //$NON-NLS-1$

		// Retrieve name of the ParameteredElement (when possible = it is a NamedElement
		ParameterableElement pElt = templateParam.getParameteredElement();
		if ((pElt != null) && (pElt instanceof NamedElement)) {
			name = ((NamedElement) pElt).getName();
		} else {
			name = "undefined"; //$NON-NLS-1$
		}

		return (name);
	}


	/**
	 * Return a Java namespace definition for a named element
	 *
	 * @param ne
	 *            a named element
	 * @return a Java namespace definition for a named element
	 */
	public static String getNamespace(NamedElement ne) {
		String namespace = ""; //$NON-NLS-1$
		for (Namespace ns : ne.allNamespaces()) {
			if (ns.getOwner() != null) {
				String nsName = ns.getName();
				if (!namespace.equals("")) { //$NON-NLS-1$
					nsName += "::"; //$NON-NLS-1$
				}
				namespace = nsName + namespace;
			}
		}
		if (!namespace.equals("")) { //$NON-NLS-1$
			namespace = "\n" + "using namespace " + namespace + ";\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return namespace;
	}

	/**
	 * Update the currentNS (used to organize imports and types)
	 * @param ne
	 *            a named element
	 */
	public static void openNS(NamedElement ne) {
		currentNE = ne;
		currentNS = ne.getNamespace();
	}
}
