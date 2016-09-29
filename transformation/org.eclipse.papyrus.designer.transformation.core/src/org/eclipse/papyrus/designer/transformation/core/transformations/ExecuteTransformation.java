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

import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoModelSplit;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Execute a transformation for each element of the model
 */
public class ExecuteTransformation {
		
	public static final String MODEL = "model"; //$NON-NLS-1$

	public static void apply(Iterator<Property> m2mPropertyIter) throws TransformationException {
		if (!m2mPropertyIter.hasNext()) {
			return;
		}
		Property m2mTrafoRef = m2mPropertyIter.next();
		M2MTrafo m2mTrafo = UMLUtil.getStereotypeApplication(m2mTrafoRef.getType(), M2MTrafo.class);

		if (m2mTrafo != null) {
			IM2MTrafo eTrafo = M2MTrafoExt.getM2MTrafo(m2mTrafo);
			Package deploymentPlan = TransformationContext.current.deploymentPlan;
			if (eTrafo instanceof IM2MTrafoElem) {
				ApplyRecursive ar = new ApplyRecursive(m2mTrafo, (IM2MTrafoElem) eTrafo);
				ar.applyRecursive(PackageUtil.getRootPackage(deploymentPlan));
				apply(m2mPropertyIter);
			}
			else if (eTrafo instanceof IM2MTrafoCDP) {
				((IM2MTrafoCDP) eTrafo).applyTrafo(m2mTrafo, deploymentPlan);
				apply(m2mPropertyIter);
			}
			else if (eTrafo instanceof IM2MTrafoModelSplit) {
				EList<TransformationContext> newContexts = ((IM2MTrafoModelSplit) eTrafo).splitModel(m2mTrafo, deploymentPlan);
				// create list of remaining M2M transformations
				EList<Property> remainingTrafos = new BasicEList<Property>();
				while (m2mPropertyIter.hasNext()) {
					remainingTrafos.add(m2mPropertyIter.next());
				}
				// now apply to each create sub-models
				for (TransformationContext newContext : newContexts) {
					TransformationContext.current = newContext;
					apply(remainingTrafos.iterator());
					
					// setURIs and save (first update all URIs to ensure that model references use the
					// correct URI before saving)

					String path = newContext.mm.getPath(newContext.project, MODEL, null);
					newContext.mm.setURI(path);
					// also save & dispose additional projects
					for (ModelManagement mm : newContext.copier.getAdditionalRootPkgs()) {
						String pathAdds = mm.getPath(newContext.project, MODEL, null);
						mm.setURI(pathAdds);
					}
					newContext.mm.save();
					for (ModelManagement mm : newContext.copier.getAdditionalRootPkgs()) {
						mm.save();
					}
					newContext.mm.dispose();
					for (ModelManagement mm : newContext.copier.getAdditionalRootPkgs()) {
						mm.dispose();
					}		
				}
			}
			else {
				throw new TransformationException("Unknown (not registered) model-to-model transformation: " + m2mTrafo.toString());
			}
		}
		else {
			throw new TransformationException(String.format(
					"type of element %s in chain does not have M2MTrafo stereotype applied.", m2mTrafoRef.getName()));
		}
	}
}
