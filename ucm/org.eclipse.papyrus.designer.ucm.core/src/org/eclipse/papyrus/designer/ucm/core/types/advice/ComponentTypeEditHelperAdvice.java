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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.ConfigureElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.commands.Activator;
import org.eclipse.papyrus.designer.ucm.core.utils.CreateUtils;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForEObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

/**
 *
 */
public class ComponentTypeEditHelperAdvice extends AbstractEditHelperAdvice {

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

	protected boolean approveCreateRelationshipRequest(IEditCommandRequest request) {
		return super.approveRequest(request);
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getBeforeConfigureCommand(org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest)
	 *
	 * @param request
	 * @return
	 */
	@Override
	protected ICommand getBeforeConfigureCommand(ConfigureRequest request) {
		final Class capsule = (Class) request.getElementToConfigure();

		return new ConfigureElementCommand(request) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				capsule.setIsActive(true);

				createCompositeDiagram();

				return CommandResult.newOKCommandResult(capsule);
			}

			protected Diagram createCompositeDiagram() {
				ServicesRegistry registry;
				try {
					registry = ServiceUtilsForEObject.getInstance().getServiceRegistry(capsule);
				} catch (ServiceException ex) {
					Activator.log.error(ex);
					return null;
				}
				ModelSet modelSet;
				try {
					modelSet = registry.getService(ModelSet.class);
				} catch (ServiceException ex) {
					Activator.log.error(ex);
					return null;
				}

				return null;
			}

		};
	}
}
