/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.transformation.base;

import org.eclipse.uml2.uml.Element;

/**
 * A common interface for element filtering in dialogs
 */
public interface ElementFilter {
	public boolean acceptElement(Element element);
}
