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

package org.eclipse.papyrus.designer.languages.java.codegen.ui.handlers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.papyrus.designer.languages.java.codegen.transformation.JavaMerger;
import org.eclipse.papyrus.designer.languages.java.codegen.transformation.JavaModelElementsCreator;
import org.eclipse.papyrus.designer.languages.java.codegen.utils.ClassUtils;
import org.eclipse.papyrus.designer.languages.java.codegen.utils.JavaGenUtils;
import org.eclipse.papyrus.designer.languages.java.codegen.utils.LocateJavaProject;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * Handler for Java code generation
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
	public void generate(JavaModelElementsCreator mec, PackageableElement pe, EList<PackageableElement> alreadyHandled, boolean recurse) {
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
				if (!alreadyHandled.contains(requiredClassifier) && requiredClassifier.getOwner() instanceof Package) {
					generate(mec, requiredClassifier, alreadyHandled, false);
				}
			}
		}
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Find a better solution to clear imports map
		JavaGenUtils.imports = new HashMap<NamedElement, EList<String>>();
		
		if (selectedEObject instanceof PackageableElement) {
			PackageableElement pe = (PackageableElement) selectedEObject;

			IProject targetProject = LocateJavaProject.getTargetProject(pe, true);
			if (targetProject == null) {
				return null;
			}

			// get the container for the current element
			JavaModelElementsCreator mec = new JavaModelElementsCreator(targetProject, pe);
			generate(mec, pe, new BasicEList<PackageableElement>(), true);
			
			JavaMerger merger = new JavaMerger(targetProject, mec.getSourceFolder(), mec.getTargetFolder());
			merger.merge();
		}
		
		// TODO Find a better solution to clear imports map
		if (JavaGenUtils.imports != null) {
			Iterator<Entry<NamedElement, EList<String>>> it = JavaGenUtils.imports.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				if (pair != null && pair.getValue() != null) {
					((List) pair.getValue()).clear();
				}
				it.remove();
			}
			
			JavaGenUtils.imports.clear();
		}
		
		return null;
	}
}
