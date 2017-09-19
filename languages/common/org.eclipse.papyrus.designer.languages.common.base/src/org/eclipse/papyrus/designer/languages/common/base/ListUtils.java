/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and others.
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

package org.eclipse.papyrus.designer.languages.common.base;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * List utility functions
 */
public class ListUtils {
	/**
	 * Reverse the order of elements within a list
	 * @param list the input list
	 * @return a list with elements in reverse order
	 */
	public static <T extends EObject> EList<T> reverse(EList<T> list) {
		EList<T> reverseList = new BasicEList<T>();
		for (int i = list.size() - 1; i >= 0; i--) {
			reverseList.add(list.get(i));
		}
		return reverseList;
	}
}
