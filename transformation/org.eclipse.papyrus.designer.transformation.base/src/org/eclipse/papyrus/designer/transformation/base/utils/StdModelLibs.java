/*******************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

/**
 * This class adds "standard" model libraries for component-based development: the FCM profile, parts of the MARTE profile (allocation)
 * and the associated package imports to your model.
 */
public class StdModelLibs {

	public static final URI DESIGNER_TRAFOLIB_URI = URI.createURI("pathmap://DML_TRAFO/trafos.uml"); //$NON-NLS-1$

	public static final URI DESIGNER_MARTE_CALLS_URI = URI.createURI("pathmap://QML_MARTE/marte.uml"); //$NON-NLS-1$

	public static final URI FCM_PROFILE_URI = URI.createURI("pathmap://FCM_PROFILES/FCM.profile.uml"); //$NON-NLS-1$

	public static final URI MARTE_PROFILE_URI = URI.createURI("pathmap://Papyrus_PROFILES/MARTE.profile.uml");//$NON-NLS-1$

	public static Package addResource(URI uri, Element anElement) {
		return addResource(uri, anElement.eResource().getResourceSet());
	}
	
	public static Package addResource(URI uri, ResourceSet rs) {
		Resource resource = rs.getResource(uri, true);
		return addResource(resource);
	}

	public static Package addResource(Resource resource) {
		EList<EObject> contentObj = resource.getContents();
		if ((contentObj.size() > 0) && (contentObj.get(0) instanceof Package)) {
			return (Package) contentObj.get(0);
		}
		return null;
	}
}
