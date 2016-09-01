package org.eclipse.papyrus.designer.transformation.library.transformations.bindinghelpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PostCopyListener;
import org.eclipse.papyrus.designer.transformation.core.templates.BindingUtils;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateInstantiation;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.library.Activator;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.TemplateBinding;

/**
 * Bind an operation to an actual, i.e. evaluate the xtend template within the opaque behavior associated with
 * the operation.
 */
public class BindOpaqueBehavior implements IM2MTrafo, PostCopyListener {

	@Override
	public void postCopyEObject(LazyCopier copier, EObject targetEObj) {

		if (targetEObj instanceof OpaqueBehavior) {
			try {
				TemplateBinding binding = TemplateInstantiation.context.getBinding();
				Classifier actual = TemplateUtils.getFirstActualFromBinding(binding);
				BindingUtils.instantiateBehavior(actual, (OpaqueBehavior) targetEObj);
			}
			catch (TransformationException e) {
				Activator.log.error(e);
			}
		}
	}
}
