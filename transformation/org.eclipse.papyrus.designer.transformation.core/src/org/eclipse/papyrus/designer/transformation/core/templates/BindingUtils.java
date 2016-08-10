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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ConstInit;
import org.eclipse.papyrus.designer.transformation.base.utils.StUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
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
	public static Operation instantiateOperation(LazyCopier copier, Element actual, Operation operation) {
		try {
			Operation newOperation = copier.getCopy(operation);
			if (actual instanceof Operation) {
				for (Parameter parameter : ((Operation) actual).getOwnedParameters()) {
					Parameter newParam = EcoreUtil.copy(parameter); // copy parameter via EcoreUtil
					newParam.setType(copier.getCopy(parameter.getType()));
					newOperation.getOwnedParameters().add(newParam);
					StUtils.copyStereotypes(parameter, newParam); // copy stereotypes of the parameter
				}
			}
			TransformationContext.classifier = newOperation.getClass_();
			if (actual instanceof Classifier) {
				bindOperation(newOperation, (Classifier) actual);
			}
			String newName = TextTemplateBinding.bind(operation.getName(), actual, null);
			newOperation.setName(newName);

			return newOperation;
		} catch (TransformationException e) {
			// throw runtime exception
			throw new RuntimeException(String.format(Messages.TemplateInstantiationListener_TrafoException, e.getMessage()));
		}
	}

	/**
	 * Instantiate a behavior
	 *
	 * @param copier
	 *            copier
	 * @param actual
	 *            actual in template instantiation
	 * @param opaqueBehavior
	 *            behavior with body in form of an Acceleo template.
	 * @return instantiated (bound) behavior.
	 * @throws TransformationException
	 */
	public static OpaqueBehavior instantiateBehavior(LazyCopier copy, Element actual, OpaqueBehavior opaqueBehavior) throws TransformationException {
		OpaqueBehavior newBehavior = copy.getCopy(opaqueBehavior);
		if (actual instanceof NamedElement) {
			String newName = TextTemplateBinding.bind(opaqueBehavior.getName(), actual, null);
			newBehavior.setName(newName);
		}
		EList<String> bodyList = newBehavior.getBodies();
		for (int i = 0; i < bodyList.size(); i++) {
			String body = bodyList.get(i);
			TransformationContext.classifier = (Classifier) newBehavior.getOwner();
			// pass qualified operation name as template name. Used to identify script in case of an error
			String newBody = TextTemplateBinding.bind(body, actual);
			bodyList.set(i, newBody);
		}
		return newBehavior;
	}



	/**
	 * Bind C++ const initializer
	 *
	 * @param operation
	 * @param actual
	 * @throws TransformationException
	 */
	public static void bindOperation(Operation operation, Classifier actual) throws TransformationException {
		// perform binding in case of C++ initializer
		ConstInit cppConstInit = UMLUtil.getStereotypeApplication(operation, ConstInit.class);
		if (cppConstInit != null) {
			// TODO: specific to C++
			String init = cppConstInit.getInitialisation();
			String newInit = TextTemplateBinding.bind(init, actual);
			cppConstInit.setInitialisation(newInit);
		}
	}
}
