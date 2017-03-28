/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Ucm_contractsFactory
 * @model kind="package"
 * @generated
 */
public interface Ucm_contractsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm_contracts";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.omg.org/ucm/contracts/0.9";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "UCMProfile.ucm_contracts";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Ucm_contractsPackage eINSTANCE = org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDeclarationImpl <em>Abstract Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDeclarationImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAbstractDeclaration()
	 * @generated
	 */
	int ABSTRACT_DECLARATION = 0;

	/**
	 * The number of structural features of the '<em>Abstract Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DECLARATION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Abstract Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DECLARATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfiguredImpl <em>IConfigured</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfiguredImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigured()
	 * @generated
	 */
	int ICONFIGURED = 2;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURED__VALUES = 0;

	/**
	 * The number of structural features of the '<em>IConfigured</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURED_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IConfigured</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 1;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__VALUES = ICONFIGURED__VALUES;

	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__DEFINITION = ICONFIGURED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base Comment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__BASE_COMMENT = ICONFIGURED_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = ICONFIGURED_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_OPERATION_COUNT = ICONFIGURED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurableImpl <em>IConfigurable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurableImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigurable()
	 * @generated
	 */
	int ICONFIGURABLE = 12;

	/**
	 * The number of structural features of the '<em>IConfigurable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURABLE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IConfigurable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationDefinitionImpl <em>Annotation Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationDefinitionImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAnnotationDefinition()
	 * @generated
	 */
	int ANNOTATION_DEFINITION = 3;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DEFINITION__BASE_CLASS = ICONFIGURABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Annotation Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DEFINITION_FEATURE_COUNT = ICONFIGURABLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Annotation Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_DEFINITION_OPERATION_COUNT = ICONFIGURABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterImpl <em>IConfiguration Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigurationParameter()
	 * @generated
	 */
	int ICONFIGURATION_PARAMETER = 5;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURATION_PARAMETER__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>IConfiguration Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURATION_PARAMETER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IConfiguration Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURATION_PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterImpl <em>Configuration Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getConfigurationParameter()
	 * @generated
	 */
	int CONFIGURATION_PARAMETER = 4;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETER__BASE_PROPERTY = ICONFIGURATION_PARAMETER__BASE_PROPERTY;

	/**
	 * The number of structural features of the '<em>Configuration Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETER_FEATURE_COUNT = ICONFIGURATION_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Configuration Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETER_OPERATION_COUNT = ICONFIGURATION_PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ContractModuleImpl <em>Contract Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ContractModuleImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getContractModule()
	 * @generated
	 */
	int CONTRACT_MODULE = 6;

	/**
	 * The feature id for the '<em><b>Base Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRACT_MODULE__BASE_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Contract Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRACT_MODULE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Contract Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRACT_MODULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterValueImpl <em>IConfiguration Parameter Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterValueImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigurationParameterValue()
	 * @generated
	 */
	int ICONFIGURATION_PARAMETER_VALUE = 7;

	/**
	 * The feature id for the '<em><b>Base Slot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURATION_PARAMETER_VALUE__BASE_SLOT = 0;

	/**
	 * The number of structural features of the '<em>IConfiguration Parameter Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURATION_PARAMETER_VALUE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>IConfiguration Parameter Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONFIGURATION_PARAMETER_VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterValueImpl <em>Configuration Parameter Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterValueImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getConfigurationParameterValue()
	 * @generated
	 */
	int CONFIGURATION_PARAMETER_VALUE = 8;

	/**
	 * The feature id for the '<em><b>Base Slot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETER_VALUE__BASE_SLOT = ICONFIGURATION_PARAMETER_VALUE__BASE_SLOT;

	/**
	 * The number of structural features of the '<em>Configuration Parameter Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETER_VALUE_FEATURE_COUNT = ICONFIGURATION_PARAMETER_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Configuration Parameter Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PARAMETER_VALUE_OPERATION_COUNT = ICONFIGURATION_PARAMETER_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractInterfaceImpl <em>Abstract Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractInterfaceImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAbstractInterface()
	 * @generated
	 */
	int ABSTRACT_INTERFACE = 9;

	/**
	 * The feature id for the '<em><b>Base Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INTERFACE__BASE_INTERFACE = ABSTRACT_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INTERFACE_FEATURE_COUNT = ABSTRACT_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Abstract Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_INTERFACE_OPERATION_COUNT = ABSTRACT_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDataTypeImpl <em>Abstract Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDataTypeImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAbstractDataType()
	 * @generated
	 */
	int ABSTRACT_DATA_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Base Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TYPE__BASE_DATA_TYPE = ABSTRACT_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TYPE_FEATURE_COUNT = ABSTRACT_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Abstract Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_DATA_TYPE_OPERATION_COUNT = ABSTRACT_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConstantImpl <em>Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConstantImpl
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getConstant()
	 * @generated
	 */
	int CONSTANT = 11;

	/**
	 * The feature id for the '<em><b>Base Instance Specification</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT__BASE_INSTANCE_SPECIFICATION = 0;

	/**
	 * The number of structural features of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration <em>Abstract Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Declaration</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration
	 * @generated
	 */
	EClass getAbstractDeclaration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Definition</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation#getDefinition()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_Definition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation#getBase_Comment <em>Base Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Comment</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Annotation#getBase_Comment()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_Base_Comment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured <em>IConfigured</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConfigured</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured
	 * @generated
	 */
	EClass getIConfigured();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Values</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigured#getValues()
	 * @see #getIConfigured()
	 * @generated
	 */
	EReference getIConfigured_Values();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AnnotationDefinition <em>Annotation Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Definition</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AnnotationDefinition
	 * @generated
	 */
	EClass getAnnotationDefinition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AnnotationDefinition#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AnnotationDefinition#getBase_Class()
	 * @see #getAnnotationDefinition()
	 * @generated
	 */
	EReference getAnnotationDefinition_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameter <em>Configuration Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Parameter</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameter
	 * @generated
	 */
	EClass getConfigurationParameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameter <em>IConfiguration Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConfiguration Parameter</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameter
	 * @generated
	 */
	EClass getIConfigurationParameter();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameter#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameter#getBase_Property()
	 * @see #getIConfigurationParameter()
	 * @generated
	 */
	EReference getIConfigurationParameter_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule <em>Contract Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contract Module</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule
	 * @generated
	 */
	EClass getContractModule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule#getBase_Package <em>Base Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Package</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ContractModule#getBase_Package()
	 * @see #getContractModule()
	 * @generated
	 */
	EReference getContractModule_Base_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue <em>IConfiguration Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConfiguration Parameter Value</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue
	 * @generated
	 */
	EClass getIConfigurationParameterValue();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue#getBase_Slot <em>Base Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Slot</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurationParameterValue#getBase_Slot()
	 * @see #getIConfigurationParameterValue()
	 * @generated
	 */
	EReference getIConfigurationParameterValue_Base_Slot();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameterValue <em>Configuration Parameter Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Parameter Value</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.ConfigurationParameterValue
	 * @generated
	 */
	EClass getConfigurationParameterValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface <em>Abstract Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Interface</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface
	 * @generated
	 */
	EClass getAbstractInterface();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface#getBase_Interface <em>Base Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Interface</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface#getBase_Interface()
	 * @see #getAbstractInterface()
	 * @generated
	 */
	EReference getAbstractInterface_Base_Interface();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType <em>Abstract Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Data Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType
	 * @generated
	 */
	EClass getAbstractDataType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType#getBase_DataType <em>Base Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Data Type</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType#getBase_DataType()
	 * @see #getAbstractDataType()
	 * @generated
	 */
	EReference getAbstractDataType_Base_DataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Constant
	 * @generated
	 */
	EClass getConstant();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Constant#getBase_InstanceSpecification <em>Base Instance Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Instance Specification</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.Constant#getBase_InstanceSpecification()
	 * @see #getConstant()
	 * @generated
	 */
	EReference getConstant_Base_InstanceSpecification();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable <em>IConfigurable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConfigurable</em>'.
	 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.IConfigurable
	 * @generated
	 */
	EClass getIConfigurable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Ucm_contractsFactory getUcm_contractsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDeclarationImpl <em>Abstract Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDeclarationImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAbstractDeclaration()
		 * @generated
		 */
		EClass ABSTRACT_DECLARATION = eINSTANCE.getAbstractDeclaration();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__DEFINITION = eINSTANCE.getAnnotation_Definition();

		/**
		 * The meta object literal for the '<em><b>Base Comment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__BASE_COMMENT = eINSTANCE.getAnnotation_Base_Comment();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfiguredImpl <em>IConfigured</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfiguredImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigured()
		 * @generated
		 */
		EClass ICONFIGURED = eINSTANCE.getIConfigured();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICONFIGURED__VALUES = eINSTANCE.getIConfigured_Values();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationDefinitionImpl <em>Annotation Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AnnotationDefinitionImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAnnotationDefinition()
		 * @generated
		 */
		EClass ANNOTATION_DEFINITION = eINSTANCE.getAnnotationDefinition();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION_DEFINITION__BASE_CLASS = eINSTANCE.getAnnotationDefinition_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterImpl <em>Configuration Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getConfigurationParameter()
		 * @generated
		 */
		EClass CONFIGURATION_PARAMETER = eINSTANCE.getConfigurationParameter();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterImpl <em>IConfiguration Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigurationParameter()
		 * @generated
		 */
		EClass ICONFIGURATION_PARAMETER = eINSTANCE.getIConfigurationParameter();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICONFIGURATION_PARAMETER__BASE_PROPERTY = eINSTANCE.getIConfigurationParameter_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ContractModuleImpl <em>Contract Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ContractModuleImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getContractModule()
		 * @generated
		 */
		EClass CONTRACT_MODULE = eINSTANCE.getContractModule();

		/**
		 * The meta object literal for the '<em><b>Base Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRACT_MODULE__BASE_PACKAGE = eINSTANCE.getContractModule_Base_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterValueImpl <em>IConfiguration Parameter Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurationParameterValueImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigurationParameterValue()
		 * @generated
		 */
		EClass ICONFIGURATION_PARAMETER_VALUE = eINSTANCE.getIConfigurationParameterValue();

		/**
		 * The meta object literal for the '<em><b>Base Slot</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ICONFIGURATION_PARAMETER_VALUE__BASE_SLOT = eINSTANCE.getIConfigurationParameterValue_Base_Slot();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterValueImpl <em>Configuration Parameter Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConfigurationParameterValueImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getConfigurationParameterValue()
		 * @generated
		 */
		EClass CONFIGURATION_PARAMETER_VALUE = eINSTANCE.getConfigurationParameterValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractInterfaceImpl <em>Abstract Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractInterfaceImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAbstractInterface()
		 * @generated
		 */
		EClass ABSTRACT_INTERFACE = eINSTANCE.getAbstractInterface();

		/**
		 * The meta object literal for the '<em><b>Base Interface</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_INTERFACE__BASE_INTERFACE = eINSTANCE.getAbstractInterface_Base_Interface();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDataTypeImpl <em>Abstract Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.AbstractDataTypeImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getAbstractDataType()
		 * @generated
		 */
		EClass ABSTRACT_DATA_TYPE = eINSTANCE.getAbstractDataType();

		/**
		 * The meta object literal for the '<em><b>Base Data Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_DATA_TYPE__BASE_DATA_TYPE = eINSTANCE.getAbstractDataType_Base_DataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConstantImpl <em>Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.ConstantImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getConstant()
		 * @generated
		 */
		EClass CONSTANT = eINSTANCE.getConstant();

		/**
		 * The meta object literal for the '<em><b>Base Instance Specification</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTANT__BASE_INSTANCE_SPECIFICATION = eINSTANCE.getConstant_Base_InstanceSpecification();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurableImpl <em>IConfigurable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.IConfigurableImpl
		 * @see org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.impl.Ucm_contractsPackageImpl#getIConfigurable()
		 * @generated
		 */
		EClass ICONFIGURABLE = eINSTANCE.getIConfigurable();

	}

} //Ucm_contractsPackage
