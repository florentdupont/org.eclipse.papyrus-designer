/********
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.components.transformation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.papyrus.designer.components.transformation.core.CommandSupport;
import org.eclipse.papyrus.designer.components.transformation.core.RunnableWithResult;
import org.eclipse.papyrus.designer.components.transformation.ui.dialogs.ContainerDialog;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * Call the dialogs for selecting a container rule
 */
public class SelectContainerHandler extends CmdHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		if ((selectedEObject instanceof Class) ||
				(selectedEObject instanceof InstanceSpecification)) {
			return true;
		}
		if (selectedEObject instanceof Property) {
			Type type = ((Property) selectedEObject).getType();
			if (type instanceof Class) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (!(selectedEObject instanceof NamedElement)) {
			return null;
		}

		NamedElement ne = (NamedElement) selectedEObject;
		final Shell shell = Display.getDefault().getActiveShell();

		if (ne instanceof Property) {
			// treat property as the associated type
			Type type = ((Property) ne).getType();
			if (type instanceof Class) {
				ne = type;
			}
		}
		final NamedElement element = ne;

		// 1. select possible connectors according to port types
		// (only show compatible connectors check-box?)
		// 2. select implementation group according to connector type

		if (element instanceof Class) {
			// container dialog: either extension, rule or interceptor
			// howto select? which? (and howto add/remove?) - Std - dialog is good?
			CommandSupport.exec("Select container", event, new RunnableWithResult() {

				@Override
				public CommandResult run() {
					ContainerDialog elementSelector =
							new ContainerDialog(shell, (Class) element);
					elementSelector.setTitle("Select container rules for component " + element.getName());
					elementSelector.open();
					if (elementSelector.getReturnCode() == IDialogConstants.OK_ID) {
						return CommandResult.newOKCommandResult();
					}
					else {
						return CommandResult.newCancelledCommandResult();
					}
				}
			});
		} else if (element instanceof InstanceSpecification) {
			/*
			 * ConnectorSelectionDialog elementSelector =
			 * new ConnectorSelectionDialog (shell, model, (InstanceSpecification) element);
			 * elementSelector.setTitle("Select connector");
			 * elementSelector.setMessage("Select an implementation for connector " + selectedConnector.getName ());
			 * elementSelector.open();
			 * if (elementSelector.getReturnCode () == IDialogConstants.OK_ID) {
			 * final Object[] result = elementSelector.getResult ();
			 * if ((result.length == 2) && (result[0] instanceof Class) && (result[1] instanceof Class)) {
			 * CommandSupport.exec ("Select connector", new Runnable () {
			 * public void run () {
			 * Object newConnType = StereotypeUtil.getApplication ((Class) result[0], ConnectorType.class);
			 * Object newConnImpl = StereotypeUtil.getApplication ((Class) result[1], ConnectorImpl.class);
			 * }
			 * });
			 * }
			 * }
			 */
		}
		return null;
	}
}
