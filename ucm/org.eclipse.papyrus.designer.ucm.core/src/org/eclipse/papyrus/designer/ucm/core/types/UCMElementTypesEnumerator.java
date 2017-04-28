/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.types;

import java.util.Arrays;
import java.util.List;

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;

/**
 * Static list of UCM specific element types
 */
public class UCMElementTypesEnumerator extends AbstractElementTypeEnumerator implements IUCMElementTypes {

	public static final IHintedType COMPONENT_TYPE = (IHintedType) getElementType(COMPONENT_TYPE_ID);

	public static final IHintedType ATOMIC_COMPONENT_IMPLEMENTATON = (IHintedType) getElementType(ATOMIC_COMPONENT_IMPLEMENTATON_ID);

	public static final IHintedType COMPOSITE_COMPONENT_IMPLEMENTATON = (IHintedType) getElementType(COMPOSITE_COMPONENT_IMPLEMENTATON_ID);

	public static final IHintedType INTERACTION_PATTERN = (IHintedType) getElementType(INTERACTION_PATTERN_ID);

	public static final IHintedType CONNECTOR_DEFINITION = (IHintedType) getElementType(CONNECTOR_DEFINITION_ID);

	public static final IHintedType APPLICATION_MODULE = (IHintedType) getElementType(APPLICATION_MODULE_ID);

	public static final IHintedType COMPONENT_MODULE= (IHintedType) getElementType(COMPONENT_MODULE_ID);

	public static final IHintedType CONTRACT_MODULE = (IHintedType) getElementType(CONTRACT_MODULE_ID);

	public static final IHintedType PORT = (IHintedType) getElementType(PORT_ID);
	
	private static final List<IHintedType> rtTypes = Arrays.asList(COMPONENT_TYPE, 
			ATOMIC_COMPONENT_IMPLEMENTATON,
			COMPOSITE_COMPONENT_IMPLEMENTATON,
			INTERACTION_PATTERN,
			CONNECTOR_DEFINITION
		);

	/**
	 * Returns all the specific semantic element types for UML-RT
	 * 
	 * @return all the specific semantic element types for UML-RT
	 */
	public static List<IHintedType> getAllRTTypes() {
		return rtTypes;
	}

}
