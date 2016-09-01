/*******************************************************************************
 * Copyright (c) 2011 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.transformation.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationRTException;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;


/**
 * Support for binding helper
 */
public class M2MTrafoExt {

	public static final String TRANSFORMATION_ID = "transformationID"; //$NON-NLS-1$
	public static final String M2M_TRAFO = Activator.PLUGIN_ID + ".m2mTrafo"; //$NON-NLS-1$

	/**
	 * Obtain a list of M2M transformations that are explicitly applied to a model element 
	 * @param element the model element
	 * @return the list of M2M transformations
	 */
	public static EList<M2MTrafo> getM2MTrafos(Element element) {
		ApplyTransformation applyTrafo =
				UMLUtil.getStereotypeApplication(element, ApplyTransformation.class);
		if (applyTrafo != null) {
			// execute the set of transformations applied to the element
			return applyTrafo.getTrafo();
		}
		return null;
	}
	
	/**
	 * Get a reference to the M2M transformation class using the m2mTrafo element (stereotype)
	 * if the kind option is set, the transformation class is obtained via the kind.
	 *
	 * @param m2mTrafo a m2m transformation reference
	 * @return
	 */
	public static IM2MTrafo getM2MTrafo(M2MTrafo m2mTrafo) {
		if (m2mTrafo != null) {
			return getM2MTrafo(m2mTrafo.getBase_Class().getQualifiedName());
		}
		return null;
	}

	public static IM2MTrafo getM2MTrafo(String trafoName) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(M2M_TRAFO);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String transformationID = configElement.getAttribute(TRANSFORMATION_ID);
				if ((transformationID != null) && transformationID.equals(trafoName)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof IM2MTrafo) {
						return (IM2MTrafo) obj;
					}
				}
			} catch (CoreException exception) {
				Activator.log.error(exception);
			}
		}
		throw new TransformationRTException(
				String.format("Cannot find class for transformation <%s>", trafoName));
	}
}
