/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.jdt.project;

import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;

public class JavaNamedProjectWizard extends NewJavaProjectWizardPageOne {
	public JavaNamedProjectWizard(String projectName) {
		this.setProjectName(projectName);
	}
}
