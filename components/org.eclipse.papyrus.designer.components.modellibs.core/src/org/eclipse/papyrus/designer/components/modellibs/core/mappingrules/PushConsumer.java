/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.components.modellibs.core.mappingrules;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.util.IMappingRule;
import org.eclipse.papyrus.designer.components.FCM.util.MapUtil;
import org.eclipse.papyrus.designer.components.transformation.core.Log;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;

/**
 * Will generate a suitable called interface push consumer. The port is typed with a primitive type
 * or data type. The generated interface has a "push (data <Type>)" operation ).
 *
 * The interface is identical to that of a PushProducer (and will be shared).
 *
 * @author ansgar
 */
public class PushConsumer implements IMappingRule {

	public static String PUSH_OP_PREFIX = "push"; //$NON-NLS-1$

	public static String PUSH_OP_PARNAME = "data"; //$NON-NLS-1$

	@Override
	public boolean needsUpdate(Port p) {
		Type type = p.getBase_Port().getType();

		if ((type instanceof PrimitiveType) || (type instanceof DataType) || (type instanceof Signal)) {

			Interface derivedInterface = MapUtil.getDerivedInterface(p, PushProducer.PUSH_I_PREFIX);
			if (derivedInterface == null) {
				return true;
			}
		
			Operation derivedOperation = derivedInterface.getOperation(PUSH_OP_PREFIX, null, null);
			if (derivedOperation == null) {
				return true;
			}
			EList<Parameter> parameters = derivedOperation.getOwnedParameters();
			if (parameters.size() != 1) {
				return true;
			} else {
				Parameter parameter = parameters.get(0);
				if (!parameter.getName().equals(PUSH_OP_PARNAME)) {
					return true;
				}
				if (parameter.getType() != type) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Type calcDerivedType(Port p, boolean update) {
		Log.log(IStatus.INFO, Log.CALC_PORTKIND,
				p.getKind().getBase_Class().getName() + " => GetProvided on " + p.getBase_Port().getName());
		Type type = p.getType();

		if ((type instanceof PrimitiveType) || (type instanceof DataType) || (type instanceof Signal)) {

			Interface derivedInterface = MapUtil.getDerivedInterface(p, PushProducer.PUSH_I_PREFIX, update);
			if (!update) {
				return derivedInterface;
			}
			if (derivedInterface == null) {
				// may happen, if within template (do not want creation of derived interfaces in template)
				return null;
			}

			// check whether operation already exists. Create, if not
			Operation derivedOperation = derivedInterface.getOperation(PUSH_OP_PREFIX, null, null);
			if (derivedOperation == null) {
				derivedOperation = derivedInterface.createOwnedOperation(PUSH_OP_PREFIX, null, null);
			}
			EList<Parameter> parameters = derivedOperation.getOwnedParameters();
			if (parameters.size() == 0) {
				derivedOperation.createOwnedParameter(PUSH_OP_PARNAME, type);
			} else {
				parameters.get(0).setName(PUSH_OP_PARNAME);
				parameters.get(0).setType(type);
			}
			return derivedInterface;
		} else {
			return null;
		}
	}
}
