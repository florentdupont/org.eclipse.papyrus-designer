/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components;

import org.eclipse.uml2.uml.Port;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Port Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage#getComponentPortImplementation()
 * @model
 * @generated
 */
public interface ComponentPortImplementation extends IPortElementImplementation {
	/**
	 * Returns the value of the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' reference.
	 * @see #setPort(Port)
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage#getComponentPortImplementation_Port()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Port getPort();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation#getPort <em>Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' reference.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(Port value);

} // ComponentPortImplementation
