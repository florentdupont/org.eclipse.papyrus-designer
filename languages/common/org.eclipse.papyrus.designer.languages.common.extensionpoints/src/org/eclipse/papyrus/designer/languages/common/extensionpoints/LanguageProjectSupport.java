/*******************************************************************************
 * Copyright (c) 2015 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - ansgar.radermacher@cea.fr   initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.extensionpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * Support for multiple target languages via the Eclipse extension mechanism
 */
public class LanguageProjectSupport {

	public static final String ILANG_SUPPORT_ID = Activator.PLUGIN_ID + ".languageProjectSupport"; //$NON-NLS-1$

	/**
	 * Get the project support for a given language editor.
	 * 
	 * @param language
	 *            a programming language
	 * @return the project support
	 */
	public static ILangProjectSupport getProjectSupport(String language) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(ILANG_SUPPORT_ID);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String extLanguage = configElement.getAttribute("language"); //$NON-NLS-1$
				if (extLanguage.equals(language)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof ILangProjectSupport) {
						return (ILangProjectSupport) obj;
					}
				}
			} catch (CoreException exception) {
				Activator.getDefault().getLog().log(
						new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.LanguageSupport_LanguageNotSupported, exception));
			}
		}
		throw new RuntimeException(String.format(Messages.LanguageSupport_LanguageNotSupported, language));
	}
}
