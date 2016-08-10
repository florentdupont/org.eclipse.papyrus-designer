/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralNull;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;

public class CopyUtils {
	/**
	 * Copy the (XML) ID from the source to the destination model element. This
	 * is useful, if you want to create a diagram for (parts of) the generated
	 * target model. Otherwise, the IDs would change with each generation and
	 * references from the diagram to model elements would break (of course,
	 * they could still break, for instance if structural modifications of the
	 * source model are made).
	 *
	 * @param source
	 *            source model element
	 * @param dest
	 *            corresponding target model element
	 */
	public static void copyID(EObject source, EObject target) {
		copyID(source, target, ""); //$NON-NLS-1$
	}

	/**
	 * Copy the (XML) ID from the source to the destination model element.
	 * Prefix the ID with a string passed as parameter
	 *
	 * @param source
	 *            source model element
	 * @param dest
	 *            corresponding target model element
	 * @param prefix
	 *            a prefix for the target model ID
	 */
	@SuppressWarnings("deprecation")
	public static void copyID(EObject source, EObject target, String prefix) {
		Resource resourceSource = source.eResource();
		Resource resourceTarget = target.eResource();
		// TODO: use EcoreUtil getURI (InternalEObject) instead?

		if ((resourceSource instanceof XMLResource) && (resourceTarget instanceof XMLResource)) {
			XMLResource xmlResSource = (XMLResource) resourceSource;
			XMLResource xmlResTarget = (XMLResource) resourceTarget;
			String id = prefix + xmlResSource.getID(source);
			int counter = 0;
			String uniqueID = id;
			while (xmlResTarget.getIDToEObjectMap().containsKey(uniqueID)) {
				uniqueID = id + counter;
				counter++;
			}
			xmlResTarget.setID(target, uniqueID);
		}
	}

	public static ValueSpecification copyDefaultValue(Property source, Property target) {
		ValueSpecification value = source.getDefaultValue();
		if (value != null) {
			ValueSpecification newValue = target.createDefaultValue(value.getName(), value.getType(), value.eClass());
			return copyValue(value, newValue);
		} else {
			return null;
		}
	}

	// TODO: the functions that follow are static and should not be part of this class
	// as they use a different way of copying things.
	
	
	public static void copyFeatureModifiers(Feature source, Feature target) {
		target.setIsStatic(source.isStatic());
		target.setIsLeaf(source.isLeaf());
	}

	public static void copyMultElemModifiers(MultiplicityElement source, MultiplicityElement target) {
		target.setIsOrdered(source.isOrdered());
		target.setIsUnique(source.isUnique());
		target.setLower(source.getLower());
		target.setUpper(source.getUpper());
	}

	/**
	 * Copy a value to a target slot
	 *
	 * @param smValue
	 * @param target
	 * @return
	 */
	public static ValueSpecification copyValue(ValueSpecification value, Slot target) {
		ValueSpecification newValue = target.createValue(value.getName(), value.getType(), value.eClass());
		return copyValue(value, newValue);
	}

	public static ValueSpecification copyValue(ValueSpecification smValue, ValueSpecification tmValue) {
		if (smValue instanceof OpaqueExpression) {
			OpaqueExpression oeValue = (OpaqueExpression) smValue;
			OpaqueExpression noeValue = (OpaqueExpression) tmValue;
			for (String language : oeValue.getLanguages()) {
				noeValue.getLanguages().add(language);
			}
			for (String body : oeValue.getBodies()) {
				noeValue.getBodies().add(body);
			}
		} else if (smValue instanceof LiteralString) {
			((LiteralString) tmValue).setValue(((LiteralString) smValue).getValue());
		} else if (smValue instanceof LiteralInteger) {
			((LiteralInteger) tmValue).setValue(((LiteralInteger) smValue).getValue());
		} else if (smValue instanceof LiteralUnlimitedNatural) {
			((LiteralUnlimitedNatural) tmValue).setValue(((LiteralUnlimitedNatural) smValue).getValue());
		} else if (smValue instanceof LiteralBoolean) {
			((LiteralBoolean) tmValue).setValue(((LiteralBoolean) smValue).booleanValue());
		} else if (smValue instanceof LiteralNull) {
		}
		return tmValue;
	}
}
