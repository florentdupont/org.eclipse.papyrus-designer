/*
 * Copyright (c) 2014 Zeligsoft (2009) Limited.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.qompass.designer.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.papyrus.junit.utils.rules.HouseKeeper;
import org.eclipse.papyrus.qompass.designer.core.ModelManagement;
import org.eclipse.papyrus.qompass.designer.core.Utils;
import org.eclipse.papyrus.qompass.designer.core.transformations.LazyCopier;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;


@SuppressWarnings("nls")
public class CreateExpectedModels {

	static {
		// This system property avoids opening dialogs during Papyrus operations. It must
		// be set before trying to load any of the Papyrus classes.
		System.setProperty("papyrus.run-headless", Boolean.TRUE.toString());
	}

	private static final IProgressMonitor npm = new NullProgressMonitor();

	private static IProject modelProject;

	private static Model model;

	protected static final ModelManagement modelManagement = new ModelManagement();

	protected static final ResourceSet resourceSet = ModelManagement.getResourceSet();

	@ClassRule
	public static HouseKeeper.Static houseKeeper = new HouseKeeper.Static();

	@BeforeClass
	public static void loadProject() throws Exception {

		// Setup the base modelUri for convenience in the test cases.
		URI modelUri = URI.createPlatformResourceURI(ModelNames.MainModelPath, true);
		assertNotNull(modelUri);

		Resource inResource = resourceSet.getResource(modelUri, true);
		List<EObject> inObjects = inResource.getContents();

		Object modelObj = inObjects.get(0);
		assertTrue(model instanceof Model);
		model = (Model) modelObj;
	}

	@AfterClass
	public static void cleanup() throws Exception {
		if (modelProject == null) {
			return;
		}

		// Now we should be able to delete the project without opening any confirmation dialogs.
		modelProject.delete(true, true, npm);
		modelProject = null;
	}

	@Test
	public void testLazyCopierFullCopy() throws Exception {
		Model targetModel = modelManagement.getModel();

		LazyCopier copier = new LazyCopier(model, targetModel, false, false);
		Model genModel = copier.getCopy(model);
		assertNotNull(genModel);

		saveModel(ModelNames.ExpectedModel_LazyCopierFullCopy);
	}

	@Test
	public void testLazyCopierIncrementalCopy() throws Exception {
		Model targetModel = modelManagement.getModel();

		LazyCopier copier = new LazyCopier(model, targetModel, false, false);
		NamedElement class1 = Utils.getQualifiedElement(model, MEnames.CLASS1);
		NamedElement class3 = Utils.getQualifiedElement(model, MEnames.CLASS3);
		assertNotNull(class3);

		NamedElement genClass3 = copier.getCopy(class3);
		assertNotNull(genClass3);

		// class3 should be in transformed model. Since class3 inherits from class2, this
		// class and the inheritance relationship as well should be copied as well.
		saveModel(ModelNames.ExpectedModel_LazyCopierIncrementalCopyA);

		// class1 should be in transformed model as well as an association between
		// class1 and class2
		NamedElement genClass1 = copier.getCopy(class1);
		assertNotNull(genClass1);
		saveModel(ModelNames.ExpectedModel_LazyCopierIncrementalCopyB);
	}

	public static void main(String args[]) {
		CreateExpectedModels cem = new CreateExpectedModels();
		try {
			loadProject();
			cem.testLazyCopierFullCopy();
			cem.testLazyCopierIncrementalCopy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void saveModel(String fileName) {
		modelManagement.saveModel(ModelNames.ExpectedModelFolder + "/" + fileName);
	}
}