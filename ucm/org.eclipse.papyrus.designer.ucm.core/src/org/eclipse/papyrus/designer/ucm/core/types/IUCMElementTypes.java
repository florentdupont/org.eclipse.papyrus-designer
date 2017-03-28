/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   Ansgar Radermacher
 *   
 *****************************************************************************/
package org.eclipse.papyrus.designer.ucm.core.types;

/**
 * Declaration of all constants for UCM Element Types.
 */
public interface IUCMElementTypes {

	public static final String PREFIX = "org.eclipse.papyrus.designer.ucm.core."; //$NON-NLS-1$

	public static final String COMPONENT_TYPE_ID = PREFIX + "ComponentType"; //$NON-NLS-1$

	public static final String ATOMIC_COMPONENT_IMPLEMENTATON_ID = PREFIX + "AtomicComponentImplementation";//$NON-NLS-1$

	public static final String COMPOSITE_COMPONENT_IMPLEMENTATON_ID = PREFIX + "CompositeComponentImplementation";//$NON-NLS-1$

	public static final String INTERACTION_PATTERN_ID = PREFIX + "InteractionPattern"; //$NON-NLS-1$

	public static final String CONNECTOR_DEFINITION_ID = PREFIX + "ConnectorDefinition"; //$NON-NLS-1$

	public static final String APPLICATION_MODULE_ID = PREFIX + "ApplicationModule"; //$NON-NLS-1$

	public static final String COMPONENT_MODULE_ID = PREFIX + "ComponentModule"; //$NON-NLS-1$

	public static final String CONTRACT_MODULE_ID = PREFIX + "ContractModule"; //$NON-NLS-1$

	public static final String PLATFORM_MODULE_ID = PREFIX + "PlatformModule"; //$NON-NLS-1$

	public static final String INTERACTION_DEFINITION_MODULE_ID = PREFIX + "InteractionDefinitionModule"; //$NON-NLS-1$

	public static final String ENVIRONMENT_MODULE_ID = PREFIX + "EnvironmentModule"; //$NON-NLS-1$

	public static final String DEPLOYMENT_MODULE_ID = PREFIX + "DeploymentModule"; //$NON-NLS-1$

	public static final String RESOURCE_DEFINITION_MODULE_ID = PREFIX + "ResourceDefinitionModule"; //$NON-NLS-1$

	public static final String NON_FUNCTIONAL_ASPECTS_MODULE_ID = PREFIX + "NonFunctionalAspectsModule"; //$NON-NLS-1$

	public static final String TEST_CASE_GROUP_ID = PREFIX + "TestCaseGroup"; //$NON-NLS-1$

	public static final String ALLOCATION_PLAN_ID = PREFIX + "AllocationPlan"; //$NON-NLS-1$

	public static final String PORT_ID = PREFIX + "Port"; //$NON-NLS-1$
	
	public static final String PORT_TYPE_ID = PREFIX + "PortType"; //$NON-NLS-1$

}
