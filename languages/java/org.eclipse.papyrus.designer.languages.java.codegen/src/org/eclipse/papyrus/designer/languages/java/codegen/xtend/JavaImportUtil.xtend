/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/
 
package org.eclipse.papyrus.designer.languages.java.codegen.xtend

import org.eclipse.papyrus.designer.languages.common.base.GenUtils
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.util.UMLUtil

class JavaImportUtil {
	static def importDirective(String path, String prefix) {
		if ((path != null) && (path.length > 0))
			return '''import ''' + prefix + path + ';'
	}

	static def javaImport(NamedElement ne) {
		if (GenUtils.hasStereotype(ne, Import)) {
			UMLUtil.getStereotypeApplication(ne, Import)
			var header = UMLUtil.getStereotypeApplication(ne, Import).manualImports
			if ((header != null) && (header.length > 0)) {
				var includeHeader = org.eclipse.papyrus.designer.languages.java.codegen.xtend.JavaImportUtil.constImportStart + GenUtils.cleanCR(header) + '\n' +
					org.eclipse.papyrus.designer.languages.java.codegen.xtend.JavaImportUtil.constImportEnd
				return includeHeader
			}
		}
	}

	static def constImportStart() '''
		// Manual imports
	'''

	static def constImportEnd() '''
		// End of manual imports
	'''
}
