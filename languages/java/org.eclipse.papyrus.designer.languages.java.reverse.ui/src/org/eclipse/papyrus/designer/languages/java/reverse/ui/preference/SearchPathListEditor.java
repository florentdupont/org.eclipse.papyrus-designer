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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.internal.ui.preferences.TypeFilterInputDialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * A ListEditor implementation. When user push "add" button, open a dialog box which can validate/invalidate the input value for Search Path. <br>
 * Used to add a Search Path value into Eclipse preferences
 * 
 * @author Jonathan Geoffroy
 *
 */
public class SearchPathListEditor extends ListEditor {

	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param labelText
	 * @param parent
	 */
	public SearchPathListEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
	}

	@SuppressWarnings("restriction")
	@Override
	/**
	 * Functionality for New button.
	 * Shows a browser dialog to select a file and returns that file.
	 */
	protected String getNewInputObject() {
		//Dialog p = new JDialog();
		List<String> l = new LinkedList<String>();


		TypeFilterInputDialog dialog = new TypeFilterInputDialog(getShell(), l);
		dialog.open();
		// If user clicked on "cancel" button, return null to NOT add empty value
		if(dialog.getReturnCode() == InputDialog.CANCEL) {
			return null;
		}
		Object pack = dialog.getResult();

		return pack.toString();
	}

	@Override
	protected String createList(String[] items) {
		StringBuffer str = new StringBuffer();
		for(String item : items) {
			str.append(item + ";");
		}
		return str.toString();
	}

	/*
	 * (non-Javadoc)
	 * initialize list of items
	 * 
	 * @see org.eclipse.jface.preference.ListEditor#parseString(java.lang.String)
	 */
	@Override
	protected String[] parseString(String stringList) {
		return stringList.split(";");
	}

}
