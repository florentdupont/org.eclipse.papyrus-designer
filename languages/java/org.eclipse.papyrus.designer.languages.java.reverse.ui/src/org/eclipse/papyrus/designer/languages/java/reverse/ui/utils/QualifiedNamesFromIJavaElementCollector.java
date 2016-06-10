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

package org.eclipse.papyrus.designer.languages.java.reverse.ui.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;

/**
 * Utility class to collect the qualified name of Java units contains in the selection.
 * 
 * @author cedric dumoulin
 *
 */
public class QualifiedNamesFromIJavaElementCollector {
	

	/**
	 * List of results.
	 */
	private List<String> result ;
	/**
	 * True if and only if this list includes packages
	 */
	private boolean includePackages = false;

	/**
	 * True if and only if this list includes compilation units
	 */
	private boolean includeCU = false;
	
	/**
	 * True if and only if this list includes class files (e.g. from jars)
	 */
	private boolean includeClassFile = false;
	
	
	/**
	 * Default Constructor.
	 *
	 * @param includePackages
	 * @param includeCU
	 */
	public QualifiedNamesFromIJavaElementCollector() {
		this(true, true, true);
	}

	/**
	 * Constructor.
	 *
	 * @param includePackages
	 * @param includeCU
	 */
	public QualifiedNamesFromIJavaElementCollector(boolean includePackages, boolean includeCU, boolean includeClassFiles) {
		super();
		this.includePackages = includePackages;
		this.includeCU = includeCU;
		this.includeClassFile = includeClassFiles;
		this.result = new ArrayList<String>();
	}


	/**
	 * Collect the list of qualified names extracted from selection. 
	 * All Java classifiers found in the collection are collected.
	 * 
	 * @param selection
	 * @return
	 */
	public static List<String> collectQualifiedNamesFromSelection( ISelection selection) {
		return new QualifiedNamesFromIJavaElementCollector().getQualifiedNamesFromSelection(selection);
	}
	

	/**
	 * Collect the list of qualified names extracted from selection. 
	 * All Java classifiers found in the collection are collected.
	 * 
	 * @param selection
	 * @return
	 */
	public List<String> getQualifiedNamesFromSelection( ISelection selection) {
		
		if( selection instanceof TreeSelection) {
			addFromTreeSelection( (TreeSelection)selection);
		}
		return result;
		
	}
	
	/**
	 *
	 * @param selection
	 * @param result 
	 * @throws JavaModelException
	 */
	private void addFromTreeSelection(TreeSelection selection)  {
		@SuppressWarnings("unchecked")
		Iterator<Object> selectionIterator = selection.iterator();
		while (selectionIterator.hasNext()) {
			Object ele = selectionIterator.next();
			if( ele instanceof IJavaElement) {
				scanJavaElement((IJavaElement)ele);
			} else if (ele instanceof IProject) {
				IJavaProject javaProject = JavaCore.create((IProject) ele);
				scanJavaElement(javaProject);
			}
		}
	}

	/**
	 * Add the specified item to the list of results.
	 * 
	 * @param item
	 */
	private void add(IJavaElement item) {
		
//		String itemName = item.getElementName();
//		if( item instanceof ICompilationUnit) {
//			// This is the java filename. Need to remove the '.java'
//			itemName = itemName.substring(0, itemName.length() - 5); // remove ".java" to the compilation unit name
//		}
		result.add(getQualifiedName(item));
	}

	private String getQualifiedName( IJavaElement item) {
		String res = null;
		
		switch (item.getElementType()) {
		case IJavaElement.PACKAGE_FRAGMENT_ROOT:
			res = ((IPackageFragmentRoot) item).getElementName();
			break;
		case IJavaElement.PACKAGE_FRAGMENT:
			res = ((IPackageFragment) item).getElementName();
			break;
		case IJavaElement.COMPILATION_UNIT:
			// Java class
			res =  ((ICompilationUnit) item).findPrimaryType().getFullyQualifiedName('.');
			break;
		case IJavaElement.CLASS_FILE:
			// compiled class in Jar
			res = ((IClassFile) item).getType().getFullyQualifiedName('.');
			break;
		}
		
		return res;
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
	 * Scan a Compilation Unit item
	 * Just add it if it's necessary
	 *
	 * @param item
	 *            a selection compilation unit
	 */
	private void scanClassFile(IClassFile item) {
		if (includeClassFile) {
			add(item);
		}
	}

	/**
	 * Scan a package.
	 * add it if it's necessary and cross its childs to display all sub-packages (and recursively)
	 *
	 * @param item
	 *            the package to scan
	 * @param result 
	 * @throws JavaModelException
	 */
	private void scanPackage(IPackageFragment item) {
		if (includePackages) {
			add(item);
		}

		// Recursive search in tree
		try {
			for (IJavaElement child : item.getChildren()) {
				scanJavaElement(child);
			}
		} catch (JavaModelException e) {
			// Can't get children. Stop.
		}
	}

	/**
	 * Scan a source folder.
	 * add its childs (and recursively) by calling scanJavaElement on each child
	 *
	 * @param item
	 *            the folder to scan
	 * @param result 
	 * @throws JavaModelException
	 */
	private void scanFolder(IPackageFragmentRoot item)  {
		// Recursive search in tree
		try {
			for (IJavaElement child : item.getChildren()) {
				scanJavaElement(child);
			}
		} catch (JavaModelException e) {
			// Problem during scan of child. Skip.
		}
	}
	
	/**
	 * Scan a source folder.
	 * add its childs (and recursively) by calling scanJavaElement on each child
	 *
	 * @param item
	 *            the folder to scan
	 * @param result 
	 * @throws JavaModelException
	 */
	private void scanProject(IJavaProject item)  {
		// Recursive search in tree
		try {
			for (IJavaElement child : item.getChildren()) {
				scanJavaElement(child);
			}
		} catch (JavaModelException e) {
			// Problem during scan of child. Skip.
		}
	}

	/**
	 * scan and add a JavaElement by delegates to scan{typeOfJavaElement}
	 *
	 * @param item
	 *            the item to display
	 * @param result 
	 * @throws JavaModelException
	 */
	private void scanJavaElement(IJavaElement item) {
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
		case IJavaElement.CLASS_FILE:
			scanClassFile((IClassFile) item);
			break;
		case IJavaElement.JAVA_PROJECT:
			scanProject((IJavaProject) item);
		}
	}

}
