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

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.*;
import org.eclipse.papyrus.designer.languages.java.reverse.jdtparser.*;

/**
 * Class to test '*' in imports.
 * In version 0.7.2, the imported classes are recognized only if they are reversed prior of this class.
 * 
 * @author dumoulin
 *
 */
public class ImportsWithStar extends AbstractTest {

	AbstractTest abstractTest;
	JdtParserExtendsTest javaParserTest;
	AbstractJdtParserTest abstractJdtParserTest;
	
	
	/**
	 * Constructor.
	 *
	 */
	public ImportsWithStar() {
		// TODO Auto-generated constructor stub
	}

}
