/*******************************************************************************
 * Copyright (c) 2011 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.papyrus.designer.transformation.base.Activator;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;

/**
 * This class applies a profile to a given package
 */
public class ApplyProfile extends RecordingCommand {

	/**
	 * Apply a profile to a given package
	 * @param pkg the package to which the profile should be applied
	 * @param profileURI the URI of the profile
	 */
	public ApplyProfile(Package pkg, URI profileURI) {
		super(TransactionUtil.getEditingDomain(pkg), CMD_LABEL);
		this.pkg = pkg;
		this.profileURI = profileURI;
	}

	public static final String CMD_LABEL = "Apply %s profile"; //$NON-NLS-1$

	Package pkg;

	URI profileURI;

	@Override
	protected void doExecute() {
		try {
			Profile profile = (Profile) StdModelLibs.addResource(profileURI, pkg);

			// apply profile to package, if not empty
			if ((profile != null) && (!profile.getOwnedStereotypes().isEmpty())) {
				pkg.applyProfile(profile);
			}
			else {
				Activator.log.debug(String.format("Profile for URI %s is not available", profileURI)); //$NON-NLS-1$
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}
	}
}
