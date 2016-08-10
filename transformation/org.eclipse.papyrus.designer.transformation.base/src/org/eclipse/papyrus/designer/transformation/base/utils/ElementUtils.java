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

package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Typedef;
import org.eclipse.papyrus.designer.transformation.base.Activator;
import org.eclipse.papyrus.designer.transformation.base.preferences.PapyrusDesignerPreferenceConstants;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

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
	public static NamedElement getNamedElementFromList(
			EList<? extends EObject> elementList, String name) {
		for (EObject element : elementList) {
			if (element instanceof NamedElement) {
				NamedElement namedElement = (NamedElement) element;
				if((namedElement.getName() != null) && namedElement.getName().equals(name)) {
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
	 * @param clazz A component implementation
	 * @return the first abstract superclass
	 */
	public static Class componentType(Class clazz) {
		if (clazz.isAbstract()) {
			return clazz;
		}
		else {
			for (Class superclass : clazz.getSuperClasses()) {
				if (superclass.isAbstract()) {
					return superclass;
				}
			}
			return null;
		}
	}

	/**
	 * return true, if an instance specification is a composite, i.e. has > 1
	 * slots TODO: distinguish parts and configuration attributes
	 */

	public static boolean isComposite(InstanceSpecification is) {
		return (is.getSlots().size() > 0);
	}

	public static boolean treatNoneAsComposite() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getBoolean(PapyrusDesignerPreferenceConstants.P_TREAT_NONE_AS_COMPOSITE);
	}

	public static EList<Property> getParts(Class implementation) {
		if (treatNoneAsComposite()) {
			EList<Property> parts = new BasicEList<Property>();
			for (Property part : implementation.getAttributes()) {
				if (part.getAggregation() != AggregationKind.SHARED_LITERAL) {
					parts.add(part);
				}
			}
			return parts;
		}
		else {
			return implementation.getParts();
		}
	}
	
	/**
	 * Get an element via its qualified name. Will find elements from the root
	 * model and elements in imported models. Also supports target model in
	 * which imports have been copied (while keeping the top-level name)
	 *
	 * @param root
	 * @param qualifiedName
	 * @return
	 */
	public static NamedElement getQualifiedElement(Package root,
			String qualifiedName) {
		NamedElement namedElement = null;
		int index = qualifiedName.indexOf("::"); //$NON-NLS-1$
		if (index != -1) {
			// first try using a path without top element (since
			// getQualifiedElement is typically used for
			// imported elements)
			String remainder = qualifiedName.substring(index + 2);
			namedElement = getQualifiedElement(root, remainder, qualifiedName);
		}
		if (namedElement == null) {
			// try with complete name as path name, but assume that the element
			// has been copied into the model,
			// i.e. qualifiedName is prefixed by model name
			namedElement = getQualifiedElement(root, qualifiedName,
					root.getName() + "::" + qualifiedName); //$NON-NLS-1$
		}
		return namedElement;
	}

	/**
	 * Retrieve an element via its qualified name within a package The segments
	 * of the package may be non unique due to imports
	 *
	 * @return the found element, if it exists
	 */
	public static NamedElement getQualifiedElement(Package root,
			String remainingPath, String qualifiedName) {
		if (root == null) {
			return null;
		}
		if (!remainingPath.contains(NamedElement.SEPARATOR)) {
			for (NamedElement candidate : root.getMembers()) {
				String name = candidate.getName();
				if ((name != null) && name.equals(remainingPath)) {
					if (candidate.getQualifiedName().equals(qualifiedName)) {
						return candidate;
					}
				}
			}
		} else {
			String segment = remainingPath.split(NamedElement.SEPARATOR)[0];
			String remainder = remainingPath.substring(segment.length() + 2);
			for (Element element : root.getMembers()) {
				if (element instanceof Package) {
					if (((NamedElement) element).getName().equals(segment)) {
						NamedElement foundElement = getQualifiedElement(
								(Package) element, remainder, qualifiedName);
						// return, if not found
						if (foundElement != null) {
							return foundElement;
						}
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
	public static boolean isElementInDifferentModel(Package model,
			NamedElement namedElement) {
		return model != PackageUtil.getRootPackage(namedElement);
	}

	public static <T extends EObject> EList<T> getAllElementsOfType(Element examineElement, java.lang.Class<T> clazz)
	{
		EList<Element> visitedPackages = new BasicEList<Element>();
		return getAllElementsOfType(examineElement, clazz, visitedPackages);
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> EList<T> getAllElementsOfType(Element examineElement, java.lang.Class<T> clazz, EList<Element> visitedPackages)
	{
		EList<T> list = new UniqueEList<T>();
		for (Element element : examineElement.allOwnedElements()) {
			if (element instanceof Package) {
				if (!visitedPackages.contains(element)) {
					visitedPackages.add(element);
					list.addAll(getAllElementsOfType(element, clazz,
							visitedPackages));
				}
			} else if (clazz.isInstance(element)) {
				list.add((T) element);
			}
		}
		return list;
	}

	/**
	 * Put quotes around a string, unless string already starts with a quote.
	 *
	 * @param str
	 * @return
	 */
	public static String quoteString(String str) {
		if (str.startsWith(StringConstants.QUOTE)) {
			return str;
		}
		else {
			return StringConstants.QUOTE + str + StringConstants.QUOTE;
		}
	}

	/**
	 * TODO: copy&paste from C++ generator (& specific for C++)
	 *
	 * @param ne
	 * @return
	 */
	public static String cppQName(NamedElement ne) {
		if ((StereotypeUtil.isApplied(ne, External.class)) || (StereotypeUtil.isApplied(ne, NoCodeGen.class))) {
			return ne.getName();
		} else {
			String qName = ne.getName();
			for (Namespace ns : ne.allNamespaces()) {
				if (!(ns instanceof Model)) {
					qName = ns.getName() + "::" + qName; //$NON-NLS-1$
				}
			}
			return qName;
		}
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
	 *
	 * TODO: Specific to C++
	 *
	 * @param type
	 *            a type
	 * @return return the definition of a typedef, if the type has been defined via
	 *         the stereotype CppType of the Cpp profile
	 */
	public static String dereferenceTypedef(Type type) {
		if (type instanceof PrimitiveType) {
			Typedef cppType = UMLUtil.getStereotypeApplication(type, Typedef.class);
			if (cppType != null) {
				return cppType.getDefinition();
			}
		}
		return type.getQualifiedName();
	
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

	/**
	 * Return the interface which owns an operation that is implemented by a class.
	 * Context: a class might implement several interfaces by defining their operations.
	 * The operation is useful in the context of state-machines: when a transition is triggered by
	 * the call of an operation of the class, we'd like to know which interceptor (for which interface)
	 * belongs to it (since the operations are enumerated within each interface).
	 * TODO: move operation into state-chart java code
	 * TODO: would not work for ROOM ports typed with a collaboration
	 *
	 * @param operation
	 * @return the interface which the operation belongs
	 */
	public static Interface implementsInterface(Operation operation) {
		Element owner = operation.getOwner();
		if (owner instanceof BehavioredClassifier) {
			String name = operation.getName();
			EList<Type> types = new BasicEList<Type>();
			for (Parameter parameter : operation.getOwnedParameters()) {
				types.add(parameter.getType());
			}
			// loop over implemented realizations. Do not rely on FCM derivedElement information
			// as it might be missing on some models (it would point from an operation of the class
			// to the associated operation of the interface)
			for (InterfaceRealization ir : ((BehavioredClassifier) owner).getInterfaceRealizations()) {
				// check for types to allow for overloading
				Operation candidate = ir.getContract().getOwnedOperation(name, null, types);
				if (candidate != null) {
					return ir.getContract();
				}
			}
		}
		else if (owner instanceof Interface) {
			return (Interface) owner;
		}
		return null;
	}

	public static <T extends EObject> EList<T> reverse(EList<T> list) {
		EList<T> reverseList = new BasicEList<T>();
		for (int i=list.size()-1; i>=0; i--) {
			reverseList.add(list.get(i));
		}
		return reverseList;
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
		return varName(element.getName());
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
