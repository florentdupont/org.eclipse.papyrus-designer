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
import org.eclipse.papyrus.uml.diagram.wizards.wizards.CreateModelWizard;
import org.eclipse.ui.IWorkbench;

public class NewUCMModelWizard extends CreateModelWizard {

	public static final String PAPYRUS_UCM = "Papyrus UCM"; //$NON-NLS-1$

	public static final String NEW_UCM_MODEL = "New UCM Model"; //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(NEW_UCM_MODEL);
	}

	@Override
	public String getModelKindName() {
		return PAPYRUS_UCM;
	}

	@Override
	public boolean isPapyrusRootWizard() {
		return false;
	}


}
