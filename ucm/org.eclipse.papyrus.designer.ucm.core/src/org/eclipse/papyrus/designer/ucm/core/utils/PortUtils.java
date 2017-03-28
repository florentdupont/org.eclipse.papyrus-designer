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

package org.eclipse.papyrus.designer.ucm.core.utils;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.ComponentModule;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;

public class PortUtils {

	public static final String SPEC_PREFIX = "specFor"; //$NON-NLS-1$
	
	public static final String PORT_TYPES = "portTypes"; //$NON-NLS-1$

	public static PortTypeSpec createPortTypeSpec(Port port) {
		Package componentPackage = CreateUtils.nearestPackageWithStereo(port, ComponentModule.class);

		if (componentPackage != null) {
			Package portSpecs = CreateUtils.subPackage(componentPackage, PORT_TYPES);
			Type umlPortTypeSpec = portSpecs.createOwnedClass(calcName(port.getName()), false);
						
			return StereotypeUtil.applyApp(umlPortTypeSpec, PortTypeSpec.class);
		}
		return null;
	}
	
	/**
	 * Calculate the name of a port type specification from the port
	 * @param port
	 * @return
	 */
	public static String calcName(String portName) {
		return SPEC_PREFIX + portName;
	}
}
