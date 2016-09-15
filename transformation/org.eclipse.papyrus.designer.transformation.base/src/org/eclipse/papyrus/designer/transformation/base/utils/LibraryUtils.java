package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.UMLFactory;

public class LibraryUtils {
	
	/**
	 * Return the first top-level element of a model
	 * @param resource
	 * @return
	 */
	public static Element getContent(Resource resource) {
		EList<EObject> contentObj = resource.getContents();
		if ((contentObj.size() > 0) && (contentObj.get(0) instanceof Element)) {
			return (Element) contentObj.get(0);
		}
		return null;
	}
	
	/**
	 * Get the first top-level element of an element stored in the
	 * modle with a given URI (and loaded into a specified resource set)
	 * @param uri
	 * @param rs
	 * @return
	 */
	public static Element getContent(URI uri, ResourceSet rs) {
		Resource resource = rs.getResource(uri, true);
		return getContent(resource);
	}
	
	/**
	 * Retrieve a model library from the repository
	 *
	 * @param uri
	 *            the URI of the repository
	 *
	 * @return
	 */
	public static PackageImport getModelLibraryImportFromURI(URI uri, ResourceSet resourceSet) {
		// Try to reach model
		Element root = getContent(uri, resourceSet);
		if (root instanceof Package) {
			// Import model library
			Package libToImport = (Package) root;
			// create import package
			PackageImport modelLibImport = UMLFactory.eINSTANCE.createPackageImport();
			modelLibImport.setImportedPackage(libToImport);

			return modelLibImport;
		}
		return null;
	}
}
