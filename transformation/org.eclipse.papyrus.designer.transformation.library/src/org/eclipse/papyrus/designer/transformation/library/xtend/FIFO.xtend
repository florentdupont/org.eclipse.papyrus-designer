package org.eclipse.papyrus.designer.transformation.library.xtend

import org.eclipse.uml2.uml.DataType
import org.eclipse.papyrus.designer.transformation.core.templates.TemplateInstantiation
import org.eclipse.papyrus.designer.transformation.extensions.ITextTemplate

public class FIFO implements ITextTemplate {
	def activate(DataType datatype) '''
		if (m_size == 0) {
			cerr << "Warning: size of FIFO is not properly configured (size = 0)" << endl;
		}
		m_fifo = new «TemplateInstantiation.context.pkgTemplateParameter("T")»[m_size];
	'''
}