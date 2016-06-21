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



import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.AbstractTest;

/**
 * @author dumoulin
 *
 */
public class AbstractJdtParserTest extends AbstractTest {

	/**
	 * Constructor.
	 *
	 */
	public AbstractJdtParserTest() {
		super();
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

}