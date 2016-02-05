/*****************************************************************************
 * Copyright (c) 2014 Jonathan Geoffroy
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	Jonathan Geoffroy  geoffroy.jonathan@gmail.com  - Meta-model conception
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.GeneratoroptionsmmPackage;
import org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.Options;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Options</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.papyrus.designer.languages.java.generator.metamodel.jdt.generatoroptionsmm.impl.OptionsImpl#getDefaultSourceFolder <em>Default Source Folder</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OptionsImpl extends MinimalEObjectImpl.Container implements Options {
	/**
	 * The default value of the '{@link #getDefaultSourceFolder() <em>Default Source Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getDefaultSourceFolder()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_SOURCE_FOLDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultSourceFolder() <em>Default Source Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getDefaultSourceFolder()
	 * @generated
	 * @ordered
	 */
	protected String defaultSourceFolder = DEFAULT_SOURCE_FOLDER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected OptionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneratoroptionsmmPackage.Literals.OPTIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String getDefaultSourceFolder() {
		return defaultSourceFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setDefaultSourceFolder(String newDefaultSourceFolder) {
		String oldDefaultSourceFolder = defaultSourceFolder;
		defaultSourceFolder = newDefaultSourceFolder;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GeneratoroptionsmmPackage.OPTIONS__DEFAULT_SOURCE_FOLDER, oldDefaultSourceFolder, defaultSourceFolder));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GeneratoroptionsmmPackage.OPTIONS__DEFAULT_SOURCE_FOLDER:
			return getDefaultSourceFolder();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GeneratoroptionsmmPackage.OPTIONS__DEFAULT_SOURCE_FOLDER:
			setDefaultSourceFolder((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GeneratoroptionsmmPackage.OPTIONS__DEFAULT_SOURCE_FOLDER:
			setDefaultSourceFolder(DEFAULT_SOURCE_FOLDER_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GeneratoroptionsmmPackage.OPTIONS__DEFAULT_SOURCE_FOLDER:
			return DEFAULT_SOURCE_FOLDER_EDEFAULT == null ? defaultSourceFolder != null : !DEFAULT_SOURCE_FOLDER_EDEFAULT.equals(defaultSourceFolder);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (defaultSourceFolder: ");
		result.append(defaultSourceFolder);
		result.append(')');
		return result.toString();
	}

} // OptionsImpl
