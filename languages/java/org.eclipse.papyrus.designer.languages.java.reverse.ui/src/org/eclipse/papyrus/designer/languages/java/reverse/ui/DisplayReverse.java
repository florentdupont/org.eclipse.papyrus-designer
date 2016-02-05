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

package org.eclipse.papyrus.designer.languages.java.reverse.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Command which allow to display java elements into papyrus diagram.
 * The java element must exists into papyrus model (for example by using reverse plugin)
 *
 * @author Jonathan Geoffroy
 *
 */
public class DisplayReverse {

	private static final String MODEL_TYPE = "2005", PACKAGE_TYPE = "2007", CU_TYPE = "2008"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * items to display into papyrus diagram
	 */
	private List<IJavaElement> items;

	/**
	 * Contains all uml resources.
	 * Used to improve research into umlModel.
	 */
	private Map<String, NamedElement> umlModelNames;

	/**
	 * Graphical container for the reverse result
	 */
	private Diagram diagram;

	/**
	 * model to display into diagram. Null if user doesn't want to display any model
	 */
	private Model model;

	/**
	 * Constructor.
	 *
	 * @param items
	 *            items to display into papyrus diagram
	 * @param diagram
	 *            container where to display items
	 * @param umlModel
	 *            resource representing the papyrus uml model
	 * @param model
	 *            model to display into diagram. Null if user doesn't want to display any model
	 */
	public DisplayReverse(List<IJavaElement> items, Diagram diagram, Resource umlModel, Model model) {
		super();
		this.items = items;
		this.diagram = diagram;
		this.model = model;

		// Change uml model collection to improve search
		umlModelNames = new HashMap<String, NamedElement>();
		TreeIterator<EObject> tree = umlModel.getAllContents();
		while (tree.hasNext()) {
			for (EObject o : tree.next().eContents()) {
				/*
				 * Only add reversable elements.
				 * The non Named element cannot been added into model (comments ...)
				 */
				if (o instanceof NamedElement) {
					// Add it into items to display
					NamedElement e = (NamedElement) o;
					umlModelNames.put(e.getName(), e);
				}
			}
		}


	}

	/**
	 * Execute the display command.
	 * Should call this method when you want display the selection into papyrus diagram.
	 *
	 * @throws JavaModelException
	 */
	public void execute() throws JavaModelException {
		// Display the model
		if (model != null) {
			display(model.getName(), MODEL_TYPE);
		}

		// Cross selection to display each selected item
		for (IJavaElement item : items) {
			scanJavaElement(item);
		}
	}

	/**
	 * Scan a Compilation Unit item
	 * Just display it
	 *
	 * @param item
	 *            a selection compilation unit
	 */
	private void scanCU(ICompilationUnit item) {
		String itemName = item.getElementName().substring(0, item.getElementName().length() - 5); // remove ".java" to the compilation unit name
		display(itemName, CU_TYPE);
	}

	/**
	 * Scan a package.
	 * Display it and cross its childs to display all sub-packages (and recursively)
	 *
	 * @param item
	 *            the package to scan
	 * @throws JavaModelException
	 */
	private void scanPackage(IPackageFragment item) throws JavaModelException {
		String itemName = item.getElementName();
		display(itemName, PACKAGE_TYPE);
	}

	/**
	 * scan and display a JavaElement by delegates to scan{typeOfJavaElement}
	 *
	 * @param item
	 *            the item to display
	 * @throws JavaModelException
	 */
	private void scanJavaElement(IJavaElement item) throws JavaModelException {
		try {
			switch (item.getElementType()) {
			case IJavaElement.PACKAGE_FRAGMENT:
				scanPackage((IPackageFragment) item);
				break;
			case IJavaElement.COMPILATION_UNIT:
				scanCU((ICompilationUnit) item);
				break;
			}
		} catch (NullPointerException e) {
			System.err.println(item.getElementName() + "is NOT in selected uml model"); //$NON-NLS-1$
		}
	}

	/**
	 * Display an element into papyrus diagram
	 *
	 * @param itemName
	 *            the name of element to display
	 */
	private void display(String itemName, String type) {
		EObject item = this.umlModelNames.get(itemName);
		if (!diagram.getChildren().contains(item)) {
			ViewService.createNode(diagram, item, type, new PreferencesHint("org.eclipse.papyrus.uml.diagram.clazz")); //$NON-NLS-1$
		}
	}
}
