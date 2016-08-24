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
 *****************************************************************************/

package org.eclipse.papyrus.designer.transformation.library.transformations.bindinghelpers;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.templates.BindingUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateInstantiation;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TextTemplateBinding;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.library.Activator;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.Type;

/**
 * This binding helper loops over all operations of the actual template parameter
 * (typically an interface)
 *
 */
public class LoopOperations implements IM2MTrafo, PreCopyListener {

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {

		if (sourceEObj instanceof Operation) {
			Operation operation = (Operation) sourceEObj;

			TemplateBinding binding = TemplateInstantiation.context.getBinding();
			Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);

			if (!(actual instanceof Interface)) {
				return sourceEObj;
			}
			Interface passedActualIntf = (Interface) actual;
			Operation last = null;
			EList<Element> removalList = new BasicEList<Element>();
			for (Operation intfOperation : passedActualIntf.getAllOperations()) {
				for (Element removalElement : removalList) {
					copy.removeForCopy(removalElement); // enable subsequent instantiations
				}
				removalList.clear();
				last = BindingUtils.instantiateOperation(copy, intfOperation, operation);
				removalList.add(operation);
				for (Behavior method : operation.getMethods()) {
					if (method instanceof OpaqueBehavior) {
						try {
							Behavior newBehavior =
									BindingUtils.instantiateBehavior(copy, intfOperation, (OpaqueBehavior) method);
							newBehavior.setSpecification(last);
						} catch (TransformationException e) {
							Activator.log.error(e);
							;
						}
						// removalList.add(method);
						copy.removeForCopy(method); // enable subsequent instantiations
					}
				}
			}
			// from a logical viewpoint, we need to copy parameters & name, but not the
			// operation identity.
			copy.put(operation, last);
			return last;
			/*
			 * else { // not LOOP_OPERATIONS
			 * Operation newOperation = instantiateOperation(actual, template, operation, boundClass);
			 * for(Behavior method : operation.getMethods()) {
			 * if(method instanceof OpaqueBehavior) {
			 * Behavior newBehavior =
			 * instantiateBehavior(actual, template, (OpaqueBehavior)method);
			 * newBehavior.setSpecification(newOperation);
			 * }
			 * }
			 * return newOperation;
			 */
		}
		else if (sourceEObj instanceof EnumerationLiteral) {
			EnumerationLiteral literal = (EnumerationLiteral) sourceEObj;
			TemplateBinding binding = TemplateInstantiation.context.getBinding();
			Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);
			// Type passedActual = getPassedActual(template, actual, boundClass);
			Type passedActual = actual;
			if (!(passedActual instanceof Interface)) {
				return sourceEObj;
			}
			Interface passedActualIntf = (Interface) passedActual;
			EnumerationLiteral newLiteral = null;
			for (Operation intfOperation : passedActualIntf.getAllOperations()) {
				copy.removeForCopy(literal);
				newLiteral = copy.getCopy(literal);
				try {
					String newName = TextTemplateBinding.bind(literal.getName(), intfOperation, null);
					newLiteral.setName(newName);
				} catch (TransformationException e) {
					Activator.log.error(e);
					newLiteral.setName("none"); //$NON-NLS-1$
				}
			}
			return newLiteral;
		}
		return null;
	}
}
