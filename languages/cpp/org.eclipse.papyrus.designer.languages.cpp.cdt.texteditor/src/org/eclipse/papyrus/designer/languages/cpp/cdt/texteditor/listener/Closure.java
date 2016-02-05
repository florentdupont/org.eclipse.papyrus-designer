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
 *  Ansgar Radermacher (CEA LIST) Ansgar.Radermacher@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.listener;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;

/**
 * Calculate the closure of classifiers that need to be re-generated, if a
 * classifier changes
 * 
 * There are two different reasons for the need to re-generate a classifier:
 * (1) one of the elements that it contains has changed
 * (2) an element outside the containment close is changed. An example is a superclass
 * that changes its name or an association target. In many cases, classifier depend
 * on names of outside elements, but the also require a regeneration if the element
 * is deleted.
 * We need to take the change-kind into account. E.g. if the name of an operation in A changes, we need to
 * re-generate class A, but we only need to regenerate class B (inheriting from A), if it actually
 * redefines that operation.
 */
public class Closure {
	public static EList<Classifier> calc(Classifier cl) {
		EList<Classifier> list = new BasicEList<Classifier>();
		calc(cl, list);
		return list;
	}

	public static void calc(Classifier cl, EList<Classifier> list) {
		if (cl != null) {
			for (Setting setting : UML2Util.getNonNavigableInverseReferences(cl)) {
				EObject eObj = setting.getEObject();
				if (eObj instanceof Element) {
					Classifier referencedCl = nearestClassifier(eObj);
					if (!list.contains(referencedCl) && (!(referencedCl instanceof Association))) {
						list.add(referencedCl);
						calc(referencedCl, list);
					}
				}
			}
		}
	}

	public static boolean isContained(EObject parent, EObject possibleChild) {
		while (possibleChild != null) {
			if (parent == possibleChild) {
				return true;
			}
			possibleChild = possibleChild.eContainer();
		}
		return false;
	}

	public static Classifier nearestClassifier(EObject eObj) {
		while (eObj != null) {
			if (eObj instanceof Classifier) {
				return (Classifier) eObj;
			}
			eObj = eObj.eContainer();
		}
		return null;
	}
}
