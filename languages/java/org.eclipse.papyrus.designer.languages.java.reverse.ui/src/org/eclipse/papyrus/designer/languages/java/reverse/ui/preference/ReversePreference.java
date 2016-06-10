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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Activator;

/**
 * Provide reverse eclipse preferences values
 * 
 * @author Jonathan Geoffroy
 *
 */
public class ReversePreference {

	/**
	 * allow to gather default value
	 */
	private IPreferenceStore store = Activator.getDefault().getPreferenceStore();

	/**
	 * 
	 * @return an array containing each search path values
	 */
	public String[] getSearchPath() {
		String searchPath = store.getString(PreferenceConstants.P_SEARCH_PATH);
		return searchPath.split(";");
	}

	/**
	 * 
	 * @return an array containing each creation path values
	 */
	public String[] getCreationPath() {
		String creationPath = store.getString(PreferenceConstants.P_CREATION_PATH);
		return creationPath.split(CreationPathListEditor.SPLIT_STRING);
	}
}
