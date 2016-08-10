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

import org.eclipse.jdt.core.dom.FieldDeclaration;

/**
 * Class carrying data about the Type of a Property, a Parameter,  ...
 * 
 * @author cedric dumoulin
 *
 */
public class TypeReferenceDeclaration {

	public enum MultiplicityKind {
		simple, array, collection;
	}
	
	MultiplicityKind multiplicityKind = MultiplicityKind.simple;
	
	/**
	 * Constructor.
	 *
	 * @param fieldDeclaration
	 */
	public TypeReferenceDeclaration(FieldDeclaration fieldDeclaration) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public MultiplicityKind getMultiplicityKind() {
		// TODO Auto-generated method stub
		return multiplicityKind;
	}

}
