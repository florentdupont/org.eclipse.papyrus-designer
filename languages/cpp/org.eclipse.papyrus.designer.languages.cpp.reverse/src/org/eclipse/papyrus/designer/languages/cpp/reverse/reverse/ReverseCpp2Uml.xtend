/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Shuai Li (CEA LIST) <shuai.li@cea.fr> - Initial API and implementation
 *   Van Cam Pham (CEA LIST) <vancam.pham@cea.fr> - Reverse implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.reverse.reverse

import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.logging.Level
import java.util.logging.Logger
import java.util.regex.Matcher
import java.util.regex.Pattern
import org.eclipse.cdt.core.CCorePlugin
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition
import org.eclipse.cdt.core.dom.ast.IASTNode
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTFunctionDefinition
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTTemplateDeclaration
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTTemplateParameter
import org.eclipse.cdt.core.index.IIndex
import org.eclipse.cdt.core.model.ICContainer
import org.eclipse.cdt.core.model.ICElement
import org.eclipse.cdt.core.model.ICProject
import org.eclipse.cdt.core.model.IDeclaration
import org.eclipse.cdt.core.model.IEnumeration
import org.eclipse.cdt.core.model.IEnumerator
import org.eclipse.cdt.core.model.IField
import org.eclipse.cdt.core.model.IFunction
import org.eclipse.cdt.core.model.IInclude
import org.eclipse.cdt.core.model.IMacro
import org.eclipse.cdt.core.model.IMethod
import org.eclipse.cdt.core.model.IMethodDeclaration
import org.eclipse.cdt.core.model.IMethodTemplateDeclaration
import org.eclipse.cdt.core.model.IParent
import org.eclipse.cdt.core.model.ISourceRange
import org.eclipse.cdt.core.model.ISourceReference
import org.eclipse.cdt.core.model.IStructure
import org.eclipse.cdt.core.model.IStructureDeclaration
import org.eclipse.cdt.core.model.IStructureTemplate
import org.eclipse.cdt.core.model.ITranslationUnit
import org.eclipse.cdt.core.model.ITypeDef
import org.eclipse.cdt.core.model.IUsing
import org.eclipse.cdt.core.model.IVariableDeclaration
import org.eclipse.cdt.core.parser.IToken
import org.eclipse.cdt.core.parser.ast.ASTAccessVisibility
import org.eclipse.core.resources.IResourceDelta
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.util.UniqueEList
import org.eclipse.emf.ecore.EObject
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Const
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ConstInit
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Friend
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Inline
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ref
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Template
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Typedef
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Virtual
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Volatile
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.ReverseUtils.StereotypeType
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change.CElementChange
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change.ChangeMapStore
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change.CppChangeObject
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change.ModelChangeObject
import org.eclipse.papyrus.designer.languages.cpp.reverse.utils.FileWatcher
import org.eclipse.papyrus.designer.languages.cpp.reverse.utils.ModelManagement
import org.eclipse.papyrus.designer.languages.cpp.reverse.utils.RoundtripCppUtils
import org.eclipse.papyrus.designer.languages.cpp.reverse.utils.TypeOperationsEnhanced
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.ClassifierTemplateParameter
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.Enumeration
import org.eclipse.uml2.uml.EnumerationLiteral
import org.eclipse.uml2.uml.Model
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.OpaqueBehavior
import org.eclipse.uml2.uml.OpaqueExpression
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.PackageableElement
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.RedefinableTemplateSignature
import org.eclipse.uml2.uml.TemplateSignature
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.UMLFactory
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.ValueSpecification
import org.eclipse.uml2.uml.VisibilityKind
import org.eclipse.uml2.uml.profile.standard.Create
import org.eclipse.uml2.uml.profile.standard.Destroy
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.uml2.uml.Generalization
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Visibility

/**
 * The main reverser class that will reverse C++ to UML
 *
 */
class ReverseCpp2Uml {	
	enum ReverseMode {
		BATCH,
		INCREMENTAL
	}
	
	enum ConflictResolutionMode {
		FROM_MODEL,
		FROM_CODE,
		UI_INTERACTION
	}
	
	private ConflictResolutionMode conflictResolveMode = ConflictResolutionMode.FROM_CODE

	public static String C_LangID = "C"
	public static String Cpp_LangID = "C++"

	private String langID = "C++"
	private Map<ICElement, EObject> map = new HashMap

	private static final String TEMPLATE_PARAMETER_SIGNATURE_NAME = "template_paremeter_signature"
	private static final String REVERSE_FOLDER = "reversed_models"
	private static final String MODEL_POSTFIX = ".uml"
	ITranslationUnit unit
	ICProject m_project
	List<Model> m_models
	private final static Logger LOGGER = Logger.getLogger(ReverseCpp2Uml.getName());

	IIndex index
	IProgressMonitor m_monitor
	
	private String path;
	
	ReverseUtils reverse_utils = ReverseUtils.instance
	private static long timestamp = 0

	private ReverseMode reverseMode = ReverseMode.BATCH
	
	private Map<ITranslationUnit, IASTTranslationUnit> translationUnitToASTTranslationUnitMap
	
	private Map<String, List<IInclude>> excludedIncludesMap
	
	//this list is used for incremental reverse engineering
	private List<CppChangeObject> changeList = new ArrayList
	private static List<ModelChangeObject> modelChangeList = new ArrayList
	private List<ICContainer> containers = new UniqueEList<ICContainer>
	
	public static def addModelChange(ModelChangeObject change) {
		modelChangeList.add(change)
	}
	
	public static def clearModelChange() {
		modelChangeList.clear
	}

	new(ITranslationUnit unit, IProgressMonitor monitor, String langID) {
		this.unit = unit
		this.m_project = unit.CProject
		index = CCorePlugin.getIndexManager().getIndex(m_project)
		translationUnitToASTTranslationUnitMap = new HashMap<ITranslationUnit, IASTTranslationUnit>()
		excludedIncludesMap = new HashMap<String, List<IInclude>>()
		m_monitor = monitor
		this.langID = langID
	}

	new(ITranslationUnit unit, IProgressMonitor monitor) {
		this.unit = unit
		this.m_project = unit.CProject
		index = CCorePlugin.getIndexManager().getIndex(m_project)
		translationUnitToASTTranslationUnitMap = new HashMap<ITranslationUnit, IASTTranslationUnit>()
		excludedIncludesMap = new HashMap<String, List<IInclude>>()
		m_monitor = monitor
	}

	new(ICProject project, IProgressMonitor monitor, String langID) {
		this.m_project = project
		index = CCorePlugin.getIndexManager().getIndex(m_project)
		translationUnitToASTTranslationUnitMap = new HashMap<ITranslationUnit, IASTTranslationUnit>()
		excludedIncludesMap = new HashMap<String, List<IInclude>>()
		m_monitor = monitor
		this.langID = langID
	}

	new(ICProject project, IProgressMonitor monitor) {
		this.m_project = project
		index = CCorePlugin.getIndexManager().getIndex(m_project)
		translationUnitToASTTranslationUnitMap = new HashMap<ITranslationUnit, IASTTranslationUnit>()
		excludedIncludesMap = new HashMap<String, List<IInclude>>()
		m_monitor = monitor
	}
	
	new(ICProject project, IProgressMonitor monitor, String langID, String path) {
		this.m_project = project
		index = CCorePlugin.getIndexManager().getIndex(m_project)
		translationUnitToASTTranslationUnitMap = new HashMap<ITranslationUnit, IASTTranslationUnit>()
		excludedIncludesMap = new HashMap<String, List<IInclude>>()
		m_monitor = monitor
		this.langID = langID
		this.path = path
	}

	public def reverse() {
		var projectName = unit.CProject.elementName
		var iSync = new BatchReverseFunctionBody(unit, projectName, this)
		iSync.run
	}

	private def boolean isSourceFolder(ICContainer container) {
		for (child : container.children) {
			if (child instanceof ITranslationUnit) {
				return true
			} else if (child instanceof ICContainer) {
				if ((child as ICContainer).sourceFolder) {
					return true
				}
			}
		}
	}

	private static String projectPrefix = "org.eclipse.papyrus.cppgen"
	public def reverseProject(boolean reset) {
		var sourceRoots = m_project.sourceRoots.filter[it.sourceFolder]
		if (sourceRoots.size < 1) {
			throw new Exception("No source folder")
		}
		
		containers.clear
		var sourceRootGenerated = sourceRoots.filter[it.elementName.contains(projectPrefix)].head
		if (sourceRootGenerated != null) {
			containers.addAll(sourceRootGenerated.children.filter(typeof(ICContainer)).filter[it.sourceFolder])
		} else {
			containers.addAll(sourceRoots)
		}

		val modelManager = new ModelManagement
		var String umlFilePath
		if (path == null || path.equals("")) {
			umlFilePath = modelManager.getPath(
				m_project.project,
				REVERSE_FOLDER,
				m_project.elementName + MODEL_POSTFIX
			)
		} else {
			umlFilePath = path
		}
		
		modelManager.createOrgetModel(m_project.elementName, umlFilePath, !reset, reset)
		
		m_models = modelManager.models
		m_models.forEach [
			RoundtripCppUtils.applyProfile(it, RoundtripCppUtils.cppProfileUri)
			RoundtripCppUtils.applyProfile(it, RoundtripCppUtils.umlStandardProfileUri)
		]

		containers.forEach[
			reverseProject(it)
		]
		
		modelManager.saveModel(Collections.singleton(umlFilePath).toList);
		timestamp = m_models.last.eResource.timeStamp/1000
		clearRawChangeList
		modelManager.dispose
	}

	public def syncIncrementalProject() {
		reverseMode = ReverseMode.INCREMENTAL
		var sourceRoots = m_project.sourceRoots.filter[it.sourceFolder]
		if (sourceRoots.size < 1) {
			throw new Exception("No source folder")
		}
		
		containers.clear
		var sourceRootGenerated = sourceRoots.filter[it.elementName.contains(projectPrefix)].head
		if (sourceRootGenerated != null) {
			containers.addAll(sourceRootGenerated.children.filter(typeof(ICContainer)).filter[it.sourceFolder])
		} else {
			containers.addAll(sourceRoots)
		}

		val modelManager = new ModelManagement
		
		var String umlFilePath
		if (path == null || path.equals("")) {
			umlFilePath = modelManager.getPath(
				m_project.project,
				REVERSE_FOLDER,
				m_project.elementName + MODEL_POSTFIX
			)
			
		} else {
			umlFilePath = path
		}
		
		modelManager.createOrgetModel(m_project.elementName, umlFilePath, false, false)
		
		m_models = modelManager.models
		m_models.forEach [
			RoundtripCppUtils.applyProfile(it, RoundtripCppUtils.cppProfileUri)
			RoundtripCppUtils.applyProfile(it, RoundtripCppUtils.umlStandardProfileUri)
		]

		if (rawChangeList == null || rawChangeList.size == 0) {
			var fileWatcher = new FileWatcher(m_project, timestamp)
			var modifiedItus = fileWatcher.getModifiledTranslationUnits(m_project)
			for(modified:modifiedItus) {
				modified.syncTranslationUnit
			}
			modelManager.saveModel(Collections.singleton(umlFilePath).toList);
			timestamp = m_models.last.eResource.timeStamp/1000
		} else {
			reverseIncrementalChanges
			modelManager.saveModel(Collections.singleton(umlFilePath).toList);
		}
		
		modelManager.dispose
		
		/*CommandSupport.executeCmd(modelManager.resourceSet, new Runnable() {
			
			override run() {
				m_models = modelManager.models
				// m_model.name = containers.head.elementName
				m_models.forEach [
					RoundtripCppUtils.applyProfile(it, RoundtripCppUtils.cppProfileUri)
				]
		
				if (rawChangeList == null || rawChangeList.size == 0) {
					var fileWatcher = new FileWatcher(m_project, timestamp)
					var modifiedItus = fileWatcher.getModifiledTranslationUnits(m_project)
					for(modified:modifiedItus) {
						modified.syncTranslationUnit
					}
					modelManager.saveModel(paths);
					timestamp = m_models.last.eResource.timeStamp/1000
				} else {
					reverseIncrementalChanges
					modelManager.saveModel(paths);
				}
				
				modelManager.dispose
			}			
		})*/
		
	}

	private def void reverseProject(IParent parent) {
		if (!(parent instanceof ITranslationUnit)) {
			parent.children.filter(typeof(ITranslationUnit)).forEach [
				m_monitor.subTask("Parsing types in " + it.elementName)
				it.reverseHeader
			]
			parent.children.filter(typeof(IParent)).forEach[reverseProject(it)]

			parent.children.filter(typeof(ITranslationUnit)).filter[!it.isHeaderUnit].forEach [	
				m_monitor.subTask("Parsing method implementations in " + it.elementName)
				it.reverseSource
			]
		}
	}

	private def Model getCorrespondingModel(ITranslationUnit unit) {
		var Model ret = null
		for (model : m_models) {
			if (unit.path.toString.contains(model.name)) {
				ret = model
			}
		}
		return ret
	}

	private def reverseSource(ITranslationUnit unit) {
		//getOrCreateClassifier(unit.correspondingModel, unit)
		var sync = new BatchReverseFunctionBody(unit, m_project.elementName, unit.correspondingModel, this)
		try {
			sync.run
		} catch (Exception e) {
			e.printStackTrace;
		}
	}

	private def reverseHeader(ITranslationUnit headerUnit) {
		getOrCreateClassifier(headerUnit.correspondingModel, headerUnit)
	}

	private def List<Type> getOrCreateClassifier(Model model, IParent parent) throws Exception {
		var List<Type> ret = new UniqueEList<Type>
		var ICElement[] childrend = parent.getChildren();
		for (var i = 0; i < childrend.length; i++) {
			var child = childrend.get(i)
			switch (child) {
				case child instanceof IStructure,
				case child instanceof IEnumeration: {
					if (!(child as IDeclaration).elementName.equals("")) {
						ret.addAll(getOrcreateTypes(child as IDeclaration, parent, model))	
					}
				}
				case child instanceof IMethod: {
					//println("getOrCreateClassifier: child is instance of IMethod")
					if ((child as IMethod).translationUnit.isHeaderUnit && !(child.parent instanceof IStructure)) {
						val IMethod method = child as IMethod
						
						var List<Classifier> types = getTypeByQualifiedName(method, method.translationUnit, method.contextNamespaces)
						
						if (types.size > 0) {
							val Classifier type = types.get(0)
							var batchReverser = new BatchReverseFunctionBody(method.translationUnit, method.CProject.elementName, this, type)
							
							// function declaration is a superclass for method declaration
							// (but need to trace functions differently?)
							var String name = method.elementName
							var IASTNode node = method.findEnclosingNode
							if (node instanceof IASTFunctionDefinition) {
								var IASTFunctionDefinition definition = node as IASTFunctionDefinition
								var IASTFunctionDeclarator declarator = definition.getDeclarator();
								var String body = getBody(method);
								
								var Operation operation = batchReverser.updateMethod(method, node, 0, parent, name, body, declarator)
								if (operation != null) {
									new DependencyAnalysis(operation, definition, method.translationUnit, this).analyzeDependencies()
								}
							}
						}
					}
				}
				case child instanceof IFunction: {
					//println("getOrCreateClassifier: child is instance of IFunction")
//					var func = child as IFunction
//					var parentPack = func.translationUnit.containerPackage
//					var Class classifier = null
//					if (parentPack.getOwnedMember(func.translationUnit.elementName) == null) {
//						parentPack.createOwnedClass(func.translationUnit.elementName, false)
//					}
//					classifier = parentPack.getOwnedMember(func.translationUnit.elementName) as Class
//					classifier.createOwnedOperation(func.elementName, null, null)
				}
				case child instanceof IParent: {
					ret.addAll(getOrCreateClassifier(model, (child as IParent)))
				}
				case child instanceof ITypeDef: {
					var parentPack = getContainerPackage((child as ITypeDef).translationUnit)
					var Classifier tempType
					if (child.parent instanceof IStructure) {
						var parentClass = getUMLType(child.parent.elementName, (child as ITypeDef).translationUnit,
							child.contextNamespaces) as Classifier
						if (parentClass.getOwnedMember(child.elementName) == null) {
							createOrgetClassifier(parentClass, child, true)
						}
						tempType = parentClass.getOwnedMember(child.elementName) as Classifier
					} else {
						if (parentPack.getOwnedType(child.elementName) == null) {
							createOrgetClassifier(parentPack, child, true)
						}
						tempType = parentPack.getOwnedMember(child.elementName) as Classifier
					}
					ret.add(tempType)
				}
			}
		}

		return ret;

	}

	private def getOrcreateTypes(IDeclaration iStructure, IParent parent, Model model) {
		var List<Type> ret = new UniqueEList<Type>
		m_monitor.subTask("Parsing type " + iStructure.elementName)
		var Classifier tempType = null
		var IDeclaration istructure = iStructure as IDeclaration
		// istructure.elementType
		var elementName = istructure.elementName
		var parentPack = getContainerPackage(istructure.translationUnit)
		var Classifier parentClass
		if (istructure.parent instanceof IStructure) {
			parentClass = getUMLType(istructure.parent.elementName, istructure.translationUnit,
				istructure.contextNamespaces) as Classifier
			if (parentClass.getOwnedMember(elementName) == null) {
				createOrgetClassifier(parentClass, istructure, true)
			}
			tempType = parentClass.getOwnedMember(elementName) as Classifier
		} else {
			if (parentPack.getOwnedType(elementName) == null) {
				createOrgetClassifier(parentPack, istructure, true)
			}
			tempType = parentPack.getOwnedMember(elementName) as Classifier
		}

		if (tempType != null) {
			ret.add(tempType)
			if (istructure instanceof IParent) {
				var List<ICElement> nestedStructures = new UniqueEList
				nestedStructures.addAll(istructure.children.filter(typeof(IDeclaration)))
				nestedStructures.addAll(istructure.children.filter(typeof(ITypeDef)))
				for (i : 0..< nestedStructures.size) {
					var ns = nestedStructures.get(i)
					if (!ns.elementName.trim.empty) { // TODO anonymous struct?
						var t = createOrgetClassifier(tempType, ns, true) as Type
						if (t != null) {
							ret.add(t)
						}
					} else if (ns instanceof IEnumeration) {
						var isAnonEnumInstance = false
						
						try {
							if (i+1 < nestedStructures.size) {
								var nextNs = nestedStructures.get(i+1)
								if (nextNs instanceof IField) {
									var nsRaw = ns.findEnclosingNode.rawSignature
									var nextNsRaw = nextNs.findEnclosingNode.rawSignature
									
									if (nextNsRaw.contains(nsRaw)) {
										isAnonEnumInstance = true
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace
						}
						
						if (!isAnonEnumInstance) {
							var t = createOrgetClassifier(tempType, ns, true) as Type
							if (t != null) {
								ret.add(t)
							}
						}
					}
				}
			}
		}

		if (istructure instanceof IStructureTemplate) {
			var istructureTemplate = istructure as IStructureTemplate
			for (var ti = 0; ti < istructureTemplate.templateParameterTypes.size; ti++) {
				tempType.createOrgetTemplateParameter(istructureTemplate.templateParameterTypes.get(ti), "class")

			}
		}
		
		// Includes handling
		if (ret.size > 0) {
			if (!(tempType instanceof Enumeration) && !(tempType instanceof PrimitiveType)) {
				// Get the ITranslationUnit that contains the temporally created type
				var structureItu = istructure.translationUnit
				
				// If no file is found previously, we set it to parent by default
				var file = parent;
				var List<IInclude> excludedIncludes = null
				if (structureItu instanceof IParent) {
					file = structureItu as IParent
					val String key = getExcludedIncludesMapKey(structureItu, tempType)
					excludedIncludes = excludedIncludesMap.get(key)
				}
				
				if (!StereotypeUtil.isApplied(tempType, Include)) {
					StereotypeUtil.apply(tempType, Include)
				}
				
				if (UMLUtil.getStereotypeApplication(tempType, Include) != null) {
					var header = UMLUtil.getStereotypeApplication(tempType, Include).header
					var escape = ""
					
					for (j : file.children) {
						if (j instanceof IInclude) {
							var excluded = false
							if (excludedIncludes != null) {
								excluded = excludedIncludes.contains(j)
							}
							
							if (!excluded && !header.contains(j.elementName)) {
								if (j.elementName.contains("Pkg_")) {
									try  {
										var includeTokens = j.elementName.split("/")
										var String includeTrimmed = ""
										for (i : 0 ..< includeTokens.length - 1) {
											includeTrimmed += includeTokens.get(i)
										}
										
										var typeQualifiedNameTokens = tempType.qualifiedName.split("::")
										var String typeQualifiedNameTrimmed = ""
										for (i : 1 ..< typeQualifiedNameTokens.length - 1) {
											typeQualifiedNameTrimmed += typeQualifiedNameTokens.get(i)
										}
										
										if (!includeTrimmed.equals(typeQualifiedNameTrimmed)) {
											header = header + escape + "#include " + "\"" + j.elementName + "\""
											escape = "\n"
										}
									} catch (Exception e) {
										e.printStackTrace
										header = header + escape + "#include " + "\"" + j.elementName + "\""
										escape = "\n"
									}
								} else {
									header = header + escape + "#include " + "\"" + j.elementName + "\""
									escape = "\n"
								}
								
							}
							
							
						} else if (j instanceof IMacro) {
							if (!header.contains(j.elementName)) {
								var IASTNode node = j.findEnclosingNode
								if (node != null) {
									val nodeString = node.toString
									var value = nodeString.substring(nodeString.indexOf("=") + 1)
									if (value == null || value.equals("")) {
										if (!j.elementName.contains(tempType.name.replaceAll(" ", "").toUpperCase)) {
											header = header + escape + "#define " + j.elementName + " " + value
											escape = "\n"
										}
									} else {
										header = header + escape + "#define " + j.elementName + " " + value
										escape = "\n"
									}
									
								}
							}
						}
					}
					UMLUtil.getStereotypeApplication(tempType, Include).header = header
					
					// Body and pre-body
					// The strategy is to find a file in the same folder with the same name by with cpp extension
					// It's very specific to generated code, but so is how body and pre-body handled (based on generated comments)
					if (file instanceof ITranslationUnit) {
						try {
							var IPath sourcePath = file.path.removeFileExtension.addFileExtension("cpp")
							var ICElement sourceFile = (file as ITranslationUnit).CProject.findElement(sourcePath)
							if (sourceFile instanceof ITranslationUnit) {
								BatchReverseFunctionBody.updateCppInclude(sourceFile, tempType)
							}
						} catch (Exception e) {
							// Fail silently
						}
					}
				}
			}
		}

		return ret
	}

	private def checkICElementInModelChanges(ICElement icElement) {
		
	}

	private def syncTranslationUnit(ITranslationUnit itu) {
		//check if a structure is created/deleted/renamed
		var structures = reverse_utils.getAllIStructures(itu, false, true, m_project).filter(typeof(IStructure))
		for(structure:structures) {
			var classifier = getClassifier(itu.correspondingModel, structure, structure.elementName, itu)
			if (classifier instanceof Classifier) {
				syncIStructureToModel(classifier, structure)
			}
		}		
	}
	
	private def syncIStructureToModel(Classifier classifier, IStructure istructure) {
		var fields = istructure.children.filter(typeof(IField)).toList
		var attributes = classifier.ownedElements.filter(typeof(Property)).toList
		mergeAttributes(classifier, attributes, fields)
		var methods = istructure.children.filter(typeof(IMethodDeclaration)).toList
		var operations = classifier.ownedElements.filter(typeof(Operation)).toList
		mergeOperations(classifier, operations, methods)
		var declarations = istructure.children.filter(typeof(IDeclaration)).toList
		var nestedTypes = classifier.ownedElements.filter(typeof(Type)).toList
		mergeNestedTypes(classifier, nestedTypes, declarations)
	}
	
	private def mergeAttributes(Classifier parent, List<Property> attributes, List<IField> fields) {
		var foundFieldList = new UniqueEList<IField>
		var notFoundFieldList = new UniqueEList<IField>
		var foundAttrList = new UniqueEList<Property> 
		var notFoundAttrList = new UniqueEList<Property> 
		for(field:fields) {
			val attrFound = attributes.filter[it.name.equals(field.elementName)].head
			if (attrFound == null) {
				notFoundFieldList.add(field)
			} else {
				foundAttrList.add(attrFound)
				foundFieldList.add(field)
				var isModelObjectChanged = (modelChangeList.filter[it.eObject == attrFound].head != null)
				if (isModelObjectChanged) {
					syncAttributeWithMode(parent, attrFound, field, conflictResolveMode)
				} else {
					syncAttributeWithMode(parent, attrFound, field, ConflictResolutionMode.FROM_CODE)
				}
			}
		}
		
		for(attr:attributes) {
			if (!foundAttrList.contains(attr)) {
				notFoundAttrList.add(attr)
			}
		}
		
		var addChanges = modelChangeList.filter[it.eventType == Notification.ADD].toList
		var tobeRemovedsInNotFound = new UniqueEList<Property>
		for(attr:notFoundAttrList) {
			var modelChangeObj = addChanges.filter[it.eObject == attr].head
			if (modelChangeObj != null) {
				tobeRemovedsInNotFound.add(attr)
			} else {
				
			}
		}
		
		for(i:tobeRemovedsInNotFound) {
			notFoundAttrList.remove(i)
		}
		
		var processedFields = new UniqueEList<IField>
		var remainingFields = new UniqueEList<IField>
		var remainingAttributes = new UniqueEList<Property>
		for(notFoundField:notFoundFieldList) {
			if (!processedFields.contains(notFoundField)) {
				val umlType = notFoundField.getUMLType(notFoundField.contextNamespaces)
				val typeName = notFoundField.typeName.cppTypeName
				var sameTypeNameFields = notFoundFieldList.filter[it.typeName.cppTypeName.equals(typeName)].toList
				var sameTypeAttrs = notFoundAttrList.filter[it.type.name.equals(umlType.name)].toList
				var i = 0
				for(i = 0; i < sameTypeNameFields.size; i++) {
					if (i >= sameTypeAttrs.size) {
						remainingFields.add(sameTypeNameFields.get(i))
					} else {
						val tobeProcessedField = sameTypeNameFields.get(i)
						val tobeProcessedAttr = sameTypeAttrs.get(i)
						var isModelObjectChanged = (modelChangeList.filter[it.eObject == tobeProcessedAttr].head != null)
						if (isModelObjectChanged) {
							syncAttributeWithMode(parent, tobeProcessedAttr, tobeProcessedField, ConflictResolutionMode.FROM_MODEL)
						} else {
							syncAttributeWithMode(parent, tobeProcessedAttr, tobeProcessedField, ConflictResolutionMode.FROM_CODE)
						}
						processedFields.add(tobeProcessedField)
						notFoundAttrList.remove(tobeProcessedAttr)
					}
				}
				if (i < sameTypeAttrs.length) {
					for(var j = i; j < sameTypeAttrs.length; j++) {
						remainingAttributes.add(sameTypeAttrs.get(j))
					}
				}
			}
		}
		
		for(notFoundAttr:notFoundAttrList) {
			if (!remainingAttributes.contains(notFoundAttr)) {
				remainingAttributes.add(notFoundAttr)
			}
		}
		
		
		for(remaining:remainingFields) {
			createProperty(remaining, parent)
		}
		
		var remainingChanges = modelChangeList.filter[it.eventType == Notification.ADD || it.eventType == Notification.SET].toList
		for(remaining:remainingAttributes) {
			if (remainingChanges.filter[it.eObject == remaining].empty) {
				if (parent instanceof Class) {
					(parent as Class).ownedAttributes -= remaining
				} else if (parent instanceof DataType) {
					(parent as DataType).ownedAttributes -= remaining
				}
			}
		}		
	}
	
	private def syncAttributeWithMode(Classifier parent, Property attribute, IField field, ConflictResolutionMode resolveMode) {
		switch(resolveMode) {
			case FROM_MODEL: {
				//TODO: sync from model to code by regenerate the file after sync from code to model
			}
			case FROM_CODE: {
				var type = field.getUMLType(field.contextNamespaces)
				attribute.type = type
				attribute.name = field.elementName
				updateProperty(field, attribute)
				var changeObj = modelChangeList.filter[it.eObject == attribute].head
				if (changeObj != null) {
					modelChangeList.remove(changeObj)
				}
			}
			case UI_INTERACTION: {
				//TODO: display UI
			}
			
		}
	}
	
	private def syncOperationWithMode(Classifier parent, Operation op, IMethodDeclaration method, ConflictResolutionMode resolveMode) {
		switch(resolveMode) {
			case FROM_MODEL: {
				//TODO: sync from model to code by regenerate the file after sync from code to model
			}
			case FROM_CODE: {
				op.name = method.elementName
				updateMethod(parent as Class, op, method)
				var changeObj = modelChangeList.filter[it.eObject == op].head
				if (changeObj != null) {
					modelChangeList.remove(changeObj)
				}
			}
			case UI_INTERACTION: {
				//TODO: display UI
			}
			
		}
	}
	
	private def isSameOperation(Operation op, IMethodDeclaration method) {
		var ret = true
		if (!op.name.equals(method.elementName)) {
			ret = false
		} else {
			ret = isSameSignature(op, method)
		}
		return ret
	}
	
	private def isSameSignature(Operation op, IMethodDeclaration method) {
		var ret = true
		var params = op.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL].toList
		if (method.numberOfParameters != params.size) {
			ret = false
		} else {
			for(var i = 0; i < method.numberOfParameters; i++) {
				if (!method.parameterTypes.get(i).cppTypeName.equals(params.get(i).type.name)) {
					ret = false
				}
			}
			if (ret != false) {
				var returnParam = op.ownedParameters.filter[it.direction == ParameterDirectionKind.RETURN_LITERAL].head
				if (returnParam == null) {
					if (!method.returnType.cppTypeName.equals("void")) {
						ret= false
					}
				} else if (returnParam.type != null && !method.returnType.cppTypeName.equals(returnParam.type.name)) {
					ret = false
				} 
			}
		}
		
		return ret
	}
	
	private def isSameMethodDeclaration(IMethodDeclaration method1, IMethodDeclaration method2) {
		var result = true;
		
		result = result && method1.signature.equals(method2.signature);
		result = result && (method1.visibility.convertVisibility == method2.visibility.convertVisibility)
		result = result && (method1.isStatic == method2.isStatic)
		result = result && (method1.isVirtual == method2.isVirtual)
		result = result && (method1.virtual == method2.virtual)
		result = result && (method1.inline == method2.inline)
		result = result && (method1.friend == method2.friend)
		result = result && (method1.volatile == method2.volatile)
		result = result && (method1.constructor == method2.constructor)
		result = result && (method1.destructor == method2.destructor)
		result = result && (method1.returnType != null && method2.returnType != null) 
		if (method1.returnType != null && method2.returnType != null) {
			result = result && method1.returnType.equals(method2.returnType)
		}
		result = result && (method1.numberOfParameters == method2.numberOfParameters)
		
		if (result == false) {
			return result
		}
		
		try {
			var declarator1 = method1.declarator
			var declarator2 = method2.declarator
			
			result = result && (declarator1.rawSignature.equals(declarator2.rawSignature))
		} catch (Exception e) {
			e.printStackTrace
			return result
		}
		
		return result
	}
	
	private def getNumberOfSameParameters(Operation op, IMethodDeclaration method) {
		var ret = 0
		var params = op.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL].toList
		for(var i = 0; i < method.numberOfParameters; i++) {
			if (i <= params.size) {
				if (params.get(i).type != null && method.parameterTypes.get(i).cppTypeName.equals(params.get(i).type.name)) {
					ret++
				}
			}
		}
		return ret
	}
	
	private def findMostSameOperation(List<Operation> ops, IMethodDeclaration method) {
		if (ops.empty) return {null}
		var ret = ops.head
		for(op:ops) {
			if (ret != op) {
				if (op.getNumberOfSameParameters(method) > ret.getNumberOfSameParameters(method)) {
					ret = op
				}
			}
		}
		return ret
	}
	
	private def mergeOperations(Classifier parent, List<Operation> operations, List<IMethodDeclaration> methods) {
		var foundMethodList = new UniqueEList<IMethodDeclaration>
		var notFoundMethodList = new UniqueEList<IMethodDeclaration>
		var foundOperationList = new UniqueEList<Operation> 
		var notFoundOperationList = new UniqueEList<Operation> 
		for(method:methods) {
			val opFound = operations.filter[it.isSameOperation(method)].head
			if (opFound == null) {
				notFoundMethodList.add(method)
			} else {
				foundOperationList.add(opFound)
				foundMethodList.add(method)
				var isModelObjectChanged = (modelChangeList.filter[it.eObject == opFound].head != null)
				if (isModelObjectChanged) {
					//syncAttributeWithMode(parent, attrFound, field, conflictResolveMode)
				} else {
					//syncAttributeWithMode(parent, attrFound, field, ConflictResolutionMode.FROM_CODE)
				}
			}
		}
		
		for(op:operations) {
			if (!foundOperationList.contains(op)) {
				notFoundOperationList.add(op)
			}
		}
		
		var addChanges = modelChangeList.filter[it.eventType == Notification.ADD].toList
		var tobeRemovedsInNotFound = new UniqueEList<Operation>
		for(op:notFoundOperationList) {
			var modelChangeObj = addChanges.filter[it.eObject == op].head
			if (modelChangeObj != null) {
				tobeRemovedsInNotFound.add(op)
			} else {
				
			}
		}
		
		for(i:tobeRemovedsInNotFound) {
			notFoundOperationList.remove(i)
		}
		
		var processedMethods = new UniqueEList<IMethodDeclaration>
		var remainingMethods = new UniqueEList<IMethodDeclaration>
		var remainingOperations = new UniqueEList<Operation>
		for(notFoundMethod:notFoundMethodList) {
			if (!processedMethods.contains(notFoundMethod)) {
				val functionName = notFoundMethod.elementName.split("::").last
				val sameSignatureOperation = notFoundOperationList.filter[it.isSameSignature(notFoundMethod)].head
				if (sameSignatureOperation != null) {
					var isModelObjectChanged = (modelChangeList.filter[it.eObject == sameSignatureOperation].head != null) {
						if (isModelObjectChanged) {
							syncOperationWithMode(parent, sameSignatureOperation, notFoundMethod, ConflictResolutionMode.FROM_MODEL)
						} else {
							syncOperationWithMode(parent, sameSignatureOperation, notFoundMethod, ConflictResolutionMode.FROM_CODE)
						}
					}
					notFoundOperationList.remove(sameSignatureOperation)
					processedMethods.add(notFoundMethod)
				} else {
					//look for function with same name
					val sameNameMethods = notFoundMethodList.filter[it.elementName.equals(functionName)].toList
					val sameNameOperations = notFoundOperationList.filter[it.name.equals(functionName)].toList
					if (sameNameOperations.size > 0) {
						var i = 0
						for(i = 0; i < sameNameMethods.size; i++) {
							if (i >= sameNameOperations.size) {
								remainingMethods.add(sameNameMethods.get(i))
							} else {
								val tobeProcessedMethod = sameNameMethods.get(i)
								val tobeProcessedOp = sameNameOperations.get(i)
								var isModelObjectChanged = (modelChangeList.filter[it.eObject == tobeProcessedOp].head != null)
								if (isModelObjectChanged) {
									syncOperationWithMode(parent, tobeProcessedOp, tobeProcessedMethod, ConflictResolutionMode.FROM_MODEL)
								} else {
									syncOperationWithMode(parent, tobeProcessedOp, tobeProcessedMethod, ConflictResolutionMode.FROM_CODE)
								}
								processedMethods.add(tobeProcessedMethod)
								notFoundOperationList.remove(tobeProcessedOp)
							}
						}
						if (i < sameNameOperations.length) {
							for(var j = i; j < sameNameOperations.length; j++) {
								remainingOperations.add(sameNameOperations.get(j))
							}
						}
					} else {
						val mostSameOp = findMostSameOperation(notFoundOperationList, notFoundMethod)
						if (mostSameOp != null) {
							var isModelObjectChanged = (modelChangeList.filter[it.eObject == mostSameOp].head != null)
							if (isModelObjectChanged) {
								syncOperationWithMode(parent, mostSameOp, notFoundMethod, ConflictResolutionMode.FROM_MODEL)
							} else {
								syncOperationWithMode(parent, mostSameOp, notFoundMethod, ConflictResolutionMode.FROM_CODE)
							}
							processedMethods.add(notFoundMethod)
							notFoundOperationList.remove(mostSameOp)
						} 
					}
					
						
				}			
				
			}
		}
		
		for(notFoundOp:notFoundOperationList) {
			if (!remainingOperations.contains(notFoundOp)) {
				remainingOperations.add(notFoundOp)
			}
		}
		
		
		for(remaining:remainingMethods) {
			createMethod(remaining, parent as Class)
		}
		
		var remainingChanges = modelChangeList.filter[it.eventType == Notification.ADD || it.eventType == Notification.SET].toList
		for(remaining:remainingOperations) {
			if (remainingChanges.filter[it.eObject == remaining].empty) {
				if (parent instanceof Class) {
					(parent as Class).ownedOperations -= remaining
				} else if (parent instanceof DataType) {
					(parent as DataType).ownedOperations -= remaining
				}
			}
		}	
	}
	
	private def mergeNestedTypes(Classifier parent, List<Type> nestedTypes, List<IDeclaration> declarations) {
		
	}

	private def reverseIncrementalChanges() {
		changeList.clear
		optimizeChangeList

		for(change:changeList) {
			switch(change.changeKind) {
				case IResourceDelta.ADDED: {
					addToModel(change)
				}
				
				case IResourceDelta.CHANGED: {
					updateToModel(change)
				}
				
				case IResourceDelta.REMOVED: {
					removeFromModel(change)
				}
			}
		}
		changeList.clear
	}
	
	private def getRawChangeList() {
		if (ChangeMapStore.changesMap != null && ChangeMapStore.changesMap.get(m_project.elementName) != null &&
			 ChangeMapStore.changesMap.get(m_project.elementName).size > 0
		) {
			return ChangeMapStore.changesMap.get(m_project.elementName)
		}
		return null;
		
	}
	
	private def clearRawChangeList() {
		if (ChangeMapStore.changesMap != null && m_project != null && ChangeMapStore.changesMap.get(m_project.elementName) != null) {
			ChangeMapStore.changesMap.get(m_project.elementName).clear
		}
	}
	
	private def optimizeChangeList() {
		var rawChangeList = getRawChangeList
		changeList.clear
		var List<CElementChange> processedList = new UniqueEList
		var List<CElementChange> remainList = new UniqueEList
		
		//remove immediate events
		for(var i = 0; i < rawChangeList.size; i++) {
			var change = rawChangeList.get(i)
			
			if (isInSourceContainers(change.element)) {
				if (!processedList.contains(change)) {
					switch(change.changeKind) {
						case IResourceDelta.ADDED: {
							var inter = findIntermediateEvent(rawChangeList, i, change)
							if (inter.size > 0) {
								processedList.add(change)
								processedList.addAll(inter)
							}
						}
						
						case IResourceDelta.CHANGED: {
							var inter = findIntermediateEvent(rawChangeList, i, change)
							if (inter.size > 0) {
								processedList.addAll(inter)
							}
						}
						
						case IResourceDelta.REMOVED: {
							
						} 
						default: {
							processedList.add(change)
						}
					}
				}
				
				if (!processedList.contains(change)) {
					remainList.add(change)
				}
			}
		}
		
		val Map<ICElement, List<CppChangeObject>> doubtChangeListMap = new HashMap
		
		for(var i = 0; i < remainList.size; i++) {
			var change = remainList.get(i)
			var CppChangeObject cppChangeObject = null
			switch(change.changeKind) {
				case IResourceDelta.ADDED: {
					var found = false
					var j = i + 1
					
					while (j < remainList.size && !found) {
						// TODO BUG: ADD(3a, 4a, 5a) then REMOVE(5, 3, 4), the first element of same type as 3a is 5 so we create a change object where 3a is the newElement and 5 is the oldElement although it's wrong
						// There is no way to know if 3a corresponds exactly to 3
						if (remainList.get(j).changeKind == IResourceDelta.REMOVED && change.element.elementType == remainList.get(j).element.elementType
							&& remainList.get(j).parent == change.parent
						) {
							cppChangeObject = new CppChangeObject(remainList.get(j).element, change.element, reverse_utils.getTranslationUnitFromElement(change.element), change.parent, IResourceDelta.CHANGED)
							if (doubtChangeListMap.get(change.parent) == null) {
								doubtChangeListMap.put(change.parent, new ArrayList)
							}
							if (change.element instanceof IField || change.element instanceof IMethodDeclaration
								|| change.element instanceof IEnumerator
							) {
								doubtChangeListMap.get(change.parent).add(cppChangeObject)
							}
							
							remainList.remove(j)
							j--
							
							found = true
						}
						
						j++
					}
					
					if (!found) {
						cppChangeObject = new CppChangeObject(null, change.element, reverse_utils.getTranslationUnitFromElement(change.element), change.parent, IResourceDelta.ADDED)
					}
				}
					
				case IResourceDelta.CHANGED: {
					cppChangeObject = new CppChangeObject(remainList.get(i).element, change.element, reverse_utils.getTranslationUnitFromElement(change.element), change.parent, IResourceDelta.CHANGED)
				}
					
				case IResourceDelta.REMOVED: {
					var found = false
					var j = i + 1
					
					while (j < remainList.size && !found) {
						if (remainList.get(j).changeKind == IResourceDelta.ADDED && change.element.elementType == remainList.get(j).element.elementType) {
							cppChangeObject = new CppChangeObject(change.element, remainList.get(j).element, reverse_utils.getTranslationUnitFromElement(change.element), change.parent, IResourceDelta.CHANGED)
							if (doubtChangeListMap.get(change.parent) == null) {
								doubtChangeListMap.put(change.parent, new ArrayList)
							}
							if (change.element instanceof IField || change.element instanceof IMethodDeclaration
								|| change.element instanceof IEnumerator
							) {
								doubtChangeListMap.get(change.parent).add(cppChangeObject)
							}
							remainList.remove(j)
							j--

							found = true
						}
						
						j++
					}
					
					if (!found) {
						cppChangeObject = new CppChangeObject(null, change.element, reverse_utils.getTranslationUnitFromElement(change.element), change.parent, IResourceDelta.REMOVED)
					}
				} 
			}
			if (cppChangeObject != null) {
				changeList.add(cppChangeObject)
			}
		}
		
		for(doubtMap:doubtChangeListMap.entrySet) {
			if (doubtMap.value.size > 1) {
				alignChangedElements(doubtMap.key, doubtMap.value)
			}
		}
		
		clearRawChangeList
	}
	
	private def alignChangedElements(ICElement parent, List<CppChangeObject> changes) {
		//This method is used to exactly verify the removed element associated to the added element in the correspond cpp change object
		//this function only checks structure members (enumerators, fields and methods)
		//The verification is based on the relative position of structure members in the code to those in the model
		val oldNamedElementsList = new ArrayList<NamedElement>
		val Map<NamedElement, CppChangeObject> oldNamedElementToChangeMap = new HashMap
		changes.forEach[
			var oldNamedElement = it.parent.getNamedElement(it.oldElement)
			oldNamedElementToChangeMap.put(oldNamedElement, it)
			oldNamedElementsList.add(oldNamedElement)
		]
		val namedElementParent = oldNamedElementsList.head.eContainer
		val alignedOldNamedElements = new ArrayList<NamedElement>
		namedElementParent.eContents.filter(typeof(NamedElement)).forEach[
			if (oldNamedElementsList.contains(it)) {
				alignedOldNamedElements.add(it)
			}
		]
		val newICElements = new ArrayList<ICElement>
		if (parent instanceof IParent) {
			//get all new elements in changes list ordered as in its parent
			parent.children.forEach[
				val childInParent = it
				val founded = changes.filter[it.newElement == childInParent].head
				if (founded != null) {
					newICElements.add(founded.newElement)
				}
			]
		}
		
		if (alignedOldNamedElements.size == newICElements.size) {
			for(var i = 0; i < alignedOldNamedElements.size; i++) {
				var change = oldNamedElementToChangeMap.get(alignedOldNamedElements.get(i))
				if (change.newElement != newICElements.get(i)) {
					var sameElementType = change.newElement instanceof IField && newICElements.get(i) instanceof IField
					sameElementType = sameElementType || (change.newElement instanceof IMethodDeclaration && newICElements.get(i) instanceof IMethodDeclaration)
					sameElementType = sameElementType || (change.newElement instanceof IEnumerator && newICElements.get(i) instanceof IEnumerator)
					if (sameElementType) {
						change.newElement = newICElements.get(i)
					}
				}
			}
		}
		
	}
	
	private def getNamedElement(ICElement parent, ICElement child) {
		var NamedElement ret
		switch(child) {
			case child instanceof IField: {
				if (parent instanceof IStructure) {
					var parentStructure = parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)				
					if (classifier != null) {
						if (classifier.isExist(child.elementName)) {
							var prop = classifier.ownedElements.filter(typeof(Property)).filter[
								it.name.equals(child.elementName)
							].head
							ret = prop
						}						
					}
				}
			}
			
			case child instanceof IMethodDeclaration: {
				if (parent instanceof IStructure) { // Method declaration
					var parentStructure = parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)
					if (classifier != null) {
						if (classifier.isExist(child.elementName)) {
							var samenames = classifier.ownedElements.filter(typeof(Operation)).filter[it.name.equals(child.elementName)].toList							
							val oldMethod = child as IMethodDeclaration
							var op = samenames.filter[it.isSameSignature(oldMethod)].head
							if (op != null) {
								ret = op
							}
						}
					}
				} 
			}
			
			case child instanceof IEnumerator: {
				var cppEnumeration = parent as IEnumeration
				var enumeration = getClassifier(cppEnumeration.translationUnit.correspondingModel,
									cppEnumeration, cppEnumeration.elementName, cppEnumeration.translationUnit
				)
				
				if (enumeration!= null && enumeration instanceof Enumeration) {
					var enumerator = (enumeration as Enumeration).ownedLiterals
									.filter(typeof(EnumerationLiteral))
									.filter[it.name == child.elementName].head
					if (enumerator != null) {
						ret = enumerator
					}				
				} 				
			}
		}
		return ret
	}
	
	private def boolean isInSourceContainers(ICElement element) {
		if (element == null) {
			return false
		}
		
		if (element instanceof ICContainer && containers.contains(element)) {
			return true
		}
		
		return isInSourceContainers(element.parent)
	}
	
	private def findIntermediateEvent(List<CElementChange> list, int position, CElementChange change) {
		var List<CElementChange> ret = new UniqueEList
		for(var i = position + 1; i < list.size; i++) {
			if (change.changeKind == IResourceDelta.ADDED && list.get(i).changeKind == IResourceDelta.REMOVED && 
					change.element.isSameICElement(list.get(i).element)) {
				if (change.element instanceof IMethodDeclaration && list.get(i).element instanceof IMethodDeclaration) {
					val method = change.element as IMethodDeclaration
					if (isSameMethodDeclaration(method, list.get(i).element as IMethodDeclaration)) {
						ret.add(list.get(i))
					}
				} else {
					ret.add(list.get(i))
				}
			} else if (change.changeKind == IResourceDelta.CHANGED && change.element.isSameICElement(list.get(i).element)) {
				ret.add(list.get(i))
			}
		}
		var changed = ret.filter[it.changeKind == IResourceDelta.CHANGED].toList
		if (changed.size > 1) {
			ret.remove(changed.last)
		}
		
		return ret
	}
	
	private def getSameICelement(ICElement element, int fromPos, int changeKind) {
		if (fromPos >= rawChangeList.size) {
			return null
		}
		
		var ICElement ret = null
		for(var i = fromPos; i < rawChangeList.size; i++) {
			var change = rawChangeList.get(i)
			if (ret == null) {
				if (changeKind == IResourceDelta.ADDED) {
					if (change.changeKind == IResourceDelta.REMOVED && element.isSameICElement(change.element)) {
						ret = change.element
					}
				} else if (changeKind == IResourceDelta.REMOVED) {
					if (change.changeKind == IResourceDelta.ADDED && element.isSameICElement(change.element)) {
						ret = change.element
					}					
				} else if (changeKind == IResourceDelta.CHANGED) {
					if (change.changeKind == IResourceDelta.CHANGED && element.isSameICElement(change.element)) {
						ret = change.element
					}
				}
			}
			
		}
		
		return ret
	}
	
	private def isSameICElement(ICElement e1, ICElement e2) {
		var boolean ret = false
		if (e1.elementName.equals(e2.elementName) && e1.elementType == e2.elementType && e1.parent.elementName.equals(e2.parent.elementName)) {
			ret = true
		}
		return ret
	} 
	
	private def isExist(NamedElement parent, String childName) {
		return !parent.ownedElements.filter(typeof(NamedElement)).filter[it.name.equals(childName)].empty
	}
	
	private def addToModel(CppChangeObject change) {
		switch(change.newElement) {
			case change.newElement instanceof IField: {
				val field = change.newElement as IField
				if (field.parent instanceof IStructure) {
					var parentStructure = field.parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)				
					if (classifier != null) {
						if (!classifier.isExist(field.elementName)) {
							createProperty(field, classifier as Classifier)
						}						
					}
				}
			}
			
			case change.newElement instanceof IMethodDeclaration: {
				val method = change.newElement as IMethodDeclaration
				if (method.parent instanceof IStructure) {
					var parentStructure = method.parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)
					if (classifier != null) {
						var samenames = classifier.ownedElements.filter(typeof(Operation)).filter[it.name.equals(method.elementName)].toList
						if (samenames.filter[it.isSameSignature(method)].empty) {
							createMethod(method, classifier as Class)
						}
						
					}
				} else if (method.parent instanceof ITranslationUnit) {
					// TODO a single function declaration (or definition) in a cpp file ==> possible strategy: add as a method of a class? which class?
				}
			}
			
			case change.newElement instanceof IStructure,			
			case change.newElement instanceof IEnumeration,			
			case change.newElement instanceof ITypeDef: {
				var declaration = change.newElement as IDeclaration
				if (declaration.parent instanceof IStructure) {
					var parentStructure = declaration.parent as IStructure
					var parentClassifier = getClassifier(parentStructure.translationUnit.correspondingModel, 
											parentStructure, parentStructure.elementName, parentStructure.translationUnit
										)
					createOrgetClassifier(parentClassifier, declaration, true)					
				} else {
					var containerPackage = declaration.translationUnit.containerPackage
					createOrgetClassifier(containerPackage, declaration, true)
				}
			}
			
			case change.newElement instanceof IEnumerator: {
				var enumerator = change.newElement as IEnumerator
				var cppEnumeration = enumerator.path as IEnumeration
				var enumeration = getClassifier(cppEnumeration.translationUnit.correspondingModel,
									cppEnumeration, cppEnumeration.elementName, cppEnumeration.translationUnit
				)
				
				if (enumeration instanceof Enumeration) {
					enumeration.createOwnedLiteral(enumerator.elementName)
				}				
			}
			
			case change.newElement instanceof ITranslationUnit: {
				var itu = change.newElement as ITranslationUnit
				getOrCreateClassifier(itu.correspondingModel, itu)
			}
		}
		
	}
	
	private def updateToModel(CppChangeObject change) {
		switch(change.newElement) {
			case change.oldElement instanceof IField: {
				val field = change.newElement as IField
				if (field.parent instanceof IStructure) {
					var parentStructure = field.parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)				
					if (classifier != null) {
						if (classifier.isExist(change.oldElement.elementName)) {
							var prop = classifier.ownedElements.filter(typeof(Property)).filter[
								it.name.equals(change.oldElement.elementName)
							].head
							if (prop != null) {
								updateProperty(change.newElement as IField, prop)
							}
						}						
					}
				}
			}
			
			case change.oldElement instanceof IMethodDeclaration: {
				val method = change.newElement as IMethodDeclaration
				if (method.parent instanceof IStructure) { // Method declaration
					var parentStructure = method.parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)
					if (classifier != null) {
						if (classifier.isExist(change.oldElement.elementName)) {
							var samenames = classifier.ownedElements.filter(typeof(Operation)).filter[it.name.equals(change.oldElement.elementName)].toList							
							val oldMethod = change.oldElement as IMethodDeclaration
							var op = samenames.filter[it.isSameSignature(oldMethod)].head
							if (op != null) {
								updateMethod(classifier as Class, op, change.newElement as IMethodDeclaration)
							}
						}
					}
				} else if (method.parent instanceof ITranslationUnit) { // Method definition (implementation in cpp)
					reverseSource(method.parent as ITranslationUnit)
				}
			}
			
			case change.oldElement instanceof IStructure,			
			case change.oldElement instanceof IEnumeration,			
			case change.oldElement instanceof ITypeDef: {
				var declaration = change.newElement as IDeclaration
				var umlClassifier = getClassifier(declaration.translationUnit.correspondingModel, 
					change.oldElement as IDeclaration, change.oldElement.elementName, declaration.translationUnit
				)
				if (umlClassifier != null) {
					umlClassifier.name = declaration.elementName
				}
			}
			
			case change.oldElement instanceof IEnumerator: {
				val cppEnumerator = change.newElement as IEnumerator
				var cppEnumeration = cppEnumerator.parent as IEnumeration
				var enumeration = getClassifier(cppEnumeration.translationUnit.correspondingModel,
									cppEnumeration, cppEnumeration.elementName, cppEnumeration.translationUnit
				)
				
				if (enumeration!= null && enumeration instanceof Enumeration) {
					var enumerator = (enumeration as Enumeration).ownedLiterals
									.filter(typeof(EnumerationLiteral))
									.filter[it.name == change.oldElement.elementName].head
					if (enumerator != null) {
						enumerator.name = cppEnumerator.elementName
					}				
				} 				
			}
			
			case change.oldElement instanceof ITranslationUnit: {
				var itu = change.newElement as ITranslationUnit
				if (itu.isSourceUnit) {
					reverseSource(itu)
				} else if (itu.isHeaderUnit) {
					reverseHeader(itu)
				}
				
				//getOrCreateClassifier(itu.correspondingModel, itu)
			}
		}
	}
	
	private def removeFromModel(CppChangeObject change) {
		switch(change.oldElement) {
			case change.oldElement instanceof IField: {
				val field = change.oldElement as IField
				if (field.parent instanceof IStructure) {
					var parentStructure = field.parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)				
					if (classifier != null) {
						if (classifier.isExist(change.oldElement.elementName)) {
							var prop = classifier.ownedElements.filter(typeof(Property)).filter[
								it.name.equals(change.oldElement.elementName)
							].head
							if (prop != null) {
								if (classifier instanceof Class) {
									classifier.ownedAttributes -= prop
								} else if (classifier instanceof DataType) {
									classifier.ownedAttributes -= prop
								}
							}
						}						
					}
				}
			}
			
			case change.oldElement instanceof IMethodDeclaration: {
				val method = change.oldElement as IMethodDeclaration
				if (method.parent instanceof IStructure) {
					var parentStructure = method.parent as IStructure
					var classifier = getClassifier(parentStructure.translationUnit.correspondingModel,
										parentStructure, parentStructure.elementName, parentStructure.translationUnit
									)
					if (classifier != null) {
						if (classifier.isExist(change.oldElement.elementName)) {
							var samenames = classifier.ownedElements.filter(typeof(Operation)).filter[it.name.equals(method.elementName)].toList							
							var op = samenames.filter[it.isSameSignature(method)].head
							if (op != null) {
								if (classifier instanceof Class) {
									classifier.ownedOperations -= op
								}
							}
						}
						
					}
				}
			}
			
			case change.oldElement instanceof IStructure,			
			case change.oldElement instanceof IEnumeration,			
			case change.oldElement instanceof ITypeDef: {
				val declaration = change.oldElement as IDeclaration
				if (change.parent instanceof IStructure) {
					var parentStructure = change.parent as IStructure
					var parentClassifier = getClassifier(parentStructure.translationUnit.correspondingModel, 
											parentStructure, parentStructure.elementName, parentStructure.translationUnit
										)
					var childClassifier = parentClassifier.ownedElements.filter(typeof(Type))
																		.filter[it.name == declaration.elementName].head
					if (childClassifier != null && parentClassifier instanceof Class) {
						(parentClassifier as Class).nestedClassifiers -= childClassifier as Classifier
					}																	
				} else {
					var containerPackage = declaration.translationUnit.containerPackage
					var childClassifier = containerPackage.ownedElements.filter(typeof(Type))
																		.filter[it.name == declaration.elementName].head
					containerPackage.ownedTypes -= childClassifier								
				}
			}
			
			case change.oldElement instanceof IEnumerator: {
				var cppEnumerator = change.oldElement as IEnumerator
				var cppEnumeration = cppEnumerator.path as IEnumeration
				var enumeration = getClassifier(cppEnumeration.translationUnit.correspondingModel,
									cppEnumeration, cppEnumeration.elementName, cppEnumeration.translationUnit
				)
				
				if (enumeration != null && enumeration instanceof Enumeration) {
					var enumerator = (enumeration as Enumeration).getOwnedLiteral(change.oldElement.elementName)
					if (enumerator != null) {
						(enumeration as Enumeration).ownedLiterals -= enumerator
					}
				}				
			}
			
			case change.oldElement instanceof ITranslationUnit: {
				var itu = change.newElement as ITranslationUnit
				getOrCreateClassifier(itu.correspondingModel, itu)
			}
		}
	}
	
	private def compareUml2Cpp(Element umlElement, ICElement icElement) {
		if (umlElement instanceof Package && icElement instanceof ICContainer) {
		}
	}

	private def comparePackage(Package pack, ICContainer container) {
		var containerChildren = container.children.filter(typeof(ICContainer)).toList
		if (pack.nestedPackages.size != containerChildren.size) {
			// TODO: one or several elements are deleted
		} else {
			// Check if package name is changed
			var iterator = containerChildren.iterator
			for(child : pack.nestedPackages) {
				child.syncName(iterator.next)
			}	
		}

	}

	private def syncName(NamedElement umlElement, ICElement icElement) {
		if (!icElement.elementName.equals(umlElement.name)) {
			umlElement.name = icElement.elementName
		}
		
	}

	private def comparePackageableElement(PackageableElement umlElement, IParent parent) {
		if (!(umlElement instanceof Package) || !(umlElement instanceof Classifier)) {
			return
		}

		var typeChildren = umlElement.ownedElements.filter(typeof(Type))
		var structureChildren = reverse_utils.getAllIStructures(parent, false, false, m_project)
		if (typeChildren.size != structureChildren.size) {
			// TODO: one or several elements are deleted
		} else {
			
		}
	}

	private def getContainerPackage(ITranslationUnit unit) {
		var model = unit.correspondingModel
		var unitPath = unit.file.projectRelativePath.toString
		if (unitPath == null || unitPath.equals("")) {
			unitPath = unit.location.toString
		}
		val arrayNames = unitPath.split("/" + model.name + "/").last.split("/")
		val names = new ArrayList<String>
		arrayNames.forEach[names.add(it)]
		// names.remove(0)
		var Package parentPack = model
		for (name : names) {
			if (!name.equals(unit.elementName)) {
				if (parentPack.getNestedPackage(name) == null) {
					parentPack.createNestedPackage(name)
				}
				parentPack = parentPack.getNestedPackage(name)
			}
		}
		parentPack
	}

	private def Type getClassifier(Model model, ICElement declaration, String name,
		ITranslationUnit unit) throws Exception {
		if (map.get(declaration) != null && map.get(declaration) instanceof Type) {
			return map.get(declaration) as Type
		}
		var Type ret = null;
		// names.remove(0)
		var Package parentPack = getContainerPackage(unit)
		var Classifier parentClass
		if (declaration.parent instanceof IStructure) {
			parentClass = getUMLType(declaration.parent.elementName, unit, declaration.contextNamespaces) as Classifier
			ret = createOrgetClassifier(parentClass, declaration, false) as Type
		} else {
			ret = createOrgetClassifier(parentPack, declaration, false) as Type
		}
		return ret;
	}

	private def createOrgetClassifier(PackageableElement packagebleElement, ICElement ideclaration, boolean createContent) {
		var PackageableElement packageble = packagebleElement
		
		if (ideclaration.elementType != ICElement.C_CLASS && ideclaration.elementType != ICElement.C_TEMPLATE_CLASS &&
			ideclaration.elementType != ICElement.C_ENUMERATION && ideclaration.elementType != ICElement.C_STRUCT &&
			ideclaration.elementType != ICElement.C_UNION && !(ideclaration instanceof ITypeDef)) {
			return null
		}
		
		var NamedElement existing = null
		if (!(packageble instanceof Package) && !(packageble instanceof Class)) {
			if (packageble instanceof DataType && ideclaration instanceof ITypeDef) {
				packageble = packagebleElement.nearestPackage
			} else {
				return null
			}
		}
		if (map.get(ideclaration) != null && map.get(ideclaration) instanceof Type) {
			existing = map.get(ideclaration) as Type
		}
		if (existing == null) {
			var namedElements = packageble.ownedElements.filter(typeof(Type)).filter [
				it.name.equals(ideclaration.elementName)
			]
			if (!namedElements.empty) {
				existing = namedElements.head
			}
		}

		if (existing != null) {
			return existing
		}

		m_monitor.subTask("Creating type " + ideclaration.elementName)
		
		if (ideclaration instanceof ITypeDef) {
			if (existing != null) {
				return existing
			}
			val typedef = ideclaration as ITypeDef
			var prinmitiveType = UMLFactory.eINSTANCE.createPrimitiveType => [
				it.name = typedef.elementName
			]
			if (packageble instanceof Package) {
				(packageble as Package).ownedTypes += prinmitiveType
			} else {
				(packageble as Class).nestedClassifiers += prinmitiveType
			}
			StereotypeUtil.apply(prinmitiveType, Typedef)
			if (typedef.typeName == "struct" || typedef.typeName == "class" || typedef.typeName == "enum") {
				var pos = (typedef.parent as IParent).children.indexOf(typedef)
				if (pos > 0) {
					var childStruct = (typedef.parent as IParent).children.get(pos - 1)
					if ((childStruct instanceof IStructure || childStruct instanceof IEnumeration) && childStruct.elementName == "") { // TODO anonymous typedefs?
						var source = (childStruct as ISourceReference).source
						UMLUtil.getStereotypeApplication(prinmitiveType, Typedef).definition = source.replace("typedef", "") + " typeName"
					}
				}
			} else {
				var typedefNode = typedef.findEnclosingNode
				var String rawSignature = typedefNode.rawSignature.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll(";", "").replaceAll("\\s+", " ").trim
				// Unescaped regex: (\()(\s*)(\*)(.*)(\))(\s*)(\()(.*)(\))
				var Pattern pattern = Pattern.compile("(\\()(\\s*)(\\*)(.*)(\\))(\\s*)(\\()(.*)(\\))");
				var Matcher matcher = pattern.matcher(rawSignature);
				if (matcher.find()) {
					val String typeName = rawSignature.replaceFirst(Pattern.quote(typedef.elementName), "typeName")
					
					UMLUtil.getStereotypeApplication(prinmitiveType, Typedef).definition = typeName
				} else {
					UMLUtil.getStereotypeApplication(prinmitiveType, Typedef).definition = typedef.typeName
				}
			}
			map.put(ideclaration, prinmitiveType)
			return prinmitiveType
		}
		
		var boolean structIsClassLike = false
		if (ideclaration.elementType == ICElement.C_STRUCT || ideclaration.elementType == ICElement.C_UNION) {
			val iStructure = ideclaration as IStructure
			
			var i = 0
			while (!structIsClassLike && i < iStructure.children.size) {
				var child = iStructure.children.get(i)
				if (child instanceof IMethodDeclaration || child instanceof IStructure) {
					structIsClassLike = true
				}
				i++;
			}
		}
		
		if (ideclaration.elementType == ICElement.C_CLASS || ideclaration.elementType == ICElement.C_TEMPLATE_CLASS || structIsClassLike) {
//			var isAbstract = !iStructure.methods.filter[it.isPureVirtual].empty
			val IStructure iStructure = ideclaration as IStructure
			var Class temp = null
			if (existing != null) {
				temp = existing as Class
			} else {
				if (packageble instanceof Package) {
					temp = (packageble as Package).createOwnedClass(iStructure.elementName, iStructure.abstract)
				} else {
					temp = (packageble as Class).createNestedClassifier(iStructure.elementName,
						UMLPackage.Literals.CLASS) as Class
					temp.isAbstract = iStructure.abstract
				}
			}

//			if (!createContent) {
//				return temp
//			}
			val classifier = temp
			iStructure.children.forEach [
				if (it.elementType == ICElement.C_FIELD) {
					createProperty(it as IField, classifier)
				} else if (it instanceof IMethodDeclaration) {
					createMethod(it, classifier)
				}
			]
			
			//
			
			var superTypes = iStructure.superClassesNames.map [
				it.getUMLType(iStructure.translationUnit, iStructure.contextNamespaces)
			]
			superTypes.forEach [
				if (it != null && it instanceof Classifier) {
					var Generalization generalization = classifier.createGeneralization(it as Classifier)
					
					var Visibility visibilitySt = UMLUtil.getStereotypeApplication(generalization, Visibility)
					if (visibilitySt == null) {
						StereotypeUtil.apply(generalization, Visibility)
						visibilitySt = UMLUtil.getStereotypeApplication(generalization, Visibility)
					}
					
					val visibility = iStructure.getSuperClassAccess(it.name)
					if (visibility != null) {
						visibilitySt.value = visibility.name.toLowerCase
					}
				}
			]
			
			map.put(ideclaration, classifier)
			if (iStructure.elementType == ICElement.C_TEMPLATE_CLASS) {
				var istructureTemplate = iStructure as IStructureTemplate
				StereotypeUtil.apply(classifier, Template)
				UMLUtil.getStereotypeApplication(classifier, Template).declaration = istructureTemplate.
					templateSignature
			}

			return classifier
		} else if (ideclaration.elementType == ICElement.C_ENUMERATION) {
			var Enumeration temp = null
			val iStructure = ideclaration as IEnumeration
			if (existing != null) {
				temp = existing as Enumeration
			} else {
				if (packageble instanceof Package) {
					temp = (packageble as Package).createOwnedEnumeration(iStructure.elementName)
				} else {
					temp = (packageble as Class).createNestedClassifier(iStructure.elementName,
						UMLPackage.Literals.ENUMERATION) as Enumeration
				}
			}

//			if (!createContent) {
//				return temp
//			}

			val enumeration = temp
			iStructure.children.forEach [
				if (it instanceof IEnumerator) {
					var EnumerationLiteral literal = enumeration.createOwnedLiteral(it.elementName)
					if ((it as IEnumerator).constantExpression != null) {
						for (Element element : literal.getOwnedElements()) {
							if (element instanceof ValueSpecification) {
								if ((element as ValueSpecification).getName().equals("defaultValue")) {
									element.destroy();
								}
							}
						}
						
						if ((it as IEnumerator).constantExpression != null && !(it as IEnumerator).constantExpression.equals("")) {
							var ValueSpecification valueSpecification = literal.createSpecification("defaultValue", literal.enumeration, UMLPackage.Literals.OPAQUE_EXPRESSION);
							
							if (valueSpecification instanceof OpaqueExpression) {
								var OpaqueExpression opaqueExpression = valueSpecification as OpaqueExpression;
								opaqueExpression.getLanguages().add(ReverseCpp2Uml.Cpp_LangID);
								opaqueExpression.getBodies().add((it as IEnumerator).constantExpression);
							}
						}
					}
				}
			]
			map.put(ideclaration, enumeration)
		} else if (!structIsClassLike && (ideclaration.elementType == ICElement.C_STRUCT || ideclaration.elementType == ICElement.C_UNION)) {
			var DataType temp = null
			val iStructure = ideclaration as IStructure
			if (existing != null) {
				temp = existing as DataType
			} else {
				if (packageble instanceof Package) {
					temp = (packageble as Package).createOwnedType(iStructure.elementName,
						UMLPackage.Literals.DATA_TYPE) as DataType
				} else {
					temp = (packageble as Class).createNestedClassifier(iStructure.elementName,
						UMLPackage.Literals.DATA_TYPE) as DataType
				}
			}

//			if (!createContent) {
//				return temp
//			}
			val DataType dataType = temp
			iStructure.children.forEach [
				if (it.elementType == ICElement.C_FIELD) {
					createProperty(it as IField, dataType)
				}
			]
			
			var superTypes = iStructure.superClassesNames.map [
				it.getUMLType(iStructure.translationUnit, iStructure.contextNamespaces)
			]
			superTypes.forEach [
				if (it != null && it instanceof Classifier) {
					var Generalization generalization = dataType.createGeneralization(it as Classifier)
					
					var Visibility visibilitySt = UMLUtil.getStereotypeApplication(generalization, Visibility)
					if (visibilitySt == null) {
						StereotypeUtil.apply(generalization, Visibility)
						visibilitySt = UMLUtil.getStereotypeApplication(generalization, Visibility)
					}
					
					val visibility = iStructure.getSuperClassAccess(it.name)
					if (visibility != null) {
						visibilitySt.value = visibility.name.toLowerCase
					}
				}
			]
			
			map.put(ideclaration, dataType)
		}
	}

	private def Classifier createOrgetTemplateParameter(NamedElement element, String parameterTypeName,
		String keyWord) {
		var Classifier ret = null
		var TemplateSignature templateSignature
		if (element instanceof Classifier) {
			var classifier = element as Classifier
			if (classifier.ownedTemplateSignature == null ||
				!(classifier.ownedTemplateSignature instanceof RedefinableTemplateSignature)) {
				templateSignature = classifier.createOwnedTemplateSignature(
					UMLPackage.Literals.REDEFINABLE_TEMPLATE_SIGNATURE) as RedefinableTemplateSignature
				(templateSignature as RedefinableTemplateSignature).name = TEMPLATE_PARAMETER_SIGNATURE_NAME
			}
			templateSignature = classifier.ownedTemplateSignature as RedefinableTemplateSignature
		} else if (element instanceof Operation) {
			var operation = element as Operation
			if (operation.ownedTemplateSignature == null ||
				!(operation.ownedTemplateSignature instanceof TemplateSignature)) {
				templateSignature = operation.createOwnedTemplateSignature(
					UMLPackage.Literals.TEMPLATE_SIGNATURE) as TemplateSignature
			}
			templateSignature = operation.ownedTemplateSignature as TemplateSignature
		} else {
			return null
		}

		var classifierTemplates = templateSignature.ownedParameters.filter(typeof(ClassifierTemplateParameter))
		var classifierTemplatesContainClassifier = classifierTemplates.filter [
			it.ownedParameteredElement instanceof Classifier
		]
		var containedClassifiers = classifierTemplatesContainClassifier.map[it.ownedParameteredElement]
		ret = containedClassifiers.filter(typeof(Classifier)).filter[it.name.equals(parameterTypeName)].head
		if (ret == null) {
			var classifierTemplate = templateSignature.createOwnedParameter(
				UMLPackage.Literals.CLASSIFIER_TEMPLATE_PARAMETER) as ClassifierTemplateParameter
			ret = classifierTemplate.createOwnedParameteredElement(UMLPackage.Literals.CLASS) as Classifier
			ret.name = parameterTypeName
			classifierTemplate.addKeyword(keyWord)
		}

		return ret
	}

	private def getSelector(ITranslationUnit unit) {
		var IASTTranslationUnit ast = null
		
		if (unit != null) {
			if (translationUnitToASTTranslationUnitMap.get(unit) != null) {
				return translationUnitToASTTranslationUnitMap.get(unit).getNodeSelector(null)
			} else {
				ast = unit.getAST(index, ITranslationUnit.AST_CONFIGURE_USING_SOURCE_CONTEXT)
				if (ast != null) {
					translationUnitToASTTranslationUnitMap.put(unit, ast)
					return ast.getNodeSelector(null);
				}
			}
		}
		
		return null
	}

	def createMethod(IMethodDeclaration method, Class classifier) {
		var IASTFunctionDeclarator declarator = method.declarator
		if (declarator != null) {
			val op = classifier.createOwnedOperation(method.elementName, null, null)
			updateMethod(classifier, op, method)
		}

	}
	
	private def findEnclosingNode(ISourceReference source) {
		var selector = getSelector(source.translationUnit)
		var ISourceRange range = source.getSourceRange();
		selector.findEnclosingNode(range.startPos, range.length)
	}
	
	private def IASTFunctionDeclarator getDeclarator(IMethodDeclaration method) {
		var IASTFunctionDeclarator declarator = null
		var node = method.findEnclosingNode
		if (node instanceof ICPPASTFunctionDefinition) {
			declarator = (node as ICPPASTFunctionDefinition).declarator
		} else if (node instanceof IASTSimpleDeclaration) {
			var declaration = node as IASTSimpleDeclaration
			declarator = declaration.declarators.filter(typeof(IASTFunctionDeclarator)).head
		} else if (node instanceof ICPPASTTemplateDeclaration) {
			var declaration = node as ICPPASTTemplateDeclaration
			var chidren = declaration.children
			for (child : chidren) {
				if (child instanceof IASTSimpleDeclaration) {
					declarator = child.declarators.filter(typeof(IASTFunctionDeclarator)).head
				}
			}
		}
		return declarator
	}
	
	private def List<String> getKeywords(IMethodDeclaration method) {
		var node = method.findEnclosingNode
		var List<String> keywords = new ArrayList
		if (node instanceof ICPPASTTemplateDeclaration) {
			var declaration = node as ICPPASTTemplateDeclaration
			var chidren = declaration.children
			for (child : chidren) {
				if (child instanceof ICPPASTTemplateParameter) {
					var token = child.syntax
					var keyword = "class"
					while (token != null) {
						if (token.type == IToken.t_typename) {
							keyword = "typename"
						}
						token = token.next
					}
					keywords.add(keyword)
				}
			}
		}
		return keywords
	}
	
	private def String getBody(IMethodDeclaration method) {
		var IASTFunctionDeclarator declarator = null
		var node = method.findEnclosingNode
		var String body = null
		
		if (node instanceof ICPPASTFunctionDefinition) {
			declarator = (node as ICPPASTFunctionDefinition).declarator
			body = BatchReverseFunctionBody.getBody(method.translationUnit, node as ICPPASTFunctionDefinition)
		} else if (node instanceof IASTFunctionDeclarator) {
			// TODO
		}
		
		// TODO
		/*if (body == null) {
			if (method.translationUnit.isHeaderUnit) {
				val headerUnitNameNoExtension = method.translationUnit.elementName.substring(0, method.translationUnit.elementName.lastIndexOf('.'))
				
				for (ICContainer container : containers) {
					var children = container.children
					container.children.filter(typeof(ITranslationUnit)).filter[!it.isHeaderUnit].forEach [
						val sourceUnitNameNoExtension = it.elementName.substring(0, it.elementName.lastIndexOf('.'))
						if (sourceUnitNameNoExtension.equals(headerUnitNameNoExtension)) {
							var unit = it
							unit.
							println(unit)
						}
					]
				}
			}
		}*/
		
		return body
	}
	
	private def IASTNode getRootNode(IASTNode fromNode) {
		if (fromNode.parent == null) {
			return fromNode
		}
		
		return getRootNode(fromNode.parent)
	}
	
	private def String getMemberInit(IMethodDeclaration method) {
		return getMemberInit(method.findEnclosingNode)
	}
	
	public static def String getMemberInit(IASTNode methodNode) {
		var ret = ""
		
		if (methodNode instanceof ICPPASTFunctionDefinition) {
			val inits = methodNode.memberInitializers
			if (inits != null) {
				ret = '''«FOR i:inits SEPARATOR ", "»«i.memberInitializerId»«i.initializer.rawSignature»«ENDFOR»'''
			}
		}
		
		return ret.toString
	}
	
	private def updateMethod(Class classifier, Operation op, IMethodDeclaration method) {
		op.name = method.elementName
		StereotypeUtil.unapply(op, Inline)
		StereotypeUtil.unapply(op, Friend)
		StereotypeUtil.unapply(op, Virtual)
		StereotypeUtil.unapply(op, Volatile)
		StereotypeUtil.unapply(op, Create)
		StereotypeUtil.unapply(op, Destroy)
		StereotypeUtil.unapply(op, Const)
		op.ownedParameters.clear
		var declarator = method.declarator
		var List<String> keywords = method.keywords		
		var body = method.body
		
		op.visibility = method.visibility.convertVisibility
		op.isStatic = method.isStatic
		reverse_utils.applyStereotype(op, method.inline, StereotypeType.INLINE, "")
		reverse_utils.applyStereotype(op, method.friend, StereotypeType.FRIENDLINE, "")
		reverse_utils.applyStereotype(op, method.isVirtual, StereotypeType.VIRTUAL, "")
		reverse_utils.applyStereotype(op, method.volatile, StereotypeType.VOLATILE, "")
		reverse_utils.applyStereotype(op, method.isConst, StereotypeType.CONST, "")
		if (method.virtual) {
			reverse_utils.applyStereotype(op, method.virtual, StereotypeType.VIRTUAL, "")
			if (method.pureVirtual) {
				op.isAbstract = true
			}
		}
		reverse_utils.applyStereotype(op, method.constructor, StereotypeType.CREATE, "")
		reverse_utils.applyStereotype(op, method.destructor, StereotypeType.DESTROY, "")
		try {
			var String signature = method.signature
			var Pattern pattern = Pattern.compile("([\\s]*)(\\.\\.\\.)([\\s]*)(\\))");
			var Matcher matcher = pattern.matcher(signature);
			reverse_utils.applyStereotype(op, matcher.find(), StereotypeType.VARIADIC, "")
		} catch (Exception e) {
			e.printStackTrace
		}
		
		if (method instanceof IMethodTemplateDeclaration) {
			for (var i = 0; i < method.templateParameterTypes.size; i++) {
				op.createOrgetTemplateParameter(method.templateParameterTypes.get(i), keywords.get(i))
			}
		}
		if (!method.constructor && !method.destructor) {
			if (method.returnType != null) {
				var parameterType = op.getParameterTemplateType(method, method.returnType.cppTypeName)
				if (parameterType == null) {
					parameterType = method.returnType.cppTypeName.getUMLType(method.translationUnit,
						method.contextNamespaces)
				}
				var ret = op.createOwnedParameter("ret", parameterType)
				ret.direction = ParameterDirectionKind.RETURN_LITERAL
				reverse_utils.analyzeDeclaration(declarator, ret.type, ret, langID)
				if (parameterType.name.equals("void") && ret.appliedStereotypes.empty) {
					ret.destroy
				}
				
				reverse_utils.applyStereotype(ret, method.returnType.contains("const "), StereotypeType.CONST, "")
				reverse_utils.applyStereotype(ret, method.returnType.contains("volatile "), StereotypeType.VOLATILE, "")
			} else {
				var ret = op.createOwnedParameter("ret",
					"void".getUMLType(method.translationUnit, method.contextNamespaces))
				ret.direction = ParameterDirectionKind.RETURN_LITERAL
				reverse_utils.analyzeDeclaration(declarator, ret.type, ret, langID)
				if (ret.appliedStereotypes.empty) {
					ret.destroy
				}
			}
		}
		
		// Single parameter of type void ==> we do not need to create any parameters
		var boolean noArguments = false 
		if (declarator.children.filter(typeof(IASTParameterDeclaration)).size == 1) {
			var parameterNode = declarator.children.filter(typeof(IASTParameterDeclaration)).get(0)
			
			if (parameterNode.declSpecifier != null) {
				var String type = ""
				var tokens = parameterNode.declSpecifier.syntax
				while(tokens != null) {
					type += tokens.toString
					tokens = tokens.next
				}
				
				if (type.trim.equals("void")) {
					noArguments = true
				}
			}
		}
		
		if (!noArguments) {
			for (param : declarator.children.filter(typeof(IASTParameterDeclaration))) {
				excludeIncludFromTranslationUnit(param, method, op)
				var parameterType = op.getParameterTemplateType(method, reverse_utils.getCppTypeName(param.declSpecifier))
				if (parameterType == null) {
					parameterType = param.declSpecifier.getUMLType(method.translationUnit, method.contextNamespaces)
				}
				val opParam = op.createOwnedParameter(param.declarator.name.toString, parameterType)
				reverse_utils.applyStereotype(opParam, param.declSpecifier.const,
					StereotypeType.CONST, "")
				reverse_utils.analyzeDeclaration(param.declarator, opParam.type, opParam, langID)
				reverse_utils.applyStereotype(opParam, param.declSpecifier.volatile,
					StereotypeType.VOLATILE, "")
			}
		}
		
		if (body != null) {
			if (method.constructor) {
				var initStr = method.memberInit
				if (!initStr.empty) {
					StereotypeUtil.apply(op, ConstInit)
					UMLUtil.getStereotypeApplication(op, ConstInit).initialisation = initStr
				}				
			}
			var OpaqueBehavior ob = null;
			if (op.getMethods().size() == 0) {
				ob = classifier.createOwnedBehavior(op.name, UMLPackage.Literals.OPAQUE_BEHAVIOR) as OpaqueBehavior
				ob.setSpecification(op)
				ob.setIsReentrant(false)
			} else {
				ob = op.getMethods().get(0) as OpaqueBehavior
				if (!ob.getName().equals(op.name)) {
					ob.setName(op.name);
				}
			}
			
			if (ob.getBodies().size() == 0) {
				ob.getLanguages().add(langID)
				ob.getBodies().add("")
			}
			
			for (var i = 0; i < ob.getLanguages().size(); i++) {
				if (ob.getLanguages().get(i).equals(langID)) {
					if (i < ob.getBodies().size()) {
						ob.getBodies().set(i, body)
					}
				}
			}
			
			val IASTNode node = method.findEnclosingNode
			
			if (node instanceof IASTFunctionDefinition) {
				new DependencyAnalysis(op, node, method.translationUnit, this).analyzeDependencies();
			}
		}
	}

	private def Type getParameterTemplateType(Operation op, IMethodDeclaration imethod, String typeName) {
		var Type ret = null
		if (imethod instanceof IMethodTemplateDeclaration) {
			if (!imethod.templateParameterTypes.filter[it.equals(typeName)].empty) {
				ret = op.createOrgetTemplateParameter(typeName, "class")
			}
		}
		return ret
	}
	
	private def isCollectionType(IField field, Property prop) {
		//
	}
	
	private def isSimpleAssociation(Property prop) {
		if ((StereotypeUtil.isApplied(prop, Ptr) || StereotypeUtil.isApplied(prop, Ref))
			&& !StereotypeUtil.isApplied(prop, Array)
		) {
			return true
		}
		
		return false
	}
	
	private def isAggregation(Property prop) {
		if ((StereotypeUtil.isApplied(prop, Ptr) || StereotypeUtil.isApplied(prop, Ref))
			&& StereotypeUtil.isApplied(prop, Array)
		) {
			return true
		}
		return false
	}
	
	private def isComposition(Property prop) {
		if ((!StereotypeUtil.isApplied(prop, Ptr) && !StereotypeUtil.isApplied(prop, Ref))
		) {
			return true
		}
		return false
	}

	private def createProperty(IField field, Classifier classifier) {
		var prop = UMLFactory.eINSTANCE.createProperty => [
			it.name = field.elementName
		]
		if (classifier instanceof DataType) {
			(classifier as DataType).ownedAttributes += prop
		} else if (classifier instanceof Class) {
			(classifier as Class).ownedAttributes += prop
		}
		updateProperty(field, prop)
	}
	
	private def updateProperty(IField field, Property prop) {
		reverse_utils.unapplyAllStereotypes(prop)
		
		var Type type = null
		var doNotAnalyzeDeclaration = false
		
		try {
			// Check the field's rawSignature to see if it's an anonymous function pointer
			var fieldNode = field.findEnclosingNode
			if (fieldNode != null) {
				var String rawSignature = fieldNode.rawSignature.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll(";", "").replaceAll("\\s+", " ").trim
				// Unescaped regex: (\()(\s*)(\*)(.*)(\))(\s*)(\()(.*)(\))
				var Pattern pattern = Pattern.compile("(\\()(\\s*)(\\*)(.*)(\\))(\\s*)(\\()(.*)(\\))");
				var Matcher matcher = pattern.matcher(rawSignature);
				if (matcher.find()) {
					var String typeName = rawSignature.replaceFirst(Pattern.quote(field.elementName), "typeName").replaceFirst("typedef", "");
					
					var PackageableElement packageable = null
					if (prop.owner instanceof Class) {
						packageable = prop.owner as Class
					} else {
						packageable = prop.nearestPackage
					}
					val String ownerName = packageable.name
					
					var primitiveType = UMLFactory.eINSTANCE.createPrimitiveType => [
						it.name = ownerName + "_" + field.elementName + "_funcptr"
					]
					
					if (packageable instanceof Package) {
						(packageable as Package).ownedTypes += primitiveType
					} else {
						(packageable as Class).nestedClassifiers += primitiveType
					}
					
					StereotypeUtil.apply(primitiveType, Typedef)
					UMLUtil.getStereotypeApplication(primitiveType, Typedef).definition = typeName
					
					type = primitiveType
					doNotAnalyzeDeclaration = true
				}
			}
			
			// Was not an anon function pointer, check if it's an anon struct or enum
			if (type == null) {
				if (field.typeName.equals("enum") || field.typeName.equals("struct") || field.typeName.equals("class")) {
					// TODO Bug: rawSignature is only name of field for anonymouse struct and class
					// This is because field.source returns only the name of the field
					// For an anonymous enum, it returns the enum as well...
					var rawSignature = fieldNode.rawSignature
					var trimmedRawSignature = rawSignature.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll(";", "").replaceAll("\\s+", " ").trim
					
	 				// Unescaped pattern: ({)(.*)(})
					var Pattern pattern = Pattern.compile("(\\{)(.*)(\\})");
					var Matcher matcher = pattern.matcher(trimmedRawSignature);
					if (matcher.find()) {
						var String[] tokens = rawSignature.split("}")
						if (tokens.size > 0) {
							var String lastToken = tokens.get(tokens.size - 1)
							if (lastToken.contains(field.elementName)) {
								lastToken = lastToken.replaceFirst(Pattern.quote(field.elementName), "typeName")
							}
							
							var String typeName = ""
							for (i : 0 ..< tokens.size - 1) {
								typeName += tokens.get(i)
							}
							typeName += lastToken
							
							var PackageableElement packageable = null
							if (prop.owner instanceof Class) {
								packageable = prop.owner as Class
							} else {
								packageable = prop.nearestPackage
							}
							val String ownerName = packageable.name
							
							var primitiveType = UMLFactory.eINSTANCE.createPrimitiveType => [
								it.name = ownerName + "_" + field.elementName + "_anon_" + field.typeName
							]
							
							if (packageable instanceof Package) {
								(packageable as Package).ownedTypes += primitiveType
							} else {
								(packageable as Class).nestedClassifiers += primitiveType
							}
							
							StereotypeUtil.apply(primitiveType, Typedef)
							UMLUtil.getStereotypeApplication(primitiveType, Typedef).definition = typeName
							
							type = primitiveType
							doNotAnalyzeDeclaration = true
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace
		}
		
		field.excludeIncludFromTranslationUnit(prop) // Important to do this before the next instruction, otherwise we can have Class1 > Class4 > Class11 > Class1.h processed without any includes excluded previously
		
		if (type == null) {
			type = field.getUMLType(reverse_utils.getContextNamespaces(field))
		}
		
		prop.type = type
		prop.name = field.elementName
		if (!doNotAnalyzeDeclaration) {
			var node = getSelector(field.translationUnit).findEnclosingNode(field.sourceRange.startPos,
			field.sourceRange.length)
			if (node instanceof IASTSimpleDeclaration) {
				reverse_utils.analyzeDeclaration(node.declarators, prop.type, prop, langID)
			}
		}
		
		prop.isStatic = field.isStatic
		prop.visibility = field.visibility.convertVisibility
		reverse_utils.applyStereotype(prop, field.const, StereotypeType.CONST, "")
		reverse_utils.applyStereotype(prop, field.volatile, StereotypeType.VOLATILE, "")
		reverse_utils.applyStereotype(prop, field.mutable, StereotypeType.MUTABLE, "")
		
		var asso = TypeOperationsEnhanced.createAssociationFromProperty(prop, true, AggregationKind.NONE_LITERAL, false, 
				AggregationKind.NONE_LITERAL, (prop.eContainer as Classifier).name.toFirstLower, 1, 1
			)
		asso.name = "A_" + prop.name + "_" + (prop.eContainer as Classifier).name.toFirstLower	
		if (prop.isSimpleAssociation) {
			prop.aggregation = AggregationKind.NONE_LITERAL
		} else if (prop.isAggregation) {
			prop.aggregation = AggregationKind.SHARED_LITERAL
		} else if (prop.isComposition) {
			prop.aggregation = AggregationKind.COMPOSITE_LITERAL
		}
	}

	private def convertVisibility(ASTAccessVisibility visibility) {
		if(visibility == ASTAccessVisibility.PRIVATE) return VisibilityKind.PRIVATE_LITERAL
		if(visibility == ASTAccessVisibility.PUBLIC) return VisibilityKind.PUBLIC_LITERAL
		if(visibility == ASTAccessVisibility.PROTECTED) return VisibilityKind.PROTECTED_LITERAL
		return VisibilityKind.PUBLIC_LITERAL
	}
	
	public def List<String> getContextNamespaces(ICElement element) {
		return reverse_utils.getContextNamespaces(element)
	}

	private def Type getUMLType(IVariableDeclaration varDecl, List<String> contextNamespaces) {
		var Type ret = null
		
		ret = varDecl.typeName.cppTypeName.getUMLType(varDecl.translationUnit, contextNamespaces)
		return ret
	}

	private def String getCppTypeName(String name) {
		var trimName = name.trim
		trimName = trimName.replace("*", "")
		trimName = trimName.replace("&", "")
		trimName = trimName.replace("[", "")
		trimName = trimName.replace("]", "")
		trimName = trimName.replace("const ", "")
		trimName = trimName.replace(" const", "")
		trimName = trimName.replace("volatile", "")
		trimName = trimName.replace(" volatile", "")
		trimName = trimName.trim
		return trimName
	}

	private def Type getUMLType(IASTDeclSpecifier declarator, ITranslationUnit itu, List<String> contextNamespaces) {
		var Type ret = null
		ret = reverse_utils.getCppTypeName(declarator).getUMLType(itu, contextNamespaces)
		return ret
	}

	private def getElementsSameName(String name) {
		map.entrySet.filter[it.key.elementName.equals(name)].toList
	}
	
	private def ICElement lookTypeInContainer(ICContainer container, String typeName) {
		var ICElement ret = null
		for(child:container.children) {
			if (child instanceof ITranslationUnit) {
				var nesteds = reverse_utils.getAllIStructures(child, false, true, m_project)
				ret = nesteds.filter[it instanceof IStructure || it instanceof IEnumeration || it instanceof ITypeDef]
								.filter[it.elementName.equals(typeName)].head				
			} else if (child instanceof ICContainer) {
				ret = lookTypeInContainer(child, typeName)
			}
			if (ret != null) {
				return ret
			}
		}
		return ret
	}
	
	private def lookForATypeByName(String typeName) {
		var Type ret = null
		var ICElement cType = null
		for(container:containers) {
			if (cType == null) {
				cType = lookTypeInContainer(container, typeName)
			}
		}
		if (cType != null) {
			var itu = reverse_utils.getTranslationUnitFromElement(cType)
			if (itu != null) {
				ret = getClassifier(itu.correspondingModel, cType, cType.elementName, itu)
			}
		}		
		return ret
	}

	public def Type getUMLType(String typeName, ITranslationUnit itu, List<String> contextNamespaces) {
		var Type ret = null
		
		if (RoundtripCppUtils.isPrimitiveCppType(typeName)) {
			return RoundtripCppUtils.getPrimitiveType(typeName, itu.correspondingModel)
		}
		var token = typeName.split("::")
		val simpleTypeName = token.get(token.length - 1).trim
		var iUsings = new ArrayList<String>
		iUsings.addAll(contextNamespaces)
		iUsings.addAll(itu.children.filter(typeof(IUsing)).map[it.elementName])

		// look for elements already reversed
		val sameNames = getElementsSameName(simpleTypeName)
		if (sameNames.size == 1 && sameNames.head.value instanceof Type) {
			//fillIncludesFromTypes(sameNames.head.value as Type, itu, iUsings)
			return sameNames.head.value as Type
		}

		// Looking in structures in the same file
		var nestedStructures = reverse_utils.getAllIStructures(itu, false, true, m_project)

		if (sameNames.size > 1) {
			// look for type in the same translation unit
			for (sameName : sameNames) {
				val withinITU = nestedStructures.filter[it.elementName.equals(sameName.key.elementName)]
				if (withinITU.size > 0) {
					//fillIncludesFromTypes(sameName.value as Type, itu, iUsings)
					return sameName.value as Type
				} else {
					if (reverse_utils.isSatisfyNamespace(iUsings, sameName.key)) {
						//fillIncludesFromTypes(sameName.value as Type, itu, iUsings)
						return sameName.value as Type
					}
				}
			}
		}

		var sameNameType = nestedStructures.filter[it.elementName.trim.equals(simpleTypeName.trim)].head
		if (sameNameType == null) {
			var structureTemplates = nestedStructures.filter(typeof(IStructureTemplate))
			val match = structureTemplates.filter [
				it.templateParameterTypes.filter [
					it.equals(simpleTypeName)
				].size > 0
			].head

			// TODO choose which one?
			if (match != null) {
				var templatClassifier = getOrCreateClassifier(itu.correspondingModel, match.parent as IParent).filter [
					it.name.equals(match.elementName)
				].head
				ret = createOrgetTemplateParameter(templatClassifier as Classifier, simpleTypeName, "class")
			}

		} else {
			if (sameNameType instanceof IStructureDeclaration && !(sameNameType instanceof IStructure)) {
				ret = lookForATypeByName(sameNameType.elementName)
			} else {
				ret = getClassifier(itu.correspondingModel, sameNameType, sameNameType.elementName, itu)
			}
			
		}

		var externalPackage = RoundtripCppUtils.getOrcreateExternalPackage(itu.correspondingModel, false)
		if (externalPackage != null && externalPackage.getOwnedMember(simpleTypeName.trim) != null &&
			externalPackage.getOwnedMember(simpleTypeName.trim) instanceof Type) {
			ret = externalPackage.getOwnedMember(simpleTypeName.trim) as Type
		}

		if (ret == null) {
			if (token.size > 1) {
				for (var i = 0; i < token.size - 1; i++) {
					iUsings.add(token.get(i).trim)
					if (i > 0) {
						var n = token.head.trim
						for (var j = 1; j <= i; j++) {
							n = n + "::" + token.get(j).trim
						}
						iUsings.add(n)
					}
				}
			}

			val List<IInclude> includes = new UniqueEList

			// look up in the same directory
			val istructures = new UniqueEList<ICElement>
			if (itu.parent instanceof ICContainer) {
				var ICContainer container = itu.parent as ICContainer
				container.children.filter(typeof(ITranslationUnit)).forEach [
					istructures.addAll(reverse_utils.getAllIStructures(it, false, true, m_project).filter [
						it.elementName.trim.equals(simpleTypeName.trim)
					])
					var includesOfItu = it.children.filter(typeof(IInclude))
					includesOfItu.forEach [
						val nameOfIncludeItu = it.elementName
						if (includes.filter[it.elementName == nameOfIncludeItu].empty) {
							includes.add(it)
						}
					]

				]
				for (structure : istructures) {
					if (ret == null) {
						if (reverse_utils.isSatisfyNamespace(iUsings, structure)) {
							var tmp = getOrCreateClassifier(reverse_utils.getTranslationUnitFromElement(structure).correspondingModel,
								reverse_utils.getTranslationUnitFromElement(structure))
							var sames = tmp.filter[it != null && it.name.trim.equals(simpleTypeName)]
							if (sames.size == 1) {
								ret = sames.head
							} else {
								if (reverse_utils.isSatisfyNamespace(iUsings, structure)) {
									// TODO 
								}
							}
						}
					}
				}
			}

			// look in include types
			includes.addAll(itu.children.filter(typeof(IInclude)))
			var List<IInclude> includes_level2 = new UniqueEList
			while (ret == null && !includes.empty) {
				for (include : includes) {
					if (ret == null) {
						// only lookup in files of project
						if (include.fullFileName.contains(m_project.elementName)) {
							ret = lookupTypeInInclude(simpleTypeName, include, iUsings)
						}
					} else {
						var translationUnit = reverse_utils.getTranslationUnitFromInclude(include, m_project)
						if (translationUnit != null) {
							includes_level2.addAll(translationUnit.children.filter(typeof(IInclude)))
						}
					}
				}
				if (ret == null) {
					includes.clear
					includes.addAll(includes_level2)
					includes_level2.clear
				}
			}
			
		}
		
		if (ret == null) {
			LOGGER.log(Level.WARNING, typeName + " is not found, it will be created dynamically")
			externalPackage = RoundtripCppUtils.getOrcreateExternalPackage(itu.correspondingModel, true)
			ret = externalPackage.getOwnedType(typeName.trim)
			if (ret == null) {
				ret = externalPackage.createOwnedType(typeName.trim, UMLPackage.Literals.DATA_TYPE)
				StereotypeUtil.apply(ret, External)
			}
		}

		//fillIncludesFromTypes(ret, itu, iUsings)
		return ret
	}
	
	private def void excludeIncludFromTranslationUnit(IField field, Property property) {
		if (field != null) {
			val type = field.typeName
			val context = reverse_utils.getContextNamespaces(field)
			val translationUnit = field.translationUnit
			var Classifier structure = null
			
			var owner = property.owner
			while (owner != null && !(owner instanceof Classifier)) {
				owner = owner.owner // TODO what about inner inner class?
			}
			if (owner instanceof Classifier) {
				structure = (owner as Classifier)
			}
			 
			excludeIncludFromTranslationUnit(type, structure, translationUnit, context)
		}
	}
	
	private def void excludeIncludFromTranslationUnit(IASTParameterDeclaration param, IMethodDeclaration method, Operation operation) {
		if (param != null && method != null) {
			val translationUnit = method.translationUnit
			val type = reverse_utils.getCppTypeName(param.declSpecifier)
			val context = reverse_utils.getContextNamespaces(method)
			var Classifier structure = null
			
			var owner = operation.owner
			while (owner != null && !(owner instanceof Classifier)) {
				owner = owner.owner
			}
			if (owner instanceof Classifier) {
				structure = (owner as Classifier)
			}
			
			excludeIncludFromTranslationUnit(type, structure, translationUnit, context)
		}
	}
	
	private def void excludeIncludFromTranslationUnit(String excludedType, Element fromStructure, ITranslationUnit translationUnit, List<String> context) {
		if (excludedType != null && fromStructure != null && translationUnit != null && context != null) {
			var includes = getIncludesFromType(excludedType, translationUnit as ITranslationUnit, context)
			
			if (includes != null && !includes.empty) {
				val String key = getExcludedIncludesMapKey(translationUnit, fromStructure)
				var List<IInclude> includesInTranslationUnit = excludedIncludesMap.get(key)
			
				if (includesInTranslationUnit == null) {
					includesInTranslationUnit = new UniqueEList<IInclude>()
					excludedIncludesMap.put(key, includesInTranslationUnit)
				}
				
				includesInTranslationUnit.addAll(includes)
			}
		}
	}
	
	private def getExcludedIncludesMapKey(ITranslationUnit translationUnit, Element umlElement) {
		return "" + translationUnit.hashCode + umlElement.hashCode
	}
	
	private def getIncludesFromType(String typeName, ITranslationUnit itu, List<String> iUsings) {
		val List<IInclude> includes = new UniqueEList
		
		if (typeName == null || itu == null || iUsings == null) {
			return includes
		}
		
		if (BatchReverseFunctionBody.ansiTypes.contains(typeName)) {
			return includes
		}
		
		var token = typeName.split("::")
		val simpleTypeName = token.get(token.length - 1).trim
		
		for (include : itu.children.filter(typeof(IInclude))) {
			// only lookup in files of project
			if (include.fullFileName.contains(m_project.elementName)) { // TODO Do we need this second condition? ==> lookupTypeInInclude(simpleTypeName, include, iUsings) != null
				includes.add(include)
			}
		}
		
		return includes
	}

	private def Type lookupTypeInInclude(String simpleTypeName, IInclude include, List<String> iUsings) {
		if (!include.fullFileName.contains(m_project.elementName)) {
			return null
		}
		var Type ret = null
		val istructures = new UniqueEList<ICElement>

		if (ret == null) {
			istructures.clear
			// lookup in included files
			var unit = reverse_utils.getTranslationUnitFromInclude(include, m_project)
			if (unit == null) {
				return null
			}
			istructures.addAll(reverse_utils.getAllIStructures(unit, false, true, m_project).filter [
				it.elementName.trim.equals(simpleTypeName.trim)
			])
			for (structure : istructures) {
				if (ret == null) {
					var tmp = getOrCreateClassifier(reverse_utils.getTranslationUnitFromElement(structure).correspondingModel,
						reverse_utils.getTranslationUnitFromElement(structure))
					var sameNames = tmp.filter[it != null && it.name.trim.equals(simpleTypeName)]
					if (sameNames.size == 1) {
						ret = sameNames.head
					} else {
						if (reverse_utils.isSatisfyNamespace(iUsings, structure)) {
							// TODO 
						}
					}

				}
			}
		}
		return ret
	}

	//TODO refactor this since it's the same method as BatchReverseFunctionBody. Seriously...
	private def getTypeByQualifiedName(ICElement element, ITranslationUnit translationUnit, List<String> contextNames) {
		var results = new UniqueEList<Classifier>()
		
		if (element instanceof IVariableDeclaration || element instanceof IMethod) {
			if (element.getElementName().contains("::")) {
				var String[] classifierNameTokens = element.getElementName().split("::");
				var String classifierName = "";
				if (classifierNameTokens.length > 1) {
					classifierName = classifierNameTokens.get(classifierNameTokens.length - 2);
				} else {
					classifierName = classifierNameTokens.get(0);
				}
				var boolean isFound = false;
				var i = 0;
				while (i < results.size && !isFound) {
					var Classifier classifier = results.get(i);
					if (classifier != null) {
						if (classifier.getName().equals(classifierName)) {
							isFound = true;
						}
					}
					i++
				}
				
				if (!isFound) {
					var Type typeRet = this.getUMLType(classifierName, translationUnit, contextNames);
					if (typeRet instanceof Classifier) {
						results.add(typeRet as Classifier);
					}
				}
			}
		}
		
		return results
	}
}