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
package org.eclipse.papyrus.java.generator.metamodel.jdt.generatoroptionsmm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Options</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.papyrus.java.generator.metamodel.jdt.generatoroptionsmm.Options#getDefaultSourceFolder <em>Default Source Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.papyrus.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmPackage#getOptions()
 * @model
 * @generated
 */
public interface Options extends EObject {
	/**
	 * Returns the value of the '<em><b>Default Source Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Source Folder</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 *
	 * @return the value of the '<em>Default Source Folder</em>' attribute.
	 * @see #setDefaultSourceFolder(String)
	 * @see org.eclipse.papyrus.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmPackage#getOptions_DefaultSourceFolder()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getDefaultSourceFolder();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.java.generator.metamodel.jdt.generatoroptionsmm.Options#getDefaultSourceFolder <em>Default Source Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param value
	 *            the new value of the '<em>Default Source Folder</em>' attribute.
	 * @see #getDefaultSourceFolder()
	 * @generated
	 */
	void setDefaultSourceFolder(String value);

} // Options
