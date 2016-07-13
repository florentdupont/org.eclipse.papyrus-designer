/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.cpp.cdt.project;

import java.util.List;

import org.eclipse.papyrus.designer.languages.common.extensionpoints.AbstractSettings;

public class CDTSettings extends AbstractSettings {

	/**
	 * include paths for compiler
	 */
	List<String> includePaths;

	/**
	 * list of libraries for linker
	 */
	List<String> libs;

	/**
	 * list of library paths for linker
	 */
	List<String> libPaths;

	/**
	 * list of predefined macros (#define)
	 */
	List<String> macros;
}
