/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher (inspired from SysML variant of this file)
 *  	- Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.util;

/**
 * Utility class to get informations on UCM resources
 *
 */
public final class UCMSuppResource {

	public static final String PROFILES_PATHMAP = "pathmap://UCM_SUPP_PROFILE/"; //$NON-NLS-1$	
	
	public static final String PROFILE_PATH = PROFILES_PATHMAP + "ucm_supplement.profile.uml"; //$NON-NLS-1$

	/**
	 * supplement profile containing the sub-profiles, deployment, detailedcomp, environment, extratypes and testcases
	 */
	public static final String PROFILE_URI = "http://www.omg.org/ucm/supplement/0.9"; //$NON-NLS-1$
}
