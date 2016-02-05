/*****************************************************************************
 * Copyright (c) 2014 Jonathan Geoffroy
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Jonathan Geoffroy  geoffroy.jonathan@gmail.com  - Meta-model conception
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmPackage
 * @generated
 */
public interface GeneratoroptionsmmFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	GeneratoroptionsmmFactory eINSTANCE = org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.GeneratoroptionsmmFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Options</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return a new object of class '<em>Options</em>'.
	 * @generated
	 */
	Options createOptions();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @return the package supported by this factory.
	 * @generated
	 */
	GeneratoroptionsmmPackage getGeneratoroptionsmmPackage();

} // GeneratoroptionsmmFactory
