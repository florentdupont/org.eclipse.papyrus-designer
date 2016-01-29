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
 * 	Jonathan Geoffroy	geoffroy.jonathan@gmail.com		initial implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.java.reverse.ui;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.infra.ui.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.java.reverse.ui.dialog.DndReverseCodeDialog;
import org.eclipse.papyrus.java.reverse.ui.dialog.ReverseCodeDialog;
import org.eclipse.papyrus.uml.diagram.common.util.MDTUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Handler to display reverse result into current papyrus diagram
 * This Handler is activated when user drag'n'drop java resource into papyrus diagram.<br>
 * It display just non displayed java resource.
 * Moreover, it use DndReverseCodeDialog to know if it have to display model, packages and CompilationUnit or not.
 *
 * @author Jonathan Geoffroy
 *
 */
public class DndReverseCodeHandler extends ReverseCodeHandler {

	/**
	 * true if and only if user want to display reversed compilation unit
	 */
	private boolean displayCU;

	/**
	 * true if and only if user want to display reversed packages
	 */
	private boolean displayPackages;

	/**
	 * true if and only if user want to display reversed model
	 */
	private boolean displayModel;

	@Override
	/**
	 * Find all resource to display (selection - resources which are already in the diagram), run the reverse command, before running the display command<br/>
	 * @see org.eclipse.papyrus.java.reverse.ui.ReverseCodeHandler#doExecute(org.eclipse.papyrus.java.reverse.ui.dialog.ReverseCodeDialog)
	 *
	 * @param dialog
	 */
	protected void doExecute(ReverseCodeDialog dialog) {
		// Get user preferences on dialog
		DndReverseCodeDialog dndDialog = (DndReverseCodeDialog) dialog;
		displayModel = dndDialog.displayModel();
		displayPackages = dndDialog.displayPackages();
		displayCU = dndDialog.displayCU();

		// get current selection
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = page.getSelection();
		TreeSelection treeSelection = (TreeSelection) selection;

		// Find active papyrus diagram
		Diagram diagram = null;
		IEditorPart activeEditor = MDTUtil.getActiveEditor();
		if (activeEditor != null) {
			if (activeEditor instanceof IMultiDiagramEditor) {
				diagram = (Diagram) ((IMultiDiagramEditor) activeEditor).getAdapter(Diagram.class);
			}
		}

		// Remove items already in diagram from selection
		List<IJavaElement> listSelection;
		try {
			listSelection = selectionMinusAlreadyInDiagram(treeSelection, diagram);
		} catch (JavaModelException e1) {
			e1.printStackTrace();
			return;
		}

		// Run the reverse
		super.doExecute(dialog);

		// Find model to display
		Model model = null;
		if (displayModel) {
			String modelName = dndDialog.getValue();
			model = getModelToDisplay(diagram, modelName);
		}

		// Run the reverse displayer
		DisplayReverse displayReverse = new DisplayReverse(listSelection, diagram, getUmlResource(), model);
		try {
			displayReverse.execute();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected ReverseCodeDialog getDialog(Shell shell, String modelUid) {
		return new DndReverseCodeDialog(shell, modelUid, null, null);
	}

	/**
	 *
	 * @param selection
	 *            java resources selected
	 * @param diagram
	 *            opened papyrus diagram
	 * @return selection - resources already in diagram
	 * @throws JavaModelException
	 */
	private List<IJavaElement> selectionMinusAlreadyInDiagram(TreeSelection selection, Diagram diagram) throws JavaModelException {
		// Add all diagram elements to Set
		TreeSet<String> alreadyExists = new TreeSet<String>();
		EList diagramList = diagram.getChildren();
		Iterator diagramIt = diagramList.iterator();
		ShapeImpl item;
		NamedElement e;
		while (diagramIt.hasNext()) {
			item = (ShapeImpl) diagramIt.next();
			e = (NamedElement) (item.getElement());
			alreadyExists.add(e.getName());
		}

		// remove all elements of Set from selection
		TreeSelectionList selectionList = new TreeSelectionList(selection, displayPackages, displayCU);
		ListIterator<IJavaElement> selectionIterator = selectionList.listIterator();
		String selectionItemName;
		while (selectionIterator.hasNext()) {
			selectionItemName = getName(selectionIterator.next());
			if (selectionItemName != null && alreadyExists.contains(selectionItemName)) {
				selectionIterator.remove();
			}
		}

		return selectionList;
	}

	/**
	 * Find the name of <code>item</code> For java compilation unit, remove ".java" extension
	 *
	 * @param item
	 * @return the name of item.
	 */
	private String getName(IJavaElement item) {
		String name = item.getElementName();
		if (item instanceof ICompilationUnit) {
			return name.substring(0, name.length() - 5);
		}
		return name;
	}

	/**
	 * find a model named <code>modelName</code>
	 *
	 * @param modelName
	 *            the name of the model the find
	 * @return the model which named <code>modelName</code>, or null if it doesn't exists into the current papyrus uml resource
	 */
	public Model getModel(String modelName) {
		TreeIterator<EObject> tree = getUmlResource().getAllContents();
		while (tree.hasNext()) {
			for (EObject o : tree.next().eContents()) {
				if (o instanceof Model) {
					// Add it into items to display
					Model model = (Model) o;

					// Search for model
					if (model.getName().equals(modelName)) {
						System.out.println("model = " + model);
						return model;
					}
				}
			}
		}
		System.out.println("model = null");
		return null;
	}

	/**
	 * find the model named <code>modelName</code>, and get it only if it has to be displayed
	 *
	 * @param diagram
	 *            active papyrus diagram
	 * @param modelName
	 *            the name of the model to find
	 * @return model corresponding to the modelName if it has to be displayed, i.e. if it doesn't already displayed into the diagram, or null
	 *         otherwise
	 */
	private Model getModelToDisplay(Diagram diagram, String modelName) {
		Model model = getModel(modelName);
		if (model != null && !isInDiagram(diagram, model)) {
			System.out.println("display model " + model);
			return model;
		}
		System.out.println("don't display model");
		return null;
	}

	/**
	 *
	 * @param diagram
	 * @param model
	 * @return true if model is in diagram
	 */
	private boolean isInDiagram(Diagram diagram, Model model) {
		EList diagramList = diagram.getChildren();
		Iterator diagramIt = diagramList.iterator();
		ShapeImpl item;
		while (diagramIt.hasNext()) {
			item = (ShapeImpl) diagramIt.next();
			if (item.getElement() == model) {
				return true;
			}
		}
		return false;
	}
}
