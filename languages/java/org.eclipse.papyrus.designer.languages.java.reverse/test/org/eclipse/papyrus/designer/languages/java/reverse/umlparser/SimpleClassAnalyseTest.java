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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.AbstractTest;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ParseException;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dumoulin
 *
 */
public class SimpleClassAnalyseTest extends AbstractTest {

	static protected CompilationUnitAnalyser analyser;
	
	protected CompilationUnit cu;
	protected Classifier c;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.eclipse.uml2.uml.Package modelRootPackage = UMLFactory.eINSTANCE.createPackage();
		String generatePackageName = "generate";
		List<String> searchPaths = Arrays.asList("*", generatePackageName);
		
		analyser = new CompilationUnitAnalyser(modelRootPackage, generatePackageName, searchPaths);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String filename = AbstractTest_class;
		
		InputStream inputStream = getJavaFileInputStream(filename);
		
		cu = JavaParser.parse(inputStream, null, true);
		analyser.processCompilationUnit(cu);
		
		List<String> qualifiedName = UmlUtils.slashNameToQualifiedName(filename);
		
		c = analyser.getClassifierCatalog().getClassifier(qualifiedName);
		
		inputStream.close();
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
	public void testCompilationUnit() throws IOException, ParseException {
		assertNotNull("cu created", cu);
	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.umlparser.CompilationUnitAnalyser#processCompilationUnit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testClassifierCreated() throws IOException, ParseException {
		assertNotNull("classifier created", c);
	}

	@Test
	public void testComment() throws IOException, ParseException {
		assertTrue("comment found", c.getOwnedComments().size()>0);
		
	}


}
