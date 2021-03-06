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

import org.eclipse.papyrus.designer.components.FCM.profile.IMappingRule;
import org.eclipse.papyrus.designer.components.FCM.profile.PortMapUtil;
import org.eclipse.papyrus.designer.components.transformation.PortUtils;
import org.eclipse.papyrus.designer.components.transformation.component.PrefixConstants;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;


/**
 * An extended Port in the sense of the DDS4CCM standard: a port typed with a component
 * type (the extended port, not the data type that is transported). Since the component typing
 * the port can have multiple provided and required
 * ports, the ports are implicitly owned by the port. Conjugation on the level of an extended
 * port level will conjugate all ports of the component typing the port.
 *
 * The derived interface that is provided will allow a caller to access individual ports. It
 * is therefore a reference to the interfaces get_<portName> operations provided by a
 * component.
 * The derived interface that is required is identical, except for a conjugation on the
 * extended port level.
 *
 * What is the difference to ExtendedPort?
 *
 */
public class ExtendedPort2 implements IMappingRule {

	public static final String CONJ_PREFIX = "C2_"; //$NON-NLS-1$

	public static final String NORM_PREFIX = "N2_"; //$NON-NLS-1$

	@Override
	public Type calcDerivedType(org.eclipse.papyrus.designer.components.FCM.Port p, boolean update) {
	
		Type type = p.getType();
		if (!(type instanceof Class)) {
			return null;
		}

		Class extendedPort = (Class) type;
		boolean isConjugated = p.getBase_Port().isConjugated();
		String prefix = isConjugated ? CONJ_PREFIX : NORM_PREFIX;
		Class derivedClass = PortMapUtil.getDerivedClass(p, prefix, update);
		Interface providedInterface = PortMapUtil.getDerivedInterface(p, prefix, update);
		Interface requiredInterface = PortMapUtil.getDerivedInterface(p, prefix, update);
		if (!update) {
			return derivedClass;
		}
		if (derivedClass == null) {
			return null;
		}
		for (Port port : extendedPort.getOwnedPorts()) {
			// if the extended port is conjugated, each of the provided/required are (implicitly)
			// conjugated [TODO: is PortUtils aware of it? - probably yes]
			Interface reqIntf = PortUtils.getRequired(port);
		
			if (reqIntf != null) {
				String name = PrefixConstants.getP_Prefix + port.getName();

				// check whether operation already exists. Create, if not
				Operation derivedOperation = requiredInterface.getOperation(name, null, null);
				if (derivedOperation == null) {
					derivedOperation = requiredInterface.createOwnedOperation(name, null, null);
				}
			}
			
			Interface provIntf = PortUtils.getProvided(port);
			
			if (provIntf != null) {
				String name = PrefixConstants.getConnQ_Prefix + port.getName();

				// check whether operation already exists. Create, if not
				Operation derivedOperation = providedInterface.getOperation(name, null, null);
				if (derivedOperation == null) {
					derivedOperation = providedInterface.createOwnedOperation(name, null, null);
				}
			}
		}
		return derivedClass;
	}

	@Override
	public boolean needsUpdate(org.eclipse.papyrus.designer.components.FCM.Port p) {
		return needsUpdate(p, false) ||
				needsUpdate(p, true);
	}

	public boolean needsUpdate(org.eclipse.papyrus.designer.components.FCM.Port p, boolean isConjugated) {
		Type type = p.getBase_Port().getType();
		if (!(type instanceof Class)) {
			return false;
		}

		Class extendedPort = (Class) type;
		String prefix = isConjugated ? CONJ_PREFIX : NORM_PREFIX;
		Interface derivedInterface = PortMapUtil.getDerivedInterface(p, prefix);

		if (derivedInterface == null) {
			return true;
		}
		for (Port port : extendedPort.getOwnedPorts()) {
			// if the extended port is conjugated, each of the provided/required are (implicitly)
			// conjugated [TODO: is PortUtils aware of it? - probably yes]
			Interface provIntf = (isConjugated) ?
					PortUtils.getRequired(port) :
					PortUtils.getProvided(port);

			if (provIntf != null) {
				String name = PrefixConstants.getP_Prefix + port.getName();

				// check whether operation already exists. Create, if not
				Operation derivedOperation = derivedInterface.getOperation(name, null, null);
				if (derivedOperation == null) {
					return true;
				}
			}
		}
		return false;
	}
}
