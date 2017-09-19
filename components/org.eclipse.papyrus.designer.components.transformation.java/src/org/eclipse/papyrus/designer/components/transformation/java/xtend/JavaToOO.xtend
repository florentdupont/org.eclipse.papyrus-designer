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
package org.eclipse.papyrus.designer.components.transformation.java.xtend

import org.eclipse.papyrus.designer.components.modellibs.core.transformations.AbstractCompToOO
import org.eclipse.papyrus.designer.components.transformation.extensions.IOOTrafo
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Element
import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.UMLFactory
import org.eclipse.papyrus.designer.transformation.base.utils.PartsUtil

/**
 * This class realizes the transformation from component-based to object-oriented
 * models for Java. It refines the abstract component to OO transformation
 */
class JavaToOO extends AbstractCompToOO implements IOOTrafo {

	override init(LazyCopier copier, Class bootloader) {
		super.init(copier, bootloader)
		progLang = "JAVA"
	}

	/**
	 * always dot
	 */
	override nameRef(Property attribute) '''
		«attribute.name».
	'''
	
	// do nothing, all attributes are references by default
	override applyRef(Element element) {
	}

	// all attributes are references in Java, nothing to do
	override getRef(Property attribute) '''
		«attribute.name»
	'''

	/**
	 * If the bootloader is responsible for creating an instance (if it is a
	 * abstract type), do nothing since the part is already a reference in Java.
	 * If the composite is responsible, add a default value that creates the sub-instances.
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
				if (!instantiateViaBootloader(type)) {
					val oe = UMLFactory.eINSTANCE.createOpaqueExpression
					oe.languages.add(progLang);
					oe.bodies.add('''new «type.qualifiedName.replace(NamedElement.SEPARATOR, ".")»()''')
					attribute.defaultValue = oe	
				}
			}
		}
	}
}
