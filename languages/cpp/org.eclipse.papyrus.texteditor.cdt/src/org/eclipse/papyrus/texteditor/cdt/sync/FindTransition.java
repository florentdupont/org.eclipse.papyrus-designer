/*******************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ansgar Radermacher - ansgar.radermacher@cea.fr CEA LIST - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.texteditor.cdt.sync;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Transition;

/**
 * Find the behavior in the model from a given method in the code. Used during the
 * back synchronization from code to model.
 */
public class FindTransition {
	
	private static final String UNDERSCORE = "_"; //$NON-NLS-1$

	public static Behavior findBehavior(Classifier cl, String methodName) {
		for (Element element : cl.allOwnedElements()) {
			if (element instanceof Transition) {
				Transition transition = (Transition) element;
				Behavior effect = transition.getEffect();
				if ((effect != null) && behaviorMatches(effect, methodName)) {
					return transition.getEffect();
				}
			}
		}
		return null;
	}
	
	public static boolean behaviorMatches(Behavior behavior, String methodName) {
		return
			methodName.endsWith(NamedElement.SEPARATOR + behavior.getName()) ||
			methodName.endsWith(UNDERSCORE + behavior.getName()); 
	}
}
