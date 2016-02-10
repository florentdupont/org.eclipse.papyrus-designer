/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Shuai Li (CEA LIST) <shuai.li@cea.fr> - Initial API and implementation
 *   Van Cam Pham (CEA LIST) <vancam.pham@cea.fr> - Reverse implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change

import org.eclipse.cdt.core.model.ICElement
import org.eclipse.cdt.core.model.ITranslationUnit

/**
 * Represents a processed change in a CDT project
 *
 */
class CppChangeObject {
	public ICElement oldElement
	public ICElement newElement
	public ITranslationUnit itu
	public ICElement parent
	public int changeKind
	new (ICElement oldElement, ICElement newElement, ITranslationUnit itu, ICElement parent, int changeKind) {
		this.oldElement = oldElement
		this.newElement = newElement
		this.itu = itu
		this.parent = parent
		this.changeKind = changeKind
	}
	new () {
		
	}
}