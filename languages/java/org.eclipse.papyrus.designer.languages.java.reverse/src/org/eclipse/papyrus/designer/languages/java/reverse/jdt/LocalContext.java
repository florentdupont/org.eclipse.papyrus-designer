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

package org.eclipse.papyrus.designer.languages.java.reverse.jdt;


import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;

/**
 * A context local to the currently processed {@link NamedElement}. This NamedElement is also a {@link Namespace}.
 * 
 * 
 * @author cedric dumoulin
 *
 */
public class LocalContext {

	protected Namespace namespace;
	protected LocalContext parentContext;
	
	/**
	 * Constructor.
	 * @param contextNamespace The namespace associated to this current context.
	 * 
	 *
	 */
	public LocalContext(Namespace contextNamespace) {
		this.namespace = contextNamespace;
		
	}

	/**
	 * Constructor.
	 * @param contextNamespace The namespace associated to this current context.
	 * 
	 *
	 */
	public LocalContext(Namespace contextNamespace, LocalContext parentContext) {
		this.namespace = contextNamespace;
		this.parentContext = parentContext;
	}

	/**
	 * @return the namespace
	 */
	public Namespace getNamespace() {
		return namespace;
	}

}
