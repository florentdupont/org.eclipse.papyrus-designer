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

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.TemplateBinding;

public class InstantiationContext {

	public InstantiationContext(TemplateInstantiation ti) {
		this.ti = ti;
	}
	
	public TemplateBinding getBinding() {
		return ti.binding;
	}

	public Classifier formalParameter;

	/**
	 * Return actual (in the current binding context) from a named package template parameter
	 * Used during template instantiation: code can access actual template parameter via formal
	 *
	 * @param formal
	 *            the name of the formal parameter
	 * @return the actual
	 */
	public String pkgTemplateParameter(String formal) {
		Classifier actual = TemplateUtils.getActualFromBinding(ti.binding, formal);
		if (actual != null) {
			return actual.getQualifiedName();
		}
		else {
			return ""; //$NON-NLS-1$
		}
	}

	public TemplateInstantiation ti;
}
