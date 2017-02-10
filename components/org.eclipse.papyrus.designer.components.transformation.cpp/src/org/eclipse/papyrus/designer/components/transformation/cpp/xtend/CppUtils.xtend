package org.eclipse.papyrus.designer.components.transformation.cpp.xtend

import org.eclipse.uml2.uml.Property ;
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Ptr

public class CppUtils {
	public static def nameRef(Property part) {
		part.name + derefOp(part) 	
	}
	
	/**
	 * return the operator for (de-) referencing a part, depending on
	 * whether it is a pointer or a "normal" attribute
	 *
	 * @param part
	 * @return -> or .
	 */
	public static def derefOp(Property attribute) {
		if(attribute.isRef) "->" else "."
	}

	/**
	 * @return true, if attribute is a pointer.
	 */
	public static def isRef(Property attribute) {
		return (attribute.getAggregation() == AggregationKind.SHARED_LITERAL) || StereotypeUtil.isApplied(attribute, Ptr);
	}
	
	/**
	 * @return reference of an attribute
	 */
	public static def String getRef(Property attribute) '''
		«IF(attribute.isRef)»&«ENDIF»«attribute.name»
	'''
}