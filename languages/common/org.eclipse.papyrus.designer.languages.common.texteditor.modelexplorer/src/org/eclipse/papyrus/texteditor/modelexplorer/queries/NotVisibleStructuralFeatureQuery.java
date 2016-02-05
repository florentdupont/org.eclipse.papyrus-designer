/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher (CEA LIST) - Initial API and implementation
 /*****************************************************************************/
package org.eclipse.papyrus.texteditor.modelexplorer.queries;

import org.eclipse.papyrus.emf.facet.efacet.core.IFacetManager;
import org.eclipse.papyrus.emf.facet.efacet.core.exception.DerivedTypedElementException;
import org.eclipse.papyrus.emf.facet.query.java.core.IJavaQuery2;
import org.eclipse.papyrus.emf.facet.query.java.core.IParameterValueList2;
import org.eclipse.papyrus.languages.designer.common.texteditor.model.TextEditorModel;

public class NotVisibleStructuralFeatureQuery implements IJavaQuery2<TextEditorModel, Boolean> {
	public Boolean evaluate(final TextEditorModel context,
			final IParameterValueList2 parameterValues,
			final IFacetManager facetManager)
			throws DerivedTypedElementException {
		// All sub-elements are invisible. EditedObject may eventually be useful, but text editor
		// is already shown as a child of edited object within the model explorer
		return false;
	}
}
