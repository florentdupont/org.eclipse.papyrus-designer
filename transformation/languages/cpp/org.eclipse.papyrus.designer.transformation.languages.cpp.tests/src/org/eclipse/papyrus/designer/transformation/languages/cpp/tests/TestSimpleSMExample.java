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

package org.eclipse.papyrus.designer.transformation.languages.cpp.tests;

import org.eclipse.core.resources.IProject;
import org.eclipse.papyrus.designer.languages.common.testutils.TestConstants;
import org.eclipse.papyrus.designer.languages.common.testutils.TransformationTestSupport;
import org.eclipse.papyrus.junit.utils.rules.HouseKeeper;
import org.eclipse.papyrus.junit.utils.rules.PapyrusEditorFixture;
import org.eclipse.papyrus.junit.utils.rules.PluginResource;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

@PluginResource("org.eclipse.papyrus.designer.transformation.languages.cpp.library:/models/examples/SimpleSM/SimpleSM.di")
public class TestSimpleSMExample {

	@SuppressWarnings("nls")
	public static final String SIMPLE_SM_DEFAULT_NODE = "SimpleSM_defaultNode_SimpleSMDepPlan";

	@SuppressWarnings("nls")
	public static final String DEPLOYMENT_MONO_PULL = "SimpleSM::deployment::SimpleSMDepPlan";

	@Rule
	/** The model set fixture. */
	public final PapyrusEditorFixture modelSetFixture = new PapyrusEditorFixture();

	@ClassRule
	public static HouseKeeper.Static houseKeeper = new HouseKeeper.Static();

	@Test
	public void testProducerConsumerMonoPull() throws InterruptedException {

		TransformationTestSupport tts = new TransformationTestSupport(this.getClass(), houseKeeper, modelSetFixture);
		
		// create generated project. For generation purposes, it is not important that this is not a CDT project
		IProject genProject = houseKeeper.createProject(SIMPLE_SM_DEFAULT_NODE);
		
		tts.runTransformation(DEPLOYMENT_MONO_PULL);
		
		tts.validateResults(genProject, TestConstants.EXPECTED_RESULT + TestConstants.FILE_SEP +
				SIMPLE_SM_DEFAULT_NODE + TestConstants.FILE_SEP + TestConstants.SRC_GEN, TestConstants.SRC_GEN);
	}

}
