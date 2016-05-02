/*****************************************************************************
 * Copyright (c) 2016 Cedric Dumoulin and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   cedric.dumoulin@univ-lille1.fr - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd;

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Messages;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.command.JavaCodeReverseOptions;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.command.JavaCodeReverseRecordingCommand;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.command.JobForTransactionalCommand;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog.ReverseCodeDialog;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Model;

/**
 * An intermediate command that start the job that will do reverse.
 * This command is used by the drop Strategy {@link DropReverseStrategy}.
 * 
 * @author cedric dumoulin
 *
 */
public class ReverseCurrentSelectionCommand extends Command {

	private static String DefaultGenerationModeleName = "generated";

	protected UmlModel umlModel;
	protected TransactionalEditingDomain domain;
	
	
	/**
	 * Constructor.
	 *
	 * @param editingDomain
	 * @param umlModel
	 */
	public ReverseCurrentSelectionCommand(TransactionalEditingDomain domain, UmlModel umlModel) {
		this.domain = domain;
		this.umlModel = umlModel;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 *
	 * @return
	 */
	@Override
	public boolean canExecute() {
		return true;
	}
	
	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 *
	 */
	@Override
	public void execute() {
		ServicesRegistry registry;
		Model modelRoot;

		Shell shell = getShell();

		try {
			modelRoot = (Model)umlModel.lookupRoot();
		} catch (NotFoundException e) {
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_NoModelError_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_NoModelError_Message, errorStatus);

			// Stop the handler execution.
			return;
		}

		// Open the dialog
		String modelUid = getModelUid(umlModel.getResource());

		// Get reverse parameters from a dialog
		// ReverseCodeDialog dialog = new ReverseCodeDialog(shell, DefaultGenerationPackageName, Arrays.asList("generated") );
		final ReverseCodeDialog dialog = getDialog(shell, modelUid);

		int res = dialog.open();
		// System.out.println("dialog result =" + res);
		if (res == Window.CANCEL) {
			return;
		}
		
		// Get reverse options from dialog
		JavaCodeReverseOptions options = new JavaCodeReverseOptions();
		options.setSearchPaths( Arrays.asList(dialog.getSearchPath() ) );
		options.setUmlModel( umlModel);
		options.setPackageName( getPackageName(dialog) );
		
		
		// Create reverse command
		ISelection selection = getCurrentSelection();
		if(! (selection instanceof TreeSelection) ) {
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_WrongSelectionType_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_WrongSelectionType_Message, errorStatus);

			// Stop the handler execution.
			return;
		}
		
		
		JavaCodeReverseRecordingCommand reverseCommand = new JavaCodeReverseRecordingCommand(domain, (TreeSelection)selection, modelRoot, options);
		// Create Job
		Job job = new JobForTransactionalCommand("Reverse Java Code", domain, reverseCommand);
		// Execute Job
		job.setUser(true);
		job.schedule();

	}
	
	/**
	 * Get the active shell from the active page.
	 * 
	 * @param event
	 * @return
	 */
	protected Shell getShell() {
		
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getShell();
	}

	/**
	 * Get the current selection from PlatformUI.getWorkbench() ...
	 * @return
	 */
	protected ISelection getCurrentSelection() {
		ISelection selection=null;

			// Try to get selection from ActivePage
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			selection = page.getSelection();
		return selection;
	}

	/**
	 * Find the modelUid name contains into umlResource taken in parameter
	 *
	 * @param umlResource
	 * @return the modelUid name
	 */
	private String getModelUid(Resource umlResource) {
		// Try to compute a uid identifying the model. Used to store user settings.
		String modelUid = umlResource.getURI().toPlatformString(true);
		if (modelUid == null) {
			// Can't compute relatif model uid. Use absolute one
//			System.err.println("Can't compute relatif model uid. Use absolute one");
			modelUid = umlResource.getURI().path();
		}
		return modelUid;
	}


	/**
	 * The dialog used for user.
	 *
	 * @param shell
	 * @param modelUid
	 * @return the dialog to show to user
	 */
	protected ReverseCodeDialog getDialog(Shell shell, String modelUid) {
		return new ReverseCodeDialog(shell, modelUid, getSelectedProjectName(), null);
	}
	/**
	 * 
	 * @return the name of the selected project into explorer
	 */
	protected String getSelectedProjectName() {
		
		// Lookup selection
		ISelection selection = getCurrentSelection();

		// Lookup the java compilation unit.
		TreeSelection treeSelection = (TreeSelection)selection;
		IJavaElement selectionElement = (IJavaElement)treeSelection.getFirstElement();
		String name = selectionElement.getAncestor(IJavaProject.JAVA_PROJECT).getElementName();
		return name;
	}

	/**
	 * Find the name of the model provided by the dialog
	 *
	 * @param dialog
	 *            opened dialog to user
	 * @return the name of the model. If the user has changed this name, return the name provided by the user; return the default model name
	 *         otherwise.
	 */
	protected String getPackageName(ReverseCodeDialog dialog) {
		String generationPackageName = dialog.getValue();
		if (generationPackageName == null || generationPackageName.length() == 0) {
			generationPackageName = DefaultGenerationModeleName;
		}
		return generationPackageName;
	}

	
}
