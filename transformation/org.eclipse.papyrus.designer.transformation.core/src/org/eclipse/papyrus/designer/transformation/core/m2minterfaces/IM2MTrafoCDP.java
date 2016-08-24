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
 */

package org.eclipse.papyrus.designer.transformation.core.m2minterfaces;

import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Package;

/**
 * Interface that must be implemented by M2M transformations
 */
public interface IM2MTrafoCDP extends IM2MTrafo {

	/**
	 * Apply a transformation on a deployment plan.
	 *
	 * @param trafo A model-to-model transformation.
	 * @param deploymentPlan The deployment plan
	 * @throws TransformationException
	 */
	public void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException;
}
