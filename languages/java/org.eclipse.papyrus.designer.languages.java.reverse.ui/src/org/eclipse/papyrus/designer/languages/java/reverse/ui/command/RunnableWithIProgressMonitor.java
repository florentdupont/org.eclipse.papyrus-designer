/*****************************************************************************
 * Copyright (c) 2016 Cedric Dumoulin and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   cedric.dumoulin@univ-lille1.fr - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.command;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * 
 * @author cedric dumoulin
 *
 */
public interface RunnableWithIProgressMonitor {

	
	public void setProgressMonitor(IProgressMonitor monitor);
	public IProgressMonitor getProgressMonitor();
	
}
