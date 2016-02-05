/*****************************************************************************
 * Copyright (c) 2014 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.common.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;

/**
 * A simple class providing access to the file system, if the project is known
 *
 */
public class JavaIoFileSystemAccess implements IPFileSystemAccess {

	File baseFolder;

	String subFolderName;

	boolean force = true;


	/**
	 * @see org.eclipse.IPFileSystemAccess.generator.IFileSystemAccess#generateFile(java.lang.String, java.lang.String)
	 *
	 * @param fileName The filename
	 * @param content The content that is written to a file
	 */
	public void generateFile(String fileName, String content) {
		File file = getFile(fileName);
		try {
			if (!file.exists()) {
				// the file does not exists
				file.createNewFile();
			}
			BufferedWriter writer = Files.newBufferedWriter(file.toPath(), Charset.defaultCharset());
			writer.write(content);
		}
		catch (IOException e) {
			throw new RuntimeException("Code generation: " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.IPFileSystemAccess.generator.IFileSystemAccess#deleteFile(java.lang.String)
	 *
	 * @param fileName
	 */
	public void deleteFile(String fileName) {
		File file = getFile(fileName);
		file.delete();
	}

	public void setOutputPath(File baseFolder) {
		this.baseFolder = baseFolder;
	}

	public void setOutputPath(File baseFolder, String folderName) {
		this.baseFolder = baseFolder;
		this.subFolderName = folderName;
	}

	/**
	 * Return a container (folder) for a given named element. The folder is embedded into a set
	 * of folders that correspond to the namespaces of the element. These folders will be
	 * created, if the do not exist (comparable to "mkdir -p" in Unix).
	 *
	 * @param element
	 *            a named element
	 * @return folder for this element
	 */
	public File getContainer(NamedElement element) {
		try {
			File folder = getFile(baseFolder, subFolderName);
			EList<Namespace> namespaces = element.allNamespaces();
			for (int i = namespaces.size() - 1; i >= 0; i--) {
				Namespace ns = namespaces.get(i);
				folder = getFile(folder, ns.getName());
				if (!folder.exists()) {
					// if packageContainer is a Project, it necessarily exists
					folder.createNewFile();
				}
			}
			return folder;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return a container (folder) for a given filename.
	 * Folders will be created, if the do not exist (comparable to "mkdir -p" in Unix).
	 *
	 * @param filename
	 *            a filename with the '/' as separation character
	 * @return file for this element
	 */
	public File getFile(String filename) {
		String paths[] = filename.split("/"); //$NON-NLS-1$
		File folder = getFile(baseFolder, subFolderName);
		try {
			for (int i = 0; i < paths.length - 1; i++) {
				String path = paths[i];
				folder = getFile(folder, path);
				if (!folder.exists()) {
					// if packageContainer is a Project, it necessarily exists
					folder.createNewFile();
				}
			}
			String last = paths[paths.length - 1];
			return getFile(folder, last);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public File getFile(File folder, String fileName) {
		return new File(folder, fileName);
	}
}
