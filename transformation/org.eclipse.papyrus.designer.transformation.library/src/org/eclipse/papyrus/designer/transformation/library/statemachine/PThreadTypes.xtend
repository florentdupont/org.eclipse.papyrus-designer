/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Van Cam Pham        <VanCam.PHAM@cea.fr>
 *
 *****************************************************************************/
 
 package org.eclipse.papyrus.designer.transformation.library.statemachine

import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.Model
import org.eclipse.uml2.uml.Package

class PThreadTypes {
	Package mPthreadPkg
	
	new (Model target) {
		for (pkg : target.importedPackages) {
			if (pkg.name.equals("core")) {
				val pthreadPkgCandidate = pkg.getMember("pthread")
				if (pthreadPkgCandidate instanceof Package) {
					mPthreadPkg = pthreadPkgCandidate as Package
				}
			}
		}
	}
	
	def public getPthreadCond() {
		if (mPthreadPkg != null) {
			mPthreadPkg.getPackagedElement("pthread_cond_t") as Type
		}
	}
	
	def public getPthreadMutex() {
		if (mPthreadPkg != null) {
			mPthreadPkg.getPackagedElement("pthread_mutex_t") as Type
		}
	}
	
	def public getPthread() {
		if (mPthreadPkg != null) {
			mPthreadPkg.getPackagedElement("pthread_t") as Type
		}
	}
}
