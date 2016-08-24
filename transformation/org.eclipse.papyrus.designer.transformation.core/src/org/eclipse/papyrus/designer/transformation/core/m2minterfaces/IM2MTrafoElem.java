/**
 * Copyright (c) 2016 CEA LIST
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
package org.eclipse.papyrus.designer.transformation.core.m2minterfaces;

import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Element;

/**
 * An interface for transformations that are typically applied to a subset of elements within the model.
 * The transformationElement is applied to all elements and decides itself for which elements
 * it actually executes a transformation.
 */
public interface IM2MTrafoElem extends IM2MTrafo {

	/**
	 * Transform an element
	 *
	 * @param trafo A model-to-model transformation.
	 * @param element The element to which the transformation could be applied
	 * @throws TransformationException
	 */
	public void transformElement(M2MTrafo trafo, Element element) throws TransformationException;
}
