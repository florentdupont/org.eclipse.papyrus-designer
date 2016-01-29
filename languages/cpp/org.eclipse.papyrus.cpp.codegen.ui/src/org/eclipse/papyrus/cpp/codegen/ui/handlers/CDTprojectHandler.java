/*******************************************************************************
 * Copyright (c) 2013 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ansgar Radermacher - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.cpp.codegen.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.papyrus.codegen.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.codegen.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.codegen.extensionpoints.LanguageProjectSupport;
import org.eclipse.papyrus.cpp.codegen.utils.LocateCppProject;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * Handler for C++ code generation
 */
public class CDTprojectHandler extends CmdHandler {

	private static final String LANGUAGE_NAME = "C++"; //$NON-NLS-1$

	// ------------------------------------------------------------------------
	// Execution
	// ------------------------------------------------------------------------

	@Override
	public boolean isEnabled() {
		updateSelectedEObject();

		if (selectedEObject instanceof Package || selectedEObject instanceof Classifier) {
			URI uri = selectedEObject.eResource().getURI();

			// URIConverter uriConverter = resource.getResourceSet().getURIConverter();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			if (uri.segmentCount() < 2) {
				return false;
			}
			IProject modelProject = root.getProject(uri.segment(1));
			return modelProject.exists();
		}

		return false;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if (selectedEObject instanceof PackageableElement) {
			PackageableElement pe = (PackageableElement) selectedEObject;

			IProject modelProject = LocateCppProject.getTargetProject(pe, true);
			if (modelProject == null) {
				return null;
			}

			// get the container for the current element
			ILangProjectSupport langSupport = LanguageProjectSupport.getProjectSupport(LANGUAGE_NAME);
			if (langSupport != null) {
				AbstractSettings settings = langSupport.initialConfigurationData();
				langSupport.setSettings(modelProject, settings);
			}
			else {
				return null;
			}
		}
		return null;
	}
}
