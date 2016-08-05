/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implementation Properties</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeRam <em>Size Ram</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeROM <em>Size ROM</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSupportedOS <em>Supported OS</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getArch <em>Arch</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getImplementationProperties()
 * @model
 * @generated
 */
public interface ImplementationProperties extends EObject {
	/**
	 * Returns the value of the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Class</em>' reference.
	 * @see #setBase_Class(org.eclipse.uml2.uml.Class)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getImplementationProperties_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

	/**
	 * Returns the value of the '<em><b>Size Ram</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size Ram</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size Ram</em>' attribute.
	 * @see #setSizeRam(int)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getImplementationProperties_SizeRam()
	 * @model dataType="org.eclipse.uml2.types.Integer" required="true" ordered="false"
	 * @generated
	 */
	int getSizeRam();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeRam <em>Size Ram</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size Ram</em>' attribute.
	 * @see #getSizeRam()
	 * @generated
	 */
	void setSizeRam(int value);

	/**
	 * Returns the value of the '<em><b>Size ROM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size ROM</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size ROM</em>' attribute.
	 * @see #setSizeROM(int)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getImplementationProperties_SizeROM()
	 * @model dataType="org.eclipse.uml2.types.Integer" required="true" ordered="false"
	 * @generated
	 */
	int getSizeROM();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getSizeROM <em>Size ROM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size ROM</em>' attribute.
	 * @see #getSizeROM()
	 * @generated
	 */
	void setSizeROM(int value);

	/**
	 * Returns the value of the '<em><b>Supported OS</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supported OS</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supported OS</em>' reference list.
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getImplementationProperties_SupportedOS()
	 * @model ordered="false"
	 * @generated
	 */
	EList<OperatingSystem> getSupportedOS();

	/**
	 * Returns the value of the '<em><b>Arch</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arch</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arch</em>' reference.
	 * @see #setArch(TargetArchitecture)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getImplementationProperties_Arch()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	TargetArchitecture getArch();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties#getArch <em>Arch</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arch</em>' reference.
	 * @see #getArch()
	 * @generated
	 */
	void setArch(TargetArchitecture value);

} // ImplementationProperties
