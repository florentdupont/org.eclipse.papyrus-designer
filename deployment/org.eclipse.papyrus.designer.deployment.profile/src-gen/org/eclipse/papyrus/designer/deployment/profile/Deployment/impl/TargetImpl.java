/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Target;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.TargetArchitecture;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl#getUsedOS <em>Used OS</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl#getAvailRAM <em>Avail RAM</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl#getAvailROM <em>Avail ROM</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.TargetImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TargetImpl extends MinimalEObjectImpl.Container implements Target {
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
	 * The cached value of the '{@link #getUsedOS() <em>Used OS</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedOS()
	 * @generated
	 * @ordered
	 */
	protected OperatingSystem usedOS;

	/**
	 * The default value of the '{@link #getAvailRAM() <em>Avail RAM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailRAM()
	 * @generated
	 * @ordered
	 */
	protected static final int AVAIL_RAM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAvailRAM() <em>Avail RAM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailRAM()
	 * @generated
	 * @ordered
	 */
	protected int availRAM = AVAIL_RAM_EDEFAULT;

	/**
	 * The default value of the '{@link #getAvailROM() <em>Avail ROM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailROM()
	 * @generated
	 * @ordered
	 */
	protected static final int AVAIL_ROM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAvailROM() <em>Avail ROM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailROM()
	 * @generated
	 * @ordered
	 */
	protected int availROM = AVAIL_ROM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected TargetArchitecture target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TargetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.TARGET;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.TARGET__BASE_CLASS, oldBase_Class, base_Class));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.TARGET__BASE_CLASS, oldBase_Class, base_Class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatingSystem getUsedOS() {
		if (usedOS != null && usedOS.eIsProxy()) {
			InternalEObject oldUsedOS = (InternalEObject)usedOS;
			usedOS = (OperatingSystem)eResolveProxy(oldUsedOS);
			if (usedOS != oldUsedOS) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.TARGET__USED_OS, oldUsedOS, usedOS));
			}
		}
		return usedOS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatingSystem basicGetUsedOS() {
		return usedOS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsedOS(OperatingSystem newUsedOS) {
		OperatingSystem oldUsedOS = usedOS;
		usedOS = newUsedOS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.TARGET__USED_OS, oldUsedOS, usedOS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAvailRAM() {
		return availRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailRAM(int newAvailRAM) {
		int oldAvailRAM = availRAM;
		availRAM = newAvailRAM;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.TARGET__AVAIL_RAM, oldAvailRAM, availRAM));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAvailROM() {
		return availROM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailROM(int newAvailROM) {
		int oldAvailROM = availROM;
		availROM = newAvailROM;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.TARGET__AVAIL_ROM, oldAvailROM, availROM));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetArchitecture getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (TargetArchitecture)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.TARGET__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetArchitecture basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(TargetArchitecture newTarget) {
		TargetArchitecture oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.TARGET__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.TARGET__BASE_CLASS:
				if (resolve) return getBase_Class();
				return basicGetBase_Class();
			case DeploymentPackage.TARGET__USED_OS:
				if (resolve) return getUsedOS();
				return basicGetUsedOS();
			case DeploymentPackage.TARGET__AVAIL_RAM:
				return getAvailRAM();
			case DeploymentPackage.TARGET__AVAIL_ROM:
				return getAvailROM();
			case DeploymentPackage.TARGET__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
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
			case DeploymentPackage.TARGET__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)newValue);
				return;
			case DeploymentPackage.TARGET__USED_OS:
				setUsedOS((OperatingSystem)newValue);
				return;
			case DeploymentPackage.TARGET__AVAIL_RAM:
				setAvailRAM((Integer)newValue);
				return;
			case DeploymentPackage.TARGET__AVAIL_ROM:
				setAvailROM((Integer)newValue);
				return;
			case DeploymentPackage.TARGET__TARGET:
				setTarget((TargetArchitecture)newValue);
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
			case DeploymentPackage.TARGET__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)null);
				return;
			case DeploymentPackage.TARGET__USED_OS:
				setUsedOS((OperatingSystem)null);
				return;
			case DeploymentPackage.TARGET__AVAIL_RAM:
				setAvailRAM(AVAIL_RAM_EDEFAULT);
				return;
			case DeploymentPackage.TARGET__AVAIL_ROM:
				setAvailROM(AVAIL_ROM_EDEFAULT);
				return;
			case DeploymentPackage.TARGET__TARGET:
				setTarget((TargetArchitecture)null);
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
			case DeploymentPackage.TARGET__BASE_CLASS:
				return base_Class != null;
			case DeploymentPackage.TARGET__USED_OS:
				return usedOS != null;
			case DeploymentPackage.TARGET__AVAIL_RAM:
				return availRAM != AVAIL_RAM_EDEFAULT;
			case DeploymentPackage.TARGET__AVAIL_ROM:
				return availROM != AVAIL_ROM_EDEFAULT;
			case DeploymentPackage.TARGET__TARGET:
				return target != null;
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
		result.append(" (availRAM: ");
		result.append(availRAM);
		result.append(", availROM: ");
		result.append(availROM);
		result.append(')');
		return result.toString();
	}

} //TargetImpl
