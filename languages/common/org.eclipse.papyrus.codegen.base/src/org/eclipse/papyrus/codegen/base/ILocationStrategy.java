package org.eclipse.papyrus.codegen.base;

import org.eclipse.uml2.uml.NamedElement;

/**
 * A location strategy defines the location of a file associated with a model element
 * depending on the namespaces of this model element (package hierarchy).
 */
public interface ILocationStrategy {
	
	public String getFolder(NamedElement element);

	public String getFileName(NamedElement element);
}
