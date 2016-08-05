package org.eclipse.papyrus.designer.transformation.library.bindinghelpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.extensions.IEmptyM2MTrafo;
import org.eclipse.papyrus.designer.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.templates.BindingUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.library.Activator;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.TemplateBinding;

/**
 * Bind an operation to an actual, i.e. evaluate the xtend template within the opaque behavior associated with
 * the operation.
 */
public class BindOperation implements IEmptyM2MTrafo, PreCopyListener {

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {

		if (sourceEObj instanceof Operation) {
			Operation operation = (Operation) sourceEObj;
			TemplateBinding binding = TransformationContext.getBinding();
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
}
