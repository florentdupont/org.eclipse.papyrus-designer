/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.languages.java.codegen.Activator;

/**
 * Utility class that returns the preference values
 */
public class JavaCodeGenUtils {

	protected static IPreferenceStore preferenceStore = null;

	public static String getHeaderSuffix() {
		initPreferenceStore();
		return preferenceStore.getString(JavaCodeGenConstants.P_JAVA_SUFFIX);
	}

	public static String getCommentHeader() {
		initPreferenceStore();
		return preferenceStore.getString(JavaCodeGenConstants.P_COMMENT_HEADER);
	}

	public static void initPreferenceStore() {
		if (preferenceStore == null) {
			preferenceStore = Activator.getDefault().getPreferenceStore();
		}
	}

}
