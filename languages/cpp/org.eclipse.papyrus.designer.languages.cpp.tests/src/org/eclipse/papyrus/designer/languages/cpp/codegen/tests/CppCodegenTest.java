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

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.sashwindows.di.service.IPageManager;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.services.openelement.service.OpenElementService;
import org.eclipse.papyrus.infra.ui.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.junit.utils.EditorUtils;
import org.eclipse.papyrus.junit.utils.rules.HouseKeeper;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

@SuppressWarnings("nls")
public class CppCodegenTest {

	static {
		// This system property avoids opening dialogs during Papyrus operations.  It must
		// be set before trying to load any of the Papyrus classes.
		System.setProperty("papyrus.run-headless", Boolean.TRUE.toString());
	}

	private static final String GENERATE_COMMAND_ID = "org.eclipse.papyrus.designer.languages.cpp.codegen.command";

	private static final String EDITOR_ID = "org.eclipse.papyrus.infra.core.papyrusEditor";

	private static final String ModelProjectName = "project";

	private static final String ModelName = "CppCodegenTest.uml";

	private static final String GenProjectName = "org.eclipse.papyrus.cppgen.CppCodegenTest";

	private static final String GenFolderName = "CppCodegenTest";

	private static final String ModelPath = "/" + ModelProjectName + '/' + ModelName;

	private static final String ExpectedGenFolderName = "ExpectedModel";

	private static final String ExpectedGenFolderPath = "/" + ModelProjectName + '/' + ExpectedGenFolderName;

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

	private static final IProgressMonitor npm = new NullProgressMonitor();

	private static IProject modelProject;

	private static IProject genProject;

	private static IHandlerService handlerService;

	private static URI modelUri;

	private static URI genCodeUri;

	private static IWorkbenchPage page;

	private static PapyrusMultiDiagramEditor editor;

	private static OpenElementService elementActivator;

	private static UmlModel model;

	@ClassRule
	public static HouseKeeper.Static houseKeeper = new HouseKeeper.Static();

	@BeforeClass
	public static void loadProject() throws Exception {

		handlerService = (IHandlerService)PlatformUI.getWorkbench().getService(IHandlerService.class);
		
		// Create a project to hold the model, make sure it has C++ natures to avoid confirmation
		// dialog during code generation.
		modelProject = houseKeeper.createProject(ModelProjectName);

		// Import the LANGUAGE_NAME .h and .cpp files from this test plugin to the runtime workbench.
		IFile diFile = houseKeeper.createFile(modelProject, "CppCodegenTest.di", "resources/CppCodegenTest.di");
		houseKeeper.createFile(modelProject, "CppCodegenTest.notation", "resources/CppCodegenTest.notation");
		houseKeeper.createFile(modelProject, "CppCodegenTest.uml", "resources/CppCodegenTest.uml");

		String[] targetFiles = new String[]{
			"Class1.h", "Class1.cpp",
			"Class2.h", "Class2.cpp",
			"Class3.h", "Class3.cpp",
			"Class4.h", "Class4.cpp",
			"Class5.h", "Class5.cpp",
			"Class6.h", "Class6.cpp",
			"Class7.h", "Class7.cpp",
			"Pkg_CppCodegenTest.h",
			"Package1/Class8.h", "Package1/Class8.cpp",
			"Package1/Class9.h", "Package1/Class9.cpp",
			"Package1/Pkg_Package1.h"
		};

		for (String targetFile: targetFiles){
			houseKeeper.createFile(modelProject, targetFile, ExpectedGenFolderName + "/" + targetFile);
		}

		// Setup the base modelUri for convenience in the test cases.
		modelUri = URI.createPlatformResourceURI(ModelPath, true);
		assertNotNull(modelUri);

		// Get the currently active workbench page so we can control the editor that is about
		// to be opened.
		page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		editor = (PapyrusMultiDiagramEditor) EditorUtils.openEditor(diFile);
		assertNotNull(editor);

		model = UmlUtils.getUmlModel();
		assertNotNull(model);

		// Model elements are selected with the appropriate service.
		elementActivator = editor.getServicesRegistry().getService(OpenElementService.class);
		assertNotNull(elementActivator);

		//Setup the base genCodeUri for convenience in the test cases.
		genCodeUri = URI.createPlatformPluginURI(ExpectedGenFolderPath, true);
		assertNotNull(genCodeUri);
	}

	@AfterClass
	public static void cleanup() throws Exception {
		if(modelProject == null) {
			return;
		}

		// Close the editor without saving anything.  This is required in order to avoid dialogs
		// about "do you want to save these changes" when the project is deleted.
		if(page != null) {
			page.closeAllEditors(false);
		}

		// Now we should be able to delete the project without opening any confirmation dialogs.
		modelProject.delete(true, true, npm);
		modelProject = null;
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
		EObject semantic = model.getResource().getResourceSet().getEObject(elementUri, true);

		// #openSemanticElement returns the editor if successful and null otherwise

		// the open often fails if pages are passed in, so we first try to open without specifying
		// pages
		IMultiDiagramEditor editor = elementActivator.openSemanticElement(semantic);//, pages.toArray());
		assertNotNull(editor);

		// If there isn't an active editor, then we try passing in the list of pages.  From observation,
		// this is needed on the first call (first test case) but causes problems on later ones.
		if(editor.getActiveEditor() == null) {
			ServicesRegistry registry = (ServicesRegistry)editor.getAdapter(ServicesRegistry.class);
			assertNotNull(registry);

			IPageManager pageManager = registry.getService(IPageManager.class);
			assertNotNull(pageManager);

			List<Object> pages = pageManager.allPages();
			assertNotNull(pages);
			assertTrue(pages.size() > 0);

			editor = elementActivator.openSemanticElement(semantic, pages.toArray());
			assertNotNull(editor);
		}

		// make sure there is an active editor so that the selection will be available
		assertNotNull(editor.getActiveEditor());
	}

	private static String getFileContents(IFile file) throws CoreException {
		InputStream inStream = file.getContents();
		assertNotNull("Contents of file \"" + file.getName() + "\" are empty.", inStream);
		String content = null;
		Scanner s = new Scanner(inStream);

		s.useDelimiter("\\Z");

		content = s.hasNext() ? s.next() : "";
		s.close();

		return content;// == null ? null : content.replaceAll("\\s+", " ").trim();
	}

	private IProject getGeneratedProject() {
		if(genProject != null) {
			return genProject;
		}

		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(GenProjectName);
		if(project == null || !project.exists()) {
			throw new AssertionError("Generated project not found");
		}

		return genProject = project;
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
		IFolder generatedFolder = getGeneratedProject().getFolder(GenFolderName);
		assertTrue("Default generated folder \"" + GenFolderName + "\" was not generated", generatedFolder.exists());

		/* TEST-GENERATED PACKAGE FOLDER */
		IFolder generatedPackageFolder = null;
		for(int i = 0; i < depthSegments.length; i++) {
			if(i == 0) {
				generatedPackageFolder = generatedFolder.getFolder(depthSegments[i]);
			} else {
				generatedPackageFolder = generatedPackageFolder.getFolder(depthSegments[i]);
			}
			assertTrue("Package folder \"" + depthSegments[i] + "\" was not generated.", generatedPackageFolder.exists());
		}

		/* TEST-GENERATED FILE */
		IFile generatedFile = null;
		if(generatedPackageFolder != null) {
			generatedFile = generatedPackageFolder.getFile(fileName);
		} else {
			generatedFile = generatedFolder.getFile(fileName);
		}
		assertTrue("File " + fileName + " was not generated.", generatedFile.exists());
		String fileContent = getFileContents(generatedFile);


		/* PREVIOUSLY GENERATED PACKAGE FOLDER */
		IFolder expectedFolder = null;
		for(int i = 0; i < depthSegments.length; i++) {
			if(i == 0) {
				expectedFolder = modelProject.getFolder(depthSegments[i]);
			} else {
				expectedFolder = expectedFolder.getFolder(depthSegments[i]);
			}
			assertTrue("Package folder \"" + depthSegments[i] + "\" was not generated.", expectedFolder.exists());
		}

		/* PREVIOUSLY GENERATED FILE */
		IFile expectedFile = null;
		if(expectedFolder != null) {
			expectedFile = expectedFolder.getFile(fileName);
		} else {
			expectedFile = modelProject.getFile(fileName);
		}
		assertTrue("File " + fileName + " was not generated.", expectedFile.exists());
		String expectedFileContent = getFileContents(expectedFile);

		assertContentMatches(fileName, fileContent, expectedFileContent);
	}

	private static void assertContentMatches(String filename, String generated, String expected) {
		Scanner expectedScanner = new Scanner(expected);
		char[] strippedGen = generated.replaceAll("\\s+", "").trim().toCharArray();
		int genCharsTraversed = 0;
		boolean outofchars = false;

		/*
		 * line by line in expected
		 * char by char in generated
		 * compare char by char expected to generated until no more chars in line
		 * if not matching then print line expected against line generated by
		 * keeping track of the amount of chars traversed, then traverse the
		 * generated with white characters
		 */
		try {
			int lineNumber = 1;
			int lineCharBegin = 0;
			for(; !outofchars && expectedScanner.hasNextLine(); ++lineNumber) {
				lineCharBegin = genCharsTraversed;
				String eLine = expectedScanner.nextLine();
				String strippedELine = eLine.replaceAll("\\s+", "").trim();
				char[] strippedExpected = strippedELine.toCharArray();
				for(int i = 0; i < strippedExpected.length; i++) {
					if(strippedExpected[i] != strippedGen[genCharsTraversed]) {
						fail(filename + ':' + lineNumber + "expected '" + eLine.trim() + "'but found '" + rebuildStringForLineError(generated.trim(), eLine.trim(), i, genCharsTraversed, lineCharBegin) + "'");
					}
					genCharsTraversed++;
					if(genCharsTraversed == strippedGen.length) {
						outofchars = true;
					}
				}
			}
			if(expectedScanner.hasNextLine()) {
				fail(filename + ':' + lineNumber + " expected '" + expectedScanner.nextLine() + "' but found end-of-file");
			} else if(!outofchars) {
				fail(filename + ':' + lineNumber + " expected end-of-file but found '" + rebuildStringForEndOfFileError(generated.trim(), genCharsTraversed) + '\'');
			}
		} finally {
			if(expectedScanner != null) {
				expectedScanner.close();
			}
		}
	}

	private static String rebuildStringForLineError(String generatedString, String expectedLine, int beginInExpectedLine, int genCharsTraversed, int firstCharInExpLine) {
		String brokenLine = "";

		int lengthFromFirstDiff = expectedLine.replaceAll("\\s+", "").length() - beginInExpectedLine;
		char[] generatedChars = generatedString.replaceAll("\\s+", " ").toCharArray();
		int nonwhitechars = 0;
		for(int i = 0; i < generatedChars.length; i++) {
			if(generatedChars[i] != ' ') {
				nonwhitechars++;
			}
			if(nonwhitechars >= firstCharInExpLine && nonwhitechars < genCharsTraversed + lengthFromFirstDiff) {
				//start copying
				brokenLine += generatedChars[i];
			} else if(nonwhitechars == genCharsTraversed + lengthFromFirstDiff) {
				//copy number of characters for the length of the expected line
				if(generatedChars[i + 1] != '\0') {
					brokenLine += "...";
				}
				break;
			}
		}

		return brokenLine;
	}

	private static String rebuildStringForEndOfFileError(String generatedString, int genCharsTraversed) {
		String brokenLine = "";

		char[] generatedChars = generatedString.replaceAll("\\s+", " ").toCharArray();
		int nonwhitechars = 0;
		for(int i = 0; i < generatedChars.length; i++) {
			if(generatedChars[i] != ' ') {
				nonwhitechars++;
			}
			if(nonwhitechars > genCharsTraversed && generatedChars[i] != '\0') {
				//start copying
				brokenLine += generatedChars[i];
			} else if(nonwhitechars == genCharsTraversed + 15 || generatedChars[i] == '\0') {
				//copy only 15 chars
				if(generatedChars[i + 1] != '\0') {
					brokenLine += "...";
				}
				break;
			}
		}
		return brokenLine;
	}
}
