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

package org.eclipse.papyrus.designer.components.transformation.core.deployment;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangProjectSupport;
import org.eclipse.papyrus.designer.components.transformation.core.listeners.PreCopyListener;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.LazyCopier;
import org.eclipse.uml2.uml.Class;

/**
 * Gather configuration data for a code generation project
 * In particular, a class might be stereotyped to provide additional information
 * about required compilation options (in case of C++ include paths, libraries, ...)
 *
 * @author ansgar
 */
public class GatherConfigData implements PreCopyListener {

	/**
	 * Gather configuration data for a code generation project
	 * Constructor.
	 *
	 * @param projectSupport
	 *            A reference to a class providing the language support interface
	 */
	public GatherConfigData(ILangProjectSupport projectSupport) {
		this.projectSupport = projectSupport;
		settings = projectSupport.initialConfigurationData();
	}

	@Override
	public EObject preCopyEObject(LazyCopier copy, EObject sourceEObj) {
		if (sourceEObj instanceof Class) {
			projectSupport.gatherConfigData((Class) sourceEObj, settings);
		}
		return sourceEObj;
	}

	/**
	 * Return the settings that have been gathered
	 * @return
	 */
	public AbstractSettings getSettings() {
		return settings;
	}
	
	protected ILangProjectSupport projectSupport;
	
	protected AbstractSettings settings;
}
