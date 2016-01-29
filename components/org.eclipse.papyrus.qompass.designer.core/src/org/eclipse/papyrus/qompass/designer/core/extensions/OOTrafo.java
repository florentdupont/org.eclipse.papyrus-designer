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

package org.eclipse.papyrus.qompass.designer.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.papyrus.qompass.designer.core.Activator;
import org.eclipse.papyrus.qompass.designer.core.Messages;

/**
 * Support for different OO transformation via the Eclipse extension
 * mechanism
 */
public class OOTrafo {

	public static final String IOO_TRANSFORMATION_ID = Activator.PLUGIN_ID + ".ooTransformation"; //$NON-NLS-1$

	/**
	 * Get an OOTransformation for a given ID (language)
	 * 
	 * @param iOOTrafoID
	 *            the ID of an OO Transformation
	 * @return
	 */
	public static IOOTrafo getOOTrafo(String iOOTrafoID) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(IOO_TRANSFORMATION_ID);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String iOOTrafoIDext = configElement.getAttribute("ooTrafoID"); //$NON-NLS-1$
				if (iOOTrafoIDext == null) {
					throw new RuntimeException(String.format(Messages.InstanceConfigurator_InvalidPluginExtension,
							iOOTrafoID));
				}
				if (iOOTrafoIDext.endsWith("." + iOOTrafoID)) { //$NON-NLS-1$
					// TODO: cache returned instance (avoid creating a new
					// instance each time => more efficient, no need for static
					// attributes)
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof IOOTrafo) {
						return (IOOTrafo) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}
}
