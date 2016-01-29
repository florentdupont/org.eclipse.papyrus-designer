/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.codegen.extensionpoints;


import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;

/**
 * Select a generator from a list of eligible generators
 */
public class GeneratorSelectionDialog extends AbstractElementListSelectionDialog {

	protected Text m_description;

	ILangCodegen generators[];
	
	public GeneratorSelectionDialog(Shell parent, List<ILangCodegen> generatorList) {
		super(parent, new LabelProvider() {
			public String getText(Object element) {
				return LanguageCodegen.getID((ILangCodegen) element);
			}
		});
		generators = generatorList.toArray(new ILangCodegen[0]);
		setMultipleSelection(false);
		setTitle(Messages.GeneratorSelectionDialog_SelectGenerator);
	}

	@Override
	public Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);
		createMessageArea(contents);
		// createLabel(contents, "Connector type");
		// GridData gridData = DialogUtils.createFillGridData();
		// gridData.heightHint = 200;

		createFilteredList(contents);
		createFilterText(contents);
		
		fFilteredList.setElements(generators);
		
		Label info = createMessageArea(contents);
		info.setText(Messages.GeneratorSelectionDialog_infoCodeGen);
		// m_description = createMessageArea(contents);

		fFilteredList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = fFilteredList.getSelectionIndex();
				updateDescription(getElement(index));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		GridData descriptionGD = new GridData();
		descriptionGD.horizontalAlignment = GridData.FILL;
		descriptionGD.grabExcessHorizontalSpace = true;
		descriptionGD.heightHint = 80;
		descriptionGD.grabExcessVerticalSpace = true;
		descriptionGD.verticalAlignment = GridData.FILL;

		m_description = new Text(contents, SWT.NONE | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		m_description.setLayoutData(descriptionGD);
		updateDescription(getElement(0));
		
		return contents;
	}

	protected void updateDescription(ILangCodegen codegen) {
		if (codegen instanceof ILangCodegen2) {
			m_description.setText(((ILangCodegen2) codegen).getDescription());
		}
		else {
			m_description.setText("not available"); //$NON-NLS-1$
		}
	}

	/**
	 * Obtain ILangCodegen object via index. Note that we need to access the data from the filtered list
	 * since the order might not be the same as the local "generators" array (the filtered list sorts)
	 * @param index
	 * @return
	 */
	ILangCodegen getElement(int index) {
		return (ILangCodegen) fFilteredList.getFoldedElements(index)[0];
	}
	
	@Override
	protected void computeResult() {
		int index = fFilteredList.getSelectionIndex();
		Object[] result = new Object[] { getElement(index) };
		setResult(Arrays.asList(result));		
	}
}
