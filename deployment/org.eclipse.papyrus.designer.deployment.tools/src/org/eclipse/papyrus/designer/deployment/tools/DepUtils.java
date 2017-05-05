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

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.ImplementationProperties;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Singleton;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Target;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint;
import org.eclipse.papyrus.designer.transformation.base.ElementFilter;
import org.eclipse.papyrus.designer.transformation.base.preferences.PapyrusDesignerPreferenceConstants;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.StdModelLibs;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Utilities around instances (within deployment plan)
 *
 */
public class DepUtils {

	public static final String TRAFOS_M2MTRANSFORMATIONS_STANDARD = "trafos::m2mtransformations::Standard"; //$NON-NLS-1$

	/**
	 * Check whether a class is an eligible implementation for a certain node, i.e.
	 * has compatible requirements.
	 * Requires that setCurrentNode has been called earlier
	 * TODO: how does that work with connector reification between distributeToNode has been
	 * called??!
	 *
	 * @param implemCandidate
	 * @return
	 */
	public static boolean isImplEligible(Class implemCandidate, EList<InstanceSpecification> nodes) {
		if (implemCandidate.isAbstract()) {
			return false;
		}
		if (nodes != null) {
			// must fit requirements of all nodes
			for (InstanceSpecification nodeInstance : nodes) {
				Target target = UMLUtil.getStereotypeApplication(nodeInstance, Target.class);
				if (target == null) {
					// no target information on instance => try to get this
					// information from the node referenced by the instance
					target = UMLUtil.getStereotypeApplication(DepUtils.getClassifier(nodeInstance), Target.class);
				}
				if (target != null) {
					ImplementationProperties implProps = UMLUtil.getStereotypeApplication(implemCandidate, ImplementationProperties.class);
					if (implProps != null) {
						//if (!implProps.getArch().contains(target.getTargetArch())) {
							//return false;
						//}
						// TODO: check OS and size as well!
					}
				}
			}
		}
		return true;
	}

	/**
	 * Find a sub instance via its name. This is in particular useful for connectors that cannot be
	 * found via a slot, since UML only supports structural features (a connector is only a feature)
	 * in the definingFeature attribute of a slot.
	 *
	 * @param owningInstance
	 *            an owning instance
	 * @param name
	 *            name of the sub-element (unqualified)
	 * @return the found sub-instance or null
	 */
	public static InstanceSpecification getNamedSubInstance(InstanceSpecification owningInstance, String name) {
		Element cdp = owningInstance.getOwner();
		String candidateName = owningInstance.getName() + "." + name; //$NON-NLS-1$
		if (cdp instanceof Package) {
			for (PackageableElement instance : ((Package) cdp).getPackagedElements()) {
				if (instance instanceof InstanceSpecification) {
					InstanceSpecification candidate = (InstanceSpecification) instance;

					if (candidateName != null) {
						if (candidateName.equals(candidate.getName())) {
							return candidate;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Automatically choose an implementation, i.e. if the passed classifier
	 * (1) is already an implementation, simply return it
	 * (2) is an implementation group, choose the first implementation that fits the requirements
	 * (3) is a type: choose the first implementation among the heirs that fits the requirements
	 *
	 * @param componentType
	 *            a component type or implementation (class, optionally abstract)
	 * @param nodes
	 *            a set of instance specification representing nodes on which this component will be allocated
	 * @param chooser
	 *            A chooser for an implementation in case of multiple candidates
	 * @return a suitable implementation
	 */
	public static Class chooseImplementation(Class componentType, EList<InstanceSpecification> nodes, ImplementationChooser chooser) {
		// choose implementation automatically: get the first one that implements the passed type
		// get reference to component model, then search all classes contained in it.
		// TODO: assumption that implementations are in same package as type;

		return chooseImplementation(new BasicEList<Class>(), componentType, nodes, chooser);
	}
	
	public static Class chooseImplementation(EList<Class> implList, Class componentType, EList<InstanceSpecification> nodes, ImplementationChooser chooser) {
				
		if (!componentType.isAbstract()) {
			return componentType;
		}
		else {
			for (DirectedRelationship relship : componentType.getTargetDirectedRelationships()) {
				if (relship instanceof Generalization) {
					Classifier source = ((Generalization) relship).getSpecific();
					if (source instanceof Class) {
						Class implClass = (Class) source;
						if (isImplEligible(implClass, nodes)) {
							implList.add(implClass);
						}
					}
				}
			}
		}
		if (implList.size() == 0) {
			return null;
		} else if (implList.size() == 1) {
			return implList.get(0);
		} else if (chooser != null) {
			Class impl = chooser.chooseImplementation(componentType, implList);
			if (impl != null) {
				return impl;
			}
		} else if (implList.size() > 0) {
			return implList.get(0);
		}
		return null;
	}

	/**
	 * return an instance specification for the main instance within
	 * a package.
	 *
	 * @param cdp
	 *            the deployment plan
	 */
	public static EList<InstanceSpecification> getInstances(Package cdp) {
		EList<InstanceSpecification> list = new BasicEList<InstanceSpecification>();
		for (PackageableElement pe : cdp.getPackagedElements()) {
			if (pe instanceof InstanceSpecification) {
				InstanceSpecification is = (InstanceSpecification) pe;
				list.add(is);
			}
		}
		return list;
	}
	
	/**
	 * return an instance specification for the main instance within
	 * a package.
	 *
	 * @param cdp
	 *            the deployment plan
	 */
	public static EList<InstanceSpecification> getTopLevelInstances(Package cdp) {
		EList<InstanceSpecification> list = new BasicEList<InstanceSpecification>();
		for (PackageableElement pe : cdp.getPackagedElements()) {
			if (pe instanceof InstanceSpecification) {
				InstanceSpecification is = (InstanceSpecification) pe;
				if (isTopLevelInstance(is)) {
					list.add(is);
				}
			}
		}
		return list;
	}

	/**
	 * Check whether an instance specification is a top-level instance, i.e. not referenced
	 * by another one.
	 * @param is an instance specification
	 */
	public static boolean isTopLevelInstance(InstanceSpecification is) {
		return getReferencingSlots(is).size() == 0;
	}
	
	/**
	 * return the implementation associated with an instance specification, i.e. a
	 * Class.
	 *
	 * @param instance
	 * @return
	 */
	public static Class getImplementation(InstanceSpecification instance) {
		Classifier cl = getClassifier(instance);
		if (cl instanceof Class) {
			return (Class) cl;
		}
		return null;
	}

	/**
	 * Return the first classifier referenced by an instance specification. Whereas UML supports
	 * a set of classifiers, we assume that that an instance specification has only one.
	 *
	 * @param instance
	 *            the instance, for which we are interested in type information
	 */
	public static Classifier getClassifier(InstanceSpecification instance) {
		Iterator<Classifier> classifierIt = instance.getClassifiers().iterator();
		// simply return the first element (if there is any)
		if (classifierIt.hasNext()) {
			return classifierIt.next();
		}
		return null;
	}

	/**
	 * Return the first instance specification within a deployment plan that instantiates a given
	 * classifier
	 *
	 * @param cdp
	 *            the deployment plan
	 * @param cl
	 *            the classifier
	 * @return
	 */
	public static InstanceSpecification getInstanceForClassifier(Package cdp, Classifier cl) {
		for (PackageableElement pe : cdp.getPackagedElements()) {
			if (pe instanceof InstanceSpecification) {
				InstanceSpecification is = (InstanceSpecification) pe;
				if (getClassifier(is) == cl) {
					return is;
				}
			}
		}
		return null;
	}

	/**
	 * Return the (unique) list of implementations that are contained within an
	 * instance specification
	 */
	public static EList<Classifier> getContainedImplementations(InstanceSpecification is) {
		Iterator<InstanceSpecification> instances = getContainedInstances(is).iterator();
		EList<Classifier> list = new UniqueEList<Classifier>();
		while (instances.hasNext()) {
			Classifier implementation = getClassifier(instances.next());
			list.add(implementation);
		}
		return list;
	}

	/**
	 * Return the slot that is associated with a property
	 *
	 * @param is
	 *            an instance specification (of a class having properties)
	 * @param property
	 *            A property of the classifier associated with the passed instance specification
	 * @return the associated slot or null, if it does not exist
	 */
	public static Slot getSlot(InstanceSpecification is, Property property) {
		for (Slot slot : is.getSlots()) {
			if (slot.getDefiningFeature() == property) {
				return slot;
			}
		}
		return null;
	}

	/**
	 * Return the instance referenced by a slot value, i.e. the first instance value associated
	 * with a slot
	 *
	 * @param slot
	 * @return
	 */
	public static InstanceSpecification getInstance(Slot slot) {
		for (ValueSpecification value : slot.getValues()) {
			// instances are accessible via ValueSpecification subclass InstanceValue
			if (value instanceof InstanceValue) {
				return ((InstanceValue) value).getInstance();
			}
		}
		return null;
	}

	/**
	 * This method returns the instances contained within a composite instance
	 * specification for an assembly.
	 */
	public static EList<InstanceSpecification> getContainedInstances(InstanceSpecification is) {
		EList<InstanceSpecification> contained = new BasicEList<InstanceSpecification>();
		for (Slot slot : is.getSlots()) {
			InstanceSpecification instance = getInstance(slot);
			if (instance != null) {
				contained.add(instance);
			}
		}
		return contained;
	}

	/**
	 * This method returns the instances contained within a composite instance
	 * specification for an assembly. Unlike @see getContainedInstances, this method only
	 * returns contained instances that are not shared.
	 */
	public static EList<InstanceSpecification> getContainedNonSharedInstances(InstanceSpecification is) {
		EList<InstanceSpecification> contained = new BasicEList<InstanceSpecification>();
		for (Slot slot : is.getSlots()) {
			InstanceSpecification instance = getInstance(slot);
			if ((instance != null) && !DepUtils.isShared(slot)) {
				contained.add(instance);
			}
		}
		return contained;
	}

	/**
	 * return all slots that reference an instance specification
	 *
	 * @param is
	 * @return
	 */
	public static EList<Slot> getReferencingSlots(InstanceSpecification is) {
		EList<Slot> list = new BasicEList<Slot>();
		for (Setting setting : UML2Util.getNonNavigableInverseReferences(is)) {
			EObject eObj = setting.getEObject();
			if (eObj instanceof ValueSpecification) {
				ValueSpecification vs = (ValueSpecification) eObj;
				Element owner = vs.getOwner();
				if (owner instanceof Slot) {
					list.add((Slot) owner);
				}
			}
		}
		return list;
	}

	/**
	 * Return a slot for a given instance specification. The slot is the first one in a list of slots
	 * whose value points to the passed instance.
	 *
	 * @param is
	 *            an instance that is contained within an composite (i.e. that
	 *            belongs to a part of this composite).
	 * @return
	 */
	public static Slot getParentSlot(InstanceSpecification is) {
		for (Slot slot : getReferencingSlots(is)) {
			if (slot.getDefiningFeature() instanceof Property) {
				if (((Property) slot.getDefiningFeature()).getAggregation() == AggregationKind.COMPOSITE_LITERAL) {
					return slot;
				}
			}
		}
		return null;
	}

	/**
	 * Return an instance specification that refers to the composite in which the
	 * passed instance is contained
	 *
	 * @param is
	 *            an instance that is contained within an composite (i.e. that
	 *            belongs to a part of this composite).
	 * @return
	 */
	public static InstanceSpecification getParentIS(InstanceSpecification is) {
		Slot parentSlot = getParentSlot(is);
		if (parentSlot != null) {
			return parentSlot.getOwningInstance();
		}
		return null;
	}

	/**
	 * Return the access path in terms of slots to an instance specification, i.e. the
	 * set of slots starting with the slot within the main instance that identifies the next
	 * instance until arriving at the passed instance.
	 *
	 * @param is
	 * @return
	 */
	public static Stack<Slot> getAccessPath(InstanceSpecification is) {
		Stack<Slot> path = new Stack<Slot>();
		while (is != null) {
			Slot parentSlot = getParentSlot(is);
			if (parentSlot == null) {
				break;
			}
			path.insertElementAt(parentSlot, 0);
			is = parentSlot.getOwningInstance();
		}
		return path;
	}

	/**
	 * Return true, if an instance is shared
	 *
	 * @param slot
	 * @return
	 */
	public static boolean isShared(Slot slot) {
		StructuralFeature df = slot.getDefiningFeature();
		if (df instanceof Property) {
			return ((Property) df).getAggregation() == AggregationKind.SHARED_LITERAL;
		}
		return false;
	}

	/**
	 * Determine which programming language should be generated for a classifier. The
	 * stereotype GeneratorHint (which could be on any owning package) is evaluated.
	 *
	 * @param element
	 *            an element (typically a classifier or a package)
	 * @return the programming language
	 */
	public static String getLanguageFromElement(Element element) {
		GeneratorHint codeGenOpt = UMLUtil.getStereotypeApplication(element, GeneratorHint.class);
		if ((codeGenOpt != null) && (codeGenOpt.getLanguage() != null)) {
			return codeGenOpt.getLanguage().getBase_Class().getName();
		}
		else if (element.getOwner() instanceof Package) {
			return getLanguageFromElement(element.getOwner());
		}
		else {
			// Use C++ as default generation language
			return "C++"; //$NON-NLS-1$;
		}
	}

	/**
	 * Return the target language when given the mainInstance 
	 * @param mainInstance the mainInstance of an application
	 * @return target language
	 */
	public static String getTargetLanguage(InstanceSpecification mainInstance) {
		Classifier cl = DepUtils.getClassifier(mainInstance);
		String targetLanguage = DepUtils.getLanguageFromElement(cl);
		return targetLanguage;
	}
	
	/**
	 * Get all instances within a package that comply with a filter criterion. Recurse into sub-packages.
	 *
	 * @param pkg
	 *            Starting package for search
	 * @param instanceList
	 *            list of instances
	 * @param filter
	 *            filter criterion.
	 */
	public static void getAllInstances(Package pkg, EList<InstanceSpecification> instanceList, ElementFilter filter) {
		for (PackageableElement el : pkg.getPackagedElements()) {
			if (el instanceof Package) {
				getAllInstances((Package) el, instanceList, filter);
			}
			else if (el instanceof InstanceSpecification) {
				InstanceSpecification instance = (InstanceSpecification) el;
				if (filter.acceptElement(instance)) {
					instanceList.add(instance);
				}
			}
		}
	}

	/**
	 * Return the first value for a slot.
	 *
	 * @param slot
	 *            the slot for which the first value should be returned.
	 * @return
	 */
	public static ValueSpecification firstValue(Slot slot) {
		if (slot.getValues().size() > 0) {
			return slot.getValues().get(0);
		}
		return null;
	}
	
	/**
	 * Return true, if aggregation type "none" should be treated in the same way
	 * as aggregation kind "composite". This information is used to create instances
	 * in compositions
	 * @return true, if aggregation type "none" should be as "composite"
	 */
	public static boolean treatNoneAsComposite() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getBoolean(PapyrusDesignerPreferenceConstants.P_TREAT_NONE_AS_COMPOSITE);
	}

	/**
	 * @return true, if all attributes of a class are considered as configuration attributes.
	 */
	public static boolean allAttributesAreConfigAttributs() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getBoolean(PapyrusDesignerPreferenceConstants.P_ALL_ATTRIBUTES_ARE_CONFIG_ATTRIBUTES);
	}

	public static EList<Property> getParts(Class implementation) {
		if (treatNoneAsComposite()) {
			EList<Property> parts = new BasicEList<Property>();
			for (Property part : implementation.getAttributes()) {
				if (part.getAggregation() != AggregationKind.SHARED_LITERAL) {
					parts.add(part);
				}
			}
			return parts;
		}
		else {
			return implementation.getParts();
		}
	}

	/**
	 * return true, if a component (implementation) is a composite, i.e. has
	 * inner parts
	 */
	public static boolean isComposite(Class implementation) {
		return (getParts(implementation).size() > 0);
	}

	/**
	 * @param attribute
	 *            an attribute
	 * @return true, if the aggregation kind is considered as a composition
	 */
	public static boolean isComposition(Property attribute) {
		if (treatNoneAsComposite()) {
			return (attribute.getAggregation() != AggregationKind.SHARED_LITERAL);
		}
		else {
			return (attribute.getAggregation() == AggregationKind.COMPOSITE_LITERAL);
		}
	}

	public static boolean isSingleton(Class component) {
		return StereotypeUtil.isApplied(component, Singleton.class);
	}
	
	public static M2MTrafoChain getTransformationChain(Package cdp) {
		M2MTrafoChain chain = null;
		if (cdp != null) {
			DeploymentPlan cdpStereo = UMLUtil.getStereotypeApplication(cdp, DeploymentPlan.class);
			if (cdpStereo != null) {
				chain = cdpStereo.getChain();
			}
		}
		if (chain == null) {
			
			// first load standard M2M library into resource set
			StdModelLibs.addResource(StdModelLibs.DESIGNER_TRAFOLIB_URI, cdp);
			NamedElement defaultChainNE = ElementUtils.getQualifiedElementFromRS(cdp, TRAFOS_M2MTRANSFORMATIONS_STANDARD);
			if (defaultChainNE != null) { 
				// chain null and default chain could be found.
				chain = UMLUtil.getStereotypeApplication(defaultChainNE, M2MTrafoChain.class);
			}
			if (chain == null) {
				throw new RuntimeException("Can not find default transformation chain");
			}
		}
		return chain;
	}
	
	public static List<M2MTrafo> getAdditionalTransformations(Package cdp) {
		if (cdp != null) {
			DeploymentPlan cdpStereo = UMLUtil.getStereotypeApplication(cdp, DeploymentPlan.class);
			if (cdpStereo != null) {
				return cdpStereo.getAdditionalTrafos();
			}
		}
		return null;
	}
}
