/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.UCMTypesPackage
 * @generated
 */
public interface UCMTypesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UCMTypesFactory eINSTANCE = org.eclipse.papyrus.designer.ucm.types.profile.UCMTypes.impl.UCMTypesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>dummy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>dummy</em>'.
	 * @generated
	 */
	dummy createdummy();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UCMTypesPackage getUCMTypesPackage();

} //UCMTypesFactory
