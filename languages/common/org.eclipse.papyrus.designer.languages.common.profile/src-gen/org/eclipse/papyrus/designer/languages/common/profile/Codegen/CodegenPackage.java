/*******************************************************************************
 * Copyright (c) 2006, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *     
 *     
 *******************************************************************************/
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
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.GeneratorHintImpl <em>Generator Hint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.GeneratorHintImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getGeneratorHint()
	 * @generated
	 */
	int GENERATOR_HINT = 1;

	/**
	 * The feature id for the '<em><b>Base Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_HINT__BASE_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_HINT__LANGUAGE = 1;

	/**
	 * The feature id for the '<em><b>Generator ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_HINT__GENERATOR_ID = 2;

	/**
	 * The number of structural features of the '<em>Generator Hint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_HINT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Generator Hint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_HINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.LanguageImpl <em>Language</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.LanguageImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getLanguage()
	 * @generated
	 */
	int LANGUAGE = 2;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE__BASE_CLASS = 0;

	/**
	 * The number of structural features of the '<em>Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LANGUAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.NoCodeGenImpl <em>No Code Gen</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.NoCodeGenImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getNoCodeGen()
	 * @generated
	 */
	int NO_CODE_GEN = 3;

	/**
	 * The feature id for the '<em><b>Base Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CODE_GEN__BASE_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>No Code Gen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CODE_GEN_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>No Code Gen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO_CODE_GEN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl <em>Maven Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getMavenProject()
	 * @generated
	 */
	int MAVEN_PROJECT = 4;

	/**
	 * The feature id for the '<em><b>Base Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__BASE_MODEL = PROJECT__BASE_MODEL;

	/**
	 * The feature id for the '<em><b>Generation Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__GENERATION_MODE = PROJECT__GENERATION_MODE;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__PROJECT_NAME = PROJECT__PROJECT_NAME;

	/**
	 * The feature id for the '<em><b>Source Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__SOURCE_FOLDER = PROJECT__SOURCE_FOLDER;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__PREFIX = PROJECT__PREFIX;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__GROUP_ID = PROJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__ARTIFACT_ID = PROJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__VERSION = PROJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Arche Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__ARCHE_TYPE = PROJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__DEPENDENCIES = PROJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__PARENT = PROJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT__PROPERTIES = PROJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Maven Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT_FEATURE_COUNT = PROJECT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Maven Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_PROJECT_OPERATION_COUNT = PROJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ArcheTypeImpl <em>Arche Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ArcheTypeImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getArcheType()
	 * @generated
	 */
	int ARCHE_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHE_TYPE__GROUP_ID = 0;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHE_TYPE__ARTIFACT_ID = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHE_TYPE__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHE_TYPE__REPOSITORY = 3;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHE_TYPE__BASE_CLASS = 4;

	/**
	 * The number of structural features of the '<em>Arche Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHE_TYPE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Arche Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenDependencyImpl <em>Maven Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenDependencyImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getMavenDependency()
	 * @generated
	 */
	int MAVEN_DEPENDENCY = 6;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_DEPENDENCY__GROUP_ID = 0;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_DEPENDENCY__ARTIFACT_ID = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_DEPENDENCY__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_DEPENDENCY__BASE_CLASS = 3;

	/**
	 * The feature id for the '<em><b>Exclusions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_DEPENDENCY__EXCLUSIONS = 4;

	/**
	 * The number of structural features of the '<em>Maven Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_DEPENDENCY_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Maven Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_DEPENDENCY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ExcludedDependencyImpl <em>Excluded Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ExcludedDependencyImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getExcludedDependency()
	 * @generated
	 */
	int EXCLUDED_DEPENDENCY = 7;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDED_DEPENDENCY__GROUP_ID = 0;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDED_DEPENDENCY__ARTIFACT_ID = 1;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDED_DEPENDENCY__BASE_CLASS = 2;

	/**
	 * The number of structural features of the '<em>Excluded Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDED_DEPENDENCY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Excluded Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCLUDED_DEPENDENCY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ParentArtifactImpl <em>Parent Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ParentArtifactImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getParentArtifact()
	 * @generated
	 */
	int PARENT_ARTIFACT = 8;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_ARTIFACT__GROUP_ID = 0;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_ARTIFACT__ARTIFACT_ID = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_ARTIFACT__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_ARTIFACT__BASE_CLASS = 3;

	/**
	 * The number of structural features of the '<em>Parent Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_ARTIFACT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Parent Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_ARTIFACT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.PropertyImpl
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__BASE_CLASS = 2;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind <em>Generation Mode Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GenerationModeKind
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getGenerationModeKind()
	 * @generated
	 */
	int GENERATION_MODE_KIND = 10;


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
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint <em>Generator Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generator Hint</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint
	 * @generated
	 */
	EClass getGeneratorHint();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getBase_Element <em>Base Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Element</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getBase_Element()
	 * @see #getGeneratorHint()
	 * @generated
	 */
	EReference getGeneratorHint_Base_Element();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Language</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getLanguage()
	 * @see #getGeneratorHint()
	 * @generated
	 */
	EReference getGeneratorHint_Language();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getGeneratorID <em>Generator ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generator ID</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint#getGeneratorID()
	 * @see #getGeneratorHint()
	 * @generated
	 */
	EAttribute getGeneratorHint_GeneratorID();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Language <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Language</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Language
	 * @generated
	 */
	EClass getLanguage();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Language#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Language#getBase_Class()
	 * @see #getLanguage()
	 * @generated
	 */
	EReference getLanguage_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.NoCodeGen <em>No Code Gen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>No Code Gen</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.NoCodeGen
	 * @generated
	 */
	EClass getNoCodeGen();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.NoCodeGen#getBase_Element <em>Base Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Element</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.NoCodeGen#getBase_Element()
	 * @see #getNoCodeGen()
	 * @generated
	 */
	EReference getNoCodeGen_Base_Element();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject <em>Maven Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Maven Project</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject
	 * @generated
	 */
	EClass getMavenProject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getGroupId <em>Group Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getGroupId()
	 * @see #getMavenProject()
	 * @generated
	 */
	EAttribute getMavenProject_GroupId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArtifactId <em>Artifact Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArtifactId()
	 * @see #getMavenProject()
	 * @generated
	 */
	EAttribute getMavenProject_ArtifactId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getVersion()
	 * @see #getMavenProject()
	 * @generated
	 */
	EAttribute getMavenProject_Version();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArcheType <em>Arche Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Arche Type</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getArcheType()
	 * @see #getMavenProject()
	 * @generated
	 */
	EReference getMavenProject_ArcheType();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dependencies</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getDependencies()
	 * @see #getMavenProject()
	 * @generated
	 */
	EReference getMavenProject_Dependencies();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getParent()
	 * @see #getMavenProject()
	 * @generated
	 */
	EReference getMavenProject_Parent();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Properties</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject#getProperties()
	 * @see #getMavenProject()
	 * @generated
	 */
	EReference getMavenProject_Properties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType <em>Arche Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arche Type</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType
	 * @generated
	 */
	EClass getArcheType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getGroupId <em>Group Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getGroupId()
	 * @see #getArcheType()
	 * @generated
	 */
	EAttribute getArcheType_GroupId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getArtifactId <em>Artifact Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getArtifactId()
	 * @see #getArcheType()
	 * @generated
	 */
	EAttribute getArcheType_ArtifactId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getVersion()
	 * @see #getArcheType()
	 * @generated
	 */
	EAttribute getArcheType_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repository</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getRepository()
	 * @see #getArcheType()
	 * @generated
	 */
	EAttribute getArcheType_Repository();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ArcheType#getBase_Class()
	 * @see #getArcheType()
	 * @generated
	 */
	EReference getArcheType_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency <em>Maven Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Maven Dependency</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency
	 * @generated
	 */
	EClass getMavenDependency();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getGroupId <em>Group Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getGroupId()
	 * @see #getMavenDependency()
	 * @generated
	 */
	EAttribute getMavenDependency_GroupId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getArtifactId <em>Artifact Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getArtifactId()
	 * @see #getMavenDependency()
	 * @generated
	 */
	EAttribute getMavenDependency_ArtifactId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getVersion()
	 * @see #getMavenDependency()
	 * @generated
	 */
	EAttribute getMavenDependency_Version();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getBase_Class()
	 * @see #getMavenDependency()
	 * @generated
	 */
	EReference getMavenDependency_Base_Class();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getExclusions <em>Exclusions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Exclusions</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency#getExclusions()
	 * @see #getMavenDependency()
	 * @generated
	 */
	EReference getMavenDependency_Exclusions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency <em>Excluded Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Excluded Dependency</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency
	 * @generated
	 */
	EClass getExcludedDependency();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency#getGroupId <em>Group Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency#getGroupId()
	 * @see #getExcludedDependency()
	 * @generated
	 */
	EAttribute getExcludedDependency_GroupId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency#getArtifactId <em>Artifact Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency#getArtifactId()
	 * @see #getExcludedDependency()
	 * @generated
	 */
	EAttribute getExcludedDependency_ArtifactId();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency#getBase_Class()
	 * @see #getExcludedDependency()
	 * @generated
	 */
	EReference getExcludedDependency_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact <em>Parent Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parent Artifact</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact
	 * @generated
	 */
	EClass getParentArtifact();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getGroupId <em>Group Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getGroupId()
	 * @see #getParentArtifact()
	 * @generated
	 */
	EAttribute getParentArtifact_GroupId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getArtifactId <em>Artifact Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getArtifactId()
	 * @see #getParentArtifact()
	 * @generated
	 */
	EAttribute getParentArtifact_ArtifactId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getVersion()
	 * @see #getParentArtifact()
	 * @generated
	 */
	EAttribute getParentArtifact_Version();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact#getBase_Class()
	 * @see #getParentArtifact()
	 * @generated
	 */
	EReference getParentArtifact_Base_Class();

	/**
	 * Returns the meta object for class '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property#getBase_Class <em>Base Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Class</em>'.
	 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property#getBase_Class()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_Base_Class();

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
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.GeneratorHintImpl <em>Generator Hint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.GeneratorHintImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getGeneratorHint()
		 * @generated
		 */
		EClass GENERATOR_HINT = eINSTANCE.getGeneratorHint();

		/**
		 * The meta object literal for the '<em><b>Base Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERATOR_HINT__BASE_ELEMENT = eINSTANCE.getGeneratorHint_Base_Element();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERATOR_HINT__LANGUAGE = eINSTANCE.getGeneratorHint_Language();

		/**
		 * The meta object literal for the '<em><b>Generator ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_HINT__GENERATOR_ID = eINSTANCE.getGeneratorHint_GeneratorID();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.LanguageImpl <em>Language</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.LanguageImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getLanguage()
		 * @generated
		 */
		EClass LANGUAGE = eINSTANCE.getLanguage();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LANGUAGE__BASE_CLASS = eINSTANCE.getLanguage_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.NoCodeGenImpl <em>No Code Gen</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.NoCodeGenImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getNoCodeGen()
		 * @generated
		 */
		EClass NO_CODE_GEN = eINSTANCE.getNoCodeGen();

		/**
		 * The meta object literal for the '<em><b>Base Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NO_CODE_GEN__BASE_ELEMENT = eINSTANCE.getNoCodeGen_Base_Element();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl <em>Maven Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenProjectImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getMavenProject()
		 * @generated
		 */
		EClass MAVEN_PROJECT = eINSTANCE.getMavenProject();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_PROJECT__GROUP_ID = eINSTANCE.getMavenProject_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_PROJECT__ARTIFACT_ID = eINSTANCE.getMavenProject_ArtifactId();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_PROJECT__VERSION = eINSTANCE.getMavenProject_Version();

		/**
		 * The meta object literal for the '<em><b>Arche Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAVEN_PROJECT__ARCHE_TYPE = eINSTANCE.getMavenProject_ArcheType();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAVEN_PROJECT__DEPENDENCIES = eINSTANCE.getMavenProject_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAVEN_PROJECT__PARENT = eINSTANCE.getMavenProject_Parent();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAVEN_PROJECT__PROPERTIES = eINSTANCE.getMavenProject_Properties();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ArcheTypeImpl <em>Arche Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ArcheTypeImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getArcheType()
		 * @generated
		 */
		EClass ARCHE_TYPE = eINSTANCE.getArcheType();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHE_TYPE__GROUP_ID = eINSTANCE.getArcheType_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHE_TYPE__ARTIFACT_ID = eINSTANCE.getArcheType_ArtifactId();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHE_TYPE__VERSION = eINSTANCE.getArcheType_Version();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCHE_TYPE__REPOSITORY = eINSTANCE.getArcheType_Repository();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHE_TYPE__BASE_CLASS = eINSTANCE.getArcheType_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenDependencyImpl <em>Maven Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.MavenDependencyImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getMavenDependency()
		 * @generated
		 */
		EClass MAVEN_DEPENDENCY = eINSTANCE.getMavenDependency();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_DEPENDENCY__GROUP_ID = eINSTANCE.getMavenDependency_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_DEPENDENCY__ARTIFACT_ID = eINSTANCE.getMavenDependency_ArtifactId();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_DEPENDENCY__VERSION = eINSTANCE.getMavenDependency_Version();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAVEN_DEPENDENCY__BASE_CLASS = eINSTANCE.getMavenDependency_Base_Class();

		/**
		 * The meta object literal for the '<em><b>Exclusions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAVEN_DEPENDENCY__EXCLUSIONS = eINSTANCE.getMavenDependency_Exclusions();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ExcludedDependencyImpl <em>Excluded Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ExcludedDependencyImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getExcludedDependency()
		 * @generated
		 */
		EClass EXCLUDED_DEPENDENCY = eINSTANCE.getExcludedDependency();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCLUDED_DEPENDENCY__GROUP_ID = eINSTANCE.getExcludedDependency_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCLUDED_DEPENDENCY__ARTIFACT_ID = eINSTANCE.getExcludedDependency_ArtifactId();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCLUDED_DEPENDENCY__BASE_CLASS = eINSTANCE.getExcludedDependency_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ParentArtifactImpl <em>Parent Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.ParentArtifactImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getParentArtifact()
		 * @generated
		 */
		EClass PARENT_ARTIFACT = eINSTANCE.getParentArtifact();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARENT_ARTIFACT__GROUP_ID = eINSTANCE.getParentArtifact_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARENT_ARTIFACT__ARTIFACT_ID = eINSTANCE.getParentArtifact_ArtifactId();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARENT_ARTIFACT__VERSION = eINSTANCE.getParentArtifact_Version();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARENT_ARTIFACT__BASE_CLASS = eINSTANCE.getParentArtifact_Base_Class();

		/**
		 * The meta object literal for the '{@link org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.PropertyImpl
		 * @see org.eclipse.papyrus.designer.languages.common.profile.Codegen.impl.CodegenPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__BASE_CLASS = eINSTANCE.getProperty_Base_Class();

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
