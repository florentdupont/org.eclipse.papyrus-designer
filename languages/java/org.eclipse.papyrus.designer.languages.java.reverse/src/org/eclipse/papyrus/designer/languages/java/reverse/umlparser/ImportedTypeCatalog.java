/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.papyrus.designer.languages.java.reverse.exception.ImportNotFoundException;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NotFoundException;

/**
 * Catalog managing the imports names.
 * This catalog allows to lookup the qualified name of a type.
 *
 * @author dumoulin
 *
 */
public class ImportedTypeCatalog {
	
	public static final String JAVA_LIBRARY_URI = "pathmap://PapyrusJava_LIBRARIES/JavaLibrary.uml";

	private Map<String, List<String>> map = new HashMap<String, List<String>>();

	/** List of imports ending with '*'. This is a list of QName */
	private List<List<String>> starImports;
	
	/**
	 * Default mapping to be set
	 */
	public String[] defaultMappingNames = {
			"String", "java.lang.String",
			"Class", "java.lang.Class",
			"Object", "java.lang.Object",
			"Exception", "java.lang.Exception",
			"NullPointerException", "java.lang.NullPointerException",
			"Integer", "primitivetype.Integer",
			"Boolean", "primitivetype.Boolean",
			"Long", "primitivetype.Long",
			"Char", "primitivetype.Char",
			"Byte", "primitivetype.Byte",
			"Float", "primitivetype.Float",
			"Double", "primitivetype.Double",
			"Short", "primitivetype.Short",
			"Runnable", "java.lang.Runnable",
			"Throwable", "java.lang.Throwable",
			"Thread", "java.lang.Thread",
	};


	/**
	 * Constructor.
	 */
	public ImportedTypeCatalog() {
		// Set default mapping
		setDefaultMapping(defaultMappingNames);
	}

	/**
	 * Set the default mappings
	 *
	 * @param defaultMappingNames
	 */
	private void setDefaultMapping(String[] defaultMappingNames) {
		for (int i = 0; i < defaultMappingNames.length; i += 2) {
			map.put(defaultMappingNames[i], UmlUtils.toQualifiedName(defaultMappingNames[i + 1]));
			System.out.println("setDefaultMapping().add(" + defaultMappingNames[i] + ", " + UmlUtils.toQualifiedName(defaultMappingNames[i + 1]) + ")");
		}
	}

	/**
	 * Get the associated qualified name from the imports.
	 * Return the translation or the name itself, as a qualifiedName.
	 *
	 * @param shortName
	 * @return
	 */
	public List<String> getQualifiedName(String shortName) {

		List<String> res = map.get(shortName);
		if (res == null) {
			res = UmlUtils.toQualifiedName(shortName);
		}
		return res;
	}

	/**
	 * Get the qualified name of the import by its simple name (with no dot).
	 * 
	 * @param shortName the name of the searched import
	 * @return The qualified name if exist. Throw an exception otherwise
	 * 
	 * @exception NotFoundException The name has no corresponding import.
	 * @since 0.7.2
	 */
	public List<String> getImportQualifiedNameChecked(String shortName) throws ImportNotFoundException {
		List<String> res = map.get(shortName);
		if (res == null) {
			throw new ImportNotFoundException(shortName);
		}
		return res;
	}


	/**
	 * Lookup the associated qualified name from the imports.
	 *
	 * @param shortName
	 * @return The qualified name, or null if there is no matching qualified name.
	 */
	private List<String> lookupQualifiedName(String shortName) {

		List<String> res = map.get(shortName);
		return res;
	}

	/**
	 * Get the qualified name of the provided [qualifiedName].
	 * If the provided name size is one, lookup for its full name.
	 * Otherwise, return the input.
	 *
	 * @param typeQualifiedName
	 * @return
	 */
	public List<String> getQualifiedName(List<String> possiblyQualifiedName) {
		if (possiblyQualifiedName.size() == 1) {
			List<String> res = lookupQualifiedName(possiblyQualifiedName.get(0));
			if (res == null) {
				res = possiblyQualifiedName;
			}
			return res;
		} else {
			return possiblyQualifiedName;
		}
	}

	/**
	 * Return true if the specified qualifiedName denote an imported name.
	 * Return false otherwise.
	 *
	 * @param qualifiedName
	 * @return
	 */
	public boolean isImportedType(List<String> qualifiedName) {

		String lastName = qualifiedName.get(qualifiedName.size() - 1);

		// Check if the last name is in the catalog, and compare package names
		List<String> found = map.get(lastName);
		if (found != null && found.equals(qualifiedName)) {
			return true;
		}

		return false;
	}


	/**
	 * Add an import
	 *
	 * @param qualifiedName
	 */
	public void addImport(List<String> qualifiedName) {

		String lastName = qualifiedName.get(qualifiedName.size() - 1);
		System.out.println("ImportedCatalog.add(" + qualifiedName + ")");
		if ("*".equals(lastName)) {
			addStarImport(qualifiedName.subList(0, qualifiedName.size() - 1));
		} else {
			map.put(lastName, qualifiedName);
		}
	}

	/**
	 * Add an import that import all Element of a package (imports a.b.c.*;)
	 *
	 * @param qualifiedName
	 */
	public void addStarImport(List<String> qualifiedName) {

		if( starImports == null) {
			starImports = new ArrayList<List<String>>();
		}
		starImports.add(qualifiedName);
	}

	/**
	 * Clear all mappings
	 */
	public void clear() {
		map.clear();
		setDefaultMapping(defaultMappingNames);

	}

	/**
	 * @return
	 */
	public List<List<String>> getStarImports() {
		if( starImports !=null) {
			return starImports;
		}
		// empty list
		return Collections.emptyList();
	}

}
