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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.papyrus.designer.ucm.core.menu.EnhancedPopupMenu;
import org.eclipse.papyrus.designer.ucm.core.menu.MenuHelper;
import org.eclipse.papyrus.designer.ucm.core.provider.PortContentProvider;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.TechnicalPolicy;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyApplicability;
import org.eclipse.papyrus.infra.emf.gmf.command.EMFtoGMFCommandWrapper;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Edit Helper Advice for {@link Port}
 */
public class AffectPolicyEditHelperAdvice extends AbstractEditHelperAdvice {

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
			if (UMLUtil.getStereotypeApplication((Element) source, AtomicComponentImplementation.class) != null && 
				UMLUtil.getStereotypeApplication((Element) target, TechnicalPolicy.class) != null) {
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
		// select implied ports
		EObject newElement = request.getElementToConfigure();
		if (!(newElement instanceof Dependency) || !(source instanceof Element)) {
			return super.getAfterConfigureCommand(request);
		}
		
		TechnicalPolicy portTP = UMLUtil.getStereotypeApplication((Element) target, TechnicalPolicy.class);
		if (portTP == null || portTP.getDefinition() == null || !(source instanceof Class))  {
			return super.getAfterConfigureCommand(request);
		}
		if (portTP.getDefinition().getApplicability() != TechnicalPolicyApplicability.ON_SOME_PORTS) {
			return super.getAfterConfigureCommand(request);
		}
		
		PortContentProvider cp = new PortContentProvider((Class) source);

		EnhancedPopupMenu popupMenu = MenuHelper.createPopupMenu(cp, "Choose ports", false);
		CompositeCommand compositeCommand = new CompositeCommand("Configure affect policy");

		if (popupMenu.show(Display.getDefault().getActiveShell())) {
			Object result = popupMenu.getSubResult();
			if (result instanceof Port) {
				Port port = (Port) result;

				RecordingCommand configureAffectPolicy = new RecordingCommand(TransactionUtil.getEditingDomain(newElement)) {
					@Override
					protected void doExecute() {	
						portTP.getManagedPort().clear();
						portTP.getManagedPort().add(port);
					}
				};
				
				compositeCommand.add(EMFtoGMFCommandWrapper.wrap(configureAffectPolicy));
			}
		}
		else {
			// User cancelled the menu
		}
	
		return compositeCommand.isEmpty() ? super.getAfterConfigureCommand(request) : compositeCommand;
	}
	
	protected EObject source;
	protected EObject target;
}
