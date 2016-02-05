/*******************************************************************************
 * Copyright (c) 2006 - 2015 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.codegen;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.languages.cpp.codegen.messages"; //$NON-NLS-1$
	public static String C_CppLangCodegen_GeneratorDesc;
	public static String CppModelElementsCreator_CannotFormatContent;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
