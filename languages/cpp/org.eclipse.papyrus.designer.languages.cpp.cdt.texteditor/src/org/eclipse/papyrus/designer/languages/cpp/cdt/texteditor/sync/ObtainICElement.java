/*******************************************************************************
 * Copyright (c) 2015 CEA LIST.
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

import java.util.Iterator;

import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.IFunctionDeclaration;
import org.eclipse.cdt.core.model.IParent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen2;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.MethodInfo;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Transition;


/**
 * This class is used to select the operation the user wants to edit after opening the editor.
 * In case of transition, the selection is based on simple name only (either prefixed with the name separator or with an
 * underscore - based on the assumption that scoped names are either kept or flattened using underscore as separator
 *
 */
public class ObtainICElement {
	
	/**
	 * Return the ICelement associated with a UML element. Currently, only methods are supported.
	 *
	 * @param parent
	 *            the parent (typically the translation unit of a CDT file)
	 * @param element
	 *            A named UML element
	 * @throws CoreException
	 */
	public static ICElement getICElement(ILangCodegen codegen, IParent parent, NamedElement element) {
		MethodInfo methodInfo = null;
		NamedElement operationOrBehavior = element;
		if (element instanceof Transition) {
			operationOrBehavior = ((Transition) element).getEffect();
		}
		
		// no behavior found => not possible to locate element
		if (operationOrBehavior == null) {
			return null;
		}
		
		if (codegen instanceof ILangCodegen2) {
			// get generator specific method info.
			methodInfo = ((ILangCodegen2) codegen).getMethodInfo(operationOrBehavior);
		}
		if (methodInfo == null) {
			// use default behavior.
			if (element instanceof Behavior) {
				methodInfo = MethodInfo.fromBehavior((Behavior) operationOrBehavior);
			}
			else if (element instanceof Operation) {
				methodInfo = MethodInfo.fromOperation((Operation) operationOrBehavior);
			}
			else {
				return null;
			}
		}
		
		try {
			for (ICElement child : parent.getChildren()) {
				if (child instanceof IParent) {
					return getICElement(codegen, (IParent) child, element);
				}
				if (child instanceof IFunctionDeclaration) {
					IFunctionDeclaration function = (IFunctionDeclaration) child;
					
					// does the element name match? (CDT provides className::methodName information). Since we are in the scope of
					// the class, we only verify the postfix part of the name
					if (child.getElementName().endsWith(NamedElement.SEPARATOR + methodInfo.getName())) {
						// check, if parameters match. This handles most common overloading cases (it does not handle functions that
						// only differ with respect to the modifiers
						Iterator<String> paramIter = methodInfo.getParameterTypes().iterator();
						boolean match = methodInfo.getParameterTypes().size() == function.getParameterTypes().length;
						if (match) {
							for (String cdtParamType : function.getParameterTypes()) {
								String umlParamType = paramIter.next();
								if (!cdtParamType.equals(umlParamType)) {
									match = false;
									break;
								}
							}
							if (match) {
								return child;
							}
						}
					}
				}
			}
		} catch (CoreException e) {
		}
		return null;
	}

	/**
	 * Count the number of parameters without taking the return parameter into account
	 *
	 * @param list
	 * @return
	 */
	public static int countParameters(EList<Parameter> list) {
		int params = 0;
		for (Parameter par : list) {
			if (par.getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
				params++;
			}
		}
		return params;
	}
}
