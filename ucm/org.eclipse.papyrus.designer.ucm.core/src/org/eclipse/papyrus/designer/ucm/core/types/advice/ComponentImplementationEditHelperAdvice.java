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

import org.eclipse.core.commands.ExecutionException;
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
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.designer.ucm.core.Activator;
import org.eclipse.papyrus.designer.ucm.core.menu.EnhancedPopupMenu;
import org.eclipse.papyrus.designer.ucm.core.menu.MenuHelper;
import org.eclipse.papyrus.designer.ucm.core.provider.UCMContentProvider;
import org.eclipse.papyrus.designer.ucm.core.utils.CreateUtils;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentType;
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForEObject;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.infra.services.edit.utils.GMFCommandUtils;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;


/**
 *
 */
public class ComponentImplementationEditHelperAdvice extends AbstractEditHelperAdvice {

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
	 * Allow creation only, if container is a componentModule (could be indirectly)
	 */
	protected boolean approveCreateElementRequest(CreateElementRequest request) {
		IElementType type = request.getElementType();
		EObject container = request.getContainer();
		if (type != null && container instanceof Package) {
			if (CreateUtils.nearestPackageWithStereo((Element) container, ComponentModule.class) != null) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getAfterCreateCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest)
	 */
	@Override
	protected ICommand getAfterCreateCommand(CreateElementRequest request) {
		// System.err.println("Create implementation");
		return super.getAfterCreateCommand(request);
	}
	
	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getAfterConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest)
	 */
	@Override
	protected ICommand getAfterConfigureCommand(ConfigureRequest request) {
		CompositeCommand compositeCommand = new CompositeCommand("Component implementation configuration command");
		EObject newElement = request.getElementToConfigure();
		if (newElement == null) {
			return super.getAfterConfigureCommand(request);
		}
		IElementEditService commandProvider = ElementEditServiceUtils.getCommandProvider(newElement);

		IStaticContentProvider cp = new UCMContentProvider(
				PackageUtil.getRootPackage((Element) newElement), UMLPackage.eINSTANCE.getClass_(), ComponentType.class);
		
		EnhancedPopupMenu popupMenu = MenuHelper.createPopupMenu(cp, "Choose component type", true);

		if (popupMenu.show(Display.getDefault().getActiveShell())) {
			Object result = popupMenu.getSubResult();
			if (result instanceof Type) {
				Type componentType = (Type) result;

				CreateElementRequest createGeneralizationReq =
						new CreateElementRequest(newElement, UMLElementTypes.GENERALIZATION, UMLPackage.eINSTANCE.getClassifier_Generalization());
				ICommand createGeneralizationCm = commandProvider.getEditCommand(createGeneralizationReq);
				compositeCommand.add(createGeneralizationCm);
				// TODO: command executed here + later? (since part of composition)
				try {
					createGeneralizationCm.execute(null, null);
				} catch (ExecutionException e) {
					Activator.log.error(e);
				}
				newElement = GMFCommandUtils.getCommandEObjectResult(createGeneralizationCm);
				
				SetRequest setType = new SetRequest(createGeneralizationReq.getNewElement(), UMLPackage.eINSTANCE.getGeneralization_General(), componentType);
				compositeCommand.add(commandProvider.getEditCommand(setType));
			}
		}
		else {
			// User cancelled the menu
		}
	
		return compositeCommand.isEmpty() ? super.getAfterConfigureCommand(request) : compositeCommand;
	}
	
	public void ToDiagram(EObject obj) throws ServiceException {
		ISashWindowsContainer windowsContainer = ServiceUtilsForEObject.getInstance()
				.getServiceRegistry(obj).getService(ISashWindowsContainer.class);
		Object model = windowsContainer.getActiveSashWindowsPage().getRawModel();
		if (model instanceof PageRef) {
			EObject diagramEObj = ((PageRef) model).getEmfPageIdentifier();
			if (diagramEObj instanceof Diagram) {
				Diagram diagram = (Diagram) diagramEObj;
				/*
				View cuEdge = compViewProvider.createDependency_RoleBindingEdge(rb,
						((BasicCompartment) compartment).getDiagram(), -1, true,
						org.eclipse.papyrus.uml.diagram.composite.part.UMLDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				if (cuEdge instanceof Edge) {
					((Edge) cuEdge).setSource(roleBindingView);
					((Edge) cuEdge).setTarget(roleView);
				}
				*/
			}
		}
	}
}
