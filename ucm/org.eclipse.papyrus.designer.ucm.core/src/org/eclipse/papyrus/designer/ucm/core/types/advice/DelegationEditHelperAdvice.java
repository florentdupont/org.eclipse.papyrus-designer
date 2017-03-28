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
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AssemblyPart;
import org.eclipse.papyrus.uml.service.types.utils.RequestParameterUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Edit Helper Advice for {@link Delegation}
 */
public class DelegationEditHelperAdvice extends AbstractEditHelperAdvice {

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
	 * 
	 * @param request
	 * @return
	 */
	protected boolean approveCreateRelationshipRequest(CreateRelationshipRequest request) {

		EObject source = request.getSource();
		EObject target = request.getTarget();

		if ((source instanceof Port) && (target instanceof Port)) {
			// in case of ports attached to parts, the UML model does not allow to
			// retrieve the associated part. It is therefore necessary to use the associated views
			View sourceView = RequestParameterUtils.getSourceView(request);
			View targetView = RequestParameterUtils.getTargetView(request);
			Element sourcePart = null;
			Element targetPart = null;
			if (sourceView.eContainer() instanceof View) {
				// container of port attached to part = view of part
				View sourceContainer = (View) sourceView.eContainer();
				sourcePart = (Element) sourceContainer.getElement();
			}
			if (targetView.eContainer() instanceof View) {
				View targetContainer = (View) targetView.eContainer();
				targetPart = (Element) targetContainer.getElement();
			}

			// delegation connector must be from a port of a class to an assembly part
			if (sourcePart instanceof Property && targetPart instanceof Class) {
				boolean sourceIsAssemblyPart = UMLUtil.getStereotypeApplication(sourcePart, AssemblyPart.class) != null;
				return sourceIsAssemblyPart;
			}
			if (targetPart instanceof Property && sourcePart instanceof Class) {
				boolean targetIsAssemblyPart = UMLUtil.getStereotypeApplication(targetPart, AssemblyPart.class) != null;
				return targetIsAssemblyPart;
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
}
