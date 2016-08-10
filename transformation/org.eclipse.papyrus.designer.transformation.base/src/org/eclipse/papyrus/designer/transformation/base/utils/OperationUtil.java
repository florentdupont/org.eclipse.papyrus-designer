/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
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

package org.eclipse.papyrus.designer.transformation.base.utils;

import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;

public class OperationUtil {

	/**
	 * Search an identical operation within a class.
	 * This function is useful to identify (and synchronize) operations.
	 *
	 * @param op
	 *            The operation that should be compared.
	 * @param owner
	 * @return
	 */
	public static Operation getSameOperation(Operation op, Class owner) {
		for (Operation ownedOp : owner.getOwnedOperations()) {
			if (isSameOperation(op, ownedOp)) {
				return ownedOp;
			}
		}
		return null;
	}

	/**
	 * Returns true, if two operations are identical.
	 * An operation is considered identical, if the operation name as well as all
	 * parameter names and types are equal. Type equality is checked via names only, to
	 * avoid that identical types within a source and a copy model would yield false.
	 *
	 * @param op1
	 *            first operation
	 * @param op2
	 *            second operation
	 * @return true, if operations are identical
	 */
	public static boolean isSameOperation(Operation op1, Operation op2) {
		return isSameOperation(op1, op2, true);
	}

	/**
	 * Check whether a certain stereotype is applied to an arbitrary operation of a classifier.
	 * This is for instance useful to verify whether a class has a constructor or destructor
	 * 
	 * @param cl
	 *            A classifier to verify
	 * @param stereotype
	 *            The stereotype that may be applied to one of its operations
	 * @return true, if the passed stereotype is applied to one of the operations
	 */
	public static boolean isOperationStereotypeApplied(Classifier cl, java.lang.Class<? extends EObject> stereotype) {
		for (Operation op : cl.getOperations()) {
			if (StereotypeUtil.isApplied(op, stereotype)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true, if two operations are identical with optional name check.
	 * An operation is considered identical, if the operation name (optional) as well as all
	 * parameter names and types are equal. Type equality is checked via names only, to
	 * avoid that identical types within a source and a copy model would yield false.
	 *
	 * @param op1
	 *            first operation
	 * @param op2
	 *            second operation
	 * @param checkName
	 *            if true, require that operation names are identical
	 * @return true, if operations are identical
	 */
	public static boolean isSameOperation(Operation op1, Operation op2, boolean checkName) {

		if (checkName) {
			String op1Name = op1.getName();
			String op2Name = op2.getName();
			// one of the names might be null
			if (op1Name == null) {
				if (op2Name != null) {
					return false;
				}
			} else if (!op1Name.equals(op2Name)) {
				return false;
			}
		}
		Iterator<Parameter> parameters1 = op1.getOwnedParameters().iterator();
		Iterator<Parameter> parameters2 = op2.getOwnedParameters().iterator();
		while (parameters1.hasNext() && parameters2.hasNext()) {
			Parameter parameter1 = parameters1.next();
			Parameter parameter2 = parameters2.next();
			String par1Name = parameter1.getName();
			Type par1Type = parameter1.getType();
			String par2Name = parameter2.getName();
			Type par2Type = parameter2.getType();

			// one of the names might be null
			if (par1Name == null) {
				if (par2Name != null) {
					return false;
				}
			} else if (!par1Name.equals(par2Name)) {
				return false;
			}
			if ((par1Type != null) && (par2Type != null)) {
				// the names might be null. Return false, if only one of these is set
				if (par1Type.getName() == null) {
					if (par2Type.getName() != null) {
						return false;
					}
				} else if (!par1Type.getName().equals(par2Type.getName())) {
					return false;
				}
			} else if (par1Type != par2Type) {
				// at least one of the two must be null due to earlier check
				// return false, if they are different, i.e. not both null
				return false;
			}
			if ((parameter1.getUpper() != parameter2.getUpper()) || (parameter1.getLower() != parameter2.getLower())) {
				return false;
			}
			if (parameter1.getStereotypeApplications().size() != parameter2.getStereotypeApplications().size()) {
				return false;
			}
			Iterator<Stereotype> par2StereoIter = parameter2.getAppliedStereotypes().iterator();
			for (Stereotype par1Stereo : parameter1.getAppliedStereotypes()) {
				// don't need to check whether iterator has next, since size has already been compared.
				Stereotype par2Stereo = par2StereoIter.next();
				if (par1Stereo != par2Stereo) {
					return false;
				}
			}
		}
		// true, if operations have same number of parameters
		return (parameters1.hasNext() == parameters2.hasNext());
	}


	/**
	 * synchronize source and target operation
	 * (remove all parameters, copy afterwards)
	 *
	 * @param sourceOp
	 *            the source operation
	 * @param targetOp
	 *            the target operation
	 * @param newRoot
	 *            a new root package (e.g. another model). Ensure that types referenced by the
	 *            parameter exist within this root package (copy, if not). Null indicates that no copying
	 *            should be done.
	 * @return
	 */
	public static void syncOperation(Operation sourceOp, Operation targetOp) {
		if (targetOp != null) {
			CopyUtil.copyFeatureModifiers(sourceOp, targetOp);
			// ordered and unique are derived from ret-parameter
			targetOp.setIsQuery(sourceOp.isQuery());
			targetOp.setIsAbstract(sourceOp.isAbstract());
			targetOp.setName(sourceOp.getName());
			targetOp.getOwnedParameters().clear();
			for (Parameter parameter : sourceOp.getOwnedParameters()) {
				Type type = parameter.getType();
				Parameter newParameter = targetOp.createOwnedParameter(parameter.getLabel(), type);
				newParameter.setDirection(parameter.getDirection());
				CopyUtil.copyMultElemModifiers(parameter, newParameter);
				StUtil.copyStereotypes(parameter, newParameter);
			}
		}
		StUtil.copyStereotypes(sourceOp, targetOp);
	}

	/**
	 * Convenience method enabling to apply getOwnedOperation equally to classes
	 * and interfaces
	 *
	 * @param cl
	 * @param name
	 * @param paramNames
	 * @param paramTypes
	 * @return
	 */
	public static Operation createOwnedOperation(Classifier cl, String name, EList<String> paramNames, EList<Type> paramTypes, Type retType) {
		if (cl instanceof Class) {
			return ((Class) cl).createOwnedOperation(name, paramNames, paramTypes, retType);
		} else if (cl instanceof Interface) {
			return ((Interface) cl).createOwnedOperation(name, paramNames, paramTypes, retType);
		} else {
			return null;
		}
	}

	/**
	 * @param operation
	 * @return all in and inout parameters of an operation
	 */
	public static EList<Parameter> parametersInInout(Operation operation) {
		EList<Parameter> list = new BasicEList<Parameter>();
		for (Parameter parameter : operation.getOwnedParameters()) {
			if ((parameter.getDirection().getValue() == ParameterDirectionKind.IN) || (parameter.getDirection().getValue() == ParameterDirectionKind.INOUT)) {
				list.add(parameter);
			}
		}
		return list;
	}

	/**
	 * @param operation
	 *            an operation
	 * @return all non-return parameters of the passed operation
	 */
	public static EList<Parameter> parametersNonRet(Operation operation) {
		EList<Parameter> list = new BasicEList<Parameter>();
		for (Parameter parameter : operation.getOwnedParameters()) {
			if (parameter.getDirection().getValue() != ParameterDirectionKind.RETURN) {
				list.add(parameter);
			}
		}
		return list;
	}

	/**
	 * @param operation
	 *            an operation
	 * @return all out, inout and return parameters of the passed operation
	 */
	public static EList<Parameter> parametersOutInout(Operation operation) {
		EList<Parameter> list = new BasicEList<Parameter>();
		for (Parameter parameter : operation.getOwnedParameters()) {
			if ((parameter.getDirection().getValue() == ParameterDirectionKind.OUT) || (parameter.getDirection().getValue() == ParameterDirectionKind.RETURN) || (parameter.getDirection().getValue() == ParameterDirectionKind.INOUT)) {
				list.add(parameter);
			}
		}
		return list;
	}

	/**
	 * Return the name of a parameter with a specific handling of return parameters:
	 * Always return the name "retValue" in case of the latter. This is quite useful for
	 * marshalling operations (which need to assign a value to the return parameter, which
	 * is sometimes not initialized)
	 *
	 * @param parameter
	 *            a UML parameter
	 * @return the parameter name or retValue in case of a return parameter
	 */
	public static String paramName(Parameter parameter) {
		if (parameter.getDirection().getValue() == ParameterDirectionKind.RETURN) {
			return "retValue"; //$NON-NLS-1$
		} else {
			return parameter.getName();
		}
	}
}
