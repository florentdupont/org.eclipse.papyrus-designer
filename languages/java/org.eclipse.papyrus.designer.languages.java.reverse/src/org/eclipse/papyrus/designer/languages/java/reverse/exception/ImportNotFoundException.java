/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
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

package org.eclipse.papyrus.designer.languages.java.reverse.exception;

/**
 * @author cedric dumoulin
 *
 */
public class ImportNotFoundException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 *
	 * @param name The name for which no import is found.
	 */
	public ImportNotFoundException(String name) {
		super(name);
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 *
	 * @return
	 */
	@Override
	public String getMessage() {
		return "No import found for '" + super.getMessage() + "'";
	}
}
