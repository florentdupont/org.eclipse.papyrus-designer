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

package org.eclipse.papyrus.designer.deployment.tools;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator;
import org.eclipse.papyrus.designer.transformation.extensions.InstanceConfigurator;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

public class ConfigUtils {

	/**
	 * Get the configuration properties of a class
	 *
	 * @param component
	 * @return
	 */
	public static EList<Property> getConfigAttributes(Classifier component) {

		EList<Property> list = new BasicEList<Property>();

		for (Property part : component.getAllAttributes()) {
			if (DepUtils.allAttributesAreConfigAttributs()) {
				// return all attributes
				list.add(part);
			}
			// otherwise add only attributes tagged as configuration properties
			else if (StereotypeUtil.isApplied(part, ConfigurationProperty.class)) {
				list.add(part);
			}
		}
		return list;
	}

	/**
	 * Configure an instance. The configurator is selected by means of a stereotype on the classifier of
	 * the passed instance.
	 *
	 * @see org.eclipse.papyrus.designer.transformation.extensions.IInstanceConfigurator
	 * @param instance
	 *            the specification of instance that should be configured
	 * @param componentPart
	 *            the part representing the instance (before container expansion)
	 * @param port
	 *            a port within the context of container
	 */
	public static void configureInstance(InstanceSpecification instance, Property componentPart, InstanceSpecification parentInstance) {
		Classifier component = DepUtils.getClassifier(instance);
		UseInstanceConfigurator useInstanceConfigurator = UMLUtil.getStereotypeApplication(component, UseInstanceConfigurator.class);
		InstanceConfigurator.configureInstance(useInstanceConfigurator, instance, componentPart, parentInstance);
	}
	
	/**
	 * Get the configuration attributes behind a container rule
	 * TODO: rule might not be active - should we still configure it??
	 *
	 * @param aRule
	 * @return
	 * @throws TransformationException
	 */
	/*
	public static EList<Property> getConfigAttributes(ContainerRule rule) throws TransformationException {
		EList<Property> list = new BasicEList<Property>();
		Class composite = rule.getBase_Class(); // a rule should always have a base class, unless malformed
		if (composite == null) {
			throw new TransformationException(Messages.ConfigUtils_RuleWithoutBaseClass);
		}
		for (Property part : composite.getAttributes()) {
			if (part instanceof Port) {
				continue;
			}
			Type type = part.getType();
			if (StereotypeUtil.isApplied(part, ConfigurationProperty.class)) {
				list.add(part);
			}
			if (type instanceof Class) {
				list.addAll(getConfigAttributes((Class) type));
			}
		}

		return list;
	}
	*/
}
