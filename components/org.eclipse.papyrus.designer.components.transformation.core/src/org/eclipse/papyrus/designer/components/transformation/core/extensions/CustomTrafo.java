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

package org.eclipse.papyrus.designer.components.transformation.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.papyrus.designer.components.transformation.core.Activator;


/**
 * Support for using customized transformations
 */
public class CustomTrafo {

	public static final String ICUSTOM_TRAFO_ID = Activator.PLUGIN_ID + ".customTrafo"; //$NON-NLS-1$

	public static AbstractContainerTrafo getCustomTrafo(String transformationID) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(ICUSTOM_TRAFO_ID);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String iTemplateID = configElement.getAttribute("transformationID"); //$NON-NLS-1$
				if (iTemplateID.equals(transformationID)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof AbstractContainerTrafo) {
						return (AbstractContainerTrafo) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}
}
