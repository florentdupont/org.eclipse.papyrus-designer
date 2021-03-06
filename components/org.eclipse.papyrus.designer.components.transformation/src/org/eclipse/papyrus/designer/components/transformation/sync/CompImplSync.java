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

package org.eclipse.papyrus.designer.components.transformation.sync;

import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.papyrus.designer.components.transformation.Activator;
import org.eclipse.papyrus.designer.components.transformation.Messages;
import org.eclipse.papyrus.designer.components.transformation.PortInfo;
import org.eclipse.papyrus.designer.components.transformation.PortUtils;
import org.eclipse.papyrus.designer.components.transformation.component.PrefixConstants;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.OperationUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.RealizationUtils;
import org.eclipse.papyrus.designer.transformation.core.sync.SyncBehaviorParameters;
import org.eclipse.papyrus.designer.transformation.core.transformations.UpdateUtils;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement;
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Synchronize component implementation classes.
 * An implementation depends on the component type in the following way:
 * (1) It has to realize the interfaces that are provided by the components ports, unless
 * the port is delegated
 * (1b) For each realized interface, its operations need to be implemented (the tool should detect and report
 * double entries which can be resolved via delegation)
 * (2) It can use interface that are required by the component ports. These changes are reflected in the
 * context object, but are currently not propagated on an implementation level.
 *
 * The first aspect may be triggered by
 * (a) modification of the port (name, kind or type)
 * (b) modification due to changes of the interface: rename the interface itself or add, remove or rename operations
 * and add, remove or rename parameters.
 *
 * InterfaceRealizations are implemented by means of stereotyped generalizations. This has the advantage
 * that the operation itself is inherited and does not have to be synchronized.
 *
 * @author ansgar
 *
 */
public class CompImplSync {

	public static void syncViaInterface(Interface intf) {
		syncViaInterface(intf, null);
	}

	/**
	 * Delegate a synchronization operation to one or more implementations when an interface
	 * (which may have changed) is given. I.e. this operation is called when an operation
	 * of an implemented interface is added, removed or modified.
	 *
	 * @param an
	 *            interface which may be implemented by a set of component implementations.
	 * @param port
	 */
	public static void syncViaInterface(Interface intf, BehavioralFeature toBeRemoved) {
		// find all implementations of this interface
		for (DirectedRelationship relationship : intf.getTargetDirectedRelationships(UMLPackage.eINSTANCE.getInterfaceRealization())) {
			InterfaceRealization ir = (InterfaceRealization) relationship;
			Classifier cl = ir.getImplementingClassifier();
			if (cl instanceof Class) {
				Activator.log.info(String.format(Messages.CompImplSync_InfoSyncIntf, cl.getName()));
				Class implementation = (Class) cl;
				// syncRealizations (implementation);
				CompImplSync.interfaceModifications(implementation, toBeRemoved);
			}
		}
	}

	/**
	 * Delegate a synchronization operation to one or more implementations when a component
	 * type is given.
	 * The function will in particular handle realization operations due to provided
	 * ports and the behaviors associated with it (e.g. opaque-behaviors implementing the provided
	 * operations of a component)
	 *
	 * @param a
	 *            component type or a realized interface
	 * @param port
	 */
	public static boolean syncViaType(Class compType, boolean addOnly) {
		// find all implementations ...
		boolean foundGeneralization = false;
		for (DirectedRelationship relationship : compType.getTargetDirectedRelationships(UMLPackage.eINSTANCE.getGeneralization())) {
			if (relationship instanceof Generalization) {
				foundGeneralization = true;
				Classifier cl = ((Generalization) relationship).getSpecific();
				if (cl instanceof Class) {
					Class implementation = (Class) cl;
					updatePorts(implementation);
					if (!cl.isAbstract()) {
						// add realization relationship only for implementations
						if (addOnly) {
							addRealizations(implementation);
							// syncContextOps (implementation, false);
						} else {
							syncRealizations(implementation);
							// syncContextOps (implementation, true);
						}
					}
				}
			}
		}
		return foundGeneralization;
	}

	/*
	 * public static void renamePortViaType (Class compType, Port port, String oldName)
	 * {
	 * // find all implementations ...
	 * for (DirectedRelationship relationship :
	 * compType.getTargetDirectedRelationships (UMLPackage.eINSTANCE.getGeneralization ()))
	 * {
	 * if (relationship instanceof Generalization) {
	 * Classifier cl = ((Generalization) relationship).getSpecific ();
	 * if (cl instanceof Class) {
	 * renamePort ((Class) cl, port, oldName);
	 * }
	 * }
	 * }
	 * }
	 */

	/**
	 * Update the ports of a class, i.e. recalculate its derived interfaces
	 *
	 * @param component
	 * @param port
	 */
	public static void updatePorts(Class implementation) {
		for (Port port : PortUtils.getAllPorts(implementation)) {
			org.eclipse.papyrus.designer.components.FCM.Port fcmPort = UMLUtil.getStereotypeApplication(port, org.eclipse.papyrus.designer.components.FCM.Port.class);
			// verify if in same eResource (= editable)
			if ((fcmPort != null) && (port.eResource() == implementation.eResource())) {
				fcmPort.update();
			}
		}
	}

	/**
	 * Synchronize realization (generalization) relationship.
	 *
	 * @param implementation
	 * @param port
	 */
	public static void syncRealizations(Class implementation) {
		EList<Interface> providedIntfs = addRealizations(implementation);
		removeRealizations(providedIntfs, implementation);
	}

	/**
	 * add derived realization relationships of a component implementation
	 * due to provided interfaces of its ports
	 *
	 * TODO: depends on OOTrafo, duplicate functionality
	 * 
	 * @param implementation a component implementation
	 */
	public static EList<Interface> addRealizations(Class implementation) {
		// create a list of all provided interfaces and check whether realization relationship
		// exists. If not, create
		EList<Interface> providedIntfs = new BasicEList<Interface>();
		for (PortInfo portInfo : PortUtils.flattenExtendedPorts(PortUtils.getAllPorts2(implementation))) {
			Interface providedIntf = portInfo.getProvided();
			// check, if there is a getter already. In this case, we assume that we should not synchronize
			// operations
			String opName = PrefixConstants.getP_Prefix + portInfo.getName();
			if (implementation.getOwnedOperation(opName, null, null) != null) {
				continue;
			}
			ConnectorEnd connEnd = ConnectorUtil.getDelegation(implementation, portInfo.getPort());
			// check that there is no delegation to a part which in turn has to implement the operations.
			if ((providedIntf != null) && (connEnd == null)) {
				if (providedIntfs.contains(providedIntf)) {
					// emit warning that more than one provider for same interface
					Activator.log.info("The interface " + providedIntf.getName() + //$NON-NLS-1$
							" is provided by more than one port of class <" + implementation.getQualifiedName() + //$NON-NLS-1$
							">, inputs would therefore be undistinguishable"); //$NON-NLS-1$
				}
				providedIntfs.add(providedIntf);
				InterfaceRealization ir = implementation.getInterfaceRealization(null,  providedIntf);
				if (ir == null) {
					RealizationUtils.addRealization(implementation, providedIntf);
				} else {
					String name = RealizationUtils.calcRealizationName(providedIntf);
					if (!name.equals(ir.getName())) {
						ir.setName(name);
					}
				}
			}
		}
		return providedIntfs;
	}

	/**
	 * remove derived realization relationships of a component implementation
	 * due to provided interfaces of its ports, i.e. remove those for which the
	 * port no longer provides the interface.
	 *
	 * @param implementation
	 */
	private static void removeRealizations(EList<Interface> providedIntfs, final Class implementation) {
		// now loop through all interface realization relations and remove those that are not
		// related to a provided port.
		final EList<InterfaceRealization> toBeRemoved = new BasicEList<InterfaceRealization>();
		for (InterfaceRealization ir : implementation.getInterfaceRealizations()) {
			String name = ir.getName();
			// automatically added interface realization is identified via its name (simpler
			// compared to use of stereotype)
			if ((name != null) && name.startsWith(RealizationUtils.REALIZATION_OF)) {
				Interface inheritedIntf = ir.getContract();
				if (!providedIntfs.contains(inheritedIntf)) {
					toBeRemoved.add(ir);
				}
			}
		}
		if (toBeRemoved.size() > 0) {
			// remove InterfaceRealization, since not part of provided interfaces
			CommandSupport.exec(TransactionUtil.getEditingDomain(implementation), Messages.CompImplSync_InfoSyncViaImpl, new Runnable() {

				@Override
				public void run() {
					// implCopy = implementation;
					for (InterfaceRealization ir : toBeRemoved) {
						ir.destroy();
					}
				}
			});
		}
	}

	/**
	 * The interface that types a port has been modified. Subsequently, the names of
	 * methods have to be adapted accordingly, methods have to be removed or added
	 *
	 * @param implementation
	 * @param port
	 * @param newName
	 */
	public static void interfaceModifications(Class implementation, BehavioralFeature toBeRemoved) {
		syncDerivedOperations(implementation);

		EList<Operation> ownedOperations = implementation.getOwnedOperations();
		// loop through all methods, adapt name according to specification name.
		Iterator<Behavior> behaviors = implementation.getOwnedBehaviors().iterator();
		while (behaviors.hasNext()) {
			Behavior behavior = behaviors.next();
			BehavioralFeature bf = behavior.getSpecification();
			if ((bf != null) && ownedOperations.contains(bf)) {
				BehavioralFeature sourceBf = (BehavioralFeature)
						UpdateUtils.getSource(bf);

				// if an operation is scheduled for deletion, it still exists at this
				// point (This operation is called by an InterfaceListener which gets called
				// before the deletion takes place) and the specification of associated behaviors
				// still point to it. Therefore the check toBeRemoved is added.
				if (sourceBf != toBeRemoved) {
					String name = calcBehaviorName(sourceBf, implementation);
					if (!name.equals(behavior.getName())) {
						behavior.setName(name);
					}
				}
			}
		}
	}

	public static void syncDerivedOperations(Class implementation) {
		// check for unimplemented operations
		// EList<DirectedRelationship> relShips = implementation.getSourceDirectedRelationships (UMLPackage.eINSTANCE.getInterfaceRealization ());
		EList<Interface> realizedInterfaces = new BasicEList<Interface>();
		// implementation.getInterfaceRealizations returns wrong value??

		EList<Operation> ownedOperations = implementation.getOwnedOperations();
		for (InterfaceRealization ir : implementation.getInterfaceRealizations()) {
			/*
			 * for (DirectedRelationship relShip : relShips)
			 * if (relShip instanceof InterfaceRealization) {
			 * InterfaceRealization ir = (InterfaceRealization) relShip;
			 */
			Interface intf = ir.getContract();
			if (intf == null) {
				// should not happen?
				continue;
			}
			realizedInterfaces.add(intf);

			for (Operation operation : intf.getAllOperations()) {
				Operation copiedOperation = (Operation)
						UpdateUtils.getDerivedElement(ownedOperations, operation);
				if (copiedOperation == null) {
					// no owned operation derived from the interface operation found.
					// Would indicate normally that there is no suitable owned operation.
					// However: stereotype values may not be available during model load, check
					// whether there is an identical operation, before copying one.
					copiedOperation = OperationUtils.getSameOperation(operation, implementation);
					if (copiedOperation == null) {
						copiedOperation = implementation.createOwnedOperation(operation.getName(), null, null);
						OperationUtils.syncOperation(operation, copiedOperation);
						copiedOperation.setIsAbstract(false);
					}
					UpdateUtils.setSource(copiedOperation, operation);
				} else if (!OperationUtils.isSameOperation(operation, copiedOperation)) {
					OperationUtils.syncOperation(operation, copiedOperation);
					copiedOperation.setIsAbstract(false);
				}
			}
		}
		for (Behavior method : implementation.getOwnedBehaviors()) {
			SyncBehaviorParameters.syncParameters(method);
		}

		// remove operations that are no longer provided via an interface of a port (and
		// that are derived elements, i.e. have a source attribute)
		Iterator<Operation> ownedOpsIter = ownedOperations.iterator();
		while (ownedOpsIter.hasNext()) {
			Operation ownedOp = ownedOpsIter.next();

			DerivedElement de = UMLUtil.getStereotypeApplication(ownedOp, DerivedElement.class);
			if (de != null) {
				if (de.getSource() instanceof Operation) {

					Operation sourceOp = (Operation) de.getSource();
					// check, whether sourceOp is offered by one of the realized interfaces
					boolean contains = false;
					for (Interface realizedIntf : realizedInterfaces) {
						if (realizedIntf.getAllOperations().contains(sourceOp)) {
							contains = true;
						}
					}
					if (!contains) {
						// operation belongs to an interface which is not implemented => remove
						ownedOpsIter.remove();
						ownedOp.destroy();
					}
				}
				else if (de.getSource() == null) {
					// source element does not exist => remove
					ownedOpsIter.remove();
					ownedOp.destroy();
				}
			}
		}
	}



	/**
	 * Calculate the name of a behavior for a given operation
	 * TODO better handling of overloading (better representation of signature)
	 *
	 * @param operation
	 * @return
	 */
	private static String calcBehaviorName(BehavioralFeature bf, Classifier cl) {
		/*
		 * Iterator<Operation> operations = cl.getOperations ().iterator ();
		 * while (operations.hasNext ()) {
		 * Operation op = operations.next ();
		 * if ((op.getName () == bf.getName ()) && (op != bf)) {
		 * // at least one other operation with same name, return long name
		 * return bf.getName () + bf.getOwnedParameters ().toString ();
		 * }
		 * }
		 */
		return bf.getName();
	}
}
