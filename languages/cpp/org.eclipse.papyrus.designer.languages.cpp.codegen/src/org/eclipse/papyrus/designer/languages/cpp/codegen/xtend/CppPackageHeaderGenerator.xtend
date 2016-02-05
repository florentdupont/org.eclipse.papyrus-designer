package org.eclipse.papyrus.designer.languages.cpp.codegen.xtend

/*******************************************************************************
 * Copyright (c) 2014 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/
 
import org.eclipse.papyrus.designer.languages.cpp.codegen.preferences.CppCodeGenUtils
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.uml2.uml.Package
import org.eclipse.papyrus.designer.languages.common.base.GenUtils

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppPackageHeaderGenerator {
	
	static def generateCode(Package pkg) '''
		#ifndef PKG_«GenUtils.getFullNameUC(pkg)»
		#define PKG_«GenUtils.getFullNameUC(pkg)»

		/************************************************************
		              Pkg_«pkg.name» package header
		 ************************************************************/

		«CppIncludeUtil.includeDirective(CppClassIncludeClassDeclaration.cppOwnerPackageIncludePath(pkg.nestingPackage))»
		
		#ifndef _IN_
		#define _IN_
		#endif
		#ifndef _OUT_
		#define _OUT_
		#endif
		#ifndef _INOUT_
		#define _INOUT_
		#endif
		
			
		/* Package dependency header include                        */
		«FOR p: GenUtils.getUsedPackages(pkg)»
		«var path = GenUtils.getFullPath(p) + "/Pkg_"+ p.name + "." + CppCodeGenUtils.getHeaderSuffix»
		«CppIncludeUtil.includeDirective(path)»
		«ENDFOR»
		
		«CppIncludeUtil.CppIncludeHeader(pkg)»
		«CppGenUtils.openNS(pkg)»
		
		// Types defined within the package
		«CppClassTypeAndEnum.CppClassTypeAndEnumPackage(pkg)»
		«CppGenUtils.closeNS(pkg)»
		
		/************************************************************
		              End of Pkg_«pkg.name» package header
		 ************************************************************/
		
		#endif
	'''
}