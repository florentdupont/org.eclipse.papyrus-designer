/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
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
package org.eclipse.papyrus.designer.transformation.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.papyrus.designer.transformation.base.preferences.PapyrusDesignerPreferenceConstants;
import org.eclipse.papyrus.designer.transformation.core.Activator;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This class represents the EC3M preference page
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That way, preferences can be accessed directly via the preference store.
 */

public class PapyrusDesignerPreferencePage
		extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public PapyrusDesignerPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Papyrus designer options (for component-based design)");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	@Override
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(
				PapyrusDesignerPreferenceConstants.P_SHOW_PROV_REQ_AS_PORT_ICONS,
				"&Show icons associated with derived provided/required interfaces instead of kind",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(
				PapyrusDesignerPreferenceConstants.P_TREAT_NONE_AS_COMPOSITE,
				"Treat aggregation kind \"&None\" as \"Composite\"",
				getFieldEditorParent()));

		addField(new BooleanFieldEditor(
				PapyrusDesignerPreferenceConstants.P_ALL_ATTRIBUTES_ARE_CONFIG_ATTRIBUTES,
				"Treat all attributes as configuration attributes (if false, only attributes of abstract classes and attributes explicitly tagged as FCM::ConfigurationProperty will be considered as configuration attribute",
				getFieldEditorParent()));

		addField(new StringFieldEditor(
				PapyrusDesignerPreferenceConstants.P_CODE_GEN_PREFIX,
				"Code generation prefix: ", getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}
}
