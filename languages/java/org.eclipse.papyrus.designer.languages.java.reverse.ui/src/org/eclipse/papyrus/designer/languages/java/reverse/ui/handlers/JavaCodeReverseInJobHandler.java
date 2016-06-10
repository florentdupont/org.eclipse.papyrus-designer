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

package org.eclipse.papyrus.designer.languages.java.reverse.ui.handlers;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Messages;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.ProjectExplorerNodeWalkerWithIProgress;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.ReverseSelectedNodeVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog.ReverseCodeDialog;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.exception.JavaReverseException;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.utils.QualifiedNamesFromIJavaElementCollector;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Model;


/**
 * A {@link IHandler} to start Java Code reverse.
 * The current selection is retrieved. For each selected element, try to reverse it to UML if it correspond to 
 * Java code.
 *  
 * @author cedric dumoulin
 * 
 * @since 2.0
 */
public class JavaCodeReverseInJobHandler extends AbstractExecuteInJobHandler implements IHandler {

	private static String DefaultGenerationModeleName = "";

	protected ReverseSelectedNodeVisitor.Parameters parameters;
	
	/**
	 * The selection recorded before the Job was started.
	 */
	private ITreeSelection recordedSelection;

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.handlers.AbstractExecuteInJobHandler#getJobName()
	 *
	 * @return
	 */
	@Override
	String getJobName() {
		return "Reverse Java Code";
	}

	/**
	 * @param event
	 * @return True if the execution should continue, false if the execution should stop.
	 */
	@Override
	protected boolean doPrepareExecute(ExecutionEvent event) {
		UmlModel umlModel;
		Model modelRoot;
		
		try {
			umlModel = getUmlModel();
			modelRoot = (Model)umlModel.lookupRoot();
		} catch (NotFoundException e) {
			// No uml resource available. User must open a model. We open an error dialog with an explicit message to advice user.
			Shell shell = getShell();
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_NoModelError_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_NoModelError_Message, errorStatus);
			// Stop the handler execution.
			return false;
		} catch (ServiceException e) {
			// No uml resource available. User must open a model. We open an error dialog with an explicit message to advice user.
			Shell shell = getShell();
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_NoPapyrusEditor_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_NoPapyrusEditor_Message, errorStatus);

			// Stop the handler execution.
			return false;
		}
		
		
		// Open the dialog
		String modelUid = getModelUid(umlModel.getResource());

		// Get reverse parameters from a dialog
		Shell shell = getShell();
		// ReverseCodeDialog dialog = new ReverseCodeDialog(shell, DefaultGenerationPackageName, Arrays.asList("generated") );
		final ReverseCodeDialog dialog = getDialog(shell, modelUid);

		int res = dialog.open();
		// System.out.println("dialog result =" + res);
		if (res == Window.CANCEL) {
			return false;
		}
		
		
		
		parameters = new ReverseSelectedNodeVisitor.Parameters();
		parameters.setSearchPaths( Arrays.asList(dialog.getSearchPath() ) );
		parameters.setUmlRootPackage( modelRoot);
		parameters.setPackageName( getPackageName(dialog) );
		parameters.setCreationPaths(dialog.getCreationPaths());
		
		// Create reverse command
		ISelection selection = getCurrentSelection();
		if(! (selection instanceof ITreeSelection) ) {
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_WrongSelectionType_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_WrongSelectionType_Message, errorStatus);

			// Stop the handler execution.
			return false;
		}
		recordedSelection = (ITreeSelection)selection;
		
		// Get qualified names of CUs in projects
		QualifiedNamesFromIJavaElementCollector collector = new QualifiedNamesFromIJavaElementCollector(false, true, false);
		List<String> qualifiedNamesInProjects = collector.getQualifiedNamesFromSelection(recordedSelection);
		parameters.setQualifiedNamesInProjects(qualifiedNamesInProjects);
		
		return true;
	}

	/**
	 * Method called from a Job, and in a Transactionnal command
	 * @param monitor
	 */
	@Override
	protected void doExecuteTransactionInJob(IProgressMonitor monitor) {
		System.err.println("Transaction and job called !");

		// Perform reverse
		ReverseSelectedNodeVisitor visitor = new ReverseSelectedNodeVisitor(parameters);
		// ProjectExplorerNodeWalker reverseWalker = new ProjectExplorerNodeWalker(visitor);
		ProjectExplorerNodeWalkerWithIProgress reverseWalker = new ProjectExplorerNodeWalkerWithIProgress(visitor);
		reverseWalker.visit(recordedSelection.toList(), monitor);
	}

	/**
	 * Find the modelUid name contained into umlResource taken in parameter
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
		
		String selectedProjectName;
		try {
			selectedProjectName = getSelectedProjectName();
		} catch (JavaReverseException e) {
			selectedProjectName = "";
		}
		
		return new ReverseCodeDialog(shell, modelUid, selectedProjectName, null);
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
