package org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.messages"; //$NON-NLS-1$

	public static String DropReverseStrategy_DESCRIPTION;

	public static String DropReverseStrategy_LABEL;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
