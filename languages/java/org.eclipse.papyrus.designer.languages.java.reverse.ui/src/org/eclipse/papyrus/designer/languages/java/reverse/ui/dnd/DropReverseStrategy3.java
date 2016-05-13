/*****************************************************************************
 * Copyright (c) 2014 Jonathan Geoffroy.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Jonathan Geoffroy	geoffroy.jonathan@gmail.com		- initial implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd;

import java.util.Arrays;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.JavaCodeReverse;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog.DndReverseCodeDialog;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog.ReverseCodeDialog;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.utils.NamedElementFromQualifiedNamesCollector;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.utils.QualifiedNamesFromIJavaElementCollector;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.gmfdiag.common.utils.ServiceUtilsForEditPart;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.DropStrategy;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.TransactionalDropStrategy;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;

/**
 * This class is called when a Drop (from dnd) is requested on a GMF Diagram or Element.
 * The class implements the Papyrus {@link DropStrategy} framework.
 * 
 * This DropStrategy create a {@link ReverseCurrentSelectionCommand} and return it.
 *
 */
public class DropReverseStrategy3 extends TransactionalDropStrategy {
	public DropReverseStrategy3() {
	}

	@Override
	public String getLabel() {
//		return Messages.DropReverseStrategy_LABEL;
		return "Drop 3 (Reverse without drawing)";
	}

	@Override
	public String getDescription() {
		return Messages.DropReverseStrategy_DESCRIPTION;
	}

	@Override
	public Image getImage() {
		// No image to display
		return null;
	}

	@Override
	public String getID() {
		return this.getClass().getName(); //$NON-NLS-1$
	}

	private static String DefaultGenerationModeleName = "generated";

	/**
	 * Get the command that will perform reverse.
	 * @return
	 */
	private org.eclipse.emf.common.command.Command getReverseCommand(TransactionalEditingDomain editingDomain, final ISelection selection, final UmlModel umlModel, final ReverseCommandParameters parameters) {
		
		RecordingCommand cmd = new RecordingCommand(editingDomain, "Reverse Java Code") {
			

			@Override
			public boolean canExecute() {
				// Find the type of each selected file (CompilationUnit, packages ...)
				List<?> selected; // The selected files from JDT

				if (!(selection instanceof IStructuredSelection)) {
					return false;
				}
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				selected = structuredSelection.toList();

				// For each selected object, check if it's reversible.
				for (Object s : selected) {
					// We can only reverse Java Elements. Moreover, reversing projects is not available yet
					if (!(s instanceof IJavaElement) /* || s instanceof IProject*/) {
						return false;
					}
				}
				return true;
			}

			@Override
			protected void doExecute() {
				
				// Prepare parameters
				String selectedProjectName = getSelectedProjectName();
				Resource umlResource = umlModel.getResource();
				String modelUID = getResourceUid(umlResource);
				
				// Open dialog and ask preferences to user.
				// Get reverse parameters from a dialog
				Shell shell = getShell();
				// ReverseCodeDialog dialog = new ReverseCodeDialog(shell, DefaultGenerationPackageName, Arrays.asList("generated") );
				final ReverseCodeDialog dialog = new DndReverseCodeDialog(shell, modelUID, selectedProjectName, null);

				int res = dialog.open();
				// System.out.println("dialog result =" + res);
				if (res == Window.CANCEL) {
					// Should stop all commands. 
					// Return an error code or throw an exception ?
					return;
				}
				
		
				// Get parameters for reverse
				final List<String> searchPaths = Arrays.asList(dialog.getSearchPath());
				org.eclipse.uml2.uml.Package rootPackage;
				try {
					rootPackage = (org.eclipse.uml2.uml.Package)umlModel.lookupRoot();
				} catch (NotFoundException e) {
					// Should stop all commands. 
					// Return an error code or throw an exception ?
					return;
				}
				String packageName = getPackageName(dialog);
				
				// Perform reverse
				JavaCodeReverse reverse = new JavaCodeReverse(rootPackage, packageName, searchPaths);
				reverse.executeCodeReverse(umlResource, packageName, searchPaths);
				
				// Get created elements
				List<String> names = QualifiedNamesFromIJavaElementCollector.collectQualifiedNamesFromSelection(selection);
				System.err.println("names=" + names);
				List<NamedElement> returnedReversedNamedElement = NamedElementFromQualifiedNamesCollector.collectNamedElementsFromQualifiedNames(names, rootPackage, searchPaths);
				System.err.println("corresponding uml elements=" + returnedReversedNamedElement);
				parameters.setReturnedReversedNamedElement(returnedReversedNamedElement);

			}
			
			/**
			 * Get an UID identifying uniquely the resource. 
			 * This is usuaaly the UTI of the resource.
			 *
			 * @param resource
			 * @return the modelUid name
			 */
			private String getResourceUid(Resource resource) {
				// Try to compute a uid identifying the model. Used to store user settings.
				String modelUid = resource.getURI().toPlatformString(true);
				if (modelUid == null) {
					// Can't compute relatif model uid. Use absolute one
//					System.err.println("Can't compute relatif model uid. Use absolute one");
					modelUid = resource.getURI().path();
				}
				return modelUid;
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
			 * 
			 * @return the name of the selected project into explorer
			 */
			protected String getSelectedProjectName() {
				
				// Lookup the java compilation unit.
				TreeSelection treeSelection = (TreeSelection)selection;
				Object firstSelection = treeSelection.getFirstElement();
				if( ! (firstSelection instanceof IJavaElement) ) {
					
					return "";
				}
				
				IJavaElement selectionElement = (IJavaElement)treeSelection.getFirstElement();
				String name = selectionElement.getAncestor(IJavaProject.JAVA_PROJECT).getElementName();
				return name;
			}



		};
		
		return cmd;
	}

	@Override
	public int getPriority() {
		return 0;
	}

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.TransactionalDropStrategy#doGetCommand(org.eclipse.gef.Request, org.eclipse.gef.EditPart)
	 *
	 * @param request
	 * @param targetEditPart
	 * @return
	 */
	@Override
	protected Command doGetCommand(Request request, EditPart targetEditPart) {
		
		if (!(request instanceof DropObjectsRequest)) {
			return null;
		}
		
		
		UmlModel umlModel;
		ServicesRegistry servicesRegistry;
		try {
			servicesRegistry = ServiceUtilsForEditPart.getInstance().getServiceRegistry(targetEditPart);
			umlModel = (UmlModel)ServiceUtils.getInstance().getModelSet(servicesRegistry).getModel(UmlModel.MODEL_ID);
		} catch (ServiceException e) {
			// Stop command
			return null;
		}
		TransactionalEditingDomain editingDomain = getTransactionalEditingDomain(targetEditPart);
		// Compute drop location
		DropObjectsRequest dropRequest = (DropObjectsRequest) request;
		GraphicalEditPart gtEditPart = (GraphicalEditPart) targetEditPart;
		View targetView = getTargetView(targetEditPart);

		Point location = dropRequest.getLocation().getCopy();
		gtEditPart.getContentPane().translateToRelative(location);
		gtEditPart.getContentPane().translateFromParent(location);
		location.translate(gtEditPart.getContentPane().getClientArea().getLocation().getNegated());

		ReverseCurrentSelectionCommand cc = new ReverseCurrentSelectionCommand(editingDomain, umlModel );

		return cc;
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


}
