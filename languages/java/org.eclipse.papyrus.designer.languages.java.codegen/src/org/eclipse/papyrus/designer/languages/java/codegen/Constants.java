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

package org.eclipse.papyrus.designer.languages.java.codegen;

import java.util.regex.Pattern;

/**
 * String constants for the code generation
 */
public class Constants {
	// support the languages "Java", "java", "JAVA"
	public static final Pattern supportedLanguages = Pattern.compile("Java|java|JAVA"); //$NON-NLS-1$
	
	public static final String DOT = "."; //$NON-NLS-1$
}
