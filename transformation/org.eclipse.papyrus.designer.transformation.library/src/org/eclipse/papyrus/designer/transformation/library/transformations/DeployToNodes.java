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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.DeploymentPlan;
import org.eclipse.papyrus.designer.deployment.tools.AllocUtils;
import org.eclipse.papyrus.designer.deployment.tools.ConfigUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepCreation;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageProjectSupport;
import org.eclipse.papyrus.designer.transformation.base.UIContext;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.ProjectManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoModelSplit;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.PartialCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.core.transformations.filters.FilterM2MTrafo;
import org.eclipse.papyrus.designer.transformation.core.transformations.filters.FilterStateMachines;
import org.eclipse.papyrus.designer.transformation.core.transformations.filters.FilterTemplateBinding;
import org.eclipse.papyrus.designer.transformation.extensions.InstanceConfigurator;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
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
public class DeployToNodes implements IM2MTrafoModelSplit {

	
	@Override
	public EList<TransformationContext> splitModel(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
	// public void deployOnNodes(Map<InstanceSpecification, InstanceSpecification> instanceMap, Model existingModel, Model tmpModel) throws TransformationException, InterruptedException {
		
		EList<InstanceSpecification> topLevelInstances = 
				DepUtils.getTopLevelInstances(TransformationContext.current.deploymentPlan);
		// now deploy on each node
		DepCreation.initAutoValues(topLevelInstances);

		EList<InstanceSpecification> nodes = AllocUtils.getAllNodes(topLevelInstances);
		EList<TransformationContext> splitModels = new BasicEList<TransformationContext>();
		InstanceConfigurator.onNodeModel = true;
		if (nodes.size() > 0) {
			for (int nodeIndex = 0; nodeIndex < nodes.size(); nodeIndex++) {
				InstanceSpecification node = nodes.get(nodeIndex);

				try {
					splitModels.add(
							deployNode(topLevelInstances, TransformationContext.current.modelRoot, nodes, nodeIndex, node));
				}
				catch (InterruptedException e) {
					throw new TransformationException(e.getMessage());
				}
			}
		} else {
			InstanceSpecification defaultNode = null; // ElementUtils.getQualifiedElement(TransformationContext.current.modelRoot, "trafos::xx::defaultNode");
			try {
				splitModels.add(
					deployNode(topLevelInstances, PackageUtil.getRootPackage(deploymentPlan), nodes, 0, node));
			}
			catch (InterruptedException e) {
				throw new TransformationException(e.getMessage());
			}
			throw new TransformationException(Messages.InstantiateDepPlan_InfoNoneAllocated);
		}
		return splitModels;
	}

	private TransformationContext deployNode(EList<InstanceSpecification> topLevelInstances, Package existingModel, EList<InstanceSpecification> nodes, int nodeIndex, InstanceSpecification node)
			throws TransformationException, InterruptedException {
		ModelManagement genModelManagement = ModelManagement.createNewModel(existingModel, "root", false);
		Model generatedModel = genModelManagement.getModel();
		
		// --------------------------------------------------------------------
		checkProgressStatus();
		// --------------------------------------------------------------------

		// new model has name "root" and contains a package with the
		// existing model
		// Package originalRoot = genModel.createNestedPackage
		// (existingModel.getName ());
		LazyCopier targetCopier = new LazyCopier(existingModel, generatedModel, true, true);
		// TODO: distribution to nodes is currently not done. How
		// can it be realized with a copier filter ?
		targetCopier.preCopyListeners.add(FilterStateMachines.getInstance());
		targetCopier.preCopyListeners.add(FilterTemplateBinding.getInstance());
		targetCopier.preCopyListeners.add(FilterM2MTrafo.getInstance());

		UIContext.monitor.setTaskName(String.format(Messages.InstantiateDepPlan_InfoDeployingForNode, node.getName()));

		TransformationContext tc = new TransformationContext();
		
		tc.modelRoot = generatedModel;
		tc.copier = targetCopier;
		tc.mm = genModelManagement;

		// change to flat copy eventually later (not yet working)
		depInstance = new PartialCopier();
	
		this.node = node;
		depInstance.init(targetCopier, node);
		for (InstanceSpecification is : topLevelInstances) {
			distributeToNode(false, is);
		}
		tc.deploymentPlan = (Package) targetCopier.get(TransformationContext.current.deploymentPlan);
		
		// get first language (restricted to single target language, acceptable?)
		String targetLanguage = DepUtils.getTargetLanguage(topLevelInstances.iterator().next());
		String projectName = getProjectName(existingModel, node);
		ILangProjectSupport projectSupport = LanguageProjectSupport.getProjectSupport(targetLanguage);
		IProject genProject = getOrCreateProject(projectSupport, projectName);
		if (genProject == null) {
			throw new TransformationException(String.format("Could not create project for language %s", targetLanguage));
		}
		tc.projectSupport = projectSupport;
		tc.project = genProject;
		tc.node = node;
		
		/*
		GatherConfigData gatherConfigData = new GatherConfigData(projectSupport);
		Deploy deployment = new Deploy(targetCopier, gatherConfigData, node, nodeIndex, nodes.size());

		for (InstanceSpecification topLevelInstance : topLevelInstances) {
			InstanceSpecification nodeRootIS = deployment.distributeToNode(topLevelInstance);
			// TransformationUtil.updateDerivedInterfaces(nodeRootIS);
		}
		deployment.finalize(targetLanguage);

		if ((generationOptions & GenerationOptions.REWRITE_SETTINGS) != 0) {
			projectSupport.setSettings(genProject, gatherConfigData.getSettings());
		}

		// --------------------------------------------------------------------
		checkProgressStatus();
		// --------------------------------------------------------------------

		if (generateCode) {
			ILangCodegen codegen = LanguageCodegen.getGenerator(targetLanguage);
			GenerateCode codeGenerator = new GenerateCode(genProject, codegen, genModelManagement, TransformationContext.monitor);
			boolean option = (generationOptions & GenerationOptions.ONLY_CHANGED) != 0;
			codeGenerator.generate(node, targetLanguage, option);
		}
		*/
		// genModelManagement.dispose();
		
		return tc;
	}

	/**
	 * Get an existing or create a new project for a given language
	 * @param projectSupport project support instance (for a given programming language)
	 * @param projectName the name of the project to create (or get, if it already exists)
	 * @return the project or null, if no project creation support is available for the target language
	 * @throws TransformationException
	 */
	protected IProject getOrCreateProject(ILangProjectSupport projectSupport, String projectName) throws TransformationException {
		IProject genProject = ProjectManagement.getNamedProject(projectName);
		if ((genProject == null) || !genProject.exists()) {
			genProject = projectSupport.createProject(projectName);
			if (!genProject.getName().equals(projectName)) {
				// updateProjectMapping(projectName, genProject.getName());
			}
			// project is new, force re-write of settings
			UIContext.configureProject = true;
		}
		return genProject;
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
	public String getProjectName(Package model, InstanceSpecification node) {
		String projectName = model.getName() + "_" + node.getName(); //$NON-NLS-1$
		projectName += "_" + TransformationContext.current.deploymentPlan.getName(); //$NON-NLS-1$
		DeploymentPlan depPlan = UMLUtil.getStereotypeApplication(TransformationContext.current.deploymentPlan, DeploymentPlan.class);
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
	 * Distribute an instance specification to the node by this
	 *
	 * @param allocAll
	 * @param slotPath
	 * @param instance
	 * @throws TransformationException
	 */
	public InstanceSpecification distributeToNode(boolean allocAll, InstanceSpecification instance)
			throws TransformationException {

		// once an instance is explicitly allocated on a partition (use of getNodes instead of getAllNodes)
		// all of its sub-instances are allocated on the node as well

		if (AllocUtils.getNodesOrThreads(instance).contains(node)) {
			allocAll = true;
		}

		// obtain implementation within source model
		Classifier smImplementation = DepUtils.getClassifier(instance);
		if (smImplementation == null) {
			throw new TransformationException(String.format(
					Messages.Deploy_0, instance.getName()));
		}

		// copy implementation into node specific model
		InstanceSpecification tmInstance = depInstance.deployInstance(instance);

		for (Slot slot : instance.getSlots()) {
			InstanceSpecification containedInstance = DepUtils.getInstance(slot);

			if (containedInstance != null) {
				if (!DepUtils.isShared(slot)) {
					StructuralFeature sf = slot.getDefiningFeature();
					boolean viaAllocAll = allocAll;
					if (allocAll && (sf instanceof Property)) {
						// only take allocation of parent instance into account, if composition
						// However, problematic, since code gets copied anyway.
						// viaAllocAll = (((Property) sf).getAggregation() == AggregationKind.COMPOSITE_LITERAL);
					}
					if (viaAllocAll || AllocUtils.getAllNodes(containedInstance).contains(node)) {
						if (sf instanceof Property) {
							// execute configurator before recursive call. Otherwise values put here would be ignored.
							// TODO: instances are not copied to node model. Thus, the instances here are the same as in the
							// configuration on the intermediate model. (true?)
							ConfigUtils.configureInstance(containedInstance, (Property) sf, tmInstance);
						}
						// distribute subInstance
						distributeToNode(allocAll, containedInstance);
					}
				}
			}
		}

		return tmInstance;
	}

	private void initiateProgressMonitor(boolean generateCode, EList<InstanceSpecification> nodes) {
		// -- calc # of steps for progress TransformationContext.monitor
		// 1 (tmpModel creation) + 1 (reification) + 1 (tmpModel save)
		// 5x on each deployed node (see below)
		// problem? Connector reification is a single, relatively long step
		int steps = 3;
		steps += 5 * nodes.size();
		if (generateCode) {
			steps += nodes.size();
		}
		UIContext.monitor.beginTask(Messages.InstantiateDepPlan_InfoGeneratingModel, steps);
	}

	private void checkProgressStatus() throws InterruptedException {
		if (UIContext.monitor.isCanceled()) {
			throw new InterruptedException();
		}
		UIContext.monitor.worked(1);
	}
	
	InstanceSpecification node;
	
	PartialCopier depInstance;
}
