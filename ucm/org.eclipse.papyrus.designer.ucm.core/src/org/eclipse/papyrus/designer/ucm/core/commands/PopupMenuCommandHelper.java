/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.PopupMenuCommand;
import org.eclipse.gmf.runtime.diagram.ui.menus.PopupMenu;
import org.eclipse.swt.widgets.Shell;

public class PopupMenuCommandHelper {
	
	public static ICommand getPopupMenuCommand(Shell parentShell, PopupMenu popupMenu) {
		return new PopupMenuCommand("Set Type", parentShell, popupMenu) {
			private ICommand delegate;

			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor progressMonitor, IAdaptable info)
					throws ExecutionException {

				if (!getPopupMenu().show(getParentShell())) {
					// User cancelled the menu
					delegate = UnexecutableCommand.INSTANCE;
					progressMonitor.setCanceled(true);
					return CommandResult.newCancelledCommandResult();
				}

				delegate = (ICommand) getPopupMenu().getResult();
				delegate.execute(progressMonitor, info);

				// Inherit contexts
				for (IUndoContext next : delegate.getContexts()) {
					if (!hasContext(next)) {
						addContext(next);
					}
				}

				return delegate.getCommandResult();
			}

			@Override
			public boolean canUndo() {
				return (delegate != null) && delegate.canUndo();
			}

			@Override
			protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				delegate.undo(progressMonitor, info);
				return delegate.getCommandResult();
			}

			@Override
			public boolean canRedo() {
				return (delegate != null) && delegate.canRedo();
			}

			@Override
			protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				delegate.redo(progressMonitor, info);
				return delegate.getCommandResult();
			}
		};
	}
}
