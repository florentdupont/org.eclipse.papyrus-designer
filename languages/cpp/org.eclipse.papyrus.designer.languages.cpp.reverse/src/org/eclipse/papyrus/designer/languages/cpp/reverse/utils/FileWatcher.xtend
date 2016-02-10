/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Van Cam Pham (CEA LIST) <vancam.pham@cea.fr> - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.reverse.utils;

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.TimeUnit
import org.eclipse.cdt.core.model.ICProject
import org.eclipse.cdt.core.model.ITranslationUnit
import org.eclipse.cdt.core.model.ICContainer
import org.eclipse.cdt.core.model.IParent
import org.eclipse.emf.common.util.UniqueEList
import java.util.List
/**
 * Listens to changes in files
 *
 */
class FileWatcher {
	private ICProject m_cproject 
	private long timestamp
	new(ICProject project, long timestamp) {
		this.m_cproject = project
		this.timestamp = timestamp
	} 
	
	public def isModified(ITranslationUnit itu) {
		var File file = itu.file.rawLocation.makeAbsolute.toFile
        var Path filePath = file.toPath();
        var BasicFileAttributes attributes = null;
        try {
            attributes = Files.readAttributes(filePath, BasicFileAttributes);
        }
        catch (IOException exception) {
            System.out.println("Exception handled when trying to get file " +
                    "attributes: " + exception.getMessage());
        }
        var long seconds = attributes.lastModifiedTime().to(TimeUnit.SECONDS);
        if((seconds > Long.MIN_VALUE) && (seconds < Long.MAX_VALUE) && seconds > timestamp) {
        	return true
        }
        return false
	}
	
	public def List<ITranslationUnit> getModifiledTranslationUnits(IParent parent) {
		val List<ITranslationUnit> ret = new UniqueEList
		if (!(parent instanceof ITranslationUnit)) {
			ret.addAll(parent.children.filter(typeof(ITranslationUnit)).filter[it.modified])
			parent.children.filter(typeof(IParent)).forEach[
				ret.addAll(it.modifiledTranslationUnits)
			]
		}
		return ret
	}
}