/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.designer.languages.java.reverse.Activator;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Utility methods.
 * <ul>
 * <li>getXxx() methods lookup for the element and create it if not found.</li>
 * <li>lookup() methods lookup for the element.</li>
 * <li>xxx( Resource, ...) methods lookup from the model container.</li>
 * <li>xxx( Package, ...) methods lookup from the specified package?</li>
 * </ul>
 *
 * The following methods can be used:
 * <ul>
 * <li>package.getPackagedElement( name, b, type, createOnDemand) : PackageableElement</li>
 * <li>package.getNestPacke()</li>
 * <li>package.getOwnedType(name, b, type, createOnDemand) : Type</li>
 * </ul>
 *
 * @author dumoulin
 *
 */
public class UmlUtils {

	
	public static final EClass CLASS_TYPE = UMLPackage.eINSTANCE.getClass_();
	public static final EClass INTERFACE_TYPE = UMLPackage.eINSTANCE.getInterface();
	public static final EClass CLASSIFIER_TYPE = UMLPackage.eINSTANCE.getClassifier();
	public static final EClass ENUM_TYPE = UMLPackage.eINSTANCE.getEnumeration();
	
	private static final String WILDCARD = "*";

	/**
	 * Get the qualified name from a name.
	 * Name is splitted arround '.'
	 *
	 * @param name
	 * @return
	 */
	public static List<String> toQualifiedName(String qname) {
		String[] splittedName = qname.split("\\.");
		return Arrays.asList(splittedName);
	}

	/**
	 * Get the qualified name from a name.
	 * Name is splitted arround '/'
	 *
	 * @param name
	 * @return
	 */
	public static List<String> slashNameToQualifiedName(String qname) {
		String[] splittedName = qname.split("/");
		return Arrays.asList(splittedName);
	}

	/**
	 * Lookup for the specified package in the parent package. Create it if not found.
	 *
	 * @param parent
	 *            the containing package
	 * @param name
	 *            Package to get
	 * @return The requested package (never null)
	 */
	public static Package getPackage(Package parent, String name) {
		// Get or create an instance of the specified package.
		Package p = (Package) parent.getPackagedElement(name, false, UMLPackage.eINSTANCE.getPackage(), true);
		return p;
	}

	/**
	 * Lookup for the specified package in the parent package. Create it if not found.
	 *
	 * @param parent
	 *            the containing package
	 * @param name
	 *            Package to get
	 * @return The requested package (never null)
	 */
	public static Package getModel(Package parent, String name) {

		Package p = lookupPackage(parent, name);
		if (p == null)
		{
			// Create as model
			p = (Package) parent.getPackagedElement(name, false, UMLPackage.eINSTANCE.getModel(), true);
		}
		return p;
	}

	/**
	 * Lookup for the specified package in the parent package. Do not create it.
	 *
	 * @param parent
	 *            the containing package
	 * @param name
	 *            Package to get
	 * @return The requested package (never null)
	 */
	private static Package lookupPackage(Package parent, String name) {
		// Get or create an instance of the specified package.
		Package p = (Package) parent.getPackagedElement(name, false, UMLPackage.eINSTANCE.getPackage(), false);
		return p;
	}

	/**
	 * Lookup for the specified Namespace in the parent Namespace. Do not create it.
	 *
	 * @param parent
	 *            the containing package
	 * @param name
	 *            Package to get
	 * @return The requested package (never null)
	 */
	private static Namespace lookupNamespace(Namespace parent, String name) {
		// Get or create an instance of the specified package.
		NamedElement foundElement = parent.getOwnedMember(name);
		if (foundElement instanceof Namespace) {
			return (Namespace) foundElement;
		}
		// Not found
		return null;
	}


	/**
	 * Get the package containing the last package of the qualifiedName.
	 * Create any missing package if needed
	 * Do not lookup for the last element.
	 * If there is no scope package in the qualifiedName, return the root.
	 *
	 * @param root
	 * @param qualifiedName
	 * @return The containing package (never null)
	 */
	public static Package getContainingPackage(Package root, List<String> qualifiedName) {
		Package cur = root;
		for (int i = 0; i < qualifiedName.size() - 1; i++) {
			String name = qualifiedName.get(i);
			cur = getPackage(cur, name);
			// if(cur==null)
			// return null;
		}
		return cur;
	}

	/**
	 * Get the package containing the last package of the qualifiedName.
	 * Create any missing package if needed
	 * Do not lookup for the last element.
	 * If there is no scope package in the qualifiedName, return the root.
	 *
	 * @param root
	 * @param qualifiedName
	 * @return The containing package (never null)
	 */
	private static Package lookupContainingPackage(Package root, List<String> qualifiedName) {
		Package cur = root;
		for (int i = 0; i < qualifiedName.size() - 1; i++) {
			String name = qualifiedName.get(i);
			cur = lookupPackage(cur, name);
			if (cur == null) {
				return null;
			}
		}
		return cur;
	}

	/**
	 * Get the Namespace containing the last namespace of the qualifiedName.
	 * Do not lookup for the last element.
	 * If there is no scope package in the qualifiedName, return the root.
	 *
	 * @param parent
	 * @param qualifiedName
	 * @return The containing package (never null)
	 */
	private static Namespace lookupContainingNamespace(Namespace parent, List<String> qualifiedName) {
		Namespace cur = parent;
		for (int i = 0; i < qualifiedName.size() - 1; i++) {
			String name = qualifiedName.get(i);
			cur = lookupNamespace(cur, name);
			if (cur == null) {
				return null;
			}
		}
		return cur;
	}

	/**
	 * Get the package containing the {@value #WILDCARD}.
	 * Do not create intermediate packages.
	 * Do not lookup for the last element.
	 * The qualified name contains a {@value #WILDCARD}, lookup the package just before it.
	 *
	 * @param root
	 * @param qualifiedName
	 * @return The containing package or null if not found.
	 */
	public static Package lookupPackageBeforeWildcard(Package root, List<String> qualifiedName) {
		Package cur = root;
		for (int i = 0; i < qualifiedName.size(); i++) {
			String name = qualifiedName.get(i);
			// Stop if we encounter the wilcard.
			if (WILDCARD.equals(name)) {
				break;
			}

			cur = lookupPackage(cur, name);
			if (cur == null) {
				return null;
			}
		}
		return cur;
	}


	/**
	 * Get the package containing the last package of the qualifiedName.
	 * Create any missing package if needed
	 * Do not lookup for the last element.
	 * If there is no scope package in the qualifiedName, return the root.
	 *
	 * @param model
	 * @param qualifiedName
	 * @return The containing package (never null)
	 */
	public static Package getContainingPackage(Resource model, List<String> qualifiedName) {
		Package cur = getNamedElement(model, UMLPackage.eINSTANCE.getPackage(), qualifiedName.get(0));

		for (int i = 1; i < qualifiedName.size() - 1; i++) {
			String name = qualifiedName.get(i);
			cur = getPackage(cur, name);
			// if(cur==null)
			// return null;
		}
		return cur;
	}

	/**
	 * Get the package containing the last package of the qualifiedName.
	 * Create any missing package if needed
	 * Do not lookup for the last element.
	 * If there is no scope package in the qualifiedName, return the root.
	 *
	 * @param model
	 * @param qualifiedName
	 * @return The containing package or null if not found.
	 */
	private static Package lookupContainingPackage(Resource model, List<String> qualifiedName) {
		Package cur = lookupNamedElement(model, UMLPackage.eINSTANCE.getPackage(), qualifiedName.get(0));
		if (cur == null) {
			return null;
		}

		for (int i = 1; i < qualifiedName.size() - 1; i++) {
			String name = qualifiedName.get(i);
			cur = lookupPackage(cur, name);
			if (cur == null) {
				return null;
			}
		}
		return cur;
	}

	/**
	 * Get the Package corresponding to the qname.
	 * Create all missing package
	 *
	 * @param qualifiedName
	 * @return The requested package (never null)
	 */
	public static Package getPackage(Resource model, List<String> qualifiedName) {
		Package cur = getNamedElement(model, UMLPackage.eINSTANCE.getPackage(), qualifiedName.get(0));

		for (int i = 1; i < qualifiedName.size(); i++) {
			String name = qualifiedName.get(i);
			cur = getPackage(cur, name);
			// if(cur==null)
			// return null;
		}
		return cur;
	}

	/**
	 * Lookup the Package corresponding to the qname in specified model resource..
	 *
	 * @param qualifiedName
	 * @return The requested package or null if not found
	 */
	public static Package lookupPackage(Resource model, List<String> qualifiedName) {
		Package cur = lookupNamedElement(model, UMLPackage.eINSTANCE.getPackage(), qualifiedName.get(0));
		if (cur == null) {
			return null;
		}

		for (int i = 1; i < qualifiedName.size(); i++) {
			String name = qualifiedName.get(i);
			cur = lookupPackage(cur, name);
			if (cur == null) {
				return null;
			}
		}
		return cur;
	}

	/**
	 * Get the Package corresponding to the qname.
	 * Create all missing package
	 *
	 * @param parent
	 * @param qualifiedName
	 * @return The requested package (never null)
	 */
	public static Package getPackage(Package parent, List<String> qualifiedName) {
		Package cur = parent;
		for (int i = 0; i < qualifiedName.size(); i++) {
			String name = qualifiedName.get(i);
			cur = getPackage(cur, name);
			// if(cur==null)
			// return null;
		}
		return cur;
	}

	/**
	 * Get the Model corresponding to the qname.
	 * Create all missing package.
	 *
	 * @param parent
	 * @param qualifiedName
	 * @return The requested package (never null)
	 */
	public static Package getModel(Package parent, List<String> qualifiedName) {
		Package cur = parent;

		// Get the intermediate as Package
		int i = 0;
		for (i = 0; i < qualifiedName.size() - 1; i++) {
			String name = qualifiedName.get(i);
			cur = getPackage(cur, name);
		}
		// Get the last one as Model
		String name = qualifiedName.get(i);
		cur = getModel(cur, name);

		return cur;
	}

	/**
	 * Get the Package corresponding to the qname.
	 *
	 * @param parent
	 * @param qualifiedName
	 * @return The requested package or null if not found
	 */
	public static Package lookupPackage(Package parent, List<String> qualifiedName) {
		Package cur = parent;
		for (int i = 0; i < qualifiedName.size(); i++) {
			String name = qualifiedName.get(i);
			cur = lookupPackage(cur, name);
			if (cur == null) {
				return null;
			}
		}
		return cur;
	}

	/**
	 * get (lookup or create) the specified NamedElement with the requested type
	 *
	 * @param <R>
	 *            should be equals to expected type.
	 * @param model
	 * @param expectedType
	 * @param name
	 * @return
	 */
	protected static <R extends NamedElement> R getNamedElement(Resource model, EClass expectedType, String name) {

		for (Object cur : model.getContents()) {
			// System.out.println("Compare " + cur.getClass().getName() + "-->" + name );
			if (expectedType.getInstanceClass().isInstance(cur) && name.equals(((NamedElement) cur).getName())) {
				// System.out.println("found !!!");
				return (R) cur;
			}
		}

		// Create it
		R res = (R) UMLFactory.eINSTANCE.create(expectedType);
		res.setName(name);
		model.getContents().add(res);
		return res;
	}

	/**
	 * Lookup for the specified NamedElement with the requested type from the model container.
	 *
	 * @param <R>
	 * @param model
	 * @param expectedType
	 * @param name
	 * @return
	 */
	protected static <R extends NamedElement> R lookupNamedElement(Resource model, EClass expectedType, String name) {

		for (Object cur : model.getContents()) {
			if (expectedType.isInstance(cur) && name.equals(((NamedElement) cur).getName())) {
				return (R) cur;
			}
		}

		return null;
	}


	/**
	 * Lookup or create the requested qualifiedName. Create it with the specified type.
	 *
	 * @param parentPackage
	 * @param generalQualifiedName
	 * @param expectedType
	 * @return
	 */
	public static <R extends Type> R getClassifier(Package parent, List<String> qualifiedName, EClass expectedType) {
		// Get or create containing packages
		Package p = getContainingPackage(parent, qualifiedName);
		// Use the last name to create the element
		return (R) p.getOwnedType(qualifiedName.get(qualifiedName.size() - 1), false, expectedType, true);
	}

	/**
	 * Lookup the corresponding classifier to the qname in specified model resource..
	 *
	 * @param qualifiedName
	 * @return The requested package or null if not found
	 */
	public static <R extends Type> R lookupClassifier(Resource model, List<String> qualifiedName, EClass expectedType) {
		Package p = lookupContainingPackage(model, qualifiedName);
		if (p == null) {
			return null;
		}

		return (R) p.getOwnedType(qualifiedName.get(qualifiedName.size() - 1), false, expectedType, false);
	}

	/**
	 * Lookup for the classifier with specified qualifiedName in specified model resource..
	 *
	 * @param qualifiedName
	 * @return The requested package or null if not found
	 */
	public static <R extends Type> R lookupClassifierWithWildcard(Resource model, List<String> qualifiedName, EClass expectedType) {
		Package p = lookupContainingPackage(model, qualifiedName);
		if (p == null) {
			return null;
		}

		return (R) p.getOwnedType(qualifiedName.get(qualifiedName.size() - 1), false, expectedType, false);
	}


	/**
	 * Lookup for the classifier with specified qualifiedName in specified model resource..
	 * Don't Create it .
	 * The qualifiedName can contain a wildcard "*".
	 *
	 * @param parentPackage
	 * @param generalQualifiedName
	 * @param expectedType
	 * @return Found classifier or null
	 */
	public static <R extends Type> R lookupClassifierWithWildcard(Package parent, List<String> qualifiedName, EClass expectedType) {

		int wildcardIndex = qualifiedName.indexOf(WILDCARD);
		// If there is no wildcard, use more simple implementation.
		if (wildcardIndex < 0) {
			return lookupClassifier(parent, qualifiedName, expectedType);
		}

		// Get containing package till the WILDCARD
		Package p = lookupPackageBeforeWildcard(parent, qualifiedName);
		// If not found, return
		if (p == null) {
			return null;
		}

		// Use the last name to lookup the element
		// Look for existing packages at the place of the wildcard.
		List<Package> existingPackages = p.getNestedPackages();
		// Get the remaining path from ]index, size]
		List<String> remainingPath = qualifiedName.subList(wildcardIndex + 1, qualifiedName.size());
		// Now continue searching from the existing packages
		for (Package nestedPackage : existingPackages) {

			R r = lookupClassifier(nestedPackage, qualifiedName, expectedType);
			if (r != null) {
				return r;
			}
		}

		// Nothing found
		return null;
	}

	/**
	 * Lookup the requested qualifiedName. Don't Create it. 
	 * The provided qualified name can denote a nested classifier.
	 *
	 * @param parent {@link Package} from where the lookup is done.
	 * @param qualifiedName Qualified name of the Classifier to lookup. Can denote a nested classifier.
	 * @param expectedType Type expected for the searched Classifier. Can be null (any subtype of Classifier).
	 * 
	 * @return Found classifier or null.
	 * 
	 * @modified 0.7.2
	 */
	public static <R extends Type> R lookupClassifier(Package parent, List<String> qualifiedName, EClass expectedType) {
		// Get containing package
//		Package p = lookupContainingPackage(parent, qualifiedName);
		Namespace p = lookupContainingNamespace(parent, qualifiedName);
		if (p == null) {
			return null;
		}
		// Use the last name to search the element
		
		return (R) p.getOwnedMember(qualifiedName.get(qualifiedName.size() - 1), false, (expectedType!=null?expectedType:CLASSIFIER_TYPE));
//		return (R) p.getOwnedType(qualifiedName.get(qualifiedName.size() - 1), false, expectedType, false);
	}

	/**
	 * Lookup the requested qualifiedName. Don't Create it .
	 *
	 * @param parentPackage
	 * @param generalQualifiedName
	 * @param expectedType
	 * @return Found classifier or null
	 */
	public static <R extends Classifier> R lookupClassifier(Namespace parent, List<String> qualifiedName, EClass expectedType) {
		// Get containing package
		Namespace p = lookupContainingNamespace(parent, qualifiedName);
		if (p == null) {
			return null;
		}

		parent = p;
		String shortname = qualifiedName.get(qualifiedName.size() - 1);
		return lookupClassifier(parent, shortname, expectedType);
	}

	/**
	 * Lookup for the specified classifier by its shortname in the provided {@link Namespace}. Do not create the Classifier.
	 * Return null if not found.
	 * 
	 * @param parent The {@link Namespace} that should contained the requested classisifer
	 * @param shortname The shortname of the requested classifier.
	 * @param expectedType The expected type of the classifier.
	 * @return The requested classifier or null if not found.
	 */
	public static <R extends Classifier> R lookupClassifier(Namespace parent, String shortname, EClass expectedType) {
		Classifier result;

		if (parent instanceof Package)
		{
			result = (Classifier) ((Package) parent).getOwnedType(shortname, false, expectedType, false);

		}
		else if (parent instanceof Interface)
		{
			result = ((Interface) parent).getNestedClassifier(shortname, false, expectedType, false);
		}
		else if (parent instanceof Class)
		{
			result = ((Class) parent).getNestedClassifier(shortname, false, expectedType, false);
		}
		else
		{
			// Should never happen !
			return null;
		}

		return (R) result;
	}

	/**
	 * Lookup for the specified nested classifier inside the parentClassifier.
	 * The first segment of the qualifiedName denote the name of the parentClassifier itself.
	 * 
	 * 
	 * @param parentClassifier
	 * @param qualifiedName
	 * @param expectedType The expected type (subclass of classifier, or null.
	 * @return
	 */
	public static <R extends Classifier> R lookupNestedClassifier(Classifier parentClassifier, List<String> qualifiedName, EClass expectedType) {
		// TODO Auto-generated method stub
		List<String> searchedQualifiedName = qualifiedName.subList(1, qualifiedName.size());
		return lookupClassifier(parentClassifier, searchedQualifiedName, expectedType);
	}

	/**
	 * Lookup for the specified package in the parent package. Create it if not found.
	 *
	 * @param parent
	 * @param name
	 *            Package to found
	 * @return
	 */
	public static Class getClass(Package parent, String name) {
		// Get or create an instance of the specified package.
		Class p = (Class) parent.getOwnedType(name, false, UMLPackage.eINSTANCE.getClass_(), true);
		return p;
	}

	/**
	 * Get the specified class from the specified parent.
	 * Missing package and the class are created if needed.
	 *
	 * @param parent
	 * @param qualifiedName
	 * @return
	 */
	public static Class getClass(Package parent, List<String> qualifiedName) {
		Package p = getContainingPackage(parent, qualifiedName);
		// Use the last name to create the element
		return getClass(p, qualifiedName.get(qualifiedName.size() - 1));
	}

	/**
	 *
	 * @param enclosingParents
	 *            list of enclosing parent, from the most outerside to the most inner side.
	 * @param name
	 * @return
	 */
	public static Class getClass(List<Namespace> enclosingParents, String name) {

		EClass type = UMLPackage.eINSTANCE.getClass_();
		Class result = (Class) getClassifier(enclosingParents, name, type);

		return result;
	}

	/**
	 * Get the class in the specified {@link Namespace}. Create the class if not found.
	 * 
	 * @param parents
	 *            The namespace where the class should be located.
	 * @param shortname Name of the class to create
	 * @return
	 * @since 0.7.1
	 */
	public static Class getExactClass(Namespace parents, String shortname) {

		return getExactClassifier(parents, shortname, UMLPackage.eINSTANCE.getClass_());
	}

	/**
	 * Create the class in the specified {@link Package}. 
	 * 
	 * @param parents
	 *            The {@link Package} where the class should be created.
	 * @param shortname Name of the class to create
	 * @return The created Class
	 * @since 0.7.1
	 */
	public static Class createClass(Package parents, String shortname) {

		return parents.createOwnedClass(shortname, false);
	}

	/**
	 * Lookup for the specified Class by its name in the provided Namespace.
	 * 
	 * @param The {@link Namespace} that should contained the requested classisifer
	 * @param shortname
	 * @return The requested Class, or null if not found.
	 * @since 0.7.1
	 */
	public static Class lookupClass(Namespace parent, String name) {

		return lookupClassifier(parent, name, UMLPackage.eINSTANCE.getClass_());
	}

	/**
	 * @param enclosingParents
	 *            list of enclosing parent, from the most outerside to the most inner side.
	 * @param shortname
	 * @return
	 */
	public static Enumeration getEnumeration(List<Namespace> enclosingParents, String shortname) {
		EClass type = UMLPackage.eINSTANCE.getEnumeration();
		Enumeration result = (Enumeration) getClassifier(enclosingParents, shortname, type);

		return result;
	}

	/**
	 * @param parents
	 *            list of enclosing parent, from the most outerside to the most inner side.
	 * @param shortname
	 * @return
	 * @since 0.7.1
	 */
	public static Enumeration getExactEnumeration(Namespace parents, String shortname) {
		EClass type = UMLPackage.eINSTANCE.getEnumeration();
		Enumeration result = (Enumeration) getExactClassifier(parents, shortname, type);

		return result;
	}

	/**
	 * Lookup for the specified Enumeration by its name in the provided Namespace.
	 * 
	 * @param parents
	 *            The {@link Namespace} that should contained the requested classisifer
	 * @param shortname
	 * @return The requested Enumeration, or null if not found.
	 */
	public static Enumeration lookupEnumeration(Namespace parents, String shortname) {
		EClass type = UMLPackage.eINSTANCE.getEnumeration();
		Enumeration result = (Enumeration) lookupClassifier(parents, shortname, type);

		return result;
	}


	/**
	 * Get or create a Classifier by its name. The type of the classifier can be Class or Interface
	 * Lookup is done in the provided namespaces, using the short name.
	 * The classifier is expected to be in the directly enclosing parent, but lookup is done in all enclosing parents.
	 * If the position is not the one expected, the found classifier is moved to the enclosing parent.
	 *
	 * If not found, create the classifier in the directly enclosing namespace.
	 * If found, correct the classifier parent to be the directly enclosing namespace. Eventually correct the type
	 * if it doesn't match.
	 *
	 * @param enclosingParents
	 * @param name
	 * @param type
	 * @return
	 */
	private static Classifier getClassifier(List<Namespace> enclosingParents, String name, EClass type) {
		Classifier result = null;
		int parentsCount = enclosingParents.size();
		// Get the direct parent
		Namespace parent = enclosingParents.get(parentsCount - 1);

		// Look in enclosing parents
		for (int i = parentsCount - 1; i >= 0; i--)
		{
			Namespace namespace = enclosingParents.get(i);

			// Lookup for the exact type
			result = (Classifier) namespace.getOwnedMember(name, false, type);
			if (result == null)
			{
				// Lookup for the other type
				result = (Classifier) namespace.getOwnedMember(name, false, UMLPackage.eINSTANCE.getClassifier());
			}

			// Here, the result should have the correct type
			if (result != null)
			{
				// Change type if needed
				if (result.eClass() != type) {
					System.err.println("Classifier type need to be corrected for (" + result.getQualifiedName() + ")");
					// Correct the classifier type
					result = (Classifier) transformInto(result, type);
				}
				// Check if parent need to be corrected
				if (result.getOwner() != parent) {

					// Correct the parent
					setClassifierOwner(result, parent);
					// Get the transformed element.
				}

				// Found, return it
				return result;
			}
			}
		
			// Not found, create in the direct parent.

			if (parent instanceof Package)
			{
				result = (Classifier) ((Package) parent).getOwnedType(name, false, type, true);

			}
			else if (parent instanceof Interface)
			{
				result = ((Interface) parent).createNestedClassifier(name, type);
			}
			else if (parent instanceof Class)
			{
				result = ((Class) parent).createNestedClassifier(name, type);
			}
			else
			{
				// Should never happen !
				// The first namespace is always the package.
				Package parentPackage = (Package) enclosingParents.get(0);
				result = (Classifier) parentPackage.getOwnedType(name, false, type, true);
			}
			return result;
		}

		/**
		 * Get or create a Classifier by its name. The type of the classifier can be Class or Interface
		 * The classifier will be located in the provided {@link Namespace}.
		 *
		 * If the classifier previously exists, check its type and change it according to the requested type.
		 *
		 * @param parent
		 * @param name
		 * @param type
		 * @return
		 */
		@SuppressWarnings("unchecked")
		private static <R extends Classifier> R getExactClassifier(Namespace parent, String name, EClass type) {

			Namespace namespace = parent;

			// Lookup for the exact type
			Classifier result = (Classifier) namespace.getOwnedMember(name, false, type);
			if (result != null) {
				return (R) result;
			} 
			else {
				// Lookup for another type (generally class, because it is the default creation type)
				result = (Classifier) namespace.getOwnedMember(name, false, UMLPackage.eINSTANCE.getClassifier());
				if (result != null) {
					// Correct the classifier type
					result = (Classifier) transformInto(result, type);
					return (R) result;
				}
			}

			// Not found, create in the direct parent.
			// Set a default type if none is specified.
			if( type == null ) {
				type = CLASS_TYPE;
			}

			if (parent instanceof Interface)
			{
				result = ((Interface) parent).createNestedClassifier(name, type);
			}
			else if (parent instanceof Class)
			{
				result = ((Class) parent).createNestedClassifier(name, type);
			}
			else if (parent instanceof Enumeration)
			{
				// UML Doesn't support nested type in Enumeration.
				// We create the type in the enumeration's parent.
				System.err.println("Nested classifier in Enumeration are not supported yet");
				Namespace parentNamespace = parent.getNamespace();
				result =  getExactClassifier(parentNamespace, name, type);
//				result = (Classifier) createNestedClassifier((Enumeration)parent, name, type);
			}
			else /* if (parent instanceof Package) */
			{
				result = (Classifier) ((Package) parent).getOwnedType(name, false, type, true);

			}
			return (R)result;
		}

		/**
		 * Create the class in the specified {@link Package}. 
		 * 
		 * @param parents
		 *            The {@link Package} where the class should be created.
		 * @param shortname Name of the class to create
		 * @return The created Class
		 * @since 0.7.1
		 */
		public static Type createType(Package parents, String shortname, EClass type) {

			return parents.createOwnedType(shortname, type);
		}

	/**
	 * Change the owner of the classifier
	 *
	 * @param classifier
	 *            The classifier to change the parent
	 * @param newParent
	 *            The parent to set.
	 */
	private static void setClassifierOwner(Classifier classifier, Namespace newParent) {
		// Not found, create in the direct parent.
		if (newParent instanceof Package)
		{
			classifier.setPackage((Package) newParent);

		}
		else if (newParent instanceof Interface)
		{

			((Interface) newParent).getNestedClassifiers().add(classifier);
		}
		else if (newParent instanceof Class)
		{
			((Class) newParent).getNestedClassifiers().add(classifier);
		}
		else
		{
			// Should never happen !
			// The first namespace is always the package.
			System.err.println("Don't know how to change classifier owner for " + newParent);
		}
	}

	/**
	 * Transform the element into the specified type
	 *
	 * @param toTransform
	 *            Classifier to transform (Class or interface)
	 * @param type
	 *            Type to transform into
	 */
	private static EObject transformInto(Classifier toTransform, EClass type) {

		GenericTransformer transformer = new GenericTransformer(toTransform);
		EObject result = transformer.transform(type);
		return result;

	}

	/**
	 * Get or create a guessed Classifier by its name. The type of the classifier can be Class or Interface.
	 * A guessed classifier is a classifier specified by an attribute, a parameter, a return type ...
	 * First, lookup for a Classifier with the same name, and return it regardless of its type.
	 * If nothing is found, create a classifier with the specified type.
	 *
	 *
	 * If not found, create the classifier in the package (enclosingParent[0]).
	 * If found, return it.
	 *
	 * @param enclosingParents
	 * @param name
	 *            The short name
	 * @param type
	 *            the proposed type to use as a hint for creation.
	 * @return
	 */
	public static Classifier getGuessedClassifier(List<Namespace> enclosingParents, List<String> name, EClass type) {
		Classifier result = null;
		int parentsCount = enclosingParents.size();
		// Get the direct parent
		Namespace parent = enclosingParents.get(parentsCount - 1);

		// Look in enclosing parents
		for (int i = parentsCount - 1; i >= 0; i--)
		{
			Namespace namespace = enclosingParents.get(i);

			// Lookup for any type
			result = lookupClassifier(namespace, name, UMLPackage.eINSTANCE.getClassifier());
			// result = (Classifier)namespace.getOwnedMember(name, false, UMLPackage.eINSTANCE.getClassifier());
			if (result != null)
			{
				return result;
			}
		}

		// Not found, create in the direct parent.
		// The first namespace is always the package.
		Package parentPackage = (Package) enclosingParents.get(0);
		result = (Classifier) getClassifier(parentPackage, name, type);
		// result = (Classifier) ((Package)parentPackage).getOwnedType(name, false, type, true);

		return result;
	}

	/**
	 *
	 * @param parent
	 * @param shortname
	 * @return
	 */
	public static Interface getInterface(Package parent, String shortname) {
		Interface p = (Interface) parent.getOwnedType(shortname, false, UMLPackage.eINSTANCE.getInterface(), true);
		return p;
	}

	/**
	 *
	 * @param enclosingParents
	 *            list of enclosing parent, from the most outerside to the most inner side.
	 * @param shortname
	 * @return
	 */
	public static Interface getInterface(List<Namespace> enclosingParents, String shortname) {
		return (Interface) getClassifier(enclosingParents, shortname, UMLPackage.eINSTANCE.getInterface());
	}

	/**
	 *
	 * @param enclosingParents
	 *            list of enclosing parent, from the most outerside to the most inner side.
	 * @param shortname
	 * @return
	 */
	public static Interface getExactInterface(Namespace enclosingParents, String shortname) {
		return (Interface) getExactClassifier(enclosingParents, shortname, UMLPackage.eINSTANCE.getInterface());
	}

	/**
	 * Lookup for the specified {@link Interface} by its name in the provided Namespace.
	 * 
	 * @param parents
	 *            The {@link Namespace} that should contained the requested classisifer
	 * @param shortname
	 * @return The requested Interface, or null if not found.
	 * 
	 * @since 0.7.1
	 */
	public static Interface lookupInterface(Namespace parent, String shortname) {
		
		return lookupClassifier(parent, shortname, UMLPackage.eINSTANCE.getInterface());
	}

	/**
	 * Create a Property and add it to the parent.
	 *
	 * @param parent
	 * @param name
	 * @param arrayCount
	 * @return
	 */
	public static Property createProperty(Classifier parent, Type type, String name, int arrayCount) {
		Property p;
		if (parent instanceof Class ) {
			p = createProperty((Class) parent, type, name, arrayCount);
		} else if (parent instanceof Interface) {
			p = createProperty((Interface) parent, type, name, arrayCount);
		} else if (parent instanceof Enumeration) {
			p = createProperty((Enumeration) parent, type, name, arrayCount);
		}
		else {
			return null;
		}

		p.setIsUnique(false);
		return p;
	}

	/**
	 * Create a property for the Class
	 *
	 * @param parent
	 * @param type
	 * @param name
	 * @param arrayCount
	 * @return
	 */
	public static Property createProperty(Class parent, Type type, String name, int arrayCount) {
		return parent.getOwnedAttribute(name, type, false, UMLPackage.eINSTANCE.getProperty(), true);
	}

	/**
	 * Create a property for the Datatype (or Enumeration)
	 *
	 * @param parent
	 * @param type
	 * @param name
	 * @param arrayCount
	 * @return
	 */
	public static Property createProperty(DataType parent, Type type, String name, int arrayCount) {
		return parent.getOwnedAttribute(name, type, false, UMLPackage.eINSTANCE.getProperty(), true);
	}

	/**
	 * Create a property for the Class
	 *
	 * @param parent
	 * @param type
	 * @param name
	 * @param arrayCount
	 * @return
	 */
	public static Property createProperty(Interface parent, Type type, String name, int arrayCount) {
		return parent.getOwnedAttribute(name, type, false, UMLPackage.eINSTANCE.getProperty(), true);
	}

	/**
	 * Create a Generalization relation between the specified Classifier
	 *
	 * @param child
	 * @param general
	 */
	public static void getGeneralization(Classifier child, Classifier general) {
		child.getGeneralization(general, true);
		// child.createGeneralization(general);
	}

	/**
	 * Create a InterfaceRealization relation between the specified Classifier
	 *
	 * @param child
	 * @param general
	 */
	public static void getInterfaceRealization(BehavioredClassifier child, Interface general) {
		Realization res = lookupInterfaceRealization(child, general);
		if (res == null) {
			createInterfaceRealization(child, general);
		}
	}

	/**
	 * Lookup for the specified realization inside the package
	 *
	 * @param parent
	 * @param child
	 * @param general
	 * @return
	 */
	private static InterfaceRealization lookupInterfaceRealization(Classifier child, Classifier general) {
		for (Dependency ele : child.getClientDependencies()) {
			if (ele instanceof InterfaceRealization) {
				InterfaceRealization real = (InterfaceRealization) ele;
				if (real.getClients().contains(child) && real.getSuppliers().contains(general)) {
					return real;
				}
			}
		}
		return null;
	}

	/**
	 * Create a Generalization relation between the specified Classifier
	 *
	 * @param child
	 * @param general
	 */
	public static void createInterfaceRealization(BehavioredClassifier child, Interface general) {
		InterfaceRealization res = UMLFactory.eINSTANCE.createInterfaceRealization();

		res.setContract(general);
		res.setImplementingClassifier(child);

		// res.getClients().add(child);
		// res.getSuppliers().add(general);
		res.setName(child.getName() + " implements " + general.getName());
		// child.createGeneralization(general);
		// child.getClientDependencies().add(res);
	}


	/**
	 * Create a Generalization relation between the specified Classifier
	 *
	 * @param child
	 * @param general
	 */
	public static void getRealization(Package parent, Classifier child, Classifier general) {
		Realization res = lookupRealization(parent, child, general);
		if (res == null) {
			createRealization(parent, child, general);
		}
	}

	/**
	 * Lookup for the specified realization inside the package
	 *
	 * @param parent
	 * @param child
	 * @param general
	 * @return
	 */
	private static Realization lookupRealization(Package parent, Classifier child, Classifier general) {
		for (PackageableElement ele : parent.getPackagedElements()) {
			if (ele instanceof Realization) {
				Realization real = (Realization) ele;
				if (real.getClients().contains(child) && real.getSuppliers().contains(general)) {
					return real;
				}
			}
		}
		return null;
	}

	/**
	 * Create a Generalization relation between the specified Classifier
	 *
	 * @param child
	 * @param general
	 */
	public static void createRealization(Package parent, Classifier child, Classifier general) {
		Realization res = UMLFactory.eINSTANCE.createRealization();
		res.getClients().add(child);
		res.getSuppliers().add(general);
		res.setName(child.getName() + " extends " + general.getName());
		parent.getPackagedElements().add(res);
	}

	/**
	 * Create a primitive type.
	 *
	 * @param root
	 * @param typeName
	 */
	public static PrimitiveType getPrimitive(Package parent, String typeName) {
		return (PrimitiveType) parent.getOwnedType(typeName, true, UMLPackage.eINSTANCE.getPrimitiveType(), true);

	}
	
	/**
	 * Get a primitive type from parent package assumed to be contained in an imported model.
	 *
	 * @param root
	 * @param typeName
	 */
	public static PrimitiveType getImportedPrimitive(Package parent, String typeName) {
		return (PrimitiveType) parent.getOwnedType(typeName, false, UMLPackage.eINSTANCE.getPrimitiveType(), false);
	}

	/**
	 * Get the specified class from the specified parent.
	 * Missing package and the class are created if needed.
	 *
	 * @param parent
	 * @param qualifiedName
	 * @return
	 */
	public static PrimitiveType getPrimitive(Package parent, List<String> qualifiedName) {
		Package p = getContainingPackage(parent, qualifiedName);
		// Use the last name to create the element
		return getPrimitive(p, qualifiedName.get(qualifiedName.size() - 1));
	}

	/**
	 * Get the specified operation from the provided type.
	 *
	 * @param classifier
	 * @param name
	 * @return
	 */
	public static Operation getOperation(Classifier classifier, String name) {
		// TODO Auto-generated method stub
		List<Operation> opers = classifier.getOperations();
		for (Operation op : opers) {
			if (name.equals(op.getName())) {
				return op;
			}
		}

		Operation res = createOperation(classifier, name);
		return res;
	}

	/**
	 * Create an operation with the specified name.
	 *
	 * @param classifier
	 * @param name
	 * @return
	 */
	public static Operation createOperation(Classifier classifier, String name) {
		// Not found, create it
		Operation res = UMLFactory.eINSTANCE.createOperation();
		res.setName(name);
		if (classifier instanceof Class) {
			((Class) classifier).getOwnedOperations().add(res);
		} else if (classifier instanceof Interface) {
			((Interface) classifier).getOwnedOperations().add(res);
		} else if (classifier instanceof DataType) {
			((DataType) classifier).getOwnedOperations().add(res);
		}
		return res;
	}

	/**
	 * Create a nested classifier of the requested type inside the specified parent Classifier.
	 * 
	 * @param parentClassifier
	 * @param nestedRelativename
	 * @param requestedType
	 * @return
	 */
	public static Type getNestedClassifier(Classifier parentClassifier, List<String> nestedRelativename, EClass requestedType) {
		// TODO Auto-generated method stub
		
		// Get intermediate classifiers
		Classifier cur = parentClassifier;
		for( int i=1; i<nestedRelativename.size()-1; i++ ) {
			cur = getNestedClassifier(cur, nestedRelativename.get(i), null);
		}
		// Get requested classifier
		cur = getNestedClassifier(cur, nestedRelativename.get(nestedRelativename.size()-1), requestedType);
		return cur;
	}

	/**
	 * Create a nested classifier of the requested type inside the specified parent Classifier.
	 * 
	 * @param parent
	 * @param name
	 * @param type, can be null.
	 * @return
	 */
	public static <R extends Classifier> R getNestedClassifier(Namespace parent, String name, EClass type) {

		// Lookup for the exact type
		Namespace result = (Classifier) parent.getOwnedMember(name, false, type);
		if (result != null) {
			return  (R) result;
		} 
		// Not found, create in the direct parent.

		// Set a default type if none is specified.
		if( type == null ) {
			type = CLASS_TYPE;
		}
		
		if (parent instanceof Interface)
		{
			result = ((Interface) parent).createNestedClassifier(name, type);
		}
		else if (parent instanceof Class)
		{
			result = ((Class) parent).createNestedClassifier(name, type);
		}
		else if (parent instanceof Enumeration)
		{
			// UML Doesn't support nested type in Enumeration.
			// We create the type in the enumeration's parent.
			System.err.println("Nested classifier in Enumeration are not supported yet");
			Namespace parentNamespace = parent.getNamespace();
			result =  getNestedClassifier(parentNamespace, name, type);
		}
		else /* if (parent instanceof Package) */
		{
			result = (Classifier) ((Package) parent).getOwnedType(name, false, type, true);

		}
		return (R)result;
	}


	public static Package importOrGetModel(Model model, String uri) {
		if (model != null & uri != null) {
			try {
				Resource theResource = model.eResource().getResourceSet().getResource(URI.createURI(uri), true);
				if (theResource != null) {
					for (EObject content : theResource.getContents()) {
						if (content instanceof Model) {
							return (Package) content;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			for (Resource resource : model.eResource().getResourceSet().getResources()) {
//				if (resource.getURI().path().equals(uri)) {
//					theResource = resource;
//					break;
//				}
//			}
			
//			if (theResource == null) {
//				theResource = model.eResource().getResourceSet().createResource(URI.createURI(uri));
//				try {
//					theResource.load(null);
//					model.eResource().getResourceSet().getResource(URI.createURI(uri), true);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			
			
		}
		
		return null;
	}
	
	/**
	 * Move elements whose qualified name starts with a provided string to the model root element and delete packages representing the qualified name
	 * @param model
	 * @param qualifiedName
	 */
	public static void trimModel(Model model, String qualifiedName) {
		// TODO
	}
	
	/**
	 * Apply java and code generation profiles
	 * 
	 * @param model
	 */
	public static void applyCodeGenerationProfilesToModel(Model model) {
		if (model != null) {
			applyProfile(model, "pathmap://PapyrusJava_PROFILES/PapyrusJava.profile.uml");
			applyProfile(model, "pathmap://Codegen_PROFILES/Codegen.profile.uml");
			applyProfile(model, "pathmap://UML_PROFILES/Standard.profile.uml");
		}
	}
	
	/**
	 * Apply to a model a profile represented by a URI
	 * 
	 * @param model
	 * @param uri
	 * @return
	 */
	private static Profile applyProfile(Model model, String uri) {
		Profile profile = null;
		
		try {
			Resource resource = model.eResource().getResourceSet().getResource(URI.createURI(uri), false);

			if (resource == null) {
				resource = model.eResource().getResourceSet().createResource(URI.createURI(uri));
				try {
					resource.load(null);
				} catch (IOException e) {
					return null;
				}
			}
			
			for (EObject content : resource.getContents()) {
				if (content instanceof Profile) {
					profile = (Profile) content;
					break;
				}
			}

			if (profile != null && !model.isProfileApplied(profile)) {
				model.applyProfile(profile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profile;
	}
}
