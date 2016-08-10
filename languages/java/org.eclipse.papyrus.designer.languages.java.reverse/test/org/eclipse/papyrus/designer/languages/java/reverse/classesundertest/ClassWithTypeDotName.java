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

import org.eclipse.papyrus.designer.languages.java.reverse.classesundertest.ClassWithNestedClassAndCrossReferences.ANested;
import org.eclipse.papyrus.designer.languages.java.reverse.classesundertest.ClassWithNestedClassAndCrossReferences.ANested.AANested;
import org.eclipse.papyrus.designer.languages.java.reverse.classesundertest.ClassWithNestedClassAndCrossReferences.ANested.AANested.AAANested;;

/**
 * Test of type of the form Type.name.
 * 
 * 
 * @author dumoulin
 *
 */
public class ClassWithTypeDotName  {

	ClassWithNestedClassAndCrossReferences.ANested.AANested subClass;
	
	ANested.AANested subClass2;
	
	AANested.AAANested subClass3;
	
	AAANested subclass4;
	
	ANested.AANested.AAANested subClass5;
}
