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
import org.eclipse.uml2.uml.ValueSpecification
import org.eclipse.uml2.uml.LiteralInteger
import org.eclipse.uml2.uml.LiteralBoolean
import org.eclipse.uml2.uml.LiteralReal
import org.eclipse.uml2.uml.LiteralString
import org.eclipse.uml2.uml.LiteralUnlimitedNatural

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
		
		«FOR pe : pkg.packagedElements»
			«IF pe instanceof ValueSpecification»
				«(pe as ValueSpecification).defineConstants»
			«ENDIF»
		«ENDFOR»

		/************************************************************
		              End of Pkg_«pkg.name» package header
		 ************************************************************/
		
		#endif
	'''
	
	def static defineConstants(ValueSpecification vs) {
		if (vs instanceof LiteralInteger) {
			'''const int «vs.name» = «vs.integerValue»;'''
		}
		else if (vs instanceof LiteralUnlimitedNatural) {
			'''const int «vs.name» = «vs.unlimitedValue»;'''
		}
		else if (vs instanceof LiteralReal) {
			'''const double «vs.name» = «vs.realValue»;'''
		}
		else if (vs instanceof LiteralBoolean) {
			'''const bool «vs.name» = «vs.booleanValue»;'''
		}
		else if (vs instanceof LiteralString) {
			'''const char* «vs.name» = «vs.stringValue»;'''
		}
	}
}