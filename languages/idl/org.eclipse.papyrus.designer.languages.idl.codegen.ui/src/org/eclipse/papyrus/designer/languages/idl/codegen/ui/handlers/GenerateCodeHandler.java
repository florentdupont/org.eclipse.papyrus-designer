/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ansgar Radermacher - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.idl.codegen.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.papyrus.designer.languages.idl.codegen.transformation.IDLModelElementsCreator;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.uml2.uml.Classifier;

/**
 * Code generation handler for IDL (filters for classifiers)
 */
public class GenerateCodeHandler extends CmdHandler {
	// ------------------------------------------------------------------------
    // Execution
	// ------------------------------------------------------------------------

	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
	
		if (selectedEObject instanceof Classifier) {
			return true;
		}
		return super.isEnabled();
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {

		if(selectedEObject instanceof Classifier) {
			Classifier classifier = (Classifier)selectedEObject;
			
			URI uri = classifier.eResource().getURI();

			// URIConverter uriConverter = resource.getResourceSet().getURIConverter();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			if(uri.segmentCount() < 2) {
				return null;
			}
			IProject modelProject = root.getProject(uri.segment(1));
			if(modelProject.exists()) {
				IDLModelElementsCreator mec = new IDLModelElementsCreator(modelProject /*, "generated with Papyrus" */);		
				mec.createPackageableElement(classifier, null);
			}
		}
		return null;
	}
}
