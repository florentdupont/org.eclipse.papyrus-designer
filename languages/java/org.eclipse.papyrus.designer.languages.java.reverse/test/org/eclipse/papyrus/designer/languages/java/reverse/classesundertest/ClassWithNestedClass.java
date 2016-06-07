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

/**
 * @author dumoulin
 *
 */
public class ClassWithNestedClass {

	/**
	 * Constructor.
	 *
	 */
	public ClassWithNestedClass() {
		// TODO Auto-generated constructor stub
	}

	
	class A1 {
		class A2 {
			class A3 {
				ClassWithNestedClass a;
				ClassWithNestedClassAndCrossReferences external;
				
				A1 a1;
				A2 a2;
				A22 a22;
			}
		}
		
		class A22 {
			
		}
	}
}

abstract class A22 {
	
}
