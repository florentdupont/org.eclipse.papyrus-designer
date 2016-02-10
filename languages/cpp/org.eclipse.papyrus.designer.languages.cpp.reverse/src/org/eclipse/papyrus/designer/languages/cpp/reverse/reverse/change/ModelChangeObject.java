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

import org.eclipse.emf.ecore.EObject;

/**
 * Represents a change in a UML model
 *
 */
public class ModelChangeObject {
	/**
	 * The eventType. @link org.eclipse.emf.common.notify.Notification
	 */
	public int eventType;
	
	/**
	 * The associated EObject
	 */
	public EObject eObject;
	
	public EObject parent;
	
	public ModelChangeObject(EObject parent, EObject eObject, int event) {
		this.parent = parent;
		this.eObject = eObject;
		this.eventType = event;
	}
}
