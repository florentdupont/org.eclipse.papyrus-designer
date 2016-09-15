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

import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Package;

/**
 * The task of the boot-loader is twofold: create the instances of all
 * implementations (non-recursive).
 *
 */
public class BootLoaderGen implements IM2MTrafoCDP {

	@Override
	public void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
		// delegate to language specific variant
		String language = DepUtils.getLanguageFromElement(deploymentPlan);
		IM2MTrafo trafo2 = M2MTrafoExt.getM2MTrafo(trafo.getBase_Class().getQualifiedName() + "::" + language); //$NON-NLS-1$
		if (trafo2 instanceof IM2MTrafoCDP) {
			((IM2MTrafoCDP) trafo2).applyTrafo(trafo, deploymentPlan);
		}
	}
}
