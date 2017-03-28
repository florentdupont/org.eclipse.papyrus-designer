/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
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

package org.eclipse.papyrus.designer.ucm.diagrams.commands;

import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.papyrus.infra.viewpoints.policy.ViewPrototype;
import org.eclipse.papyrus.uml.diagram.composite.CreateCompositeDiagramCommand;

/**
 * Create UCM component diagram command
 */
public class CreateUCMComponentDiagramCommand extends CreateCompositeDiagramCommand {

	/**
	 * UCM component diagram type.
	 */
	public static final String DIAGRAM_TYPE = "UCMComponentDiagram"; //$NON-NLS-1$

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.common.AbstractPapyrusGmfCreateDiagramCommandHandler#getCreatedDiagramType()
	 *
	 * @return
	 */
	@Override
	public String getCreatedDiagramType() {
		return DIAGRAM_TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CommandResult doEditDiagramName(ViewPrototype prototype, String name) {
		// would override dialog creation to edit the name, not done right now in UCM, i.e. keep name dialog
		// return CommandResult.newOKCommandResult(null);
		return super.doEditDiagramName(prototype, name);
	}

}
