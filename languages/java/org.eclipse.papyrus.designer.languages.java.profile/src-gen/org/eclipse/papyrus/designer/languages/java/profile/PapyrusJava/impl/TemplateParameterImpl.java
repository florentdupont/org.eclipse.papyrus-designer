/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.TemplateParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl.TemplateParameterImpl#getBase_templateparameter <em>Base templateparameter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateParameterImpl extends MinimalEObjectImpl.Container implements TemplateParameter {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBase_templateparameter() <em>Base templateparameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_templateparameter()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.TemplateParameter base_templateparameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PapyrusJavaPackage.Literals.TEMPLATE_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusJavaPackage.TEMPLATE_PARAMETER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.TemplateParameter getBase_templateparameter() {
		if (base_templateparameter != null && base_templateparameter.eIsProxy()) {
			InternalEObject oldBase_templateparameter = (InternalEObject)base_templateparameter;
			base_templateparameter = (org.eclipse.uml2.uml.TemplateParameter)eResolveProxy(oldBase_templateparameter);
			if (base_templateparameter != oldBase_templateparameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PapyrusJavaPackage.TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER, oldBase_templateparameter, base_templateparameter));
			}
		}
		return base_templateparameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.TemplateParameter basicGetBase_templateparameter() {
		return base_templateparameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_templateparameter(org.eclipse.uml2.uml.TemplateParameter newBase_templateparameter) {
		org.eclipse.uml2.uml.TemplateParameter oldBase_templateparameter = base_templateparameter;
		base_templateparameter = newBase_templateparameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PapyrusJavaPackage.TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER, oldBase_templateparameter, base_templateparameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__NAME:
				return getName();
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER:
				if (resolve) return getBase_templateparameter();
				return basicGetBase_templateparameter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__NAME:
				setName((String)newValue);
				return;
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER:
				setBase_templateparameter((org.eclipse.uml2.uml.TemplateParameter)newValue);
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
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER:
				setBase_templateparameter((org.eclipse.uml2.uml.TemplateParameter)null);
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
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PapyrusJavaPackage.TEMPLATE_PARAMETER__BASE_TEMPLATEPARAMETER:
				return base_templateparameter != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TemplateParameterImpl
