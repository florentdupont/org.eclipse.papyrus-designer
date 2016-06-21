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

package org.eclipse.papyrus.designer.languages.java.reverse.classesundertest;

import java.util.List;

/**
 * @author dumoulin
 *
 */
public class PropertiesWithGenerics<N, T> {

	T propertyTypedByGeneric;
	
	T[] arrayTypedByGeneric;
	
	List<N> listWithGenericN;
	
	List<List<N>> listOfListOfGenericN;
	
	List<?> listOfUnknown;
	
	List<? extends String> listOfUnknownExtendingString;
	
	/**
	 * Constructor.
	 *
	 */
	public PropertiesWithGenerics() {
		// TODO Auto-generated constructor stub
	}

}
