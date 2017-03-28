/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage
 * @generated
 */
public interface Ucm_componentsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_componentsFactory eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.impl.Ucm_componentsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Interaction Item Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction Item Binding</em>'.
	 * @generated
	 */
	InteractionItemBinding createInteractionItemBinding();

	/**
	 * Returns a new object of class '<em>Port Role Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Role Spec</em>'.
	 * @generated
	 */
	PortRoleSpec createPortRoleSpec();

	/**
	 * Returns a new object of class '<em>Port Type Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Type Spec</em>'.
	 * @generated
	 */
	PortTypeSpec createPortTypeSpec();

	/**
	 * Returns a new object of class '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection</em>'.
	 * @generated
	 */
	Connection createConnection();

	/**
	 * Returns a new object of class '<em>Connection Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Link</em>'.
	 * @generated
	 */
	ConnectionLink createConnectionLink();

	/**
	 * Returns a new object of class '<em>Technical Policy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Technical Policy</em>'.
	 * @generated
	 */
	TechnicalPolicy createTechnicalPolicy();

	/**
	 * Returns a new object of class '<em>Atomic Component Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Atomic Component Implementation</em>'.
	 * @generated
	 */
	AtomicComponentImplementation createAtomicComponentImplementation();

	/**
	 * Returns a new object of class '<em>Component Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Type</em>'.
	 * @generated
	 */
	ComponentType createComponentType();

	/**
	 * Returns a new object of class '<em>Composite Component Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Component Implementation</em>'.
	 * @generated
	 */
	CompositeComponentImplementation createCompositeComponentImplementation();

	/**
	 * Returns a new object of class '<em>Assembly Part</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assembly Part</em>'.
	 * @generated
	 */
	AssemblyPart createAssemblyPart();

	/**
	 * Returns a new object of class '<em>Abstract Type Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Type Binding</em>'.
	 * @generated
	 */
	AbstractTypeBinding createAbstractTypeBinding();

	/**
	 * Returns a new object of class '<em>Component Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Module</em>'.
	 * @generated
	 */
	ComponentModule createComponentModule();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Ucm_componentsPackage getUcm_componentsPackage();

} //Ucm_componentsFactory
