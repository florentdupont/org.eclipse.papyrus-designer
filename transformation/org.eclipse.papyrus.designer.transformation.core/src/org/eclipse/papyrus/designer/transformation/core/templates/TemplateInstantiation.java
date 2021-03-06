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
package org.eclipse.papyrus.designer.transformation.core.templates;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.designer.languages.common.base.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier.CopyStatus;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.core.transformations.filters.FilterSignatures;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;

/**
 * This class encapsulates functions around template instantiation.
 *
 * With respect to composite structures, we need to consider two different
 * cases: (1) the type of a property is a template class itself (2) the type of
 * a property is a sub-class (nesting) of the template class. We consider that
 * such a class inherits the template signature of its owner, i.e. it can access
 * formal parameters of the owning template class. (3) the type of a property is
 * a normal class
 */
public class TemplateInstantiation {

	/**
	 * Convenience constructor, take copier from transformation context.
	 * 
	 * @param binding
	 * @throws TransformationException
	 */
	public TemplateInstantiation(TemplateBinding binding) throws TransformationException {
		this(TransformationContext.current.copier, binding);

	}

	/**
	 * Create a new template instantiation from a given copier and binding
	 * 
	 * @param copier
	 *            a lazy copier
	 * @param binding
	 *            a template binding (formal->actual mapping)
	 * @throws TransformationException
	 */
	public TemplateInstantiation(LazyCopier copier, TemplateBinding binding) throws TransformationException {
		if (binding == null) {
			// user should never see this exception
			throw new TransformationException("Passed binding is null"); //$NON-NLS-1$
		}
		this.copier = copier;
		signature = binding.getSignature();
		if (signature == null) {
			throw new TransformationException("Passed template binding does not have a signature"); //$NON-NLS-1$
		}
		packageTemplate = (Package) signature.getOwner();

		Package boundPackage = (Package) binding.getBoundElement();
		// set template instantiation parameter. Used by xtend templates to get relation between
		// formal and actual parameters
		context = new InstantiationContext(this);
		copier.setPackageTemplate(packageTemplate, boundPackage);
		// some parameters of the package template may not be owned. Thus, an additional package
		// template is involved in the instantiation
		for (TemplateParameter parameter : signature.getParameters()) {
			//
			if (parameter.getSignature() != signature) {
				Package addedPkgTemplate = parameter.getSignature().getNearestPackage();
				copier.setPackageTemplate(addedPkgTemplate, boundPackage);
			}
		}
		if (boundPackage.getPackagedElements() != null) {
			// bound package is not empty, but copier does not know about it. Fill copyMap with information about the relation
			// This happens, if the original model already contains template instantiations, e.g. for template ports
			if (copier.getMap(signature).keySet().size() == 0) {
				sourceResource = packageTemplate.eResource();
				targetResource = boundPackage.eResource();
				syncCopyMap(packageTemplate, boundPackage);
			}
			// syncCopy map would not be necessary, if ports are not updated (by a listener?) during copier)
		}
		this.binding = binding;
		// register a combination of formal/actual in the hashmap
		// => copier will replace actual with formal
		for (TemplateParameterSubstitution substitution : binding.getParameterSubstitutions()) {
			ParameterableElement formal = substitution.getFormal().getParameteredElement();
			ParameterableElement actual = substitution.getActual();
			copier.putPair(formal, actual);
		}
		// add copy listeners ---
		// remove template signature
		if (!copier.preCopyListeners.contains(FilterSignatures.getInstance())) {
			copier.preCopyListeners.add(FilterSignatures.getInstance());
		}
		// 2a. special treatment for elements stereotyped with template parameter
		if (!copier.preCopyListeners.contains(PreTemplateInstantiationListener.getInstance())) {
			copier.preCopyListeners.add(PreTemplateInstantiationListener.getInstance());
		}
		PreTemplateInstantiationListener.getInstance().init(copier, binding);
		// 2b. special treatment for elements stereotyped with template parameter
		if (!copier.postCopyListeners.contains(PostTemplateInstantiationListener.getInstance())) {
			copier.postCopyListeners.add(PostTemplateInstantiationListener.getInstance());
		}
		/*
		 * PostTemplateInstantiationListener.getInstance().init(copier, binding);
		 * if(!copier.postCopyListeners.contains(FixTemplateSync.getInstance())) {
		 * copier.postCopyListeners.add(FixTemplateSync.getInstance());
		 * }
		 */
	}

	/**
	 * Synchronize the copy map, i.e. put the correspondences between existing source and target elements into the map.
	 * Otherwise, a new binding would produce duplicates.
	 * TODO: A more efficient way would be to cache the copy function and only re-sync, if a new model has been loaded.
	 * On the other hand, the bound package is normally not very large
	 *
	 * @param source
	 *            An element of the source model
	 * @param targetPkg
	 *            The associated element of the target model
	 */
	public void syncCopyMap(EObject source, EObject targetPkg) {
		if ((copier.get(source) == null) || copier.getStatus(targetPkg) != CopyStatus.FULL) {
			copier.put(source, targetPkg);
			if (source instanceof Package) {
				copier.setStatus(targetPkg, CopyStatus.SHALLOW);
			} else {
				copier.setStatus(targetPkg, CopyStatus.FULL);
			}
			for (EObject targetSubElem : targetPkg.eContents()) {
				EObject sourceSubElement = null;
				if (targetSubElem instanceof NamedElement) {
					String targetName = ((NamedElement) targetSubElem).getName();
					sourceSubElement = ElementUtils.getNamedElementFromList(source.eContents(), targetName);
				}
				if (sourceSubElement == null) {
					// no source element found, try to find via XML URI (copier synchronizes on demand)
					String uriFragment = targetResource.getURIFragment(targetSubElem);
					sourceSubElement = sourceResource.getEObject(uriFragment);
				}
				if (sourceSubElement != null) {
					syncCopyMap(sourceSubElement, targetSubElem);
				}
			}
		}
	}

	Package packageTemplate;

	public TemplateBinding binding;

	protected LazyCopier copier;

	protected TemplateSignature signature;

	protected Resource sourceResource;

	protected Resource targetResource;

	/**
	 * Bind a named element. Besides of binding the passed element, this
	 * operation will bind all elements that are referenced (required) by the
	 * passed element.
	 *
	 * In consequence, typically only a small part of a package template is
	 * actually created within the bound package. We call this mechanism lazy
	 * instantiation/binding
	 *
	 * @param copier
	 *            Source and target model
	 * @param namedElement
	 *            A member within the package template which should be bound,
	 *            i.e. for which template instantiation should be performed.
	 * @param binding
	 *            The binding between the bound package and the package template
	 */
	@SuppressWarnings("unchecked")
	public <T extends Element> T bindElement(T namedElement) throws TransformationException {
		if (namedElement == null) {
			// user should never see this exception
			throw new TransformationException(Messages.TemplateInstantiation_TemplateIsNull);
		}
		Package boundPackage = (Package) binding.getBoundElement();
		EList<Namespace> path = TemplateUtils.relativePathWithMerge(namedElement, packageTemplate);

		if (path != null) {
			// register owning package template (template can be defined in
			// multiple packages)
			Element owner = TemplateUtils.getTemplateOwner(namedElement, signature);
			if (owner != null) {
				// note that we might overwrite an existing value
				copier.put(owner, boundPackage);
			}
		} else {
			// element is not part of the package template referenced by the
			// binding
			if (namedElement instanceof TemplateableElement) {
				// check whether the referenced element is part of another
				// package template,
				// (for which we allow for implicit binding with the first
				// template parameter)
				TemplateSignature signatureOfNE = TemplateUtils.getSignature((TemplateableElement) namedElement);
				if ((signatureOfNE != null) && (signature != signatureOfNE)) {
					TemplateBinding subBinding = TemplateUtils.getSubBinding(copier.target, (TemplateableElement) namedElement, binding);
					TemplateInstantiation ti = new TemplateInstantiation(subBinding);
					Element ret = ti.bindElement(namedElement);
					return (T) ret;
				}
			}
			// => nothing to do with respect to template instantiation, but
			// since the template is potentially instantiated in another model,
			// the referenced element might need to be copied.
			return copier.getCopy(namedElement);
		}
		// element is contained in the template package, examine whether it
		// already exists in the
		// bound package.
		NamedElement existingMember = (NamedElement) copier.get(namedElement);
		/*
		 * if((existingMember != null) && (templateKind != TemplateKind.ACCUMULATE)) {
		 * // element is already existing (and thus bound), nothing to do
		 * // additional check, whether the ACCUMULATE information is unset)
		 * // however: if the element is a package, existence is not sufficient
		 * // since it might have been created via getAndCreate above
		 *
		 * //if(namedElement instanceof Package) {
		 * // bindPackage((Package)namedElement);
		 * //}
		 * return (T)existingMember;
		 * }
		 */
		if (existingMember == null) {
			T copiedElement = copier.getCopy(namedElement);
			copier.setPackageTemplate(null, null);
			return copiedElement;
		}
		return (T) existingMember;
	}

	public static InstantiationContext context;
}
