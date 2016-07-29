/*******************************************************************************
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher (CEA LIST) ansgar.radermacher@cea.fr - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.components.transformation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.designer.components.FCM.DeploymentPlan;
import org.eclipse.papyrus.designer.components.transformation.core.CommandSupport;
import org.eclipse.papyrus.designer.components.transformation.core.RunnableWithResult;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepUtils;
import org.eclipse.papyrus.designer.components.transformation.ui.dialogs.AllocationDialog;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Package;

/**
 * Implementation class for ClassAction action
 */
public class AllocateHandler extends CmdHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		if (selectedEObject instanceof Package) {
			return (StereotypeUtil.isApplied((Package) selectedEObject, DeploymentPlan.class));
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event)
			throws ExecutionException {
		if (!(selectedEObject instanceof Package)) {
			return null;
		}

		final Package cdp = (Package) selectedEObject;
		final Shell shell = Display.getDefault().getActiveShell();

		// org.eclipse.papyrus.designer.components.vsl.ParseVSL.test();
		if (DepUtils.getMainInstance(cdp) == null) {
			MessageDialog.openInformation(shell, "Error",
					"Deployment plan has no main instance (check stereotype attribute mainInstance)");
			return null;
		}

		// 1. select possible connectors according to port types
		// (only show compatible connectors check-box?)
		// 2. select implementation group according to connector type

		// container dialog: either extension, rule or interceptor
		// howto select? which? (and howto add/remove?) - Std - dialog is good?
		CommandSupport.exec("Instance allocation", event, new RunnableWithResult() {

			@Override
			public CommandResult run() {
				AllocationDialog allocDialog =
						new AllocationDialog(shell, cdp);
				allocDialog.setTitle("Allocate instances");
				allocDialog.open();
				if (allocDialog.getReturnCode() == IDialogConstants.OK_ID) {
					return CommandResult.newOKCommandResult();
				}
				else {
					return CommandResult.newCancelledCommandResult();
				}
			}
		});

		return null;
	}
}
