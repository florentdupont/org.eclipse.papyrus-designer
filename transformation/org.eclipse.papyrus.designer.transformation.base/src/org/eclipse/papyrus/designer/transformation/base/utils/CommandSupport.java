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

package org.eclipse.papyrus.designer.transformation.base.utils;

import java.util.concurrent.CancellationException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.papyrus.designer.transformation.base.Activator;
import org.eclipse.papyrus.designer.transformation.base.Messages;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.ui.util.ServiceUtilsForHandlers;

/**
 * Utility function. Allow execution of commands on a transactional command stack
 *
 * @author ansgar
 *
 */
public class CommandSupport {

	/**
	 * Execute the passed Runnable within a command
	 *
	 * @param label
	 * @param command
	 */
	public static void exec(String label, ExecutionEvent event, final Runnable command) {
		ServiceUtilsForHandlers serviceUtils = ServiceUtilsForHandlers.getInstance();
		try {
			exec(serviceUtils.getTransactionalEditingDomain(event), label, command);
		} catch (ServiceException e) {
			Activator.log.error(Messages.CommandSupport_NoEditingDomain, e);
		}
	}

	/**
	 * Execute the passed Runnable within a command
	 *
	 * @param eObject an element of the model that is modified (domain will be determined from it)
	 * @param label A command label
	 * @param command The command in form of a runnable
	 */
	public static void exec(EObject eObject, String label, final Runnable command) {
		exec(TransactionUtil.getEditingDomain(eObject), label, command);
	}
	
	
	/**
	 * Execute the passed Runnable with result within a command
	 *
	 * @param label
	 * @param command
	 */
	public static void exec(String label, ExecutionEvent event, final RunnableWithResult command) {
		// ServiceUtilsForActionHandlers serviceUtils = ServiceUtilsForActionHandlers.getInstance();
		ServiceUtilsForHandlers serviceUtils = ServiceUtilsForHandlers.getInstance();
		try {
			TransactionalEditingDomain domain = serviceUtils.getTransactionalEditingDomain(event);
			domain.getCommandStack().execute(new RecordingCommand(domain, label) {

				@Override
				public void doExecute() {
					CommandResult result = command.run();
					if (result.getReturnValue() instanceof IStatus) {
						IStatus status = (IStatus) result.getReturnValue();
						if (!status.isOK()) {
							throw new CancellationException();
						}
					}
				}
			});
		}
		catch (ServiceException e) {
			Activator.log.error(Messages.CommandSupport_NoEditingDomain, e);
		}
	}

	/**
	 * Execute the passed Runnable within a command
	 *
	 * @param label
	 * @param command
	 */
	public static void exec(TransactionalEditingDomain domain, String label, final Runnable command) {
		if (domain == null) {
			command.run();
		} else {
			domain.getCommandStack().execute(new RecordingCommand(domain, label) {

				@Override
				public void doExecute() {
					command.run();
				}
			});
		}
	}

	public static void exec(TransactionalEditingDomain domain, Command command) {
		domain.getCommandStack().execute(command);
	}
}
