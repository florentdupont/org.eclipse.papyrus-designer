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

package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.AbstractTest;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ParseException;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.TypeDeclaration;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author dumoulin
 *
 */
public class CompilationUnitAnalyserTest extends AbstractTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.umlparser.CompilationUnitAnalyser#processCompilationUnit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testProcessCompilationUnit() throws IOException, ParseException {
		InputStream inputStream = getJavaFileInputStream(JavaParser_class);
		
		CompilationUnit cu = JavaParser.parse(inputStream, null, true);
		
		org.eclipse.uml2.uml.Package modelRootPackage = UMLFactory.eINSTANCE.createPackage();
		String generatePackageName = "model";
		List<String> searchPaths = new ArrayList<String>();
		
		CompilationUnitAnalyser analyser = new CompilationUnitAnalyser(modelRootPackage, generatePackageName, searchPaths);
		
		
		assertNotNull("analyser created", analyser);
		assertEquals("Name is ok", cu.getTypes().get(0).getName(), "JavaParserTest" );
		TypeDeclaration typeDecl = cu.getTypes().get(0);
	}

}
