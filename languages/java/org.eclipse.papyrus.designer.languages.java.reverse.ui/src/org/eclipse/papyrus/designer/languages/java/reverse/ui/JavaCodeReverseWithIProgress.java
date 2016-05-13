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

package org.eclipse.papyrus.designer.languages.java.reverse.ui;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.ParseException;
import org.eclipse.uml2.uml.Package;


/**
 * @author dumoulin
 *
 */
public class JavaCodeReverseWithIProgress extends JavaCodeReverse  {

	protected IProgressMonitor monitor;
	
	/**
	 * Constructor.
	 *
	 * @param rootPackage
	 * @param generatedPackageName
	 * @param searchPaths
	 */
	public JavaCodeReverseWithIProgress(Package rootPackage, String generatedPackageName, List<String> searchPaths, IProgressMonitor monitor) {
		super(rootPackage, generatedPackageName, searchPaths);
		this.monitor = monitor;
	}

	/**
	 * @return the monitor
	 */
	public IProgressMonitor getProgressMonitor() {
		return monitor;
	}

	/**
	 * @param monitor the monitor to set
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}
	

	/**
	 * Take into account the monitor
	 * 
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.JavaCodeReverse#executeCodeReverse(org.eclipse.jface.viewers.TreeSelection)
	 *
	 * @param treeSelection
	 */
	@Override
	public void executeCodeReverse(ITreeSelection treeSelection) {
		// TODO Auto-generated method stub
		
		int totalWork = treeSelection.size();
		
		monitor.beginTask("Reverse", totalWork);
		
		super.executeCodeReverse(treeSelection);
		
		monitor.done();
	}
	
	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.JavaCodeReverse#reverseResource(org.eclipse.core.resources.IResource)
	 *
	 * @param resource
	 * @throws ParseException
	 * @throws CoreException
	 */
	@Override
	public void reverseResource(IResource resource) throws ParseException, CoreException {
		monitor.subTask(resource.getName());
		super.reverseResource(resource);
		monitor.worked(1);
	}
}
