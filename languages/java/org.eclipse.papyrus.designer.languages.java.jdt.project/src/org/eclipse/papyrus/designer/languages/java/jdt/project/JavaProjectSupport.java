/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.jdt.project;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.actions.OpenNewJavaProjectWizardAction;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.m2e.core.ui.internal.wizards.MavenProjectWizard;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.JavaProjectSettings;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Supports the creation and configuration of JDT projects
 */
public class JavaProjectSupport implements ILangProjectSupport {

	public JavaProjectSupport() {

	}

	// private int dialogStatus;

	/**
	 * Create a Java project. Caller should test before calling, whether the
	 * project exists already
	 *
	 * @param projectName
	 * @return the created project
	 */
	@Override
	public IProject createProject(String projectName) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject(projectName);
		// dialogStatus = 0;
		try {
			// check is an ArcheType is defined, if so, the project is not
			// created using wizard
			Package rootElement = TransformationContext.current.modelRoot;
			MavenProject mavenProjectDetails = UMLUtil.getStereotypeApplication(rootElement, MavenProject.class);
			if (mavenProjectDetails == null) {
				// create JDT wizard for Java
				final NewJavaProjectWizardPageOne wiz = new JavaNamedProjectWizard(projectName);
				// wiz.setWindowTitle("create project " + projectName);
				// //$NON-NLS-1$
				// wiz.init(wb, null);
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						OpenNewJavaProjectWizardAction wizDiag = new OpenNewJavaProjectWizardAction();
						wizDiag.setConfiguredWizardPages(wiz, null);
						wizDiag.run();
					}
				});
			} else {
				// Create Maven wizard
				final MavenProjectWizard mavenWiz = new MavenProjectWizard();
				mavenWiz.setWindowTitle(projectName);
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						IWorkbench wb = PlatformUI.getWorkbench();
						mavenWiz.init(wb, new StructuredSelection());
						Shell shell = wb.getActiveWorkbenchWindow().getShell();
						WizardDialog dialog = new WizardDialog(shell, mavenWiz);
						dialog.create();
						dialog.open();
					}
				});
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		project = root.getProject(projectName);
		/*
		 * if ((project == null) || !project.exists()) { throw new
		 * RuntimeException("Could not create JDT project. This might indicate that there is a problem with your JDT installation."
		 * ); //$NON-NLS-1$ }
		 */
		return project;
	}

	@Override
	public void setSettings(IProject project, AbstractSettings settings) {
		JDTSettings jdtSettings = (JDTSettings) settings;
		try {
			// Setting the project nature
			if (jdtSettings.projectNatures.size() > 0) {
				IProjectDescription projectDescription = project.getDescription();
				String[] natures = new String[jdtSettings.projectNatures.size()];
				natures = jdtSettings.projectNatures.toArray(natures);
				projectDescription.setNatureIds(natures);
				project.setDescription(projectDescription, null);
			}

			// Adding containers to the classpath
			if (jdtSettings.classPaths.size() > 0) {
				List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
				for (String classPath : jdtSettings.classPaths) {
					IPath path = new Path(classPath);
					entries.add(JavaCore.newContainerEntry(path));
				}
				IJavaProject javaProject = JavaCore.create(project);
				javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
			}

			// Add the output folder for compiled ".class" [It does not support
			// the creation of multi-level hierarchy]
			if (jdtSettings.outputLocation != null) {
				if (!jdtSettings.outputLocation.equals("")) {
					IProgressMonitor progressMonitor = new NullProgressMonitor();
					IFolder target = project.getFolder(jdtSettings.outputLocation);
					target.create(true, true, progressMonitor);
					IJavaProject javaProject = JavaCore.create(project);
					javaProject.setOutputLocation(target.getFullPath(), progressMonitor);
				}
			}

			// Customizing the generated POM file
			if (jdtSettings.mavenSettings != null) {
				CustomizePOMFile customizePOMFile = new CustomizePOMFile(project, jdtSettings.mavenSettings);
				customizePOMFile.execute();
			}

		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AbstractSettings initialConfigurationData() {
		JDTSettings settings = new JDTSettings();
		settings.projectNatures = new UniqueEList<String>();
		settings.classPaths = new UniqueEList<String>();
		settings.outputLocation = "";
		return settings;
	}

	@Override
	public void gatherConfigData(Classifier implementation, AbstractSettings settings) {
		JDTSettings jdtSettings = (JDTSettings) settings;
		Element owner = implementation.getNearestPackage();
		while (owner instanceof Package) {
			JavaProjectSettings projectSettings = UMLUtil.getStereotypeApplication(owner, JavaProjectSettings.class);
			if (projectSettings != null) {
				if (projectSettings.getClassPaths() != null) {
					jdtSettings.classPaths.addAll(projectSettings.getClassPaths());
				}
				if (projectSettings.getProjectNatures() != null) {
					jdtSettings.projectNatures.addAll(projectSettings.getProjectNatures());
				}
				if (projectSettings.getOutputLocation() != null) {
					if (!(projectSettings.getOutputLocation()).equals("")) {
						jdtSettings.outputLocation = projectSettings.getOutputLocation();
					}
				}
			}
			MavenProject mavenProjectDetails = UMLUtil.getStereotypeApplication(owner, MavenProject.class);
			if (mavenProjectDetails != null) {
				jdtSettings.mavenSettings = mavenProjectDetails;
			}

			owner = owner.getOwner();
		}
	}
}