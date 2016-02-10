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

import java.util.List
import java.util.regex.Matcher
import java.util.regex.Pattern
import org.eclipse.cdt.core.dom.ast.ExpansionOverlapsBoundaryException
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier
import org.eclipse.cdt.core.dom.ast.IASTDeclarator
import org.eclipse.cdt.core.dom.ast.IASTEqualsInitializer
import org.eclipse.cdt.core.dom.ast.IASTPointerOperator
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTArrayDeclarator
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTReferenceOperator
import org.eclipse.cdt.core.model.CoreModel
import org.eclipse.cdt.core.model.CoreModelUtil
import org.eclipse.cdt.core.model.ICContainer
import org.eclipse.cdt.core.model.ICElement
import org.eclipse.cdt.core.model.ICProject
import org.eclipse.cdt.core.model.IEnumeration
import org.eclipse.cdt.core.model.IInclude
import org.eclipse.cdt.core.model.INamespace
import org.eclipse.cdt.core.model.IParent
import org.eclipse.cdt.core.model.IStructure
import org.eclipse.cdt.core.model.IStructureDeclaration
import org.eclipse.cdt.core.model.ITranslationUnit
import org.eclipse.cdt.core.model.ITypeDef
import org.eclipse.emf.common.util.UniqueEList
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Const
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Friend
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Inline
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Mutable
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ref
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Variadic
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Virtual
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Volatile
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.OpaqueExpression
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.TypedElement
import org.eclipse.uml2.uml.UMLPackage
import org.eclipse.uml2.uml.ValueSpecification
import org.eclipse.uml2.uml.util.UMLUtil

/**
 * Utility methods for the reverse
 *
 */

class ReverseUtils {
	enum StereotypeType {
		CONST,
		VOLATILE,
		ARRAY,
		POINTER,
		REFERENCE,
		EXTERNAL,
		FRIENDLINE,
		VIRTUAL,
		INLINE,
		CREATE,
		DESTROY,
		MUTABLE,
		VARIADIC
	}
	
	private static ReverseUtils instance
	
	public static def getInstance() {
		if (instance == null) {
			instance = new ReverseUtils
		}
		
		instance
	}
	
	
	public def analyzeDeclaration(List<IASTDeclarator> declarators, Type type, TypedElement typedElement, String langID) {
		for (declarator : declarators) {
			declarator.analyzeDeclaration(type, typedElement, langID)
		}

	}

	public def analyzeDeclaration(IASTDeclarator declarator, Type type, TypedElement typedElement, String langID) {
		if (declarator instanceof ICPPASTArrayDeclarator) {
			var arrayDeclarator = declarator as ICPPASTArrayDeclarator
			var arrays = arrayDeclarator.arrayModifiers
			if (arrays.size > 0) {
				if (StereotypeUtil.isApplied(typedElement, Array)) {
					StereotypeUtil.unapply(typedElement, Array)
				}
				applyStereotype(typedElement, true, StereotypeType.ARRAY, "")
				arrays.forEach [
					var expr = it.constantExpression
					if (expr != null) {
						var definition = UMLUtil.getStereotypeApplication(typedElement, Array).definition
						UMLUtil.getStereotypeApplication(typedElement, Array).definition = definition + "[" +
							expr.toString + "]"
					}

				]
			}
		}
		
		if (declarator.pointerOperators != null) {
			if (StereotypeUtil.isApplied(typedElement, Ptr)) {
				UMLUtil.getStereotypeApplication(typedElement, Ptr).declaration = ""	
			}
			for (pointerOperator : declarator.pointerOperators) {
				if (pointerOperator instanceof ICPPASTReferenceOperator) {
					applyStereotype(typedElement, true, StereotypeType.REFERENCE, pointerOperator.syntax.toString)
				} else if (pointerOperator instanceof IASTPointerOperator) {
					var pointer = pointerOperator as IASTPointerOperator
					if (!StereotypeUtil.isApplied(typedElement, Ptr)) {
						applyStereotype(typedElement, true, StereotypeType.POINTER, pointer.syntax.toString)
					} else {
						var value = UMLUtil.getStereotypeApplication(typedElement, Ptr).declaration
						UMLUtil.getStereotypeApplication(typedElement, Ptr).declaration = value +
							pointer.syntax.toString
					}
				}
			}
			
			if (StereotypeUtil.isApplied(typedElement, Ptr)) {
				var value = UMLUtil.getStereotypeApplication(typedElement, Ptr).declaration
				
				var Pattern pattern = Pattern.compile("(\\*)([\\s]*)(const)");
				var Matcher matcher = pattern.matcher(declarator.rawSignature);
				if (matcher.find()) {
					value += " const"
					UMLUtil.getStereotypeApplication(typedElement, Ptr).declaration = value
				}
				
				if (value.equals("*")) {
					UMLUtil.getStereotypeApplication(typedElement, Ptr).declaration = ""
				}
			}
		}

		// parse initial values
		var initilizer = declarator.initializer
		var valueExisting = typedElement.ownedElements.filter(typeof(ValueSpecification)).filter[it.name.equals("defaultValue")].head
		if (valueExisting != null) {
			valueExisting.destroy
		}
		if (initilizer != null) {
			var ValueSpecification vs = null
			if (typedElement instanceof Property) {
				vs = (typedElement as Property).createDefaultValue("defaultValue", typedElement.type,
					UMLPackage.Literals.OPAQUE_EXPRESSION)
			} else if (typedElement instanceof Parameter) {
				vs = (typedElement as Parameter).createDefaultValue("default", typedElement.type,
					UMLPackage.Literals.OPAQUE_EXPRESSION)
			}

			if (vs == null) {
				return
			}
			var oe = vs as OpaqueExpression
			oe.getLanguages().add(langID);
			if (initilizer instanceof IASTEqualsInitializer) {
				var equalsInitialiser = initilizer as IASTEqualsInitializer
				var clause = equalsInitialiser.initializerClause
				if (clause != null) {
					oe.bodies.add(clause.rawSignature)
				}
			}
		}
	}
	
	public def applyStereotype(Element element, boolean isApply, StereotypeType stType, String additional) {
		if (!isApply) {
			return
		}
		switch (stType) {
			case CONST: {
				StereotypeUtil.apply(element, Const)
			}
			case VOLATILE: {
				StereotypeUtil.apply(element, Volatile)
			}
			case ARRAY: {
				StereotypeUtil.apply(element, Array)
				UMLUtil.getStereotypeApplication(element, Array).definition = additional
			}
			case POINTER: {
				StereotypeUtil.apply(element, Ptr)
				UMLUtil.getStereotypeApplication(element, Ptr).declaration = additional
			}
			case REFERENCE: {
				StereotypeUtil.apply(element, Ref)
			}
			case EXTERNAL: {
				StereotypeUtil.apply(element, External)
			}
			case FRIENDLINE: {
				StereotypeUtil.apply(element, Friend)
			}
			case VIRTUAL: {
				StereotypeUtil.apply(element, Virtual)
			}
			case INLINE: {
				StereotypeUtil.apply(element, Inline)
			}
			case CREATE: {
				StereotypeUtil.apply(element, "StandardProfile::Create")
			}
			case DESTROY: {
				StereotypeUtil.apply(element, "StandardProfile::Destroy")
			}
			case MUTABLE: {
				StereotypeUtil.apply(element, Mutable)
			}
			case VARIADIC: {
				StereotypeUtil.apply(element, Variadic)
			}
		}
	}
	
	public def String getCppTypeName(IASTDeclSpecifier declarator) {
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
	
	public static def String getCppTypeName(String name) {
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
	
	public def List<ICElement> getAllIStructures(IParent parent, boolean allIncludes, boolean lookNestedTypes, ICProject project) { // including structure included
		var List<ICElement> ret = new UniqueEList
		var ICElement[] childrend = parent.getChildren();
		for (var i = 0; i < childrend.length; i++) {
			var child = childrend.get(i)
			switch (child) {
				case child instanceof IStructure: {
					ret.add(child as IStructure)
					if (lookNestedTypes) {
						ret.addAll(getAllIStructures(child as IStructure, allIncludes, lookNestedTypes, project))
					}

				}
				case child instanceof IEnumeration: {
					ret.add(child as IEnumeration)
				}
				case child instanceof IParent: {
					ret.addAll(getAllIStructures(child as IParent, allIncludes, lookNestedTypes, project))
				}
				case child instanceof IInclude: {
					if (allIncludes) {
						var unit = getTranslationUnitFromInclude(child as IInclude, project)
						if (unit != null) {
							ret.addAll(getAllIStructures(unit, allIncludes, lookNestedTypes, project))
						}
					}

				}
				case child instanceof ITypeDef: {
					ret.add(child)
				}
				case child instanceof IStructureDeclaration: {
					ret.add(child)
				}
			}
		}
		return ret
	}
	
	public def ITranslationUnit getTranslationUnitFromInclude(IInclude include, ICProject project) {
		if (!include.fullFileName.contains(project.elementName)) {
			return null
		}
		var ITranslationUnit ret = null
		var fileIncludePath = include.fullFileName.split(project.elementName).last
		var file = project.project.getFile(fileIncludePath)
		if (file != null) {
			var ITranslationUnit unit = CoreModelUtil.findTranslationUnit(file);
			if (unit == null) {
				unit = CoreModel.getDefault().createTranslationUnitFrom(project, file.getLocation());
			}
			ret = unit
		}
		return ret
	}
	
	public def getTranslationUnitFromElement(ICElement element) {
		var owner = element.parent
		while (owner != null && !(owner instanceof ITranslationUnit)) {
			owner = owner.parent
		}
		return owner as ITranslationUnit
	}

	public def isSatisfyNamespace(List<String> iUsings, ICElement istructure) {
		if (!(istructure.parent instanceof INamespace) && !(istructure.parent instanceof IStructure)) {
			return true
		}
		val namespaces = new StringBuilder
		var ICElement owner = istructure.parent
		namespaces.append(owner.elementName)
		while (owner.parent instanceof INamespace || owner.parent instanceof IStructure) {
			namespaces.insert(0, (owner.parent as INamespace).elementName + "::")
			owner = owner.parent
		}

		return !(iUsings.filter[it.equals(namespaces.toString) || it.contains(namespaces.toString.trim)].empty)
	}
	
	public def List<String> getContextNamespaces(ICElement element) {
		var List<String> ret = new UniqueEList
		var owner = element.parent
		while (owner != null) {
			if (owner instanceof INamespace || owner instanceof IStructure || owner instanceof ICContainer) {
				ret.add(owner.elementName)
				if (ret.length > 1) {
					var n = ret.head
					var len = ret.length
					for (var i = 1; i < len; i++) {
						if (!ret.get(i).contains("::")) {
							n = ret.get(i) + "::" + n
						}
					}
					ret.add(n)
				}
			}
			owner = owner.parent
		}
		return ret
	}
	
	public def Package getNearestPackage(Element element) {
		return element.nearestPackage
	}
	
	def unapplyAllStereotypes(Element element) {
		val stereotypeAppList = element.stereotypeApplications
		stereotypeAppList.forEach[
			val stereotype = UMLUtil.getStereotype(it)
			element.unapplyStereotype(stereotype)
		]
	}
}