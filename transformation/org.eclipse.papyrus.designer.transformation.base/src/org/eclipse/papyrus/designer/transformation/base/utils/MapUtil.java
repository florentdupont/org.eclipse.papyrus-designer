/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Type;

public class MapUtil {

	/**
	 * Get or create a derived type for a given element
	 *
	 * @param element
	 *            An element for which the derived element should be created. Used to determine the model in which
	 *            to create the derived element
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @param createKind
	 *            if non NONE, create either an interfaces or a class, if it does not exist yet
	 * @return
	 */
	public enum ECreateKind {
		CLASS, INTERFACE, NONE
	}

	/**
	 * 
	 * @param element
	 * @param format a format string for the name of the new type. This name is obtained by call the String.format function and
	 * 	passing the original type name as parameter. E.g. the call can use a fixed name, if the format string does not contain
	 *  a %s directive, a %s at the end of the format string enables a fixed prefix and so on.
	 * @param type
	 * @param createKind
	 * @return
	 */
	public static Type getOrCreateDerivedType(Element element, String format, Type type, ECreateKind createKind) {
		String typeName = String.format(format, type.getName());
	
		// create derived element in "derivedInterface" package within the model owning
		// the port (which must be an FCM model, since the port carries the FCM stereotype)
		Package baseModelOfPort = getTop(element);
		// handle specific case of type within template. Place derived type into same template.
		Package derivedTypes = getAndCreate(baseModelOfPort, DERIVED_TYPES, createKind != ECreateKind.NONE);
		if (derivedTypes != null) {
			Package owner = getAndCreate(derivedTypes, type.allNamespaces(), createKind != ECreateKind.NONE);
			Interface intf = null;
	
			PackageableElement pe = owner.getPackagedElement(typeName);
			if (pe instanceof Type) {
				// type already exists
				return (Type) pe;
			} else if (createKind == ECreateKind.INTERFACE) {
				// System.out.println ("Derived port types: create new interface " + typeName + " in package " + owner.getQualifiedName ());
				intf = owner.createOwnedInterface(typeName);
	
				// System.out.println ("Derived port types: Apply derived stereotype annotation to interface: " + intf.getQualifiedName());
				DerivedElement de = applyDE(intf);
				if (de != null) {
					// de may be null, if FCM is not properly applied
					de.setSource(type);
				}
				return intf;
			} else if (createKind == ECreateKind.CLASS) {
				// System.out.println ("Derived port types: create new interface " + typeName + " in package " + owner.getQualifiedName ());
				Class newType = owner.createOwnedClass(typeName, false);
	
				// System.out.println ("Derived port types: Apply derived stereotype annotation to interface: " + intf.getQualifiedName());
				DerivedElement de = applyDE(newType);
				if (de != null) {
					// de may be null, if FCM is not properly applied
					de.setSource(type);
				}
				return newType;
			}
		}
	
		// instead of returning null, return a dummy interface that indicates the user that an element needs updating.
		PackageableElement portKinds = baseModelOfPort.getImportedMember("PortKinds"); //$NON-NLS-1$
		if (portKinds instanceof Package) {
			PackageableElement pe = ((Package) portKinds).getPackagedElement("Please update derived elements"); //$NON-NLS-1$
			if (pe instanceof Interface) {
				// dummy interface exists
				return (Interface) pe;
			}
		}
		return null;
	}

	/**
	 * Get the template signature of a templateable element (typically a package). The class must
	 * (1) either own the signature
	 * (2) or merge with a package which owns the signature.
	 * Qompass enables the "extension" of existing packages via the package merge mechanism
	 *
	 * @param template
	 *            The potential template
	 * @return the signature or null, if none can be found.
	 */
	public static TemplateSignature getSignature(TemplateableElement template) {
		for (Element element : template.getOwnedElements()) {
			if (element instanceof TemplateSignature) {
				return (TemplateSignature) element;
			}
		}
	
		// enable multiple package templates sharing the same signature.
		if (template instanceof Package) {
			Package pkg = (Package) template;
			for (PackageMerge pkgImport : pkg.getPackageMerges()) {
				Package importedPkg = pkgImport.getMergedPackage();
				return getSignature(importedPkg);
			}
		}
		return null;
	}

	/**
	 * Return the top-level owner of an element. This function returns the same value
	 * as getModel, if the top-level element is a model and not contained in a template
	 * While this is the case for models, model libraries have a top-level package (not
	 * a model). In this case, getTop returns the top-level package whereas getModel
	 * would return null.
	 * In case of a package owning a signature, it returns this package instead of the
	 * top level package. The reason is that the derived types might reference elements
	 * of the template signature which would be undefined outside.
	 *
	 * @param element
	 *            An element of which we want to query
	 * @return the top-level owning package
	 */
	public static Package getTop(Element element) {
		while (element != null) {
			Element owner = element.getOwner();
			if (owner == null) {
				if (element instanceof Package) {
					return (Package) element;
				}
			}
			if ((element instanceof Package) && (getSignature((Package) element) != null)) {
				return (Package) element;
			}
			element = owner;
		}
		return null;
	}

	private static final String DERIVED_TYPES = "derivedTypes"; //$NON-NLS-1$

	/**
	 * Apply the derived element stereotype to a model element
	 *
	 * @param element
	 * @return
	 */
	public static DerivedElement applyDE(Element element) {
		Stereotype stereotype = element.getApplicableStereotype("FCM::DerivedElement"); //$NON-NLS-1$
		if (stereotype != null) {
			EObject alreadyApplied = element.getStereotypeApplication(stereotype);
			if (alreadyApplied instanceof DerivedElement) {
				return (DerivedElement) alreadyApplied;
			}
			alreadyApplied = element.applyStereotype(stereotype);
			if (alreadyApplied instanceof DerivedElement) {
				return (DerivedElement) alreadyApplied;
			}
		}
		return null;
	}

	/**
	 * This function returns a Package reference that corresponds to a qualified name.
	 * Packages are created, if not existing yet - the function is thus a bit similar to
	 * the unix "mkdir -p" command. Note that the main model within the list of name-spaces is
	 * ignored, in order to avoid that a sub-package within a model starts with the name
	 * of the model.
	 *
	 * @param model
	 *            a model
	 * @param list
	 *            a list of namespace elements (as can be obtained via allNamespaces),
	 *            the top-level element is the last within the list.
	 * @param skipTop
	 *            if true, skip top level namespace element
	 * @return
	 */
	public static Package getAndCreate(Package root, EList<Namespace> list, boolean createOnDemand) {
		for (int i = list.size() - 1; i >= 0; i--) {
			Namespace ns = list.get(i);
	
			NamedElement pkg = root.getOwnedMember(ns.getName());
			if ((pkg == null) && createOnDemand) {
				// package does not exist => create it.
				pkg = root.createNestedPackage(ns.getName());
				// copy stereotype to create package
			}
			if (!(pkg instanceof Package)) {
				break;
			}
			root = (Package) pkg;
		}
		return root;
	}

	/**
	 * Get or create a package within another package (often the root of a model), i.e. return
	 * a package with the passed name, if it exists or create a new one of this name
	 *
	 * @param root
	 *            a package potentially owning a certain element
	 * @param name
	 *            the name of a package that should be be returned.
	 * @return a package
	 */
	public static Package getAndCreate(Package root, String name, boolean createOnDemand) {
		NamedElement pkg = root.getOwnedMember(name);
		if ((pkg == null) && createOnDemand) {
			pkg = root.createNestedPackage(name);
		}
		return (Package) pkg;
	}

}
