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
 *  Ansgar Radermacher (CEA LIST) Ansgar.Radermacher@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.texteditor.cdt.listener;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.emf.utils.ServiceUtilsForEObject;
import org.eclipse.papyrus.texteditor.cdt.Activator;
import org.eclipse.papyrus.texteditor.cdt.modelresource.TextEditorModelSharedResource;
import org.eclipse.papyrus.texteditor.cdt.sync.SyncModelToCDT;
import org.eclipse.papyrus.texteditor.model.texteditormodel.TextEditorModel;
import org.eclipse.uml2.uml.Classifier;

public class OperationHistoryListener implements IOperationHistoryListener {

	public static void init() {
		if (ohl == null) {
			ohl = new OperationHistoryListener();
			OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(ohl);
		}
	}

	private static OperationHistoryListener ohl = null;

	@Override
	public void historyNotification(OperationHistoryEvent event) {
		int eventType = event.getEventType();
		if (eventType == OperationHistoryEvent.OPERATION_ADDED || eventType == OperationHistoryEvent.REDONE
				|| eventType == OperationHistoryEvent.UNDONE) {

			EList<Classifier> regenListCopy = new BasicEList<Classifier>(ModelListener.regenList);
			ModelListener.regenList.clear();

			// re-generate files from list - if they are still in a resource
			for (Classifier cl : regenListCopy) {
				if (cl.eResource() != null) {
					try {
						ServicesRegistry serviceRegistry = ServiceUtilsForEObject.getInstance().getServiceRegistry(cl);
						TextEditorModelSharedResource model = (TextEditorModelSharedResource) ServiceUtils.getInstance()
								.getModelSet(serviceRegistry).getModelChecked(TextEditorModelSharedResource.MODEL_ID);

						if (model != null) {
							TextEditorModel tem = model.getTextEditorModel(cl);
							if (tem != null) {
								SyncModelToCDT.syncModelToCDT(cl, tem.getGeneratorID());
							}
						}
					} catch (ServiceException e) {
						Activator.log.error(e);
					} catch (NotFoundException e) {
						Activator.log.error(e);
					}
				}
			}
		}
	}
}
