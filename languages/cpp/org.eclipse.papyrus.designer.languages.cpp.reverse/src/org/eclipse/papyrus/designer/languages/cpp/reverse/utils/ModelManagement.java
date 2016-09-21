/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Shuai Li (CEA LIST) <shuai.li@cea.fr> - Initial API and implementation
 *   Van Cam Pham (CEA LIST) <vancam.pham@cea.fr> - Reverse implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.reverse.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.CommandSupport;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Manages a model (and the associated resource). Allows saving this model. It
 * uses its own static resource set.
 *
 */
public class ModelManagement {

	/**
	 * Create a new model and associate it with a temporary resource
	 */
	public ModelManagement() {

	}

	public void createOrgetModel(final String name, final String uri, final boolean create, final boolean reset) {
		getResourceSet();
		CommandSupport.executeCmd(resourceSet, new Runnable() {
			
			@Override
			public void run() {
				Resource resource = null;
				if (create) {
					resource = resourceSet.createResource(URI.createURI(uri));
				} else {
					try {
						resource = resourceSet.getResource(URI.createURI(uri), true);
						if (reset) {
							resource.delete(null);
							resource = resourceSet.createResource(URI.createURI(uri));
						}
					} catch (Exception e) {
						resource = resourceSet.createResource(URI.createURI(uri));
					}
				}
				
				if (!resources.contains(resource)) {
					resources.add(resource);
				}
				Model model = null;
				if (create) {
					model = UMLFactory.eINSTANCE.createModel();
					resource.getContents().add(model);
				} else {
					model = UMLUtil.load(resourceSet,resource.getURI(), UMLPackage.Literals.MODEL);
					if (model == null) {
						model = UMLFactory.eINSTANCE.createModel();
						resource.getContents().add(model);
					}
				}
				model.setName(name);
				models.add(model);
			}
		});
		
	}

	public void dispose() {
		for (Iterator iterator = models.iterator(); iterator.hasNext();) {
			Model model = (Model) iterator.next();
			if (model != null) {
				model.destroy();
				model = null;
			}
		}
		
		for (Resource resource : resources) {
			resource.unload();
		}
		
		resourceSet.getResources().removeAll(resources);
		
		resources.clear();
	}

	/**
	 * provide access to the model
	 * 
	 * @return the model amanaged by this instance of model manager
	 */
	public List<Model> getModels() {
		return models;
	}

//	/**
//	 * Save a model within the given project at a default location. This
//	 * location is [model.name/].uml within the project root.
//	 * 
//	 * @link ModelManagement.getPath
//	 * 
//	 * @param project
//	 *            an existing project
//	 */
//	public void saveModel(IProject project) {
//		saveModel(getPath(project, null, null));
//	}
//
//	/**
//	 * Save the model within a given project, folder and postfix
//	 * 
//	 * @param project
//	 * @param modelFolder
//	 * @param modelPostfix
//	 */
//	public void saveModel(IProject project, String modelFolder,
//			String modelPostfix) {
//		String path = this.getPath(project, modelFolder, this.getModels()
//				.get(0).getName()
//				+ modelPostfix);
//		this.saveModel(path);
//	}
	
	public static Map<Object, Object> getDefaultSaveOptions() {
		Map<Object, Object> saveOptions = new HashMap<Object, Object>();

		// default save options.
		saveOptions.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		saveOptions.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_SKIP_ESCAPE_URI, Boolean.FALSE);
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
		saveOptions.put(XMLResource.OPTION_USE_FILE_BUFFER, true);
		saveOptions.put(XMLResource.OPTION_FLUSH_THRESHOLD, 4 * 1024 * 1024); // 4 MB Buffer

		// see bug 397987: [Core][Save] The referenced plugin models are saved using relative path
		saveOptions.put(XMLResource.OPTION_URI_HANDLER, new org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl.PlatformSchemeAware());

		return saveOptions;
	}

	/**
	 * Save a model using the passed path
	 *
	 * @param path
	 *            A string representation of the path. It will be converted into
	 *            a URI
	 */
	public void saveModel(List<String> paths) {

		try {
			for (int i = 0; i < paths.size(); i++) {
				URI uri = URI.createURI(paths.get(i));
				resources.get(i).setURI(uri);
				EList<EObject> contents = resources.get(i).getContents();
				for (Iterator<EObject> allContents = UML2Util.getAllContents(models.get(i),	true, false); allContents.hasNext();) {
					EObject eObject = allContents.next();
					if (eObject instanceof Element) {
						contents.addAll(((Element) eObject)
								.getStereotypeApplications());
					}
				}

				resources.get(i).save(getDefaultSaveOptions());
			}
			
		} catch (IOException e) {
			// Activator.log.error(e);
		}
	}

	/**
	 * Return a path based on project, folder and file name
	 *
	 * @param project
	 *            an existing project
	 * @param subFolder
	 *            a subfolder within the project (will be created, if it does
	 *            not exist) if null, the project will be saved in the root of
	 *            the project
	 * @param filename
	 *            the name of the file or null (in his case, the name of the
	 *            model with the postfix .uml is used)
	 *
	 * @return The access path to a file
	 */
	public String getPath(IProject project, String subFolder, String filename) {
		IFile file;
		if (filename == null) {
			filename = models.get(0).getName() + ".uml"; //$NON-NLS-1$
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
	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	public static ResourceSet resourceSet = null;

	private List<Model> models = new ArrayList<Model>();

	private List<Resource> resources = new ArrayList<Resource>();
}
