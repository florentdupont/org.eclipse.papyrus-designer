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

import org.eclipse.swt.graphics.Image;

/**
 * @author dumoulin
 *
 */
public class TestJobAndTransactionalDropStrategy extends AbstractJobAndTransactionalDropStrategy {

	@Override
	public String getLabel() {
//		return Messages.DropReverseStrategy_LABEL;
		return "Test Drop (print method calls)";
	}

	@Override
	public String getDescription() {
		return Messages.DropReverseStrategy_DESCRIPTION;
	}

	@Override
	public Image getImage() {
		// No image to display
		return null;
	}

	@Override
	public String getID() {
		return this.getClass().getName(); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.DropStrategy#getPriority()
	 *
	 * @return
	 * @deprecated
	 */
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.ui.dnd.AbstractJobAndTransactionalDropStrategy#createJobAndTransactionForDrop()
	 *
	 * @return
	 */
	@Override
	public IJobAndTransactionForDrop createJobAndTransactionForDrop() {
		// TODO Auto-generated method stub
		return new ReverseJobAndTransactionForDrop();
	}

}
