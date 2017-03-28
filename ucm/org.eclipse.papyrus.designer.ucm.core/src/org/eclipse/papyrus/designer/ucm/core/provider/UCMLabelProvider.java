/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.provider;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Simple label provider for UCM
 */
public class UCMLabelProvider extends LabelProvider {
	
	public static final String UNDEFINED = "undefined"; //$NON-NLS-1$

	public static final String PREFIX = "  "; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof String) {
			return (String) element;
		}
		else if (element instanceof NamedElement) {
			return PREFIX + ((NamedElement) element).getQualifiedName();
		}
		else if (element instanceof Enumerator) {
			return ((Enumerator) element).getLiteral();
		}
		else if (element == null) {
			return UNDEFINED;
		}
		else {
			return ""; //$NON-NLS-1$
		}
	}
}
