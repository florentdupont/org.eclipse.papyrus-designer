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

package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.designer.transformation.base.Activator;
import org.eclipse.papyrus.designer.transformation.base.Messages;
import org.eclipse.papyrus.designer.transformation.base.UIContext;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * Some functions around stereotype usage.
 *
 * @author ansgar
 *
 */
public class StUtils {

	/**
	 * Copy the stereotype application from a source to a destination element. The
	 * function will also copy the stereotype attributes.
	 *
	 * @param source
	 * @param destination
	 * @return
	 */
	public static void copyStereotypes(Element source, Element destination) {
		for (Stereotype stereotype : source.getAppliedStereotypes()) {
			copyAttributes(stereotype.getQualifiedName(), source, destination);
		}
	}

	/**
	 * copy stereotype attributes.
	 *
	 * TODO: verify if it is necessary to distinguish between stereoSource and stereoDest
	 * (they should be identical, if the resource sets are).
	 * => simplify code
	 *
	 * @param stereotypeName
	 * @param source
	 * @param destination
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static boolean copyAttributes(String stereotypeName, Element source, Element destination) {
		Stereotype stereoSource = source.getAppliedStereotype(stereotypeName);
		Stereotype stereoDest = destination.getApplicableStereotype(stereotypeName);

		if ((stereoSource == null) || (stereoDest == null)) {
			return false;
		}
		Stereotype alreadyApplied = destination.getAppliedStereotype(stereotypeName);
		if (alreadyApplied == null) {
			// only apply stereotype, if not already applied
			destination.applyStereotype(stereoDest);
		}

		// getAllAttributes? (but have to avoid attribute base_Class which resets stereotype application)
		for (Property attribute : stereoSource.getAllAttributes()) {
			String attrName = attribute.getName();

			if (attrName.length() >= 5) {
				// do not copy base_ stereotypes (base_class, base_package and base_PackageImport)
				if (attrName.startsWith(Extension.METACLASS_ROLE_PREFIX)) {
					continue;
				}
			}
			// do not copy derived attributes
			if (attribute.isDerived()) {
				continue;
			}

			Object value = source.getValue(stereoSource, attrName);
			if (value instanceof EList) {
				// copy list
				EList<Object> newList = new BasicEList<Object>();
				for (Object valueEl : (EList<Object>) value) {
					newList.add(valueEl);
				}
				if (newList.size() > 0) {
					destination.setValue(stereoDest, attrName, newList);
				}
			}
			else {
				destination.setValue(stereoDest, attrName, value);
			}
		}
		return true;
	}

	/**
	 * Copy the profile applications from one model to another
	 * @param sourceModel the existing model
	 * @param targetModel the target model
	 * @throws TransformationException
	 */
	public static void copyProfileApplications(Package sourceModel, Package targetModel) throws TransformationException {
		try {
			// copy profile application
			for (Profile profile : sourceModel.getAppliedProfiles()) {
				// reload profile in resource of new model
				UIContext.monitor.subTask(Messages.InstantiateDepPlan_InfoApplyProfile + profile.getQualifiedName());

				if (profile.eResource() == null) {
					String profileName = profile.getQualifiedName();
					if (profileName == null) {
						if (profile instanceof MinimalEObjectImpl.Container) {
							URI uri = ((MinimalEObjectImpl.Container) profile).eProxyURI();
							if (uri != null) {
								throw new TransformationException(String.format(Messages.InstantiateDepPlan_CheckInputModelProfileNoRes, uri));
							}
						}
						throw new TransformationException(Messages.InstantiateDepPlan_CheckInputModelProfileNoResNoName);
					}
					throw new TransformationException(String.format(Messages.InstantiateDepPlan_CheckInputModelProfile3, profileName));
				}

				Resource profileResource = null;
				try {
					profileResource = ModelManagement.getResourceSet().getResource(profile.eResource().getURI(), true);
				} catch (WrappedException e) {
					// read 2nd time (some diagnostic errors are raised only
					// once)
					Activator.log.warn("Warning: exception in profile.eResource() " + e.getMessage()); //$NON-NLS-1$
					profileResource = ModelManagement.getResourceSet().getResource(profile.eResource().getURI(), true);
				}
				if (profileResource.getContents().size() == 0) {
					throw new TransformationException(String.format("Cannot copy profile with URI %s. Check whether the URI corresponds to an existing location", profileResource.getURI()));
				}
				Profile newProfileTop = (Profile) profileResource.getContents().get(0);
				Profile newProfile;
				String qname = profile.getQualifiedName();
				if ((qname != null) && qname.contains("::")) { //$NON-NLS-1$
					// profile is a sub-profile within same resource
					newProfile = (Profile) ElementUtils.getQualifiedElement(newProfileTop, qname);
				} else {
					newProfile = newProfileTop;
				}
				newProfile.getMember("dummy"); // force profile loading //$NON-NLS-1$
				targetModel.applyProfile(newProfile);
			}
		} catch (IllegalArgumentException e) {
			throw new TransformationException(Messages.InstantiateDepPlan_IllegalArgumentDuringCopy + e.toString());
		}
	}

}
