/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.common.extensionpoints;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.languages.common.extensionpoints.messages"; //$NON-NLS-1$
	public static String GeneratorSelectionDialog_infoCodeGen;
	public static String GeneratorSelectionDialog_SelectGenerator;
	public static String LanguageCodegen_NoGeneratorsFound;
	public static String LanguageCodegen_NoGeneratorsFoundLong;
	public static String LanguageSupport_LanguageNotSupported;
	public static String ModelElementsCreator_UnsupportedModelElement;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
