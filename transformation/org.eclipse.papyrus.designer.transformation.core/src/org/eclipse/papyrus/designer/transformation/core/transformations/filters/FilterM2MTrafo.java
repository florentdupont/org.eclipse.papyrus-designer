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
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;


/**
 * Do not copy the M2MTrafo stereotype (references by ApplyTransformation stereotype). It would imply copying
 * the M2M definitions into the target model
 */
public class FilterM2MTrafo implements PreCopyListener {

	public static FilterM2MTrafo getInstance() {
		if (instance == null) {
			instance = new FilterM2MTrafo();
		}
		return instance;
	}

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {
		if (sourceEObj instanceof M2MTrafo) {
			return null;
		}
		return sourceEObj;
		
	}

	private static FilterM2MTrafo instance = null;
}
