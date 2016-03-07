/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.common.texteditor.modelexplorer.queries;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.emf.facet.efacet.core.IFacetManager;
import org.eclipse.papyrus.emf.facet.efacet.core.exception.DerivedTypedElementException;
import org.eclipse.papyrus.emf.facet.query.java.core.IJavaQuery2;
import org.eclipse.papyrus.emf.facet.query.java.core.IParameterValueList2;

/**
 * @author Camille Letavernier
 * copy of org.eclipse.papyrus.infra.gmfdiag.modelexplorer.queries.EmptyListQuery. We can not simply
 * reference the class in infra.gmfdiag, since it is not exported.
 *
 */
public class EmptyListQuery implements IJavaQuery2<EObject, List<Object>> {

	/**
	 * @see org.eclipse.papyrus.emf.facet.query.java.core.IJavaQuery2#evaluate(org.eclipse.emf.ecore.EObject, org.eclipse.papyrus.emf.facet.query.java.core.IParameterValueList2, org.eclipse.papyrus.emf.facet.efacet.core.IFacetManager)
	 *
	 * @param source
	 * @param parameterValues
	 * @param facetManager
	 * @return
	 * @throws DerivedTypedElementException
	 */
	public List<Object> evaluate(EObject source, IParameterValueList2 parameterValues, IFacetManager facetManager) throws DerivedTypedElementException {
		return Collections.emptyList();
	}

}
