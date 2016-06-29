/*******************************************************************************
 * Copyright (c) 2011 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - ansgar.radermacher@cea.fr   initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.extensionpoints;

import org.eclipse.core.resources.IProject;
import org.eclipse.uml2.uml.Classifier;

/**
 * Common interface to create projects for a specific programming language.
 */
public interface ILangProjectSupport {

	/**
	 * Create a project for a specific language and configure it (according to
	 * gathered configuration data before). In case of C/C++ for instance, a CDT
	 * project should be created, in case of Java, a JDT project. An
	 * implementation should call setProject and setSettings before returning
	 * the project
	 *
	 * @param projectName
	 *            the named of the project
	 * @return the created project
	 */
	public IProject createProject(String projectName);

	/**
	 * Write the project settings from the passed project specific settings.
	 * (which may be obtained via gatherConfigData).
	 *
	 * @param the
	 *            operating system for which code should be produced
	 */
	public void setSettings(IProject project, AbstractSettings settings);

	/**
	 * Create an initial (empty) configuration.
	 * @return the project specific configuration.
	 */
	public AbstractSettings initialConfigurationData();

	/**
	 * Gather configuration data from implementations, e.g. required include
	 * paths or libraries. It will enrich the project specific settings.
	 *
	 * @param implementation
	 *            a class copied to the target
	 */
	public void gatherConfigData(Classifier implementation, AbstractSettings settings);
}
