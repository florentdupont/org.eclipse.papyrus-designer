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
 *  Francois Le Fevre francois.le-fevre@cea.fr  - Initial API and implementation
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.css;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.papyrus.infra.gmfdiag.css.helper.CSSDOMSemanticElementHelper;
import org.eclipse.papyrus.uml.diagram.common.stereotype.display.helper.StereotypeDisplayUtil;

/**
 * This class is a helper for retrieving view and semantic element from a compatible object related to UML.
 */
public class CSSDOMUCMSemanticElementHelper extends CSSDOMSemanticElementHelper {


	/**
	 * singleton instance
	 */
	private static CSSDOMUCMSemanticElementHelper elementHelper;

	/** Private Constructor. */
	protected CSSDOMUCMSemanticElementHelper() {
		super();
	}

	/**
	 * Returns the singleton instance of this class
	 *
	 * @return the singleton instance.
	 */
	public static CSSDOMUCMSemanticElementHelper getInstance() {
		if (elementHelper == null) {
			elementHelper = new CSSDOMUCMSemanticElementHelper();
		}
		return elementHelper;
	}



	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.css.helper.CSSDOMSemanticElementHelper#findSemanticElement(org.eclipse.emf.ecore.EObject)
	 *
	 * @param notationElement
	 * @return
	 */
	@Override
	public EObject findSemanticElement(EObject notationElement) {
		StereotypeDisplayUtil stereotypeHelper = StereotypeDisplayUtil.getInstance();
		// Add Stereotype Comment
		if (notationElement instanceof Shape && stereotypeHelper.isStereotypeComment(notationElement)) {
			return notationElement;
		}

		// Add Stereotype Label
		if (notationElement instanceof DecorationNode && stereotypeHelper.isStereotypeLabel(notationElement)) {
			return notationElement;
		}

		// Add StereotypeProperty to the DOM model
		if (notationElement instanceof DecorationNode && (stereotypeHelper.isStereotypeProperty(notationElement) || stereotypeHelper.isStereotypeBraceProperty(notationElement))) {
			return notationElement;
		}
		return super.findSemanticElement(notationElement);
	}
}
