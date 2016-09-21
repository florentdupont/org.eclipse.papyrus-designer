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

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.InitPrecedence;
import org.eclipse.papyrus.designer.deployment.tools.AllocUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.library.Messages;
import org.eclipse.papyrus.uml.tools.utils.ConnectorUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * The task of the boot-loader is twofold: create the instances of all
 * implementations (non-recursive).
 * - create Connections: what should be done?
 *
 * TODO: factor out common code (TemplateInstantiation mechanism & createConnections below)
 * Split between C++ specific and C++ independent aspects
 */
abstract public class AbstractBootLoaderGen implements IM2MTrafoCDP {

	protected static final String SYSINTERFACES_ISTART = "sysinterfaces::IStart"; //$NON-NLS-1$

	protected static final String INIT_OP = "init"; //$NON-NLS-1$

	protected static final String I_LIFE_CYCLE = "ILifeCycle"; //$NON-NLS-1$

	protected static final String EMPTYSTR = ""; //$NON-NLS-1$

	protected static final String NODE_INFO = "NodeInfo"; //$NON-NLS-1$

	protected static final String NL = "\n"; //$NON-NLS-1$

	protected static final String EOL = ";\n"; //$NON-NLS-1$

	/**
	 * language specific initialization code
	 */
	public abstract void languageInit();
	
	/**
	 * language specific activation
	 * @param activationKeys set of keys 
	 * @return
	 */
	public abstract String languageActivation(Class[] activationKeys);
	
	public abstract String languageDeactivation(Class[] activationKeys);

	public abstract String languageDefaultExecCode();

	public abstract String languageCreateInstance(InstanceSpecification is, Class implementation);
	
	public abstract String languageCreateConn(String varName);

	public abstract String languageRunStart(String varName, boolean useOO);
		
	public abstract String languageAssignRef(String accessName, String referenceVarName);
	 
	/**
	 * Create a new boot-loader in a specific package
	 * (which represents a node of the system).
	 *
	 * @param copier a lazy copier
	 * @param nodeIndex the index of the node
	 * @param numberOfNodes the number of nodes
	 * @throws TransformationException
	 */
	public void init(LazyCopier copier, int nodeIndex, int numberOfNodes)
			throws TransformationException {

		// m_bootLoader = TransformationContext.current.modelRoot.createOwnedClass(BOOTLOADER_NAME, false);
		outputSizeof = false;
		m_copier = copier;

		m_initCode = EMPTYSTR;
		m_initCodeRun = EMPTYSTR;
		m_activation = new HashMap<Class, EList<String>>();
		m_initCodeCConnections = EMPTYSTR;
		m_initCodeCConfig = EMPTYSTR;

		// indexMap = new HashMap<String, Integer>();
	}

	/**
	 * Return the path from the main instance towards a sub-instance using the proper dereference
	 * operators (only relevant in case of C++)
	 *
	 * @param slotPath
	 * @param instance
	 * @param accessName
	 *            return the name to access the feature. Returns access path to instance, not
	 *            the name of the variable for this instance (if instantiated by bootloader)
	 * @return
	 */
	public String getPath(Stack<Slot> slotPath, InstanceSpecification instance, boolean accessName) {
		if (slotPath.size() > 0) {
			// start with first instance
			String path = slotPath.get(0).getOwningInstance().getName();
			boolean previousInstantiatedByBL = false;
			for (Slot pathElement : slotPath) {
				if (pathElement != null) {
					if (previousInstantiatedByBL && accessName) {
						// If an instance is instantiated by the bootloader, it is only referenced via its type in the
						// owning composite. Thus, configuration (and activation calls) might fail as the type might not
						// have these configuration properties or operations.
						// Therefore, configuration and initial calls use
						// - the path, if instantiated by the composite
						// - the variable name, if done by the bootloader
						path = ElementUtils.varName(path); // use variable name instead.
					}
					path += "." + pathElement.getDefiningFeature().getName(); //$NON-NLS-1$
					previousInstantiatedByBL = instantiateViaBootloader(pathElement.getDefiningFeature());
				}
			}
			if (previousInstantiatedByBL && !accessName) {
				// name of the variable for this expression instantiated by the bootloader
				path = ElementUtils.varName(path);
			}
			return path;
		}
		else {
			return instance.getName(); // instance has no path via slots, it is a top level instance
		}
	}

	/**
	 * Check whether the passed implementation has an unconnected start port.
	 * This information is required, since only unconnected start ports are automatically called by the
	 * bootloader, in particular we want to avoid calling a start port of an executor (which is connected)
	 * and its container.
	 *
	 * @param implementation
	 * @param containerSlot
	 * @return
	 */
	public static boolean hasUnconnectedStartRoutine(LazyCopier copier, Class implementation, Slot containerSlot) {
		Port startPort = AllocUtils.getStartPort(implementation);
		if (startPort != null) {
			return !isConnected(copier, containerSlot, startPort);
		}
		return false;
	}
	
	public boolean implementsIStart(Class implementation) {
		Port startPort = AllocUtils.getStartPort(implementation);
		if (startPort == null) {
			// OO case
			for (InterfaceRealization ir : implementation.getInterfaceRealizations()) {
				if (ir.getContract().getQualifiedName().equals(SYSINTERFACES_ISTART)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check, if the passed implementation has an unconnected life-cycle interface (activate/deactivate).
	 * This information is required, since only unconnected life cycle ports are automatically called by the
	 * bootloader, in particular we want to avoid calling a life cycle port of an executor (which is connected)
	 * and its container.
	 *
	 * @param implementation
	 * @param name
	 * @return
	 */
	public static boolean hasUnconnectedLifeCycle(LazyCopier copy, Class implementation, Slot containerSlot) {
		if (implementation != null) {
			Element lcPortElem = ElementUtils.getNamedElementFromList(implementation.getAllAttributes(), "lc"); //$NON-NLS-1$
			if (lcPortElem instanceof Port) {
				Port lcPort = (Port) lcPortElem;
				// check, if port typed with ILifeCycle interface
				if (lcPort.getType().getName().equals(I_LIFE_CYCLE)) {
					return !isConnected(copy, containerSlot, lcPort);
				}
			}
		}
		return false;
	}

	/**
	 * The check verifies whether the passed port is connected within
	 * the context of the composite represented by the passed slot
	 *
	 * @param containerSlot
	 *            a slot within an instance that represents a composite class
	 * @Param a port that is checked for being connected
	 * @return true, if connected
	 */
	private static boolean isConnected(LazyCopier copier, Slot containerSlot, Port port) {
		if (containerSlot != null) {
			StructuralFeature sf = containerSlot.getDefiningFeature();
			if (sf instanceof Property) {
				Property part = (Property) sf;
				Class composite = part.getClass_();
				for (Connector connector : composite.getOwnedConnectors()) {
					// must assure same connector end connects part & port
					ConnectorEnd end = ConnectorUtil.connEndForPart(connector, part);
					if ((end != null) && (end.getRole() == port)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Add the configuration code for an instance
	 * 
	 * @param slotPath
	 * @param instance
	 * @throws TransformationException
	 */
	public void instanceConfig(Stack<Slot> slotPath, InstanceSpecification instance) throws TransformationException {
		Slot slot = slotPath.peek();
		// String varName = getPath(slotPath, instance, false);
		StructuralFeature sf = slot.getDefiningFeature();
		if (sf == null) {
			throw new TransformationException(String.format("A slot for instance %s has no defining feature", instance.getName())); //$NON-NLS-1$
		}

		String varName = instance.getName() + "." + sf.getName(); //$NON-NLS-1$
		for (ValueSpecification value : slot.getValues()) {

			// only set value, if not null
			if (value.stringValue() != null) {
				m_initCodeCConfig += varName + " = " + value.stringValue() + EOL; //$NON-NLS-1$
			}
		}
	}

	/**
	 * add the initialize operation. Must be called after a set of addInstance invocations
	 * @param language programming language to use (in the opaque behavior creation)
	 */
	public void addInit(String language) {
		Operation initOp = m_bootLoader.getOwnedOperation(INIT_OP, null, null);

		OpaqueBehavior initBehavior = (OpaqueBehavior)
				m_bootLoader.createOwnedBehavior(initOp.getName(), UMLPackage.eINSTANCE.getOpaqueBehavior());
		initOp.getMethods().add(initBehavior);

		String code = m_initCode;
		if (m_initCodeCConfig.length() > 0) {
			code += m_initCodeCConfig;
		}
		if (m_initCodeCConnections.length() > 0) {
			code += m_initCodeCConnections;
		}
		Comparator<Class> comparator = new Comparator<Class>() {

			@Override
			public int compare(Class clazz1, Class clazz2) {

				InitPrecedence precedenceC1 = UMLUtil.getStereotypeApplication(clazz1, InitPrecedence.class);
				InitPrecedence precedenceC2 = UMLUtil.getStereotypeApplication(clazz2, InitPrecedence.class);
				if (precedenceC1 != null) {
					// need to use named comparison instead of precedenceC1.getInvokeAfter ().contains (clazz2)
					// since class referenced by stereotype attribute still points to element in source model
					if (ElementUtils.getNamedElementFromList(precedenceC1.getInvokeAfter(), clazz2.getName()) != null) {
						return 1;
					}
					else if (ElementUtils.getNamedElementFromList(precedenceC1.getInvokeBefore(), clazz2.getName()) != null) {
						return -1;
					}
				}
				else if (precedenceC2 != null) {
					if (ElementUtils.getNamedElementFromList(precedenceC2.getInvokeAfter(), clazz1.getName()) != null) {
						return -1;
					}
					else if (ElementUtils.getNamedElementFromList(precedenceC2.getInvokeBefore(), clazz1.getName()) != null) {
						return 1;
					}
				}
				// singletons have precedence over "normal" classes
				boolean ci1IsSingleton = DepUtils.isSingleton(clazz1);
				boolean ci2IsSingleton = DepUtils.isSingleton(clazz2);
				if (ci1IsSingleton) {
					if (!ci2IsSingleton) {
						// not both are singletons
						return -1;
					}
				}
				else if (ci2IsSingleton) {
					return 1;
				}
				return 0;
			}
		};
		Class[] activationKeys = m_activation.keySet().toArray(new Class[0]);

		if (activationKeys.length > 0) {
			Arrays.sort(activationKeys, comparator);
			code += languageActivation(activationKeys);
		}
		
		if (m_initCodeRun != null) {
			code+= m_initCodeRun;
		}
		else {
			code += languageDefaultExecCode();
		}
		
		if (activationKeys.length > 0) {
			code += languageDeactivation(activationKeys);
		}

		initBehavior.getLanguages().add(language);
		initBehavior.getBodies().add(code);
	}

	/**
	 * Normally, a composite instantiates its children. However, we want to enable the possibility
	 * to type a part in a composite with an abstract class and choose the concrete implementation
	 * in the moment of the deployment. In this case, the bootloader needs to perform the instantiation. 
	 * @param structuralFeature a structural feature in a composition (typically an attribute)
	 * @return true, if the boot loader should instantiate the associated component.
	 */
	protected boolean instantiateViaBootloader(StructuralFeature structuralFeature) {
		if (structuralFeature.getType() instanceof Classifier) {
			return ((Classifier) structuralFeature.getType()).isAbstract();
		}
		// should not happen (all UML types are classifiers)
		return false;
	}

	public Class getUML() {
		return m_bootLoader;
	}

	protected Class m_bootLoader;

	/**
	 * Initialization code, in particular assignment of part properties within composites
	 */
	protected String m_initCode;

	/**
	 * Init code for create connections calls in composites with at least one assembly
	 * connector
	 */
	protected String m_initCodeCConfig;

	/**
	 * Init code for create connections calls in composites with at least one assembly
	 * connector
	 */
	protected String m_initCodeCConnections;

	/**
	 * Init code for blocking "run" calls (related to CStart system component)
	 */
	protected String m_initCodeRun;

	/**
	 * Map containing activations/de-activations
	 */
	protected Map<Class, EList<String>> m_activation;

	protected boolean outputSizeof;

	/**
	 * copier variable (instances still point to non-copied classes)
	 */
	protected LazyCopier m_copier;

	public Property addInstance(Stack<Slot> slotPath, InstanceSpecification instance, Class implementation)
			throws TransformationException
	{
		// TODO: comments not clear. seems unnecessary complex. Problem in general is that access to
		// shared instances needs to be configured.
		// It should always be possible to configure this instance via a path w/o sharing.
		String accessName = getPath(slotPath, instance, true);
		final String varName = getPath(slotPath, instance, false);

		Property implemPart = null;

		// containing instance not null (=> neither main instance nor singleton)
		Slot containerSlot = null;
		if (slotPath.size() > 0) {
			containerSlot = slotPath.peek();

			// initialize part/property in containing instance. The containing instance itself is accessed
			// via the naming of the associated instance, the part itself via the name of the defining feature.
			if (DepUtils.isShared(containerSlot)) {
				// we need to initialize the property (a reference) with the given instance
				Stack<Slot> referencePath = DepUtils.getAccessPath(instance);
				final String referenceVarName = getPath(referencePath, instance, false);

				// add code for initialization
				m_initCode += languageAssignRef(accessName, referenceVarName);
						
				// is a reference which should not be called via activation & start
				// return now and skip code below
				return implemPart;
			}
			else if (instantiateViaBootloader(containerSlot.getDefiningFeature())) {
				// let bootloader instantiate
				implemPart = m_bootLoader.createOwnedAttribute(/* "i_" + */varName, implementation);
				// add code for initialization (TODO: specific to C++!)
				m_initCode += accessName + " = &" + varName + EOL; //$NON-NLS-1$
				implemPart.setIsComposite(true);
			}
		}
		else {
			// top level instance => bootloader instantiates, create attribute
			implemPart = m_bootLoader.createOwnedAttribute(/* "i_" + */varName, implementation);
			implemPart.setIsComposite(true);
			// depending on the programming language, the created attribute is a reference and additional
			// code is required to create the instance
			m_initCode += languageCreateInstance(instance, implementation);
		}
		if (outputSizeof) {
			// TODO - specific for C++
			m_initCode += "cout << \"sizeof " + implementation.getName() + ": \" << sizeof (" + varName + ") << endl;" + EOL; //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		}

		// Need to check whether implementation is an executor which is encapsulated in a container. In this case, only
		// the method of the container and not the method of the executor (which owns the same port) maybe called.
		// Currently, this check is based on the use of "executor" as reserved part name (validation checks that the
		// user does not use this name for application components)
		boolean unconnectedStart = hasUnconnectedStartRoutine(m_copier, implementation, containerSlot);
		boolean implementsIStart = implementsIStart(implementation);
		if (unconnectedStart || implementsIStart) {
			// check if already assigned earlier
			if (m_initCodeRun.equals(EMPTYSTR)) {
				// call start's run method
				// TODO: Need path that uses the right dereference operator ("->" or ".")
				m_initCodeRun = languageRunStart(varName, implementsIStart);
			} else {
				throw new TransformationException(String.format(
						Messages.BootLoaderGen_AtLeastOneBlockingCall,
						varName, m_initCodeRun));
			}
		}
		if (hasUnconnectedLifeCycle(m_copier, implementation, containerSlot)) {
			// precedence is checked below (when code is actually produced)
			// multiple varNames might share the same implementation. Put a list of variable names into the table
			EList<String> varNameList = m_activation.get(implementation);
			if (varNameList == null) {
				varNameList = new BasicEList<String>();
			}
			varNameList.add(varName + "."); //$NON-NLS-1$
			m_activation.put(implementation, varNameList);
		}

		// check, if implementation contains a composite with assembly connectors
		for (Connector connector : implementation.getOwnedConnectors()) {
			if (ConnectorUtil.isAssembly(connector)) {
				m_initCodeCConnections += languageCreateConn(varName);
				break;
			}
		}

		return implemPart;
	}
	
	public void addInstance(InstanceSpecification is, Stack<Slot> slotPath) throws TransformationException {
		Classifier implementation = DepUtils.getClassifier(is);
		if (implementation instanceof Class) {
			addInstance(slotPath, is, (Class) implementation);
		}
		for (Slot slot : is.getSlots()) {
			InstanceSpecification subIS = DepUtils.getInstance(slot);
			slotPath.push(slot);
			if (subIS != null) {
				addInstance(subIS, slotPath);
			}
			else {
				instanceConfig(slotPath, is);
			}
			slotPath.pop();	
		}
	}
}
