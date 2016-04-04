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

import org.eclipse.papyrus.designer.languages.java.codegen.utils.Modifier
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.Signal
import org.eclipse.papyrus.designer.languages.java.codegen.utils.JavaGenUtils

class JavaAttribute {

	// return a list of owned attributes, empty set, if null
	static def getOwnedAttributes(Classifier cl) {
		val attributes = getOwnedAttributesWNull(cl)
		if (attributes == null) {
			emptySet
		}
		else {
			attributes
		}
	}
	
	// return a list of owned attributes, since this is not supported directly on a classifier
	static def getOwnedAttributesWNull(Classifier cl) {	
		if (cl instanceof Class) {
			(cl as Class).ownedAttributes
		} else if (cl instanceof DataType) {
			(cl as DataType).ownedAttributes
		} else if (cl instanceof Interface) {
			(cl as Interface).ownedAttributes
		} else if (cl instanceof Signal) {
			(cl as Signal).ownedAttributes
		} else {
			//Sequence{}
		}
	}

	static def defaultValue(Property attribute) {
		if (attribute.defaultValue != null) {
			" =" + attribute.defaultValue.stringValue()
		}
	}

	static def javaAttributeDeclaration(Property attribute) '''
		«JavaDocumentation.javaElementDoc(attribute)»
		«Modifier.attributeModifiers(attribute)»«JavaGenUtils.javaQualifiedName(attribute.type, attribute.owner)»«Modifier.modArray(attribute)» «attribute.name»«defaultValue(attribute)»;
	'''
}
