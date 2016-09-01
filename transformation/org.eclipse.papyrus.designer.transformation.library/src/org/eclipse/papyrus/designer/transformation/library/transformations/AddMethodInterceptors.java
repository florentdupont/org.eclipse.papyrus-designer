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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.designer.deployment.tools.ConfigUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepCreation;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.StUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.core.templates.TextTemplateBinding;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.profile.standard.Create;
import org.eclipse.uml2.uml.profile.standard.Destroy;

/**
 * A model-2-model transformation that merges a class with another.
 * different cases: (1) merge only
 * (2) some methods in the template intercept all operations of the other. This is required for instance for the state machine.
 * This class focuses on the interception utility
 * TODO: complete and test M2M transformation
 */
public class AddMethodInterceptors implements IM2MTrafoElem {

	private static final String XTEND_CPP_UTILS_CPP_CALL = "!template CppUtils.cppCall"; //$NON-NLS-1$

	public final String origOpPrefix = "orig_"; //$NON-NLS-1$

	LazyCopier copier;
	
	Package tmCDP;
	
	Class tmClass;
	
	/**
	 * Constructor
	 *
	 * @param copier
	 *            copier from source to target model
	 * @param tmCDP
	 *            deployment plan within target model
	 */
	public AddMethodInterceptors(LazyCopier copier, Package tmCDP) {
		this.copier = copier;
		this.tmCDP = tmCDP;
		interceptionOpMap = new HashMap<Operation, Operation>();
	}

	/**
	 * original operation => operation gets interception prefix
	 *
	 * Objectives: existing call operations call interception operation. Existing operations include CallOperationActions as well as calls within
	 * opaque behavior
	 * This is assured by keeping same model reference for the operation, but associating a new interception behavior, existing behavior will be
	 * associated
	 * with a new renamed operation
	 *
	 * @param operation
	 */
	protected Operation createInterceptionOperation(Operation operation) throws TransformationException {
		// create copy of operation
		Operation copiedOperation = EcoreUtil.copy(operation);
		tmClass.getOwnedOperations().add(copiedOperation);
		StUtils.copyStereotypes(operation, copiedOperation);

		copiedOperation.setName(origOpPrefix + operation.getName());

		// create interception code
		OpaqueBehavior b = (OpaqueBehavior) tmClass.createOwnedBehavior(operation.getName(), UMLPackage.eINSTANCE.getOpaqueBehavior());
		// TODO: solution is specific to C++ (and creates implicit dependency to modellibs.core which defines the C++ utils class)
		String body = TextTemplateBinding.bind(XTEND_CPP_UTILS_CPP_CALL, copiedOperation);
		body += ";";
		b.getLanguages().add("C/C++"); //$NON-NLS-1$
		b.getBodies().add(body);
		// copy existing methods into new operation, copy method list,
		// since adding the method to copied operation will remove these from original operation)
		EList<Behavior> methods = new BasicEList<Behavior>(operation.getMethods());
		for (Behavior behavior : methods) {
			copiedOperation.getMethods().add(behavior);
		}
		// new behavior is associated with existing call.
		b.setSpecification(operation);
		return copiedOperation;
	}
	
	/**
	 * Add interception code to all operations of the lwContainer.
	 * Can be called several times with different interception operations (which will then be concatenated)
	 *
	 * @throws TransformationException
	 */
	EList<Property> expandInterceptorExtension(Class mergeTemplateClass, Operation interceptionOperationInRule)
			throws TransformationException
	{
		for (Operation smOperation : operations) {
			Operation tmOperation = copier.getCopy(smOperation);
			String interceptionBody = getBody(interceptionOperationInRule);
			if (StereotypeUtil.isApplied(interceptionOperationInRule, Create.class) !=
				StereotypeUtil.isApplied(smOperation, Create.class)) {
				// if the intercepting operation is a constructor, the intercepted operation must also be a constructor
				continue;
			}
			if (StereotypeUtil.isApplied(interceptionOperationInRule, Destroy.class) !=
				StereotypeUtil.isApplied(smOperation, Destroy.class)) {
				// if the intercepting operation is a destructor, the intercepted operation must also be a destructor
				continue;
			}
			if (StereotypeUtil.isApplied(interceptionOperationInRule, ApplyTransformation.class)) {
				// pass operation in source model, since this enables Xtend code to check
				// for markers on model
				TransformationContext.current.classifier = tmClass;
				interceptionBody = TextTemplateBinding.bind(interceptionBody, smOperation, null);
			}
			if (interceptionBody.length() > 0) {
				// only add interception operation, if the interception is not empty
				interceptionBody = "// --- interception code from rule <" + mergeTemplateClass.getName() + "> ---\n" + //$NON-NLS-1$ //$NON-NLS-2$
						interceptionBody;
				Operation interceptionOpInClass = interceptionOpMap.get(tmOperation);
				if (interceptionOpInClass == null) {
					Operation copiedOperation = createInterceptionOperation(tmOperation);
					// the copied operation has all stereotypes of the original one, but it is
					// not a con/destructor (it is called by the con/destructor)
					if (StereotypeUtil.isApplied(copiedOperation, Create.class)) {
						StereotypeUtil.unapply(copiedOperation, Create.class);
					}
					if (StereotypeUtil.isApplied(copiedOperation, Destroy.class)) {
						StereotypeUtil.unapply(copiedOperation, Destroy.class);
					}
					// existing operation becomes interception operation (by assigning it a new behavior and moving its behavior to a new operation
					interceptionOpInClass = tmOperation;
					interceptionOpMap.put(tmOperation, interceptionOpInClass);
				}
				String newBody = interceptionBody + "\n" + //$NON-NLS-1$
						getBody(interceptionOpInClass);
				setBody(interceptionOpInClass, newBody);
			}
		}
		return null;
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

	// protected InstanceSpecification tmClassIS;
	protected Class smClass;

	protected EList<Operation> operations;

	protected Map<Operation, Operation> interceptionOpMap;

	/**
	 * @see AbstractContainerTrafo.createContainerInstance
	 *      In case of the lightweight container, there is no separate container instance. It is identical with the
	 *      executor instance (container and executor are merged).
	 *      The purpose of this operation is therefore the configuration of the instance rather than its creation
	 */
	public InstanceSpecification createContainerInstance(Class tmComponent, InstanceSpecification tmExecutorIS) throws TransformationException {
		InstanceSpecification containerIS = tmExecutorIS;
		// InstanceConfigurator.configureInstance(smContainerRule, containerIS, null, context);

		// now create instances for the contained elements
		// TODO: why not limits to parts?
		// TODO: risk of concurrent modification, if an additional attribute is created as a side effect
		for (Property extensionPart : tmComponent.getAttributes()) {
			Type tmContainerExtImpl = extensionPart.getType();
			if (tmContainerExtImpl instanceof Class) {
				if (DepUtils.getSlot(tmExecutorIS, extensionPart) == null) {
					// no slot for part exists => assume that the part has been added by the container and create an instance specification for it.
					InstanceSpecification containerExtIS = DepCreation.createDepPlan(tmCDP, (Class) tmContainerExtImpl, containerIS.getName() + "." + //$NON-NLS-1$
							extensionPart.getName(), false);
					// configure extension
					ConfigUtils.configureInstance(containerExtIS, extensionPart, null);
					DepCreation.createSlot(containerIS, containerExtIS, extensionPart);
				}
			}
		}
		return containerIS;
	}

	@Override
	public void transformElement(M2MTrafo trafo, Element element) throws org.eclipse.papyrus.designer.transformation.base.utils.TransformationException {
		// TODO Auto-generated method stub
		
	}
}
