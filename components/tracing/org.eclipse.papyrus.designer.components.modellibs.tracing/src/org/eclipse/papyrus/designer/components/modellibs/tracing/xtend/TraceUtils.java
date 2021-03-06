/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.modellibs.tracing.xtend;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.papyrus.infra.services.tracepoints.MarkerUtils;
import org.eclipse.papyrus.infra.services.tracepoints.TracepointConstants;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationContext;
import org.eclipse.uml2.uml.Element;

public class TraceUtils {

	public static IMarker[] getMarkersForEObject(EObject eObject, String markerType) {
		Resource resource = eObject.eResource();
		IFile file = WorkspaceSynchronizer.getFile(resource);
		if (file != null) {
			try {
				return file.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
			}
		}
		return new IMarker[0];
	}

	public static boolean hasTrace(Element eObject) {
		IMarker markers[] = getMarkersForEObject(eObject, TracepointConstants.tpOrbpMarker);
		for (IMarker marker : markers) {
			// explicitly pass resourceSet of eObject we want to compare. Otherwise, the marker utils would
			// load resources into its own resource set (leading to non-comparable eObjects)
			EObject eObjOfMarker = MarkerUtils.getEObjectOfMarker(eObject.eResource().getResourceSet(), marker);
			if (eObjOfMarker == eObject) {
				return true;
			}
		}
		// TODO: testing workaround (always return true for port based transformations)
		if (TransformationContext.getPort() != null) {
			return true;
		}
		return false;
	}
}
