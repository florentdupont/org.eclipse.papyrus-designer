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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.emf.facet.efacet.core.IFacetManager;
import org.eclipse.papyrus.emf.facet.efacet.core.exception.DerivedTypedElementException;
import org.eclipse.papyrus.emf.facet.query.java.core.IJavaQuery2;
import org.eclipse.papyrus.emf.facet.query.java.core.IParameterValueList2;
import org.eclipse.papyrus.infra.viewpoints.policy.NotationUtils;
import org.eclipse.papyrus.languages.designer.common.texteditor.model.TextEditorModel;

/** Returns true if the element contains a Table */
public class IsTextEditorContainer implements IJavaQuery2<EObject, Boolean> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean evaluate(EObject context, IParameterValueList2 parameterValues, IFacetManager manager)
			throws DerivedTypedElementException {
		Iterator<EObject> roots = NotationUtils.getNotationRoots(context);
		if (roots == null) {
			return false;
		}

		while (roots.hasNext()) {
			EObject root = roots.next();
			if (root instanceof TextEditorModel) {
				TextEditorModel textEditorModel = (TextEditorModel) root; 
				if (textEditorModel.getEditedObject() == context) {
					return true;
				}
			}
		}
		return false;
	}
}
