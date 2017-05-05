/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IInteraction Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Can extend either a class or a collaboration. Collaborations are preferable, since they are used to capture collaboration roles. But some modelers might not manage collaboration diagrams correctly.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition#getBase_StructuredClassifier <em>Base Structured Classifier</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage#getIInteractionDefinition()
 * @model abstract="true"
 * @generated
 */
public interface IInteractionDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Structured Classifier</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Structured Classifier</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Structured Classifier</em>' reference.
	 * @see #setBase_StructuredClassifier(StructuredClassifier)
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.Ucm_interactionsPackage#getIInteractionDefinition_Base_StructuredClassifier()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	StructuredClassifier getBase_StructuredClassifier();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IInteractionDefinition#getBase_StructuredClassifier <em>Base Structured Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Structured Classifier</em>' reference.
	 * @see #getBase_StructuredClassifier()
	 * @generated
	 */
	void setBase_StructuredClassifier(StructuredClassifier value);

} // IInteractionDefinition
