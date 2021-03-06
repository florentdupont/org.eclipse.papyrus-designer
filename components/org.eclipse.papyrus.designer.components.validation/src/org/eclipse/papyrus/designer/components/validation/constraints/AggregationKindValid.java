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
import org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * Verify that aggregation kinds are used well. General rule:
 * attributes of a components should have aggregation kind "composite" unless
 * (1) the are singletons (implementation property), i.e. there is exactly one instance.
 *
 * (2) they are *shared*. In this case an enclosing component has to define a property of same type
 * and name (open if "same name" restriction is kept, since it implies an implicit relationship
 * that would break after name changes. But it allows for having multiple shared properties of same type)
 * [it does not make a difference, whether "aggregation" or "none" is used as aggregation kind]
 * Alternative? give user a fine control in the instantiation model? Fractal: no need for a single
 * composed property?
 *
 * Rule has been made specific to SW Designer, since we verify that configurationProperty is applicable (implying that the
 * FCM profile is applied)
 *
 * @author ansgar
 *
 */
public class AggregationKindValid extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx)
	{
		Property attribute = (Property) ctx.getTarget();
		Type type = attribute.getType();
		if (type instanceof Class) {
			AggregationKind aggKind = attribute.getAggregation();
			if (aggKind != AggregationKind.COMPOSITE_LITERAL) {
				if (StereotypeUtil.isApplicable(attribute, ConfigurationProperty.class) && !DepUtils.isSingleton((Class) type)) {
					// no composition, but not singleton: warn user to make up a proper deployment plan
					// validation has been made SW Designer specific by checking whether the Configuration attribute is applicable

					return ctx.createFailureStatus("The attribute '" + attribute.getName() + "' has a non-composition aggregation kind, but the referenced type ('" +
							type.getName() + "') is not a singleton. The associated instance specifications have to be set-up manually");
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}
