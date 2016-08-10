/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.validation;

import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageImport;

public class ValidationDelegateClientSelector implements IClientSelector {

	/**
	 * Queries whether to select the specified object, which indicates that it
	 * belongs to this client context.
	 *
	 * @param object
	 *            a model element of some kind
	 * @return <code>true</code> if the <code>object</code> matches my client
	 *         context; <code>false</code>, otherwise
	 */
	@Override
	public boolean selects(Object object) {
		if (object instanceof PackageImport) {
			// return false;
		}
		return (object instanceof Element);
	}
}
