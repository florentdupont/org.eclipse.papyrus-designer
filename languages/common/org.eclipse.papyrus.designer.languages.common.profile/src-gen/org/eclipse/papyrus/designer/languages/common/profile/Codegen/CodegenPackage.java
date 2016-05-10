/**
 */
package org.eclipse.papyrus.designer.languages.common.profile.Codegen;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.CodegenFactory
 * @model kind="package"
 * @generated
 */
public interface CodegenPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Codegen";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/papyrus/Codegen/1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Codegen";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CodegenPackage eINSTANCE = org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ProjectImpl <em>Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ProjectImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 0;

	/**
	 * The feature id for the '<em><b>Base Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__BASE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Generation Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__GENERATION_MODE = 1;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__PROJECT_NAME = 2;

	/**
	 * The feature id for the '<em><b>Source Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__SOURCE_FOLDER = 3;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__PREFIX = 4;

	/**
	 * The number of structural features of the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind <em>Generation Mode Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getGenerationModeKind()
	 * @generated
	 */
	int GENERATION_MODE_KIND = 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getBase_Model <em>Base Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Model</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getBase_Model()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Base_Model();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getProjectName()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_ProjectName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getSourceFolder <em>Source Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Folder</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getSourceFolder()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_SourceFolder();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getPrefix()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Prefix();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getGenerationMode <em>Generation Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generation Mode</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Project#getGenerationMode()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_GenerationMode();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind <em>Generation Mode Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Generation Mode Kind</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind
	 * @generated
	 */
	EEnum getGenerationModeKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CodegenFactory getCodegenFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ProjectImpl <em>Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ProjectImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getProject()
		 * @generated
		 */
		EClass PROJECT = eINSTANCE.getProject();

		/**
		 * The meta object literal for the '<em><b>Base Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT__BASE_MODEL = eINSTANCE.getProject_Base_Model();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__PROJECT_NAME = eINSTANCE.getProject_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Source Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__SOURCE_FOLDER = eINSTANCE.getProject_SourceFolder();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__PREFIX = eINSTANCE.getProject_Prefix();

		/**
		 * The meta object literal for the '<em><b>Generation Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__GENERATION_MODE = eINSTANCE.getProject_GenerationMode();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind <em>Generation Mode Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getGenerationModeKind()
		 * @generated
		 */
		EEnum GENERATION_MODE_KIND = eINSTANCE.getGenerationModeKind();

	}

} //CodegenPackage
