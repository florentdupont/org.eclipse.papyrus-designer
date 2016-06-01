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
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.components.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier;


/**
 * Filter the PortKind stereotype application, since it can be an extended port within a package template.
 * The rule checks whether the package template is present.
 * The objective is to avoid copying a package template into the target model.
 */
public class FilterPortKind implements PreCopyListener {

	public static FilterPortKind getInstance() {
		if (instance == null) {
			instance = new FilterPortKind();
		}
		return instance;
	}

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {
		if (sourceEObj instanceof PortKind) {
			PortKind portKind = (PortKind) sourceEObj;
			org.eclipse.uml2.uml.Class umlPortKind = portKind.getBase_Class();
			if ((umlPortKind != null) && TemplateUtils.getSignature(umlPortKind) != null) {
				return LazyCopier.useSourceEObject;
			}
		}
		return sourceEObj;
	}

	private static FilterPortKind instance = null;
}
