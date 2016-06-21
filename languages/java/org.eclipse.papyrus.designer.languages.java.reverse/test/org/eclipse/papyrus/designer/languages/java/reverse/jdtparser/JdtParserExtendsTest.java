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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.AbstractTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cedric dumoulin
 *
 */
public class JdtParserExtendsTest extends AbstractTest {

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
	 * Open the specified java file and parse it. Return the corresponding compilation unit
	 * @return The {@link CompilationUnit} corresponding to the specified file.
	 * 
	 * @throws IOException
	 */
	public CompilationUnit parseCompilationUnit(String classname) throws IOException {
		InputStream inputStream = getJavaFileInputStream(classname);
		Scanner scanner = new Scanner(inputStream,"UTF-8");
		String str = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		return cu;
	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.InputStream)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testExtendsSimple() throws IOException {
		
			CompilationUnit cu = parseCompilationUnit(ExtendsSimple_class);
			
			assertNotNull("CU created", cu);
			
			List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
			// Get first type
			TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
			
			// Extend type is 'Date'
			Type extendsType = typeDeclaration.getSuperclassType();
			
			assertNotNull("exstends found", extendsType);
			assertTrue( "is simple Type", extendsType.isSimpleType() );
			assertFalse( "is Generic Type", extendsType.isParameterizedType() );
			assertFalse( "is Generic Type", extendsType.isArrayType() );
			assertFalse( "is Generic Type", extendsType.isNameQualifiedType() );
			assertFalse( "is Generic Type", extendsType.isPrimitiveType() );
			assertFalse( "is Generic Type", extendsType.isWildcardType() );
			
			SimpleType simpleType = (SimpleType)extendsType;
			assertEquals("Type Name found", "Date", simpleType.getName().getFullyQualifiedName());
			
//			
//			TypeDeclaration typeDecl = cu.getTypes().get(0);
//			assertNotNull("comment found", typeDecl.getComment());
////			assertNotNull("javadoc found", typeDecl.getJavaDoc());
//			System.err.println(typeDecl.getComment());
			
	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.Reader, boolean)}.
	 * @throws IOException 
	 */
	@Test
	public void testExtendsGeneric() throws IOException {
		CompilationUnit cu = parseCompilationUnit(ExtendsGeneric_class);

		List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
		// Get first type
		TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
		
		// Extend type is 'Date'
		Type extendsType = typeDeclaration.getSuperclassType();
		
		assertNotNull("exstends found", extendsType);

		assertFalse( "is simple Type", extendsType.isSimpleType() );
		assertTrue( "is Generic Type", extendsType.isParameterizedType() );
		assertFalse( "is Generic Type", extendsType.isArrayType() );
		assertFalse( "is Generic Type", extendsType.isNameQualifiedType() );
		assertFalse( "is Generic Type", extendsType.isPrimitiveType() );
		assertFalse( "is Generic Type", extendsType.isWildcardType() );
		
		ParameterizedType subType = (ParameterizedType)extendsType;
		assertEquals("Type Name found", "ArrayList", ((SimpleType)subType.getType()).getName().getFullyQualifiedName());

	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.InputStream)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testExtendsWithGeneric() throws IOException {
		

			CompilationUnit cu = parseCompilationUnit(ExtendsWithGeneric_class);
			
			List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
			// Get first type
			TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
			
			// Extend type is 'Date'
			Type extendsType = typeDeclaration.getSuperclassType();
			
			assertNotNull("exstends found", extendsType);
			assertFalse( "is simple Type", extendsType.isSimpleType() );
			assertTrue( "is Generic Type", extendsType.isParameterizedType() );
			assertFalse( "is Generic Type", extendsType.isArrayType() );
			assertFalse( "is Generic Type", extendsType.isNameQualifiedType() );
			assertFalse( "is Generic Type", extendsType.isPrimitiveType() );
			assertFalse( "is Generic Type", extendsType.isWildcardType() );
			
			ParameterizedType subType = (ParameterizedType)extendsType;
			assertEquals("Type Name found", "ArrayList", ((SimpleType)subType.getType()).getName().getFullyQualifiedName());
	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.InputStream)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testTypeJavadoc() throws IOException {
		
			CompilationUnit cu = parseCompilationUnit(SimpleClass_class);
			
			List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
			// Get first type
			TypeDeclaration typeDeclaration = (TypeDeclaration)types.get(0);
			
			// Extend type is 'Date'
			Type extendsType = typeDeclaration.getSuperclassType();
			
			assertNull("exstends found", extendsType);
			
	}


}
