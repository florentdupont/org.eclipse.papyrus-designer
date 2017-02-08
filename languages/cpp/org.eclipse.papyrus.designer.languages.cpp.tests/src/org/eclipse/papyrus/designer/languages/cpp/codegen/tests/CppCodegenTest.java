/*
 * Copyright (c) 2014 Zeligsoft (2009) Limited.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.cpp.codegen.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.languages.common.base.TestInfo;
import org.eclipse.papyrus.designer.languages.common.testutils.FileComparison;
import org.eclipse.papyrus.designer.languages.common.testutils.RecursiveCopy;
import org.eclipse.papyrus.designer.languages.common.testutils.TestConstants;
import org.eclipse.papyrus.infra.services.openelement.service.OpenElementService;
import org.eclipse.papyrus.infra.ui.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.junit.utils.rules.HouseKeeper;
import org.eclipse.papyrus.junit.utils.rules.PapyrusEditorFixture;
import org.eclipse.papyrus.junit.utils.rules.PluginResource;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.uml2.uml.Package;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("nls")
@PluginResource("resources/CppCodegenTest.di")
public class CppCodegenTest {

	static {
		// This system property avoids opening dialogs during Papyrus operations. It must
		// be set before trying to load any of the Papyrus classes.
		TestInfo.runHeadless();
	}

	private static final String GENERATE_COMMAND_ID = "org.eclipse.papyrus.designer.languages.cpp.codegen.command";

	private static final String ModelName = "CppCodegenTest.uml";

	private static final String GenProjectName = "org.eclipse.papyrus.cppgen.CppCodegenTest";

	private static final String GenFolderName = "CppCodegenTest";

	private static final String Class1_fragment = "_x6ArECrKEeOncLSXAkfRBA";

	private static final String Class2_fragment = "_0E-t0CrKEeOncLSXAkfRBA";

	private static final String Class3_fragment = "_29UM4CrKEeOncLSXAkfRBA";

	private static final String Class4_fragment = "_-j3HgCrKEeOncLSXAkfRBA";

	private static final String Class5_fragment = "_hTMV0CumEeOcwILjsIdkdw";

	private static final String Class6_fragment = "_OJ7A0CxUEeOcwILjsIdkdw";

	private static final String Class7_fragment = "_ZqD3YCz9EeOcwILjsIdkdw";

	private static final String Class8_fragment = "_qS9iYDEmEeOSfbt-FmCdoQ";

	private static final String Class9_fragment = "_jcK5MDG0EeOOEc5pE2t6oQ";

	private static final String Package1_fragment = "_nZ5DgDEmEeOSfbt-FmCdoQ";

	private static final String Model_fragment = "_1_ToYCoNEeOncLSXAkfRBA";

	private static IProject modelProject;

	private static IHandlerService handlerService;

	private static URI modelUri;

	private static URI genCodeUri;

	private static IMultiDiagramEditor multiEditor;

	private static OpenElementService elementActivator;

	private static Package model;

	@ClassRule
	public static HouseKeeper.Static houseKeeper = new HouseKeeper.Static();

	@ClassRule
	/** The model set fixture. */
	public final static PapyrusEditorFixture modelSetFixture = new PapyrusEditorFixture();

	@BeforeClass
	public static void loadProject() throws Exception {

		handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);

		// Obtain model project
		modelProject = modelSetFixture.getProject().getProject();

		String modelProjectName = modelProject.getName();

		// copy expected results folder into model project
		Bundle srcBundle = FrameworkUtil.getBundle(CppCodegenTest.class);
		RecursiveCopy copier = new RecursiveCopy(houseKeeper);
		copier.copy(srcBundle, TestConstants.EXPECTED_RESULT, modelProject, "");

		// Setup the base modelUri for convenience in the test cases.
		modelUri = URI.createPlatformResourceURI("/" + modelProjectName + '/' + ModelName, true);
		assertNotNull(modelUri);

		model = modelSetFixture.getModel();
		assertNotNull(model);

		multiEditor = modelSetFixture.getEditor();
		assertNotNull(multiEditor);

		// Model elements are selected with the appropriate service.
		elementActivator = multiEditor.getServicesRegistry().getService(OpenElementService.class);
		assertNotNull(elementActivator);

		elementActivator.startService();

		// Setup the base genCodeUri for convenience in the test cases.
		genCodeUri = URI.createPlatformPluginURI("/" + modelProjectName + '/' + TestConstants.EXPECTED_RESULT, true);
		assertNotNull(genCodeUri);
	}

	@Test
	public void testGenerateClass1() throws Exception {
		assertGenerate(Class1_fragment);
		assertGeneratedMatchesExpected("Class1.h");
		assertGeneratedMatchesExpected("Class1.cpp");
	}

	@Test
	public void testGenerateClass2() throws Exception {
		assertGenerate(Class2_fragment);
		assertGeneratedMatchesExpected("Class2.h");
		assertGeneratedMatchesExpected("Class2.cpp");
	}

	@Test
	public void testGenerateClass3() throws Exception {
		assertGenerate(Class3_fragment);
		assertGeneratedMatchesExpected("Class3.h");
		assertGeneratedMatchesExpected("Class3.cpp");
	}

	@Test
	public void testGenerateClass4() throws Exception {
		assertGenerate(Class4_fragment);
		assertGeneratedMatchesExpected("Class4.h");
		assertGeneratedMatchesExpected("Class4.cpp");
	}

	@Test
	public void testGenerateClass5() throws Exception {
		assertGenerate(Class5_fragment);
		assertGeneratedMatchesExpected("Class5.h");
		assertGeneratedMatchesExpected("Class5.cpp");
	}

	@Test
	public void testGenerateClass6() throws Exception {
		assertGenerate(Class6_fragment);
		assertGeneratedMatchesExpected("Class6.h");
		assertGeneratedMatchesExpected("Class6.cpp");
	}

	@Test
	public void testGenerateClass7() throws Exception {
		assertGenerate(Class7_fragment);
		assertGeneratedMatchesExpected("Class7.h");
		assertGeneratedMatchesExpected("Class7.cpp");
	}

	@Test
	public void testGenerateClass8() throws Exception {
		assertGenerate(Class8_fragment);
		assertGeneratedMatchesExpected("Class8.h", "Package1");
		assertGeneratedMatchesExpected("Class8.cpp", "Package1");
	}

	@Test
	public void testGenerateClass9() throws Exception {
		assertGenerate(Class9_fragment);
		assertGeneratedMatchesExpected("Class9.h", "Package1");
		assertGeneratedMatchesExpected("Class9.cpp", "Package1");
	}

	@Test
	public void testGeneratePackage1NamespaceHeader() throws Exception {
		assertGenerate(Package1_fragment);
		assertGeneratedMatchesExpected("Pkg_Package1.h", "Package1");
	}


	@Test
	public void testGenerateModelNamespaceHeader() throws Exception {
		assertGenerate(Model_fragment);
		assertGeneratedMatchesExpected("Pkg_CppCodegenTest.h");
	}

	private void assertGenerate(String fragment) throws Exception {
		selectSemanticElement(fragment);
		handlerService.executeCommand(GENERATE_COMMAND_ID, null);
	}

	private void selectSemanticElement(String uriFragment) throws Exception {
		URI elementUri = modelUri.appendFragment(uriFragment);
		EObject semantic = model.eResource().getResourceSet().getEObject(elementUri, true);

		// #openSemanticElement returns the multiEditor if successful and null otherwise

		// the open often fails if pages are passed in, so we first try to open without specifying
		// pages
		IMultiDiagramEditor editor = elementActivator.openSemanticElement(semantic);
		assertNotNull(editor);

		try {
			// wait (max 1 second) until editor becomes available
			int i = 0;
			while (editor.getActiveEditor() == null) {
				editor = elementActivator.openSemanticElement(semantic);
				Thread.sleep(10);
				if (i++ > 100) {
					fail("Timeout during wait for editor to become active");
				}
			}
		} catch (InterruptedException e) {
		}

		// make sure there is an active multiEditor so that the selection will be available
		assertNotNull(editor.getActiveEditor());
	}

	/**
	 * Compare the files in folder with what we expect to see. When comparing
	 * file content, filter out whitespace which will replace all whitespace
	 * with a single space in the actual file content and the expected file
	 * content in order to avoid problems with differences caused by code
	 * formatting options where the test suite is run. Then a simple string
	 * comparison is done.
	 */
	private void assertGeneratedMatchesExpected(String fileName, String... depthSegments) throws Exception {
		IFolder srcGenFolder = FileComparison.getGeneratedProject(GenProjectName).getFolder(TestConstants.SRC_GEN);
		assertTrue("src-gen folder \"" + srcGenFolder + "\" does not exist", srcGenFolder.exists());
		IFolder generatedFolder = srcGenFolder.getFolder(GenFolderName);
		FileComparison.assertGeneratedMatchesExpected(generatedFolder, modelProject, fileName, depthSegments);
	}
}
