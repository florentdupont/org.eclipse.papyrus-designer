/*******************************************************************************
 * Copyright (c) 2006 - 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLUtil;


/**
 * Some utilities: a set of static methods for code generation
 *
 * @author wassim, ansgar
 *
 */
public class GenUtils {

	public static final String NL = System.getProperties().getProperty("line.separator"); //$NON-NLS-1$

	/**
	 * Retrieve first template binding from list of template bindings, if
	 * exactly one exists. Return null otherwise.
	 *
	 * @param current
	 *            Class on which the template binding is searched
	 * @return the template binding of current Class
	 */
	public static TemplateBinding getTemplateBinding(Classifier current) {
		TemplateBinding binding = null;
		if (current.getTemplateBindings().size() == 1) {
			binding = current.getTemplateBindings().get(0);
		}

		return binding;
	}

	/**
	 * Check whether the passed classifier has a template binding with itself as bound element
	 *
	 * @param cl
	 * @return
	 */
	public static boolean isTemplateBoundElement(Classifier cl) {
		boolean result = false;
		EList<TemplateBinding> tbs = cl.getTemplateBindings();
		if (tbs.size() > 0) {
			for (TemplateBinding tb : tbs) {
				// TODO: will only work for single element in template binding list
				result = tb.getBoundElement() == cl;
			}
		}
		return result;
	}

	/**
	 * Get the name of a template parameter or undefined, if it is not set
	 *
	 * @param templateParam
	 * @return
	 */
	public static String getTemplateName(TemplateParameter templateParam) {
		String name = ""; //$NON-NLS-1$
		ParameterableElement pElt = templateParam.getParameteredElement();
		if ((pElt != null) && (pElt instanceof NamedElement)) {
			name = ((NamedElement) pElt).getName();
		} else {
			name = "undefined"; //$NON-NLS-1$
		}

		return name;
	}


	/**
	 *
	 * @param classifier
	 *            a classifier owning a template signature
	 * @return the list of (formal) parameters defined within a template signature
	 */
	public static Collection<TemplateParameter> getTemplateParameters(Classifier classifier) {

		Collection<TemplateParameter> params = new ArrayList<TemplateParameter>();
		TemplateSignature ts = classifier.getOwnedTemplateSignature();
		if (ts != null) {
			params.addAll(ts.getOwnedParameters());
		}

		return params;
	}

	/**
	 *
	 * @param classifier
	 * @return
	 */
	public static Collection<ParameterableElement> getTemplateParameteredElements(Classifier classifier) {

		Collection<ParameterableElement> params = new ArrayList<ParameterableElement>();
		TemplateSignature ts = classifier.getOwnedTemplateSignature();
		if (ts != null) {
			for (TemplateParameter tp : ts.getOwnedParameters()) {
				if (tp != null) {
					params.add(tp.getParameteredElement());
				}
			}
		}
		return params;
	}

	/**
	 * Retrieve a list of types of attributes that belong to the current classifier.
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @return Collection of classifiers which are the types of the attributes
	 */
	public static EList<Classifier> getTypesViaAttributes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();

		Iterator<Property> attributes;
		attributes = current.getAttributes().iterator();
		while (attributes.hasNext()) {
			Property currentAttribute = attributes.next();
			Type type = currentAttribute.getType();
			addFarthestOwnerType(type, result);
		}
		return result;
	}
	
	/**
	 * Retrieve a list of types of attributes that belong to the current classifier.
	 * Inner classifiers are returned as is
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @return Collection of classifiers which are the types of the attributes
	 */
	public static EList<Classifier> getDirectTypesViaAttributes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();

		Iterator<Property> attributes;
		attributes = current.getAttributes().iterator();
		while (attributes.hasNext()) {
			Property currentAttribute = attributes.next();
			Type type = currentAttribute.getType();
			addClosestOwnerType(type, result);
		}
		return result;
	}
	
	/**
	 * Retrieve a list of types of attributes that belong to the current classifier. Filter by stereotypes.
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @param excludedStereotypes
	 *            List of ALL stereotypes that must no be applied
	 * @param includedStereotypes
	 *            List of ANY stereotype that must no be applied (at least one)
	 * @param bypassForInnerClassifiers
	 *            Always include inner classifier types or not
	 * @param noSharedAggregation
	 *            Always exclude attributes with a shared aggregation kind
	 * @return Collection of classifiers which are the types of the attributes
	 */
	public static EList<Classifier> getTypesViaAttributes(Classifier current, EList<Class<? extends EObject>> excludedStereotypes, EList<Class<? extends EObject>> includedStereotypes, boolean bypassForInnerClassifiers, boolean noSharedAggregation) {
		EList<Classifier> result = new UniqueEList<Classifier>();

		Iterator<Property> attributes;
		attributes = current.getAttributes().iterator();
		while (attributes.hasNext()) {
			Property currentAttribute = attributes.next();
			Type type = currentAttribute.getType();
			
			if  (type != null) {
				// Check type is inner classifier
				if (bypassForInnerClassifiers && !(type.getOwner() instanceof Package)) { // if we force inner class types to be processed
					addFarthestOwnerType(type, result);
				} else {
					// Check other conditions
					boolean ok = true;
					
					// Check attribute is shared aggregation
					if (noSharedAggregation && currentAttribute.getAggregation() == AggregationKind.SHARED_LITERAL) {
						ok = false;
					}
					// Check attribute does not have an excluded stereotype
					if (ok) {
						if (excludedStereotypes != null && GenUtils.hasAnyStereotype(currentAttribute, excludedStereotypes)) {
							ok = false;
						}
					}
					// Check attribute has an included stereotype
					if (ok) {
						if (includedStereotypes != null && !GenUtils.hasAnyStereotype(currentAttribute, includedStereotypes)) {
							ok = false;
						}
					}
					
					// All ok
					if (ok) {
						addFarthestOwnerType(type, result);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Retrieve a list of types of attributes, with SHARED aggregation, that belong to the current classifier.
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @return Collection of classifiers which are the types of the attributes
	 */
	public static EList<Classifier> getTypesViaSharedAggregationAttributes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();

		Iterator<Property> attributes;
		attributes = current.getAttributes().iterator();
		while (attributes.hasNext()) {
			Property currentAttribute = attributes.next();
			Type type = currentAttribute.getType();
			
			if  (type != null) {
				if (currentAttribute.getAggregation() == AggregationKind.SHARED_LITERAL) {
					addFarthestOwnerType(type, result);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Retrieve the operations in the current classifier. For each
	 * operation, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 *
	 * @param current
	 *            Classifier on which the operations are searched for
	 * @return Collection of classifiers which are the types of the operation parameters
	 */
	public static EList<Classifier> getTypesViaOperations(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Operation operation : current.getOperations()) {
			for (Parameter param : operation.getOwnedParameters()) {
				Type type = param.getType();
				addFarthestOwnerType(type, result);
				
				
			}
			
			for (Type type : operation.getRaisedExceptions()) {
				addFarthestOwnerType(type, result);
			}
			
		}
		return result;
	}
	
	/**
	 * Retrieve the operations in the current classifier. For each
	 * operation, collect direct types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 *
	 * @param current
	 *            Classifier on which the operations are searched for
	 * @return Collection of classifiers which are the types of the operation parameters
	 */
	public static EList<Classifier> getDirectTypesViaOperations(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Operation operation : current.getOperations()) {
			for (Parameter param : operation.getOwnedParameters()) {
				Type type = param.getType();
				addClosestOwnerType(type, result);
			}
			
			for (Type type : operation.getRaisedExceptions()) {
				addClosestOwnerType(type, result);
			}
		}
		return result;
	}
	
	/**
	 * Retrieve the operations in the current classifier. For each
	 * operation, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 * We check if operations and parameters must have or not have some stereotypes.
	 *
	 * @param current
	 *            Classifier on which the operations are searched for
	 * @param excludedOperationStereotypes
	 *            List of ALL stereotypes that must not be applied to an operation
	 * @param includedOperationStereotypes
	 *            List of ANY stereotype that must be applied to an operation (at least one)
	 * @param excludedParameterStereotypes
	 *            List of ALL stereotypes that must not be applied to a parameter
	 * @param includedParameterStereotypes
	 *            List of ANY stereotype that must be applied to a parameter (at least one)
	 * @param bypassForInnerClassifiers
	 *            Always include types that are inner classifiers
	 * @return Collection of classifiers which are the types of the operation parameters
	 */
	public static EList<Classifier> getTypesViaOperations(Classifier current,
			EList<Class<? extends EObject>> excludedOperationStereotypes,
			EList<Class<? extends EObject>> includedOperationStereotypes,
			EList<Class<? extends EObject>> excludedParameterStereotypes,
			EList<Class<? extends EObject>> includedParameterStereotypes,
			boolean bypassForInnerClassifiers) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		
		for (Operation operation : current.getOperations()) {
			boolean ok = true;
			
			if (excludedOperationStereotypes != null && GenUtils.hasAnyStereotype(operation, excludedOperationStereotypes)) {
				ok = false;
			}
			
			if (includedOperationStereotypes != null && !GenUtils.hasAnyStereotype(operation, includedOperationStereotypes)) {
				ok = false;
			}
			
			boolean operationOk = ok;
			if (operationOk || bypassForInnerClassifiers) {
				for (Parameter param : operation.getOwnedParameters()) {
					Type type = param.getType();
					if  (type != null) {
						if (bypassForInnerClassifiers && !(type.getOwner() instanceof Package)) { // if we force inner class types to be processed
							addFarthestOwnerType(type, result);
						} else if (operationOk) {
							ok = true;
							
							if (excludedParameterStereotypes != null && GenUtils.hasAnyStereotype(param, excludedParameterStereotypes)) {
								ok = false;
							}
							
							if (includedParameterStereotypes != null && !GenUtils.hasAnyStereotype(param, includedParameterStereotypes)) {
								ok = false;
							}
							
							if (ok) {
								addFarthestOwnerType(type, result);
							}
						}
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Retrieve the opaque behaviors in the current classifier, without specification.
	 * For each opaque behavior, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 *
	 * @param current
	 *            Classifier on which the opaque behaviors are searched for
	 * @return Collection of classifiers which are the types of the opaque behavior parameters
	 */
	public static EList<Classifier> getTypesViaOpaqueBehaviors(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element element : current.getOwnedElements()) {
			if (element instanceof OpaqueBehavior) {
				OpaqueBehavior opaqueBehavior = (OpaqueBehavior) element;
				if (opaqueBehavior.getSpecification() == null) {
					for (Parameter param : opaqueBehavior.getOwnedParameters()) {
						Type type = param.getType();
						addFarthestOwnerType(type, result);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Retrieve the opaque behaviors in the current classifier, without specification.
	 * For each opaque behavior, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 * We check if opaque behavior and parameters must have or not have some stereotypes.
	 *
	 * @param current
	 *            Classifier on which the opaque behaviors are searched for
	 * @param excludedOperationStereotypes
	 *            List of ALL stereotypes that must not be applied to an opaque behavior
	 * @param includedOperationStereotypes
	 *            List of ANY stereotype that must be applied to an opaque behavior (at least one)
	 * @param excludedParameterStereotypes
	 *            List of ALL stereotypes that must not be applied to a parameter
	 * @param includedParameterStereotypes
	 *            List of ANY stereotype that must be applied to a parameter (at least one)
	 * @param bypassForInnerClassifiers
	 *            Always include types that are inner classifiers
	 * @return Collection of classifiers which are the types of the opaque behavior parameters
	 */
	public static EList<Classifier> getTypesViaOpaqueBehaviors(Classifier current,
			EList<Class<? extends EObject>> excludedOperationStereotypes,
			EList<Class<? extends EObject>> includedOperationStereotypes,
			EList<Class<? extends EObject>> excludedParameterStereotypes,
			EList<Class<? extends EObject>> includedParameterStereotypes,
			boolean bypassForInnerClassifiers) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		
		for (Element element : current.getOwnedElements()) {
			if (element instanceof OpaqueBehavior) {
				OpaqueBehavior opaqueBehavior = (OpaqueBehavior) element;
				if (opaqueBehavior.getSpecification() == null) {
					boolean ok = true;
					
					if (excludedOperationStereotypes != null && GenUtils.hasAnyStereotype(opaqueBehavior, excludedOperationStereotypes)) {
						ok = false;
					}
					
					if (includedOperationStereotypes != null && !GenUtils.hasAnyStereotype(opaqueBehavior, includedOperationStereotypes)) {
						ok = false;
					}
					
					if (ok) {
						for (Parameter param : opaqueBehavior.getOwnedParameters()) {
							Type type = param.getType();
							if  (type != null) {
								if (bypassForInnerClassifiers && !(type.getOwner() instanceof Package)) { // if we force inner class types to be processed
									addFarthestOwnerType(type, result);
								} else {
									ok = true;
									
									if (excludedParameterStereotypes != null && GenUtils.hasAnyStereotype(param, excludedParameterStereotypes)) {
										ok = false;
									}
									
									if (includedParameterStereotypes != null && !GenUtils.hasAnyStereotype(param, includedParameterStereotypes)) {
										ok = false;
									}
									
									if (ok) {
										addFarthestOwnerType(type, result);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Retrieves a list of types of attributes of inner classifiers of the current classifier
	 * 
	 * @param current
	 *            Class on which the attributes are searched
	 * @return collection of classes which are the types of the operations parameters
	 */
	public static EList<Classifier> getInnerClassifierTypes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		result.addAll(getInnerClassifierTypesViaAttributes(current));
		result.addAll(getInnerClassifierTypesViaOperations(current));
		return result;
	}
	
	/**
	 * Retrieves a list of direct types of attributes of inner classifiers of the current classifier
	 * 
	 * @param current
	 *            Class on which the attributes are searched
	 * @return collection of classes which are the types of the operations parameters
	 */
	public static EList<Classifier> getDirectInnerClassifierTypes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		result.addAll(getDirectInnerClassifierTypesViaAttributes(current));
		result.addAll(getDirectInnerClassifierTypesViaOperations(current));
		return result;
	}
	
	/**
	 * Retrieve a list of types of attributes of inner classifiers that belong to the current classifier.
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @return Collection of classifiers which are the type of the attributes of inner classifiers
	 */
	public static EList<Classifier> getInnerClassifierTypesViaAttributes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getTypesViaAttributes((Classifier) ownedElement));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve a list of direct types of attributes of inner classifiers that belong to the current classifier.
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @return Collection of classifiers which are the type of the attributes of inner classifiers
	 */
	public static EList<Classifier> getDirectInnerClassifierTypesViaAttributes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getDirectTypesViaAttributes((Classifier) ownedElement));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve a list of types of attributes of inner classifiers that belong to the current classifier. Filter by stereotypes.
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @param excludedStereotypes
	 *            List of ALL stereotypes that must no be applied
	 * @param includedStereotypes
	 *            List of ANY stereotype that must no be applied (at least one)
	 * @param bypassForInnerClassifiers
	 *            Always include types that are inner classifiers
	 * @param noSharedAggregation
	 *            Always exclude attributes with a shared aggregation kind
	 * @return Collection of classifiers which are the types of the attributes of inner classifiers
	 */
	public static EList<Classifier> getInnerClassifierTypesViaAttributes(Classifier current, EList<Class<? extends EObject>> excludedStereotypes, EList<Class<? extends EObject>> includedStereotypes, boolean bypassForInnerClassifiers, boolean noSharedAggregation) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getTypesViaAttributes((Classifier) ownedElement, excludedStereotypes, includedStereotypes, bypassForInnerClassifiers, noSharedAggregation));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve a list of types of attributes, of shared aggregation, of inner classifiers that belong to the current classifier.
	 *
	 * @param current
	 *            Class on which the attributes are searched
	 * @return Collection of classifiers which are the types of the attributes of inner classifiers
	 */
	public static EList<Classifier> getInnerClassifierTypesViaSharedAggregationAttributes(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getTypesViaSharedAggregationAttributes((Classifier) ownedElement));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve the operations of inner classifiers of the current classifier. For each
	 * operation, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 *
	 * @param current
	 *            Classifier on which the operations are searched for
	 * @return Collection of classifiers which are the types of the parameters of inner classifiers
	 */
	public static EList<Classifier> getInnerClassifierTypesViaOperations(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getTypesViaOperations((Classifier) ownedElement));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve the operations of direct inner classifiers of the current classifier. For each
	 * operation, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 *
	 * @param current
	 *            Classifier on which the operations are searched for
	 * @return Collection of classifiers which are the types of the parameters of inner classifiers
	 */
	public static EList<Classifier> getDirectInnerClassifierTypesViaOperations(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getDirectTypesViaOperations((Classifier) ownedElement));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve the operations of inner classifiers of the current classifier. For each
	 * operation, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 * We check if operations and parameters must have or not have some stereotypes.
	 *
	 * @param current
	 *            Classifier on which the operations are searched for
	 * @param excludedOperationStereotypes
	 *            List of ALL stereotypes that must not be applied to an operation
	 * @param includedOperationStereotypes
	 *            List of ANY stereotype that must be applied to an operation (at least one)
	 * @param excludedParameterStereotypes
	 *            List of ALL stereotypes that must not be applied to a parameter
	 * @param includedParameterStereotypes
	 *            List of ANY stereotype that must be applied to a parameter (at least one)
	 * @return Collection of classifiers which are the types of the parameters of inner classifiers
	 */
	public static EList<Classifier> getInnerClassifierTypesViaOperations(Classifier current,
			EList<Class<? extends EObject>> excludedOperationStereotypes,
			EList<Class<? extends EObject>> includedOperationStereotypes,
			EList<Class<? extends EObject>> excludedParameterStereotypes,
			EList<Class<? extends EObject>> includedParameterStereotypes,
			boolean bypassForInnerClassifiers) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getTypesViaOperations((Classifier) ownedElement,
						excludedOperationStereotypes,
						includedOperationStereotypes,
						excludedParameterStereotypes,
						includedParameterStereotypes,
						bypassForInnerClassifiers));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve the opaque behaviors of inner classifiers of the current classifier, without specification.
	 * For each opaque behavior, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 *
	 * @param current
	 *            Classifier on which the opaque behaviors are searched for
	 * @return Collection of classifiers which are the types of the parameters of inner classifiers
	 */
	public static EList<Classifier> getInnerClassifierTypesViaOpaqueBehaviors(Classifier current) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getTypesViaOpaqueBehaviors((Classifier) ownedElement));
			}
		}
		return result;
	}
	
	/**
	 * Retrieve the opaque behaviors of inner classifiers of the current classifier, without specification.
	 * For each opaque behavior, collect types of its parameters.
	 * This method thus finds types, on
	 * which the signature depends.
	 * We check if opaque behavior and parameters must have or not have some stereotypes.
	 *
	 * @param current
	 *            Classifier on which the opaque behaviors are searched for
	 * @param excludedOperationStereotypes
	 *            List of ALL stereotypes that must not be applied to an opaque behavior
	 * @param includedOperationStereotypes
	 *            List of ANY stereotype that must be applied to an opaque behavior (at least one)
	 * @param excludedParameterStereotypes
	 *            List of ALL stereotypes that must not be applied to a parameter
	 * @param includedParameterStereotypes
	 *            List of ANY stereotype that must be applied to a parameter (at least one)
	 * @return Collection of classifiers which are the types of the opaque behavior parameters of inner classifiers
	 */
	
	public static EList<Classifier> getInnerClassifierTypesViaOpaqueBehaviors(Classifier current,
			EList<Class<? extends EObject>> excludedOperationStereotypes,
			EList<Class<? extends EObject>> includedOperationStereotypes,
			EList<Class<? extends EObject>> excludedParameterStereotypes,
			EList<Class<? extends EObject>> includedParameterStereotypes,
			boolean bypassForInnerClassifiers) {
		EList<Classifier> result = new UniqueEList<Classifier>();
		for (Element ownedElement : current.allOwnedElements()) {
			if (ownedElement instanceof Classifier) {
				result.addAll(getTypesViaOpaqueBehaviors((Classifier) ownedElement,
						excludedOperationStereotypes,
						includedOperationStereotypes,
						excludedParameterStereotypes,
						includedParameterStereotypes,
						bypassForInnerClassifiers));
			}
		}
		return result;
	}

	/**
	 * Return a list of classifiers that are referenced by relationships, i.e.
	 * dependencies or associations
	 *
	 * @param current
	 * @return
	 */
	public static EList<Classifier> getTypesViaRelationships(Classifier current) {
		EList<Classifier> classifiers = new UniqueEList<Classifier>();

		for (DirectedRelationship relationship : current.getSourceDirectedRelationships()) {

			if (relationship.getTargets().size() > 0) {
				// there should always be at least one element in the target
				// list and it should be a classifier, but better check.
				Element element = relationship.getTargets().get(0);
				addFarthestOwnerType(element, classifiers);
			}
		}
		return classifiers;
	}

	/**
	 * Return a list of classifiers that are referenced via dependencies
	 *
	 * @param current
	 * @return
	 */
	public static EList<Classifier> getTypesViaDependencies(Classifier current) {
		EList<Classifier> classifiers = new UniqueEList<Classifier>();

		for (DirectedRelationship relationship : current.getSourceDirectedRelationships()) {
			if (relationship instanceof Dependency) {
				if (relationship.getTargets().size() > 0) {
					// there should always be at least one element in the target
					// list and it should be a classifier, but better check.
					Element element = relationship.getTargets().get(0);
					addFarthestOwnerType(element, classifiers);
				}
			}
		}
		return classifiers;
	}
	
	/**
	 * Return a list of classifiers that are referenced via dependencies
	 *
	 * @param current
	 * @return
	 */
	public static EList<Classifier> getDirectTypesViaDependencies(Classifier current) {
		EList<Classifier> classifiers = new UniqueEList<Classifier>();

		for (DirectedRelationship relationship : current.getSourceDirectedRelationships()) {
			if (relationship instanceof Dependency) {
				if (relationship.getTargets().size() > 0) {
					// there should always be at least one element in the target
					// list and it should be a classifier, but better check.
					Element element = relationship.getTargets().get(0);
					addClosestOwnerType(element, classifiers);
				}
			}
		}
		
		for (Element owned : current.getOwnedElements()) {
			if (owned instanceof Classifier) {
				classifiers.addAll(getDirectTypesViaDependencies((Classifier) owned));
			}
		}
		
		return classifiers;
	}

	/**
	 * Return a list of classifiers that are referenced via all kinds of relations except
	 * dependencies
	 *
	 * @param current
	 * @return
	 */
	public static EList<Classifier> getTypesViaRelationshipsNoDeps(Classifier current) {
		EList<Classifier> classifiers = new UniqueEList<Classifier>();

		for (DirectedRelationship relationship : current.getSourceDirectedRelationships()) {
			if (!(relationship instanceof Dependency)) {
				if (relationship.getTargets().size() > 0) {
					// there should always be at least one element in the target
					// list and it should be a classifier, but better check.
					Element element = relationship.getTargets().get(0);
					addFarthestOwnerType(element, classifiers);
				}
			}
		}
		
		for (Element owned : current.getOwnedElements()) {
			if (owned instanceof Classifier) {
				classifiers.addAll(getTypesViaRelationshipsNoDeps((Classifier) owned));
			}
		}
		
		return classifiers;
	}
	
	/**
	 * Return a list of classifiers that are referenced via all kinds of relations except
	 * dependencies
	 *
	 * @param current
	 * @return
	 */
	public static EList<Classifier> getDirectTypesViaRelationshipsNoDeps(Classifier current) {
		EList<Classifier> classifiers = new UniqueEList<Classifier>();

		for (DirectedRelationship relationship : current.getSourceDirectedRelationships()) {
			if (!(relationship instanceof Dependency)) {
				if (relationship.getTargets().size() > 0) {
					// there should always be at least one element in the target
					// list and it should be a classifier, but better check.
					Element element = relationship.getTargets().get(0);
					addClosestOwnerType(element, classifiers);
				}
			}
		}
		
		for (Element owned : current.getOwnedElements()) {
			if (owned instanceof Classifier) {
				classifiers.addAll(getDirectTypesViaRelationshipsNoDeps((Classifier) owned));
			}
		}
		
		return classifiers;
	}


	/**
	 * Return the qualified name of a named element, but use "_" instead of "::" as separator
	 *
	 * @param ne
	 *            a named element
	 * @return the fully qualified name with "_" as separator character
	 */
	public static String getFullName(NamedElement ne) {
		return ne.getQualifiedName().replace("::", "_"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * Return the qualified name of a named element, but use separator instead of "::" as separator
	 *
	 * @param ne
	 *            a named element
	 * @return the fully qualified name with separator character
	 */
	public static String getFullName(NamedElement ne, String separator) {
		return ne.getQualifiedName().replace("::", separator); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * Return the qualified name of a named element, but use separator instead of "::" as separator, and check if project name should be considered
	 *
	 * @param ne
	 *            a named element
	 * @return the fully qualified name with separator character, without the project name optionally
	 */
	public static String getFullName(NamedElement ne, String separator, boolean withProjectName) {
		checkProxy(ne);
		String qName = getFullName(ne, separator);
		if (!withProjectName) {
			return getQualifiedNameWithoutProject(ne, qName, separator);
		}
		return qName;
	}

	/**
	 * return the full name in upper case
	 *
	 * @param ne
	 * @return
	 */
	public static String getFullNameUC(NamedElement ne) {
		return ne.getQualifiedName().replace("::", "_").toUpperCase(); //$NON-NLS-1$ //$NON-NLS-2$
	}


	/**
	 * Retrieve the comments associated with an element
	 * TODO: check whether comment's annotated element link belongs to element in question
	 *
	 * @param element
	 * @return
	 */
	public static String getComments(Element element) {
		String commentText = ""; //$NON-NLS-1$
		for (Comment comment : element.getOwnedComments()) {
			// remove eventual CRs (avoid confusion in Acceleo template which adds " *" after line breaks)
			commentText += cleanCR(comment.getBody());
		}
		return commentText;
	}

	/**
	 * Return a list of dependent package (the list of dependent
	 * elements filtered for packages)
	 *
	 * @param pkg
	 * @return
	 */
	public static EList<Package> getUsedPackages(Package pkg) {
		EList<Package> result = new UniqueEList<Package>();
		for (Element depElement : pkg.getClientDependencies()) {
			if (depElement instanceof Package) {
				result.add((Package) depElement);
			}
		}
		return result;
	}

	/**
	 * Return a list of dependent classifiers (the list of dependent
	 * elements filtered for classifiers)
	 *
	 * @param pkg
	 * @return
	 */
	public static EList<Classifier> getUsedClassifiers(Classifier cls) {
		EList<Classifier> result = new BasicEList<Classifier>();
		for (Element depElement : cls.getClientDependencies()) {
			addFarthestOwnerType(depElement, result);
		}
		return result;
	}
	
	/**
	 * Adds the first element owned by a package in a classifier's namespace
	 * 
	 * @param classifier
	 * @return
	 */
	public static void addFarthestOwnerType(Element element, EList<Classifier> result) {
		if (element == null || result == null) {
			return;
		}
		
		// TODO why was this condition added?
		// We don't need the namespace of a primitive type that is not defined since the namespace won't appear before the primitive type in the code
		/*if (element instanceof PrimitiveType && !GenUtils.hasStereotype(element, "C_Cpp::Typedef")) {
			return;
		}*/
		
		if (element.getOwner() instanceof Package && element instanceof Classifier) {
			result.add((Classifier) element);
		} else { // Type is an inner class. We want to return a classifier C directly owned by a package since it is "C.h" that should be included
			addFarthestOwnerType(element.getOwner(), result);
		}
	}
	
	/**
	 * Adds the first element that is a classifier
	 * 
	 * @param classifier
	 * @return
	 */
	public static void addClosestOwnerType(Element element, EList<Classifier> result) {
		if (element == null || result == null) {
			return;
		}
		
		if (element instanceof Classifier) {
			result.add((Classifier) element);
		} else {
			addClosestOwnerType(element.getOwner(), result);
		}
	}
	
	/**
	 * Get the namespace of the farthest classifier owner that owns an operation
	 * 
	 * @param op
	 * @return
	 */
	public static String getNestedOperationFarthestClassifierOwnerNamespace(Operation op) {
		StringBuffer buffer = new StringBuffer(""); //$NON-NLS-1$
		if (op != null && op.getOwner() instanceof Classifier) {
			getFarthestOwnerNamespace(op.getOwner(), buffer);
		}
		return buffer.toString();
	}
	
	/**
	 * Get the namespace of the farthest classifier owner that owns an operation
	 * 
	 * @param behavior
	 * @return
	 */
	public static String getNestedBehaviorFarthestClassifierOwnerNamespace(OpaqueBehavior behavior) {
		StringBuffer buffer = new StringBuffer(""); //$NON-NLS-1$
		if (behavior != null && behavior.getOwner() instanceof Classifier) {
			getFarthestOwnerNamespace(behavior.getOwner(), buffer);
		}
		return buffer.toString();
	}
	
	/**
	 * Build a namespace to the farthest owner (i.e. owned by a package) of some element
	 *  
	 * @param element
	 * @param result
	 */
	private static void getFarthestOwnerNamespace(Element element, StringBuffer result) {
		if (element == null || result == null) {
			return;
		}
		
		if (element.getOwner() instanceof Package) {
			result.insert(0, ((Classifier) element).getName());
		} else {
			result.insert(0, "::" + ((Classifier) element).getName()); //$NON-NLS-1$
			getFarthestOwnerNamespace(element.getOwner(), result);
		}
	}

	/**
	 * Return the qualified name of a package, but use "/" instead of "::" as separator
	 *
	 * @param pkg
	 * @return
	 */
	public static String getFullPath(Package pkg) {
		return pkg.getQualifiedName().replace("::", "/"); //$NON-NLS-1$//$NON-NLS-2$
	}
	
	/**
	 * Return the qualified name of a package, but use passed separator instead of "::" as separator
	 *
	 * @param pkg
	 * @return the fully qualified name with separator character, without the project name optionally
	 */
	public static String getFullPath(Package pkg, String seperator) {
		return pkg.getQualifiedName().replace("::", seperator); //$NON-NLS-1$//$NON-NLS-2$
	}
	
	/**
	 * Return the qualified name of a package, but use separator instead of "::" as separator, and check if project name should be considered
	 *
	 * @param pkg
	 * @return the fully qualified name with separator character, without the project name optionally
	 */
	public static String getFullPath(Package pkg, String separator, boolean withProjectName) {
		String qName = getFullPath(pkg, separator);
		if (!withProjectName) {
			return getQualifiedNameWithoutProject(pkg, qName, separator);
		}
		return qName;
	}
	
	private static String getQualifiedNameWithoutProject(NamedElement ne, String qName, String separator) {
		org.eclipse.uml2.uml.Package root = PackageUtil.getRootPackage(ne);
		if (qName != null && root != null && GenUtils.hasStereotype(root, Project.class)) {
			if (qName.equals(root.getName())) {
				return "";
			}
			
			if (qName.startsWith(root.getName())) {
				return qName.replaceFirst(root.getName() + separator, "");
			}
		}
		
		return qName;
	}

	/**
	 * Is a certain stereotype applied?
	 *
	 * @param element
	 * @param stereotype
	 *            fully qualified stereotype name
	 * @return
	 */
	public static boolean hasStereotype(Element element, String stereotName) {
		return element.getAppliedStereotype(stereotName) != null;
	}

	/**
	 * Is a certain stereotype applied?
	 *
	 * @param element
	 *            a UML element
	 * @param stereotype
	 *            The class of an element of a static profile
	 * @return
	 */
	public static boolean hasStereotype(Element element, java.lang.Class<? extends EObject> clazz) {
		for (EObject stereoApplication : element.getStereotypeApplications()) {
			// check whether the stereotype is a super-class of the passed parameter clazz
			if (clazz.isAssignableFrom(stereoApplication.getClass())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Is a list of stereotypes all applied?
	 *
	 * @param element
	 *            a UML element
	 * @param stereotypes
	 *            The list of classes of an element of a static profile
	 * @return
	 */
	public static boolean hasAllStereotypes(Element element, EList<java.lang.Class<? extends EObject>> stereotypes) {
		for (EObject stereoApplication : element.getStereotypeApplications()) {
			// check whether the stereotype is a super-class of the passed parameter clazz
			for (Class<? extends EObject> stereotype : stereotypes) {
				if (!stereotype.isAssignableFrom(stereoApplication.getClass())) {
					return false;
				}
			}
			
		}
		return true;
	}
	
	/**
	 * Is a any stereotype in a list applied?
	 *
	 * @param element
	 *            a UML element
	 * @param stereotypes
	 *            The list of classes of an element of a static profile
	 * @return
	 */
	public static boolean hasAnyStereotype(Element element, EList<java.lang.Class<? extends EObject>> stereotypes) {
		for (EObject stereoApplication : element.getStereotypeApplications()) {
			// check whether the stereotype is a super-class of the passed parameter clazz
			for (Class<? extends EObject> stereotype : stereotypes) {
				if (stereotype.isAssignableFrom(stereoApplication.getClass())) {
					return true;
				}
			}
			
		}
		return false;
	}


	/**
	 * Is a certain stereotype applied?
	 * In case of Java, we use the class above (without the A) prefix. In case of Acceleo, a stereotype
	 * such as C_Cpp::Include is passed as EClass and we therefore use this operation from Acceleo.
	 *
	 * @param element
	 * @param definition
	 *            The eClass associated with the stereotype name (its definition)
	 * @return
	 */
	public static boolean hasStereotypeA(Element element, EClass definition) {
		if (element == null) {
			// make query more robust
			return false;
		}
		for (EObject stereoApplication : element.getStereotypeApplications()) {
			// check whether the stereotype application has the right eClass
			if (stereoApplication.eClass() == definition) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verify if an Element or its parent Elements have a stereotype. Pass the class associated with a stereotype
	 *
	 * @param elt
	 *            Element used.
	 * @param clazz
	 *            the class associated with a stereotype in a static profile
	 *
	 * @return true if found. false otherwise
	 */
	public static boolean hasStereotypeTree(Element elt, java.lang.Class<? extends EObject> clazz)
	{

		if (hasStereotype(elt, clazz)) {
			return true;
		}
		else {
			Element owner = elt.getOwner();
			if (owner != null) {
				return hasStereotypeTree(owner, clazz);
			}
			else {
				return false;
			}
		}
	}


	/**
	 * Verify if an Element or its parent Elements have a stereotype. Pass the definition of the stereotype
	 *
	 * @param elt
	 *            Element used.
	 * @param definition
	 *            The stereotype definition
	 * @return true if found. false otherwise
	 */
	public static boolean hasStereotypeTree(Element elt, EClass definition)
	{
		Element owner;

		if (hasStereotypeA(elt, definition)) {
			return true;
		} else if ((owner = elt.getOwner()) != null) {
			return hasStereotypeTree(owner, definition);
		} else {
			return false;
		}
	}


	/**
	 * return the first occurrence of a stereotype application in the ownership tree
	 *
	 * @param elt
	 *            an element
	 * @param definition
	 *            the definition of a stereotype (its eClass)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends EObject> T getApplicationTree(Element elt, java.lang.Class<T> clazz)
	{
		EObject application = UMLUtil.getStereotypeApplication(elt, clazz);
		if (application != null) {
			return (T) application;
		}
		else {
			Element owner = elt.getOwner();
			if (owner != null) {
				return getApplicationTree(owner, clazz);
			}
			else {
				return null;
			}
		}
	}


	/**
	 * return the first occurrence of a stereotype application in the ownership tree
	 * Variant of @see getApplicationTree that is useful for Acceleo
	 *
	 * @param elt
	 *            an element
	 * @param definition
	 *            the definition of a stereotype (its eClass)
	 * @return
	 */
	public static EObject getApplicationTreeA(Element elt, EClass definition)
	{
		EObject application = getApplicationA(elt, definition);
		if (application != null) {
			return application;
		}
		else {
			Element owner = elt.getOwner();
			if (owner != null) {
				return getApplicationTreeA(owner, definition);
			}
			else {
				return null;
			}
		}
	}


	/**
	 * Return a stereotype application when given the eClass of that application.
	 * In case of Java, we use the class above (without the A) prefix. In case of Acceleo, a stereotype
	 * such as C_Cpp::Include is passed as EClass and we therefore use this operation from Acceleo.
	 *
	 * @param element
	 *            the UML model element
	 * @param eClass
	 *            the eClass of the stereotype application
	 * @return
	 */
	public static EObject getApplicationA(Element element, EClass eClass) {
		for (EObject stereoApplication : element.getStereotypeApplications()) {
			// check whether the stereotype is an instance of the passed parameter clazz
			if (stereoApplication.eClass() == eClass) {
				return stereoApplication;
			}
		}
		return null;
	}


	/**
	 * @param operation
	 *            the operation
	 * @param selectedLanguage
	 *            the selected language
	 * @return Return the first body of a selected language that is provided by
	 *         one of the operation's methods
	 */
	public static String getBody(Operation operation, Pattern selectedLanguages) {
		for (Behavior behavior : operation.getMethods()) {
			if (behavior instanceof OpaqueBehavior) {
				return getBodyFromOB((OpaqueBehavior) behavior, selectedLanguages);
			}
		}
		return ""; //$NON-NLS-1$
	}


	/**
	 * @param ob
	 *            an opaque behavior
	 * @param selectedLanguage
	 *            the selected language, this may be a regular expression
	 * @return Return the first body of a selected language that is provided by
	 *         one of the operation's methods
	 */
	public static String getBodyFromOB(OpaqueBehavior ob, Pattern selectedLanguages) {
		Iterator<String> bodies = ob.getBodies().iterator();
		for (String language : ob.getLanguages()) {
			// additional sanity check: number of languages and number of bodies should be synchronized,
			// but there is no guarantee that this is the case
			if (bodies.hasNext()) {
				String body = bodies.next();
				Matcher matcher = selectedLanguages.matcher(language);
				if (matcher.matches()) {
					// additional "\r" confuses Acceleo
					return cleanCR(body);
				}
			}
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Remove <CR> from a String. These confuse Acceleo's indentation
	 *
	 * @param str
	 * @return
	 */
	public static String cleanCR(String str) {
		if (str == null) {
			return "// <null>"; //$NON-NLS-1$
		}
		return str.replace("\r", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}


	/**
	 * Avoid null strings, i.e. replace null strings by empty strings
	 *
	 * @param str
	 * @return
	 */
	public static String maskNull(String str) {
		if (str == null) {
			return ""; //$NON-NLS-1$
		}
		return str;
	}

	public static void checkProxy(EObject element) {
		if (element.eIsProxy()) {
			String msg = Messages.GenUtils_ElementIsProxy;
			if (element instanceof InternalEObject) {
				msg += " " + ((InternalEObject) element).eProxyURI(); //$NON-NLS-1$
			}
			throw new RuntimeException(msg);
		}
    }

	/**
	 * Return the relative path of ne2 as seen from ne1
	 * (might not always be useful, if includes are always done from a common root)
	 * TODO: incomplete, currently unused
	 *
	 * @param ne1
	 *            a named element
	 * @param ne2
	 *            a named element
	 * @return
	 */
	public static String getRelativePath(NamedElement ne1, NamedElement ne2) {
		// get common prefix
		EList<Namespace> ne1namespaces = ne1.allNamespaces();
		String path = ""; //$NON-NLS-1$
		for (Namespace ns : ne2.allNamespaces()) {
			if (ne1namespaces.contains(ns)) {
				// ns is a common prefix
				return ne2.getName();
			}
			path += "../"; //$NON-NLS-1$
		}
		return path;
	}

	/**
	 * Return the type of a behavior, i.e. the type of the first parameter with
	 * "return" direction
	 *
	 * @param behavior
	 *            a behavior
	 * @return the associated type
	 */
	public static Parameter returnResult(Behavior behavior) {
		for (Parameter parameter : behavior.getOwnedParameters()) {
			if (parameter.getDirection() == ParameterDirectionKind.RETURN_LITERAL) {
				return parameter;
			}
		}
		return null;
	}
	
	/**
	 * @param element
	 *            An element of the model
	 * @return the source folder from the Project stereotype, that is eventually applied to the
	 *         model root.
	 */
	public static String getSourceFolder(Element element) {
		Package rootPkg = PackageUtil.getRootPackage(element);
		if (rootPkg != null) {
			org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project projectStereo =
					UMLUtil.getStereotypeApplication(rootPkg, org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project.class);

			if (projectStereo != null) {
				return projectStereo.getSourceFolder() + "/";
			}
		}
		// default folder for generated code
		return "src-gen" + "/"; //$NON-NLS-1$
	}
}
