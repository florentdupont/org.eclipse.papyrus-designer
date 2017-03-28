/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsPackage
 * @generated
 */
public interface Ucm_detailed_componentsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_detailed_componentsFactory eINSTANCE = org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Detailed Component Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Detailed Component Implementation</em>'.
	 * @generated
	 */
	DetailedComponentImplementation createDetailedComponentImplementation();

	/**
	 * Returns a new object of class '<em>Component Port Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Port Implementation</em>'.
	 * @generated
	 */
	ComponentPortImplementation createComponentPortImplementation();

	/**
	 * Returns a new object of class '<em>Technical Port Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Technical Port Implementation</em>'.
	 * @generated
	 */
	TechnicalPortImplementation createTechnicalPortImplementation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Ucm_detailed_componentsPackage getUcm_detailed_componentsPackage();

} //Ucm_detailed_componentsFactory
