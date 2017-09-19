/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.common.base;

import org.eclipse.uml2.uml.NamedElement;

/**
 * Collection of utility functions around strings
 */
public class StringUtils {
	/**
	 * Put quotes around a string, unless string already starts with a quote.
	 *
	 * @param str
	 * @return
	 */
	public static String quoteString(String str) {
		if (str.startsWith(StringConstants.QUOTE)) {
			return str;
		} else {
			return StringConstants.QUOTE + str + StringConstants.QUOTE;
		}
	}

	/**
	 * A small helper function that makes names compliant with variable
	 * names in programming languages such as C++ or Java
	 * Unlike varName2, replace scoping signs as well
	 */
	public static String varName(String umlName) {
		umlName = umlName.replace(".", StringConstants.UNDERSCORE); //$NON-NLS-1$
		umlName = umlName.replace(NamedElement.SEPARATOR, StringConstants.UNDERSCORE);
		return varName2(umlName);
	}

	/**
	 * A small helper function that makes names compliant with variable
	 * names in programming languages such as C++ or Java
	 * TODO: obviously, it is not complete (e.g. in case of "$", national characters ("ä", "é", ...) , ...)
	 */
	public static String varName2(NamedElement element) {
		String umlName = element.getName();
		return varName2(umlName);
	}

	/**
	 * Like varName, but does not replace "." with "_"
	 *
	 * @param umlName
	 * @return
	 */
	public static String varName2(String umlName) {
		umlName = umlName.replace(" ", StringConstants.UNDERSCORE); //$NON-NLS-1$
		umlName = umlName.replace("-", StringConstants.UNDERSCORE); //$NON-NLS-1$
		umlName = umlName.replace("+", StringConstants.UNDERSCORE); //$NON-NLS-1$
		umlName = umlName.replace("?", StringConstants.UNDERSCORE); //$NON-NLS-1$
		return umlName;
	}

}
