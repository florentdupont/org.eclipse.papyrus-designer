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
package org.eclipse.papyrus.designer.transformation.library.transformations.bindinghelpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PostCopyListener;
import org.eclipse.papyrus.designer.transformation.core.templates.BindingUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateInstantiation;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.TemplateBinding;

/**
 * Bind an operation to an actual, i.e. evaluate the xtend template within the opaque behavior associated with
 * the operation.
 */
public class BindOperation implements IM2MTrafo, PostCopyListener {

	@Override
	public void postCopyEObject(LazyCopier copier, EObject targetEObj) {

		if (targetEObj instanceof Operation) {
			Operation operation = (Operation) targetEObj;
			TemplateBinding binding = TemplateInstantiation.context.getBinding();
			Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);

			BindingUtils.instantiateOperation(actual, operation);
		}
	}
}
