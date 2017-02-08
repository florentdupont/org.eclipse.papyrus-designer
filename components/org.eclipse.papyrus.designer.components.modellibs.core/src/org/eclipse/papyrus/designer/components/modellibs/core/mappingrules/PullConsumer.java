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

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.profile.IMappingRule;
import org.eclipse.papyrus.designer.components.FCM.profile.PortMapUtil;
import org.eclipse.papyrus.designer.components.modellibs.core.Activator;
import org.eclipse.papyrus.designer.languages.common.base.UriConstants;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.RealizationUtils;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;

/**
 * Will generate a suitable callable interface pulling consumer. The port is typed with a primitive type
 * or data type. The generated interface has a "<Type> pull as well as a "boolean hasData()" operation).
 */
public class PullConsumer implements IMappingRule {

	public static String PULL_I_PREFIX = "PullConsumer_"; //$NON-NLS-1$
	
	public static String PULL_C_PREFIX = "CPullConsumer_"; //$NON-NLS-1$

	public static String PULL_OP_NAME = "pull"; //$NON-NLS-1$

	public static String HASDATA_OP_NAME = "hasData"; //$NON-NLS-1$

	public static String RET_PAR_NAME = "ret"; //$NON-NLS-1$

	public static String BOOL_QNAME = "PrimitiveTypes::Boolean"; //$NON-NLS-1$

	@Override
	public boolean needsUpdate(Port p) {
		Type type = p.getType();

		if ((type instanceof PrimitiveType) || (type instanceof DataType) || (type instanceof Signal)) {

			Interface derivedInterface = PortMapUtil.getDerivedInterface(p, PULL_I_PREFIX);
			if (derivedInterface == null) {
				return true;
			}
			Operation derivedOperation = derivedInterface.getOperation(PULL_OP_NAME, null, null);
			if (derivedOperation == null) {
				return true;
			}
			EList<Parameter> parameters = derivedOperation.getOwnedParameters();
			if (parameters.size() != 1) {
				return true;
			} else {
				Parameter parameter = parameters.get(0);
				if (!parameter.getName().equals(RET_PAR_NAME)) {
					return true;
				}
				if (parameter.getType() != type) {
					return true;
				}
			}
		}
		return false;
	}

	public static PullConsumer getInstance() {
		if (instance == null) {
			instance = new PullConsumer();
		}
		return instance;
	}

	@Override
	public Type calcDerivedType(Port p, boolean update) {
		org.eclipse.uml2.uml.Port umlPort = p.getBase_Port();
		Element owner = umlPort.getOwner();
		String ownerStr = ""; //$NON-NLS-1$
		if (owner instanceof NamedElement) {
			ownerStr = " of class " + ((NamedElement) owner).getQualifiedName(); //$NON-NLS-1$
		}
		Activator.log.info(
				p.getKind().getBase_Class().getName() + " => GetRequired on " + umlPort.getName() + ownerStr);
		Type type = p.getType();

		if ((type instanceof PrimitiveType) || (type instanceof DataType) || (type instanceof Signal)) {

			Class derivedClass = PortMapUtil.getDerivedClass(p, PULL_C_PREFIX, update);
			Interface derivedInterface = PortMapUtil.getDerivedInterface(p, PULL_I_PREFIX, update);
			RealizationUtils.addUsage(derivedClass, derivedInterface);
			if (!update) {
				return derivedClass;
			}
			if (derivedInterface ==  null) {
				return null;
			}
			// check whether operation already exists. Create, if not
			Operation derivedOperationPull = derivedInterface.getOperation(PULL_OP_NAME, null, null);
			if (derivedOperationPull == null) {
				derivedOperationPull = derivedInterface.createOwnedOperation(PULL_OP_NAME, null, null, type);
			}
			EList<Parameter> parameters = derivedOperationPull.getOwnedParameters();
			if (parameters.size() > 0) {
				Parameter parameter = parameters.get(0);
				if ((parameter.getName() == null) || (!parameter.getName().equals(RET_PAR_NAME))) {
					parameter.setName(RET_PAR_NAME);
				}
				if (parameter.getType() != type) {
					parameter.setType(type);
				}
			}
			Package model = PackageUtil.getRootPackage(umlPort);
			PackageUtil.loadPackage(UriConstants.UML_PRIMITIVE_TYPES, model.eResource().getResourceSet());
			Element element = ElementUtils.getQualifiedElementFromRS(model, BOOL_QNAME);
			Type booleanType = null;
			if (element instanceof Type) {
				booleanType = (Type) element;
			}
			else {
				throw new RuntimeException(String.format("Cannot find type %s. Check whether the UML primitive types library has been imported.", BOOL_QNAME)); //$NON-NLS-1$
			}

			// check whether operation already exists. Create, if not
			Operation derivedOperationHasData = derivedInterface.getOperation(HASDATA_OP_NAME, null, null);
			if (derivedOperationHasData == null) {
				derivedOperationHasData = derivedInterface.createOwnedOperation(HASDATA_OP_NAME, null, null, booleanType);
			}

			parameters = derivedOperationHasData.getOwnedParameters();
			if (parameters.size() > 0) {
				Parameter parameter = parameters.get(0);
				if ((parameter.getName() == null) || (!parameter.getName().equals(RET_PAR_NAME))) {
					parameter.setName(RET_PAR_NAME);
				}
				if ((booleanType != null) && (parameter.getType() != booleanType)) {
					// added != null check
					parameter.setType(booleanType);
				}
			}

			return derivedClass;
		} else {
			return null;
		}
	}

	protected static PullConsumer instance;
}
