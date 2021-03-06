/**
 */
package org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject;

import org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Maven Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getArtifactId <em>Artifact Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getArcheType <em>Arche Type</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl#getBase_Package <em>Base Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MavenProjectImpl extends ProjectImpl implements MavenProject {
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
	 * The cached value of the '{@link #getArcheType() <em>Arche Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArcheType()
	 * @generated
	 * @ordered
	 */
	protected ArcheType archeType;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<MavenDependency> dependencies;

	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected ParentArtifact parent;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

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
	public ArcheType getArcheType() {
		if (archeType != null && archeType.eIsProxy()) {
			InternalEObject oldArcheType = (InternalEObject)archeType;
			archeType = (ArcheType)eResolveProxy(oldArcheType);
			if (archeType != oldArcheType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CodegenPackage.MAVEN_PROJECT__ARCHE_TYPE, oldArcheType, archeType));
			}
		}
		return archeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArcheType basicGetArcheType() {
		return archeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArcheType(ArcheType newArcheType) {
		ArcheType oldArcheType = archeType;
		archeType = newArcheType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__ARCHE_TYPE, oldArcheType, archeType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MavenDependency> getDependencies() {
		if (dependencies == null) {
			dependencies = new EObjectResolvingEList<MavenDependency>(MavenDependency.class, this, CodegenPackage.MAVEN_PROJECT__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParentArtifact getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject)parent;
			parent = (ParentArtifact)eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CodegenPackage.MAVEN_PROJECT__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParentArtifact basicGetParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(ParentArtifact newParent) {
		ParentArtifact oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__PARENT, oldParent, parent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectResolvingEList<Property>(Property.class, this, CodegenPackage.MAVEN_PROJECT__PROPERTIES);
		}
		return properties;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CodegenPackage.MAVEN_PROJECT__BASE_PACKAGE, oldBase_Package, base_Package));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CodegenPackage.MAVEN_PROJECT__BASE_PACKAGE, oldBase_Package, base_Package));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				return getGroupId();
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				return getArtifactId();
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				return getVersion();
			case CodegenPackage.MAVEN_PROJECT__ARCHE_TYPE:
				if (resolve) return getArcheType();
				return basicGetArcheType();
			case CodegenPackage.MAVEN_PROJECT__DEPENDENCIES:
				return getDependencies();
			case CodegenPackage.MAVEN_PROJECT__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case CodegenPackage.MAVEN_PROJECT__PROPERTIES:
				return getProperties();
			case CodegenPackage.MAVEN_PROJECT__BASE_PACKAGE:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				setGroupId((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				setArtifactId((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				setVersion((String)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARCHE_TYPE:
				setArcheType((ArcheType)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection<? extends MavenDependency>)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__PARENT:
				setParent((ParentArtifact)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
			case CodegenPackage.MAVEN_PROJECT__BASE_PACKAGE:
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
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				setGroupId(GROUP_ID_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				setArtifactId(ARTIFACT_ID_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case CodegenPackage.MAVEN_PROJECT__ARCHE_TYPE:
				setArcheType((ArcheType)null);
				return;
			case CodegenPackage.MAVEN_PROJECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case CodegenPackage.MAVEN_PROJECT__PARENT:
				setParent((ParentArtifact)null);
				return;
			case CodegenPackage.MAVEN_PROJECT__PROPERTIES:
				getProperties().clear();
				return;
			case CodegenPackage.MAVEN_PROJECT__BASE_PACKAGE:
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
			case CodegenPackage.MAVEN_PROJECT__GROUP_ID:
				return GROUP_ID_EDEFAULT == null ? groupId != null : !GROUP_ID_EDEFAULT.equals(groupId);
			case CodegenPackage.MAVEN_PROJECT__ARTIFACT_ID:
				return ARTIFACT_ID_EDEFAULT == null ? artifactId != null : !ARTIFACT_ID_EDEFAULT.equals(artifactId);
			case CodegenPackage.MAVEN_PROJECT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case CodegenPackage.MAVEN_PROJECT__ARCHE_TYPE:
				return archeType != null;
			case CodegenPackage.MAVEN_PROJECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case CodegenPackage.MAVEN_PROJECT__PARENT:
				return parent != null;
			case CodegenPackage.MAVEN_PROJECT__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case CodegenPackage.MAVEN_PROJECT__BASE_PACKAGE:
				return base_Package != null;
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
		result.append(" (groupId: ");
		result.append(groupId);
		result.append(", artifactId: ");
		result.append(artifactId);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //MavenProjectImpl
