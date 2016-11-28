/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
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

import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Target;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageProjectSupport;
import org.eclipse.papyrus.designer.transformation.base.UIContext;
import org.eclipse.papyrus.designer.transformation.base.utils.CommandSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.ProjectManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.core.transformations.ApplyRecursive;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.util.UMLUtil;

public class CreateAndConfigureProject implements IM2MTrafoCDP {

	/**
	 * Gather configuration data for a code generation project In particular, a
	 * class might be stereotyped to provide additional information about
	 * required compilation options (in case of C++ include paths, libraries,
	 * ...)
	 */
	public class GatherConfigData implements IM2MTrafoElem {

		@Override
		public void transformElement(M2MTrafo trafo, Element element) throws TransformationException {
			if (element instanceof Classifier) {
				projectSupport.gatherConfigData((Classifier) element, settings);
			}
		}
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

	/**
	 * Get an existing or create a new project for a given language
	 * 
	 * @param projectSupport
	 *            project support instance (for a given programming language)
	 * @param projectName
	 *            the name of the project to create (or get, if it already
	 *            exists)
	 * @param targetLanguage
	 * @param modelRoot
	 * @return the project or null, if no project creation support is available
	 *         for the target language
	 * @throws TransformationException
	 */
	protected IProject getOrCreateProject(ILangProjectSupport projectSupport, String projectName, String targetLanguage,
			Package modelRoot) throws TransformationException {
		IProject genProject = ProjectManagement.getNamedProject(projectName);
		if ((genProject == null) || !genProject.exists()) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject[] projectsBeforeCreation = root.getProjects();
			genProject = projectSupport.createProject(projectName, modelRoot);
			try {
				root.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
				IProject[] projectsAfterCreation = root.getProjects();
				boolean found = false;
				for (int i = 0; i < projectsBeforeCreation.length; i++) {
					if (!projectsAfterCreation[i].getName().equals(projectsBeforeCreation[i].getName())) {
						genProject = projectsAfterCreation[i];
						found = true;
						break;
					}
				}
				// The generated project is the last in the projects list in the
				// workspace
				if ((found == false) && (projectsBeforeCreation.length < projectsAfterCreation.length)) {
					genProject = projectsAfterCreation[projectsAfterCreation.length - 1];
				}

			} catch (CoreException e) {
				e.printStackTrace();
			}
			if ((genProject == null) || !genProject.exists()) {
				throw new RuntimeException("Could not create " + targetLanguage //$NON-NLS-1$
						+ " project. This might indicate that there is a problem with your " + targetLanguage
						+ " installation.");
			}
			
			// project is new, force re-write of settings
			UIContext.configureProject = true;
		}
		return genProject;
	}

	void storeProjectName(IProject genProject, Package model, InstanceSpecification node) {
		final String projectGeneratedName = model.getName() + "_" + node.getName() + "_"
				+ TransformationContext.current.deploymentPlan.getName();
		if (genProject != null) {
			final String pName = genProject.getName();
			CommandSupport.exec(TransformationContext.initialDeploymentPlan, "Change Project Mapping", new Runnable() {
				@Override
				public void run() {
					Package deploymentPlanElement = TransformationContext.initialDeploymentPlan;
					DeploymentPlan deploymentPlan = UMLUtil.getStereotypeApplication(deploymentPlanElement,
							DeploymentPlan.class);
					EList<String> projectMappings = deploymentPlan.getProjectMappings();
					String newMapping = "";
					int oldMappingIndex = -1;
					for (int j = 0; j < projectMappings.size(); j++) {
						StringTokenizer str = new StringTokenizer(projectMappings.get(j), "=");
						String genName = str.nextToken();
						if (genName.equals(projectGeneratedName)) {
							newMapping = projectGeneratedName + "=" + pName;
							oldMappingIndex = j;
						}
					}
					if (oldMappingIndex != -1) {
						projectMappings.remove(oldMappingIndex);
						projectMappings.add(newMapping);
					}
				}
			});
		}
	}

	/**
	 * Return the name of a project that is associated with a model that is
	 * deployed on a node (in the context of a deployment plan)
	 * 
	 * @param model
	 *            The model that is deployed
	 * @param node
	 *            The node onto which the software is deployed
	 * @return The resulting project name
	 */
	public String getProjectName(Package model, InstanceSpecification node) {
		String projectName = model.getName() + "_" + node.getName(); //$NON-NLS-1$
		projectName += "_" + TransformationContext.current.deploymentPlan.getName(); //$NON-NLS-1$
		DeploymentPlan depPlan = UMLUtil.getStereotypeApplication(TransformationContext.current.deploymentPlan,
				DeploymentPlan.class);
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

	protected AbstractSettings settings;

	protected ILangProjectSupport projectSupport;

	@Override
	public void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
		TransformationContext tc = TransformationContext.current;
		EList<InstanceSpecification> topLevelInstances = DepUtils
				.getTopLevelInstances(TransformationContext.current.deploymentPlan);
		// get first language (restricted to single target language,
		// acceptable?)
		String targetLanguage = DepUtils.getTargetLanguage(topLevelInstances.iterator().next());
		String projectName = getProjectName(tc.modelRoot, tc.node);
		projectSupport = LanguageProjectSupport.getProjectSupport(targetLanguage);
		IProject genProject = getOrCreateProject(projectSupport, projectName, targetLanguage, tc.modelRoot);
		if (genProject == null) {
			throw new TransformationException(
					String.format(Messages.DeployToNodes_CouldNotCreateProject, targetLanguage));
		}

		storeProjectName(genProject, tc.modelRoot, tc.node);

		tc.projectSupport = projectSupport;
		tc.project = genProject;

		if (UIContext.configureProject) {
			GatherConfigData gatherData = new GatherConfigData();
			settings = projectSupport.initialConfigurationData();
			if (settings != null) {
				settings.targetOS = getTargetOS(TransformationContext.current.node);
			}
			ApplyRecursive ar = new ApplyRecursive(trafo, gatherData);
			ar.applyRecursive(PackageUtil.getRootPackage(deploymentPlan));
			projectSupport.setSettings(TransformationContext.current.project, settings);
		}
	}
}
