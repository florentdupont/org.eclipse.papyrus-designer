/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.languages.common.base.GenUtils;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageProjectSupport;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project;
import org.eclipse.papyrus.designer.languages.java.codegen.Activator;
import org.eclipse.papyrus.designer.languages.java.codegen.preferences.JavaCodeGenConstants;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.util.UMLUtil;

public class LocateJavaProject {
	public static final String LANGUAGE_NAME = "Java"; //$NON-NLS-1$

	private static final boolean Headless = Boolean.getBoolean("papyrus.run-headless"); //$NON-NLS-1$
	
	public static final String DEFAULT_SOURCE_FOLDER = "src-codegen/"; //$NON-NLS-1$

	/**
	 * Locate and return the target project for the given packageable element. Return null if
	 * no target project can be found.
	 *
	 * Ensures that the target project is correctly setup to contain generated Java code. Does
	 * not create a new project, but may modify existing ones.
	 *
	 * @param pe
	 *            a packageable element within a model
	 * @param createIfMissing
	 *            if true, ask the user to apply the Java nature if required.
	 * @return the associated project, if the Java nature is applied.
	 */
	public static IProject getTargetProject(PackageableElement pe, boolean createIfMissing) {
		Package rootPkg = PackageUtil.getRootPackage(pe);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		String projectName = null;
		
		if (GenUtils.hasStereotype(rootPkg, Project.class)) {
			Project javaProject = UMLUtil.getStereotypeApplication(rootPkg, Project.class);
			projectName = javaProject.getProjectName();
			
			if (projectName != null && (projectName.equals(".") || projectName.equals(".."))) {
				displayError("An error was encountered during code generation: invalid project name. The default project name will be chosen."); // $NON-NLS-1$
				projectName = null;
			}
		}
		
		if (projectName == null || projectName.isEmpty()) {
			IPreferenceStore store = Activator.getDefault().getPreferenceStore();
			String prefix = store != null ? store.getString(JavaCodeGenConstants.P_PROJECT_PREFIX) : "bad preferences."; //$NON-NLS-1$
			projectName = prefix + rootPkg.getName();
		}
		
		// Validate project name
		if (!ResourcesPlugin.getWorkspace().validateName(projectName, IResource.PROJECT).isOK()) {
			displayError("An error was encountered during code generation: invalid project name"); // $NON-NLS-1$
			return null;
		}
		
		IProject modelProject = null;
		try {
			modelProject = root.getProject(projectName);
		} catch (Exception e) {
			displayError("An error was encountered during code generation: invalid project name");
			Activator.log.error(e);
			return null;
		}
		
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
						Messages.LocateJavaProject_CreateTargetProjectTitle,
						String.format(Messages.LocateJavaProject_CreateTargetProjectDesc, projectName));
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

		// Make sure the target project has the Java nature.
		try {
			if (!modelProject.hasNature(JavaCore.NATURE_ID)) {
				boolean apply = createIfMissing && (Headless || openQuestion(
						Messages.LocateJavaProject_ApplyCNatureTitle,
						Messages.LocateJavaProject_ApplyCNatureDesc));
				if (!apply) {
					return null;
				}

				IProjectDescription description = modelProject.getDescription();
				if (description != null) {
					description.setNatureIds(new String[] {JavaCore.NATURE_ID});
				}
			}
		} catch (CoreException e) {
			Activator.log.error(e);
		}
		return modelProject;
	}
	
	public static String getTargetSourceFolder(PackageableElement packageableElement, IProject project) {
		if (project != null && packageableElement != null) {
			IJavaProject javaProject = JavaCore.create(project);
			
			if (javaProject != null) {
				Package rootPkg = PackageUtil.getRootPackage(packageableElement);
				
				String sourceFolderName = null;
				if (GenUtils.hasStereotype(rootPkg, Project.class)) {
					sourceFolderName = UMLUtil.getStereotypeApplication(rootPkg, Project.class).getSourceFolder();
				}
				
				if (sourceFolderName != null && !sourceFolderName.isEmpty()) {
					sourceFolderName = trimOnceLeadTail(sourceFolderName, "/");
					if (createSourceFolder(javaProject, sourceFolderName)) {
						return sourceFolderName + "/";
					}
				}
				
				// Source folder name from model not found: get root or src_codegen/ or src/ or create one
				if (sourceFolderExists(javaProject, "")) {
					return "";
				} else if (sourceFolderExists(javaProject, "src-codegen")) {
					return DEFAULT_SOURCE_FOLDER;
				} else if (sourceFolderExists(javaProject, "src")) {
					return "src/";
				} else if (createSourceFolder(javaProject, "src-codegen")) {
					return DEFAULT_SOURCE_FOLDER;
				}
			}
		}
		
		return "";
	}
	
	public static String getTargetPrefix(PackageableElement packageableElement) {
		if (packageableElement != null) {
			Package rootPkg = PackageUtil.getRootPackage(packageableElement);
			
			String prefixName = null;
			if (GenUtils.hasStereotype(rootPkg, Project.class)) {
				prefixName = UMLUtil.getStereotypeApplication(rootPkg, Project.class).getPrefix();
				if (prefixName != null && !prefixName.isEmpty()) {
					prefixName = trimOnceLeadTail(prefixName, ".");
					
					if (!prefixName.isEmpty()) {
						String prefixPath = prefixName.replaceAll("\\.", "/");
						if (!Path.ROOT.isValidPath(prefixPath)
								|| prefixPath.startsWith("/")
								|| prefixPath.endsWith("/")) { // Since we trimmed lead and tail already, it shouldn't have an extra "/"
							displayError("An error was encountered during code generation: invalid prefix. No prefix will be used."); // $NON-NLS-1$
							return "";
						}
						return prefixName + ".";
					}
				}
			}
		}
		
		return "";
	}
	
	private static boolean sourceFolderExists(IJavaProject javaProject, String sourceFolderName) {
		try {
			IClasspathEntry[] classPathEntries = javaProject.getRawClasspath();
			if (classPathEntries != null) {
				for (IClasspathEntry pathEntry : classPathEntries) {
					int entryKind = pathEntry.getEntryKind();
					if (entryKind == IClasspathEntry.CPE_SOURCE) { // entry is a source folder
						IPath path = pathEntry.getPath();
						String pathString = path.toString();
						
						if (pathString.equals(javaProject.getPath().toString() + "/" + sourceFolderName)) {
							return true;
						}
					}
				}
			}
		} catch (JavaModelException e) {
			Activator.log.error(e);
		}
		
		return false;
	}
	
	private static boolean createSourceFolder(IJavaProject javaProject, String sourceFolderName) {
		if (sourceFolderName == null) {
			sourceFolderName = "";
		}
		
		// Validate folder name
		if (!Path.ROOT.isValidPath(sourceFolderName)
				|| sourceFolderName.startsWith("/")
				|| sourceFolderName.endsWith("/")) { // This is called by getTargetSourceFolder which already trimmed the leading and ending "/"
			displayError("An error was encountered during code generation: invalid source folder name. A default source folder will be chosen."); // $NON-NLS-1$
			return false;
		}
		
		if (sourceFolderExists(javaProject, sourceFolderName)) {
			return true;
		}
		
		try {
			IPath srcPath= javaProject.getPath().append(sourceFolderName);
			
			// TODO embedding source folders problem
			// TODO my-src/1/2/3 ==> check my-src/1/2 exists and append 3 to my-src/1/2. If it does
			//srcPath.uptoSegment(count)
			//javaProject.findElement(path)
						
			IClasspathEntry[] classPathEntries = javaProject.getRawClasspath();
			if (classPathEntries != null) {
				IClasspathEntry[] newEntries = new IClasspathEntry[classPathEntries.length + 1];
				System.arraycopy(classPathEntries, 0, newEntries, 0, classPathEntries.length);
				
				IClasspathEntry srcEntry= JavaCore.newSourceEntry(srcPath, null);

				newEntries[classPathEntries.length] = JavaCore.newSourceEntry(srcEntry.getPath());
				javaProject.setRawClasspath(newEntries, null);

				return true;
			}
		} catch (Exception e) {
			Activator.log.error(e);
			displayError("An error was encountered during code generation: invalid source folder name. A default source folder will be chosen."); // $NON-NLS-1$
			return false;
		}
		
		return false;
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
	
	private static String trimOnceLeadTail(String name, String character) {
		String theName = name;
		if (theName != null && !theName.isEmpty()) {
			theName = theName.trim();
			
			if (theName.startsWith(character)) {
				theName = theName.replaceFirst(character, "");
			}
			
			if (theName.endsWith(character)) {
				theName = theName.substring(0, theName.length() - 1);
			}
		}
		
		return theName;
	}
	
	private static void displayError(String message) {
		Shell shell = Display.getDefault().getActiveShell();
		
		if (shell != null) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
			messageBox.setText("Code generation error"); // $NON-NLS-1$
			messageBox.setMessage(message); // $NON-NLS-1$
			messageBox.open();
		}
	}
}
