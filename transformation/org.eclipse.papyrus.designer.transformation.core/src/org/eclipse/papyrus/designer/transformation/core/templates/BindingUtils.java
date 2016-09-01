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
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 */

package org.eclipse.papyrus.designer.transformation.core.templates;

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ConstInit;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.util.UMLUtil;

public class BindingUtils {

	/**
	 * Bind an operation by copying the signature from the actual.
	 *
	 * @param copier
	 *            the copier
	 * @param actual
	 *            the actual. If an operation, its signature is copied to the template
	 * @param operation
	 *            The operation template
	 * @return
	 */
	public static void instantiateOperation(Element actual, Operation operation) {
		try {
			TransformationContext.current.classifier = operation.getClass_();
			// set new name
			String newName = TextTemplateBinding.bind(operation.getName(), actual, null);
			operation.setName(newName);
			// perform binding in case of C++ initializer
			ConstInit cppConstInit = UMLUtil.getStereotypeApplication(operation, ConstInit.class);
			if (cppConstInit != null) {
				// TODO: specific to C++
				String init = cppConstInit.getInitialisation();
				String newInit = TextTemplateBinding.bind(init, actual);
				cppConstInit.setInitialisation(newInit);
			}
		} catch (TransformationException e) {
			// throw runtime exception
			throw new RuntimeException(String.format(Messages.TemplateInstantiationListener_TrafoException, e.getMessage()));
		}
	}

	/**
	 * Instantiate a behavior, i.e. check whether the body text consists of a text template
	 * reference.  If yes, instantiate the text template.
	 *
	 * @param copier
	 *            copier
	 * @param actual
	 *            actual in template instantiation
	 * @param opaqueBehavior
	 *            behavior with body containing eventually a text template reference
	 * @return instantiated (bound) behavior.
	 * @throws TransformationException
	 */
	public static void instantiateBehavior(Element actual, OpaqueBehavior opaqueBehavior) throws TransformationException {
		if (actual instanceof NamedElement) {
			String newName = TextTemplateBinding.bind(opaqueBehavior.getName(), actual, null);
			if (newName != null && !newName.equals(opaqueBehavior.getName())) {
				opaqueBehavior.setName(newName);
			}
		}
		EList<String> bodyList = opaqueBehavior.getBodies();
		for (int i = 0; i < bodyList.size(); i++) {
			String body = bodyList.get(i);
			TransformationContext.current.classifier = (Classifier) opaqueBehavior.getOwner();
			// pass qualified operation name as template name. Used to identify script in case of an error
			String newBody = TextTemplateBinding.bind(body, actual);
			if (newBody != null && !newBody.equals(body)) {
				bodyList.set(i, newBody);
			}
		}
	}
}
