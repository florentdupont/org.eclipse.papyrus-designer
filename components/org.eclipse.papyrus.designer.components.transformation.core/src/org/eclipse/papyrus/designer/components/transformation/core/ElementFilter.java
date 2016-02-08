package org.eclipse.papyrus.designer.components.transformation.core;

import org.eclipse.uml2.uml.Element;

public interface ElementFilter {
	public boolean acceptElement(Element element);
}
