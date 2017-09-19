/*****************************************************************************
 * Copyright (c) 2015 CEA LIST.
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 * 
 *****************************************************************************/
package org.eclipse.papyrus.designer.components.transformation.cpp.xtend

import org.eclipse.papyrus.designer.components.modellibs.core.transformations.AbstractCompToOO
import org.eclipse.papyrus.designer.components.transformation.extensions.IOOTrafo
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.Property
import org.eclipse.papyrus.designer.transformation.base.utils.PartsUtil

/**
 * This class realizes the transformation from component-based to object-oriented
 * models for C++. It refines the abstract component to OO transformation
 */
class StaticCppToOO extends AbstractCompToOO implements IOOTrafo {

	def override init(LazyCopier copier, Class bootloader) {
		super.init(copier, bootloader)
		progLang = "C/C++";
	}

	override nameRef(Property attribute) {
		CppUtils.nameRef(attribute)
	}
	
	override applyRef(Element element) {
		StereotypeUtil.apply(element, Ptr)
	}

	override getRef(Property attribute) {
		CppUtils.getRef(attribute)
	}

	/**
	 * Transform parts if necessary.
	 * 
	 * If the bootloader is responsible for creating an instance (if it is a
	 * abstract type), mark the associated part as a C++ pointer. We do not want
	 * to change the aggregation kind, since it remains logically a composition,
	 * it is merely an implementation issue that it must be a pointer for C++ if
	 * the concrete type is not yet known.
	 * 
	 * @param compositeImplementation
	 *            a (composite) component
	 */
	override transformParts(Class compositeImplementation) {

		for (Property attribute : PartsUtil.getParts(compositeImplementation)) {
			val type = attribute.type
			if (type instanceof Class) {
				// => requires adaptations of boot-loader which is then only
				// responsible for creating instances corresponding to types
				if (instantiateViaBootloader(type)) {
					StereotypeUtil.apply(attribute, Ptr)
				}
			}
		}
	}
}
