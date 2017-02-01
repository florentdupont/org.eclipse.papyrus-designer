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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.designer.deployment.tools.DepCreation;
import org.eclipse.papyrus.designer.deployment.tools.DepPlanUtils;
import org.eclipse.papyrus.designer.deployment.tools.DeployConstants;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.sync.DepPlanSync;
import org.eclipse.papyrus.designer.transformation.ui.Messages;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

public class CreateDepPlanHandler extends CmdHandler {

	private Package depPlans;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		if ((selectedEObject instanceof Class) && !((Class) selectedEObject).isAbstract()) {
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

		CommandSupport.exec(Messages.CreateDepPlanHandler_CreateDP, event, new Runnable() {

			@Override
			public void run() {
				// execute with transaction support
				depPlans = DepPlanUtils.getDepPlanRoot(selectedComposite);

				String depPlanName = selectedComposite.getName() + DeployConstants.DepPlanPostfix;
				if (depPlans.getMember(depPlanName) != null) {
					// deployment plan already exists
					Shell shell = Display.getDefault().getActiveShell();
					String dialogButtonLabels[] = new String[] { Messages.CreateDepPlanHandler_Cancel, Messages.CreateDepPlanHandler_Sync, Messages.CreateDepPlanHandler_CreateNew };
					MessageDialog dialog = new MessageDialog(shell, Messages.CreateDepPlanHandler_WhatShouldIDo, null, String.format(Messages.CreateDepPlanHandler_DPwithNameExistsAlready, depPlanName), MessageDialog.QUESTION, dialogButtonLabels, 0);
					int result = dialog.open();
					if (result == 0) {
						return;
					} else if (result == 1) {
						NamedElement existing = depPlans.getMember(depPlanName);
						if (existing instanceof Package) {
							DepPlanSync.syncDepPlan((Package) existing);
							return;
						} else {
							MessageDialog.openError(shell, Messages.CreateDepPlanHandler_CannotSync, String.format(Messages.CreateDepPlanHandler_DPwithNameExistsNoPackage, depPlanName));
						}
					} else {
						for (int i = 2;; i++) {
							depPlanName = selectedComposite.getName() + DeployConstants.DepPlanPostfix + i;
							if (depPlans.getMember(depPlanName) == null) {
								break;
							}
						}

					}
				}

				Package cdp = depPlans.createNestedPackage(depPlanName);
				try {
					InstanceSpecification newRootIS = DepCreation.createDepPlan(cdp, selectedComposite, DeployConstants.MAIN_INSTANCE, true);
					DepCreation.initAutoValues(newRootIS);
				} catch (TransformationException e) {
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.CreateDepPlanHandler_CannotCreateDP, e.getMessage());
				}
			}

		});
		return null;
	}
}
