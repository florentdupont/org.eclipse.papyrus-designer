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

package org.eclipse.papyrus.designer.languages.cpp.codegen.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.papyrus.designer.languages.cpp.codegen.transformation.CppModelElementsCreator;
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.ClassUtils;
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.LocateCppProject;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * Handler for C++ code generation
 */
public class GenerateCodeHandler extends CmdHandler {

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

	/**
	 * Generate code for a passed packageable element and the elements required by it (in the sense of #included statements)
	 *
	 * @param mec
	 *            model elements creator
	 * @param pe
	 *            the element that should be generated
	 * @param alreadyHandled
	 *            list of packageable elements for which code has already been generated.
	 * @param recurse
	 *            if the passed packageableElement is a package, generate code for its contents (recursively).
	 */
	public void generate(CppModelElementsCreator mec, PackageableElement pe, EList<PackageableElement> alreadyHandled, boolean recurse) {
		alreadyHandled.add(pe);
		mec.createPackageableElement(pe, null, false);
		// Eventual refresh is done in createPackageableElement

		// if recursion is active, go into all sub-elements
		if (pe instanceof Package && recurse) {
			for (PackageableElement subPe : ((Package) pe).getPackagedElements()) {
				if (!alreadyHandled.contains(subPe)) {
					generate(mec, subPe, alreadyHandled, recurse);
				}
			}
		}

		// add required classifiers
		if (pe instanceof Classifier) {
			EList<Classifier> requiredClassifiers = ClassUtils.requiredClassifiers((Classifier) pe);
			
			for (Classifier requiredClassifier : requiredClassifiers) {
				if (!alreadyHandled.contains(requiredClassifier)) {
					generate(mec, requiredClassifier, alreadyHandled, false);
				}
			}
		}
		
		// owning package is required by generated code.
		Package owningPackage = pe.getNearestPackage();
		if ((owningPackage != null) && (owningPackage != pe)) {
			if (!alreadyHandled.contains(owningPackage)) {
				generate(mec, owningPackage, alreadyHandled, false);
			}
		}
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
			CppModelElementsCreator mec = new CppModelElementsCreator(modelProject);
			generate(mec, pe, new BasicEList<PackageableElement>(), true);
		}
		return null;
	}
}
