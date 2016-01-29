/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher (CEA LIST) Ansgar.Radermacher@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.codegen.base.codesync;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.codegen.base.Activator;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForResourceSet;
import org.eclipse.papyrus.infra.ui.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.ui.services.EditorLifecycleEventListener;
import org.eclipse.papyrus.infra.ui.services.EditorLifecycleManager;

/**
 * Manage and update change events for incremental code generation
 */
public class ManageChangeEvents {

	protected static Map<TransactionalEditingDomain, RecordEventsListener> registeredListeners =
			new HashMap<TransactionalEditingDomain, RecordEventsListener>();

	/**
	 * An internal listener for life cycle events of Papyrus
	 */
	protected static class LifeCycleListener implements EditorLifecycleEventListener {

		TransactionalEditingDomain domain;

		LifeCycleListener(TransactionalEditingDomain domain) {
			this.domain = domain;
		}

		public void postInit(IMultiDiagramEditor editor) {
		}

		public void postDisplay(IMultiDiagramEditor editor) {
		}

		/**
		 * Executed before an editor will close => stop recording for this editing domain
		 */
		public void beforeClose(IMultiDiagramEditor editor) {
			stopRecording(domain);
		}
	}

	/**
	 * Start recording changes for an editing domain
	 * 
	 * @param domain
	 *            a transactional editing domain. Can be obtained via TransactionUtil from an EObject
	 */
	public static void startRecording(TransactionalEditingDomain domain) {
		if (!registeredListeners.containsKey(domain)) {
			RecordEventsListener recordEvents = new RecordEventsListener();
			registeredListeners.put(domain, recordEvents);
			// register a listener that records events
			domain.addResourceSetListener(recordEvents);

			// register a listener for editor lifecycle events, in order to
			// cleanup the set of registered listeners after the editor is closed.
			try {
				ServicesRegistry registry = ServiceUtilsForResourceSet.getInstance().getServiceRegistry(
						domain.getResourceSet());
				EditorLifecycleManager lifecycleManager = registry.getService(EditorLifecycleManager.class);
				lifecycleManager.addEditorLifecycleEventsListener(new LifeCycleListener(domain));
			} catch (ServiceException e) {
				Activator.log.error(e);
			}
		}

	}

	/**
	 * Stop recording changes for an editing domain
	 * 
	 * @param domain
	 *            a transactional editing domain. Can be obtained via TransactionUtil from an EObject
	 */
	public static void stopRecording(TransactionalEditingDomain domain) {
		RecordEventsListener recordEvents = registeredListeners.get(domain);
		if (recordEvents != null) {
			domain.removeResourceSetListener(recordEvents);
		}
		registeredListeners.remove(domain);
	}

	/**
	 * Get the list of changes for an editing domain
	 * 
	 * @param domain
	 *            a transactional editing domain. Can be obtained via TransactionUtil from an EObject
	 * @return the list of changes or null, if there is currently no event listener (which means
	 *         typcially, that event recording has not been started yet)
	 */
	public static EList<ChangeObject> getChangeList(TransactionalEditingDomain domain) {
		RecordEventsListener recordEvents = registeredListeners.get(domain);
		if (recordEvents != null) {
			return recordEvents.getChangeList();
		}
		return null;
	}

	/**
	 * initialize the difference list for an editing domain
	 * 
	 * @param domain
	 *            a transactional editing domain. Can be obtained via TransactionUtil from an EObject
	 * @param startAutomatically
	 *            if true, start event recording, if there is currently no event recording active
	 */
	public static void initChangeList(TransactionalEditingDomain domain, boolean startAutomatically) {
		RecordEventsListener recordEvents = registeredListeners.get(domain);
		if (recordEvents != null) {
			recordEvents.initChangeList();
		}
		else if (startAutomatically) {
			startRecording(domain);
		}
	}
}
