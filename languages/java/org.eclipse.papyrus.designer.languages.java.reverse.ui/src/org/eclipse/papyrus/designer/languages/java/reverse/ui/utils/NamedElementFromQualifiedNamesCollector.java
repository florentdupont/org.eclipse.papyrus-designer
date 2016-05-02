/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
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

package org.eclipse.papyrus.designer.languages.java.reverse.ui.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ClassifierCatalog;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

/**
 * this class allows to get the {@link NamedElement} by their qualifiednames.
 * NamedElement are searched from a root {@link Model}, using the searchpaths.
 * 
 * 
 * @author cedric dumoulin
 *
 */
public class NamedElementFromQualifiedNamesCollector {

	/**
	 * Catalog used to lookup {@link NamedElement}
	 */
	private ClassifierCatalog catalog;

	/**
	 * Constructor.
	 *
	 * @param rootPackage
	 * @param searchPaths
	 */
	protected NamedElementFromQualifiedNamesCollector(Package rootPackage, List<String> searchPaths) {
		
		this.catalog  = new ClassifierCatalog(rootPackage, searchPaths);
	}

	/**
	 * @param names
	 * @param rootPackage
	 * @param searchPaths
	 * @return
	 */
	public static List<NamedElement> collectNamedElementsFromQualifiedNames(List<String> names, Package rootPackage, List<String> searchPaths) {
		return new NamedElementFromQualifiedNamesCollector( rootPackage, searchPaths).getNamedElement(names);
	}

	/**
	 * Get the {@link NamedElement} from the specified qualified names. If a {@link NamedElement} is not found for a given name, 
	 * nothing is added to the result.
	 * 
	 * @param names A collection of qualified name
	 * @return A list of found elements.
	 */
	private List<NamedElement> getNamedElement(List<String> names) {


		List<NamedElement> res = new ArrayList<NamedElement>();
		
		// Walk all names
		for( String qname : names) {
			NamedElement ele = catalog.getClassifier(qname);
			if(ele != null) {
				res.add(ele);
			}
		}
		return res;
	}

	
	
}
