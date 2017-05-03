/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.deployment.tools;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.transformation.core.messages"; //$NON-NLS-1$

	public static String DepCreation_SingletonExistsAlready;
	public static String DepCreation_CannotFindImplementation;
	public static String DepCreation_CircularReference;
	public static String DepCreation_DefiningFeatureNull;
	public static String DepCreation_FCMconnectorWithoutBaseClass;
	public static String DepCreation_InfoCreateDepPlan;
	public static String DepCreation_TypeInAttributeUndefined;

	public static String DepPlanUtils_CannotFindAttribute;
	public static String DepPlanUtils_ConfigOfPropertyFailed;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
