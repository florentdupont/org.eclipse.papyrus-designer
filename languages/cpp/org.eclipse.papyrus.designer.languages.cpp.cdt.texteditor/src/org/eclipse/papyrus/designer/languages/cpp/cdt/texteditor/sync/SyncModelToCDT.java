/*******************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ansgar Radermacher - ansgar.radermacher@cea.fr CEA LIST - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.sync;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen2;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen.FILE_KIND;
import org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.TextEditorConstants;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Constants;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.uml2.uml.Classifier;

/**
 * Main listener for model changes (registered via plugin.xml). It will delegate
 * to the sub-listeners for specific sub-elements (type, operation, port, ...) that
 * can be found in this package
 *
 */
public class SyncModelToCDT {

	private static final String CPP_SUFFIX = "cpp"; //$NON-NLS-1$
	/**
	 * set to true, if a synchronization from an CDT editor to the model is active
	 */
	public static boolean syncFromEditor;

	public static IFile syncModelToCDT(Classifier classifier, String generatorID) {
		if ((classifier == null) || (classifier.eResource() == null)) {
			return null;
		}

		ILangCodegen codegen = LanguageCodegen.getGenerator(TextEditorConstants.CPP, generatorID);
		
		IProject modelProject = codegen.getTargetProject(classifier, false);
		if (modelProject == null) {
			return null;
		}

		IContainer srcPkg = null;
		IFile cppFile = null;
		try {
			codegen.generateCode(modelProject, classifier, null); // need listener for sync in both directions!

			String suffix = (codegen instanceof ILangCodegen2) ?
				suffix = ((ILangCodegen2) codegen).getSuffix(FILE_KIND.BODY) : CPP_SUFFIX;
			
			cppFile = modelProject.getFile(new Path(codegen.getFileName(modelProject, classifier) + Constants.DOT + suffix));
	
			// IStorage storage = new TextStorage(string);
		} finally {
			// Refresh the container for the newly created files. This needs to be done even
			// during error because of the possibility for partial results.
			try {
				if (srcPkg != null) {
					srcPkg.refreshLocal(IResource.DEPTH_INFINITE, null);
				}
			} catch (CoreException e) {
				Activator.log.error(e);
			}
		}
		return cppFile;
	}
}
