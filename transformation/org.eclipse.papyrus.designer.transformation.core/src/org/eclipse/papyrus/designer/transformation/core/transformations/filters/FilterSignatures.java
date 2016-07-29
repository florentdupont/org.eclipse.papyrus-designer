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

package org.eclipse.papyrus.designer.transformation.core.transformations.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.uml2.uml.TemplateSignature;


/**
 * Remove signatures from package template (only remove signatures within the
 * template, not others)
 *
 * @author ansgar
 *
 */
public class FilterSignatures implements PreCopyListener {

	public static FilterSignatures getInstance() {
		if (instance == null) {
			instance = new FilterSignatures();
		}
		return instance;
	}

	@Override
	public EObject preCopyEObject(LazyCopier copier, EObject sourceEObj) {
		if (sourceEObj instanceof TemplateSignature) {
			if (copier.withinTemplate(sourceEObj)) {
				return null;
			}
		}
		return sourceEObj;
	}

	private static FilterSignatures instance = null;
}
