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
package org.eclipse.papyrus.designer.components.FCM.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.papyrus.designer.components.FCM.*;
import org.eclipse.papyrus.designer.components.FCM.CodeGenOptions;
import org.eclipse.papyrus.designer.components.FCM.CompImplTemplate;
import org.eclipse.papyrus.designer.components.FCM.CompToOOmapping;
import org.eclipse.papyrus.designer.components.FCM.Connector;
import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.FCM.ContainerRuleKind;
import org.eclipse.papyrus.designer.components.FCM.FCMFactory;
import org.eclipse.papyrus.designer.components.FCM.FCMPackage;
import org.eclipse.papyrus.designer.components.FCM.ImplementationGroup;
import org.eclipse.papyrus.designer.components.FCM.InstanceConfigurator;
import org.eclipse.papyrus.designer.components.FCM.InteractionComponent;
import org.eclipse.papyrus.designer.components.FCM.InterceptionKind;
import org.eclipse.papyrus.designer.components.FCM.InterceptionRule;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.FCM.RuleApplication;
import org.eclipse.papyrus.designer.components.FCM.Singleton;
import org.eclipse.papyrus.designer.components.FCM.TemplatePort;
import org.eclipse.papyrus.designer.components.FCM.UseInstanceConfigurator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FCMFactoryImpl extends EFactoryImpl implements FCMFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FCMFactory init() {
		try {
			FCMFactory theFCMFactory = (FCMFactory)EPackage.Registry.INSTANCE.getEFactory(FCMPackage.eNS_URI);
			if (theFCMFactory != null) {
				return theFCMFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FCMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FCMFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case FCMPackage.IMPLEMENTATION_GROUP: return createImplementationGroup();
			case FCMPackage.COMP_TO_OOMAPPING: return createCompToOOmapping();
			case FCMPackage.CODE_GEN_OPTIONS: return createCodeGenOptions();
			case FCMPackage.INTERACTION_COMPONENT: return createInteractionComponent();
			case FCMPackage.SINGLETON: return createSingleton();
			case FCMPackage.CONNECTOR: return createConnector();
			case FCMPackage.USE_INSTANCE_CONFIGURATOR: return createUseInstanceConfigurator();
			case FCMPackage.INSTANCE_CONFIGURATOR: return createInstanceConfigurator();
			case FCMPackage.RULE_APPLICATION: return createRuleApplication();
			case FCMPackage.CONTAINER_RULE: return createContainerRule();
			case FCMPackage.TEMPLATE_PORT: return createTemplatePort();
			case FCMPackage.PORT: return createPort();
			case FCMPackage.PORT_KIND: return createPortKind();
			case FCMPackage.COMP_IMPL_TEMPLATE: return createCompImplTemplate();
			case FCMPackage.INTERCEPTION_RULE: return createInterceptionRule();
			case FCMPackage.FRAGMENT: return createFragment();
			case FCMPackage.ASSEMBLY: return createAssembly();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case FCMPackage.CONTAINER_RULE_KIND:
				return createContainerRuleKindFromString(eDataType, initialValue);
			case FCMPackage.INTERCEPTION_KIND:
				return createInterceptionKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case FCMPackage.CONTAINER_RULE_KIND:
				return convertContainerRuleKindToString(eDataType, instanceValue);
			case FCMPackage.INTERCEPTION_KIND:
				return convertInterceptionKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ImplementationGroup createImplementationGroup() {
		ImplementationGroupImpl implementationGroup = new ImplementationGroupImpl();
		return implementationGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompToOOmapping createCompToOOmapping() {
		CompToOOmappingImpl compToOOmapping = new CompToOOmappingImpl();
		return compToOOmapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CodeGenOptions createCodeGenOptions() {
		CodeGenOptionsImpl codeGenOptions = new CodeGenOptionsImpl();
		return codeGenOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InteractionComponent createInteractionComponent() {
		InteractionComponentImpl interactionComponent = new InteractionComponentImpl();
		return interactionComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Singleton createSingleton() {
		SingletonImpl singleton = new SingletonImpl();
		return singleton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connector createConnector() {
		ConnectorImpl connector = new ConnectorImpl();
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UseInstanceConfigurator createUseInstanceConfigurator() {
		UseInstanceConfiguratorImpl useInstanceConfigurator = new UseInstanceConfiguratorImpl();
		return useInstanceConfigurator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstanceConfigurator createInstanceConfigurator() {
		InstanceConfiguratorImpl instanceConfigurator = new InstanceConfiguratorImpl();
		return instanceConfigurator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RuleApplication createRuleApplication() {
		RuleApplicationImpl ruleApplication = new RuleApplicationImpl();
		return ruleApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerRule createContainerRule() {
		ContainerRuleImpl containerRule = new ContainerRuleImpl();
		return containerRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplatePort createTemplatePort() {
		TemplatePortImpl templatePort = new TemplatePortImpl();
		return templatePort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Port createPort() {
		PortImpl port = new PortImpl();
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PortKind createPortKind() {
		PortKindImpl portKind = new PortKindImpl();
		return portKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompImplTemplate createCompImplTemplate() {
		CompImplTemplateImpl compImplTemplate = new CompImplTemplateImpl();
		return compImplTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterceptionRule createInterceptionRule() {
		InterceptionRuleImpl interceptionRule = new InterceptionRuleImpl();
		return interceptionRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment createFragment() {
		FragmentImpl fragment = new FragmentImpl();
		return fragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Assembly createAssembly() {
		AssemblyImpl assembly = new AssemblyImpl();
		return assembly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerRuleKind createContainerRuleKindFromString(EDataType eDataType, String initialValue) {
		ContainerRuleKind result = ContainerRuleKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContainerRuleKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterceptionKind createInterceptionKindFromString(EDataType eDataType, String initialValue) {
		InterceptionKind result = InterceptionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInterceptionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FCMPackage getFCMPackage() {
		return (FCMPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FCMPackage getPackage() {
		return FCMPackage.eINSTANCE;
	}

} // FCMFactoryImpl
