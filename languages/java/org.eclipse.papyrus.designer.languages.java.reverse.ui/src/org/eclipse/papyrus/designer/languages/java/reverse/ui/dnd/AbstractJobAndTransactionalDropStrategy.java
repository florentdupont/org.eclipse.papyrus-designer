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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Activator;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.exception.StopExecutionException;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.DropStrategy;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.TransactionalDropStrategy;

/**
 * A base class for {@link DropStrategy} using a Job running a Transaction.
 * This class require an implementation of IJobAndTransactionForDrop, which provides methods of the life cycle.
 * 
 * To use this class:
 * <ul>
 * <li>Provide an implementation of IJobAndTransactionForDrop</li>
 * <li>subclass this class and implements the {@link #createJobAndTransactionForDrop()} method. The method return an instance of the previous class.</li>
 * <li>Register this class as a drop Strategy extension.</li>
 * </ul>
 * 
 * The {@link IJobAndTransactionForDrop} implementation will be called as following :
 * <ul>
 * <li>Constructor</li>
 * <li> {@link IJobAndTransactionForDrop#init(Request, EditPart)}</li>
 * <li> {@link IJobAndTransactionForDrop#canExecute()}</li>
 * <li> {@link IJobAndTransactionForDrop#jobPreprocess(IProgressMonitor)}</li>
 * <li> {@link IJobAndTransactionForDrop#jobRun()}</li>
 * <li> {@link IJobAndTransactionForDrop#jobPostProcess()}</li>
 * </ul>
 * 
 * <br>
 * This implementation subclass {@link TransactionalDropStrategy} in order to inherit the two methods {@link #getEditingDomain(EditPart)} and
 * {@link #getTransactionalEditingDomain(EditPart)}. 
 * <br>
 * The method {@link #doGetCommand(Request, EditPart)} is disguarded and not used.
 * <br>
 * The method {@link #getCommand(Request, EditPart)} is reimplemented.
 * 
 * @author cedric dumoulin
 *
 */
public abstract class AbstractJobAndTransactionalDropStrategy extends TransactionalDropStrategy {


	public static final String PLUGIN_ID = Activator.PLUGIN_ID;
	
	// The object implementing the methods called during the life cycle.
	private IJobAndTransactionForDrop lifeCycle;

	/**
	 * This is the main method of the Strategy. This method is called several times by the Strategy manager, but on different objects.
	 * 
	 * @param request
	 * @param targetEditPart
	 * @return
	 */
	@Override
	public Command getCommand(Request request, EditPart targetEditPart) {
		if (!(request instanceof DropObjectsRequest)) {
			return null;
		}
		
		// Get the IJobAndTransactionForDrop
		lifeCycle = createJobAndTransactionForDrop();
		
		try {
			lifeCycle.init(  request, targetEditPart );
		} catch (StopExecutionException e) {
			// stop execution
			return null;
		}
		
		if( ! lifeCycle.canExecute() ) {
			return null;
		};
		
		// Create the command that will be executed by the DropStrategyMngr
		final TransactionalEditingDomain domain = getTransactionalEditingDomain(targetEditPart);
		Command cmd = new Command(getLabel()) {
			/**
			 * Create the Job, and schedule it.
			 *
			 */
			@Override
			public void execute() {
				
				try {
					lifeCycle.executePreJob();
				} catch (StopExecutionException e) {
					// Do not continue execution
					return;
				}
				
				Job job = new JobForIJobAndTransactionForDrop("Reverse Java Code", domain);
				// Execute Job
				job.setUser(true);
				job.schedule();
			}
		};
		
		return cmd;
	}
	
	/**
	 * @return an implementation of {@link IJobAndTransactionForDrop}. The method of the returned class will be called by the strategy in a predefined order (see {@link IJobAndTransactionForDrop}.
	 */
	public abstract IJobAndTransactionForDrop createJobAndTransactionForDrop();

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.TransactionalDropStrategy#doGetCommand(org.eclipse.gef.Request, org.eclipse.gef.EditPart)
	 *
	 * @param request
	 * @param targetEditPart
	 * @return
	 */
	@Override
	final protected Command doGetCommand(Request request, EditPart targetEditPart) {
		// TODO Auto-generated method stub
		return null;
	}

	public class JobForIJobAndTransactionForDrop extends Job {

		private TransactionalEditingDomain domain;
		/**
		 * Constructor.
		 *
		 * @param name
		 * @param domain 
		 */
		public JobForIJobAndTransactionForDrop(String name, TransactionalEditingDomain domain) {
			super(name);
			this.domain = domain;
		}
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			try {
				// Run preProcess
				lifeCycle.jobPreprocess(monitor);
				
				// Start the transaction
				RecordingCommand cmd = new RecordingCommand(domain) {
					
					@Override
					protected void doExecute() {
						try {
							lifeCycle.jobRun();
						} catch (StopExecutionException e) {
							System.err.println("TODO: should stop the Job execution !");
							e.printStackTrace();
						}		
					}
				};
				
				domain.getCommandStack().execute(cmd);
				
				// Run postProcess
				lifeCycle.jobPostProcess();
				
			} catch (Exception e) {
				return new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e);
			}
			
			return Status.OK_STATUS;
		}

		
	}
}
