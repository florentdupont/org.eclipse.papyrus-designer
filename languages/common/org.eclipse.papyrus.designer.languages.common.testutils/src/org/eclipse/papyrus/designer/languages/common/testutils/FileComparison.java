/*******************************************************************************
 * Copyright (c) 2017 CEA LIST, Zeligsoft
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *     Based on work from Zeligsoft (in context of C++ test suite)
 *
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.testutils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.Scanner;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

@SuppressWarnings("nls")
public class FileComparison {

	/**
	 * Compare the files in folder with what we expect to see. When comparing
	 * file content, filter out whitespace which will replace all whitespace
	 * with a single space in the actual file content and the expected file
	 * content in order to avoid problems with differences caused by code
	 * formatting options where the test suite is run. Then a simple string
	 * comparison is done.
	 * 
	 * @param generatedFolder
	 *            the generated folder
	 * @param modelProject
	 *            the project containing the model
	 * @param fileName
	 *            the filename to compare
	 * @param depthSegments
	 *            hierarchical folders, if any
	 * @throws Exception
	 */
	public static void assertGeneratedMatchesExpected(IFolder generatedFolder, IProject modelProject, String fileName, String... depthSegments) throws Exception {
		assertTrue("Default generated folder \"" + generatedFolder + "\" was not generated", generatedFolder.exists());

		/* TEST-GENERATED PACKAGE FOLDER */
		IFolder generatedPackageFolder = null;
		for (int i = 0; i < depthSegments.length; i++) {
			if (i == 0) {
				generatedPackageFolder = generatedFolder.getFolder(depthSegments[i]);
			} else {
				generatedPackageFolder = generatedPackageFolder.getFolder(depthSegments[i]);
			}
			assertTrue("Package folder \"" + depthSegments[i] + "\" was not generated.", generatedPackageFolder.exists());
		}

		/* TEST-GENERATED FILE */
		IFile generatedFile = null;
		if (generatedPackageFolder != null) {
			generatedFile = generatedPackageFolder.getFile(fileName);
		} else {
			generatedFile = generatedFolder.getFile(fileName);
		}
		assertTrue("File " + fileName + " was not generated.", generatedFile.exists());
		String fileContent = getFileContents(generatedFile);


		/* PREVIOUSLY GENERATED PACKAGE FOLDER */
		IFolder expectedFolder = null;
		for (int i = 0; i < depthSegments.length; i++) {
			
			if (i == 0) {
				expectedFolder = modelProject.getFolder(depthSegments[i]);
			} else {
				expectedFolder = expectedFolder.getFolder(depthSegments[i]);
			}
			assertTrue("Package folder \"" + depthSegments[i] + "\" was not generated.", expectedFolder.exists());
		}

		/* PREVIOUSLY GENERATED FILE */
		IFile expectedFile = null;
		if (expectedFolder != null) {
			expectedFile = expectedFolder.getFile(fileName);
		} else {
			expectedFile = modelProject.getFile(fileName);
		}
		assertTrue("File " + fileName + " was not generated.", expectedFile.exists());
		String expectedFileContent = getFileContents(expectedFile);

		assertContentMatches(fileName, fileContent, expectedFileContent);
	}

	public static void assertGeneratedMatchesExpected(IFolder generatedFolder, IFolder expectedFolder) {
		try {
			for (IResource memberExp : expectedFolder.members()) {
				IResource memberGen = generatedFolder.findMember(memberExp.getName());
				assertThat("expected file does not exist in generated code", memberGen.exists());
				if (memberGen instanceof IFile) {
					assertThat("expected resource exists, but is not a file in generated code", memberGen instanceof IFile);
					String generatedFileContent = getFileContents((IFile) memberGen);
					String expectedFileContent = getFileContents((IFile) memberExp);

					assertContentMatches(memberGen.getName(), generatedFileContent, expectedFileContent);
				}
				else if (memberGen instanceof IFolder) {
					assertThat("expected resource exists, but is not a folder in generated code", memberGen instanceof IFolder);
					assertGeneratedMatchesExpected((IFolder) memberGen, (IFolder) memberExp);
				}
			}
		} catch (CoreException e) {
			fail(e.getMessage());
		}
	}
	
	public static IProject getGeneratedProject(String genProjectName) {

		IProject genProject = ResourcesPlugin.getWorkspace().getRoot().getProject(genProjectName);
		if(genProject == null || !genProject.exists()) {
			throw new AssertionError("Generated project not found");
		}

		return genProject;
	}

	
	public static String getFileContents(IFile file) throws CoreException {
		InputStream inStream = file.getContents();
		assertNotNull("Contents of file \"" + file.getName() + "\" are empty.", inStream);
		String content = null;
		Scanner s = new Scanner(inStream);

		s.useDelimiter("\\Z");

		content = s.hasNext() ? s.next() : "";
		s.close();

		return content;// == null ? null : content.replaceAll("\\s+", " ").trim();
	}

	public static void assertContentMatches(String filename, String generated, String expected) {
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
			for (; !outofchars && expectedScanner.hasNextLine(); ++lineNumber) {
				lineCharBegin = genCharsTraversed;
				String eLine = expectedScanner.nextLine();
				String strippedELine = eLine.replaceAll("\\s+", "").trim();
				char[] strippedExpected = strippedELine.toCharArray();
				for (int i = 0; i < strippedExpected.length; i++) {
					if (strippedExpected[i] != strippedGen[genCharsTraversed]) {
						fail(filename + ':' + lineNumber + "expected '" + eLine.trim() + "'but found '" + rebuildStringForLineError(generated.trim(), eLine.trim(), i, genCharsTraversed, lineCharBegin) + "'");
					}
					genCharsTraversed++;
					if (genCharsTraversed == strippedGen.length) {
						outofchars = true;
					}
				}
			}
			if (expectedScanner.hasNextLine()) {
				fail(filename + ':' + lineNumber + " expected '" + expectedScanner.nextLine() + "' but found end-of-file");
			} else if (!outofchars) {
				fail(filename + ':' + lineNumber + " expected end-of-file but found '" + rebuildStringForEndOfFileError(generated.trim(), genCharsTraversed) + '\'');
			}
		} finally {
			if (expectedScanner != null) {
				expectedScanner.close();
			}
		}
	}

	private static String rebuildStringForLineError(String generatedString, String expectedLine, int beginInExpectedLine, int genCharsTraversed, int firstCharInExpLine) {
		String brokenLine = "";

		int lengthFromFirstDiff = expectedLine.replaceAll("\\s+", "").length() - beginInExpectedLine;
		char[] generatedChars = generatedString.replaceAll("\\s+", " ").toCharArray();
		int nonwhitechars = 0;
		for (int i = 0; i < generatedChars.length; i++) {
			if (generatedChars[i] != ' ') {
				nonwhitechars++;
			}
			if (nonwhitechars >= firstCharInExpLine && nonwhitechars < genCharsTraversed + lengthFromFirstDiff) {
				// start copying
				brokenLine += generatedChars[i];
			} else if (nonwhitechars == genCharsTraversed + lengthFromFirstDiff) {
				// copy number of characters for the length of the expected line
				if (generatedChars[i + 1] != '\0') {
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
		for (int i = 0; i < generatedChars.length; i++) {
			if (generatedChars[i] != ' ') {
				nonwhitechars++;
			}
			if (nonwhitechars > genCharsTraversed && generatedChars[i] != '\0') {
				// start copying
				brokenLine += generatedChars[i];
			} else if (nonwhitechars == genCharsTraversed + 15 || generatedChars[i] == '\0') {
				// copy only 15 chars
				if (generatedChars[i + 1] != '\0') {
					brokenLine += "...";
				}
				break;
			}
		}
		return brokenLine;
	}
}
