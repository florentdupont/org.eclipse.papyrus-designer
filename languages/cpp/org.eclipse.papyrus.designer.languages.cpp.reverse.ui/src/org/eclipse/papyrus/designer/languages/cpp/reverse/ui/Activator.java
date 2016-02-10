/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Shuai Li (CEA LIST) <shuai.li@cea.fr> - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.reverse.ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change.CElementChange;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change.ChangeMapStore;
import org.eclipse.papyrus.designer.languages.cpp.reverse.ui.listener.CProjectChangesListener;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.ReverseCpp2Uml;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.papyrus.designer.languages.cpp.reverse.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	public static LogHelper log;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		// register the login helper
		log = new LogHelper(plugin);

		ChangeMapStore.changesMap = Collections.synchronizedMap(new HashMap<String, List<CElementChange>>());
		//CoreModel.getDefault().addElementChangedListener(new CProjectChangesListener(ChangeMapStore.changesMap));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
