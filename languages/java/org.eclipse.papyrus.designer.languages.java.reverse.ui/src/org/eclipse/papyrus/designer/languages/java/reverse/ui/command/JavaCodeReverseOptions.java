/*****************************************************************************
 * Copyright (c) 2016 Cedric Dumoulin and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   cedric.dumoulin@univ-lille1.fr - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.ui.command;

import java.util.List;

import org.eclipse.papyrus.uml.tools.model.UmlModel;

/**
 * Class holding options for the reverse engine.
 * This class is used to carry reverse option values.
 * 
 * @author cedric dumoulin
 *
 */
public class JavaCodeReverseOptions {

	protected List<String> searchPaths;
	
	protected UmlModel umlModel;
	
	protected String packageName;

	/**
	 * @return the searchPaths
	 */
	public List<String> getSearchPaths() {
		return searchPaths;
	}

	/**
	 * @param searchPaths the searchPaths to set
	 */
	public void setSearchPaths(List<String> searchPaths) {
		this.searchPaths = searchPaths;
	}

	/**
	 * @return the umlModel
	 */
	public UmlModel getUmlModel() {
		return umlModel;
	}

	/**
	 * @param umlModel the umlModel to set
	 */
	public void setUmlModel(UmlModel umlModel) {
		this.umlModel = umlModel;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
}
