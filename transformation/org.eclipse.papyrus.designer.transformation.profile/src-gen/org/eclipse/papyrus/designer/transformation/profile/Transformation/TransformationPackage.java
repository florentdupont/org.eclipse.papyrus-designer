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
	int APPLY_TRANSFORMATION = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoImpl <em>M2M Trafo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafo()
	 * @generated
	 */
	int M2M_TRAFO = 0;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO__BASE_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Before</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO__BEFORE = 1;

	/**
	 * The feature id for the '<em><b>After</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO__AFTER = 2;

	/**
	 * The number of structural features of the '<em>M2M Trafo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>M2M Trafo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int M2M_TRAFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoChainImpl <em>M2M Trafo Chain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.M2MTrafoChainImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getM2MTrafoChain()
	 * @generated
	 */
	int M2M_TRAFO_CHAIN = 1;

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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.DerivedElementImpl <em>Derived Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.DerivedElementImpl
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationPackageImpl#getDerivedElement()
	 * @generated
	 */
	int DERIVED_ELEMENT = 2;

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
	 * The feature id for the '<em><b>Trafo</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLY_TRANSFORMATION__TRAFO = 0;

	/**
	 * The feature id for the '<em><b>Base Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLY_TRANSFORMATION__BASE_ELEMENT = 1;

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
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation <em>Apply Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Apply Transformation</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation
	 * @generated
	 */
	EClass getApplyTransformation();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getTrafo <em>Trafo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Trafo</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getTrafo()
	 * @see #getApplyTransformation()
	 * @generated
	 */
	EReference getApplyTransformation_Trafo();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getBase_Element <em>Base Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Element</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation#getBase_Element()
	 * @see #getApplyTransformation()
	 * @generated
	 */
	EReference getApplyTransformation_Base_Element();

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
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBefore <em>Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Before</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getBefore()
	 * @see #getM2MTrafo()
	 * @generated
	 */
	EReference getM2MTrafo_Before();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getAfter <em>After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>After</em>'.
	 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo#getAfter()
	 * @see #getM2MTrafo()
	 * @generated
	 */
	EReference getM2MTrafo_After();

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
		 * The meta object literal for the '<em><b>Trafo</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLY_TRANSFORMATION__TRAFO = eINSTANCE.getApplyTransformation_Trafo();

		/**
		 * The meta object literal for the '<em><b>Base Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLY_TRANSFORMATION__BASE_ELEMENT = eINSTANCE.getApplyTransformation_Base_Element();

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
		 * The meta object literal for the '<em><b>Before</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference M2M_TRAFO__BEFORE = eINSTANCE.getM2MTrafo_Before();

		/**
		 * The meta object literal for the '<em><b>After</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference M2M_TRAFO__AFTER = eINSTANCE.getM2MTrafo_After();

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
