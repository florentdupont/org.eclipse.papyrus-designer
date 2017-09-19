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

package org.eclipse.papyrus.designer.transformation.library.transformations;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.designer.deployment.tools.ConfigUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepCreation;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.OperationUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.StUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier.CopyStatus;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.library.Messages;
import org.eclipse.papyrus.designer.transformation.library.transformations.bindinghelpers.BindOperation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.profile.standard.Create;
import org.eclipse.uml2.uml.profile.standard.Destroy;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * A model-2-model transformation that merges a class with another.
 */
public class MergeClass implements IM2MTrafoElem {

	LazyCopier copier;

	/**
	 * Merge a class
	 *
	 * @param mergeTemplateClass
	 *            the class to merge into an existing class
	 * @param tmClass
	 *            the class which should be merged
	 * @throws TransformationException
	 */
	public void mergeClass(Class mergeTemplateClass, Class tmClass) throws TransformationException {
		// dependencies of the rule become dependencies of the class that applies this rule.
		for (Dependency dependency : mergeTemplateClass.getClientDependencies()) {
			Dependency dependencyCopy = EcoreUtil.copy(dependency);
			tmClass.getClientDependencies().add(dependencyCopy);
		}

		boolean hasConstructor = OperationUtils.isOperationStereotypeApplied(tmClass, Create.class);
		boolean hasDestructor = OperationUtils.isOperationStereotypeApplied(tmClass, Destroy.class);

		// register relation to facilitate attribute copy
		copier.setPackageTemplate(mergeTemplateClass, tmClass);
		// reset status to in-progress. Otherwise, the copier will not properly add new
		// elements.
		copier.setStatus(tmClass, CopyStatus.INPROGRESS);

		for (Operation templateOperation : mergeTemplateClass.getOperations()) {
			// Need a specific treatment of Constructor/destructor: if original class has a
			// constructor, must add to all constructors, if it has none, copy constructor
			boolean templateOpIsConstructor = StereotypeUtil.isApplied(templateOperation, Create.class);
			boolean templateOpIsDestructor = StereotypeUtil.isApplied(templateOperation, Destroy.class);

			boolean needsMerge = (templateOpIsConstructor && hasConstructor) || (templateOpIsDestructor && hasDestructor);
			// check, whether the operation has a specific binding hind (in this case, do not copy it)
			ApplyTransformation trafoOnOpSt = UMLUtil.getStereotypeApplication(templateOperation, ApplyTransformation.class);
			boolean isTemplate = false;
			if (trafoOnOpSt != null) {
				for (M2MTrafo stTrafo : trafoOnOpSt.getTrafo()) {
					IM2MTrafo trafoOnOp = M2MTrafoExt.getM2MTrafo(stTrafo);
					if (trafoOnOp instanceof BindOperation) {
						isTemplate = true;
						break;
					}
				}
			}
			if (needsMerge) {
				// reset package template. Make sure not to use template map, otherwise methods of original class might be duplicated
				copier.setPackageTemplate(null, null);

				mergeOperations(tmClass, mergeTemplateClass, templateOperation);

			} else if (!isTemplate) {
				copier.setPackageTemplate(mergeTemplateClass, tmClass);
				// normal operation. Copy from container to class
				// TODO: C++/Java specific
				Operation newOperation = copier.getCopy(templateOperation);
				if (templateOpIsConstructor) {
					newOperation.setName(tmClass.getName());
				} else if (templateOpIsDestructor) {
					newOperation.setName("~" + tmClass.getName()); //$NON-NLS-1$
				}
			}
		}

		copier.setPackageTemplate(mergeTemplateClass, tmClass);

		for (Property part : mergeTemplateClass.getAllAttributes()) {
			Type type = part.getType();
			if (type == null) {
				String ruleName = (mergeTemplateClass.getName() != null) ? mergeTemplateClass.getName() : "undefined"; //$NON-NLS-1$
				throw new TransformationException(String.format(Messages.MergeClass_CannotApply0, ruleName));
			}
			Property newPart = EcoreUtil.copy(part);
			StUtils.copyStereotypes(part, newPart);
			tmClass.getOwnedAttributes().add(newPart);
		}
	}

	
	/**
	 * Merge an operation of the template with all operations in a class.
	 * This is primarily used for merging constructor and destructors, but can also be used to get inject common code
	 * into all operations.
	 *
	 * @throws TransformationException
	 */
	void mergeOperations(Class tmClass, Class mergeTemplateClass, Operation mergeOperation) throws TransformationException {
		for (Operation tmOperation : tmClass.getOwnedOperations()) {
			String mergeBody = getBody(mergeOperation);
			if (StereotypeUtil.isApplied(mergeOperation, Create.class) != StereotypeUtil.isApplied(tmOperation, Create.class)) {
				// if the merge operation is a constructor, the intercepted operation must also be a constructor
				continue;
			}
			if (StereotypeUtil.isApplied(mergeOperation, Destroy.class) != StereotypeUtil.isApplied(tmOperation, Destroy.class)) {
				// if the merge operation is a destructor, the intercepted operation must also be a destructor
				continue;
			}
			if (mergeBody.length() > 0) {
				// only add merge body, if not empty, TODO: C++/Java specific comment
				mergeBody = "// --- merged from template <" + mergeTemplateClass.getName() + "> ---\n" + //$NON-NLS-1$ //$NON-NLS-2$
						mergeBody;
				String newBody = getBody(tmOperation) + mergeBody;
				setBody(tmOperation, newBody);
			}
		}
	}

	public static String getBody(Operation op) {
		for (Behavior behavior : op.getMethods()) {
			if (behavior instanceof OpaqueBehavior) {
				EList<String> bodies = ((OpaqueBehavior) behavior).getBodies();
				if (bodies.size() > 0) {
					// always take first
					return bodies.get(0);
				}
			}
		}
		return ""; //$NON-NLS-1$
	}

	public static void setBody(Operation op, String body) {
		for (Behavior behavior : op.getMethods()) {
			if (behavior instanceof OpaqueBehavior) {
				EList<String> bodies = ((OpaqueBehavior) behavior).getBodies();
				if (bodies.size() > 0) {
					// always take first
					bodies.set(0, body);
					break;
				}
			}
		}
	}

	/**
	 * Configure the merged instance, i.e. create sub-instances for parts
	 * @param mergedInstance
	 * @throws TransformationException
	 */
	public void configureMergedInstance(InstanceSpecification mergedInstance, Class tmClass) throws TransformationException {

		Package tmCDP = mergedInstance.getNearestPackage();
		for (Property part : DepUtils.getParts(tmClass)) {
			Type typeFromTemplate = part.getType();
			if (typeFromTemplate instanceof Class) {
				if (DepUtils.getSlot(mergedInstance, part) == null) {
					// no slot for part exists => assume that the part has been added by the container and create an instance specification for it.
					InstanceSpecification containerExtIS = DepCreation.createDepPlan(tmCDP, (Class) typeFromTemplate, mergedInstance.getName() + "." + //$NON-NLS-1$
							part.getName(), false);
					// configure extension (TODO required, since instance configurators will be called anyway later?)
					ConfigUtils.configureInstance(containerExtIS, part, null);
					DepCreation.createSlot(mergedInstance, containerExtIS, part);
				}
			}
		}
	}

	@Override
	public void transformElement(M2MTrafo trafo, Element element) throws org.eclipse.papyrus.designer.transformation.base.utils.TransformationException {
		if (element instanceof Class) {
			Class tmClass = (Class) element;
			copier = TransformationContext.current.copier;
			mergeClass(trafo.getBase_Class(), tmClass);
		}
		else if (element instanceof InstanceSpecification) {
			InstanceSpecification is = (InstanceSpecification) element;
			Classifier cl = DepUtils.getClassifier(is);
			if (cl instanceof Class) {
				configureMergedInstance(is, (Class) cl);
			}
		}
	}
}
