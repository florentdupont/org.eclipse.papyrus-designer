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

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.papyrus.designer.languages.common.base.ElementUtils;
import org.eclipse.papyrus.designer.languages.common.base.TestInfo;
import org.eclipse.papyrus.designer.transformation.base.utils.ProjectManagement;
import org.eclipse.papyrus.designer.transformation.core.transformations.InstantiateDepPlan;
import org.eclipse.papyrus.junit.utils.rules.AbstractHouseKeeperRule;
import org.eclipse.papyrus.junit.utils.rules.PapyrusEditorFixture;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class TransformationTestSupport {

	static {
		// This system property avoids opening dialogs during Papyrus operations. It must
		// be set before trying to load any of the Papyrus classes.
		System.setProperty(TestInfo.PAPYRUS_RUN_HEADLESS, Boolean.TRUE.toString());
	}

	/**
	 * The house keeper
	 */
	protected AbstractHouseKeeperRule houseKeeper;

	/**
	 * The modelSetFixture
	 */
	protected PapyrusEditorFixture modelSetFixture;

	/**
	 * The test class (used to get access to its bundle)
	 */
	Class<?> testClass;
	
	public TransformationTestSupport(Class<?> testClass, AbstractHouseKeeperRule houseKeeper, PapyrusEditorFixture modelSetFixture) {
		this.houseKeeper = houseKeeper;
		this.modelSetFixture = modelSetFixture;
		this.testClass = testClass;
	}

	/**
	 * Run a transformation with a given deployment plan
	 *
	 * @param depPlanName the qualified name of a deployment plan within the provided example
	 *
	 */
	public void runTransformation(String depPlanName) {

		NamedElement depPlan = ElementUtils.getQualifiedElement(modelSetFixture.getModel(), depPlanName);

		if (depPlan instanceof Package) {
			IProject project = ProjectManagement.getCurrentProject();
			Package selectedCDP = (Package) depPlan;

			final int genOptions = 0;
			new InstantiateDepPlan().instantiate(selectedCDP, new NullProgressMonitor(), project, genOptions);
		}
	}

	/**
	 * Validate the results
	 * 
	 * @param genProject the project into which generation has been done;
	 * @param folderInTestBundle The folder within the source bundle containing the expected results
	 * @param folderInModelProject The folder name containing the generated code (a folder of the same name is created within the model project to facilitate the comparison)
	 */
	public void validateResults(IProject genProject, String folderInTestBundle, String srcGen) {
		IProject modelProject = modelSetFixture.getProject().getProject();
		RecursiveCopy copier = new RecursiveCopy(houseKeeper);
		Bundle srcBundle = FrameworkUtil.getBundle(testClass);
		// copy expected results folder to model project
		copier.copy(srcBundle, folderInTestBundle, modelProject, srcGen);

		IFolder expectedSrcGen = modelProject.getFolder(srcGen);
		assertThat("expected source folder must exist", expectedSrcGen.exists());
		IFolder generatedSrcGen = genProject.getFolder(srcGen);
		assertThat("generated source folder must exist", generatedSrcGen.exists());

		FileComparison.assertGeneratedMatchesExpected(generatedSrcGen, expectedSrcGen);
	}
}
