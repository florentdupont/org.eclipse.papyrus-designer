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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyDependentsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.papyrus.designer.ucm.core.menu.EnhancedPopupMenu;
import org.eclipse.papyrus.designer.ucm.core.menu.MenuHelper;
import org.eclipse.papyrus.designer.ucm.core.provider.UCMContentProvider;
import org.eclipse.papyrus.designer.ucm.core.utils.PortUtils;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType;
import org.eclipse.papyrus.infra.emf.gmf.command.EMFtoGMFCommandWrapper;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Edit Helper Advice for {@link Port}
 */
public class PortEditHelperAdvice extends AbstractEditHelperAdvice {

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
	 * Allow creation only, if container is a component type
	 */
	protected boolean approveCreateElementRequest(CreateElementRequest request) {
		IElementType type = request.getElementType();
		EObject container = request.getContainer();
		if (type != null && container instanceof Class) {
			if (UMLUtil.getStereotypeApplication((Class) container, ComponentType.class) != null) {
				return true;
			}
		}
		return false;
	}


	/**
	 * If the name of a port changes, change the name of the associated port type spec, if any.
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getBeforeConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest)
	 */
	@Override
	protected ICommand getAfterSetCommand(SetRequest request) {
		EObject toEdit = request.getElementToEdit();
		EStructuralFeature feature = request.getFeature();
		if (toEdit instanceof Port && feature == UMLPackage.eINSTANCE.getNamedElement_Name()) {
			Type type = ((Port) toEdit).getType();
			if (type != null && StereotypeUtil.isApplied(type, PortTypeSpec.class)) {
				String newName = PortUtils.calcName((String) request.getValue());
				if (!type.getName().equals(newName)) {
					SetRequest setRequest = new SetRequest(type, feature, newName);
					return ElementEditServiceUtils.getCommandProvider(type).getEditCommand(setRequest);
				}
			}
		}
		return super.getAfterSetCommand(request);
	}


	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getAfterConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest)
	 */
	@Override
	protected ICommand getAfterConfigureCommand(ConfigureRequest request) {
		CompositeCommand compositeCommand = new CompositeCommand("Port configuration command"); //$NON-NLS-1$
		EObject newElement = request.getElementToConfigure();

		IStaticContentProvider cp = new UCMContentProvider(PackageUtil.getRootPackage((Element) newElement), UMLPackage.eINSTANCE.getType(), IPortType.class);

		EnhancedPopupMenu popupMenu = MenuHelper.createPopupMenu(cp, "Choose port type", false);

		if (popupMenu.show(Display.getDefault().getActiveShell())) {
			Object result = popupMenu.getSubResult();
			if (result instanceof Type) {
				Type portType = (Type) result;

				RecordingCommand setStereoCommand = new RecordingCommand(TransactionUtil.getEditingDomain(portType)) {
					@Override
					protected void doExecute() {

						Port newPort = (Port) newElement;
						PortTypeSpec portTypeSpec = PortUtils.createPortTypeSpec(newPort);
						EObject ucmPortType = UMLUtil.getStereotypeApplication(portType, IPortType.class);
						portTypeSpec.setType((IPortType) ucmPortType);

						// set port to port type spec
						newPort.setType(portTypeSpec.getBase_Class());

					}
				};
				compositeCommand.add(EMFtoGMFCommandWrapper.wrap(setStereoCommand));
			}
		} else {
			// User cancelled the menu
		}

		return compositeCommand.isEmpty() ? super.getAfterConfigureCommand(request) : compositeCommand;
	}
	
	/**
	 * If a port is destroyed, also destroy its port type specification, if any
	 */
	@Override
	protected ICommand getAfterDestroyDependentsCommand(DestroyDependentsRequest request) {
		EObject toDestroy = request.getElementToDestroy();
		if (toDestroy instanceof Port) {
			Type type = ((Port) toDestroy).getType();
			if (type != null && StereotypeUtil.isApplied(type, PortTypeSpec.class)) {
				DestroyElementRequest destroyPortTypeSpec = new DestroyElementRequest(TransactionUtil.getEditingDomain(type), type, false);
				return new DestroyElementCommand(destroyPortTypeSpec);
				
			}
		}
		return super.getAfterDestroyDependentsCommand(request);
	}

	@Override
	protected ICommand getBeforeDestroyElementCommand(	DestroyElementRequest request) {
		return super.getBeforeDestroyElementCommand(request);
 
	}
	
	/**
	 * Gets my 'after' advice for destroying an element.
	 * 
	 * @param request
	 *            the request
	 * @return the command to execute after the edit helper work is done
	 */
	@Override
	protected ICommand getAfterDestroyElementCommand(
			DestroyElementRequest request) {
		return super.getAfterDestroyElementCommand(request);
	}
}
