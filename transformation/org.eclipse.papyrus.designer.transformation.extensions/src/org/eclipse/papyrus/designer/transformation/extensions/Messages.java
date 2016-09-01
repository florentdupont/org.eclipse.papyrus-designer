package org.eclipse.papyrus.designer.transformation.extensions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.transformation.core.messages"; //$NON-NLS-1$

	public static String InstanceConfigurator_InvalidPluginExtension;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
