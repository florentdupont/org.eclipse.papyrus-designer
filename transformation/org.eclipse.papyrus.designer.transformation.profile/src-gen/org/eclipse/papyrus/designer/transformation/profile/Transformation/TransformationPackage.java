/**
 */
package org.eclipse.papyrus.designer.transformation.profile.Transformation;

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
 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationFactory
 * @model kind="package"
 * @generated
 */
public interface TransformationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Transformation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/Transformation/1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Transformation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TransformationPackage eINSTANCE = org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ApplyTransformationImpl <em>Apply Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ApplyTransformationImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getApplyTransformation()
	 * @generated
	 */
	int APPLY_TRANSFORMATION = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLY_TRANSFORMATION__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Trafo</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLY_TRANSFORMATION__TRAFO = 1;

	/**
	 * The number of structural features of the '<em>Apply Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLY_TRANSFORMATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Apply Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLY_TRANSFORMATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoImpl <em>M2M Trafo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafo()
	 * @generated
	 */
	int M2M_TRAFO = 1;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Trafo Kind</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO__TRAFO_KIND = 1;

	/**
	 * The number of structural features of the '<em>M2M Trafo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>M2M Trafo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoKindImpl <em>M2M Trafo Kind</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoKindImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafoKind()
	 * @generated
	 */
	int M2M_TRAFO_KIND = 2;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_KIND__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>M2M Trafo Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_KIND_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>M2M Trafo Kind</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_KIND_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoChainImpl <em>M2M Trafo Chain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoChainImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafoChain()
	 * @generated
	 */
	int M2M_TRAFO_CHAIN = 3;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_CHAIN__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>M2M Trafo Chain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_CHAIN_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>M2M Trafo Chain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_CHAIN_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ConfigurationPropertyImpl <em>Configuration Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ConfigurationPropertyImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getConfigurationProperty()
	 * @generated
	 */
	int CONFIGURATION_PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Configuration Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Configuration Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexImpl <em>Auto Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getAutoIndex()
	 * @generated
	 */
	int AUTO_INDEX = 5;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Auto Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Auto Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexPerNodeImpl <em>Auto Index Per Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexPerNodeImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getAutoIndexPerNode()
	 * @generated
	 */
	int AUTO_INDEX_PER_NODE = 6;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_PER_NODE__BASE_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Auto Index Per Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_PER_NODE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Auto Index Per Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTO_INDEX_PER_NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.CopyAttributeValueImpl <em>Copy Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.CopyAttributeValueImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getCopyAttributeValue()
	 * @generated
	 */
	int COPY_ATTRIBUTE_VALUE = 7;

	/**
	 * The feature id for the '<em><b>Base Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE__BASE_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE__SOURCE = 1;

	/**
	 * The number of structural features of the '<em>Copy Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Copy Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPY_ATTRIBUTE_VALUE_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.DerivedElementImpl <em>Derived Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.DerivedElementImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getDerivedElement()
	 * @generated
	 */
	int DERIVED_ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Base Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_ELEMENT__BASE_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_ELEMENT__SOURCE = 1;

	/**
	 * The number of structural features of the '<em>Derived Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Derived Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_ELEMENT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation <em>Apply Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Apply Transformation</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation
	 * @generated
	 */
	EClass getApplyTransformation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getBase_Class()
	 * @see #getApplyTransformation()
	 * @generated
	 */
	EReference getApplyTransformation_Base_Class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getTrafo <em>Trafo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Trafo</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getTrafo()
	 * @see #getApplyTransformation()
	 * @generated
	 */
	EReference getApplyTransformation_Trafo();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo <em>M2M Trafo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>M2M Trafo</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo
	 * @generated
	 */
	EClass getM2MTrafo();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBase_Class()
	 * @see #getM2MTrafo()
	 * @generated
	 */
	EReference getM2MTrafo_Base_Class();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getTrafoKind <em>Trafo Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Trafo Kind</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getTrafoKind()
	 * @see #getM2MTrafo()
	 * @generated
	 */
	EReference getM2MTrafo_TrafoKind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoKind <em>M2M Trafo Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>M2M Trafo Kind</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoKind
	 * @generated
	 */
	EClass getM2MTrafoKind();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoKind#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoKind#getBase_Class()
	 * @see #getM2MTrafoKind()
	 * @generated
	 */
	EReference getM2MTrafoKind_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain <em>M2M Trafo Chain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>M2M Trafo Chain</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain
	 * @generated
	 */
	EClass getM2MTrafoChain();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain#getBase_Class()
	 * @see #getM2MTrafoChain()
	 * @generated
	 */
	EReference getM2MTrafoChain_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ConfigurationProperty <em>Configuration Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Property</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ConfigurationProperty
	 * @generated
	 */
	EClass getConfigurationProperty();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ConfigurationProperty#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ConfigurationProperty#getBase_Property()
	 * @see #getConfigurationProperty()
	 * @generated
	 */
	EReference getConfigurationProperty_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndex <em>Auto Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Auto Index</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndex
	 * @generated
	 */
	EClass getAutoIndex();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndex#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndex#getBase_Property()
	 * @see #getAutoIndex()
	 * @generated
	 */
	EReference getAutoIndex_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndexPerNode <em>Auto Index Per Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Auto Index Per Node</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndexPerNode
	 * @generated
	 */
	EClass getAutoIndexPerNode();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndexPerNode#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.AutoIndexPerNode#getBase_Property()
	 * @see #getAutoIndexPerNode()
	 * @generated
	 */
	EReference getAutoIndexPerNode_Base_Property();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.CopyAttributeValue <em>Copy Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Copy Attribute Value</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.CopyAttributeValue
	 * @generated
	 */
	EClass getCopyAttributeValue();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.CopyAttributeValue#getBase_Property <em>Base Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Property</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.CopyAttributeValue#getBase_Property()
	 * @see #getCopyAttributeValue()
	 * @generated
	 */
	EReference getCopyAttributeValue_Base_Property();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.CopyAttributeValue#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.CopyAttributeValue#getSource()
	 * @see #getCopyAttributeValue()
	 * @generated
	 */
	EReference getCopyAttributeValue_Source();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement <em>Derived Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Element</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement
	 * @generated
	 */
	EClass getDerivedElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getBase_Element <em>Base Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Element</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getBase_Element()
	 * @see #getDerivedElement()
	 * @generated
	 */
	EReference getDerivedElement_Base_Element();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.DerivedElement#getSource()
	 * @see #getDerivedElement()
	 * @generated
	 */
	EReference getDerivedElement_Source();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TransformationFactory getTransformationFactory();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ApplyTransformationImpl <em>Apply Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ApplyTransformationImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getApplyTransformation()
		 * @generated
		 */
		EClass APPLY_TRANSFORMATION = eINSTANCE.getApplyTransformation();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLY_TRANSFORMATION__BASE_CLASS = eINSTANCE.getApplyTransformation_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Trafo</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLY_TRANSFORMATION__TRAFO = eINSTANCE.getApplyTransformation_Trafo();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoImpl <em>M2M Trafo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafo()
		 * @generated
		 */
		EClass M2M_TRAFO = eINSTANCE.getM2MTrafo();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference M2M_TRAFO__BASE_CLASS = eINSTANCE.getM2MTrafo_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Trafo Kind</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference M2M_TRAFO__TRAFO_KIND = eINSTANCE.getM2MTrafo_TrafoKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoKindImpl <em>M2M Trafo Kind</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoKindImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafoKind()
		 * @generated
		 */
		EClass M2M_TRAFO_KIND = eINSTANCE.getM2MTrafoKind();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference M2M_TRAFO_KIND__BASE_CLASS = eINSTANCE.getM2MTrafoKind_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoChainImpl <em>M2M Trafo Chain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoChainImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafoChain()
		 * @generated
		 */
		EClass M2M_TRAFO_CHAIN = eINSTANCE.getM2MTrafoChain();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference M2M_TRAFO_CHAIN__BASE_CLASS = eINSTANCE.getM2MTrafoChain_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ConfigurationPropertyImpl <em>Configuration Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.ConfigurationPropertyImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getConfigurationProperty()
		 * @generated
		 */
		EClass CONFIGURATION_PROPERTY = eINSTANCE.getConfigurationProperty();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION_PROPERTY__BASE_PROPERTY = eINSTANCE.getConfigurationProperty_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexImpl <em>Auto Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getAutoIndex()
		 * @generated
		 */
		EClass AUTO_INDEX = eINSTANCE.getAutoIndex();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTO_INDEX__BASE_PROPERTY = eINSTANCE.getAutoIndex_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexPerNodeImpl <em>Auto Index Per Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.AutoIndexPerNodeImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getAutoIndexPerNode()
		 * @generated
		 */
		EClass AUTO_INDEX_PER_NODE = eINSTANCE.getAutoIndexPerNode();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTO_INDEX_PER_NODE__BASE_PROPERTY = eINSTANCE.getAutoIndexPerNode_Base_Property();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.CopyAttributeValueImpl <em>Copy Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.CopyAttributeValueImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getCopyAttributeValue()
		 * @generated
		 */
		EClass COPY_ATTRIBUTE_VALUE = eINSTANCE.getCopyAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Base Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COPY_ATTRIBUTE_VALUE__BASE_PROPERTY = eINSTANCE.getCopyAttributeValue_Base_Property();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COPY_ATTRIBUTE_VALUE__SOURCE = eINSTANCE.getCopyAttributeValue_Source();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.DerivedElementImpl <em>Derived Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.DerivedElementImpl
		 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getDerivedElement()
		 * @generated
		 */
		EClass DERIVED_ELEMENT = eINSTANCE.getDerivedElement();

		/**
		 * The meta object literal for the '<em><b>Base Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_ELEMENT__BASE_ELEMENT = eINSTANCE.getDerivedElement_Base_Element();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_ELEMENT__SOURCE = eINSTANCE.getDerivedElement_Source();

	}

} //TransformationPackage
