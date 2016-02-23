/*******************************************************************************
 * Copyright (c) 2006 - 2012 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.codegen.utils;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.languages.common.base.GenUtils;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Inline;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ref;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PrimitiveType;

/**
 * A set of utility functions related to classes.
 *
 * @author ansgar (ansgar.radermacher@cea.fr)
 *
 */
public class ClassUtils {
	/**
	 * Calculate the list of classifiers that are required by another classifier
	 *
	 * @param currentClass
	 * @return
	 */
	public static EList<Classifier> requiredClassifiers(Classifier currentClass) {
		// Retrieve package used by current package (dependencies)
		// use a unique list to avoid duplicates
		EList<Classifier> usedClasses = new UniqueEList<Classifier>();

		// class attributes dependencies
		usedClasses.addAll(GenUtils.getTypesViaAttributes(currentClass));
		// operation parameters dependencies
		usedClasses.addAll(GenUtils.getTypesViaOperations(currentClass));
		// inner classifier dependencies
		usedClasses.addAll(GenUtils.getInnerClassifierTypes(currentClass));
		// realized interface dependencies
		if (currentClass instanceof org.eclipse.uml2.uml.Class) {
			org.eclipse.uml2.uml.Class clazz = (org.eclipse.uml2.uml.Class) currentClass;
			EList<Interface> implementedInterfaces = clazz.getImplementedInterfaces();
			usedClasses.addAll(implementedInterfaces);
		}
		// dependencies and associations
		usedClasses.addAll(GenUtils.getTypesViaRelationshipsNoDeps(currentClass));

		// template parameters are declared locally (if owned) and do not correspond to a file
		// that can be included
		usedClasses.removeAll(GenUtils.getTemplateParameteredElements(currentClass));
		return usedClasses;
	}
	
	/**
	 * Calculate the list of classifiers that needs to be included in a header file
	 *
	 * @param currentClass
	 * @return
	 */
	public static EList<Classifier> includedClassifiers(Classifier currentClass) {
		// Retrieve package used by current package (dependencies)
		// use a unique list to avoid duplicates
		EList<Classifier> usedClasses = new UniqueEList<Classifier>();

		// Lists of excluded/included stereotypes
		EList<Class<? extends EObject>> ptrRefStereotypes = new BasicEList<Class<? extends EObject>>();
		ptrRefStereotypes.add(Ptr.class);
		ptrRefStereotypes.add(Ref.class);
		
		EList<Class<? extends EObject>> noCodeGenInlineStereotypes = new BasicEList<Class<? extends EObject>>();
		noCodeGenInlineStereotypes.add(NoCodeGen.class);
		noCodeGenInlineStereotypes.add(Inline.class);
		
		EList<Class<? extends EObject>> inlineStereotypes = new BasicEList<Class<? extends EObject>>();
		inlineStereotypes.add(Inline.class);
		
		EList<Class<? extends EObject>> noCodeGenStereotypes = new BasicEList<Class<? extends EObject>>();
		noCodeGenStereotypes.add(NoCodeGen.class);
		
		// class attributes dependencies (non-ptr and non-ref)
		usedClasses.addAll(GenUtils.getTypesViaAttributes(currentClass, ptrRefStereotypes, null, true, true));
		addEnumerationsPrimitiveTypesExternalTypes(usedClasses, GenUtils.getTypesViaAttributes(currentClass));
		
		// class inline operation parameters dependencies (non-ptr and non-ref)
		usedClasses.addAll(GenUtils.getTypesViaOperations(currentClass, noCodeGenStereotypes, inlineStereotypes, ptrRefStereotypes, null, true));
		addEnumerationsPrimitiveTypesExternalTypes(usedClasses, GenUtils.getTypesViaOperations(currentClass));
		
		// inner classifier attribute dependencies (non-ptr and non-ref)
		usedClasses.addAll(GenUtils.getInnerClassifierTypesViaAttributes(currentClass, ptrRefStereotypes, null, true, true));
		addEnumerationsPrimitiveTypesExternalTypes(usedClasses, GenUtils.getInnerClassifierTypesViaAttributes(currentClass));
		
		// inner classifier operation parameters dependencies (non-ptr and non-ref)
		usedClasses.addAll(GenUtils.getInnerClassifierTypesViaOperations(currentClass, noCodeGenStereotypes, inlineStereotypes, ptrRefStereotypes, null, true));
		addEnumerationsPrimitiveTypesExternalTypes(usedClasses, GenUtils.getInnerClassifierTypesViaOperations(currentClass));
		
		// realized interface dependencies
		if (currentClass instanceof org.eclipse.uml2.uml.Class) {
			org.eclipse.uml2.uml.Class clazz = (org.eclipse.uml2.uml.Class) currentClass;
			EList<Interface> implementedInterfaces = clazz.getImplementedInterfaces();
			usedClasses.addAll(implementedInterfaces);
		}
		// dependencies and associations
		usedClasses.addAll(GenUtils.getTypesViaRelationshipsNoDeps(currentClass));

		// template parameters are declared locally (if owned) and do not correspond to a file
		// that can be included
		usedClasses.removeAll(GenUtils.getTemplateParameteredElements(currentClass));
		return usedClasses;
	}
	
	/**
	 * Calculate the list of classifiers that needs to be included in a body file
	 *
	 * @param currentClass
	 * @return
	 */
	public static EList<Classifier> includedImplementationClassifiers(Classifier currentClass) {
		// Retrieve package used by current package (dependencies)
		// use a unique list to avoid duplicates
		EList<Classifier> usedClasses = new UniqueEList<Classifier>();

		// Include all declared classifiers in header
		// Make sure to not include classifiers already included in header
		usedClasses.addAll(declaredClassifiers(currentClass));
		usedClasses.removeAll(includedClassifiers(currentClass));
		
		// dependency relationships
		usedClasses.addAll(GenUtils.getTypesViaDependencies(currentClass));
		
		// template parameters are declared locally (if owned) and do not correspond to a file
		// that can be included
		usedClasses.removeAll(GenUtils.getTemplateParameteredElements(currentClass));
		return usedClasses;
	}
	
	/**
	 * Calculate the list of classifiers that needs to be declared in a header file
	 *
	 * @param currentClass
	 * @return
	 */
	public static EList<Classifier> declaredClassifiers(Classifier currentClass) {
		EList<Classifier> usedClasses = new UniqueEList<Classifier>();

		// List of excluded/included stereotypes
		EList<Class<? extends EObject>> ptrRefStereotypes = new BasicEList<Class<? extends EObject>>();
		ptrRefStereotypes.add(Ptr.class);
		ptrRefStereotypes.add(Ref.class);
		
		EList<Class<? extends EObject>> inlineStereotypes = new BasicEList<Class<? extends EObject>>();
		inlineStereotypes.add(Inline.class);
		
		EList<Class<? extends EObject>> noCodeGenStereotypes = new BasicEList<Class<? extends EObject>>();
		noCodeGenStereotypes.add(NoCodeGen.class);
		
		// class attributes dependencies (only ptr and ref and shared aggregation)
		usedClasses.addAll(GenUtils.getTypesViaAttributes(currentClass, null, ptrRefStereotypes, false, false));
		usedClasses.addAll(GenUtils.getTypesViaSharedAggregationAttributes(currentClass));
		// operation parameters dependencies
		usedClasses.addAll(GenUtils.getTypesViaOperations(currentClass));
		usedClasses.removeAll(GenUtils.getTypesViaOperations(currentClass, noCodeGenStereotypes, inlineStereotypes, ptrRefStereotypes, null, true)); // Remove inline operation parameter types that have been included previously
		// no-specification opaque behavior dependencies
		usedClasses.addAll(GenUtils.getTypesViaOpaqueBehaviors(currentClass));
		
		// inner classifier attribute dependencies (only ptr and ref and shared aggregation)
		usedClasses.addAll(GenUtils.getInnerClassifierTypesViaAttributes(currentClass, null, ptrRefStereotypes, false, false));
		usedClasses.addAll(GenUtils.getInnerClassifierTypesViaSharedAggregationAttributes(currentClass));
		// inner classifier parameters dependencies
		usedClasses.addAll(GenUtils.getInnerClassifierTypesViaOperations(currentClass));
		usedClasses.removeAll(GenUtils.getInnerClassifierTypesViaOperations(currentClass, noCodeGenStereotypes, inlineStereotypes, ptrRefStereotypes, null, true)); // Remove inner classifier inline operation parameter types that have been included previously
		// inner classifier no-specification opaque behavior dependencies
		usedClasses.addAll(GenUtils.getInnerClassifierTypesViaOpaqueBehaviors(currentClass));
		
		// TODO inline operation body dependencies: how?
		
		// template parameters are declared locally (if owned) and do not correspond to a file
		// that can be included
		usedClasses.removeAll(GenUtils.getTemplateParameteredElements(currentClass));
		
		// Remove enumerations and primitive types
		List<Classifier> enumerationsAndPrimitiveTypes = new UniqueEList<Classifier>();
		for (Classifier classifier : usedClasses) {
			if ((classifier instanceof Enumeration) || (classifier instanceof PrimitiveType)) {
				if (classifier.getOwner() instanceof Package) {
					enumerationsAndPrimitiveTypes.add(classifier);
				}
			}
		}
		usedClasses.removeAll(enumerationsAndPrimitiveTypes);
		
		return usedClasses;
	}
	
	/**
	 * Retrieve the list of operations of classes nested in the current class
	 * without the operations directly owned by the current class
	 * 
	 * @param currentClass
	 * @return
	 */
	public static EList<Operation> nestedOperations(Classifier currentClass) {
		EList<Operation> nestedOperations = new UniqueEList<Operation>();
		
		for (Element element : currentClass.allOwnedElements()) {
			if (element instanceof Operation && !(element.getOwner().equals(currentClass))) {
				nestedOperations.add((Operation) element);
			}
		}
		
		return nestedOperations;
	}
	
	private static void addEnumerationsPrimitiveTypesExternalTypes(List<Classifier> usedClasses, List<Classifier> unfilteredClasses) {
		if (usedClasses != null && unfilteredClasses != null) {
			for (Classifier classifier : unfilteredClasses) {
				if ((classifier instanceof Enumeration) || (classifier instanceof PrimitiveType)) {
					if (classifier.getOwner() instanceof Package) {
						usedClasses.add(classifier);
					}
				} else if (GenUtils.hasStereotype(classifier, External.class)) {
					usedClasses.add(classifier);
				}
			}
		}
		
	}
}
