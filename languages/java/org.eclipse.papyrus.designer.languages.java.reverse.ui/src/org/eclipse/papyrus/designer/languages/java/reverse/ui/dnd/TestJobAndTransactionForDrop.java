/*****************************************************************************
 * Copyright (c) 2016 Cedric Dumoulin and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Cedric Dumoulin - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.exception.StopExecutionException;

/**
 * @author cedric dumoulin
 *
 */
public class TestJobAndTransactionForDrop implements IJobAndTransactionForDrop {

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#init(org.eclipse.gef.Request, org.eclipse.gef.EditPart)
	 *
	 * @param request
	 * @param targetEditPart
	 */
	@Override
	public void init(Request request, EditPart targetEditPart) {
		System.err.println(this.getClass().getName() + ".init()");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#canExecute()
	 *
	 * @return
	 */
	@Override
	public boolean canExecute() {
		System.err.println(this.getClass().getName() + ".canExecute()");
		return true;
	}

	/**
	 * This method is called after mouse release, and before the Job.
	 *
	 * @throws StopExecutionException
	 */
	@Override
	public void executePreJob() throws StopExecutionException {
		System.err.println(this.getClass().getName() + ".executePreJob()");
		
	}
	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#jobPreprocess(org.eclipse.core.runtime.IProgressMonitor)
	 *
	 * @param monitor
	 */
	@Override
	public void jobPreprocess(IProgressMonitor monitor) {
		System.err.println(this.getClass().getName() + ".jobPreprocess()");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#jobRun()
	 *
	 */
	@Override
	public void jobRun() {
		System.err.println(this.getClass().getName() + ".jobRun()");

	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#jobPostProcess()
	 *
	 */
	@Override
	public void jobPostProcess() {
		System.err.println(this.getClass().getName() + ".jobPostProcess()");

	}

}
