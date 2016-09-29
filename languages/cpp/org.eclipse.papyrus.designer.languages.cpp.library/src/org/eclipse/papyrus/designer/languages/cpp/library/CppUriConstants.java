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

package org.eclipse.papyrus.designer.languages.cpp.library;

import org.eclipse.emf.common.util.URI;

/**
 * URI constants for libraries defined in this plug-in
 */
public class CppUriConstants {
	
	public static final String PATHMAP = "pathmap://PapyrusC_Cpp_LIBRARIES/"; //$NON-NLS-1$
	
	public static final URI PTHREAD_LIB = URI.createURI(PATHMAP + "pthread.uml"); //$NON-NLS-1$

	public static final URI ANSIC_LIB = URI.createURI(PATHMAP + "ansiCLibrary.uml"); //$NON-NLS-1$

	public static final URI OSAL_LIB = URI.createURI(PATHMAP + "osal.uml"); //$NON-NLS-1$

	public static final URI STL_LIB = URI.createURI(PATHMAP + "STL.uml"); //$NON-NLS-1$
}
