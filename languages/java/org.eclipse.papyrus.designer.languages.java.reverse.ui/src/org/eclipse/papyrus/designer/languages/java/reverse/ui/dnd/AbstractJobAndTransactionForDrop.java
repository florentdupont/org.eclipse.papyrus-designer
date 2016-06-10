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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.exception.StopExecutionException;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;

/**
 * @author dumoulin
 *
 */
public abstract class AbstractJobAndTransactionForDrop implements IJobAndTransactionForDrop {

	
	protected Request request;
	protected EditPart targetEditPart;
	/**
	 * Constructor.
	 *
	 */
	public AbstractJobAndTransactionForDrop() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.IJobAndTransactionForDrop#init(org.eclipse.gef.Request, org.eclipse.gef.EditPart)
	 *
	 * @param request
	 * @param targetEditPart
	 * @throws StopExecutionException
	 */
	@Override
	public void init(Request request, EditPart targetEditPart) throws StopExecutionException {
		this.request = request;
		this.targetEditPart = targetEditPart;

	}
	
	/**
	 * Returns the list of selected objects. The objects can be of any type.
	 *
	 * @param request
	 *            The drop request
	 * @return
	 *         The list of dropped Objects
	 */
	protected List<Object> getSourceObjects(Request request) {
		List<Object> result = new LinkedList<Object>();
		if (request instanceof DropObjectsRequest) {
			List objects = ((DropObjectsRequest) request).getObjects();
			if (objects != null) {
				result.addAll(objects);
			}
		} else if (request instanceof GroupRequest) {
			List editParts = ((ChangeBoundsRequest) request).getEditParts();
			if (editParts != null) {
				result.addAll(editParts);
			}
		}
		return result;
	}

	/**
	 * Returns the list of semantic EObjects being dropped. If some of the dropped elements
	 * cannot be resolved to EObjects, they are ignored.
	 *
	 * @param request
	 *            The drop request
	 * @return
	 *         The list of dropped EObjects
	 */
	protected List<EObject> getSourceEObjects(Request request) {
		List<EObject> result = new LinkedList<EObject>();

		for (Object object : getSourceObjects(request)) {
			EObject eObject = EMFHelper.getEObject(object);
			if (eObject instanceof View) {
				eObject = ((View) eObject).getElement();
			}

			if (eObject != null) {
				result.add(eObject);
			}
		}

		return result;
	}

}
