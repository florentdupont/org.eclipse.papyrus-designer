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
 *  Ansgar Radermacher (CEA LIST) ansgar.radermacher@cea.fr - Initial API and implementation
 *  (with the help of examining table support from Vincent Lorenzo)
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.common.texteditor.modelexplorer.queries;

import org.eclipse.papyrus.emf.facet.efacet.core.IFacetManager;
import org.eclipse.papyrus.emf.facet.efacet.core.exception.DerivedTypedElementException;
import org.eclipse.papyrus.emf.facet.query.java.core.IJavaQuery2;
import org.eclipse.papyrus.emf.facet.query.java.core.IParameterValueList2;
import org.eclipse.papyrus.languages.designer.common.texteditor.model.TextEditorModel;

/** Return the name for the table */
public class GetTextEditorName implements IJavaQuery2<TextEditorModel, String> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String evaluate(TextEditorModel context, IParameterValueList2 parameterValues, IFacetManager manager)
			throws DerivedTypedElementException {
		return context.getName();
	}
}
