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
import org.eclipse.papyrus.designer.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Template instantiation is implemented by means of an CopyListener that
 * evaluates the template stereotype.
 */

public class PreTemplateInstantiationListener implements PreCopyListener {

	public static PreTemplateInstantiationListener getInstance() {
		if (preTemplateInstantiationListener == null) {
			preTemplateInstantiationListener = new PreTemplateInstantiationListener();
			preTemplateInstantiationListener.treatTemplate = false;
		}
		return preTemplateInstantiationListener;
	}

	public void init(LazyCopier copy, TemplateBinding binding) {
		this.binding = binding;
		preTemplateInstantiationListener.treatTemplate = false;
	}

	private TemplateBinding binding;

	private boolean treatTemplate;

	private static PreTemplateInstantiationListener preTemplateInstantiationListener;

	@Override
	public EObject preCopyEObject(LazyCopier copier, EObject sourceEObj) {
		if (treatTemplate) {
			return sourceEObj;
		}
		treatTemplate = true;
		EObject targetEObj = checkEObject(copier, sourceEObj);
		treatTemplate = false;
		return targetEObj;
	}

	protected EObject checkEObject(LazyCopier copier, EObject sourceEObj) {

		if (sourceEObj instanceof Element) {
			ApplyTransformation applyTrafo =
					UMLUtil.getStereotypeApplication((Element) sourceEObj, ApplyTransformation.class);
			if (applyTrafo != null) {
				for (M2MTrafo trafo : applyTrafo.getTrafo()) {
					IM2MTrafo ihelper = M2MTrafoExt.getM2MTrafo(trafo.getBase_Class().getQualifiedName());
					if (ihelper instanceof PreCopyListener) {
						return ((PreCopyListener) ihelper).preCopyEObject(copier, sourceEObj);
					}
				}
			}
		}
		return sourceEObj;
	}
}
