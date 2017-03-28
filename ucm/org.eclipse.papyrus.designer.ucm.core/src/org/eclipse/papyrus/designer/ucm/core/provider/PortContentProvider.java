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

import org.eclipse.papyrus.infra.widgets.providers.AbstractStaticContentProvider;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;

/**
 * Provide the list of ports for a given component (including inherited ports)
 * @author ansgar
 *
 */
public class PortContentProvider extends AbstractStaticContentProvider implements IStaticContentProvider {

	public PortContentProvider(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object[] getElements() {
		List<Element> results = new ArrayList<Element>();
		for (Property attribute : clazz.getAllAttributes()) {
			if (attribute instanceof Port) {
				results.add(attribute);
			}
		}
		return results.toArray();
	}

	protected Class clazz;
}
