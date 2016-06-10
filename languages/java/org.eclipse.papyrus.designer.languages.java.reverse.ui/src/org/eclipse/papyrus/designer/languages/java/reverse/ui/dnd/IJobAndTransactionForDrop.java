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
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.exception.StopExecutionException;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.DropStrategy;

/**
 * This interface provides the methods used in the life cycle of a drop strategy with Job and Transaction.
 * 
 * 
 * The methods are called in the following order :
 * <ul>
 * <li>{@link #init(Request, EditPart)}</li>
 * <li>{@link #canExecute()}</li>
 * <li>{@link #jobPreprocess(IProgressMonitor)}</li>
 * <li>{@link #jobRun()}</li>
 * <li>{@link #jobPostProcess()}</li>
 * <li></li>
 * </ul>
 * 
 * This lifecycle is controlled by the {@link AbstractJobAndTransactionalDropStrategy} class.
 * The controler will create appropriate Job and transactional command, and call corresponding methods when required.
 * 
 * 
 * @author cedric dumoulin
 *
 */
public interface IJobAndTransactionForDrop {

	/**
	 * Initialization from a {@link DropStrategy}.
	 * @param request The Strategy request.
	 * @param targetEditPart The {@link EditPart} provided by the DropStrategy.
	 * @throws StopExecutionException 
	 */
	public void init(Request request, EditPart targetEditPart) throws StopExecutionException;

	/**
	 * Check if the Job and its command can be executed.
	 * 
	 * @return True if the Job and its command can be executed, false otherwise.
	 */
	public boolean canExecute();

	/**
	 * 	Method called immediately after the user has drop the dragged object, and before the Job is running.
	 * 
	 * @throws StopExecutionException 
	 * 
	 */
	public void executePreJob() throws StopExecutionException;

	/**
	 * 	Method called from the job, before the transactional method. 
	 * The method must record the monitor for future use.
	 * 
	 * @param monitor The monitor provided by the {@link Job}. 
	 * @throws StopExecutionException 
	 * 
	 */
	public void jobPreprocess(IProgressMonitor monitor) throws StopExecutionException;

	/**
	 * Run the job in a {@link Transaction}.
	 * @param monitor 
	 * @throws StopExecutionException 
	 */
	public void jobRun(IProgressMonitor monitor) throws StopExecutionException;

	/**
	 * 	Method called from the job, after the transactional method. 
	 * @throws StopExecutionException 
	 */
	public void jobPostProcess() throws StopExecutionException;


}
