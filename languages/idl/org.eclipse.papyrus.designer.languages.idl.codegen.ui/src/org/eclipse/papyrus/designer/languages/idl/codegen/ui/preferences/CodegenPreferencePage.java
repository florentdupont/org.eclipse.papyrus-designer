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

package org.eclipse.papyrus.designer.languages.idl.codegen.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.papyrus.designer.languages.idl.codegen.Activator;
import org.eclipse.papyrus.designer.languages.idl.codegen.preferences.IDLCodeGenConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CodegenPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private Document pCommentDoc = new Document();

	public CodegenPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("This preferences page allows to customize Papyrus IDL code generation");
	}

	public void addTextField(String name, String label, Document currentDoc) {
		// ///////////////////////////////////////////////////////////////////////
		// Create a Group for the text
		// ///////////////////////////////////////////////////////////////////////
		Group txtGroup = new Group(getFieldEditorParent(), SWT.RESIZE);
		txtGroup.setLayout(new FillLayout());
		txtGroup.setText(label);
		GridData gd = new GridData(GridData.FILL_BOTH /* FILL_HORIZONTAL */);
		// gd.heightHint = 250;
		gd.horizontalSpan = 2;
		txtGroup.setLayoutData(gd);

		// Text area
		SourceViewer txtViewer = new SourceViewer(txtGroup, null, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		txtViewer.setDocument(currentDoc);

		// Retrieving existing preference
		String content = getPreferenceStore().getString(name);

		// Loading preference in txt zone
		currentDoc.set(content);

	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */

	@Override
	public void createFieldEditors() {

		addField(new StringFieldEditor(IDLCodeGenConstants.P_IDL_SUFFIX,
				"Suffix for IDL files ", getFieldEditorParent()));

		// Add P_COMMENT_HEADER
		addTextField(IDLCodeGenConstants.P_IDL_COMMENT_HEADER, "Header for generated files",
				pCommentDoc);
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	public void performDefaults() {

		// initialize comment field from default
		pCommentDoc.set(getPreferenceStore().getDefaultString(IDLCodeGenConstants.P_IDL_COMMENT_HEADER));
		super.performDefaults();
	}

	@Override
	public boolean performOk() {

		// Store txt for P_COMMENT_HEADER
		String currentComment = pCommentDoc.get();
		getPreferenceStore().setValue(IDLCodeGenConstants.P_IDL_COMMENT_HEADER, currentComment);

		return super.performOk();
	}
}
