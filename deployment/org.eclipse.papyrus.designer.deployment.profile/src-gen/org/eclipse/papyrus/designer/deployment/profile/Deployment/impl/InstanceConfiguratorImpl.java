/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.InstanceConfigurator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instance Configurator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InstanceConfiguratorImpl#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InstanceConfiguratorImpl#isOnNodeModel <em>On Node Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceConfiguratorImpl extends MinimalEObjectImpl.Container implements InstanceConfigurator {
	/**
	 * The cached value of the '{@link #getBase_Class() <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_Class()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.Class base_Class;

	/**
	 * The default value of the '{@link #isOnNodeModel() <em>On Node Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnNodeModel()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ON_NODE_MODEL_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isOnNodeModel() <em>On Node Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnNodeModel()
	 * @generated
	 * @ordered
	 */
	protected boolean onNodeModel = ON_NODE_MODEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceConfiguratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.INSTANCE_CONFIGURATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class getBase_Class() {
		if (base_Class != null && base_Class.eIsProxy()) {
			InternalEObject oldBase_Class = (InternalEObject)base_Class;
			base_Class = (org.eclipse.uml2.uml.Class)eResolveProxy(oldBase_Class);
			if (base_Class != oldBase_Class) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.INSTANCE_CONFIGURATOR__BASE_CLASS, oldBase_Class, base_Class));
			}
		}
		return base_Class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class basicGetBase_Class() {
		return base_Class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_Class(org.eclipse.uml2.uml.Class newBase_Class) {
		org.eclipse.uml2.uml.Class oldBase_Class = base_Class;
		base_Class = newBase_Class;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.INSTANCE_CONFIGURATOR__BASE_CLASS, oldBase_Class, base_Class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOnNodeModel() {
		return onNodeModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnNodeModel(boolean newOnNodeModel) {
		boolean oldOnNodeModel = onNodeModel;
		onNodeModel = newOnNodeModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.INSTANCE_CONFIGURATOR__ON_NODE_MODEL, oldOnNodeModel, onNodeModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.INSTANCE_CONFIGURATOR__BASE_CLASS:
				if (resolve) return getBase_Class();
				return basicGetBase_Class();
			case DeploymentPackage.INSTANCE_CONFIGURATOR__ON_NODE_MODEL:
				return isOnNodeModel();
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
			case DeploymentPackage.INSTANCE_CONFIGURATOR__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)newValue);
				return;
			case DeploymentPackage.INSTANCE_CONFIGURATOR__ON_NODE_MODEL:
				setOnNodeModel((Boolean)newValue);
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
			case DeploymentPackage.INSTANCE_CONFIGURATOR__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)null);
				return;
			case DeploymentPackage.INSTANCE_CONFIGURATOR__ON_NODE_MODEL:
				setOnNodeModel(ON_NODE_MODEL_EDEFAULT);
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
			case DeploymentPackage.INSTANCE_CONFIGURATOR__BASE_CLASS:
				return base_Class != null;
			case DeploymentPackage.INSTANCE_CONFIGURATOR__ON_NODE_MODEL:
				return onNodeModel != ON_NODE_MODEL_EDEFAULT;
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
		result.append(" (onNodeModel: ");
		result.append(onNodeModel);
		result.append(')');
		return result.toString();
	}

} //InstanceConfiguratorImpl
