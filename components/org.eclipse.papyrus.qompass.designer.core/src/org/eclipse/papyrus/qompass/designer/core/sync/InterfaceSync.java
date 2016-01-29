/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
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

package org.eclipse.papyrus.qompass.designer.core.sync;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.FCM.DerivedElement;
import org.eclipse.papyrus.qompass.designer.core.OperationUtils;
import org.eclipse.papyrus.qompass.designer.core.Utils;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Synchronize the use of a common superclass for signal reception with the
 * question whether an interface has at least one owned reception.
 */
public class InterfaceSync {
	
	public static final String SIG_PREFIX = "SIG_"; //$NON-NLS-1$

	/**
	 * Name of an interface for generic reception support within the Qompass
	 * sysinterfaces modeling library
	 */
	private static final String SIGNAL_RECEPTION_QN = "sysinterfaces::SignalReception"; //$NON-NLS-1$

	protected static boolean useGenericSigInterface = false;

	public static void syncSignalReceptionSupport(Interface intf) {
		if (useGenericSigInterface) {
			syncGenericSignalReceptionSupport(intf);
		} else {
			syncSpecificSignalReceptionSupport(intf);
		}
	}

	public static void syncSpecificSignalReceptionSupport(Interface intf) {
		for (Reception reception : intf.getOwnedReceptions()) {
			Signal signal = reception.getSignal();
			if (signal != null) {
				String opName = SIG_PREFIX + signal.getName();
				Operation existingOperation = intf.getOwnedOperation(opName, null, null);
				Operation newOperation = UMLFactory.eINSTANCE.createOperation();
				newOperation.setName(opName);
				for (Property attribute : signal.getAttributes()) {
					Parameter parameter = newOperation.createOwnedParameter(attribute.getName(), attribute.getType());
					parameter.setLower(attribute.getLower());
					parameter.setUpper(attribute.getUpper());
				}
				if (existingOperation == null) {
					intf.getOwnedOperations().add(newOperation);
				} else if (!OperationUtils.isSameOperation(existingOperation, newOperation)) {
					OperationUtils.syncOperation(newOperation, existingOperation);
				}
				DerivedElement de = StereotypeUtil.applyApp(newOperation, DerivedElement.class);
				if (de != null) {
					de.setSource(reception);
				}
			}
		}
		// now remove derived operations that are no longer needed.
		EList<Operation> ownedOperations = new BasicEList<Operation>();
		ownedOperations.addAll(intf.getOwnedOperations());
		for (Operation operation : ownedOperations) {
			DerivedElement de = UMLUtil.getStereotypeApplication(operation, DerivedElement.class);
			if (de != null) {
				if (de.getSource() instanceof Reception) {
					Reception reception = (Reception) de.getSource();
					Signal signal = reception.getSignal();
					if ((signal == null) || !(SIG_PREFIX + signal.getName()).equals(operation.getName())) {
						// reception exists, but has no signal or another signal
						operation.destroy();
					}
				} else {
					// reception does not exist any more
					operation.destroy();
				}
			}
		}
	}

	public static void syncGenericSignalReceptionSupport(Interface intf) {

		NamedElement genericReceptionNE = Utils.getQualifiedElement(PackageUtil.getRootPackage(intf), SIGNAL_RECEPTION_QN);
		if (genericReceptionNE instanceof Interface) {
			Interface genericReceptionIntf = (Interface) genericReceptionNE;
			if (intf.getOwnedReceptions().size() > 0) {
				if (!hasReceptionSupport(intf, genericReceptionIntf)) {
					addReceptionSupport(intf, genericReceptionIntf);
				}
			} else if (hasReceptionSupport(intf, genericReceptionIntf)) {
				removeReceptionSupport(intf, genericReceptionIntf);
			}
		}
	}

	public static boolean hasReceptionSupport(Interface intf, Interface genericReceptionIntf) {
		// TODO: does it find an indirect reception?
		for (Classifier general : intf.getGenerals()) {
			if (general == genericReceptionIntf) {
				return true;
			}
		}
		return false;
	}

	public static void addReceptionSupport(Interface intf, Interface genericReceptionIntf) {
		intf.createGeneralization(genericReceptionIntf);
	}

	public static void removeReceptionSupport(Interface intf, Interface genericReceptionIntf) {
		for (Generalization generalization : intf.getGeneralizations()) {
			if (generalization.getGeneral() == genericReceptionIntf) {
				generalization.destroy();
			}
		}
	}
}
