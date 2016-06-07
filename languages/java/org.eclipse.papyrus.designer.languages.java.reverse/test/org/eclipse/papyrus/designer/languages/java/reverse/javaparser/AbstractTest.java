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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author cedric dumoulin
 *
 */
public class AbstractTest {

	static public final String JavaParser_class = "org/eclipse/papyrus/designer/languages/java/reverse/javaparser/JavaParserTest";
	static public final String JavaParser_classname = classname(JavaParser_class);
	static public final String AbstractTest_class = "org/eclipse/papyrus/designer/languages/java/reverse/javaparser/AbstractTest";
	static public final String SimpleClass_class = "org/eclipse/papyrus/designer/languages/java/reverse/classesundertest/SimpleClass";	
	static public final String SimpleClass_classname = classname(SimpleClass_class);
	static public final String ClassWithImport_class = "org/eclipse/papyrus/designer/languages/java/reverse/classesundertest/ClassWithImports";	
	static public final String ClassWithImport_classname = classname(ClassWithImport_class);
	
	static public final String SRC_FOLDER_NAME = "test";
	static public final String JAVA_EXT = ".java";
	static public final String PLUGIN_NAME = "org.eclipse.papyrus.designer.languages.java.reverse";
	static public final String PLUGIN_PROTOCOL = "platform:/plugin/";
	private static final String SEPARATOR = "/";
	
	/**
	 * Constructor.
	 *
	 */
	public AbstractTest() {
		super();
	}

	/**
	 * @param javaparserClass
	 * @return
	 */
	private static String classname(String qualifiedName) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(SEPARATOR)+1);
	}

	/**
	 * Open the {@link InputStream} for the specified filename. 
	 * The filename is relative to the current project. 
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	static protected InputStream getJavaFileInputStream(String filename) throws IOException {
		
		String urlStr;
		if( filename.startsWith("/")) {
			urlStr = PLUGIN_PROTOCOL + PLUGIN_NAME + "/" +  SRC_FOLDER_NAME + filename + JAVA_EXT;
		}
		else {
			urlStr = PLUGIN_PROTOCOL + PLUGIN_NAME + "/" +  SRC_FOLDER_NAME + "/" +  filename + JAVA_EXT;
		}
			
		URL url = new URL(urlStr);
		InputStream inputStream = url.openConnection().getInputStream();
		
		return inputStream;
	}

}