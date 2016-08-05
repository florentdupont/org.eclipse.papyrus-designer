/**
 */
package org.eclipse.papyrus.designer.transformation.profile.Transformation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.transformation.profile.Transformation.TransformationPackage
 * @generated
 */
public interface TransformationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TransformationFactory eINSTANCE = org.eclipse.papyrus.designer.transformation.profile.Transformation.impl.TransformationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Apply Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Apply Transformation</em>'.
	 * @generated
	 */
	ApplyTransformation createApplyTransformation();

	/**
	 * Returns a new object of class '<em>M2M Trafo</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>M2M Trafo</em>'.
	 * @generated
	 */
	M2MTrafo createM2MTrafo();

	/**
	 * Returns a new object of class '<em>M2M Trafo Kind</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>M2M Trafo Kind</em>'.
	 * @generated
	 */
	M2MTrafoKind createM2MTrafoKind();

	/**
	 * Returns a new object of class '<em>M2M Trafo Chain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>M2M Trafo Chain</em>'.
	 * @generated
	 */
	M2MTrafoChain createM2MTrafoChain();

	/**
	 * Returns a new object of class '<em>Derived Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Derived Element</em>'.
	 * @generated
	 */
	DerivedElement createDerivedElement();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TransformationPackage getTransformationPackage();

} //TransformationFactory
