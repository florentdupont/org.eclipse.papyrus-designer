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

package org.eclipse.papyrus.designer.languages.cpp.reverse.reverse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTEqualsInitializer;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTInitializer;
import org.eclipse.cdt.core.dom.ast.IASTInitializerClause;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTNodeSelector;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.IFunctionDeclaration;
import org.eclipse.cdt.core.model.IMethod;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.IParent;
import org.eclipse.cdt.core.model.ISourceRange;
import org.eclipse.cdt.core.model.ISourceReference;
import org.eclipse.cdt.core.model.IStructure;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.core.model.IVariableDeclaration;
import org.eclipse.cdt.core.model.IWorkingCopy;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ConstInit;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Constants;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.DependencyAnalysis;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.ReverseCpp2Uml;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.ReverseUtils;
import org.eclipse.papyrus.designer.languages.cpp.reverse.utils.RoundtripCppUtils;
import org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.Utils;
import org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.listener.ModelListener;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLUtil;

public class BatchReverseFunctionBody {

	public static final String sAtParam = "@param"; //$NON-NLS-1$

	public static final String ansiCLib = "AnsiCLibrary"; //$NON-NLS-1$
	public static final String[] ansiTypes = { "char", "double", "float",
			"int", "void", "long", "long double", "short", "unsigned int",
			"unsigned short", "unsigned char", "unsigned long", "bool",
			"int16", "uint16", "int32", "uint32", "int64", "uint64", "wchar_t",
			"int8_t", "uint8_t", "uint16_t", "int16_t", "uint32_t", "int32_t", "uint64_t", "int64_t" };

	public static final String[] names = {"UMLRTNotify"};

	private ITranslationUnit m_iTu;
	private Model m_model;
	ReverseCpp2Uml m_reverseCppUml;

	public BatchReverseFunctionBody(ITranslationUnit iTu, String projectName, ReverseCpp2Uml reverse) {
		m_iTu = iTu;
		m_projectName = projectName;
		this.m_reverseCppUml = reverse;
	}
	
	public BatchReverseFunctionBody(ITranslationUnit iTu, String projectName, ReverseCpp2Uml reverse, Classifier classifier) {
		m_iTu = iTu;
		m_projectName = projectName;
		m_reverseCppUml = reverse;
		m_classifier = classifier;
	}

	public BatchReverseFunctionBody(ITranslationUnit iTu, String projectName,
			Model model, ReverseCpp2Uml reverse) {
		m_iTu = iTu;
		m_projectName = projectName;
		this.m_model = model;
		this.m_reverseCppUml = reverse;
	}

	private List<Classifier> getOrCreateClassifiers(IParent iTu)
			throws Exception {
		List<Classifier> ret = new UniqueEList<Classifier>();
		ICElement[] childrend = iTu.getChildren();
		for (int i = 0; i < childrend.length; i++) {
			if (childrend[i] instanceof IStructure) {
				IStructure istructure = (IStructure) childrend[i];
				ret.add(RoundtripCppUtils
						.getOrCreateClassifier(istructure,
								istructure.getTranslationUnit(), m_projectName,
								m_model));
			} else if (childrend[i] instanceof IParent) {
				ret.addAll(getOrCreateClassifiers((IParent) childrend[i]));
			} else if (childrend[i] instanceof IVariableDeclaration || childrend[i] instanceof IMethod) {
				getTypeByQualifiedName(childrend[i], ret);
			}
		}
		return ret;
	}

	private void getTypeByQualifiedName(ICElement element, List<Classifier> results) {
		if (element instanceof IVariableDeclaration || element instanceof IMethod) {
			if (element.getElementName().contains("::")) {
				String[] classifierNameTokens = element.getElementName().split("::");
				String classifierName = "";
				if (classifierNameTokens.length > 1) {
					classifierName = classifierNameTokens[classifierNameTokens.length - 2];
				} else {
					classifierName = classifierNameTokens[0];
				}
				boolean isFound = false;
				for (Iterator<Classifier> iterator = results.iterator(); iterator.hasNext();) {
					Classifier classifier = (Classifier) iterator.next();
					if (classifier != null) {
						if (classifier.getName().equals(classifierName)) {
							isFound = true;
							break;
						}
					}
				}
				if (!isFound) {
					List<String> contextNamespaces = new UniqueEList<String>();
					String qualifiedTypeName = "";
					for (int i = 0; i < classifierNameTokens.length - 1; i++) {
						qualifiedTypeName += classifierNameTokens[i];
						if (i != classifierNameTokens.length - 2) {
							qualifiedTypeName += "::";
						}
					}
					contextNamespaces.add(qualifiedTypeName);
							
					Type typeRet = m_reverseCppUml.getUMLType(classifierName, m_iTu, contextNamespaces);
					if (typeRet instanceof Classifier) {
						results.add((Classifier)typeRet);
					}
				}
			}
		}
	}

	public boolean isAnsiType(String name) {
		for (String item : ansiTypes) {
			if (item.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public final String langID = "C/C++"; //$NON-NLS-1

	public void run() throws Exception {
		ModelListener.syncFromEditor = true;
		List<Classifier> classifiers = new UniqueEList<Classifier>();
		try {
			classifiers = getOrCreateClassifiers(m_iTu);
		} catch (CModelException e1) {
			e1.printStackTrace();
		}

		for (Iterator iterator = classifiers.iterator(); iterator.hasNext();) {
			m_classifier = (Classifier) iterator.next();
			// create classifier from itu
			ICProject project = CoreModel.getDefault().getCModel()
					.getCProject(m_projectName);
			IIndex index = null;
			try {
				index = CCorePlugin.getIndexManager().getIndex(project);
				index.acquireReadLock();

				// index = CCorePlugin.getIndexManager().getIndex(project);
				ITranslationUnit itu = m_iTu;
				// hack: force re-evaluation of AST node, requires modified CDT!
				// Seems to be no longer required.
				// ASTProvider.getASTProvider().fCache.setActiveElement(itu);

				IASTTranslationUnit ast = itu.getAST(index,
						ITranslationUnit.AST_CONFIGURE_USING_SOURCE_CONTEXT);
				IASTNodeSelector selector = ast.getNodeSelector(null);
				reverseCpp(itu, selector, itu);

				CUIPlugin.getDefault().getProblemMarkerManager();
				if (itu instanceof IWorkingCopy) {
					// ((IWorkingCopy)itu).commit(true, new
					// NullProgressMonitor());
					// ((IWorkingCopy)itu).reconcile();
					((IWorkingCopy) itu).reconcile(true,
							new NullProgressMonitor());
					// ((IWorkingCopy)itu).reconcile(true, true, new
					// NullProgressMonitor());
				}

				index.releaseReadLock();
			} catch (CModelException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (index != null) {
					index.releaseReadLock();
				}
			}
		}

		ModelListener.syncFromEditor = false;
	}

	private IASTTranslationUnit getIASTTranslationUnitHeader() {
		IASTTranslationUnit ret = null;

		return ret;
	}

	public void reverseHeader(IASTTranslationUnit header) {

	}

	public void reverseCpp(ITranslationUnit itu, IASTNodeSelector selector,
			IParent parent) {

		try {
			examineChildren(itu, selector, itu);
		} catch (CModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateCppInclude(itu);
	}

	/**
	 * Examine the children of a translation unit in order to extract the
	 * methods that are defined within hte unit
	 *
	 * @param itu
	 * @param selector
	 * @param parent
	 * @throws CModelException
	 */
	public void examineChildren(ITranslationUnit itu,
			IASTNodeSelector selector, IParent parent) throws CModelException {

		int position = 0;
		// if (parent instanceof Namespace) {
		for (ICElement child : parent.getChildren()) {
			if (child instanceof IParent) {
				examineChildren(itu, selector, (IParent) child);
			}

			ISourceRange range = null;
			if (child instanceof ISourceReference) {
				range = ((ISourceReference) child).getSourceRange();
			}
			if (child instanceof IFunctionDeclaration) {
				// function declaration is a superclass for method declaration
				// (but need to trace functions differently?)
				String name = ((IFunctionDeclaration) child).getElementName();
				IASTNode node = selector.findEnclosingNode(range.getStartPos(),
						range.getLength());
				if (node instanceof IASTFunctionDefinition) {
					IASTFunctionDefinition definition = (IASTFunctionDefinition) node;
					IASTFunctionDeclarator declarator = definition
							.getDeclarator();
					String body = getBody(itu, definition);
					Operation operation = updateMethod((IFunctionDeclaration) child, node, position, parent, name, body, declarator);
					if (operation != null) {
						new DependencyAnalysis(operation, definition, m_iTu, m_reverseCppUml).analyzeDependencies();
					}
				}
				position++;
			} else if (child instanceof IVariableDeclaration) {
				String name = ((IVariableDeclaration) child).getElementName();
				String typeName = ((IVariableDeclaration) child).getTypeName();
				String value = null;
				
				// Get value
				IASTNode node = selector.findEnclosingNode(range.getStartPos(), range.getLength());
				if (node instanceof IASTSimpleDeclaration) {
					IASTSimpleDeclaration declaration = (IASTSimpleDeclaration) node;
					
					if (declaration.getDeclarators().length > 0) {
						IASTDeclarator declarator = declaration.getDeclarators()[0];
						
						if (declarator != null) {
							IASTInitializer initializer = declarator.getInitializer();
							
							if (initializer instanceof IASTEqualsInitializer) {
								IASTEqualsInitializer equalsInitialiser = (IASTEqualsInitializer) initializer;
								IASTInitializerClause clause = equalsInitialiser.getInitializerClause();
								
								if (clause != null) {
									value = clause.getRawSignature();
									
									if (value != null) {
										updateProperty(name, typeName, value, m_classifier);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * update the contents of the CppInclude directive
	 *
	 * @param itu
	 *            the translation unit
	 */
	public void updateCppInclude(ITranslationUnit itu) {
		updateCppInclude(itu, m_classifier);
	}

	/**
	 * update the contents of the CppInclude directive
	 *
	 * @param itu
	 *            the translation unit
	 * @param classifier
	 *            the UML classifier
	 */
	public static void updateCppInclude(ITranslationUnit itu, Classifier classifier) {
		if (itu == null || classifier == null) {
			return;
		}

		String contents = new String(itu.getContents());
		int preBodyStart = contents.indexOf(Constants.cppIncPreBodyStart);
		int preBodyEnd = contents.indexOf(Constants.cppIncPreBodyEnd);
		String preBody = ""; //$NON-NLS-1$
		String body = ""; //$NON-NLS-1$
		if (preBodyStart != -1) {
			preBodyStart += Constants.cppIncPreBodyStart.length();
			if (preBodyEnd > preBodyStart) {
				preBody = contents.substring(preBodyStart, preBodyEnd).trim();
			}
		}

		int bodyStart = contents.indexOf(Constants.cppIncBodyStart);
		int bodyEnd = contents.indexOf(Constants.cppIncBodyEnd);

		if (bodyStart != -1) {
			bodyStart += Constants.cppIncBodyStart.length() + 1;
			if (bodyEnd > bodyStart) {
				body = contents.substring(bodyStart, bodyEnd).trim();
			}
		}
		if (body.length() > 0 || preBody.length() > 0) {
			Include Include = StereotypeUtil.applyApp(classifier,
					Include.class);
			Include.setPreBody(preBody);
			Include.setBody(body);
		}
	}

	private void updateProperty(String name, String typeName, String value, Classifier classifier) {
		if (name != null && typeName != null && value != null && classifier != null) {
			String[] nameTokens = name.split("::");
			if (nameTokens.length > 1) {
				name = nameTokens[nameTokens.length - 1];
			}

			String[] typeNameTokens = typeName.split("::");
			if (typeNameTokens.length > 1) {
				typeName = typeNameTokens[typeNameTokens.length - 1];
			}
			typeName = ReverseUtils.getCppTypeName(typeName);

			for (Property property : classifier.getAttributes()) {
				if (property.getName().equals(name) && property.getType() != null && property.getType().getName().equals(typeName)) {
					for (Element element : property.getOwnedElements()) {
						if (element instanceof ValueSpecification) {
							if (((ValueSpecification) element).getName().equals("defaultValue")) {
								element.destroy();
								break;
							}
						}
					}

					ValueSpecification valueSpecification = property.createDefaultValue("defaultValue", property.getType(), UMLPackage.Literals.OPAQUE_EXPRESSION);

					if (valueSpecification instanceof OpaqueExpression) {
						OpaqueExpression opaqueExpression = (OpaqueExpression) valueSpecification;
						opaqueExpression.getLanguages().add(ReverseCpp2Uml.Cpp_LangID);
						opaqueExpression.getBodies().add(value);
					}
				}
			}
		}
	}

	/**
	 * Update a method in the model while passing the qualified name
	 *
	 * @param position
	 *            The position of the method within the file. Used to identify
	 *            renaming operations
	 * @param parent
	 *            the CDT parent which is used to get a list of children
	 * @param qualifiedName
	 *            the qualified name of a method
	 * @param body
	 *            the method body
	 * @param declarator
	 *            the declarator for the method
	 */
	public Operation updateMethod(IFunctionDeclaration function, IASTNode functionNode, int position, IParent parent, String qualifiedName, String body, IASTFunctionDeclarator declarator) {

		String names[] = qualifiedName.split(Utils.nsSep);
		String name = names[names.length - 1];

		Operation operation = RoundtripCppUtils.findMatchOperation(m_classifier, declarator, names, m_reverseCppUml, m_iTu);

		if (operation == null) {
			// operation is not found via name in the model. That does not
			// necessarily mean that this is a new method.
			// It may also have been renamed.
			// Strategy: try to locate the operation in the model at the same
			// "position" as the method in the file and
			// verify that this method does not have the same name as any method
			// in the CDT file.
			/*if (position < m_classifier.getOperations().size()) {
				operation = m_classifier.getOperations().get(position);
				String modelName = operation.getName();
				try {
					for (ICElement child : parent.getChildren()) {
						if (child instanceof IMethodDeclaration) {
							String cdtName = ((IMethodDeclaration) child)
									.getElementName();
							if (cdtName.equals(modelName)) {
								// an existing operation in the CDT file already
								// has this name
								operation = null;
								break;
							}
						}
					}
				} catch (CModelException e) {
				}
			}
			if (operation != null) {
				operation.setName(name);
			} else {
				// still null => create new operation in model
				if (m_classifier instanceof Class) {
					operation = ((Class) m_classifier).createOwnedOperation(
							name, null, null);
				} else if (m_classifier instanceof DataType) {
					operation = ((DataType) m_classifier).createOwnedOperation(
							name, null, null);
				}
			} */
			return null;
		}
		
		if (function instanceof IMethodDeclaration) {
			IMethodDeclaration method = (IMethodDeclaration) function;
			try {
				if (method.isConstructor()) {
					StereotypeUtil.apply(operation, ConstInit.class);
					String initStr = ReverseCpp2Uml.getMemberInit(functionNode);
					if (!initStr.isEmpty()) {
						UMLUtil.getStereotypeApplication(operation, ConstInit.class).setInitialisation(initStr);
					}
				}
			} catch (CModelException e) {
				e.printStackTrace();
			}
		}

		OpaqueBehavior ob = null;
		if (operation.getMethods().size() == 0) {
			// does not exist, create

			if (m_classifier instanceof Class) {
				ob = (OpaqueBehavior) ((Class) m_classifier).createOwnedBehavior(name, UMLPackage.eINSTANCE.getOpaqueBehavior());
			}
			
			ob.setSpecification(operation);
			ob.setIsReentrant(false);
		} else {
			ob = (OpaqueBehavior) operation.getMethods().get(0);
			if (!ob.getName().equals(name)) {
				ob.setName(name);
			}
		}

		// a parameters.clear() is not sufficient. Otherwise stereotype
		// applications to unresolved elements remain in the model

		// UMLUtil.destroyElements(operation.getOwnedParameters());
		// UMLUtil.destroyElements(ob.getOwnedParameters());
		//
		// for (IASTNode declaratorChild : declarator.getChildren()) {
		// if (declaratorChild instanceof IASTParameterDeclaration) {
		// IASTParameterDeclaration parameter = (IASTParameterDeclaration)
		// declaratorChild;
		// IASTName parameterName = parameter.getDeclarator().getName();
		// IASTDeclSpecifier parameterType = parameter.getDeclSpecifier();
		// boolean isPointer = false;
		// boolean isRef = false;
		//				String array = ""; //$NON-NLS-1$
		//				String parameterTypeName = ""; //$NON-NLS-1$
		// String parameterTypeSimpleName = "";
		// try {
		// IToken token = parameter.getDeclarator().getSyntax();
		// while (token != null) {
		// String tokenStr = token.toString();
		//						if (tokenStr.equals("*")) { //$NON-NLS-1$
		// isPointer = true;
		//						} else if (tokenStr.equals("&")) { //$NON-NLS-1$
		// isRef = true;
		//						} else if (tokenStr.equals("[")) { //$NON-NLS-1$
		// while (token != null) {
		// array += token.toString();
		// token = token.getNext();
		// }
		// if (token == null) {
		// break;
		// }
		// }
		// token = token.getNext();
		// }
		//
		// token = parameterType.getSyntax();
		// while (token != null) {
		// String tokenStr = token.toString();
		//						if (tokenStr.equals("*")) { //$NON-NLS-1$
		// // TODO: check, if this can be called (depending on
		// // * position with different semantics?)
		// isPointer = true;
		//						} else if (tokenStr.equals("&")) { //$NON-NLS-1$
		// isRef = true;
		//						} else if (tokenStr.equals("const")) { //$NON-NLS-1$
		// // do nothing (use isConst() operation of
		// // parameterType)
		// // is not part of parameter type
		// } else {
		// if (parameterTypeName.length() > 0) {
		//								parameterTypeName += " "; //$NON-NLS-1$
		// }
		// parameterTypeName += tokenStr;
		// parameterTypeSimpleName = tokenStr;
		// }
		// token = token.getNext();
		// }
		// } catch (ExpansionOverlapsBoundaryException e) {
		// }
		// NamedElement umlParameterType = null;
		// List<NamedElement> foundTypes = new UniqueEList<NamedElement>();
		// List<Classifier> classifiers = IncQueryEngineSingleton
		// .getClassifierByName(m_classifier.eResource().getResourceSet(),
		// parameterTypeSimpleName);
		// for (Classifier classifier : classifiers) {
		// System.out.println(classifier.getQualifiedName());
		// if (parameterTypeName.equals(parameterTypeSimpleName)) {
		// if (Utils.getTop(m_classifier) == Utils
		// .getTop(classifier)) {
		// umlParameterType = classifier;
		// break;
		// } else {
		// foundTypes.add(classifier);
		// }
		// } else if (classifier.getQualifiedName().equals(
		// parameterTypeName)) {
		// umlParameterType = classifier;
		// break;
		// }
		// }
		//
		// if (foundTypes.size() > 0) {
		// // System.out.println("Ambiguous type, choose one automatically");
		// umlParameterType = foundTypes.get(0);
		// }
		//
		// // umlParameterType =
		// // Utils.getQualifiedElement(Utils.getTop(m_classifier),
		// // parameterTypeName);
		// Model ansiLib = null;
		// if (umlParameterType == null
		// && isAnsiType(parameterTypeSimpleName)) {
		// // umlParameterType =
		// // Utils.getQualifiedElement(Utils.getTop(m_classifier),
		// // ansiCLib + Utils.nsSep + parameterTypeName);
		// // import ansi lib package
		// ansiLib = org.eclipse.uml2.uml.util.UMLUtil
		// .load(m_classifier.eResource().getResourceSet(),
		// URI.createFileURI("platform://PapyrusC_Cpp_LIBRARIES//AnsiCLibrary.uml"),
		// UMLPackage.Literals.MODEL);
		// importPackage(
		// IncQueryEngineSingleton
		// .getSourceModel(m_classifier),
		// ansiLib);
		// umlParameterType = ansiLib
		// .getMember(parameterTypeSimpleName);
		// }
		// if (parameterType.isRestrict()) {
		// }
		// Parameter umlParameter = null;
		// if (umlParameterType instanceof Type) {
		// umlParameter = operation.createOwnedParameter(
		// parameterName.toString(), (Type) umlParameterType);
		// ob.createOwnedParameter(parameterName.toString(),
		// (Type) umlParameterType);
		// } else {
		// umlParameter = operation.createOwnedParameter(
		// parameterName.toString(), null);
		// ob.createOwnedParameter(parameterName.toString(), null);
		// }
		// if (parameterType.isConst()) {
		// StereotypeUtil.apply(umlParameter, Const.class);
		// }
		// if (parameterType.isVolatile()) {
		// StereotypeUtil.apply(umlParameter, Volatile.class);
		// }
		// if (isPointer) {
		// StereotypeUtil.apply(umlParameter, Ptr.class);
		// } else if (isRef) {
		// StereotypeUtil.apply(umlParameter, Ref.class);
		// }
		// if (array.length() > 0) {
		// Array arraySt = StereotypeUtil.applyApp(umlParameter,
		// Array.class);
		//					if (!array.equals("[]") && (!array.equals("[ ]"))) { //$NON-NLS-1$//$NON-NLS-2$
		// arraySt.setDefinition(array);
		// }
		// }
		//
		// }
		// }

		if (ob.getBodies().size() == 0) {
			ob.getLanguages().add(langID);
			ob.getBodies().add(""); //$NON-NLS-1$
		}
		for (int i = 0; i < ob.getLanguages().size(); i++) {
			if (ob.getLanguages().get(i).equals(langID)) {
				if (i < ob.getBodies().size()) { // should always be true,
					// unless sync between
					// languages/bodies is lost
					ob.getBodies().set(i, body);
				}
			}
		}
		return operation;
	}
	public static String getBody(ITranslationUnit itu,
			IASTFunctionDefinition definition) {
		IASTStatement body = definition.getBody();

		if (body instanceof IASTCompoundStatement) {
			IASTCompoundStatement bodyComp = (IASTCompoundStatement) body;

			IASTFileLocation bodyLoc = bodyComp.getFileLocation();
			int start = bodyLoc.getNodeOffset();
			int end = start + bodyLoc.getNodeLength();
			char contents[] = itu.getContents();
			// body contains enclosing { } which we need to remove (+2, -2). We
			// cannot use the
			// first and last statement, since leading and trailing comments are
			// not part
			// of the AST tree.

			//
			/*IASTStatement[] statements = bodyComp.getStatements();
			List<Classifier> typeList = new UniqueEList<Classifier>();
			for (int i = 0; i < statements.length; i++) {
				IASTDeclarationStatement statement = null;
				if (statements[i] instanceof IASTDeclarationStatement) {
					statement = (IASTDeclarationStatement) statements[i];
					String signature = ((CPPASTSimpleDeclaration) statement
							.getDeclaration()).getDeclSpecifier()
							.getRawSignature();
					// typeList.add(IncQueryEngineSingleton.getClassifierByName(signature).get(0));
				}

			}*/

			int indentation = getIndentation(contents);
			return Utils.decreaseIndent(contents, start + 2, end - 2, indentation);
		}
		return ""; //$NON-NLS-1$
	}

	private static int getIndentation(char[] contents) {
		if (contents != null && contents.length > 0) {
			String stringContent = new String(contents);
			int indentation = 4; // Default is 4 white spaces

			BufferedReader bufReader = new BufferedReader(new StringReader(stringContent));
			String line = null;

			try {
				while((line = bufReader.readLine()) != null)
				{
					char[] lineChars = line.toCharArray();
					int leadingSpaces = 0;
					for (int i = 0; i < lineChars.length; i++) {
						char c = lineChars[i];
						if (c == ' ') {
							leadingSpaces++;
						}

						if (leadingSpaces == 4) {
							break;
						}
					}

					if (leadingSpaces < indentation) {
						indentation = leadingSpaces;
					}
				}
			} catch (IOException e) {
				// Fail silently
				return 0;
			}

			return indentation;
		}

		return 0;
	}

	public void updateComment(ITranslationUnit itu,
			IASTFunctionDefinition definition, Operation operation) {
		IASTFileLocation bodyLoc = definition.getFileLocation();
		int start = bodyLoc.getNodeOffset() - 1;
		int end = start;
		char contents[] = itu.getContents();
		String comment = ""; //$NON-NLS-1$
		// backward scan for beginning /*
		while (start > 0) {
			if (contents[start] == '/' && contents[start + 1] == '*') {
				start += "/**".length(); // TODO: common string constants with generator //$NON-NLS-1$
				for (int i = start; i < end; i++) {
					comment += contents[i];
				}
				comment = comment.replace("\n * ", "\n"). //$NON-NLS-1$//$NON-NLS-2$
						replace("*/", "").trim(); //$NON-NLS-1$//$NON-NLS-2$
				break;
			}
			start--;
		}
		if (comment.length() > 0) {
			// filter @param
			int atParam = comment.indexOf(sAtParam);
			String commentMethodOnly = (atParam != -1) ? comment.substring(0,
					atParam).trim() : comment;

					EList<Comment> commentsUML = operation.getOwnedComments();
					Comment commentUML;
					if (commentsUML.size() == 0) {
						commentUML = operation.createOwnedComment();
						commentUML.getAnnotatedElements().add(commentUML);
					} else {
						commentUML = commentsUML.get(0);
					}
					while (atParam != -1) {
						int currentAtParam = atParam;
						atParam = comment.indexOf(sAtParam, atParam + 1);
						String commentParam = (atParam != -1) ? comment.substring(
								currentAtParam, atParam) : comment
								.substring(currentAtParam);
								Comment commentParamUML;
								int atParamName = sAtParam.length();

								while ((atParamName < commentParam.length())
										&& Character.isWhitespace(commentParam
												.charAt(atParamName))) {
									atParamName++;
								}
								int atParamNameEnd = atParamName;
								while ((atParamNameEnd < commentParam.length())
										&& !Character.isWhitespace(commentParam
												.charAt(atParamNameEnd))) {
									atParamNameEnd++;
								}
								if (atParamNameEnd < commentParam.length() - 1) {
									String parameterName = commentParam.substring(atParamName,
											atParamNameEnd);
									String commentParamText = commentParam.substring(
											atParamNameEnd).trim();
									Parameter parameter = operation.getOwnedParameter(
											parameterName, null, false, false);
									if (parameter != null) {
										EList<Comment> commentsParamUML = parameter
												.getOwnedComments();
										if (commentsParamUML.size() == 0) {
											commentParamUML = parameter.createOwnedComment();
											commentParamUML.getAnnotatedElements().add(
													commentParamUML);
										} else {
											commentParamUML = commentsParamUML.get(0);
										}
										commentParamUML.setBody(commentParamText);
									} else {
										// parameter is not found in model, e.g. either renamed
										// or not yet existing
										// store comment in operation comment
										commentMethodOnly += "\n " + sAtParam + parameterName + //$NON-NLS-1$
												" not found(!) " + commentParamText; //$NON-NLS-1$
									}
								}
					}
					commentUML.setBody(commentMethodOnly);
		}
	}

	protected Classifier m_classifier;

	protected String m_projectName;

}
