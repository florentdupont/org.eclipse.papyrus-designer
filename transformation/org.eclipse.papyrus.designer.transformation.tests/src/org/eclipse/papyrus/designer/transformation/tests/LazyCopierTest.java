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

package org.eclipse.papyrus.designer.transformation.tests;

import static org.hamcrest.MatcherAssert.assertThat;

import org.eclipse.papyrus.MARTE.MARTE_DesignModel.GCM.ClientServerPort;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier.CopyExtResources;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement;
import org.eclipse.papyrus.junit.utils.rules.PapyrusEditorFixture;
import org.eclipse.papyrus.junit.utils.rules.PluginResource;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test of the lazy copier
 */
@SuppressWarnings("nls")
@PluginResource("models/simpleUML/model.di")
public class LazyCopierTest {

	@Rule
	/** The model set fixture. */
    public final PapyrusEditorFixture modelSetFixture = new PapyrusEditorFixture();

	@Test
	public void test() {
		
		Package model = modelSetFixture.getModel();
		Class clazz = (Class) ElementUtils.getQualifiedElement(model, "RootElement::Component");

		assertThat("RootElement must exist", clazz != null);
		
		Port p = clazz.getOwnedPort("p", null); //$NON-NLS-1$
		
		assertThat("Client/Server port must be applied to source model",
				StereotypeUtil.isApplied(p, ClientServerPort.class));

		assertThat("DerivedElement must be applied to source model",
				StereotypeUtil.isApplied(clazz, DerivedElement.class));

		// Model newModel = mm.getModel();
		ModelManagement mm = new ModelManagement();
		Package targetModel = (Package) mm.getModel();

		LazyCopier copier = new LazyCopier(model, targetModel, CopyExtResources.SELECTED, false);
		Class targetCl = copier.getCopy(clazz);

		assertThat("stereotype from source model is not visible in target model",
				StereotypeUtil.isApplied(targetCl, DerivedElement.class));
	}

}
