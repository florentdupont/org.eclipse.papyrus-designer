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

package org.eclipse.papyrus.designer.transformation.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationRTException;
import org.eclipse.papyrus.designer.transformation.core.Activator;
import org.eclipse.papyrus.designer.transformation.core.listeners.PostCopyListener;
import org.eclipse.papyrus.designer.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoKind;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.util.UMLUtil;


/**
 * Support for binding helper
 */
public class M2MTrafoExt {

	public static final String TRANSFORMATION_ID = "transformationID"; //$NON-NLS-1$
	public static final String M2M_TRAFO_KIND = Activator.PLUGIN_ID + ".m2mTrafo"; //$NON-NLS-1$

	/**
	 *
	 * @param iConfiguratorName
	 * @param component
	 *            containing composite (container)
	 * @param instance
	 */
	public static EObject applyPreHelper(M2MTrafo helper, LazyCopier copier, TemplateBinding binding, EObject sourceEObj) {
		IEmptyM2MTrafo ihelper = getM2MTrafo(helper.getBase_Class().getQualifiedName());
		if (ihelper instanceof PreCopyListener) {
			return ((PreCopyListener) ihelper).preCopyEObject(copier, sourceEObj);
		}
		return sourceEObj;
	}

	/**
	 *
	 * @param iConfiguratorName
	 * @param component
	 *            containing composite (container)
	 * @param instance
	 */
	public static void applyPostHelper(M2MTrafo helper, LazyCopier copier, TemplateBinding binding, EObject targetEObj) {
		IEmptyM2MTrafo ihelper = getM2MTrafo(helper.getBase_Class().getName());
		if (ihelper instanceof PostCopyListener) {
			((PostCopyListener) ihelper).postCopyEObject(copier, targetEObj);
		}
	}

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
	public static IEmptyM2MTrafo getM2MTrafo(M2MTrafo m2mTrafo) {
		if (m2mTrafo != null) {
			M2MTrafoKind kind = m2mTrafo.getKind();
			if (kind != null) {
				return getM2MTrafo(kind.getBase_Class().getQualifiedName());
			}
			else 
				return getM2MTrafo(m2mTrafo.getBase_Class().getQualifiedName());
		}
		return null;
	}

	public static IEmptyM2MTrafo getM2MTrafo(String trafoName) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(M2M_TRAFO_KIND);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String transformationID = configElement.getAttribute(TRANSFORMATION_ID);
				if ((transformationID != null) && transformationID.equals(trafoName)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof IEmptyM2MTrafo) {
						return (IEmptyM2MTrafo) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		throw new TransformationRTException(
				String.format("Cannot find class for transformation <%s>", trafoName));
	}
}
