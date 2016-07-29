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

package org.eclipse.papyrus.designer.transformation.ui.dialogs;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.papyrus.designer.transformation.core.deployment.ImplementationChooser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;

public class InteractiveImplementationChooser implements ImplementationChooser {

	@Override
	public Class chooseImplementation(Class componentType, EList<Class> implList) {
		// SelectionDialog = SelectionDialog.
		ILabelProvider ilabel = new LabelProvider() {

			@Override
			public String getText(Object element) {
				return ((NamedElement) element).getQualifiedName();
			}
		};
		ElementListSelectionDialog dialog =
				new ElementListSelectionDialog(Display.getDefault().getActiveShell(), ilabel);

		dialog.setTitle("Multiple implementations found");
		dialog.setMessage("Select an implementation for component type " + componentType.getName());

		dialog.setElements(implList.toArray());

		dialog.open();
		Object[] selection = dialog.getResult();
		if (selection.length == 1) {
			if (selection[0] instanceof Class) {
				return (Class) selection[0];
			}
		}
		return null;
	}
}
