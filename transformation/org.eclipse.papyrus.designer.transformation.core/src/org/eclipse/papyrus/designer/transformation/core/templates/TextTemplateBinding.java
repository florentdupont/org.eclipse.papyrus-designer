/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    CEA LIST - initial API and implementation
 */

package org.eclipse.papyrus.designer.transformation.core.templates;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.extensions.ITextTemplate;
import org.eclipse.papyrus.designer.transformation.extensions.TextTemplateExt;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Support for binding/instantiation of text templates
 */
public class TextTemplateBinding {

	public static final String TEMPLATE_MAGIC = "!template"; //$NON-NLS-1$

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
		if (templateStr.startsWith(TEMPLATE_MAGIC)) {
			return bindTemplate(templateStr, element, args);
		}
		else if (element instanceof NamedElement) {
			// simple replacement of specific template entry [name]
			String name = ((NamedElement) element).getName();
			if (templateStr.contains(NAME_IN_BRACKETS)) {
				return templateStr.replace(NAME_IN_BRACKETS, name);
			}
			else if (templateStr.contains(NAME_IN_BRACKETS_SIMPLE)) {
				return templateStr.replace(NAME_IN_BRACKETS_SIMPLE, name);
			}
		}
		return templateStr;
	}

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
	public static String bindTemplate(String templateStr, Element element, Object args[]) throws TransformationException {
		String[] templateRef = templateStr.substring(TEMPLATE_MAGIC.length() + 1).split("\\."); //$NON-NLS-1$
		String templateId = templateRef[0].trim();
		String methodName = templateRef[1].trim();
		ITextTemplate generator = TextTemplateExt.getTextTemplate(templateId);
		if (generator == null) {
			throw new TransformationException(String.format(Messages.TextTemplateBinding_TemplateNotFound, templateId, methodName));
		}
		
		try {
			Object result;
			if (args != null) {
				Method method = getMethod(generator, methodName, 1+args.length);
				result = method.invoke(generator, element, args);
			}
			else {
				Method method = getMethod(generator, methodName, 1);
				result = method.invoke(generator, element);
			}
			if (result instanceof String) {
				return (String) result;
			}
			else if (result instanceof CharSequence) {
				return result.toString();
			}
			else {
				throw new TransformationException(Messages.TextTemplateBinding_TemplateResultIsNotAString);
			}
		} catch (SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | NullPointerException e) {
			throw new RuntimeException(e);
		}
	}

	public static Method getMethod(ITextTemplate generator, String methodName, int numberOfArguments) {
		for (Method method : generator.getClass().getMethods()) {
			if (method.getName().equals(methodName) && method.getParameterTypes().length == numberOfArguments) {
				return method;
			}
		}
		return null;
	}
}
