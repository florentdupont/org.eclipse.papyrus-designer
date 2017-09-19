/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
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

package org.eclipse.papyrus.designer.languages.common.base;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * A set of utility functions around elements
 */
public class ElementUtils {

	/**
	 * Retrieve an element from a list of named elements via its name
	 *
	 * @param namedElementList
	 * @param name
	 * @return
	 */
	public static NamedElement getNamedElementFromList(EList<? extends EObject> elementList, String name) {
		for (EObject element : elementList) {
			if (element instanceof NamedElement) {
				NamedElement namedElement = (NamedElement) element;
				if ((namedElement.getName() != null) && namedElement.getName().equals(name)) {
					return namedElement;
				}
			}
		}
		return null;
	}

	/**
	 * @param element
	 *            an element which is owned by a model.
	 * @param subfolder
	 *            the name of a sub-folder within root (created, if not
	 *            existent)
	 * @return a reference to the sub folder within the root of the model that
	 *         belongs to the passed element.
	 */
	public static Package getRoot(Element element, String subfolder) {
		Package root = PackageUtil.getRootPackage(element);
		if (root.getNestedPackage(subfolder) != null) {
			return root.getNestedPackage(subfolder);
		} else {
			return root.createNestedPackage(subfolder);
		}
	}

	/**
	 * this method returns the component type of an implementation. It is based
	 * on the modeling convention that implementations inherit from types.
	 * TODO: It currently returns the first abstract (direct) superclass
	 * 
	 * @param clazz
	 *            A component implementation
	 * @return the first abstract superclass
	 */
	public static Class componentType(Class clazz) {
		if (clazz.isAbstract()) {
			return clazz;
		} else {
			for (Class superclass : clazz.getSuperClasses()) {
				if (superclass.isAbstract()) {
					return superclass;
				}
			}
			return null;
		}
	}

	/**
	 * @param is an instance specification
	 * @return true, if an instance specification is a composite, i.e. has more than 1 slots
	 */
	public static boolean isComposite(InstanceSpecification is) {
		return (is.getSlots().size() > 0);
	}

	/**
	 * Get an element via its qualified name.  This function will find the first element with a
	 * matching qualified name within the resource set associated with the passed element
	 *
	 * @param element
	 * 				An element within a resource which in turn is part of the search resource set
	 * @param qualifiedName
	 *            the qualified name of an element
	 * @return the found element or null
	 */
	public static NamedElement getQualifiedElementFromRS(Element element, String qualifiedName) {
		return getQualifiedElementFromRS(element.eResource().getResourceSet(), qualifiedName);
	}
	
	/**
	 * Get an element via its qualified name. This function will find the first element in the
	 * resource set that has this qualified name (whether imported or not)
	 *
	 * @param rs
	 *            a resource set
	 * @param qualifiedName
	 *            the qualified name of an element
	 * @return the found element or null
	 */
	public static NamedElement getQualifiedElementFromRS(ResourceSet rs, String qualifiedName) {
		for (Resource resource : rs.getResources()) {
			if (resource instanceof UMLResource) {
				if (resource.getContents().size() > 0) {
					EObject topLevelElem = resource.getContents().get(0);
					if (topLevelElem instanceof Package) {
						NamedElement ne = getQualifiedElement((Package) topLevelElem, qualifiedName);
						if (ne != null) {
							return ne;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Get an element via its qualified name. It will ignore imports
	 *
	 * @param root
	 *            the root element (package) of a model
	 * @param qualifiedName
	 *            the qualified name of an element
	 * @return the found element or null
	 */
	public static NamedElement getQualifiedElement(Package root, String qualifiedName) {
		String[] path = qualifiedName.split(NamedElement.SEPARATOR);
		if (root.getName().equals(path[0])) {
			return getQualifiedElement(root, path, 1);
		}
		return null;
	}

	/**
	 * Retrieve an element via its qualified name within a root package.
	 *
	 * @return the found element, if it exists
	 */
	public static NamedElement getQualifiedElement(Package root, String[] path, int pos) {
		if (root == null) {
			return null;
		}
		if (pos == path.length) {
			return root;
		} else if (pos < path.length) {
			NamedElement segmentElem = root.getPackagedElement(path[pos++]);
			if (segmentElem != null) {
				if (pos == path.length) {
					return segmentElem;
				} else if (segmentElem instanceof Package) {
					// requires further recursion
					NamedElement foundElement = getQualifiedElement((Package) segmentElem, path, pos);
					// return, if not found
					if (foundElement != null) {
						return foundElement;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Simple check whether an element is in a different model than the passed
	 * package It will return true, whenever the the top elements do not match.
	 * This is always true, if the 2nd belongs to a different model, whether
	 * imported or not. This distinction is however not required in our context.
	 */
	public static boolean isElementInDifferentModel(Package model, NamedElement namedElement) {
		return model != PackageUtil.getRootPackage(namedElement);
	}

	public static <T extends EObject> EList<T> getAllElementsOfType(Element examineElement, java.lang.Class<T> clazz) {
		EList<Element> visitedPackages = new BasicEList<Element>();
		return getAllElementsOfType(examineElement, clazz, visitedPackages);
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> EList<T> getAllElementsOfType(Element examineElement, java.lang.Class<T> clazz, EList<Element> visitedPackages) {
		EList<T> list = new UniqueEList<T>();
		for (Element element : examineElement.allOwnedElements()) {
			if (element instanceof Package) {
				if (!visitedPackages.contains(element)) {
					visitedPackages.add(element);
					list.addAll(getAllElementsOfType(element, clazz, visitedPackages));
				}
			} else if (clazz.isInstance(element)) {
				list.add((T) element);
			}
		}
		return list;
	}

	/**
	 * Convenience function: Declare a dependency from source to destination. The function checks,
	 * if a dependency already exists to avoid double dependencies.
	 *
	 * @param source
	 *            source type of the dependency
	 * @param dest
	 *            destination of the dependency
	 */
	public static void declareDependency(Type source, Type dest) {
		// check, if a relationship already exists
		for (Relationship dependency : source.getRelationships(UMLPackage.eINSTANCE.getDependency())) {
			if (((Dependency) dependency).getSuppliers().contains(dest)) {
				return;
			}
		}
		source.createDependency(dest);
	}



	/**
	 * This method returns all types that are referenced by a classifier. This includes
	 * attribute types, types within operations as well as inherited types.
	 * This is useful to generate the #includes
	 * (which is used by the Acceleo code within the packaging plugin)
	 */
	public static EList<Classifier> getReferencedClassifiers(Classifier classifier) {
		EList<Classifier> list = new UniqueEList<Classifier>();
		list.addAll(classifier.parents());

		if (classifier instanceof Class) {
			// get classifiers referenced by attributes
			for (Operation operation : ((Class) classifier).getOwnedOperations()) {
				for (Parameter parameter : operation.getOwnedParameters()) {
					Type type = parameter.getType();
					if (type instanceof Classifier) {
						list.add((Classifier) type);
					}
				}
			}

			// get classifiers referenced by attributes
			for (Property attribute : ((Class) classifier).getOwnedAttributes()) {
				Type type = attribute.getType();
				if (type instanceof Classifier) {
					list.add((Classifier) type);
				}
			}
		}
		return list;
	}

	public static EList<Namespace> usedNamespaces(NamedElement element) {
		EList<Namespace> list = new BasicEList<Namespace>(element.allNamespaces());

		if (list.size() < 1) {
			return null;
		}
		list.remove(list.size() - 1); // remove last element (top-level)
		return list;
	}

	/**
	 * A small helper function that makes names compliant with variable
	 * names in programming languages such as C++ or Java
	 * Unlike varName2, replace scoping signs as well
	 */
	public static String varName(NamedElement element) {
		return StringUtils.varName(element.getName());
	}

	/**
	 * Get the XML (URI) Id of an element within your model
	 * Useful for transmitting model references
	 *
	 * @param element
	 *            a UML element
	 * @return
	 */
	public static String xmlID(Element element) {
		Resource resource = element.eResource();
		// TODO: use EcoreUtil getURI (InternalEObject) instead?

		if (resource instanceof XMLResource) {
			XMLResource xmlResource = (XMLResource) resource;
			return xmlResource.getID(element);
		}
		return null;
	}
}
