/*****************************************************************************
 * Copyright (c) 2013, 2017 CEA LIST.
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

package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.transformation.base.Activator;
import org.eclipse.papyrus.designer.transformation.base.preferences.PapyrusDesignerPreferenceConstants;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;

/**
 * Utility function that returns the parts of a components.
 */
public class PartsUtil {

	public static boolean treatNoneAsComposite() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getBoolean(PapyrusDesignerPreferenceConstants.P_TREAT_NONE_AS_COMPOSITE);
	}

	/**
	 * Return the parts of a composite class. In contrast to the standard UML2 function, it will also
	 * return attributes with aggregation kind NONE (i.e. all != SHARED), depending on a user preference.
	 * 
	 * @param implementation
	 *            A composite class
	 * @return the contained parts
	 */
	public static EList<Property> getParts(Class implementation) {
		if (treatNoneAsComposite()) {
			EList<Property> parts = new BasicEList<Property>();
			for (Property part : implementation.getAttributes()) {
				if (part.getAggregation() != AggregationKind.SHARED_LITERAL) {
					parts.add(part);
				}
			}
			return parts;
		} else {
			return implementation.getParts();
		}
	}

}
