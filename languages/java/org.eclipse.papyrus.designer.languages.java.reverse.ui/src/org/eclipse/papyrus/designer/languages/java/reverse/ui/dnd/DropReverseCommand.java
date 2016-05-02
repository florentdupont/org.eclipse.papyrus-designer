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

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.DndReverseCodeHandler;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * Command called when user drop Java Elements into diagram model (on Papyrus editor).
 * Check for selected elements, and run reverse handler if all selected files are reversible.
 *
 * This command implements a Singleton Pattern. You should use ReverseCommand.getInstance() to receive a Singleton of this Command.
 *
 * @author Jonathan Geoffroy
 *
 */
public class DropReverseCommand extends Command {

	/**
	 * Singleton implementation of ReverseCommand
	 * Always return this instance of Reverse Command when ask for instance
	 *
	 * @see {getInstance()}
	 */
	private static DropReverseCommand instance;

	/**
	 * Constructor. Initialize the request for this ReverseCommand
	 */
	private DropReverseCommand() {
		super();
	}

	@Override
	public boolean canExecute() {
		// Find the type of each selected file (CompilationUnit, packages ...)
		List<?> selected; // The selected files from JDT

		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = page.getSelection();
		if (!(selection instanceof IStructuredSelection)) {
			return false;
		}
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		selected = structuredSelection.toList();

		// For each selected object, check if it's reversible.
		for (Object s : selected) {
			// We can only reverse Java Elements. Moreover, reversing projects is not available yet
			if (!(s instanceof IJavaElement) || s instanceof IProject) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void execute() {
		DndReverseCodeHandler reverse = new DndReverseCodeHandler();
		try {
			
			reverse.execute(null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * return a singleton of this command
	 * Change the request if it differ from the previous calling
	 * @param targetEditPart 
	 *
	 *
	 * @return a singleton of this command
	 */
	public static DropReverseCommand getInstance(Request request, EditPart targetEditPart) {
		if (instance == null) {
			instance = new DropReverseCommand();
		}

		return instance;
	}
}
