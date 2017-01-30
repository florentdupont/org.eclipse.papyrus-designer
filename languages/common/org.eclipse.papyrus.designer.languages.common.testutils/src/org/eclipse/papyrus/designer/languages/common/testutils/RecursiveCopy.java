/*******************************************************************************
 * Copyright (c) 2017 CEA LIST
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ansgar Radermacher (CEA LIST) - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.testutils;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.papyrus.junit.utils.rules.AbstractHouseKeeperRule;
import org.osgi.framework.Bundle;

/**
 * enable a recursive copy of directories from an installed bundle to a workspace file
 */
public class RecursiveCopy {

	protected AbstractHouseKeeperRule houseKeeper;

	public RecursiveCopy(AbstractHouseKeeperRule houseKeeper) {
		this.houseKeeper = houseKeeper;
	}

	/**
	 * Make a recursive copy from a source directory to a destination directory within a workspace project. The
	 * source is assumed to be part of a bundle, not stored in the workspace
	 * 
	 * @param srcBundle
	 *            the installed source bundle
	 * @param srcPath
	 *            a path pointing to a file or folder within the source bundle
	 * @param dstProject
	 *            a destination project within the workspace
	 * @param dstPath
	 *            a path within the destination project
	 */
	public void copy(Bundle srcBundle, String srcPath, IProject dstProject, String dstPath) {

		URL fileURL = srcBundle.getEntry(srcPath);
		assertThat("source path is not contained in bundle", fileURL != null);

		try {
			File file = new File(FileLocator.resolve(fileURL).toURI());
			assertThat("source file must exist", file.exists());

			if (file.isDirectory()) {
				for (File subFile : file.listFiles()) {
					copy(srcBundle, srcPath + TestConstants.FILE_SEP + subFile.getName(), dstProject, dstPath + TestConstants.FILE_SEP + subFile.getName());
				}
			} else if (file.isFile()) {
				houseKeeper.createFile(dstProject, dstPath, srcPath);
			}
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
