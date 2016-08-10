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
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NotFoundException;
import org.eclipse.papyrus.designer.languages.java.reverse.jdt.JdtAstUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test QName used in type references.
 * 
 * @author cedric dumoulin
 *
 */
public class JdtParserTypeQNameTest extends AbstractJdtParserTest {

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
	public void testTypeName() throws IOException, NotFoundException {
		
			CompilationUnit cu = parseCompilationUnit(ClassWithTypesQName_class);
			
			assertNotNull("CU created", cu);
			
			List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
			// Get first type
			TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
			
			// Get declared properties
			FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, "nameFromType" );
			
			assertNotNull("field found", field);
			assertTrue( "isSimpleType", field.getType().isSimpleType() );
			SimpleType type = ((SimpleType)field.getType());
			assertTrue( "isSimpleName", type.getName().isQualifiedName() );
			
			assertEquals("Type Name found", "ClassWithQnames.A", JdtAstUtils.getTypeShortname(field.getType()));

	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.Reader, boolean)}.
	 * @throws IOException 
	 * @throws NotFoundException 
	 */
	@Test
	public void testFullyQualifiedNameType() throws IOException, NotFoundException {
		CompilationUnit cu = parseCompilationUnit(ClassWithTypesQName_class);

		List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
		// Get first type
		TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
		
		// Get declared properties
		FieldDeclaration field = JdtAstUtils.getField( typeDeclaration, "fullyQualifiedName" );
		
		assertNotNull("field found", field);
		assertTrue( "isSimpleType", field.getType().isSimpleType() );
		SimpleType type = ((SimpleType)field.getType());
		assertTrue( "isSimpleName", type.getName().isQualifiedName() );
		
		assertEquals("Type Name found", "org.eclipse.papyrus.designer.languages.java.reverse.classesundertest.SimpleClass", JdtAstUtils.getTypeShortname(field.getType()));

	}

}
