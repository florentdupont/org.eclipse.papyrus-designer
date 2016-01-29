/*****************************************************************************
 * Copyright (c) 2014 Jonathan Geoffroy.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Jonathan Geoffroy	geoffroy.jonathan@gmail.com		- initial implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.java.reverse.ui.dnd;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.DropStrategy;
import org.eclipse.swt.graphics.Image;


public class DropReverseStrategy implements DropStrategy {
	public DropReverseStrategy() {
	}

	@Override
	public String getLabel() {
		return Messages.DropReverseStrategy_LABEL;
	}

	@Override
	public String getDescription() {
		return Messages.DropReverseStrategy_DESCRIPTION;
	}

	@Override
	public Image getImage() {
		// No image to display
		return null;
	}

	@Override
	public String getID() {
		return "org.eclipse.papyrus.java.reverse.ui.dnd.reversedropstrategy"; //$NON-NLS-1$
	}

	@Override
	public Command getCommand(final Request request, final EditPart targetEditPart) {
		return DropReverseCommand.getInstance(request);

	}

	@Override
	public int getPriority() {
		return 0;
	}

}
