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
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.papyrus.designer.ucm.core.menu.EnhancedPopupMenu;
import org.eclipse.papyrus.designer.ucm.core.menu.MenuHelper;
import org.eclipse.papyrus.designer.ucm.core.provider.UCMContentProvider;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.CompositeComponentImplementation;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.ConnectorDefinition;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.AppAssembly;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;


/**
 * Edit Helper Advice for {@link Connection}
 */
public class ConnectionEditHelperAdvice extends AbstractEditHelperAdvice {

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
	 * Allow creation only, if container is a composite component implementation or an application assembly
	 */
	protected boolean approveCreateElementRequest(CreateElementRequest request) {
		IElementType type = request.getElementType();
		EObject container = request.getContainer();
		if (type != null && container instanceof Class) {
			if (UMLUtil.getStereotypeApplication((Class) container, CompositeComponentImplementation.class) != null ||
				UMLUtil.getStereotypeApplication((Class) container, AppAssembly.class) != null) {
				return true;
			}
		}
		return false;
	}


	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getAfterConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest)
	 */
	@Override
	protected ICommand getAfterConfigureCommand(ConfigureRequest request) {
		CompositeCommand compositeCommand = new CompositeCommand("Connection configuration command");
		EObject newElement = request.getElementToConfigure();
		if (newElement == null) {
			return super.getAfterConfigureCommand(request);
		}
		IElementEditService commandProvider = ElementEditServiceUtils.getCommandProvider(newElement);

		IStaticContentProvider cp = new UCMContentProvider(
				PackageUtil.getRootPackage((Element) newElement), UMLPackage.eINSTANCE.getClass_(), ConnectorDefinition.class);
		
		EnhancedPopupMenu popupMenu = MenuHelper.createPopupMenu(cp, "Choose connector definition", false);

		if (popupMenu.show(Display.getDefault().getActiveShell())) {
			Object result = popupMenu.getSubResult();
			if (result instanceof Type) {
				Type connectorType = (Type) result;

				SetRequest setType = new SetRequest(newElement, UMLPackage.eINSTANCE.getTypedElement_Type(), connectorType);
				compositeCommand.add(commandProvider.getEditCommand(setType));
			}
		}
		else {
			// User cancelled the menu
		}
	
		return compositeCommand.isEmpty() ? super.getAfterConfigureCommand(request) : compositeCommand;	}
}
