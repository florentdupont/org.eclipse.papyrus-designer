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

import org.eclipse.core.resources.IProject;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;

/**
 * Return information about the current values in the context of a transformation
 * e.g. the current instance, the current port (container transformation), ...
 *
 */
public class TransformationContext {

	public Package deploymentPlan;

	public ModelManagement mm;
	
	public IProject project;
	
	public ILangProjectSupport projectSupport;
	
	public Package sourceRoot;

	public LazyCopier copier;
		
	// the element, to which transformations are currently applied.
	public Classifier classifier;
	
	public InstanceSpecification node;

	public static TransformationContext current;
}
