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

package org.eclipse.papyrus.designer.languages.java.reverse.exception;

import java.util.List;

import org.eclipse.uml2.uml.Classifier;

/**
 * No {@link Classifier} is found for the specified simple name or qualified name.
 * <br>
 * The exception contains the simple name or qualified name.
 * 
 * @author cedric dumoulin
 *
 */
public class ClassifierNotFoundException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The relative name of the requested nested classifier. The first segment is the name of the parent classifier
	 * Only one of {@link #requestedQualifiedName} or {@link #requestedSimpleName} is set.
	 */
	private List<String> requestedQualifiedName;
	
	


	/**
	 * The requested simple name.
	 * Only one of {@link #requestedQualifiedName} or {@link #requestedSimpleName} is set.
	 */
	private String requestedSimpleName;
	
	

	/**
	 * Constructor.
	 *
	 * @param requestedQualifiedName
	 */
	public ClassifierNotFoundException(List<String> requestedQualifiedName) {
		this.requestedQualifiedName = requestedQualifiedName;
	}



	/**
	 * Constructor.
	 *
	 * @param requestedSimpleName
	 */
	public ClassifierNotFoundException(String requestedSimpleName) {
		this.requestedSimpleName = requestedSimpleName;
	}

	/**
	 * @return the requestedQualifiedName
	 */
	public List<String> getRequestedQualifiedName() {
		return requestedQualifiedName;
	}



	/**
	 * @return the requestedSimpleName
	 */
	public String getRequestedSimpleName() {
		return requestedSimpleName;
	}



	/**
	 * @see java.lang.Throwable#getMessage()
	 *
	 * @return
	 */
	@Override
	public String getMessage() {
		if( requestedQualifiedName != null ) {
		   return "No Classifier found for the name '" + requestedQualifiedName + "'";
		}
		return "No Classifier found for the name '" + requestedSimpleName + "'";
	}
}
