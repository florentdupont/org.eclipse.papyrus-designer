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
package org.eclipse.papyrus.designer.transformation.core.commands;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.designer.transformation.base.utils.StdModelLibs;
import org.eclipse.papyrus.designer.transformation.core.Activator;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Profile;

/**
 * This class adds the FCM profile, parts of the MARTE profile and required package imports to your model.
 */
public class AddMarteAndFcmProfile extends RecordingCommand {

	public AddMarteAndFcmProfile(Package selectedPkg, int applyCode, TransactionalEditingDomain domain) {
		super(domain, CMD_LABEL);
		this.selectedPkg = selectedPkg;
		this.applyCode = applyCode;
	}

	public static final String CMD_LABEL = "Add MARTE/FCM profiles"; //$NON-NLS-1$

	static final String MARTE_FOUNDATIONS = "MARTE_Foundations"; //$NON-NLS-1$

	static final String MARTE_F_ALLOC = "Alloc"; //$NON-NLS-1$

	static final String MARTE_DESIGN_MODEL = "MARTE_DesignModel"; //$NON-NLS-1$

	static final String MARTE_DM_HLAM = "HLAM"; //$NON-NLS-1$

	static final String MARTE_DM_GCM = "GCM"; //$NON-NLS-1$

	public static final int APPLY_FCM = 1;

	public static final int APPLY_ALLOC = 2;

	public static final int APPLY_HLAM_GCM = 4;

	Package selectedPkg;

	int applyCode;

	@Override
	protected void doExecute() {
		final ResourceSet resourceSet = selectedPkg.eResource().getResourceSet();

		try {
			if ((applyCode & APPLY_FCM) != 0) {
				// Retrieve FCM profile
				Profile fcmProfile = (Profile) StdModelLibs.addResource(StdModelLibs.FCM_PROFILE_URI, resourceSet);

				// Apply FCM profile and its nested profiles to new model
				if (fcmProfile instanceof Profile) {
					Profile profile = selectedPkg.getAppliedProfile(fcmProfile.getQualifiedName());
					if ((profile == null) && (!fcmProfile.getOwnedStereotypes().isEmpty())) {
						selectedPkg.applyProfile(fcmProfile);
					}
				}
				else {
					Activator.log.debug("The FCM profile is not available."); //$NON-NLS-1$
				}
			}

			if ((applyCode & (APPLY_ALLOC | APPLY_HLAM_GCM)) != 0) {
				// Retrieve MARTE profile
				Profile marteProfile = (Profile) StdModelLibs.addResource(StdModelLibs.MARTE_PROFILE_URI, resourceSet);

				// Apply MARTE::MARTE_DesignModel::HLAM
				// & MARTE::MARTE_DesignModel::GCM
				if (marteProfile != null) {
					PackageableElement foundationModel = marteProfile.getPackagedElement(MARTE_FOUNDATIONS);
					if ((foundationModel instanceof Package) && ((applyCode & APPLY_ALLOC) != 0)) {
						PackageableElement alloc = ((Package) foundationModel).getPackagedElement(MARTE_F_ALLOC);
						selectedPkg.applyProfile((Profile) alloc);
					}
					PackageableElement designModel = marteProfile.getPackagedElement(MARTE_DESIGN_MODEL);
					if ((designModel instanceof Package) && ((applyCode & APPLY_HLAM_GCM) != 0)) {
						PackageableElement hlam = ((Package) designModel).getPackagedElement(MARTE_DM_HLAM);
						if (hlam instanceof Profile) {
							selectedPkg.applyProfile((Profile) hlam);
						}
						PackageableElement gcm = ((Package) designModel).getPackagedElement(MARTE_DM_GCM);
						if (gcm instanceof Profile) {
							selectedPkg.applyProfile((Profile) gcm);
						}
					}
				}
				else {
					Activator.log.debug("The MARTE profile is not available."); //$NON-NLS-1$
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
