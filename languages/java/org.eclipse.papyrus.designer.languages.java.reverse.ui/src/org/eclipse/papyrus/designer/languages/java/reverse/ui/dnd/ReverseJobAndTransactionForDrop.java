/*****************************************************************************
 * Copyright (c) 2016 Cedric Dumoulin and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Cedric Dumoulin - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.JavaCodeReverse;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog.DndReverseCodeDialog;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog.ReverseCodeDialog;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.exception.StopExecutionException;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.utils.NamedElementFromQualifiedNamesCollector;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.utils.QualifiedNamesFromIJavaElementCollector;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.gmfdiag.common.helper.NotationHelper;
import org.eclipse.papyrus.infra.gmfdiag.common.utils.ServiceUtilsForEditPart;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;

/**
 * @author cedric dumoulin
 *
 */
public class ReverseJobAndTransactionForDrop extends AbstractJobAndTransactionForDrop implements IJobAndTransactionForDrop {


	private static String DefaultGenerationModeleName = "generated";

	private UmlModel umlModel;
	private ServicesRegistry servicesRegistry;
	/**
	 * The selection recorded before the Job was started.
	 */
	private ITreeSelection recordedSelection;


	private ReverseCodeDialog dialog;

	private IProgressMonitor progressMonitor;

	private View parentView;

	private GraphicalEditPart parentViewEditPart;

	private Point firstNodeLocation;

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#init(org.eclipse.gef.Request, org.eclipse.gef.EditPart)
	 *
	 * @param request
	 * @param targetEditPart
	 */
	@Override
	public void init(Request request, EditPart targetEditPart) throws StopExecutionException {
		
		System.err.println(this.getClass().getName() + ".init()");

		super.init(request, targetEditPart);
		
		// Filter out sources that come from Papyrus Model Explorer.
		List<EObject> sources = getSourceEObjects(request);
		if (! sources.isEmpty()) {
			throw new StopExecutionException();
		}


		try {
			servicesRegistry = ServiceUtilsForEditPart.getInstance().getServiceRegistry(targetEditPart);
			umlModel = (UmlModel)ServiceUtils.getInstance().getModelSet(servicesRegistry).getModel(UmlModel.MODEL_ID);
		} catch (ServiceException e) {
			// Stop command
			throw new StopExecutionException(e);
		}
//		TransactionalEditingDomain editingDomain = getTransactionalEditingDomain(targetEditPart);
		// Compute drop location
		DropObjectsRequest dropRequest = (DropObjectsRequest) request;
		GraphicalEditPart gtEditPart = (GraphicalEditPart) targetEditPart;
		parentViewEditPart = gtEditPart;
		
		parentView = getTargetView(targetEditPart);

		Point location = dropRequest.getLocation().getCopy();
		gtEditPart.getContentPane().translateToRelative(location);
		gtEditPart.getContentPane().translateFromParent(location);
		location.translate(gtEditPart.getContentPane().getClientArea().getLocation().getNegated());
		firstNodeLocation = location;
		
		recordedSelection = getCurrentSelection();

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#canExecute()
	 *
	 * @return
	 */
	@Override
	public boolean canExecute() {
		System.err.println(this.getClass().getName() + ".canExecute()");
		
		
		return true;
	}

	/**
	 * This method is called after mouse release, and before the Job.
	 *
	 * @throws StopExecutionException
	 */
	@Override
	public void executePreJob() throws StopExecutionException {
		System.err.println(this.getClass().getName() + ".jobPreprocess()");

		String selectedProjectName = getSelectedProjectName();
		Resource umlResource = umlModel.getResource();
		String modelUID = getResourceUid(umlResource);
		
		// Open dialog and ask preferences to user.
		// Get reverse parameters from a dialog
		Shell shell = getShell();
		dialog = new DndReverseCodeDialog(shell, modelUID, selectedProjectName, null);

		int res = dialog.open();
		// System.out.println("dialog result =" + res);
		if (res == Window.CANCEL) {
			// Should stop all commands. 
			// Return an error code or throw an exception ?
			throw new StopExecutionException("Job canceled by user.");
		}
		
	}

	/**
	 * Compute all variables required for Reverse.
	 *
	 * @param monitor
	 */
	@Override
	public void jobPreprocess(IProgressMonitor monitor) throws StopExecutionException  {
		// Prepare parameters
		progressMonitor = monitor;

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#jobRun()
	 *
	 */
	@Override
	public void jobRun() throws StopExecutionException {
		System.err.println(this.getClass().getName() + ".jobRun()");

		// Get parameters for reverse
		final List<String> searchPaths = Arrays.asList(dialog.getSearchPath());
		org.eclipse.uml2.uml.Package rootPackage;
		try {
			rootPackage = (org.eclipse.uml2.uml.Package)umlModel.lookupRoot();
		} catch (NotFoundException e) {
			// Should stop all commands. 
			// Return an error code or throw an exception ?
			throw new StopExecutionException(e);
		}
		
		// Perform reverse
		JavaCodeReverse reverse = new JavaCodeReverse(rootPackage, getPackageName(dialog), searchPaths);
		reverse.executeCodeReverse(getRecordedSelection());
		
		// Get created elements
		List<String> names = QualifiedNamesFromIJavaElementCollector.collectQualifiedNamesFromSelection(getRecordedSelection());
		System.err.println("names=" + names);
		final List<NamedElement> returnedReversedNamedElement = NamedElementFromQualifiedNamesCollector.collectNamedElementsFromQualifiedNames(names, rootPackage, searchPaths);
		System.err.println("corresponding uml elements=" + returnedReversedNamedElement);

		final DiagramNodeCreator nodeCreator = new DiagramNodeCreator(parentView, parentViewEditPart, firstNodeLocation);
		
		  nodeCreator.createNodesFor(progressMonitor, returnedReversedNamedElement);
		// Can't run in the main UI, because the transaction will be lost.
//		// Update the user interface synchronously in the main thread
//		Display.getDefault().syncExec(new Runnable() {
//		  public void run() {
//			  nodeCreator.createNodesFor(progressMonitor, returnedReversedNamedElement);
//		  }
//		});
	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#jobPostProcess()
	 *
	 */
	@Override
	public void jobPostProcess() throws StopExecutionException {
		System.err.println(this.getClass().getName() + ".jobPostProcess()");

	}

	 /************************************/
	
	/**
	 * Get the current selection from PlatformUI.getWorkbench() ...
	 * @return
	 */
	protected ITreeSelection getCurrentSelection() throws StopExecutionException {
		ISelection selection=null;

			// Try to get selection from ActivePage
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			selection = page.getSelection();
			if( selection instanceof ITreeSelection ) {
				return (ITreeSelection)selection;
			}
			
		throw new StopExecutionException("Can't find a ITreeSelection");
	}

	/**
	 * Get the selection found when the Job was started.
	 * @return
	 */
	private ITreeSelection getRecordedSelection() {
		return recordedSelection;
	}


	/**
	 * Get the name of the currently selected project
	 * @return the name of the selected project into explorer
	 */
	protected String getSelectedProjectName() {
		
		// Lookup the java compilation unit.
		TreeSelection treeSelection = (TreeSelection)getRecordedSelection();
		Object firstSelection = treeSelection.getFirstElement();
		if( ! (firstSelection instanceof IJavaElement) ) {
			
			return "";
		}
		
		IJavaElement selectionElement = (IJavaElement)treeSelection.getFirstElement();
		String name = selectionElement.getAncestor(IJavaProject.JAVA_PROJECT).getElementName();
		return name;
	}

	/**
	 * Get an UID identifying uniquely the resource. 
	 * This is usually the UTI of the resource.
	 *
	 * @param resource
	 * @return the modelUid name
	 */
	private String getResourceUid(Resource resource) {
		// Try to compute a uid identifying the model. Used to store user settings.
		String modelUid = resource.getURI().toPlatformString(true);
		if (modelUid == null) {
			// Can't compute relatif model uid. Use absolute one
//			System.err.println("Can't compute relatif model uid. Use absolute one");
			modelUid = resource.getURI().path();
		}
		return modelUid;
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
	 * Returns the Notation View which is the drop target
	 *
	 * @param targetEditPart
	 *            The drop target edit part
	 * @return
	 *         The drop target notation View
	 */
	protected View getTargetView(EditPart targetEditPart) {
		return NotationHelper.findView(targetEditPart);
	}

}
