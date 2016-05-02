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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.Activator;


/**
 * A job used to execute a {@link Command} in an async way.
 * 
 * @author cedric dumoulin
 *
 */
public class JobForTransactionalCommand extends Job {

	private static final String IDEAPAD_EDITOR_ID = Activator.PLUGIN_ID;

	protected TransactionalEditingDomain editingDomain;
	protected Command command;
	
	/**
	 * @param name
	 */
	public JobForTransactionalCommand(String name, TransactionalEditingDomain editingDomain, Command command) {
		super(name);
		this.editingDomain = editingDomain;
		this.command = command;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			
			// Does the command accept the monitor ?
			if(command instanceof RunnableWithIProgressMonitor) {
				((RunnableWithIProgressMonitor)command).setProgressMonitor(monitor);
			}
			
			editingDomain.getCommandStack().execute( command );
		} catch (Exception e) {
			return new Status(IStatus.ERROR, IDEAPAD_EDITOR_ID, e.getMessage(), e);
		}
		
		return Status.OK_STATUS;
	}

}
