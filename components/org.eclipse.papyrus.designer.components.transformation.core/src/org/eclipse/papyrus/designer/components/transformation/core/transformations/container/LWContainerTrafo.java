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

package org.eclipse.papyrus.designer.components.transformation.core.transformations.container;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.FCM.InterceptionRule;
import org.eclipse.papyrus.designer.components.FCM.Template;
import org.eclipse.papyrus.designer.components.transformation.core.Messages;
import org.eclipse.papyrus.designer.components.transformation.core.StUtils;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepCreation;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepUtils;
import org.eclipse.papyrus.designer.components.transformation.core.extensions.InstanceConfigurator;
import org.eclipse.papyrus.designer.components.transformation.core.templates.TemplateInstantiation;
import org.eclipse.papyrus.designer.components.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.components.transformation.core.templates.TextTemplateBinding;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.AbstractContainerTrafo;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier.CopyStatus;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.profile.standard.Create;
import org.eclipse.uml2.uml.profile.standard.Destroy;

/**
 * A light-weight container transformation. Unlike the standard component-based transformation, this transformation does not add a new class.
 * It is target for standard object-oriented architecture, enabling in particular to intercept the methods of a class (instead of
 * adding interceptors to ports).
 *
 */
public class LWContainerTrafo extends AbstractContainerTrafo {

	private static final String XTEND_CPP_UTILS_CPP_CALL = "!xtend CppUtils.cppCall"; //$NON-NLS-1$

	public final String origOpPrefix = "orig_"; //$NON-NLS-1$

	/**
	 * Constructor
	 *
	 * @param sat
	 *            information about source and target model
	 * @param tmCDP
	 *            deployment plan within target model
	 */
	public LWContainerTrafo(LazyCopier copy, Package tmCDP) {
		this.copier = copy;
		this.tmCDP = tmCDP;
		interceptionOpMap = new HashMap<Operation, Operation>();
	}

	/**
	 * creates the executor. Needs to be called *before* the other operations of
	 * this class.
	 *
	 * @param tmComponent
	 *            the implementation of a component
	 *
	 * @throws TransformationException
	 */
	@Override
	public void createContainer(Class smClass, Class tmClass) throws TransformationException {

		// for non-components: create a delegation operation for all operations that are provided by a class (excluding those derived by ports)
		// Calls on model level use CallOperationAction, will point to existing operation, unless changed.
		// If deployed dynamically (no static component deployment), need to change factories as well.
		// Pragmatic: rename/add existing operations (as Accord has done), do some renaming and clever model handling (assure
		// that code referencing classes via name automatically uses the new class.
		// => container with name of existing class, rename existing class.
		// possible: move operations into container, existing class gets copy (and update behaviors)

		// TODO: need something simple ...
		// requirements:
		// - existing creation operations create container (separation activity CreateAction: needs to change.)
		// trivially in this case case, since container is no separate entity.
		// - => references could be exchanged during copy operation with a suitable copyFilter (=> container transfo becomes a copy filter)
		// [in case of ports: quite difficult to handle: if port belongs to abstract components, it may be inherited by multiple components that
		// might or might-not have a container => only some references need to be changed]
		// => clarify, how container handles super-classes, i.e. if it inherits ports as well (from a container of the abstract component) or not (not trivial at all!)
		// TODO: don't copy derived operations

		this.smClass = smClass;
		this.tmClass = tmClass;
		// create a copy of all operations
		operations = new BasicEList<Operation>(smClass.getOperations());
	}

	public void createInstance() {
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
	 * return the reference of the created container class
	 *
	 * @return
	 */
	public Class getContainer() {
		return tmClass;
	}

	/**
	 * apply a container rule, i.e. add either a container extension or an
	 * interceptor to the container.
	 *
	 * @param smContainerRule
	 *            An container rule
	 * @param smComponent
	 *            the application component in the source model
	 * @param tmComponent
	 *            the application component in the target model
	 * @param tmIS
	 *            the instance specification for the application component in the target model
	 * @throws TransformationException
	 */
	@Override
	public void applyRule(ContainerRule smContainerRule, Class smComponent, Class tmComponent)
			throws TransformationException
	{
		// dependencies of the rule become dependencies of he class. These dependencies must be instantiated
		for (Dependency dependency : smContainerRule.getBase_Class().getClientDependencies()) {
			//
			for (Element target : dependency.getTargets()) {
				// target may, or may not be in a template
				if (target instanceof Class) {
					Class targetCl = (Class) target;
					Class extClass = expandAggregationDep(targetCl, smComponent);
					tmComponent.createDependency(extClass);
					// if(TemplateUtils.getSignature(targetCl) != null) {
					// }
				}
			}
		}

		boolean hasConstructor = isOperationStereotypeApplied(Create.class);
		boolean hasDestructor = isOperationStereotypeApplied(Destroy.class);

		// register relation to facilitate attribute copy
		copier.setPackageTemplate(smContainerRule.getBase_Class(), tmClass);
		// reset status to in-progress. Otherwise, the copier will not properly add new
		// elements.
		copier.setStatus(tmClass, CopyStatus.INPROGRESS);

		for (Operation templateOperation : smContainerRule.getBase_Class().getOperations()) {
			// Need a specific treatment of Constructor/destructor: if original class has a
			// constructor, must add to all constructors, if it has none, copy constructor
			boolean templateIsConstructor = StereotypeUtil.isApplied(templateOperation, Create.class);
			boolean templateIsDestructor = StereotypeUtil.isApplied(templateOperation, Destroy.class);
			
			boolean needsMerge = (templateIsConstructor && hasConstructor) || (templateIsDestructor && hasDestructor);
			if (needsMerge || StereotypeUtil.isApplied(templateOperation, InterceptionRule.class)) {
				// operation is an interceptor: add its content to the methods of the
				// original class
				
				// reset package template. Make sure not to use template map, otherwise methods of original class might be duplicated
				copier.setPackageTemplate(null, null);
				expandInterceptorExtension(smContainerRule, templateOperation);
			}
			else {
				copier.setPackageTemplate(smContainerRule.getBase_Class(), tmClass);
				// normal operation. Copy from container to class
				Operation newOperation = copier.getCopy(templateOperation);
				if (StereotypeUtil.isApplied(templateOperation, Template.class)) {
					String opBody = getBody(templateOperation);
					// operation is not an interceptor, assume binding with class itself
					TransformationContext.classifier = tmClass;
					opBody = TextTemplateBinding.bind(opBody, tmClass, null);
					setBody(newOperation, opBody);
				}
				if (templateIsConstructor) {
					newOperation.setName(tmClass.getName());
				}
				else if (templateIsConstructor) {
					newOperation.setName("~" + tmClass.getName());		
				}
			}
		}

		copier.setPackageTemplate(smContainerRule.getBase_Class(), tmClass);
		
		for (Property part : smContainerRule.getBase_Class().getAllAttributes()) {
			Type type = part.getType();
			if (type == null) {
				String ruleName = (smContainerRule.getBase_Class() != null) ? smContainerRule.getBase_Class().getName() : "undefined"; //$NON-NLS-1$
				throw new TransformationException(String.format(Messages.LWContainerTrafo_CannotApplyRule, ruleName));
			}
			if (part instanceof Port) {
				Port newPort = tmClass.createOwnedPort(part.getName(), part.getType());
				StUtils.copyStereotypes(part, newPort);
			}
			else if (type instanceof Class) {
				Class extOrInterceptor = (Class) type;
				// DepUtils.chooseImplementation((Class) type,
				// new BasicEList<InstanceSpecification>(), false);

				if (StereotypeUtil.isApplied(part, InterceptionRule.class)) {
					// port.filter
					//
				}
				else {
					Property extensionPart =
							expandAggregationExtension(part.getName(), extOrInterceptor, tmComponent);
					copier.put(part, extensionPart);
					copier.setPackageTemplate(null, null);
				}
			}
			else {
				Property newAttribute = EcoreUtil.copy(part);
				tmClass.getOwnedAttributes().add(newAttribute);
			}
		}

		// tell copy that tmcontainerImpl is associated with the smContainerRule
		// register a package template (although it is not a template) to assure that the connectors
		// get copied, although they are in a different resource (only the connectors are copied, not
		// the types of the referenced parts).
		// [main issue here: properties of container rule are not copies in the sense of identical
		// copies]

		// copy.setPackageTemplate(smContainerRule.getBase_Class(), tmClass);
		// copy.setPackageTemplate(null, null);
	}

	protected boolean isOperationStereotypeApplied(java.lang.Class<? extends EObject> stereotype) {
		for (Operation op : tmClass.getOwnedOperations()) {
			if (StereotypeUtil.isApplied(op,  stereotype)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This container expansion does not create a new composite. Instead, it
	 * adds the extension as a part to the copied application component. The
	 * application component also inherits from the type of the container
	 * extension in order to avoid copying ports.
	 */
	Property expandAggregationExtension(String name, Class smContainerExtImpl, Class tmComponent) throws TransformationException {
		Class tmContainerExtImpl = expandAggregationDep(smContainerExtImpl, tmComponent);

		// add part associated with the extension to the container
		Property extensionPart = tmClass.createOwnedAttribute(name, tmContainerExtImpl);

		// problem: would not be unique in case of multiple extensions
		// Copy.copyID(tmComponent, extensionPart, "a");
		extensionPart.setIsComposite(true);

		return extensionPart;
	}

	Class expandAggregationDep(Class smContainerExtImpl, Class tmComponent) throws TransformationException {
		Class tmContainerExtImpl = null;

		TemplateSignature signature = TemplateUtils.getSignature(smContainerExtImpl);
		if (signature == null) {
			// no template signature, just copy the container extension into the target model
			tmContainerExtImpl = copier.getCopy(smContainerExtImpl);
		} else {
			// template signature found, instantiate container extension via the
			// template binding mechanism
			TemplateBinding binding = TemplateUtils.fixedBinding(copier.target, smContainerExtImpl, tmComponent);
			Object[] args = new Object[] {};
			TemplateInstantiation ti = new TemplateInstantiation(copier, binding, args);
			tmContainerExtImpl = ti.bindElement(smContainerExtImpl);
		}

		return tmContainerExtImpl;
	}

	/**
	 * Add interception code to all operations of the lwContainer.
	 * Can be called several times with different interception operations (which will then be concatenated)
	 *
	 * @throws TransformationException
	 */
	EList<Property> expandInterceptorExtension(ContainerRule smContainerRule, Operation interceptionOperationInRule)
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
			if (StereotypeUtil.isApplied(interceptionOperationInRule, Template.class)) {
				// pass operation in source model, since this enables Xtend code to check
				// for markers on model
				TransformationContext.classifier = tmClass;
				interceptionBody = TextTemplateBinding.bind(interceptionBody, smOperation, null);
			}
			if (interceptionBody.length() > 0) {
				// only add interception operation, if the interception is not empty
				interceptionBody = "// --- interception code from rule <" + smContainerRule.getBase_Class().getName() + "> ---\n" + //$NON-NLS-1$ //$NON-NLS-2$
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
		return "";			 //$NON-NLS-1$
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
	 */
	@Override
	public InstanceSpecification createContainerInstance(Class tmComponent, InstanceSpecification executorIS) throws TransformationException {
		InstanceSpecification containerIS = executorIS;
		// TODO ... incomplete!
		// InstanceConfigurator.configureInstance(smContainerRule, containerIS, null, context);

		// now create instances for the contained elements
		// TODO: why no limits to parts?
		// TODO: risk of concurrent modification, if an additional attribute is created as a side effect
		for (Property extensionPart : tmComponent.getAttributes()) {
			Type tmContainerExtImpl = extensionPart.getType();
			if (tmContainerExtImpl instanceof Class) {
				if (DepUtils.getSlot(executorIS, extensionPart) == null) {
					// no slot for part exists => assume that the part has been added by the container and create an instance specification for it.

					InstanceSpecification containerExtIS = DepCreation.createDepPlan(tmCDP, (Class) tmContainerExtImpl, containerIS.getName() + "." + //$NON-NLS-1$
							extensionPart.getName(), false);
					// configure extension
					InstanceConfigurator.configureInstance(containerExtIS, extensionPart, null);
					DepCreation.createSlot(containerIS, containerExtIS, extensionPart);
				}
			}
		}
		return containerIS;
	}
}
