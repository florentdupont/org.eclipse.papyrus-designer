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
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Edit Helper Advice for realization link between an technical policy definition and an advice
 */
public class AffectPolicyDefEditHelperAdvice extends AbstractEditHelperAdvice {

	@Override
	public boolean approveRequest(IEditCommandRequest request) {
		if (request instanceof GetEditContextRequest) {
			GetEditContextRequest context = (GetEditContextRequest) request;
			if (context.getEditCommandRequest() instanceof CreateRelationshipRequest) {
				return approveCreateRelationshipRequest((CreateRelationshipRequest) context.getEditCommandRequest());
			}
		}
		return super.approveRequest(request);
	}

	/**
	 * Check that the source of the assignment is a technical policy
	 * @param request
	 * @return
	 */
	protected boolean approveCreateRelationshipRequest(CreateRelationshipRequest request) {
		
		source = request.getSource();
		target = request.getTarget();
		if ((source instanceof Element) && (target instanceof Element)) {
			if (UMLUtil.getStereotypeApplication((Element) source, TechnicalPolicyDefinition.class) != null && 
				UMLUtil.getStereotypeApplication((Element) target, TechnicalAspect.class) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getBeforeConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest)
	 */
	@Override
	protected ICommand getBeforeConfigureCommand(ConfigureRequest request) {
		return super.getBeforeConfigureCommand(request);
	}


	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getAfterConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest)
	 */
	@Override
	protected ICommand getAfterConfigureCommand(ConfigureRequest request) {
		return super.getAfterConfigureCommand(request);
	}
	
	protected EObject source;
	protected EObject target;
}
