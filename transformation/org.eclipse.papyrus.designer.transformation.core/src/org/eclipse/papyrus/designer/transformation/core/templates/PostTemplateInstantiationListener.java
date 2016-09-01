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

package org.eclipse.papyrus.designer.transformation.core.templates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PostCopyListener;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Specific tweaking of template instantiation is implemented by means of a PostCopyListener that
 * evaluates the template stereotype.
 * Chiefly used for text templates
 */
public class PostTemplateInstantiationListener implements PostCopyListener {

	public static PostTemplateInstantiationListener getInstance() {
		if (postTemplateInstantiationListener == null) {
			postTemplateInstantiationListener = new PostTemplateInstantiationListener();
		}
		return postTemplateInstantiationListener;
	}

	public void init(LazyCopier copy, TemplateBinding binding) {
		this.binding = binding;
	}

	private TemplateBinding binding;

	private static PostTemplateInstantiationListener postTemplateInstantiationListener;

	@Override
	public void postCopyEObject(LazyCopier copier, EObject targetEObj) {
		if (targetEObj instanceof Element) {

			ApplyTransformation applyTrafo =
					UMLUtil.getStereotypeApplication((Element) targetEObj, ApplyTransformation.class);
			if (applyTrafo != null) {
				for (M2MTrafo trafo : applyTrafo.getTrafo()) {
					IM2MTrafo ihelper = M2MTrafoExt.getM2MTrafo(trafo.getBase_Class().getQualifiedName());
					if (ihelper instanceof PostCopyListener) {
						((PostCopyListener) ihelper).postCopyEObject(copier, targetEObj);
					}
				}
			}
		}
	}
}
