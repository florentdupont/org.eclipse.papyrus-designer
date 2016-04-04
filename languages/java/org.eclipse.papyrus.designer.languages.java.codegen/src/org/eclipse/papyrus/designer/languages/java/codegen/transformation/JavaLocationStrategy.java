/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen.transformation;

import org.eclipse.papyrus.designer.languages.common.base.HierarchyLocationStrategy;
import org.eclipse.uml2.uml.NamedElement;

public class JavaLocationStrategy extends HierarchyLocationStrategy {
	/**
	 * Return the filename for a given named element.
	 *
	 * @param element
	 *            a named element
	 * @return filename for this element
	 */
	public String getFileName(NamedElement element) {
		return super.getFileName(element);
	}
}
