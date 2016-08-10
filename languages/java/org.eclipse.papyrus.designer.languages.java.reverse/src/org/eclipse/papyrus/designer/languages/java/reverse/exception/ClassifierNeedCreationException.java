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

import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ClassifierCatalog;
import org.eclipse.uml2.uml.Classifier;

/**
 * The qualified name of the requested {@link Classifier} is found, but the {@link Classifier} itself is not found in the
 * {@link ClassifierCatalog}.
 * The exception contains the found  qualified  name.
 * 
 * 
 * @author cedric dumoulin
 *
 */
public class ClassifierNeedCreationException extends NotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The qname that is not found.
	 */
	private List<String> qualifiedName;
	
	/**
	 * Constructor.
	 *
	 */
	public ClassifierNeedCreationException(List<String> qname) {
		this.qualifiedName = qname;
	}

	/**
	 * @return the qname
	 */
	public List<String> getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 *
	 * @return
	 */
	@Override
	public String getMessage() {
		return "Qualified name found for '" + qualifiedName.get(qualifiedName.size()-1) 
		     + "', but corresponding classifier need to be created under qualified name '" + qualifiedName + "'";
	}
}
