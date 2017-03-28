/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Inspired from similar class by Christian W. Damus
 *    Ansgar Radermacher - additional sub-menu functions
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gmf.runtime.diagram.ui.menus.PopupMenu;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.widgets.editors.TreeSelectorDialog;
import org.eclipse.papyrus.infra.widgets.providers.EncapsulatedContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * A specialized {@link PopupMenu} that supports the insertion of
 * (1) {@linkplain Separator separators} into the menu.
 * (2) Disabled elements
 * (3) Sub-menu selections
 */
public class EnhancedPopupMenu extends PopupMenu {
	/**
	 * Initializes me with my menu contents (including, potentially, {@link Separator}s)
	 * and a label provider.
	 */
	public EnhancedPopupMenu(List<?> aContent, ILabelProvider aLabelProvider) {
		super(aContent, aLabelProvider);
	}

	@Override
	protected void createMenuItems(Menu parentMenu, PopupMenu rootMenu,
			@SuppressWarnings("rawtypes") final List resultThusFar) {

		Assert.isNotNull(getContent());
		Assert.isNotNull(getLabelProvider());

		EnhancedPopupMenu rootMenuWithSeparators = (EnhancedPopupMenu) rootMenu;
		for (Iterator<?> iter = getContent().iterator(); iter.hasNext();) {
			Object contentObject = iter.next();

			MenuItem menuItem;

			if (contentObject instanceof CascadingMenu) {
				EnhancedPopupMenu subMenu = (EnhancedPopupMenu) ((CascadingMenu) contentObject)
						.getSubMenu();
				contentObject = ((CascadingMenu) contentObject)
						.getParentMenuItem();

				@SuppressWarnings("unchecked")
				List<Object> thisResult = new ArrayList<>(resultThusFar);
				thisResult.add(contentObject);
				menuItem = new MenuItem(parentMenu, SWT.CASCADE);
				menuItem.setMenu(new Menu(parentMenu));

				subMenu.createMenuItems(menuItem.getMenu(), rootMenu,
						thisResult);
			} else if (contentObject instanceof Separator) {
				menuItem = new MenuItem(parentMenu, SWT.SEPARATOR);
			} else {
				menuItem = new MenuItem(parentMenu, SWT.NONE);
			}
			if (contentObject instanceof Disabled) {
				contentObject = ((Disabled) contentObject).content;
				menuItem.setEnabled(false);
			}
		
			if (!(contentObject instanceof Separator)) {
				Object selection = contentObject;
				if (contentObject instanceof SubSelect) {
					menuItem.setText(((SubSelect) contentObject).text);
				}
				else {
					menuItem.setText(getLabelProvider().getText(selection));
					menuItem.setImage(getLabelProvider().getImage(selection));
				}
				menuItem.addSelectionListener(new SelectionListener() {

					@SuppressWarnings("unchecked")
					@Override
					public void widgetSelected(SelectionEvent e) {
						resultThusFar.add(selection);
						rootMenuWithSeparators.setResult(resultThusFar);
					}

					@SuppressWarnings("unchecked")
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						resultThusFar.add(selection);
						rootMenuWithSeparators.setResult(resultThusFar);
					}
				});
			}
		}
	}

	//
	// Nested types
	//

	/**
	 * In a similar fashion to the {@link PopupMenu.CascadingMenu}, an object
	 * that can be added to the menu in order to create a separator, which
	 * is not an actionable item.
	 */
	public static class Separator {
	}
	
	/**
	 * In a similar fashion to the {@link PopupMenu.CascadingMenu}, an object
	 * that can be added to the menu in order to create a separator, which
	 * is not an actionable item.
	 */
	public static class Disabled {
		public Disabled(Object content) {
			this.content = content;
		}
		public Object content;
	}
	
	/**
	 * In a similar fashion to the {@link PopupMenu.CascadingMenu}, an object
	 * that will imply a sub-selection in a new box
	 */
	public static class SubSelect {
		public SubSelect(String text, IStructuredContentProvider contentProvider) {
			this.text = text;
			this.contentProvider = contentProvider;
		}
		public String text;
		public IStructuredContentProvider contentProvider;
	}
	
	/**
	 * Get a result that takes possible sub-selection into account.
	 * @return the original result or the result of a new selection in a sub-dialog
	 */
	public Object getSubResult() {
		Object result = getResult();
		if (result instanceof SubSelect) {
			SubSelect subSel = (SubSelect) result;
			TreeSelectorDialog dialog = new TreeSelectorDialog(Display.getDefault().getActiveShell());
			dialog.setLabelProvider(getLabelProvider());
			dialog.setContentProvider(new EncapsulatedContentProvider(subSel.contentProvider));
			dialog.setTitle(subSel.text);
			int code = dialog.open();
			if (code == Window.OK) {
				// TreeSelectorDialog returns an array.
				return dialog.getResult()[0];
			}
			return null;
		}
		else {
			return result;
		}
	}
}
