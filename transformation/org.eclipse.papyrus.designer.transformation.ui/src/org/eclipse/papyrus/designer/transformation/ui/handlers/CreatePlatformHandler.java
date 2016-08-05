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

package org.eclipse.papyrus.designer.transformation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.designer.deployment.tools.DepCreation;
import org.eclipse.papyrus.designer.deployment.tools.DeployConstants;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationRTException;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtil;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

public class CreatePlatformHandler extends CmdHandler {

	private Package platform;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		if (selectedEObject instanceof Class) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (!(selectedEObject instanceof Class)) {
			return null;
		}
		final Class selectedComposite = (Class) selectedEObject;

		CommandSupport.exec("Create platform model", event, new Runnable() { //$NON-NLS-1$

					@Override
					public void run() {
						// execute with transaction support
						platform = ElementUtil.getRoot(selectedComposite, DeployConstants.depPlanFolderHw);
					}
				});

		final String newPlatform = selectedComposite.getName() + DeployConstants.DepPlanPostfixHw;

		try {
			if (platform.getMember(newPlatform) != null) {
				Shell shell = Display.getDefault().getActiveShell();
				MessageDialog.openInformation(shell, "Error", //$NON-NLS-1$
						"Platform definition \"" + newPlatform + "\" exists already"); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				CommandSupport.exec("Create platform definition", event, new Runnable() { //$NON-NLS-1$

							@Override
							public void run() {
								Package platformPkg = platform.createNestedPackage(newPlatform);
								try {
									DepCreation.createPlatformInstances(platformPkg, selectedComposite, null);
								}
								catch (TransformationException e) {
									throw new TransformationRTException(e.getMessage());
								}
							}
						});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
