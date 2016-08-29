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
import org.eclipse.uml2.uml.Package

class PThreadTypes {
	Package target
	
	new (Package target) {
		// thread pthread model is imported into state-machine, its members can be directly found. 
		this.target = target;
	}
	
	def public getPthreadCond() {
		if (target != null) {
			target.getMember("pthread_cond_t") as Type
		}
	}
	
	def public getPthreadMutex() {
		if (target != null) {
			target.getMember("pthread_mutex_t") as Type
		}
	}
	
	def public getPthread() {
		if (target != null) {
			target.getMember("pthread_t") as Type
		}
	}
}
