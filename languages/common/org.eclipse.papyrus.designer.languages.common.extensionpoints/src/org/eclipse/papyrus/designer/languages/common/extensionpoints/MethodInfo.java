/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.common.extensionpoints;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;

/**
 * Additional information about a method that has been generated for a UML behavior.
 * This information is used by the CDT editor integration to locate the method in the code, but might be useful
 * for other languages as well.
 */
public class MethodInfo {
	
	/**
	 * Create a new method info with a given name. The parameter list is initially empty.
	 * Parameter types can be added with the method addParameterType
	 * can be initialized later.
	 * @param name
	 */
	public MethodInfo(String name) {
		this.name = name;
		this.parameterTypes = new ArrayList<String>();
	}

	/**
	 * Create a new method info
	 * @param name
	 * @param parameterTypes
	 */
	public MethodInfo(String name, List<String> parameterTypes) {
		this.name = name;
		this.parameterTypes = parameterTypes;
	}
	
	/**
	 * constructor creating a default method information instance from a given behavior
	 * @param behavior
	 */
	public static MethodInfo fromBehavior(Behavior behavior) {
		MethodInfo mi = new MethodInfo(behavior.getName());
		for (Parameter parameter : behavior.getOwnedParameters()) {
			if (parameter.getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
				if (parameter.getType() != null) {
					mi.addParameterType(parameter.getType().getQualifiedName());
				}
			}
			else {
				mi.addParameterType("undefined"); //$NON-NLS-1$
			}
		}
		return mi;
	}
	
	/**
	 * default initializer from a method
	 * @param behavior
	 */
	public static MethodInfo fromOperation(Operation operation) {
		MethodInfo mi = new MethodInfo(operation.getName());
		for (Parameter parameter : operation.getOwnedParameters()) {
			if (parameter.getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
				if (parameter.getType() != null) {
					mi.addParameterType(parameter.getType().getQualifiedName());
				}
			}
			else {
				mi.addParameterType("undefined"); //$NON-NLS-1$
			}
		}
		return mi;
	}
	
	/**
	 * Add a parameter type
	 * 
	 * @param parameterType the parameter type
	 */
	public void addParameterType(String parameterType) {
		parameterTypes.add(parameterType);
	}
	
	/**
	 * @return method name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return list of parameters types (in form of strings). These might contain additional
	 * modifiers, e.g. { "const char*" }
	 */
	public List<String> getParameterTypes() {
		return parameterTypes;
	}
	
	/**
	 * The name of the generated method.
	 */
	protected String name;
	
	/**
	 * The names of parameter types
	 */
	protected List<String> parameterTypes;
}
