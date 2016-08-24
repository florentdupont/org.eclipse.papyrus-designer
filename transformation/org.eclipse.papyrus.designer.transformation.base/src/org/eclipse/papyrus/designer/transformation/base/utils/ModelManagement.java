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
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.papyrus.designer.transformation.base.Activator;
import org.eclipse.papyrus.designer.transformation.base.Messages;
import org.eclipse.papyrus.designer.transformation.base.UIContext;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
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
	 * 
	 * @return the model amanaged by this instance of model manager
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Save a model within the given project at a default location.
	 * This location is [model.name/].uml within the project root.
	 * 
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
	 * 
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
	 * @param path
	 *            A string representation of the path. It will be converted into a URI
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
	public static ModelManagement createNewModel(Package existingModel, String name, boolean copyImports) throws TransformationException {
		ModelManagement mm = new ModelManagement();
		Model newModel = mm.getModel();
		newModel.setName(name);

		try {
			// copy profile application
			for (Profile profile : existingModel.getAppliedProfiles()) {
				// reload profile in resource of new model
				UIContext.monitor.subTask(Messages.InstantiateDepPlan_InfoApplyProfile + profile.getQualifiedName());

				if (profile.eResource() == null) {
					String profileName = profile.getQualifiedName();
					if (profileName == null) {
						if (profile instanceof MinimalEObjectImpl.Container) {
							URI uri = ((MinimalEObjectImpl.Container) profile).eProxyURI();
							if (uri != null) {
								throw new TransformationException(String.format(Messages.InstantiateDepPlan_CheckInputModelProfileNoRes, uri));
							}
						}
						throw new TransformationException(Messages.InstantiateDepPlan_CheckInputModelProfileNoResNoName);
					}
					throw new TransformationException(String.format(Messages.InstantiateDepPlan_CheckInputModelProfile3, profileName));
				}

				Resource profileResource = null;
				try {
					profileResource = ModelManagement.getResourceSet().getResource(profile.eResource().getURI(), true);
				} catch (WrappedException e) {
					// read 2nd time (some diagnostic errors are raised only
					// once)
					Activator.log.warn("Warning: exception in profile.eResource() " + e.getMessage()); //$NON-NLS-1$
					profileResource = ModelManagement.getResourceSet().getResource(profile.eResource().getURI(), true);
				}
				if (profileResource.getContents().size() == 0) {
					throw new TransformationException(String.format("Cannot copy profile with URI %s. Check whether the URI corresponds to an existing location", profileResource.getURI()));
				}
				Profile newProfileTop = (Profile) profileResource.getContents().get(0);
				Profile newProfile;
				String qname = profile.getQualifiedName();
				if ((qname != null) && qname.contains("::")) { //$NON-NLS-1$
					// profile is a sub-profile within same resource
					newProfile = (Profile) ElementUtils.getQualifiedElement(newProfileTop, qname);
				} else {
					newProfile = newProfileTop;
				}
				newProfile.getMember("dummy"); // force profile loading //$NON-NLS-1$
				newModel.applyProfile(newProfile);
			}
		} catch (IllegalArgumentException e) {
			throw new TransformationException(Messages.InstantiateDepPlan_IllegalArgumentDuringCopy + e.toString());
		}

		// copier imports (and load resources associated - TODO: might not be
		// necessary)
		// While this is useful in general, it implies that code for imported
		// models
		// has been generated and compiled (for the right target) into a
		// library. This may be
		// quite tedious, unless automatically managed.
		// Therefore we do not activate this option in a first pass of the model
		// transformations.
		if (copyImports) {
			for (Package importedPackage : existingModel.getImportedPackages()) {
				if (importedPackage == null) {
					throw new TransformationException(Messages.InstantiateDepPlan_CheckInputImportPkg);
				}
				if (importedPackage.eResource() == null) {
					String errorMsg = Messages.InstantiateDepPlan_CheckInputImportPkgNoRes;
					if (importedPackage instanceof MinimalEObjectImpl.Container) {
						URI uri = ((MinimalEObjectImpl.Container) importedPackage).eProxyURI();
						if (uri != null) {
							errorMsg += " - URI: " + uri.devicePath(); //$NON-NLS-1$
						}
					}
					throw new TransformationException(errorMsg);
				}
				newModel.createPackageImport(importedPackage);
				UIContext.monitor.subTask(String.format(Messages.InstantiateDepPlan_InfoImportPackage, importedPackage.getName()));

				try {
					importedPackage.eResource().load(null);
					newModel.getMember("dummy"); // force loading of model //$NON-NLS-1$
				} catch (IOException e) {
					throw new TransformationException(e.getMessage());
				}
			}
		}
		StUtils.copyStereotypes(existingModel, newModel);

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

	private Model model;

	private Resource resource;
}
