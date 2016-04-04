/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extern Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl#getBase_package <em>Base package</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl#getImportPaths <em>Import Paths</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl#getLibPaths <em>Lib Paths</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl#getLibs <em>Libs</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.ExternLibraryImpl#getImports <em>Imports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExternLibraryImpl extends MinimalEObjectImpl.Container implements ExternLibrary {
	/**
	 * The cached value of the '{@link #getBase_package() <em>Base package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_package()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.Package base_package;

	/**
	 * The cached value of the '{@link #getImportPaths() <em>Import Paths</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<String> importPaths;

	/**
	 * The cached value of the '{@link #getLibPaths() <em>Lib Paths</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<String> libPaths;

	/**
	 * The cached value of the '{@link #getLibs() <em>Libs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> libs;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<String> imports;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternLibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PapyrusJavaPackage.Literals.EXTERN_LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package getBase_package() {
		if (base_package != null && base_package.eIsProxy()) {
			InternalEObject oldBase_package = (InternalEObject)base_package;
			base_package = (org.eclipse.uml2.uml.Package)eResolveProxy(oldBase_package);
			if (base_package != oldBase_package) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PapyrusJavaPackage.EXTERN_LIBRARY__BASE_PACKAGE, oldBase_package, base_package));
			}
		}
		return base_package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package basicGetBase_package() {
		return base_package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_package(org.eclipse.uml2.uml.Package newBase_package) {
		org.eclipse.uml2.uml.Package oldBase_package = base_package;
		base_package = newBase_package;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusJavaPackage.EXTERN_LIBRARY__BASE_PACKAGE, oldBase_package, base_package));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getImportPaths() {
		if (importPaths == null) {
			importPaths = new EDataTypeEList<String>(String.class, this, PapyrusJavaPackage.EXTERN_LIBRARY__IMPORT_PATHS);
		}
		return importPaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getLibPaths() {
		if (libPaths == null) {
			libPaths = new EDataTypeUniqueEList<String>(String.class, this, PapyrusJavaPackage.EXTERN_LIBRARY__LIB_PATHS);
		}
		return libPaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getLibs() {
		if (libs == null) {
			libs = new EDataTypeUniqueEList<String>(String.class, this, PapyrusJavaPackage.EXTERN_LIBRARY__LIBS);
		}
		return libs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getImports() {
		if (imports == null) {
			imports = new EDataTypeUniqueEList<String>(String.class, this, PapyrusJavaPackage.EXTERN_LIBRARY__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PapyrusJavaPackage.EXTERN_LIBRARY__BASE_PACKAGE:
				if (resolve) return getBase_package();
				return basicGetBase_package();
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORT_PATHS:
				return getImportPaths();
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIB_PATHS:
				return getLibPaths();
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIBS:
				return getLibs();
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORTS:
				return getImports();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PapyrusJavaPackage.EXTERN_LIBRARY__BASE_PACKAGE:
				setBase_package((org.eclipse.uml2.uml.Package)newValue);
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORT_PATHS:
				getImportPaths().clear();
				getImportPaths().addAll((Collection<? extends String>)newValue);
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIB_PATHS:
				getLibPaths().clear();
				getLibPaths().addAll((Collection<? extends String>)newValue);
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIBS:
				getLibs().clear();
				getLibs().addAll((Collection<? extends String>)newValue);
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PapyrusJavaPackage.EXTERN_LIBRARY__BASE_PACKAGE:
				setBase_package((org.eclipse.uml2.uml.Package)null);
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORT_PATHS:
				getImportPaths().clear();
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIB_PATHS:
				getLibPaths().clear();
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIBS:
				getLibs().clear();
				return;
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORTS:
				getImports().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PapyrusJavaPackage.EXTERN_LIBRARY__BASE_PACKAGE:
				return base_package != null;
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORT_PATHS:
				return importPaths != null && !importPaths.isEmpty();
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIB_PATHS:
				return libPaths != null && !libPaths.isEmpty();
			case PapyrusJavaPackage.EXTERN_LIBRARY__LIBS:
				return libs != null && !libs.isEmpty();
			case PapyrusJavaPackage.EXTERN_LIBRARY__IMPORTS:
				return imports != null && !imports.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (importPaths: ");
		result.append(importPaths);
		result.append(", libPaths: ");
		result.append(libPaths);
		result.append(", libs: ");
		result.append(libs);
		result.append(", imports: ");
		result.append(imports);
		result.append(')');
		return result.toString();
	}

} //ExternLibraryImpl
