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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Messages;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.ProjectExplorerNodeWalker;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.ReverseSelectedNodeVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.command.JavaCodeReverseOptions;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.command.JavaCodeReverseRecordingCommand;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog.ReverseCodeDialog;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.exception.JavaReverseException;
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
public class JavaCodeReverseInJobHandler extends AbstractHandler implements IHandler {

	private static final String EDITOR_ID = Activator.PLUGIN_ID;

	private static String DefaultGenerationModeleName = "generated";

	/**
	 * Event provided to the execute method. The event is stored when the execute() method start.
	 * Other method can then use this event.
	 */
	protected ExecutionEvent event;
	
	protected JavaCodeReverseOptions options;
	
	protected ReverseSelectedNodeVisitor.Parameters parameters;
	
	/**
	 * The selection recorded before the Job was started.
	 */
	private ITreeSelection recordedSelection;


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
		
		parameters = new ReverseSelectedNodeVisitor.Parameters();
		parameters.setSearchPaths( Arrays.asList(dialog.getSearchPath() ) );
		parameters.setUmlRootPackage( modelRoot);
		parameters.setPackageName( getPackageName(dialog) );
		
		// Create reverse command
		ISelection selection = getCurrentSelection();
		if(! (selection instanceof ITreeSelection) ) {
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_WrongSelectionType_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_WrongSelectionType_Message, errorStatus);

			// Stop the handler execution.
			return null;
		}
		recordedSelection = (ITreeSelection)selection;
		
		// Create Job
		Job job = new JobForHandler("Reverse Java Code", domain);
		// Execute Job
		job.setUser(true);
		job.schedule();

		return null;
	}

	/**
	 * Method called from a Job, and in a Transactionnal command
	 * @param monitor
	 */
	public void doExecuteTransactionInJob(IProgressMonitor monitor) {
		System.err.println("Transaction and job called !");
		
		// Perform reverse
		ReverseSelectedNodeVisitor visitor = new ReverseSelectedNodeVisitor(parameters);
		ProjectExplorerNodeWalker reverseWalker = new ProjectExplorerNodeWalker(visitor);
		reverseWalker.visit(recordedSelection.toList());

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
		
		String selectedProjectName;
		try {
			selectedProjectName = getSelectedProjectName();
		} catch (JavaReverseException e) {
			selectedProjectName = "";
		}
		
		return new ReverseCodeDialog(shell, modelUid, selectedProjectName, null);
	}
	
	/**
	 * 
	 * @return the name of the selected project into explorer
	 */
	protected String getSelectedProjectName() throws JavaReverseException {
		
		// Lookup selection
		ISelection selection = getCurrentSelection();

		// Lookup the java compilation unit.
		TreeSelection treeSelection = (TreeSelection)selection;
		IJavaProject javaProject = getNodeEnclosingIJavaProject(treeSelection.getFirstElement());
		String name = javaProject.getAncestor(IJavaProject.JAVA_PROJECT).getElementName();
		return name;
	}

	/**
	 * Get the {@link IJavaProject} containing the provided node. Node should be an node selected in Package/Project Explorer.
	 * 
	 * @param node The node for which the enclosing {@link IJavaProject} is looked. 
	 * @return
	 * @throws CoreException 
	 */
	protected IJavaProject getNodeEnclosingIJavaProject( Object node ) throws JavaReverseException {
		
		if( node instanceof IJavaElement ) {
			
			return (IJavaProject)((IJavaElement)node).getAncestor(IJavaProject.JAVA_PROJECT);
		}
		if( node instanceof IProject ) {
			// Try to get the Java nature
			IProject project = (IProject)node;
			try {
				if (project.hasNature(JavaCore.NATURE_ID)) {
					IJavaProject javaProject = (IJavaProject)project.getNature(JavaCore.NATURE_ID);
				return javaProject;
				}
			} catch (CoreException e) {
				throw new JavaReverseException("Can't find enclosing Project for node '" + node +"'", e);
			}
		}
		
		// Not found
		throw new JavaReverseException("Can't find enclosing Project for node '" + node +"'");
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


	/**
	 * An internal {@link Job} implementation used to execute a part of the Handler as a Job.
	 * 
	 */
	protected class JobForHandler extends Job {

		private TransactionalEditingDomain domain;
		
		/**
		 * Constructor.
		 * @param name 
		 *
		 * @param domain
		 */
		public JobForHandler(String name, TransactionalEditingDomain domain) {
			super(name);
			this.domain = domain;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			
			
			try {
				RecordingCommandForHandler command = new RecordingCommandForHandler(domain, monitor);
				domain.getCommandStack().execute(command);
			} catch (Exception e) {
				return new Status(IStatus.ERROR, EDITOR_ID, e.getMessage(), e);
			}
			
			return Status.OK_STATUS;
		}

		
		
	}
	
	/**
	 * An internal Recording command used to execute a part of the Handler in a Recording Transaction.
	 *
	 */
	protected class  RecordingCommandForHandler extends RecordingCommand {
		protected IProgressMonitor monitor=null;
		
		/**
		 * Constructor.
		 *
		 * @param domain
		 * @param domain2
		 * @param monitor
		 */
		public RecordingCommandForHandler(TransactionalEditingDomain domain, IProgressMonitor monitor) {
			super(domain);
			this.monitor = monitor;
		}

		/**
		 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
		 *
		 */
		@Override
		protected void doExecute() {
			// Call the appropriate method in the parent class
			doExecuteTransactionInJob( monitor );
		}
		
		
	}

}
