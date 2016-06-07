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
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.AbstractTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cedric dumoulin
 *
 */
public class JdtParserTest extends AbstractTest {

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
	 * @throws ParseException 
	 */
	@Test
	public void testParseInputStream() throws IOException {
		
			InputStream inputStream = getJavaFileInputStream(SimpleClass_class);
			Scanner scanner = new Scanner(inputStream,"UTF-8");
			String str = scanner.useDelimiter("\\A").next();
			scanner.close();
			
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(str.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
	 
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			
			assertNotNull("CU created", cu);
			
			List<AbstractTypeDeclaration> types = (List<AbstractTypeDeclaration>)cu.types();
			// Get first type
			AbstractTypeDeclaration type = types.get(0);
			
			assertEquals("Name is ok", type.getName().getIdentifier(), SimpleClass_classname );
			assertEquals("Name is ok", type.getName().getFullyQualifiedName(), SimpleClass_classname );
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
	public void testParseVisitor() throws IOException {
		InputStream inputStream = getJavaFileInputStream(SimpleClass_class);
		Scanner scanner = new Scanner(inputStream,"UTF-8");
		String str = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		ASTVisitor visitor = new ASTVisitor() {
			
			
		};
		
		cu.accept(visitor);
	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.InputStream)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testImports() throws IOException {
		
			InputStream inputStream = getJavaFileInputStream(ClassWithImport_class);
			Scanner scanner = new Scanner(inputStream,"UTF-8");
			String str = scanner.useDelimiter("\\A").next();
			scanner.close();
			
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(str.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
	 
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			
			assertNotNull("CU created", cu);
						
			// Test imports
			assertNotNull("Import is ok", cu.imports());
			assertTrue("Has imports", cu.imports().size() > 0 );

			List<ImportDeclaration> imports = cu.imports();
			boolean found = false;
			for( ImportDeclaration decl : imports) {
				System.err.println("import=" + decl.getName().getFullyQualifiedName());
				if( "java.util.Date".equals( decl.getName().getFullyQualifiedName() ) ) {
					found = true;
					break;
				}
			}
			assertTrue( "import found", found);

			
	}


}
