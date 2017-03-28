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
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Variant of UCM content provider that adds an explicit "undefined" field
 */
public class UCMContentProviderWithUndef extends UCMContentProvider implements IStaticContentProvider {

	public UCMContentProviderWithUndef(Package rootPkg, EClass metaClass) {
		super(rootPkg, metaClass);
	}

	public UCMContentProviderWithUndef(Package rootPkg, EClass metaClass, Class<? extends EObject> stereoAppFilter) {
		super(rootPkg, metaClass, stereoAppFilter);
	}
	
	@Override
	public Object[] getElements() {
		List<Object> results = new ArrayList<Object>();
		results.add(getUndef());
		for (Object element : super.getElements()) {
			results.add(element);
		}
		return results.toArray();
	}
	
	public static Type getUndef() {
		if (undef == null) {
			undef = UMLFactory.eINSTANCE.createClass();
			undef.setName("<undefined>");
		}
		return undef;
	}
	
	static Type undef = null;
}
