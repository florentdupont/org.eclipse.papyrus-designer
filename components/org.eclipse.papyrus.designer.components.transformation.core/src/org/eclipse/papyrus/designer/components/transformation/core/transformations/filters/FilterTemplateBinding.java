/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.components.transformation.core.transformations.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.components.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.TemplateBinding;


/**
 * Remove template bindings to packages from model. The reason is to avoid that the template binding will drag the
 * package template with it.
 * (However, template binding on non-class level)
 *
 */
public class FilterTemplateBinding implements PreCopyListener {

	public static FilterTemplateBinding getInstance() {
		if (instance == null) {
			instance = new FilterTemplateBinding();
		}
		return instance;
	}

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {
		if (sourceEObj instanceof TemplateBinding) {
			TemplateBinding binding = (TemplateBinding) sourceEObj;
			for (Element target : binding.getTargets()) {
				if (target != null) {
					if (!(target.eContainer() instanceof Package)) {
						return sourceEObj;
					}
				}
			}
			return null;
		}
		return sourceEObj;
		
	}

	private static FilterTemplateBinding instance = null;
}
