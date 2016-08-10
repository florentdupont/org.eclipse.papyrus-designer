/*******************************************************************************
 * Copyright (c) 2006, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *     
 *     
 *******************************************************************************/
/**
 */
package org.eclipse.papyrus.designer.languages.common.profile.Codegen;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generator Hint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Information about the generator that should be used.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getBase_Element <em>Base Element</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getGeneratorID <em>Generator ID</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getGeneratorHint()
 * @model
 * @generated
 */
public interface GeneratorHint extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Element</em>' reference.
	 * @see #setBase_Element(Element)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getGeneratorHint_Base_Element()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Element getBase_Element();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getBase_Element <em>Base Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Element</em>' reference.
	 * @see #getBase_Element()
	 * @generated
	 */
	void setBase_Element(Element value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' reference.
	 * @see #setLanguage(Language)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getGeneratorHint_Language()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Language getLanguage();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getLanguage <em>Language</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' reference.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(Language value);

	/**
	 * Returns the value of the '<em><b>Generator ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generator ID</em>' attribute.
	 * @see #setGeneratorID(String)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getGeneratorHint_GeneratorID()
	 * @model dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	String getGeneratorID();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getGeneratorID <em>Generator ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generator ID</em>' attribute.
	 * @see #getGeneratorID()
	 * @generated
	 */
	void setGeneratorID(String value);

} // GeneratorHint
