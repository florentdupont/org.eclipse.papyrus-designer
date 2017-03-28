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

package org.eclipse.papyrus.designer.ucm.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.papyrus.designer.ucm.core.utils.CommandSupport;
import org.eclipse.papyrus.designer.ucm.core.utils.RunnableWithResult;
import org.eclipse.papyrus.designer.ucm.ui.dialogs.PortConfigurationDialog;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Port;

public class ConfigurePortHandler extends CmdHandler {

	// ------------------------------------------------------------------------
	// Execution
	// ------------------------------------------------------------------------

	public static final String CONFIGURE_PORT = "Configure port"; //$NON-NLS-1$

	@Override
	public boolean isEnabled() {
		updateSelectedEObject();

		if (selectedEObject instanceof Port) {
			return true;
		}

		return false;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if (selectedEObject instanceof Port) {
			final Port port = (Port) selectedEObject;

			CommandSupport.exec(CONFIGURE_PORT, event, new RunnableWithResult() {
		
				@Override
				public CommandResult run() {
					PortConfigurationDialog portConfigurationDialog =
							new PortConfigurationDialog(Display.getDefault().getActiveShell(), port);
					portConfigurationDialog.open();
					if (portConfigurationDialog.getReturnCode() == IDialogConstants.OK_ID) {
						return CommandResult.newOKCommandResult();
					}
					else {
						return CommandResult.newCancelledCommandResult();
					}
				}
			});
		}
		return null;
	}
}
