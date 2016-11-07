/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.jdt.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.ui.actions.OpenNewJavaProjectWizardAction;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Classifier;

/**
 * Supports the creation and configuration of JDT projects
 */
public class JavaProjectSupport implements ILangProjectSupport {
	private int dialogStatus;

	/**
	 * Create a Java project.
	 * Caller should test before calling, whether the project exists already
	 *
	 * @param projectName
	 * @return the created project
	 */
	@Override
	public IProject createProject(String projectName)
	{
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		IProject project = root.getProject(projectName);
		dialogStatus = 0;
		try {
			// create JDT wizard for Java
			final NewJavaProjectWizardPageOne wiz = new JavaNamedProjectWizard(projectName);

			//wiz.setWindowTitle("create project " + projectName); //$NON-NLS-1$
			//wiz.init(wb, null);

			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					OpenNewJavaProjectWizardAction wizDiag = new OpenNewJavaProjectWizardAction();
					wizDiag.setConfiguredWizardPages(wiz, null);
					wizDiag.run();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			project = null;
		}
		if (dialogStatus == 1) {
			// corresponds to Cancel
			return null;
		}
		if ((project == null) || !project.exists()) {
			throw new RuntimeException("Could not create JDT project. This might indicate that there is a problem with your JDT installation."); //$NON-NLS-1$
		}
		return project;
	}

	@Override
	public void setSettings(IProject project, AbstractSettings settings) {
	}

	@Override
	public AbstractSettings initialConfigurationData() {
		return null;
	}

	@Override
	public void gatherConfigData(Classifier implementation, AbstractSettings settings) {
	}
}