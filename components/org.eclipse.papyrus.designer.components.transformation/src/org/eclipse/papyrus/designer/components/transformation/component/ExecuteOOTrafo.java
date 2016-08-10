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

package org.eclipse.papyrus.designer.components.transformation.component;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.transformation.deployment.DepUtils;
import org.eclipse.papyrus.designer.components.transformation.extensions.IOOTrafo;
import org.eclipse.papyrus.designer.components.transformation.extensions.OOTrafo;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.StructuredClassifier;

public class ExecuteOOTrafo {

	IOOTrafo ooTrafo;

	ExecuteOOTrafo(IOOTrafo ooTrafo) {
		this.ooTrafo = ooTrafo;
	}

	/**
	 * Execute the OO transformation for a package, include removal of connectors
	 *
	 * @param pkg
	 * @throws TransformationException
	 */
	public void transformPackage(Package pkg) throws TransformationException {
		recursiveOOTransformation(pkg);
		// transformation and removal is not done in same loop. Otherwise it would be possible
		// that inherited ports have already been removed.
		recursiveRemoval(pkg);
	}

	/**
	 * Execute the OO transformation for a package
	 *
	 * @param pkg
	 * @throws TransformationException
	 */
	public void recursiveOOTransformation(Package pkg) throws TransformationException {
		EList<PackageableElement> peList = new BasicEList<PackageableElement>();
		peList.addAll(pkg.getPackagedElements());
		for (PackageableElement element : peList) {
			if (element instanceof Package) {
				recursiveOOTransformation((Package) element);
			} else if (element instanceof Class) {
				Class implementation = (Class) element;
				// do not apply transformation to port-kinds
				if (!StereotypeUtil.isApplied(implementation, PortKind.class)) {
					ooTrafo.addPortOperations(implementation);
					ooTrafo.addConnectionOperation(implementation);
					ooTrafo.transformParts(implementation);
				}
			}
		}
	}

	/**
	 * Remove connectors and ports from elements within a package
	 * 
	 * @param pkg
	 * @throws TransformationException
	 */
	public void recursiveRemoval(Package pkg) throws TransformationException {
		EList<PackageableElement> peList = new BasicEList<PackageableElement>();
		peList.addAll(pkg.getPackagedElements());
		for (PackageableElement element : peList) {
			if (element instanceof Package) {
				recursiveRemoval((Package) element);
			}
			else {
				// delete connectors and ports
				if (element instanceof StructuredClassifier) {
					removeConnectors((StructuredClassifier) element);
				}
				if (element instanceof EncapsulatedClassifier) {
					removePorts((EncapsulatedClassifier) element);
				}
			}
		}
	}

	/**
	 * Helper: remove connectors from a structured classifier (in most cases a
	 * class)
	 * 
	 * @param implementation
	 *            A component
	 */
	public void removeConnectors(StructuredClassifier implementation) {
		EList<Connector> connectors = new BasicEList<Connector>();
		connectors.addAll(implementation.getOwnedConnectors());
		for (Connector connector : connectors) {
			connector.destroy();
		}
	}

	/**
	 * Helper: remove ports from a from an encapsulated classifier (in most
	 * cases a class)
	 * 
	 * @param implementation
	 *            A component
	 */
	public void removePorts(EncapsulatedClassifier implementation) {
		EList<Port> portListCopy = new BasicEList<Port>();
		portListCopy.addAll(implementation.getOwnedPorts());
		// avoid dangling references by calling destroy on the port list
		for (Port port : portListCopy) {
			port.destroy();
		}
	}

	/**
	 * Transform a component model into an object oriented model.
	 * 
	 * @param copier a copier
	 * @param bootloader the generated bootloader
	 * @param genModel the model to generate into
	 * @throws TransformationException
	 */
	public static void transform(LazyCopier copier, Class bootloader, Model genModel)
			throws TransformationException {
		String ooTransformation = DepUtils.getOOTransformationFromPackage(genModel);
		if (ooTransformation == null) {
			// default OO transformation
			ooTransformation = "StaticCpp"; //$NON-NLS-1$
		}
		IOOTrafo ooTrafo = OOTrafo.getOOTrafo(ooTransformation);
		ooTrafo.init(copier, bootloader);
		ExecuteOOTrafo executeOOTrafo = new ExecuteOOTrafo(ooTrafo);
		executeOOTrafo.transformPackage(genModel);

		// complete access operations?
	}
}
