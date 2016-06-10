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

package org.eclipse.papyrus.designer.languages.java.reverse.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;

/**
 * A simple Visitor that do nothing.
 * This class should be subclassed, and appropriate method overloaded.
 *
 * @author cedric dumoulin
 *
 */
public class SimpleProjectExplorerNodeVisitor implements IProjectExplorerNodeVisitor {

	/**
	 * Constructor.
	 *
	 */
	public SimpleProjectExplorerNodeVisitor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitICompilationUnit(org.eclipse.jdt.core.ICompilationUnit)
	 *
	 * @param node
	 */
	@Override
	public void visitICompilationUnit(ICompilationUnit node) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIClassFile(org.eclipse.jdt.core.IClassFile)
	 *
	 * @param node
	 */
	@Override
	public void visitIClassFile(IClassFile node) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIPackageFragment(org.eclipse.jdt.core.IPackageFragment)
	 *
	 * @param node
	 */
	@Override
	public void visitIPackageFragment(IPackageFragment node) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIPackageFragmentRoot(org.eclipse.jdt.core.IPackageFragmentRoot)
	 *
	 * @param node
	 */
	@Override
	public void visitIPackageFragmentRoot(IPackageFragmentRoot node) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIClasspathContainer(org.eclipse.jdt.core.IClasspathContainer)
	 *
	 * @param node
	 */
	@Override
	public void visitIClasspathContainer(IClasspathContainer node) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIProject(org.eclipse.core.resources.IProject)
	 *
	 * @param node
	 */
	@Override
	public void visitIProject(IProject node) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIJavaProject(org.eclipse.jdt.core.IJavaProject)
	 *
	 * @param node
	 */
	@Override
	public void visitIJavaProject(IJavaProject node) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#postVisit()
	 *
	 */
	@Override
	public void postVisit() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#preVisit()
	 *
	 */
	@Override
	public void preVisit() {
		// TODO Auto-generated method stub
		
	}

}
