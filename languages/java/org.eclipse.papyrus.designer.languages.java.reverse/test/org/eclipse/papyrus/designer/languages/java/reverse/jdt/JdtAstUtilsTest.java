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

package org.eclipse.papyrus.designer.languages.java.reverse.jdt;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NotFoundException;
import org.eclipse.papyrus.designer.languages.java.reverse.jdtparser.AbstractJdtParserTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dumoulin
 *
 */
public class JdtAstUtilsTest extends AbstractJdtParserTest {

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

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.jdt.JdtAstUtils#getTypeShortname(org.eclipse.jdt.core.dom.Type)}.
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	@Test
	public void testGetTypeShortname() throws IOException, NotFoundException {
		CompilationUnit cu = parseCompilationUnit(ClassWithTypes_class);
		
		assertNotNull("CU created", cu);
		
		List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
		// Get first type
		TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
		
		// Get declared properties
		String fieldName = "simpleClass";
		String typeName = "SimpleClass";
		FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, fieldName );
		
		assertNotNull("field found", field);
		assertEquals("Type Name found", typeName, JdtAstUtils.getTypeShortname(field.getType()));
				
		fieldName = "classWithGeneric";
		typeName = "ClassWithGeneric";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);
		assertEquals("Type Name found", typeName, JdtAstUtils.getTypeShortname(field.getType()));

		fieldName = "listOfString";
		typeName = "List";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);
		assertEquals("Type Name found", typeName, JdtAstUtils.getTypeShortname(field.getType()));

		fieldName = "aString";
		typeName = "String";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);
		assertEquals("Type Name found", typeName, JdtAstUtils.getTypeShortname(field.getType()));

		fieldName = "x";
		typeName = "int";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);
		assertEquals("Type Name found", typeName, JdtAstUtils.getTypeShortname(field.getType()));

		fieldName = "arrayOfInt";
		typeName = "int";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);
		assertEquals("Type Name found", typeName, JdtAstUtils.getTypeShortname(field.getType()));

//		fieldName = "";
//		typeName = "";
//		field = JdtAstUtils.getField( typeDeclaration, fieldName );
//		assertNotNull("field found", field);
//		assertEquals("Type Name found", typeName, JdtAstUtils.getTypeShortname(field.getType()));

	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.jdt.JdtAstUtils#getField(org.eclipse.jdt.core.dom.TypeDeclaration, java.lang.String)}.
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	@Test
	public void testGetField() throws IOException, NotFoundException {
		CompilationUnit cu = parseCompilationUnit(ClassWithTypes_class);
		
		assertNotNull("CU created", cu);
		
		List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
		// Get first type
		TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
		
		// Get declared properties
		String fieldName = "simpleClass";
		String typeName = "SimpleClass";
		FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, fieldName );
		
		assertNotNull("field found", field);
				
		fieldName = "classWithGeneric";
		typeName = "ClassWithGeneric";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);

		fieldName = "listOfString";
		typeName = "List";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);

		fieldName = "aString";
		typeName = "String";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);

		fieldName = "x";
		typeName = "int";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);

		fieldName = "arrayOfInt";
		typeName = "int";
		field = JdtAstUtils.getField( typeDeclaration, fieldName );
		assertNotNull("field found", field);
	}

}
