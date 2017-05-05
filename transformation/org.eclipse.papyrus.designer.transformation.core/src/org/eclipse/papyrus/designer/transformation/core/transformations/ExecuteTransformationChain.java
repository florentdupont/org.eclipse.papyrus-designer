/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
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

package org.eclipse.papyrus.designer.transformation.core.transformations;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * This class executes a complete transformation chain. It executes the transformation in
 * the order specified in the transformation chain.
 */
public class ExecuteTransformationChain {

	public static void apply(M2MTrafoChain chain, List<M2MTrafo> additionalTrafos) throws TransformationException {
		ArrayList<M2MTrafo> transformations = new ArrayList<M2MTrafo>();
		for (Property m2mTrafoRef : chain.getBase_Class().getAllAttributes()) {
			M2MTrafo trafo = UMLUtil.getStereotypeApplication(m2mTrafoRef.getType(), M2MTrafo.class);
			if (trafo != null) {
				transformations.add(trafo);
			}
			else {
				throw new TransformationException(String.format(
						"type of element %s in chain does not have M2MTrafo stereotype applied.", m2mTrafoRef.getName()));
			}
		}
		
		// instead of fully resolving constraints, we assume that the before/after conditions are not both
		// specified and only relate to elements already in the original transformation chain.
		for (M2MTrafo additionalTrafo : additionalTrafos) {
			if (additionalTrafo.getBefore() != null) {
				int index = transformations.indexOf(additionalTrafo.getBefore());
				if (index >= 0) {
					transformations.add(index, additionalTrafo);
				}
			}
			else if (additionalTrafo.getAfter() != null) {
				int index = transformations.indexOf(additionalTrafo.getAfter());
				if (index >= 0) {
					transformations.add(index+1, additionalTrafo);
				}
			}
			else {
				transformations.add(additionalTrafo);
			}
		}

		ExecuteTransformation.apply(transformations.iterator());
	}
}
