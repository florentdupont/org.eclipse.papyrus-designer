/**
 */
package org.eclipse.papyrus.designer.deployment.profile.Deployment;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getBase_Class <em>Base Class</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getUsedOS <em>Used OS</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailRAM <em>Avail RAM</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailROM <em>Avail ROM</em>}</li>
 *   <li>{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getTarget()
 * @model
 * @generated
 */
public interface Target extends EObject {
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
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getTarget_Base_Class()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	org.eclipse.uml2.uml.Class getBase_Class();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getBase_Class <em>Base Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' reference.
	 * @see #getBase_Class()
	 * @generated
	 */
	void setBase_Class(org.eclipse.uml2.uml.Class value);

	/**
	 * Returns the value of the '<em><b>Used OS</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used OS</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used OS</em>' reference.
	 * @see #setUsedOS(OperatingSystem)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getTarget_UsedOS()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	OperatingSystem getUsedOS();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getUsedOS <em>Used OS</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used OS</em>' reference.
	 * @see #getUsedOS()
	 * @generated
	 */
	void setUsedOS(OperatingSystem value);

	/**
	 * Returns the value of the '<em><b>Avail RAM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Avail RAM</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Avail RAM</em>' attribute.
	 * @see #setAvailRAM(int)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getTarget_AvailRAM()
	 * @model dataType="org.eclipse.uml2.types.Integer" required="true" ordered="false"
	 * @generated
	 */
	int getAvailRAM();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailRAM <em>Avail RAM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Avail RAM</em>' attribute.
	 * @see #getAvailRAM()
	 * @generated
	 */
	void setAvailRAM(int value);

	/**
	 * Returns the value of the '<em><b>Avail ROM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Avail ROM</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Avail ROM</em>' attribute.
	 * @see #setAvailROM(int)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getTarget_AvailROM()
	 * @model dataType="org.eclipse.uml2.types.Integer" required="true" ordered="false"
	 * @generated
	 */
	int getAvailROM();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getAvailROM <em>Avail ROM</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Avail ROM</em>' attribute.
	 * @see #getAvailROM()
	 * @generated
	 */
	void setAvailROM(int value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(TargetArchitecture)
	 * @see org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPackage#getTarget_Target()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	TargetArchitecture getTarget();

	/**
	 * Sets the value of the '{@link org.eclipse.papyrus.designer.deployment.profile.Deployment.Target#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(TargetArchitecture value);

} // Target
