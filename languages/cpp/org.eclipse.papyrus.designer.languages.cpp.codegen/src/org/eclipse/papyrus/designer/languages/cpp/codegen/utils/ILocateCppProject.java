/*******************************************************************************
 * Copyright (c) 2006 - 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.codegen.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.uml2.uml.PackageableElement;

public interface ILocateCppProject {
	
	/**
	 * Locate and return the target project for the given packageable element. Return null if
	 * no target project can be found.
	 *
	 * Ensures that the target project is correctly setup to contain generated C/C++ code. Does
	 * not create a new project, but may modify existing ones.
	 *
	 * @param pe
	 *            a packageable element within a model
	 * @param createIfMissing
	 *            if true, ask the user to apply the C++ nature if required.
	 * @return the associated project, if the C++ nature is applied.
	 */
	public IProject getTargetProject(PackageableElement pe, boolean createIfMissing);
	
	public IFile getTargetFile();
}
