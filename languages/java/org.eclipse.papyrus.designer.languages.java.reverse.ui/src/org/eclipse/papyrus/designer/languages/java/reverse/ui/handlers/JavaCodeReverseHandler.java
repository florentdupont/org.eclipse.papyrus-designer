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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.services.spi.IContextualServiceRegistryTracker;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.ui.util.ServiceUtilsForHandlers;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
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
public class JavaCodeReverseHandler extends AbstractHandler implements IHandler {

	private static String DefaultGenerationModeleName = "generated";

	/**
	 * Event provided to the execute method. The event is stored when the execute() method start.
	 * Other method can then use this event.
	 */
	protected ExecutionEvent event;
	
	/**
	 * Method called when button is pressed.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// Store the event in order to be able to use it from utility methods.
		this.event = event;
		ServicesRegistry registry;
		TransactionalEditingDomain domain ;
		UmlModel umlModel;
		Model modelRoot;
		
		try {
			registry = lookupServiceRegistry(event);
			domain = getEditingDomain(registry);
			umlModel = getUmlModel(registry);
			modelRoot = (Model)umlModel.lookupRoot();
		} catch (NotFoundException e) {
			// No uml resource available. User must open a model. We open an error dialog with an explicit message to advice user.
			Shell shell = getShell();
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_NoModelError_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_NoModelError_Message, errorStatus);
			// Stop the handler execution.
			return null;
		} catch (ServiceException e) {
			// No uml resource available. User must open a model. We open an error dialog with an explicit message to advice user.
			Shell shell = getShell();
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_NoPapyrusEditor_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_NoPapyrusEditor_Message, errorStatus);

			// Stop the handler execution.
			return null;
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
			return null;
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
			return null;
		}
		
		JavaCodeReverseRecordingCommand reverseCommand = new JavaCodeReverseRecordingCommand(domain, (TreeSelection)selection, modelRoot, options);
		// Create Job
		Job job = new JobForTransactionalCommand("Reverse Java Code", domain, reverseCommand);
		// Execute Job
		job.setUser(true);
		job.schedule();

		return null;
	}

	/**
	 * Get the active shell from the event, or from the active page if event is null.
	 * 
	 * @param event
	 * @return
	 */
	protected Shell getShell() {
		
		if(event != null ) {
			return HandlerUtil.getActiveShell(event);
		}
		else {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getShell();

		}
	}

	/**
	 * @param event
	 * @return
	 */
	protected ServicesRegistry lookupServiceRegistry(ExecutionEvent event) throws ServiceException {
		
		ServicesRegistry registry;
		
		try {
			// This utility accept null event.
			registry = ServiceUtilsForHandlers.getInstance().getServiceRegistry(event);
		} catch (ServiceException e1) {
			registry = getContextualServiceRegistry();
		}
		return registry;
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
	 * Get the current selection.
	 * First, try with ISources.ACTIVE_CURRENT_SELECTION_NAME.
	 * Then try with PlatformUI.getWorkbench() ...
	 * @return
	 */
	protected ISelection getCurrentSelection() {
		ISelection selection=null;

		if (event != null) {
			// Try to get selection from context
			Object context = event.getApplicationContext();
			if ( context instanceof IEvaluationContext) {
				IEvaluationContext evaluationContext = (IEvaluationContext) context;
				selection = (ISelection)evaluationContext.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
			}
		}
		if( selection == null) {
			// Try to get selection from ActivePage
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			selection = page.getSelection();
		}
		return selection;
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


	/**
	 * Get the uml resource used by the model.
	 *
	 * @return the Uml Resource
	 * @throws ServiceException 
	 */
	protected UmlModel getUmlModel(ServicesRegistry registry) throws ServiceException {
		
		UmlModel umlModel = (UmlModel)ServiceUtils.getInstance().getModelSet(registry).getModel(UmlModel.MODEL_ID);
		return umlModel;
	}

	/**
	 * Gets the {@link IEditorPart} of the currently nested active editor.
	 *
	 * @param from
	 * @return
	 * @throws ServiceException
	 *             If an error occurs while getting the requested service.
	 */
	public IEditorPart getNestedActiveIEditorPart(ServicesRegistry registry) throws ServiceException {
		return ServiceUtils.getInstance().getService(ISashWindowsContainer.class, registry).getActiveEditor();
	}

	/**
	 * Get the main editing domain.
	 *
	 * @return
	 * @throws ServiceException
	 */
	protected TransactionalEditingDomain getEditingDomain(ServicesRegistry registry) throws ServiceException {
		return ServiceUtils.getInstance().getTransactionalEditingDomain(registry);
	}

	/**
	 * Obtains the service registry determined automatically from the context of which
	 * Papyrus editor or view is active (implying the model that the user is currently editing).
	 * 
	 * @return the contextual service registry, or {@code null} if none can be determined
	 * @throws ServiceException 
	 * @since 2.0
	 */
	protected ServicesRegistry getContextualServiceRegistry() throws ServiceException {
		IContextualServiceRegistryTracker tracker = Activator.getDefault().getContextualServiceRegistryTracker();
		if( tracker !=null) {
			return tracker.getServiceRegistry();
		}
		throw new ServiceException("Can't get ServiceRegistry from Tracker");
	}


}
