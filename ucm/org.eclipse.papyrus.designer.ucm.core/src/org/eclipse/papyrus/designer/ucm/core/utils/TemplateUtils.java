/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
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

package org.eclipse.papyrus.designer.ucm.core.utils;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Type;

@Deprecated
/**
 * A set of template functions.
 *
 * Copied in parts from designer.core to avoid a dependency. Needs to be removed, once UCM is fully integrated
 * with Papyrus SW designer
 *
 */
public class TemplateUtils {

	/**
	 * Get the template signature of a classifier. If the classifier does not
	 * have a signature, examine if the owning class provides one
	 *
	 * @param template
	 *            The potential template
	 * @return the signature or null, if none can be found.
	 */
	public static TemplateSignature getSignature(TemplateableElement template) {
		TemplateSignature signature = getSignatureDirect(template);
		if (signature != null) {
			return signature;
		}

		// no signature found, try signature of owning classifier (nested class)
		// or package
		Element owner = template.getOwner();
		if (owner instanceof TemplateableElement) {
			// owned by a classifier, not a package
			return getSignature((TemplateableElement) owner);
		} else {
			return null;
		}
	}

	public static Element getTemplateOwner(Element ne, TemplateSignature signature) {
		if ((ne instanceof TemplateableElement) && getSignatureDirect((TemplateableElement) ne) == signature) {
			return ne;
		}
		Element owner = ne.getOwner();
		if (owner != null) {
			return getTemplateOwner(owner, signature);
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

	public static TemplateSignature getSignatureDirect(TemplateableElement template) {
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
	 * Return the actual for a potential formal parameter within a
	 * templateBinding (move to TemplateUtils?)
	 *
	 * @param binding
	 *            a template binding
	 * @param formal
	 *            a potential formal parameter, i.e. a parameter for which we
	 *            check, if it really corresponds to a formal parameter within
	 *            the template binding.
	 * @return the actual parameter that is associated with the potential formal
	 *         parameter, or null if the 2nd parameter does not correspond to a
	 *         formal parameter of the binding.
	 */
	public static Classifier getActualFromBinding(TemplateBinding binding, Type formal) {
		for (TemplateParameterSubstitution substitution : binding.getParameterSubstitutions()) {
			ParameterableElement pe = substitution.getFormal().getParameteredElement();
			if (pe == formal) {
				return (Classifier) substitution.getActual();
			}
		}
		return null;
	}

	public static Classifier getActualFromBinding(TemplateBinding binding, String formalName) {
		for (TemplateParameterSubstitution substitution : binding.getParameterSubstitutions()) {
			ParameterableElement pe = substitution.getFormal().getParameteredElement();
			if ((pe instanceof NamedElement) && ((NamedElement) pe).getName().equals(formalName)) {
				return (Classifier) substitution.getActual();
			}
		}
		return null;
	}

	/**
	 * Get the first actual from the binding.
	 *
	 * @param binding
	 *            the template binding
	 * @return the first actual.
	 */
	public static Classifier getFirstActualFromBinding(TemplateBinding binding) {
		for (TemplateParameterSubstitution substitution : binding.getParameterSubstitutions()) {
			ParameterableElement pe = substitution.getFormal().getParameteredElement();
			return (Classifier) substitution.getActual();
		}
		return null;
	}
}
