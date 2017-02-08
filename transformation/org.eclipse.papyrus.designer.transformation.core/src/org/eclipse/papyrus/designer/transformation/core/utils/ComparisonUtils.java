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

package org.eclipse.papyrus.designer.transformation.core.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;

/**
 * Check whether two references identify the "same" element, but take into account that one of these
 * might already have been copied. 
 *
 */
public class ComparisonUtils {
	/**
	 * @param a an eObject
	 * @param b another eObject
	 * @return true, if the objects are identical or if one of these has been copied and the copy
	 * corresponds to the same object 
	 */
	public static boolean sameObject(EObject a, EObject b) {
		LazyCopier copier = (TransformationContext.current != null) ? TransformationContext.current.copier : null;
		if (copier != null) {
			return (a == b) || (copier.get(a) == b) || (copier.get(b) == a);
		}
		else {
			return a == b;
		}
	}
}
