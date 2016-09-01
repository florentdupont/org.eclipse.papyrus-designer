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

package org.eclipse.papyrus.designer.transformation.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;


/**
 * Support for embedding text templates
 */
public class TextTemplateExt {

	public static final String ITEMPLATE_ID = Activator.PLUGIN_ID + ".textTemplate"; //$NON-NLS-1$

	public static ITextTemplate getTextTemplate(String templateID) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(ITEMPLATE_ID);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String iTemplateID = configElement.getAttribute("templateID"); //$NON-NLS-1$
				if (iTemplateID.equals(templateID)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof ITextTemplate) {
						return (ITextTemplate) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}
}
