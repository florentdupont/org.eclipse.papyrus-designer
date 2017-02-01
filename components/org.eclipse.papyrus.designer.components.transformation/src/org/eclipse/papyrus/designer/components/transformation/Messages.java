/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.transformation;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.papyrus.designer.components.transformation.messages"; //$NON-NLS-1$

	public static String ConnectorBinding_CannotFindBinding;
	public static String ConnectorBinding_CannotFindBindingForPort;
	public static String ConnectorBinding_CannotFindConsistentBinding;
	public static String ConnectorBinding_ConnectorsPort;
	public static String ConnectorBinding_FormalAlreadyBound;
	public static String ConnectorBinding_InfoActualReturnIntfIs;
	public static String ConnectorBinding_InfoConnPortConnectedVia;
	public static String ConnectorBinding_InfoConnectsPort;
	public static String ConnectorBinding_InfoCreateBoundPackage;
	public static String ConnectorBinding_InfoMatchOtherEnd;
	public static String ConnectorBinding_InfoProvidedPortTypeMatches;
	public static String ConnectorBinding_NoTemplateSignature;

	public static String ConnectorReification_CannotFindMatchingPort;
	public static String ConnectorReification_CouldNotBind;
	public static String ConnectorReification_CouldNotConnectPort;
	public static String ConnectorReification_CouldNotConnectPortOfType;
	public static String ConnectorReification_InfoAddConnectorPart;
	public static String ConnectorReification_InfoPortTypes;
	public static String ConnectorReification_RequiresUseOfPorts;
	public static String ConnectorReification_TemplateTypeNotClass;

	public static String TemplateUtils_InfoCreateBoundPackage;
	public static String TemplateUtils_InfoGetActualFrom;
	public static String TemplateUtils_NoTemplateSignature;

	public static String ContainerTrafo_CannotApplyRule;
	public static String ContainerTrafo_CannotFindDelegationConn;
	public static String ContainerTrafo_InterceptionRuleButNoInterceptor;
	public static String ContainerTrafo_RecursiveLWnotSupported;

	public static String InstanceConfigurator_InvalidPluginExtension;

	public static String CompImplSync_InfoSyncIntf;
	public static String CompImplSync_InfoSyncViaImpl;
	public static String CompImplSync_InfoSyncViaType;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
