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
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.reverse.ui.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.model.ElementChangedEvent;
import org.eclipse.cdt.core.model.ICElementDelta;
import org.eclipse.cdt.core.model.IElementChangedListener;
import org.eclipse.papyrus.designer.languages.cpp.reverse.reverse.change.CElementChange;

/**
 * A listener that detects changes on a CDT project.
 * The events and processed and the listener feeds a map
 * that will be used for reverse in merge mode.
 *
 */
public class CProjectChangesListener implements IElementChangedListener {
	private Map<String, List<CElementChange>> changesMap;
	
	public CProjectChangesListener(Map<String, List<CElementChange>> changesMap) {
		this.changesMap = changesMap;
	}

	@Override
	public void elementChanged(ElementChangedEvent event) {
		String eventName = "";
		if (event.getType() == 1) {
			eventName = "POST_CHANGE";
		} else if (event.getType() == 4) {
			eventName = "POST_RECONCILE";
		} else {
			eventName = event.getType() + "";
		}
		
		System.out.println("Process event of type " + eventName);
		
		if (event.getType() == ElementChangedEvent.POST_RECONCILE || event.getType() == ElementChangedEvent.POST_CHANGE) {
			List<CElementChange> affectedLeafElements = new ArrayList<CElementChange>();
			fillAffectedLeafElements(event.getDelta(), affectedLeafElements);
			
			List<CElementChange> changes = null;
			for (CElementChange cElementChange : affectedLeafElements) {
				if (cElementChange.getChangeKind() != 0) {
					if (cElementChange.getElement().getCProject() != null) {
						String projectName = cElementChange.getElement().getCProject().getElementName();
						if (changesMap != null) {
							if (changesMap.get(projectName) != null) {
								changes = changesMap.get(projectName);
							} else {
								changes = Collections.synchronizedList(new LinkedList<CElementChange>());
								changesMap.put(cElementChange.getElement().getCProject().getElementName(), changes);
							}
						}
						
						if (changes != null) {
							changes.add(cElementChange);
							
							String changeName = "";
							if (cElementChange.getChangeKind() == 1) {
								changeName = "ADDED";
							} else if (cElementChange.getChangeKind() == 2) {
								changeName = "REMOVED";
							} else if (cElementChange.getChangeKind() == 4) {
								changeName = "CHANGED";
							} else {
								changeName = cElementChange.getChangeKind() + "";
							}
							System.out.println("---> " + cElementChange.getElement().getElementName() + " of type " + cElementChange.getElement().getElementType() + " has been " + changeName + " ==> PUT IN MAP");
						}
					}
				} else {
					System.out.println("---> " + cElementChange.getElement().getElementName() + " of type " + cElementChange.getElement().getElementType() + " has been " + cElementChange.getChangeKind() + " ==> NOTHING");
				}
			}
		}
	}
	
	private void fillAffectedLeafElements(ICElementDelta delta, List<CElementChange> affectedLeafElements) {
		if (affectedLeafElements == null) {
			return;
		}
		
		if (delta != null) {
			if (delta.getAffectedChildren().length == 0 && delta.getElement() != null) {
				affectedLeafElements.add(new CElementChange(delta));
			} else {
				for (ICElementDelta childDelta : delta.getAffectedChildren()) {
					fillAffectedLeafElements(childDelta, affectedLeafElements);
				}
			}
		}
	}

}
