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
package org.eclipse.papyrus.designer.components.transformation.core.extensions;

import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException;
import org.eclipse.uml2.uml.Class;

/**
 * An interface that must be implemented by classes that realize the transformation
 * from component-based to object-oriented models. It includes the replacement of
 * ports and connectors. Ports are
 * replaced with attributes and access operations, connectors within a composite
 * by an operation that creates the initial setup.
 * 
 * 1. add an operation that allows to retrieve the reference to an interface provided
 * by a port. This operation has a mapping specific name, e.g. get_<port_name>
 * 2. add an operation that allows to connect a specific port.
 * the connect_q operation (*including a
 * storage attribute*) for a port with a required interface
 * 3. add an implementation for the getcnx_q operation for a port
 * with a required interface (the operation itself has been added before)
 *
 * Problems: need to align bootloader creation with this mapping, since
 * the bootloader may be responsible for instantiation
 * 
 * Caveat: Assure that the folder derivedInterfaces already exists in a model.
 * Otherwise the call to getProvided/getRequired interface might trigger its
 * creation resulting in the corruption of list iterators (ConcurrentAccess
 * exception)
 *
 * New considerations: could the OO trafo be seen as a specific form of the
 * LwContainerTrafo?
 * [if yes, would that be useful?] Problem: it's not only a merge and operation
 * interception, but additional operation(s) for ports. But, it might well
 * be based on similar mechanisms as in merging in a template + Java code instead
 * of Java code only. Also possible: still have OOTrafo, but there is a collaboration
 * between the OO trafo and a LW container rule.
 */
public interface IOOTrafo {

	/**
	 * Initialize the transformation. Pass the copier and the bootloader to the
	 * transformation in case it is needed by an implementation of this interface.
	 * @param copier an instance of the lazy copier that copies from source (intermediate) model to the OO model.
	 * @param bootloader
	 */
	public void init(LazyCopier copier, Class bootloader);
	
	/**
	 * Transformations adds operations for accessing ports. A common but not necessary
	 * code generation pattern is to associate different operations with ports that
	 * provide services (access operations) and ports requiring services (connection operations)
	 * 
	 * @param implementation
	 *            A component implementation
	 */
	public void addPortOperations(Class implementation);


	/**
	 * Add an operation that connects the parts within a composite class based on the
	 * UML connector information.
	 *
	 * @param compositeImplementation
	 *            A (composite) component implementation
	 * @throws TransformationException
	 */
	public void addConnectionOperation(Class compositeImplementation)
			throws TransformationException;

	/**
	 * Transform parts if necessary. This transformation is for target programming languages
	 * like C++ that support different ways of composition for attributes, i.e. references/pointers
	 * versus values. In case of the latter, the instantiation of a composite directly implies
	 * the instantiation of its sub-components. In case of the former, parts need to be instantiated
	 * by a different mechanism, e.g. by the constructor (which is still a valid correspondence for
	 * composition in UML at a logical level).
	 * Another possible use is the support of a more dynamic deployment: it is for instance possible
	 * to use a use a part-list (single attribute) instead of fixed named attributes.
	 * 
	 * @param compositeImplementation
	 *            A (composite) component implementation
	 */
	public void transformParts(Class compositeImplementation);
}
