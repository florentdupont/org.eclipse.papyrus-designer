/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.transformation.library.transformations;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.designer.deployment.tools.Activator;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageCodegen;
import org.eclipse.papyrus.designer.transformation.base.UIContext;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

public class GenerateCode implements IM2MTrafoCDP {

	public void generate(InstanceSpecification node, String targetLanguage)
			throws TransformationException, InterruptedException
	{
		IProgressMonitor monitor = UIContext.monitor;
		IProject genProject = TransformationContext.current.project;
		
		Package genModel = TransformationContext.current.mm.getModel();

		monitor.worked(1);
		if (genProject == null) {
			return;
		}

		if (node == null) {
			monitor.setTaskName(String.format(Messages.GenerateCode_GeneratingCode, targetLanguage));
		}
		else {
			monitor.setTaskName(String.format(Messages.GenerateCode_GeneratingCodeForNode, targetLanguage, node.getName()));
		}
		IFolder folder = genProject.getFolder(genModel.getName());
		try {
			folder.delete(true, null);
		} catch (CoreException e) {
			throw new TransformationException(String.format(Messages.GenerateCode_CouldNotDeleteOldCode, e.getMessage()));
		}
		ILangCodegen codegen = LanguageCodegen.getGenerator(targetLanguage);
	
		// the generated model can contain more than one top-level element due to copied external model references
		Resource genModelResource = genModel.eResource();
		for (EObject topLevelElement : genModelResource.getContents()) {
			if (topLevelElement instanceof Package) {
				codegen.generateCode(genProject, (Package) topLevelElement, monitor);
			}
		}

		if (monitor.isCanceled()) {
			return;
		}
		monitor.worked(1);
	}

	/**
	 * @param element
	 * @return
	 */
	public PackageableElement getNearestClassifierOrPackage(Element element) {
		while (element != null) {
			if (element instanceof Classifier) {
				return (Classifier) element;
			}
			if (element instanceof Package) {
				return (Package) element;
			}
			element = element.getOwner();
		}
		return null;
	}

	/*
	 * public void deleteRemovedClasses(EObject diffModelOrElement) throws TransformationException {
	 * EList<DiffElement> diffs;
	 * if(diffModelOrElement instanceof DiffModel) {
	 * diffs = ((DiffModel)diffModelOrElement).getDifferences();
	 * }
	 * else if(diffModelOrElement instanceof DiffElement) {
	 * diffs = ((DiffElement)diffModelOrElement).getSubDiffElements();
	 * }
	 * else {
	 * return;
	 * }
	 * for(DiffElement diff : diffs) {
	 * EObject modifiedEObj = null;
	 *
	 * if(diff.getKind() == DifferenceKind.DELETION) {
	 * if(diff instanceof AttributeChange) {
	 * modifiedEObj = ((AttributeChange)diff).getRightElement();-
	 * }
	 * else if(diff instanceof ModelElementChangeRightTarget) {
	 * modifiedEObj = ((ModelElementChangeRightTarget)diff).getRightElement();
	 * }
	 * }
	 * else if(diff.getKind() == DifferenceKind.CHANGE) {
	 * if(diff instanceof AttributeChange) {
	 * modifiedEObj = ((AttributeChange)diff).getRightElement();
	 * }
	 * }
	 * if(modifiedEObj instanceof PackageableElement) {
	 * // => delete tree
	 * langSupport.cleanCode(monitor, (PackageableElement)modifiedEObj);
	 * }
	 * // no recursion needed?
	 * // deleteRemovedClasses(diff);
	 * }
	 * }
	 */

	@Override
	public void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
		// get first language (restricted to single target language, acceptable?)

		String targetLanguage = DepUtils.getTargetLanguage(DepUtils.getTopLevelInstances(deploymentPlan).iterator().next());
		try {
			generate(null, targetLanguage);
		}
		catch (InterruptedException e) {
			Activator.log.error(e);
		}
	}
}
