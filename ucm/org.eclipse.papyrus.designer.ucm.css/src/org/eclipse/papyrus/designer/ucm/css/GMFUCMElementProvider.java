/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher (based on code from Francois Le Fevre)
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.css;

import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.css.engine.ExtendedCSSEngine;
import org.eclipse.papyrus.infra.gmfdiag.css.provider.IPapyrusElementProvider;
import org.w3c.dom.Element;

/**
 * An IElementProvider for UCM-specific CSS concepts
 *
 * Provides a specialization of GMFElementAdapter for UCM elements
 */
public class GMFUCMElementProvider implements IPapyrusElementProvider {

	@Override
	public Element getElement(Object element, CSSEngine engine) {

		if (!(element instanceof View)) {
			throw new IllegalArgumentException("Unknown element: " + element);//$NON-NLS-1$
		}

		if (!(engine instanceof ExtendedCSSEngine)) {
			throw new IllegalArgumentException("Invalid CSS Engine: " + engine);//$NON-NLS-1$
		}

		return new GMFUCMElementAdapter((View) element, (ExtendedCSSEngine) engine);
	}

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.css.provider.IPapyrusElementProvider#getPrimaryView(org.eclipse.emf.ecore.EObject)
	 *
	 * @param notationElement
	 * @return
	 */
	@Override
	public View getPrimaryView(EObject notationElement) {
		return CSSDOMUCMSemanticElementHelper.getInstance().findPrimaryView(notationElement);
	}
}
