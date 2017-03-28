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
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.papyrus.designer.ucm.core.menu.EnhancedPopupMenu;
import org.eclipse.papyrus.designer.ucm.core.menu.MenuHelper;
import org.eclipse.papyrus.designer.ucm.core.provider.PortElementKindContentProvider;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElement;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortElementKind;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition;
import org.eclipse.papyrus.infra.emf.gmf.command.EMFtoGMFCommandWrapper;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Edit Helper Advice for {@link Port}
 */
public class PortElementEditHelperAdvice extends AbstractEditHelperAdvice {

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
	 * Check the creation condition for the different UCM module kinds
	 *
	 * @param request
	 *            the create request
	 * @return true, if the element can be created
	 */
	protected boolean approveCreateElementRequest(CreateElementRequest request) {
		IElementType type = request.getElementType();
		EObject container = request.getContainer();
		if (type != null && container instanceof Class) {
			if (UMLUtil.getStereotypeApplication((Class) container, PortType.class) != null) {
				return true;
			}
			if (UMLUtil.getStereotypeApplication((Class) container, TechnicalPolicyDefinition.class) != null) {
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
		if (!(newElement instanceof Element)) {
			return super.getAfterConfigureCommand(request);
		}

		PortElementKindContentProvider cp = new PortElementKindContentProvider();

		EnhancedPopupMenu popupMenu = MenuHelper.createPopupMenu(cp, "Choose value", false);
		
		CompositeCommand compositeCommand = new CompositeCommand("Configure port element");
		if (popupMenu.show(Display.getDefault().getActiveShell())) {
			Object result = popupMenu.getSubResult();
		
			if (result instanceof PortElementKind) {
				PortElementKind kind = (PortElementKind) result;

				RecordingCommand setPortElemKind = new RecordingCommand(TransactionUtil.getEditingDomain(newElement)) {
					@Override
					protected void doExecute() {
						// TODO: exception, if element type also applies the stereotype.
						PortElement portElement = StereotypeUtil.applyApp((Element) newElement, PortElement.class);
						portElement.setKind(kind);
					}
				};
				
				compositeCommand.add(EMFtoGMFCommandWrapper.wrap(setPortElemKind));
			}
		}
		else {
			// User cancelled the menu
		}
	
		return compositeCommand.isEmpty() ? super.getAfterConfigureCommand(request) : compositeCommand;
		
	}
}
