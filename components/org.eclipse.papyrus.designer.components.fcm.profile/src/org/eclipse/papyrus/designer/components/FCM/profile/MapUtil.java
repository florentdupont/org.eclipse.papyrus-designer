/**
 * Copyright (c) 2013 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - Initial API and implementation
 *
 */

package org.eclipse.papyrus.designer.components.FCM.profile;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.components.FCM.DerivedElement;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.FCM.TemplatePort;
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


/**
 * This class is responsible for creating derived types associated with ports. The derived
 * type is the class that will type the port and contain imported and used interfaces depending
 * on the FCM type and kind.
 * A particular question related to the derived types is into which package we can place them
 * within the type hierarchy:
 * (1) Of course, the package must be writable. That means, it must not belong to an imported library.
 * (2) The source type must be clearly identifiable.
 * (3) If a template get's instantiated, derived types within (if we place them there) might
 * need (re-) instantiation. Imagine AMI_ interface that contains some of I's method. The creation of an AMI_I type within the template
 * 
 * Therefore, a global "derived types" package is created within the model that owns the port. This
 * package contains the full folder hierarchy of the original type and places the elements there.
 * TODO: Align with package template instantiation => create type specific sub-folder, e.g. kind_type. Use package specific side-folders.
 * Need specific solution for elements within template (placing into same template is finally not a good idea).
 * Idea of package template (and the possibility to extend) was, that common elements are instantiated once. Thus, we will have interfaceBased_<intfName>/UseInterface/Use.cpp,
 * not UseInterface_<intfName>/Use.cpp
 * [Users choice to define template with additional package or not?]
 */
public class MapUtil {

	private static final String DERIVED_TYPES = "derivedTypes"; //$NON-NLS-1$

	// specific treatment of "root" model that is created by eC3M for deployment
	// It avoids that copies of derived interfaces are created at different places
	// [better alternative: avoid problem by using set of models instead of single large one]
	// [other option?: no-update flag?]
	public static final String rootModelName = "root"; //$NON-NLS-1$

	public static final String MAPPING_RULE_ID = "fcmPortMappingRule"; //$NON-NLS-1$

	public enum CREATE_KIND {
		CLASS, INTERFACE, NONE
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
			} else if ((owner instanceof Package) && (owner.getOwner() == null) &&
					((Package) owner).getName().equals(rootModelName)) {
				// Hack: assure that no new derived interface folder is created in "root" model
				// that eC3M creates for deployment
				if (element instanceof Package) {
					// return (Package)element;
				}
			}
			if ((element instanceof Package) && (getSignature((Package) element) != null)) {
				return (Package) element;
			}
			element = owner;
		}
		return null;
	}

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

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Class getDerivedClass(Port port, String prefix) {
		Type type = getOrCreateDerivedTypeIntern(port, prefix, port.getType(), CREATE_KIND.NONE);
		return type instanceof Class ? (Class) type : null;
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Class getDerivedClass(Port port, String prefix, boolean update) {
		return update ? getOrCreateDerivedClass(port, prefix) : getDerivedClass(port, prefix);
	}

	/**
	 * Get a derived interface of a port. The interface is searched within the derived types
	 * folder based on the passed prefix
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            FCM port type
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Interface getDerivedInterface(Port port, String prefix) {
		Type type = getOrCreateDerivedTypeIntern(port, prefix, port.getType(), CREATE_KIND.NONE);
		return type instanceof Interface ? (Interface) type : null;
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Interface getDerivedInterface(Port port, String prefix, boolean update) {
		return update ? getOrCreateDerivedInterface(port, prefix) : getDerivedInterface(port, prefix);
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @param createKind
	 *            if non NONE, create either an interfaces or a class, if it does not exist yet
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Class getOrCreateDerivedClass(Port port, String prefix) {
		Type type = getOrCreateDerivedTypeIntern(port, prefix, port.getType(), CREATE_KIND.CLASS);
		return type instanceof Class ? (Class) type : null;
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Interface getOrCreateDerivedInterface(Port port, String prefix) {
		Type type = getOrCreateDerivedTypeIntern(port, prefix, port.getType(), CREATE_KIND.INTERFACE);
		return type instanceof Interface ? (Interface) type : null;
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
		boolean first = true;
		for (int i = list.size() - 1; i >= 0; i--) {
			Namespace ns = list.get(i);

			if (first) {
				first = false;
				// Hack: if rootModel is used, skip top
				// (avoid that new derived interface with "root" prefix is created)
				if (ns.getName().equals(rootModelName)) {
					continue;
				}
			}
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
	 * Get or create a derived interface for a port using the name given by concatenation of prefix and
	 * type name
	 *
	 * @param port
	 *            The port for which to create a derived interface. Only used to determine the place where the
	 *            derived interface is placed
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @param createKind
	 *            if non NONE, create either an interfaces or a class, if it does not exist yet
	 * @return
	 */
	private static Type getOrCreateDerivedTypeIntern(Port port, String prefix, Type type, CREATE_KIND createKind) {
		String typeName = prefix + type.getName();

		// create derived element in "derivedInterface" package within the model owning
		// the port (which must be an FCM model, since the port carries the FCM stereotype)
		Package baseModelOfPort = getTop(port.getBase_Port());
		// handle specific case of type within template. Place derived type into same template.
		Package derivedTypes = getAndCreate(baseModelOfPort, DERIVED_TYPES, createKind != CREATE_KIND.NONE);
		if (derivedTypes != null) {
			Package owner = getAndCreate(derivedTypes, type.allNamespaces(), createKind != CREATE_KIND.NONE);
			Interface intf = null;

			PackageableElement pe = owner.getPackagedElement(typeName);
			if (pe instanceof Type) {
				// type already exists
				return (Type) pe;
			} else if (createKind == CREATE_KIND.INTERFACE) {
				// System.out.println ("Derived port types: create new interface " + typeName + " in package " + owner.getQualifiedName ());
				intf = owner.createOwnedInterface(typeName);

				// System.out.println ("Derived port types: Apply derived stereotype annotation to interface: " + intf.getQualifiedName());
				DerivedElement de = applyDE(intf);
				if (de != null) {
					// de may be null, if FCM is not properly applied
					de.setSource(type);
				}
				return intf;
			} else if (createKind == CREATE_KIND.CLASS) {
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

	public static void addRealization(Class portType, Interface providedInterface) {
		if (!portType.getImplementedInterfaces().contains(providedInterface)) {
			portType.createInterfaceRealization(null, providedInterface);
		}
	}

	public static void addUsage(Class portType, Interface usedInterface) {
		if (!portType.getUsedInterfaces().contains(usedInterface)) {
			portType.createUsage(usedInterface);
		}
	}

	/**
	 * Calculate derived required interface in function of port type and kind
	 *
	 * @param port
	 *            the port, for which the calculation should be done
	 * @return
	 */
	public static Type getDerivedType(final Port port) {
		if (port.getBase_Port() == null) {
			// should not happen, but can occur in case of corrupted XMI files
			return null;
		}
		PortKind portKind = port.getKind();
		if (portKind != null) {
			String ruleName = portKind.isExtendedPort() ? "ExtendedPort" : portKind.getBase_Class().getName(); //$NON-NLS-1$
			final IMappingRule mappingRule = getMappingRule(ruleName);
			if (mappingRule != null) {
				return mappingRule.calcDerivedType(port, false);
			}
		}
		return null;
	}


	/**
	 * Obtain the mapping rule for a port when the name of the portKind is given
	 *
	 * @param portKindName
	 *            the name of the port-kind
	 *
	 * @return the mapping rule or null, if no rule could be found
	 */
	public static IMappingRule getMappingRule(String portKindName) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(Activator.PLUGIN_ID + "." + MAPPING_RULE_ID); //$NON-NLS-1$
		for (IConfigurationElement configElement : configElements) {
			try {
				final String extPortKindName = configElement.getAttribute("portKindName"); //$NON-NLS-1$
				if (extPortKindName.equals(portKindName)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof IMappingRule) {
						return (IMappingRule) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Get the mapping rule for a port
	 *
	 * @param port
	 *            the FCM port
	 * @return the mapping rule or null, if no rule could be found
	 */
	public static IMappingRule getMappingRule(final Port port) {
		if (port.getBase_Port() == null) {
			// should not happen, but can occur in case of corrupted XMI files
			return null;
		}
		PortKind portKind = port.getKind();
		if (portKind == null) {
			return null;
		}
		if (portKind.getBase_Class() != null) {
			String ruleName = portKind.isExtendedPort() ? "ExtendedPort" : portKind.getBase_Class().getName(); //$NON-NLS-1$
			if (port instanceof TemplatePort) {
				ruleName = "TemplatePort"; //$NON-NLS-1$
			}
			return getMappingRule(ruleName);
		}
		return null;
	}

	public static PortKind getBoundType(final Port port) {
		if (port.getBase_Port() == null) {
			// should not happen, but can occur in case of corrupted XMI files
			return null;
		} else {
			String ruleName = "TemplatePort"; //$NON-NLS-1$
			final IMappingRule mappingRule = getMappingRule(ruleName);
			if (mappingRule instanceof ITemplateMappingRule) {
				return ((ITemplateMappingRule) mappingRule).getBoundType(port);
			}
		}
		return null;
	}

	/**
	 * Update the derived interfaces of a port this operation needs to be
	 * called in the context of an update command (transaction).
	 *
	 * @param port
	 */
	public static void update(final Port port) {
		final IMappingRule mappingRule = getMappingRule(port);

		if (mappingRule != null) {
			Type type = mappingRule.calcDerivedType(port, true);
			port.getBase_Port().setType(type);
			if (mappingRule instanceof ITemplateMappingRule) {
				((ITemplateMappingRule) mappingRule).updateBinding(port);
			}
		}
	}

	/**
	 * Check whether a port needs to be updated, since its derived interfaces
	 * are either not existing or out of date.
	 *
	 * @param port
	 * @return
	 */
	public static boolean needsUpdate(final Port port) {
		final IMappingRule mappingRule = getMappingRule(port);
		if (mappingRule != null) {
			return mappingRule.needsUpdate(port);
		}
		return false;
	}
}
