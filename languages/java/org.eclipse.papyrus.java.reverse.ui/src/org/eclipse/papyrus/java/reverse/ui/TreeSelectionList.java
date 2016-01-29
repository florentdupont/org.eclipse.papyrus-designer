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
import java.util.LinkedList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.TreeSelection;

/**
 * Transform a TreeSelection collection, provided by java resource selection, into a java List (java.util.List). <br>
 * Add each element of TreeSelection which have to be displayed. i.e. include packages if <code>includePackages</code> == true; include compilation
 * unit if <code>inclupdeCU</code> == true<br/>
 * Note the each element of TreeSelection is a IJavaElement.
 *
 * @author Jonathan Geoffroy
 *
 */
public class TreeSelectionList extends LinkedList<IJavaElement> {

	/**
	 *
	 */
	private static final long serialVersionUID = -5749380826507346600L;

	/**
	 * True if and only if this list includes packages
	 */
	private boolean includePackages;

	/**
	 * True if and only if this list includes packages
	 */
	private boolean includeCU;

	/**
	 *
	 * Constructor.
	 *
	 * @param treeSelection
	 *            java resources selection
	 * @param includePackages
	 *            True if and only if this list includes packages from <code>treeSelection</code>
	 * @param includeCU
	 *            True if and only if this list includes compilation units from <code>treeSelection</code>
	 * @throws JavaModelException
	 */
	public TreeSelectionList(TreeSelection treeSelection, boolean includePackages, boolean includeCU) throws JavaModelException {
		super();
		this.includePackages = includePackages;
		this.includeCU = includeCU;

		// Add each treeSelection item into the list
		addAll(treeSelection);
	}

	/**
	 *
	 * @param selection
	 * @throws JavaModelException
	 */
	private void addAll(TreeSelection selection) throws JavaModelException {
		Iterator<IJavaElement> selectionIterator = selection.iterator();
		while (selectionIterator.hasNext()) {
			IJavaElement item = selectionIterator.next();
			scanJavaElement(item);
		}
	}

	/**
	 * Scan a Compilation Unit item
	 * Just add it if it's necessary
	 *
	 * @param item
	 *            a selection compilation unit
	 */
	private void scanCU(ICompilationUnit item) {
		if (includeCU) {
			add(item);
		}
	}

	/**
	 * Scan a package.
	 * add it if it's necessary and cross its childs to display all sub-packages (and recursively)
	 *
	 * @param item
	 *            the package to scan
	 * @throws JavaModelException
	 */
	private void scanPackage(IPackageFragment item) throws JavaModelException {
		if (includePackages) {
			add(item);
		}

		// Recursive search in tree
		for (IJavaElement child : item.getChildren()) {
			scanJavaElement(child);
		}
	}

	/**
	 * Scan a source folder.
	 * add its childs (and recursively) by calling scanJavaElement on each child
	 *
	 * @param item
	 *            the folder to scan
	 * @throws JavaModelException
	 */
	private void scanFolder(IPackageFragmentRoot item) throws JavaModelException {
		// Recursive search in tree
		for (IJavaElement child : item.getChildren()) {
			scanJavaElement(child);
		}
	}

	/**
	 * scan and add a JavaElement by delegates to scan{typeOfJavaElement}
	 *
	 * @param item
	 *            the item to display
	 * @throws JavaModelException
	 */
	private void scanJavaElement(IJavaElement item) throws JavaModelException {
		switch (item.getElementType()) {
		case IJavaElement.PACKAGE_FRAGMENT_ROOT:
			scanFolder((IPackageFragmentRoot) item);
			break;
		case IJavaElement.PACKAGE_FRAGMENT:
			scanPackage((IPackageFragment) item);
			break;
		case IJavaElement.COMPILATION_UNIT:
			scanCU((ICompilationUnit) item);
			break;
		}
	}
}
