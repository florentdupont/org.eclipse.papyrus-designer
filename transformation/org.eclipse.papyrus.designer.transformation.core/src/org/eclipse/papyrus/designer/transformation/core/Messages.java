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
package org.eclipse.papyrus.designer.transformation.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.transformation.core.messages"; //$NON-NLS-1$

	public static String BootLoaderGen_CannotRetrieveTemplate;

	public static String GenerateCode_CouldNotDeleteOldCode;
	public static String GenerateCode_GeneratingCodeForNode;
	public static String GenerateCode_GeneratingCode;

	public static String TemplateUtils_InfoCreateBoundPackage;
	public static String TemplateUtils_InfoGetActualFrom;

	public static String TemplateInstantiation_TemplateIsNull;
	public static String TemplateInstantiationListener_TrafoException;

	public static String InstantiateDepPlan_ConsultConsole;
	public static String InstantiateDepPlan_ErrorsDuringTransformation;
	public static String InstantiateDepPlan_TransformationException;

	public static String UpdateUtils_CannotApplyTrafoStereotype;

	public static String TextTemplateBinding_TemplateNotFound;
	public static String TextTemplateBinding_TemplateResultIsNotAString;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
