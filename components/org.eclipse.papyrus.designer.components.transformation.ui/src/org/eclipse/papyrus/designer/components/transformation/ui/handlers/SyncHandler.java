/*****************************************************************************
 * Copyright (c) 2017 CEA LIST.
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

package org.eclipse.papyrus.designer.components.transformation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.papyrus.designer.components.transformation.sync.CompImplSync;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.uml2.uml.Class;

/**
 * Handler for synchronizing derived elements. Specialization for components (update of ports)
 */
public class SyncHandler extends CmdHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		// if a property is selected, use the associated type
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
		// if a property is selected, use the associated type
		if (selectedEObject instanceof Class) {
			final Class selectedClass = (Class) selectedEObject;
			CommandSupport.exec("Synchronize class (components)", event, new Runnable() {

				@Override
				public void run() {
					CompImplSync.updatePorts(selectedClass);
					CompImplSync.syncRealizations(selectedClass);
				}
			});
		}
		return null;
	}
}
