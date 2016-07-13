/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.transformation.core.templates;

import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Wrap different ways to bind a template. This may be either a reference to an
 * Xtend template or a specific name template.
 */
public class TextTemplateBinding {

	/**
	 * Simple template expression that is replaced with the name of a named
	 * element. Uses an Acceleo syntax
	 * The objective is that the access to an element name is very common and does
	 * not need an xtend script behind.
	 */
	private static final String NAME_IN_BRACKETS = "[name/]"; //$NON-NLS-1$

	/**
	 * Simple template expression that is replaced with the name of a named
	 * element. Slight variation of the Acceleo syntax
	 */
	private static final String NAME_IN_BRACKETS_SIMPLE = "[name]"; //$NON-NLS-1$

	/**
	 * Execute a script passed as parameter. It will be executed in the context
	 * of a base script that declares the meta-model (UML2) and has additional
	 * imports (currently none).
	 *
	 * @param template
	 *            The template in string form
	 * @param element
	 *            The UML element to which the template is applied
	 * @return the evaluated template
	 */
	public static String bind(String templateStr, Element element) throws TransformationException {
		return bind(templateStr, element, null);
	}

	/**
	 * Execute a script passed as parameter. It will be executed in the context
	 * of a base script that declares the meta-model (UML2). It allows for
	 * additional arguments
	 *
	 * @param template
	 *            The template in string form
	 * @param element
	 *            The UML element to which the template is applied
	 * @return the evaluated template
	 */
	public static String bind(String templateStr, Element element, Object args[]) throws TransformationException {
		if (templateStr.startsWith(XtendTemplateBinding.XTEND_MAGIC)) {
			return XtendTemplateBinding.bind(templateStr, element, args);
		}
		else if (element instanceof NamedElement) {
			String name = ((NamedElement) element).getName();
			if (templateStr.contains(NAME_IN_BRACKETS)) {
				return templateStr.replace(NAME_IN_BRACKETS, name);
			}
			else if (templateStr.contains(NAME_IN_BRACKETS_SIMPLE)) {
				return templateStr.replace(NAME_IN_BRACKETS_SIMPLE, name);
			}
		}
		return templateStr;
		// throw new TransformationException("Template is not supported"); //$NON-NLS-1$
	}
}
