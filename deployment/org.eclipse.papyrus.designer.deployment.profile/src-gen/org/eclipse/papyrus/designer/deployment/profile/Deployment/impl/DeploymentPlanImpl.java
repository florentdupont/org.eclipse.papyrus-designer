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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl#getBase_Package <em>Base Package</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl#getProjectMappings <em>Project Mappings</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl#getChain <em>Chain</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.impl.DeploymentPlanImpl#getAdditionalTrafos <em>Additional Trafos</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeploymentPlanImpl extends MinimalEObjectImpl.Container implements DeploymentPlan {
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
	 * The cached value of the '{@link #getProjectMappings() <em>Project Mappings</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<String> projectMappings;

	/**
	 * The cached value of the '{@link #getChain() <em>Chain</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChain()
	 * @generated
	 * @ordered
	 */
	protected M2MTrafoChain chain;

	/**
	 * The cached value of the '{@link #getAdditionalTrafos() <em>Additional Trafos</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalTrafos()
	 * @generated
	 * @ordered
	 */
	protected EList<M2MTrafo> additionalTrafos;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentPlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DeploymentPackage.Literals.DEPLOYMENT_PLAN;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.DEPLOYMENT_PLAN__BASE_PACKAGE, oldBase_Package, base_Package));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.DEPLOYMENT_PLAN__BASE_PACKAGE, oldBase_Package, base_Package));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getProjectMappings() {
		if (projectMappings == null) {
			projectMappings = new EDataTypeUniqueEList<String>(String.class, this, DeploymentPackage.DEPLOYMENT_PLAN__PROJECT_MAPPINGS);
		}
		return projectMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public M2MTrafoChain getChain() {
		if (chain != null && chain.eIsProxy()) {
			InternalEObject oldChain = (InternalEObject)chain;
			chain = (M2MTrafoChain)eResolveProxy(oldChain);
			if (chain != oldChain) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DeploymentPackage.DEPLOYMENT_PLAN__CHAIN, oldChain, chain));
			}
		}
		return chain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public M2MTrafoChain basicGetChain() {
		return chain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChain(M2MTrafoChain newChain) {
		M2MTrafoChain oldChain = chain;
		chain = newChain;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DeploymentPackage.DEPLOYMENT_PLAN__CHAIN, oldChain, chain));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<M2MTrafo> getAdditionalTrafos() {
		if (additionalTrafos == null) {
			additionalTrafos = new EObjectResolvingEList<M2MTrafo>(M2MTrafo.class, this, DeploymentPackage.DEPLOYMENT_PLAN__ADDITIONAL_TRAFOS);
		}
		return additionalTrafos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DeploymentPackage.DEPLOYMENT_PLAN__BASE_PACKAGE:
				if (resolve) return getBase_Package();
				return basicGetBase_Package();
			case DeploymentPackage.DEPLOYMENT_PLAN__PROJECT_MAPPINGS:
				return getProjectMappings();
			case DeploymentPackage.DEPLOYMENT_PLAN__CHAIN:
				if (resolve) return getChain();
				return basicGetChain();
			case DeploymentPackage.DEPLOYMENT_PLAN__ADDITIONAL_TRAFOS:
				return getAdditionalTrafos();
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
			case DeploymentPackage.DEPLOYMENT_PLAN__BASE_PACKAGE:
				setBase_Package((org.eclipse.uml2.uml.Package)newValue);
				return;
			case DeploymentPackage.DEPLOYMENT_PLAN__PROJECT_MAPPINGS:
				getProjectMappings().clear();
				getProjectMappings().addAll((Collection<? extends String>)newValue);
				return;
			case DeploymentPackage.DEPLOYMENT_PLAN__CHAIN:
				setChain((M2MTrafoChain)newValue);
				return;
			case DeploymentPackage.DEPLOYMENT_PLAN__ADDITIONAL_TRAFOS:
				getAdditionalTrafos().clear();
				getAdditionalTrafos().addAll((Collection<? extends M2MTrafo>)newValue);
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
			case DeploymentPackage.DEPLOYMENT_PLAN__BASE_PACKAGE:
				setBase_Package((org.eclipse.uml2.uml.Package)null);
				return;
			case DeploymentPackage.DEPLOYMENT_PLAN__PROJECT_MAPPINGS:
				getProjectMappings().clear();
				return;
			case DeploymentPackage.DEPLOYMENT_PLAN__CHAIN:
				setChain((M2MTrafoChain)null);
				return;
			case DeploymentPackage.DEPLOYMENT_PLAN__ADDITIONAL_TRAFOS:
				getAdditionalTrafos().clear();
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
			case DeploymentPackage.DEPLOYMENT_PLAN__BASE_PACKAGE:
				return base_Package != null;
			case DeploymentPackage.DEPLOYMENT_PLAN__PROJECT_MAPPINGS:
				return projectMappings != null && !projectMappings.isEmpty();
			case DeploymentPackage.DEPLOYMENT_PLAN__CHAIN:
				return chain != null;
			case DeploymentPackage.DEPLOYMENT_PLAN__ADDITIONAL_TRAFOS:
				return additionalTrafos != null && !additionalTrafos.isEmpty();
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
		result.append(" (projectMappings: ");
		result.append(projectMappings);
		result.append(')');
		return result.toString();
	}

} //DeploymentPlanImpl
