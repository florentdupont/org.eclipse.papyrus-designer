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
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.deployment.tools.DeployConstants;
import org.eclipse.papyrus.designer.languages.common.base.TestInfo;
import org.eclipse.papyrus.designer.transformation.base.UIContext;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Activator;
import org.eclipse.papyrus.designer.transformation.core.EnumService;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.generate.GenerationOptions;
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateUtils;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier.CopyExtResources;
import org.eclipse.papyrus.designer.transformation.extensions.InstanceConfigurator;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafoChain;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * This class executes all transformations during the instantiation of a
 * deployment plan, i.e. 1. The reification of connectors (including template
 * instantiation). This transformation targets a new model 2. Adding
 * get_p/connect_q operations to a class (transformation within same model) 3.
 * Remove all component types 4. distribute to nodes
 *
 * @author ansgar
 *
 */
public class InstantiateDepPlan {

	/**
	 * The location of the temporary model (relative to current project). TODO:
	 * make configurable?
	 */
	public static final String TEMP_MODEL_FOLDER = "tmpModel"; //$NON-NLS-1$

	/**
	 * Postfix of the temporary model (prefix = name of top-level package).
	 * TODO: make configurable?
	 */
	public static final String TEMP_MODEL_POSTFIX = "Tmp.uml"; //$NON-NLS-1$

	/**
	 * Progress monitor of Eclipse.
	 */
	protected IProgressMonitor monitor = null;

	protected int generationOptions;

	protected boolean generateCode;

	protected boolean generateCACOnly;

	protected Package srcModelComponentDeploymentPlan = null;

	protected IProject project;

	protected IProject genProject;

	public void instantiate(Package pkg, IProgressMonitor monitor, IProject project, int genOptions) {
		srcModelComponentDeploymentPlan = pkg;
		//
		this.project = project;
		if (project == null) {
			String projectName = pkg.eResource().getURI().toString();
			this.project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		}

		//
		instantiate(monitor, genOptions);
	}

	/**
	 * Instantiate a deployment plan, i.e. generate an intermediate model via a
	 * sequence of transformations
	 *
	 * @param umlElement
	 *            a deployment plan (UML package) or a configuration (UML class)
	 * @param monitor
	 *            a progress monitor.
	 *
	 * @param project
	 *            the current project. This information is used to store the
	 *            intermediate model in a subfolder (tmpModel) of the current
	 *            project
	 * @param genOptions
	 *            select whether to produce an intermediate model only, also
	 *            code, ... @see GenerationOptions
	 */
	private void instantiate(IProgressMonitor monitor, int genOptions) {
		try {
			initialize(monitor, genOptions);
			executeTransformation();
		} catch (final TransformationException e) {
			printAndDisplayErrorMessage(e, Messages.InstantiateDepPlan_TransformationException, false);
		} catch (final Exception e) {
			printAndDisplayErrorMessage(e, Messages.InstantiateDepPlan_ErrorsDuringTransformation, true);
		} finally {
			TransformationContext.current = null;
		}
	}

	private void initialize(IProgressMonitor monitor, int genOptions) throws TransformationException {
		this.monitor = monitor;
		this.generationOptions = genOptions;
		this.generateCode = (genOptions & GenerationOptions.MODEL_ONLY) == 0;
		this.generateCACOnly = (genOptions & GenerationOptions.CAC_ONLY) != 0;
	}

	protected void executeTransformation() throws Exception {
		ModelManagement intermediateModelManagement = null;
		UIContext.monitor = monitor;
		UIContext.configureProject = (generationOptions & GenerationOptions.REWRITE_SETTINGS) != 0;

		// 1a: create a new model (and applies same profiles / imports)
		Model existingModel = srcModelComponentDeploymentPlan.getModel();
		TransformationContext tc = new TransformationContext();
		TransformationContext.current = tc;
		TransformationContext.initialSourceRoot = existingModel;
		TransformationContext.initialDeploymentPlan = srcModelComponentDeploymentPlan;

		intermediateModelManagement = ModelManagement.createNewModel(existingModel.getName());

		// get the temporary model
		Package intermediateModel = intermediateModelManagement.getModel();

		// create a package for global enumerations that are used by xtend code
		EnumService.createEnumPackage(intermediateModel);

		// create a lazy copier towards the intermediate model
		LazyCopier intermediateModelCopier = new LazyCopier(existingModel, intermediateModel, CopyExtResources.SELECTED, true);

		// obtain the component deployment plan in target model, only make a shallow
		// copy right now, otherwise all instances would already be copied.
		intermediateModelCopier.shallowCopy(srcModelComponentDeploymentPlan);

		InstanceConfigurator.onNodeModel = false;

		tc.copier = intermediateModelCopier;
		Map<InstanceSpecification, InstanceSpecification> instanceMap = new HashMap<InstanceSpecification, InstanceSpecification>();

		// calculate which resources to copy: based on the classifiers that are referenced from the instances
		for (InstanceSpecification instance : DepUtils.getInstances(srcModelComponentDeploymentPlan)) {
			if (!TemplateUtils.withinPkgTemplate(instance)) {
				Classifier cl = DepUtils.getClassifier(instance);
				intermediateModelCopier.addResource(cl.eResource());
			}
		}

		for (InstanceSpecification instance : DepUtils.getTopLevelInstances(srcModelComponentDeploymentPlan)) {
			// InstanceSpecification newInstance = mainModelTrafo.transformInstance(instance, null);
			InstanceSpecification newInstance = intermediateModelCopier.getCopy(instance);

			// --------------------------------------------------------------------
			checkProgressStatus();
			// --------------------------------------------------------------------

			TransformationUtil.applyInstanceConfigurators(newInstance);

			TransformationUtil.propagateAllocation(newInstance);
			instanceMap.put(instance, newInstance);
		}
		tc.deploymentPlan = intermediateModelCopier.getCopy(srcModelComponentDeploymentPlan);
		tc.modelRoot = PackageUtil.getRootPackage(tc.deploymentPlan);

		M2MTrafoChain chain = DepUtils.getTransformationChain(srcModelComponentDeploymentPlan);

		ExecuteTransformationChain.apply(chain, DepUtils.getAdditionalTransformations(srcModelComponentDeploymentPlan));

		/*
		 * if (!generateCACOnly) {
		 * deployOnNodes(instanceMap, existingModel, intermediateModel);
		 * }
		 */

		// setURIs and save (first update all URIs to ensure that model references use the
		// correct URI before saving)
		intermediateModelManagement.setURI(project, TEMP_MODEL_FOLDER, TEMP_MODEL_POSTFIX);
		// also save additional root elements
		for (ModelManagement mm : tc.copier.getAdditionalRootPkgs()) {
			mm.setURI(project, TEMP_MODEL_FOLDER, TEMP_MODEL_POSTFIX);
		}
		intermediateModelManagement.save();
		for (ModelManagement mm : tc.copier.getAdditionalRootPkgs()) {
			mm.save();
		}

		// --------------------------------------------------------------------
		checkProgressStatus();
		// --------------------------------------------------------------------

		intermediateModelManagement.dispose();
		// also dispose additional models
		for (ModelManagement mm : tc.copier.getAdditionalRootPkgs()) {
			mm.dispose();
		}
	}

	private void destroyDeploymentPlanFolder(Model generatedModel) {
		PackageableElement deploymentPlanFolder = generatedModel.getPackagedElement(DeployConstants.depPlanFolderHw);
		if (deploymentPlanFolder != null) {
			deploymentPlanFolder.destroy();
		}
	}

	private void removeDerivedInterfacesInRoot(Model generatedModel) {
		// 2b: remove derived interfaces in root: derived interfaces
		// that can not be placed in the same package as the port type (e.g.
		// since read-only type from system library), are put in a top-level
		// package called "derivedInterfaces". Due to the copying of imports,
		// the top-level package has changed which implies that new derived
		// interfaces are put into a different package and the derivedInterfaces
		// package in the original root becomes obsolete. Delete this obsolete
		// package, if existing.
		for (PackageableElement packagedElement : generatedModel.getPackagedElements()) {
			if (packagedElement instanceof Package) {
				NamedElement derivedInterfaces = ((Package) packagedElement).getPackagedElement("derivedInterfaces"); //$NON-NLS-1$
				if (derivedInterfaces instanceof Package) {
					derivedInterfaces.destroy();
				}
			}
		}
	}

	/**
	 *
	 * @param canonicalProjectName
	 *            the automatically calculated project name
	 * @param userProjectName
	 *            the project name choosen by the user
	 */
	public void updateProjectMapping(final String canonicalProjectName, final String userProjectName) {
		CommandSupport.exec(srcModelComponentDeploymentPlan, "Update project mapping", new Runnable() {

			@Override
			public void run() {
				DeploymentPlan depPlan = UMLUtil.getStereotypeApplication(srcModelComponentDeploymentPlan, DeploymentPlan.class);
				String mapName = canonicalProjectName + "=" + userProjectName;
				for (String mapping : depPlan.getProjectMappings()) {
					if (mapping.startsWith(canonicalProjectName)) {
						mapping = mapName;
						return;
					}
				}
				depPlan.getProjectMappings().add(mapName);
			}
		});

	}

	private void checkProgressStatus() throws InterruptedException {
		if (monitor.isCanceled()) {
			throw new InterruptedException();
		}
		monitor.worked(1);
	}

	private void printAndDisplayErrorMessage(Exception e, final String title, final boolean consultConsole) {
		String message = e.toString();
		if (consultConsole) {
			message = message + "\n\n" //$NON-NLS-1$
					+ Messages.InstantiateDepPlan_ConsultConsole;
		}

		printAndDisplayErrorMessage(e, title, message, consultConsole);
	}

	private void printAndDisplayErrorMessage(Exception e, final String title, final String message, final boolean consultConsole) {
		e.printStackTrace();
		// only display message, if not running headless
		if (!TestInfo.runsHeadless()) {
			displayError(title, message);
		}
		Activator.log.error(e);
	}

	private void displayError(final String title, final String message) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				Shell shell = Display.getDefault().getActiveShell();
				MessageDialog.openInformation(shell, title, message);
			}
		});
	}
}
