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
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.Language;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;


/**
 * Assure that language attribute (of the GeneratorHint stereotype) is not copied
 * (avoid copying the language model library)
 */
public class FilterKeepLanguage implements PreCopyListener {

	public static FilterKeepLanguage getInstance() {
		if (instance == null) {
			instance = new FilterKeepLanguage();
		}
		return instance;
	}

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {
		if (sourceEObj instanceof Language) {
			return LazyCopier.USE_SOURCE_OBJECT;
		}
		return sourceEObj;
	}

	private static FilterKeepLanguage instance = null;
}
