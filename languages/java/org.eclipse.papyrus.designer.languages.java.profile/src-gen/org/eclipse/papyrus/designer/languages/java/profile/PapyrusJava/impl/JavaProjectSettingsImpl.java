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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.JavaProjectSettings;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Project Settings</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.JavaProjectSettingsImpl#getProjectNatures <em>Project Natures</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.JavaProjectSettingsImpl#getClassPaths <em>Class Paths</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.JavaProjectSettingsImpl#getOutputLocation <em>Output Location</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.JavaProjectSettingsImpl#getBase_Package <em>Base Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JavaProjectSettingsImpl extends MinimalEObjectImpl.Container implements JavaProjectSettings {
	/**
	 * The cached value of the '{@link #getProjectNatures() <em>Project Natures</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectNatures()
	 * @generated
	 * @ordered
	 */
	protected EList<String> projectNatures;

	/**
	 * The cached value of the '{@link #getClassPaths() <em>Class Paths</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<String> classPaths;

	/**
	 * The default value of the '{@link #getOutputLocation() <em>Output Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPUT_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutputLocation() <em>Output Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputLocation()
	 * @generated
	 * @ordered
	 */
	protected String outputLocation = OUTPUT_LOCATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBase_Package() <em>Base Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_Package()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.Package base_Package;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JavaProjectSettingsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PapyrusJavaPackage.Literals.JAVA_PROJECT_SETTINGS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getProjectNatures() {
		if (projectNatures == null) {
			projectNatures = new EDataTypeUniqueEList<String>(String.class, this, PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__PROJECT_NATURES);
		}
		return projectNatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getClassPaths() {
		if (classPaths == null) {
			classPaths = new EDataTypeUniqueEList<String>(String.class, this, PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__CLASS_PATHS);
		}
		return classPaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOutputLocation() {
		return outputLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputLocation(String newOutputLocation) {
		String oldOutputLocation = outputLocation;
		outputLocation = newOutputLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__OUTPUT_LOCATION, oldOutputLocation, outputLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package getBase_Package() {
		if (base_Package != null && base_Package.eIsProxy()) {
			InternalEObject oldBase_Package = (InternalEObject)base_Package;
			base_Package = (org.eclipse.uml2.uml.Package)eResolveProxy(oldBase_Package);
			if (base_Package != oldBase_Package) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__BASE_PACKAGE, oldBase_Package, base_Package));
			}
		}
		return base_Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package basicGetBase_Package() {
		return base_Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_Package(org.eclipse.uml2.uml.Package newBase_Package) {
		org.eclipse.uml2.uml.Package oldBase_Package = base_Package;
		base_Package = newBase_Package;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__BASE_PACKAGE, oldBase_Package, base_Package));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__PROJECT_NATURES:
				return getProjectNatures();
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__CLASS_PATHS:
				return getClassPaths();
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__OUTPUT_LOCATION:
				return getOutputLocation();
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__BASE_PACKAGE:
				if (resolve) return getBase_Package();
				return basicGetBase_Package();
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
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__PROJECT_NATURES:
				getProjectNatures().clear();
				getProjectNatures().addAll((Collection<? extends String>)newValue);
				return;
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__CLASS_PATHS:
				getClassPaths().clear();
				getClassPaths().addAll((Collection<? extends String>)newValue);
				return;
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__OUTPUT_LOCATION:
				setOutputLocation((String)newValue);
				return;
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__BASE_PACKAGE:
				setBase_Package((org.eclipse.uml2.uml.Package)newValue);
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
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__PROJECT_NATURES:
				getProjectNatures().clear();
				return;
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__CLASS_PATHS:
				getClassPaths().clear();
				return;
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__OUTPUT_LOCATION:
				setOutputLocation(OUTPUT_LOCATION_EDEFAULT);
				return;
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__BASE_PACKAGE:
				setBase_Package((org.eclipse.uml2.uml.Package)null);
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
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__PROJECT_NATURES:
				return projectNatures != null && !projectNatures.isEmpty();
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__CLASS_PATHS:
				return classPaths != null && !classPaths.isEmpty();
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__OUTPUT_LOCATION:
				return OUTPUT_LOCATION_EDEFAULT == null ? outputLocation != null : !OUTPUT_LOCATION_EDEFAULT.equals(outputLocation);
			case PapyrusJavaPackage.JAVA_PROJECT_SETTINGS__BASE_PACKAGE:
				return base_Package != null;
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
		result.append(" (projectNatures: ");
		result.append(projectNatures);
		result.append(", classPaths: ");
		result.append(classPaths);
		result.append(", outputLocation: ");
		result.append(outputLocation);
		result.append(')');
		return result.toString();
	}

} //JavaProjectSettingsImpl
