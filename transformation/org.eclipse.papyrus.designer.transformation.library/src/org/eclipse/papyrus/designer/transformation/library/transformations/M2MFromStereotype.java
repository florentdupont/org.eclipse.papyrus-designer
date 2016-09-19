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
package org.eclipse.papyrus.designer.transformation.library.transformations;

import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Apply the set of transformations that are specified by the "ApplyTransformation" stereotype
 * on an element
 */
public class M2MFromStereotype implements IM2MTrafoElem {
	
	@Override
	public void transformElement(M2MTrafo trafo, Element element) throws TransformationException {
		ApplyTransformation applyTrafo = UMLUtil.getStereotypeApplication(element, ApplyTransformation.class);
		if (applyTrafo != null) {
			// execute the set of transformations applied to the element
			for (M2MTrafo stTrafoOnElem : applyTrafo.getTrafo()) {
				IM2MTrafo trafoOnElem = M2MTrafoExt.getM2MTrafo(stTrafoOnElem);
				if (trafoOnElem instanceof IM2MTrafoElem) {
					((IM2MTrafoElem) trafoOnElem).transformElement(stTrafoOnElem, element);
				}
			}
		}
	}
}
