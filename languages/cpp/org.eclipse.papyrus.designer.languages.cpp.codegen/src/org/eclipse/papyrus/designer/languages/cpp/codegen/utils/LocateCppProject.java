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

import org.eclipse.cdt.core.CCProjectNature;
import org.eclipse.cdt.core.CProjectNature;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageProjectSupport;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Activator;
import org.eclipse.papyrus.designer.languages.cpp.codegen.preferences.CppCodeGenConstants;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

public class LocateCppProject {
	public static final String LANGUAGE_NAME = "C++"; //$NON-NLS-1$

	private static final boolean Headless = Boolean.getBoolean("papyrus.run-headless"); //$NON-NLS-1$

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
	public static IProject getTargetProject(PackageableElement pe, boolean createIfMissing) {
		Package rootPkg = PackageUtil.getRootPackage(pe);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String prefix = store != null ? store.getString(CppCodeGenConstants.P_PROJECT_PREFIX) : "bad preferences."; //$NON-NLS-1$
		String projectName = prefix + rootPkg.getName();
		IProject modelProject = root.getProject(projectName);
		if (!modelProject.exists()) {
			if (Headless)
			{
				try {
					modelProject.create(null);
				} catch (CoreException e) {
					return null;
				}
			}
			else
			{
				boolean create = createIfMissing && openQuestion(
						Messages.LocateCppProject_CreateTargetProjectTitle,
						String.format(Messages.LocateCppProject_CreateTargetProjectDesc, projectName));
				if (create) {
					ILangProjectSupport langSupport = LanguageProjectSupport.getProjectSupport(LANGUAGE_NAME);
					if (langSupport != null) {
						modelProject = langSupport.createProject(projectName);
						langSupport.setSettings(modelProject, langSupport.initialConfigurationData());
						if (modelProject == null) {
							return null;
						}
					}
					else {
						return null;
					}
				}
				else {
					return null;
				}
			}
		}

		// Make sure the target project is open. If it was just created then it is likely open,
		// however if it is an old project then it could have been closed.
		if (!modelProject.isOpen()) {
			try {
				modelProject.open(null);
			} catch (CoreException e) {
				return null;
			}
		}

		// Make sure the target project has the C and C++ build natures.
		try {
			if (!modelProject.hasNature(CCProjectNature.CC_NATURE_ID)) {
				boolean apply = createIfMissing && (Headless || openQuestion(
						Messages.LocateCppProject_ApplyCNatureTitle,
						Messages.LocateCppProject_ApplyCNatureDesc));
				if (!apply) {
					return null;
				}
				CProjectNature.addCNature(modelProject, null);
				CCProjectNature.addCCNature(modelProject, null);
			}
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		return modelProject;
	}

	private static boolean openQuestion(final String title, final String message)
	{
		final boolean[] ret = new boolean[] { false };
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				ret[0] = MessageDialog.openQuestion(Display.getCurrent().getActiveShell(), title, message);
			}
		});
		return ret[0];
	}
}
