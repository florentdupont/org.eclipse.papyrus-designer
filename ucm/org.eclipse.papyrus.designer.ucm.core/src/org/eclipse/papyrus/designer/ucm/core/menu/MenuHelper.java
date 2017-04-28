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

package org.eclipse.papyrus.designer.ucm.core.menu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.designer.ucm.core.provider.UCMLabelProvider;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.uml2.uml.Type;

/**
 * Simplify the creation of an enhanced popup menu
 */
public class MenuHelper {
	
	public static final String CREATE_NEW_TYPE = "Create new type"; //$NON-NLS-1$

	/**
	 * Create a list of menu items based on the result content provider
	 * @param cp a content provider
	 * @param title the title of the dialog
	 * @param allowNew if true, allow for the creation of a new type
	 * @return
	 */
	public static List<Object> createMenuItems(IStaticContentProvider cp, String title, boolean allowNew) {
		List<Object> menuItems = new ArrayList<Object>();

		menuItems.add(new EnhancedPopupMenu.Disabled(title));
		menuItems.add(new EnhancedPopupMenu.Separator());
		if (allowNew) {
			menuItems.add(CREATE_NEW_TYPE);
		}
		if (cp.getElements().length > 0) {
			if (cp.getElements().length < 5) {
				if (cp.getElements()[0] instanceof Type) {
					// display short info text, if the first element (and presumably the following)
					// are UML types
					menuItems.add(new EnhancedPopupMenu.Disabled("Existing types"));
				}

				for (Object element : cp.getElements()) {
					menuItems.add(element);
				}
			} else {
				menuItems.add(new EnhancedPopupMenu.SubSelect("Select existing type", cp));
			}
		}
		return menuItems;
	}

	public static EnhancedPopupMenu createPopupMenu(IStaticContentProvider cp, String title, boolean allowNew) {

		return new EnhancedPopupMenu(createMenuItems(cp, title, allowNew), new UCMLabelProvider());
	}

}
