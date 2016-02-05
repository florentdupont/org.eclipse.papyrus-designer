/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.designer.languages.java.profile.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.languages.java.profile.JavaClass;
import org.eclipse.papyrus.designer.languages.java.profile.JavaGen;
import org.eclipse.papyrus.designer.languages.java.profile.JavaMethod;
import org.eclipse.papyrus.designer.languages.java.profile.JavaNamedElement;
import org.eclipse.papyrus.designer.languages.java.profile.JavaPackage;
import org.eclipse.papyrus.designer.languages.java.profile.JavaPackage_;
import org.eclipse.papyrus.designer.languages.java.profile.JavaParameter;
import org.eclipse.papyrus.designer.languages.java.profile.JavaProject;
import org.eclipse.papyrus.designer.languages.java.profile.JavaProperty;
import org.eclipse.papyrus.designer.languages.java.profile.JavaSrcFolder;
import org.eclipse.papyrus.designer.languages.java.profile.PrimitiveType;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaPackage
 * @generated
 */
public class JavaAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected static JavaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public JavaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = JavaPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 *
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected JavaSwitch<Adapter> modelSwitch =
			new JavaSwitch<Adapter>() {
				@Override
				public Adapter caseJavaClass(JavaClass object) {
					return createJavaClassAdapter();
				}

				@Override
				public Adapter caseJavaGen(JavaGen object) {
					return createJavaGenAdapter();
				}

				@Override
				public Adapter caseJavaNamedElement(JavaNamedElement object) {
					return createJavaNamedElementAdapter();
				}

				@Override
				public Adapter caseJavaPackage_(JavaPackage_ object) {
					return createJavaPackage_Adapter();
				}

				@Override
				public Adapter caseJavaMethod(JavaMethod object) {
					return createJavaMethodAdapter();
				}

				@Override
				public Adapter caseJavaParameter(JavaParameter object) {
					return createJavaParameterAdapter();
				}

				@Override
				public Adapter caseJavaProperty(JavaProperty object) {
					return createJavaPropertyAdapter();
				}

				@Override
				public Adapter casePrimitiveType(PrimitiveType object) {
					return createPrimitiveTypeAdapter();
				}

				@Override
				public Adapter caseJavaProject(JavaProject object) {
					return createJavaProjectAdapter();
				}

				@Override
				public Adapter caseJavaSrcFolder(JavaSrcFolder object) {
					return createJavaSrcFolderAdapter();
				}

				@Override
				public Adapter defaultCase(EObject object) {
					return createEObjectAdapter();
				}
			};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaClass <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaClass
	 * @generated
	 */
	public Adapter createJavaClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaGen <em>Gen</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaGen
	 * @generated
	 */
	public Adapter createJavaGenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaNamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaNamedElement
	 * @generated
	 */
	public Adapter createJavaNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaPackage_ <em>Package </em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaPackage_
	 * @generated
	 */
	public Adapter createJavaPackage_Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaMethod
	 * @generated
	 */
	public Adapter createJavaMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaParameter
	 * @generated
	 */
	public Adapter createJavaParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaProperty
	 * @generated
	 */
	public Adapter createJavaPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaProject
	 * @generated
	 */
	public Adapter createJavaProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.papyrus.designer.languages.java.profile.JavaSrcFolder <em>Src Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.JavaSrcFolder
	 * @generated
	 */
	public Adapter createJavaSrcFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 *
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // JavaAdapterFactory
