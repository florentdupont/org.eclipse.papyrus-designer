/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.validation.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.papyrus.designer.components.FCM.ConfigurationProperty;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;

/**
 * Verify if all configuration attribute have a (user-provided) value.
 *
 * @author ansgar
 *
 *         TODO: implementation assumes that at least the slot exists
 */
public class ConfigurationAttributeNotSet extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx)
	{
		Slot slot = (Slot) ctx.getTarget();
		StructuralFeature feature = slot.getDefiningFeature();
		if ((feature != null) && (StereotypeUtil.isApplied(feature, ConfigurationProperty.class))) {
			if (slot.getValues().size() == 0) {
				return ctx.createFailureStatus("The attribute '" + feature.getName() + "' is tagged as a configuration property, but the associated slot has no value.");
			}
		}
		return ctx.createSuccessStatus();
	}
}
