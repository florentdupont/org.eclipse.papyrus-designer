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

package org.eclipse.papyrus.designer.transformation.library.transformations;

import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.library.Messages;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StateMachine;

/**
 * Programming language neutral state-machine transformation that delegates to a language
 * specific variant
 */
public class CompleteStatemachine implements IM2MTrafoElem {

	@Override
	public void transformElement(M2MTrafo trafo, Element element) throws TransformationException {
		if (element instanceof Class) {
			Class clazz = (Class) element;
			if (clazz.getClassifierBehavior() instanceof StateMachine) {
				String language = DepUtils.getLanguageFromElement(clazz);
				IM2MTrafo trafoForLang = M2MTrafoExt.getM2MTrafoForLanguage(trafo, language);
				if (trafoForLang instanceof IM2MTrafoElem) {
					((IM2MTrafoElem) trafoForLang).transformElement(trafo, element);
				}
				else {
					throw new TransformationException(String.format(Messages.CompleteStatemachine_NoTransformationFound, language));
				}
			}
		}
	}
}
