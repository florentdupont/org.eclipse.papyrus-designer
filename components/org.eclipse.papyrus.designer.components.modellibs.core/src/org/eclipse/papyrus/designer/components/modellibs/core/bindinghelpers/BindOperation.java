/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.modellibs.core.bindinghelpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.components.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.components.transformation.core.templates.BindingUtils;
import org.eclipse.papyrus.designer.components.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException;
import org.eclipse.papyrus.designer.components.FCM.util.IBindingHelper;
import org.eclipse.papyrus.designer.components.modellibs.core.Activator;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.TemplateBinding;

/**
 * Bind an operation to an actual, i.e. evaluate the Acceleo template within the opaque behavior associated with
 * the operation.
 */
public class BindOperation implements IBindingHelper, PreCopyListener {

	private TemplateBinding binding;

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {

		if (sourceEObj instanceof Operation) {
			Operation operation = (Operation) sourceEObj;
			Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);

			Operation newOperation = BindingUtils.instantiateOperation(copy, actual, operation);
			for (Behavior method : operation.getMethods()) {
				if (method instanceof OpaqueBehavior) {
					try {
						Behavior newBehavior =
								BindingUtils.instantiateBehavior(copy, actual, (OpaqueBehavior) method);
						newBehavior.setSpecification(newOperation);
					} catch (TransformationException e) {
						Activator.log.error(e);
					}
				}
			}
			return newOperation;
		}
		return sourceEObj;
	}

	@Override
	public void handleElement(TemplateBinding binding, Element object) {
		this.binding = binding;
	}
}
