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
 * Support for embedding xtend templates
 */
public class XtendGenerator {

	public static final String ITEMPLATE_ID = Activator.PLUGIN_ID + ".xtendGenerator"; //$NON-NLS-1$

	public static IXtend getXtendGenerator(String generatorID) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(ITEMPLATE_ID);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String iTemplateID = configElement.getAttribute("templateID"); //$NON-NLS-1$
				if (iTemplateID.equals(generatorID)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof IXtend) {
						return (IXtend) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}
}
