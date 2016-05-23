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
 * Visitor used to visit nodes of Eclipse Package Explorer.
 * 
 * @author cedric dumoulin
 *
 */
public interface IProjectExplorerNodeVisitor {

	/**
	 * @param node
	 */
	void visitICompilationUnit(ICompilationUnit node);

	/**
	 * @param node
	 */
	void visitIClassFile(IClassFile node);

	/**
	 * @param node
	 */
	void visitIPackageFragment(IPackageFragment node);

	/**
	 * @param node
	 */
	void visitIPackageFragmentRoot(IPackageFragmentRoot node);

	/**
	 * @param node
	 */
	void visitIClasspathContainer(IClasspathContainer node);

	/**
	 * @param node
	 */
	void visitIProject(IProject node);

	/**
	 * @param node
	 */
	void visitIJavaProject(IJavaProject node);

}
