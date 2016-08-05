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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Target;
import org.eclipse.papyrus.designer.deployment.tools.AllocUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepCreation;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.deployment.tools.DeployConstants;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageProjectSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtil;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.ProjectManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.StUtil;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Activator;
import org.eclipse.papyrus.designer.transformation.core.EnumService;
import org.eclipse.papyrus.designer.transformation.core.GatherConfigData;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.extensions.InstanceConfigurator;
import org.eclipse.papyrus.designer.transformation.core.generate.GenerateCode;
import org.eclipse.papyrus.designer.transformation.core.generate.GenerationOptions;
import org.eclipse.papyrus.designer.transformation.core.transformations.deployment.Deploy;
import org.eclipse.papyrus.designer.transformation.core.transformations.filters.FilterStateMachines;
import org.eclipse.papyrus.designer.transformation.core.transformations.filters.FilterTemplateBinding;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Profile;
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

		// 1a: create a new model (and applies same profiles / imports)
		Model existingModel = srcModelComponentDeploymentPlan.getModel();
		TransformationContext.sourceRoot = existingModel;

		intermediateModelManagement = createTargetModel(existingModel, existingModel.getName(), true);

		// get the temporary model
		Model intermediateModel = intermediateModelManagement.getModel();

		// create a package for global enumerations that are used by Acceleo code
		EnumService.createEnumPackage(intermediateModel);

		// create a lazy copier towards the intermediate model
		LazyCopier intermediateModelCopier = new LazyCopier(existingModel, intermediateModel, false, true);
		// add pre-copy and post-copy listeners to the copier
		// intermediateModelCopier.preCopyListeners.add(FilterTemplate.getInstance());

		// 1b: reify the connectors "into" the new model
		monitor.subTask(Messages.InstantiateDepPlan_InfoExpandingConnectors);

		// obtain the component deployment plan in target model
		Package intermediateModelComponentDeploymentPlan = (Package) intermediateModelCopier.shallowCopy(srcModelComponentDeploymentPlan);
		intermediateModelCopier.createShallowContainer(srcModelComponentDeploymentPlan);

		InstanceConfigurator.onNodeModel = false;

		Map<InstanceSpecification, InstanceSpecification> instanceMap = new HashMap<InstanceSpecification, InstanceSpecification>();
		for (PackageableElement pe : srcModelComponentDeploymentPlan.getPackagedElements()) {
			if (pe instanceof InstanceSpecification) {
				InstanceSpecification instance = (InstanceSpecification) pe;
				// check whether a top level instance and not an instance specification of a connector. The latter
				// is added, since interaction components might have configuration parameters that appear in the
				// deployment plan. Since the container transformation is not executed at this moment, the interaction is
				// not represented by a part yet.
				if (DepUtils.isTopLevelInstance(instance)) {
					//InstanceSpecification newInstance = mainModelTrafo.transformInstance(instance, null);
					InstanceSpecification newInstance = intermediateModelCopier.getCopy(instance);

					// --------------------------------------------------------------------
					checkProgressStatus();
					// --------------------------------------------------------------------

					TransformationUtil.applyInstanceConfigurators(newInstance);

					FlattenInteractionComponents.getInstance().flattenAssembly(newInstance, null);
					TransformationUtil.propagateAllocation(newInstance);
					instanceMap.put(instance, newInstance);
				}
			}
		}
		new ExecuteTransformation(intermediateModelCopier).executeTransformation(intermediateModel);
		
		if (!generateCACOnly) {
			deployOnNodes(instanceMap, existingModel, intermediateModel);
		}

		intermediateModelManagement.saveModel(project, TEMP_MODEL_FOLDER, TEMP_MODEL_POSTFIX);

		// --------------------------------------------------------------------
		checkProgressStatus();
		// --------------------------------------------------------------------

		intermediateModelManagement.dispose();
	}

	private void deployOnNodes(Map<InstanceSpecification, InstanceSpecification> instanceMap, Model existingModel, Model tmpModel) throws TransformationException, InterruptedException {

		// not deploy on each node
		DepCreation.initAutoValues(instanceMap.values());

		EList<InstanceSpecification> nodes = AllocUtils.getAllNodes(instanceMap.values());
		if (nodes.size() > 0) {
			InstanceConfigurator.onNodeModel = true;
			for (int nodeIndex = 0; nodeIndex < nodes.size(); nodeIndex++) {
				InstanceSpecification node = nodes.get(nodeIndex);

				deployNode(instanceMap, existingModel, tmpModel, nodes, nodeIndex, node);
			}
		} else {
			throw new TransformationException(Messages.InstantiateDepPlan_InfoNoneAllocated);
		}
	}

	private void deployNode(Map<InstanceSpecification, InstanceSpecification> instanceMap, Model existingModel, Model tmpModel, EList<InstanceSpecification> nodes, int nodeIndex, InstanceSpecification node)
			throws TransformationException, InterruptedException {
		ModelManagement genModelManagement = createTargetModel(existingModel, "root", false);
		Model generatedModel = genModelManagement.getModel();

		// --------------------------------------------------------------------
		checkProgressStatus();
		// --------------------------------------------------------------------

		// new model has name "root" and contains a package with the
		// existing model
		// Package originalRoot = genModel.createNestedPackage
		// (existingModel.getName ());
		LazyCopier targetCopier = new LazyCopier(tmpModel, generatedModel, true, true);
		// TODO: distribution to nodes is currently not done. How
		// can it be realized with a copier filter ?
		targetCopier.preCopyListeners.add(FilterStateMachines.getInstance());
		targetCopier.preCopyListeners.add(FilterTemplateBinding.getInstance());

		monitor.setTaskName(String.format(Messages.InstantiateDepPlan_InfoDeployingForNode, node.getName()));

		if (instanceMap.isEmpty()) {
			return;
		}
		// get first language (restricted to single target language, acceptable?)
		String targetLanguage = DepUtils.getTargetLanguage(instanceMap.keySet().iterator().next());
		ILangProjectSupport projectSupport = configureLanguageSupport(targetLanguage, existingModel, node);
		if (projectSupport == null) {
			return;
		}

		GatherConfigData gatherConfigData = new GatherConfigData(projectSupport);
		Deploy deployment = new Deploy(targetCopier, gatherConfigData, node, nodeIndex, nodes.size());

		for (InstanceSpecification topLevelInstance : instanceMap.keySet()) {
			InstanceSpecification newTopLevelInstance = instanceMap.get(topLevelInstance);
			InstanceSpecification nodeRootIS = deployment.distributeToNode(newTopLevelInstance);
			// TransformationUtil.updateDerivedInterfaces(nodeRootIS);
		}
		deployment.finalize(targetLanguage);
		if ((generationOptions & GenerationOptions.REWRITE_SETTINGS) != 0) {
			projectSupport.setSettings(genProject, gatherConfigData.getSettings());
		}

		// --------------------------------------------------------------------
		checkProgressStatus();
		// --------------------------------------------------------------------

		removeDerivedInterfacesInRoot(generatedModel);

		// --------------------------------------------------------------------
		checkProgressStatus();
		// --------------------------------------------------------------------

		destroyDeploymentPlanFolder(generatedModel);

		if (generateCode) {
			ILangCodegen codegen = LanguageCodegen.getGenerator(targetLanguage);
			GenerateCode codeGenerator = new GenerateCode(genProject, codegen, genModelManagement, monitor);
			boolean option = (generationOptions & GenerationOptions.ONLY_CHANGED) != 0;
			codeGenerator.generate(node, targetLanguage, option);
		}

		genModelManagement.dispose();
	}

	/**
	 *
	 * @param mainInstance
	 * @param existingModel
	 * @param node
	 * @return null, if no language support is available or no project could be created.
	 * @throws TransformationException
	 */
	private ILangProjectSupport configureLanguageSupport(String targetLanguage, Model existingModel, InstanceSpecification node) throws TransformationException {
		ILangProjectSupport projectSupport = LanguageProjectSupport.getProjectSupport(targetLanguage);
		AbstractSettings settings = projectSupport.initialConfigurationData();
		if (settings != null) {
			settings.targetOS = getTargetOS(node);
		}

		String projectName = getProjectName(existingModel, node);
		genProject = ProjectManagement.getNamedProject(projectName);
		if ((genProject == null) || !genProject.exists()) {
			genProject = projectSupport.createProject(projectName);
			if (!genProject.getName().equals(projectName)) {
				updateProjectMapping(projectName, genProject.getName());
			}
			// project is new, force re-write of settings
			generationOptions |= GenerationOptions.REWRITE_SETTINGS;
		}
		return projectSupport;
	}

	protected String getTargetOS(InstanceSpecification node) {
		Target target = UMLUtil.getStereotypeApplication(node, Target.class);
		if (target == null) {
			// get information from node referenced by the instance
			target = UMLUtil.getStereotypeApplication(DepUtils.getClassifier(node), Target.class);
		}
		if (target != null) {
			OperatingSystem os = target.getUsedOS();
			if (os != null) {
				return os.getBase_Class().getName();
			}
		}
		return null;
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
	 * Return the name of a project that is associated with a model that
	 * is deployed on a node (in the context of a deployment plan)
	 * 
	 * @param model
	 *            The model that is deployed
	 * @param node
	 *            The node onto which the software is deployed
	 * @return The resulting project name
	 */
	public String getProjectName(Model model, InstanceSpecification node) {
		String projectName = model.getName() + "_" + node.getName(); //$NON-NLS-1$
		projectName += "_" + srcModelComponentDeploymentPlan.getName(); //$NON-NLS-1$
		DeploymentPlan depPlan = UMLUtil.getStereotypeApplication(srcModelComponentDeploymentPlan, DeploymentPlan.class);
		if (depPlan != null) {
			for (String mapping : depPlan.getProjectMappings()) {
				if (mapping.startsWith(projectName)) {
					int index = mapping.indexOf("="); //$NON-NLS-1$
					if (index != -1) {
						return mapping.substring(index + 1);
					}
				}
			}
		}
		return projectName;
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

	private void initiateProgressMonitor(boolean generateCode, EList<InstanceSpecification> nodes) {
		// -- calc # of steps for progress monitor
		// 1 (tmpModel creation) + 1 (reification) + 1 (tmpModel save)
		// 5x on each deployed node (see below)
		// problem? Connector reification is a single, relatively long step
		int steps = 3;
		steps += 5 * nodes.size();
		if (generateCode) {
			steps += nodes.size();
		}
		monitor.beginTask(Messages.InstantiateDepPlan_InfoGeneratingModel, steps);
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
		displayError(title, message);
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

	/**
	 * Create a new empty model from an existing model that applies the same
	 * profiles and has the same imports
	 *
	 * @param existingModel
	 * @return
	 */
	public ModelManagement createTargetModel(Model existingModel, String name, boolean copyImports) throws TransformationException {
		ModelManagement mm = new ModelManagement();
		Model newModel = mm.getModel();
		newModel.setName(name);

		try {
			// copy profile application
			for (Profile profile : existingModel.getAppliedProfiles()) {
				// reload profile in resource of new model
				monitor.subTask(Messages.InstantiateDepPlan_InfoApplyProfile + profile.getQualifiedName());

				if (profile.eResource() == null) {
					String profileName = profile.getQualifiedName();
					if (profileName == null) {
						if (profile instanceof MinimalEObjectImpl.Container) {
							URI uri = ((MinimalEObjectImpl.Container) profile).eProxyURI();
							if (uri != null) {
								throw new TransformationException(String.format(Messages.InstantiateDepPlan_CheckInputModelProfileNoRes, uri));
							}
						}
						throw new TransformationException(Messages.InstantiateDepPlan_CheckInputModelProfileNoResNoName);
					}
					throw new TransformationException(String.format(Messages.InstantiateDepPlan_CheckInputModelProfile3, profileName));
				}

				Resource profileResource = null;
				try {
					profileResource = ModelManagement.getResourceSet().getResource(profile.eResource().getURI(), true);
				} catch (WrappedException e) {
					// read 2nd time (some diagnostic errors are raised only
					// once)
					Activator.log.warn("Warning: exception in profile.eResource() " + e.getMessage()); //$NON-NLS-1$
					profileResource = ModelManagement.getResourceSet().getResource(profile.eResource().getURI(), true);
				}
				if (profileResource.getContents().size() == 0) {
					throw new TransformationException(String.format("Cannot copy profile with URI %s. Check whether the URI corresponds to an existing location", profileResource.getURI()));
				}
				Profile newProfileTop = (Profile) profileResource.getContents().get(0);
				Profile newProfile;
				String qname = profile.getQualifiedName();
				if ((qname != null) && qname.contains("::")) { //$NON-NLS-1$
					// profile is a sub-profile within same resource
					// TODO: should Copier class copy profile applications?
					// Should be handled in shallowContainer class.
					// if we put profile/newProfile pair into copier map, copier
					// would find (and copier profile
					// applications in sub-folders
					qname = qname.substring(qname.indexOf("::") + 2); //$NON-NLS-1$
					newProfile = (Profile) ElementUtil.getQualifiedElement(newProfileTop, qname);
				} else {
					newProfile = newProfileTop;
				}
				newProfile.getMember("dummy"); // force profile loading //$NON-NLS-1$
				newModel.applyProfile(newProfile);
			}
		} catch (IllegalArgumentException e) {
			throw new TransformationException(Messages.InstantiateDepPlan_IllegalArgumentDuringCopy + e.toString());
		}

		// copier imports (and load resources associated - TODO: might not be
		// necessary)
		// While this is useful in general, it implies that code for imported
		// models
		// has been generated and compiled (for the right target) into a
		// library. This may be
		// quite tedious, unless automatically managed.
		// Therefore we do not activate this option in a first pass of the model
		// transformations.
		if (copyImports) {
			for (Package importedPackage : existingModel.getImportedPackages()) {
				if (importedPackage == null) {
					throw new TransformationException(Messages.InstantiateDepPlan_CheckInputImportPkg);
				}
				if (importedPackage.eResource() == null) {
					String errorMsg = Messages.InstantiateDepPlan_CheckInputImportPkgNoRes;
					if (importedPackage instanceof MinimalEObjectImpl.Container) {
						URI uri = ((MinimalEObjectImpl.Container) importedPackage).eProxyURI();
						if (uri != null) {
							errorMsg += " - URI: " + uri.devicePath(); //$NON-NLS-1$
						}
					}
					throw new TransformationException(errorMsg);
				}
				newModel.createPackageImport(importedPackage);
				monitor.subTask(String.format(Messages.InstantiateDepPlan_InfoImportPackage, importedPackage.getName()));

				try {
					importedPackage.eResource().load(null);
					newModel.getMember("dummy"); // force loading of model //$NON-NLS-1$
				} catch (IOException e) {
					throw new TransformationException(e.getMessage());
				}
			}
		}
		StUtil.copyStereotypes(existingModel, newModel);

		return mm;
	}
}
