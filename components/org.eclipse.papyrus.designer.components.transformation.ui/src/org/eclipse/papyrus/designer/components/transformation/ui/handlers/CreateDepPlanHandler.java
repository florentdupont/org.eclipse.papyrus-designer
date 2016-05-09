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

package org.eclipse.papyrus.designer.components.transformation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.designer.components.transformation.core.CommandSupport;
import org.eclipse.papyrus.designer.components.transformation.core.RunnableWithResult;
import org.eclipse.papyrus.designer.components.transformation.core.Utils;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepCreation;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepPlanUtils;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DeployConstants;
import org.eclipse.papyrus.designer.components.transformation.core.sync.DepPlanSync;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException;
import org.eclipse.papyrus.designer.components.transformation.ui.Messages;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;

public class CreateDepPlanHandler extends CmdHandler {

	private Package depPlans;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		if ((selectedEObject instanceof Class) && Utils.isCompImpl((Class) selectedEObject)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (!(selectedEObject instanceof Class)) {
			return null;
		}
		final Class selectedComposite = (Class) selectedEObject;

		CommandSupport.exec(Messages.CreateDepPlanHandler_CreateDPs, event, new Runnable() {

			@Override
			public void run() {
				// execute with transaction support
				depPlans = DepPlanUtils.getDepPlanRoot(selectedComposite);
			}
		});

		try {
			String name = selectedComposite.getName() + DeployConstants.DepPlanPostfix;
			if (depPlans.getMember(name) != null) {
				Shell shell = Display.getDefault().getActiveShell();
				String dialogButtonLabels[] = new String[] {
						Messages.CreateDepPlanHandler_Cancel,
						Messages.CreateDepPlanHandler_Sync,
						Messages.CreateDepPlanHandler_CreateNew
				};
				MessageDialog dialog = new MessageDialog(shell, Messages.CreateDepPlanHandler_WhatShouldIDo, null,
						String.format(Messages.CreateDepPlanHandler_DPwithNameExistsAlready, name),
						MessageDialog.QUESTION, dialogButtonLabels, 0);
				int result = dialog.open();
				if (result == 0) {
					return null;
				}
				else if (result == 1) {
					NamedElement existing = depPlans.getMember(name);
					if (existing instanceof Package) {
						DepPlanSync.syncDepPlan((Package) existing);
						return null;
					}
					else {
						MessageDialog.openError(shell, Messages.CreateDepPlanHandler_CannotSync,
								String.format(Messages.CreateDepPlanHandler_DPwithNameExistsNoPackage, name));
					}
				}
				else {
					for (int i = 2;; i++) {
						name = selectedComposite.getName() + DeployConstants.DepPlanPostfix + i;
						if (depPlans.getMember(name) == null) {
							break;
						}
					}

				}
			}
			final String depPlanName = name;

			CommandSupport.exec(Messages.CreateDepPlanHandler_CreateDP, event, new RunnableWithResult() {

				@Override
				public CommandResult run() {
					Package cdp = depPlans.createNestedPackage(depPlanName);
					Stereotype st = StereotypeUtil.apply(cdp, org.eclipse.papyrus.designer.components.FCM.DeploymentPlan.class);
					if (st == null) {
						MessageDialog.openInformation(Display.getDefault().getActiveShell(),
								Messages.CreateDepPlanHandler_CannotCreateDP,
								Messages.CreateDepPlanHandler_StereoApplicationFailed);
						return CommandResult.newErrorCommandResult(Messages.CreateDepPlanHandler_CannotCreateDP);
					}
					try {
						InstanceSpecification newRootIS =
								DepCreation.createDepPlan(cdp, selectedComposite, DeployConstants.MAIN_INSTANCE, true);
						DepCreation.initAutoValues(newRootIS);
						return CommandResult.newOKCommandResult();
					}
					catch (TransformationException e) {
						MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.CreateDepPlanHandler_CannotCreateDP,
								e.getMessage());
						return CommandResult.newErrorCommandResult(e.getMessage());
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
