/**
 * Copyright (c) 2013 CEA LIST
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
package org.eclipse.papyrus.designer.components.FCM.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.papyrus.designer.components.FCM.*;
import org.eclipse.papyrus.designer.components.FCM.CodeGenOptions;
import org.eclipse.papyrus.designer.components.FCM.CompImplTemplate;
import org.eclipse.papyrus.designer.components.FCM.CompToOOmapping;
import org.eclipse.papyrus.designer.components.FCM.ConfigurableElementInstance;
import org.eclipse.papyrus.designer.components.FCM.Connector;
import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.FCM.FCMPackage;
import org.eclipse.papyrus.designer.components.FCM.ImplementationGroup;
import org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.components.FCM.InterceptionRule;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.FCM.RuleApplication;
import org.eclipse.papyrus.designer.components.FCM.Singleton;
import org.eclipse.papyrus.designer.components.FCM.TemplatePort;
import org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.components.FCM.FCMPackage
 * @generated
 */
public class FCMSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FCMPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FCMSwitch() {
		if (modelPackage == null) {
			modelPackage = FCMPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case FCMPackage.IMPLEMENTATION_GROUP: {
				ImplementationGroup implementationGroup = (ImplementationGroup)theEObject;
				T result = caseImplementationGroup(implementationGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.COMP_TO_OOMAPPING: {
				CompToOOmapping compToOOmapping = (CompToOOmapping)theEObject;
				T result = caseCompToOOmapping(compToOOmapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.CODE_GEN_OPTIONS: {
				CodeGenOptions codeGenOptions = (CodeGenOptions)theEObject;
				T result = caseCodeGenOptions(codeGenOptions);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.INTERACTION_COMPONENT: {
				InteractionComponent interactionComponent = (InteractionComponent)theEObject;
				T result = caseInteractionComponent(interactionComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.SINGLETON: {
				Singleton singleton = (Singleton)theEObject;
				T result = caseSingleton(singleton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.CONNECTOR: {
				Connector connector = (Connector)theEObject;
				T result = caseConnector(connector);
				if (result == null) result = caseConfigurableElementInstance(connector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.CONFIGURABLE_ELEMENT_INSTANCE: {
				ConfigurableElementInstance configurableElementInstance = (ConfigurableElementInstance)theEObject;
				T result = caseConfigurableElementInstance(configurableElementInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.USE_INSTANCE_CONFIGURATOR: {
				UseInstanceConfigurator useInstanceConfigurator = (UseInstanceConfigurator)theEObject;
				T result = caseUseInstanceConfigurator(useInstanceConfigurator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.INSTANCE_CONFIGURATOR: {
				InstanceConfigurator instanceConfigurator = (InstanceConfigurator)theEObject;
				T result = caseInstanceConfigurator(instanceConfigurator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.RULE_APPLICATION: {
				RuleApplication ruleApplication = (RuleApplication)theEObject;
				T result = caseRuleApplication(ruleApplication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.CONTAINER_RULE: {
				ContainerRule containerRule = (ContainerRule)theEObject;
				T result = caseContainerRule(containerRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.TEMPLATE_PORT: {
				TemplatePort templatePort = (TemplatePort)theEObject;
				T result = caseTemplatePort(templatePort);
				if (result == null) result = casePort(templatePort);
				if (result == null) result = caseConfigurableElementInstance(templatePort);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.PORT: {
				Port port = (Port)theEObject;
				T result = casePort(port);
				if (result == null) result = caseConfigurableElementInstance(port);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.PORT_KIND: {
				PortKind portKind = (PortKind)theEObject;
				T result = casePortKind(portKind);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.COMP_IMPL_TEMPLATE: {
				CompImplTemplate compImplTemplate = (CompImplTemplate)theEObject;
				T result = caseCompImplTemplate(compImplTemplate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.INTERCEPTION_RULE: {
				InterceptionRule interceptionRule = (InterceptionRule)theEObject;
				T result = caseInterceptionRule(interceptionRule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.FRAGMENT: {
				Fragment fragment = (Fragment)theEObject;
				T result = caseFragment(fragment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case FCMPackage.ASSEMBLY: {
				Assembly assembly = (Assembly)theEObject;
				T result = caseAssembly(assembly);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Implementation Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Implementation Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImplementationGroup(ImplementationGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comp To OOmapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comp To OOmapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompToOOmapping(CompToOOmapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Code Gen Options</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Code Gen Options</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCodeGenOptions(CodeGenOptions object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteractionComponent(InteractionComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Singleton</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Singleton</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleton(Singleton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnector(Connector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configurable Element Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configurable Element Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfigurableElementInstance(ConfigurableElementInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Use Instance Configurator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Instance Configurator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUseInstanceConfigurator(UseInstanceConfigurator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instance Configurator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instance Configurator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanceConfigurator(InstanceConfigurator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule Application</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule Application</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuleApplication(RuleApplication object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerRule(ContainerRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplatePort(TemplatePort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePort(Port object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Kind</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Kind</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortKind(PortKind object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comp Impl Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comp Impl Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompImplTemplate(CompImplTemplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interception Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interception Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterceptionRule(InterceptionRule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFragment(Fragment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssembly(Assembly object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // FCMSwitch
