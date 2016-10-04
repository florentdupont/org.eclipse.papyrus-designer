/**
 */
package org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject;

import org.eclipse.uml2.uml.Model;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Maven Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getParentGroupId <em>Parent Group Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getParentArtifactId <em>Parent Artifact Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getParentVersion <em>Parent Version</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getArtifactId <em>Artifact Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getBase_Model <em>Base Model</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getArchType <em>Arch Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MavenProjectImpl extends MinimalEObjectImpl.Container implements MavenProject {
	/**
	 * The default value of the '{@link #getParentGroupId() <em>Parent Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_GROUP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParentGroupId() <em>Parent Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentGroupId()
	 * @generated
	 * @ordered
	 */
	protected String parentGroupId = PARENT_GROUP_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getParentArtifactId() <em>Parent Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentArtifactId()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_ARTIFACT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParentArtifactId() <em>Parent Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentArtifactId()
	 * @generated
	 * @ordered
	 */
	protected String parentArtifactId = PARENT_ARTIFACT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getParentVersion() <em>Parent Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParentVersion() <em>Parent Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentVersion()
	 * @generated
	 * @ordered
	 */
	protected String parentVersion = PARENT_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected String groupId = GROUP_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected String artifactId = ARTIFACT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBase_Model() <em>Base Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase_Model()
	 * @generated
	 * @ordered
	 */
	protected Model base_Model;

	/**
	 * The cached value of the '{@link #getArchType() <em>Arch Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchType()
	 * @generated
	 * @ordered
	 */
	protected ArcheType archType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MavenProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CodegenPackage.Literals.MAVEN_PROJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParentGroupId() {
		return parentGroupId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentGroupId(String newParentGroupId) {
		String oldParentGroupId = parentGroupId;
		parentGroupId = newParentGroupId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__PARENT_GROUP_ID, oldParentGroupId, parentGroupId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParentArtifactId() {
		return parentArtifactId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentArtifactId(String newParentArtifactId) {
		String oldParentArtifactId = parentArtifactId;
		parentArtifactId = newParentArtifactId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__PARENT_ARTIFACT_ID, oldParentArtifactId, parentArtifactId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParentVersion() {
		return parentVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentVersion(String newParentVersion) {
		String oldParentVersion = parentVersion;
		parentVersion = newParentVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__PARENT_VERSION, oldParentVersion, parentVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupId(String newGroupId) {
		String oldGroupId = groupId;
		groupId = newGroupId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__GROUP_ID, oldGroupId, groupId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArtifactId(String newArtifactId) {
		String oldArtifactId = artifactId;
		artifactId = newArtifactId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID, oldArtifactId, artifactId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getBase_Model() {
		if (base_Model != null && base_Model.eIsProxy()) {
			InternalEObject oldBase_Model = (InternalEObject)base_Model;
			base_Model = (Model)eResolveProxy(oldBase_Model);
			if (base_Model != oldBase_Model) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CodegenPackage.MAVEN_PROJECT__BASE_MODEL, oldBase_Model, base_Model));
			}
		}
		return base_Model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model basicGetBase_Model() {
		return base_Model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase_Model(Model newBase_Model) {
		Model oldBase_Model = base_Model;
		base_Model = newBase_Model;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__BASE_MODEL, oldBase_Model, base_Model));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArcheType getArchType() {
		if (archType != null && archType.eIsProxy()) {
			InternalEObject oldArchType = (InternalEObject)archType;
			archType = (ArcheType)eResolveProxy(oldArchType);
			if (archType != oldArchType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CodegenPackage.MAVEN_PROJECT__ARCH_TYPE, oldArchType, archType));
			}
		}
		return archType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArcheType basicGetArchType() {
		return archType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArchType(ArcheType newArchType) {
		ArcheType oldArchType = archType;
		archType = newArchType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__ARCH_TYPE, oldArchType, archType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CodegenPackage.MAVEN_PROJECT__PARENT_GROUP_ID:
				return getParentGroupId();
			case CodegenPackage.MAVEN_PROJECT__PARENT_ARTIFACT_ID:
				return getParentArtifactId();
			case CodegenPackage.MAVEN_PROJECT__PARENT_VERSION:
				return getParentVersion();
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				return getGroupId();
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				return getArtifactId();
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				return getVersion();
			case CodegenPackage.MAVEN_PROJECT__BASE_MODEL:
				if (resolve) return getBase_Model();
				return basicGetBase_Model();
			case CodegenPackage.MAVEN_PROJECT__ARCH_TYPE:
				if (resolve) return getArchType();
				return basicGetArchType();
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
			case CodegenPackage.MAVEN_PROJECT__PARENT_GROUP_ID:
				setParentGroupId((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__PARENT_ARTIFACT_ID:
				setParentArtifactId((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__PARENT_VERSION:
				setParentVersion((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				setGroupId((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				setArtifactId((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				setVersion((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__BASE_MODEL:
				setBase_Model((Model)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARCH_TYPE:
				setArchType((ArcheType)newValue);
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
			case CodegenPackage.MAVEN_PROJECT__PARENT_GROUP_ID:
				setParentGroupId(PARENT_GROUP_ID_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__PARENT_ARTIFACT_ID:
				setParentArtifactId(PARENT_ARTIFACT_ID_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__PARENT_VERSION:
				setParentVersion(PARENT_VERSION_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				setGroupId(GROUP_ID_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				setArtifactId(ARTIFACT_ID_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__BASE_MODEL:
				setBase_Model((Model)null);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARCH_TYPE:
				setArchType((ArcheType)null);
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
			case CodegenPackage.MAVEN_PROJECT__PARENT_GROUP_ID:
				return PARENT_GROUP_ID_EDEFAULT == null ? parentGroupId != null : !PARENT_GROUP_ID_EDEFAULT.equals(parentGroupId);
			case CodegenPackage.MAVEN_PROJECT__PARENT_ARTIFACT_ID:
				return PARENT_ARTIFACT_ID_EDEFAULT == null ? parentArtifactId != null : !PARENT_ARTIFACT_ID_EDEFAULT.equals(parentArtifactId);
			case CodegenPackage.MAVEN_PROJECT__PARENT_VERSION:
				return PARENT_VERSION_EDEFAULT == null ? parentVersion != null : !PARENT_VERSION_EDEFAULT.equals(parentVersion);
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				return GROUP_ID_EDEFAULT == null ? groupId != null : !GROUP_ID_EDEFAULT.equals(groupId);
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				return ARTIFACT_ID_EDEFAULT == null ? artifactId != null : !ARTIFACT_ID_EDEFAULT.equals(artifactId);
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case CodegenPackage.MAVEN_PROJECT__BASE_MODEL:
				return base_Model != null;
			case CodegenPackage.MAVEN_PROJECT__ARCH_TYPE:
				return archType != null;
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
		result.append(" (parentGroupId: ");
		result.append(parentGroupId);
		result.append(", parentArtifactId: ");
		result.append(parentArtifactId);
		result.append(", parentVersion: ");
		result.append(parentVersion);
		result.append(", groupId: ");
		result.append(groupId);
		result.append(", artifactId: ");
		result.append(artifactId);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //MavenProjectImpl
