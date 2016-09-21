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

package org.eclipse.papyrus.designer.transformation.core.transformations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.papyrus.designer.transformation.base.utils.CopyUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.StUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Activator;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PostCopyListener;
import org.eclipse.papyrus.designer.transformation.core.copylisteners.PreCopyListener;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * A specific copier that enables to make iterative and shallow copies of model elements
 * from a source to a target model. It also supports copy-listeners, i.e. listeners that
 * might apply modifications before and after an element is copied.
 * This class is very useful for model transformations that make a lazy copy of elements,
 * i.e. copy only elements that are needed in the target model.
 *
 * Iterative means that you can copy one element after another, i.e. you do not need
 * to copy all elements in a single call.
 * Shallow means that some elements are incomplete copies. For instance, if you copy an
 * attribute of a class into the target model, the copy routine will create the attribute within
 * a shallow copy of the original class. The created class is a kind of shallow "container". It
 * is required, since we can't create the attribute without having a class, but it would initially
 * only contain the attribute that we copy. This class would have the same qualified name as the
 * original, i.e. it would be created within shallow packages.
 * A shallow copy can be transformed into a "real" copy
 * by explicitly copying it.
 *
 */
public class LazyCopier extends Copier {

	public enum CopyStatus {
		/**
		 * The status is not known, in most cases this indicates that the object has not yet been copied.
		 */
		UNKNOWN,

		/**
		 * A full copy obtained via the copy function. Full means that the contained features have been completely
		 * copied
		 */
		FULL,

		/**
		 * A full copy in progress. Intermediate state of a target element after creation within the copy function,
		 * before all attributes & references have been copied.
		 */
		INPROGRESS,

		/**
		 * A shallow copy, i.e. a copy only containing a subset of the original element. These are typically containers
		 * for copied objects and avoids that the copies are not enclosed in an object. A shallow copy may become a full
		 * copy later on.
		 */
		SHALLOW
	}

	public static final EObject useSourceEObject = EcoreFactory.eINSTANCE.createEObject();
	
	/**
	 *
	 * @param source
	 *            source package (root)
	 * @param target
	 *            target package (root)
	 * @param copyExtResources_
	 *            copy elements that are not within the same resource instead of referencing them.
	 * @param copyID
	 *            copyID true, if XML IDs should be copied as well.
	 */
	public LazyCopier(Package source, Package target, boolean copyExtResources_, boolean copyID) {
		this.source = source;
		this.target = target;
		// useOriginalReferences = false;
		copyExtReferences = copyExtResources_;
		preCopyListeners = new BasicEList<PreCopyListener>();
		postCopyListeners = new BasicEList<PostCopyListener>();
		templateMapInfo = new HashMap<EObject, Map<EObject, EObject>>();
		standardMap = new HashMap<EObject, EObject>();
		statusMap = new HashMap<EObject, CopyStatus>();
		boundPackages = new Stack<Namespace>();
		put(source, target);
		setStatus(target, CopyStatus.SHALLOW);
		this.copyID = copyID;
		if (copyID) {
			CopyUtils.copyID(source, target);
		}
		rootPkgs = new BasicEList<ModelManagement>();
	};

	/**
	 *
	 */
	private static final long serialVersionUID = -1664013545661635289L;

	/**
	 * Source model within a transformation
	 */

	public Package source;

	/**
	 * Target model within a transformation
	 */
	public Package target;

	/**
	 * if true, copy packages or elements that are imported into the target
	 * model
	 */
	public boolean copyExtReferences;

	/**
	 * Bound package template
	 */
	protected Namespace boundPackage;

	/**
	 * Map to identify target objects when given source objects
	 */
	protected Map<EObject, EObject> standardMap;

	/**
	 * Map to identify target objects when given source objects
	 */
	protected Map<EObject, EObject> templateMap;

	/**
	 * Set of maps for template instantiations
	 */
	protected Map<EObject, Map<EObject, EObject>> templateMapInfo;

	/**
	 * Map using a target EObject as key
	 */
	protected Map<EObject, CopyStatus> statusMap;

	protected boolean copyID;

	protected EList<ModelManagement> rootPkgs;

	/**
	 * Elements within package templates must be treated differently, we have to ensure that:
	 * (1) several instantiations with same binding of the same package template do not lead to double copies
	 * (yet, it may be possible that a 2nd instantiation adds contents, e.g. the trace package template could be
	 * instantiated containing only OTF trace (and superclasses), a second instantiation might add a different trace
	 * implementation)
	 * (2) several instantiations with different binding do not prevent classes from being copied that have already
	 * been copied earlier.
	 * The solution is to use a different map for the elements with package template. This map is instantiated once
	 * for each binding (managed by the TemplateInstantiation class)
	 */
	public Map<EObject, EObject> getMap(EObject sourceEObj) {
		boolean withinTemplate = withinTemplate(sourceEObj);
		return withinTemplate ?
				templateMap :
				standardMap;
	}

	/**
	 * Elements from referenced resources might get copied as well and become
	 * additional root pkgs.
	 * @return
	 */
	public EList<ModelManagement> getAdditionalRootPkgs() {
		return rootPkgs;
	}
	
	@Override
	public EObject get(Object sourceEObj) {
		if (sourceEObj instanceof EObject) {
			Map<EObject, EObject> map = getMap((EObject) sourceEObj);
			return map.get(sourceEObj);
		}
		return null;
	}

	@Override
	public EObject put(EObject sourceEObj, EObject targetEObj) {
		if (sourceEObj instanceof EObject) {
			Map<EObject, EObject> map = getMap(sourceEObj);
			return map.put(sourceEObj, targetEObj);
		}
		return null;
	}

	/**
	 * Put a pair into the copy map. Unlike the standard put operation,
	 * the target object is marked as full copy.
	 * Just using the put operation leads to bug 422899 - [QDesigner] Regression in
	 * template instantiation
	 *
	 * @return
	 */
	public EObject putPair(EObject sourceEObj, EObject targetEObj) {
		EObject target = put(sourceEObj, targetEObj);
		setStatus(targetEObj, CopyStatus.FULL);
		return target;
	}

	@Override
	public boolean containsKey(Object sourceEObj) {
		if (sourceEObj instanceof EObject) {
			Map<EObject, EObject> map = getMap((EObject) sourceEObj);
			return map.containsKey(sourceEObj);
		}
		return false;
	}

	@Override
	public EObject remove(Object sourceEObj) {
		if (sourceEObj instanceof EObject) {
			Map<EObject, EObject> map = getMap((EObject) sourceEObj);
			return map.remove(sourceEObj);
		}
		return null;
	}

	/**
	 * Set the status of a copy object
	 *
	 * @param targetEObj
	 * @param status
	 */
	public void setStatus(EObject targetEObj, CopyStatus status) {
		statusMap.put(targetEObj, status);
	}

	/**
	 * return true, if a shallow copy of the passed EObject exists
	 *
	 * @param sourceEObj
	 * @return
	 */
	public CopyStatus getStatus(EObject targetEObj) {
		if (targetEObj != null) {
			CopyStatus status = statusMap.get(targetEObj);
			if (status != null) {
				return status;
			}
		}
		return CopyStatus.UNKNOWN;
	}

	/**
	 * Set the reference of a bound package template. It must be a member of the target model.
	 * Setting the package template is required to assure that elements that are part of a different
	 * resource get copied (if the copyExtReferences flag is set to false, copying would not be done otherwise)
	 *
	 * @param packageTemplate
	 *            Reference to package (with a template signature) in source model that should be instantiated
	 * @param boundPackage
	 *            Reference to (an initially empty) package in which the packate template will be instantiated
	 *            during the copy process
	 */
	public void setPackageTemplate(Namespace packageTemplate, Namespace boundPackage) {
		this.boundPackage = boundPackage;
		if (packageTemplate == null) {
			return;
		}
		templateMap =
				templateMapInfo.get(boundPackage);
		if (templateMap == null) {
			templateMap = new HashMap<EObject, EObject>();
			templateMapInfo.put(boundPackage, templateMap);
		}
		// declare relation between packageTemplate and bound package
		// but: the owner of the package template is not equal to the packageTemplate (e.g. perClass)
		// since we can extend package templates in different models.
		standardMap.put(packageTemplate, boundPackage);
	}

	public void pushPackageTemplate() {
		boundPackages.push(boundPackage);
	}

	public void popPackageTemplate() {
		boundPackage = boundPackages.pop();
		templateMap =
				templateMapInfo.get(boundPackage);
	}

	private Stack<Namespace> boundPackages;

	@SuppressWarnings("unchecked")
	public EList<EObject> getRefs(EReference eReference, EObject eObject) {
		EList<EObject> refs = new BasicEList<EObject>();
		if (eObject.eIsSet(eReference)) {
			if (eReference.isMany()) {
				// @SuppressWarnings("unchecked")
				refs.addAll((List<EObject>) eObject.eGet(eReference));
			} else {
				refs.add((EObject) eObject.eGet(eReference));
			}
		}
		return refs;
	}

	/**
	 * Check whether the passed element (within the source model) is within a
	 * a template, i.e. one of is owners is mapped towards the bound package in
	 * the target model. Note that multiple owners in the source model may be
	 * mapped to the same bound package.
	 *
	 * @param element
	 * @return
	 */
	public boolean withinTemplate(EObject element) {
		if (boundPackage != null) {
			EObject owner = element;
			if ((element.eContainer() == null) &&
					!(element instanceof Element)) { // has no eContainer and is not a UML element => likely to be a be a stereotype application.
				// it is important not to call getBaseElement for all eobjects, since its execution can take
				// quite a while (in particular, if not called on a stereotype application)
				Element base = UMLUtil.getBaseElement(owner);
				if (base != null) {
					owner = base; // containment check is done with base element
				}
			}
			while (owner != null) {
				owner = owner.eContainer();
				if (get(owner) == boundPackage) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a copy of the given eObject.
	 *
	 * Normally, we do not want to copy elements that are from a different
	 * resource. There are two exceptions (1) if this is explicitly specified
	 * (for producing "complete" models) (2) if we want to copy elements from a
	 * template into the target model.
	 *
	 * @param sourceEObj
	 *            the object to copy.
	 * @return the copy.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EObject copy(EObject sourceEObj) {
		if (sourceEObj == null) {
			// this case may happen, if elements are systematically copied without checking for
			// null references in the application code (e.g. if we copy a part-with-port which might
			// be null in case of delegation or connectors without ports
			return null;
		}


		EObject targetEObj = get(sourceEObj);

		CopyStatus status = getStatus(targetEObj);

		if (status == CopyStatus.FULL || status == CopyStatus.INPROGRESS) {
			// copy already exists, return targetEObj
			return targetEObj;
		}

		boolean withinTemplate = withinTemplate(sourceEObj);
		boolean sameResource = (sourceEObj.eResource() == source.eResource());
		if (!sameResource && !copyExtReferences && !withinTemplate) {
			// do not copy if within different resource, unless
			// 1. copyExtReferences
			// 2. within package template
			return sourceEObj;
		}
	
		if ((sourceEObj instanceof Stereotype) || (sourceEObj instanceof Profile)) {
			// do not copy Stereotypes, as it would imply copying meta-model elements (the base_X
			// attribute of the stereotype is typed with a meta-model element)
			return sourceEObj;
		}

		for (PreCopyListener listener : preCopyListeners) {
			EObject result = listener.preCopyEObject(this, sourceEObj);
			if (result != sourceEObj) {
				if (result == useSourceEObject) {
					return sourceEObj;
				}
				return result;
			}
		}

		if (sourceEObj instanceof NamedElement) {
			String name = ((NamedElement) sourceEObj).getQualifiedName();
			if ((name != null) && name.startsWith("uml::")) { //$NON-NLS-1$
				Activator.log.debug("copy for meta-model element \"" + name + //$NON-NLS-1$
						"\" requested. Return original element"); //$NON-NLS-1$
				return sourceEObj;
			}
		}
		// additional sanity check: want to avoid copying (instead of instantiating) elements
		// of a package template
		if ((sourceEObj instanceof Package) && (!withinTemplate)) {
			if (((Package) sourceEObj).getOwnedTemplateSignature() != null) {
				Activator.log.debug("warning: copying a package template without instantiating a template"); //$NON-NLS-1$
			}
		}

		if (status == CopyStatus.SHALLOW) {
			// copy exists, but was a shallow copy, change status to INPROGRESS
			setStatus(targetEObj, CopyStatus.INPROGRESS);
		}
		else {
			targetEObj = createCopy(sourceEObj);
			put(sourceEObj, targetEObj);
			setStatus(targetEObj, CopyStatus.INPROGRESS);
			if (copyID) {
				CopyUtils.copyID(sourceEObj, targetEObj);
			}
			// creates a shallow copy of the container. This container will update containment references (such as packagedElement)
			// and thus update links
			createShallowContainer(sourceEObj);
		}
		EClass eClass = sourceEObj.eClass();
		for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i)
		{
			EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
			if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
			{
				if (eStructuralFeature instanceof EAttribute) {
					copyAttribute((EAttribute) eStructuralFeature, sourceEObj, targetEObj);
				}
				else {
					EReference eReference = (EReference) eStructuralFeature;
					if (eReference.isContainment()) {
						copyContainment(eReference, sourceEObj, targetEObj);
					}
					// some containment relationships require copying the container completely
					// e.g. if an owned template signature is referenced, we need to follow the "template"
					// reference, which subsets the "owner" relationship.
					// e.g. if an operation is referenced, we need to copy the whole interface
					// Currently: only the standard owning reference is not copied recursively.

					else if (!eReference.getName().equals("owner") && //$NON-NLS-1$
							(!eReference.getName().equals("owningInstance"))) { //$NON-NLS-1$
						Object feature = sourceEObj.eGet(eStructuralFeature);
						if (feature instanceof Element) {
							copy((Element) feature);
						} else if (feature instanceof EList) {
							copyAll((EList<Object>) feature);
						}
						copyReference(eReference, sourceEObj, targetEObj);
					}
				}
			}
			else if ((eStructuralFeature instanceof EReference)) {
				if (eStructuralFeature.getName().equals("clientDependency")) { //$NON-NLS-1$
					Object feature = sourceEObj.eGet(eStructuralFeature);

					if (feature instanceof Element) {
						copy((Element) feature);
					} else if (feature instanceof EList) {
						copyAll((EList<Object>) feature);
					}
				}
			}
		}
		copyProxyURI(sourceEObj, targetEObj);
		CopyUtils.copyID(sourceEObj, targetEObj);
		copyStereotypes(sourceEObj);
		setStatus(targetEObj, CopyStatus.FULL);

		for (PostCopyListener listener : postCopyListeners) {
			listener.postCopyEObject(this, targetEObj);
		}

		return targetEObj;
	}

	/**
	 * @param sourceEObj
	 * @return a copy, if it already exists. If it does not exist, return the
	 *         source object itself, if no copy is required, otherwise return null.
	 */
	public EObject noCopy(EObject sourceEObj) {
		boolean withinTemplate = withinTemplate(sourceEObj);
		boolean sameResource = (sourceEObj.eResource() == source.eResource());
		if (!sameResource && !copyExtReferences && !withinTemplate) {
			return sourceEObj;
		}
		else {
			return get(sourceEObj);
		}
	}

	/**
	 * Copy stereotype applications. Since stereotype applications are not part of the containment of an eObject, they are not copied by the
	 * generic function.
	 * A problem of copying stereotypes is that it may drag whole hierarchies with it, for instance if we copy the base_ attributes,
	 * we transform a shallow copy into a normal copy.
	 * => always make shallow copies of packages, never shallow copies of classes? [the split into fragments is solved, but the split of the system component???]
	 */
	public void copyStereotypes(EObject sourceEObj, boolean duringShallow) {
		if (sourceEObj instanceof Element) {

			for (EObject stereoApplication : ((Element) sourceEObj).getStereotypeApplications()) {
				EObject copiedStereoApplication = (duringShallow) ?
						shallowCopy(stereoApplication) :
						copy(stereoApplication);

				if (copiedStereoApplication != null) {
					// UMLUtil.setBaseElement(copiedStereoApplication, (Element) get(sourceEObj));
					// add copied stereotype application to the resource (as top-level objects).
					if (!target.eResource().getContents().contains(copiedStereoApplication)) {
						target.eResource().getContents().add(copiedStereoApplication);
					}
				}
			}
		}
	}

	public void copyStereotypes(EObject sourceEObj) {
		copyStereotypes(sourceEObj, false);
	}

	public void shallowCopyStereotypes(EObject sourceEObj) {
		copyStereotypes(sourceEObj, true);
	}

	/**
	 * Copy the containment of an element with respect to a certain reference
	 *
	 * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyContainment(org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 *      Differences to referenced function in ECoreUtil
	 *      - If an element in copyAll is null, it is not added
	 *      - List elements are always cleared before copying, since the list elements may already have been
	 *      partially filled by a previous shallow copy
	 *
	 * @param eReference
	 *            a reference, such as for instance packagedElement (the
	 *            caller needs to check, is this reference is a containment reference).
	 * @param eObject
	 *            the source eObject
	 * @param copyEObject
	 *            the copy of this eObject
	 */
	@Override
	protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject) {
		if (eObject.eIsSet(eReference)) {
			if (eReference.isMany()) {
				@SuppressWarnings("unchecked")
				List<EObject> source = (List<EObject>) eObject.eGet(eReference);
				@SuppressWarnings("unchecked")
				List<EObject> target = (List<EObject>) copyEObject.eGet(getTarget(eReference));
				// do not clear target element (would remove elements that are added by copy listeners)
				// But: better enforce exact copy? (listeners could only add in a post-copy step)
				// target.clear();
				if (!source.isEmpty()) {
					for (EObject copyEObj : copyAll(source)) {
						if (copyEObj != null) {
							target.add(copyEObj);
						}
					}
				}
			} else {
				EObject childEObject = (EObject) eObject.eGet(eReference);
				copyEObject.eSet(getTarget(eReference), childEObject == null ? null : copy(childEObject));
			}
		}
	}

	/**
	 * Copy the containment in a "shallow" way, i.e. copy references to contained objects, if these exist already.
	 * If called for instance for a package, it will add those elements to the packagedElements list of the
	 * target package, that have already been copied.
	 *
	 * @param eReference
	 * @param eObject
	 * @param copyEObject
	 */
	protected void shallowCopyContainment(EReference eReference, EObject eObject, EObject copyEObject) {
		if (eObject.eIsSet(eReference)) {
			if (eReference.isMany()) {
				@SuppressWarnings("unchecked")
				List<EObject> source = (List<EObject>) eObject.eGet(eReference);
				@SuppressWarnings("unchecked")
				List<EObject> target = (List<EObject>) copyEObject.eGet(getTarget(eReference));
				if (source.isEmpty()) {
					target.clear();
				} else {
					for (EObject sourceEObj : source) {
						// if eObject has already been copied, add it to target
						// don't add, if copyEObj is identical to sourceEObj. This would imply manipulating an
						// element of the source model.
						EObject copyEObj = noCopy(sourceEObj);
						if ((copyEObj != null) && (copyEObj != sourceEObj) && (!target.contains(copyEObj))) {
							try  {
								target.add(copyEObj);
							}
							catch (Exception e) {
								System.err.println(e);
							}
						}
					}
				}
			} else {
				EObject childEObject = (EObject) eObject.eGet(eReference);
				// get will return null, if object should not be copied. In this case, we do not want to replace
				copyEObject.eSet(getTarget(eReference), childEObject == null ? null : noCopy(childEObject));
			}
		}
	}

	/**
	 * Create a "shallow" container for an object, i.e. create (recursively) the owner without
	 * adding all other children of this owner (e.g. in case of a package, the package itself will
	 * be created, but not all elements within that package).
	 *
	 * @param sourceEObj
	 */
	public void createShallowContainer(EObject sourceEObj) {
		EObject owner = sourceEObj.eContainer();
		EObject copy = null;
		EObject lastSource = null;
		EList<EObject> copyStereoList = new BasicEList<EObject>();
		boolean contained = false;
		while (owner != null) {
			if (containsKey(owner)) {
				// owner is in map, still need to re-copy (update) the containment
				// references, since one of the children did not exist before
				shallowCopy(owner);
				contained = true;
				break;
			}
			copy = shallowCopy(owner);
			copyStereoList.add(lastSource);
			lastSource = owner;
			owner = owner.eContainer();
		}
		if (copy instanceof Package && !contained) {
			Package copiedPkg = (Package) copy;
	
			// if we copy external resources, we might reach the "top" on the source level
			// which becomes a new top-level element that is added to a new resource (managed by the
			// model management instance) below.
			ModelManagement mm = new ModelManagement(copiedPkg);
			rootPkgs.add(mm);
			
			try {
				StUtils.copyProfileApplications((Package) lastSource, copiedPkg);
			}
			catch (TransformationException e) {
				throw new RuntimeException(e);
			}
		}
		// copy the stereotypes after the container has been created completely
		// (stereotypes are added to the resource contents, which becomes defined after
		// adding an element to the model tree)
		for (EObject copyStereo : copyStereoList) {
			copyStereotypes(copyStereo);
		}
	}

	/**
	 * Make a shallow copy of an element, i.e. only create the element itself and not
	 * all of its contents. If a subset of the containing elements already exist in the copied
	 * model, update the containment references pointing to these. The function may be called
	 * multiple times in order to add elements to the containment references that
	 * have been copied since the previous call (i.e. it is possible to make a shallow copy
	 * of a package after a single class within it has been copied. It may be called again,
	 * once a second class within the package has been copied => the packagedElements reference
	 * of the package will be updated).
	 *
	 * It is important that the implementation of this object does not make recursive calls.
	 * In particular, stereotypes are based on shallow copy as well. This means that stereotype
	 * attributes that reference other model elements will only be initialized if these elements
	 * exist already.
	 *
	 * @param sourceEObj
	 * @return
	 */
	public EObject shallowCopy(EObject sourceEObj) {
		boolean first = false;
		EObject targetEObj = get(sourceEObj);
		if (targetEObj == null) {
			targetEObj = createCopy(sourceEObj);
			put(sourceEObj, targetEObj);
			setStatus(targetEObj, CopyStatus.SHALLOW);
			if (copyID) {
				CopyUtils.copyID(sourceEObj, targetEObj);
			}
			first = true;
		}
		else if (getStatus(targetEObj) == CopyStatus.FULL) {
			// object has already been completely copied. Nothing to do, return targetEObj.
			// Note that this implies that the update of references below is called for full copies
			// in progress and shallow copies. The former assures that all copied elements have an
			// eContainer during the call of pre-copy listeners (example: if a class is copied, its
			// operations are recursively copied, the ownedOperation relationship is only updated
			// *afterwards* by the code within the (full) copy operation).
			return targetEObj;
		}

		shallowCopyStereotypes(sourceEObj);

		EClass eClass = sourceEObj.eClass();

		for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i) {
			EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
			if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived()) {
				if (eStructuralFeature instanceof EAttribute) {
					// copy all attributes during first pass after creation of target object
					if (first) {
						copyAttribute((EAttribute) eStructuralFeature, sourceEObj, targetEObj);
					}
				} else {
					EReference eReference = (EReference) eStructuralFeature;
					// create a shallow copy of the containment: update only references already in the copy map
					if (sourceEObj != targetEObj) {
						shallowCopyContainment(eReference, sourceEObj, targetEObj);
					}
				}
			}
		}
		return targetEObj;
	}

	@SuppressWarnings("unchecked")
	public <T extends Element> T getCopy(T source) {
		return (T) copy(source);
	}

	public EList<PreCopyListener> preCopyListeners;

	public EList<PostCopyListener> postCopyListeners;

	/**
	 * Called to handle the copying of a cross reference;
	 * this adds values or sets a single value as appropriate for the multiplicity
	 * while omitting any bidirectional reference that isn't in the copy map.
	 *
	 * @param eReference
	 *            the reference to copy.
	 * @param eObject
	 *            the object from which to copy.
	 * @param copyEObject
	 *            the object to copy to.
	 */
	@Override
	protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject)
	{
		if (eObject.eIsSet(eReference)) {
			if (eReference.isMany()) {
				@SuppressWarnings("unchecked")
				InternalEList<EObject> source = (InternalEList<EObject>) eObject.eGet(eReference);
				@SuppressWarnings("unchecked")
				InternalEList<EObject> target = (InternalEList<EObject>) copyEObject.eGet(getTarget(eReference));
				if (source.isEmpty()) {
					target.clear();
				}
				else {
					boolean isBidirectional = eReference.getEOpposite() != null;
					int index = 0;
					for (Iterator<EObject> k = resolveProxies ? source.iterator() : source.basicIterator(); k.hasNext();) {
						EObject referencedEObject = k.next();
						EObject copyReferencedEObject = get(referencedEObject);
						// check filters (modification compared to method in superclass)
						boolean noCopy = false;
						for (PreCopyListener listener : preCopyListeners) {
							EObject result = listener.preCopyEObject(this, referencedEObject);
							if (result != referencedEObject) {
								copyReferencedEObject = result;
								noCopy = (result == null);
								break;
							}
						}
						if (noCopy) {
							continue;
						}
						if (copyReferencedEObject == null) {
							if (useOriginalReferences && !isBidirectional) {
								target.addUnique(index, referencedEObject);
								++index;
							}
						}
						else {
							if (isBidirectional) {
								int position = target.indexOf(copyReferencedEObject);
								if (position == -1) {
									target.addUnique(index, copyReferencedEObject);
								}
								else if (index != position) {
									target.move(index, copyReferencedEObject);
								}
							}
							else if (!target.contains(copyReferencedEObject)) {
								// TODO: does not allow multiple identical elements in the list. Problematic?
								// Check above is necessary, since some references that are not
								// part of the containment may have already been copied (e.g. in case of
								// a TemplateSignature "ownedParameter" subsets "parameter", thus copying
								// ownedParameter as part of the containment adds a template parameter)
								target.addUnique(index, copyReferencedEObject);
							}
							++index;
						}
					}
				}
			}
			else {
				Object referencedEObject = eObject.eGet(eReference, resolveProxies);
				if (referencedEObject == null) {
					copyEObject.eSet(getTarget(eReference), null);
				}
				else if (referencedEObject instanceof EObject) {
					// difference to original code in EcoreUtil: we obtain a copy (which might be null or the
					// source object) of the referenced EObject. This assures that we only set a value of a
					// reference to something we actually want to have in the target model.
					// Specific problematic case in original code: classifierBehavior is a reference, but assigning such
					// a behavior will also add an owned behavior. If we assign a referencedEObject (a behavior) from the
					// source model in the target, we will actually remove it from the source model (as it is uniquely owned).
					EObject copyReferencedEObject = copy((EObject) referencedEObject);
					if (copyReferencedEObject != null) {
						copyEObject.eSet(getTarget(eReference), copyReferencedEObject);
					}
				}
			}
		}
	}

	/**
	 * Copy all methods from the passed source-model class.
	 * This function is useful, if the passed class only exist
	 * as a shallow copy.
	 *
	 * @param source
	 *            A class within the source model
	 */
	public void copyMethods(Class source) {
		for (Behavior method : source.getOwnedBehaviors()) {
			getCopy(method);
		}
	}

	/**
	 * Copy all attributes from the source-model classifier
	 * This function is useful, if the passed class only exist
	 * as a shallow copy.
	 *
	 * @param source
	 *            A classifier within the source model
	 */
	public void copyAttributes(Classifier source) {
		for (Property attribute : source.getAttributes()) {
			getCopy(attribute);
		}
	}

	/**
	 * copy all operations from the source-model classifier.
	 * This function is useful, if the passed class only exist
	 * as a shallow copy.
	 *
	 * @param source
	 *            A classifier within the source model
	 */
	public void copyOperations(Classifier source) {
		for (Operation operation : source.getOperations()) {
			getCopy(operation);
		}
	}
}
