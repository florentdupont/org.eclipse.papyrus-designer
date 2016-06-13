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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * @author dumoulin
 *
 */
public class Properties {

	String stringProperty;
	
	String str1, str2, str3;
	
	String str11="init", str12="str12", str13;
	
	String propertyWithInit = "hello";
	
	// Arrays mono dimensional
	
	String[] stringArray;
	
	String[] stringArrayWithInit = {"a", "b"};
	
	String stringArrayWithFixedSize[] = new String[3];
	
	String stringArrayVar1[], stringArrayVar2[];
	
	String[] stringArrayVar3, stringArrayVar4[];
	
	// Arrays multi-dimensionnals
	
	String[][] stringArray2Dim;
	
	String[][] stringArray2DimWithInit = { {"a1", "b1" }, {"a2", "b2" } };
	
	// mixed cardinality declaration
	String a[]={"hello"}, b[][]={{"",""}};
	
	String[] a1[]={{"hello"}}, b2[][]={{{"",""}}};
	
	
	// Primitives
	int x; 
	int y, z;
	
	int[] intArray;
	
	int[] intArrayWithInit = {1,2};
	
	// Collections
	List<String> listOfString;
	
	List<Date> listWithInit = new ArrayList<Date>();
	List<Date> listWithInit2 = new ArrayList<Date>(), listWithInit3 = new ArrayList<Date>();
	
	// Collections multi-dimensionnals
	List<List<String>> listOfListOfString;

	// Annotations
	@Inject
	List<String> injectedValue;
	
	/**
	 * Constructor.
	 *
	 */
	public Properties() {
		// TODO Auto-generated constructor stub
	}

}
