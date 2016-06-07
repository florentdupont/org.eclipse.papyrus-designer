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

import org.eclipse.papyrus.designer.languages.java.reverse.classesundertest.B.BNested;
import org.eclipse.papyrus.designer.languages.java.reverse.classesundertest.ClassWithNestedClassAndCrossReferences.ANested;

/**
 * @author dumoulin
 *
 */
public class ClassWithNestedClassAndCrossReferences {

	BNested bNested ;
	
	/**
	 * Constructor.
	 *
	 */
	public ClassWithNestedClassAndCrossReferences() {
		// TODO Auto-generated constructor stub
	}

	class ANested {
		class AANested {
			class AAANested {
				
			}
		}
	}
}

/**
 * 
 * @author dumoulin
 *
 */
  abstract class B {
	
	  ANested aNested;
	  
	  class BNested {
		  
	  }
  }
  
