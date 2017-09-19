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

package org.eclipse.papyrus.designer.transformation.languages.cpp.library.statemachine

import org.eclipse.uml2.uml.Type
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext
import org.eclipse.papyrus.designer.languages.common.base.ElementUtils

class PThreadTypes {

	val static PTHREAD_COND_T = "pthread::pthread_cond_t"
	val static PTHREAD_MUTEX_T = "pthread::pthread_mutex_t"
	val static PTHREAD_T = "pthread::pthread_t"

	def public getPthreadCond() {
		ElementUtils.getQualifiedElementFromRS(getRoot, PTHREAD_COND_T) as Type
	}

	def public getPthreadMutex() {
		ElementUtils.getQualifiedElementFromRS(getRoot, PTHREAD_MUTEX_T) as Type
	}

	def public getPthread() {
		ElementUtils.getQualifiedElementFromRS(getRoot, PTHREAD_T) as Type
	}

	def public getRoot() {
		TransformationContext.current.copier.source
	}
}
