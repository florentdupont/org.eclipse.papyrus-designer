/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.types.advice;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.papyrus.designer.ucm.core.types.ElementTypeUtils;
import org.eclipse.papyrus.designer.ucm.core.types.IUCMElementTypes;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.ApplicationModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_commons.PlatformModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.InteractionDefinitionModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.NonfunctionalAspectModule;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Package;


/**
 * Advice for all modules kinds in UCM
 */
public class ModuleEditHelperAdvice extends AbstractEditHelperAdvice {

	@Override
	public boolean approveRequest(IEditCommandRequest request) {
		if (request instanceof GetEditContextRequest) {
			GetEditContextRequest context = (GetEditContextRequest) request;
			if (context.getEditCommandRequest() instanceof CreateElementRequest) {
				return approveCreateElementRequest((CreateElementRequest) context.getEditCommandRequest());
			}
		}
		return super.approveRequest(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configureRequest(IEditCommandRequest request) {
		super.configureRequest(request);
	}

	/**
	 * Check the creation condition for the different UCM module kinds
	 *
	 * @param request the create request
	 * @return true, if the element can be created
	 */
	protected boolean approveCreateElementRequest(CreateElementRequest request) {
		IElementType type = request.getElementType();
		if (type != null) {
			EObject containerEObj = request.getContainer();
			Package container = containerEObj instanceof Package ? (Package) containerEObj : null;
			if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.APPLICATION_MODULE_ID))) {
				// check application module conditions:
				// - can be owned by another application module or root
				if (container == null || StereotypeUtil.isApplied(container, ApplicationModule.class)) {
					return true;
				}
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.COMPONENT_MODULE_ID))) {
				// check component module conditions
				// - can be owned by an application module or another component module
				if (container == null ||
						StereotypeUtil.isApplied(container, ApplicationModule.class) || StereotypeUtil.isApplied(container, ComponentModule.class)) {
					return true;
				}		
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.CONTRACT_MODULE_ID))) {
				// check component module conditions
				// - can be owned by an application module, a component, a platform, a policy, or another contract module
				if (container == null ||
						StereotypeUtil.isApplied(container, ApplicationModule.class) ||
						StereotypeUtil.isApplied(container, ComponentModule.class) ||
						StereotypeUtil.isApplied(container, PlatformModule.class) ||
						StereotypeUtil.isApplied(container, NonfunctionalAspectModule.class) || 
						StereotypeUtil.isApplied(container, ContractModule.class)) {
					return true;
				}		
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.PLATFORM_MODULE_ID))) {
				// check platform module conditions:
				// - can be owned by root
				if (container == null) {
					return true;
				}
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.NON_FUNCTIONAL_ASPECTS_MODULE_ID))) {
				// check non functional aspects module conditions:
				if (container == null ||
					StereotypeUtil.isApplied(container, NonfunctionalAspectModule.class) ||
					StereotypeUtil.isApplied(container, PlatformModule.class)) {
					return true;
				}
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.INTERACTION_DEFINITION_MODULE_ID))) {
				// check interaction module conditions:
				// - can be owned by platform or interaction module
				if (container == null ||
					StereotypeUtil.isApplied(container, PlatformModule.class) ||
					StereotypeUtil.isApplied(container, InteractionDefinitionModule.class)) {
					return true;
				}
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.ENVIRONMENT_MODULE_ID)) ||
					ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.DEPLOYMENT_MODULE_ID))) {
				// check test case group, environment module & deployment module
				// - can be owned by application or in independent model
				if (container == null || StereotypeUtil.isApplied(container, ApplicationModule.class)) {
					return true;
				}
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.RESOURCE_DEFINITION_MODULE_ID))) {
				// check interaction module conditions:
				// - can be owned by platform or interaction module
				if (container == null || StereotypeUtil.isApplied(container, PlatformModule.class)) {
					return true;
				}
			}
			else if (ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.TEST_CASE_GROUP_ID)) ||
					ElementTypeUtils.isTypeCompatible(type, ElementTypeRegistry.getInstance().getType(IUCMElementTypes.ALLOCATION_PLAN_ID))) {
				// check interaction module conditions:
				// - can be owned by platform or interaction module
				if (container == null ||
						StereotypeUtil.isApplied(container, ApplicationModule.class) ||
						StereotypeUtil.isApplied(container, DeploymentModule.class)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getBeforeConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest)
	 *
	 * @param request
	 * @return
	 */
	@Override
	protected ICommand getBeforeConfigureCommand(ConfigureRequest request) {
		return super.getBeforeConfigureCommand(request);
	}
}
