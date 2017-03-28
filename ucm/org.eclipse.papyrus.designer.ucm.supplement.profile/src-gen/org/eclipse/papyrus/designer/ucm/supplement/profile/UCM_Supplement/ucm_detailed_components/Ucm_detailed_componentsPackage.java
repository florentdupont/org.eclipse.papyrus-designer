/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.Ucm_componentsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.Ucm_detailed_componentsFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_detailed_componentsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_detailed_components";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/detailedcomp/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCM_Supplement.ucm_detailed_components";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_detailed_componentsPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.DetailedComponentImplementationImpl <em>Detailed Component Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.DetailedComponentImplementationImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getDetailedComponentImplementation()
	 * @generated
	 */
	int DETAILED_COMPONENT_IMPLEMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAILED_COMPONENT_IMPLEMENTATION__BASE_CLASS = Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION__BASE_CLASS;

	/**
	 * The feature id for the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAILED_COMPONENT_IMPLEMENTATION__LANGUAGE = Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Port Implementation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION = Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Detailed Component Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAILED_COMPONENT_IMPLEMENTATION_FEATURE_COUNT = Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Detailed Component Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAILED_COMPONENT_IMPLEMENTATION_OPERATION_COUNT = Ucm_componentsPackage.ATOMIC_COMPONENT_IMPLEMENTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.IPortElementImplementationImpl <em>IPort Element Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.IPortElementImplementationImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getIPortElementImplementation()
	 * @generated
	 */
	int IPORT_ELEMENT_IMPLEMENTATION = 1;

	/**
	 * The feature id for the '<em><b>Port Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS = 1;

	/**
	 * The number of structural features of the '<em>IPort Element Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_ELEMENT_IMPLEMENTATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>IPort Element Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPORT_ELEMENT_IMPLEMENTATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.ComponentPortImplementationImpl <em>Component Port Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.ComponentPortImplementationImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getComponentPortImplementation()
	 * @generated
	 */
	int COMPONENT_PORT_IMPLEMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Port Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_IMPLEMENTATION__PORT_ELEMENT = IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_IMPLEMENTATION__BASE_CLASS = IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_IMPLEMENTATION__PORT = IPORT_ELEMENT_IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Port Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_IMPLEMENTATION_FEATURE_COUNT = IPORT_ELEMENT_IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Component Port Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PORT_IMPLEMENTATION_OPERATION_COUNT = IPORT_ELEMENT_IMPLEMENTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.TechnicalPortImplementationImpl <em>Technical Port Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.TechnicalPortImplementationImpl
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getTechnicalPortImplementation()
	 * @generated
	 */
	int TECHNICAL_PORT_IMPLEMENTATION = 3;

	/**
	 * The feature id for the '<em><b>Port Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_PORT_IMPLEMENTATION__PORT_ELEMENT = IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_PORT_IMPLEMENTATION__BASE_CLASS = IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_PORT_IMPLEMENTATION__POLICY = IPORT_ELEMENT_IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Technical Port Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_PORT_IMPLEMENTATION_FEATURE_COUNT = IPORT_ELEMENT_IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Technical Port Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_PORT_IMPLEMENTATION_OPERATION_COUNT = IPORT_ELEMENT_IMPLEMENTATION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation <em>Detailed Component Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Detailed Component Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation
	 * @generated
	 */
	EClass getDetailedComponentImplementation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation#getPortImplementation <em>Port Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.DetailedComponentImplementation#getPortImplementation()
	 * @see #getDetailedComponentImplementation()
	 * @generated
	 */
	EReference getDetailedComponentImplementation_PortImplementation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation <em>IPort Element Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IPort Element Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation
	 * @generated
	 */
	EClass getIPortElementImplementation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation#getPortElement <em>Port Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port Element</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation#getPortElement()
	 * @see #getIPortElementImplementation()
	 * @generated
	 */
	EReference getIPortElementImplementation_PortElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.IPortElementImplementation#getBase_Class()
	 * @see #getIPortElementImplementation()
	 * @generated
	 */
	EReference getIPortElementImplementation_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation <em>Component Port Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Port Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation
	 * @generated
	 */
	EClass getComponentPortImplementation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.ComponentPortImplementation#getPort()
	 * @see #getComponentPortImplementation()
	 * @generated
	 */
	EReference getComponentPortImplementation_Port();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation <em>Technical Port Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Technical Port Implementation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation
	 * @generated
	 */
	EClass getTechnicalPortImplementation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Policy</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.TechnicalPortImplementation#getPolicy()
	 * @see #getTechnicalPortImplementation()
	 * @generated
	 */
	EReference getTechnicalPortImplementation_Policy();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_detailed_componentsFactory getUcm_detailed_componentsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.DetailedComponentImplementationImpl <em>Detailed Component Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.DetailedComponentImplementationImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getDetailedComponentImplementation()
		 * @generated
		 */
		EClass DETAILED_COMPONENT_IMPLEMENTATION = eINSTANCE.getDetailedComponentImplementation();

		/**
		 * The meta object literal for the '<em><b>Port Implementation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DETAILED_COMPONENT_IMPLEMENTATION__PORT_IMPLEMENTATION = eINSTANCE.getDetailedComponentImplementation_PortImplementation();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.IPortElementImplementationImpl <em>IPort Element Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.IPortElementImplementationImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getIPortElementImplementation()
		 * @generated
		 */
		EClass IPORT_ELEMENT_IMPLEMENTATION = eINSTANCE.getIPortElementImplementation();

		/**
		 * The meta object literal for the '<em><b>Port Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPORT_ELEMENT_IMPLEMENTATION__PORT_ELEMENT = eINSTANCE.getIPortElementImplementation_PortElement();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IPORT_ELEMENT_IMPLEMENTATION__BASE_CLASS = eINSTANCE.getIPortElementImplementation_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.ComponentPortImplementationImpl <em>Component Port Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.ComponentPortImplementationImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getComponentPortImplementation()
		 * @generated
		 */
		EClass COMPONENT_PORT_IMPLEMENTATION = eINSTANCE.getComponentPortImplementation();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PORT_IMPLEMENTATION__PORT = eINSTANCE.getComponentPortImplementation_Port();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.TechnicalPortImplementationImpl <em>Technical Port Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.TechnicalPortImplementationImpl
		 * @see org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_detailed_components.impl.Ucm_detailed_componentsPackageImpl#getTechnicalPortImplementation()
		 * @generated
		 */
		EClass TECHNICAL_PORT_IMPLEMENTATION = eINSTANCE.getTechnicalPortImplementation();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TECHNICAL_PORT_IMPLEMENTATION__POLICY = eINSTANCE.getTechnicalPortImplementation_Policy();

	}

} //Ucm_detailed_componentsPackage
