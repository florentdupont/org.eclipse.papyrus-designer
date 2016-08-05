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
import org.eclipse.papyrus.designer.deployment.profile.Deployment.UseInstanceConfigurator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Instance Configurator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.UseInstanceConfiguratorImpl#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.UseInstanceConfiguratorImpl#getConfigurator <em>Configurator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UseInstanceConfiguratorImpl extends MinimalEObjectImpl.Container implements UseInstanceConfigurator {
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
	 * The cached value of the '{@link #getConfigurator() <em>Configurator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurator()
	 * @generated
	 * @ordered
	 */
	protected InstanceConfigurator configurator;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseInstanceConfiguratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.USE_INSTANCE_CONFIGURATOR;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.USE_INSTANCE_CONFIGURATOR__BASE_CLASS, oldBase_Class, base_Class));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.USE_INSTANCE_CONFIGURATOR__BASE_CLASS, oldBase_Class, base_Class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceConfigurator getConfigurator() {
		if (configurator != null && configurator.eIsProxy()) {
			InternalEObject oldConfigurator = (InternalEObject)configurator;
			configurator = (InstanceConfigurator)eResolveProxy(oldConfigurator);
			if (configurator != oldConfigurator) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.USE_INSTANCE_CONFIGURATOR__CONFIGURATOR, oldConfigurator, configurator));
			}
		}
		return configurator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceConfigurator basicGetConfigurator() {
		return configurator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfigurator(InstanceConfigurator newConfigurator) {
		InstanceConfigurator oldConfigurator = configurator;
		configurator = newConfigurator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.USE_INSTANCE_CONFIGURATOR__CONFIGURATOR, oldConfigurator, configurator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__BASE_CLASS:
				if (resolve) return getBase_Class();
				return basicGetBase_Class();
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__CONFIGURATOR:
				if (resolve) return getConfigurator();
				return basicGetConfigurator();
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
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)newValue);
				return;
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__CONFIGURATOR:
				setConfigurator((InstanceConfigurator)newValue);
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
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)null);
				return;
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__CONFIGURATOR:
				setConfigurator((InstanceConfigurator)null);
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
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__BASE_CLASS:
				return base_Class != null;
			case DeploymentPackage.USE_INSTANCE_CONFIGURATOR__CONFIGURATOR:
				return configurator != null;
		}
		return super.eIsSet(featureID);
	}

} //UseInstanceConfiguratorImpl
