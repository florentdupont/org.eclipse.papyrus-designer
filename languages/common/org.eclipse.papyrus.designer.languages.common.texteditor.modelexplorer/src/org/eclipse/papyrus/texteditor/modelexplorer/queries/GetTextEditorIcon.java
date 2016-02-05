/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *  Ansgar Radermacher (CEA LIST) ansgar.radermacher@cea.fr - Initial API and implementation
 *  (with the help of examining table support from Vincent Lorenzo)
 *
 *****************************************************************************/
package org.eclipse.papyrus.texteditor.modelexplorer.queries;

import org.eclipse.papyrus.emf.facet.custom.metamodel.custompt.IImage;
import org.eclipse.papyrus.emf.facet.custom.ui.ImageUtils;
import org.eclipse.papyrus.emf.facet.efacet.core.IFacetManager;
import org.eclipse.papyrus.emf.facet.query.java.core.IJavaQuery2;
import org.eclipse.papyrus.emf.facet.query.java.core.IParameterValueList2;
import org.eclipse.papyrus.languages.designer.common.texteditor.model.TextEditorModel;
import org.eclipse.papyrus.views.modelexplorer.queries.AbstractGetEditorIconQuery;

/** Return the path to the icon of the corresponding table */
public class GetTextEditorIcon extends AbstractGetEditorIconQuery implements IJavaQuery2<TextEditorModel, IImage> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IImage evaluate(TextEditorModel textInstance, IParameterValueList2 parameterValues, IFacetManager manager) {
		IImage image = ImageUtils.wrap("platform:/plugin/" + getEditorRegistry(textInstance).getEditorURLIcon(textInstance)); //$NON-NLS-1$
		return image;
	}
}
