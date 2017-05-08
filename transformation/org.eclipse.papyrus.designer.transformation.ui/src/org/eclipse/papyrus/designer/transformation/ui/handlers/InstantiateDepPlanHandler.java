/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
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

package org.eclipse.papyrus.designer.transformation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.transformation.base.utils.ProjectManagement;
import org.eclipse.papyrus.designer.transformation.core.transformations.InstantiateDepPlan;
import org.eclipse.papyrus.designer.transformation.ui.dialogs.GenerationOptionsDialog;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Package;

/**
 * handler for instantiating a deployment plan
 */
public class InstantiateDepPlanHandler extends CmdHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		if (selectedEObject instanceof Package) {
			return StereotypeUtil.isApplied((Package) selectedEObject,  DeploymentPlan.class);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// only one model is selected
		if (selectedEObject instanceof Package) {
			selectedCDP = (Package) selectedEObject;

			project = ProjectManagement.getCurrentProject();

			GenerationOptionsDialog optionsDialog = new GenerationOptionsDialog(Display.getDefault().getActiveShell());
			optionsDialog.open();
			if (optionsDialog.getReturnCode() == IDialogConstants.OK_ID) {

				final int genOptions = (Integer) optionsDialog.getResult()[0];
				Job job = new Job("Create deployment model (OO)") {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						// execute the task ...
						new InstantiateDepPlan().instantiate(selectedCDP, monitor, project, genOptions);
						return Status.OK_STATUS;
					}
				};
				job.setUser(true);
				job.schedule();
			}
		}

		return null;
	}

	private Package selectedCDP;

	private IProject project;
}
