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
package org.eclipse.papyrus.designer.deployment.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndex;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.AutoIndexPerNode;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.ConfigurationProperty;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.CopyAttributeValue;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.transformation.base.utils.ApplyProfile;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.StdModelLibs;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationRTException;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLUtil;

public class DepCreation {

	private static Map<Object, Integer> map;

	public static final String valueFor = "value for "; //$NON-NLS-1$

	// TODO: why needed?
	public static final String CREATE_CONNECTIONS = "createConnections"; //$NON-NLS-1$

	/**
	 * Create a slot and add an instance value associated with a part instance
	 * specification.
	 *
	 * @param is
	 *            the instance specification for which a slot should be created
	 * @param partIS
	 *            the instance specification associated with the slot (to be
	 *            precise with slot's instance value)
	 * @param part
	 *            the part associated with the slot, i.e. its defining feature
	 * @return The created slot
	 */
	public static Slot createSlot(InstanceSpecification is, InstanceSpecification partIS, Property part) {
		// the instance specification of the composite has a slot for each part
		// and it points
		// to the instance specification associated with the part.
		Slot slot = is.createSlot();
		slot.setDefiningFeature(part);

		InstanceValue iv = (InstanceValue) slot.createValue(null, null, UMLPackage.eINSTANCE.getInstanceValue());
		iv.setInstance(partIS);
		return slot;

	}


	/**
	 * Create a Slot and a value for a configuration attribute
	 *
	 * @param attribute
	 */
	public static Slot createSlotForConfigProp(InstanceSpecification is, Property attribute) {

		Slot slot = is.createSlot();
		slot.setDefiningFeature(attribute);
		// For primitive types, the UML type does not provide sufficient information to decide
		// whether it is a string or a numerical value. In case of the C++ profile, primitive
		// UML types denote a language specific type, but we want to avoid C++ specific code, if
		// possible.
		// => pragmatic solution: use LiteralString as default, unless a check for some known numerical
		// primitive types holds (in particular the UML primitive types)
		// In addition, string values are not automatically quoted, i.e. it is possible to enter e.g.
		// a float value as a string expression (it also means that a "real" string value must be quoted
		// by the user).
		Type type = attribute.getType();
		if (type != null) {
			String name = type.getName();
			String qname = type.getQualifiedName();
			if (type instanceof Enumeration) {
				slot.createValue(valueFor + attribute.getName(), type, UMLPackage.eINSTANCE.getInstanceValue());
			} else if (name.equals("Integer") || qname.equals(CORBAtypeNames.Octet) //$NON-NLS-1$
					|| qname.equals(CORBAtypeNames.Long) || qname.equals(CORBAtypeNames.UnsignedLong) || qname.equals(CORBAtypeNames.Short) || qname.equals(CORBAtypeNames.UnsignedShort)) {
				slot.createValue(valueFor + attribute.getName(), type, UMLPackage.eINSTANCE.getLiteralInteger());
			} else if (name.equals("Boolean")) { //$NON-NLS-1$
				slot.createValue(valueFor + attribute.getName(), type, UMLPackage.eINSTANCE.getLiteralBoolean());
			} else {
				slot.createValue(valueFor + attribute.getName(), type, UMLPackage.eINSTANCE.getLiteralString());
			}
		}
		return slot;
	}

	/**
	 * Create a string slot and a value for a configuration attribute
	 *
	 * @param attribute
	 */
	public static Slot createStringSlotForConfigProp(InstanceSpecification is, Property attribute) {

		Slot slot = is.createSlot();
		slot.setDefiningFeature(attribute);
		Type type = attribute.getType();
		if (type != null) {
			slot.createValue(valueFor + attribute.getName(), type, UMLPackage.eINSTANCE.getLiteralString());
		}
		return slot;
	}

	/**
	 * Create a new deployment plan package that is later filled
	 * @param parent
	 * @return
	 */
	public static Package createDepPlanPkg(Package parent, String name) {
		Package cdpPkg = parent.createNestedPackage(name);
		EObject cdp = StereotypeUtil.applyApp(cdpPkg, DeploymentPlan.class);
		if (cdp == null) {
			// application failed. Assume that profile application is missing and apply profile
			// first and then stereotype again
			CommandSupport.exec(TransactionUtil.getEditingDomain(cdpPkg), new ApplyProfile(cdpPkg, StdModelLibs.DEP_PROFILE_URI));
			StereotypeUtil.applyApp(cdpPkg, DeploymentPlan.class);
		}
		return cdpPkg;
	}
	
	/**
	 * create a deployment plan, i.e. a set of instances that correspond to an
	 * implementation which is potentially a composite. In case of the latter,
	 * this function is called recursively to create deployment plans for the
	 * parts. Since a composite implementation may optionally specify only the
	 * type of parts (not the concrete implementation), this function also
	 * supports that a type is passed. In this case, an implementation is
	 * automatically assigned.
	 *
	 * @param cdp
	 *            the deployment plan (package) in which to create instances
	 * @param typeOrImplem
	 *            the type
	 * @param nane
	 *            the name of the instance
	 * @param createSlotsForConfigValues
	 *            if true, create slots for configuration values
	 */
	public static InstanceSpecification createDepPlan(Package cdp, Class typeOrImplem, String name, boolean createSlotsForConfigValues) throws TransformationException {
		return createDepPlan(cdp, typeOrImplem, name, createSlotsForConfigValues, new Stack<Classifier>());
	}

	public static InstanceSpecification createDepPlan(Package cdp, Class typeOrImplem, String name, boolean createSlotsForConfigValues, Stack<Classifier> visitedClassifiers) throws TransformationException {
		// create an instance specification for the composite
		if (visitedClassifiers.contains(typeOrImplem)) {
			String path = ""; //$NON-NLS-1$
			for (Classifier cl : visitedClassifiers) {
				if (path.length() > 0) {
					path += ", "; //$NON-NLS-1$
				}
				path += cl.getName();
			}
			path += ", " + typeOrImplem.getName(); //$NON-NLS-1$
			throw new TransformationException(String.format(Messages.DepCreation_CircularReference, typeOrImplem.getQualifiedName(), path));
		}
		visitedClassifiers.push(typeOrImplem);

		InstanceSpecification is;
		// treat singleton
		if (DepUtils.isSingleton(typeOrImplem)) {

			// use canonical name for singleton instance - lower case for type-name
			String instanceName = DeployConstants.singletonPrefix + typeOrImplem.getName().toLowerCase();
			PackageableElement pe = cdp.getPackagedElement(instanceName);

			if (pe == null) {
				// instance specification for singleton does not exist yet => create
				is = (InstanceSpecification) cdp.createPackagedElement(name, UMLPackage.eINSTANCE.getInstanceSpecification());
			} else if (pe instanceof InstanceSpecification) {
				// exists already, return it without recursing into its sub-specifications
				return (InstanceSpecification) pe;
			} else {
				// unlikely case that a packaged element with the name
				// <singletonISname> exists already, but is not an instance specification
				throw new TransformationException(String.format(Messages.DepCreation_SingletonExistsAlready, name));
			}
		} else {
			is = (InstanceSpecification) cdp.createPackagedElement(name, UMLPackage.eINSTANCE.getInstanceSpecification());
		}

		Class implementation = null;

		// treat HW architecture or nodes as implementations
		if (!typeOrImplem.isAbstract() || typeOrImplem instanceof Node) {
			// implementation is known => must be able to do this.
			if (typeOrImplem instanceof Class) {
				implementation = typeOrImplem;
			}
		} else {
			// problem? further tree expansion might depend on chosen
			// implementation)
			// TODO: don't know node yet => implementation choice is more
			// general than necessary
			implementation = DepUtils.chooseImplementation(typeOrImplem, null, null);
		}

		if (!(implementation instanceof Class)) {
			throw new TransformationException(String.format(Messages.DepCreation_CannotFindImplementation, name, typeOrImplem.getName()));
		}
		// else implementation is instance of Class (and not null)

		is.getClassifiers().add(implementation);
		// create slots for elements that are not in the current model but will be added by transformations, e.g.
		// container and connector transformations.
		// TODO: this is not very clean since the referenced elements are actually not in the model (e.g. the connector is
		// not yet a part which could referenced as a defining feature of a slot). This might require a different strategy
		// in which the transformed model is part of the user model and synchronized).
		if (createSlotsForConfigValues) {
			addConfigurationOfAddedElements(cdp, is);
		}
		/*
		 * EList<ContainerRule> rules = FCMUtil.getAllContainerRules(implementation);
		 * // create slots for configuration.
		 * for (ContainerRule rule : rules) {
		 * addConfigurationOfContainer(rule, is);
		 * }
		 * }
		 * 
		 * for (Connector connector : implementation.getOwnedConnectors()) {
		 * org.eclipse.papyrus.designer.components.FCM.Connector fcmConn = UMLUtil.getStereotypeApplication(connector, org.eclipse.papyrus.designer.components.FCM.Connector.class);
		 * if (fcmConn != null) {
		 * String partName = name + "." + connector.getName(); //$NON-NLS-1$
		 * InteractionComponent connectorComp = fcmConn.getIc();
		 * if (connectorComp != null) {
		 * Class cl = fcmConn.getIc().getBase_Class();
		 * if (cl == null) {
		 * throw new TransformationException(Messages.DepCreation_FCMconnectorWithoutBaseClass);
		 * }
		 * // create sub-instance for connector. It is not possible to
		 * // create a slot in the owning instance specification,
		 * // since the connector cannot be referenced as a defining-feature
		 * createDepPlan(cdp, cl, partName, createSlotsForConfigValues, visitedClassifiers);
		 * }
		 * }
		 * }
		 */

		for (Property attribute : implementation.getAllAttributes()) {
			// loop over all attributes (not only parts, since we need to
			// capture singletons)
			if (attribute instanceof Port) {
				continue;
			}
			Type type = attribute.getType();

			if (DepUtils.isComposition(attribute)) {
				// composition, attribute is a part
				if (type instanceof Class) {
					Class cl = (Class) type;

					// TODO: ad-hoc replication support. Better solution via design patterns
					int upper = attribute.getUpper();
					String infix = ""; //$NON-NLS-1$

					// TODO: check validation constraints
					for (int i = 0; i < upper; i++) {
						String partName = name + "." + attribute.getName(); //$NON-NLS-1$
						if (upper > 1) {
							partName += "_" + infix + i; //$NON-NLS-1$
						}
						InstanceSpecification partIS = createDepPlan(cdp, cl, partName, createSlotsForConfigValues, visitedClassifiers);
						// may not create slot for singleton, since automatically done
						if (!DepUtils.isSingleton((Class) type)) {
							createSlot(is, partIS, attribute);
						}
					}
				} else if (StereotypeUtil.isApplied(attribute, ConfigurationProperty.class) && createSlotsForConfigValues) {
					// is a configuration property, create slot
					// TODO: implicit assumption that configuration attributes
					// are not components
					createSlotForConfigProp(is, attribute);
				}
			} else if (type instanceof Class) {
				// no composition - only create slot, if a singleton
				// (otherwise, it's not clear with which instance the slot
				// should be associated)
				if (DepUtils.isSingleton((Class) type)) {
					// is a singleton - exactly one instance exists
					// recursive call - pass empty name, since name for singletons is re-calculated.
					InstanceSpecification singletonIS = createDepPlan(cdp, (Class) type, "", createSlotsForConfigValues, visitedClassifiers); //$NON-NLS-1$
					createSlot(is, singletonIS, attribute);
				}
			} else if (type == null) {
				throw new TransformationException(String.format(Messages.DepCreation_TypeInAttributeUndefined, attribute.getName(), implementation.getName()));
			}
		}
		visitedClassifiers.pop();
		return is;
	}

	/**
	 * Create slot for configuration properties that originate from elements that are added
	 * by M2M transformations. For instance, connector and container related transformations
	 * might add parts that need a configuration.
	 *
	 * @param aRule
	 * @param is
	 * @throws TransformationException
	 */
	private static void addConfigurationOfAddedElements(Package cdp, InstanceSpecification is) throws TransformationException {
		M2MTrafoChain chain = DepUtils.getTransformationChain(cdp);
		if (chain != null) {
			DeploymentPlan cdpStereo = UMLUtil.getStereotypeApplication(cdp, DeploymentPlan.class);
			if (cdpStereo != null) {
				for (Property m2mTrafoRef : chain.getBase_Class().getAllAttributes()) {
					M2MTrafo m2mTrafo = UMLUtil.getStereotypeApplication(m2mTrafoRef.getType(), M2MTrafo.class);

					if (m2mTrafo != null) {
						IM2MTrafo eTrafo = M2MTrafoExt.getM2MTrafo(m2mTrafo);
						/*
						if (eTrafo instanceof IM2MTrafoAddTypes) {
							EList<Type> addedTypes = ((IM2MTrafoAddTypes) eTrafo).getAddedTypes(m2mTrafo, is);
							for (Type addedType : addedTypes) {
								// TODO: create attribute??
								createSlotForConfigProp(is, null);
							}
						}
						*/
					}
				}
			}
		}
	}

	/**
	 * create a deployment plan, i.e. a set of instances that correspond to an
	 * implementation which is potentially a composite. In case of the latter,
	 * this function is called recursively to create deployment plans for the
	 * parts. Since a composite implementation may optionally specify only the
	 * type of parts (not the concrete implementation), this function also
	 * supports that a type is passed. In this case, an implementation is
	 * automatically assigned.
	 *
	 * @param cdp
	 *            the deployment plan (package) in which to create instances
	 * @param typeOrImplem
	 *            the type
	 * @param nane
	 *            the name of the instance
	 * @param createSlotsForConfigValues
	 *            if true, create slots for configuration values
	 */
	public static InstanceSpecification createPlatformInstances(Package platform, Class implementation, String name) throws TransformationException {
		// create an instance specification for the composite
		InstanceSpecification is = null;
		is = (InstanceSpecification) platform.createPackagedElement(name, UMLPackage.eINSTANCE.getInstanceSpecification());
		is.getClassifiers().add(implementation);

		// add connector and container implementations

		for (Property attribute : implementation.getAllAttributes()) {
			// loop over all attributes (not only parts, since we need to
			// capture singletons)
			if (attribute instanceof Port) {
				continue;
			}
			Type type = attribute.getType();

			if (DepUtils.isComposition(attribute)) {
				// composition, attribute is a part
				if (type instanceof Class) { // should be a node, but do not require it
					Class cl = (Class) type;

					int upper = attribute.getUpper();
					String infix = ""; //$NON-NLS-1$
					// TODO: check validation constraints
					for (int i = 0; i < upper; i++) {
						// prefix with name, unless null
						String partName = (name != null) ? name + DeployConstants.SEP_CHAR : ""; //$NON-NLS-1$
						partName += attribute.getName();
						if (upper > 1) {
							partName += "_" + infix + i; //$NON-NLS-1$
						}
						InstanceSpecification partIS = createPlatformInstances(platform, cl, partName);

						if (is != null) {
							createSlot(is, partIS, attribute);
						}
					}
				} else if (StereotypeUtil.isApplied(attribute, ConfigurationProperty.class)) {
					// is a configuration property, create slot
					// TODO: implicit assumption that configuration attributes
					// are not components
					createSlotForConfigProp(is, attribute);
				}
			}
		}
		return is;
	}

	/**
	 * Initialize the automatic values within a deployment plan - and the update
	 * eventual copies of these values.
	 *
	 * @param is
	 *            the main instance of the deployment plan
	 */
	public static void initAutoValues(InstanceSpecification is) {
		Collection<InstanceSpecification> isList = new ArrayList<InstanceSpecification>();
		isList.add(is);
		initAutoValues(isList);
	}

	/**
	 * Initialize the automatic values within a deployment plan - and update
	 * eventual copies of these values.
	 *
	 * @param isList
	 *            a list of instance specifications
	 */
	public static void initAutoValues(Collection<InstanceSpecification> isList) {
		map = new HashMap<Object, Integer>();
		for (InstanceSpecification is : isList) {
			initAutoValuesHelper(is);
		}
		Stack<InstanceSpecification> isStack = new Stack<InstanceSpecification>();
		for (InstanceSpecification is : isList) {
			copyAutoValues(isStack, is);
		}
	}

	/**
	 * Initialize the automatic values within a deployment plan.
	 *
	 * @param is
	 *            the main instance of the deployment plan
	 */
	public static void initAutoValuesHelper(InstanceSpecification is) {
		for (Slot slot : is.getSlots()) {
			StructuralFeature sf = slot.getDefiningFeature();
			if (sf == null) {
				throw new RuntimeException(String.format(Messages.DepCreation_DefiningFeatureNull, slot.getOwningInstance().getName()));
			}
			if (StereotypeUtil.isApplied(sf, AutoIndex.class)) {
				Integer value = null;
				Object key;
				if (StereotypeUtil.isApplied(sf, AutoIndexPerNode.class)) {
					InstanceSpecification nodeOrThread = AllocUtils.getNode(is);
					key = sf.getName() + nodeOrThread.getName();
				} else {
					key = sf;
				}
				if (!map.containsKey(key)) {
					map.put(key, new Integer(0));
				}
				value = map.get(key);
				value++; // no need to put incremented value back into map,
							// since Integer is a reference => value is updated

				// create slot and value specification (literal-integer) for the
				// auto index
				LiteralInteger li = (LiteralInteger) slot.createValue(sf.getName() + DeployConstants.AUTO_POSTFIX, sf.getType(), UMLPackage.eINSTANCE.getLiteralInteger());
				li.setValue(value);

				// recursion in case of values that are instance values
				for (ValueSpecification vs : slot.getValues()) {
					if (vs instanceof InstanceValue) {
						InstanceSpecification subIS = ((InstanceValue) vs).getInstance();
						initAutoValues(subIS);
					}
				}
			}
		}
	}

	/**
	 * Copy automatic values within a deployment plan.
	 * Attributes can apply the CopyAttributeValue stereotype. The stereotype can point
	 * to another attribute (source). If the other attribute has a configuration value, it gets
	 * copied.
	 * The function takes into account that the source attribute might be in a component that is
	 * instantiated several times with different configuration values. The attribute that gets the
	 * copy must use the "nearest" value which means that there must be a common composite that
	 * contains both attribute (indirectly).
	 * The main use case is that connector fragments need to have common configuration attributes
	 * (e-g- server fragment has automatic index value, client fragment needs to copy this value)
	 *
	 * @param is
	 *            the main instance of the deployment plan
	 */
	public static void copyAutoValues(Stack<InstanceSpecification> isStack, InstanceSpecification is) {
		isStack.push(is);
		for (Slot slot : is.getSlots()) {
			StructuralFeature sf = slot.getDefiningFeature();
			if (sf == null) {
				throw new TransformationRTException(is.getName() + " has a slot without defining feature"); //$NON-NLS-1$
			}
			if (StereotypeUtil.isApplied(sf, CopyAttributeValue.class)) {
				CopyAttributeValue cav = UMLUtil.getStereotypeApplication(sf, CopyAttributeValue.class);
				Property source = cav.getSource();
				ValueSpecification vs = getNearestValue(isStack, source);
				if (vs instanceof LiteralInteger) {
					LiteralInteger liCopy = (LiteralInteger) slot.createValue(sf.getName() + "_copy", sf.getType(), //$NON-NLS-1$
							UMLPackage.eINSTANCE.getLiteralInteger());
					int value = ((LiteralInteger) vs).getValue();
					liCopy.setValue(value);
				}
			}

			// recursion in case of values that are instance values
			for (ValueSpecification vs : slot.getValues()) {
				if (vs instanceof InstanceValue) {
					InstanceSpecification subIS = ((InstanceValue) vs).getInstance();
					if (subIS != null) {
						copyAutoValues(isStack, subIS);
					}
				}
			}
		}
		isStack.pop();
	}

	/**
	 * try to find a value (ValueSpecification) for the passed source element,
	 * beginning a the "deepest" instance specification of the passed stack. If
	 * not found, continue at elements higher up in the hierarchy
	 *
	 * @param isStack
	 *            a stack of instance specifications corresponding to a path
	 *            within an instance tree
	 * @param source
	 *            a property (defining feature) for which we search an instance
	 *            specification
	 * @return The value specifications for the passed source property or null
	 */
	public static ValueSpecification getNearestValue(Stack<InstanceSpecification> isStack, Property source) {
		Stack<InstanceSpecification> copy = new Stack<InstanceSpecification>();
		copy.addAll(isStack);
		while (!copy.isEmpty()) {
			InstanceSpecification pop = copy.pop();
			ValueSpecification vs = getNearestValueHelper(isStack, pop, source);
			if (vs != null) {
				return vs;
			}
		}
		return null;
	}

	/**
	 * Helper for getNearestValue: search for an instance specification that has
	 * source as defining feature. Start at the passed instance specification,
	 * but do not recurse into elements that are contained in the stack.
	 *
	 * @param isStack
	 *            A stack of instance specifications corresponding to a path
	 *            within an instance tree
	 * @param is
	 *            the starting instance specification
	 * @param source
	 *            source a property (defining feature) for which we search an
	 *            instance specification
	 * @return The value specifications for the passed source property or null
	 */
	public static ValueSpecification getNearestValueHelper(Stack<InstanceSpecification> isStack, InstanceSpecification is, Property source) {
		for (Slot slot : is.getSlots()) {
			StructuralFeature sf = slot.getDefiningFeature();
			if (sf == source) {
				// found property, now return first value specification
				for (ValueSpecification vs : slot.getValues()) {
					return vs;
				}
				return null;
			}

			// recursion in case of values that are instance values
			for (ValueSpecification vs : slot.getValues()) {
				if (vs instanceof InstanceValue) {
					InstanceSpecification subIS = ((InstanceValue) vs).getInstance();
					if (!isStack.contains(subIS)) {
						// only recurse, if not contained in stack of instance
						// specifications (avoid traversing
						// the same elements multiple times)
						return getNearestValueHelper(isStack, subIS, source);
					}
				}
			}
		}
		return null;
	}
}
