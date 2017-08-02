/*******************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ansgar Radermacher - ansgar.radermacher@cea.fr CEA LIST - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.sync;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.dom.ast.ExpansionOverlapsBoundaryException;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTNodeSelector;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.model.CModelException;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.IFunctionDeclaration;
import org.eclipse.cdt.core.model.IMethodDeclaration;
import org.eclipse.cdt.core.model.IParent;
import org.eclipse.cdt.core.model.ISourceRange;
import org.eclipse.cdt.core.model.ISourceReference;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.core.model.IWorkingCopy;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.ui.CDTUITools;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen2;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.SyncInformation;
import org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.TextEditorConstants;
import org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.Utils;
import org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.listener.ModelListener;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Constants;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Const;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.EStorageClass;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ref;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.StorageClass;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Volatile;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.papyrus.uml.tools.utils.UMLUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;

public class SyncCDTtoModel implements Runnable {

	public static final String REGISTER = "register"; //$NON-NLS-1$

	public static final String CONST = "const";	 //$NON-NLS-1$

	public static final String VOLATILE = "volatile"; //$NON-NLS-1$

	public static final String sAtParam = "@param"; //$NON-NLS-1$

	public static final String ansiCLib = "AnsiCLibrary"; //$NON-NLS-1$

	public SyncCDTtoModel(IEditorInput input, Classifier classifier, String projectName, String generatorID) {
		m_input = input;
		m_classifier = classifier;
		m_projectName = projectName;
		m_codegen = LanguageCodegen.getGenerator(TextEditorConstants.CPP, generatorID);
	}
	

	public final String c_cpp_langID = "C/C++"; //$NON-NLS-1$

	public void syncCDTtoModel() {
		CommandSupport.exec(m_classifier, "update model from CDT", this);
	}

	@Override
	public void run() {
		ICElement ice = CDTUITools.getEditorInputCElement(m_input);
		ModelListener.syncFromEditor = true;

		if (ice instanceof ITranslationUnit) {
			ICProject project = CoreModel.getDefault().getCModel().getCProject(m_projectName);

			IIndex index = null;
			try {
				index = CCorePlugin.getIndexManager().getIndex(project);
				index.acquireReadLock();

				// index = CCorePlugin.getIndexManager().getIndex(project);
				ITranslationUnit itu = (ITranslationUnit) ice;
				// hack: force re-evaluation of AST node, requires modified CDT!
				// Seems to be no longer required.
				// ASTProvider.getASTProvider().fCache.setActiveElement(itu);

				IASTTranslationUnit ast = itu.getAST(index, ITranslationUnit.AST_SKIP_INDEXED_HEADERS);
				IASTNodeSelector selector = ast.getNodeSelector(null);

				examineChildren(itu, selector, itu);
				updateCppInclude(itu);

				CUIPlugin.getDefault().getProblemMarkerManager();
				if (itu instanceof IWorkingCopy) {
					// ((IWorkingCopy)itu).commit(true, new
					// NullProgressMonitor());
					// ((IWorkingCopy)itu).reconcile();
					((IWorkingCopy) itu).reconcile(true, new NullProgressMonitor());
					// ((IWorkingCopy)itu).reconcile(true, true, new
					// NullProgressMonitor());
				}

			} catch (CModelException e) {
				Activator.getDefault().getLog().log(e.getStatus());
			} catch (Exception e) {
				System.err.println(e);
			} finally {
				if (index != null) {
					index.releaseReadLock();
				}
			}
		}
		ModelListener.syncFromEditor = false;
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
	public void examineChildren(ITranslationUnit itu, IASTNodeSelector selector, IParent parent)
			throws CModelException {

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
				IASTNode node = selector.findEnclosingNode(range.getStartPos(), range.getLength());
				if (node instanceof IASTFunctionDefinition) {
					IASTFunctionDefinition definition = (IASTFunctionDefinition) node;
					IASTFunctionDeclarator declarator = definition.getDeclarator();
					String unfilteredBody = getBody(itu, definition);
					// get additional information about method synchronization from generator
					SyncInformation syncInfo = null;
					if (m_codegen instanceof ILangCodegen2) {
						syncInfo = ((ILangCodegen2) m_codegen).getSyncInformation(name, unfilteredBody);
					}
					String body = Utils.removeGenerated(unfilteredBody);
					if (syncInfo == null || !syncInfo.isGenerated) {
						// only update method, if it is not generated 
						NamedElement ne = updateMethod(position, parent, name, body, declarator, syncInfo);
						updateComment(itu, definition, ne);
					}
					// System.err.println("body source <" + body + ">");
				}
				position++;
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
			Include include = StereotypeUtil.applyApp(m_classifier, Include.class);
			if (include != null) {
				include.setPreBody(preBody);
				include.setBody(body);
			}
		}
	}

	/**
	 * Update a method in the model based on the qualified name.
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
	 * @return the operation or the behavior within the model that got updated. The latter is returned in
	 *            case of behaviors that do not have a specification (e.g. the effect of a transition).
	 */
	public NamedElement updateMethod(int position, IParent parent, String qualifiedName, String body,
			IASTFunctionDeclarator declarator, SyncInformation syncInfo) {

		String names[] = qualifiedName.split(Utils.nsSep);
		String name = names[names.length - 1];

		Operation operation = null;
		Behavior behavior = null;

		if (syncInfo == null || (syncInfo.behavior == null && syncInfo.createBehaviorName == null)) {
			operation = getModelOperationFromName(name, parent, position);
			if (operation != null) {
				operation.setName(name);
			} else {
				// it is possible that the C++ method corresponds to the effect
				// of a transition. try to locate the behavior (without using an operation)
				behavior = FindTransition.findBehavior(m_classifier, name);
				if (behavior == null) {
					// still null => create new operation in model
					if (m_classifier instanceof Class) {
						operation = ((Class) m_classifier).createOwnedOperation(name, null, null);
					} else if (m_classifier instanceof DataType) {
						operation = ((DataType) m_classifier).createOwnedOperation(name, null, null);
					}
				}
			}
		}
		else if (syncInfo.behavior != null) {
			// operation is still null (=> does not enter operation != null case below)
			behavior = syncInfo.behavior;
		}
		else if ((syncInfo.createBehaviorName != null) && (m_classifier instanceof Class)) {
			Class clazz = (Class) m_classifier;
			behavior = (OpaqueBehavior) clazz.createOwnedBehavior(syncInfo.createBehaviorName,
					UMLPackage.eINSTANCE.getOpaqueBehavior().eClass());
		}

		if (operation != null) {
			if (operation.getMethods().size() == 0) {
				// operation exists, but does not have any method => create

				if (m_classifier instanceof Class) {
					behavior = ((Class) m_classifier).createOwnedBehavior(name, UMLPackage.eINSTANCE.getOpaqueBehavior());
				} else if (m_classifier instanceof DataType) {
					// ob = (OpaqueBehavior) ((DataType)
					// m_classifier).createOwnedBehavior(name,
					// UMLPackage.eINSTANCE.getOpaqueBehavior());
				}
				behavior.setSpecification(operation);
				behavior.setIsReentrant(false);
			}
			else {
				behavior = operation.getMethods().get(0);
				// operation has at least one method, this may not be null.
				if (!behavior.getName().equals(name)) {
					behavior.setName(name);
				}
			}
		}

		// assertions: operation can be null, behavior is always non-null
		
		// Remove all parameters from operation / behavior (they will be added later).
		// Calling parameters.clear() is not sufficient. Otherwise stereotype
		// applications to unresolved elements remain in the model
		if (operation != null) {
			UMLUtil.destroyElements(operation.getOwnedParameters());
		}
		UMLUtil.destroyElements(behavior.getOwnedParameters());
	
		for (IASTNode declaratorChild : declarator.getChildren()) {
			if (declaratorChild instanceof IASTParameterDeclaration) {
				IASTParameterDeclaration parameter = (IASTParameterDeclaration) declaratorChild;
				IASTName parameterName = parameter.getDeclarator().getName();
				IASTDeclSpecifier parameterType = parameter.getDeclSpecifier();
				ParameterModifiers modifiers = new ParameterModifiers();
				String parameterTypeName = ""; //$NON-NLS-1$
				try {
					IToken token = parameter.getDeclarator().getSyntax();
					while (token != null) {
						String tokenStr = token.toString();
						if (tokenStr.equals("*")) { //$NON-NLS-1$
							modifiers.isPointer = true;
						} else if (tokenStr.equals("&")) { //$NON-NLS-1$
							modifiers.isRef = true;
						} else if (tokenStr.equals("[")) { //$NON-NLS-1$
							while (token != null) {
								modifiers.array += token.toString();
								token = token.getNext();
							}
							if (token == null) {
								break;
							}
						}
						token = token.getNext();
					}

					token = parameterType.getSyntax();
					while (token != null) {
						String tokenStr = token.toString();
						if (tokenStr.equals("*")) { //$NON-NLS-1$
							// TODO: check, if this can be called (depending on
							// * position with different semantics?)
							modifiers.isPointer = true;
						} else if (tokenStr.equals("&")) { //$NON-NLS-1$
							modifiers.isRef = true;
						} else if (tokenStr.equals(REGISTER)) {
							modifiers.isRegister = true;
						} else if (tokenStr.equals(CONST) || tokenStr.equals(VOLATILE)) {
							// do nothing (use isConst() or isVolatile() operation of parameterType)
							// is not part of parameter type
						} else {
							if (parameterTypeName.length() > 0) {
								parameterTypeName += " "; //$NON-NLS-1$
							}
							parameterTypeName += tokenStr;
						}
						token = token.getNext();
					}
				} catch (ExpansionOverlapsBoundaryException e) {
				}

				NamedElement namedElemParamType = Utils.getQualifiedElement(Utils.getTop(m_classifier),
						parameterTypeName);
				if (namedElemParamType == null) {
					namedElemParamType = Utils.getQualifiedElement(Utils.getTop(m_classifier),
							ansiCLib + Utils.nsSep + parameterTypeName);
				}
				if (parameterType.isRestrict()) {
				}
				Parameter umlParameter = null;
				Type paramType = namedElemParamType instanceof Type ? (Type) namedElemParamType : null;
				if (operation != null) {
					umlParameter = operation.createOwnedParameter(parameterName.toString(), paramType);
					applyParameterModifiers(parameterType, umlParameter, modifiers);
				}
				umlParameter = behavior.createOwnedParameter(parameterName.toString(), paramType);
				applyParameterModifiers(parameterType, umlParameter, modifiers);
			}
		}

		if (behavior instanceof OpaqueBehavior) {
			OpaqueBehavior ob = (OpaqueBehavior) behavior;
			if (ob.getBodies().size() == 0) {
				ob.getLanguages().add(c_cpp_langID);
				ob.getBodies().add(""); //$NON-NLS-1$
			}
			for (int i = 0; i < ob.getLanguages().size(); i++) {
				// update first body of one of the languages supported by CDT. This implies that
				// it is actually not possible to have separate C and C++ bodes in the same opaque
				// behavior (which is rarely a good idea).
				String language = ob.getLanguages().get(i);
				if (TextEditorConstants.CPP.matcher(language).matches() || c_cpp_langID.equals(language)) {
					if (i < ob.getBodies().size()) {
						// should always be true, unless sync between
						// languages/bodies is lost
						ob.getBodies().set(i, body);
					}
				}
			}
		}
		if (operation != null) {
			return operation;
		}
		else {
			return behavior;
		}
	}

	/**
	 * Apply the modifiers for a parameter, notably the stereotypes of the C++ profile
	 * 
	 * @param parameterType the CDT AST parameter specification
	 * @param umlParameter the UML parameter (to which a stereotype should be applied)
	 * @param modifiers the modifiers that should be applied (stored in an instance of class ParameterModifiers)
	 */
	public void applyParameterModifiers(IASTDeclSpecifier parameterType, Parameter umlParameter, ParameterModifiers modifiers) {
		if (parameterType.isConst()) {
			StereotypeUtil.apply(umlParameter, Const.class);
		}
		if (parameterType.isVolatile()) {
			StereotypeUtil.apply(umlParameter, Volatile.class);
		}
		if (modifiers.isRegister) {
			StorageClass sc = StereotypeUtil.applyApp(umlParameter, StorageClass.class);
			if (sc != null) {
				sc.setStorageClass(EStorageClass.REGISTER);
			}
		}
		if (modifiers.isPointer) {
			StereotypeUtil.apply(umlParameter, Ptr.class);
		} else if (modifiers.isRef) {
			StereotypeUtil.apply(umlParameter, Ref.class);
		}
		if (modifiers.array.length() > 0) {
			Array arraySt = StereotypeUtil.applyApp(umlParameter, Array.class);
			if (arraySt != null && !modifiers.array.equals("[]") && (!modifiers.array.equals("[ ]"))) { //$NON-NLS-1$//$NON-NLS-2$
				arraySt.setDefinition(modifiers.array);
			}
		}
	}
	
	/**
	 * Obtain an operation from the model by using the name of a CDT method.
	 * If an operation of the given name does not exist, it might indicate that
	 * the method has been renamed.
	 * 
	 * @param name the operation name within CDT
	 * @param parent the parent of the CDT method within CDT editor model
	 * @param position the position within the other methods. This information is used to locate methods
	 *   within the model that might have been renamed in the CDT editor.
	 * @return
	 */
	public Operation getModelOperationFromName(String name, IParent parent, int position) {
		Operation operation = m_classifier.getOperation(name, null, null);
		
		if (operation == null) {
			// operation is not found via name in the model. try to locate the operation in the model at the same
			// "position" as the method in the file and
			// verify that this method does not have the same name as any method
			// in the CDT file.
			if (position < m_classifier.getOperations().size()) {
				operation = m_classifier.getOperations().get(position);
				String modelName = operation.getName();
				try {
					for (ICElement child : parent.getChildren()) {
						if (child instanceof IMethodDeclaration) {
							String cdtName = ((IMethodDeclaration) child).getElementName();
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
		}
		return operation;
	}
	
	public static String getBody(ITranslationUnit itu, IASTFunctionDefinition definition) {
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
			// not part of the AST tree.
			return Utils.decreaseIndent(contents, start + 2, end - 2);
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * update a comment of a named element. Besides the comment of the element itself, comments on contained
	 * parameters are handled.
	 * 
	 * @param itu a translation unit
	 * @param definition
	 * @param ne a named element that is either an operation or a behavior (in order to update parameters)
	 */
	public void updateComment(ITranslationUnit itu, IASTFunctionDefinition definition, NamedElement ne) {
		IASTFileLocation bodyLoc = definition.getFileLocation();
		int start = bodyLoc.getNodeOffset() - 1;
		int end = start;
		char contents[] = itu.getContents();
		String comment = ""; //$NON-NLS-1$
		// backward scan for beginning /*
		while (start > 0) {
			if (contents[start] == '/' && contents[start + 1] == '*') {
				start += "/**".length(); // TODO: common string //$NON-NLS-1$
											// constants with generator
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
			String commentMethodOnly = (atParam != -1) ? comment.substring(0, atParam).trim() : comment;

			EList<Comment> commentsUML = ne.getOwnedComments();
			Comment commentUML;
			if (commentsUML.size() == 0) {
				commentUML = ne.createOwnedComment();
				commentUML.getAnnotatedElements().add(commentUML);
			} else {
				commentUML = commentsUML.get(0);
			}
			while (atParam != -1) {
				int currentAtParam = atParam;
				atParam = comment.indexOf(sAtParam, atParam + 1);
				String commentParam = (atParam != -1) ? comment.substring(currentAtParam, atParam)
						: comment.substring(currentAtParam);
				Comment commentParamUML;
				int atParamName = sAtParam.length();

				while ((atParamName < commentParam.length())
						&& Character.isWhitespace(commentParam.charAt(atParamName))) {
					atParamName++;
				}
				int atParamNameEnd = atParamName;
				while ((atParamNameEnd < commentParam.length())
						&& !Character.isWhitespace(commentParam.charAt(atParamNameEnd))) {
					atParamNameEnd++;
				}
				if (atParamNameEnd < commentParam.length() - 1) {
					String parameterName = commentParam.substring(atParamName, atParamNameEnd);
					String commentParamText = commentParam.substring(atParamNameEnd).trim();
					Parameter parameter = null;
					if (ne instanceof BehavioralFeature) {
						parameter = ((BehavioralFeature) ne).getOwnedParameter(parameterName, null, false, false);
					}
					else if (ne instanceof Behavior) {
						parameter = ((Behavior) ne).getOwnedParameter(parameterName, null, false, false);		
					}
					if (parameter != null) {
						EList<Comment> commentsParamUML = parameter.getOwnedComments();
						if (commentsParamUML.size() == 0) {
							commentParamUML = parameter.createOwnedComment();
							commentParamUML.getAnnotatedElements().add(commentParamUML);
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

	/**
	 * Accessor
	 * @return value of codegen attribute
	 */
	public ILangCodegen getCodeGen() {
		return m_codegen;
	}
	
	/**
	 * input of the CDT editor. Used to obtain code within editor.
	 */
	protected IEditorInput m_input;

	/**
	 * The classifier (class) that is currently edited
	 */
	protected Classifier m_classifier;

	/**
	 * name of CDT project in which the generated code is stored.
	 */
	protected String m_projectName;
	
	/**
	 * reference to code generator
	 */
	protected ILangCodegen m_codegen;
}
