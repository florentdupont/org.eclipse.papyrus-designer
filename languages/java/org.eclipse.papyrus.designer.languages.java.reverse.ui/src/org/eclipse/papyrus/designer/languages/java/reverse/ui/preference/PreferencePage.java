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

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Activator;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Define a new section into eclipse preferences. Create a page for reverse eclipse preferences.
 * 
 * @author Jonathan Geoffroy
 *
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public PreferencePage() {
		super(GRID);
	}

	public PreferencePage(int style) {
		super(style);
	}

	public PreferencePage(String title, int style) {
		super(title, style);
	}

	public PreferencePage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Edit your preferences to reverse java project into papyrus model");
	}

	@Override
	protected void createFieldEditors() {
		// Search path field
		addField(new SearchPathListEditor(PreferenceConstants.P_SEARCH_PATH, "Default search path", getFieldEditorParent()));

		// Creation path field
		addField(new CreationPathListEditor(PreferenceConstants.P_CREATION_PATH, "Default creation path", getFieldEditorParent()));

	}

}
