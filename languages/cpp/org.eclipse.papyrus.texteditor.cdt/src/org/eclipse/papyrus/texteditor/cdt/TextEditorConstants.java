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

package org.eclipse.papyrus.texteditor.cdt;

import java.util.regex.Pattern;

public class TextEditorConstants {

	/**
	 * Regular expression for accepted language for compatible CDT code generators
	 */
	public static final Pattern CPP = Pattern.compile("C\\+\\+|c\\+\\+|CPP|cpp|C|c"); //$NON-NLS-1$
}
