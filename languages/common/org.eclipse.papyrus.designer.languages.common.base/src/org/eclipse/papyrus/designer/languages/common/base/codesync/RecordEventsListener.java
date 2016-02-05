/*****************************************************************************
 * Copyright (c) 2014 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.languages.common.base.codesync;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * This listener is used to register ADD ant SET about in the diff model during
 * the launch of the revision mode. It also create review element in the review
 * model. See package commands for specification of review creation commands.
 *
 */
public class RecordEventsListener implements ResourceSetListener {

	protected EList<ChangeObject> changeList;

	/**
	 * constructor
	 * 
	 * @param reviewResourceManager
	 */
	public RecordEventsListener() {
		changeList = new BasicEList<ChangeObject>();
	}

	public NotificationFilter getFilter() {
		return null;
	}

	/**
	 * @return the list of recorded change events
	 */
	public EList<ChangeObject> getChangeList() {
		return changeList;
	}

	/**
	 * initialize (reset) the list of recorded changes
	 */
	public void initChangeList() {
		changeList.clear();
	}

	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		for (Notification notification : event.getNotifications()) {
			int eventType = notification.getEventType();
			if (notification.getNotifier() instanceof EObject) {
				EObject notifier = (EObject) notification.getNotifier();
				
				EObject baseElement = UMLUtil.getBaseElement(notifier);
				if (baseElement != null) {
					notifier = baseElement;
				}
				if ((eventType != Notification.REMOVING_ADAPTER) && (eventType != Notification.RESOLVE)) {
					if ((notifier instanceof Element) || (notifier instanceof EAnnotation)) {
						ChangeObject changeObject = new ChangeObject();
						changeObject.eventType = notification.getEventType();
						changeObject.eObject = notifier;
						changeList.add(changeObject);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {

	}

	@Override
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	@Override
	public boolean isPrecommitOnly() {
		return false;
	}

	@Override
	public boolean isPostcommitOnly() {
		return false;
	}

}
