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

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Package;

public interface IM2MTrafoModelSplit extends IM2MTrafo {
	/**
	 * Split a model into several sub-models (on which sub-sequent transformations are applied)
	 * @param trafo
	 * @param deploymentPlan
	 * @return
	 * @throws TransformationException
	 */
	public EList<TransformationContext> splitModel(M2MTrafo trafo, Package deploymentPlan) throws TransformationException;
}
