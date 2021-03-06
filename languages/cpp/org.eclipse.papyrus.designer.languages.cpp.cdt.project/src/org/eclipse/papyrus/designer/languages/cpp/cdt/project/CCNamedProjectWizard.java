/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.cpp.cdt.project;

import org.eclipse.cdt.ui.wizards.CCProjectWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * Variant of CCProjectWizard that allows for setting an initial
 * project name. It creates the pages and sets the initial name.
 * It does not use the standard CDTMainWizardPage but a variant
 * called CDTMainWizardPageV that skips the first validation which
 * would other trigger an error.
 *
 * @see org.eclipse.papyrus.designer.languages.cpp.cdt.project.CDTMainWizardPageV
 */
public class CCNamedProjectWizard extends CCProjectWizard {

	public CCNamedProjectWizard(String projectName) {
		this.projectName = projectName;
		dontAdd = false;
	}

	@Override
	public void addPages() {
		dontAdd = true;
		// avoid that page is actually added, since we want to add our page below
		super.addPages();
		dontAdd = false;
		String title = fMainPage.getTitle();
		String desc = fMainPage.getDescription();
		fMainPage = new CDTMainWizardPageV(fMainPage.getName());
		fMainPage.setTitle(title);
		fMainPage.setDescription(desc);
		fMainPage.setInitialProjectName(projectName);
		addPage(fMainPage);
	}

	@Override
	public void addPage(IWizardPage page) {
		if (!dontAdd) {
			super.addPage(page);
		}
	}

	private String projectName;

	private boolean dontAdd;
}
