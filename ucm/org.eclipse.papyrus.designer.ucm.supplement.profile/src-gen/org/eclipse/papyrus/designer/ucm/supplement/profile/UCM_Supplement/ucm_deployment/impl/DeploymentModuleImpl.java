/**
 * Copyright (c) 2017 CEA LIST and Thales
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_technicalpolicies.ProgrammingLanguages;

import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.DeploymentModule;
import org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.Ucm_deploymentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployment Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.DeploymentModuleImpl#getProgrammingLanguages <em>Programming Languages</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.ucm.supplement.profile.UCM_Supplement.ucm_deployment.impl.DeploymentModuleImpl#getBase_Package <em>Base Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeploymentModuleImpl extends MinimalEObjectImpl.Container implements DeploymentModule {
	/**
	 * The cached value of the '{@link #getProgrammingLanguages() <em>Programming Languages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProgrammingLanguages()
	 * @generated
	 * @ordered
	 */
	protected ProgrammingLanguages programmingLanguages;

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
	protected DeploymentModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Ucm_deploymentPackage.Literals.DEPLOYMENT_MODULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProgrammingLanguages getProgrammingLanguages() {
		return programmingLanguages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProgrammingLanguages(ProgrammingLanguages newProgrammingLanguages, NotificationChain msgs) {
		ProgrammingLanguages oldProgrammingLanguages = programmingLanguages;
		programmingLanguages = newProgrammingLanguages;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES, oldProgrammingLanguages, newProgrammingLanguages);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProgrammingLanguages(ProgrammingLanguages newProgrammingLanguages) {
		if (newProgrammingLanguages != programmingLanguages) {
			NotificationChain msgs = null;
			if (programmingLanguages != null)
				msgs = ((InternalEObject)programmingLanguages).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES, null, msgs);
			if (newProgrammingLanguages != null)
				msgs = ((InternalEObject)newProgrammingLanguages).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES, null, msgs);
			msgs = basicSetProgrammingLanguages(newProgrammingLanguages, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES, newProgrammingLanguages, newProgrammingLanguages));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Ucm_deploymentPackage.DEPLOYMENT_MODULE__BASE_PACKAGE, oldBase_Package, base_Package));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Ucm_deploymentPackage.DEPLOYMENT_MODULE__BASE_PACKAGE, oldBase_Package, base_Package));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES:
				return basicSetProgrammingLanguages(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES:
				return getProgrammingLanguages();
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__BASE_PACKAGE:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES:
				setProgrammingLanguages((ProgrammingLanguages)newValue);
				return;
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__BASE_PACKAGE:
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
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES:
				setProgrammingLanguages((ProgrammingLanguages)null);
				return;
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__BASE_PACKAGE:
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
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__PROGRAMMING_LANGUAGES:
				return programmingLanguages != null;
			case Ucm_deploymentPackage.DEPLOYMENT_MODULE__BASE_PACKAGE:
				return base_Package != null;
		}
		return super.eIsSet(featureID);
	}

} //DeploymentModuleImpl
