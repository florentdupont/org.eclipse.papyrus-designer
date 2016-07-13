/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.common.base;

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.infra.tools.file.IPFileSystemAccess;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;

public class HierarchyLocationStrategy implements ILocationStrategy {

	/**
	 * Return the filename for a given named element.
	 *
	 * @param element
	 *            a named element
	 * @return filename for this element
	 */
	public String getFolder(NamedElement element) {
		// IContainer baseContainer = getFolder(project, subFolderName);
		EList<Namespace> namespaces = element.allNamespaces();
		String fileName = ""; //$NON-NLS-1$
		for (int i = namespaces.size() - 1; i >= 0; i--) {
			Namespace ns = namespaces.get(i);
			fileName += ns.getName() + IPFileSystemAccess.SEP_CHAR;
		}
		return fileName;
	}
	
	/**
	 * Return the filename for a given named element.
	 *
	 * @param element
	 *            a named element
	 * @return filename for this element
	 */
	public String getFileName(NamedElement element) {
		String folder = getFolder(element);
		if (folder.length() > 0) {
			folder += IPFileSystemAccess.SEP_CHAR;
		}
		return getFolder(element) + element.getName();
	}
}
