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

package org.eclipse.papyrus.designer.languages.java.reverse.jdtparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NotFoundException;
import org.eclipse.papyrus.designer.languages.java.reverse.jdt.JdtAstUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cedric dumoulin
 *
 */
public class JdtParserTypesTest extends AbstractJdtParserTest {

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
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.InputStream)}.
	 * @throws IOException 
	 * @throws NotFoundException 
	 * @throws ParseException 
	 */
	@Test
	public void testSimpleType() throws IOException, NotFoundException {
		
			CompilationUnit cu = parseCompilationUnit(ClassWithTypes_class);
			
			assertNotNull("CU created", cu);
			
			List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
			// Get first type
			TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
			
			// Get declared properties
			FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, "simpleClass" );
			
			assertNotNull("field found", field);
			assertEquals("Type Name found", "SimpleClass", JdtAstUtils.getTypeShortname(field.getType()));

	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.Reader, boolean)}.
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	@Test
	public void testGenericType() throws IOException, NotFoundException {
		CompilationUnit cu = parseCompilationUnit(ClassWithTypes_class);

		List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
		// Get first type
		TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
		
		// Get declared properties
		FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, "classWithGeneric" );
		
		assertNotNull("field found", field);
		assertEquals("Type Name found", "ClassWithGeneric", JdtAstUtils.getTypeShortname(field.getType()));

	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.InputStream)}.
	 * @throws IOException 
	 * @throws NotFoundException 
	 * @throws ParseException 
	 */
	@Test
	public void testArrayType() throws IOException, NotFoundException {
		
		CompilationUnit cu = parseCompilationUnit(ClassWithTypes_class);

		List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
		// Get first type
		TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
		
		// Get declared properties
		FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, "arrayOfInt" );
		
		assertNotNull("field found", field);
		assertEquals("Type Name found", "int", JdtAstUtils.getTypeShortname(field.getType()));

	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.InputStream)}.
	 * @throws IOException 
	 * @throws NotFoundException 
	 * @throws ParseException 
	 */
	@Test
	public void testTypeJavadoc() throws IOException, NotFoundException {
		
		CompilationUnit cu = parseCompilationUnit(ClassWithTypes_class);

		List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
		// Get first type
		TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
		
		// Get declared properties
		FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, "classWithGeneric" );
		
		assertNotNull("field found", field);
		assertEquals("Type Name found", "ClassWithGeneric", JdtAstUtils.getTypeShortname(field.getType()));

			
	}


}
