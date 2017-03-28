/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;

/**
 * Provide the list of available policy definitions for a given component (filtering on applicability)
 *
 */
public class PortElementKindContentProvider implements IStaticContentProvider {

	@Override
	public Object[] getElements() {
		List<Object> results = new ArrayList<Object>();
		results.add(PortElementKind.PROVIDED);
		results.add(PortElementKind.REQUIRED);
		return results.toArray();
	}

	@Override
	public Object[] getElements(Object dummy) {
		return null;
	}
}
