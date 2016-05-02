/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.NamedElement;

/**
 * Class that hold parameters that are crried between commands called to perform code reverse.
 * 
 * @author cedric dumoulin
 *
 */
public class ReverseCommandParameters {

	/**
	 * A collection of NamedElement reversed by the reverse command. This collection only include NamedElement corresponding to 
	 * the requested classes. It does not include NamedElement induce by the reverse (like imported Class).
	 * 
	 */
	protected List<NamedElement> returnedReversedNamedElement;
	
	protected Point firstNodeLocation;
	
	protected GraphicalEditPart parentViewEditPart;
	
	protected View parentView;
	
	
	/**
	 * Constructor.
	 *
	 * @param parentViewEditPart
	 * @param parentView
	 * @param firstNodeLocation
	 */
	public ReverseCommandParameters(GraphicalEditPart parentViewEditPart, View parentView, Point firstNodeLocation) {
		this.parentViewEditPart = parentViewEditPart;
		this.parentView = parentView;
		this.firstNodeLocation = firstNodeLocation;
	}
	/**
	 * 
	 * @return the returnedReversedNamedElement
	 */
	public List<NamedElement> getReturnedReversedNamedElement() {
		return returnedReversedNamedElement;
	}
	/**
	 * @param returnedReversedNamedElement the returnedReversedNamedElement to set
	 */
	public void setReturnedReversedNamedElement(List<NamedElement> returnedReversedNamedElement) {
		this.returnedReversedNamedElement = returnedReversedNamedElement;
	}
	/**
	 * @return the firstNodeLocation
	 */
	public Point getFirstNodeLocation() {
		return firstNodeLocation;
	}
	/**
	 * @param firstNodeLocation the firstNodeLocation to set
	 */
	public void setFirstNodeLocation(Point firstNodeLocation) {
		this.firstNodeLocation = firstNodeLocation;
	}
	/**
	 * @return the parentViewEditPart
	 */
	public GraphicalEditPart getParentViewEditPart() {
		return parentViewEditPart;
	}
	/**
	 * @param parentViewEditPart the parentViewEditPart to set
	 */
	public void setParentViewEditPart(GraphicalEditPart parentViewEditPart) {
		this.parentViewEditPart = parentViewEditPart;
	}
	/**
	 * @return the parentView
	 */
	public View getParentView() {
		return parentView;
	}
	/**
	 * @param parentView the parentView to set
	 */
	public void setParentView(View parentView) {
		this.parentView = parentView;
	}

}
