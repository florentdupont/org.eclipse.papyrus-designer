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

package org.eclipse.papyrus.qompass.modellibs.core.mappingrules;

import org.eclipse.papyrus.FCM.Port;
import org.eclipse.papyrus.FCM.util.IMappingRule;
import org.eclipse.papyrus.FCM.util.MapUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;

/**
 * Create a bidirectional port with a push producer and a pull consumer
 * TODO: Objective is not clear 
 * Will generate a suitable callable interface pulling consumer. The port is typed with a primitive type
 * or data type. The generated interface has a "<Type> pull as well as a "boolean hasData()" operation).
 */
public class PushProdPullCons implements IMappingRule {

	@Override
	public Type calcDerivedType(Port p, boolean update) {
		Type type = p.getType();

		if ((type instanceof PrimitiveType) || (type instanceof DataType) || (type instanceof Signal)) {

			Class derivedClass = MapUtil.getDerivedClass(p, "PushProdPullcons", update);
			if (!update) {
				return derivedClass;
			}

			// obtain derived interface for other port kind (Caveat: some rules get the prefix from the
			// name of the port kind attached to port "p" which would produce wrong results.
			Type derivedInterfacePushProd = PushProducer.getInstance().calcDerivedType(p, update);
			Type derivedInterfacePullCons = PullConsumer.getInstance().calcDerivedType(p, update);
			
			/*
			if (derivedInterface == null) {
				return null;
			}

			if (!derivedInterface.getGenerals().contains(derivedInterfacePushProd)) {
				derivedInterface.createGeneralization(derivedInterfacePushProd);
			}
			if (!derivedInterface.getGenerals().contains(derivedInterfacePullCons)) {
				derivedInterface.createGeneralization(derivedInterfacePullCons);
			}
			return derivedInterface;
			*/
			return null;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean needsUpdate(Port p) {
		return PushProducer.getInstance().needsUpdate(p) ||
				PullConsumer.getInstance().needsUpdate(p);
	}
}
