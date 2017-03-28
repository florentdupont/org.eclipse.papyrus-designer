/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
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

package org.eclipse.papyrus.designer.ucm.ui.dialogs;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.uml2.uml.NamedElement;

/**
 * @deprecated: use common class in designer
 *
 */
@Deprecated
public class QNameLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof NamedElement) {
			return ((NamedElement) element).getQualifiedName();
		}
		else if (element == null) {
			return "undefined";
		}
		else {
			return "";
		}
	}
};
