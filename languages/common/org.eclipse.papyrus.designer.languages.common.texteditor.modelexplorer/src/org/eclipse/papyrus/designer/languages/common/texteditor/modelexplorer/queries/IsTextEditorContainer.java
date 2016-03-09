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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.papyrus.emf.facet.efacet.core.IFacetManager;
import org.eclipse.papyrus.emf.facet.efacet.core.exception.DerivedTypedElementException;
import org.eclipse.papyrus.emf.facet.query.java.core.IJavaQuery2;
import org.eclipse.papyrus.emf.facet.query.java.core.IParameterValueList2;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.languages.designer.common.texteditor.model.TextEditorModel;

/** Returns true if the element contains a text editor */
public class IsTextEditorContainer implements IJavaQuery2<EObject, Boolean> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean evaluate(EObject source, IParameterValueList2 parameterValues, IFacetManager manager)
			throws DerivedTypedElementException {

		Collection<Setting> settings = EMFHelper.getUsages(source);
		if (settings != null) {
			for (Setting setting : settings) {
				EObject usingElement = setting.getEObject();
				if (usingElement instanceof TextEditorModel) {
					TextEditorModel textEditorModel = (TextEditorModel) usingElement;
					if (textEditorModel.getEditedObject() == source) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
