/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.uml2.uml.Package;

/**
 * Provide the list of available policy definitions for a given component (filtering on applicability)
 *
 */
public class TechnicalPolicyContentProvider extends UCMContentProvider implements IStaticContentProvider {

	public TechnicalPolicyContentProvider(Package rootPkg, EClass metaClass) {
		super(rootPkg, metaClass);
	}

	public TechnicalPolicyContentProvider(Package rootPkg, EClass metaClass, Class<? extends EObject> stereoAppFilter) {
		super(rootPkg, metaClass, stereoAppFilter);
	}
	
	@Override
	public Object[] getElements() {
		List<Object> results = new ArrayList<Object>();
		for (Object element : super.getElements()) {
			results.add(element);
		}
		return results.toArray();
	}
}
