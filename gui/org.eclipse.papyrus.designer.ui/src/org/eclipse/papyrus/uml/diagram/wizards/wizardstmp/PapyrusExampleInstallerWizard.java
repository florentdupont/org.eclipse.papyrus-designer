/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.wizards.wizardstmp;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard;
import org.eclipse.emf.common.ui.wizard.ExampleInstallerWizard;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.osgi.framework.Bundle;

/**
 * This class extends the EMF ExampleInstallerWizard with respect to three aspects
 * 1. It supports the definition of example models for the same installers in multiple extension points. This is
 *    important, if sample models (of the same category) are provided by different plug-ins.
 * 2. It only copies the selected model
 * 3. It also opens the selected example model, if another editor is already open
 * 
 * @since 2.1
 * @deprecated: will soon be in Papyrus base (oep.uml.diagram.wizards)
 */
@Deprecated
public class PapyrusExampleInstallerWizard extends ExampleInstallerWizard {

	@SuppressWarnings("nls")
	@Override
	/**
	 * @see org.eclipse.emf.common.ui.wizard.ExampleInstallerWizard#loadFromExtensionPoints()
	 * 
	 *      identical copy from superclass with one exception: do not stop after the processing of a single extension point
	 */
	protected void loadFromExtensionPoints() {
		projectDescriptors = new ArrayList<ProjectDescriptor>();
		filesToOpen = new ArrayList<FileToOpen>();

		String wizardID = wizardConfigurationElement.getAttribute("id");

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(CommonUIPlugin.INSTANCE.getSymbolicName(), "examples");
		IConfigurationElement[] exampleElements = extensionPoint.getConfigurationElements();
		for (int i = 0; i < exampleElements.length; i++) {
			IConfigurationElement exampleElement = exampleElements[i];
			if ("example".equals(exampleElement.getName()) && wizardID.equals(exampleElement.getAttribute("wizardID"))) {
				IConfigurationElement[] projectDescriptorElements = exampleElement.getChildren("projectDescriptor");
				for (int j = 0; j < projectDescriptorElements.length; j++) {
					IConfigurationElement projectDescriptorElement = projectDescriptorElements[j];
					String projectName = projectDescriptorElement.getAttribute("name");
					if (projectName != null) {
						String contentURI = projectDescriptorElement.getAttribute("contentURI");
						if (contentURI != null) {
							AbstractExampleInstallerWizard.ProjectDescriptor projectDescriptor = new AbstractExampleInstallerWizard.ProjectDescriptor();
							projectDescriptor.setName(projectName);

							URI uri = URI.createURI(contentURI);
							if (uri.isRelative()) {
								uri = URI.createPlatformPluginURI(projectDescriptorElement.getContributor().getName() + "/" + contentURI, true);
							}
							projectDescriptor.setContentURI(uri);

							projectDescriptor.setDescription(projectDescriptorElement.getAttribute("description"));

							projectDescriptors.add(projectDescriptor);
						}
					}
				}

				if (!projectDescriptors.isEmpty()) {
					IConfigurationElement[] openElements = exampleElement.getChildren("fileToOpen");
					for (int j = 0; j < openElements.length; j++) {
						IConfigurationElement openElement = openElements[j];
						String location = openElement.getAttribute("location");
						if (location != null) {
							AbstractExampleInstallerWizard.FileToOpen fileToOpen = new AbstractExampleInstallerWizard.FileToOpen();
							fileToOpen.setLocation(location);
							fileToOpen.setEditorID(openElement.getAttribute("editorID"));
							filesToOpen.add(fileToOpen);
						}
					}

					String imagePath = exampleElement.getAttribute("pageImage");
					if (imagePath != null) {
						imagePath = imagePath.replace('\\', '/');
						if (!imagePath.startsWith("/")) {
							imagePath = "/" + imagePath;
						}

						Bundle pluginBundle = Platform.getBundle(exampleElement.getDeclaringExtension().getContributor().getName());
						try {
							ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(pluginBundle.getEntry(imagePath));
							setDefaultPageImageDescriptor(imageDescriptor);
						} catch (Exception e) {
							// Ignore
						}
					}
				}
			}
		}
	}


	@Override
	/**
	 * @see org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard#openEditor(org.eclipse.core.resources.IFile, java.lang.String)
	 *
	 *      Only change compared to superclass: don't pass IWorkbenchPage.MATCH_ID
	 */
	protected void openEditor(IFile file, String editorID) throws PartInitException {
		IEditorRegistry editorRegistry = getWorkbench().getEditorRegistry();
		if (editorID == null || editorRegistry.findEditor(editorID) == null) {
			editorID = getWorkbench().getEditorRegistry().getDefaultEditor(file.getFullPath().toString()).getId();
		}

		IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow().getActivePage();
		page.openEditor(new FileEditorInput(file), editorID, true);
	}

	/**
	 * @see org.eclipse.emf.common.ui.wizard.AbstractExampleInstallerWizard#performFinish()
	 *
	 *      Only copy selected files
	 */
	@Override
	public boolean performFinish() {
		Control control = projectPage.getControl();
		// get selection index from list. The following code depends on the super class implementation, but this is the only way
		// to obtain the selection, since projectPage.projectList and projectPage.getSelectedProjectDescriptor are not accessible
		if (control instanceof Composite) {
			Control list = ((Composite) control).getChildren()[0];
			if (list instanceof List) {
				int index = ((List) list).getSelectionIndex();

				// remove all but selected projects from list
				FileToOpen fileToOpen = filesToOpen.get(index);
				ProjectDescriptor projectToCreate = projectDescriptors.get(index);
				projectDescriptors.clear();
				filesToOpen.clear();
				projectDescriptors.add(projectToCreate);
				filesToOpen.add(fileToOpen);
			}
		}
		return super.performFinish();
	}
}
