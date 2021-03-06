/**
 * Copyright (c) 2013 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - Initial API and implementation
 *
 */

package org.eclipse.papyrus.designer.components.FCM.profile;

import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.uml2.uml.Type;

/**
 * Interface used by extension point for evaluation of interface mappings
 *
 */
public interface IMappingRule {
	/**
	 * Calculate the derived portType
	 *
	 * @param p
	 *            the port
	 * @param update
	 *            if true, make model updates during calculation of derived interface
	 * @return the list of provided interfaces
	 */
	public Type calcDerivedType(Port p, boolean update);

	/**
	 * check whether a port needs to be updated (recalculation of its derived interfaces)
	 *
	 * @return true, if update is required, false otherwise
	 */
	public boolean needsUpdate(Port p);
}
