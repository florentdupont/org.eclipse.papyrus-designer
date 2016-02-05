/*****************************************************************************
 * Copyright (c) 2015 CEA
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher - ansgar.radermacher@cea.fr - Initial API and implementation
 *    (derived from DiagramAdapterFactory)
 *
 *****************************************************************************/
package org.eclipse.papyrus.texteditor.modelexplorer.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.IOpenable;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.IOpenableWithContainer;
import org.eclipse.papyrus.languages.designer.common.texteditor.model.TextEditorModel;

@SuppressWarnings("rawtypes")
public class TextEditorAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IOpenable.class) {
			if (adaptableObject instanceof TextEditorModel) {
				TextEditorModel textEditorModel = (TextEditorModel) adaptableObject;
				return new IOpenableWithContainer.Openable(adaptableObject, textEditorModel.getEditedObject());
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] { IOpenable.class };
	}
}
