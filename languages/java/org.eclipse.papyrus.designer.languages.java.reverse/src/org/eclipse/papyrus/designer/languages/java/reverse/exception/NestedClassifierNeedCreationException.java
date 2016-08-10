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
 * The parent Classifier containing the requested nested classifier is found, but the nested {@link Classifier} is not found inside the parent.
 * The parent 
 * 
 * <br>
 * The exception contains the parent Classifier or its qualified name, and the relative name of the nested classifier.
 * The relative name first segment is the name of the parent classifier..
 * 
 * 
 * @author cedric dumoulin
 *
 */
public class NestedClassifierNeedCreationException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The relative name of the requested nested classifier. The first segment is the name of the parent classifier
	 */
	private List<String> requestedNestedClassifierRelativename;
	
	/**
	 * The qualified name of the parent Classifier.
	 * Only one of {@link #parentQualifiedname} or {@link #parentClassifier} is set.
	 */
	private List<String> parentQualifiedname;
	
	/**
	 * The parent classifier which should contains the requested nested Classifier.
	 * Only one of {@link #parentQualifiedname} or {@link #parentClassifier} is set.
	 */
	private Classifier parentClassifier;
	
	
	/**
	 * Constructor.
	 *
	 * @param parentQualifiedname
	 * @param requestedNestedClassifierRelativename
	 */
	public NestedClassifierNeedCreationException(List<String> parentQualifiedname, List<String> requestedNestedClassifierRelativename) {
		this.parentQualifiedname = parentQualifiedname;
		this.requestedNestedClassifierRelativename = requestedNestedClassifierRelativename;
	}


	/**
	 * Constructor.
	 *
	 * @param parentClassifier
	 * @param requestedNestedClassifierRelativename
	 */
	public NestedClassifierNeedCreationException(Classifier parentClassifier, List<String> requestedNestedClassifierRelativename) {
		this.parentClassifier = parentClassifier;
		this.requestedNestedClassifierRelativename = requestedNestedClassifierRelativename;
	}


	/**
	 * @return the requestedNestedClassifierRelativename
	 */
	public List<String> getRequestedNestedClassifierRelativename() {
		return requestedNestedClassifierRelativename;
	}


	/**
	 * @return the parentQualifiedname
	 */
	public List<String> getParentQualifiedname() {
		return parentQualifiedname;
	}


	/**
	 * @return the parentClassifier
	 */
	public Classifier getParentClassifier() {
		return parentClassifier;
	}


	/**
	 * @see java.lang.Throwable#getMessage()
	 *
	 * @return
	 */
	@Override
	public String getMessage() {
		return "No Classifier found for the name '" + requestedNestedClassifierRelativename + "'";
	}
}
