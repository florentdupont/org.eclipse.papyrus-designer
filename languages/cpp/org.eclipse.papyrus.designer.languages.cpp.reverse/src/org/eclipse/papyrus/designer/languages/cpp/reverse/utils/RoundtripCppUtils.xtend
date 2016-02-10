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

package org.eclipse.papyrus.designer.languages.cpp.reverse.utils

import java.util.ArrayList
import java.util.List
import org.eclipse.cdt.core.dom.ast.ExpansionOverlapsBoundaryException
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration
import org.eclipse.cdt.core.model.ICElement
import org.eclipse.cdt.core.model.IParent
import org.eclipse.cdt.core.model.IStructure
import org.eclipse.cdt.core.model.IStructureTemplate
import org.eclipse.cdt.core.model.ITranslationUnit
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.common.util.UniqueEList
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen
import org.eclipse.papyrus.designer.languages.cpp.codegen.transformation.CppLocationStrategy
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Model
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Profile
import org.eclipse.uml2.uml.Type
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.BatchReverseFunctionBody

/**
 * Utility methods for round-trip
 * 
 */
class RoundtripCppUtils {
	public def static Classifier getOrCreateClassifier(IStructure iStructure, ITranslationUnit iTu, String projectName,
		Model model) {
		var Classifier classifier = null
		// var engine = IncQueryEngineSingleton.getEngine(model.eResource.resourceSet)
		val arrayNames = iTu.location.toString.split(projectName).last.substring(1).split("/")
		val names = new ArrayList<String>
		arrayNames.forEach[names.add(it)]
//		classifier = RoundtripQuery.instance.getMappedElement(engine).
//							getAllValuesOfumlElement(classifier.fileName).filter(typeof(Classifier)).head
		if (classifier == null) {
			names.remove(0)
			var Package parentPack = model
			for (name : names) {
				if (!name.equals(names.last)) {
					if (parentPack.getNestedPackage(name) == null) {
						parentPack.createNestedPackage(name)
					}
					parentPack = parentPack.getNestedPackage(name)
				}
			}
			if (parentPack.getOwnedType(iStructure.elementName) == null) {
				parentPack.createOwnedClass(iStructure.elementName, false)
			}
			classifier = parentPack.getOwnedType(iStructure.elementName) as Classifier
		// while(child)
		}
		return classifier
	}

	public def static getFileName(NamedElement element) {
		val cppLocationStrategy = new CppLocationStrategy
		return cppLocationStrategy.getFileName(element)
	}

	public static def getPrimitiveType(String name, Model model) {
		var isPrimitive = isPrimitiveCppType(name)
		if (isPrimitive) {
			// get ansipackage or import to model
			var Package ansiPack = importOrgetAModel(model, ansiUri)
			return name.getTypeFromModel(ansiPack)
		}

		return null;
	}

	public static def isPrimitiveCppType(String name) {
		return !BatchReverseFunctionBody.ansiTypes.filter[it.equals(name)].empty
	}

	public def static Type getTypeFromModel(String name, Package pack) {
		if(pack == null) return {
			null
		}
		var ret = pack.getPackagedElement(name)
		if (ret == null || !(ret instanceof Type)) {
			return null
		}
		return ret as Type
	}

	private static String ansiUri = "pathmap://PapyrusC_Cpp_LIBRARIES/AnsiCLibrary.uml"
	public static String cppProfileUri = "pathmap://PapyrusC_Cpp_PROFILES/C_Cpp.profile.uml"
	public static String umlStandardProfileUri = "pathmap://UML_PROFILES/Standard.profile.uml"

	public static def importOrgetAModel(Model model, String uri) {
		var resource = model.eResource.resourceSet.resources.filter [
			it.URI.path.equals(uri)
		].head
		if (resource == null) {
			resource = model.eResource.resourceSet.createResource(URI.createURI(uri))
			resource.load(null)
		}
		var Package pack = null
		pack = resource.contents.filter(typeof(Model)).head
		return pack
	}

	public static def applyProfile(Model model, String uri) {
		var resource = model.eResource.resourceSet.getResource(URI.createURI(uri), false)
		
		if (resource == null) {
			resource = model.eResource.resourceSet.createResource(URI.createURI(uri))
			resource.load(null)
		}
		
		var profile = resource.contents.filter(typeof(Profile)).head
		if (profile != null && !model.isProfileApplied(profile)) {
			model.applyProfile(profile)
		}
	}

	public static def Operation findMatchOperation(Classifier classifier, IASTFunctionDeclarator declarator, String[] names) {
		var Operation ret = null;
		
		if (classifier != null && declarator != null && names != null) {
			if (names.length == 1) {
				// TODO: function not inside any classes
			}
			
			// Single parameter of type void ==> no parameters
			var nParmeters = declarator.children.filter(typeof(IASTParameterDeclaration)).size
			val parameterFunctionList = declarator.children.filter(typeof(IASTParameterDeclaration))
			
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
						nParmeters = 0
					}
				}
			}
			
			val numberParameter = nParmeters
			
			val functionName = names.get(names.length - 1)
			// TODO:check classifier name
			var sameNames = classifier.allOperations.filter[it.name.equals(functionName)]
			var sameNumberOfParameters = sameNames.filter [
				it.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL].size == numberParameter
			]
			// compare signatures by parameter type name
			for (op : sameNumberOfParameters) {
				if (ret == null) {
					var isMatch = true
					var parameterList = op.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL]
					for (var i = 0; i < numberParameter; i++) {
						if (!parameterList.get(i).type.name.equals(
							parameterFunctionList.get(i).declSpecifier.cppTypeName)) {
							isMatch = false
						} else {
							if (parameterList.get(i).name == null || parameterList.get(i).name.equals("")) {
								var paramName = parameterFunctionList.get(i).declarator.name
								
								if (paramName != null) {
									parameterList.get(i).name = paramName.toString
								}
							}
						}
					}
					if (isMatch) {
						ret = op
					}
				}
	
			}
		}
		
		return ret;
	}

	private def static String getCppTypeName(IASTDeclSpecifier declarator) {
		var String parameterTypeName = ""; // $NON-NLS-1$
		try {
			var token = declarator.syntax
			while (token != null) {
				var String tokenStr = token.toString()
				if (tokenStr.equals("*")) { // $NON-NLS-1$
					// TODO: check, if this can be called (depending on
					// * position with different semantics?)
					// isPointer = true;
				} else if (tokenStr.equals("&")) { // $NON-NLS-1$
					// isRef = true;
				} else if (tokenStr.equals("const")) { // $NON-NLS-1$
					// do nothing (use isConst() operation of
					// parameterType)
					// is not part of parameter type
				} else if (tokenStr.equals("volatile")) {
					// do nothing
				} else {
					if (parameterTypeName.length() > 0) {
						parameterTypeName += " "; // $NON-NLS-1$
					}
					parameterTypeName += tokenStr;
				}

				token = token.getNext();
			}
		} catch (ExpansionOverlapsBoundaryException e) {
		}
		return parameterTypeName
	}
	
	public static def List<IStructureTemplate> getAllStructureTemplates(IParent parent) {
		var List<IStructureTemplate> ret = new UniqueEList
		var ICElement[] childrend = parent.getChildren();
		for (var i = 0; i < childrend.length; i++) {
			var child = childrend.get(i)
			switch (child) {
				case child instanceof IStructureTemplate: {
					var istructureTemplate = childrend.get(i) as IStructureTemplate
					ret.add(istructureTemplate)					
				}
				case child instanceof IParent: {
					ret.addAll(getAllStructureTemplates(child as IParent))
				}
			}
		}
		
		return ret
	}
	
	public static final String EXTERNAL_PACKAGE_NAME = "external"
	
	public static def getOrcreateExternalPackage(Package parentPack, boolean create) {
		if (parentPack.getNestedPackage(EXTERNAL_PACKAGE_NAME) == null) {
			if (create) {
				var createdPack = parentPack.createNestedPackage(EXTERNAL_PACKAGE_NAME)
				StereotypeUtil.apply(createdPack, NoCodeGen)
			}
			
		}
		parentPack.getNestedPackage(EXTERNAL_PACKAGE_NAME)
	}
}
