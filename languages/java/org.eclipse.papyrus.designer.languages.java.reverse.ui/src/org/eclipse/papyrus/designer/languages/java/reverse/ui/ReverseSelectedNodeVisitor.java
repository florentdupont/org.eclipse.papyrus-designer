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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ParseException;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.ReverseSelectedNodeVisitor.Parameters;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ClassifierCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.CompilationUnitAnalyser;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

/**
 * This visitor visit the provided Nodes. 
 * Node are usually selections from Eclipse Project/Package Explorers.
 * 
 * This visitor walk the nodes and apply the appropriate code reverse.
 * 
 * @author cedric dumoulin
 *
 */
public class ReverseSelectedNodeVisitor implements IProjectExplorerNodeVisitor {

	/**
	 * Java analyser used to parse a java file.
	 * 
	 */
	protected CompilationUnitAnalyser javaAnalyser;
	
	/**
	 * The {@link ClassifierCatalog} used by the {@link #javaAnalyser}. This Catalog is used to retrieve created uml elements.
	 */
	protected ClassifierCatalog classifierCatalog;
	

	/**
	 * List of reversed elements. This list is filled with the found reversed elements.
	 * The result is available after all node visit.
	 * 
	 */
	protected List<String> reversedElementQualifiedNames;

	/**
	 * The UML::NamedElement corresponding to reversed elements. This list is filled with the found reversed elements.
	 * The result is available after all node visit.
	 */
	protected List<NamedElement> reversedNamedElement;
	
	/**
	 * Constructor.
	 *
	 */
	public ReverseSelectedNodeVisitor(Package rootPackage, String generatedPackageName, List<String> searchPaths) {
		javaAnalyser = new CompilationUnitAnalyser(rootPackage, generatedPackageName, searchPaths);
		reversedElementQualifiedNames = new ArrayList<String>();
		reversedNamedElement = new ArrayList<NamedElement>();
		classifierCatalog = javaAnalyser.getClassifierCatalog();
	}

	/**
	 * Constructor.
	 *
	 * @param parameters
	 */
	public ReverseSelectedNodeVisitor(Parameters parameters) {
		this( parameters.getUmlRootPackage(), parameters.getPackageName(), parameters.getSearchPaths());
	}

	/**
	 * @return the reversedElementQualifiedNames
	 */
	public List<String> getReversedElementQualifiedNames() {
		return reversedElementQualifiedNames;
	}

	/**
	 * @return the reversedNamedElement
	 */
	public List<NamedElement> getReversedNamedElement() {
		return reversedNamedElement;
	}

	protected void addReversedName( String name ) {
		reversedElementQualifiedNames.add(name);
		
		NamedElement namedElement = classifierCatalog.getClassifier(name);
		if(namedElement != null ) {
			reversedNamedElement.add(namedElement);
		}
		
	}
	
	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitICompilationUnit(org.eclipse.jdt.core.ICompilationUnit)
	 *
	 * @param node
	 */
	@Override
	public void visitICompilationUnit(ICompilationUnit unit) {
		processITypeRoot(unit);
	}

	/**
	 * Process a {@link ICompilationUnit} or a {@link IClassFile}.
	 * 
	 * @param unit
	 */
	protected void processITypeRoot(ITypeRoot unit) {
		System.out.println("try to reverseType(" + unit.getElementName() + ")");
		try {
			String source = unit.getSource();
			if (source == null)
			{
				System.err.println("No source attached to unit '" + unit.getElementName() + "'");
				return;
			}


			CompilationUnit cu = JavaParser.parse(new StringReader(source),true);
			javaAnalyser.processCompilationUnit(cu);
			// Add the name to the reversed list
			addReversedName(unit.findPrimaryType().getFullyQualifiedName('.'));
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.IProjectExplorerNodeVisitor#visitIClassFile(org.eclipse.jdt.core.IClassFile)
	 *
	 * @param node
	 */
	@Override
	public void visitIClassFile(IClassFile unit) {
		processITypeRoot(unit);
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
	 * Parameters to provide to the constructor.
	 *
	 */
	static public class Parameters {
		protected List<String> searchPaths;
		
		protected Package umlRootPackage;
		
		protected String packageName;

		/**
		 * @return the searchPaths
		 */
		public List<String> getSearchPaths() {
			return searchPaths;
		}

		/**
		 * @param searchPaths the searchPaths to set
		 */
		public void setSearchPaths(List<String> searchPaths) {
			this.searchPaths = searchPaths;
		}

		/**
		 * @return the umlModel
		 */
		public Package getUmlRootPackage() {
			return umlRootPackage;
		}

		/**
		 * @param umlModel the umlModel to set
		 */
		public void setUmlRootPackage(Package umlModel) {
			this.umlRootPackage = umlModel;
		}

		/**
		 * @return the packageName
		 */
		public String getPackageName() {
			return packageName;
		}

		/**
		 * @param packageName the packageName to set
		 */
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		
	}
}
