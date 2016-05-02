/*****************************************************************************
 * Copyright (c) 2014 Jonathan Geoffroy
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Jonathan Geoffroy	geoffroy.jonathan@gmail.com		initial implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.languages.java.reverse.ui.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Activator;

/**
 * Initialize reverse eclipse preference by default values.
 * 
 * @author Jonathan Geoffroy
 *
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public PreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		store.setDefault(PreferenceConstants.P_SEARCH_PATH, "java;*;osgi.*;datatype");
		store.setDefault(PreferenceConstants.P_CREATION_PATH, "java.* ; ; java" 
		        + CreationPathListEditor.SPLIT_STRING + "org.eclipse.papyrus.* ; ; *" 
				+ CreationPathListEditor.SPLIT_STRING + "org.eclipse.* ; org.eclipse.papyrus.* ; *" 
		        + CreationPathListEditor.SPLIT_STRING + " org.osgi.* ; ; osgi.*" 
				+ CreationPathListEditor.SPLIT_STRING + " datatype.* ; ; datatype");
	}
}
