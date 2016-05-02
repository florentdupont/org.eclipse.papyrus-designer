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

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * A ListEditor implementation. When user push "add" button, open a dialog box which can validate/invalidate the input value for Creation Path. <br>
 * Used to add a Creation Path value into Eclipse preferences
 * 
 * @author Jonathan Geoffroy
 *
 */
public class CreationPathListEditor extends ListEditor {

	public static final String SPLIT_STRING = ":";

	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param labelText
	 * @param parent
	 */
	public CreationPathListEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
	}

	@Override
	protected String createList(String[] items) {
		StringBuffer str = new StringBuffer();
		for(String item : items) {
			str.append(item + SPLIT_STRING);
		}
		return str.toString();
	}

	@Override
	protected String getNewInputObject() {
		InputDialog dialog = new InputDialog(getShell(), "Add creation path", "Add creation path", "", new CreationPathValidator());
		dialog.open();
		
		// If user clicked on "cancel" button, return null to NOT add empty value
		if(dialog.getReturnCode() == InputDialog.CANCEL) {
			return null;
		}
		return dialog.getValue();
	}

	@Override
	protected String[] parseString(String stringList) {
		return stringList.split(SPLIT_STRING);
	}

	/**
	 * Validator for creation path value
	 * 
	 * @author Jonathan Geoffroy
	 *
	 */
	private class CreationPathValidator implements IInputValidator {

		@Override
		/**
		 * Validate the input value if it contains 3 parts, separated by ';' character
		 * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
		 *
		 * @param newText
		 * @return
		 */
		public String isValid(String newText) {
			String[] splittedText = newText.split(";");
			if(! (splittedText.length == 3 || (splittedText.length == 2 && newText.charAt(newText.length() - 1) == ';'))) {
				return "pattern: includePath ; excludePath ; destination (use ';' as separator)";
			}
			return null;
		}

	}
}
