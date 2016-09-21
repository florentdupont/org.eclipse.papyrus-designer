/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.modellibs.core.mappingrules;

import java.util.Iterator;

import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.profile.IMappingRule;
import org.eclipse.papyrus.designer.components.FCM.profile.PortMapUtil;
import org.eclipse.papyrus.designer.transformation.base.utils.RealizationUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Type;


public class AMIcallback implements IMappingRule {

	private static final String _REPLY = "_reply_"; //$NON-NLS-1$
	private static final String _REQUEST = "_request_"; //$NON-NLS-1$
	private static final String AMI_CB = "AMI_CB"; //$NON-NLS-1$

	@Override
	public Type calcDerivedType(Port p, boolean update) {
		Type type = p.getType();
		if (!(type instanceof Interface)) {
			return null;
		}

		Interface typingInterface = (Interface) type;
		Class derivedType = PortMapUtil.getDerivedClass(p, AMI_CB);
		Interface derivedRequestInterface = PortMapUtil.getDerivedInterface(p, _REQUEST);
		Interface derivedReplyInterface = PortMapUtil.getDerivedInterface(p, _REPLY);
		RealizationUtils.addUsage(derivedType, derivedRequestInterface);			// caller can use (require) the request interface
		RealizationUtils.addRealization(derivedType, derivedReplyInterface);		// callers must implement (provide) the reply interface
		
		if (!update) {
			return derivedType;
		}

		// -----------------------------------------------
		// calculate "request" interface (OUT parameter are removed)
		// -----------------------------------------------
		for (Operation operation : typingInterface.getOwnedOperations()) {
			String name = operation.getName();

			// check whether operation already exists. Create, if not
			Operation derivedOperation = derivedRequestInterface.getOperation(name, null, null);
			if (derivedOperation == null) {
				derivedOperation = derivedRequestInterface.createOwnedOperation(name, null, null);
			}

			// request operation contains only in and inout parameters
			for (Parameter parameter : operation.getOwnedParameters()) {
				if ((parameter.getDirection() == ParameterDirectionKind.IN_LITERAL) ||
						(parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL)) {

					String paramName = parameter.getName();
					Type paramType = parameter.getType();
					if (derivedOperation.getOwnedParameter(paramName, paramType) == null) {
						Parameter newParameter = derivedOperation.createOwnedParameter(parameter.getName(), parameter.getType());
						newParameter.setDirection(parameter.getDirection());
						newParameter.setLower(parameter.getLower());
						newParameter.setUpper(parameter.getUpper());
					}
				}
			}

			// remove those parameters that exist in derived, but not original interface.
			Iterator<Parameter> derivedParameters = derivedOperation.getOwnedParameters().iterator();
			while (derivedParameters.hasNext()) {
				Parameter parameter = derivedParameters.next();
				String paramName = parameter.getName();
				Type paramType = parameter.getType();
				if (operation.getOwnedParameter(paramName, paramType) == null) {
					// not on in original interface, remove from derived as well
					derivedParameters.remove();
				}
			}
		}

		// check whether operations in derived interface exist in original interface
		// (remove, if not)
		Iterator<Operation> derivedRequestOperations = derivedRequestInterface.getOwnedOperations().iterator();
		while (derivedRequestOperations.hasNext()) {
			Operation derivedOperation = derivedRequestOperations.next();
			String name = derivedOperation.getName();
			if (name == null) {
				continue;
			}
			if (typingInterface.getOperation(name, null, null) == null) {
				// not in typing interface, remove
				derivedRequestOperations.remove();
			}
		}

		// -----------------------------------------------
		// calculate "reply" interface (with OUT and INOUT parameter transformed into in parameters)
		// -----------------------------------------------
		for (Operation operation : typingInterface.getOwnedOperations()) {
			String name = operation.getName();

			if (AMIpoll.hasOutParameters(operation)) {

				// check whether operation already exists. Create, if not
				Operation derivedOperation = derivedReplyInterface.getOperation(name, null, null);
				if (derivedOperation == null) {
					derivedOperation = derivedReplyInterface.createOwnedOperation(name, null, null);
				}

				// each non-in parameter is in the poll operation.
				for (Parameter parameter : operation.getOwnedParameters()) {
					if (parameter.getDirection() != ParameterDirectionKind.IN_LITERAL) { // OUT and INOUT

						String paramName = parameter.getName();
						Type paramType = parameter.getType();
						if (derivedOperation.getOwnedParameter(paramName, paramType) == null) {
							Parameter newParameter = derivedOperation.createOwnedParameter(parameter.getName(), parameter.getType());
							newParameter.setDirection(ParameterDirectionKind.IN_LITERAL);
							newParameter.setLower(parameter.getLower());
							newParameter.setUpper(parameter.getUpper());
						}
					}
				}

				// remove those parameters that exist in derived, but not original interface.
				Iterator<Parameter> derivedParameters = derivedOperation.getOwnedParameters().iterator();
				while (derivedParameters.hasNext()) {
					Parameter parameter = derivedParameters.next();
					String paramName = parameter.getName();
					Type paramType = parameter.getType();
					if (operation.getOwnedParameter(paramName, paramType) == null) {
						// not on in original interface, remove from derived as well
						derivedParameters.remove();
					}
				}
			}
		}

		// check whether operations in derived interface exist in original interface
		// (remove, if not)
		Iterator<Operation> derivedReplyOperations = derivedReplyInterface.getOwnedOperations().iterator();
		while (derivedRequestOperations.hasNext()) {
			Operation derivedOperation = derivedReplyOperations.next();
			String name = derivedOperation.getName();
			if (name == null) {
				continue;
			}
			if (typingInterface.getOperation(name, null, null) == null) {
				// not in typing interface, remove
				derivedReplyOperations.remove();
			}
		}

		return derivedType;
	}

	@Override
	public boolean needsUpdate(Port p) {
		// TODO: insufficient condition
		return (calcDerivedType(p, false) == null);
	}
}
