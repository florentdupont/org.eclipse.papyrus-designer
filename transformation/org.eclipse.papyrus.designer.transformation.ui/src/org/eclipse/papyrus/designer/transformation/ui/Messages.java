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
package org.eclipse.papyrus.designer.transformation.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.transformation.ui.messages"; //$NON-NLS-1$

	public static String ConfigurePortHandler_ConfigurePorts;
	public static String ConfigurePortHandler_ConfigurePortsOfComponent;
	public static String ConfigurePortHandler_ConfigureInstance;

	public static String ConfigurePortDialog_AvailPorts;
	public static String ConfigurePortDialog_PortConfig;
	public static String ConfigurePortDialog_NotAvail;
	public static String ConfigurePortDialog_Kind;
	public static String ConfigurePortDialog_None;
	public static String ConfigurePortDialog_Type;
	public static String ConfigurePortDialog_ChangeType;
	public static String ConfigurePortDialog_IntfDerived;
	public static String ConfigurePortDialog_Provided;
	public static String ConfigurePortDialog_Required;
	public static String ConfigurePortDialog_Undef;

	public static String CreateDepPlanHandler_CreateDPs;
	public static String CreateDepPlanHandler_Sync;
	public static String CreateDepPlanHandler_CreateNew;
	public static String CreateDepPlanHandler_WhatShouldIDo;
	public static String CreateDepPlanHandler_DPwithNameExistsAlready;
	public static String CreateDepPlanHandler_DPwithNameExistsNoPackage;
	public static String CreateDepPlanHandler_CannotSync;
	public static String CreateDepPlanHandler_Cancel;
	public static String CreateDepPlanHandler_CreateDP;
	public static String CreateDepPlanHandler_CannotCreateDP;
	public static String CreateDepPlanHandler_StereoApplicationFailed;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
