package org.eclipse.papyrus.cpp.cdtproject;

import org.eclipse.core.resources.IProject;
import org.eclipse.papyrus.codegen.extensionpoints.ILangProjectSupport;

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
