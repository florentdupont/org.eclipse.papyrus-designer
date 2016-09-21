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
package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;

public class RealizationUtils {
	
	public static final String REALIZATION_OF = "realization of "; //$NON-NLS-1$

	/**
	 * Add an interface realization relationship from an implementation (Class) towards an interface
	 *
	 * @param implementation
	 *            A class
	 * @param intf
	 *            The interface it should realize
	 */
	public static void addRealization(final Class implementation, final Interface intf) {
		if (!hasRealization(implementation, intf)) {
			InterfaceRealization ir =
					implementation.createInterfaceRealization(calcRealizationName(intf), intf);
			ir.getClients().add(implementation);
			ir.getSuppliers().add(intf);
		}
	}

	/**
	 * check, whether a realization relationship to a given interface exists
	 *
	 * @param implementation
	 *            A class
	 * @param intf
	 *            The interface it should realize
	 * @return true, if the class has an (interface) realization relationship to the passed interface
	 */
	public static boolean hasRealization(Class implementation, Interface intf) {
		return implementation.getInterfaceRealization(null, intf) != null;
	}

	/**
	 * Calculate the name of an interface realization to an interface
	 *
	 * @param intf
	 *            an interface
	 * @return the calculated name for the realization relationship
	 */
	public static String calcRealizationName(Interface intf) {
		String name = intf.getName();
		if (name == null) {
			name = "undefined"; //$NON-NLS-1$
		}
		return REALIZATION_OF + name;
	}

	public static void addUsage(Class portType, Interface usedInterface) {
		if (!portType.getUsedInterfaces().contains(usedInterface)) {
			portType.createUsage(usedInterface);
		}
	}
}
