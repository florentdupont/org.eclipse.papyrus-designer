/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;

/**
 * An EMF {@link RecordingCommand} that perform Reverse of java code.
 * This command open a Dialog asking for reverse parameters.
 * 
 * @author cedric dumoulin
 *
 */
public class ReverseJavaFromJDTCommand extends RecordingCommand {

	protected ISelection selection;
	protected UmlModel umlModel;
	protected ReverseCommandParameters parameters;
	

	/**
	 * 
	 * Constructor.
	 *
	 * @param domain The {@link TransactionalEditingDomain}
	 * @param label A label used to identify the command by human.
	 * @param selection The current selection containing the Java files to reverse.
	 * @param umlModel The umlModel into which java elements are reversed
	 * @param parameters An object used to carry parameters between commands. This command set the  {@link ReverseCommandParameters#returnedReversedNamedElement} List.
	 */
	public ReverseJavaFromJDTCommand(TransactionalEditingDomain domain, String label, ISelection selection, UmlModel umlModel, ReverseCommandParameters parameters) {
		super(domain, label);
		this.selection = selection;
		this.umlModel = umlModel;
		this.parameters = parameters;
		
	}

	/**
	 * Return true if the command can be executed. False otherwise.
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
	 *
	 * @return
	 */
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

	/**
	 * Execute the command.
	 * 
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 *
	 */
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
//			System.err.println("Can't compute relatif model uid. Use absolute one");
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
//		if (generationPackageName == null || generationPackageName.length() == 0) {
//			generationPackageName = DefaultGenerationModeleName;
//		}
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

	/**
	 * Get the active shell from the active page.
	 * 
	 * @param event
	 * @return
	 */
	protected Shell getShell() {
		
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getShell();
	}

}
