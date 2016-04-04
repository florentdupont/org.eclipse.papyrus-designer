/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extern Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getBase_package <em>Base package</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getImportPaths <em>Import Paths</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getLibPaths <em>Lib Paths</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getLibs <em>Libs</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getImports <em>Imports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getExternLibrary()
 * @model
 * @generated
 */
public interface ExternLibrary extends EObject {
	/**
	 * Returns the value of the '<em><b>Base package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base package</em>' reference.
	 * @see #setBase_package(org.eclipse.uml2.uml.Package)
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getExternLibrary_Base_package()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Package getBase_package();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary#getBase_package <em>Base package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base package</em>' reference.
	 * @see #getBase_package()
	 * @generated
	 */
	void setBase_package(org.eclipse.uml2.uml.Package value);

	/**
	 * Returns the value of the '<em><b>Import Paths</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import Paths</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import Paths</em>' attribute list.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getExternLibrary_ImportPaths()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	EList<String> getImportPaths();

	/**
	 * Returns the value of the '<em><b>Lib Paths</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lib Paths</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lib Paths</em>' attribute list.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getExternLibrary_LibPaths()
	 * @model dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	EList<String> getLibPaths();

	/**
	 * Returns the value of the '<em><b>Libs</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Libs</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Libs</em>' attribute list.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getExternLibrary_Libs()
	 * @model dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	EList<String> getLibs();

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' attribute list.
	 * @see org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage#getExternLibrary_Imports()
	 * @model dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	EList<String> getImports();

} // ExternLibrary
