/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage
 * @generated
 */
public interface PapyrusJavaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PapyrusJavaFactory eINSTANCE = org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.PapyrusJavaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array</em>'.
	 * @generated
	 */
	Array createArray();

	/**
	 * Returns a new object of class '<em>External</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External</em>'.
	 * @generated
	 */
	External createExternal();

	/**
	 * Returns a new object of class '<em>Extern Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extern Library</em>'.
	 * @generated
	 */
	ExternLibrary createExternLibrary();

	/**
	 * Returns a new object of class '<em>Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Import</em>'.
	 * @generated
	 */
	Import createImport();

	/**
	 * Returns a new object of class '<em>No Code Gen</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>No Code Gen</em>'.
	 * @generated
	 */
	NoCodeGen createNoCodeGen();

	/**
	 * Returns a new object of class '<em>Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template</em>'.
	 * @generated
	 */
	Template createTemplate();

	/**
	 * Returns a new object of class '<em>Template Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template Binding</em>'.
	 * @generated
	 */
	TemplateBinding createTemplateBinding();

	/**
	 * Returns a new object of class '<em>Template Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template Parameter</em>'.
	 * @generated
	 */
	TemplateParameter createTemplateParameter();

	/**
	 * Returns a new object of class '<em>Manual Generation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Manual Generation</em>'.
	 * @generated
	 */
	ManualGeneration createManualGeneration();

	/**
	 * Returns a new object of class '<em>Variadic</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variadic</em>'.
	 * @generated
	 */
	Variadic createVariadic();

	/**
	 * Returns a new object of class '<em>Volatile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Volatile</em>'.
	 * @generated
	 */
	Volatile createVolatile();

	/**
	 * Returns a new object of class '<em>Transient</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transient</em>'.
	 * @generated
	 */
	Transient createTransient();

	/**
	 * Returns a new object of class '<em>Synchronized</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Synchronized</em>'.
	 * @generated
	 */
	Synchronized createSynchronized();

	/**
	 * Returns a new object of class '<em>Strictfp</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Strictfp</em>'.
	 * @generated
	 */
	Strictfp createStrictfp();

	/**
	 * Returns a new object of class '<em>Native</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Native</em>'.
	 * @generated
	 */
	Native createNative();

	/**
	 * Returns a new object of class '<em>Default</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Default</em>'.
	 * @generated
	 */
	Default createDefault();

	/**
	 * Returns a new object of class '<em>Static Classifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Static Classifier</em>'.
	 * @generated
	 */
	StaticClassifier createStaticClassifier();

	/**
	 * Returns a new object of class '<em>Final</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Final</em>'.
	 * @generated
	 */
	Final createFinal();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PapyrusJavaPackage getPapyrusJavaPackage();

} //PapyrusJavaFactory
