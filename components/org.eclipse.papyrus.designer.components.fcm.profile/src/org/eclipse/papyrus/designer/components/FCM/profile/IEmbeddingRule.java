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

import org.eclipse.papyrus.designer.components.FCM.Connector;
import org.eclipse.papyrus.designer.components.FCM.util.FCMUtil;
import org.eclipse.papyrus.designer.components.FCM.util.FCMUtil.RoleBindingTable;

/**
 * Interface used by extension point for embedding rules
 */
public interface IEmbeddingRule {
	/**
	 * Return a role binding table for a given connector
	 *
	 * @param connector
	 *            the connector
	 * @return role binding table
	 */
	public FCMUtil.RoleBindingTable getRoleBindings(Connector connector);

}
