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
 * @author dumoulin
 *
 */
public class DebugProjectExplorerNodeVisitor implements IProjectExplorerNodeVisitor {

	/**
	 * Constructor.
	 *
	 */
	public DebugProjectExplorerNodeVisitor() {
	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitICompilationUnit(org.eclipse.jdt.core.ICompilationUnit)
	 *
	 * @param node
	 */
	@Override
	public void visitICompilationUnit(ICompilationUnit node) {
		System.out.println("visitICompilationUnit(" + node.getElementName() + ")");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIClassFile(org.eclipse.jdt.core.IClassFile)
	 *
	 * @param node
	 */
	@Override
	public void visitIClassFile(IClassFile node) {
		System.out.println("visitIClassFile(" + node.getElementName() + ")");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIPackageFragment(org.eclipse.jdt.core.IPackageFragment)
	 *
	 * @param node
	 */
	@Override
	public void visitIPackageFragment(IPackageFragment node) {
		System.out.println("visitIPackageFragment(" + node.getElementName() + ")");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIPackageFragmentRoot(org.eclipse.jdt.core.IPackageFragmentRoot)
	 *
	 * @param node
	 */
	@Override
	public void visitIPackageFragmentRoot(IPackageFragmentRoot node) {
		System.out.println("visitIPackageFragmentRoot(" + node.getElementName() + ")");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIClasspathContainer(org.eclipse.jdt.core.IClasspathContainer)
	 *
	 * @param node
	 */
	@Override
	public void visitIClasspathContainer(IClasspathContainer node) {
		System.out.println("visitIClasspathContainer()");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIProject(org.eclipse.core.resources.IProject)
	 *
	 * @param node
	 */
	@Override
	public void visitIProject(IProject node) {
		System.out.println("visitIProject(" + node.getName() + ")");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIJavaProject(org.eclipse.jdt.core.IJavaProject)
	 *
	 * @param node
	 */
	@Override
	public void visitIJavaProject(IJavaProject node) {
		System.out.println("visitIJavaProject(" + node.getElementName() + ")");

	}

}
