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

package org.eclipse.papyrus.designer.languages.java.reverse.javaparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.TypeDeclaration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cedric dumoulin
 *
 */
public class JavaParserTest extends AbstractTest {

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
	public void testParseInputStream() throws IOException, ParseException {
		
			InputStream inputStream = getJavaFileInputStream(JavaParser_class);
			
			CompilationUnit cu = JavaParser.parse(inputStream, null, true);
			assertNotNull("CU created", cu);
			
			List<TypeDeclaration> types = cu.getTypes();
			assertEquals("Found 1 type", 1, types.size() );
			// Get first type
			TypeDeclaration typeDecl = types.get(0);
			assertNotNull("comment found", typeDecl.getComment());
//			assertNotNull("javadoc found", typeDecl.getJavaDoc());
			System.err.println(typeDecl.getComment());
			
	}

	/**
	 * Test method for {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.JavaParser#parse(java.io.Reader, boolean)}.
	 */
	@Test
	public void testParseReaderBoolean() {
		fail("Not yet implemented");
	}

}
