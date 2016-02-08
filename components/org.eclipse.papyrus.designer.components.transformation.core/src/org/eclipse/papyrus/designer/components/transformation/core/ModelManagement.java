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

package org.eclipse.papyrus.designer.components.transformation.core;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;


/**
 * Manages a model (and the associated resource). Allows saving this model. It uses its own static resource set.
 *
 */
public class ModelManagement {

	/**
	 * Create a new model and associate it with a temporary
	 * resource
	 */
	public ModelManagement() {
		getResourceSet();
		model = UMLFactory.eINSTANCE.createModel();
		resource = resourceSet.createResource(URI.createURI("temp.uml")); //$NON-NLS-1$
		resource.getContents().add(model);
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
	 * @return the model amanaged by this instance of model manager
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Save a model within the given project at a default location.
	 * This location is [model.name/].uml within the project root.
	 * @link ModelManagement.getPath
	 *  
	 * @param project
	 *            an existing project
	 */
	public void saveModel(IProject project) {
		saveModel(getPath(project, null, null));
	}

	/**
	 * Save the model within a given project, folder and postfix
	 * @param project
	 * @param modelFolder
	 * @param modelPostfix
	 */
	public void saveModel(IProject project, String modelFolder, String modelPostfix) {
		String path = this.getPath(project, modelFolder, this.getModel().getName() + modelPostfix);
		this.saveModel(path);
	}

	/**
	 * Save a model using the passed path
	 *
	 * @param path A string representation of the path. It will be converted into a URI
	 */
	public void saveModel(String path) {

		try {
			URI uri = URI.createURI(path);

			ResourceSetImpl resourceSet = new ResourceSetImpl();
			resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

			resource.setURI(uri);
			EList<EObject> contents = resource.getContents();

			for (Iterator<EObject> allContents = UML2Util.getAllContents(model, true, false); allContents.hasNext();) {
				EObject eObject = allContents.next();

				if (eObject instanceof Element) {
					contents.addAll(((Element) eObject).getStereotypeApplications());
				}
			}
			resource.save(null);
		}
		catch (IOException e) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			file = ifolder.getFile(filename);
		} else {
			file = project.getFile(filename);
		}
		return file.getFullPath().toString();
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

	private Model model;

	private Resource resource;
}
