/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.common.base;

/**
 * Constant to determine whether the system runs headless (should not create UI dialogs
 * that are waiting for user input)
 */
public class TestInfo {

	public static final String PAPYRUS_RUN_HEADLESS = "papyrus.run-headless"; //$NON-NLS-1$

	/**
	 * check whether the system is running headless
	 * @return true, iff running headless
	 */
	public static boolean runsHeadless() {
		return Boolean.getBoolean(PAPYRUS_RUN_HEADLESS);
	}
	
	/**
	 * Indicate that system is running headless
	 */
	public static void runHeadless() {
		System.setProperty(TestInfo.PAPYRUS_RUN_HEADLESS, Boolean.TRUE.toString());
	}
}
