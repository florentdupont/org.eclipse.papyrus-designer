/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Init Precedence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InitPrecedenceImpl#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InitPrecedenceImpl#getInvokeBefore <em>Invoke Before</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.InitPrecedenceImpl#getInvokeAfter <em>Invoke After</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InitPrecedenceImpl extends MinimalEObjectImpl.Container implements InitPrecedence {
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
	 * The cached value of the '{@link #getInvokeBefore() <em>Invoke Before</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvokeBefore()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.uml2.uml.Class> invokeBefore;
	/**
	 * The cached value of the '{@link #getInvokeAfter() <em>Invoke After</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvokeAfter()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.uml2.uml.Class> invokeAfter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InitPrecedenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.INIT_PRECEDENCE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.INIT_PRECEDENCE__BASE_CLASS, oldBase_Class, base_Class));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.INIT_PRECEDENCE__BASE_CLASS, oldBase_Class, base_Class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<org.eclipse.uml2.uml.Class> getInvokeBefore() {
		if (invokeBefore == null) {
			invokeBefore = new EObjectResolvingEList<org.eclipse.uml2.uml.Class>(org.eclipse.uml2.uml.Class.class, this, DeploymentPackage.INIT_PRECEDENCE__INVOKE_BEFORE);
		}
		return invokeBefore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class getInvokeBefore(String name) {
		return getInvokeBefore(name, false, null);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class getInvokeBefore(String name, boolean ignoreCase, EClass eClass) {
		invokeBeforeLoop: for (org.eclipse.uml2.uml.Class invokeBefore : getInvokeBefore()) {
			if (eClass != null && !eClass.isInstance(invokeBefore))
				continue invokeBeforeLoop;
			if (name != null && !(ignoreCase ? name.equalsIgnoreCase(invokeBefore.getName()) : name.equals(invokeBefore.getName())))
				continue invokeBeforeLoop;
			return invokeBefore;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<org.eclipse.uml2.uml.Class> getInvokeAfter() {
		if (invokeAfter == null) {
			invokeAfter = new EObjectResolvingEList<org.eclipse.uml2.uml.Class>(org.eclipse.uml2.uml.Class.class, this, DeploymentPackage.INIT_PRECEDENCE__INVOKE_AFTER);
		}
		return invokeAfter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class getInvokeAfter(String name) {
		return getInvokeAfter(name, false, null);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class getInvokeAfter(String name, boolean ignoreCase, EClass eClass) {
		invokeAfterLoop: for (org.eclipse.uml2.uml.Class invokeAfter : getInvokeAfter()) {
			if (eClass != null && !eClass.isInstance(invokeAfter))
				continue invokeAfterLoop;
			if (name != null && !(ignoreCase ? name.equalsIgnoreCase(invokeAfter.getName()) : name.equals(invokeAfter.getName())))
				continue invokeAfterLoop;
			return invokeAfter;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.INIT_PRECEDENCE__BASE_CLASS:
				if (resolve) return getBase_Class();
				return basicGetBase_Class();
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_BEFORE:
				return getInvokeBefore();
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_AFTER:
				return getInvokeAfter();
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
			case DeploymentPackage.INIT_PRECEDENCE__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)newValue);
				return;
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_BEFORE:
				getInvokeBefore().clear();
				getInvokeBefore().addAll((Collection<? extends org.eclipse.uml2.uml.Class>)newValue);
				return;
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_AFTER:
				getInvokeAfter().clear();
				getInvokeAfter().addAll((Collection<? extends org.eclipse.uml2.uml.Class>)newValue);
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
			case DeploymentPackage.INIT_PRECEDENCE__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)null);
				return;
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_BEFORE:
				getInvokeBefore().clear();
				return;
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_AFTER:
				getInvokeAfter().clear();
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
			case DeploymentPackage.INIT_PRECEDENCE__BASE_CLASS:
				return base_Class != null;
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_BEFORE:
				return invokeBefore != null && !invokeBefore.isEmpty();
			case DeploymentPackage.INIT_PRECEDENCE__INVOKE_AFTER:
				return invokeAfter != null && !invokeAfter.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //InitPrecedenceImpl
