package org.eclipse.papyrus.designer.languages.java.jdt.project;

import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;

public class JavaNamedProjectWizard extends NewJavaProjectWizardPageOne {
	public JavaNamedProjectWizard(String projectName) {
		this.setProjectName(projectName);
	}
}
