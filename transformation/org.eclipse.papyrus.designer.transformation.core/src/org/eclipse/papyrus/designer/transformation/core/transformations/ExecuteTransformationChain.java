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


import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;

/**
 * This class executes a complete transformation chain. It executes the transformation in
 * the order specified in the transformation chain.
 */
public class ExecuteTransformationChain {

	public static void apply(M2MTrafoChain chain) throws TransformationException {
		ExecuteTransformation.apply(
				chain.getBase_Class().getAllAttributes().iterator());
	}
}
