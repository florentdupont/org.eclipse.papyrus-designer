/*******************************************************************************
 * Copyright (c) 2017 CEA LIST
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ansgar Radermacher (CEA LIST) - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.designer.transformation.base.utils;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.papyrus.infra.core.log.LogHelper;

/**
 * Simple log helper that filters some messages
 */
public class FilteringLogHelper extends LogHelper {
	
	protected int filter;
	
	public static final int SHOW_INFO = 1;

	public static final int SHOW_WARN = 2;

	public static final int SHOW_DEBUG = 4;

	/**
	 * By default, log warning and debug, but not info message
	 *  
	 * @param activator the activator plugin
	 */
	public FilteringLogHelper(Plugin activator) {
		this(activator, SHOW_WARN | SHOW_DEBUG);
	}

	public FilteringLogHelper(Plugin activator, int filter) {
		super(activator);
		this.filter = filter;
	}
	
	@Override
	public void info(String msg) {
		if ((filter & SHOW_INFO) > 0) {
			super.info(msg);
		}
	}
	
	@Override
	public void warn(String msg) {
		if ((filter & SHOW_WARN) > 0) {
			super.warn(msg);
		}
	}

	@Override
	public void debug(String msg) {
		if ((filter & SHOW_DEBUG) > 0) {
			super.debug(msg);
		}
	}
}
