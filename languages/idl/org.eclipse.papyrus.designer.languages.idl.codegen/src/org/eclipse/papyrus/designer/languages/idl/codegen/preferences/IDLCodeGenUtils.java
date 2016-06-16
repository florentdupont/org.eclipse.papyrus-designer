/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ansgar Radermacher - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.idl.codegen.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.languages.idl.codegen.Activator;

/**
 * Utility class that returns the preference values
 * 
 * @author ansgar
 * 
 */

public class IDLCodeGenUtils {

	protected static IPreferenceStore preferenceStore = null;

	public static String getIDLSuffix() {
		initPreferenceStore();
		return preferenceStore.getString(IDLCodeGenConstants.P_IDL_SUFFIX);
	}

	public static String getCommentHeader() {
		initPreferenceStore();
		return preferenceStore.getString(IDLCodeGenConstants.P_IDL_COMMENT_HEADER);
	}

	public static void initPreferenceStore() {
		if(preferenceStore == null) {
			preferenceStore = Activator.getDefault().getPreferenceStore();
		}
	}
}
