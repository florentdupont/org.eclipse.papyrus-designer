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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

/**
 * Create the {@link View} nodes for the provided {@link NamedElement}. Node location is set according to the provided starting location.
 * 
 * 
 * @author cedric dumoulin
 *
 */
public class CreateNodesAtLocationCommand extends AbstractTransactionalCommand {

	private ReverseCommandParameters parameters;
	private View targetView;
	
	/**
	 * Constructor.
	 *
	 * @param domain
	 * @param label
	 * @param affectedFiles
	 */
	public CreateNodesAtLocationCommand(TransactionalEditingDomain domain, String label, View targetView, ReverseCommandParameters parameters) {
		super(domain, label, null);
		this.parameters = parameters;
		this.targetView = targetView;
	}

	/**
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 *
	 * @param monitor
	 * @param info
	 * @return
	 * @throws ExecutionException
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
	
		if( parameters.getReturnedReversedNamedElement() == null) {
			return CommandResult.newErrorCommandResult("The list of element to show must be set.");
		}
		Point nextLocation = parameters.getFirstNodeLocation();
		
		for( NamedElement namedElement : parameters.getReturnedReversedNamedElement() ) {
			
			// Do not create View if there is already one in the diagram for the specified namedelement.
			if( isViewExistFor(targetView, namedElement)) {
				continue;
			}
			
			// Create View
			View view = null;
			if( namedElement instanceof org.eclipse.uml2.uml.Class ) {
				view = createViewForClass( parameters.getParentViewEditPart(), parameters.getParentView(), (Class)namedElement);
			} 
			else if( namedElement instanceof Package ) {
				view = createViewForPackage( (Package)namedElement);
			}
			
			// Set bounds
			if(view != null) {
				setViewBounds( view, nextLocation);
				nextLocation = nextLocation.getTranslated(20, 20);
			}
			
		}
		
		return CommandResult.newOKCommandResult();
	}

	/**
	 * @param namedElement
	 * @return
	 */
	private View createViewForPackage(Package namedElement) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param namedElement
	 * @return
	 */
	private View createViewForClass(GraphicalEditPart parentViewEditPart, View parentView, Class namedElement) {
		// TODO Auto-generated method stub


		
		IHintedType elementType = (IHintedType) UMLElementTypes.getElementType(UMLVisualIDRegistry.getNodeVisualID(targetView, namedElement));

		View view = ViewService.createNode(parentView, namedElement, elementType.getSemanticHint(), parentViewEditPart.getDiagramPreferencesHint()); //$NON-NLS-1$

//		Diagram diagram = parentView.getDiagram().;
//
//		View view =
//				ViewService.getInstance().createView(
//					Node.class,
//					namedElement,
//					parentView,
//					elementType.getSemanticHint(),
//					ViewUtil.APPEND,
//					false,
//					parentViewEditPart.getDiagramPreferencesHint());		
		
		return view;
	}

	/**
	 * Set the location of the specified View
	 * @param view
	 * @param nextLocation
	 */
	private void setViewBounds(View view, Point location) {
		if (location != null) {
			ViewUtil.setStructuralFeatureValue(view,NotationPackage.eINSTANCE.getLocation_X(), Integer.valueOf(location.x));
			ViewUtil.setStructuralFeatureValue(view,NotationPackage.eINSTANCE.getLocation_Y(), Integer.valueOf(location.y));
		}
	}

	/**
	 * Check if a View exist for the specified class inside the parent view
	 * 
	 * @param targetView
	 * @param createdClass
	 * @return
	 */
	private boolean isViewExistFor(View targetView, NamedElement createdClass) {
		for( Object ele : targetView.getChildren() ) {
			if( ((View)ele).getElement() == createdClass ) {
				return true;
			}
		}
		return false;
	}


}
