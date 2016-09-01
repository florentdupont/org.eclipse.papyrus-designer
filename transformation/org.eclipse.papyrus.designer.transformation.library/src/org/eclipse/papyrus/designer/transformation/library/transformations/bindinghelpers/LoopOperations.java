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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PostCopyListener;
import org.eclipse.papyrus.designer.transformation.core.templates.BindingUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateInstantiation;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TextTemplateBinding;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.library.Activator;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.Type;

/**
 * This binding helper loops over all operations of the actual template parameter
 * (an interface). For each, it creates a new copy of the operation and instantiates
 * the operation name as well as the body (a text template) with the passed actual.
 */
public class LoopOperations implements IM2MTrafo, PostCopyListener {

	@Override
	public void postCopyEObject(LazyCopier copier, EObject targetEObj) {

		if (targetEObj instanceof Operation) {
			Operation operation = (Operation) targetEObj;

			TemplateBinding binding = TemplateInstantiation.context.getBinding();
			Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);

			if (!(actual instanceof Interface)) {
				return;
			}
			Interface passedActualIntf = (Interface) actual;
			Iterator<Operation> intfOperationIter = passedActualIntf.getAllOperations().iterator();
			while (intfOperationIter.hasNext()) {
				Operation intfOperation = intfOperationIter.next();
				boolean last = intfOperationIter.hasNext();
				// create a copy, unless last element
				Operation operationCopy = last ? operation : EcoreUtil.copy(operation);
				operation.getClass_().getOwnedOperations().add(operationCopy);
				BindingUtils.instantiateOperation(intfOperation, operationCopy);
				for (Behavior method : operation.getMethods()) {
					if (method instanceof OpaqueBehavior) {
						try {
							OpaqueBehavior ob = (OpaqueBehavior) method;
							OpaqueBehavior obCopy = last ? ob : EcoreUtil.copy(ob);
							BindingUtils.instantiateBehavior(intfOperation, obCopy);
							obCopy.setSpecification(operationCopy);
						}
						catch (TransformationException e) {
							Activator.log.error(e);
						}
					}
				}
			}
		}
		else if (targetEObj instanceof EnumerationLiteral) {
			EnumerationLiteral literal = (EnumerationLiteral) targetEObj;
			TemplateBinding binding = TemplateInstantiation.context.getBinding();
			Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);
			// Type passedActual = getPassedActual(template, actual, boundClass);
			Type passedActual = actual;
			if (!(passedActual instanceof Interface)) {
				return;
			}
			Interface passedActualIntf = (Interface) passedActual;
			Iterator<Operation> intfOperationIter = passedActualIntf.getAllOperations().iterator();
			while (intfOperationIter.hasNext()) {
				Operation intfOperation = intfOperationIter.next();
				boolean last = intfOperationIter.hasNext();
				// create a copy, unless last element
				EnumerationLiteral listeralCopy = last ? literal : EcoreUtil.copy(literal);
				try {
					String newName = TextTemplateBinding.bind(literal.getName(), intfOperation, null);
					listeralCopy.setName(newName);
				} catch (TransformationException e) {
					Activator.log.error(e);
				}
			}
		}
	}
}
