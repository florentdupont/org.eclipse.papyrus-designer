/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.components.transformation.ui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.IAction;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.components.FCM.util.FCMUtil;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.ui.IViewPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * TODO: for testing, currently unused
 */
@Deprecated
public class GenConnectionPatternHandler extends CmdHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		return null;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	public void init(IViewPart view) {
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		// only one model is selected
		Class selectedClass = (Class) selectedEObject;
		InteractionComponent connType = UMLUtil.getStereotypeApplication(selectedClass, InteractionComponent.class);
		FCMUtil.generateDefaultConnectionPattern(connType);
	}
}
