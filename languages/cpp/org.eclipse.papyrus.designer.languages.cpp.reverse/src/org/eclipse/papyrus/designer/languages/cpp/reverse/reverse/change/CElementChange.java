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

package org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change;

import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICElementDelta;
import org.eclipse.core.runtime.IPath;

/**
 * Represents a raw change in a CDT project
 *
 */
public class CElementChange {
	protected ICElementDelta delta; // See ICElementDelta for flags and delta kind integers
	protected ICElement element; // The current ADDED/DELETED/CHANGED element
	protected ICElement fromElement; // If not null, the current element was ADDED. It was "fromElement" before it was moved and became "element"
	protected ICElement toElement; // If not null, the current element was DELETED. It became "toElement". Before it was "element".
	protected ICElement parent; // The parent of the current element (e.g. class for an attribute, package for a class)
	protected int changeKind; // The change kind that corresponds to ICElementDelta#getKind
	protected IPath filePath; // The path of the file that contains the element
	
	public CElementChange() {
		
	}
	
	public CElementChange(ICElementDelta delta) {
		this.delta = delta;
		
		if (this.delta != null) {
			element = delta.getElement();
			fromElement = delta.getMovedFromElement();
			toElement = delta.getMovedToElement();
			if (element != null) {
				parent = element.getParent();
				filePath = delta.getElement().getPath();
			}
			changeKind = delta.getKind();
		}
	}

	public ICElementDelta getDelta() {
		return delta;
	}

	public void setDelta(ICElementDelta delta) {
		this.delta = delta;
	}

	public ICElement getElement() {
		return element;
	}

	public void setElement(ICElement element) {
		this.element = element;
	}

	public ICElement getFromElement() {
		return fromElement;
	}

	public void setFromElement(ICElement fromElement) {
		this.fromElement = fromElement;
	}

	public ICElement getToElement() {
		return toElement;
	}

	public void setToElement(ICElement toElement) {
		this.toElement = toElement;
	}

	public ICElement getParent() {
		return parent;
	}

	public void setParent(ICElement parent) {
		this.parent = parent;
	}

	public int getChangeKind() {
		return changeKind;
	}

	public void setChangeKind(int changeKind) {
		this.changeKind = changeKind;
	}

	public IPath getFilePath() {
		return filePath;
	}

	public void setFilePath(IPath filePath) {
		this.filePath = filePath;
	}
}
