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
import org.eclipse.papyrus.designer.ucm.core.provider.TechnicalPolicyContentProvider;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.NonfunctionalAspectModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalAspect;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.TechnicalPolicyDefinition;
import org.eclipse.papyrus.infra.emf.gmf.command.EMFtoGMFCommandWrapper;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Edit Helper Advice for {@link TechnicalPolicyDefinition}
 */
public class TechnicalPolicyDefinitionEditHelperAdvice extends AbstractEditHelperAdvice {

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
	 * Check the creation condition for a technical policy definition
	 *
	 * @param request
	 *            the create request
	 * @return true, if the element can be created
	 */
	protected boolean approveCreateElementRequest(CreateElementRequest request) {
		IElementType type = request.getElementType();
		EObject container = request.getContainer();
		if (type != null && container instanceof Package) {
			if (UMLUtil.getStereotypeApplication((Package) container, NonfunctionalAspectModule.class) != null) {
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

		TechnicalPolicyContentProvider cp = new TechnicalPolicyContentProvider(
				PackageUtil.getRootPackage((Element) newElement), UMLPackage.eINSTANCE.getClass_(), TechnicalAspect.class);

		EnhancedPopupMenu popupMenu = MenuHelper.createPopupMenu(cp, "Choose aspect", false);
		CompositeCommand compositeCommand = new CompositeCommand("Configure technical policy definition");

		if (popupMenu.show(Display.getDefault().getActiveShell())) {
			Object result = popupMenu.getSubResult();
		
			if (result instanceof Class) {
				Class techAspect = (Class) result;

				RecordingCommand configureAffectPolicy = new RecordingCommand(TransactionUtil.getEditingDomain(newElement)) {
					@Override
					protected void doExecute() {
						Realization realization = UMLFactory.eINSTANCE.createRealization();
						Class definition = (Class) newElement;
						realization.getClients().add(definition);
						realization.getSuppliers().add(techAspect);
						definition.getNearestPackage().getPackagedElements().add(realization);
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
}
