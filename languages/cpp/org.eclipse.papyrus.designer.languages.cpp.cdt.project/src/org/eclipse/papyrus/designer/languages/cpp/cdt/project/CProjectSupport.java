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
