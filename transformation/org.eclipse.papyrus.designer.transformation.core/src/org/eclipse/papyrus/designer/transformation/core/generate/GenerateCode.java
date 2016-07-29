package org.eclipse.papyrus.designer.transformation.core.generate;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.ModelManagement;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationException;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

public class GenerateCode {

	public GenerateCode(IProject genProject, ILangCodegen codegen, ModelManagement genMM, IProgressMonitor monitor) {
		this.genProject = genProject;
		this.genMM = genMM;
		this.monitor = monitor;
		this.codegen = codegen;
	}

	public void generate(InstanceSpecification node, String targetLanguage, boolean differential)
			throws TransformationException, InterruptedException
	{
		String path = genMM.getPath(genProject, "model", null); //$NON-NLS-1$
		Package genModel = genMM.getModel();

		genMM.saveModel(path);

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
		codegen.generateCode(genProject, genModel, monitor);

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

	private ModelManagement genMM;

	private IProject genProject;

	private IProgressMonitor monitor;

	private ILangCodegen codegen;
}
