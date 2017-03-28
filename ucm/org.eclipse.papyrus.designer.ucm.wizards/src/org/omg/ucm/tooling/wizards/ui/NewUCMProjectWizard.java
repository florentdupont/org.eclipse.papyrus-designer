/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
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
package org.omg.ucm.tooling.wizards.ui;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.uml.diagram.wizards.pages.SelectDiagramCategoryPage;
import org.eclipse.papyrus.uml.diagram.wizards.wizards.NewPapyrusProjectWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

//Unused yet. The Papyrus wizard is not really useful when the language/profile doesn't provide specific diagrams
public class NewUCMProjectWizard extends NewPapyrusProjectWizard {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("New UCM Project");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected WizardNewProjectCreationPage createNewProjectCreationPage() {
		WizardNewProjectCreationPage newProjectPage = super.createNewProjectCreationPage();
		newProjectPage.setTitle("Papyrus UCM Project");
		newProjectPage.setDescription("Create a new Papyrus UCM Project");
		return newProjectPage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SelectDiagramCategoryPage createSelectDiagramCategoryPage() {
		// here UML RT is the only available category
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void saveDiagramCategorySettings() {
		// do nothing
		// here UML RT is the only available category
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getDiagramCategoryIds() {
		return new String[] {};
	}

	@Override
	public boolean isPapyrusRootWizard() {
		return false;
	}
}
