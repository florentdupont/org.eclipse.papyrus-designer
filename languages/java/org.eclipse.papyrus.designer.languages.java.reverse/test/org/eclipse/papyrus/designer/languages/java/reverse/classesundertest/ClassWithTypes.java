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
 * Class to test types references
 * 
 * @author dumoulin
 *
 */
public class ClassWithTypes {

	SimpleClass simpleClass;
	
	ClassWithGeneric<String> classWithGeneric;
	
	int[] arrayOfInt;
	
	List<String> listOfString;
	
	int x, y;
	
	String aString;
	
	/**
	 * Constructor.
	 *
	 */
	public ClassWithTypes() {
		// TODO Auto-generated constructor stub
	}

	
}
