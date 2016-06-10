/*****************************************************************************
 * Copyright (c) 2016 Cedric Dumoulin and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Cedric Dumoulin - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.exception;

import org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop;

/**
 * An exception used to stop the execution flow in LifeCycle.
 * As example, it is used in {@link IJobAndTransactionForDrop}.
 * 
 * @author cedric dumoulin
 *
 */
public class StopExecutionException extends JavaReverseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 *
	 */
	public StopExecutionException() {
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 */
	public StopExecutionException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param cause
	 */
	public StopExecutionException(Throwable cause) {
		super(cause);
		}

	/**
	 * Constructor.
	 *
	 * @param message
	 * @param cause
	 */
	public StopExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public StopExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
