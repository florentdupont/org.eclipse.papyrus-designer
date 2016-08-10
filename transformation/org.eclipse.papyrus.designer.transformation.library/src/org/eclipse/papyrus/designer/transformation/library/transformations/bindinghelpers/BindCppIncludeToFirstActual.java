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
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.extensions.IEmptyM2MTrafo;
import org.eclipse.papyrus.designer.transformation.core.listeners.PostCopyListener;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TextTemplateBinding;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Instantiate (bind via xtend template) the text within a C++Include stereotype.
 *
 * The actual is the first actual within the template binding. This function does not check
 * whether the classifier has the template stereotype.
 *
 * Note: this function is C++ specific, but many parts of the model library are C++ specific as well
 *
 */
public class BindCppIncludeToFirstActual implements PostCopyListener, IEmptyM2MTrafo {

	@Override
	public void postCopyEObject(LazyCopier copier, EObject targetEObj) {
		// if (copy.get(sourceEObj) isWithinTemplate)
		if (targetEObj instanceof Classifier) {

			Classifier targetCl = (Classifier) targetEObj;
			try {
				TemplateBinding binding = TransformationContext.getBinding();
				Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);
				Include cppInclude = UMLUtil.getStereotypeApplication(targetCl, Include.class);
				if ((actual != null) && (cppInclude != null)) {
					TransformationContext.classifier = targetCl;
					String newBody = TextTemplateBinding.bind(cppInclude.getBody(), actual, null);
					String newPreBody = TextTemplateBinding.bind(cppInclude.getPreBody(), actual, null);
					String newHeader = TextTemplateBinding.bind(cppInclude.getHeader(), actual, null);
					cppInclude.setBody(newBody);
					cppInclude.setPreBody(newPreBody);
					cppInclude.setHeader(newHeader);
				}
			} catch (TransformationException e) {
				// create nested exception
				throw new RuntimeException(e);
			}
		}
	}
}
