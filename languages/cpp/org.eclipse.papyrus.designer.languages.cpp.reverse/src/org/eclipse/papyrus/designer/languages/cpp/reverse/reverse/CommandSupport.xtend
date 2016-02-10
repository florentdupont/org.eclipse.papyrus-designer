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
 *   Van Cam Pham (CEA LIST) <vancam.pham@cea.fr> - Reverse implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.reverse.reverse

import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.transaction.TransactionalEditingDomain
import org.eclipse.emf.transaction.RecordingCommand

/**
 * Class to support execution of commands
 *
 */
class CommandSupport {
	static def executeCmd(ResourceSet rset, Runnable runnable) {
		var domain = TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(rset)
		if (domain != null) {
			domain.commandStack.execute(new RecordingCommand(domain) {
				override protected doExecute() {
					runnable.run
				}

			})
		} else {
			runnable.run
		}
	}
}
