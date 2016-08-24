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
 */

package org.eclipse.papyrus.designer.components.transformation.templates;

import org.eclipse.papyrus.designer.components.transformation.PortUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;

/**
 * TODO: Check whether used/useful
 */
public class PortBindingUtils {

	/**
	 * @param actual
	 *            the actual template parameter
	 * @param boundClass
	 *            the bound class
	 * @param provides
	 *            true, if the provided interface should be returned
	 * @return the provided or required interface of a port (of the passed
	 *         boundClass) that is typed with the the actual.
	 */
	public static Interface getInterfaceFromPortTypedWithActual(Type actual, Class boundClass, boolean provided) {
		for (Port port : PortUtils.getAllPorts(boundClass)) {
			Interface provOrReqIntf;
			if (provided) {
				provOrReqIntf = PortUtils.getProvided(port);
			} else {
				provOrReqIntf = PortUtils.getRequired(port);
			}

			if ((port.getType() == actual) && (provOrReqIntf != null)) {
				return provOrReqIntf;
			}
		}
		return null;
	}
}
