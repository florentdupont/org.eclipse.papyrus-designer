/**
 */
package org.eclipse.papyrus.designer.languages.common.profile.Codegen;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.Model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Maven Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParentGroupId <em>Parent Group Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParentArtifactId <em>Parent Artifact Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParentVersion <em>Parent Version</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getGroupId <em>Group Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArtifactId <em>Artifact Id</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getBase_Model <em>Base Model</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArchType <em>Arch Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject()
 * @model
 * @generated
 */
public interface MavenProject extends EObject {
	/**
	 * Returns the value of the '<em><b>Parent Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Group Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Group Id</em>' attribute.
	 * @see #setParentGroupId(String)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_ParentGroupId()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getParentGroupId();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParentGroupId <em>Parent Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Group Id</em>' attribute.
	 * @see #getParentGroupId()
	 * @generated
	 */
	void setParentGroupId(String value);

	/**
	 * Returns the value of the '<em><b>Parent Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Artifact Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Artifact Id</em>' attribute.
	 * @see #setParentArtifactId(String)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_ParentArtifactId()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getParentArtifactId();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParentArtifactId <em>Parent Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Artifact Id</em>' attribute.
	 * @see #getParentArtifactId()
	 * @generated
	 */
	void setParentArtifactId(String value);

	/**
	 * Returns the value of the '<em><b>Parent Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Version</em>' attribute.
	 * @see #setParentVersion(String)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_ParentVersion()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getParentVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParentVersion <em>Parent Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Version</em>' attribute.
	 * @see #getParentVersion()
	 * @generated
	 */
	void setParentVersion(String value);

	/**
	 * Returns the value of the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Id</em>' attribute.
	 * @see #setGroupId(String)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_GroupId()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getGroupId();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getGroupId <em>Group Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Id</em>' attribute.
	 * @see #getGroupId()
	 * @generated
	 */
	void setGroupId(String value);

	/**
	 * Returns the value of the '<em><b>Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Id</em>' attribute.
	 * @see #setArtifactId(String)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_ArtifactId()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getArtifactId();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArtifactId <em>Artifact Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact Id</em>' attribute.
	 * @see #getArtifactId()
	 * @generated
	 */
	void setArtifactId(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_Version()
	 * @model dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Base Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Model</em>' reference.
	 * @see #setBase_Model(Model)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_Base_Model()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Model getBase_Model();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getBase_Model <em>Base Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Model</em>' reference.
	 * @see #getBase_Model()
	 * @generated
	 */
	void setBase_Model(Model value);

	/**
	 * Returns the value of the '<em><b>Arch Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arch Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arch Type</em>' reference.
	 * @see #setArchType(ArcheType)
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenPackage#getMavenProject_ArchType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ArcheType getArchType();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArchType <em>Arch Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arch Type</em>' reference.
	 * @see #getArchType()
	 * @generated
	 */
	void setArchType(ArcheType value);

} // MavenProject
