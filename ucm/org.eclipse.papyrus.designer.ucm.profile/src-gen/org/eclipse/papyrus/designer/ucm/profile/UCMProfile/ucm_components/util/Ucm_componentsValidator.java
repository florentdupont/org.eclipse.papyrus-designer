/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage
 * @generated
 */
public class Ucm_componentsValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final Ucm_componentsValidator INSTANCE = new Ucm_componentsValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Constraint1' of 'Assembly Part'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int ASSEMBLY_PART__CONSTRAINT1 = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ucm_componentsValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return Ucm_componentsPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case Ucm_componentsPackage.INTERACTION_ITEM_BINDING:
				return validateInteractionItemBinding((InteractionItemBinding)value, diagnostics, context);
			case Ucm_componentsPackage.IBINDING:
				return validateIBinding((IBinding)value, diagnostics, context);
			case Ucm_componentsPackage.PORT_ROLE_SPEC:
				return validatePortRoleSpec((PortRoleSpec)value, diagnostics, context);
			case Ucm_componentsPackage.IPORT_SPEC:
				return validateIPortSpec((IPortSpec)value, diagnostics, context);
			case Ucm_componentsPackage.PORT_TYPE_SPEC:
				return validatePortTypeSpec((PortTypeSpec)value, diagnostics, context);
			case Ucm_componentsPackage.CONNECTION:
				return validateConnection((Connection)value, diagnostics, context);
			case Ucm_componentsPackage.CONNECTION_LINK:
				return validateConnectionLink((ConnectionLink)value, diagnostics, context);
			case Ucm_componentsPackage.TECHNICAL_POLICY:
				return validateTechnicalPolicy((TechnicalPolicy)value, diagnostics, context);
			case Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION:
				return validateAtomicComponentImplementation((AtomicComponentImplementation)value, diagnostics, context);
			case Ucm_componentsPackage.ICOMPONENT_IMPLEMENTATION:
				return validateIComponentImplementation((IComponentImplementation)value, diagnostics, context);
			case Ucm_componentsPackage.ICOMPONENT:
				return validateIComponent((IComponent)value, diagnostics, context);
			case Ucm_componentsPackage.COMPONENT_TYPE:
				return validateComponentType((ComponentType)value, diagnostics, context);
			case Ucm_componentsPackage.COMPOSITE_COMPONENT_IMPLEMENTATION:
				return validateCompositeComponentImplementation((CompositeComponentImplementation)value, diagnostics, context);
			case Ucm_componentsPackage.ASSEMBLY_PART:
				return validateAssemblyPart((AssemblyPart)value, diagnostics, context);
			case Ucm_componentsPackage.ABSTRACT_TYPE_BINDING:
				return validateAbstractTypeBinding((AbstractTypeBinding)value, diagnostics, context);
			case Ucm_componentsPackage.COMPONENT_MODULE:
				return validateComponentModule((ComponentModule)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInteractionItemBinding(InteractionItemBinding interactionItemBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(interactionItemBinding, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIBinding(IBinding iBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(iBinding, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortRoleSpec(PortRoleSpec portRoleSpec, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(portRoleSpec, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIPortSpec(IPortSpec iPortSpec, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(iPortSpec, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortTypeSpec(PortTypeSpec portTypeSpec, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(portTypeSpec, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConnection(Connection connection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(connection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConnectionLink(ConnectionLink connectionLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(connectionLink, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTechnicalPolicy(TechnicalPolicy technicalPolicy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(technicalPolicy, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAtomicComponentImplementation(AtomicComponentImplementation atomicComponentImplementation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(atomicComponentImplementation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIComponentImplementation(IComponentImplementation iComponentImplementation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(iComponentImplementation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIComponent(IComponent iComponent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(iComponent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentType(ComponentType componentType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(componentType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompositeComponentImplementation(CompositeComponentImplementation compositeComponentImplementation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(compositeComponentImplementation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssemblyPart(AssemblyPart assemblyPart, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(assemblyPart, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(assemblyPart, diagnostics, context);
		if (result || diagnostics != null) result &= validateAssemblyPart_Constraint1(assemblyPart, diagnostics, context);
		return result;
	}

	/**
	 * Validates the Constraint1 constraint of '<em>Assembly Part</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssemblyPart_Constraint1(AssemblyPart assemblyPart, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return assemblyPart.Constraint1(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractTypeBinding(AbstractTypeBinding abstractTypeBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(abstractTypeBinding, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentModule(ComponentModule componentModule, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(componentModule, diagnostics, context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //Ucm_componentsValidator
