/*******************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ansgar Radermacher - ansgar.radermacher@cea.fr CEA LIST - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.texteditor.cdt.sync;

/**
 * Simple grouping of modifier related parameters
 */
public class ParameterModifiers {
	public ParameterModifiers() {
		isPointer = false;
		isRef = false;
		isRegister = false;
		array = ""; //$NON-NLS-1$
	}
	
	/**
	 * true, if parameter is a pointer
	 */
	boolean isPointer;
	
	/**
	 * true, if parameter is a reference
	 */
	boolean isRef;
	
	/**
	 * true, if parameter is a register
	 */
	boolean isRegister;
	
	/**
	 * value of array modifiers (e.g. [2])
	 */
	String array ;
}
