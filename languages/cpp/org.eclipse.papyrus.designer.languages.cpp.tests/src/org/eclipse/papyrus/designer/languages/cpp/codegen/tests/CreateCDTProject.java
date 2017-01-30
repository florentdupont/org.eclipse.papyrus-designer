/*******************************************************************************
 * Copyright (c) 2017 CEA LIST
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ansgar Radermacher (CEA LIST) - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.codegen.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

@SuppressWarnings("nls")
public class CreateCDTProject {
	public static IProject create(String projectName) {
		try {

			// Create model project and accompanied project description
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();

			IProject newProject = root.getProject(projectName);
			assertThat("must be able to create new project", newProject != null);
			assertThat("project must not already exist", !newProject.exists());

			IProjectDescription description = workspace.newProjectDescription(newProject.getName());
			CCorePlugin.getDefault().createCDTProject(description, newProject, new NullProgressMonitor());
			assertThat("project must be open", newProject.isOpen());

			return newProject;
		} catch (CoreException e) {
			fail(e.getMessage());
		}
		return null;
	}
}
