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

import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.cdt.core.dom.ast.IASTCaseStatement
import org.eclipse.cdt.core.dom.ast.IASTCastExpression
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement
import org.eclipse.cdt.core.dom.ast.IASTDeclarationStatement
import org.eclipse.cdt.core.dom.ast.IASTDefaultStatement
import org.eclipse.cdt.core.dom.ast.IASTDoStatement
import org.eclipse.cdt.core.dom.ast.IASTEqualsInitializer
import org.eclipse.cdt.core.dom.ast.IASTExpression
import org.eclipse.cdt.core.dom.ast.IASTExpressionList
import org.eclipse.cdt.core.dom.ast.IASTExpressionStatement
import org.eclipse.cdt.core.dom.ast.IASTFieldReference
import org.eclipse.cdt.core.dom.ast.IASTForStatement
import org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition
import org.eclipse.cdt.core.dom.ast.IASTIdExpression
import org.eclipse.cdt.core.dom.ast.IASTIfStatement
import org.eclipse.cdt.core.dom.ast.IASTInitializerClause
import org.eclipse.cdt.core.dom.ast.IASTLiteralExpression
import org.eclipse.cdt.core.dom.ast.IASTReturnStatement
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration
import org.eclipse.cdt.core.dom.ast.IASTStatement
import org.eclipse.cdt.core.dom.ast.IASTSwitchStatement
import org.eclipse.cdt.core.dom.ast.IASTWhileStatement
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTCatchHandler
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTFunctionCallExpression
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTNewExpression
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTRangeBasedForStatement
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTSimpleTypeConstructorExpression
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTTryBlockStatement
import org.eclipse.cdt.core.model.ITranslationUnit
import org.eclipse.papyrus.designer.languages.cpp.reverse.utils.RoundtripCppUtils
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.Usage

/**
 * Analysis dependencies in a C++ method body
 *
 */
class DependencyAnalysis {
	private ReverseUtils reverseUtils = ReverseUtils.instance
	private Operation m_operation
	private IASTFunctionDefinition m_definition
	private ITranslationUnit m_itu
	private ReverseCpp2Uml m_reverseCppUml
	private Classifier m_classifier
	private Map<String, Type> localVaribaleList = new HashMap

	new(Operation op, IASTFunctionDefinition definition, ITranslationUnit itu, ReverseCpp2Uml reverseCppUml) {
		this.m_operation = op
		this.m_definition = definition
		this.m_itu = itu
		this.m_reverseCppUml = reverseCppUml
		if (op.eContainer instanceof Classifier) {
			m_classifier = op.eContainer as Classifier
		}
	}

	public def analyzeDependencies() {
		val body = m_definition.body
		if (body != null) {
			analyzeStatement(body)
		}
	}

	private def void analyzeStatement(IASTStatement statement) {
		if (statement == null) {
			return
		}
		switch (statement) {
			case statement instanceof IASTCompoundStatement: {
				(statement as IASTCompoundStatement).analyzeStatement
			}
			case statement instanceof ICPPASTCatchHandler: {
				(statement as ICPPASTCatchHandler).analyzeStatement
			}
			case statement instanceof ICPPASTRangeBasedForStatement: {
				(statement as ICPPASTRangeBasedForStatement).analyzeStatement
			}
			case statement instanceof ICPPASTTryBlockStatement: {
				(statement as ICPPASTTryBlockStatement).analyzeStatement
			}
			case statement instanceof IASTCaseStatement: {
				(statement as IASTCaseStatement).analyzeStatement
			}
			case statement instanceof IASTDeclarationStatement: {
				(statement as IASTDeclarationStatement).analyzeStatement
			}
			case statement instanceof IASTDefaultStatement: {
				(statement as IASTDefaultStatement).analyzeStatement
			}
			case statement instanceof IASTDoStatement: {
				(statement as IASTDoStatement).analyzeStatement
			}
			case statement instanceof IASTExpressionStatement: {
				(statement as IASTExpressionStatement).analyzeStatement
			}
			case statement instanceof IASTForStatement: {
				(statement as IASTForStatement).analyzeStatement
			}
			case statement instanceof IASTIfStatement: {
				(statement as IASTIfStatement).analyzeStatement
			}
			case statement instanceof IASTReturnStatement: {
				(statement as IASTReturnStatement).analyzeStatement
			}
			case statement instanceof IASTSwitchStatement: {
				(statement as IASTSwitchStatement).analyzeStatement
			}
			case statement instanceof IASTWhileStatement: {
				(statement as IASTWhileStatement).analyzeStatement
			}
		}
	}

	private def void analyzeStatement(IASTCompoundStatement statement) {
		var statements = (statement as IASTCompoundStatement).statements
		statements.forEach [
			it.analyzeStatement
		]
	}

	private def void analyzeStatement(ICPPASTCatchHandler statement) {
	}

	private def void analyzeStatement(ICPPASTRangeBasedForStatement statement) {
	}

	private def void analyzeStatement(ICPPASTTryBlockStatement statement) {
	}

	private def void analyzeStatement(IASTCaseStatement statement) {
	}

	private def void analyzeStatement(IASTDeclarationStatement statement) {
		if (statement.declaration instanceof IASTSimpleDeclaration) {
			var declaration = statement.declaration as IASTSimpleDeclaration
			if (declaration != null) {
				var specifier = declaration.declSpecifier
				var typeName = reverseUtils.getCppTypeName(specifier)
				if (!RoundtripCppUtils.isPrimitiveCppType(typeName)) {
					var namespaces = reverseUtils.getContextNamespaces(m_itu)
					val umlType = m_reverseCppUml.getUMLType(typeName, m_itu, namespaces)
					if (umlType != null) {
						declaration.declarators.forEach [
							localVaribaleList.put(it.name.toString, umlType)
						]
						createDependency(umlType)
					}
				}
				declaration.declarators.forEach [
					var initilizer = it.initializer
					if (initilizer != null) {
						if (initilizer instanceof IASTEqualsInitializer) {
							var equalsInitialiser = initilizer as IASTEqualsInitializer
							var clause = equalsInitialiser.initializerClause
							if (clause instanceof IASTExpression) {
								clause.analyzeExpression
							}
						}
					}
				]
			}
		}
	}

	private def void createDependency(NamedElement suplier) {
		if (m_operation.owner instanceof NamedElement) {
			var nearestPack = reverseUtils.getNearestPackage(m_operation)
			if (nearestPack != null) {
				var usages = nearestPack.ownedElements.filter(typeof(Usage)).toList
				var usage = usages.filter[it.clients.contains(m_operation.owner) && it.suppliers.contains(suplier)].head
				if (usage == null) {
					(m_operation.owner as NamedElement).createUsage(suplier)
				}
			}
		}
	}

	private def void analyzeStatement(IASTDefaultStatement statement) {
		// statement.get
	}

	private def void analyzeStatement(IASTDoStatement statement) {
		statement.body.analyzeStatement
	}

	private def void analyzeStatement(IASTExpressionStatement statement) {
		var expression = statement.expression
		expression.analyzeExpression
	}

	private def Type getTypeOfVariableOrAttribute(String attributeName) {
		var type = localVaribaleList.get(attributeName)
		if (type == null) {
			// look attributes has same name
			var attribute = m_classifier.getAllAttributes().filter[it.name.equals(attributeName)].head
			if (attribute != null) {
				type = attribute.type
			}
		}
		return type
	}

	private def isSameSignature(Operation op, List<Type> l2) {
		var params = op.ownedParameters.filter[it.direction != ParameterDirectionKind.RETURN_LITERAL].toList.map [
			it.type
		]
		return params.isSameTypeList(l2)
	}

	private def isSameTypeList(List<Type> l1, List<Type> l2) {
		if (l1.size != l2.size) {
			return false
		}

		for (var i = 0; i < l1.size; i++) {
			if (l1.get(i) != l2.get(i)) {
				return false
			}
		}

		return true
	}

	private def Type analyzeExpression(IASTExpression expression) {
		if (expression == null) {
			return null
		}
		var Type ret = null
		if (expression instanceof ICPPASTFunctionCallExpression) {
			var nameExpression = expression.functionNameExpression
			var arguments = expression.arguments
			var returnParam = null as Parameter
			if (nameExpression instanceof IASTFieldReference) {
				val functionName = nameExpression.fieldName
				val fieldOwner = nameExpression.fieldOwner
				var Classifier targetClass = null
				if (fieldOwner instanceof IASTIdExpression) {
					var type = getTypeOfVariableOrAttribute(fieldOwner.name.toString)
					if (type instanceof Classifier) {
							// look attributes has same name
						targetClass = type
					}
				} else if (fieldOwner instanceof IASTLiteralExpression) {
					if (fieldOwner.rawSignature == "this") {
						targetClass = m_classifier
					}
				}
				if (targetClass != null) {
					val sameNameOps = targetClass.allOperations.filter[it.name.equals(functionName.toString)].toList
					if (sameNameOps.size > 1) {
						// look for all types of arguments of current function call
						val currentArgumentTypes = arguments.map [
							it.argumentType
						].toList
						var targetOp = sameNameOps.filter[it.isSameSignature(currentArgumentTypes)].head
						if (targetOp != null) {
							createDependency(targetOp)
							returnParam = targetOp.ownedParameters.filter [
								it.direction == ParameterDirectionKind.RETURN_LITERAL
							].head
						}

					} else if (sameNameOps.head != null) {
						createDependency(sameNameOps.head)
						returnParam = sameNameOps.head.ownedParameters.filter [
							it.direction == ParameterDirectionKind.RETURN_LITERAL
						].head
					}
				}
			}
			if (returnParam != null) {
				ret = returnParam.type
			}
		} else if (expression instanceof IASTFunctionCallExpression) {
		} else if (expression instanceof ICPPASTNewExpression) {
			var typeName = reverseUtils.getCppTypeName(expression.typeId.declSpecifier)
			ret = m_reverseCppUml.getUMLType(typeName, m_itu, reverseUtils.getContextNamespaces(m_itu))
		} else if (expression instanceof ICPPASTSimpleTypeConstructorExpression) {
		} else if (expression instanceof IASTCastExpression) {
			var typeName = reverseUtils.getCppTypeName(expression.typeId.declSpecifier)
			ret = m_reverseCppUml.getUMLType(typeName, m_itu, reverseUtils.getContextNamespaces(m_itu))
		} else if (expression instanceof IASTExpressionList) {
			expression.expressions.forEach [
				it.analyzeExpression
			]
		} else {
			if (expression.children != null && expression.children.size > 0) {
				expression.children.forEach [
					if (it instanceof IASTExpression) {
						it.analyzeExpression
					}
				]
			}
		}
		return ret
	}

	private def Type getArgumentType(IASTInitializerClause argument) {
		var Type argType = null
		if (argument instanceof IASTExpression) {
			argType = argument.analyzeExpression
		}
		return argType
	}

	private def void analyzeStatement(IASTForStatement statement) {
	}

	private def void analyzeStatement(IASTReturnStatement statement) {
	}

	private def void analyzeStatement(IASTSwitchStatement statement) {
		var body = statement.body
		body.analyzeStatement
	}

	private def void analyzeStatement(IASTWhileStatement statement) {
		statement.body.analyzeStatement
	}
	
	private def void analyzeStatement(IASTIfStatement statement) {
		statement.conditionExpression.analyzeExpression
		if (statement.thenClause != null) {
			statement.thenClause.analyzeStatement
		}
		if (statement.elseClause != null) {
			statement.elseClause.analyzeStatement
		}
	}
}