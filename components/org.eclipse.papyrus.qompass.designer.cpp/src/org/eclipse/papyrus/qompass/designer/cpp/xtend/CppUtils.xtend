package org.eclipse.papyrus.qompass.designer.cpp.xtend

import org.eclipse.uml2.uml.Property ;
import org.eclipse.uml2.uml.AggregationKind
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.papyrus.C_Cpp.Ptr

public class CppUtils {
	public static def nameRef(Property part) {
		part.name + refOp(part) 	
	}
	
	/**
	 * return the operator for (de-) referencing a part. If the part is
	 * instantiate via the bootloader, it becomes a pointer. If it is
	 * instantiated by the composite itself, it is not a pointer, it will be
	 * instantiated along with the composite
	 *
	 * @param part
	 * @return
	 */
	public static def refOp(Property part) {
		if((part.getAggregation() == AggregationKind.SHARED_LITERAL) || StereotypeUtil.isApplied(part, Ptr)) "->" else "."
	}
	
}