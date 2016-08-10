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
 *  Initial developer : Christophe JOUVRAY from CEA LIST
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.transformation.core.transformations;


import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Package;

/**
 * This class executes the main model transformation. It traverses the
 * instances of a deployment plan in a recursive way and executes
 * connector reification and container expansion.
 */
public class ExecuteTransformationChain {

	public ExecuteTransformationChain(LazyCopier copier, Package sourceModel) {
		this.copier = copier;
		this.sourceModel = sourceModel;
	}

	public void execute(EList<AbstractM2MTrafo> chain) {
		for (AbstractM2MTrafo m2mTrafo : chain) {
			// ExecuteTransformation execTrafo = new ExecuteTransformation(m2mTrafo);
			// execTrafo.executeTransformation(sourceModel);
		}
	}
	
	/**
	 * Copier from source to target model
	 */
	protected LazyCopier copier;

	/**
	 * deployment plan within target model
	 */
	//protected Package tmCDP;
	
	protected Package sourceModel;
}
