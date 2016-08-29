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

package org.eclipse.papyrus.designer.components.modellibs.core.transformations;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.FCM.CodeGenOptions;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.transformation.extensions.IOOTrafo;
import org.eclipse.papyrus.designer.components.transformation.extensions.OOTrafo;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.core.transformations.ApplyRecursive;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.util.UMLUtil;

public class ExecuteOOTrafo implements IM2MTrafoCDP {

	/**
	 * Add OO elements that correspond to ports and connectors
	 */
	public class AddOOElements implements IM2MTrafoElem {

		public AddOOElements(IOOTrafo ooTrafo) {
			this.ooTrafo = ooTrafo;
		}
		
		@Override
		public void transformElement(M2MTrafo trafo, Element element) throws TransformationException {
			if (element instanceof Class) {
				Class implementation = (Class) element;
				// do not apply transformation to port-kinds
				if (!StereotypeUtil.isApplied(implementation, PortKind.class)) {
					ooTrafo.addPortOperations(implementation);
					ooTrafo.addConnectionOperation(implementation);
					ooTrafo.transformParts(implementation);
				}
			}
		}
		
		IOOTrafo ooTrafo;
	}

	/**
	 * Delete connectors and ports
	 * Removal is not done after all additions and not after the addition of a single element.
	 * Otherwise it would be possible that inherited ports have already been removed.
	 */
	public class RemoveComponentElements implements IM2MTrafoElem {
		
		@Override
		public void transformElement(M2MTrafo trafo, Element element) throws TransformationException {
			if (element instanceof StructuredClassifier) {
				removeConnectors((StructuredClassifier) element);
			}
			if (element instanceof EncapsulatedClassifier) {
				removePorts((EncapsulatedClassifier) element);
			}
		}
		
		/**
		 * Remove connectors from a structured classifier (in most cases a class)
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
		 * Remove ports from a from an encapsulated classifier (in most
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
	}
	
	/**
	 * Determine the component to OO language. The stereotype CodeGenOptions
	 * (which could be on any owning package) is evaluated.
	 *
	 * @param pkg
	 *            a classifier
	 * @return the programming language
	 */
	public static String getOOTransformationFromPackage(Package pkg) {
		CodeGenOptions codeGenOpt = UMLUtil.getStereotypeApplication(pkg, CodeGenOptions.class);
		if ((codeGenOpt != null) && (codeGenOpt.getCompToOOmapping() != null)) {
			return codeGenOpt.getCompToOOmapping().getBase_Class().getName();
		}
		else {
			return null;
		}
	}

    
	/**
	 * Transform a component model into an object oriented model.
	 */
	@Override
	public void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
		String ooTransformation = getOOTransformationFromPackage(TransformationContext.current.modelRoot);
		if (ooTransformation == null) {
			// default OO transformation
			ooTransformation = "StaticCpp"; //$NON-NLS-1$
		}
		IOOTrafo ooTrafo = OOTrafo.getOOTrafo(ooTransformation);
		ooTrafo.init(TransformationContext.current.copier, null);
		ApplyRecursive add = new ApplyRecursive(trafo, new AddOOElements(ooTrafo));
		ApplyRecursive remove = new ApplyRecursive(trafo, new RemoveComponentElements());
		add.applyRecursive(TransformationContext.current.modelRoot);
		remove.applyRecursive(TransformationContext.current.modelRoot);
		// complete access operations?
	}
}
