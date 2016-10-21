/**
 * Copyright (c) 2015 CEA LIST
 * 
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Ansgar Radermacher - Initial API and implementation
 * 
 */
package org.eclipse.papyrus.designer.components.FCM;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.components.FCM.FCMPackage
 * @generated
 */
public interface FCMFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FCMFactory eINSTANCE = org.eclipse.papyrus.designer.components.FCM.impl.FCMFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Implementation Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Implementation Group</em>'.
	 * @generated
	 */
	ImplementationGroup createImplementationGroup();

	/**
	 * Returns a new object of class '<em>Comp To OOmapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Comp To OOmapping</em>'.
	 * @generated
	 */
	CompToOOmapping createCompToOOmapping();

	/**
	 * Returns a new object of class '<em>Code Gen Options</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Code Gen Options</em>'.
	 * @generated
	 */
	CodeGenOptions createCodeGenOptions();

	/**
	 * Returns a new object of class '<em>Interaction Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction Component</em>'.
	 * @generated
	 */
	InteractionComponent createInteractionComponent();

	/**
	 * Returns a new object of class '<em>Singleton</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Singleton</em>'.
	 * @generated
	 */
	Singleton createSingleton();

	/**
	 * Returns a new object of class '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector</em>'.
	 * @generated
	 */
	Connector createConnector();

	/**
	 * Returns a new object of class '<em>Use Instance Configurator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Use Instance Configurator</em>'.
	 * @generated
	 */
	UseInstanceConfigurator createUseInstanceConfigurator();

	/**
	 * Returns a new object of class '<em>Instance Configurator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instance Configurator</em>'.
	 * @generated
	 */
	InstanceConfigurator createInstanceConfigurator();

	/**
	 * Returns a new object of class '<em>Rule Application</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule Application</em>'.
	 * @generated
	 */
	RuleApplication createRuleApplication();

	/**
	 * Returns a new object of class '<em>Container Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Rule</em>'.
	 * @generated
	 */
	ContainerRule createContainerRule();

	/**
	 * Returns a new object of class '<em>Template Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template Port</em>'.
	 * @generated
	 */
	TemplatePort createTemplatePort();

	/**
	 * Returns a new object of class '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port</em>'.
	 * @generated
	 */
	Port createPort();

	/**
	 * Returns a new object of class '<em>Port Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Kind</em>'.
	 * @generated
	 */
	PortKind createPortKind();

	/**
	 * Returns a new object of class '<em>Comp Impl Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Comp Impl Template</em>'.
	 * @generated
	 */
	CompImplTemplate createCompImplTemplate();

	/**
	 * Returns a new object of class '<em>Interception Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interception Rule</em>'.
	 * @generated
	 */
	InterceptionRule createInterceptionRule();

	/**
	 * Returns a new object of class '<em>Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fragment</em>'.
	 * @generated
	 */
	Fragment createFragment();

	/**
	 * Returns a new object of class '<em>Assembly</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assembly</em>'.
	 * @generated
	 */
	Assembly createAssembly();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FCMPackage getFCMPackage();

} //FCMFactory
