package org.eclipse.papyrus.designer.components.transformation.java;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.components.transformation.cpp.messages"; //$NON-NLS-1$

	public static String CompImplTrafos_CannotFindType;
	public static String CompImplTrafos_IntfNotImplemented;
	public static String CompTypeTrafos_CannotFindType;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
