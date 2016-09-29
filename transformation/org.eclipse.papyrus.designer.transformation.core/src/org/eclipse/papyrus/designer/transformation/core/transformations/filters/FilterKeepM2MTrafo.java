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
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;

/**
 * This filter keeps the stereotypes M2MTrafoChain and M2MTrafo as a source code reference.
 * This avoids that the deployment plan stereotype references to a copy of the transformation
 * chain.
 */
public class FilterKeepM2MTrafo implements PreCopyListener {

	public static FilterKeepM2MTrafo getInstance() {
		if (instance == null) {
			instance = new FilterKeepM2MTrafo();
		}
		return instance;
	}
	
	@Override
	public EObject preCopyEObject(LazyCopier copier, EObject sourceEObj) {
		if (sourceEObj instanceof M2MTrafoChain || sourceEObj instanceof M2MTrafo) {
			return LazyCopier.USE_SOURCE_OBJECT;
		}
		return sourceEObj;
	}


	private static FilterKeepM2MTrafo instance = null;
}
