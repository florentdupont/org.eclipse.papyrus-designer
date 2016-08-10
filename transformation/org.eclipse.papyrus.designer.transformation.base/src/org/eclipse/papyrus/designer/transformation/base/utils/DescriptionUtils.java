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

import org.eclipse.papyrus.designer.transformation.base.Messages;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;

/**
 * Set of static operations to retrieve the comment for an element
 */
public class DescriptionUtils {

	/**
	 * Return a description of an element. By default search comments that are
	 * owned by this elements and the annotated elements points to itself.
	 *
	 * @param element
	 *            the element for which to obtain a description
	 * @return the description of the element
	 */
	public static String getDescription(Element element) {
		return getDescription(element.getOwner(), element);
	}

	/**
	 * Convenience function: Return a description of an element. It will delegate to
	 * default getDescription, but returns a user specified text, if a description is
	 * not available in the model.
	 *
	 * @param element
	 * @param unavailable
	 *            The text that is returned, if no description is available
	 * @return
	 */
	public static String getDescription(Element element, String unavailable) {
		String description = getDescription(element);
		if (description != null) {
			return description;
		}
		else {
			return unavailable;
		}
	}

	/**
	 * Return a description or implementation description of an element, i.e. the
	 * first owned comment that annotated the passed element
	 *
	 * @param the
	 *            owner of the comment
	 *            the element for which to obtain a description
	 * @param annotated
	 *            element
	 *            the element for which to obtain a description
	 * @return the description of the element
	 */
	public static String getDescription(Element owner, Element annotatedElement) {
		if (owner == null) {
			return null;
		}
		// loop over all owned comments, check those that begins with
		// "description"
		for (Comment comment : owner.getOwnedComments()) {
			if (comment.getAnnotatedElements().contains(annotatedElement)) {
				return comment.getBody();
			}

		}
		return Messages.Description_NoAvail;
	}
}
