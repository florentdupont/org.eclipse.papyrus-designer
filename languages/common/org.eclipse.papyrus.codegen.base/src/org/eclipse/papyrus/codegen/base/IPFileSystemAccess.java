/*******************************************************************************
 * Copyright (c) 2006 - 2014 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *
 *******************************************************************************/

package org.eclipse.papyrus.codegen.base;


/**
 * Simple interface inspired from IFileSystemAccess in xtext.generator.
 * The prefix 'P' should avoid confusions with the latter. We do not the xbase class
 * directly to avoid a dependency to xtext (not all potential generators are xtend/xtext based).
 */
public interface IPFileSystemAccess {
	
	/**
	 * @param fileName using '/' as file separator
	 * @param contents the to-be-written contents.
	 */
	public void generateFile(String fileName, String contents);
	
	/**
	 * @param fileName using '/' as file separator
	 */
	public void deleteFile(String fileName);
}