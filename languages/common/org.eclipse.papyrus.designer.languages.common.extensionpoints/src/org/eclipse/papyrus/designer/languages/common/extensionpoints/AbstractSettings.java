/*******************************************************************************
 * Copyright (c) 2015 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.extensionpoints;

/**
 * Abstract superclass for project settings. It is empty to allow for arbitrary
 * project specific settings
 */
public abstract class AbstractSettings {
	/**
	 * the operating system for which code should be produced (will have a
	 * project specific effect, e.g. in case of CDT it will result in a #define)
	 */
	public String targetOS;
}
