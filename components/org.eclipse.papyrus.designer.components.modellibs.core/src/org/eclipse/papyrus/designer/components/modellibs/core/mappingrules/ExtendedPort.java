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
import org.eclipse.papyrus.designer.transformation.base.utils.OperationUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;


/**
 * An extended Port in the sense of the DDS4CCM standard: a port typed with a component
 * type. Since the component typing the port can have multiple provided and required
 * ports, the ports are implicitly owned by the port. Conjugation on the level of an extended
 * port level will conjugate all ports of the component typing the port.
 *
 * The derived interface that is provided will concatenate provided port names within the
 * extended port with the port name and provided a "big" interface.
 * The derived interface that is required is identical, except for a conjugation on the
 * extended port level.
 *
 * Creates a fixed template binding that binds T (of the extended Port) to the used data type
 *
 * Here, the idea is that the port type is a classifier, e.g. the data type that is
 * transported by a DDS port. The port kind is the extended port, e.g. DDSWrite.
 *
 * The derived property isExtended of FCM port-kind is true, if the class representing the port-kind owns at least one
 * port
 *
 * TODO: This class has become obsolete now, since extended DDS ports are now supported via flattening
 *
 */
@Deprecated
public class ExtendedPort implements IMappingRule {

	public static final String PROV_PREFIX = "P_"; //$NON-NLS-1$

	public static final String REQ_PREFIX = "R_"; //$NON-NLS-1$

	@Override
	public boolean needsUpdate(org.eclipse.papyrus.designer.components.FCM.Port p) {
		return needsUpdate(p, false) ||
				needsUpdate(p, true);
	}

	public boolean needsUpdate(org.eclipse.papyrus.designer.components.FCM.Port p, boolean isRequired) {
		return true;
	}

	@Override
	public Type calcDerivedType(org.eclipse.papyrus.designer.components.FCM.Port p, boolean update)
	{
		Type type = p.getType();
		if (!(type instanceof Classifier)) {
			return null;
		}
		Class extendedPort = p.getKind().getBase_Class();

		String prefix = extendedPort.getName() + "_" + (p.getBase_Port().isConjugated() ? REQ_PREFIX : PROV_PREFIX); //$NON-NLS-1$
		Interface derivedInterface = PortMapUtil.getDerivedInterface(p, prefix, true);
		if (!update) {
			return derivedInterface;
		}
		if (derivedInterface == null) {
			return null;
		}
		/*
		 * TemplateSignature signature = TemplateUtils.getSignature(type.getNearestPackage());
		 * if(signature != null) {
		 * Package model = Utils.getTop(derivedInterface);
		 * try {
		 * TemplateBinding binding =
		 * TemplateUtils.fixedBinding(model, extendedPort, (Classifier)type);
		 * Copy copier = new Copy(model, model, false);
		 * TemplateInstantiation ti = new TemplateInstantiation(copier, binding);
		 * // create a bound element of the extended port. Add bound class to derived interface class
		 * Class boundClass = ti.bindNamedElement(extendedPort);
		 * derivedInterface.getNearestPackage().getPackagedElements().add(boundClass);
		 * } catch (TransformationException e) {
		 * return null;
		 * }
		 * }
		 */
		// obtain first template parameter = port type
		// kind.getBase_Class().getNearestPackage().getTemplateParameter();

		for (Port port : extendedPort.getOwnedPorts()) {
			Interface derivedIntf = (p.getBase_Port().isConjugated()) ?
					PortUtils.getRequired(port) :
					PortUtils.getProvided(port);

			if (derivedIntf != null) {
				for (Operation op : derivedIntf.getAllOperations()) {
					String name = port.getName() + "_" + op.getName(); //$NON-NLS-1$

					// check whether operation already exists. Create, if not
					Operation derivedOperation = derivedInterface.getOperation(name, null, null);
					if (derivedOperation == null) {
						derivedOperation = derivedInterface.createOwnedOperation(name, null, null);
						OperationUtils.syncOperation(op, derivedOperation);
						derivedOperation.setName(name);
					}
					else {
						if (!OperationUtils.isSameOperation(derivedOperation, op, false)) {
							OperationUtils.syncOperation(op, derivedOperation);
							derivedOperation.setName(name);
						}
					}
				}
			}
		}
		return derivedInterface;
	}
}
