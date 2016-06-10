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

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

/**
 * This class allows to visit nodes of the Eclipse Model Explorer.
 * The method {@link #visit(List)} take a list of node to visit. It visits each node and its sub-nodes according
 * to the visit policy.
 * <br>
 * A visitor must be provided. Its method are called according to encountered node's type.
 * <br>
 * Here the visit is oriented to the exploration of Java classes for Java reverse.
 * 
 * @author cedric dumoulin
 *
 */
public class ProjectExplorerNodeWalker {

	protected IProjectExplorerNodeVisitor visitor;
	
	/**
	 * Constructor.
	 * @param visitor The visitor called when a node is encountered.
	 * 
	 */
	public ProjectExplorerNodeWalker(IProjectExplorerNodeVisitor visitor) {
		this.visitor = visitor;
	}

	/**
	 * Visit the provided node and their sub-nodes.
	 */
	public void visit(List<Object> nodes) {
		doSwitch(nodes);
	}
	
	/**
	 * Perform switch on a collection of nodes.
	 * @param nodes
	 */
	protected void doSwitch( List<Object> nodes ) {
		for( Object node : nodes ) {
			doSwitch(node);
		}
	}
	
	/**
	 * Switch to the appropriate method according to node type.
	 * @param node
	 */
	protected void doSwitch( Object node ) {
		
		// Source file with one of the Java-like extensions
		if (node instanceof ICompilationUnit) {
			visitICompilationUnit((ICompilationUnit) node);
		}
		// Class file
		else if (node instanceof IClassFile) {
			visitIClassFile((IClassFile) node);
		}
		// A package fragment ~= a folder containing a package 
		// A package is the union of all package fragments having the same package name.
		// A Java package can be spread on multiple folders
		// IPackageFragment is a logical view. The corresponding resource (folder) should be acces withe
		// IPackageFragment.getCorrespondingResource();
		else if (node instanceof IPackageFragment)
		{
			visitIPackageFragment((IPackageFragment) node);
		}
		// A package fragment root contains a set of package fragments. It corresponds to an 
		// underlying resource which is either a folder, JAR, or zip. 
		else if (node instanceof IPackageFragmentRoot)
		{
			visitIPackageFragmentRoot((IPackageFragmentRoot) node);
		}
		else if (node instanceof IJavaProject)
		{
			visitIJavaProject((IJavaProject) node);
		}
		// This case happen when we drag from the Project Explorer. We need to check if the dragged Project 
		// is also a JavaProject. We do this by checking the project's nature.
		else if (node instanceof IProject)
		{
			visitIProject((IProject)node);
		}
		// Plug-in Dependencies
		else if (node instanceof IClasspathContainer)
		{
			visitIClasspathContainer((IClasspathContainer) node);
		}
		else 
		{
			System.err.println("Skip node of type " + node.getClass());
		}

	}

	/**
	 * @param node
	 */
	private void visitIClasspathContainer(IClasspathContainer node) {
		visitor.visitIClasspathContainer(node);
		// Do not recurse
	}

	/**
	 * @param node
	 */
	private void visitIProject(IProject project) {
		
		try {
			if (project.hasNature(JavaCore.NATURE_ID)) {
				IJavaProject javaProject = (IJavaProject)project.getNature(JavaCore.NATURE_ID);
//				IJavaProject javaProject = JavaCore.create(project);
				visitIJavaProject(javaProject);
			}
			else {
				visitor.visitIProject(project);
			}
		} catch (CoreException e) {
		}

		
		
	}

	/**
	 * @param node
	 */
	private void visitIJavaProject(IJavaProject node) {
		visitor.visitIJavaProject(node);
		// Recurse in children
		try {
			for (IPackageFragmentRoot child : node.getPackageFragmentRoots()) {
				switch( child.getKind()) {
				case IPackageFragmentRoot.K_SOURCE : 
				{
					doSwitch(child);
				}
				default :
				{
					
				}
				}
				
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * @param node
	 */
	private void visitIPackageFragmentRoot(IPackageFragmentRoot node) {
		visitor.visitIPackageFragmentRoot(node);
		
		try {
			for (IJavaElement child : node.getChildren()) {
				doSwitch(child);
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param node
	 */
	private void visitIClassFile(IClassFile node) {
		visitor.visitIClassFile(node);
		
	}

	/**
	 * @param node
	 */
	private void visitIPackageFragment(IPackageFragment node) {
		visitor.visitIPackageFragment(node);
		// recurse in children
		try {
			for( IJavaElement child : node.getChildren() ) {
				doSwitch(child);
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param node
	 */
	private void visitICompilationUnit(ICompilationUnit node) {
		visitor.visitICompilationUnit(node);
		
	}
}
