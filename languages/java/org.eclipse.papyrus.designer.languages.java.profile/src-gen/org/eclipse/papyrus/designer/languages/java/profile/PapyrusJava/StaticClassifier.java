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
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Classifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier#getBase_Classifier <em>Base Classifier</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getStaticClassifier()
 * @model
 * @generated
 */
public interface StaticClassifier extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Classifier</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Classifier</em>' reference.
	 * @see #setBase_Classifier(Classifier)
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getStaticClassifier_Base_Classifier()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Classifier getBase_Classifier();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier#getBase_Classifier <em>Base Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Classifier</em>' reference.
	 * @see #getBase_Classifier()
	 * @generated
	 */
	void setBase_Classifier(Classifier value);

} // StaticClassifier
