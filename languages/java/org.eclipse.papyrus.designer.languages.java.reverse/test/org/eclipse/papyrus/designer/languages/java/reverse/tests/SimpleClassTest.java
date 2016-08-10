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

package org.eclipse.papyrus.designer.languages.java.reverse.tests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.jdt.JdtCompilationUnitAnalyzer;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ClassifierCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.CompilationUnitAnalyser;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author cedric dumoulin
 *
 */
public class SimpleClassTest extends AbstractJavaReverseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		String[] filenames = {SimpleClass_class};
		Package p = createRootPackage("name");
		
		ClassifierCatalog catalog = reverseJavaFile(p, filenames);
		
		// Test class reversed
		Class c = (Class)catalog.getClassifier("org.eclipse.papyrus.designer.languages.java.reverse.classesundertest.SimpleClass");
		assertNotNull("classifier created", c);
		
		// Test property created
		Property property = c.getAttribute("x", null);
		assertNotNull("Property 'x' created", property);
		
		Operation operation = c.getOwnedOperation("getX", null, null);
		assertNotNull("Operation 'getX' created", operation);
	}

	/**
	 * @param string
	 * @return
	 */
	protected Package createRootPackage(String name) {
		// TODO Auto-generated method stub
		Package p = UMLFactory.eINSTANCE.createPackage();
		p.setName(name);
		
		return p;
	}

	/**
	 * @param p
	 * @param filenames
	 * @return
	 * @throws IOException 
	 */
	protected ClassifierCatalog reverseJavaFile(Package rootPackage, String[] filenames) throws IOException {
		// Create analyzer
		String generatePackageName = "model";
		List<String> searchPaths = new ArrayList<String>();
		
		JdtCompilationUnitAnalyzer analyser = new JdtCompilationUnitAnalyzer(rootPackage, generatePackageName, searchPaths);
		
		// reverse files
		for( String filename : filenames ) {
			InputStream inputStream = getJavaFileInputStream(filename);
			Scanner scanner = new Scanner(inputStream,"UTF-8");
			String str = scanner.useDelimiter("\\A").next();
			scanner.close();

			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(str.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);

			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

			analyser.processCompilationUnit(cu);
		}
		
		
		return analyser.getClassifierCatalog();
	}

}
