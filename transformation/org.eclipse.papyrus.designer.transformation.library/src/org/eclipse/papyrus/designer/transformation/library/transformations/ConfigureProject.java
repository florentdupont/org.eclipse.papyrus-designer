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

import org.eclipse.papyrus.designer.deployment.profile.Deployment.OperatingSystem;
import org.eclipse.papyrus.designer.deployment.profile.Deployment.Target;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.transformation.base.UIContext;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
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

public class ConfigureProject implements IM2MTrafoCDP {

	/**
	 * Gather configuration data for a code generation project
	 * In particular, a class might be stereotyped to provide additional information
	 * about required compilation options (in case of C++ include paths, libraries, ...)
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

	protected AbstractSettings settings;

	protected ILangProjectSupport projectSupport;

	@Override
	public void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
		if (UIContext.configureProject) {
			projectSupport = TransformationContext.current.projectSupport;
			GatherConfigData gatherData = new GatherConfigData();
			settings = projectSupport.initialConfigurationData();
			AbstractSettings settings = projectSupport.initialConfigurationData();
			if (settings != null) {
				settings.targetOS = getTargetOS(TransformationContext.current.node);
			}
			ApplyRecursive ar = new ApplyRecursive(trafo, gatherData);
			ar.applyRecursive(PackageUtil.getRootPackage(deploymentPlan));
			projectSupport.setSettings(TransformationContext.current.project, settings);
		}
	}
}
