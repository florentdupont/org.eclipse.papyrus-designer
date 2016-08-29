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

package org.eclipse.papyrus.designer.components.transformation.component;

/**
 * A set of constants used for the CIF. In particular prefixes for the operations
 * to access the implementation of a provided interface and connect a required interface
 *
 */
public class PrefixConstants {

	public static String getP_Prefix;

	public static String getConnQ_Prefix;

	public static String connectQ_Prefix;

	public static String attributePrefix;

	public static String createConnections;

	public enum CIFvariant {
		UML, CCM
	}

	public static final String INDEX_TYPE_FOR_MULTI_RECEPTACLE = "corba::Long"; //$NON-NLS-1$;

	/**
	 * Initialize a specific CIF variant
	 *
	 * TODO currently unused
	 *
	 * @param cif
	 *            Which CIF variant should used?
	 */
	public static void init(CIFvariant cif) {
		if (cif == CIFvariant.UML) {
			getP_Prefix = "get_"; //$NON-NLS-1$
			getConnQ_Prefix = ""; //$NON-NLS-1$
			connectQ_Prefix = "connect_"; //$NON-NLS-1$
			attributePrefix = ""; //$NON-NLS-1$
		} else if (cif == CIFvariant.CCM) {
			getP_Prefix = "get_"; //$NON-NLS-1$
			getConnQ_Prefix = "getconnection_"; //$NON-NLS-1$
			connectQ_Prefix = "connect_"; //$NON-NLS-1$
			attributePrefix = "m_"; //$NON-NLS-1$
		}
		createConnections = "createConnections"; //$NON-NLS-1$
	}
};
