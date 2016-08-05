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

package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * Some functions around stereotype usage.
 *
 * @author ansgar
 *
 */
public class StUtil {

	/**
	 * Copy the stereotype application from a source to a destination element. The
	 * function will also copy the stereotype attributes.
	 *
	 * @param source
	 * @param destination
	 * @return
	 */
	public static void copyStereotypes(Element source, Element destination) {
		for (Stereotype stereotype : source.getAppliedStereotypes()) {
			copyAttributes(stereotype.getQualifiedName(), source, destination);
		}
	}

	/**
	 * copy stereotype attributes.
	 *
	 * TODO: verify if it is necessary to distinguish between stereoSource and stereoDest
	 * (they should be identical, if the resource sets are).
	 * => simplify code
	 *
	 * @param stereotypeName
	 * @param source
	 * @param destination
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static boolean copyAttributes(String stereotypeName, Element source, Element destination) {
		Stereotype stereoSource = source.getAppliedStereotype(stereotypeName);
		Stereotype stereoDest = destination.getApplicableStereotype(stereotypeName);

		if ((stereoSource == null) || (stereoDest == null)) {
			return false;
		}
		Stereotype alreadyApplied = destination.getAppliedStereotype(stereotypeName);
		if (alreadyApplied == null) {
			// only apply stereotype, if not already applied
			destination.applyStereotype(stereoDest);
		}

		// getAllAttributes? (but have to avoid attribute base_Class which resets stereotype application)
		for (Property attribute : stereoSource.getAllAttributes()) {
			String attrName = attribute.getName();

			if (attrName.length() >= 5) {
				// do not copy base_ stereotypes (base_class, base_package and base_PackageImport)
				if (attrName.startsWith(Extension.METACLASS_ROLE_PREFIX)) {
					continue;
				}
			}
			// do not copy derived attributes
			if (attribute.isDerived()) {
				continue;
			}

			Object value = source.getValue(stereoSource, attrName);
			if (value instanceof EList) {
				// copy list
				EList<Object> newList = new BasicEList<Object>();
				for (Object valueEl : (EList<Object>) value) {
					newList.add(valueEl);
				}
				if (newList.size() > 0) {
					destination.setValue(stereoDest, attrName, newList);
				}
			}
			else {
				destination.setValue(stereoDest, attrName, value);
			}
		}

		return true;
	}
}
