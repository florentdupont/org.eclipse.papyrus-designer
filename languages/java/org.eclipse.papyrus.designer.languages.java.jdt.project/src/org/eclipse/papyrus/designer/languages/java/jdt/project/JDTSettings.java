/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.jdt.project;

import java.util.List;

import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject;

/**
 * Stores the information that is needed to configure a Java project such as its nature(s),
 * the containers to be added to the class paths (eg. Maven container), and the output location for the ".class" files.
 * It also stores the information needed for generating the POM file of a maven project. 
 */

public class JDTSettings extends AbstractSettings {

	/**
	 * Project natures
	 */
	List<String> projectNatures;

	/**
	 * List of class paths
	 */
	List<String> classPaths;

	/**
	 * Output folder for compiled ".class"
	 */
	String outputLocation;
	
	/**
	 * Details of the POM file
	 */
	MavenProject mavenSettings;
}
