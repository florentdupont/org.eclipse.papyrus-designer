/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.cpp.cdt.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;

/**
 * C project support
 */
public class CProjectSupport extends C_CppProjectSupport implements ILangProjectSupport {

	@Override
	public IProject createProject(String projectName) {
		IProject project = super.createProject(projectName);
		return  project;
	}
}
