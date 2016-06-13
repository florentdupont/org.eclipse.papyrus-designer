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


import org.eclipse.emf.ecore.EClass;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NotFoundException;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;

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

	/**
	 * Lookup for a Classifier with the specified shortname in the contexts.
	 * First, check this context, and if not found, recursively check parent contexts.
	 * 
	 * @param shortname
	 * @param classType
	 * @return the classifier, or null if not found.
	 */
	public Classifier lookupClassifier(String shortname, EClass classType) {
		
		Classifier result;
		
		result = (Classifier)namespace.getOwnedMember(shortname, false, classType);
		if( result != null) {
			return result;
		}
		// Check in parent
		if( parentContext != null ) {
			return parentContext.lookupClassifier(shortname, classType);
		}
		// Not found
		return null;
	}

	/**
	 * Get the Current package of the contexts.
	 * The current package is the first {@link Package} encountered in contexts' namespaces. 
	 * 
	 * @return The current package, or null if not found (should not happen).
	 */
	public Package getCurrentPackage()  {
		
		if( parentContext != null ) {
			return parentContext.getCurrentPackage();
		}
		// There is no parent, so we are the outest most context. The namespace should be a Package
		if( namespace instanceof Package ) {
			return (Package)namespace;
		}
		
		// Not found !! Should not happen.
//		throw new NotFoundException("No package found in Contexts.");
		return null;
	}

}
