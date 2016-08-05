package org.eclipse.papyrus.designer.transformation.library.xtend

import org.eclipse.uml2.uml.DataType
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext

class FIFO {
	def activate(DataType datatype) '''
		if (m_size == 0) {
			cerr << "Warning: size of FIFO is not properly configured (size = 0)" << endl;
		}
		m_fifo = new «TransformationContext.pkgTemplateParameter("T")»[m_size];
	'''
}