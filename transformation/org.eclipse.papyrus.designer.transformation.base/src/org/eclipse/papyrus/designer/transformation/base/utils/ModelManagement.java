/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.transformation.base.utils;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.designer.transformation.base.Activator;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;


/**
 * Manages a model (and the associated resource). Allows saving this model. It uses its own static resource set.
 *
 */
public class ModelManagement {

	/**
	 * Create a new model and associate it with a temporary
	 * resource
	 */
	public ModelManagement(Package model) {
		getResourceSet();
		this.model = model;
		resource = resourceSet.createResource(URI.createURI("temp.uml")); //$NON-NLS-1$
		resource.getContents().add(model);
	}

	/**
	 * Create a new model and associate it with a temporary
	 * resource
	 */
	public ModelManagement() {
		this(UMLFactory.eINSTANCE.createModel());
	}
	
	public void dispose() {
		if (model != null) {
			model.destroy();
			model = null;
		}
		resourceSet.getResources().remove(resource);
	}

	/**
	 * provide access to the model
	 * 
	 * @return the model managed by this instance of model manager
	 */
	public Package getModel() {
		return model;
	}

	/**
	 * Set the URI of the managed resource via a string
	 * @param path
	 */
	public void setURI(String path) {
		URI uri = URI.createURI(path);
		setURI(uri);	
	}
	
	/**
	 * Set the URI of the managed resource
	 * @param uri
	 */
	public void setURI(URI uri) {
		resource.setURI(uri);
	}
	
	/**
	 * Set the URI  model within a given project, folder and postfix
	 * 
	 * @param project
	 * @param modelFolder
	 * @param modelPostfix
	 */
	public void setURI(IProject project, String modelFolder, String modelPostfix) {
		String path = this.getPath(project, modelFolder, this.getModel().getName() + modelPostfix);
		setURI(path);
	}

	/**
	 * Save a model
	 *
	 * @param path
	 *            A string representation of the path. It will be converted into a URI
	 */
	public void save() {

		try {
			resource.save(null);
		} catch (IOException e) {
			Activator.log.error(e);
		}
	}

	/**
	 * Return a path based on project, folder and file name
	 *
	 * @param project
	 *            an existing project
	 * @param subFolder
	 *            a subfolder within the project (will be created, if it does not exist)
	 *            if null, the project will be saved in the root of the project
	 * @param filename
	 *            the name of the file or null (in his case, the name of the
	 *            model with the postfix .uml is used)
	 *
	 * @return The access path to a file
	 */
	public String getPath(IProject project, String subFolder, String filename) {
		IFile file;
		if (filename == null) {
			filename = model.getName() + ".uml"; //$NON-NLS-1$
		}
		if (subFolder != null) {
			IFolder ifolder = project.getFolder(subFolder);
			if (!ifolder.exists()) {
				try {
					ifolder.create(false, true, null);
				} catch (CoreException e) {
					Activator.log.error(e);
				}
			}
			file = ifolder.getFile(filename);
		} else {
			file = project.getFile(filename);
		}
		return file.getFullPath().toString();
	}

	/**
	 * Create a new empty model from an existing model that applies the same
	 * profiles and has the same imports
	 *
	 * @param existingModel
	 *            an existing model
	 * @param name
	 *            the name of the new model
	 * @param copyImports
	 *            true, if (top-level) package import should be copied from the existing into the new model
	 * @return the model-management instance for the new model (use getModel() to obtain the actual model)
	 * @throws TransformationException
	 */
	public static ModelManagement createNewModel(String name) throws TransformationException {
		ModelManagement mm = new ModelManagement();
		Package newModel = mm.getModel();
		newModel.setName(name);

		return mm;
	}
	
	/**
	 * return the used resource set (a singleton)
	 */
	public static ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	private static ResourceSet resourceSet = null;

	private Package model;

	private Resource resource;
}
