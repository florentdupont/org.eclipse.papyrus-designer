/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.transformation.base.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;

public class FileUtils {

	public static String decodeID(String encodedURI) {
		String result = ""; //$NON-NLS-1$
		for (int i = 0; i < encodedURI.length(); i++) {
			char c = encodedURI.charAt(i);
			if (c == '_') {
				char next = encodedURI.charAt(i + 1);
				if (next == 'M') {
					result += '-';
				} else if (next == '_') {
					result += '_';
				}
				i++;
			}
			else {
				result += c;
			}
		}
		return result;
	}

	public static String encodeID(String uri) {
		// _ becomes escape character. original _ is __, '-' becomes _M
		return uri.
				replace(StringConstants.UNDERSCORE, StringConstants.UNDERSCORE + StringConstants.UNDERSCORE).
				replace("-", "_M"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Get the URI fragment of an element within your model
	 * Useful for transmitting model references
	 *
	 * @param element
	 *            a UML element
	 * @return
	 */
	public static String fragment(Element element) {
		Resource resource = element.eResource();
		// TODO: use EcoreUtil getURI (InternalEObject) instead?
	
		if (resource instanceof XMLResource) {
			XMLResource xmlResource = (XMLResource) resource;
			return "\"" + xmlResource.getURIFragment(element) + "\""; //$NON-NLS-1$//$NON-NLS-2$
		}
		return null;
	}

	/**
	 * Return the absolute file name to a file name given e.g. in the form platform:/plugin/xxx
	 *
	 * @param fileName
	 *            the file name using eclipse elements such as platform:/plugin
	 * @return the absolute file name
	 */
	public static String getAbsoluteFN(String fileName) {
		try {
			URL absoluteURL = FileLocator.toFileURL(new URL(fileName));
			return absoluteURL.getFile();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return null;
	}

	public static String getURI(Element element) {
		Resource resource = element.eResource();
		if (resource != null) {
			URI uri = resource.getURI();
			return uri.toString();
		}
		return null;
	}

	/**
	 * Return a sequence of namespaces for a given element, starting from the "bottom"
	 * one, i.e. the one in which the element is contained. It will end before the
	 * searchNS namespace is reached. Returns null, if the element is not contained
	 * within the search namespace.
	 *
	 * @param element
	 * @param searchNS
	 * @return
	 */
	public static EList<Namespace> relativePath(Element element, Namespace searchNS) {
		EList<Namespace> pathList = new BasicEList<Namespace>();
		Element owner = element.getOwner();
		if (!(owner instanceof Namespace)) {
			// happens, if element is contained in a template signature
			return null;
		}
		Namespace ns = (Namespace) owner;
		while (ns != null) {
			if (ns == searchNS) {
				return pathList;
			}
			pathList.add(ns);
	
			ns = (Namespace) ns.getOwner();
		}
		return null;
	}

}
