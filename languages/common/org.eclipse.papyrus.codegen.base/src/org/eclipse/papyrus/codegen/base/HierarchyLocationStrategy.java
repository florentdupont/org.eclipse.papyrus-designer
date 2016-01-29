package org.eclipse.papyrus.codegen.base;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;

public class HierarchyLocationStrategy implements ILocationStrategy {
	
	/**
	 * Always use / instead of File.separationChar 
	 */
	public static final String SEP_CHAR = "/"; //$NON-NLS-1$

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
			fileName += ns.getName() + SEP_CHAR;
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
			folder += SEP_CHAR;
		}
		return getFolder(element) + element.getName();
	}
}
