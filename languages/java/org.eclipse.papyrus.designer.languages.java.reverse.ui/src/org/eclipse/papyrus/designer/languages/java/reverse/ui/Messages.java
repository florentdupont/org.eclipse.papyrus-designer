package org.eclipse.papyrus.designer.languages.java.reverse.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.languages.java.reverse.ui.messages"; //$NON-NLS-1$

	public static String ReverseCodeHandler_NoModelError_Message;

	public static String ReverseCodeHandler_NoModelError_Title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
