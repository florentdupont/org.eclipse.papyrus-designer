package org.eclipse.papyrus.cpp.codegen.utils;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.cpp.codegen.utils.messages"; //$NON-NLS-1$
	public static String LocateCppProject_ApplyCNatureTitle;
	public static String LocateCppProject_ApplyCNatureDesc;
	public static String LocateCppProject_CreateTargetProjectTitle;
	public static String LocateCppProject_CreateTargetProjectDesc;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
