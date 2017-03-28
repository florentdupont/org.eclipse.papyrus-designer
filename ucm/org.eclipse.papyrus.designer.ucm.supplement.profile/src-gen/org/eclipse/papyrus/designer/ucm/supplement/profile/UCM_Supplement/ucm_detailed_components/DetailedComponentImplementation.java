/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components;

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AtomicComponentImplementation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Detailed Component Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation#getPortImplementation <em>Port Implementation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage#getDetailedComponentImplementation()
 * @model
 * @generated
 */
public interface DetailedComponentImplementation extends AtomicComponentImplementation {
	/**
	 * Returns the value of the '<em><b>Port Implementation</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Implementation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Implementation</em>' containment reference list.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage#getDetailedComponentImplementation_PortImplementation()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<IPortElementImplementation> getPortImplementation();

} // DetailedComponentImplementation
