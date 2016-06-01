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

package org.eclipse.papyrus.designer.components.transformation.core.transformations.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.components.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier;
import org.eclipse.uml2.uml.StateMachine;

/**
 * This filter copies the effect of transitions to the class level.
 */
public class FilterStateMachines implements PreCopyListener {

	public static FilterStateMachines getInstance() {
		if (instance == null) {
			instance = new FilterStateMachines();
		}
		return instance;
	}
	
	@Override
	public EObject preCopyEObject(LazyCopier copier, EObject sourceEObj) {
		if (sourceEObj instanceof StateMachine) {
			return null;
		}
		return sourceEObj;
	}


	private static FilterStateMachines instance = null;
}
