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
 
 package org.eclipse.papyrus.designer.languages.cpp.codegen.xtend

import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.CppGenUtils
import org.eclipse.papyrus.designer.languages.cpp.codegen.utils.Modifier
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.Interface
import org.eclipse.uml2.uml.Property
import org.eclipse.uml2.uml.Signal

/**
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
class CppAttribute {

	//  Loop over attributes. Check that the attribute is not a static const.
	//	Constant static attributes are declared within the class declaration 
	static def CppStaticAttributes(Classifier classifier) {
		var code = '''
			// static attributes (if any)
			«FOR ownedAttribute : getOwnedAttributes(classifier)»
				«IF (ownedAttribute.isStatic)»
					«CppStaticAttributeImplementation(ownedAttribute)»
				«ENDIF»
			«ENDFOR»
		'''
		return code
	}

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

	static def CppStaticAttributeImplementation(Property attribute) '''
		«CppDocumentation.CppElementDoc(attribute)»
		«Modifier.modCVQualifier(attribute)» «CppGenUtils.cppQualifiedName(attribute.type)»«Modifier.modPtr(attribute)»«Modifier.modRef(attribute)»  «attribute.class_.name»::«attribute.name»«Modifier.modArray(attribute)»«defaultValue(attribute)»;
	'''

	static def defaultValue(Property attribute) {
		if ((attribute.defaultValue != null) && attribute.isStatic) {
			"=" + attribute.defaultValue.stringValue()
		}
	}

	static def CppAttributeDeclaration(Property attribute) '''
		«CppDocumentation.CppElementDoc(attribute)»
		«staticValue(attribute)» «Modifier.modCVQualifier(attribute)»«CppGenUtils.cppQualifiedName(attribute.type)»«Modifier.
			modPtr(attribute)»«Modifier.modRef(attribute)» «attribute.name»«Modifier.modArray(attribute)»;
	'''

	static def staticValue(Property attribute) {
		if (attribute.isStatic) 'static'
	}

}
