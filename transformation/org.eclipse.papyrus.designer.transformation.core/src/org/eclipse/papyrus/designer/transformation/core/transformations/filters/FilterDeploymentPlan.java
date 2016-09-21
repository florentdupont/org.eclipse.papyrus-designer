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

package org.eclipse.papyrus.designer.transformation.core.transformations.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;

/**
 * This filter removes the deployment plan stereotype. The objective is to
 * avoid creating a copy of transformation chain (referenced via the deployment plan)
 * in the target model
 */
public class FilterDeploymentPlan implements PreCopyListener {

	public static FilterDeploymentPlan getInstance() {
		if (instance == null) {
			instance = new FilterDeploymentPlan();
		}
		return instance;
	}
	
	@Override
	public EObject preCopyEObject(LazyCopier copier, EObject sourceEObj) {
		if (sourceEObj instanceof DeploymentPlan) {
			return null;
		}
		return sourceEObj;
	}


	private static FilterDeploymentPlan instance = null;
}
