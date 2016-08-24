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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Execute a transformation for each element of the model
 */
public class ApplyRecursive {
		
	public ApplyRecursive(M2MTrafo stTrafo, IM2MTrafoElem transformation) {
		this.stTrafo = stTrafo;
		this.transformation = transformation;
	}

	public void applyRecursive(Package pkg) throws TransformationException {
		EList<PackageableElement> elements = new BasicEList<PackageableElement>();
		elements.addAll(pkg.getPackagedElements());
		for (PackageableElement pe : elements) {
			if (pe instanceof Classifier) {
				TransformationContext.current.classifier = (Classifier) pe;
				if (transformation != null) {
					// explicit transformation
					transformation.transformElement(stTrafo, pe);
				}
				else {
					// take transformation from stereotype
					ApplyTransformation applyTrafo = UMLUtil.getStereotypeApplication(pe, ApplyTransformation.class);
					if (applyTrafo != null) {
						// execute the set of transformations applied to the element
						for (M2MTrafo stTrafo : applyTrafo.getTrafo()) {
							IM2MTrafo trafo = M2MTrafoExt.getM2MTrafo(stTrafo);
							if (trafo instanceof IM2MTrafoElem) {
								((IM2MTrafoElem) trafo).transformElement(stTrafo, pe);
							}
						}
					}
				}
			} else if (pe instanceof Package) {
				// recurse
				applyRecursive((Package) pe);
			}
		}
	}

	protected M2MTrafo stTrafo;

	protected IM2MTrafoElem transformation;
}
