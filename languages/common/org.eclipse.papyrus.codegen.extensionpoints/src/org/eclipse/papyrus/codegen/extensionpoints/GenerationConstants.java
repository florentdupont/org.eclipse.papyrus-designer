/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
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

package org.eclipse.papyrus.codegen.extensionpoints;

public class GenerationConstants {
	/**
	 * Constant indicating that generated code follows. Code generators should use this text in bodies, if bodies contain
	 * generated code (besides the contents of the opaque behaviors. Code generators need to prefix this string with the
	 * language specific comment symbol, e.g. // in case of C/C++ or Java.
	 */
	public static final String GENERATED_START = "the following code has been generated"; //$NON-NLS-1$
	
	/**
	 * Constant indicating that generated code ends. Code generators should use this text in bodies, if bodies contain
	 * generated code (besides the contents of the opaque behaviors. Code generators need to prefix this string with the
	 * language specific comment symbol, e.g. // in case of C/C++ or Java.
	 */
	public static final String GENERATED_END = "generated code ends"; //$NON-NLS-1$
}
