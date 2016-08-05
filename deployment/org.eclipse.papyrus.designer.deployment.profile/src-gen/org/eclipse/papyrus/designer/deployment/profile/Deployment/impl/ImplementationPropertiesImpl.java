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
import org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.TargetArchitecture;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implementation Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl#getSizeRam <em>Size Ram</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl#getSizeROM <em>Size ROM</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl#getSupportedOS <em>Supported OS</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.ImplementationPropertiesImpl#getArch <em>Arch</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImplementationPropertiesImpl extends MinimalEObjectImpl.Container implements ImplementationProperties {
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
	 * The default value of the '{@link #getSizeRam() <em>Size Ram</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeRam()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_RAM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSizeRam() <em>Size Ram</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeRam()
	 * @generated
	 * @ordered
	 */
	protected int sizeRam = SIZE_RAM_EDEFAULT;

	/**
	 * The default value of the '{@link #getSizeROM() <em>Size ROM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeROM()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_ROM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSizeROM() <em>Size ROM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeROM()
	 * @generated
	 * @ordered
	 */
	protected int sizeROM = SIZE_ROM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSupportedOS() <em>Supported OS</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupportedOS()
	 * @generated
	 * @ordered
	 */
	protected EList<OperatingSystem> supportedOS;

	/**
	 * The cached value of the '{@link #getArch() <em>Arch</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArch()
	 * @generated
	 * @ordered
	 */
	protected TargetArchitecture arch;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImplementationPropertiesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.IMPLEMENTATION_PROPERTIES;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.IMPLEMENTATION_PROPERTIES__BASE_CLASS, oldBase_Class, base_Class));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.IMPLEMENTATION_PROPERTIES__BASE_CLASS, oldBase_Class, base_Class));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSizeRam() {
		return sizeRam;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSizeRam(int newSizeRam) {
		int oldSizeRam = sizeRam;
		sizeRam = newSizeRam;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_RAM, oldSizeRam, sizeRam));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSizeROM() {
		return sizeROM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSizeROM(int newSizeROM) {
		int oldSizeROM = sizeROM;
		sizeROM = newSizeROM;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_ROM, oldSizeROM, sizeROM));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperatingSystem> getSupportedOS() {
		if (supportedOS == null) {
			supportedOS = new EObjectResolvingEList<OperatingSystem>(OperatingSystem.class, this, DeploymentPackage.IMPLEMENTATION_PROPERTIES__SUPPORTED_OS);
		}
		return supportedOS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetArchitecture getArch() {
		if (arch != null && arch.eIsProxy()) {
			InternalEObject oldArch = (InternalEObject)arch;
			arch = (TargetArchitecture)eResolveProxy(oldArch);
			if (arch != oldArch) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.IMPLEMENTATION_PROPERTIES__ARCH, oldArch, arch));
			}
		}
		return arch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TargetArchitecture basicGetArch() {
		return arch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArch(TargetArchitecture newArch) {
		TargetArchitecture oldArch = arch;
		arch = newArch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.IMPLEMENTATION_PROPERTIES__ARCH, oldArch, arch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__BASE_CLASS:
				if (resolve) return getBase_Class();
				return basicGetBase_Class();
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_RAM:
				return getSizeRam();
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_ROM:
				return getSizeROM();
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SUPPORTED_OS:
				return getSupportedOS();
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__ARCH:
				if (resolve) return getArch();
				return basicGetArch();
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
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)newValue);
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_RAM:
				setSizeRam((Integer)newValue);
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_ROM:
				setSizeROM((Integer)newValue);
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SUPPORTED_OS:
				getSupportedOS().clear();
				getSupportedOS().addAll((Collection<? extends OperatingSystem>)newValue);
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__ARCH:
				setArch((TargetArchitecture)newValue);
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
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__BASE_CLASS:
				setBase_Class((org.eclipse.uml2.uml.Class)null);
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_RAM:
				setSizeRam(SIZE_RAM_EDEFAULT);
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_ROM:
				setSizeROM(SIZE_ROM_EDEFAULT);
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SUPPORTED_OS:
				getSupportedOS().clear();
				return;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__ARCH:
				setArch((TargetArchitecture)null);
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
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__BASE_CLASS:
				return base_Class != null;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_RAM:
				return sizeRam != SIZE_RAM_EDEFAULT;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SIZE_ROM:
				return sizeROM != SIZE_ROM_EDEFAULT;
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__SUPPORTED_OS:
				return supportedOS != null && !supportedOS.isEmpty();
			case DeploymentPackage.IMPLEMENTATION_PROPERTIES__ARCH:
				return arch != null;
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
		result.append(" (sizeRam: ");
		result.append(sizeRam);
		result.append(", sizeROM: ");
		result.append(sizeROM);
		result.append(')');
		return result.toString();
	}

} //ImplementationPropertiesImpl
